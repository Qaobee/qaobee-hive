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

import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.WriteOption;

import javax.inject.Inject;
import java.util.List;

/**
 * The type Mongo db.
 */
@ProxyService(address = MongoDB.ADDRESS, iface = MongoDB.class)
public class MongoDBImpl implements MongoDB {
    @Inject
    private MongoClientCustom mongoClient;
    @Inject
    private Utils utils;

    /**
     * Instantiates a new Mongo db.
     *
     * @param vertx the vertx
     */
    public MongoDBImpl(Vertx vertx) {
        super();
    }

    @Override
    public void upsert(JsonObject document, String collection, Handler<AsyncResult<String>> resultHandler) {
        upsertWithQuery(new JsonObject().put("_id", document.getString("_id")), document, collection, resultHandler);
    }

    @Override
    public void upsertWithQuery(JsonObject query, JsonObject document, String collection, Handler<AsyncResult<String>> resultHandler) {
        if (document.containsKey("_id") && document.getString("_id") != null) {
            mongoClient.saveWithOptions(collection, document, WriteOption.ACKNOWLEDGED, res -> {
                if (res.succeeded()) {
                    resultHandler.handle(Future.succeededFuture(document.getString("_id")));
                } else {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause().getMessage())));
                }
            });
        } else {
            document.remove("_id");
            mongoClient.insert(collection, document, res -> {
                if (res.succeeded()) {
                    resultHandler.handle(Future.succeededFuture(res.result()));
                } else {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause().getMessage())));
                }
            });
        }
    }

    @Override
    public void getById(String id, String collection, Handler<AsyncResult<JsonObject>> resultHandler) {
        getByIdMinimal(id, collection, null, resultHandler);
    }

    @Override
    public void getByIdMinimal(String id, String collection, List<String> minimal, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject query = new JsonObject().put("_id", id);
        JsonObject mini = null;
        if (minimal != null) {
            mini = utils.getMinimal(minimal);
        }
        mongoClient.findOne(collection, query, mini, res -> {
            if (res.succeeded()) {
                if (res.result() != null) {
                    resultHandler.handle(Future.succeededFuture(res.result()));
                } else {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, "no data found")));
                }
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause().getMessage())));
            }
        });
    }

    @Override
    public void findByCriterias(JsonObject criteria, CriteriaOption criteriaOption, String collection, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject query = new JsonObject();
        if (criteria.size() > 0) {
            final JsonArray and = new JsonArray();
            criteria.fieldNames().forEach(K -> {
                if (criteria.getValue(K) instanceof String && ((String) criteria.getValue(K)).startsWith("//")) {
                    and.add(new JsonObject().put(K, new JsonObject().put("$regex", ((String) criteria.getValue(K)).substring(2)).put("$options", "i")));
                } else {
                    and.add(new JsonObject().put(K, criteria.getValue(K)));
                }
            });
            query = new JsonObject().put("$and", and);
        }
        FindOptions options = new FindOptions();
        if (criteriaOption.toJson().containsKey("fields")) {
            final JsonObject map = new JsonObject();
            for (Object key : criteriaOption.toJson().getJsonArray("fields")) {
                map.put((String) key, Boolean.TRUE);
            }
            options.setFields(map);
        }
        if (criteriaOption.toJson().containsKey("limit")) {
            options.setLimit(criteriaOption.toJson().getInteger("limit"));
        }
        if (criteriaOption.toJson().containsKey("sort")) {
            options.setSort(new JsonObject().put(criteriaOption.toJson().getString("sort"), criteriaOption.toJson().getInteger("order", -1)));
        }
        mongoClient.findWithOptions(collection, query, options, res -> {
            if (res.succeeded()) {
                final JsonArray json = new JsonArray();
                res.result().forEach(json::add);
                resultHandler.handle(Future.succeededFuture(json));
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause().getMessage())));
            }
        });
    }

    @Override
    public void findAll(CriteriaOption criteriaOption, String collection, Handler<AsyncResult<JsonArray>> resultHandler) {
        findByCriterias(new JsonObject(), criteriaOption, collection, resultHandler);
    }

    @Override
    public void aggregate(JsonArray pipelineAggregation, String collection, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject command = new JsonObject()
                .put("aggregate", collection)
                .put("pipeline", pipelineAggregation);
        mongoClient.runCommand("aggregate", command, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(res.result().getJsonArray("result")));
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause())));
            }
        });
    }
}
