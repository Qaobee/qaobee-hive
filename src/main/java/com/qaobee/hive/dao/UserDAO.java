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
import org.vertx.java.core.json.JsonObject;

/**
 * The interface User dao.
 */
public interface UserDAO {
    /**
     * Update avatar json object.
     *
     * @param uid      the uid
     * @param filename the filename
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject updateAvatar(String uid, String filename) throws QaobeeException;

    /**
     * Generate bill pdf json object.
     *
     * @param user   the user
     * @param payId  the pay id
     * @param planId the plan id
     * @param locale the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject generateBillPDF(User user, String payId, String planId, String locale) throws QaobeeException;

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
     * @throws QaobeeException the qaobee exception
     */
    JsonObject updateUser(JsonObject user) throws QaobeeException;
}
