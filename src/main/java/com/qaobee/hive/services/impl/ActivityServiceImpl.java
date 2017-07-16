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

import com.qaobee.hive.services.ActivityService;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Activity service.
 */
@ProxyService(address = ActivityService.ADDRESS, iface = ActivityService.class)
public class ActivityServiceImpl implements ActivityService {

    @Inject
    private MongoDB mongo;

    /**
     * Instantiates a new Activity service.
     *
     * @param vertx the vertx
     */
    public ActivityServiceImpl(Vertx vertx) {
        super();
    }

    @Override
    public void getEnabled(Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject criterias = new JsonObject().put("enable", true);
        mongo.findByCriterias(criterias, new CriteriaOption(), DBCollections.ACTIVITY, resultHandler);
    }

    @Override
    public void getActivityList(Handler<AsyncResult<JsonArray>> resultHandler) {
        mongo.findByCriterias(new JsonObject(), new CriteriaOption(), DBCollections.ACTIVITY, resultHandler);
    }

    @Override
    public void getActivity(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.ACTIVITY, resultHandler);
    }
}
