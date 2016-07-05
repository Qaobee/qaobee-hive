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
     * @throws QaobeeException the qaobee exception
     */
    boolean notify(String id, String collection, JsonObject notification, JsonArray exclude) throws QaobeeException;

    /**
     * Add notification to user.
     *
     * @param id           the id
     * @param notification the notification
     * @throws QaobeeException the qaobee exception
     */
    void addNotificationToUser(String id, JsonObject notification) throws QaobeeException;

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
