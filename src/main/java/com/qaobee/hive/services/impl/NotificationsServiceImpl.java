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

package com.qaobee.hive.services.impl;

import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import io.vertx.core.*;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import org.apache.http.protocol.HTTP;
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
@ProxyService(address = NotificationsService.ADDRESS, iface = NotificationsService.class)
public class NotificationsServiceImpl implements NotificationsService {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationsServiceImpl.class);
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
    private static final String FIELD_INDEX = "index";

    @Inject
    private MongoDB mongo;
    @Inject
    @Named("firebase")
    private JsonObject firebase;
    @Inject
    private WebClient webClient;

    private Vertx vertx;

    public NotificationsServiceImpl(Vertx vertx) {
        super();
        this.vertx = vertx;
    }


    private void addNotificationToSandbox(JsonObject target, JsonObject notification, JsonArray exclude, Handler<AsyncResult<Boolean>> resultHandler) {
        final List<Object> excludeList = new ArrayList<>();
        final boolean[] res = {true};
        if (exclude != null) {
            exclude.forEach(excludeList::add);
        }
        List<Future> promises = new ArrayList<>();
        if (target.getJsonArray(FIELD_MEMBERS) != null && target.getJsonArray(FIELD_MEMBERS).size() > 0) {
            for (int i = 0; i < target.getJsonArray(FIELD_MEMBERS).size(); i++) {
                if (!excludeList.contains((target.getJsonArray(FIELD_MEMBERS).getJsonObject(i)).getString(FIELD_PERSON_ID))
                        && "activated".equals((target.getJsonArray(FIELD_MEMBERS).getJsonObject(i)).getString(FIELD_MEMBER_STATUS))) {
                    LOG.info(target.getJsonArray(FIELD_MEMBERS).getJsonObject(i).getString(FIELD_PERSON_ID));
                    Future<Boolean> f = Future.future();

                    addNotificationToUser(target.getJsonArray(FIELD_MEMBERS).getJsonObject(i).getString(FIELD_PERSON_ID),
                            notification, addRes -> {
                                if (addRes.succeeded()) {
                                    f.complete(addRes.result());
                                }
                            });
                    promises.add(f);
                }
            }
        }
        CompositeFuture.all(promises).setHandler(rs -> {
            if (rs.succeeded()) {
                rs.result().list().forEach(r -> res[0] = res[0] && (boolean) r);
                resultHandler.handle(Future.succeededFuture(res[0]));
            } else {
                resultHandler.handle(Future.failedFuture(rs.cause()));
            }
        });
    }

    @Override
    public void sendNotification(String id, String collection, JsonObject notification, JsonArray exclude, Handler<AsyncResult<Boolean>> resultHandler) {
        mongo.getById(id, collection, target -> {
            if (target.succeeded()) {
                if (target.result() == null) {
                    resultHandler.handle(Future.succeededFuture(false));
                } else {
                    switch (collection) {
                        case DBCollections.USER:
                            addNotificationToUser(id, notification, resultHandler);
                            break;
                        case DBCollections.SANDBOX:
                            addNotificationToSandbox(target.result(), notification, exclude, resultHandler);
                            break;
                        default:
                            resultHandler.handle(Future.succeededFuture(false));
                    }
                }
            } else {
                resultHandler.handle(Future.succeededFuture(false));
            }
        });
    }

    @Override
    public void addNotificationToUser(String id, JsonObject notification, Handler<AsyncResult<Boolean>> resultHandler) {
        notification.put("_id", UUID.randomUUID().toString())
                .put(TARGET_ID, id)
                .put("timestamp", System.currentTimeMillis())
                .put("read", false)
                .put(DELETED, false);
        mongo.upsert(notification, DBCollections.NOTIFICATION, updtRes -> {
            if (updtRes.succeeded()) {
                mongo.getById(id, DBCollections.USER, u -> {
                    if (u.succeeded()) {
                        if (u.result() != null && u.result().containsKey(ACCOUNT) && u.result().getJsonObject(ACCOUNT).containsKey(FIELD_DEVICES)) {
                            // Send firebase notification
                            u.result().getJsonObject(ACCOUNT).getJsonArray(FIELD_DEVICES).forEach(d -> {
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
                        resultHandler.handle(Future.succeededFuture(true));
                    } else {
                        resultHandler.handle(Future.failedFuture(u.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(updtRes.cause()));
            }
        });
    }

    @Override
    public void markAsRead(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.NOTIFICATION, res -> {
            if (res.succeeded()) {
                JsonObject n = res.result();
                n.put("read", !n.getBoolean("read"));
                updateNotification(n, resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void delete(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.NOTIFICATION, res -> {
            if (res.succeeded()) {
                JsonObject n = res.result();
                n.put(DELETED, true);
                updateNotification(n, resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    private void updateNotification(JsonObject n, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(n, DBCollections.NOTIFICATION, updtRes -> {
            if (updtRes.succeeded()) {
                vertx.eventBus().send(WS_NOTIFICATION_PREFIX + n.getString(TARGET_ID), new JsonObject());
                resultHandler.handle(Future.succeededFuture(n));
            } else {
                resultHandler.handle(Future.failedFuture(updtRes.cause()));
            }
        });
    }

    @Override
    public void getList(String id, int start, int limit, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject criterias = new JsonObject().put(TARGET_ID, id).put(DELETED, false);
        mongo.findByCriterias(criterias, new ArrayList<>(), "timestamp", -1, -1, DBCollections.NOTIFICATION, res -> {
            if (res.succeeded()) {
                JsonArray notifications = res.result();
                JsonArray jnotif = new JsonArray();
                if (notifications.size() == 0) {
                    resultHandler.handle(Future.succeededFuture(jnotif));
                } else {
                    int myLimit = limit;
                    if (myLimit == -1) {
                        myLimit = notifications.size();
                    }
                    List<Future> promises = new ArrayList<>();
                    for (int i = start; i < start + myLimit; i++) {
                        promises.add(getUser(i, notifications.getJsonObject(i).getString(SENDER_ID)));
                    }
                    CompositeFuture.all(promises).setHandler(rs -> {
                        if (rs.succeeded()) {
                            rs.result().list().forEach(u -> {
                                notifications.getJsonObject(((JsonObject) u).getInteger(FIELD_INDEX)).put(SENDER_ID, ((JsonObject) u).getJsonObject("user"));
                                jnotif.add(notifications.getJsonObject(((JsonObject) u).getInteger(FIELD_INDEX)));
                            });
                            resultHandler.handle(Future.succeededFuture(jnotif));
                        } else {
                            resultHandler.handle(Future.failedFuture(rs.cause()));
                        }
                    });
                }
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    private Future<JsonObject> getUser(int i, String id) {
        Future<JsonObject> deferred = Future.future();
        mongo.getById(id, DBCollections.USER, u -> {
            if (u.succeeded()) {
                JsonObject cu = new JsonObject();
                u.result().fieldNames().stream()
                        .filter(Arrays.asList("_id", "name", "firstname", "avatar")::contains)
                        .forEachOrdered(f -> cu.put(f, u.result().getValue(f)));
                deferred.complete(new JsonObject().put(FIELD_INDEX, i).put("user", cu));
            } else {
                deferred.fail(u.cause());
            }
        });
        return deferred;
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
}
