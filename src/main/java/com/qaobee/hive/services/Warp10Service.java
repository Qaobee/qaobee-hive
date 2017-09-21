package com.qaobee.hive.services;

import com.qaobee.hive.services.impl.Warp10ServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Warp 10 service.
 */
@ProxyGen
@VertxGen
public interface Warp10Service {

    /**
     * Create warp 10 service.
     *
     * @param vertx the vertx
     * @return the warp 10 service
     */
    static Warp10Service create(Vertx vertx) {
        return new Warp10ServiceImpl(vertx);
    }

    /**
     * Create proxy warp 10 service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the warp 10 service
     */
    static Warp10Service createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(Warp10Service.class, vertx, address);
    }

    /**
     * Push gts.
     *
     * @param series        the series
     * @param time          the time
     * @param value         the value
     * @param labels        the labels
     * @param resultHandler the result handler
     */
    void pushGTS(String series, long time, JsonObject value, JsonObject labels, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Push gts str.
     *
     * @param series        the series
     * @param time          the time
     * @param value         the value
     * @param labels        the labels
     * @param resultHandler the result handler
     */
    void pushGTSStr(String series, long time, String value, JsonObject labels, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Execute warp script.
     *
     * @param script        the script
     * @param resultHandler the result handler
     */
    void executeWarpScript(String script, Handler<AsyncResult<JsonArray>> resultHandler);
}
