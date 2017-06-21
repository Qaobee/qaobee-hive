package com.qaobee.hive.api.v1.commons.utils;

import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.netty.handler.codec.http.QueryStringEncoder;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import org.apache.http.protocol.HTTP;
import org.jdeferred.Deferred;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The type Captcha verticle.
 */
@DeployableVerticle(isWorker = false)
public class CaptchaVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(CaptchaVerticle.class);
    /**
     * The constant VERIFY.
     */
    public static final String VERIFY = "reCaptcha.verify";
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    private Utils utils;
    @Inject
    private WebClient webClient;

    @Override
    public void start() {
        if (LOG.isDebugEnabled()) {
            LOG.debug(this.getClass().getName() + " started");
        }
        super.start();
        vertx.eventBus().consumer(VERIFY, this::verify);
    }

    private void verify(Message<JsonObject> message) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        QueryStringEncoder enc = new QueryStringEncoder("");
        enc.addParam("secret", runtime.getString("recaptcha.pkey"));
        enc.addParam("response", message.body().getString("challenge"));
        String encodedBody = enc.toString().substring(1);

        webClient.post(443, "www.google.com", "/recaptcha/api/siteverify")
                .ssl(true)
                .putHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=utf-8")
                .sendBuffer(Buffer.buffer(encodedBody), res -> {
                    if (res.succeeded()) {
                        if (res.result().statusCode() >= 200 && res.result().statusCode() < 400) {
                            JsonObject response = res.result().bodyAsJsonObject();
                            if (response.getBoolean("success")) {
                                utils.sendStatusJson(true, message);
                            } else {
                                utils.sendStatusJson(false, "wrong captcha", message);
                            }
                        } else {
                            utils.sendStatusJson(false, res.result().statusMessage(), message);
                        }
                    } else {
                        utils.sendStatusJson(false, res.cause().getMessage(), message);
                    }
                });
    }
}
