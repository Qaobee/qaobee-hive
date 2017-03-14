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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * The type Sand box dao.
 */
public class SandBoxDAOImpl implements SandBoxDAO {
    private static final Logger LOG = LoggerFactory.getLogger(SandBoxDAOImpl.class);
    private static final String PARAM_OWNER_ID = "owner";
    private  static final String PARAM_ACTIVITY_ID = "activity";
    private static final String FIELD_FIRSTNAME = "firstname";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_AVATAR = "avatar";
    private static final String FIELD_CONTACT = "contact";
    private static final String FIELD_MEMBERS = "members";
    private static final String FIELD_PERSON_ID = "personId";
    private static final String FIELD_ID = "_id";
    private static final String FIELD_COUNTRY = "country";

    private final MongoDB mongo;

    /**
     * Instantiates a new Sand box dao.
     *
     * @param mongo the mongo
     */
    @Inject
    public SandBoxDAOImpl(MongoDB mongo) {
        this.mongo = mongo;
    }

    @Override
    public JsonObject getSandboxById(String sandboxId) throws QaobeeException {
        return getEnrichedSandbox(mongo.getById(sandboxId, DBCollections.SANDBOX));
    }

    @Override
    public JsonObject getEnrichedSandbox(JsonObject sandbox) {
        JsonArray members = sandbox.getArray(FIELD_MEMBERS);
        members.forEach(m -> {
            try {
                ((JsonObject) m).putObject("person", mongo.getById(((JsonObject) m).getString(FIELD_PERSON_ID), DBCollections.USER, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
        });
        sandbox.putArray(FIELD_MEMBERS, members);
        return sandbox;
    }

    @Override
    public JsonObject updateSandbox(JsonObject sandbox) {
        sandbox.putString("_id", mongo.update(sandbox, DBCollections.SANDBOX));
        return sandbox;
    }

    @Override
    public JsonArray getListByOwner(List<String> usersIds, String loggedUserId) throws QaobeeException {
        CriteriaBuilder cb = new CriteriaBuilder();
        if (usersIds != null && StringUtils.isNoneBlank(usersIds.get(0))) {
            cb.add(PARAM_OWNER_ID, usersIds.get(0));
        } else {
            cb.add(PARAM_OWNER_ID, loggedUserId);
        }
        JsonArray resultJson = mongo.findByCriterias(cb.get(), null, null, -1, -1, DBCollections.SANDBOX);
        if (resultJson.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for user id :" + loggedUserId);
        }
        return resultJson;
    }

    @Override
    public JsonObject getByOwner(String activityId, String userId) throws QaobeeException {
        CriteriaBuilder cb = new CriteriaBuilder()
                .add(PARAM_OWNER_ID, userId)
                .add("structure." + PARAM_ACTIVITY_ID + "._id", activityId);
        JsonArray resultJson = mongo.findByCriterias(cb.get(), null, null, -1, -1, DBCollections.SANDBOX);
        if (resultJson.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for user id :" + userId
                    + " ,and activityId : " + activityId);
        }
        return resultJson.get(0);
    }
}
