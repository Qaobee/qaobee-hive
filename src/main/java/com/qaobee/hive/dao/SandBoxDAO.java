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

import java.util.List;

/**
 * The interface Sand box dao.
 */
public interface SandBoxDAO {
    /**
     * Gets sandbox by id.
     *
     * @param sandboxId the sandbox id
     * @return the sandbox sharing
     */
    Promise<JsonObject, QaobeeException, Integer> getSandboxById(String sandboxId);

    /**
     * Gets enriched sandbox.
     *
     * @param sandbox the sandbox
     * @return the enriched sandbox
     */
    Promise<JsonObject, QaobeeException, Integer> getEnrichedSandbox(JsonObject sandbox);

    /**
     * Update json object.
     *
     * @param sandbox the sandbox
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> updateSandbox(JsonObject sandbox);

    /**
     * Gets list by owner.
     *
     * @param usersIds     the users ids
     * @param loggedUserId the logged user id
     * @return the list by owner
     */
    Promise<JsonArray, QaobeeException, Integer> getListByOwner(List<String> usersIds, String loggedUserId);

    /**
     * Gets by owner.
     *
     * @param activityId the activity id
     * @param userId     the user id
     * @return the by owner
     */
    Promise<JsonObject, QaobeeException, Integer> getByOwner(String activityId, String userId);

}
