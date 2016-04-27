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

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.sandbox.agenda.SB_EventVerticle;
import com.qaobee.hive.api.v1.sandbox.effective.SB_TeamVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sb team test.
 *
 * @author cke
 */
public class SB_TeamTest extends VertxJunitSupport {
    /**
     * Gets my teams list.
     */
    @Test
    public void getMyTeamsList() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_TEAM_HAND);
        final Map<String, String> params = new HashMap<>();
        params.put(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f");
        params.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23");
        params.put(SB_TeamVerticle.PARAM_ENABLE, "true");
        params.put(SB_TeamVerticle.PARAM_ADVERSARY, "false");

        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .queryParams(params)
                .when().get(getURL(SB_TeamVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("label", hasItem("Cesson-Sevigne A"));
    }

    /**
     * Gets my teams list with non logged user.
     */
    @Test
    public void getMyTeamsListWithNonLoggedUser() {
        given().when().get(getURL(SB_TeamVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets my teams list with wrong http method.
     */
    @Test
    public void getMyTeamsListWithWrongHttpMethod() {
        given().when().post(getURL(SB_TeamVerticle.GET_LIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets my teams liste with missing parameters.
     */
    @Test
    public void getMyTeamsListeWithMissingParameters() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_TEAM_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_TeamVerticle.GET_LIST).mandatoryParams());
        final Map<String, String> params = new HashMap<>();
        params.put(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f");
        params.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23");
        params.put(SB_TeamVerticle.PARAM_ENABLE, "true");
        params.put(SB_TeamVerticle.PARAM_ADVERSARY, "false");

        for (String k : params.keySet()) {
            if (mandatoryParams.contains(k)) {
                Map<String, String> params2 = new HashMap<>(params);
                params2.remove(k);
                given().header(TOKEN, user.getAccount().getToken())
                        .queryParams(params2)
                        .when().get(getURL(SB_TeamVerticle.GET_LIST))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            }
        }
    }

    /**
     * Gets my adversary teams list.
     */
    @Test
    public void getMyAdversaryTeamsList() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_TEAM_HAND);
        final Map<String, String> params = new HashMap<>();
        params.put(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f");
        params.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23");
        params.put(SB_TeamVerticle.PARAM_ENABLE, "true");
        params.put(SB_TeamVerticle.PARAM_ADVERSARY, "true");

        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .queryParams(params)
                .when().get(getURL(SB_TeamVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(7))
                .body("label", hasItem("AIX En Provence HB"))
                .body("label", hasItem("Nantes HBC"));

        params.put(SB_TeamVerticle.PARAM_LINK_TEAM_ID, "552d5e08644a77b3a20afdfe");

        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .queryParams(params)
                .when().get(getURL(SB_TeamVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(6));
    }

    /**
     * Gets by id.
     */
    @Test
    public void getById() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_TEAM_HAND);
        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .queryParam(SB_TeamVerticle.PARAM_ID, "552d5e08644a77b3a20afdfe")
                .when().get(getURL(SB_TeamVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("label", is("Cesson-Sevigne A"));
    }

    /**
     * Gets by id with non logged user.
     */
    @Test
    public void getByIdWithNonLoggedUser() {
        given().when().get(getURL(SB_TeamVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets by id with wrong http method.
     */
    @Test
    public void getByIdWithWrongHttpMethod() {
        given().when().post(getURL(SB_TeamVerticle.GET))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets by id with missing parameters.
     */
    @Test
    public void getByIdWithMissingParameters() {
        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .when().get(getURL(SB_TeamVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Add team.
     */
    @Test
    public void addTeam() {
        populate(POPULATE_ONLY, DATA_USERS);
        final JsonObject params = new JsonObject()
                .putString(SB_EventVerticle.PARAM_LABEL, "TheNewTeam")
                .putString(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f")
                .putString(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23")
                .putBoolean(SB_TeamVerticle.PARAM_ENABLE, true)
                .putBoolean(SB_TeamVerticle.PARAM_ADVERSARY, true);

        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_TeamVerticle.ADD))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("label", is("TheNewTeam"));
    }

    /**
     * Add team with non logged user.
     */
    @Test
    public void addTeamWithNonLoggedUser() {
        given().when().post(getURL(SB_TeamVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add team with wrong http method.
     */
    @Test
    public void addTeamWithWrongHttpMethod() {
        given().when().get(getURL(SB_TeamVerticle.ADD))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update team.
     */
    @Test
    public void updateTeam() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_TEAM_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");

        JsonObject team = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_TeamVerticle.PARAM_ID, "552d5e08644a77b3a20afdfe")
                .when().get(getURL(SB_TeamVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("label", is("Cesson-Sevigne A")).extract().asString());
        team.putBoolean(SB_TeamVerticle.PARAM_ENABLE, false);

        given().header(TOKEN, user.getAccount().getToken())
                .body(team.encode())
                .when().put(getURL(SB_TeamVerticle.UPDATE))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("label", is("Cesson-Sevigne A"));

        final Map<String, String> params = new HashMap<>();
        params.put(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f");
        params.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23");
        params.put(SB_TeamVerticle.PARAM_ENABLE, "true");
        params.put(SB_TeamVerticle.PARAM_ADVERSARY, "false");

        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .queryParams(params)
                .when().get(getURL(SB_TeamVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));
    }

    /**
     * Update team with non logged user.
     */
    @Test
    public void updateTeamWithNonLoggedUser() {
        given().when().put(getURL(SB_TeamVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update team with wrong http method.
     */
    @Test
    public void updateTeamWithWrongHttpMethod() {
        given().when().get(getURL(SB_TeamVerticle.UPDATE))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }
}
