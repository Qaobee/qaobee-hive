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

import com.qaobee.hive.dao.StatisticsDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
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
    private DeferredManager dm = new DefaultDeferredManager();

    /**
     * Instantiates a new Statistics dao.
     *
     * @param mongo the mongo
     */
    @Inject
    public StatisticsDAOImpl(MongoDB mongo) {
        this.mongo = mongo;
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> addBulk(JsonArray stats) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        final long[] count = {0};
        List<Promise> promises = new ArrayList<>();
        List<Promise> promises2 = new ArrayList<>();
        for (String evtId : collectUniqueIds(stats)) {
            promises.add(getListForEvent(evtId));
        }
        dm.when(promises.toArray(new Promise[promises.size()])).done(rs -> {
            rs.forEach(eventStats -> {
                if (((JsonObject) eventStats.getResult()).getJsonArray("stats").size() == 0) {
                    promises2.add(pushAllStats(stats, ((JsonObject) eventStats.getResult()).getString("evtId")));
                } else {
                    promises2.add(pushNonDuplicateStats(stats, ((JsonObject) eventStats.getResult()).getJsonArray("stats")));
                }
            });
            dm.when(promises2.toArray(new Promise[promises2.size()])).done(counts -> {
                counts.forEach(c -> count[0] += (Integer) c.getResult());
                deferred.resolve(new JsonObject().put("count", count[0]));
            });
        }).fail(e -> {
            LOG.error(((Throwable) e.getReject()).getMessage());
            deferred.reject(((QaobeeException) e.getReject()));
        });
        return deferred.promise();
    }

    private Promise<Integer, QaobeeException, Integer> pushNonDuplicateStats(JsonArray stats, JsonArray eventStats) {
        Deferred<Integer, QaobeeException, Integer> deferred = new DeferredObject<>();
        List<Promise> promises = new ArrayList<>();
        stats.forEach(s -> {
            boolean found = false;
            for (int j = 0; j < eventStats.size(); j++) {
                eventStats.getJsonObject(j).remove("_id");
                if (eventStats.getJsonObject(j).equals(s)) {
                    found = true;
                }
            }
            if (!found) {
                promises.add(addStat((JsonObject) s));
            }
        });
        count(promises, deferred);
        return deferred.promise();
    }

    private void count(List<Promise> promises, Deferred<Integer, QaobeeException, Integer> deferred) {
        dm.when(promises.toArray(new Promise[promises.size()])).done(rs -> {
            final int[] count = {0};
            rs.forEach(r -> count[0]++);
            deferred.resolve(count[0]);
        }).fail(e -> {
            LOG.error(((Throwable) e.getReject()).getMessage());
            deferred.reject(((QaobeeException) e.getReject()));
        });
    }

    private Promise<Integer, QaobeeException, Integer> pushAllStats(JsonArray stats, String evtId) {
        Deferred<Integer, QaobeeException, Integer> deferred = new DeferredObject<>();
        List<Promise> promises = new ArrayList<>();
        stats.forEach(s -> {
            if (evtId.equals(((JsonObject) s).getString(EVENT_ID_FIELD))) {
                promises.add(addStat((JsonObject) s));
            }
        });
        count(promises, deferred);
        return deferred.promise();
    }

    private static HashSet<String> collectUniqueIds(JsonArray stats) {
        HashSet<String> events = new HashSet<>();
        for (int i = 0; i < stats.size(); i++) {
            events.add(stats.getJsonObject(i).getString(EVENT_ID_FIELD));
        }
        return events;
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getListForEvent(String eventId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.findByCriterias(new CriteriaBuilder().add("eventId", eventId).get(), null, null, -1, -1, DBCollections.STATS).done(res ->
                deferred.resolve(new JsonObject().put("eventId", eventId).put("stats", res))
        ).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> addStat(JsonObject stat) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (!stat.containsKey(TIMER_FIELD) || Integer.valueOf(0).equals(stat.getInteger(TIMER_FIELD))) {
            stat.put(TIMER_FIELD, System.currentTimeMillis());
        }
        mongo.upsert(stat, DBCollections.STATS).done(id -> {
            stat.put("_id", id);
            deferred.resolve(stat);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getListDetailValue(JsonArray listIndicators, JsonArray listOwners, Long startDate, Long endDate, JsonArray values, int limit) {
        // $MATCH section
        JsonObject dbObjectParent = new JsonObject()
                .put(CODE_FIELD, new JsonObject().put("$in", listIndicators))
                .put(OWNER_FIELD, new JsonObject().put("$in", listOwners));
        // - values
        if (values != null) {
            dbObjectParent.put(VALUE_FIELD, new JsonObject().put("$in", values));
        }
        // - timer
        JsonObject o = new JsonObject().put("$gte", startDate).put("$lt", endDate);
        dbObjectParent.put(TIMER_FIELD, o);
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        dbObjectParent = new JsonObject().put(OWNER_FIELD, 1).put(TIMER_FIELD, 1);
        JsonObject sort = new JsonObject().put("$sort", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match).add(sort);
        if (limit > 0) {
            pipelineAggregation.add(new JsonObject().put("$limit", limit));
        }
        return mongo.aggregate(pipelineAggregation, DBCollections.STATS);
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getStatsGroupedBy(JsonArray listIndicators, JsonArray listOwners, Long startDate, Long endDate, String aggregate, JsonArray value, JsonArray shootSeqId, JsonArray groupBy, JsonArray sortedBy, Integer limit) {
        // Aggregate section
        // $MACTH section
        JsonObject dbObjectParent = new JsonObject()
                .put(CODE_FIELD, new JsonObject().put("$in", listIndicators))
                .put(OWNER_FIELD, new JsonObject().put("$in", listOwners));
        // - values
        if (value != null) {
            dbObjectParent.put(VALUE_FIELD, new JsonObject().put("$in", value));
        }
        // - shootSeqId
        if (shootSeqId != null) {
            dbObjectParent.put("shootSeqId", new JsonObject().put("$in", shootSeqId));
        }
        // - timer
        JsonObject o = new JsonObject().put("$gte", startDate).put("$lt", endDate);
        dbObjectParent.put(TIMER_FIELD, o);
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        // $GROUP section
        dbObjectParent = new JsonObject();
        JsonObject dbObjectChild = new JsonObject();
        // - _id - List of field for id's group step
        if (groupBy != null) {
            for (Object field : groupBy) {
                dbObjectChild.put((String) field, "$" + field);
            }
        }
        dbObjectParent.put("_id", dbObjectChild);
        // - average
        switch (aggregate) {
            case "SUM":
                dbObjectParent.put(VALUE_FIELD, new JsonObject().put("$sum", "$value"));
                break;
            case "AVG":
                dbObjectParent.put(VALUE_FIELD, new JsonObject().put("$avg", "$value"));
                break;
            default: // COUNT
                dbObjectParent.put(VALUE_FIELD, new JsonObject().put("$sum", 1));
                break;
        }
        JsonObject group = new JsonObject().put("$group", dbObjectParent);
        // $SORT section
        dbObjectParent = new JsonObject();
        if (sortedBy != null) {
            for (Object item : sortedBy) {
                JsonObject field = (JsonObject) item;
                dbObjectParent.put(field.getString("fieldName"), field.getInteger("sortOrder"));
            }
        } else {
            dbObjectParent.put("_id", 1);
        }
        JsonObject sort = new JsonObject().put("$sort", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match).add(group).add(sort);
        if (limit > 0) {
            pipelineAggregation.add(new JsonObject().put("$limit", limit));
        }
        return mongo.aggregate(pipelineAggregation, DBCollections.STATS);
    }
}
