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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.commons.referencial.ChampionshipVerticle;
import com.qaobee.hive.dao.ChampionshipDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The type Championship dao.
 */
public class ChampionshipDAOImpl implements ChampionshipDAO {

    private final MongoDB mongo;

    /**
     * Instantiates a new Championship dao.
     *
     * @param mongo the mongo
     */
    @Inject
    public ChampionshipDAOImpl(MongoDB mongo) {
        this.mongo = mongo;
    }

    @Override
    public JsonObject updateChampionship(JsonObject championship) throws QaobeeException {
        mongo.save(championship, DBCollections.CHAMPIONSHIP);
        return championship;
    }

    @Override
    public JsonObject addChampionship(JsonObject championship) throws QaobeeException {
        championship.putString("_id", mongo.save(championship, DBCollections.CHAMPIONSHIP));
        return championship;
    }

    @Override
    public JsonObject getChampionship(String id) throws QaobeeException {
        return mongo.getById(id, DBCollections.CHAMPIONSHIP);
    }

    @Override
    public JsonArray getListChampionships(JsonObject params) throws QaobeeException {
        Map<String, Object> mapParams = params.toMap();
        // Aggregat section
        DBObject match;
        BasicDBObject dbObjectParent;
        BasicDBObject dbObjectChild;
        //$MACTH section
        dbObjectParent = new BasicDBObject();
        // Activity ID
        dbObjectParent.put("activityId", mapParams.get(ChampionshipVerticle.PARAM_ACTIVITY));
        // Category Age Code
        dbObjectParent.put("categoryAge.code", mapParams.get(ChampionshipVerticle.PARAM_CATEGORY_AGE));
        // Structure ID
        dbObjectParent.put("participants.structureId", mapParams.get(ChampionshipVerticle.PARAM_STRUCTURE));
        // Participant
        if (mapParams.containsKey(ChampionshipVerticle.PARAM_PARTICIPANT)) {
            @SuppressWarnings("unchecked") Map<String, Object> mapParticipant = (Map<String, Object>) mapParams.get(ChampionshipVerticle.PARAM_PARTICIPANT);
            dbObjectChild = new BasicDBObject();
            if (mapParticipant.containsKey("id")) {
                dbObjectChild.put("participants.id", mapParticipant.get("id"));
            }
            if (mapParticipant.containsKey("structureId")) {
                dbObjectChild.put("participants.structureId", mapParticipant.get("structureId"));
            }
            if (mapParticipant.containsKey("name")) {
                dbObjectChild.put("participants.name", mapParticipant.get("name"));
            }
            if (mapParticipant.containsKey("type")) {
                dbObjectChild.put("participants.type", mapParticipant.get("type"));
            }
            dbObjectParent.put("$and", Collections.singletonList(dbObjectChild));
        }
        match = new BasicDBObject("$match", dbObjectParent);
        // Pipeline
        List<DBObject> pipelineAggregation = Collections.singletonList(match);
        return mongo.aggregate("_id", pipelineAggregation, DBCollections.CHAMPIONSHIP);
    }
}
