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
import com.qaobee.hive.services.TeamService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import com.qaobee.hive.technical.tools.Messages;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

/**
 * The type Team service.
 */
@ProxyService(address = "vertx.Team.service", iface = TeamService.class)
public class TeamServiceImpl implements TeamService {
    private static final String PARAM_ADVERSARY = "adversary";
    private static final String PARAM_EFFECTIVE_ID = "effectiveId";
    private static final String PARAM_SANDBOX_ID = "sandboxId";
    private static final String PARAM_ENABLE = "enable";
    private static final String PARAM_LINK_TEAM_ID = "linkTeamId";

    @Inject
    private MongoDB mongo;
    @Inject
    private NotificationsService notificationsService;

    /**
     * Instantiates a new Team service.
     *
     * @param vertx the vertx
     */
    public TeamServiceImpl(Vertx vertx) { // NOSONAR
        super();
    }

    @Override
    public void getTeamList(String sandboxId, String effectiveId, String adversary, String enabled, String link, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject criteria = new JsonObject()
                .put(PARAM_SANDBOX_ID, sandboxId)
                .put(PARAM_EFFECTIVE_ID, effectiveId)
                .put(PARAM_ADVERSARY, "true".equals(adversary));
        if (!"all".equals(enabled)) {
            criteria.put(PARAM_ENABLE, "true".equals(enabled));
        }
        if (StringUtils.isNotBlank(link)) {
            criteria.put(PARAM_LINK_TEAM_ID, link);
        }
        mongo.findByCriterias(criteria, new CriteriaOption(), DBCollections.TEAM, resultHandler);
    }

    @Override
    public void getTeam(String teamId, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(teamId, DBCollections.TEAM, resultHandler);
    }

    @Override
    public void updateTeam(JsonObject team, String userId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(team, DBCollections.TEAM, upsertRes -> {
            if (upsertRes.succeeded()) {
                team.put("_id", upsertRes.result());
                JsonObject notification = new JsonObject()
                        .put("content", Messages.getString("notification.team.update.content", locale,
                                team.getString("label"),
                                "/#/private/updateTeam/" + team.getString("_id") + "/" + team.getBoolean(PARAM_ADVERSARY)))
                        .put("title", Messages.getString("notification.team.update.title", locale))
                        .put("senderId", userId);
                notificationsService.sendNotification(team.getString(PARAM_SANDBOX_ID), DBCollections.SANDBOX, notification, new JsonArray().add(userId), ar -> {
                    // empty
                });
                resultHandler.handle(Future.succeededFuture(team));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }

    @Override
    public void addTeam(JsonObject team, String userId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(team, DBCollections.TEAM, upsertRes -> {
            if (upsertRes.succeeded()) {
                team.put("_id", upsertRes.result());
                JsonObject notification = new JsonObject()
                        .put("content", Messages.getString("notification.team.add.content", locale,
                                team.getString("label"),
                                "/#/private/updateTeam/" + team.getString("_id") + "/" + team.getBoolean(PARAM_ADVERSARY)))
                        .put("title", Messages.getString("notification.team.add.title", locale))
                        .put("senderId", userId);
                notificationsService.sendNotification(team.getString(PARAM_SANDBOX_ID), DBCollections.SANDBOX, notification, new JsonArray().add(userId), ar -> {
                    // empty
                });
                resultHandler.handle(Future.succeededFuture(team));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }
}
