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
import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

/**
 * The type Recaptcha.
 */
public class RecaptchaImpl implements ReCaptcha {
    private final Vertx vertx;

    /**
     * Instantiates a new Recaptcha.
     *
     * @param vertx the vertx
     */
    @Inject
    public RecaptchaImpl(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public CompletableFuture<Boolean> verify(String challenge) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        vertx.eventBus().sendWithTimeout(CaptchaVerticle.VERIFY, new JsonObject().putString("challenge", challenge), 5000L, r -> {
            if (r.succeeded()) {
                JsonObject res = (JsonObject) r.result().body();
                if (res.getBoolean("status", false)) {
                    future.complete(true);
                } else {
                    future.completeExceptionally(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha"));
                }
            } else {
                future.completeExceptionally(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha"));
            }
        });
        return future;
    }
}
