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

import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.services.SeasonService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Season service.
 */
@ProxyService(address = "vertx.Season.service", iface = SeasonService.class)
public class SeasonServiceImpl implements SeasonService {
    private static final String PARAM_ACTIVITY_ID = "activityId";
    private static final String PARAM_COUNTRY_ID = "countryId";
    private static final String END_DATE_FIELD = "endDate";
    private static final String NO_SEASON_MESS = "No season defined for (%s / %s)";

    @Inject
    private MongoDB mongo;

    /**
     * Instantiates a new Season.
     *
     * @param vertx the vertx
     */
    public SeasonServiceImpl(Vertx vertx) { // NOSONAR
        super();
    }

    @Override
    public void getCurrentSeason(String activityId, String countryId, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject criterias = new JsonObject()
                .put(PARAM_ACTIVITY_ID, activityId)
                .put(PARAM_COUNTRY_ID, countryId);
        mongo.findByCriterias(criterias, new CriteriaOption().withSort(END_DATE_FIELD).withOrder(-1), DBCollections.SEASON, resultJson -> {
            if (resultJson.succeeded()) {
                long currentDate = System.currentTimeMillis();
                if (resultJson.result().size() == 0) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, String.format(NO_SEASON_MESS, activityId, countryId))));
                } else {
                    JsonObject season = null;
                    for (int i = 0; i < resultJson.result().size(); i++) {
                        JsonObject s = resultJson.result().getJsonObject(i);
                        if (s.getLong(END_DATE_FIELD, 0L) > currentDate && s.getLong("startDate") < currentDate) {
                            season = s;
                        }
                    }
                    if (season != null) {
                        resultHandler.handle(Future.succeededFuture(season));
                    } else {
                        resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, String.format(NO_SEASON_MESS, activityId, countryId))));
                    }
                }
            } else {
                resultHandler.handle(Future.failedFuture(resultJson.cause()));
            }
        });
    }

    @Override
    public void getListByActivity(String activityId, String countryId, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject criterias = new JsonObject()
                .put(PARAM_ACTIVITY_ID, activityId)
                .put(PARAM_COUNTRY_ID, countryId);
        mongo.findByCriterias(criterias, new CriteriaOption().withSort(END_DATE_FIELD).withOrder(-1), DBCollections.SEASON, resultJson -> {
            if (resultJson.succeeded()) {
                if (resultJson.result().size() == 0) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, String.format(NO_SEASON_MESS, activityId, countryId))));
                } else {
                    resultHandler.handle(Future.succeededFuture(resultJson.result()));
                }
            } else {
                resultHandler.handle(Future.failedFuture(resultJson.cause()));
            }
        });
    }

    @Override
    public void getSeason(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.SEASON, resultHandler);
    }
}
