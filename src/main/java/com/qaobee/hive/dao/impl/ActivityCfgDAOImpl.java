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

import com.qaobee.hive.api.v1.commons.settings.ActivityCfgVerticle;
import com.qaobee.hive.dao.ActivityCfgDAO;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import javax.inject.Inject;


/**
 * The type Activity cfg dao.
 */
@ProxyService(address = ActivityCfgDAO.ADDRESS, iface = ActivityCfgDAO.class)
public class ActivityCfgDAOImpl implements ActivityCfgDAO {

    @Inject
    private MongoClient mongoClient;
    @Inject
    private MongoDB mongoDB;

    public ActivityCfgDAOImpl(Vertx vertx) {
        super();
    }

    @Override
    public void getActivityCfgParams(String activityId, String countryId, Long dateRef, String paramField, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject match;
        JsonObject project;
        JsonObject dbObjectParent;
        // $MATCH section
        dbObjectParent = new JsonObject();
        // - activityId
        dbObjectParent.put(ActivityCfgVerticle.PARAM_ACTIVITY_ID, activityId);
        // - countryId
        dbObjectParent.put(ActivityCfgVerticle.PARAM_COUNTRY_ID, countryId);
        // - date between start and end dates
        dbObjectParent.put("startDate", new JsonObject().put("$lte", dateRef));
        dbObjectParent.put("endDate", new JsonObject().put("$gte", dateRef));
        match = new JsonObject().put("$match", dbObjectParent);
        // $PROJECT section
        dbObjectParent = new JsonObject();
        dbObjectParent.put("_id", 0);
        dbObjectParent.put(paramField, 1);
        project = new JsonObject().put("$project", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match).add(project);
        JsonObject command = new JsonObject()
                .put("aggregate", DBCollections.ACTIVITY_CFG)
                .put("pipeline", pipelineAggregation);
        mongoClient.runCommand("aggregate", command, res -> {
            if (res.succeeded()) {
                JsonArray resultJSon = res.result().getJsonArray("result");
                if (resultJSon.size() != 1 || !resultJSon.getJsonObject(0).containsKey(paramField)) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, "Field to retrieve is unknown : '" + paramField + "' (" + activityId + "/" + countryId + "/" + dateRef + ")")));
                } else {
                    resultHandler.handle(Future.succeededFuture(resultJSon));
                }
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause())));
                res.cause().printStackTrace();
            }
        });
    }

    @Override
    public void getActivityCfg(String activityId, String countryId, Long dateRef, Handler<AsyncResult<JsonObject>> resultHandler) {
        // Creation of request
        CriteriaBuilder criterias = new CriteriaBuilder();
        criterias.add(ActivityCfgVerticle.PARAM_ACTIVITY_ID, activityId);
        criterias.add(ActivityCfgVerticle.PARAM_COUNTRY_ID, countryId);
        criterias.between("startDate", "endDate", dateRef);
        // Call to mongo
        mongoDB.findByCriterias(criterias.get(), null, null, -1, -1, DBCollections.ACTIVITY_CFG, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(res.result().getJsonObject(0)));
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, "No activity configuration was found for (" + activityId + " / " + countryId + " / " + dateRef + ")")));
            }
        });
    }
}
