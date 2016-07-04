package com.qaobee.hive.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.commons.referencial.ChampionshipVerticle;
import com.qaobee.hive.business.model.commons.referencial.ChampionShip;
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
public class ChampionshipDAOImpl implements com.qaobee.hive.dao.ChampionshipDAO {

    @Inject
    private MongoDB mongo;

    @Override
    public JsonObject updateChampionship(JsonObject championship) throws QaobeeException {
        mongo.save(championship, ChampionShip.class);
        return championship;
    }

    @Override
    public JsonObject addChampionship(JsonObject championship) throws QaobeeException {
        JsonObject res = championship;
        res.putString("_id", mongo.save(championship, ChampionShip.class));
        return res;
    }

    @Override
    public JsonObject getChampionship(String id) throws QaobeeException {
        return mongo.getById(id, ChampionShip.class);
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
        return mongo.aggregate("_id", pipelineAggregation, ChampionShip.class);
    }
}
