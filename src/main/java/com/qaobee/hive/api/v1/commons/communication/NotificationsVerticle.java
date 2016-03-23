package com.qaobee.hive.api.v1.commons.communication;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.communication.Notification;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
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
import org.vertx.java.core.json.EncodeException;
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


    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        /**
         * @apiDescription Fetch notifications for the current user
         * @api {post} /api/v1/commons/communication/notifications commons.communication.notifications
         * @apiName getUserNotifications
         * @apiGroup NotificationsVerticle
         * @apiSuccess {Array} notification com.qaobee.hive.business.model.commons.users.communication.Notification
         * @apiError HTTP_ERROR wrong request's method
         */
        final Handler<Message<String>> getUserNotifications = new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    utils.isUserLogged(req);
                    int start = 0;

                    if (req.getParams() != null && req.getParams().containsKey(PARAM_START)) {
                        start = Integer.parseInt(req.getParams().get(PARAM_START).get(0));
                    }
                    CriteriaBuilder cb = new CriteriaBuilder()
                            .add("user_id", req.getUser().get_id())
                            .add("deleted", false);
                    JsonArray notifications = mongo.findByCriterias(cb.get(), null, "timestamp", -1, -1, Notification.class);
                    int limit = notifications.size();
                    if (req.getParams() != null && req.getParams().containsKey(PARAM_LIMIT)) {
                        limit = Integer.parseInt(req.getParams().get(PARAM_LIMIT).get(0));
                    }
                    JsonArray jnotif = new JsonArray();
                    List<String> wl = Arrays.asList(new String[]  {"_id", "name", "firstname", "avatar"});
                    for (int i = start; i < limit; i++) {
                        JsonObject u = mongo.getById(((JsonObject) notifications.get(i)).getString("from_user_id"), User.class);
                        JsonObject cu = new JsonObject();
                        for(String f : u.getFieldNames()) {
                            if(wl.contains(f)) {
                                cu.putValue(f, u.getValue(f));
                            }
                        }
                        ((JsonObject) notifications.get(i)).putObject("from_user_id", cu);
                        jnotif.add(notifications.get(i));
                    }
                    message.reply(jnotif.encode());
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        };
        /**
         * @apiDescription Delete a notification
         * @api {put} /api/v1/commons/communication/notifications/del/?id=:id commons.communication.notifications.del
         * @apiName delNotificationHandler
         * @apiGroup NotificationsVerticle
         * @apiParam {String} id notification id
         * @apiSuccess {Object} status
         * @apiError HTTP_ERROR wrong request's method
         */
        final Handler<Message<String>> delNotificationHandler = new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.DELETE, req.getMethod());
                    utils.isUserLogged(req);
                    utils.testMandatoryParams(req.getParams(), PARAM_NOTIF_ID);
                    JsonObject n = mongo.getById(req.getParams().get(PARAM_NOTIF_ID).get(0), Notification.class);
                    n.putBoolean("deleted", true);
                    mongo.save(n, Notification.class);
                    vertx.eventBus().send("qaobee.notification." + n.getString("user_id"), new JsonObject());
                    utils.sendStatus(true, message);
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final EncodeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                }
            }
        };
        /**
         * @apiDescription Mark a notification as read
         * @api {put} /api/v1/commons/communication/notifications/read/?id=:id commons.communication.notifications.read
         * @apiName readNotificationHandler
         * @apiGroup NotificationsVerticle
         * @apiParam {String} id notification id
         * @apiSuccess {Object} status
         * @apiError HTTP_ERROR wrong request's method
         */
        final Handler<Message<String>> readNotificationHandler = new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    utils.isUserLogged(req);
                    utils.testMandatoryParams(req.getParams(), PARAM_NOTIF_ID);
                    JsonObject n = mongo.getById(req.getParams().get(PARAM_NOTIF_ID).get(0), Notification.class);
                    n.putBoolean("read", !n.getBoolean("read"));
                    mongo.save(n, Notification.class);
                    vertx.eventBus().send("qaobee.notification." + n.getString("user_id"), new JsonObject());
                    utils.sendStatus(true, message);
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final EncodeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                }
            }
        };
        /**
         * Add a notification to a collection
         * <p>Message : <pre>
         *     {
         *      id : "123456", // id of a document
         *      target : "User | SB_SandBoxCfg", // collection's name
         *      notification : {
         *          content: "bla bla bla",
         *          from_user_id : "123456",
         *          title : "Hello"
         *      }
         *     }
         * </pre></p>
         */
        final Handler<Message<JsonObject>> notify = new Handler<Message<JsonObject>>() {
            @Override
            public void handle(final Message<JsonObject> message) {
                try {
                    utils.testMandatoryParams(message.body().encode(), "id", "target", "notification");
                    String id = message.body().getString("id");
                    String collection = message.body().getString("target");
                    JsonObject notification = message.body().getObject("notification");
                    switch (collection) {
                        case "User":
                            addNotificationToUser(id, notification);
                            break;
                        case "SB_SandBoxCfg":
                            JsonObject sandbox = mongo.getById(id, "SB_SandBoxCfg");
                            for(int i = 0; i < sandbox.getArray("members").size(); i++)
                            // TODO : comment trouver tous les users d'une sandbox?
                            addNotificationToUser((String) sandbox.getArray("members").get(0), notification);
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
        };
        /*
         * Handlers registrations
		 */
        vertx.eventBus().registerHandler(LIST, getUserNotifications);
        vertx.eventBus().registerHandler(DEL, delNotificationHandler);
        vertx.eventBus().registerHandler(READ, readNotificationHandler);
        vertx.eventBus().registerHandler(NOTIFY, notify);

        vertx.eventBus().registerHandler(Module.VERSION + ".commons.communication.notifications.add", new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.isUserLogged(req);
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    JsonObject request = new JsonObject()
                            .putString("id", req.getParams().get("id").get(0))
                            .putString("target", User.class.getSimpleName())
                            .putObject("notification", new JsonObject(req.getBody()));
                    vertx.eventBus().send(NOTIFY, request, new Handler<Message<JsonObject>>() {
                        @Override
                        public void handle(Message<JsonObject> event) {
                            utils.sendStatus(event.body().getBoolean("status", false), message);
                        }
                    });
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
    }

    private void addNotificationToUser(String id, JsonObject notification) throws QaobeeException {
        notification.putString("_id", UUID.randomUUID().toString());
        notification.putString("user_id", id);
        notification.putNumber("timestamp", System.currentTimeMillis());
        notification.putBoolean("read", false);
        notification.putBoolean("deleted", false);
        mongo.save(notification, Notification.class);
        vertx.eventBus().send("qaobee.notification." + id, notification);
    }
}
