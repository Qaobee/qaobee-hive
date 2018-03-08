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

import com.qaobee.hive.services.IndicatorService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.services.MongoDB;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Indicator.
 */
@ProxyService(address = "vertx.Indicator.service", iface = IndicatorService.class)
public class IndicatorServiceImpl implements IndicatorService {
    private static final String PARAM_ACTIVITY_ID = "activityId";
    private static final String PARAM_COUNTRY_ID = "countryId";
    @Inject
    private MongoDB mongo;

    /**
     * Instantiates a new Indicator.
     *
     * @param vertx the vertx
     */
    public IndicatorServiceImpl(Vertx vertx) { // NOSONAR
        super();
    }

    @Override
    public void getIndicatorByCode(String activityId, String countryId, JsonArray listIndicators, Handler<AsyncResult<JsonArray>> resultHandler) {
        final JsonObject[] dbObjectParent = {new JsonObject()
                .put(PARAM_ACTIVITY_ID, activityId)
                .put(PARAM_COUNTRY_ID, countryId)
                .put("code", new JsonObject().put("$in", listIndicators))};
        final JsonObject[] match = {new JsonObject().put("$match", dbObjectParent[0])};
        final JsonArray[] pipelineAggregation = {new JsonArray().add(match[0])};
        mongo.aggregate(pipelineAggregation[0], DBCollections.INDICATOR_CFG, r -> {
            if (r.succeeded() && r.result().size() > 0) {
                resultHandler.handle(Future.succeededFuture(r.result()));
            } else {
                dbObjectParent[0].put(PARAM_COUNTRY_ID, "CNTR-250-FR-FRA");
                match[0] = new JsonObject().put("$match", dbObjectParent[0]);
                pipelineAggregation[0] = new JsonArray().add(match[0]);
                mongo.aggregate(pipelineAggregation[0], DBCollections.INDICATOR_CFG, resultHandler);
            }
        });
    }

    @Override
    public void getIndicatorsList(String activityId, String countryId, JsonArray screen, Handler<AsyncResult<JsonArray>> resultHandler) {
        // $MATCH section
        final JsonObject[] match = {new JsonObject().put("$match", new JsonObject()
                .put(PARAM_ACTIVITY_ID, activityId)
                .put(PARAM_COUNTRY_ID, countryId)
                .put("listScreen", new JsonObject().put("$in", screen)))};
        // $PROJECT section
        final JsonObject[] dbObjectParent = {new JsonObject()
                .put("_id", 1)
                .put("code", 1)
                .put(PARAM_ACTIVITY_ID, 1)
                .put("indicatorType", 1)
                .put("listScreen", 1)
                .put("listField", 1)
                .put("listValues", 1)};
        JsonObject project = new JsonObject().put("$project", dbObjectParent[0]);
        final JsonArray[] pipelineAggregation = {new JsonArray().add(match[0]).add(project)};
        mongo.aggregate(pipelineAggregation[0], DBCollections.INDICATOR_CFG, r -> {
            if (r.succeeded() && r.result().size() > 0) {
                resultHandler.handle(Future.succeededFuture(r.result()));
            } else {
                match[0].getJsonObject("$match").put(PARAM_COUNTRY_ID, "CNTR-250-FR-FRA");
                pipelineAggregation[0] = new JsonArray().add(match[0]).add(project);
                mongo.aggregate(pipelineAggregation[0], DBCollections.INDICATOR_CFG, resultHandler);
            }
        });
    }

    @Override
    public void getIndicator(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.INDICATOR_CFG, resultHandler);
    }
}
