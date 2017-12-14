package com.qaobee.hive.services;

import com.qaobee.hive.services.impl.Warp10ServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

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
        return new Warp10ServiceVertxEBProxy(vertx, address);
    }

    /**
     * Send string.
     *
     * @param className     the class name
     * @param labels        the labels
     * @param value         the value
     * @param resultHandler the result handler
     */
    void sendString(String className, JsonObject labels,  String value, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Send string.
     *
     * @param timestamp     the timestamp
     * @param className     the class name
     * @param labels        the labels
     * @param value         the value
     * @param resultHandler the result handler
     */
    void sendStringWithTS(long timestamp, String className, JsonObject labels,  String value, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Send number.
     *
     * @param className     the class name
     * @param labels        the labels
     * @param value         the value
     * @param resultHandler the result handler
     */
    void sendNumber(String className, JsonObject labels,  double value, Handler<AsyncResult<Boolean>> resultHandler);

    /**
     * Send number.
     *
     * @param timestamp     the timestamp
     * @param className     the class name
     * @param labels        the labels
     * @param value         the value
     * @param resultHandler the result handler
     */
    void sendNumberWithTS(long timestamp, String className, JsonObject labels,  double value, Handler<AsyncResult<Boolean>> resultHandler);


}
