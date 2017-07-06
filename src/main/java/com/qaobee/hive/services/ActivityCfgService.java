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

import com.qaobee.hive.services.impl.ActivityCfgServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Activity cfg service.
 */
@ProxyGen
@VertxGen
public interface ActivityCfgService {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.ActivityCfgService.service";

    /**
     * Create activity cfg service.
     *
     * @param vertx the vertx
     * @return the activity cfg service
     */
    static ActivityCfgService create(Vertx vertx) {
        return new ActivityCfgServiceImpl(vertx);
    }

    /**
     * Create proxy activity cfg service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the activity cfg service
     */
    static ActivityCfgService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(ActivityCfgService.class, vertx, address);
    }

    /**
     * Gets activity cfg params.
     *
     * @param activityId    the activity id
     * @param countryId     the country id
     * @param dateRef       the date ref
     * @param paramField    the param field
     * @param resultHandler the result handler
     */
    void getActivityCfgParams(String activityId, String countryId, Long dateRef, String paramField, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets activity cfg.
     *
     * @param activityId    the activity id
     * @param countryId     the country id
     * @param dateRef       the date ref
     * @param resultHandler the result handler
     */
    void getActivityCfg(String activityId, String countryId, Long dateRef, Handler<AsyncResult<JsonObject>> resultHandler);
}
