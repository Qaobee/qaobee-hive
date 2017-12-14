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

import com.qaobee.hive.services.impl.PersonServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * The interface Person service.
 */
@ProxyGen
@VertxGen
public interface PersonService {

    /**
     * Create person service.
     *
     * @param vertx the vertx
     *
     * @return the person service
     */
    static PersonService create(Vertx vertx) {
        return new PersonServiceImpl(vertx);
    }

    /**
     * Create proxy person service.
     *
     * @param vertx   the vertx
     * @param address the address
     *
     * @return the person service
     */
    static PersonService createProxy(Vertx vertx, String address) {
        return new PersonServiceVertxEBProxy(vertx, address);
    }

    /**
     * Gets person list by sandbox.
     *
     * @param sandboxId     the sandbox id
     * @param resultHandler the result handler
     */
    void getPersonListBySandbox(String sandboxId, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets person list.
     *
     * @param listId        the list id
     * @param listfield     the listfield
     * @param resultHandler the result handler
     */
    void getPersonList(JsonArray listId, JsonArray listfield, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Update person.
     *
     * @param person        the person
     * @param userId        the user id
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void updatePerson(JsonObject person, String userId, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets person.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getPerson(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Add person.
     *
     * @param person        the person
     * @param userId        the user id
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void addPerson(JsonObject person, String userId, String locale, Handler<AsyncResult<JsonObject>> resultHandler);
}
