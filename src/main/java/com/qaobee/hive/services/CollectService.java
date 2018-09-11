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

import com.qaobee.hive.services.impl.CollectServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * The interface Collect service.
 */
@ProxyGen
@VertxGen
public interface CollectService {
    /**
     * Create collect service.
     *
     * @param vertx the vertx
     * @return the collect service
     */
    static CollectService create(Vertx vertx) {
        return new CollectServiceImpl(vertx);
    }

    /**
     * Create proxy collect service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the collect service
     */
    static CollectService createProxy(Vertx vertx, String address) {
        return new CollectServiceVertxEBProxy(vertx, address);
    }

    /**
     * Get.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void get(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Update.
     *
     * @param collect       the collect
     * @param currentUserId the current user id
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void update(JsonObject collect, String currentUserId, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Add.
     *
     * @param collect       the collect
     * @param currentUserId the current user id
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void add(JsonObject collect, String currentUserId, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list.
     *
     * @param params        the params
     * @param resultHandler the result handler
     */
    void getList(JsonObject params, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Delete collect by event id.
     *
     * @param eventId       the event id
     * @param resultHandler the result handler
     */
    void deleteCollectByEventId(String eventId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Delete collect by id.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void deleteCollectById(String id, Handler<AsyncResult<JsonObject>> resultHandler);
}
