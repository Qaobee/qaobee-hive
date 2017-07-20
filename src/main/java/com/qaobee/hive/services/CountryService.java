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

package com.qaobee.hive.services;

import com.qaobee.hive.services.impl.CountryServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Country service.
 */
@ProxyGen
@VertxGen
public interface CountryService {

    /**
     * Create country service.
     *
     * @param vertx the vertx
     * @return the country service
     */
    static CountryService create(Vertx vertx) {
        return new CountryServiceImpl(vertx);
    }

    /**
     * Create proxy country service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the country service
     */
    static CountryService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(CountryService.class, vertx, address);
    }

    /**
     * Gets country from alpha 2.
     *
     * @param alpha2        the alpha 2
     * @param resultHandler the result handler
     */
    void getCountryFromAlpha2(String alpha2, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets country list.
     *
     * @param locale        the locale
     * @param label         the label
     * @param resultHandler the result handler
     */
    void getCountryList(String locale, String label, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets country.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getCountry(String id, Handler<AsyncResult<JsonObject>> resultHandler);
}
