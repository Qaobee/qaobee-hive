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

import com.qaobee.hive.services.impl.ActivityServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Activity dao.
 */
@ProxyGen
@VertxGen
public interface ActivityService {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.Activity.service";

    /**
     * Create activity service.
     *
     * @param vertx the vertx
     * @return the activity service
     */
    static ActivityService create(Vertx vertx) {
        return new ActivityServiceImpl(vertx);
    }

    /**
     * Create proxy activity service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the activity service
     */
    static ActivityService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(ActivityService.class, vertx, address);
    }

    /**
     * Gets enabled.
     *
     * @param resultHandler the result handler
     */
    void getEnabled(Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets activity list.
     *
     * @param resultHandler the result handler
     */
    void getActivityList(Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets activity.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getActivity(String id, Handler<AsyncResult<JsonObject>> resultHandler);
}
