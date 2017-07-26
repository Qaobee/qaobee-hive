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

package com.qaobee.hive.dao.impl;

import com.qaobee.hive.verticles.CaptchaVerticle;
import com.qaobee.hive.dao.ReCaptcha;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Recaptcha.
 */
public class ReCaptchaImpl implements ReCaptcha {
    @Inject
    private Vertx vertx;

    @Override
    public void verify(String challenge, Handler<AsyncResult<Void>> resultHandler) {
        vertx.eventBus().send(CaptchaVerticle.VERIFY, new JsonObject().put("challenge", challenge), new DeliveryOptions().setSendTimeout(5000L), r -> {
            if (r.succeeded()) {
                JsonObject res = (JsonObject) r.result().body();
                if (res.getBoolean("status", false)) {
                    resultHandler.handle(Future.succeededFuture());
                } else {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha")));
                }
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha")));
            }
        });
    }
}
