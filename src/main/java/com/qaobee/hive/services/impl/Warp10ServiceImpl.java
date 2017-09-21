package com.qaobee.hive.services.impl;

import com.qaobee.hive.services.Warp10Service;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ProxyService(address = "vertx.Warp10.service", iface = Warp10Service.class)
public class Warp10ServiceImpl implements Warp10Service {
    @Inject
    @Named("warp10")
    private JsonObject config;
    @Inject
    private WebClient webClient;

    public Warp10ServiceImpl(Vertx vertx) {
        super();
    }

    @Override
    public void pushGTS(String series, long time, JsonObject value, JsonObject labels, Handler<AsyncResult<Boolean>> resultHandler) {
        String gts = String.join("\n", getGTS(series, time, value, labels));
        webClient.post(config.getInteger("port"), config.getString("host"), config.getString("path") + "update")
                .putHeader("X-Warp10-Token", config.getString("write"))
                .ssl("https".equals(config.getString("scheme", "http")))
                .sendBuffer(Buffer.buffer(gts), res -> resultHandler.handle(Future.succeededFuture(res.succeeded())));
    }

    @Override
    public void pushGTSStr(String series, long time, String value, JsonObject labels, Handler<AsyncResult<Boolean>> resultHandler) {
        List<String> labelsArr = new ArrayList<>(labels.fieldNames().size());
        labels.fieldNames().forEach(n -> labelsArr.add(n + "=" + labels.getValue(n)));
        StringBuilder gts = new StringBuilder()
                .append(time * 1000)
                .append("// ")
                .append(series)
                .append("{")
                .append(String.join(",", labelsArr))
                .append("} ").append("'")
                .append(value)
                .append("'");
        webClient.post(config.getInteger("port"), config.getString("host"), config.getString("path") + "update")
                .putHeader("X-Warp10-Token", config.getString("write"))
                .ssl("https".equals(config.getString("scheme", "http")))
                .sendBuffer(Buffer.buffer(gts.toString()), res -> resultHandler.handle(Future.succeededFuture(res.succeeded())));
    }

    private List<String> getGTS(String series, long time, JsonObject value, JsonObject labels) {
        List<String> labelsArr = new ArrayList<>(labels.fieldNames().size());
        labels.fieldNames().forEach(n -> labelsArr.add(n + "=" + labels.getValue(n)));
        List<String> gts = new ArrayList<>();
        gts.add(getGTS(series, time, value, labelsArr, "chrono"));
        gts.add(getGTS(series, time, value, labelsArr, "value"));
        return gts;
    }

    private String getGTS(String series, long time, JsonObject value, List<String> labelsArr, String type) {
        StringBuilder sb = new StringBuilder()
                .append(time * 1000)
                .append("// ")
                .append(series)
                .append(".").append(type)
                .append("{")
                .append(String.join(",", labelsArr))
                .append("} ");
        Object val = value.getValue(type);
        if (val instanceof String) {
            sb.append("'")
                    .append(value.getValue(type))
                    .append("'");
        } else {
            sb.append(value.getValue(type));
        }
        return sb.toString();
    }

    @Override
    public void executeWarpScript(String script, Handler<AsyncResult<JsonArray>> resultHandler) {
        webClient.post(config.getInteger("port"), config.getString("host"), config.getString("path") + "exec")
                .putHeader("Content-Type", "application/json")
                .ssl("https".equals(config.getString("scheme", "http")))
                .sendBuffer(Buffer.buffer("'" + config.getString("read") + "' " + script), res -> {
                    if (res.succeeded() && res.result().statusCode() < 400) {
                        resultHandler.handle(Future.succeededFuture(new JsonArray(res.result().bodyAsString())));
                    } else {
                        resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BUSINESS_ERROR, res.result().statusMessage())));
                    }
                });
    }
}
