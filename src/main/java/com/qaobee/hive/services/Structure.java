/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */

package com.qaobee.hive.services;

import com.qaobee.hive.services.impl.StructureImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * The interface Structure.
 */
@ProxyGen
@VertxGen
public interface Structure {
    /**
     * The constant ADDRESS.
     */
    String ADDRESS = "vertx.Structure.service";

    /**
     * Create structure.
     *
     * @param vertx the vertx
     * @return the structure
     */
    static Structure create(Vertx vertx) {
        return new StructureImpl(vertx);
    }

    /**
     * Create proxy structure.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the structure
     */
    static Structure createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(Structure.class, vertx, address);
    }

    /**
     * Update.
     *
     * @param structure      the structure
     * @param resultHandler the result handler
     */
    void update(JsonObject structure, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets list of structures.
     *
     * @param activity      the activity
     * @param address       the address
     * @param resultHandler the result handler
     */
    void getListOfStructures(String activity, JsonObject address, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Gets structure.
     *
     * @param id            the id
     * @param resultHandler the result handler
     */
    void getStructure(String id, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Add structure.
     *
     * @param structure     the structure
     * @param resultHandler the result handler
     */
    void addStructure(JsonObject structure, Handler<AsyncResult<JsonObject>> resultHandler);
}
