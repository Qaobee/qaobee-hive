package com.qaobee.hive.dao.impl;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.communication.Notification;
import com.qaobee.hive.dao.NotificationsDAO;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * The type Notifications dao.
 */
public class NotificationsDAOImpl implements NotificationsDAO {
    private static final String WS_NOTIFICATION_PREFIX = "qaobee.notification.";
    private static final String SENDER_ID = "senderId";
    private static final String TARGET_ID = "targetId";
    private static final String DELETED = "deleted";
    private static final String FIELD_MEMBERS = "members";

    @Inject
    private MongoDB mongo;
    @Inject
    private Vertx vertx;

    @Override
    public boolean notify(String id, String collection, JsonObject notification, JsonArray exclude) throws QaobeeException {
        JsonObject target = mongo.getById(id, collection);
        if (target == null) {
           return false;
        } else {
            switch (collection) {
                case "User":
                    addNotificationToUser(id, notification);
                    break;
                case "SB_SandBoxCfg":
                    addNotificationToSandboxCfg(target,notification, exclude);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private void addNotificationToSandboxCfg(JsonObject target, JsonObject notification, JsonArray exclude) throws QaobeeException {
        List<Object> excludeList = new ArrayList<>();
        if(exclude != null) {
            exclude.forEach(excludeList::add);
        }
        for (int i = 0; i < target.getArray(FIELD_MEMBERS).size(); i++) {
            if (!excludeList.contains(((JsonObject) target.getArray(FIELD_MEMBERS).get(0)).getString("personId"))) {
                addNotificationToUser(((JsonObject) target.getArray(FIELD_MEMBERS).get(0)).getString("personId"), notification);
            }
        }
    }

    @Override
    public void addNotificationToUser(String id, JsonObject notification) throws QaobeeException {
        notification.putString("_id", UUID.randomUUID().toString())
                .putString(TARGET_ID, id)
                .putNumber("timestamp", System.currentTimeMillis())
                .putBoolean("read", false)
                .putBoolean(DELETED, false);
        mongo.save(notification, Notification.class);
        vertx.eventBus().send(WS_NOTIFICATION_PREFIX + id, notification);
    }

    @Override
    public JsonObject markAsRead(String id) throws QaobeeException {
        JsonObject n = mongo.getById(id, Notification.class);
        n.putBoolean("read", !n.getBoolean("read"));
        mongo.save(n, Notification.class);
        vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
        return n;
    }

    @Override
    public JsonObject delete(String id) throws QaobeeException {
        JsonObject n = mongo.getById(id, Notification.class);
        n.putBoolean(DELETED, true);
        mongo.save(n, Notification.class);
        vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
        return n;
    }

    @Override
    public JsonArray getList(String id, int start, int limit) throws QaobeeException {
        CriteriaBuilder cb = new CriteriaBuilder()
                .add(TARGET_ID, id)
                .add(DELETED, false);
        JsonArray notifications = mongo.findByCriterias(cb.get(), null, "timestamp", -1, -1, Notification.class);

        JsonArray jnotif = new JsonArray();
        int myLimit = limit;
        if (myLimit == -1) {
            myLimit = notifications.size();
        }
        for (int i = start; i < start + myLimit; i++) {
            ((JsonObject) notifications.get(i)).putObject(SENDER_ID, getUser(((JsonObject) notifications.get(i)).getString(SENDER_ID)));
            jnotif.add(notifications.get(i));
        }
        return jnotif;
    }

    private JsonObject getUser(String id) throws QaobeeException {
        JsonObject u = mongo.getById(id, User.class);
        JsonObject cu = new JsonObject();
        u.getFieldNames().stream()
                .filter(Arrays.asList("_id", "name", "firstname", "avatar")::contains)
                .forEachOrdered(f -> cu.putValue(f, u.getValue(f)));
        return cu;
    }
}
