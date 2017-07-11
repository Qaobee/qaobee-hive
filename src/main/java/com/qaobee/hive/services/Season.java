/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */

package com.qaobee.hive.services;

import com.qaobee.hive.services.impl.SeasonImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Season.
 */
@ProxyGen
@VertxGen
public interface Season {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.Season.service";

    /**
     * Create season.
     *
     * @param vertx the vertx
     * @return the season
     */
    static Season create(Vertx vertx) {
        return new SeasonImpl(vertx);
    }

    /**
     * Create proxy season.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the season
     */
    static Season createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(Season.class, vertx, address);
    }

    /**
     * Gets current season.
     *
     * @param activityId    the activity id
     * @param countryId     the country id
     * @param resultHandler the result handler
     */
    void getCurrentSeason(String activityId, String countryId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list by activity.
     *
     * @param activityId    the activity id
     * @param countryId     the country id
     * @param resultHandler the result handler
     */
    void getListByActivity(String activityId, String countryId, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets season.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getSeason(String id, Handler<AsyncResult<JsonObject>> resultHandler);
}
