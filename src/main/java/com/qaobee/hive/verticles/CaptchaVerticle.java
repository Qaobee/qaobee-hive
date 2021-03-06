package com.qaobee.hive.verticles;

import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.netty.handler.codec.http.QueryStringEncoder;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import org.apache.http.protocol.HTTP;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The type Captcha verticle.
 */
@DeployableVerticle
public class CaptchaVerticle extends AbstractGuiceVerticle {
    /**
     * The constant VERIFY.
     */
    public static final String VERIFY = "reCaptcha.verify";
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    private WebClient webClient;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this).add(VERIFY, this::verify).register(startFuture);
    }

    private void verify(Message<JsonObject> message) {
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
                                utils.sendStatus(true, message);
                            } else {
                                utils.sendStatus(false, "wrong captcha", message);
                            }
                        } else {
                            utils.sendStatus(false, res.result().statusMessage(), message);
                        }
                    } else {
                        utils.sendStatus(false, res.cause().getMessage(), message);
                    }
                });
    }
}
