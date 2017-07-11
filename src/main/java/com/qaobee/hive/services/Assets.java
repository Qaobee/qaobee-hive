package com.qaobee.hive.services;

import com.qaobee.hive.services.impl.AssetsImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Assets service.
 */
@ProxyGen
@VertxGen
public interface Assets {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.Assets.service";

    /**
     * Create assets service.
     *
     * @param vertx the vertx
     * @return the assets service
     */
    static Assets create(Vertx vertx) {
        return new AssetsImpl(vertx);
    }

    /**
     * Create proxy assets service.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the assets service
     */
    static Assets createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(Assets.class, vertx, address);
    }

    /**
     * Add asset.
     *
     * @param userId        the user id
     * @param filename      the filename
     * @param collection    the collection
     * @param field         the field
     * @param contentType   the content type
     * @param locale        the locale
     * @param resultHandler the result handler
     */
    void addAsset(String userId, String filename, String collection, String field, String contentType, String locale, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets asset.
     *
     * @param collection    the collection
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getAsset(String collection, String id,Handler<AsyncResult<JsonObject>> resultHandler);
}
