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

import com.qaobee.hive.dao.ActivityCfgDAO;
import com.qaobee.hive.dao.SandBoxDAO;
import com.qaobee.hive.dao.ShareDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Share dao.
 */
public class ShareDAOImpl implements ShareDAO {
    private static final Logger LOG = LoggerFactory.getLogger(ShareDAOImpl.class);
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
    private MongoClientCustom mongoClientCustom;
    @Inject
    private ActivityCfgDAO activityCfgDAO;
    @Inject
    private SandBoxDAO sandBoxDAO;

    @Override
    public Promise<JsonObject, QaobeeException, Integer> desactivateMemberToSandbox(String sandboxId, String userId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(sandboxId, DBCollections.SANDBOX)
                .done(sandbox -> {
                    sandbox.getJsonArray(FIELD_MEMBERS).forEach(m -> {
                        if (((JsonObject) m).getString(FIELD_PERSON_ID).equals(userId)) {
                            ((JsonObject) m).put(FIELD_STATUS, "desactivated");
                        }
                    });
                    mongo.upsert(sandbox, DBCollections.SANDBOX).done(id -> {
                        sandbox.put("_id", id);
                        sandBoxDAO.getEnrichedSandbox(sandbox).done(deferred::resolve).fail(deferred::reject);
                    }).fail(deferred::reject);
                }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> activateMemberToSandbox(String sandboxId, String userId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(sandboxId, DBCollections.SANDBOX).done(sandbox -> {
            sandbox.getJsonArray(FIELD_MEMBERS).forEach(m -> {
                if (((JsonObject) m).getString(FIELD_PERSON_ID).equals(userId)) {
                    ((JsonObject) m).put(FIELD_STATUS, "activated");
                }
            });
            mongo.upsert(sandbox, DBCollections.SANDBOX).done(id -> {
                sandbox.put("_id", id);
                sandBoxDAO.getEnrichedSandbox(sandbox).done(deferred::resolve).fail(deferred::reject);
            }).fail(deferred::reject);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> inviteMemberToSandbox(String sandboxId, String userEmail, String roleCode) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(sandboxId, DBCollections.SANDBOX).done(sandbox -> {
            final JsonObject[] role = {new JsonObject().put("code", roleCode)};
            mongo.getById(sandbox.getString(FIELD_OWNER), DBCollections.USER).done(owner -> {
                if (owner.containsKey(FIELD_COUNTRY) && owner.getJsonObject(FIELD_COUNTRY) != null && owner.getJsonObject(FIELD_COUNTRY).containsKey(FIELD_ID)) {
                    activityCfgDAO.getActivityCfgParams(
                            sandbox.getString("activityId"),
                            owner.getJsonObject(FIELD_COUNTRY).getString(FIELD_ID),
                            System.currentTimeMillis(),
                            "listRoleSandbox"
                    ).done(activityCfg -> {
                        activityCfg.getJsonObject(0).getJsonArray("listRoleSandbox").forEach(n -> {
                            if (((JsonObject) n).getString("code").equals(roleCode)) {
                                role[0] = (JsonObject) n;
                            }
                        });
                        JsonObject invitation = new JsonObject()
                                .put("senderId", owner.getString("_id"))
                                .put("userEmail", userEmail)
                                .put("role", role[0])
                                .put(FIELD_SANDBOX_ID, sandbox.getString("_id"))
                                .put(FIELD_STATUS, "waiting")
                                .put("invitationDate", System.currentTimeMillis());
                        mongo.findByCriterias(new CriteriaBuilder().add("contact.email", userEmail).get(), null, null, -1, 0, DBCollections.USER).done(invited -> {
                            if (invited.size() > 0) {
                                invitation.put("userId", invited.getJsonObject(0).getString("_id"));
                            }
                            mongo.upsert(invitation, DBCollections.INVITATION).done(id -> {
                                invitation.put("_id", id);
                                deferred.resolve(invitation);
                            }).fail(deferred::reject);
                        }).fail(deferred::reject);
                    }).fail(deferred::reject);
                }else {
                    deferred.resolve(null);
                }
            }).fail(deferred::reject);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> reviveInvitationToUser(String invitationId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(invitationId, DBCollections.INVITATION).done(invitation -> {
            invitation.put("invitationDate", System.currentTimeMillis());
            mongo.upsert(invitation, DBCollections.INVITATION).done(id -> {
                invitation.put("_id", id);
                deferred.resolve(invitation);
            }).fail(deferred::reject);

        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> removeInvitationToSandbox(String invitationId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(invitationId, DBCollections.INVITATION).done(invitation -> {
            invitation.put(FIELD_STATUS, "abandonned");
            mongo.upsert(invitation, DBCollections.INVITATION).done(id -> {
                invitation.put("_id", id);
                deferred.resolve(invitation);
            }).fail(deferred::reject);

        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getInvitationToSandbox(String invitationId) {
        return mongo.getById(invitationId, DBCollections.INVITATION);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> confirmInvitationToSandbox(String invitationId, String userId, String answer) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(invitationId, DBCollections.INVITATION).done(invitation -> {
            if ("accepted".equals(answer)) {
                invitation.put(FIELD_STATUS, "accepted").put("answerDate", System.currentTimeMillis());
                mongo.upsert(invitation, DBCollections.INVITATION).done(id -> {
                    invitation.put("_id", id);
                    addMemberToSandbox(invitation.getString(FIELD_SANDBOX_ID), userId, invitation.getJsonObject("role"))
                            .done(r -> deferred.resolve(invitation))
                            .fail(deferred::reject);
                }).fail(deferred::reject);
            } else {
                invitation.put(FIELD_STATUS, "refused").put("answerDate", System.currentTimeMillis());
                mongo.upsert(invitation, DBCollections.INVITATION).done(id -> {
                    invitation.put("_id", id);
                    deferred.resolve(invitation);
                }).fail(deferred::reject);
            }
        }).fail(deferred::reject);

        return deferred.promise();
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getListOfInvitationsToSandbox(String sandboxId, String status) {
        CriteriaBuilder criterias = new CriteriaBuilder().add(FIELD_SANDBOX_ID, sandboxId);
        if (!"ALL".equals(status)) {
            criterias.add(FIELD_STATUS, status);
        }
        return mongo.findByCriterias(criterias.get(), null, null, -1, 0, DBCollections.INVITATION);
    }

    private Promise<JsonObject, QaobeeException, Integer> addMemberToSandbox(String sandboxId, String userId, JsonObject role) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(sandboxId, DBCollections.SANDBOX).done(sandbox -> {
            sandbox.getJsonArray(FIELD_MEMBERS).add(new JsonObject()
                    .put(FIELD_PERSON_ID, userId)
                    .put("role", role)
                    .put(FIELD_STATUS, "activated")
                    .put("startDate", System.currentTimeMillis())
            );
            mongo.upsert(sandbox, DBCollections.SANDBOX).done(id -> {
                sandbox.put("_id", id);
                sandBoxDAO.getEnrichedSandbox(sandbox).done(deferred::resolve).fail(deferred::reject);
            }).fail(deferred::reject);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getListOfSharedSandboxes(String userId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        JsonObject result = new JsonObject()
                .put(FIELD_MEMBERS, new JsonArray())
                .put(FIELD_OWNER, new JsonArray());

        mongo.findByCriterias(new CriteriaBuilder().add(FIELD_OWNER, userId).get(), null, null, -1, 0, DBCollections.SANDBOX)
                .done(sandboxes -> {
                    JsonObject elemMatch = new JsonObject().put(FIELD_PERSON_ID, userId);
                    JsonObject array = new JsonObject().put("$elemMatch", elemMatch);
                    JsonObject query = new JsonObject().put(FIELD_MEMBERS, array);
                    List<Promise> promises = new ArrayList<>();
                    sandboxes.forEach(s -> promises.add(sandBoxDAO.getEnrichedSandbox((JsonObject) s)));
                    DeferredManager dm = new DefaultDeferredManager();
                    dm.when(promises.toArray(new Promise[promises.size()]))
                            .done(rs -> {
                                rs.forEach(result.getJsonArray(FIELD_OWNER)::add);
                                mongoClientCustom.find(DBCollections.SANDBOX, query, res -> {
                                    if (res.succeeded()) {
                                        List<Promise> promises2 = new ArrayList<>();
                                        res.result().forEach(sandboxRes -> promises2.add(sandBoxDAO.getEnrichedSandbox(new JsonObject(sandboxRes.toString()))));
                                        dm.when(promises2.toArray(new Promise[promises2.size()]))
                                                .done(rs2 -> {
                                                    rs2.forEach(sb -> result.getJsonArray(FIELD_MEMBERS).add(sb));
                                                    deferred.resolve(result);
                                                })
                                                .fail(e -> {
                                                    LOG.error(((Throwable) e.getReject()).getMessage());
                                                    deferred.reject(((QaobeeException) e.getReject()));
                                                });

                                    } else {
                                        deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, res.cause().getMessage()));
                                    }
                                });
                            })
                            .fail(e -> {
                                LOG.error(((Throwable) e.getReject()).getMessage());
                                deferred.reject(((QaobeeException) e.getReject()));
                            });
                }).fail(deferred::reject);
        return deferred.promise();
    }
}
