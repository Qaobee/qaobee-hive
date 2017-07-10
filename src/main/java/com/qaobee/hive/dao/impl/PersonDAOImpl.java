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

import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.dao.PersonDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;

/**
 * The type Person dao.
 */
public class PersonDAOImpl implements PersonDAO {

    private static final String PARAM_SANDBOX_ID = "sandboxId";

    @Inject
    private MongoDB mongo;
    @Inject
    private NotificationsService notificationsService;

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getPersonListBySandbox(String sandboxId) {
        Deferred<JsonArray, QaobeeException, Integer> deferred = new DeferredObject<>();
        CriteriaBuilder criteria = new CriteriaBuilder().add(PARAM_SANDBOX_ID, sandboxId);
        mongo.findByCriterias(criteria.get(), null, null, -1, -1, DBCollections.PERSON)
                .done(resultJson -> {
                    if (resultJson.size() == 0) {
                        deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "No person found for sandboxId (" + sandboxId + ")"));
                    } else {
                        deferred.resolve(resultJson);
                    }
                })
                .fail(deferred::reject);

        return deferred.promise();
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getPersonList(JsonArray listId, JsonArray listfield) {
        JsonObject dbObjectParent = new JsonObject();
        JsonObject dbObjectChild = new JsonObject().put("$in", listId);
        dbObjectParent.put("_id", dbObjectChild);
        JsonObject match = new JsonObject().put("$match", dbObjectParent);
        dbObjectParent = new JsonObject();
        for (Object field : listfield) {
            dbObjectParent.put((String) field, "$" + field);
        }
        JsonObject project = new JsonObject().put("$project", dbObjectParent);
        JsonArray pipelineAggregation = new JsonArray().add(match).add(project);
        return mongo.aggregate(pipelineAggregation, DBCollections.PERSON);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> updatePerson(JsonObject person, String userId, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.upsert(person, DBCollections.PERSON)
                .done(res -> {
                    person.put("_id", res);
                    deferred.resolve(person);
                    if (userId != null && locale != null) {
                        JsonObject notification = new JsonObject()
                                .put("content", Messages.getString("notification.person.update.content", locale,
                                        person.getString("firstname") + " " + person.getString("name"),
                                        "/#/private/viewPlayer/" + person.getString("_id")))
                                .put("title", Messages.getString("notification.person.update.title", locale))
                                .put("senderId", userId);
                        notificationsService.notify(person.getString(PARAM_SANDBOX_ID), DBCollections.SANDBOX, notification, new JsonArray().add(userId), null);
                    }
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getPerson(String id) {
        return mongo.getById(id, DBCollections.PERSON);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> addPerson(JsonObject person, String userId, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.upsert(person, DBCollections.PERSON)
                .done(res -> {
                    person.put("_id", res);
                    deferred.resolve(person);
                    if (userId != null && locale != null) {
                        JsonObject notification = new JsonObject()
                                .put("content", Messages.getString("notification.person.add.content", locale,
                                        person.getString("firstname") + " " + person.getString("name"),
                                        "/#/private/viewPlayer/" + person.getString("_id")))
                                .put("title", Messages.getString("notification.person.add.title", locale))
                                .put("senderId", userId);
                        notificationsService.notify(person.getString(PARAM_SANDBOX_ID), DBCollections.SANDBOX, notification, new JsonArray().add(userId), null);
                    }
                })
                .fail(deferred::reject);
        return deferred.promise();
    }
}
