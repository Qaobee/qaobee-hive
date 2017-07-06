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
import com.qaobee.hive.dao.NotificationsDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * The type Notifications verticle.
 */
@DeployableVerticle
public class NotificationsVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationsVerticle.class);
    /**
     * The constant LIST.
     */
    public static final String LIST = Module.VERSION + ".commons.communication.notifications";
    /**
     * The constant DEL.
     */
    public static final String DEL = Module.VERSION + ".commons.communication.notifications.del";
    /**
     * The constant READ.
     */
    public static final String READ = Module.VERSION + ".commons.communication.notifications.read";
    /**
     * The constant ADD_TO_USER.
     */
    public static final String ADD_TO_USER = Module.VERSION + ".commons.communication.notifications.user.add";
    /**
     * The constant ADD_TO_SANDBOX.
     */
    public static final String ADD_TO_SANDBOX = Module.VERSION + ".commons.communication.notifications.sandbox.add";
    /**
     * The constant NOTIFY.
     */
    public static final String NOTIFY = "internal.notify";
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
    private NotificationsDAO notificationsDAO;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this)
                .add(LIST, this::notificationList)
                .add(DEL, this::delete)
                .add(READ, this::markAsRead)
                .add(NOTIFY, this::notifyPeople)
                .add(ADD_TO_USER, this::addNotificationToUser)
                .add(ADD_TO_SANDBOX, this::addNotificationToSandBox)
                .register(startFuture);
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
    @Rule(address = ADD_TO_SANDBOX, method = Constants.POST, logged = true,
            mandatoryParams = {TARGET_ID, "content", SENDER_ID, "title"},
            scope = Rule.Param.BODY)
    private void addNotificationToSandBox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject notification = new JsonObject(req.getBody());
        vertx.eventBus().send(NOTIFY, new JsonObject()
                        .put("id", notification.getString(TARGET_ID))
                        .put(TARGET, "SB_SandBox")
                        .put(NOTIFICATION, notification),
                (Handler<AsyncResult<Message<JsonObject>>>) event -> utils.sendStatus(event.result().body().getBoolean("status", false), message)
        );
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
    @Rule(address = ADD_TO_USER, method = Constants.POST, logged = true,
            mandatoryParams = {TARGET_ID, "content", SENDER_ID, "title"},
            scope = Rule.Param.BODY)
    private void addNotificationToUser(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject notification = new JsonObject(req.getBody());
        vertx.eventBus().send(NOTIFY, new JsonObject()
                .put("id", notification.getString(TARGET_ID))
                .put(TARGET, "User")
                .put(NOTIFICATION, notification), (Handler<AsyncResult<Message<JsonObject>>>) event ->
                utils.sendStatus(event.result().body().getBoolean("status", false), message)
        );
    }

    private void notifyPeople(Message<JsonObject> message) {
        try {
            utils.testMandatoryParams(message.body(), "id", TARGET, NOTIFICATION);
            notificationsDAO.notify(message.body().getString("id"),
                    message.body().getString(TARGET), message.body().getJsonObject(NOTIFICATION),
                    message.body().getJsonObject(NOTIFICATION).getJsonArray("exclude")
            )
                    .done(r -> utils.sendStatusJson(r, message))
                    .fail(e -> utils.sendStatusJson(false, message));
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendStatusJson(false, message);
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
     * @apiError HTTP_ERROR wrong request's method
     */
    @Rule(address = READ, method = Constants.POST, logged = true, mandatoryParams = PARAM_NOTIF_ID,
            scope = Rule.Param.REQUEST)
    private void markAsRead(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        notificationsDAO.markAsRead(req.getParams().get(PARAM_NOTIF_ID).get(0))
                .done(json -> utils.sendStatus(true, message))
                .fail(e -> utils.sendError(message, e));
    }

    /**
     * @apiDescription Delete a notification
     * @api {put} /api/v1/commons/communication/notifications/del/?id=:id commons.communication.notifications.del
     * @apiName delNotificationHandler
     * @apiGroup NotificationsVerticle
     * @apiParam {String} id notification id
     * @apiHeader {String} token
     * @apiSuccess {Object} status
     * @apiError HTTP_ERROR wrong request's method
     */
    @Rule(address = DEL, method = Constants.DELETE, logged = true, mandatoryParams = PARAM_NOTIF_ID,
            scope = Rule.Param.REQUEST)
    private void delete(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        notificationsDAO.delete(req.getParams().get(PARAM_NOTIF_ID).get(0))
                .done(json -> utils.sendStatus(true, message))
                .fail(e -> utils.sendError(message, e));
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
     * @apiError HTTP_ERROR wrong request's method
     */
    @Rule(address = LIST, method = Constants.GET, logged = true)
    private void notificationList(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        int start = 0;
        if (req.getParams() != null && req.getParams().containsKey(PARAM_START)) {
            start = Integer.parseInt(req.getParams().get(PARAM_START).get(0));
        }
        int limit = -1;
        if (req.getParams() != null && req.getParams().containsKey(PARAM_LIMIT)) {
            limit = Integer.parseInt(req.getParams().get(PARAM_LIMIT).get(0));
        }
        replyJsonArray(message, notificationsDAO.getList(req.getUser().get_id(), start, limit));
    }
}
