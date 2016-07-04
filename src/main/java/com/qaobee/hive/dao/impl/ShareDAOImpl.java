package com.qaobee.hive.dao.impl;

import com.mongodb.BasicDBObject;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBoxCfg;
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
    private static final String FIELD_SBCFG_ID = "sandboxCfgId";
    private static final String FIELD_SBCFG = "sandboxCfg";
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
        JsonObject sandbox = mongo.getById(sandboxId, SB_SandBox.class);
        String sandboxCfgId = sandbox.getString(FIELD_SBCFG_ID);
        sandbox.putObject(FIELD_SBCFG, getEnrichedSandboxCfg(sandboxCfgId));
        return sandbox;
    }

    @Override
    public JsonObject getEnrichedSandboxCfg(String sandboxCfgId) throws QaobeeException {
        JsonObject sandboxCfg = mongo.getById(sandboxCfgId, SB_SandBoxCfg.class);
        JsonArray members = new JsonArray();
        sandboxCfg.getArray(FIELD_MEMBERS).forEach(m -> {
            try {
                members.add(mongo.getById(((JsonObject) m).getString(FIELD_PERSON_ID), User.class, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
        });
        sandboxCfg.putArray(FIELD_MEMBERS, members);
        return sandboxCfg;
    }

    @Override
    public JsonObject removeUserFromSandbox(String sandboxId, String userId) throws QaobeeException {
        JsonObject sandbox = mongo.getById(sandboxId, SB_SandBox.class);
        String sandboxCfgId = sandbox.getString(FIELD_SBCFG_ID);
        JsonObject sandboxCfg = mongo.getById(sandboxCfgId, SB_SandBoxCfg.class);
        JsonArray members = new JsonArray();
        sandboxCfg.getArray(FIELD_MEMBERS).forEach(m -> {
            if (!((JsonObject) m).getString(FIELD_PERSON_ID).equals(userId)) {
                members.add(m);
            }
        });
        sandboxCfg.putArray(FIELD_MEMBERS, members);
        mongo.update(sandboxCfg, SB_SandBoxCfg.class);
        sandbox.putObject(FIELD_SBCFG, getEnrichedSandboxCfg(sandboxCfgId));
        return sandbox;
    }

    @Override
    public JsonObject addUserToSandbox(String sandboxId, String userId, String roleCode) throws QaobeeException {
        JsonObject sandbox = mongo.getById(sandboxId, SB_SandBox.class);
        String sandboxCfgId = sandbox.getString(FIELD_SBCFG_ID);
        JsonObject sandboxCfg = mongo.getById(sandboxCfgId, SB_SandBoxCfg.class);
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
        sandboxCfg.getArray(FIELD_MEMBERS).add(new JsonObject()
                .putString(FIELD_PERSON_ID, userId)
                .putObject("role", role[0])
        );
        mongo.update(sandboxCfg, SB_SandBoxCfg.class);
        sandbox.putObject(FIELD_SBCFG, getEnrichedSandboxCfg(sandboxCfgId));
        return sandbox;
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

        mongo.getDb().getCollection(SB_SandBoxCfg.class.getSimpleName()).find(query).forEach(sbCfg -> {
            JsonObject sandboxCfg = new JsonObject(sbCfg.toString());
            JsonArray members = new JsonArray();
            sandboxCfg.getArray(FIELD_MEMBERS).forEach(m -> {
                try {
                    members.add(mongo.getById(((JsonObject) m).getString(FIELD_PERSON_ID), User.class, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                }
            });
            sandboxCfg.putArray(FIELD_MEMBERS, members);
            mongo.findByCriterias(new CriteriaBuilder().add(FIELD_SBCFG_ID, sbCfg.get(FIELD_ID)).get(), null, null, -1, 0, SB_SandBox.class).forEach(sb -> {
                        try {
                            ((JsonObject) sb).putObject(FIELD_OWNER, mongo.getById(((JsonObject) sb).getString(FIELD_OWNER), User.class, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
                        } catch (QaobeeException e) {
                            LOG.error(e.getMessage(), e);
                        }
                        ((JsonObject) sb).putObject(FIELD_SBCFG, sandboxCfg);
                        result.getArray(FIELD_MEMBERS).add(sb);
                    }
            );
        });

        sandboxes.forEach(s -> {
            try {
                ((JsonObject) s).putObject(FIELD_OWNER, mongo.getById(((JsonObject) s).getString(FIELD_OWNER),
                        User.class,
                        Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
                ((JsonObject) s).putObject(FIELD_SBCFG, getEnrichedSandboxCfg(((JsonObject) s).getString(FIELD_SBCFG_ID)));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
            result.getArray(FIELD_OWNER).add(s);
        });
        return result;
    }
}
