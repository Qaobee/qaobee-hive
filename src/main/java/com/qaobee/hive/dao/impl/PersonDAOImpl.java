/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.dao.NotificationsDAO;
import com.qaobee.hive.dao.PersonDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * The type Person dao.
 */
public class PersonDAOImpl implements PersonDAO {

    private static final String PARAM_SANDBOX_ID = "sandboxId";

    @Inject
    private MongoDB mongo;
    @Inject
    private NotificationsDAO notificationsDAO;

    @Override
    public JsonArray getPersonListBySandbox(String sandboxId) throws QaobeeException {
        CriteriaBuilder criteria = new CriteriaBuilder().add(PARAM_SANDBOX_ID, sandboxId);
        JsonArray resultJson = mongo.findByCriterias(criteria.get(), null, null, -1, -1, DBCollections.PERSON);
        if (resultJson.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No person found for sandboxId (" + sandboxId + ")");
        }
        return resultJson;
    }

    @Override
    public JsonArray getPersonList(JsonArray listId, JsonArray listfield) throws QaobeeException {
        BasicDBObject dbObjectParent = new BasicDBObject();
        BasicDBObject dbObjectChild = new BasicDBObject("$in", listId.toArray());
        dbObjectParent.put("_id", dbObjectChild);
        DBObject match = new BasicDBObject("$match", dbObjectParent);
        dbObjectParent = new BasicDBObject();
        for (Object field : listfield) {
            dbObjectParent.put((String) field, "$" + field);
        }
        DBObject project = new BasicDBObject("$project", dbObjectParent);
        List<DBObject> pipelineAggregation = Arrays.asList(match, project);
        return mongo.aggregate(null, pipelineAggregation, DBCollections.PERSON);
    }

    @Override
    public JsonObject updatePerson(JsonObject person, String userId, String locale) {
        person.putString("_id", mongo.update(person, DBCollections.PERSON));
        if(userId != null && locale != null) {
            JsonObject notification = new JsonObject()
                    .putString("content", Messages.getString("notification.person.update.content", locale,
                            person.getString("firstname") + " " + person.getString("name"),
                            "/#/private/viewPlayer/" + person.getString("_id")))
                    .putString("title", Messages.getString("notification.person.update.title", locale))
                    .putString("senderId", userId);
            notificationsDAO.notify(person.getString("sandboxId"), DBCollections.SANDBOX, notification, new JsonArray().add(userId));
        }
        return person;
    }

    @Override
    public JsonObject getPerson(String id) throws QaobeeException {
        return mongo.getById(id, DBCollections.PERSON);
    }

    @Override
    public JsonObject addPerson(JsonObject person, String userId, String locale) throws QaobeeException {
        person.putString("_id", mongo.save(person, DBCollections.PERSON));
        if(userId != null && locale != null) {
            JsonObject notification = new JsonObject()
                    .putString("content", Messages.getString("notification.person.add.content", locale,
                            person.getString("firstname") + " " + person.getString("name"),
                            "/#/private/viewPlayer/" + person.getString("_id")))
                    .putString("title", Messages.getString("notification.person.add.title", locale))
                    .putString("senderId", userId);
            notificationsDAO.notify(person.getString("sandboxId"), DBCollections.SANDBOX, notification, new JsonArray().add(userId));
        }
        return person;
    }
}
