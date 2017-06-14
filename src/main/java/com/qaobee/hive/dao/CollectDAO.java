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
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

/**
 * The interface Collect dao.
 */
public interface CollectDAO {
    /**
     * Get json object.
     *
     * @param id the id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonObject, QaobeeException, Integer> get(String id) throws QaobeeException;

    /**
     * Update json object.
     *
     * @param collect       the collect
     * @param currentUserId the current user id
     * @param locale        the locale
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> update(JsonObject collect, String currentUserId, String locale);

    /**
     * Add json object.
     *
     * @param collect       the collect
     * @param currentUserId the current user id
     * @param locale        the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonObject, QaobeeException, Integer> add(JsonObject collect, String currentUserId, String locale) throws QaobeeException;

    /**
     * Gets list.
     *
     * @param params the params
     * @return the list
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonArray, QaobeeException, Integer> getList(JsonObject params) throws QaobeeException;
}
