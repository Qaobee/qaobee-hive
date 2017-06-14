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

package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

/**
 * The interface Signup dao.
 */
public interface SignupDAO {
    /**
     * Finalize signup json object.
     *
     * @param jsonUser       the json user
     * @param activationCode the activation code
     * @param activityId     the activity id
     * @param structure      the structure
     * @param categoryAge    the category age
     * @param countryId      the country id
     * @param locale         the locale
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> finalizeSignup(JsonObject jsonUser, String activationCode, String activityId, JsonObject structure, JsonObject categoryAge, String countryId, String locale);

    /**
     * First connection check json object.
     *
     * @param id             the id
     * @param activationCode the activation code
     * @param locale         the locale
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> firstConnectionCheck(String id, String activationCode, String locale);

    /**
     * Account check boolean.
     *
     * @param id             the id
     * @param activationCode the activation code
     * @return the boolean
     */
    Promise<Boolean, QaobeeException, Integer> accountCheck(String id, String activationCode);

    /**
     * Register json object.
     *
     * @param reCaptchaChallenge the re captcha
     * @param user               the user
     * @param locale             the locale
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> register(String reCaptchaChallenge, JsonObject user, String locale);

    /**
     * Resend mail.
     *
     * @param login  the login
     * @param locale the locale
     * @return the status
     */
    Promise<Boolean, QaobeeException, Integer> resendMail(String login, String locale);

    /**
     * Send register mail.
     *
     * @param user   the user
     * @param locale the locale
     * @throws QaobeeException the qaobee exception
     */
    void sendRegisterMail(JsonObject user, String locale) throws QaobeeException;
}
