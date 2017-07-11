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


import com.qaobee.hive.services.Notifications;
import com.qaobee.hive.dao.TeamDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;

/**
 * The type Team dao.
 */
public class TeamDAOImpl implements TeamDAO {
    private static final String PARAM_ADVERSARY = "adversary";
    private static final String PARAM_EFFECTIVE_ID = "effectiveId";
    private static final String PARAM_SANDBOX_ID = "sandboxId";
    private static final String PARAM_ENABLE = "enable";
    private static final String PARAM_LINK_TEAM_ID = "linkTeamId";

    private final MongoDB mongo;
    private final Notifications notifications;

    /**
     * Instantiates a new Team dao.
     *
     * @param mongo            the mongo
     * @param notifications the notifications dao
     */
    @Inject
    public TeamDAOImpl(MongoDB mongo, Notifications notifications) {
        this.mongo = mongo;
        this.notifications = notifications;
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getTeamList(String sandboxId, String effectiveId, String adversary, String enabled, String link) {
        CriteriaBuilder criteria = new CriteriaBuilder()
                .add(PARAM_SANDBOX_ID, sandboxId)
                .add(PARAM_EFFECTIVE_ID, effectiveId)
                .add(PARAM_ADVERSARY, "true".equals(adversary));
        if (!"all".equals(enabled)) {
            criteria.add(PARAM_ENABLE, "true".equals(enabled));
        }
        if (link != null) {
            criteria.add(PARAM_LINK_TEAM_ID, link);
        }
        return mongo.findByCriterias(criteria.get(), null, null, -1, -1, DBCollections.TEAM);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getTeam(String teamId) {
        return mongo.getById(teamId, DBCollections.TEAM);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> updateTeam(JsonObject team, String userId, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.upsert(team, DBCollections.TEAM).done(id -> {
            team.put("_id", id);
            JsonObject notification = new JsonObject()
                    .put("content", Messages.getString("notification.team.update.content", locale,
                            team.getString("label"),
                            "/#/private/updateTeam/" + team.getString("_id") + "/" + team.getBoolean(PARAM_ADVERSARY)))
                    .put("title", Messages.getString("notification.team.update.title", locale))
                    .put("senderId", userId);
            notifications.sendNotification(team.getString(PARAM_SANDBOX_ID), DBCollections.SANDBOX, notification, new JsonArray().add(userId), ar->{});
            deferred.resolve(team);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> addTeam(JsonObject team, String userId, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.upsert(team, DBCollections.TEAM).done(id -> {
            team.put("_id", id);
            JsonObject notification = new JsonObject()
                    .put("content", Messages.getString("notification.team.add.content", locale,
                            team.getString("label"),
                            "/#/private/updateTeam/" + team.getString("_id") + "/" + team.getBoolean(PARAM_ADVERSARY)))
                    .put("title", Messages.getString("notification.team.add.title", locale))
                    .put("senderId", userId);
            notifications.sendNotification(team.getString(PARAM_SANDBOX_ID), DBCollections.SANDBOX, notification, new JsonArray().add(userId), ar->{});
            deferred.resolve(team);
        }).fail(deferred::reject);
        return deferred.promise();
    }
}
