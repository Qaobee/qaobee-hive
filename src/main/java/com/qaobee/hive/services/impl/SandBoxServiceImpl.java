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
import com.qaobee.hive.services.SandBoxService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import io.vertx.core.*;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Sand box service.
 */
@ProxyService(address = SandBoxService.ADDRESS, iface = SandBoxService.class)
public class SandBoxServiceImpl implements SandBoxService {
    private static final String PARAM_OWNER_ID = "owner";
    private static final String PARAM_ACTIVITY_ID = "activity";
    private static final String FIELD_FIRSTNAME = "firstname";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_AVATAR = "avatar";
    private static final String FIELD_CONTACT = "contact";
    private static final String FIELD_MEMBERS = "members";
    private static final String FIELD_PERSON_ID = "personId";
    private static final String FIELD_ID = "_id";
    private static final String FIELD_COUNTRY = "country";

    @Inject
    private MongoDB mongo;

    public SandBoxServiceImpl(Vertx vertx) {
        super();
    }

    private Future<JsonObject> getPerson(JsonObject m) {
        Future<JsonObject> deferred = Future.future();
        mongo.getByIdMinimal(m.getString(FIELD_PERSON_ID), DBCollections.USER, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY), u -> {
            if (u.succeeded()) {
                m.put("person", u.result());
                deferred.complete(m);
            } else {
                deferred.fail(u.cause());
            }
        });
        return deferred;
    }

    @Override
    public void getSandboxById(String sandboxId, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(sandboxId, DBCollections.SANDBOX, result -> {
            if (result.succeeded()) {
                getEnrichedSandbox(result.result(), resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(result.cause()));
            }
        });
    }

    @Override
    public void getEnrichedSandbox(JsonObject sandbox, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonArray members = sandbox.getJsonArray(FIELD_MEMBERS);
        List<Future> promises = new ArrayList<>();
        members.forEach(m -> promises.add(getPerson((JsonObject) m)));
        CompositeFuture.all(promises).setHandler(rs -> {
            if (rs.succeeded()) {
                JsonArray enrichedMembers = new JsonArray();
                rs.result().list().forEach(enrichedMembers::add);
                sandbox.put(FIELD_MEMBERS, enrichedMembers);
                resultHandler.handle(Future.succeededFuture(sandbox));
            } else {
                resultHandler.handle(Future.failedFuture(rs.cause()));
            }
        });
    }

    @Override
    public void updateSandbox(JsonObject sandbox, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(sandbox, DBCollections.SANDBOX, upsertRes -> {
            if (upsertRes.succeeded()) {
                sandbox.put("_id", upsertRes.result());
                resultHandler.handle(Future.succeededFuture(sandbox));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }

    @Override
    public void getListByOwner(List<String> usersIds, String loggedUserId, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject cb = new JsonObject();
        if (usersIds != null && !usersIds.isEmpty() && StringUtils.isNoneBlank(usersIds.get(0))) {
            cb.put(PARAM_OWNER_ID, usersIds.get(0));
        } else {
            cb.put(PARAM_OWNER_ID, loggedUserId);
        }
        mongo.findByCriterias(cb, new CriteriaOption(), DBCollections.SANDBOX, resultJson -> {
            if (resultJson.succeeded()) {
                if (resultJson.result().size() == 0) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR,
                            "No SandBox found for user id :" + loggedUserId)));
                } else {
                    resultHandler.handle(Future.succeededFuture(resultJson.result()));
                }
            } else {
                resultHandler.handle(Future.failedFuture(resultJson.cause()));
            }
        });
    }

    @Override
    public void getByOwner(String activityId, String userId, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject cb = new JsonObject()
                .put(PARAM_OWNER_ID, userId)
                .put("structure." + PARAM_ACTIVITY_ID + "._id", activityId);
        mongo.findByCriterias(cb, new CriteriaOption(), DBCollections.SANDBOX, resultJson -> {
            if (resultJson.succeeded()) {
                if (resultJson.result().size() == 0) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR,
                            "No SandBox found for user id :" + userId + " ,and activityId : " + activityId)));
                } else {
                    resultHandler.handle(Future.succeededFuture(resultJson.result().getJsonObject(0)));
                }
            } else {
                resultHandler.handle(Future.failedFuture(resultJson.cause()));
            }
        });
    }
}
