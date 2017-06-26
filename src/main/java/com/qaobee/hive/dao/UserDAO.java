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

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

/**
 * The interface User dao.
 */
public interface UserDAO {

    /**
     * Generate profile pdf json object.
     *
     * @param user   the user
     * @param locale the locale
     * @return the json object
     */
    JsonObject generateProfilePDF(User user, String locale);

    /**
     * Update user json object.
     *
     * @param user the user
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> updateUser(JsonObject user);

    /**
     * Check user informations boolean.
     *
     * @param user   the user
     * @param locale the locale
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    boolean checkUserInformations(User user, String locale) throws QaobeeException;

    /**
     * Existing login.
     *
     * @param login the login
     * @return the boolean
     */
    Promise<Boolean, QaobeeException, Integer> existingLogin(String login);

    /**
     * Permet de vérifier une adresse e-mail.
     *
     * @param email  the email
     * @param locale the locale
     * @throws QaobeeException the qaobee exception
     */
    void testEmail(String email, String locale) throws QaobeeException;

    /**
     * Prepare upsert.
     *
     * @param u a user
     * @return a prepared person for upsert
     * @throws QaobeeException the qaobee exception
     */
    User prepareUpsert(User u) throws QaobeeException;

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    Promise<JsonObject, QaobeeException, Integer> getUser(String id);

    /**
     * Gets user by login.
     *
     * @param login the login
     * @return the user by login
     */
    Promise<JsonObject, QaobeeException, Integer> getUserByLogin(String login, String locale);

    /**
     * Gets user info.
     *
     * @param id the id
     * @return the user info
     */
    Promise<JsonObject, QaobeeException, Integer> getUserInfo(String id);

    /**
     * Gets meta.
     *
     * @param sandboxId the sandbox id
     * @return the meta
     */
    Promise<JsonObject, QaobeeException, Integer> getMeta(String sandboxId);
}