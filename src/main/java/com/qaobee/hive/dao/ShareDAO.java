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

/**
 * The interface Share dao.
 */
public interface ShareDAO {

    /**
     * Desactivate user from sandbox.
     *
     * @param sandboxId the sandbox id
     * @param userId    the user id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject desactivateMemberToSandbox(String sandboxId, String userId) throws QaobeeException;

    /**
     * Activate user from sandbox.
     *
     * @param sandboxId the sandbox id
     * @param userId    the user id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject activateMemberToSandbox(String sandboxId, String userId) throws QaobeeException;

    /**
     * Invite user to sandbox json object.
     *
     * @param sandboxId the sandbox id
     * @param userEmail the user email
     * @param roleCode  the role code
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject inviteMemberToSandbox(String sandboxId, String userEmail, String roleCode) throws QaobeeException;

    /**
     * Remove revive an invitation to an person to join sandbox json object.
     *
     * @param invitationId the invitation id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject reviveInvitationToUser(String invitationId) throws QaobeeException;

    /**
     * Remove invitation json object.
     *
     * @param invitationId the invitation id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject removeInvitationToSandbox(String invitationId) throws QaobeeException;
    
    /**
     * Get invitation json object.
     *
     * @param invitationId the invitation id
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getInvitationToSandbox(String invitationId) throws QaobeeException;

    /**
     * Confirm invitation to joint the sandbox.
     *
     * @param invitationId the invitation id
     * @param userId       the user id
     * @param answer       the answer (accepted or refused)
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject confirmInvitationToSandbox(String invitationId, String userId, String answer) throws QaobeeException;


    /**
     * Gets list of shared sandboxes.
     *
     * @param userId     the user id
     * @return the list of shared sandboxes owners and members
     */
    JsonObject getListOfSharedSandboxes(String userId);

    /**
     * Gets list of invitation sandboxes.
     *
     * @param sandboxId the sandbox id
     * @param status    the status of invitation
     * @return the list of invitation to the sandbox
     */
    JsonArray getListOfInvitationsToSandbox(String sandboxId, String status);
}
