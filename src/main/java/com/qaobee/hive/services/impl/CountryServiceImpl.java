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

import com.qaobee.hive.api.v1.commons.settings.CountryRoute;
import com.qaobee.hive.services.CountryService;
import com.qaobee.hive.services.MongoDB;
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
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

/**
 * The type Country dao.
 */
@ProxyService(address = CountryService.ADDRESS, iface = CountryService.class)
public class CountryServiceImpl implements CountryService {
    private final JsonObject mapCountry = new JsonObject();
    @Inject
    private MongoDB mongo;

    public CountryServiceImpl(Vertx vertx) {
        super();
    }

    @Override
    public void getCountryFromAlpha2(String alpha2, Handler<AsyncResult<JsonObject>> resultHandler) {
        getCountries(map -> {
            if (map.succeeded()) {
                if (map.result().containsKey(alpha2.toUpperCase())) {
                    resultHandler.handle(Future.succeededFuture(map.result().getJsonObject(alpha2.toUpperCase())));
                } else {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, "no data found")));
                }
            } else {
                resultHandler.handle(Future.failedFuture(map.cause()));
            }
        });
    }

    @Override
    public void getCountryList(String locale, String label, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject criterias = new JsonObject().put(CountryRoute.PARAM_LOCAL, locale);
        // label
        if (StringUtils.isNotBlank(label)) {
            criterias.put(CountryRoute.PARAM_LABEL, label);
        }
        mongo.findByCriterias(criterias, new CriteriaOption(), DBCollections.COUNTRY, resultHandler);
    }

    @Override
    public void getCountry(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.COUNTRY, res -> {
            if (res.succeeded()) {
                if (res.result().size() > 0) {
                    resultHandler.handle(Future.succeededFuture(res.result()));
                } else {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, "no data found")));
                }
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    private void getCountries(Handler<AsyncResult<JsonObject>> resultHandler) {
        if (mapCountry.fieldNames().isEmpty()) {
            mongo.findAll(new CriteriaOption(), DBCollections.COUNTRY, resultJson -> {
                if (resultJson.succeeded()) {
                    resultJson.result().forEach(c -> mapCountry.put(((JsonObject) c).getString("_id").split("-")[2], (JsonObject) c));
                    resultHandler.handle(Future.succeededFuture(mapCountry));
                } else {
                    resultHandler.handle(Future.failedFuture(resultJson.cause()));
                }
            });
        } else {
            resultHandler.handle(Future.succeededFuture(mapCountry));
        }
    }
}
