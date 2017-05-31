/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.dao.StatisticsDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * The type Statistics dao.
 */
public class StatisticsDAOImpl implements StatisticsDAO {
    private static final Logger LOG = LoggerFactory.getLogger(StatisticsDAOImpl.class);
    private static final String TIMER_FIELD = "timer";
    private static final String OWNER_FIELD = "owner";
    private static final String CODE_FIELD = "code";
    private static final String VALUE_FIELD = "value";
    private static final String EVENT_ID_FIELD = "eventId";
    private final MongoDB mongo;
    private final Utils utils;

    /**
     * Instantiates a new Statistics dao.
     *
     * @param mongo the mongo
     * @param utils the utils
     */
    @Inject
    public StatisticsDAOImpl(MongoDB mongo, Utils utils) {
        this.mongo = mongo;
        this.utils = utils;
    }

    @Override
    public JsonObject addBulk(JsonArray stats) {
        long count = 0;
        for (String evtId : collectUniqueIds(stats)) {
            JsonArray eventStats = getListForEvent(evtId);
            if (eventStats.size() == 0) {
                count = pushAllStats(stats, evtId);
            } else {
                count = pushNonDuplicateStats(stats, eventStats);
            }
        }
        return new JsonObject().putNumber("count", count);
    }

    private int pushNonDuplicateStats(JsonArray stats, JsonArray eventStats) {
        List<Integer> statsToAdd = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < stats.size(); i++) {
            boolean found = false;
            for (int j = 0; j < eventStats.size(); j++) {
                ((JsonObject) eventStats.get(j)).removeField("_id");
                if (eventStats.get(j).equals(stats.get(i))) {
                    found = true;
                }
            }
            if (!found) {
                try {
                    addStat(stats.get(i));
                    statsToAdd.add(i);
                    count++;
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return count;
    }

    private long pushAllStats(JsonArray stats, String evtId) {
        long count = 0;
        for (int i = 0; i < stats.size(); i++) {
            try {
                if (evtId.equals(((JsonObject) stats.get(i)).getString(EVENT_ID_FIELD))) {
                    addStat(stats.get(i));
                    count++;
                }
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return count;
    }

    private HashSet<String> collectUniqueIds(JsonArray stats) {
        HashSet<String> events = new HashSet<>();
        for (int i = 0; i < stats.size(); i++) {
            events.add(((JsonObject) stats.get(i)).getString(EVENT_ID_FIELD));
        }
        return events;
    }

    @Override
    public JsonArray getListForEvent(String eventId) {
        return mongo.findByCriterias(new CriteriaBuilder().add("eventId", eventId).get(), null, null, -1, -1, DBCollections.STATS);
    }

    @Override
    public JsonObject addStat(JsonObject stat) throws QaobeeException {
        if (!stat.containsField(TIMER_FIELD) || Integer.valueOf(0).equals(stat.getInteger(TIMER_FIELD))) {
            stat.putNumber(TIMER_FIELD, System.currentTimeMillis());
        }
        stat.putString("_id", mongo.save(stat, DBCollections.STATS));
        return stat;
    }

    @Override
    public JsonArray getListDetailValue(JsonArray listIndicators, JsonArray listOwners, Long startDate, Long endDate, JsonArray values, int limit) throws QaobeeException {
        // $MATCH section
        BasicDBObject dbObjectParent = new BasicDBObject();
        // - code
        BasicDBObject dbObjectChild = new BasicDBObject("$in", listIndicators.toArray());
        dbObjectParent.put(CODE_FIELD, dbObjectChild);
        // - owner
        dbObjectChild = new BasicDBObject("$in", listOwners.toArray());
        dbObjectParent.put(OWNER_FIELD, dbObjectChild);
        // - values
        if (values != null) {
            dbObjectChild = new BasicDBObject("$in", values);
            dbObjectParent.put(VALUE_FIELD, dbObjectChild);
        }
        // - timer
        DBObject o = new BasicDBObject();
        o.put("$gte", startDate);
        o.put("$lt", endDate);
        dbObjectParent.put(TIMER_FIELD, o);
        DBObject match = new BasicDBObject("$match", dbObjectParent);
        dbObjectParent = new BasicDBObject();
        dbObjectParent.put(OWNER_FIELD, 1);
        dbObjectParent.put(TIMER_FIELD, 1);
        DBObject sort = new BasicDBObject("$sort", dbObjectParent);
        List<DBObject> pipelineAggregation;
        if (limit > 0) {
            pipelineAggregation = Arrays.asList(match, sort, new BasicDBObject("$limit", limit));
        } else {
            pipelineAggregation = Arrays.asList(match, sort);
        }
        return mongo.aggregate("_id", pipelineAggregation, DBCollections.STATS);
    }

    @Override
    public JsonArray getStatsGroupedBy(JsonArray listIndicators, JsonArray listOwners, Long startDate, Long endDate, String aggregate, JsonArray value, JsonArray shootSeqId, JsonArray groupBy, JsonArray sortedBy, Integer limit) throws QaobeeException {
        // Aggregate section
        // $MACTH section
        DBObject dbObjectParent = new BasicDBObject();
        // - code
        BasicDBObject dbObjectChild = new BasicDBObject("$in", listIndicators.toArray());
        dbObjectParent.put(CODE_FIELD, dbObjectChild);
        // - owner
        dbObjectChild = new BasicDBObject("$in", listOwners.toArray());
        dbObjectParent.put(OWNER_FIELD, dbObjectChild);
        // - values
        if (value != null) {
            dbObjectChild = new BasicDBObject("$in", value);
            dbObjectParent.put(VALUE_FIELD, dbObjectChild);
        }
        // - shootSeqId
        if (shootSeqId != null) {
            dbObjectChild = new BasicDBObject("$in", shootSeqId);
            dbObjectParent.put("shootSeqId", dbObjectChild);
        }
        // - timer
        DBObject o = new BasicDBObject();
        o.put("$gte", startDate);
        o.put("$lt", endDate);
        dbObjectParent.put(TIMER_FIELD, o);
        DBObject match = new BasicDBObject("$match", dbObjectParent);
        // $GROUP section
        dbObjectParent = new BasicDBObject();
        dbObjectChild = new BasicDBObject();
        // - _id - List of field for id's group step
        if (groupBy != null) {
            for (Object field : groupBy) {
                dbObjectChild.append((String) field, "$" + field);
            }
        }
        dbObjectParent.put("_id", dbObjectChild);
        // - average
        switch (aggregate) {
            case "SUM":
                dbObjectChild = new BasicDBObject("$sum", "$value");
                dbObjectParent.put(VALUE_FIELD, dbObjectChild);
                break;
            case "AVG":
                dbObjectChild = new BasicDBObject("$avg", "$value");
                dbObjectParent.put(VALUE_FIELD, dbObjectChild);
                break;
            default: // COUNT
                dbObjectChild = new BasicDBObject("$sum", 1);
                dbObjectParent.put(VALUE_FIELD, dbObjectChild);
                break;
        }
        DBObject group = new BasicDBObject("$group", dbObjectParent);
        // $SORT section
        dbObjectParent = new BasicDBObject();
        if (sortedBy != null) {
            for (Object item : sortedBy) {
                JsonObject field = (JsonObject) item;
                dbObjectParent.put(field.getString("fieldName"), field.getInteger("sortOrder"));
            }
        } else {
            dbObjectParent.put("_id", 1);
        }
        DBObject sort = new BasicDBObject("$sort", dbObjectParent);
        List<DBObject> pipelineAggregation;
        pipelineAggregation = limit > 0 ? Arrays.asList(match, group, sort, new BasicDBObject("$limit", limit)) : Arrays.asList(match, group, sort);
        return mongo.aggregate("_id", pipelineAggregation, DBCollections.STATS);
    }
}
