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

import com.qaobee.hive.services.impl.EffectiveServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Effective service.
 */
@ProxyGen
@VertxGen
public interface EffectiveService {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.Effective.service";

    /**
     * Create effective service.
     *
     * @param vertx the vertx
     *
     * @return the effective service
     */
    static EffectiveService create(Vertx vertx) {
        return new EffectiveServiceImpl(vertx);
    }

    /**
     * Create proxy effective service.
     *
     * @param vertx   the vertx
     * @param address the address
     *
     * @return the effective service
     */
    static EffectiveService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(EffectiveService.class, vertx, address);
    }

    /**
     * Add.
     *
     * @param effective     the effective
     * @param resultHandler the result handler
     */
    void add(JsonObject effective, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Update.
     *
     * @param effective     the effective
     * @param resultHandler the result handler
     */
    void update(JsonObject effective, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets effective list.
     *
     * @param sandboxId       the sandbox id
     * @param categoryAgeCode the category age code
     * @param resultHandler   the result handler
     */
    void getEffectiveList(String sandboxId, String categoryAgeCode, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets effective.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getEffective(String id, Handler<AsyncResult<JsonObject>> resultHandler);
}
