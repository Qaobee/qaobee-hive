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
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

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
    private static final String COLLECTION = "Season";
    @Inject
    private MongoDB mongo;

    @Override
    public JsonObject getCurrentSeason(String activityId, String countryId) throws QaobeeException {
        // Creation of the request
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(PARAM_ACTIVITY_ID, activityId);
        criterias.put(PARAM_COUNTRY_ID, countryId);
        JsonArray resultJson = mongo.findByCriterias(criterias, null, END_DATE_FIELD, -1, -1, COLLECTION);
        long currentDate = System.currentTimeMillis();
        if (resultJson == null || resultJson.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No season defined for (" + activityId + " / " + countryId + ")");
        }
        for (int i = 0; i < resultJson.size(); i++) {
            JsonObject s = resultJson.get(i);
            if (s.getLong(END_DATE_FIELD, 0) > currentDate && s.getLong("startDate") < currentDate) {
                return s;
            }
        }
        return new JsonObject();
    }

    @Override
    public JsonArray getListByActivity(String activityId, String countryId) throws QaobeeException {
        // Creation of the request
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(PARAM_ACTIVITY_ID, activityId);
        criterias.put(PARAM_COUNTRY_ID, countryId);
        JsonArray resultJson = mongo.findByCriterias(criterias, null, END_DATE_FIELD, -1, -1, COLLECTION);
        if (resultJson == null || resultJson.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No season defined for (" + activityId + " / " + countryId + ")");
        }
        return resultJson;
    }

    @Override
    public JsonObject getSeason(String id) throws QaobeeException {
        return mongo.getById(id, COLLECTION);
    }
}
