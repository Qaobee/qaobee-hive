package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Share dao.
 */
public interface ShareDAO {

    /**
     * Gets sandbox sharing.
     *
     * @param sandboxId the sandbox id
     * @return the sandbox sharing
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getSandboxSharing(String sandboxId) throws QaobeeException;

    /**
     * Gets enriched sandbox cfg.
     *
     * @param sandboxCfgId the sandbox cfg id
     * @return the enriched sandbox cfg
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getEnrichedSandboxCfg(String sandboxCfgId) throws QaobeeException;

    /**
     * Remove user from sandbox.
     *
     * @param sandboxId the sandbox id
     * @param userId    the user id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject removeUserFromSandbox(String sandboxId, String userId) throws QaobeeException;

    /**
     * Add user to sandbox json object.
     *
     * @param sandboxId the sandbox id
     * @param userId    the user id
     * @param roleCode  the role code
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject addUserToSandbox(String sandboxId, String userId, String roleCode) throws QaobeeException;

    /**
     * Gets list of shared sandboxes.
     *
     * @param userId the user id
     * @return the list of shared sandboxes owners and members
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getListOfSharedSandboxes(String userId) throws QaobeeException;
}
