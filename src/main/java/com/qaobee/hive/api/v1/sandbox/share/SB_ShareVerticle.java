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
import com.qaobee.hive.api.v1.commons.utils.MailVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.dao.ShareDAO;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.dao.UserDAO;
import com.qaobee.hive.dao.impl.TemplatesDAOImpl;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.MailUtils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * The type Sb share verticle.
 */
@DeployableVerticle
public class SB_ShareVerticle extends AbstractGuiceVerticle { // NOSONAR
    private static final Logger LOG = LoggerFactory.getLogger(SB_ShareVerticle.class);

    /**
     * The constant GET_SANDBOX_SHARING_LIST.
     */
    public static final String GET_SANDBOX_SHARING_LIST = Module.VERSION + ".sandbox.share.list";
    /**
     * The constant GET_SANDBOX_ADMIN_SHARING_LIST for admin request.
     */
    public static final String GET_ADMIN_SANDBOX_SHARING_LIST = Module.VERSION + ".sandbox.share.listAdmin";
    /**
     * The constant CONFIRM_INVITATION_TO_SANDBOX.
     */
    public static final String CONFIRM_INVITATION_TO_SANDBOX = Module.VERSION + ".sandbox.share.confirm";
    /**
     * The constant INVITE_MEMBER_TO_SANDBOX.
     */
    public static final String INVITE_MEMBER_TO_SANDBOX = Module.VERSION + ".sandbox.share.inviteMember";
    /**
     * The constant GET_LIST_INVITATION_TO_SANDBOX.
     */
    public static final String GET_LIST_INVITATION_TO_SANDBOX = Module.VERSION + ".sandbox.share.listInvitation";
    /**
     * The constant DESACTIVATE_MEMBER_TO_SANDBOX.
     */
    public static final String DESACTIVATE_MEMBER_TO_SANDBOX = Module.VERSION + ".sandbox.share.desactivateMember";
    /**
     * The constant ACTIVATE_MEMBER_TO_SANDBOX.
     */
    public static final String ACTIVATE_MEMBER_TO_SANDBOX = Module.VERSION + ".sandbox.share.activateMember";

    /**
     * The constant REVIVE_INVITATION_TO_SANDBOX.
     */
    public static final String REVIVE_INVITATION_TO_SANDBOX = Module.VERSION + ".sandbox.share.reviveInvitation";

    /**
     * The constant REMOVE_INVITATION_TO_SANDBOX.
     */
    public static final String REMOVE_INVITATION_TO_SANDBOX = Module.VERSION + ".sandbox.share.removeInvitation";

    /**
     * The constant GET_INVITATION_TO_SANDBOX.
     */
    public static final String GET_INVITATION_TO_SANDBOX = Module.VERSION + ".sandbox.share.getInvitation";

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
    /**
     * The constant PARAM_ACTIVITY_ID.
     */
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * The constant PARAM_ACTIVITY_ID.
     */
    public static final String PARAM_ANSWER_INVITATION = "answer";
    /**
     * The constant PARAM_INVITATION_STATUS.
     */
    public static final String PARAM_INVITATION_STATUS = "invitationStatus";

    /**
     * The constant PARAM_INVITATION_ID.
     */
    public static final String PARAM_INVITATION_ID = "invitationId";
    /**
     * The constant PARAM_USER_EMAIL.
     */
    public static final String PARAM_USER_EMAIL = "email";

    private static final String INTERNAL_SHARE_NOTIFICATION = "internal.sandbox.share";
    private static final String FIELD_LOCALE = "locale";
    private static final String FIELD_ROOT = "root";
    private static final String FIELD_UID = "uid";
    private static final String INVITE_URL = "invitationToSandbox.html";
    private static final String USER_EMAIL_FIELD = "userEmail";
    private static final String TARGET_FIELD = "target";
    private static final String NOTIFICATION_FIELD = "notification";
    private static final String CONTENT_FIELD = "content";
    private static final String TITLE_FIELD = "title";
    private static final String SENDER_ID_FIELD = "senderId";
    private static final String FIRSTNAME_FIELD = "firstname";

