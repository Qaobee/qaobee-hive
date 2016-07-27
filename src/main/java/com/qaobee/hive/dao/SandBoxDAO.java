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
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.List;

/**
 * The interface Sand box dao.
 */
public interface SandBoxDAO {
    /**
     * Update json object.
     *
     * @param id           the id
     * @param sandboxCfgId the sandbox cfg id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject updateSandboxCfgId(String id, String sandboxCfgId) throws QaobeeException;

    /**
     * Add json object.
     *
     * @param activityId the activity id
     * @param userId     the user id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject add(String activityId, String userId) throws QaobeeException;

    /**
     * Gets list by owner.
     *
     * @param usersIds     the users ids
     * @param loggedUserId the logged user id
     * @return the list by owner
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getListByOwner(List<String> usersIds, String loggedUserId) throws QaobeeException;

    /**
     * Gets by owner.
     *
     * @param activityId the activity id
     * @param userId     the user id
     * @return the by owner
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getByOwner(String activityId, String userId) throws QaobeeException;

}
