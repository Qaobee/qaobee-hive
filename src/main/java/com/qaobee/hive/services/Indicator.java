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

import com.qaobee.hive.services.impl.IndicatorImpl;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;
import org.jdeferred.Promise;

/**
 * The interface Indicator.
 */
@ProxyGen
@VertxGen
public interface Indicator {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.Indicator.service";

    /**
     * Create indicator.
     *
     * @param vertx the vertx
     * @return the indicator
     */
    static Indicator create(Vertx vertx) {
        return new IndicatorImpl(vertx);
    }

    /**
     * Create proxy indicator.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the indicator
     */
    static Indicator createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(Indicator.class, vertx, address);
    }

    /**
     * Gets indicator by code.
     *
     * @param activityId     the activity id
     * @param countryId      the country id
     * @param listIndicators the list indicators
     * @param resultHandler  the result handler
     */
    void getIndicatorByCode(String activityId, String countryId, JsonArray listIndicators, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets indicators list.
     *
     * @param activityId    the activity id
     * @param countryId     the country id
     * @param screen        the screen
     * @param resultHandler the result handler
     */
    void getIndicatorsList(String activityId, String countryId, JsonArray screen, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets indicator.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getIndicator(String id, Handler<AsyncResult<JsonObject>> resultHandler);
}
