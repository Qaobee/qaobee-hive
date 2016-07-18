package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Team dao.
 */
public interface TeamDAO {
    /**
     * Gets team list.
     *
     * @param sandboxId   the sandbox id
     * @param effectiveId the effective id
     * @param adversary   the adversary
     * @param enabled     the enabled
     * @param link        the link
     * @return the team list
     */
    JsonArray getTeamList(String sandboxId, String effectiveId, String adversary, String enabled, String link);

    /**
     * Gets team.
     *
     * @param teamId the team id
     * @return the team
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getTeam(String teamId) throws QaobeeException;

    /**
     * Update team json object.
     *
     * @param team   the team
     * @param userId the user id
     * @param locale the locale
     * @return the json object
     */
    JsonObject updateTeam(JsonObject team, String userId, String locale);

    /**
     * Add team json object.
     *
     * @param team   the team
     * @param userId the user id
     * @param locale the locale
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject addTeam(JsonObject team, String userId, String locale) throws QaobeeException;
}
