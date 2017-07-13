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

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * The interface Team dao.
 */
public interface TeamDAO {
    /**
     * Gets team list.
     *
     * @param sandboxId     the sandbox id
     * @param effectiveId   the effective id
     * @param adversary     the adversary
     * @param enabled       the enabled
     * @param link          the link
     * @param resultHandler the result handler
     */
    void getTeamList(String sandboxId, String effectiveId, String adversary, String enabled, String link, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets team.
     *
     * @param teamId        the team id
     * @param resultHandler the result handler
     */
    void getTeam(String teamId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Update team.
     *
     * @param team          the team
     * @param userId        the user id
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void updateTeam(JsonObject team, String userId, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Add team.
     *
     * @param team          the team
     * @param userId        the user id
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void addTeam(JsonObject team, String userId, String locale, Handler<AsyncResult<JsonObject>> resultHandler);
}
