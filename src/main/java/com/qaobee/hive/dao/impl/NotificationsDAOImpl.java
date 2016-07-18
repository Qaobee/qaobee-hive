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

package com.qaobee.hive.dao.impl;

import com.qaobee.hive.dao.NotificationsDAO;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(NotificationsDAOImpl.class);
    private static final String WS_NOTIFICATION_PREFIX = "qaobee.notification.";
    private static final String SENDER_ID = "senderId";
    private static final String TARGET_ID = "targetId";
    private static final String DELETED = "deleted";
    private static final String FIELD_MEMBERS = "members";
    private static final String COLLECTION = "Notification";

    @Inject
    private MongoDB mongo;
    @Inject
    private Vertx vertx;

    @Override
    public boolean notify(String id, String collection, JsonObject notification, JsonArray exclude) {
        try {
            JsonObject target = mongo.getById(id, collection);
            if (target == null) {
                return false;
            } else {
                switch (collection) {
                    case "User":
                        return addNotificationToUser(id, notification);
                    case "SB_SandBox":
                        return addNotificationToSandbox(target, notification, exclude);
                    default:
                        return true;
                }
            }
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }

    private boolean addNotificationToSandbox(JsonObject target, JsonObject notification, JsonArray exclude) {
        List<Object> excludeList = new ArrayList<>();
        boolean res = true;
        if(exclude != null) {
            exclude.forEach(excludeList::add);
        }
        for (int i = 0; i < target.getArray(FIELD_MEMBERS).size(); i++) {
            if (!excludeList.contains(((JsonObject) target.getArray(FIELD_MEMBERS).get(0)).getString("personId"))) {
                res = res && addNotificationToUser(((JsonObject) target.getArray(FIELD_MEMBERS).get(0)).getString("personId"), notification);
            }
        }
        return res;
    }

    @Override
    public boolean addNotificationToUser(String id, JsonObject notification) {
        try {
            notification.putString("_id", UUID.randomUUID().toString())
                    .putString(TARGET_ID, id)
                    .putNumber("timestamp", System.currentTimeMillis())
                    .putBoolean("read", false)
                    .putBoolean(DELETED, false);
            mongo.save(notification, COLLECTION);
            vertx.eventBus().send(WS_NOTIFICATION_PREFIX + id, notification);
            return true;
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public JsonObject markAsRead(String id) throws QaobeeException {
        JsonObject n = mongo.getById(id, COLLECTION);
        n.putBoolean("read", !n.getBoolean("read"));
        mongo.save(n, COLLECTION);
        vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
        return n;
    }

    @Override
    public JsonObject delete(String id) throws QaobeeException {
        JsonObject n = mongo.getById(id, COLLECTION);
        n.putBoolean(DELETED, true);
        mongo.save(n, COLLECTION);
        vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
        return n;
    }

    @Override
    public JsonArray getList(String id, int start, int limit) throws QaobeeException {
        CriteriaBuilder cb = new CriteriaBuilder()
                .add(TARGET_ID, id)
                .add(DELETED, false);
        JsonArray notifications = mongo.findByCriterias(cb.get(), null, "timestamp", -1, -1, COLLECTION);

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
        JsonObject u = mongo.getById(id, "User");
        JsonObject cu = new JsonObject();
        u.getFieldNames().stream()
                .filter(Arrays.asList("_id", "name", "firstname", "avatar")::contains)
                .forEachOrdered(f -> cu.putValue(f, u.getValue(f)));
        return cu;
    }
}
