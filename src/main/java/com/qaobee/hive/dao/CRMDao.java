package com.qaobee.hive.dao;

import io.vertx.core.json.JsonObject;

/**
 * The interface Crm dao.
 */
@FunctionalInterface
public interface CRMDao {

    /**
     * Register user.
     *
     * @param user        the user
     * @param firstLogin the first login
     */
    void registerUser(JsonObject user, boolean firstLogin);
}
