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

/**
 * The interface Team dao.
 */
public interface TeamDAO {
    /**
     * Gets team list.
     *
     * @param sandboxId   the sandbox id
     * @param effectiveId the effective id
     * @param adversary   the adversary
     * @param enabled     the enabled
     * @param link        the link
     * @return the team list
     */
    JsonArray getTeamList(String sandboxId, String effectiveId, String adversary, String enabled, String link);

    /**
     * Gets team.
     *
     * @param teamId the team id
     * @return the team
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getTeam(String teamId) throws QaobeeException;

    /**
     * Update team json object.
     *
     * @param team   the team
     * @param userId the user id
     * @param locale the locale
     * @return the json object
     */
    JsonObject updateTeam(JsonObject team, String userId, String locale);

    /**
     * Add team json object.
     *
     * @param team   the team
     * @param userId the user id
     * @param locale the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject addTeam(JsonObject team, String userId, String locale) throws QaobeeException;
}
