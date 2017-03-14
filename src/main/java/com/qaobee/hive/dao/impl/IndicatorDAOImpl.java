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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.dao.IndicatorDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The type Indicator dao.
 */
public class IndicatorDAOImpl implements IndicatorDAO {
    private static final String PARAM_ACTIVITY_ID = "activityId";
    private static final String PARAM_COUNTRY_ID = "countryId";
    private final MongoDB mongo;

    /**
     * Instantiates a new Indicator dao.
     *
     * @param mongo the mongo
     */
    @Inject
    public IndicatorDAOImpl(MongoDB mongo) {
        this.mongo = mongo;
    }

    @Override
    public JsonArray getIndicatorByCode(String activityId, String countryId, JsonArray listIndicators) throws QaobeeException {
        DBObject match;
        BasicDBObject dbObjectParent;
        BasicDBObject dbObjectChild;
        // $MATCH section
        dbObjectParent = new BasicDBObject();
        // - activity code
        dbObjectParent.put(PARAM_ACTIVITY_ID, activityId);
        // - country
        dbObjectParent.put(PARAM_COUNTRY_ID, countryId);
        // - code
        dbObjectChild = new BasicDBObject("$in", listIndicators.toArray());
        dbObjectParent.put("code", dbObjectChild);
        match = new BasicDBObject("$match", dbObjectParent);
        List<DBObject> pipelineAggregation = Collections.singletonList(match);
        return mongo.aggregate("_id", pipelineAggregation, DBCollections.INDICATOR_CFG);
    }

    @Override
    public JsonArray getIndicatorsList(String activityId, String countryId, JsonArray screen) throws QaobeeException {
        DBObject match;
        DBObject project;
        BasicDBObject dbObjectParent;
        BasicDBObject dbObjectChild;
        // $MATCH section
        dbObjectParent = new BasicDBObject();
        // - activity code
        dbObjectParent.put(PARAM_ACTIVITY_ID, activityId);
        // - country
        dbObjectParent.put(PARAM_COUNTRY_ID, countryId);
        // - SCREEN
        dbObjectChild = new BasicDBObject("$in", screen.toArray());
        dbObjectParent.put("listScreen", dbObjectChild);
        match = new BasicDBObject("$match", dbObjectParent);
        // $PROJECT section
        dbObjectParent = new BasicDBObject();
        dbObjectParent.put("_id", 1);
        dbObjectParent.put("code", 1);
        dbObjectParent.put(PARAM_ACTIVITY_ID, 1);
        dbObjectParent.put("indicatorType", 1);
        dbObjectParent.put("listScreen", 1);
        dbObjectParent.put("listField", 1);
        dbObjectParent.put("listValues", 1);
        project = new BasicDBObject("$project", dbObjectParent);
        List<DBObject> pipelineAggregation = Arrays.asList(match, project);
       return mongo.aggregate("_id", pipelineAggregation, DBCollections.INDICATOR_CFG);
    }

    @Override
    public JsonObject getIndicator(String id) throws QaobeeException {
        return mongo.getById(id, DBCollections.INDICATOR_CFG);
    }
}
