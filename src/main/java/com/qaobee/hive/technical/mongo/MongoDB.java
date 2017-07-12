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

package com.qaobee.hive.technical.mongo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * The interface Mongo db.
 */
public interface MongoDB {

    /**
     * Upsert.
     *
     * @param o             the o
     * @param resultHandler the result handler
     */
    void upsert(Object o, Handler<AsyncResult<String>> resultHandler);

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
    void upsert(JsonObject query, JsonObject document, Class<?> collection, Handler<AsyncResult<String>> resultHandler);

    /**
     * Upsert.
     *
     * @param query         the query
     * @param document      the document
     * @param collection    the collection
     * @param resultHandler the result handler
     */
    void upsert(JsonObject query, JsonObject document, String collection, Handler<AsyncResult<String>> resultHandler);

    /**
     * Gets by id.
     *
     * @param id            the id
     * @param collection    the collection
     * @param resultHandler the result handler
     */
    void  getById(String id, String collection, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets by id.
     *
     * @param id            the id
     * @param collection    the collection
     * @param minimal       the minimal
     * @param resultHandler the result handler
     */
    void getById(String id, String collection, List<String> minimal, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * Gets minimal.
     *
     * @param minimal the minimal
     * @return the minimal
     */
    JsonObject getMinimal(List<String> minimal);

    /**
     * Find by criterias.
     *
     * @param criteria      the criteria
     * @param fields        the fields
     * @param sort          the sort
     * @param order         the order
     * @param limit         the limit
     * @param collection    the collection
     * @param resultHandler the result handler
     */
    void findByCriterias(Map<String, Object> criteria, List<String> fields, String sort, int order, int limit, String collection, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Find all.
     *
     * @param fields        the fields
     * @param sort          the sort
     * @param order         the order
     * @param limit         the limit
     * @param collection    the collection
     * @param resultHandler the result handler
     */
    void findAll(List<String> fields, String sort, int order, int limit, String collection, Handler<AsyncResult<JsonArray>> resultHandler);

    /**
     * Aggregate.
     *
     * @param pipelineAggregation the pipeline aggregation
     * @param collection          the collection
     * @param resultHandler       the result handler
     */
    void aggregate(JsonArray pipelineAggregation, String collection, Handler<AsyncResult<JsonArray>> resultHandler);
}
