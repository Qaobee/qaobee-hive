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

package com.qaobee.hive.api.v1.sandbox.share;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.api.v1.commons.communication.NotificationsVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.dao.ShareDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.DecodeException;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

/**
 * The type Sb share verticle.
 */
@DeployableVerticle
public class SB_ShareVerticle extends AbstractGuiceVerticle { // NOSONAR
    private static final Logger LOG = LoggerFactory.getLogger(SB_ShareVerticle.class);

    /**
     * The constant GET_SANDBOX_LIST.
     */
    public static final String GET_SANDBOX_LIST = Module.VERSION + ".share.sandbox.list";
    /**
     * The constant ADD_TO_SANDBOX.
     */
    public static final String ADD_TO_SANDBOX = Module.VERSION + ".share.sandbox.add";
    /**
     * The constant REMOVE_FROM_SANDBOX.
     */
    public static final String REMOVE_FROM_SANDBOX = Module.VERSION + ".share.sandbox.del";
    /**
     * The constant GET_SANDOX_SHARING.
     */
    public static final String GET_SANDOX_SHARING = Module.VERSION + ".share.sandbox.get";

    /**
     * The constant PARAM_SANBOXID.
     */
    public static final String PARAM_SANBOXID = "sandboxId";
    /**
     * The constant PARAM_USERID.
     */
    public static final String PARAM_USERID = "userId";
    /**
     * The constant PARAM_ROLE_CODE.
     */
    public static final String PARAM_ROLE_CODE = "role_code";

    private static final String INTERNAL_SHARE_NOTIFICATION = "internal.sandbox.share";
    private static final String FIELD_LOCALE = "locale";
    private static final String FIELD_ROOT = "root";
    private static final String FIELD_UID = "uid";

    @Inject
    private Utils utils;
    @Inject
    private ShareDAO shareDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET_SANDBOX_LIST, this::getListOfSharedSandboxes)
                .registerHandler(ADD_TO_SANDBOX, this::addUserToSandbox)
                .registerHandler(REMOVE_FROM_SANDBOX, this::removeUserFromSandbox)
                .registerHandler(GET_SANDOX_SHARING, this::getSandboxSharing)
                .registerHandler(INTERNAL_SHARE_NOTIFICATION, this::internalShareNotification);
    }

    private void internalShareNotification(Message<JsonObject> message) {
        JsonObject notification = new JsonObject()
                .putString("id", message.body().getString(PARAM_USERID))
                .putString("target", User.class.getSimpleName())
                .putObject("notification", new JsonObject()
                        .putString("content", Messages.getString(message.body().getString(FIELD_ROOT) + ".content", message.body().getString(FIELD_LOCALE)))
                        .putString("title", Messages.getString(message.body().getString(FIELD_ROOT) + ".title", message.body().getString(FIELD_LOCALE)))
                        .putString("senderId", message.body().getString(FIELD_UID))
                );
        vertx.eventBus().send(NotificationsVerticle.NOTIFY, notification);
    }

    /**
     * @apiDescription Get an enriched SB_SandBoxCfg
     * @api {post} /api/1/share/sandbox/get Get an enriched SB_SandBoxCfg
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName getSandboxSharing
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = GET_SANDOX_SHARING, method = Constants.GET, logged = true, mandatoryParams = {PARAM_SANBOXID}, scope = Rule.Param.REQUEST)
    private void getSandboxSharing(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(shareDAO.getSandboxSharing(req.getParams().get(PARAM_SANBOXID).get(0)).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        } catch (DecodeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, new QaobeeException(ExceptionCodes.JSON_EXCEPTION, e));
        }
    }

    /**
     * @apiDescription Remove a member to a SB_SandBoxCfg
     * @api {post} /api/1/share/sandbox/del Remove a member to a SB_SandBoxCfg
     * @apiParam {String} userId User id to remeve
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName removeUserFromSandbox
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = REMOVE_FROM_SANDBOX, method = Constants.POST, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_USERID}, scope = Rule.Param.BODY)
    private void removeUserFromSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        try {
            JsonObject sandbox = shareDAO.removeUserFromSandbox(request.getString(PARAM_SANBOXID), request.getString(PARAM_USERID));
            vertx.eventBus().send(INTERNAL_SHARE_NOTIFICATION, new JsonObject()
                    .putString(PARAM_USERID, request.getString(PARAM_USERID))
                    .putString(FIELD_ROOT, "notification.sandbox.del")
                    .putString(FIELD_LOCALE, req.getLocale())
                    .putString(FIELD_UID, req.getUser().get_id())
            );
            message.reply(sandbox.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Add a member to a SB_SandBoxCfg
     * @api {post} /api/1/share/sandbox/add Add a member to a SB_SandBoxCfg
     * @apiParam {String} userId User id to add as a member
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiParam {String} role_code Role code
     * @apiName addUserToSandbox
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = ADD_TO_SANDBOX, method = Constants.POST, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_USERID, PARAM_ROLE_CODE}, scope = Rule.Param.BODY)
    private void addUserToSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        try {
            JsonObject sandbox = shareDAO.addUserToSandbox(request.getString(PARAM_SANBOXID), request.getString(PARAM_USERID), request.getString(PARAM_ROLE_CODE));
            vertx.eventBus().send(INTERNAL_SHARE_NOTIFICATION, new JsonObject()
                    .putString(PARAM_USERID, request.getString(PARAM_USERID))
                    .putString(FIELD_ROOT, "notification.sandbox.add")
                    .putString(FIELD_LOCALE, req.getLocale())
                    .putString(FIELD_UID, req.getUser().get_id())
            );
            message.reply(sandbox.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Get list of enriched sandboxes for the current user
     * @api {get} /api/1/share/sandbox/list Get list of enriched sandboxes for the current user
     * @apiName getListOfSharedSandboxes
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandboxes list of enriched sandboxes owned and as member;
     */
    @Rule(address = GET_SANDBOX_LIST, method = Constants.GET, logged = true)
    private void getListOfSharedSandboxes(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        message.reply(shareDAO.getListOfSharedSandboxes(req.getUser().get_id()).encode());
    }
}
