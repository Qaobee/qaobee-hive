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
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

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
    boolean notify(String id, String collection, JsonObject notification, JsonArray exclude) ;

    /**
     * Add notification to user.
     *
     * @param id           the id
     * @param notification the notification
     * @throws QaobeeException the qaobee exception
     */
    boolean addNotificationToUser(String id, JsonObject notification) throws QaobeeException;

    /**
     * Mark as read json object.
     *
     * @param id the id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject markAsRead(String id) throws QaobeeException;

    /**
     * Delete json object.
     *
     * @param id the id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject delete(String id) throws QaobeeException;

    /**
     * Gets list.
     *
     * @param id    the id
     * @param start the start
     * @param limit the limit
     * @return the list
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getList(String id, int start, int limit) throws QaobeeException;
}
