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
 * The interface Notifications dao.
 */
public interface NotificationsDAO {

    /**
     * Notify boolean.
     *
     * @param id           the id
     * @param collection   the collection
     * @param notification the notification
     * @param exclude      the exclude
     * @return the boolean
     */
    Promise<Boolean, QaobeeException, Integer> notify(String id, String collection, JsonObject notification, JsonArray exclude) ;

    /**
     * Add notification to user.
     *
     * @param id           the id
     * @param notification the notification
     * @return the boolean
     */
    Promise<Boolean, QaobeeException, Integer> addNotificationToUser(String id, JsonObject notification);

    /**
     * Mark as read json object.
     *
     * @param id the id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonObject, QaobeeException, Integer> markAsRead(String id) throws QaobeeException;

    /**
     * Delete json object.
     *
     * @param id the id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonObject, QaobeeException, Integer> delete(String id) throws QaobeeException;

    /**
     * Gets list.
     *
     * @param id    the id
     * @param start the start
     * @param limit the limit
     * @return the list
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonArray, QaobeeException, Integer> getList(String id, int start, int limit) throws QaobeeException;
}
