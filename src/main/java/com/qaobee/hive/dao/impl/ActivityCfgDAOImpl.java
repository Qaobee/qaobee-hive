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
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;


/**
 * The type Activity cfg dao.
 */
public class ActivityCfgDAOImpl implements ActivityCfgDAO {

    @Inject
    private MongoClientCustom mongoClient;
    @Inject
    private MongoDB mongoDB;

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getActivityCfgParams(String activityId, String countryId, Long dateRef, String paramField) {
        Deferred<JsonArray, QaobeeException, Integer> deferred = new DeferredObject<>();
        // $MATCH section
        JsonObject dbObjectParent = new JsonObject();
        // - activityId
        dbObjectParent.put(ActivityCfgVerticle.PARAM_ACTIVITY_ID, activityId);
        // - countryId
        dbObjectParent.put(ActivityCfgVerticle.PARAM_COUNTRY_ID, countryId);
        // - date between start and end dates
        dbObjectParent.put("startDate", new JsonObject().put("$lte", dateRef));
        dbObjectParent.put("endDate", new JsonObject().put("$gte", dateRef));
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        // $PROJECT section
        dbObjectParent = new JsonObject();
        dbObjectParent.put("_id", 0);
        dbObjectParent.put(paramField, 1);
        JsonObject project = new JsonObject().put("$project", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match).add(project);
        JsonObject command = new JsonObject()
                .put("aggregate", DBCollections.ACTIVITY_CFG)
                .put("pipeline", pipelineAggregation);
        mongoClient.runCommand("aggregate", command, res -> {
            if (res.succeeded()) {
                JsonArray resultJSon = res.result().getJsonArray("result");
                if (resultJSon.size() != 1 || !resultJSon.getJsonObject(0).containsKey(paramField)) {
                    deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "Field to retrieve is unknown : '" + paramField + "' (" + activityId + "/" + countryId + "/" + dateRef + ")"));
                } else {
                    deferred.resolve(resultJSon);
                }
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause()));
            }
        });
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getActivityCfg(String activityId, String countryId, Long dateRef) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        // Creation of request
        CriteriaBuilder criterias = new CriteriaBuilder()
                .add(ActivityCfgVerticle.PARAM_ACTIVITY_ID, activityId)
                .add(ActivityCfgVerticle.PARAM_COUNTRY_ID, countryId)
                .between("startDate", "endDate", dateRef);
        // Call to mongo
        mongoDB.findByCriterias(criterias.get(), null, null, -1, -1, DBCollections.ACTIVITY_CFG)
                .done(res -> deferred.resolve(res.getJsonObject(0)))
                .fail(deferred::reject);
        return deferred.promise();
    }
}
