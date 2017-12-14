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

import com.qaobee.hive.services.impl.ShippingServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * The interface Shipping service.
 */
@ProxyGen
@VertxGen
public interface ShippingService {

    /**
     * Create shipping service.
     *
     * @param vertx the vertx
     * @return the shipping service
     */
    static ShippingService create(Vertx vertx) {
        return new ShippingServiceImpl(vertx);
    }

    /**
     * Create proxy shipping service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the shipping service
     */
    static ShippingService createProxy(Vertx vertx, String address) {
        return new ShippingServiceVertxEBProxy(vertx, address);
    }

    /**
     * Pay.
     *
     * @param user          the user
     * @param paymentData   the payment data
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void pay(JsonObject user, JsonObject paymentData, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Unsubscribe.
     *
     * @param user          the user
     * @param planId        the plan id
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void unsubscribe(JsonObject user, int planId , String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Web hook.
     *
     * @param body          the body
     * @param resultHandler the result handler
     */
    void webHook(JsonObject body, Handler<AsyncResult<Boolean>> resultHandler);
}
