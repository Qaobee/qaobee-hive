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

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * The interface Event dao.
 */
public interface EventDAO {

    /**
     * Gets event.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getEvent(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Update event.
     *
     * @param event         the event
     * @param currentUserId the current user id
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void updateEvent(JsonObject event, String currentUserId, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Add event.
     *
     * @param event         the event
     * @param currentUserId the current user id
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void addEvent(JsonObject event, String currentUserId, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets event list.
     *
     * @param params        the params
     * @param resultHandler the result handler
     */
    void getEventList(JsonObject params, Handler<AsyncResult<JsonArray>> resultHandler);
}
