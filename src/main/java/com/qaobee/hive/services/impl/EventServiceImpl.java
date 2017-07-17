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

import com.qaobee.hive.api.v1.sandbox.event.SB_EventRoute;
import com.qaobee.hive.services.EventService;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.tools.Messages;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Event service.
 */
@ProxyService(address = "vertx.Event.service", iface = EventService.class)
public class EventServiceImpl implements EventService {
    private static final String FIELD_SANDBOX_ID = "sandboxId";
    @Inject
    private MongoDB mongo;
    @Inject
    private NotificationsService notificationsService;

    /**
     * Instantiates a new Event service.
     *
     * @param vertx the vertx
     */
    public EventServiceImpl(Vertx vertx) { // NOSONAR
        super();
    }
    @Override
    public void getEvent(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.EVENT, resultHandler);
    }

    @Override
    public void updateEvent(JsonObject event, String currentUserId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(event, DBCollections.EVENT, upsertRes -> {
            if (upsertRes.succeeded()) {
                event.put("_id", upsertRes.result());
                mongo.getById(event.getJsonObject("owner").getString(FIELD_SANDBOX_ID), DBCollections.SANDBOX, sandbox -> {
                    if (sandbox.succeeded()) {
                        notificationsService.sendNotification(sandbox.result().getString("_id"), DBCollections.SANDBOX, new JsonObject()
                                .put("content", Messages.getString("notification.event.update.content", locale, event.getString("label"), "/#/private/updateEvent/" + event.getString("_id")))
                                .put("title", Messages.getString("notification.event.update.title", locale))
                                .put("senderId", currentUserId), new JsonArray().add(currentUserId), ar -> {
                            // empty
                        });
                    }
                });
                resultHandler.handle(Future.succeededFuture(event));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }

    @Override
    public void addEvent(JsonObject event, String currentUserId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(event, DBCollections.EVENT, upsertRes -> {
            if (upsertRes.succeeded()) {
                event.put("_id", upsertRes.result());
                mongo.getById(event.getJsonObject("owner").getString(FIELD_SANDBOX_ID), DBCollections.SANDBOX, sandbox -> {
                    if (sandbox.succeeded()) {
                        notificationsService.sendNotification(sandbox.result().getString("_id"), DBCollections.SANDBOX, new JsonObject()
                                .put("content", Messages.getString("notification.event.add.content", locale, event.getString("label"), "/#/private/updateEvent/" + event.getString("_id")))
                                .put("title", Messages.getString("notification.event.add.title", locale))
                                .put("senderId", currentUserId), new JsonArray().add(currentUserId), ar -> {
                            // empty
                        });
                    }
                });

                resultHandler.handle(Future.succeededFuture(event));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }

    @Override
    public void getEventList(JsonObject params, Handler<AsyncResult<JsonArray>> resultHandler) {
        // Aggregate section
        // $MACTH section
        JsonObject dbObjectChild = new JsonObject();
        JsonObject dbObjectParent = new JsonObject();
        // Event Activity
        dbObjectParent.put(SB_EventRoute.PARAM_ACTIVITY_ID, params.getString(SB_EventRoute.PARAM_ACTIVITY_ID));
        // Event sandboxId
        dbObjectParent.put("owner.sandboxId", params.getString(SB_EventRoute.PARAM_OWNER_SANBOXID));
        if (params.getString(SB_EventRoute.PARAM_OWNER_EFFECTIVEID) != null && !"".equals(params.getString(SB_EventRoute.PARAM_OWNER_EFFECTIVEID).trim())) {
            dbObjectParent.put("owner.effectiveId", params.getString(SB_EventRoute.PARAM_OWNER_EFFECTIVEID));
        }
        if (params.getString(SB_EventRoute.PARAM_OWNER_TEAMID) != null && !"".equals(params.getString(SB_EventRoute.PARAM_OWNER_TEAMID).trim())) {
            dbObjectParent.put("owner.teamId", params.getString(SB_EventRoute.PARAM_OWNER_TEAMID));
        }
        JsonObject o = new JsonObject()
                .put("$gte", params.getLong(SB_EventRoute.PARAM_START_DATE))
                .put("$lt", params.getLong(SB_EventRoute.PARAM_END_DATE));
        dbObjectParent.put("startDate", o);
        // Link.type
        if (params.containsKey(SB_EventRoute.PARAM_LINK_TYPE)) {
            dbObjectChild.put("$in", params.getJsonArray(SB_EventRoute.PARAM_LINK_TYPE));
            dbObjectParent.put("link.type", dbObjectChild);
        }
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        JsonObject sortVal = new JsonObject();
        // $SORT section
        if (params.containsKey(SB_EventRoute.PARAM_LIST_SORTBY)) {
            for (Object item : params.getJsonArray(SB_EventRoute.PARAM_LIST_SORTBY)) {
                JsonObject field = (JsonObject) item;
                sortVal.put(field.getString("fieldName"), field.getInteger("sortOrder"));
            }
        } else {
            sortVal.put("_id", 1);
        }
        JsonObject sort = new JsonObject().put("$sort", sortVal);
        JsonArray pipelineAggregation = new JsonArray().add(match).add(sort);
        // $limit section
        if (params.containsKey(SB_EventRoute.PARAM_LIMIT_RESULT)) {
            int limitNumber = params.getInteger(SB_EventRoute.PARAM_LIMIT_RESULT);
            JsonObject limit = new JsonObject().put("$limit", limitNumber);
            pipelineAggregation.add(limit);
        }
        mongo.aggregate(pipelineAggregation, DBCollections.EVENT, resultHandler);
    }
}
