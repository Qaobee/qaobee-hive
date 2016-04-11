/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.test.api.sandbox.effective;

import com.qaobee.hive.api.v1.sandbox.agenda.SB_EventVerticle;
import com.qaobee.hive.api.v1.sandbox.effective.SB_TeamVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cke
 */
public class SB_TeamTest extends VertxJunitSupport {

    /**
     * Tests of getting a list of my teams for sandbox and effective
     */
    @Test
    public void getMyTeamsList() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_TEAM_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

		/* list of parameters */
        final Map<String, List<String>> params = new HashMap<>();

        params.put(SB_TeamVerticle.PARAM_SANDBOX_ID, Collections.singletonList("558b0efebd2e39cdab651e1f"));
        params.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, Collections.singletonList("550b31f925da07681592db23"));
        params.put(SB_TeamVerticle.PARAM_ENABLE, Collections.singletonList("true"));
        params.put(SB_TeamVerticle.PARAM_ADVERSARY, Collections.singletonList("false"));

        req.setParams(params);

        final String reply = sendOnBus(SB_TeamVerticle.GET_LIST, req, user.getAccount().getToken());
        JsonArray result = new JsonArray(reply);

        Assert.assertEquals(1, result.size());

        JsonObject team = result.get(0);
        Assert.assertEquals("Cesson-Sevigne A", team.getString("label"));
    }

    /**
     * Tests of getting a list of my adversaries for sandbox and effective
     */
    @Test
    public void getMyAdversaryTeamsList() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_TEAM_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

		/* list of parameters */
        final Map<String, List<String>> params = new HashMap<>();

        params.put(SB_TeamVerticle.PARAM_SANDBOX_ID, Collections.singletonList("558b0efebd2e39cdab651e1f"));
        params.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, Collections.singletonList("550b31f925da07681592db23"));
        params.put(SB_TeamVerticle.PARAM_ENABLE, Collections.singletonList("true"));
        params.put(SB_TeamVerticle.PARAM_ADVERSARY, Collections.singletonList("true"));

        req.setParams(params);

        final String reply = sendOnBus(SB_TeamVerticle.GET_LIST, req, user.getAccount().getToken());
        JsonArray result = new JsonArray(reply);

        Assert.assertEquals(7, result.size());

        JsonObject team = result.get(0);
        Assert.assertEquals("Nantes HBC", team.getString("label"));

        params.put(SB_TeamVerticle.PARAM_LINK_TEAM_ID, Collections.singletonList("552d5e08644a77b3a20afdfe"));

        final String reply2 = sendOnBus(SB_TeamVerticle.GET_LIST, req, user.getAccount().getToken());
        JsonArray result2 = new JsonArray(reply2);

        Assert.assertEquals(6, result2.size());
    }

    /**
     * Tests of getting a  team by Id
     */
    @Test
    public void getByIdOk() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_TEAM_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

		/* list of parameters */
        final Map<String, List<String>> params = new HashMap<>();

        params.put(SB_TeamVerticle.PARAM_ID, Collections.singletonList("552d5e08644a77b3a20afdfe"));

        req.setParams(params);

        final String reply = sendOnBus(SB_TeamVerticle.GET, req, user.getAccount().getToken());
        JsonObject team = new JsonObject(reply);
        Assert.assertEquals("Cesson-Sevigne A", team.getString("label"));

    }

    /**
     * Tests of getting a  team by Id
     */
    @Test
    public void addTeam() {

        populate(POPULATE_ONLY, DATA_USERS);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(user);

        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_LABEL, "TheNewTeam");
        params.putString(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23");
        params.putBoolean(SB_TeamVerticle.PARAM_ENABLE, true);
        params.putBoolean(SB_TeamVerticle.PARAM_ADVERSARY, true);

        req.setBody(params.encode());
        final JsonObject team = new JsonObject(sendOnBus(SB_TeamVerticle.ADD, req, user.getAccount().getToken()));
        System.out.println(team);
        Assert.assertNotNull("id is null", team.getString("_id"));
        Assert.assertEquals("TheNewTeam", team.getString("label"));

    }

    /**
     * Tests of getting a  team by Id
     */
    @Test
    public void updateTeam() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_TEAM_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

		/* lget team */
        final Map<String, List<String>> params = new HashMap<>();
        params.put(SB_TeamVerticle.PARAM_ID, Collections.singletonList("552d5e08644a77b3a20afdfe"));
        req.setParams(params);

        final String reply = sendOnBus(SB_TeamVerticle.GET, req, user.getAccount().getToken());
        JsonObject team = new JsonObject(reply);
        Assert.assertEquals("Cesson-Sevigne A", team.getString("label"));

        /* Update team */
        req.setMethod(Constantes.PUT);
        team.putBoolean(SB_TeamVerticle.PARAM_ENABLE, false);
        req.setBody(team.encode());
        sendOnBus(SB_TeamVerticle.UPDATE, req, user.getAccount().getToken());
        
        /* list of parameters */
        req.setMethod(Constantes.GET);
        final Map<String, List<String>> param2s = new HashMap<>();

        param2s.put(SB_TeamVerticle.PARAM_SANDBOX_ID, Collections.singletonList("558b0efebd2e39cdab651e1f"));
        param2s.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, Collections.singletonList("550b31f925da07681592db23"));
        param2s.put(SB_TeamVerticle.PARAM_ENABLE, Collections.singletonList("true"));
        param2s.put(SB_TeamVerticle.PARAM_ADVERSARY, Collections.singletonList("false"));

        req.setParams(param2s);

        final String reply2 = sendOnBus(SB_TeamVerticle.GET_LIST, req, user.getAccount().getToken());
        JsonArray result = new JsonArray(reply2);

        Assert.assertEquals(0, result.size());

    }

}
