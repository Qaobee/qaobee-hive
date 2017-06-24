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
package com.qaobee.hive.test.api.commons.referencial;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.commons.referencial.ChampionshipVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * The type Championship.
 *
 * @author jerome
 */
@Ignore
public class ChampionshipTest extends VertxJunitSupport {

    /**
     * Gets list championships.
     */
    @Ignore
    @Test
    public void getListChampionships() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND")
                    .put(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen")
                    .put(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("[0].activityId", is(params.getString(ChampionshipVerticle.PARAM_ACTIVITY)));

            params.put(ChampionshipVerticle.PARAM_PARTICIPANT, new JsonObject()
                    .put("name", "CHAMBERY SAVOIE HB")
                    .put("id", "ID-TEAM-CHAMBERY")
                    .put("structureId", "CHAMBERYSAVOIEHB")
                    .put("type", "team"));
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("[0].activityId", is(params.getString(ChampionshipVerticle.PARAM_ACTIVITY)));
        });
    }

    /**
     * Gets list championships with non logged user.
     */
    @Test
    public void getListChampionshipsWithNonLoggedUser() {
        given().when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list championships with wrong http method.
     */
    @Test
    public void getListChampionshipsWithWrongHttpMethod() {
        given().when().get(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list championships with missing params.
     */
    @Test
    public void getListChampionshipsWithMissingParams() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
            params.put(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
            List<String> mandatoryParams = Arrays.asList(Main.getRules().get(ChampionshipVerticle.GET_LIST).mandatoryParams());
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, u.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(ChampionshipVerticle.GET_LIST))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
        });
    }

    /**
     * Gets list championships with wrong params.
     */
    @Test
    public void getListChampionshipsWithWrongParams() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject()
                    .put(ChampionshipVerticle.PARAM_ACTIVITY, "blabla")
                    .put(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen")
                    .put(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));


            params.put(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
            params.put(ChampionshipVerticle.PARAM_STRUCTURE, "12345");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));

            params.put(ChampionshipVerticle.PARAM_CATEGORY_AGE, "blabla");
            params.put(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
        });
    }


    /**
     * Gets list championships with infra.
     */
    @Ignore
    @Test
    public void getListChampionshipsWithInfra() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
            params.put(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
            params.put(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
            JsonObject paramParticipant = new JsonObject();
            paramParticipant.put("id", "ID-PHARE-CHAMBERY");
            paramParticipant.put("type", "infrastructure");
            params.put(ChampionshipVerticle.PARAM_PARTICIPANT, paramParticipant);

            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("[0].participants.id", hasItem(paramParticipant.getString("id")));
        });
    }

    /**
     * Gets list championships with infra unknown.
     */
    @Test
    public void getListChampionshipsWithInfraUnknown() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
            params.put(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
            params.put(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
            JsonObject paramParticipant = new JsonObject();
            paramParticipant.put("id", "ID-Anywhere-But-Not-There");
            paramParticipant.put("type", "infrastructure");
            params.put(ChampionshipVerticle.PARAM_PARTICIPANT, paramParticipant);

            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
        });
    }

    /**
     * Gets championship.
     */
    @Ignore
    @Test
    public void getChampionship() {
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipVerticle.PARAM_ID, "559ebfb499f07aa6f04dec76")
                    .when().get(getURL(ChampionshipVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is("Championnat du bout du monde"));
        });
    }

    /**
     * Gets championship with non logged user.
     */
    @Test
    public void getChampionshipWithNonLoggedUser() {
        given().when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets championship with wrong http method.
     */
    @Test
    public void getChampionshipWithWrongHttpMethod() {
        given().when().post(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets championship with missing params.
     */
    @Test
    public void getChampionshipWithMissingParams() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(ChampionshipVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Gets championship with wrong params.
     */
    @Test
    public void getChampionshipWithWrongParams() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipVerticle.PARAM_ID, "12345")
                    .when().get(getURL(ChampionshipVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
        });
    }

    /**
     * Add championship.
     */
    @Test
    public void addChampionship() {
        generateLoggedAdminUser().then(u -> {
            populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
            final JsonObject params = getShampionship();

            String id = given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.ADD))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("label", is(params.getString("label")))
                    .extract().path("_id");

            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipVerticle.PARAM_ID, id)
                    .when().get(getURL(ChampionshipVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is(params.getString("label")));
        });
    }

    /**
     * Add championship with non logged user.
     */
    @Test
    public void addChampionshipWithNonLoggedUser() {
        given().when().post(getURL(ChampionshipVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add championship with non admin user.
     */
    @Test
    public void addChampionshipWithNonAdminUser() {
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(getURL(ChampionshipVerticle.ADD))
                    .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                    .body(CODE, is(ExceptionCodes.NOT_ADMIN.toString()));
        });
    }


    /**
     * Add championship with wrong http method.
     */
    @Test
    public void addChampionshipWithWrongHttpMethod() {
        given().when().get(getURL(ChampionshipVerticle.ADD))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add championship with missing params.
     */
    @Test
    public void addChampionshipWithMissingParams() {
        generateLoggedAdminUser().then(u -> {
            populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
            final JsonObject params = getShampionship();
            params.remove("label");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.ADD))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }


    /**
     * Update championship.
     */
    @Ignore
    @Test
    public void updateChampionship() {
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        generateLoggedAdminUser().then(u -> {

            final JsonObject shampionship = new JsonObject(given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipVerticle.PARAM_ID, "559ebfb499f07aa6f04dec76")
                    .when().get(getURL(ChampionshipVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .extract().asString());

            shampionship.put("label", "blabla");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(shampionship.encode())
                    .when().post(getURL(ChampionshipVerticle.UPDATE))
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is(shampionship.getString("label")));

            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ChampionshipVerticle.PARAM_ID, "559ebfb499f07aa6f04dec76")
                    .when().get(getURL(ChampionshipVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is(shampionship.getString("label")));
        });
    }

    /**
     * Update championship with non logged user.
     */
    @Test
    public void updateChampionshipWithNonLoggedUser() {
        given().when().post(getURL(ChampionshipVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update championship with non admin user.
     */
    @Test
    public void updateChampionshipWithNonAdminUser() {
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(getURL(ChampionshipVerticle.UPDATE))
                    .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                    .body(CODE, is(ExceptionCodes.NOT_ADMIN.toString()));
        });
    }


    /**
     * Update championship with wrong http method.
     */
    @Test
    public void updateChampionshipWithWrongHttpMethod() {
        given().when().get(getURL(ChampionshipVerticle.UPDATE))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update championship with missing params.
     */
    @Test
    public void updateChampionshipWithMissingParams() {
        generateLoggedAdminUser().then(u -> {
            populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
            final JsonObject params = getShampionship();
            params.remove("label");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(ChampionshipVerticle.UPDATE))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    private JsonObject getShampionship() {
        return new JsonObject()
                .put(ChampionshipVerticle.PARAM_LABEL, "Mon championnat")
                .put(ChampionshipVerticle.PARAM_LEVEL_GAME, new JsonObject()
                        .put(CODE, "R")
                        .put("label", "regional"))
                .put(ChampionshipVerticle.PARAM_SUB_LEVEL_GAME, "Honneur regional")
                .put(ChampionshipVerticle.PARAM_POOL, "Cocotte")
                .put(ChampionshipVerticle.PARAM_INSTANCE, "Ligue du Boukhistan")
                .put(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND")
                .put(ChampionshipVerticle.PARAM_CATEGORY_AGE, new JsonObject()
                        .put(CODE, "sen")
                        .put("label", "senior")
                        .put("ageMax", 34)
                        .put("ageMin", 20)
                        .put("genre", "gender.male"))
                .put(ChampionshipVerticle.PARAM_SEASON_CODE, "SAI-2014")
                .put(ChampionshipVerticle.PARAM_LIST_PARTICIPANTS, new JsonArray().add(new JsonObject()
                        .put("id", "mon-id")
                        .put("name", "participantName")
                        .put("structureId", "participantStructureId")
                        .put("type", "team")));
    }

}
