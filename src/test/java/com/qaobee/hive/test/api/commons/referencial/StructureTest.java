/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */
package com.qaobee.hive.test.api.commons.referencial;

import com.qaobee.hive.api.v1.commons.referencial.StructureVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Structure test.
 *
 * @author cke
 */
public class StructureTest extends VertxJunitSupport {

    /**
     * Gets structure by id.
     */
    @Test
    public void getStructureById() {
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(StructureVerticle.PARAM_ID, "541168295971d35c1f2d1b5e")
                .when().get(getURL(StructureVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(StructureVerticle.PARAM_LABEL, notNullValue())
                .body(StructureVerticle.PARAM_LABEL, is("Dunkerque Handball"));
    }

    /**
     * Gets structure by id with non logged user test.
     */
    @Test
    public void getStructureByIdWithNonLoggedUserTest() {
        given().when().get(getURL(StructureVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets structure by id with wrong http method test.
     */
    @Test
    public void getStructureByIdWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .post(getURL(StructureVerticle.GET))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets structure by id with missing parameter test.
     */
    @Test
    public void getStructureByIdWithMissingParameterTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .get(getURL(StructureVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets structures list.
     */
    @Test
    public void getStructuresList() {
        populate(POPULATE_ONLY, DATA_STRUCTURE, SETTINGS_COUNTRY);
        JsonObject param = new JsonObject()
                .putString(StructureVerticle.PARAM_ACTIVITY, "ACT-HAND")
                .putObject(StructureVerticle.PARAM_ADDRESS, new JsonObject()
                        .putString("city", "DUNKERQUE")
                        .putString("zipcode", "59240")
                );

        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .body(param.encode())
                .when().post(getURL(StructureVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("acronym", hasItem("USDK"));
    }

    /**
     * Gets structures list with non logged user test.
     */
    @Test
    public void getStructuresListWithNonLoggedUserTest() {
        given().when().post(getURL(StructureVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets structures list with wrong http method test.
     */
    @Test
    public void getStructuresListWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .get(getURL(StructureVerticle.GET_LIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets structures list with missing parameter test.
     */
    @Test
    public void getStructuresListWithMissingParameterTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .post(getURL(StructureVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets structures list with wrong parameters.
     */
    @Test
    public void getStructuresListWithWrongParameters() {
        populate(POPULATE_ONLY, DATA_STRUCTURE, SETTINGS_COUNTRY);
        JsonObject param = new JsonObject()
                .putString(StructureVerticle.PARAM_ACTIVITY, "ACT-HAND")
                .putObject(StructureVerticle.PARAM_ADDRESS, new JsonObject()
                        .putString("city", "DUNKERQUE")
                        .putString("zipcode", "59240")
                        .putString("countryAlpha2", "KL")
                );

        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .body(param.encode())
                .when().post(getURL(StructureVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

        param = new JsonObject()
                .putString(StructureVerticle.PARAM_ACTIVITY, "ACT-HAND")
                .putObject(StructureVerticle.PARAM_ADDRESS, new JsonObject()
                        .putString("city", "blabla")
                        .putString("zipcode", "bla")
                );

        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .body(param.encode())
                .when().post(getURL(StructureVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));

        param = new JsonObject()
                .putString(StructureVerticle.PARAM_ACTIVITY, "ACT-BIDON")
                .putObject(StructureVerticle.PARAM_ADDRESS, new JsonObject()
                        .putString("city", "DUNKERQUE")
                        .putString("zipcode", "59240")
                );

        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .body(param.encode())
                .when().post(getURL(StructureVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));
    }


    /**
     * Update structure.
     */
    @Test
    public void updateStructure() {
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        User u = generateLoggedUser();
        JsonObject structure = new JsonObject(given().header(TOKEN, u.getAccount().getToken())
                .queryParam(StructureVerticle.PARAM_ID, "541168295971d35c1f2d1b5e")
                .when().get(getURL(StructureVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(StructureVerticle.PARAM_LABEL, notNullValue())
                .body(StructureVerticle.PARAM_LABEL, is("Dunkerque Handball"))
                .extract().asString());
        structure.putString(StructureVerticle.PARAM_LABEL, "newValue");
        given().header(TOKEN, u.getAccount().getToken())
                .body(structure.encode())
                .when().post(getURL(StructureVerticle.UPDATE))
                .then().assertThat().statusCode(200)
                .body(StructureVerticle.PARAM_LABEL, notNullValue())
                .body(StructureVerticle.PARAM_LABEL, is("newValue"));
    }

    /**
     * Update structure with non logged user test.
     */
    @Test
    public void updateStructureWithNonLoggedUserTest() {
        given().when().post(getURL(StructureVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update structure with wrong http method test.
     */
    @Test
    public void updateStructureWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .get(getURL(StructureVerticle.UPDATE))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update structure with missing parameter test.
     */
    @Test
    public void updateStructureWithMissingParameterTest() {
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        User u = generateLoggedUser();
        JsonObject structure = new JsonObject(given().header(TOKEN, u.getAccount().getToken())
                .queryParam(StructureVerticle.PARAM_ID, "541168295971d35c1f2d1b5e")
                .when().get(getURL(StructureVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(StructureVerticle.PARAM_LABEL, notNullValue())
                .body(StructureVerticle.PARAM_LABEL, is("Dunkerque Handball"))
                .extract().asString());
        structure.removeField(StructureVerticle.PARAM_COUNTRY);
        given().header(TOKEN, u.getAccount().getToken())
                .body(structure.encode())
                .post(getURL(StructureVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Tests addHandler for StructureVerticle
     */
    @Test
    public void addStructure() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString("label", "labelValue");
        params.putString("acronym", "acronymValue");
        params.putObject(StructureVerticle.PARAM_COUNTRY, getCountry("CNTR-250-FR-FRA"));
        params.putObject(StructureVerticle.PARAM_ACTIVITY, getActivity("ACT-HAND", u));
        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(StructureVerticle.ADD))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue());
    }

    /**
     * Add structure with non logged user test.
     */
    @Test
    public void addStructureWithNonLoggedUserTest() {
        given().when().post(getURL(StructureVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add structure with wrong http method test.
     */
    @Test
    public void addStructureWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .get(getURL(StructureVerticle.ADD))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add structure with missing parameter test.
     */
    @Test
    public void addStructureWithMissingParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString("label", "labelValue");
        params.putString("acronym", "acronymValue");
        params.putObject(StructureVerticle.PARAM_COUNTRY, getCountry("CNTR-250-FR-FRA"));
        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(StructureVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }
}
