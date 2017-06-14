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

import com.qaobee.hive.api.v1.commons.utils.CaptchaVerticle;
import com.qaobee.hive.dao.ReCaptcha;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;

/**
 * The type Recaptcha.
 */
public class RecaptchaImpl implements ReCaptcha {
    @Inject
    private Vertx vertx;

    @Override
    public Promise<Boolean, QaobeeException, Integer> verify(String challenge) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        vertx.eventBus().send(CaptchaVerticle.VERIFY, new JsonObject().put("challenge", challenge), new DeliveryOptions().setSendTimeout(5000L), r -> {
            if (r.succeeded()) {
                JsonObject res = (JsonObject) r.result().body();
                if (res.getBoolean("status", false)) {
                    deferred.resolve(true);
                } else {
                    deferred.reject(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha"));
                }
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha"));
            }
        });
        return deferred.promise();
    }
}
