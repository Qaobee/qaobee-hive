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

import com.qaobee.hive.api.v1.commons.referencial.ChampionshipRoute;
import com.qaobee.hive.services.ChampionshipService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.services.MongoDB;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Collections;

/**
 * The type Championship dao.
 */
@ProxyService(address = "vertx.Championship.service", iface = ChampionshipService.class)
public class ChampionshipServiceImpl implements ChampionshipService {

    @Inject
    private MongoDB mongo;

    /**
     * Instantiates a new Championship service.
     *
     * @param vertx the vertx
     */
    public ChampionshipServiceImpl(Vertx vertx) { // NOSONAR
        super();
    }

    @Override
    public void updateChampionship(JsonObject championship, Handler<AsyncResult<String>> resultHandler) {
        mongo.upsert(championship, DBCollections.CHAMPIONSHIP, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(res.result()));
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void addChampionship(JsonObject championship, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(championship, DBCollections.CHAMPIONSHIP, res -> {
            if (res.succeeded()) {
                championship.put("_id", res.result());
                resultHandler.handle(Future.succeededFuture(championship));
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void getChampionship(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.CHAMPIONSHIP, resultHandler);
    }

    @Override
    public void getListChampionships(JsonObject params, Handler<AsyncResult<JsonArray>> resultHandler) {
        // Aggregat section
        //$MACTH section
        JsonObject dbObjectParent = new JsonObject();
        // Activity ID
        dbObjectParent.put("activityId", params.getString(ChampionshipRoute.PARAM_ACTIVITY));
        // Category Age Code
        dbObjectParent.put("categoryAge.code", params.getString(ChampionshipRoute.PARAM_CATEGORY_AGE));
        // Structure ID
        dbObjectParent.put("participants.structureId", params.getString(ChampionshipRoute.PARAM_STRUCTURE));
        // Participant
        if (params.containsKey(ChampionshipRoute.PARAM_PARTICIPANT)) {
            JsonObject mapParticipant = params.getJsonObject(ChampionshipRoute.PARAM_PARTICIPANT);
            JsonObject dbObjectChild = new JsonObject();
            if (mapParticipant.containsKey("id")) {
                dbObjectChild.put("participants.id", mapParticipant.getString("id"));
            }
            if (mapParticipant.containsKey("structureId")) {
                dbObjectChild.put("participants.structureId", mapParticipant.getString("structureId"));
            }
            if (mapParticipant.containsKey("name")) {
                dbObjectChild.put("participants.name", mapParticipant.getString("name"));
            }
            if (mapParticipant.containsKey("type")) {
                dbObjectChild.put("participants.type", mapParticipant.getString("type"));
            }
            dbObjectParent.put("$and", Collections.singletonList(dbObjectChild));
        }
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match);
        mongo.aggregate(pipelineAggregation, DBCollections.CHAMPIONSHIP, resultHandler);
    }
}
