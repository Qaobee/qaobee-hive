package com.qaobee.hive.dao.impl;


import com.qaobee.hive.dao.NotificationsDAO;
import com.qaobee.hive.dao.TeamDAO;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Team dao.
 */
public class TeamDAOImpl implements TeamDAO {
    private static final String COLLECTION = "SB_Team";
    private static final String PARAM_ADVERSARY = "adversary";
    private static final String PARAM_EFFECTIVE_ID = "effectiveId";
    private static final String PARAM_SANDBOX_ID = "sandboxId";
    private static final String PARAM_ENABLE = "enable";
    private static final String PARAM_LINK_TEAM_ID = "linkTeamId";

    @Inject
    private MongoDB mongo;
    @Inject
    private NotificationsDAO notificationsDAO;

    @Override
    public JsonArray getTeamList(String sandboxId, String effectiveId, String adversary, String enabled, String link) {
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
        return mongo.findByCriterias(criteria.get(), null, null, -1, -1, COLLECTION);
    }

    @Override
    public JsonObject getTeam(String teamId) throws QaobeeException {
        return mongo.getById(teamId, COLLECTION);
    }

    @Override
    public JsonObject updateTeam(JsonObject team, String userId, String locale) {
        team.putString("_id", mongo.update(team, COLLECTION));
        JsonObject notification = new JsonObject()
                .putString("content", Messages.getString("notification.team.update.content", locale,
                        team.getString("label"),
                        "/#/private/updateTeam/" + team.getString("_id") + "/" + team.getBoolean(PARAM_ADVERSARY)))
                .putString("title", Messages.getString("notification.team.update.title", locale))
                .putString("senderId", userId);
        notificationsDAO.notify(team.getString(PARAM_SANDBOX_ID), "SB_SandBox", notification, new JsonArray().add(userId));
        return team;
    }

    @Override
    public JsonObject addTeam(JsonObject team, String userId, String locale) throws QaobeeException {
        team.putString("_id", mongo.save(team, COLLECTION));
        JsonObject notification = new JsonObject()
                .putString("content", Messages.getString("notification.team.add.content", locale,
                        team.getString("label"),
                        "/#/private/updateTeam/" + team.getString("_id") + "/" + team.getBoolean(PARAM_ADVERSARY)))
                .putString("title", Messages.getString("notification.team.add.title", locale))
                .putString("senderId", userId);
        notificationsDAO.notify(team.getString(PARAM_SANDBOX_ID), "SB_SandBox", notification, new JsonArray().add(userId));
        return team;
    }
}
