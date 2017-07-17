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

package com.qaobee.hive.services.impl;

import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.services.PersonService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import com.qaobee.hive.technical.tools.Messages;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Person service.
 */
@ProxyService(address = "vertx.Person.service", iface = PersonService.class)
public class PersonServiceImpl implements PersonService {

    private static final String PARAM_SANDBOX_ID = "sandboxId";

    @Inject
    private MongoDB mongo;
    @Inject
    private NotificationsService notificationsService;

    /**
     * Instantiates a new Person service.
     *
     * @param vertx the vertx
     */
    public PersonServiceImpl(Vertx vertx) { // NOSONAR
        super();
    }
    
    @Override
    public void getPersonListBySandbox(String sandboxId, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject criteria = new JsonObject().put(PARAM_SANDBOX_ID, sandboxId);
        mongo.findByCriterias(criteria, new CriteriaOption(), DBCollections.PERSON, resultJson -> {
            if (resultJson.succeeded()) {
                if (resultJson.result().size() == 0) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, "No person found for sandboxId (" + sandboxId + ")")));
                } else {
                    resultHandler.handle(Future.succeededFuture(resultJson.result()));
                }
            } else {
                resultHandler.handle(Future.failedFuture(resultJson.cause()));
            }
        });
    }

    @Override
    public void getPersonList(JsonArray listId, JsonArray listfield, Handler<AsyncResult<JsonArray>> resultHandler) {
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
        mongo.aggregate(pipelineAggregation, DBCollections.PERSON, resultHandler);
    }

    @Override
    public void updatePerson(JsonObject person, String userId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(person, DBCollections.PERSON, upsertRes -> {
            if (upsertRes.succeeded()) {
                person.put("_id", upsertRes.result());
                if (userId != null && locale != null) {
                    JsonObject notification = new JsonObject()
                            .put("content", Messages.getString("notification.person.update.content", locale,
                                    person.getString("firstname") + " " + person.getString("name"),
                                    "/#/private/viewPlayer/" + person.getString("_id")))
                            .put("title", Messages.getString("notification.person.update.title", locale))
                            .put("senderId", userId);
                    notificationsService.sendNotification(person.getString(PARAM_SANDBOX_ID), DBCollections.SANDBOX, notification, new JsonArray().add(userId), ar -> {
                        // empty
                    });
                }
                resultHandler.handle(Future.succeededFuture(person));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }

    @Override
    public void getPerson(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.PERSON, resultHandler);
    }

    @Override
    public void addPerson(JsonObject person, String userId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(person, DBCollections.PERSON, upsertRes -> {
            if (upsertRes.succeeded()) {
                person.put("_id", upsertRes.result());
                if (userId != null && locale != null) {
                    JsonObject notification = new JsonObject()
                            .put("content", Messages.getString("notification.person.add.content", locale,
                                    person.getString("firstname") + " " + person.getString("name"),
                                    "/#/private/viewPlayer/" + person.getString("_id")))
                            .put("title", Messages.getString("notification.person.add.title", locale))
                            .put("senderId", userId);
                    notificationsService.sendNotification(person.getString(PARAM_SANDBOX_ID), DBCollections.SANDBOX, notification, new JsonArray().add(userId), ar -> {
                        // empty
                    });
                }
                resultHandler.handle(Future.succeededFuture(person));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }
}
