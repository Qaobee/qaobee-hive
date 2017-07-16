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

import com.qaobee.hive.api.MainAPI;
import com.qaobee.hive.api.v1.sandbox.effective.SB_TeamVerticle;
import com.qaobee.hive.api.v1.sandbox.event.SB_EventVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sb team testBodyParams.
 *
 * @author cke
 */
public class SB_TeamTest extends VertxJunitSupport {
    /**
     * Gets my teams list.
     */
    @Test
    public void getMyTeamsList(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        final Map<String, String> params = new HashMap<>();
        params.put(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f");
        params.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23");
        params.put(SB_TeamVerticle.PARAM_ENABLE, "true");
        params.put(SB_TeamVerticle.PARAM_ADVERSARY, "false");
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParams(params)
                    .when().get(getURL(SB_TeamVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("label", hasItem("Cesson-Sevigne A"));
            async.complete();
        });
        async.await(TIMEOUT);
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
    public void getMyTeamsListeWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            List<String> mandatoryParams = Arrays.asList(MainAPI.getRules().get(SB_TeamVerticle.GET_LIST).mandatoryParams());
            final Map<String, String> params = new HashMap<>();
            params.put(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f");
            params.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23");
            params.put(SB_TeamVerticle.PARAM_ENABLE, "true");
            params.put(SB_TeamVerticle.PARAM_ADVERSARY, "false");

            params.keySet().stream().filter(mandatoryParams::contains).forEach(k -> {
                Map<String, String> params2 = new HashMap<>(params);
                params2.remove(k);
                given().header(TOKEN, user.result().getAccount().getToken())
                        .queryParams(params2)
                        .when().get(getURL(SB_TeamVerticle.GET_LIST))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets my adversary teams list.
     */
    @Test
    public void getMyAdversaryTeamsList(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        final Map<String, String> params = new HashMap<>();
        params.put(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f");
        params.put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23");
        params.put(SB_TeamVerticle.PARAM_ENABLE, "true");
        params.put(SB_TeamVerticle.PARAM_ADVERSARY, "true");
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParams(params)
                    .when().get(getURL(SB_TeamVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(7))
                    .body("label", hasItem("AIX En Provence HB"))
                    .body("label", hasItem("Nantes HBC"));

            params.put(SB_TeamVerticle.PARAM_LINK_TEAM_ID, "552d5e08644a77b3a20afdfe");

            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParams(params)
                    .when().get(getURL(SB_TeamVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(6));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets by id.
     */
    @Test
    public void getById(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_TeamVerticle.PARAM_ID, "552d5e08644a77b3a20afdfe")
                    .when().get(getURL(SB_TeamVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("label", is("Cesson-Sevigne A"));
            async.complete();
        });
        async.await(TIMEOUT);
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
    public void getByIdWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().get(getURL(SB_TeamVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Add team.
     */
    @Test
    public void addTeam(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        final JsonObject params = new JsonObject()
                .put(SB_EventVerticle.PARAM_LABEL, "TheNewTeam")
                .put(SB_TeamVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f")
                .put(SB_TeamVerticle.PARAM_EFFECTIVE_ID, "550b31f925da07681592db23")
                .put(SB_TeamVerticle.PARAM_ENABLE, true)
                .put(SB_TeamVerticle.PARAM_ADVERSARY, true);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(SB_TeamVerticle.ADD))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("label", is("TheNewTeam"));
            async.complete();
        });
        async.await(TIMEOUT);
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
    public void updateTeam(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {

            JsonObject team = new JsonObject(given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_TeamVerticle.PARAM_ID, "552d5e08644a77b3a20afdfe")
                    .when().get(getURL(SB_TeamVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("label", is("Cesson-Sevigne A")).extract().asString());
            team.put(SB_TeamVerticle.PARAM_ENABLE, false);

            given().header(TOKEN, user.result().getAccount().getToken())
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

            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParams(params)
                    .when().get(getURL(SB_TeamVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
            async.complete();
        });
        async.await(TIMEOUT);
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
