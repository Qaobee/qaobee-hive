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

import com.qaobee.hive.dao.ReCaptcha;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.netty.handler.codec.http.QueryStringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpHeaders;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.CompletableFuture;

/**
 * The type Recaptcha.
 */
public class RecaptchaImpl implements ReCaptcha {
    private static final Logger LOG = LoggerFactory.getLogger(RecaptchaImpl.class);
    @Inject
    private Vertx vertx;
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    private HttpClient client;

    @Override
    public CompletableFuture<Boolean> verify(String challenge) {
        if(client == null) {
            client = vertx.createHttpClient()
                    .setHost("www.google.com")
                    .setSSL(true)
                    .setTrustAll(true)
                    .setPort(443)
                    .setKeepAlive(true)
                    .setConnectTimeout(3000);
        }
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        QueryStringEncoder enc = new QueryStringEncoder("");
        enc.addParam("secret", runtime.getString("recaptcha.pkey"));
        enc.addParam("response", challenge);
        String encodedBody = enc.toString().substring(1);

        client.exceptionHandler(ex -> future.completeExceptionally(new QaobeeException(ExceptionCodes.HTTP_ERROR, ex.getMessage())));

        HttpClientRequest query = client.post("/recaptcha/api/siteverify", resp -> {
            if (resp.statusCode() >= 200 && resp.statusCode() < 400) {
                resp.bodyHandler(buffer -> {
                    System.out.println(buffer.length() + " : " + buffer.toString());
                    if (buffer.length() > 0) {
                        final JsonObject response = new JsonObject(buffer.toString());
                        if (response.getBoolean("success")) {
                            future.complete(true);
                        } else {
                            future.completeExceptionally(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha"));
                        }
                    } else {
                        future.completeExceptionally(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "no response"));
                    }
                });
            } else {
                future.completeExceptionally(new QaobeeException(ExceptionCodes.HTTP_ERROR, resp.statusMessage()));
            }
        });
        query.headers().add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=utf-8");
        query.end(new Buffer(encodedBody));
        return future;
    }
}
