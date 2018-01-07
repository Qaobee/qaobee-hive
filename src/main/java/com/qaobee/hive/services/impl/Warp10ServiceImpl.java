package com.qaobee.hive.services.impl;

import com.qaobee.hive.services.Warp10Service;
import com.qaobee.hive.technical.annotations.ProxyService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Warp 10 service.
 */
@ProxyService(address = "vertx.Warp10.service", iface = Warp10Service.class)
public class Warp10ServiceImpl implements Warp10Service {
    private static final Logger LOG = LoggerFactory.getLogger(Warp10ServiceImpl.class);
    @Inject
    @Named("warp10")
    private JsonObject config;
    @Inject
    private WebClient webClient;
    private String env;

    /**
     * Instantiates a new Warp 10 service.
     *
     * @param vertx the vertx
     */
    public Warp10ServiceImpl(Vertx vertx) { // NOSONAR
        super();
        env = System.getenv("ENV");
        if (StringUtils.isBlank(env)) {
            env = "DEV";
        }
    }

    @Override
    public void sendString(String className, JsonObject labels, String value, Handler<AsyncResult<Boolean>> resultHandler) {
        sendStringWithTS(System.currentTimeMillis(), className, labels, value, resultHandler);
    }

    @Override
    public void sendStringWithTS(long timestamp, String className, JsonObject labels, String value, Handler<AsyncResult<Boolean>> resultHandler) {
        sendToWarp10(getGTS(className, timestamp, labels, value), resultHandler);
    }

    @Override
    public void sendNumber(String className, JsonObject labels, double value, Handler<AsyncResult<Boolean>> resultHandler) {
        sendNumberWithTS(System.currentTimeMillis(), className, labels, value, resultHandler);
    }


    @Override
    public void sendNumberWithTS(long timestamp, String className, JsonObject labels, double value, Handler<AsyncResult<Boolean>> resultHandler) {
        sendToWarp10(getGTS(className, timestamp, labels, value), resultHandler);
    }

    private void sendToWarp10(String gts, Handler<AsyncResult<Boolean>> resultHandler) {
        if(!"DEV".equals(env)) {
            webClient.post(config.getInteger("port"), config.getString("host"), config.getString("path") + "update")
                    .putHeader("X-Warp10-Token", config.getString("writeToken"))
                    .ssl("https".equals(config.getString("scheme", "http")))
                    .sendBuffer(Buffer.buffer(gts), res -> resultHandler.handle(Future.succeededFuture(res.succeeded())));
        }
    }

    private String getLabels(JsonObject labels) {
        labels.put("env", env);
        List<String> labelsArr = new ArrayList<>(labels.fieldNames().size());
        labels.fieldNames().forEach(n -> {
            try {
                labelsArr.add(n + "=" + URLEncoder.encode(labels.getValue(n).toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LOG.error(e.getMessage(), e);
            }
        });
        return String.join(",", labelsArr);
    }

    private String getGTS(String className, long time, JsonObject labels, Object value) {
        StringBuilder sb = new StringBuilder()
                .append(time * 1000)
                .append("// ")
                .append(className)
                .append("{")
                .append(getLabels(labels))
                .append("} ");
        if (value instanceof String) {
            sb.append("'").append(value).append("'");
        } else {
            sb.append(value);
        }
        return sb.toString();
    }
}
