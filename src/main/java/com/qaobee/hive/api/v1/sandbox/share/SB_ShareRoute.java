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
import com.qaobee.hive.dao.MailUtils;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.dao.impl.TemplatesDAOImpl;
import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.services.ShareService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import com.qaobee.hive.verticles.MailVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static com.qaobee.hive.technical.constantes.Constants.ADMIN_HABILIT;

/**
 * The type Sb share Route.
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/sandbox/share")
public class SB_ShareRoute extends AbstractRoute { // NOSONAR
    private static final Logger LOG = LoggerFactory.getLogger(SB_ShareRoute.class);

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

    private static final String FIELD_LOCALE = "locale";
    private static final String FIELD_ROOT = "root";
    private static final String FIELD_UID = "uid";
    private static final String INVITE_URL = "invitationToSandbox.html";
    private static final String USER_EMAIL_FIELD = "userEmail";
    private static final String CONTENT_FIELD = "content";
    private static final String TITLE_FIELD = "title";
    private static final String SENDER_ID_FIELD = "senderId";
    private static final String FIRSTNAME_FIELD = "firstname";

    @Inject
    private MailUtils mailUtils;
    @Inject
    private ShareService shareService;
    @Inject
    private TemplatesDAO templatesDAO;
    @Inject
    private NotificationsService notificationsService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/list", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ACTIVITY_ID),
                this::getListOfSharedSandboxes);

        addRoute(router, "/listAdmin", HttpMethod.GET,
                authHandler,
                c -> roleHandler.hasRole(c, ADMIN_HABILIT),
                c -> mandatoryHandler.testRequestParams(c, PARAM_USERID),
                this::getAdminListOfSharedSandboxes);

        addRoute(router, "/listInvitation", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_SANBOXID, PARAM_INVITATION_STATUS),
                this::getListInvitationOfSandbox);

        addRoute(router, "/inviteMember", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_SANBOXID, PARAM_USER_EMAIL, PARAM_ROLE_CODE),
                this::inviteMemberToSandbox);

        addRoute(router, "/confirm", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_INVITATION_ID, PARAM_USERID, PARAM_ANSWER_INVITATION),
                this::confirmInvitationToSandbox);

        addRoute(router, "/activateMember", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_SANBOXID, PARAM_USERID),
                this::activateMemberToSandbox);

        addRoute(router, "/desactivateMember", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_SANBOXID, PARAM_USERID),
                this::desactivateMemberToSandbox);

        addRoute(router, "/reviveInvitation", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_INVITATION_ID),
                this::reviveInvitationToSandbox);

        addRoute(router, "/removeInvitation", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_INVITATION_ID),
                this::removeInvitationToSandbox);

        addRoute(router, "/getInvitation", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_INVITATION_ID),
                this::getInvitationToSandbox);

        return router;
    }

    private void internalShareNotification(JsonObject message) {
        notificationsService.sendNotification(message.getString(PARAM_USERID), DBCollections.USER, new JsonObject()
                .put(CONTENT_FIELD, Messages.getString(message.getString(FIELD_ROOT) + ".content", message.getString(FIELD_LOCALE)))
                .put(TITLE_FIELD, Messages.getString(message.getString(FIELD_ROOT) + ".title", message.getString(FIELD_LOCALE)))
                .put(SENDER_ID_FIELD, message.getString(FIELD_UID)), new JsonArray(), ar -> {
            // empty
        });
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
    private void desactivateMemberToSandbox(RoutingContext context) {
        JsonObject request = context.getBodyAsJson();
        shareService.desactivateMemberToSandbox(request.getString(PARAM_SANBOXID), request.getString(PARAM_USERID), sandbox -> {
            if (sandbox.succeeded()) {
                internalShareNotification(new JsonObject()
                        .put(PARAM_USERID, request.getString(PARAM_USERID))
                        .put(FIELD_ROOT, "notification.sandbox.desactivateMember")
                        .put(FIELD_LOCALE, getLocale(context))
                        .put(FIELD_UID, context.user().principal().getString("_id"))
                );
                handleResponse(context, sandbox.result());
            } else {
                utils.handleError(context, sandbox.cause());
            }
        });
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
    private void activateMemberToSandbox(RoutingContext context) {
        JsonObject request = context.getBodyAsJson();
        shareService.activateMemberToSandbox(request.getString(PARAM_SANBOXID), request.getString(PARAM_USERID), sandbox -> {
            if (sandbox.succeeded()) {
                internalShareNotification(new JsonObject()
                        .put(PARAM_USERID, request.getString(PARAM_USERID))
                        .put(FIELD_ROOT, "notification.sandbox.activateMember")
                        .put(FIELD_LOCALE, getLocale(context))
                        .put(FIELD_UID, context.user().principal().getString("_id"))
                );
                handleResponse(context, sandbox.result());
            } else {
                utils.handleError(context, sandbox.cause());
            }
        });
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
    private void inviteMemberToSandbox(RoutingContext context) {
        JsonObject request = context.getBodyAsJson();
        shareService.inviteMemberToSandbox(request.getString(PARAM_SANBOXID), request.getString(PARAM_USER_EMAIL), request.getString(PARAM_ROLE_CODE), invitation -> {
            if (invitation.succeeded()) {
                try {
                    sendNotification(invitation.result(), context.user().principal(), request.getString(PARAM_USER_EMAIL), getLocale(context));
                    handleResponse(context, invitation.result());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.handleError(context, e);
                }
            } else {
                utils.handleError(context, invitation.cause());
            }
        });
    }

    private void sendNotification(JsonObject invitation, JsonObject user, String userEmail, String locale) {
        final JsonObject tplReq = new JsonObject();
        if (StringUtils.isNotBlank(invitation.getString(PARAM_USERID, ""))) {
            notificationsService.sendNotification(invitation.getString(PARAM_USERID), DBCollections.USER, new JsonObject()
                    .put(CONTENT_FIELD, Messages.getString("notification.sandbox.add.content", locale, user.getString(FIRSTNAME_FIELD) + " " + user.getString("name")))
                    .put(TITLE_FIELD, Messages.getString("notification.sandbox.add.title", locale))
                    .put(SENDER_ID_FIELD, user.getString("_id")), new JsonArray(), ar -> {
                // empty
            });
                /* send an E-mail to guest */
            tplReq.put(TemplatesDAOImpl.TEMPLATE, INVITE_URL)
                    .put(TemplatesDAOImpl.DATA, mailUtils.generateInvitationToSandboxBody(Json.decodeValue(user.encode(), com.qaobee.hive.business.model.commons.users.User.class), locale, userEmail, invitation.getString("_id"), "internal"));
        } else {
                /* send an E-mail to guest */
            tplReq.put(TemplatesDAOImpl.TEMPLATE, INVITE_URL)
                    .put(TemplatesDAOImpl.DATA, mailUtils.generateInvitationToSandboxBody(Json.decodeValue(user.encode(), com.qaobee.hive.business.model.commons.users.User.class), locale, userEmail, invitation.getString("_id"), "external"));
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
    private void reviveInvitationToSandbox(RoutingContext context) {
        shareService.reviveInvitationToUser(context.request().getParam(PARAM_INVITATION_ID), invitation -> {
            if (invitation.succeeded()) {
                try {
                    sendNotification(invitation.result(), context.user().principal(), invitation.result().getString(USER_EMAIL_FIELD), getLocale(context));
                    handleResponse(context, invitation.result());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.handleError(context, e);
                }
            } else {
                utils.handleError(context, invitation.cause());
            }
        });
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
    private void removeInvitationToSandbox(RoutingContext context) {
        shareService.removeInvitationToSandbox(context.request().getParam(PARAM_INVITATION_ID), handleResponse(context));
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
    private void getInvitationToSandbox(RoutingContext context) {
        shareService.getInvitationToSandbox(context.request().getParam(PARAM_INVITATION_ID), handleResponse(context));
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
    private void confirmInvitationToSandbox(RoutingContext context) {
        JsonObject request = context.getBodyAsJson();
        shareService.confirmInvitationToSandbox(request.getString(PARAM_INVITATION_ID), request.getString(PARAM_USERID), request.getString(PARAM_ANSWER_INVITATION), invitation -> {
            if (invitation.succeeded()) {
                JsonObject notification = new JsonObject();
                if ("accepted".equals(request.getString(PARAM_ANSWER_INVITATION))) {
                    notification.put(CONTENT_FIELD, Messages.getString("notification.sandbox.accept.content", getLocale(context), invitation.result().getString(USER_EMAIL_FIELD)))
                            .put(TITLE_FIELD, Messages.getString("notification.sandbox.accept.title", getLocale(context)))
                            .put(SENDER_ID_FIELD, request.getString(PARAM_USERID)
                            );
                } else {
                    notification.put(CONTENT_FIELD, Messages.getString("notification.sandbox.refuse.content", getLocale(context), invitation.result().getString(USER_EMAIL_FIELD)))
                            .put(TITLE_FIELD, Messages.getString("notification.sandbox.refuse.title", getLocale(context)))
                            .put(SENDER_ID_FIELD, request.getString(PARAM_USERID)
                            );
                }
                notificationsService.sendNotification(invitation.result().getString(SENDER_ID_FIELD), DBCollections.USER, notification, new JsonArray(), ar -> {
                    // empty
                });
                handleResponse(context, invitation.result());
            } else {
                utils.handleError(context, invitation.cause());
            }
        });
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
    private void getListOfSharedSandboxes(RoutingContext context) {
        shareService.getListOfSharedSandboxes(context.user().principal().getString("_id"), handleResponse(context));
    }

    /**
     * @apiDescription Get list of enriched sandboxes for the current user
     * @api {get} /api/1/share/sandbox/list Get list of enriched sandboxes for the current user
     * @apiName getListOfSharedSandboxes
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandboxes list of enriched sandboxes owned and as member;
     */
    private void getAdminListOfSharedSandboxes(RoutingContext context) {
        shareService.getListOfSharedSandboxes(context.request().getParam(PARAM_USERID), handleResponse(context));
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
    private void getListInvitationOfSandbox(RoutingContext context) {
        shareService.getListOfInvitationsToSandbox(context.request().getParam(PARAM_SANBOXID), context.request().getParam(PARAM_INVITATION_STATUS), handleResponseArray(context));
    }
}
