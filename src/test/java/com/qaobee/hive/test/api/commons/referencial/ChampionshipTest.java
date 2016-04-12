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
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * @author jerome
 */
public class ChampionshipTest extends VertxJunitSupport {

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

    @Test
    public void getListChampionshipsWithNonLoggedUserTest() {
        given().when().post(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    @Test
    public void getListChampionshipsWithWrongHttpMthodTest() {
        given().when().get(getURL(ChampionshipVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

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
                .body("[0].participants.id" , hasItem(paramParticipant.getString("id")));
    }

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

    @Test
    public void getChampionshipTest() {
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);User u = generateLoggedUser();
        given().header("token", u.getAccount().getToken())
                .queryParam(ChampionshipVerticle.PARAM_ID, "559ebfb499f07aa6f04dec76")
                .when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("label",notNullValue())
                .body("label",is("Championnat du bout du monde"));
    }

    @Test
    public void getChampionshipWithNonLoggedUserTest() {
        given().when().get(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    @Test
    public void getChampionshipWithWrongHttpMthodTest() {
        given().when().post(getURL(ChampionshipVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

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

    @Test
    public void addChampionshipTest() {
        User user = generateLoggedAdminUser();
        RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);

        final JsonObject params = new JsonObject();
        params.putString(ChampionshipVerticle.PARAM_LABEL, "Mon championnat");

        JsonObject jsonObject = new JsonObject();
        jsonObject.putString("code", "R");
        jsonObject.putString("label", "regional");
        params.putObject(ChampionshipVerticle.PARAM_LEVEL_GAME, jsonObject);

        params.putString(ChampionshipVerticle.PARAM_SUB_LEVEL_GAME, "Honneur regional");
        params.putString(ChampionshipVerticle.PARAM_POOL, "Cocotte");
        params.putString(ChampionshipVerticle.PARAM_INSTANCE, "Ligue du Boukhistan");
        params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");

        jsonObject = new JsonObject();
        jsonObject.putString("code", "sen");
        jsonObject.putString("label", "senior");
        jsonObject.putNumber("ageMax", 34);
        jsonObject.putNumber("ageMin", 20);
        jsonObject.putString("genre", "gender.male");
        params.putObject(ChampionshipVerticle.PARAM_CATEGORY_AGE, jsonObject);

        params.putString(ChampionshipVerticle.PARAM_SEASON_CODE, "SAI-2014");

        jsonObject = new JsonObject();
        jsonObject.putString("id", "mon-id");
        jsonObject.putString("name", "participantName");
        jsonObject.putString("structureId", "participantStructureId");
        jsonObject.putString("type", "team");
        params.putArray(ChampionshipVerticle.PARAM_LIST_PARTICIPANTS, new JsonArray().addObject(jsonObject));

        req.setBody(params.encode());

        final JsonObject result = new JsonObject(sendOnBus(ChampionshipVerticle.ADD, req, user.getAccount().getToken()));
        Assert.assertNotNull("id is null", result.getString("_id"));

        // Test si donnée présente et accessible
        req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final HashMap<String, List<String>> params2 = new HashMap<>();
        params2.put(ChampionshipVerticle.PARAM_ID, Collections.singletonList(result.getString("_id")));
        req.setParams(params2);

        final JsonObject reply = new JsonObject(sendOnBus(ChampionshipVerticle.GET, req, user.getAccount().getToken()));
        System.out.println(reply);
        Assert.assertEquals("addChampionShipTest", "Mon championnat", reply.getString("label"));
    }

    @Test
    public void updateChampionshipTest() {
        populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
        User user = generateLoggedAdminUser();
        RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        HashMap<String, List<String>> params = new HashMap<>();
        params.put(ChampionshipVerticle.PARAM_ID, Collections.singletonList("559ebfb499f07aa6f04dec76"));
        req.setParams(params);

        JsonObject reply = new JsonObject(sendOnBus(ChampionshipVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertEquals("updateChampionShipTest", "Championnat du bout du monde", reply.getString("label"));

        // Return the _id to "id" param
        reply.putString(ChampionshipVerticle.PARAM_ID, reply.getString("_id"));

        // Update a field
        final String newLabel = "Autre";
        reply.putString("label", newLabel);

        // Prepare update request
        req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        req.setBody(reply.encode());

        reply = new JsonObject(sendOnBus(ChampionshipVerticle.UPDATE, req, user.getAccount().getToken()));
        Assert.assertEquals("updateChampionShipTest - result update", newLabel, reply.getString("label"));

        // Just to see if it's really updated
        req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        params = new HashMap<>();
        params.put(ChampionshipVerticle.PARAM_ID, Collections.singletonList("559ebfb499f07aa6f04dec76"));
        req.setParams(params);

        reply = new JsonObject(sendOnBus(ChampionshipVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertEquals("updateChampionShipTest - get check", newLabel, reply.getString("label"));
    }

}