    @Inject
    private MailUtils mailUtils;
    @Inject
    private ShareDAO shareDAO;
    @Inject
    private UserDAO userDAO;
    @Inject
    private TemplatesDAO templatesDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus().consumer(GET_SANDBOX_SHARING_LIST, this::getListOfSharedSandboxes);
        vertx.eventBus().consumer(GET_ADMIN_SANDBOX_SHARING_LIST, this::getAdminListOfSharedSandboxes);
        vertx.eventBus().consumer(GET_LIST_INVITATION_TO_SANDBOX, this::getListInvitationOfSandbox);
        vertx.eventBus().consumer(INVITE_MEMBER_TO_SANDBOX, this::inviteMemberToSandbox);
        vertx.eventBus().consumer(CONFIRM_INVITATION_TO_SANDBOX, this::confirmInvitationToSandbox);
        vertx.eventBus().consumer(ACTIVATE_MEMBER_TO_SANDBOX, this::activateMemberToSandbox);
        vertx.eventBus().consumer(DESACTIVATE_MEMBER_TO_SANDBOX, this::desactivateMemberToSandbox);
        vertx.eventBus().consumer(INTERNAL_SHARE_NOTIFICATION, this::internalShareNotification);
        vertx.eventBus().consumer(REVIVE_INVITATION_TO_SANDBOX, this::reviveInvitationToSandbox);
        vertx.eventBus().consumer(REMOVE_INVITATION_TO_SANDBOX, this::removeInvitationToSandbox);
        vertx.eventBus().consumer(GET_INVITATION_TO_SANDBOX, this::getInvitationToSandbox);
    }

    private void internalShareNotification(Message<JsonObject> message) {
        JsonObject notification = new JsonObject()
                .put("id", message.body().getString(PARAM_USERID))
                .put(TARGET_FIELD, User.class.getSimpleName())
                .put(NOTIFICATION_FIELD, new JsonObject()
                        .put(CONTENT_FIELD, Messages.getString(message.body().getString(FIELD_ROOT) + ".content", message.body().getString(FIELD_LOCALE)))
                        .put(TITLE_FIELD, Messages.getString(message.body().getString(FIELD_ROOT) + ".title", message.body().getString(FIELD_LOCALE)))
                        .put(SENDER_ID_FIELD, message.body().getString(FIELD_UID))
                );
        vertx.eventBus().send(NotificationsVerticle.NOTIFY, notification);
    }

    /**
     * @apiDescription Desactivate a member to a SB_SandBox
     * @api {post} /api/1/share/sandbox/del Desactivate a member to a SB_SandBox
     * @apiParam {String} userId User id to Desactivate
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName desactivateMemberToSandbox
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = DESACTIVATE_MEMBER_TO_SANDBOX, method = Constants.POST, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_USERID}, scope = Rule.Param.BODY)
    private void desactivateMemberToSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        shareDAO.desactivateMemberToSandbox(request.getString(PARAM_SANBOXID), request.getString(PARAM_USERID))
                .done(sandbox -> {
                    vertx.eventBus().send(INTERNAL_SHARE_NOTIFICATION, new JsonObject()
                            .put(PARAM_USERID, request.getString(PARAM_USERID))
                            .put(FIELD_ROOT, "notification.sandbox.desactivateMember")
                            .put(FIELD_LOCALE, req.getLocale())
                            .put(FIELD_UID, req.getUser().get_id())
                    );
                    message.reply(sandbox.encode());
                }).fail(e -> utils.sendError(message, e));
    }

    /**
     * @apiDescription Desactivate a member to a SB_SandBox
     * @api {post} /api/1/share/sandbox/del activate a member to a SB_SandBox
     * @apiParam {String} userId User id to activate
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName desactivateMemberToSandbox
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = ACTIVATE_MEMBER_TO_SANDBOX, method = Constants.POST, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_USERID}, scope = Rule.Param.BODY)
    private void activateMemberToSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        shareDAO.activateMemberToSandbox(request.getString(PARAM_SANBOXID), request.getString(PARAM_USERID)).done(sandbox -> {
            vertx.eventBus().send(INTERNAL_SHARE_NOTIFICATION, new JsonObject()
                    .put(PARAM_USERID, request.getString(PARAM_USERID))
                    .put(FIELD_ROOT, "notification.sandbox.activateMember")
                    .put(FIELD_LOCALE, req.getLocale())
                    .put(FIELD_UID, req.getUser().get_id())
            );
            message.reply(sandbox.encode());
        }).fail(e -> utils.sendError(message, e));
    }

    /**
     * @apiDescription Invite a member to a SB_SandBox
     * @api {post} /api/1/share/sandbox/add Invite a member to a SB_SandBox
     * @apiParam {String} userId User id to add as a member
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiParam {String} role_code Role code
     * @apiName addUserToSandbox
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = INVITE_MEMBER_TO_SANDBOX, method = Constants.POST, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_USER_EMAIL, PARAM_ROLE_CODE}, scope = Rule.Param.BODY)
    private void inviteMemberToSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        userDAO.getUserInfo(req.getUser().get_id())
                .done(user -> shareDAO.inviteMemberToSandbox(request.getString(PARAM_SANBOXID), request.getString(PARAM_USER_EMAIL), request.getString(PARAM_ROLE_CODE))
                        .done(invitation -> {
                            try {
                                sendNotification(invitation, user, request.getString(PARAM_USER_EMAIL), req.getLocale());
                                message.reply(invitation.encode());
                            } catch (QaobeeException e) {
                                LOG.error(e.getMessage(), e);
                                utils.sendError(message, e);
                            }
                        }).fail(e -> utils.sendError(message, e)))
                .fail(e -> utils.sendError(message, e));
    }

    private void sendNotification(JsonObject invitation, JsonObject user, String userEmail, String locale) throws QaobeeException {
        final JsonObject tplReq = new JsonObject();
        if (StringUtils.isNotBlank(invitation.getString(PARAM_USERID, ""))) {
            JsonObject notification = new JsonObject()
                    .put("id", invitation.getString(PARAM_USERID))
                    .put(TARGET_FIELD, User.class.getSimpleName())
                    .put(NOTIFICATION_FIELD, new JsonObject()
                            .put(CONTENT_FIELD, Messages.getString("notification.sandbox.add.content", locale, user.getString(FIRSTNAME_FIELD) + " " + user.getString("name")))
                            .put(TITLE_FIELD, Messages.getString("notification.sandbox.add.title", locale))
                            .put(SENDER_ID_FIELD, user.getString("_id"))
                    );
            vertx.eventBus().send(NotificationsVerticle.NOTIFY, notification);
                /* send an E-mail to guest */
            tplReq.put(TemplatesDAOImpl.TEMPLATE, INVITE_URL)
                    .put(TemplatesDAOImpl.DATA, mailUtils.generateInvitationToSandboxBody(Json.decodeValue(user.encode(), User.class), locale, userEmail, invitation.getString("_id"), "internal"));
        } else {
                /* send an E-mail to guest */
            tplReq.put(TemplatesDAOImpl.TEMPLATE, INVITE_URL)
                    .put(TemplatesDAOImpl.DATA, mailUtils.generateInvitationToSandboxBody(Json.decodeValue(user.encode(), User.class), locale, userEmail, invitation.getString("_id"), "external"));
        }
        final JsonObject emailReq = new JsonObject()
                .put("from", user.getJsonObject("contact").getString(PARAM_USER_EMAIL))
                .put("to", userEmail)
                .put("subject", Messages.getString("mail.account.sharingSB.subject", locale, user.getString(FIRSTNAME_FIELD) + " " + user.getString("name")))
                .put("content_type", "text/html")
                .put("body", templatesDAO.generateMail(tplReq).getString("result"));
        vertx.eventBus().publish(MailVerticle.INTERNAL_MAIL, emailReq);
    }

    /**
     * @apiDescription Revive an invitation to a person to join a SB_SandBox
     * @api {post} /api/1/share/sandbox/reviveInvitation Invite a member to a SB_SandBox
     * @apiParam {String} invitationId invitation Id
     * @apiName reviveInvitationToSandbox
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} invitation;
     */
    @Rule(address = REVIVE_INVITATION_TO_SANDBOX, method = Constants.GET, logged = true, mandatoryParams = {PARAM_INVITATION_ID}, scope = Rule.Param.REQUEST)
    private void reviveInvitationToSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        shareDAO.reviveInvitationToUser(req.getParams().get(PARAM_INVITATION_ID).get(0))
                .done(invitation -> userDAO.getUserInfo(req.getUser().get_id()).done(user -> {
                    try {
                        sendNotification(invitation, user, invitation.getString(USER_EMAIL_FIELD), req.getLocale());
                        message.reply(invitation.encode());
                    } catch (QaobeeException e) {
                        LOG.error(e.getMessage(), e);
                        utils.sendError(message, e);
                    }
                }).fail(e -> utils.sendError(message, e))).fail(e -> utils.sendError(message, e));
    }

    /**
     * @apiDescription Leave an invitation to a person to join a SB_SandBox
     * @api {post} /api/1/share/sandbox/removeInvitation remove an invitation 's member to a SB_SandBox
     * @apiParam {String} invitationId invitation Id
     * @apiName removeInvitationToSandbox
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} invitation;
     */
    @Rule(address = REMOVE_INVITATION_TO_SANDBOX, method = Constants.GET, logged = true, mandatoryParams = {PARAM_INVITATION_ID}, scope = Rule.Param.REQUEST)
    private void removeInvitationToSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, shareDAO.removeInvitationToSandbox(req.getParams().get(PARAM_INVITATION_ID).get(0)));
    }

    /**
     * @apiDescription get an invitation  to join a SB_SandBox
     * @api {post} /api/1/share/sandbox/getInvitation get an invitation 's member to a SB_SandBox
     * @apiParam {String} invitationId invitation Id
     * @apiName getInvitationToSandbox
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} invitation;
     */
    @Rule(address = GET_INVITATION_TO_SANDBOX, method = Constants.GET, mandatoryParams = {PARAM_INVITATION_ID}, scope = Rule.Param.REQUEST)
    private void getInvitationToSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, shareDAO.getInvitationToSandbox(req.getParams().get(PARAM_INVITATION_ID).get(0)));
    }

    /**
     * @apiDescription accept or refuse to become a member to a SB_SandBox
     * @api {post} /api/1/share/sandbox/add Add a member to a SB_SandBox
     * @apiParam {String} userId User id to add as a member
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiParam {String} role_code Role code
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = CONFIRM_INVITATION_TO_SANDBOX, method = Constants.POST, mandatoryParams = {PARAM_INVITATION_ID, PARAM_USERID, PARAM_ANSWER_INVITATION}, scope = Rule.Param.BODY)
    private void confirmInvitationToSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        shareDAO.confirmInvitationToSandbox(request.getString(PARAM_INVITATION_ID), request.getString(PARAM_USERID), request.getString(PARAM_ANSWER_INVITATION))
                .done(invitation -> {
                    JsonObject notification = new JsonObject();
                    if ("accepted".equals(request.getString(PARAM_ANSWER_INVITATION))) {
                        notification.put("id", invitation.getString(SENDER_ID_FIELD))
                                .put(TARGET_FIELD, User.class.getSimpleName())
                                .put(NOTIFICATION_FIELD, new JsonObject()
                                        .put(CONTENT_FIELD, Messages.getString("notification.sandbox.accept.content", req.getLocale(), invitation.getString(USER_EMAIL_FIELD)))
                                        .put(TITLE_FIELD, Messages.getString("notification.sandbox.accept.title", req.getLocale()))
                                        .put(SENDER_ID_FIELD, request.getString(PARAM_USERID))
                                );
                    } else {
                        notification.put("id", invitation.getString(SENDER_ID_FIELD))
                                .put(TARGET_FIELD, User.class.getSimpleName())
                                .put(NOTIFICATION_FIELD, new JsonObject()
                                        .put(CONTENT_FIELD, Messages.getString("notification.sandbox.refuse.content", req.getLocale(), invitation.getString(USER_EMAIL_FIELD)))
                                        .put(TITLE_FIELD, Messages.getString("notification.sandbox.refuse.title", req.getLocale()))
                                        .put(SENDER_ID_FIELD, request.getString(PARAM_USERID))
                                );
                    }
                    vertx.eventBus().send(NotificationsVerticle.NOTIFY, notification);
                    message.reply(invitation.encode());
                }).fail(e -> utils.sendError(message, e));
    }

    /**
     * @apiDescription Get list of enriched sandboxes for the current user
     * @api {get} /api/1/share/sandbox/list Get list of enriched sandboxes for the current user
     * @apiName getListOfSharedSandboxes
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandboxes list of enriched sandboxes owned and as member;
     */
    //TODO utilité du ACTIVITY_ID ????
    @Rule(address = GET_SANDBOX_SHARING_LIST, method = Constants.GET, logged = true, mandatoryParams = PARAM_ACTIVITY_ID, scope = Rule.Param.REQUEST)
    private void getListOfSharedSandboxes(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, shareDAO.getListOfSharedSandboxes(req.getUser().get_id()));
    }

    /**
     * @apiDescription Get list of enriched sandboxes for the current user
     * @api {get} /api/1/share/sandbox/list Get list of enriched sandboxes for the current user
     * @apiName getListOfSharedSandboxes
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandboxes list of enriched sandboxes owned and as member;
     */
    @Rule(address = GET_ADMIN_SANDBOX_SHARING_LIST, method = Constants.GET, logged = true, admin = true, mandatoryParams = PARAM_USERID, scope = Rule.Param.REQUEST)
    private void getAdminListOfSharedSandboxes(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, shareDAO.getListOfSharedSandboxes(req.getParams().get(PARAM_USERID).get(0)));
    }

    /**
     * @apiDescription Get list of invitations of sandbox
     * @api {get} /api/1/share/sandbox/listInvitation Get list of invitation of sandbox
     * @apiName getListInvitationOfSandbox
     * @apiHeader {String} token
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiParam {String} status InvitationStatus (ALL, waiting, accepted, refused)
     * @apiGroup Share API
     * @apiSuccess {JsonArray} invitations list of invitation of sandbox
     */
    @Rule(address = GET_LIST_INVITATION_TO_SANDBOX, method = Constants.GET, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_INVITATION_STATUS}, scope = Rule.Param.REQUEST)
    private void getListInvitationOfSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonArray(message, shareDAO.getListOfInvitationsToSandbox(req.getParams().get(PARAM_SANBOXID).get(0), req.getParams().get(PARAM_INVITATION_STATUS).get(0)));
    }
}
