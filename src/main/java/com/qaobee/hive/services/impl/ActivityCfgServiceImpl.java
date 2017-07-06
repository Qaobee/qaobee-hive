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

import com.qaobee.hive.api.v1.commons.settings.ActivityCfgRoute;
import com.qaobee.hive.services.ActivityCfgService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeSvcException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;


@ProxyService(address = ActivityCfgService.ADDRESS, iface = ActivityCfgService.class)
public class ActivityCfgServiceImpl implements ActivityCfgService {

    @Inject
    private MongoClientCustom mongoClient;
    @Inject
    private MongoDB mongoDB;

    private Vertx vertx;

    public ActivityCfgServiceImpl(Vertx vertx) {
        super();
        this.vertx = vertx;
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
    @Override
    public void getActivityCfgParams(String activityId, String countryId, Long dateRef, String paramField, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject dbObjectParent = new JsonObject()
                .put(ActivityCfgRoute.PARAM_ACTIVITY_ID, activityId)
                .put(ActivityCfgRoute.PARAM_COUNTRY_ID, countryId)
                .put("startDate", new JsonObject().put("$lte", dateRef))
                .put("endDate", new JsonObject().put("$gte", dateRef));
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        // $PROJECT section
        dbObjectParent = new JsonObject()
                .put("_id", 0)
                .put(paramField, 1);
        JsonObject project = new JsonObject().put("$project", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match).add(project);
        JsonObject command = new JsonObject()
                .put("aggregate", DBCollections.ACTIVITY_CFG)
                .put("pipeline", pipelineAggregation);
        mongoClient.runCommand("aggregate", command, res -> {
            if (res.succeeded()) {
                JsonArray resultJSon = res.result().getJsonArray("result");
                if (resultJSon.size() != 1 || !resultJSon.getJsonObject(0).containsKey(paramField)) {
                    resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.DATA_ERROR, "Field to retrieve is unknown : '" + paramField + "' (" + activityId + "/" + countryId + "/" + dateRef + ")")));
                } else {
                    resultHandler.handle(Future.succeededFuture(resultJSon));
                }
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.DATA_ERROR, res.cause())));
            }
        });
    }

    /**
     * Gets activity cfg.
     *
     * @param activityId    the activity id
     * @param countryId     the country id
     * @param dateRef       the date ref
     * @param resultHandler the result handler
     */
    @Override
    public void getActivityCfg(String activityId, String countryId, Long dateRef, Handler<AsyncResult<JsonObject>> resultHandler) {
        CriteriaBuilder criterias = new CriteriaBuilder()
                .add(ActivityCfgRoute.PARAM_ACTIVITY_ID, activityId)
                .add(ActivityCfgRoute.PARAM_COUNTRY_ID, countryId)
                .between("startDate", "endDate", dateRef);
        // Call to mongo
        mongoDB.findByCriterias(criterias.get(), null, null, -1, -1, DBCollections.ACTIVITY_CFG)
                .done(res -> {
                    if(res.size()>0) {
                        resultHandler.handle(Future.succeededFuture(res.getJsonObject(0)));
                    } else {
                        resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.DATA_ERROR, "no data found")));
                    }
                })
                .fail(e-> resultHandler.handle(Future.failedFuture(e)));
    }
}
