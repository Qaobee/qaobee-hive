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
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.apache.commons.lang3.StringUtils;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.List;

/**
 * The type Sand box dao.
 */
public class SandBoxDAOImpl implements SandBoxDAO {
    private static final String COLLECTION = "SB_SandBox";
    private static final String PARAM_OWNER_ID = "owner";
    private  static final String PARAM_ACTIVITY_ID = "activity";

    @Inject
    private MongoDB mongo;

    @Override
    public JsonObject updateSandboxCfgId(String id, String sandboxCfgId) throws QaobeeException {
        final JsonObject sandbox = mongo.getById(id, COLLECTION);
        if (sandbox == null) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for id :" + id);
        }
        sandbox.putString("sandboxCfgId", sandboxCfgId);
        mongo.save(sandbox, COLLECTION);
        return sandbox;
    }

    @Override
    public JsonObject add(String activityId, String userId) throws QaobeeException {
        JsonObject sandbox = new JsonObject()
                .putString("activityId", activityId)
                .putString("owner", userId);
        sandbox.putString("_id", mongo.save(sandbox, COLLECTION));
        return sandbox;
    }

    @Override
    public JsonArray getListByOwner(List<String> usersIds, String loggedUserId) throws QaobeeException {
        CriteriaBuilder cb = new CriteriaBuilder();
        if (usersIds != null && !usersIds.isEmpty() && StringUtils.isNoneBlank(usersIds.get(0))) {
            cb.add(PARAM_OWNER_ID, usersIds.get(0));
        } else {
            cb.add(PARAM_OWNER_ID, loggedUserId);
        }
        JsonArray resultJson = mongo.findByCriterias(cb.get(), null, null, -1, -1, COLLECTION);
        if (resultJson == null || resultJson.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for user id :" + loggedUserId);
        }
        return resultJson;
    }

    @Override
    public JsonObject getByOwner(String activityId, String userId) throws QaobeeException {
        CriteriaBuilder cb = new CriteriaBuilder()
                .add(PARAM_OWNER_ID, userId)
                .add("structure." + PARAM_ACTIVITY_ID + "._id", activityId);
        JsonArray resultJson = mongo.findByCriterias(cb.get(), null, null, -1, -1, COLLECTION);
        if (resultJson == null || resultJson.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for user id :" + userId
                    + " ,and activityId : " + activityId);
        }
        return resultJson.get(0);
    }

    @Override
    public JsonObject update(JsonObject sandbox) {
        return sandbox.putString("_id", mongo.update(sandbox, COLLECTION));
    }
}
