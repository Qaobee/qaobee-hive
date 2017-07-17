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

import com.qaobee.hive.services.impl.ChampionshipServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Championship service.
 */
@ProxyGen
@VertxGen
public interface ChampionshipService {

    /**
     * Create championship service.
     *
     * @param vertx the vertx
     * @return the championship service
     */
    static ChampionshipService create(Vertx vertx) {
        return new ChampionshipServiceImpl(vertx);
    }

    /**
     * Create proxy championship service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the championship service
     */
    static ChampionshipService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(ChampionshipService.class, vertx, address);
    }

    /**
     * Update championship.
     *
     * @param championship  the championship
     * @param resultHandler the result handler
     */
    void updateChampionship(JsonObject championship, Handler<AsyncResult<String>> resultHandler);

    /**
     * Add championship.
     *
     * @param championship  the championship
     * @param resultHandler the result handler
     */
    void addChampionship(JsonObject championship, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets championship.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getChampionship(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list championships.
     *
     * @param params        the params
     * @param resultHandler the result handler
     */
    void getListChampionships(JsonObject params, Handler<AsyncResult<JsonArray>> resultHandler);
}
