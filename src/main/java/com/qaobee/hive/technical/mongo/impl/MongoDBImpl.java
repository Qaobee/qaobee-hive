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
package com.qaobee.hive.technical.mongo.impl;

import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClient;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MongoDBImpl implements MongoDB {
    @Inject
    MongoClient mongoClient;

    @Override
    public void upsert(final Object o, Handler<AsyncResult<String>> resultHandler) {
        upsert(new JsonObject(Json.encode(o)), o.getClass().getSimpleName(), resultHandler);
    }

    @Override
    public void upsert(JsonObject document, String collection, Handler<AsyncResult<String>> resultHandler) {
        upsert(new JsonObject().put("_id", document.getString("_id")), document, collection, resultHandler);
    }

    @Override
    public void upsert(JsonObject query, JsonObject document, Class<?> collection, Handler<AsyncResult<String>> resultHandler) {
        upsert(query, document, collection.getClass().getSimpleName(), resultHandler);
    }

    @Override
    public void upsert(JsonObject query, JsonObject document, String collection, Handler<AsyncResult<String>> resultHandler) {
        if (document.containsKey("_id")) {
            mongoClient.updateCollection(collection, query, document, res -> {
                if (res.succeeded()) {
                    resultHandler.handle(Future.succeededFuture(document.getString("_id")));
                } else {
                    resultHandler.handle(Future.failedFuture(res.cause()));
                }
            });
        } else {
            mongoClient.insert(collection, document, res -> {
                if (res.succeeded()) {
                    resultHandler.handle(Future.succeededFuture(res.result()));
                } else {
                    resultHandler.handle(Future.failedFuture(res.cause()));
                }
            });
        }
    }

    @Override
    public void getById(String id, String collection, Handler<AsyncResult<JsonObject>> resultHandler) {
        getById(id, collection, null, resultHandler);
    }

    @Override
    public void getById(String id, String collection, List<String> minimal, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject query = new JsonObject().put("_id", id);
        JsonObject mini = null;
        if (minimal != null) {
            mini = getMinimal(minimal);
        }
        mongoClient.findOne(collection, query, mini, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(res.result()));
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public JsonObject getMinimal(final List<String> minimal) {
        final JsonObject map = new JsonObject();
        for (final String key : minimal) {
            map.put(key, Boolean.TRUE);
        }
        return map;
    }

    @Override
    public void findByCriterias(Map<String, Object> criteria, List<String> fields, String sort, int order, int limit, String collection, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject query = new JsonObject();
        if (criteria != null) {
            final JsonArray and = new JsonArray();
            criteria.keySet().forEach(k -> {
                if (criteria.get(k) instanceof String && ((String) criteria.get(k)).startsWith("//")) {
                    and.add(new JsonObject().put(k, Pattern.compile(((String) criteria.get(k)).substring(2))));
                } else {
                    and.add(new JsonObject().put(k, criteria.get(k)));
                }
            });
            query = new JsonObject().put("$and", and);
        }
        FindOptions options = new FindOptions();
        if (fields != null) {
            options.setFields(getMinimal(fields));
        }
        if (limit > 0) {
            options.setLimit(limit);
        }
        if (sort != null) {
            options.setSort(new JsonObject().put(sort, order));
        }
        mongoClient.findWithOptions(collection, query, options, res -> {
            if (res.succeeded()) {
                final JsonArray json = new JsonArray();
                res.result().forEach(json::add);
                resultHandler.handle(Future.succeededFuture(json));
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void findAll(List<String> fields, String sort, int order, int limit, String collection, Handler<AsyncResult<JsonArray>> resultHandler) {
        findByCriterias(null, fields, sort, order, limit, collection, resultHandler);
    }
}
