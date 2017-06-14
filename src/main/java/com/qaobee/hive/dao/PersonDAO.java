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
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

/**
 * The interface Person dao.
 */
public interface PersonDAO {
    /**
     * Gets person list by sandbox.
     *
     * @param sandboxId the sandbox id
     * @return the person list by sandbox
     */
    Promise<JsonArray, QaobeeException, Integer> getPersonListBySandbox(String sandboxId);

    /**
     * Gets person list.
     *
     * @param listId    the list id
     * @param listfield the listfield
     * @return the person list
     */
    Promise<JsonArray, QaobeeException, Integer> getPersonList(JsonArray listId, JsonArray listfield);

    /**
     * Update person json object.
     *
     * @param person the person
     * @param userId the user id
     * @param locale the locale
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> updatePerson(JsonObject person, String userId, String locale);

    /**
     * Gets person.
     *
     * @param id the id
     * @return the person
     */
    Promise<JsonObject, QaobeeException, Integer> getPerson(String id);

    /**
     * Add person json object.
     *
     * @param person the person
     * @param userId the user id
     * @param locale the locale
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> addPerson(JsonObject person, String userId, String locale);
}
