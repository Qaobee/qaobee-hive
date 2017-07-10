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

package com.qaobee.hive.api.v1.commons.communication;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.exceptions.QaobeeSvcException;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * The type Notifications verticle.
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/commons/communication/notifications")
public class NotificationsRoute extends AbstractRoute {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationsRoute.class);

    /*public static final String LIST = Module.VERSION + ".commons.communication.notifications";
    public static final String DEL = Module.VERSION + ".commons.communication.notifications.del";
    public static final String READ = Module.VERSION + ".commons.communication.notifications.read";
    public static final String ADD_TO_USER = Module.VERSION + ".commons.communication.notifications.user.add";
    public static final String ADD_TO_SANDBOX = Module.VERSION + ".commons.communication.notifications.sandbox.add";*/

    /**
     * The constant PARAM_LIMIT.
     */
    public static final String PARAM_LIMIT = "limit";
    /**
     * The constant PARAM_START.
     */
    public static final String PARAM_START = "start";
    /**
     * The constant PARAM_NOTIF_ID.
     */
    public static final String PARAM_NOTIF_ID = "id";
    private static final String TARGET_ID = "targetId";
    private static final String TARGET = "target";
    private static final String NOTIFICATION = "notification";
    private static final String SENDER_ID = "senderId";

    @Inject
    private NotificationsService notificationsService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);
        router.get("/").handler(authHandler);
        router.get("/").handler(this::notificationList);
        router.delete("/del").handler(authHandler);
        router.delete("/del").handler(this::delete);
        router.post("/read").handler(authHandler);
        router.post("/read").handler(this::markAsRead);
        router.post("/user/add").handler(authHandler);
        router.post("/user/add").handler(this::addNotificationToUser);
        router.post("/sandbox/add").handler(authHandler);
        router.post("/sandbox/add").handler(this::addNotificationToSandBox);
        return router;
    }


    /**
     * Add a notification to a collection
     * <p>Message : <pre>
     *     {
     *      id : "123456", // id of a document
     *      target : "User | SB_SandBoxCfg", // collection's name
     *      notification : {
     *          content: "bla bla bla",
     *          senderId : "123456",
     *          title : "Hello"
     *      }
     *     }
     * </pre></p>
     */
    private void addNotificationToSandBox(RoutingContext context) {
        try {
            utils.testMandatoryParams(context, TARGET_ID, "content", SENDER_ID, "title");
            JsonObject notification = context.getBodyAsJson();
            notifyPeople(new JsonObject()
                    .put("id", notification.getString(TARGET_ID))
                    .put(TARGET, DBCollections.SANDBOX)
                    .put(NOTIFICATION, notification), context);
        } catch (QaobeeException e) {
            handleError(context, e);
        }
    }

    /**
     * Add a notification to a collection
     * <p>Message : <pre>
     *     {
     *      id : "123456", // id of a document
     *      target : "User | SB_SandBoxCfg", // collection's name
     *      notification : {
     *          content: "bla bla bla",
     *          senderId : "123456",
     *          title : "Hello"
     *      }
     *     }
     * </pre></p>
     */
    private void addNotificationToUser(RoutingContext context) {
        try {
            utils.testMandatoryParams(context, TARGET_ID, "content", SENDER_ID, "title");
            JsonObject notification = context.getBodyAsJson();
            notifyPeople(new JsonObject()
                    .put("id", notification.getString(TARGET_ID))
                    .put(TARGET, DBCollections.USER)
                    .put(NOTIFICATION, notification), context);
        } catch (QaobeeException e) {
            handleError(context, e);
        }
    }

    private void notifyPeople(JsonObject obj, RoutingContext context) {
        try {
            utils.testMandatoryParams(obj, "id", TARGET, NOTIFICATION);
            notificationsService.notify(obj.getString("id"),
                    obj.getString(TARGET), obj.getJsonObject(NOTIFICATION),
                    obj.getJsonObject(NOTIFICATION).getJsonArray("exclude"), ar -> {
                        if (ar.succeeded()) {
                            handleStatus(ar.result(), context);
                        } else {
                            handleStatus(false, context);
                        }
                    }
            );
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            handleStatus(false, context);
        }
    }

    /**
     * @apiDescription Mark a notification as read
     * @api {put} /api/v1/commons/communication/notifications/read/?id=:id commons.communication.notifications.read
     * @apiName readNotificationHandler
     * @apiGroup NotificationsVerticle
     * @apiParam {String} id notification id
     * @apiSuccess {Object} status
     * @apiHeader {String} token
     */
    private void markAsRead(RoutingContext context) {
        try {
            utils.testMandatoryParams(context.request().params(), PARAM_NOTIF_ID);
            notificationsService.markAsRead(context.request().getParam(PARAM_NOTIF_ID), ar -> {
                if(ar.succeeded()) {
                    handleStatus(ar.succeeded(), context);
                } else {
                    handleError(context, (QaobeeSvcException) ar.cause());
                }
            });
        } catch (QaobeeException e) {
            handleError(context, e);
        }
    }

    /**
     * @apiDescription Delete a notification
     * @api {put} /api/v1/commons/communication/notifications/del/?id=:id commons.communication.notifications.del
     * @apiName delNotificationHandler
     * @apiGroup NotificationsVerticle
     * @apiParam {String} id notification id
     * @apiHeader {String} token
     * @apiSuccess {Object} status
     */
    private void delete(RoutingContext context) {
        try {
            utils.testMandatoryParams(context.request().params(), PARAM_NOTIF_ID);
            notificationsService.delete(context.request().getParam(PARAM_NOTIF_ID),ar -> {
                if (ar.succeeded()) {
                    handleStatus(ar.succeeded(), context);
                } else {
                    handleError(context, (QaobeeSvcException) ar.cause());
                }
            });
        } catch (QaobeeException e) {
            handleError(context, e);
        }
    }

    /**
     * @apiDescription Fetch the last notifications for the current user (from start to start+limit) ordered by
     * the newest first
     * @api {post} /api/v1/commons/communication/notifications commons.communication.notifications
     * @apiParam {number} start start
     * @apiParam {number} limit limit
     * @apiName getUserNotifications
     * @apiGroup NotificationsVerticle
     * @apiHeader {String} token
     * @apiSuccess {Array} notification com.qaobee.hive.business.model.commons.users.communication.Notification
     */
    private void notificationList(RoutingContext context) {
        int start = 0;
        if (context.request().params().contains(PARAM_START)) {
            start = Integer.parseInt(context.request().getParam(PARAM_START));
        }
        int limit = -1;
        if (context.request().params().contains(PARAM_LIMIT)) {
            limit = Integer.parseInt(context.request().getParam(PARAM_LIMIT));
        }
        notificationsService.getList(context.user().principal().getString("_id"), start, limit, handleResponseArray(context));
    }
}
