package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.List;

/**
 * The interface Sand box dao.
 */
public interface SandBoxDAO {
    /**
     * Update json object.
     *
     * @param id           the id
     * @param sandboxCfgId the sandbox cfg id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject updateSandboxCfgId(String id, String sandboxCfgId) throws QaobeeException;

    /**
     * Add json object.
     *
     * @param activityId the activity id
     * @param userId     the user id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject add(String activityId, String userId) throws QaobeeException;

    /**
     * Gets list by owner.
     *
     * @param usersIds     the users ids
     * @param loggedUserId the logged user id
     * @return the list by owner
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getListByOwner(List<String> usersIds, String loggedUserId) throws QaobeeException;

    /**
     * Gets by owner.
     *
     * @param activityId the activity id
     * @param userId     the user id
     * @return the by owner
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getByOwner(String activityId, String userId) throws QaobeeException;

    /**
     * Update json object.
     *
     * @param sandbox the sandbox
     * @return the json object
     */
    JsonObject update(JsonObject sandbox);
}
