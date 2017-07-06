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

import com.qaobee.hive.dao.IndicatorDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

import javax.inject.Inject;

/**
 * The type Indicator dao.
 */
public class IndicatorDAOImpl implements IndicatorDAO {
    private static final String PARAM_ACTIVITY_ID = "activityId";
    private static final String PARAM_COUNTRY_ID = "countryId";
    @Inject
    private MongoDB mongo;

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getIndicatorByCode(String activityId, String countryId, JsonArray listIndicators) {
        // $MATCH section
        JsonObject dbObjectParent = new JsonObject();
        // - activity code
        dbObjectParent.put(PARAM_ACTIVITY_ID, activityId);
        // - country
        dbObjectParent.put(PARAM_COUNTRY_ID, countryId);
        // - code
        JsonObject dbObjectChild = new JsonObject().put("$in", listIndicators);
        dbObjectParent.put("code", dbObjectChild);
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match);
        return mongo.aggregate(pipelineAggregation, DBCollections.INDICATOR_CFG);
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getIndicatorsList(String activityId, String countryId, JsonArray screen) {
        // $MATCH section
        JsonObject dbObjectParent = new JsonObject();
        // - activity code
        dbObjectParent.put(PARAM_ACTIVITY_ID, activityId);
        // - country
        dbObjectParent.put(PARAM_COUNTRY_ID, countryId);
        // - SCREEN
        JsonObject dbObjectChild = new JsonObject().put("$in", screen);
        dbObjectParent.put("listScreen", dbObjectChild);
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        // $PROJECT section
        dbObjectParent = new JsonObject();
        dbObjectParent.put("_id", 1);
        dbObjectParent.put("code", 1);
        dbObjectParent.put(PARAM_ACTIVITY_ID, 1);
        dbObjectParent.put("indicatorType", 1);
        dbObjectParent.put("listScreen", 1);
        dbObjectParent.put("listField", 1);
        dbObjectParent.put("listValues", 1);
        JsonObject project = new JsonObject().put("$project", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match).add(project);
        return mongo.aggregate(pipelineAggregation, DBCollections.INDICATOR_CFG);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getIndicator(String id) {
        return mongo.getById(id, DBCollections.INDICATOR_CFG);
    }
}
