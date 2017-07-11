/* ************************************************************************
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
package com.qaobee.hive.test.api.commons.referencial;

import com.qaobee.hive.api.v1.commons.referencial.ChampionshipRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.qaobee.hive.api.v1.commons.referencial.ChampionshipRoute.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Championship.
 *
 * @author jerome
 */
@Ignore // missing data_championship_Hand.js
public class ChampionshipTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/referencial/championship");

    /**
     * Gets list championships.
     */
    @Test
    public void getListChampionships(TestContext context) {// NOSONAR
        Async async = context.async();
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put(PARAM_ACTIVITY, "ACT-HAND")
                    .put(PARAM_CATEGORY_AGE, "sen")
                    .put(PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL  + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("[0].activityId", is(params.getString(PARAM_ACTIVITY)));

            params.put(ChampionshipRoute.PARAM_PARTICIPANT, new JsonObject()
                    .put("name", "CHAMBERY SAVOIE HB")
                    .put("id", "ID-TEAM-CHAMBERY")
                    .put("structureId", "CHAMBERYSAVOIEHB")
                    .put("type", "team"));
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL  + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("[0].activityId", is(params.getString(PARAM_ACTIVITY)));

            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets list championships with non logged user.
     */
    @Test
    public void getListChampionshipsWithNonLoggedUser(TestContext context) {
        given().when().post(BASE_URL  + "/list")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list championships with wrong http method.
     */
    @Test
    public void getListChampionshipsWithWrongHttpMethod(TestContext context) {
        given().when().get(BASE_URL  + "/list")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list championships with missing params.
     */
    @Test
    public void getListChampionshipsWithMissingParams(TestContext context) {
        Async async = context.async();
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put(PARAM_CATEGORY_AGE, "sen");
            params.put(PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
            List<String> mandatoryParams = Arrays.asList(PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_STRUCTURE);
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, u.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(BASE_URL  + "/list")
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets list championships with wrong params.
     */
    @Test
    public void getListChampionshipsWithWrongParams(TestContext context) {
        Async async = context.async();
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject()
                    .put(PARAM_ACTIVITY, "blabla")
                    .put(PARAM_CATEGORY_AGE, "sen")
                    .put(PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL  + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));


            params.put(PARAM_ACTIVITY, "ACT-HAND");
            params.put(PARAM_STRUCTURE, "12345");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL  + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));

            params.put(PARAM_CATEGORY_AGE, "blabla");
            params.put(PARAM_STRUCTURE, "541168295971d35c1f2d1b5e");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL  + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }


    /**
     * Gets list championships with infra.
     */
    @Test
    public void getListChampionshipsWithInfra(TestContext context) {// NOSONAR
        Async async = context.async();
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put(PARAM_ACTIVITY, "ACT-HAND");
            params.put(PARAM_CATEGORY_AGE, "sen");
            params.put(PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
            JsonObject paramParticipant = new JsonObject();
            paramParticipant.put("id", "ID-PHARE-CHAMBERY");
            paramParticipant.put("type", "infrastructure");
            params.put(ChampionshipRoute.PARAM_PARTICIPANT, paramParticipant);

            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL  + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("[0].participants.id", hasItem(paramParticipant.getString("id")));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets list championships with infra unknown.
     */
    @Test
    public void getListChampionshipsWithInfraUnknown(TestContext context) {
        Async async = context.async();
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put(PARAM_ACTIVITY, "ACT-HAND");
            params.put(PARAM_CATEGORY_AGE, "sen");
            params.put(PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
            JsonObject paramParticipant = new JsonObject();
            paramParticipant.put("id", "ID-Anywhere-But-Not-There");
            paramParticipant.put("type", "infrastructure");
            params.put(ChampionshipRoute.PARAM_PARTICIPANT, paramParticipant);

            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL  + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets championship.
     */
    @Test
    public void getChampionship(TestContext context) {// NOSONAR
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipRoute.PARAM_ID, "559ebfb499f07aa6f04dec76")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is("Championnat du bout du monde"));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets championship with non logged user.
     */
    @Test
    public void getChampionshipWithNonLoggedUser(TestContext context) {
        given().when().get(BASE_URL + "/get")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets championship with wrong http method.
     */
    @Test
    public void getChampionshipWithWrongHttpMethod(TestContext context) {
        given().when().post(BASE_URL + "/get")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets championship with missing params.
     */
    @Test
    public void getChampionshipWithMissingParams(TestContext context) {
        Async async = context.async();
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets championship with wrong params.
     */
    @Test
    public void getChampionshipWithWrongParams(TestContext context) {
        Async async = context.async();
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipRoute.PARAM_ID, "12345")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add championship.
     */
    @Test
    public void addChampionship(TestContext context) {
        Async async = context.async();
        generateLoggedAdminUser().then(u -> {
            populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
            final JsonObject params = getShampionship();

            String id = given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("label", is(params.getString("label")))
                    .extract().path("_id");

            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipRoute.PARAM_ID, id)
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is(params.getString("label")));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add championship with non logged user.
     */
    @Test
    public void addChampionshipWithNonLoggedUser(TestContext context) {
        given().when().post(BASE_URL + "/add")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add championship with non admin user.
     */
    @Test
    public void addChampionshipWithNonAdminUser(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(BASE_URL + "/add")
                    .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                    .body(CODE, is(ExceptionCodes.NOT_ADMIN.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }


    /**
     * Add championship with wrong http method.
     */
    @Test
    public void addChampionshipWithWrongHttpMethod(TestContext context) {
        given().when().get(BASE_URL + "/add")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add championship with missing params.
     */
    @Test
    public void addChampionshipWithMissingParams(TestContext context) {
        Async async = context.async();
        generateLoggedAdminUser().then(u -> {
            populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
            final JsonObject params = getShampionship();
            params.remove("label");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/add")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }


    /**
     * Update championship.
     */
    @Test
    public void updateChampionship(TestContext context) {// NOSONAR
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        generateLoggedAdminUser().then(u -> {

            final JsonObject shampionship = new JsonObject(given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipRoute.PARAM_ID, "559ebfb499f07aa6f04dec76")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .extract().asString());

            shampionship.put("label", "blabla");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(shampionship.encode())
                    .when().post(BASE_URL + "/update")
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is(shampionship.getString("label")));

            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipRoute.PARAM_ID, "559ebfb499f07aa6f04dec76")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is(shampionship.getString("label")));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Update championship with non logged user.
     */
    @Test
    public void updateChampionshipWithNonLoggedUser(TestContext context) {
        given().when().post(BASE_URL + "/update")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update championship with non admin user.
     */
    @Test
    public void updateChampionshipWithNonAdminUser(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(BASE_URL + "/update")
                    .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                    .body(CODE, is(ExceptionCodes.NOT_ADMIN.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }


    /**
     * Update championship with wrong http method.
     */
    @Test
    public void updateChampionshipWithWrongHttpMethod(TestContext context) {
        given().when().get(BASE_URL + "/update")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update championship with missing params.
     */
    @Test
    public void updateChampionshipWithMissingParams(TestContext context) {
        Async async = context.async();
        generateLoggedAdminUser().then(u -> {
            populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
            final JsonObject params = getShampionship();
            params.remove("label");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/update")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    private JsonObject getShampionship() {
        return new JsonObject()
                .put(ChampionshipRoute.PARAM_LABEL, "Mon championnat")
                .put(ChampionshipRoute.PARAM_LEVEL_GAME, new JsonObject()
                        .put(CODE, "R")
                        .put("label", "regional"))
                .put(ChampionshipRoute.PARAM_SUB_LEVEL_GAME, "Honneur regional")
                .put(ChampionshipRoute.PARAM_POOL, "Cocotte")
                .put(ChampionshipRoute.PARAM_INSTANCE, "Ligue du Boukhistan")
                .put(PARAM_ACTIVITY, "ACT-HAND")
                .put(PARAM_CATEGORY_AGE, new JsonObject()
                        .put(CODE, "sen")
                        .put("label", "senior")
                        .put("ageMax", 34)
                        .put("ageMin", 20)
                        .put("genre", "gender.male"))
                .put(ChampionshipRoute.PARAM_SEASON_CODE, "SAI-2014")
                .put(ChampionshipRoute.PARAM_LIST_PARTICIPANTS, new JsonArray().add(new JsonObject()
                        .put("id", "mon-id")
                        .put("name", "participantName")
                        .put("structureId", "participantStructureId")
                        .put("type", "team")));
    }

}
