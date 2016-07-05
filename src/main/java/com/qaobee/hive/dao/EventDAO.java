package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Event dao.
 */
public interface EventDAO {

    /**
     * Gets event.
     *
     * @param id the id
     * @return the event
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getEvent(String id) throws QaobeeException;

    /**
     * Update event json object.
     *
     * @param event         the event
     * @param currentUserId the current user id
     * @param locale        the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject updateEvent(JsonObject event, String currentUserId, String locale) throws QaobeeException;

    /**
     * Add event json object.
     *
     * @param event         the event
     * @param currentUserId the current user id
     * @param locale        the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject addEvent(JsonObject event, String currentUserId, String locale) throws QaobeeException;

    /**
     * Gets event list.
     *
     * @param params the params
     * @return the event list
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getEventList(JsonObject params) throws QaobeeException;
}
