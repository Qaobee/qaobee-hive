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
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.exceptions.QaobeeSvcException;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Country dao.
 */
@ProxyService(address = CountryService.ADDRESS, iface = CountryService.class)
public class CountryServiceImpl implements CountryService {
    private final JsonObject mapCountry = new JsonObject();
    @Inject
    private MongoDB mongo;

    private Vertx vertx;

    public CountryServiceImpl(Vertx vertx) {
        super();
        this.vertx = vertx;
    }

    @Override
    public void getCountryFromAlpha2(String alpha2, Handler<AsyncResult<JsonObject>> resultHandler) {
        getCountries()
                .done(map -> {
                    if(map.containsKey(alpha2.toUpperCase())) {
                        resultHandler.handle(Future.succeededFuture(map.getJsonObject(alpha2.toUpperCase())));
                    } else {
                        resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.DATA_ERROR, "no data found")));
                    }
                })
                .fail(e -> resultHandler.handle(Future.failedFuture(new QaobeeSvcException(e.getCode(), e))));
    }

    @Override
    public void getCountryList(String locale, String label, Handler<AsyncResult<JsonArray>> resultHandler) {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(CountryRoute.PARAM_LOCAL, locale);
        // label
        if (StringUtils.isNotBlank(label)) {
            criterias.put(CountryRoute.PARAM_LABEL, label);
        }
        mongo.findByCriterias(criterias, null, null, -1, -1, DBCollections.COUNTRY)
                .done(res -> resultHandler.handle(Future.succeededFuture(res)))
                .fail(e -> resultHandler.handle(Future.failedFuture(new QaobeeSvcException(e.getCode(), e))));
    }

    @Override
    public void getCountry(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.COUNTRY).done(res -> {
            if (res.size() > 0) {
                resultHandler.handle(Future.succeededFuture(res));
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.DATA_ERROR, "no data found")));
            }
        }).fail(e -> resultHandler.handle(Future.failedFuture(new QaobeeSvcException(e.getCode(), e))));
    }

    private Promise<JsonObject, QaobeeException, Integer> getCountries() {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (mapCountry.fieldNames().isEmpty()) {
            mongo.findAll(null, null, -1, 0, DBCollections.COUNTRY)
                    .done(resultJson -> {
                        resultJson.forEach(c -> mapCountry.put(((JsonObject) c).getString("_id").split("-")[2], (JsonObject) c));
                        deferred.resolve(mapCountry);
                    })
                    .fail(deferred::reject);
        } else {
            deferred.resolve(mapCountry);
        }
        return deferred.promise();
    }
}
