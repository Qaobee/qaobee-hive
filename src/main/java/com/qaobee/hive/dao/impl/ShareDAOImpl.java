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

import com.qaobee.hive.dao.SandBoxDAO;
import com.qaobee.hive.dao.ShareDAO;
import com.qaobee.hive.services.ActivityCfgService;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    private MongoClientCustom mongoClientCustom;
    @Inject
    private ActivityCfgService activityCfgService;
    @Inject
    private SandBoxDAO sandBoxDAO;

    private void sendInvitation(JsonArray activityCfg, String roleCode, JsonObject[] role, JsonObject owner, String userEmail, JsonObject sandbox, Handler<AsyncResult<JsonObject>> resultHandler) {
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
        mongo.findByCriterias(new JsonObject().put("contact.email", userEmail), new CriteriaOption(), DBCollections.USER, invitedRes -> {
            if (invitedRes.succeeded()) {
                if (invitedRes.result().size() > 0) {
                    invitation.put("userId", invitedRes.result().getJsonObject(0).getString("_id"));
                }
                mongo.upsert(invitation, DBCollections.INVITATION, upsertRes -> {
                    if (upsertRes.succeeded()) {
                        invitation.put("_id", upsertRes.result());
                        resultHandler.handle(Future.succeededFuture(invitation));
                    } else {
                        resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(invitedRes.cause()));
            }
        });

    }

    private void addMemberToSandbox(String sandboxId, String userId, JsonObject role, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(sandboxId, DBCollections.SANDBOX, sandboxRes -> {
            if (sandboxRes.succeeded()) {
                JsonObject sandbox = sandboxRes.result();
                sandbox.getJsonArray(FIELD_MEMBERS).add(new JsonObject()
                        .put(FIELD_PERSON_ID, userId)
                        .put("role", role)
                        .put(FIELD_STATUS, "activated")
                        .put("startDate", System.currentTimeMillis())
                );
                mongo.upsert(sandbox, DBCollections.SANDBOX, upsertRes -> {
                    if (upsertRes.succeeded()) {
                        sandbox.put("_id", upsertRes.result());
                        sandBoxDAO.getEnrichedSandbox(sandbox, resultHandler);
                    } else {
                        resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(sandboxRes.cause()));
            }
        });
    }

    @Override
    public void desactivateMemberToSandbox(String sandboxId, String userId, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(sandboxId, DBCollections.SANDBOX, sandbox -> {
            if (sandbox.succeeded()) {
                JsonObject sb = sandbox.result();
                sb.getJsonArray(FIELD_MEMBERS).forEach(m -> {
                    if (((JsonObject) m).getString(FIELD_PERSON_ID).equals(userId)) {
                        ((JsonObject) m).put(FIELD_STATUS, "desactivated");
                    }
                });
                mongo.upsert(sandbox.result(), DBCollections.SANDBOX, upsertRes -> {
                    if (upsertRes.succeeded()) {
                        sb.put("_id", upsertRes.result());
                        sandBoxDAO.getEnrichedSandbox(sb, resultHandler);
                    } else {
                        resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(sandbox.cause()));
            }
        });
    }

    @Override
    public void activateMemberToSandbox(String sandboxId, String userId, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(sandboxId, DBCollections.SANDBOX, sandbox -> {
            if (sandbox.succeeded()) {
                JsonObject sb = sandbox.result();
                sb.getJsonArray(FIELD_MEMBERS).forEach(m -> {
                    if (((JsonObject) m).getString(FIELD_PERSON_ID).equals(userId)) {
                        ((JsonObject) m).put(FIELD_STATUS, "activated");
                    }
                });
                mongo.upsert(sb, DBCollections.SANDBOX, upsertRes -> {
                    if (upsertRes.succeeded()) {
                        sb.put("_id", upsertRes.result());
                        sandBoxDAO.getEnrichedSandbox(sb, resultHandler);
                    } else {
                        resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(sandbox.cause()));
            }
        });
    }

    @Override
    public void inviteMemberToSandbox(String sandboxId, String userEmail, String roleCode, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(sandboxId, DBCollections.SANDBOX, sandboxRes -> {
            if (sandboxRes.succeeded()) {
                final JsonObject[] role = {new JsonObject().put("code", roleCode)};
                JsonObject sandbox = sandboxRes.result();
                mongo.getById(sandbox.getString(FIELD_OWNER), DBCollections.USER, ownerRes -> {
                    if (ownerRes.succeeded()) {
                        JsonObject owner = ownerRes.result();
                        if (owner.containsKey(FIELD_COUNTRY) && owner.getJsonObject(FIELD_COUNTRY) != null && owner.getJsonObject(FIELD_COUNTRY).containsKey(FIELD_ID)) {
                            activityCfgService.getActivityCfgParams(
                                    sandbox.getString("activityId"),
                                    owner.getJsonObject(FIELD_COUNTRY).getString(FIELD_ID),
                                    System.currentTimeMillis(),
                                    "listRoleSandbox", ar -> {
                                        if (ar.succeeded()) {
                                            sendInvitation(ar.result(), roleCode, role, owner, userEmail, sandbox, resultHandler);
                                        } else {
                                            resultHandler.handle(Future.failedFuture(ar.cause()));
                                        }
                                    });
                        } else {
                            resultHandler.handle(Future.succeededFuture(null));
                        }
                    } else {
                        resultHandler.handle(Future.failedFuture(ownerRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(sandboxRes.cause()));
            }
        });
    }

    @Override
    public void reviveInvitationToUser(String invitationId, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(invitationId, DBCollections.INVITATION, invitationRes -> {
            if (invitationRes.succeeded()) {
                JsonObject invitation = invitationRes.result();
                invitation.put("invitationDate", System.currentTimeMillis());
                mongo.upsert(invitation, DBCollections.INVITATION, upsertRes -> {
                    if (upsertRes.succeeded()) {
                        invitation.put("_id", upsertRes.result());
                        resultHandler.handle(Future.succeededFuture(invitation));
                    } else {
                        resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(invitationRes.cause()));
            }
        });
    }

    @Override
    public void removeInvitationToSandbox(String invitationId, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(invitationId, DBCollections.INVITATION, invitationRes -> {
            if (invitationRes.succeeded()) {
                JsonObject invitation = invitationRes.result();
                invitation.put(FIELD_STATUS, "abandonned");
                mongo.upsert(invitation, DBCollections.INVITATION, upsertRes -> {
                    if (upsertRes.succeeded()) {
                        invitation.put("_id", upsertRes.result());
                        resultHandler.handle(Future.succeededFuture(invitation));
                    } else {
                        resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(invitationRes.cause()));
            }
        });
    }

    @Override
    public void getInvitationToSandbox(String invitationId, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(invitationId, DBCollections.INVITATION, resultHandler);
    }

    @Override
    public void confirmInvitationToSandbox(String invitationId, String userId, String answer, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(invitationId, DBCollections.INVITATION, invitationRes -> {
            if (invitationRes.succeeded()) {
                JsonObject invitation = invitationRes.result();
                if ("accepted".equals(answer)) {
                    invitation.put(FIELD_STATUS, "accepted").put("answerDate", System.currentTimeMillis());
                    mongo.upsert(invitation, DBCollections.INVITATION, upsertRes -> {
                        if (upsertRes.succeeded()) {
                            invitation.put("_id", upsertRes.result());
                            addMemberToSandbox(invitation.getString(FIELD_SANDBOX_ID), userId, invitation.getJsonObject("role"), ar -> {
                                if (ar.succeeded()) {
                                    resultHandler.handle(Future.succeededFuture(invitation));
                                } else {
                                    resultHandler.handle(Future.failedFuture(ar.cause()));
                                }
                            });
                        } else {
                            resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                        }
                    });
                } else {
                    invitation.put(FIELD_STATUS, "refused").put("answerDate", System.currentTimeMillis());
                    mongo.upsert(invitation, DBCollections.INVITATION, upsertRes -> {
                        if (upsertRes.succeeded()) {
                            invitation.put("_id", upsertRes.result());
                            resultHandler.handle(Future.succeededFuture(invitation));
                        } else {
                            resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                        }
                    });
                }
            } else {
                resultHandler.handle(Future.failedFuture(invitationRes.cause()));
            }
        });
    }

    @Override
    public void getListOfSharedSandboxes(String userId, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject result = new JsonObject()
                .put(FIELD_MEMBERS, new JsonArray())
                .put(FIELD_OWNER, new JsonArray());

        mongo.findByCriterias(new JsonObject().put(FIELD_OWNER, userId), new CriteriaOption(), DBCollections.SANDBOX, sandboxesRes -> {
            if (sandboxesRes.succeeded()) {
                JsonArray sandboxes = sandboxesRes.result();
                JsonObject elemMatch = new JsonObject().put(FIELD_PERSON_ID, userId);
                JsonObject array = new JsonObject().put("$elemMatch", elemMatch);
                JsonObject query = new JsonObject().put(FIELD_MEMBERS, array);
                List<Future> promises = new ArrayList<>();

                sandboxes.forEach(s -> {
                    Future<JsonObject> f = Future.future();
                    promises.add(f);
                    sandBoxDAO.getEnrichedSandbox((JsonObject) s, ar -> {
                        if (ar.succeeded()) {
                            f.complete(ar.result());
                        } else {
                            f.fail(ar.cause());
                        }
                    });
                });
                CompositeFuture.all(promises).setHandler(rs -> {
                    if (rs.succeeded()) {
                        rs.result().list().forEach(r -> result.getJsonArray(FIELD_OWNER).add(r));
                        mongoClientCustom.find(DBCollections.SANDBOX, query, res -> {
                            if (res.succeeded()) {
                                List<Future> promises2 = new ArrayList<>();
                                res.result().forEach(s -> {
                                    Future<JsonObject> f = Future.future();
                                    promises2.add(f);
                                    sandBoxDAO.getEnrichedSandbox(s, ar -> {
                                        if (ar.succeeded()) {
                                            f.complete(ar.result());
                                        } else {
                                            f.fail(ar.cause());
                                        }
                                    });
                                });
                                CompositeFuture.all(promises2).setHandler(rs2 -> {
                                    if (rs2.succeeded()) {
                                        rs2.result().list().forEach(sb -> result.getJsonArray(FIELD_MEMBERS).add(sb));
                                        resultHandler.handle(Future.succeededFuture(result));
                                    } else {
                                        resultHandler.handle(Future.failedFuture(rs2.cause()));
                                    }
                                });
                            } else {
                                resultHandler.handle(Future.failedFuture(res.cause()));
                            }
                        });
                    } else {
                        resultHandler.handle(Future.failedFuture(rs.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(sandboxesRes.cause()));
            }
        });
    }

    @Override
    public void getListOfInvitationsToSandbox(String sandboxId, String status, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject criterias = new JsonObject().put(FIELD_SANDBOX_ID, sandboxId);
        if (!"ALL".equals(status)) {
            criterias.put(FIELD_STATUS, status);
        }
        mongo.findByCriterias(criterias, new CriteriaOption(), DBCollections.INVITATION, resultHandler);
    }
}
