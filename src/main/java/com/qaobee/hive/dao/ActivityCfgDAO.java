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

package com.qaobee.hive.dao;

import com.qaobee.hive.dao.impl.ActivityCfgDAOImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Activity cfg dao.
 */
@ProxyGen
@VertxGen
public interface ActivityCfgDAO {
    String ADDRESS = "ActivityCfgDAO";

    static ActivityCfgDAO create(Vertx vertx) {
        return new ActivityCfgDAOImpl(vertx);
    }

    static ActivityCfgDAO createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(ActivityCfgDAO.class, vertx, address);
    }

    /**
     * Gets activity cfg params.
     *
     * @param activityId the activity id
     * @param countryId  the country id
     * @param dateRef    the date ref
     * @param paramField the param field
     */
    void getActivityCfgParams(String activityId, String countryId, Long dateRef, String paramField, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets activity cfg.
     *
     * @param activityId the activity id
     * @param countryId  the country id
     * @param dateRef    the date ref
     */
    void getActivityCfg(String activityId, String countryId, Long dateRef, Handler<AsyncResult<JsonObject>> resultHandler);
}
