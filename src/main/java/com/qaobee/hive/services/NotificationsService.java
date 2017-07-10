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

package com.qaobee.hive.services;

import com.qaobee.hive.services.impl.NotificationsServiceImpl;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;
import org.jdeferred.Promise;

/**
 * The interface Notifications service.
 */
@ProxyGen
@VertxGen
public interface NotificationsService {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.NotificationsService.service";

    /**
     * Create notifications service.
     *
     * @param vertx the vertx
     * @return the notifications service
     */
    static NotificationsService create(Vertx vertx) {
        return new NotificationsServiceImpl(vertx);
    }

    /**
     * Create proxy notifications service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the notifications service
     */
    static NotificationsService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(NotificationsService.class, vertx, address);
    }

    /**
     * Notify.
     *
     * @param id            the id
     * @param collection    the collection
     * @param notification  the notification
     * @param exclude       the exclude
     * @param resultHandler the result handler
     */
    void notify(String id, String collection, JsonObject notification, JsonArray exclude, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Add notification to user.
     *
     * @param id            the id
     * @param notification  the notification
     * @param resultHandler the result handler
     */
    void addNotificationToUser(String id, JsonObject notification, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Mark as read.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void markAsRead(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Delete.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void delete(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list.
     *
     * @param id            the id
     * @param start         the start
     * @param limit         the limit
     * @param resultHandler the result handler
     */
    void getList(String id, int start, int limit, Handler<AsyncResult<JsonArray>> resultHandler);
}
