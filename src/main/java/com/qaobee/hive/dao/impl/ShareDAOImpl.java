/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */

package com.qaobee.hive.dao.impl;

import com.mongodb.BasicDBObject;
import com.qaobee.hive.dao.ActivityCfgDAO;
import com.qaobee.hive.dao.SandBoxDAO;
import com.qaobee.hive.dao.ShareDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Share dao.
 */
public class ShareDAOImpl implements ShareDAO {
    private static final String FIELD_ID = "_id";
    private static final String FIELD_OWNER = "owner";
    private static final String FIELD_MEMBERS = "members";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_PERSON_ID = "personId";
    private static final String FIELD_SANDBOX_ID = "sandboxId";
    private static final String FIELD_STATUS = "status";

    @Inject
    private MongoDB mongo;
    @Inject
    private ActivityCfgDAO activityCfgDAO;
    @Inject
    private SandBoxDAO sandBoxDAO;

    @Override
    public JsonObject desactivateMemberToSandbox(String sandboxId, String userId) throws QaobeeException {
        JsonObject sandbox = mongo.getById(sandboxId, DBCollections.SANDBOX);
        sandbox.getArray(FIELD_MEMBERS).forEach(m -> {
            if (((JsonObject) m).getString(FIELD_PERSON_ID).equals(userId)) {
                ((JsonObject) m).putString(FIELD_STATUS, "desactivated");
            }
        });
        sandbox.putString("_id", mongo.update(sandbox, DBCollections.SANDBOX));
        return sandBoxDAO.getEnrichedSandbox(sandbox);
    }

    @Override
    public JsonObject activateMemberToSandbox(String sandboxId, String userId) throws QaobeeException {
        JsonObject sandbox = mongo.getById(sandboxId, DBCollections.SANDBOX);
        sandbox.getArray(FIELD_MEMBERS).forEach(m -> {
            if (((JsonObject) m).getString(FIELD_PERSON_ID).equals(userId)) {
                ((JsonObject) m).putString(FIELD_STATUS, "activated");
            }
        });
        sandbox.putString("_id", mongo.update(sandbox, DBCollections.SANDBOX));
        return sandBoxDAO.getEnrichedSandbox(sandbox);
    }

    @Override
    public JsonObject inviteMemberToSandbox(String sandboxId, String userEmail, String roleCode) throws QaobeeException {
        JsonObject sandbox = mongo.getById(sandboxId, DBCollections.SANDBOX);
        final JsonObject[] role = {new JsonObject().putString("code", roleCode)};
        JsonObject owner = mongo.getById(sandbox.getString(FIELD_OWNER), DBCollections.USER);
        if (owner.containsField(FIELD_COUNTRY) && owner.getObject(FIELD_COUNTRY) != null && owner.getObject(FIELD_COUNTRY).containsField(FIELD_ID)) {
            ((JsonObject) activityCfgDAO.getActivityCfgParams(
                    sandbox.getString("activityId"),
                    owner.getObject(FIELD_COUNTRY).getString(FIELD_ID),
                    System.currentTimeMillis(),
                    "listRoleSandbox"
            ).get(0)).getArray("listRoleSandbox").forEach(n -> {
                if (((JsonObject) n).getString("code").equals(roleCode)) {
                    role[0] = (JsonObject) n;
                }
            });
        }
        JsonObject invitation = new JsonObject()
                .putString("userEmail", userEmail)
                .putObject("role", role[0])
                .putString(FIELD_SANDBOX_ID, sandbox.getString("_id"))
                .putString(FIELD_STATUS, "waiting")
                .putNumber("invitationDate", System.currentTimeMillis());
        JsonArray invited = mongo.findByCriterias(new CriteriaBuilder().add("contact.email", userEmail).get(), null, null, -1, 0, DBCollections.USER);
        if (invited.size() > 0) {
            invitation.putString("userId", ((JsonObject) invited.get(0)).getString("_id"));
        }
        invitation.putString("_id", mongo.save(invitation, DBCollections.INVITATION));
        return invitation;
    }
    
