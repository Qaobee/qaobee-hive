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

package com.qaobee.hive.dao.impl;


import com.qaobee.hive.api.v1.sandbox.stats.SB_CollectVerticle;
import com.qaobee.hive.dao.CollectDAO;
import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;


/**
 * The type Collect dao.
 */
public class CollectDAOImpl implements CollectDAO {

    @Inject
    private MongoDB mongo;
    @Inject
    private NotificationsService notificationsService;

    @Override
    public void get(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.COLLECT, resultHandler);
    }

    @Override
    public void update(JsonObject collect, String currentUserId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(collect, DBCollections.COLLECT, upsertRes -> {
            if (upsertRes.succeeded()) {
                collect.put("_id", upsertRes.result());
                JsonObject notification = new JsonObject()
                        .put("content", Messages.getString("notification.collect.update.content", locale,
                                collect.getJsonObject(SB_CollectVerticle.PARAM_EVENT).getString("label")))
                        .put("title", Messages.getString("notification.collect.update.title", locale))
                        .put("senderId", currentUserId);
                notificationsService.sendNotification(collect.getJsonObject(SB_CollectVerticle.PARAM_EVENT).getJsonObject("owner").getString(SB_CollectVerticle.PARAM_SANDBOX_ID),
                        DBCollections.SANDBOX, notification, new JsonArray().add(currentUserId), ar -> {
                        });
                resultHandler.handle(Future.succeededFuture(collect));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }

    @Override
    public void add(JsonObject collect, String currentUserId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(collect, DBCollections.COLLECT, upsertRes -> {
            if (upsertRes.succeeded()) {
                collect.put("_id", upsertRes.result());
                JsonObject notification = new JsonObject()
                        .put("content", Messages.getString("notification.collect.start.content", locale, collect.getJsonObject(SB_CollectVerticle.PARAM_EVENT).getString("label")))
                        .put("title", Messages.getString("notification.collect.start.title", locale))
                        .put("senderId", currentUserId);
                notificationsService.sendNotification(collect.getJsonObject(SB_CollectVerticle.PARAM_EVENT).getJsonObject("owner").getString(SB_CollectVerticle.PARAM_SANDBOX_ID),
                        DBCollections.SANDBOX, notification, new JsonArray().add(currentUserId), ar -> {
                        });
                resultHandler.handle(Future.succeededFuture(collect));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }

    @Override
    public void getList(JsonObject params, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject dbObjectParent = new JsonObject();
        // Collecte sandboxId
        dbObjectParent.put("eventRef.owner.sandboxId", params.getString(SB_CollectVerticle.PARAM_SANDBOX_ID));
        if (params.getString(SB_CollectVerticle.PARAM_EVENT_ID) != null && !"".equals(params.getString(SB_CollectVerticle.PARAM_EVENT_ID).trim())) {
            dbObjectParent.put("eventRef._id", params.getString(SB_CollectVerticle.PARAM_EVENT_ID));
        }
        if (params.getString(SB_CollectVerticle.PARAM_EFFECTIVE_ID) != null && !"".equals(params.getString(SB_CollectVerticle.PARAM_EFFECTIVE_ID).trim())) {
            dbObjectParent.put("eventRef.owner.effectiveId", params.getString(SB_CollectVerticle.PARAM_EFFECTIVE_ID));
        }
        if (params.getString(SB_CollectVerticle.PARAM_TEAM_ID) != null && !"".equals(params.getString(SB_CollectVerticle.PARAM_TEAM_ID).trim())) {
            dbObjectParent.put("eventRef.owner.teamId", params.getString(SB_CollectVerticle.PARAM_TEAM_ID));
        }
        JsonObject o = new JsonObject();
        o.put("$gte", params.getLong(SB_CollectVerticle.PARAM_START_DATE));
        o.put("$lt", params.getLong(SB_CollectVerticle.PARAM_END_DATE));
        dbObjectParent.put("startDate", o);
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match);
        mongo.aggregate(pipelineAggregation, DBCollections.COLLECT, resultHandler);
    }
}
