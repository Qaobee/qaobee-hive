package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Collect dao.
 */
public interface CollectDAO {
    /**
     * Get json object.
     *
     * @param id the id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject get(String id) throws QaobeeException;

    /**
     * Update json object.
     *
     * @param collect       the collect
     * @param currentUserId the current user id
     * @param locale        the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject update(JsonObject collect, String currentUserId, String locale) throws QaobeeException;

    /**
     * Add json object.
     *
     * @param collect       the collect
     * @param currentUserId the current user id
     * @param locale        the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject add(JsonObject collect, String currentUserId, String locale) throws QaobeeException;

    /**
     * Gets list.
     *
     * @param params the params
     * @return the list
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getList(JsonObject params) throws QaobeeException;
}
