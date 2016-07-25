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
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import javax.inject.Named;
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
    private static final String PUSH_ID = "pushId";
    private static final String ACCOUNT = "account";

    @Inject
    private MongoDB mongo;
    @Inject
    private Vertx vertx;
    @Inject
    @Named("firebase")
    JsonObject firebase;

    @Override
    public boolean notify(String id, String collection, JsonObject notification, JsonArray exclude) {
        try {
            JsonObject target = mongo.getById(id, collection);
            if (target == null) {
                return false;
            } else {
                switch (collection) {
                    case DBCollections.USER:
                        return addNotificationToUser(id, notification);
                    case DBCollections.SANDBOX:
                        return addNotificationToSandbox(target, notification, exclude);
                    default:
                        return false;
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
            mongo.save(notification, DBCollections.NOTIFICATION);
            JsonObject u = mongo.getById(id, DBCollections.USER);
            if (u != null && u.containsField(ACCOUNT) && u.getObject(ACCOUNT).containsField(PUSH_ID) && StringUtils.isNotBlank(u.getObject(ACCOUNT).getString(PUSH_ID, ""))) {
                // Send firebase notification
                JsonObject requestBody = new JsonObject()
                        .putObject("notification", new JsonObject()
                                .putString("title", notification.getString("title"))
                                .putString("text", notification.getString("content"))
                        )
                        .putObject("data", new JsonObject().putString(SENDER_ID, notification.getString(SENDER_ID)))
                        .putString("to", u.getObject(ACCOUNT).getString(PUSH_ID));
                HttpClient client = vertx.createHttpClient().setKeepAlive(true);
                client.setHost(firebase.getString("host"));
                client.setPort(firebase.getInteger("port"));
                client.setSSL(true).setTrustAll(true);
                client.exceptionHandler(ex -> LOG.error(ex.getMessage(), ex));
                client.post(firebase.getString("basePath"), resp -> {
                    if (resp.statusCode() >= 200 && resp.statusCode() < 400) {
                        resp.bodyHandler(buffer -> {

                        });
                    } else {
                        LOG.error(resp.statusCode() + " : " + resp.statusMessage());
                    }
                })
                        .putHeader("Authorization", "key= " + firebase.getString("api_key"))
                        .putHeader(HTTP.CONTENT_TYPE, "application/json")
                        .putHeader(HTTP.CONTENT_LEN, String.valueOf(requestBody.encode().length()))
                        .end(requestBody.encode());
            }
            vertx.eventBus().send(WS_NOTIFICATION_PREFIX + id, notification);
            return true;
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public JsonObject markAsRead(String id) throws QaobeeException {
        JsonObject n = mongo.getById(id, DBCollections.NOTIFICATION);
        n.putBoolean("read", !n.getBoolean("read"));
        mongo.save(n, DBCollections.NOTIFICATION);
        vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
        return n;
    }

    @Override
    public JsonObject delete(String id) throws QaobeeException {
        JsonObject n = mongo.getById(id, DBCollections.NOTIFICATION);
        n.putBoolean(DELETED, true);
        mongo.save(n, DBCollections.NOTIFICATION);
        vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
        return n;
    }

    @Override
    public JsonArray getList(String id, int start, int limit) throws QaobeeException {
        CriteriaBuilder cb = new CriteriaBuilder()
                .add(TARGET_ID, id)
                .add(DELETED, false);
        JsonArray notifications = mongo.findByCriterias(cb.get(), null, "timestamp", -1, -1, DBCollections.NOTIFICATION);

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
        JsonObject u = mongo.getById(id, DBCollections.USER);
        JsonObject cu = new JsonObject();
        u.getFieldNames().stream()
                .filter(Arrays.asList("_id", "name", "firstname", "avatar")::contains)
                .forEachOrdered(f -> cu.putValue(f, u.getValue(f)));
        return cu;
    }
}
