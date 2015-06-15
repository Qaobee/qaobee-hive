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

import java.util.List;
import java.util.Map;

import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.qaobee.hive.technical.exceptions.QaobeeException;

/**
 * Created by xavier on 09/11/14.
 */
public interface MongoDB {

	/**
	 * @param o
	 *            object to save
	 * @return id
	 * @throws org.vertx.java.core.json.EncodeException
	 *             can't encode
	 * @throws com.qaobee.swarn.technical.exceptions.QaobeeException
	 *             can't save
	 */
	String save(Object o) throws EncodeException, QaobeeException;

	String update(JsonObject document, Class<?> collection) throws MongoException;

	/**
	 * Update a document.
	 * 
	 * @param query
	 *            (JSonObject) : The selection criteria for the update.
	 * @param set
	 *            (JSonObject) : The modifications to apply.(need to have a {"$set" : {...}}
	 * @param collection
	 *            (Class) : collection to update
	 * @return
	 * @throws MongoException
	 */
	String update(JsonObject query, JsonObject set, Class<?> collection) throws MongoException;

	/**
	 * Saves a document in a colection.
	 * 
	 * @param document
	 *            object to save
	 * @param collection
	 *            target
	 * @return id
	 * @throws com.qaobee.swarn.technical.exceptions.QaobeeException
	 *             can't save
	 * @throws com.mongodb.MongoException
	 *             the mongo exception
	 */
	String save(JsonObject document, Class<?> collection) throws QaobeeException, MongoException;

	/**
	 * Get a document by id.
	 * 
	 * @param id
	 *            the id
	 * @param collection
	 *            the collection
	 * @return the document
	 * @throws com.qaobee.swarn.technical.exceptions.QaobeeException
	 *             not found
	 */
	JsonObject getById(String id, Class<?> collection) throws QaobeeException;

	/**
	 * Get a document by id.
	 * 
	 * @param id
	 *            the id
	 * @param collection
	 *            the collection
	 * @param minimal
	 *            fields to retrieve
	 * @return the document
	 * @throws com.qaobee.swarn.technical.exceptions.QaobeeException
	 *             not found
	 */
	JsonObject getById(String id, Class<?> collection, List<String> minimal) throws QaobeeException;

	/**
	 * Gets the minimal.
	 * 
	 * @param minimal
	 *            minimal list of fields to retrieve
	 * @return a map
	 */
	Map<String, Boolean> getMinimal(List<String> minimal);

	/**
	 * Remove a document from a collection.
	 * 
	 * @param id
	 *            document id
	 * @param collection
	 *            the collection
	 */
	void deleteById(String id, Class<?> collection);

	/**
	 * Find document by criteria with minimal fields and a sort order.
	 * 
	 * @param criteria
	 *            criteria
	 * @param fields
	 *            fields to include
	 * @param sort
	 *            sort field
	 * @param order
	 *            sort order
	 * @param limit
	 *            limit
	 * @param collection
	 *            collection
	 * @return an array
	 */
	JsonArray findByCriterias(Map<String, Object> criteria, List<String> fields, String sort, int order, int limit, Class<?> collection);

	/**
	 * Find all documents with minimal fields and a sort order.
	 * 
	 * @param fields
	 *            fields to include
	 * @param sort
	 *            sort field
	 * @param order
	 *            sort order
	 * @param limit
	 *            limit
	 * @param collection
	 *            collection
	 * @return an array
	 */
	JsonArray findAll(List<String> fields, String sort, int order, int limit, Class<?> collection);

	JsonArray findByInClause(List<String> in, String sort, int order, int limit, Class<?> collection);

	// TODO : jro/xke : javadoc
	JsonArray aggregate(String field, List<DBObject> pipeline, Class<?> collection);

	DB getDb();
}
