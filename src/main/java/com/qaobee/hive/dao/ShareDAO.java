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
    JsonObject getEnrichedSandbox(JsonObject sandboxCfgId) throws QaobeeException;

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
