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

import com.qaobee.hive.dao.SeasonDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Season dao.
 */
public class SeasonDAOImpl implements SeasonDAO {
    private static final String PARAM_ACTIVITY_ID = "activityId";
    private static final String PARAM_COUNTRY_ID = "countryId";
    private static final String END_DATE_FIELD = "endDate";
    @Inject
    private MongoDB mongo;

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getCurrentSeason(String activityId, String countryId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        // Creation of the request
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(PARAM_ACTIVITY_ID, activityId);
        criterias.put(PARAM_COUNTRY_ID, countryId);
        mongo.findByCriterias(criterias, null, END_DATE_FIELD, -1, -1, DBCollections.SEASON)
                .done(resultJson -> {
                    long currentDate = System.currentTimeMillis();
                    if (resultJson.size() == 0) {
                        deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "No season defined for (" + activityId + " / " + countryId + ")"));
                    }
                    for (int i = 0; i < resultJson.size(); i++) {
                        JsonObject s = resultJson.getJsonObject(i);
                        if (s.getLong(END_DATE_FIELD, 0L) > currentDate && s.getLong("startDate") < currentDate) {
                            deferred.resolve(s);
                            return;
                        }
                    }
                    deferred.resolve(new JsonObject());
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getListByActivity(String activityId, String countryId) {
        Deferred<JsonArray, QaobeeException, Integer> deferred = new DeferredObject<>();
        // Creation of the request
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(PARAM_ACTIVITY_ID, activityId);
        criterias.put(PARAM_COUNTRY_ID, countryId);
        mongo.findByCriterias(criterias, null, END_DATE_FIELD, -1, -1, DBCollections.SEASON)
                .done(resultJson -> {
                    if (resultJson.size() == 0) {
                        deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "No season defined for (" + activityId + " / " + countryId + ")"));
                    } else {
                        deferred.resolve(resultJson);
                    }
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getSeason(String id) {
        return mongo.getById(id, DBCollections.SEASON);
    }
}
