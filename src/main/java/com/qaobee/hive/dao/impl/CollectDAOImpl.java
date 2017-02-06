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
import com.qaobee.hive.api.v1.sandbox.stats.SB_CollectVerticle;
import com.qaobee.hive.dao.CollectDAO;
import com.qaobee.hive.dao.NotificationsDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;


/**
 * The type Collect dao.
 */
public class CollectDAOImpl implements CollectDAO {

    @Inject
    private MongoDB mongo;
    @Inject
    private Vertx vertx;
    @Inject
    private NotificationsDAO notificationsDAO;

    @Override
    public JsonObject get(String id) throws QaobeeException {
        return mongo.getById(id, DBCollections.COLLECT);
    }

    @Override
    public JsonObject update(JsonObject collect, String currentUserId, String locale) {
        collect.putString("_id", mongo.update(collect, DBCollections.COLLECT));
        JsonObject notification = new JsonObject()
                .putString("content", Messages.getString("notification.collect.update.content", locale,
                        collect.getObject(SB_CollectVerticle.PARAM_EVENT).getString("label")))
                .putString("title", Messages.getString("notification.collect.update.title", locale))
                .putString("senderId", currentUserId);
        notificationsDAO.notify(collect.getObject(SB_CollectVerticle.PARAM_EVENT).getObject("owner").getString(SB_CollectVerticle.PARAM_SANDBOX_ID),
                DBCollections.SANDBOX, notification, new JsonArray().add(currentUserId));
        return collect;
    }

    @Override
    public JsonObject add(JsonObject collect, String currentUserId, String locale) throws QaobeeException {
        collect.putString("_id", mongo.save(collect, DBCollections.COLLECT));
        JsonObject notification = new JsonObject()
                .putString("content", Messages.getString("notification.collect.start.content", locale, collect.getObject(SB_CollectVerticle.PARAM_EVENT).getString("label")))
                .putString("title", Messages.getString("notification.collect.start.title", locale))
                .putString("senderId", currentUserId);
        notificationsDAO.notify(collect.getObject(SB_CollectVerticle.PARAM_EVENT).getObject("owner").getString(SB_CollectVerticle.PARAM_SANDBOX_ID),
                DBCollections.SANDBOX, notification, new JsonArray().add(currentUserId));
        return collect;
    }

    @Override
    public JsonArray getList(JsonObject params) throws QaobeeException {
        DBObject match;
        BasicDBObject dbObjectParent;
        dbObjectParent = new BasicDBObject();
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
        DBObject o = new BasicDBObject();
        o.put("$gte", params.getLong(SB_CollectVerticle.PARAM_START_DATE));
        o.put("$lt", params.getLong(SB_CollectVerticle.PARAM_END_DATE));
        dbObjectParent.put("startDate", o);
        match = new BasicDBObject("$match", dbObjectParent);
        List<DBObject> pipelineAggregation;
        pipelineAggregation = Collections.singletonList(match);
        return mongo.aggregate("_id", pipelineAggregation, DBCollections.COLLECT);
    }
}
