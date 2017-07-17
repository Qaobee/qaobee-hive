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

package com.qaobee.hive.services;


import com.qaobee.hive.services.impl.StatisticsServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Statistics service.
 */
@ProxyGen
@VertxGen
public interface StatisticsService {

    /**
     * Create statistics service.
     *
     * @param vertx the vertx
     *
     * @return the statistics service
     */
    static StatisticsService create(Vertx vertx) {
        return new StatisticsServiceImpl(vertx);
    }

    /**
     * Create proxy statistics service.
     *
     * @param vertx   the vertx
     * @param address the address
     *
     * @return the statistics service
     */
    static StatisticsService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(StatisticsService.class, vertx, address);
    }

    /**
     * Add bulk.
     *
     * @param stats         the stats
     * @param resultHandler the result handler
     */
    void addBulk(JsonArray stats, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list for event.
     *
     * @param eventId       the event id
     * @param resultHandler the result handler
     */
    void getListForEvent(String eventId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Add stat.
     *
     * @param stat          the stat
     * @param resultHandler the result handler
     */
    void addStat(JsonObject stat, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list detail value.
     *
     * @param listIndicators the list indicators
     * @param listOwners     the list owners
     * @param startDate      the start date
     * @param endDate        the end date
     * @param values         the values
     * @param limit          the limit
     * @param resultHandler  the result handler
     */
    void getListDetailValue(JsonArray listIndicators, JsonArray listOwners, Long startDate, Long endDate, JsonArray values, int limit, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets stats grouped by.
     *
     * @param listIndicators the list indicators
     * @param listOwners     the list owners
     * @param startDate      the start date
     * @param endDate        the end date
     * @param aggregate      the aggregate
     * @param value          the value
     * @param shootSeqId     the shoot seq id
     * @param groupBy        the group by
     * @param sortedBy       the sorted by
     * @param limit          the limit
     * @param resultHandler  the result handler
     */
    void getStatsGroupedBy(JsonArray listIndicators, JsonArray listOwners, Long startDate, // NOSONAR
                                                                   Long endDate, String aggregate, JsonArray value, JsonArray shootSeqId,
                                                                   JsonArray groupBy, JsonArray sortedBy, Integer limit, Handler<AsyncResult<JsonArray>> resultHandler);
}
