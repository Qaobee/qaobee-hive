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
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import org.apache.http.protocol.HTTP;
import org.jdeferred.Deferred;
import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final String FIELD_DEVICES = "devices";
    private static final String FIELD_PUSH_ID = "id";
    private static final String FIELD_DEVICE_OS = "os";
    private static final String ACCOUNT = "account";
    private static final String FIELD_PERSON_ID = "personId";
    private static final String FIELD_MEMBER_STATUS = "status";

    @Inject
    private MongoDB mongo;
    @Inject
    private Vertx vertx;
    @Inject
    @Named("firebase")
    private JsonObject firebase;
    @Inject
    private WebClient webClient;

    @Override
    public Promise<Boolean, QaobeeException, Integer> notify(String id, String collection, JsonObject notification, JsonArray exclude) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(id, collection)
                .done(target -> {
                    if (target == null) {
                        deferred.resolve(false);
                    } else {
                        switch (collection) {
                            case DBCollections.USER:
                                addNotificationToUser(id, notification).done(deferred::resolve).fail(deferred::reject);
                                break;
                            case DBCollections.SANDBOX:
                                addNotificationToSandbox(target, notification, exclude).done(deferred::resolve).fail(deferred::reject);
                                break;
                            default:
                                deferred.resolve(false);
                        }
                    }
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    private Promise<Boolean, QaobeeException, Integer> addNotificationToSandbox(JsonObject target, JsonObject notification, JsonArray exclude) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        final List<Object> excludeList = new ArrayList<>();
        final boolean[] res = {true};
        if (exclude != null) {
            exclude.forEach(excludeList::add);
        }

        if (target.getJsonArray(FIELD_MEMBERS) != null && target.getJsonArray(FIELD_MEMBERS).size() > 0) {
            for (int i = 0; i < target.getJsonArray(FIELD_MEMBERS).size(); i++) {
                if (!excludeList.contains((target.getJsonArray(FIELD_MEMBERS).getJsonObject(i)).getString(FIELD_PERSON_ID))
                        && "activated".equals((target.getJsonArray(FIELD_MEMBERS).getJsonObject(i)).getString(FIELD_MEMBER_STATUS))) {
                    LOG.info(target.getJsonArray(FIELD_MEMBERS).getJsonObject(i).getString(FIELD_PERSON_ID));
                    addNotificationToUser(target.getJsonArray(FIELD_MEMBERS).getJsonObject(i).getString(FIELD_PERSON_ID), notification)
                            .done(addRes -> res[0] = res[0] && addRes)
                            .fail(deferred::reject);
                }
            }
        }
        deferred.resolve(res[0]);
        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> addNotificationToUser(String id, JsonObject notification) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        notification.put("_id", UUID.randomUUID().toString())
                .put(TARGET_ID, id)
                .put("timestamp", System.currentTimeMillis())
                .put("read", false)
                .put(DELETED, false);
        mongo.upsert(notification, DBCollections.NOTIFICATION).fail(deferred::reject);
        mongo.getById(id, DBCollections.USER)
                .done(u -> {
                    if (u != null && u.containsKey(ACCOUNT) && u.getJsonObject(ACCOUNT).containsKey(FIELD_DEVICES)) {
                        // Send firebase notification
                        u.getJsonObject(ACCOUNT).getJsonArray(FIELD_DEVICES).forEach(d -> {
                            switch (((JsonObject) d).getString(FIELD_DEVICE_OS, "unknown")) {
                                case "android":
                                    notifyAndroid(notification, ((JsonObject) d).getString(FIELD_PUSH_ID));
                                    break;
                                case "ios":
                                    notifyIOS(notification, ((JsonObject) d).getString(FIELD_PUSH_ID));
                                    break;
                                default:
                                    break;
                            }
                        });
                    }
                    vertx.eventBus().send(WS_NOTIFICATION_PREFIX + id, notification);
                    deferred.resolve(true);
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    private static void notifyIOS(JsonObject notification, String pushId) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("IOS notification not yet implemented for : {}\nn{}", pushId, notification.encode());
        }
    }

    private void notifyAndroid(JsonObject notification, String pushId) {
        JsonObject requestBody = new JsonObject()
                .put("notification", new JsonObject()
                        .put("title", notification.getString("title"))
                        .put("text", notification.getString("content"))
                )
                .put("data", new JsonObject().put(SENDER_ID, notification.getString(SENDER_ID)))
                .put("to", pushId);
        webClient.post(firebase.getInteger("port"), firebase.getString("host"), firebase.getString("basePath"))
                .ssl(true)
                .putHeader("Authorization", "key= " + firebase.getString("api_key"))
                .putHeader(HTTP.CONTENT_TYPE, "application/json")
                .sendJsonObject(requestBody, res -> {
                    if (res.succeeded()) {
                        if (res.result().statusCode() >= 200 && res.result().statusCode() < 400) {
                            LOG.debug(res.result().bodyAsString());
                        } else {
                            LOG.error(res.result().statusCode() + " : " + res.result().statusMessage());
                        }
                    } else {
                        LOG.error(res.cause().getMessage(), res.cause());
                    }
                });
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> markAsRead(String id) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(id, DBCollections.NOTIFICATION)
                .done(n -> {
                    n.put("read", !n.getBoolean("read"));
                    mongo.upsert(n, DBCollections.NOTIFICATION)
                            .done(updtRes -> {
                                vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
                                deferred.resolve(n);
                            })
                            .fail(deferred::reject);
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> delete(String id) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(id, DBCollections.NOTIFICATION)
                .done(n -> {
                    n.put(DELETED, true);
                    mongo.upsert(n, DBCollections.NOTIFICATION)
                            .done(updtRes -> {
                                vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
                                deferred.resolve(n);
                            })
                            .fail(deferred::reject);
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getList(String id, int start, int limit) {
        Deferred<JsonArray, QaobeeException, Integer> deferred = new DeferredObject<>();
        CriteriaBuilder cb = new CriteriaBuilder()
                .add(TARGET_ID, id)
                .add(DELETED, false);
        mongo.findByCriterias(cb.get(), null, "timestamp", -1, -1, DBCollections.NOTIFICATION)
                .done(notifications -> {
                    JsonArray jnotif = new JsonArray();
                    int myLimit = limit;
                    if (myLimit == -1) {
                        myLimit = notifications.size();
                    }
                    List<Promise> promises = new ArrayList<>();
                    for (int i = start; i < start + myLimit; i++) {
                        promises.add(getUser(i, notifications.getJsonObject(i).getString(SENDER_ID)));
                    }
                    DeferredManager dm = new DefaultDeferredManager();
                    dm.when(promises.toArray(new Promise[promises.size()]))
                            .done(rs -> {
                                rs.forEach(u -> {
                                    JsonObject tuple = (JsonObject) u.getResult();
                                    notifications.getJsonObject(tuple.getInteger("index")).put(SENDER_ID, tuple.getJsonObject("user"));
                                    jnotif.add(notifications.getJsonObject(tuple.getInteger("index")));
                                });
                                deferred.resolve(jnotif);
                            })
                            .fail(e -> {
                                LOG.error(((Throwable) e.getReject()).getMessage());
                            });

                })
                .fail(deferred::reject);

        return deferred.promise();
    }

    private Promise<JsonObject, QaobeeException, Integer> getUser(int i, String id) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(id, DBCollections.USER)
                .done(u -> {
                    JsonObject cu = new JsonObject();
                    u.fieldNames().stream()
                            .filter(Arrays.asList("_id", "name", "firstname", "avatar")::contains)
                            .forEachOrdered(f -> cu.put(f, u.getValue(f)));
                    deferred.resolve(new JsonObject().put("index", i).put("user", cu));
                })
                .fail(deferred::reject);
        return deferred.promise();
    }
}
