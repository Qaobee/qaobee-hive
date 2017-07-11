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

import com.qaobee.hive.services.Season;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeSvcException;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Season.
 */
@ProxyService(address = Season.ADDRESS, iface = Season.class)
public class SeasonImpl implements Season {
    private static final String PARAM_ACTIVITY_ID = "activityId";
    private static final String PARAM_COUNTRY_ID = "countryId";
    private static final String END_DATE_FIELD = "endDate";
    private final Vertx vertx;
    @Inject
    private MongoDB mongo;

    /**
     * Instantiates a new Season.
     *
     * @param vertx the vertx
     */
    public SeasonImpl(Vertx vertx) {
        super();
        this.vertx = vertx;
    }

    @Override
    public void getCurrentSeason(String activityId, String countryId, Handler<AsyncResult<JsonObject>> resultHandler) {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(PARAM_ACTIVITY_ID, activityId);
        criterias.put(PARAM_COUNTRY_ID, countryId);
        mongo.findByCriterias(criterias, null, END_DATE_FIELD, -1, -1, DBCollections.SEASON)
                .done(resultJson -> {
                    long currentDate = System.currentTimeMillis();
                    if (resultJson.size() == 0) {
                        resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.DATA_ERROR, "No season defined for (" + activityId + " / " + countryId + ")")));
                    } else {
                        JsonObject season = null;
                        for (int i = 0; i < resultJson.size(); i++) {
                            JsonObject s = resultJson.getJsonObject(i);
                            if (s.getLong(END_DATE_FIELD, 0L) > currentDate && s.getLong("startDate") < currentDate) {
                                season = s;
                            }
                        }
                        if (season != null) {
                            resultHandler.handle(Future.succeededFuture(season));
                        } else {
                            resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.DATA_ERROR, "No season defined for (" + activityId + " / " + countryId + ")")));
                        }
                    }
                }).fail(e -> resultHandler.handle(Future.failedFuture(new QaobeeSvcException(e))));
    }

    @Override
    public void getListByActivity(String activityId, String countryId, Handler<AsyncResult<JsonArray>> resultHandler) {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(PARAM_ACTIVITY_ID, activityId);
        criterias.put(PARAM_COUNTRY_ID, countryId);
        mongo.findByCriterias(criterias, null, END_DATE_FIELD, -1, -1, DBCollections.SEASON)
                .done(resultJson -> {
                    if (resultJson.size() == 0) {
                        resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.DATA_ERROR, "No season defined for (" + activityId + " / " + countryId + ")")));
                    } else {
                        resultHandler.handle(Future.succeededFuture(resultJson));
                    }
                }).fail(e -> resultHandler.handle(Future.failedFuture(new QaobeeSvcException(e))));
    }

    @Override
    public void getSeason(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.SEASON)
                .done(res -> resultHandler.handle(Future.succeededFuture(res)))
                .fail(e -> resultHandler.handle(Future.failedFuture(new QaobeeSvcException(e))));
    }
}
