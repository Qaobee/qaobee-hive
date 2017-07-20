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

package com.qaobee.hive.services.impl;

import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.services.StatisticsService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import io.vertx.core.*;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * The type Statistics service.
 */
@ProxyService(address = "vertx.Statistics.service", iface = StatisticsService.class)
public class StatisticsServiceImpl implements StatisticsService {
    private static final String TIMER_FIELD = "timer";
    private static final String OWNER_FIELD = "owner";
    private static final String CODE_FIELD = "code";
    private static final String VALUE_FIELD = "value";
    private static final String EVENT_ID_FIELD = "eventId";
    private static final String STAT_FIELD = "stats";
    @Inject
    private MongoDB mongo;

    /**
     * Instantiates a new Statistics service.
     *
     * @param vertx the vertx
     */
    public StatisticsServiceImpl(Vertx vertx) { // NOSONAR
        super();
    }

    private Future<Integer> pushNonDuplicateStats(JsonArray stats, JsonArray eventStats) {
        Future<Integer> deferred = Future.future();
        List<Future> promises = new ArrayList<>();
        stats.forEach(s -> {
            boolean found = false;
            for (int j = 0; j < eventStats.size(); j++) {
                eventStats.getJsonObject(j).remove("_id");
                if (eventStats.getJsonObject(j).equals(s)) {
                    found = true;
                }
            }
            if (!found) {
                Future<JsonObject> f = Future.future();
                addStat((JsonObject) s, ar -> {
                    if (ar.succeeded()) {
                        f.complete(ar.result());
                    } else {
                        f.fail(ar.cause());
                    }
                });
                promises.add(f);
            }
        });
        count(promises, deferred);
        return deferred;
    }

    private void count(List<Future> promises, Future<Integer> deferred) {
        CompositeFuture.all(promises).setHandler(rs -> {
            if (rs.succeeded()) {
                final int[] count = {0};
                rs.result().list().forEach(r -> count[0]++);
                deferred.complete(count[0]);
            } else {
                deferred.fail(rs.cause());
            }
        });
    }

    private Future<Integer> pushAllStats(JsonArray stats, String evtId) {
        Future<Integer> deferred = Future.future();
        List<Future> promises = new ArrayList<>();
        stats.forEach(s -> {
            if (evtId.equals(((JsonObject) s).getString(EVENT_ID_FIELD))) {
                Future<JsonObject> f = Future.future();
                addStat((JsonObject) s, ar -> {
                    if (ar.succeeded()) {
                        f.complete(ar.result());
                    } else {
                        f.fail(ar.cause());
                    }
                });
                promises.add(f);
            }
        });
        count(promises, deferred);
        return deferred;
    }

    private static HashSet<String> collectUniqueIds(JsonArray stats) {
        HashSet<String> events = new HashSet<>();
        for (int i = 0; i < stats.size(); i++) {
            events.add(stats.getJsonObject(i).getString(EVENT_ID_FIELD));
        }
        return events;
    }

    @Override
    public void addBulk(JsonArray stats, Handler<AsyncResult<JsonObject>> resultHandler) {
        final int[] count = {0};
        List<Future> promises = new ArrayList<>();
        List<Future> promises2 = new ArrayList<>();
        for (String evtId : collectUniqueIds(stats)) {
            Future<JsonObject> f = Future.future();
            promises.add(f);
            getListForEvent(evtId, ar -> {
                if (ar.succeeded()) {
                    f.complete(ar.result());
                } else {
                    f.fail(ar.cause());
                }
            });
        }
        CompositeFuture.all(promises).setHandler(rs -> {
            if (rs.succeeded()) {
                rs.result().list().forEach(eventStats -> {
                    if (((JsonObject) eventStats).getJsonArray(STAT_FIELD).size() == 0) {
                        promises2.add(pushAllStats(stats, ((JsonObject) eventStats).getString(EVENT_ID_FIELD)));
                    } else {
                        promises2.add(pushNonDuplicateStats(stats, ((JsonObject) eventStats).getJsonArray(STAT_FIELD)));
                    }
                });
                CompositeFuture.all(promises2).setHandler(counts -> {
                    counts.result().list().forEach(c -> count[0] = count[0] + (int) c);
                    resultHandler.handle(Future.succeededFuture(new JsonObject().put("count", count[0])));
                });
            } else {
                resultHandler.handle(Future.failedFuture(rs.cause()));
            }
        });
    }

    @Override
    public void getListForEvent(String eventId, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.findByCriterias(new JsonObject().put(EVENT_ID_FIELD, eventId), new CriteriaOption(), DBCollections.STATS, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(new JsonObject().put(EVENT_ID_FIELD, eventId).put(STAT_FIELD, res.result())));
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void addStat(JsonObject stat, Handler<AsyncResult<JsonObject>> resultHandler) {
        if (!stat.containsKey(TIMER_FIELD) || Integer.valueOf(0).equals(stat.getInteger(TIMER_FIELD))) {
            stat.put(TIMER_FIELD, System.currentTimeMillis());
        }
        mongo.upsert(stat, DBCollections.STATS, upsertRes -> {
            if (upsertRes.succeeded()) {
                stat.put("_id", upsertRes.result());
                resultHandler.handle(Future.succeededFuture(stat));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }

    @Override
    public void getListDetailValue(JsonArray listIndicators, JsonArray listOwners, Long startDate, Long endDate, JsonArray values, int limit, Handler<AsyncResult<JsonArray>> resultHandler) {
        // $MATCH section
        JsonObject dbObjectParent = new JsonObject()
                .put(CODE_FIELD, new JsonObject().put("$in", listIndicators))
                .put(OWNER_FIELD, new JsonObject().put("$in", listOwners));
        // - values
        if (!values.isEmpty()) {
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
        mongo.aggregate(pipelineAggregation, DBCollections.STATS, resultHandler);
    }

    @Override
    public void getStatsGroupedBy(JsonArray listIndicators, JsonArray listOwners, Long startDate, Long endDate, String aggregate, JsonArray value, JsonArray shootSeqId, JsonArray groupBy, JsonArray sortedBy, Integer limit, Handler<AsyncResult<JsonArray>> resultHandler) {
        // Aggregate section
        // $MACTH section
        JsonObject dbObjectParent = new JsonObject()
                .put(CODE_FIELD, new JsonObject().put("$in", listIndicators))
                .put(OWNER_FIELD, new JsonObject().put("$in", listOwners));
        // - values
        if (!value.isEmpty()) {
            dbObjectParent.put(VALUE_FIELD, new JsonObject().put("$in", value));
        }
        // - shootSeqId
        if (!shootSeqId.isEmpty()) {
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
        for (Object field : groupBy) {
            dbObjectChild.put((String) field, "$" + field);
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
        if (!sortedBy.isEmpty()) {
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
        mongo.aggregate(pipelineAggregation, DBCollections.STATS, resultHandler);
    }
}
