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

package com.qaobee.hive.services.impl;

import com.qaobee.hive.services.CountryService;
import com.qaobee.hive.services.StructureService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.services.MongoDB;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Structure.
 */
@ProxyService(address = StructureService.ADDRESS, iface = StructureService.class)
public class StructureServiceImpl implements StructureService {
    private static final String COUNTRY_ALPHA_2_FIELD = "countryAlpha2";
    @Inject
    private MongoDB mongo;
    @Inject
    private CountryService countryService;
    private Vertx vertx;

    /**
     * Instantiates a new Structure.
     *
     * @param vertx the vertx
     */
    public StructureServiceImpl(Vertx vertx) {
        super();
        this.vertx = vertx;
    }

    @Override
    public void update(JsonObject structure, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(structure, DBCollections.STRUCTURE, res -> {
            if(res.succeeded()) {
                getStructure(res.result(), resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void getListOfStructures(String activity, JsonObject address, Handler<AsyncResult<JsonArray>> resultHandler) {
        countryService.getCountryFromAlpha2(address.getString(COUNTRY_ALPHA_2_FIELD, "FR"), country -> {
            if (country.succeeded()) {
                // $MACTH section
                JsonObject dbObjectParent = new JsonObject().put("activity._id", activity).put("country._id", country.result().getString("_id"));
                // City OR Zipcode
                JsonArray dbList = new JsonArray()
                        .add(new JsonObject().put("address.city", address.getString("city").toUpperCase()))
                        .add(new JsonObject().put("address.zipcode", address.getString("zipcode")));
                dbObjectParent.put("$or", dbList);
                JsonObject match = new JsonObject().put("$match", dbObjectParent);
                // Pipeline
                JsonArray pipelineAggregation = new JsonArray().add(match);
                mongo.aggregate(pipelineAggregation, DBCollections.STRUCTURE, resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(country.cause()));
            }
        });
    }

    @Override
    public void getStructure(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.STRUCTURE, resultHandler);
    }

    @Override
    public void addStructure(JsonObject structure, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(structure, DBCollections.STRUCTURE, id -> {
            if(id.succeeded()) {
                structure.put("_id", id.result());
                resultHandler.handle(Future.succeededFuture(structure));
            } else {
                resultHandler.handle(Future.failedFuture(id.cause()));
            }
        });
    }
}
