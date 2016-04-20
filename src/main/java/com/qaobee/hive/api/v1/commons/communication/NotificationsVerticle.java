package com.qaobee.hive.api.v1.commons.communication;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.communication.Notification;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBoxCfg;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * The type Notifications verticle.
 */
@DeployableVerticle
public class NotificationsVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationsVerticle.class);
    private static final String WS_NOTIFICATION_PREFIX = "qaobee.notification.";
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;
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
    private static final String DELETED = "deleted";
    private static final String TARGET = "target";
    private static final String NOTIFICATION = "notification";
    private static final String SENDER_ID = "senderId";


    @Override
    @VerticleHandler({
            @Rule(address = LIST, method = Constantes.GET, logged = true),
            @Rule(address = DEL, method = Constantes.DELETE, logged = true, mandatoryParams = {PARAM_NOTIF_ID}, scope = Rule.Param.REQUEST),
            @Rule(address = READ, method = Constantes.POST, logged = true, mandatoryParams = {PARAM_NOTIF_ID}, scope = Rule.Param.REQUEST),
            @Rule(address = ADD_TO_USER, method = Constantes.POST, logged = true, mandatoryParams = {TARGET_ID, "content", SENDER_ID, "title"}, scope = Rule.Param.BODY),
            @Rule(address = ADD_TO_SANDBOX, method = Constantes.POST, logged = true, mandatoryParams = {TARGET_ID, "content", SENDER_ID, "title"}, scope = Rule.Param.BODY),
    })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        /**
         * @apiDescription Fetch the last notifications for the current user (from start to start+limit) ordered by
         *  the newest first
         * @api {post} /api/v1/commons/communication/notifications commons.communication.notifications
         * @apiParam {number} start start
         * @apiParam {number} limit limit
         * @apiName getUserNotifications
         * @apiGroup NotificationsVerticle
         * @apiSuccess {Array} notification com.qaobee.hive.business.model.commons.users.communication.Notification
         * @apiError HTTP_ERROR wrong request's method
         */
        vertx.eventBus().registerHandler(LIST, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    int start = 0;
                    if (req.getParams() != null && req.getParams().containsKey(PARAM_START)) {
                        start = Integer.parseInt(req.getParams().get(PARAM_START).get(0));
                    }
                    CriteriaBuilder cb = new CriteriaBuilder()
                            .add(TARGET_ID, req.getUser().get_id())
                            .add(DELETED, false);
                    JsonArray notifications = mongo.findByCriterias(cb.get(), null, "timestamp", -1, -1, Notification.class);
                    int limit = notifications.size();
                    if (req.getParams() != null && req.getParams().containsKey(PARAM_LIMIT)) {
                        limit = Integer.parseInt(req.getParams().get(PARAM_LIMIT).get(0));
                    }
                    JsonArray jnotif = new JsonArray();
                    for (int i = start; i < start + limit; i++) {
                        ((JsonObject) notifications.get(i)).putObject(SENDER_ID, getUser(((JsonObject) notifications.get(i)).getString(SENDER_ID)));
                        jnotif.add(notifications.get(i));
                    }
                    message.reply(jnotif.encode());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
        /**
         * @apiDescription Delete a notification
         * @api {put} /api/v1/commons/communication/notifications/del/?id=:id commons.communication.notifications.del
         * @apiName delNotificationHandler
         * @apiGroup NotificationsVerticle
         * @apiParam {String} id notification id
         * @apiSuccess {Object} status
         * @apiError HTTP_ERROR wrong request's method
         */
        vertx.eventBus().registerHandler(DEL, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    JsonObject n = mongo.getById(req.getParams().get(PARAM_NOTIF_ID).get(0), Notification.class);
                    n.putBoolean(DELETED, true);
                    mongo.save(n, Notification.class);
                    vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
                    utils.sendStatus(true, message);
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
        /**
         * @apiDescription Mark a notification as read
         * @api {put} /api/v1/commons/communication/notifications/read/?id=:id commons.communication.notifications.read
         * @apiName readNotificationHandler
         * @apiGroup NotificationsVerticle
         * @apiParam {String} id notification id
         * @apiSuccess {Object} status
         * @apiError HTTP_ERROR wrong request's method
         */
        vertx.eventBus().registerHandler(READ, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    JsonObject n = mongo.getById(req.getParams().get(PARAM_NOTIF_ID).get(0), Notification.class);
                    n.putBoolean("read", !n.getBoolean("read"));
                    mongo.save(n, Notification.class);
                    vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
                    utils.sendStatus(true, message);
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
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
        vertx.eventBus().registerHandler(NOTIFY, new Handler<Message<JsonObject>>() {
            @Override
            public void handle(final Message<JsonObject> message) {
                try {
                    utils.testMandatoryParams(message.body().encode(), "id", TARGET, NOTIFICATION);
                    String id = message.body().getString("id");
                    String collection = message.body().getString(TARGET);
                    JsonObject notification = message.body().getObject(NOTIFICATION);
                    switch (collection) {
                        case "User":
                            mongo.getById(id, collection);
                            addNotificationToUser(id, notification);
                            break;
                        case "SB_SandBoxCfg":
                            JsonObject sandbox = mongo.getById(id, collection);
                            for (int i = 0; i < sandbox.getArray("members").size(); i++)
                                addNotificationToUser(((JsonObject) sandbox.getArray("members").get(0)).getString("personId"), notification);
                            break;
                        default:
                            break;
                    }
                    utils.sendStatusJson(true, message);
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendStatusJson(false, message);
                }
            }
        });

        vertx.eventBus().registerHandler(ADD_TO_USER, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                JsonObject notification = new JsonObject(req.getBody());
                JsonObject request = new JsonObject()
                        .putString("id", notification.getString(TARGET_ID))
                        .putString(TARGET, User.class.getSimpleName())
                        .putObject(NOTIFICATION, notification);
                vertx.eventBus().send(NOTIFY, request, new Handler<Message<JsonObject>>() {
                    @Override
                    public void handle(Message<JsonObject> event) {
                        utils.sendStatus(event.body().getBoolean("status", false), message);
                    }
                });
            }
        });

        vertx.eventBus().registerHandler(ADD_TO_SANDBOX, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                JsonObject notification = new JsonObject(req.getBody());
                JsonObject request = new JsonObject()
                        .putString("id", notification.getString(TARGET_ID))
                        .putString(TARGET, SB_SandBoxCfg.class.getSimpleName())
                        .putObject(NOTIFICATION, new JsonObject(req.getBody()));
                vertx.eventBus().send(NOTIFY, request, new Handler<Message<JsonObject>>() {
                    @Override
                    public void handle(Message<JsonObject> event) {
                        utils.sendStatus(event.body().getBoolean("status", false), message);
                    }
                });
            }
        });
    }

    private JsonObject getUser(String id) throws QaobeeException {
        List<String> wl = Arrays.asList("_id", "name", "firstname", "avatar");
        JsonObject u = mongo.getById(id, User.class);
        JsonObject cu = new JsonObject();
        for (String f : u.getFieldNames()) {
            if (wl.contains(f)) {
                cu.putValue(f, u.getValue(f));
            }
        }
        return cu;
    }

    /**
     * @param id           id user
     * @param notification notification object
     * @throws QaobeeException exception
     */
    private void addNotificationToUser(String id, JsonObject notification) throws QaobeeException {
        notification.putString("_id", UUID.randomUUID().toString());
        notification.putString(TARGET_ID, id);
        notification.putNumber("timestamp", System.currentTimeMillis());
        notification.putBoolean("read", false);
        notification.putBoolean(DELETED, false);
        mongo.save(notification, Notification.class);
        vertx.eventBus().send("qaobee.notification." + id, notification);
    }
}
