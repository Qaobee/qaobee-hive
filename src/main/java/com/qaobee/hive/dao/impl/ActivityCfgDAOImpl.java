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
import com.qaobee.hive.api.v1.commons.settings.ActivityCfgVerticle;
import com.qaobee.hive.dao.ActivityCfgDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * The type Activity cfg dao.
 */
public class ActivityCfgDAOImpl implements ActivityCfgDAO {
    private static final String COLLECTION = "ActivityCfg";

    @Inject
    private MongoDB mongo;

    @Override
    public JsonArray getActivityCfgParams(String activityId, String countryId, Long dateRef, String paramField) throws QaobeeException {
        DBObject match;
        DBObject project;
        BasicDBObject dbObjectParent;
        // $MATCH section
        dbObjectParent = new BasicDBObject();
        // - activityId
        dbObjectParent.put(ActivityCfgVerticle.PARAM_ACTIVITY_ID, activityId);
        // - countryId
        dbObjectParent.put(ActivityCfgVerticle.PARAM_COUNTRY_ID, countryId);
        // - date between start and end dates
        dbObjectParent.put("startDate", new BasicDBObject("$lte", dateRef));
        dbObjectParent.put("endDate", new BasicDBObject("$gte", dateRef));
        match = new BasicDBObject("$match", dbObjectParent);
        // $PROJECT section
        dbObjectParent = new BasicDBObject();
        dbObjectParent.put("_id", 0);
        dbObjectParent.put(paramField, 1);
        project = new BasicDBObject("$project", dbObjectParent);
        List<DBObject> pipelineAggregation = Arrays.asList(match, project);
        final JsonArray resultJSon = mongo.aggregate(paramField, pipelineAggregation, COLLECTION);
        if (resultJSon.size() != 1 || !((JsonObject) resultJSon.get(0)).containsField(paramField)) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "Field to retrieve is unknown : '" + paramField + "' (" + activityId + "/" + countryId + "/" + dateRef + ")");
        }
        return resultJSon;
    }

    @Override
    public JsonObject getActivityCfg(String activityId, String countryId, Long dateRef) throws QaobeeException {
        // Creation of request
        CriteriaBuilder criterias = new CriteriaBuilder();
        criterias.add(ActivityCfgVerticle.PARAM_ACTIVITY_ID, activityId);
        criterias.add(ActivityCfgVerticle.PARAM_COUNTRY_ID, countryId);
        criterias.between("startDate", "endDate", dateRef);
        // Call to mongo
        JsonArray resultJSon = mongo.findByCriterias(criterias.get(), null, null, -1, -1, COLLECTION);
        if (resultJSon.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No activity configuration was found for (" + activityId + " / " + countryId + " / " + dateRef + ")");
        }
        return resultJSon.get(0);
    }
}
