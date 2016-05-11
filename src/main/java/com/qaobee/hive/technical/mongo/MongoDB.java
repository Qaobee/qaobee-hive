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

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * The interface Mongo dB.
 */
public interface MongoDB {

    /**
     * Save string.
     *
     * @param o object to save
     * @return id string
     * @throws QaobeeException can't save
     */
    String save(Object o) throws QaobeeException;

    /**
     * Update string.
     *
     * @param document   the document
     * @param collection the collection
     * @return the string
     * @throws MongoException the mongo exception
     */
    String update(JsonObject document, Class<?> collection);

    /**
     * Update string.
     *
     * @param document   the document
     * @param collection the collection
     * @return the string
     * @throws MongoException the mongo exception
     */
    String update(JsonObject document, String collection);

    /**
     * Update a document.
     *
     * @param query      (JSonObject) : The selection criteria for the update.
     * @param set        (JSonObject) : The modifications to apply.(need to have a                   {                   "$set" :                   {                   ...                   }                   }
     * @param collection (Class) : collection to update
     * @return string string
     * @throws MongoException the mongo exception
     */
    String update(JsonObject query, JsonObject set, Class<?> collection);

    /**
     * Saves a document in a colection.
     *
     * @param document   object to save
     * @param collection target
     * @return id string
     * @throws QaobeeException can't save
     */
    String save(JsonObject document, Class<?> collection) throws QaobeeException;

    /**
     * Saves a document in a colection.
     *
     * @param document   object to save
     * @param collection target
     * @return id string
     * @throws QaobeeException the qaobee exception
     */
    String save(JsonObject document, String collection) throws QaobeeException;

    /**
     * Get a document by id.
     *
     * @param id         the id
     * @param collection the collection
     * @return the document
     * @throws QaobeeException not found
     */
    JsonObject getById(String id, Class<?> collection) throws QaobeeException;

    /**
     * Get a document by id.
     *
     * @param id         the id
     * @param collection the collection
     * @return the document
     * @throws QaobeeException not found
     */
    JsonObject getById(String id, String collection) throws QaobeeException;

    /**
     * Get a document by id.
     *
     * @param id         the id
     * @param collection the collection
     * @param minimal    fields to retrieve
     * @return the document
     * @throws QaobeeException not found
     */
    JsonObject getById(String id, Class<?> collection, List<String> minimal) throws QaobeeException;

    /**
     * Gets the minimal.
     *
     * @param minimal minimal list of fields to retrieve
     * @return a map
     */
    Map<String, Boolean> getMinimal(List<String> minimal);

    /**
     * Find document by criteria with minimal fields and a sort order.
     *
     * @param criteria   (Map(String, Object)) : criteria
     * @param fields     (List(String))    : fields to include (null if all fields)
     * @param sort       (String)      : sort field (null if no sort)
     * @param order      (int)      : sort order
     * @param limit      (int)      : limit (0 if no limit)
     * @param collection (Class)    : collection
     * @return JsonArray : an array
     */
    JsonArray findByCriterias(Map<String, Object> criteria, List<String> fields, String sort, int order, int limit, Class<?> collection);

    /**
     * Find all documents with minimal fields and a sort order.
     *
     * @param fields     fields to include
     * @param sort       sort field
     * @param order      sort order
     * @param limit      limit
     * @param collection collection
     * @return an array
     */
    JsonArray findAll(List<String> fields, String sort, int order, int limit, Class<?> collection);

    /**
     * Aggregate json array.
     *
     * @param field      the field
     * @param pipeline   the pipeline
     * @param collection the collection
     * @return the json array
     * @throws QaobeeException the qaobee exception
     */
    JsonArray aggregate(String field, List<DBObject> pipeline, Class<?> collection) throws QaobeeException;

    /**
     * Aggregate json array.
     *
     * @param field      the field
     * @param pipeline   the pipeline
     * @param collection the collection
     * @return the json array
     * @throws QaobeeException the qaobee exception
     */
    JsonArray aggregate(String field, List<DBObject> pipeline, String collection) throws QaobeeException;

    /**
     * Gets db.
     *
     * @return the db
     */
    DB getDb();
}
