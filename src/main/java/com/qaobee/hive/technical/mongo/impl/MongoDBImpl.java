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

import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MongoDBImpl implements MongoDB {
    @Inject
    private MongoClientCustom mongoClient;

    @Override
    public Promise<String, QaobeeException, Integer> upsert(final Object o) {
        return upsert(new JsonObject(Json.encode(o)), o.getClass().getSimpleName());
    }

    @Override
    public Promise<String, QaobeeException, Integer> upsert(JsonObject document, String collection) {
        return upsert(new JsonObject().put("_id", document.getString("_id")), document, collection);
    }

    @Override
    public Promise<String, QaobeeException, Integer> upsert(JsonObject query, JsonObject document, Class<?> collection) {
        return upsert(query, document, collection.getClass().getSimpleName());
    }

    @Override
    public Promise<String, QaobeeException, Integer> upsert(JsonObject query, JsonObject document, String collection) {
        Deferred<String, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (document.containsKey("_id")) {
            mongoClient.findOneAndReplace(collection, query, document, res -> {
                if (res.succeeded()) {
                    deferred.resolve(document.getString("_id"));
                } else {
                    deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause().getMessage()));
                }
            });
        } else {
            mongoClient.insert(collection, document, res -> {
                if (res.succeeded()) {
                    deferred.resolve(res.result());
                } else {
                    deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause().getMessage()));
                }
            });
        }
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getById(String id, String collection) {
        return getById(id, collection, null);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getById(String id, String collection, List<String> minimal) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        JsonObject query = new JsonObject().put("_id", id);
        JsonObject mini = null;
        if (minimal != null) {
            mini = getMinimal(minimal);
        }
        mongoClient.findOne(collection, query, mini, res -> {
            if (res.succeeded()) {
                deferred.resolve(res.result());
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause().getMessage()));
            }
        });
        return deferred.promise();
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
    public Promise<JsonArray, QaobeeException, Integer> findByCriterias(Map<String, Object> criteria, List<String> fields, String sort, int order, int limit, String collection) {
        Deferred<JsonArray, QaobeeException, Integer> deferred = new DeferredObject<>();
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
                deferred.resolve(json);
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause().getMessage()));
            }
        });
        return deferred.promise();
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> findAll(List<String> fields, String sort, int order, int limit, String collection) {
        return findByCriterias(null, fields, sort, order, limit, collection);
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> aggregate(String id, JsonArray pipelineAggregation, String collection) {
        Deferred<JsonArray, QaobeeException, Integer> deferred = new DeferredObject<>();
        JsonObject command = new JsonObject()
                .put("aggregate", collection)
                .put("pipeline", pipelineAggregation);
        mongoClient.runCommand("aggregate", command, res -> {
            if (res.succeeded()) {
                deferred.resolve(res.result().getJsonArray("result"));
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause()));
            }
        });
        return deferred.promise();
    }
}
