package com.qaobee.hive.dao.impl;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.commons.communication.NotificationsVerticle;
import com.qaobee.hive.api.v1.sandbox.event.SB_EventVerticle;
import com.qaobee.hive.business.model.sandbox.agenda.SB_Event;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.dao.EventDAO;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * The type Event dao.
 */
public class EventDAOImpl implements EventDAO {

    private static final String FIELD_SANDBOX_ID = "sandboxId";
    @Inject
    private MongoDB mongo;
    @Inject
    private Vertx vertx;

    @Override
    public JsonObject getEvent(String id) throws QaobeeException {
        return mongo.getById(id, SB_Event.class);
    }

    @Override
    public JsonObject updateEvent(JsonObject event, String currentUserId, String locale) throws QaobeeException {
        mongo.save(event, SB_Event.class);
        String sandBoxId = mongo.getById(event.getObject("owner").getString(FIELD_SANDBOX_ID), SB_SandBox.class).getString(FIELD_SANDBOX_ID);
        JsonObject notification = new JsonObject();
        notification.putString("id", sandBoxId);
        notification.putString("target", SB_SandBox.class.getSimpleName());
        notification.putArray("exclude", new JsonArray().add(currentUserId));
        notification.putObject("notification", new JsonObject()
                .putString("content", Messages.getString("notification.event.update.content", locale, event.getString("label"), "/#/private/updateEvent/" + event.getString("_id")))
                .putString("title", Messages.getString("notification.event.update.title", locale))
                .putString("senderId", currentUserId)
        );
        vertx.eventBus().send(NotificationsVerticle.NOTIFY, notification);
        return event;
    }

    @Override
    public JsonObject addEvent(JsonObject event, String currentUserId, String locale) throws QaobeeException {
        event.putString("_id", mongo.save(event, SB_Event.class));
        String sandBoxId = mongo.getById(event.getObject("owner").getString(FIELD_SANDBOX_ID), SB_SandBox.class).getString(FIELD_SANDBOX_ID);
        JsonObject notification = new JsonObject();
        notification.putString("id", sandBoxId);
        notification.putString("target", SB_SandBox.class.getSimpleName());
        notification.putArray("exclude", new JsonArray().add(currentUserId));
        notification.putObject("notification", new JsonObject()
                .putString("content", Messages.getString("notification.event.add.content", locale, event.getString("label"), "/#/private/updateEvent/" + event.getString("_id")))
                .putString("title", Messages.getString("notification.event.add.title", locale))
                .putString("senderId", currentUserId)
        );
        vertx.eventBus().send(NotificationsVerticle.NOTIFY, notification);
        return event;
    }

    @Override
    public JsonArray getEventList(JsonObject params) throws QaobeeException {
        // Aggregat section
        DBObject match;
        DBObject sort;
        DBObject limit;
        BasicDBObject dbObjectChild;
        // $MACTH section
        BasicDBObject dbObjectParent = new BasicDBObject();
        // Event Activity
        dbObjectParent.put(SB_EventVerticle.PARAM_ACTIVITY_ID, params.getString(SB_EventVerticle.PARAM_ACTIVITY_ID));
        // Event sandboxId
        dbObjectParent.put("owner.sandboxId", params.getString(SB_EventVerticle.PARAM_OWNER_SANBOXID));
        if (params.getString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID) != null && !"".equals(params.getString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID).trim())) {
            dbObjectParent.put("owner.effectiveId", params.getString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID));
        }
        if (params.getString(SB_EventVerticle.PARAM_OWNER_TEAMID) != null && !"".equals(params.getString(SB_EventVerticle.PARAM_OWNER_TEAMID).trim())) {
            dbObjectParent.put("owner.teamId", params.getString(SB_EventVerticle.PARAM_OWNER_TEAMID));
        }
        DBObject o = new BasicDBObject();
        o.put("$gte", params.getLong(SB_EventVerticle.PARAM_START_DATE));
        o.put("$lt", params.getLong(SB_EventVerticle.PARAM_END_DATE));
        dbObjectParent.put("startDate", o);
        // Link.type
        if (params.containsField(SB_EventVerticle.PARAM_LINK_TYPE)) {
            dbObjectChild = new BasicDBObject("$in", params.getArray(SB_EventVerticle.PARAM_LINK_TYPE).toArray());
            dbObjectParent.put("link.type", dbObjectChild);
        }
        match = new BasicDBObject("$match", dbObjectParent);
        // $SORT section
        dbObjectParent = new BasicDBObject();
        if (params.containsField(SB_EventVerticle.PARAM_LIST_SORTBY)) {
            for (Object item : params.getArray(SB_EventVerticle.PARAM_LIST_SORTBY)) {
                JsonObject field = (JsonObject) item;
                dbObjectParent.put(field.getString("fieldName"), field.getInteger("sortOrder"));
            }
        } else {
            dbObjectParent.put("_id", 1);
        }
        sort = new BasicDBObject("$sort", dbObjectParent);
        // $limit section
        List<DBObject> pipelineAggregation;
        if (params.containsField(SB_EventVerticle.PARAM_LIMIT_RESULT)) {
            int limitNumber = params.getInteger(SB_EventVerticle.PARAM_LIMIT_RESULT);
            limit = new BasicDBObject("$limit", limitNumber);
            pipelineAggregation = Arrays.asList(match, sort, limit);
        } else {
            pipelineAggregation = Arrays.asList(match, sort);
        }
        return mongo.aggregate("_id", pipelineAggregation, SB_Event.class);
    }
}
