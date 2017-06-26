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

import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

import java.util.List;
import java.util.Map;

/**
 * The interface Mongo db.
 */
public interface MongoDB {

    /**
     * Upsert.
     *
     * @param o the o
     *
     * @return the promise
     */
    Promise<String, QaobeeException, Integer> upsert(Object o);

    /**
     * Upsert.
     *
     * @param document   the document
     * @param collection the collection
     *
     * @return the promise
     */
    Promise<String, QaobeeException, Integer> upsert(JsonObject document, String collection);

    /**
     * Upsert.
     *
     * @param query      the query
     * @param document   the document
     * @param collection the collection
     *
     * @return the promise
     */
    Promise<String, QaobeeException, Integer> upsert(JsonObject query, JsonObject document, Class<?> collection);

    /**
     * Upsert.
     *
     * @param query      the query
     * @param document   the document
     * @param collection the collection
     *
     * @return the promise
     */
    Promise<String, QaobeeException, Integer> upsert(JsonObject query, JsonObject document, String collection);

    /**
     * Gets by id.
     *
     * @param id         the id
     * @param collection the collection
     *
     * @return the by id
     */
    Promise<JsonObject, QaobeeException, Integer>  getById(String id, String collection);

    /**
     * Gets by id.
     *
     * @param id         the id
     * @param collection the collection
     * @param minimal    the minimal
     *
     * @return the by id
     */
    Promise<JsonObject, QaobeeException, Integer> getById(String id, String collection, List<String> minimal);

    /**
     * Gets minimal.
     *
     * @param minimal the minimal
     *
     * @return the minimal
     */
    JsonObject getMinimal(List<String> minimal);

    /**
     * Find by criterias.
     *
     * @param criteria   the criteria
     * @param fields     the fields
     * @param sort       the sort
     * @param order      the order
     * @param limit      the limit
     * @param collection the collection
     *
     * @return the promise
     */
    Promise<JsonArray, QaobeeException, Integer> findByCriterias(Map<String, Object> criteria, List<String> fields, String sort, int order, int limit, String collection);

    /**
     * Find all.
     *
     * @param fields     the fields
     * @param sort       the sort
     * @param order      the order
     * @param limit      the limit
     * @param collection the collection
     *
     * @return the promise
     */
    Promise<JsonArray, QaobeeException, Integer> findAll(List<String> fields, String sort, int order, int limit, String collection);

    /**
     * Aggregate promise.
     *
     * @param pipelineAggregation the pipeline aggregation
     * @param collection          the collection
     *
     * @return the promise
     */
    Promise<JsonArray, QaobeeException, Integer> aggregate(JsonArray pipelineAggregation, String collection);
}
