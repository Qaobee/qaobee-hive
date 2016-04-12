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
 * The type Championship test.
 *
 * @author jerome
 */
public class ChampionshipTest extends VertxJunitSupport {

    /**
     * Gets list championships test.
     */
    @Test
    public void getListChampionshipsTest() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

        given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("[0].activityId", is(params.getString(ChampionshipVerticle.PARAM_ACTIVITY)));
    }

    /**
     * Gets list championships with non logged user test.
     */
    @Test
    public void getListChampionshipsWithNonLoggedUserTest() {
        given().when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list championships with wrong http method test.
     */
    @Test
    public void getListChampionshipsWithWrongHttpMethodTest() {
        given().when().get(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets list championships with missing params test.
     */
    @Test
    public void getListChampionshipsWithMissingParamsTest() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

        given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets list championships with wrong params test.
     */
    @Test
    public void getListChampionshipsWithWrongParamsTest() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "blabla");
        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON

        given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));


        params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "12345");
        given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));

        params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "blabla");
        params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e");
        given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));
    }


    /**
     * Gets list championships with infra test.
     */
    @Test
    public void getListChampionshipsWithInfraTest() {
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

        given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("[0].participants.id", hasItem(paramParticipant.getString("id")));
    }

    /**
     * Gets list championships with infra unknown test.
     */
    @Test
    public void getListChampionshipsWithInfraUnknownTest() {
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

        given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));
    }

    /**
     * Gets championship test.
     */
    @Test
    public void getChampionshipTest() {
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        User u = generateLoggedUser();
        given().header("token", u.getAccount().getToken())
                .queryParam(ChampionshipVerticle.PARAM_ID, "559ebfb499f07aa6f04dec76")
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is("Championnat du bout du monde"));
    }

    /**
     * Gets championship with non logged user test.
     */
    @Test
    public void getChampionshipWithNonLoggedUserTest() {
        given().when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets championship with wrong http method test.
     */
    @Test
    public void getChampionshipWithWrongHttpMethodTest() {
        given().when().post(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets championship with missing params test.
     */
    @Test
    public void getChampionshipWithMissingParamsTest() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        given().header("token", u.getAccount().getToken())
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets championship with wrong params test.
     */
    @Test
    public void getChampionshipWithWrongParamsTest() {
        // Populate default value
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        // User connected
        User u = generateLoggedUser();
        given().header("token", u.getAccount().getToken())
                .queryParam(ChampionshipVerticle.PARAM_ID, "12345")
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body("code", is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Add championship test.
     */
    @Test
    public void addChampionshipTest() {
        User u = generateLoggedAdminUser();
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        final JsonObject params = getShampionship();

        String id = given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.ADD))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("label", is(params.getString("label")))
                .extract().path("_id");

        given().header("token", u.getAccount().getToken())
                .queryParam(ChampionshipVerticle.PARAM_ID, id)
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is(params.getString("label")));
    }

    /**
     * Add championship with non logged user test.
     */
    @Test
    public void addChampionshipWithNonLoggedUserTest() {
        given().when().post(getURL(ChampionshipVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add championship with non admin user test.
     */
    @Test
    public void addChampionshipWithNonAdminUserTest() {
        User u = generateLoggedUser();
        given().header("token", u.getAccount().getToken())
                .when().post(getURL(ChampionshipVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                .body("code", is(ExceptionCodes.NOT_ADMIN.toString()));
    }


    /**
     * Add championship with wrong http method test.
     */
    @Test
    public void addChampionshipWithWrongHttpMethodTest() {
        given().when().get(getURL(ChampionshipVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Add championship with missing params test.
     */
    @Test
    public void addChampionshipWithMissingParamsTest() {
        User u = generateLoggedAdminUser();
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        final JsonObject params = getShampionship();
        params.removeField("label");
        given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }


    /**
     * Update championship test.
     */
    @Test
    public void updateChampionshipTest() {
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        User u = generateLoggedAdminUser();

        final JsonObject shampionship = new JsonObject(given().header("token", u.getAccount().getToken())
                .queryParam(ChampionshipVerticle.PARAM_ID, "559ebfb499f07aa6f04dec76")
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .extract().asString());

        shampionship.putString("label", "blabla");
        given().header("token", u.getAccount().getToken())
                .body(shampionship.encode())
                .when().post(getURL(ChampionshipVerticle.UPDATE))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is(shampionship.getString("label")));

        given().header("token", u.getAccount().getToken())
                .queryParam(ChampionshipVerticle.PARAM_ID, "559ebfb499f07aa6f04dec76")
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is(shampionship.getString("label")));
    }

    /**
     * Update championship with non logged user test.
     */
    @Test
    public void updateChampionshipWithNonLoggedUserTest() {
        given().when().post(getURL(ChampionshipVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update championship with non admin user test.
     */
    @Test
    public void updateChampionshipWithNonAdminUserTest() {
        User u = generateLoggedUser();
        given().header("token", u.getAccount().getToken())
                .when().post(getURL(ChampionshipVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                .body("code", is(ExceptionCodes.NOT_ADMIN.toString()));
    }


    /**
     * Update championship with wrong http method test.
     */
    @Test
    public void updateChampionshipWithWrongHttpMethodTest() {
        given().when().get(getURL(ChampionshipVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Update championship with missing params test.
     */
    @Test
    public void updateChampionshipWithMissingParamsTest() {
        User u = generateLoggedAdminUser();
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        final JsonObject params = getShampionship();
        params.removeField("label");
        given().header("token", u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(ChampionshipVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    private JsonObject getShampionship() {
        JsonObject shampionship = new JsonObject();
        shampionship.putString(ChampionshipVerticle.PARAM_LABEL, "Mon championnat");
        JsonObject jsonObject = new JsonObject();
        jsonObject.putString("code", "R");
        jsonObject.putString("label", "regional");
        shampionship.putObject(ChampionshipVerticle.PARAM_LEVEL_GAME, jsonObject);
        shampionship.putString(ChampionshipVerticle.PARAM_SUB_LEVEL_GAME, "Honneur regional");
        shampionship.putString(ChampionshipVerticle.PARAM_POOL, "Cocotte");
        shampionship.putString(ChampionshipVerticle.PARAM_INSTANCE, "Ligue du Boukhistan");
        shampionship.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
        jsonObject = new JsonObject();
        jsonObject.putString("code", "sen");
        jsonObject.putString("label", "senior");
        jsonObject.putNumber("ageMax", 34);
        jsonObject.putNumber("ageMin", 20);
        jsonObject.putString("genre", "gender.male");
        shampionship.putObject(ChampionshipVerticle.PARAM_CATEGORY_AGE, jsonObject);
        shampionship.putString(ChampionshipVerticle.PARAM_SEASON_CODE, "SAI-2014");
        jsonObject = new JsonObject();
        jsonObject.putString("id", "mon-id");
        jsonObject.putString("name", "participantName");
        jsonObject.putString("structureId", "participantStructureId");
        jsonObject.putString("type", "team");
        shampionship.putArray(ChampionshipVerticle.PARAM_LIST_PARTICIPANTS, new JsonArray().addObject(jsonObject));
        return shampionship;
    }

}
