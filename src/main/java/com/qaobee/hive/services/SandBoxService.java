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

import com.qaobee.hive.services.impl.SandBoxServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.List;

/**
 * The interface Sand box service.
 */
@ProxyGen
@VertxGen
public interface SandBoxService {

    /**
     * Create sand box service.
     *
     * @param vertx the vertx
     *
     * @return the sand box service
     */
    static SandBoxService create(Vertx vertx) {
        return new SandBoxServiceImpl(vertx);
    }

    /**
     * Create proxy sand box service.
     *
     * @param vertx   the vertx
     * @param address the address
     *
     * @return the sand box service
     */
    static SandBoxService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(SandBoxService.class, vertx, address);
    }

    /**
     * Gets sandbox by id.
     *
     * @param sandboxId     the sandbox id
     * @param resultHandler the result handler
     */
    void getSandboxById(String sandboxId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets enriched sandbox.
     *
     * @param sandbox       the sandbox
     * @param resultHandler the result handler
     */
    void getEnrichedSandbox(JsonObject sandbox, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Update sandbox.
     *
     * @param sandbox       the sandbox
     * @param resultHandler the result handler
     */
    void updateSandbox(JsonObject sandbox, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list by owner.
     *
     * @param usersIds      the users ids
     * @param loggedUserId  the logged user id
     * @param resultHandler the result handler
     */
    void getListByOwner(List<String> usersIds, String loggedUserId, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets by owner.
     *
     * @param activityId    the activity id
     * @param userId        the user id
     * @param resultHandler the result handler
     */
    void getByOwner(String activityId, String userId, Handler<AsyncResult<JsonObject>> resultHandler);

}
