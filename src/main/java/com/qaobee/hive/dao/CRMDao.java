package com.qaobee.hive.dao;

import org.vertx.java.core.json.JsonObject;

/**
 * The interface Crm dao.
 */
public interface CRMDao {

    /**
     * Register user.
     *
     * @param user        the user
     * @param firstLogin the first login
     */
    void registerUser(JsonObject user, boolean firstLogin);
}
