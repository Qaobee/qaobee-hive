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

import com.qaobee.hive.services.impl.UserServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface User service.
 */
@ProxyGen
@VertxGen
public interface UserService {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.User.service";

    /**
     * Create user service.
     *
     * @param vertx the vertx
     *
     * @return the user service
     */
    static UserService create(Vertx vertx) {
        return new UserServiceImpl(vertx);
    }

    /**
     * Create proxy user service.
     *
     * @param vertx   the vertx
     * @param address the address
     *
     * @return the user service
     */
    static UserService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(UserService.class, vertx, address);
    }

    /**
     * Generate profile pdf.
     *
     * @param user          the user
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void generateProfilePDF(JsonObject user, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Update user.
     *
     * @param user          the user
     * @param resultHandler the result handler
     */
    void updateUser(JsonObject user, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Check user informations.
     *
     * @param user          the user
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void checkUserInformations(JsonObject user, String locale, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Existing login.
     *
     * @param login         the login
     * @param resultHandler the result handler
     */
    void existingLogin(String login, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Test email.
     *
     * @param email         the email
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void testEmail(String email, String locale, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Prepare upsert.
     *
     * @param u             the u
     * @param resultHandler the result handler
     */
    void prepareUpsert(JsonObject u, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets user.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getUser(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets user by login.
     *
     * @param login         the login
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void getUserByLogin(String login, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets user info.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getUserInfo(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets meta.
     *
     * @param sandboxId     the sandbox id
     * @param resultHandler the result handler
     */
    void getMeta(String sandboxId, Handler<AsyncResult<JsonObject>> resultHandler);
}