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

package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

/**
 * Created by b3605 on 18/07/16.
 *
 * @author Xavier MARIN (b3605)
 */
public interface SecurityDAO {
    /**
     * Login by token json object.
     *
     * @param login       the login
     * @param mobileToken the mobile token
     * @param locale      the locale
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> loginByToken(String login, String mobileToken, String locale);

    /**
     * Password reset boolean.
     *
     * @param reCaptchaChallenge   the re captcha challenge
     * @param id                   the id
     * @param code                 the code
     * @param passwd               the passwd
     * @param byPassActivationCode the by pass activation code
     * @return the boolean
     */
    Promise<Boolean, QaobeeException, Integer> passwordReset(String reCaptchaChallenge, String id, String code, String passwd, boolean byPassActivationCode);

    /**
     * Password renew check json object.
     *
     * @param id   the id
     * @param code the code
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> passwordRenewCheck(String id, String code);

    /**
     * Password renew boolean.
     *
     * @param login  the login
     * @param locale the locale
     * @return the boolean
     */
    Promise<Boolean, QaobeeException, Integer> passwordRenew(String login, String locale);

    /**
     * Logout boolean.
     *
     * @param token the token
     * @return the boolean
     */
    Promise<Boolean, QaobeeException, Integer> logout(String token);

    /**
     * Login json object.
     *
     * @param login       the login
     * @param password    the password
     * @param mobileToken the mobile token
     * @param pushId      the push id
     * @param deviceOS    the device os
     * @param locale      the locale
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> login(String login, String password, String mobileToken, String pushId, String deviceOS, String locale);
}
