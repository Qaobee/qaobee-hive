/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.services;

import com.qaobee.hive.services.impl.FeedbackServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * The interface Feedback dao.
 */
@ProxyGen
@VertxGen
@FunctionalInterface
public interface FeedbackService {

    /**
     * Create feedback service.
     *
     * @param vertx the vertx
     *
     * @return the feedback service
     */
    static FeedbackService create(Vertx vertx) {
        return new FeedbackServiceImpl(vertx);
    }

    /**
     * Create proxy feedback service.
     *
     * @param vertx   the vertx
     * @param address the address
     *
     * @return the feedback service
     */
    static FeedbackService createProxy(Vertx vertx, String address) {
        return new FeedbackServiceVertxEBProxy(vertx, address);
    }

    /**
     * Send feedback.
     *
     * @param data          the data
     * @param resultHandler the result handler
     */
    void sendFeedback(JsonObject data, Handler<AsyncResult<Void>> resultHandler) ;
}
