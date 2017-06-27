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

import com.qaobee.hive.dao.SandBoxDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.jdeferred.Deferred;
import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Sand box dao.
 */
public class SandBoxDAOImpl implements SandBoxDAO {
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

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getSandboxById(String sandboxId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(sandboxId, DBCollections.SANDBOX)
                .done(result -> getEnrichedSandbox(result)
                        .done(deferred::resolve)
                        .fail(deferred::reject))
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getEnrichedSandbox(JsonObject sandbox) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        JsonArray members = sandbox.getJsonArray(FIELD_MEMBERS);
        List<Promise> promises = new ArrayList<>();
        members.forEach(m -> promises.add(getPerson(m)));
        DeferredManager dm = new DefaultDeferredManager();
        dm.when(promises.toArray(new Promise[promises.size()]))
                .done(rs -> {
                    JsonArray enrichedMembers = new JsonArray();
                    rs.forEach(r->{
                        enrichedMembers.add(r.getResult());
                    });
                    sandbox.put(FIELD_MEMBERS, enrichedMembers);
                    deferred.resolve(sandbox);
                })
                .fail(e -> deferred.reject((QaobeeException) e.getReject()));
        return deferred.promise();
    }

    private Promise getPerson(Object m) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(((JsonObject) m).getString(FIELD_PERSON_ID), DBCollections.USER, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY))
                .done(u -> {
                    ((JsonObject) m).put("person", u);
                    deferred.resolve(((JsonObject) m));
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> updateSandbox(JsonObject sandbox) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.upsert(sandbox, DBCollections.SANDBOX)
                .done(res -> {
                    sandbox.put("_id", res);
                    deferred.resolve(sandbox);
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getListByOwner(List<String> usersIds, String loggedUserId) {
        Deferred<JsonArray, QaobeeException, Integer> deferred = new DeferredObject<>();
        CriteriaBuilder cb = new CriteriaBuilder();
        if (usersIds != null && StringUtils.isNoneBlank(usersIds.get(0))) {
            cb.add(PARAM_OWNER_ID, usersIds.get(0));
        } else {
            cb.add(PARAM_OWNER_ID, loggedUserId);
        }
        mongo.findByCriterias(cb.get(), null, null, -1, -1, DBCollections.SANDBOX)
                .done(resultJson -> {
                    if (resultJson.size() == 0) {
                        deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for user id :" + loggedUserId));
                    } else {
                        deferred.resolve(resultJson);
                    }
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getByOwner(String activityId, String userId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        CriteriaBuilder cb = new CriteriaBuilder()
                .add(PARAM_OWNER_ID, userId)
                .add("structure." + PARAM_ACTIVITY_ID + "._id", activityId);
        mongo.findByCriterias(cb.get(), null, null, -1, -1, DBCollections.SANDBOX)
                .done(resultJson -> {
                    if (resultJson.size() == 0) {
                        deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for user id :" + userId
                                + " ,and activityId : " + activityId));
                    } else {
                        deferred.resolve(resultJson.getJsonObject(0));
                    }
                })
                .fail(deferred::reject);
        return deferred.promise();
    }
}
