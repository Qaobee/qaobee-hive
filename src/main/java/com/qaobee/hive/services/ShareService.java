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

package com.qaobee.hive.services;


import com.qaobee.hive.services.impl.SecurityServiceImpl;
import com.qaobee.hive.services.impl.ShareServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Share service.
 */
@ProxyGen
@VertxGen
public interface ShareService {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.Share.service";

    /**
     * Create share service.
     *
     * @param vertx the vertx
     *
     * @return the share service
     */
    static ShareService create(Vertx vertx) {
        return new ShareServiceImpl(vertx);
    }

    /**
     * Create proxy share service.
     *
     * @param vertx   the vertx
     * @param address the address
     *
     * @return the share service
     */
    static ShareService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(ShareService.class, vertx, address);
    }

    /**
     * Desactivate member to sandbox.
     *
     * @param sandboxId     the sandbox id
     * @param userId        the user id
     * @param resultHandler the result handler
     */
    void desactivateMemberToSandbox(String sandboxId, String userId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Activate member to sandbox.
     *
     * @param sandboxId     the sandbox id
     * @param userId        the user id
     * @param resultHandler the result handler
     */
    void activateMemberToSandbox(String sandboxId, String userId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Invite member to sandbox.
     *
     * @param sandboxId     the sandbox id
     * @param userEmail     the user email
     * @param roleCode      the role code
     * @param resultHandler the result handler
     */
    void inviteMemberToSandbox(String sandboxId, String userEmail, String roleCode, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Revive invitation to user.
     *
     * @param invitationId  the invitation id
     * @param resultHandler the result handler
     */
    void reviveInvitationToUser(String invitationId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Remove invitation to sandbox.
     *
     * @param invitationId  the invitation id
     * @param resultHandler the result handler
     */
    void removeInvitationToSandbox(String invitationId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets invitation to sandbox.
     *
     * @param invitationId  the invitation id
     * @param resultHandler the result handler
     */
    void getInvitationToSandbox(String invitationId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Confirm invitation to sandbox.
     *
     * @param invitationId  the invitation id
     * @param userId        the user id
     * @param answer        the answer
     * @param resultHandler the result handler
     */
    void confirmInvitationToSandbox(String invitationId, String userId, String answer, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list of shared sandboxes.
     *
     * @param userId        the user id
     * @param resultHandler the result handler
     */
    void getListOfSharedSandboxes(String userId, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list of invitations to sandbox.
     *
     * @param sandboxId     the sandbox id
     * @param status        the status
     * @param resultHandler the result handler
     */
    void getListOfInvitationsToSandbox(String sandboxId, String status, Handler<AsyncResult<JsonArray>> resultHandler);
}