    @Override
    public JsonObject reviveInvitationToUser(String invitationId) throws QaobeeException {
        JsonObject invitation = mongo.getById(invitationId, DBCollections.INVITATION);

        invitation.putNumber("invitationDate", System.currentTimeMillis());
        invitation.putString("_id", mongo.update(invitation, DBCollections.INVITATION));
        return invitation;
    }

    @Override
    public JsonObject removeInvitationToSandbox(String invitationId) throws QaobeeException {
        JsonObject invitation = mongo.getById(invitationId, DBCollections.INVITATION);

        invitation.putString(FIELD_STATUS, "abandonned");
        invitation.putString("_id", mongo.save(invitation, DBCollections.INVITATION));

        return invitation;
    }

    @Override
    public JsonObject confirmInvitationToSandbox(String invitationId, String userId, String answer) throws QaobeeException {
        JsonObject invitation = mongo.getById(invitationId, DBCollections.INVITATION);
        if ("accepted".equals(answer)) {
            invitation.putString(FIELD_STATUS, "accepted").putNumber("answerDate", System.currentTimeMillis());
            invitation.putString("_id", mongo.update(invitation, DBCollections.INVITATION));
            addMemberToSandbox(invitation.getString(FIELD_SANDBOX_ID), userId, invitation.getObject("role"));
        } else {
            invitation.putString(FIELD_STATUS, "refused").putNumber("answerDate", System.currentTimeMillis());
            invitation.putString("_id", mongo.update(invitation, DBCollections.INVITATION));
        }
        return invitation;
    }
    
    @Override
    public JsonArray getListOfInvitationsToSandbox(String sandboxId, String status) {
        CriteriaBuilder criterias = new CriteriaBuilder().add(FIELD_SANDBOX_ID, sandboxId);
        if(!"ALL".equals(status)) {
            criterias.add(FIELD_STATUS, status);
        }
        return mongo.findByCriterias(criterias.get(), null, null, -1, 0, DBCollections.INVITATION);
    }

    private JsonObject addMemberToSandbox(String sandboxId, String userId, JsonObject role) throws QaobeeException {
        JsonObject sandbox = mongo.getById(sandboxId, DBCollections.SANDBOX);
        sandbox.getArray(FIELD_MEMBERS).add(new JsonObject()
                .putString(FIELD_PERSON_ID, userId)
                .putObject("role", role)
                .putString(FIELD_STATUS, "activated")
                .putNumber("startDate", System.currentTimeMillis())
        );
        sandbox.putString("_id", mongo.update(sandbox, DBCollections.SANDBOX));
        return sandBoxDAO.getEnrichedSandbox(sandbox);
    }

    @Override
    public JsonObject getListOfSharedSandboxes(String userId) {
        JsonObject result = new JsonObject()
                .putArray(FIELD_MEMBERS, new JsonArray())
                .putArray(FIELD_OWNER, new JsonArray());

        JsonArray sandboxes = mongo.findByCriterias(new CriteriaBuilder().add(FIELD_OWNER, userId).get(),
                null, null, -1, 0, DBCollections.SANDBOX);
        BasicDBObject elemMatch = new BasicDBObject();
        elemMatch.put(FIELD_PERSON_ID, userId);
        BasicDBObject array = new BasicDBObject();
        array.put("$elemMatch", elemMatch);
        BasicDBObject query = new BasicDBObject();
        query.put(FIELD_MEMBERS, array);
        sandboxes.forEach(s -> result.getArray(FIELD_OWNER).add(sandBoxDAO.getEnrichedSandbox((JsonObject) s)));
        mongo.getDb().getCollection(DBCollections.SANDBOX)
                .find(query)
                .forEach(sandboxRes -> result.getArray(FIELD_MEMBERS).add(sandBoxDAO.getEnrichedSandbox(new JsonObject(sandboxRes.toString()))));
        return result;
    }
}
