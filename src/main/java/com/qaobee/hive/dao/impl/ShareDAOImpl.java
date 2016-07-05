package com.qaobee.hive.dao.impl;

import com.mongodb.BasicDBObject;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.dao.ActivityCfgDAO;
import com.qaobee.hive.dao.ShareDAO;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * The type Share dao.
 */
public class ShareDAOImpl implements ShareDAO {
    private static final Logger LOG = LoggerFactory.getLogger(ShareDAOImpl.class);
    private static final String FIELD_ID = "_id";
    private static final String FIELD_OWNER = "owner";
    private static final String FIELD_MEMBERS = "members";
    private static final String FIELD_FIRSTNAME = "firstname";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_AVATAR = "avatar";
    private static final String FIELD_CONTACT = "contact";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_PERSON_ID = "personId";

    @Inject
    private MongoDB mongo;
    @Inject
    private ActivityCfgDAO activityCfgDAO;

    @Override
    public JsonObject getSandboxSharing(String sandboxId) throws QaobeeException {
        return getEnrichedSandbox(mongo.getById(sandboxId, SB_SandBox.class));
    }

    @Override
    public JsonObject getEnrichedSandbox(JsonObject sandbox) throws QaobeeException {
        JsonArray members = new JsonArray();
        sandbox.getArray(FIELD_MEMBERS).forEach(m -> {
            try {
                members.add(mongo.getById(((JsonObject) m).getString(FIELD_PERSON_ID), User.class, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
        });
        sandbox.putArray(FIELD_MEMBERS, members);
        sandbox.putObject(FIELD_OWNER, mongo.getById(sandbox.getString(FIELD_OWNER), User.class, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
        return sandbox;
    }

    @Override
    public JsonObject removeUserFromSandbox(String sandboxId, String userId) throws QaobeeException {
        JsonObject sandbox = mongo.getById(sandboxId, SB_SandBox.class);
        JsonArray members = new JsonArray();
        sandbox.getArray(FIELD_MEMBERS).forEach(m -> {
            if (!((JsonObject) m).getString(FIELD_PERSON_ID).equals(userId)) {
                members.add(m);
            }
        });
        sandbox.putArray(FIELD_MEMBERS, members);
        sandbox.putString("_id", mongo.update(sandbox, SB_SandBox.class));
        return getEnrichedSandbox(sandbox);
    }

    @Override
    public JsonObject addUserToSandbox(String sandboxId, String userId, String roleCode) throws QaobeeException {
        JsonObject sandbox = mongo.getById(sandboxId, SB_SandBox.class);
        final JsonObject[] role = {new JsonObject().putString("code", roleCode)};
        JsonObject owner = mongo.getById(sandbox.getString(FIELD_OWNER), User.class);
        if (owner.containsField(FIELD_COUNTRY) && owner.getObject(FIELD_COUNTRY) != null && owner.getObject(FIELD_COUNTRY).containsField(FIELD_ID)) {
            ((JsonObject) activityCfgDAO.getActivityCfgParams(
                    sandbox.getString("activityId"),
                    owner.getObject(FIELD_COUNTRY).getString(FIELD_ID),
                    System.currentTimeMillis(),
                    "listRoleStr"
            ).get(0)).getArray("listRoleStr").forEach(n -> {
                if (((JsonObject) n).getString("code").equals(roleCode)) {
                    role[0] = (JsonObject) n;
                }
            });
        }
        sandbox.getArray(FIELD_MEMBERS).add(new JsonObject()
                .putString(FIELD_PERSON_ID, userId)
                .putObject("role", role[0])
        );
        sandbox.putString("_id", mongo.update(sandbox, SB_SandBox.class));
        return getEnrichedSandbox(sandbox);
    }

    @Override
    public JsonObject getListOfSharedSandboxes(String userId) throws QaobeeException {
        JsonObject result = new JsonObject()
                .putArray(FIELD_MEMBERS, new JsonArray())
                .putArray(FIELD_OWNER, new JsonArray());

        JsonArray sandboxes = mongo.findByCriterias(new CriteriaBuilder().add(FIELD_OWNER, userId).get(),
                null, null, -1, 0, SB_SandBox.class);
        BasicDBObject elemMatch = new BasicDBObject();
        elemMatch.put(FIELD_PERSON_ID, userId);
        BasicDBObject array = new BasicDBObject();
        array.put("$elemMatch", elemMatch);
        BasicDBObject query = new BasicDBObject();
        query.put(FIELD_MEMBERS, array);
        sandboxes.forEach(s -> {
            try {
                result.getArray(FIELD_OWNER).add(getEnrichedSandbox((JsonObject) s));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
        });
        mongo.getDb().getCollection(SB_SandBox.class.getSimpleName()).find(query).forEach(sandboxRes -> {
            try {
                result.getArray(FIELD_MEMBERS).add(getEnrichedSandbox(new JsonObject(sandboxRes.toString())));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
        });
        return result;
    }
}
