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

import com.qaobee.hive.services.impl.MongoDBImpl;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.List;

/**
 * The interface Mongo db.
 */
@ProxyGen
@VertxGen
public interface MongoDB {

    /**
     * Create mongo db.
     *
     * @param vertx the vertx
     * @return the mongo db
     */
    static MongoDB create(Vertx vertx) {
        return new MongoDBImpl(vertx);
    }

    /**
     * Create proxy mongo db.
     *
     * @param vertx   the vertx
     * @param address the address
     * @return the mongo db
     */
    static MongoDB createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(MongoDB.class, vertx, address);
    }

    /**
     * Upsert.
     *
     * @param document      the document
     * @param collection    the collection
     * @param resultHandler the result handler
     */
    void upsert(JsonObject document, String collection, Handler<AsyncResult<String>> resultHandler);

    /**
     * Upsert.
     *
     * @param query         the query
     * @param document      the document
     * @param collection    the collection
     * @param resultHandler the result handler
     */
    void upsertWithQuery(JsonObject query, JsonObject document, String collection, Handler<AsyncResult<String>> resultHandler);

    /**
     * Gets by id.
     *
     * @param id            the id
     * @param collection    the collection
     * @param resultHandler the result handler
     */
    void getById(String id, String collection, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets by id.
     *
     * @param id            the id
     * @param collection    the collection
     * @param minimal       the minimal
     * @param resultHandler the result handler
     */
    void getByIdMinimal(String id, String collection, List<String> minimal, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Find by criterias.
     *
     * @param criteria       the criteria
     * @param criteriaOption the criteria option
     * @param collection     the collection
     * @param resultHandler  the result handler
     */
    void findByCriterias(JsonObject criteria, CriteriaOption criteriaOption, String collection, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Find all.
     *
     * @param criteriaOption the criteria option
     * @param collection     the collection
     * @param resultHandler  the result handler
     */
    void findAll(CriteriaOption criteriaOption, String collection, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Aggregate.
     *
     * @param pipelineAggregation the pipeline aggregation
     * @param collection          the collection
     * @param resultHandler       the result handler
     */
    void aggregate(JsonArray pipelineAggregation, String collection, Handler<AsyncResult<JsonArray>> resultHandler);
}
