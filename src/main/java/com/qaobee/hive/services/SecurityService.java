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

import com.qaobee.hive.services.impl.SecurityServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * The interface Security dao.
 */
@ProxyGen
@VertxGen
public interface SecurityService {

    /**
     * Create security service.
     *
     * @param vertx the vertx
     * @return the security service
     */
    static SecurityService create(Vertx vertx) {
        return new SecurityServiceImpl(vertx);
    }

    /**
     * Create proxy security service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the security service
     */
    static SecurityService createProxy(Vertx vertx, String address) {
        return new SecurityServiceVertxEBProxy(vertx, address);
    }

    /**
     * Login by token.
     *
     * @param login         the login
     * @param mobileToken   the mobile token
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void loginByToken(String login, String mobileToken, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Maj user account validity.
     *
     * @param user          the user
     * @param resultHandler the result handler
     */
    void majUserAccountValidity(JsonObject user, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Password reset.
     *
     * @param reCaptchaChallenge   the re captcha challenge
     * @param id                   the id
     * @param code                 the code
     * @param passwd               the passwd
     * @param byPassActivationCode the by pass activation code
     * @param resultHandler        the result handler
     */
    void passwordReset(String reCaptchaChallenge, String id, String code, String passwd, boolean byPassActivationCode, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Password renew check.
     *
     * @param id            the id
     * @param code          the code
     * @param resultHandler the result handler
     */
    void passwordRenewCheck(String id, String code, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Password renew.
     *
     * @param login         the login
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void passwordRenew(String login, String locale, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Logout.
     *
     * @param token         the token
     * @param resultHandler the result handler
     */
    void logout(String token, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Login.
     *
     * @param login         the login
     * @param password      the password
     * @param mobileToken   the mobile token
     * @param pushId        the push id
     * @param deviceOS      the device os
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void login(String login, String password, String mobileToken, String pushId, String deviceOS, String locale, Handler<AsyncResult<JsonObject>> resultHandler);
}
