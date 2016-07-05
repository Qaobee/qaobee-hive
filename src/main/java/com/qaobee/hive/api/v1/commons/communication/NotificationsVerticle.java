package com.qaobee.hive.api.v1.commons.communication;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.dao.NotificationsDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;

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
    private Utils utils;
    @Inject
    private NotificationsDAO notificationsDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(LIST, this::notificationList)
                .registerHandler(DEL, this::delete)
                .registerHandler(READ, this::markAsRead)
                .registerHandler(NOTIFY, this::notify)
                .registerHandler(ADD_TO_USER, this::addNotificationToUser)
                .registerHandler(ADD_TO_SANDBOX, this::addNotificationToSandBox);
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
        vertx.eventBus().send(NOTIFY, new JsonObject()
                .putString("id", new JsonObject(req.getBody()).getString(TARGET_ID))
                .putString(TARGET, SB_SandBox.class.getSimpleName())
                .putObject(NOTIFICATION, new JsonObject(req.getBody())), (Handler<Message<JsonObject>>) event ->
                utils.sendStatus(event.body().getBoolean("status", false), message)
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
                .putString("id", notification.getString(TARGET_ID))
                .putString(TARGET, User.class.getSimpleName())
                .putObject(NOTIFICATION, notification), (Handler<Message<JsonObject>>) event ->
                utils.sendStatus(event.body().getBoolean("status", false), message)
        );
    }

    private void notify(Message<JsonObject> message) {
        try {
            utils.testMandatoryParams(message.body().encode(), "id", TARGET, NOTIFICATION);
            utils.sendStatusJson(notificationsDAO.notify(message.body().getString("id"),
                    message.body().getString(TARGET), message.body().getObject(NOTIFICATION),
                    message.body().getArray("exclude")
            ), message);
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
    @Rule(address = READ, method = Constants.POST, logged = true, mandatoryParams = {PARAM_NOTIF_ID}, scope = Rule.Param.REQUEST)
    private void markAsRead(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            notificationsDAO.markAsRead(req.getParams().get(PARAM_NOTIF_ID).get(0));
            utils.sendStatus(true, message);
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
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
     * @apiError HTTP_ERROR wrong request's method
     */
    @Rule(address = DEL, method = Constants.DELETE, logged = true, mandatoryParams = {PARAM_NOTIF_ID}, scope = Rule.Param.REQUEST)
    private void delete(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            notificationsDAO.delete(req.getParams().get(PARAM_NOTIF_ID).get(0));
            utils.sendStatus(true, message);
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
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
     * @apiError HTTP_ERROR wrong request's method
     */
    @Rule(address = LIST, method = Constants.GET, logged = true)
    private void notificationList(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            int start = 0;
            if (req.getParams() != null && req.getParams().containsKey(PARAM_START)) {
                start = Integer.parseInt(req.getParams().get(PARAM_START).get(0));
            }
            int limit = -1;
            if (req.getParams() != null && req.getParams().containsKey(PARAM_LIMIT)) {
                limit = Integer.parseInt(req.getParams().get(PARAM_LIMIT).get(0));
            }
            message.reply(notificationsDAO.getList(req.getUser().get_id(), start, limit).encode());
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
