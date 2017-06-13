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
     * @throws QaobeeException the qaobee exception
     */
    JsonObject loginByToken(String login, String mobileToken, String locale) throws QaobeeException;

    /**
     * Password reset boolean.
     *
     * @param reCaptchaChallenge   the re captcha challenge
     * @param id                   the id
     * @param code                 the code
     * @param passwd               the passwd
     * @param byPassActivationCode the by pass activation code
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    boolean passwordReset(String reCaptchaChallenge, String id, String code, String passwd, boolean byPassActivationCode) throws QaobeeException;

    /**
     * Password renew check json object.
     *
     * @param id   the id
     * @param code the code
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject passwordRenewCheck(String id, String code) throws QaobeeException;

    /**
     * Password renew boolean.
     *
     * @param login  the login
     * @param locale the locale
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    boolean passwordRenew(String login, String locale) throws QaobeeException;

    /**
     * Logout boolean.
     *
     * @param token the token
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    boolean logout(String token) throws QaobeeException;

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
     * @throws QaobeeException the qaobee exception
     */
    JsonObject login(String login, String password, String mobileToken, String pushId, String deviceOS, String locale) throws QaobeeException;

}
