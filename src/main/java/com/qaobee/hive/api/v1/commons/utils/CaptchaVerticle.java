package com.qaobee.hive.api.v1.commons.utils;

import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.netty.handler.codec.http.QueryStringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpHeaders;
import org.vertx.java.core.json.JsonObject;

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
    private HttpClient clientCaptcha;
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        clientCaptcha = vertx.createHttpClient()
                .setHost("www.google.com")
                .setSSL(true)
                .setTrustAll(true)
                .setPort(443)
                .setKeepAlive(true)
                .setConnectTimeout(3000);
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus().registerHandler(VERIFY, this::verify);
    }

    private void verify(Message<JsonObject> message) {
        QueryStringEncoder enc = new QueryStringEncoder("");
        enc.addParam("secret", runtime.getString("recaptcha.pkey"));
        enc.addParam("response", message.body().getString("challenge"));
        String encodedBody = enc.toString().substring(1);

        clientCaptcha.exceptionHandler(ex -> utils.sendStatusJson(false, ex.getMessage(), message));

        HttpClientRequest query = clientCaptcha.post("/recaptcha/api/siteverify", resp -> {
            if (resp.statusCode() >= 200 && resp.statusCode() < 400) {
                resp.bodyHandler(buffer -> {
                    System.out.println(buffer.length() + " : " + buffer.toString());
                    if (buffer.length() > 0) {
                        final JsonObject response = new JsonObject(buffer.toString());
                        if (response.getBoolean("success")) {
                            utils.sendStatusJson(true, message);
                        } else {
                            utils.sendStatusJson(false, "wrong captcha", message);
                        }
                    } else {
                        utils.sendStatusJson(false, "no response", message);
                    }
                });
            } else {
                utils.sendStatusJson(false, resp.statusMessage(), message);
            }
        });
        query.headers().add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=utf-8");
        query.end(new Buffer(encodedBody));
    }
}
