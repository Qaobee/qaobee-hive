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

import com.qaobee.hive.services.impl.SignupServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * The interface Signup dao.
 */
@ProxyGen
@VertxGen
public interface SignupService {

    /**
     * Create structure.
     *
     * @param vertx the vertx
     * @return the structure
     */
    static SignupService create(Vertx vertx) {
        return new SignupServiceImpl(vertx);
    }

    /**
     * Create proxy signup service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the signup service
     */
    static SignupService createProxy(Vertx vertx, String address) {
        return new SignupServiceVertxEBProxy(vertx, address);
    }

    /**
     * First connection check.
     *
     * @param id             the id
     * @param activationCode the activation code
     * @param locale         the locale
     * @param resultHandler  the result handler
     */
    void firstConnectionCheck(String id, String activationCode, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Account check.
     *
     * @param id             the id
     * @param activationCode the activation code
     * @param resultHandler  the result handler
     */
    void accountCheck(String id, String activationCode, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Register.
     *
     * @param reCaptchaChallenge the re captcha challenge
     * @param user               the user
     * @param locale             the locale
     * @param resultHandler      the result handler
     */
    void register(String reCaptchaChallenge, JsonObject user, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Resend mail.
     *
     * @param login         the login
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void resendMail(String login, String locale, Handler<AsyncResult<Void>> resultHandler);

    /**
     * Send register mail.
     *
     * @param user          the user
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void sendRegisterMail(JsonObject user, String locale, Handler<AsyncResult<Void>> resultHandler);

    /**
     * Add structure to sandbox.
     *
     * @param sandboxId     the sandbox id
     * @param structure     the structure
     * @param resultHandler the result handler
     */
    void addStructureToSandbox(String sandboxId, JsonObject structure, Handler<AsyncResult<Void>> resultHandler);
}
