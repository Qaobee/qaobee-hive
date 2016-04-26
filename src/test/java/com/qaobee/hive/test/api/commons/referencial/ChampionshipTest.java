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

import com.qaobee.hive.api.v1.commons.referencial.ChampionshipVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Championship.
 *
 * @author jerome
 */
public class ChampionshipTest extends VertxJunitSupport {

    /**
     * Gets list championships.
     */
    @Test
    public void getListChampionships() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("[0].activityId", is(params.getString(ChampionshipVerticle.PARAM_ACTIVITY)));

        params.putObject(ChampionshipVerticle.PARAM_PARTICIPANT, new JsonObject()
                .putString("name", "CHAMBERY SAVOIE HB")
                .putString("id", "ID-TEAM-CHAMBERY")
                .putString("structureId", "CHAMBERYSAVOIEHB")
                .putString("type", "team"));
        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("[0].activityId", is(params.getString(ChampionshipVerticle.PARAM_ACTIVITY)));
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
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets list championships with wrong params.
     */
    @Test
    public void getListChampionshipsWithWrongParams() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "blabla");
        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));


        params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "12345");
        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));

        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "blabla");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e");
        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));
    }


    /**
     * Gets list championships with infra.
     */
    @Test
    public void getListChampionshipsWithInfra() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
        JsonObject paramParticipant = new JsonObject();
        paramParticipant.putString("id", "ID-PHARE-CHAMBERY");
        paramParticipant.putString("type", "infrastructure");
        params.putObject(ChampionshipVerticle.PARAM_PARTICIPANT, paramParticipant);

        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("[0].participants.id", hasItem(paramParticipant.getString("id")));
    }

    /**
     * Gets list championships with infra unknown.
     */
    @Test
    public void getListChampionshipsWithInfraUnknown() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
        JsonObject paramParticipant = new JsonObject();
        paramParticipant.putString("id", "ID-Anywhere-But-Not-There");
        paramParticipant.putString("type", "infrastructure");
        params.putObject(ChampionshipVerticle.PARAM_PARTICIPANT, paramParticipant);

        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));
    }

    /**
     * Gets championship.
     */
    @Test
    public void getChampionship() {
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(ChampionshipVerticle.PARAM_ID, "559ebfb499f07aa6f04dec76")
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is("Championnat du bout du monde"));
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
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets championship with wrong params.
     */
    @Test
    public void getChampionshipWithWrongParams() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(ChampionshipVerticle.PARAM_ID, "12345")
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Add championship.
     */
    @Test
    public void addChampionship() {
        User u = generateLoggedAdminUser();
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
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
                .when().post(getURL(ChampionshipVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                .body(CODE, is(ExceptionCodes.NOT_ADMIN.toString()));
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
        User u = generateLoggedAdminUser();
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        final JsonObject params = getShampionship();
        params.removeField("label");
        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }


    /**
     * Update championship.
     */
    @Test
    public void updateChampionship() {
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        User u = generateLoggedAdminUser();

        final JsonObject shampionship = new JsonObject(given().header(TOKEN, u.getAccount().getToken())
                .queryParam(ChampionshipVerticle.PARAM_ID, "559ebfb499f07aa6f04dec76")
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .extract().asString());

        shampionship.putString("label", "blabla");
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
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
                .when().post(getURL(ChampionshipVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                .body(CODE, is(ExceptionCodes.NOT_ADMIN.toString()));
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
        User u = generateLoggedAdminUser();
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        final JsonObject params = getShampionship();
        params.removeField("label");
        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    private JsonObject getShampionship() {
        return new JsonObject()
                .putString(ChampionshipVerticle.PARAM_LABEL, "Mon championnat")
                .putObject(ChampionshipVerticle.PARAM_LEVEL_GAME, new JsonObject()
                        .putString(CODE, "R")
                        .putString("label", "regional"))
                .putString(ChampionshipVerticle.PARAM_SUB_LEVEL_GAME, "Honneur regional")
                .putString(ChampionshipVerticle.PARAM_POOL, "Cocotte")
                .putString(ChampionshipVerticle.PARAM_INSTANCE, "Ligue du Boukhistan")
                .putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND")
                .putObject(ChampionshipVerticle.PARAM_CATEGORY_AGE, new JsonObject()
                        .putString(CODE, "sen")
                        .putString("label", "senior")
                        .putNumber("ageMax", 34)
                        .putNumber("ageMin", 20)
                        .putString("genre", "gender.male"))
                .putString(ChampionshipVerticle.PARAM_SEASON_CODE, "SAI-2014")
                .putArray(ChampionshipVerticle.PARAM_LIST_PARTICIPANTS, new JsonArray().addObject(new JsonObject()
                        .putString("id", "mon-id")
                        .putString("name", "participantName")
                        .putString("structureId", "participantStructureId")
                        .putString("type", "team")));
    }

}
