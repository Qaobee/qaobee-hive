/*
 * __________________
 *   Qaobee
 * __________________
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

package com.qaobee.hive.test.api.sandbox.effective;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.sandbox.effective.SB_EffectiveVerticle;
import com.qaobee.hive.api.v1.sandbox.effective.SB_PersonVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Person test.
 */
public class SB_PersonTest extends VertxJunitSupport {
    /**
     * Gets by id.
     */
    @Test
    public void getById() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_PERSON_HAND);
        final JsonObject params = new JsonObject()
                .putArray(SB_PersonVerticle.PARAM_LIST_ID, new JsonArray(
                        new String[]{"550a05dadb8f8b6e2f51f4db", "550a05e3db8f8b6e2f51f4dc",
                                "550a05e9db8f8b6e2f51f4dd", "550a05f7db8f8b6e2f51f4de",
                                "550a0600db8f8b6e2f51f4df", "550a0606db8f8b6e2f51f4e0",
                                "550a060ddb8f8b6e2f51f4e1", "550a0614db8f8b6e2f51f4e2",
                                "550a061bdb8f8b6e2f51f4e3", "550a0620db8f8b6e2f51f4e4",
                                "550a0620db8f8b6e2f51f4e5"}))
                .putArray(SB_PersonVerticle.PARAM_LIST_FIELD, new JsonArray(new String[]{"_id", "name", "firstname", "avatar", "status"}));

        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_PersonVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(11))
                .body("name", hasItem("Batinovic"));
    }

    /**
     * Gets by id with non logged user.
     */
    @Test
    public void getByIdWithNonLoggedUser() {
        given().when().post(getURL(SB_PersonVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets by id with wrong http method.
     */
    @Test
    public void getByIdWithWrongHttpMethod() {
        given().when().get(getURL(SB_PersonVerticle.GET_LIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets by id with missing parameters.
     */
    @Test
    public void getByIdWithMissingParameters() {
        final JsonObject params = new JsonObject();
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        params.putArray(SB_PersonVerticle.PARAM_LIST_ID, new JsonArray(
                new String[]{"550a05dadb8f8b6e2f51f4db", "550a05e3db8f8b6e2f51f4dc", "550a05e9db8f8b6e2f51f4dd", "550a05f7db8f8b6e2f51f4de", "550a0600db8f8b6e2f51f4df", "550a0606db8f8b6e2f51f4e0",
                        "550a060ddb8f8b6e2f51f4e1", "550a0614db8f8b6e2f51f4e2", "550a061bdb8f8b6e2f51f4e3", "550a0620db8f8b6e2f51f4e4", "550a0620db8f8b6e2f51f4e5"}));
        params.putArray(SB_PersonVerticle.PARAM_LIST_FIELD, new JsonArray(new String[]{"_id", "name", "firstname", "avatar", "status"}));
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_PersonVerticle.GET_LIST).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params2.encode())
                    .when().post(getURL(SB_PersonVerticle.GET_LIST))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Gets list person sandbox.
     */
    @Test
    public void getListPersonSandbox() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_PERSON_HAND);
        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .queryParam(SB_PersonVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f")
                .when().get(getURL(SB_PersonVerticle.GET_LIST_SANDBOX))
                .then().assertThat().statusCode(200)
                .body("", hasSize(17))
                .body("name", hasItem("Batinovic"));
    }

    /**
     * Gets list person sandbox with non logged user.
     */
    @Test
    public void getListPersonSandboxWithNonLoggedUser() {
        given().when().queryParam(SB_PersonVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f")
                .get(getURL(SB_PersonVerticle.GET_LIST_SANDBOX))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list person sandbox with wrong http method.
     */
    @Test
    public void getListPersonSandboxWithWrongHttpMethod() {
        given().when().post(getURL(SB_PersonVerticle.GET_LIST_SANDBOX))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list person sandbox with missing parameters.
     */
    @Test
    public void getListPersonSandboxWithMissingParameters() {
        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .when().get(getURL(SB_PersonVerticle.GET_LIST_SANDBOX))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets list person sandbox with wring parameters.
     */
    @Test
    public void getListPersonSandboxWithWringParameters() {
        given().header(TOKEN, generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").getAccount().getToken())
                .queryParam(SB_PersonVerticle.PARAM_SANDBOX_ID, "bla")
                .when().get(getURL(SB_PersonVerticle.GET_LIST_SANDBOX))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Add person.
     */
    @Test
    public void addPerson() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_PERSON_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        JsonObject person = new JsonObject().putObject("person", generatePerson());
        given().header(TOKEN, user.getAccount().getToken())
                .body(person.encode())
                .when().put(getURL(SB_PersonVerticle.ADD))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("name", is("Ranu"));
    }

    /**
     * Add person with non logged user.
     */
    @Test
    public void addPersonWithNonLoggedUser() {
        given().when().post(getURL(SB_EffectiveVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add person with wrong http method.
     */
    @Test
    public void addPersonWithWrongHttpMethod() {
        given().when().get(getURL(SB_EffectiveVerticle.ADD))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add person with missing parameter.
     */
    @Test
    public void addPersonWithMissingParameter() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_PERSON_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        given().header(TOKEN, user.getAccount().getToken())
                .when().put(getURL(SB_PersonVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Update person.
     */
    @Test
    public void updatePerson() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_PERSON_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        String id = "550a05dadb8f8b6e2f51f4db";
        JsonObject person = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_PersonVerticle.PARAM_PERSON_ID, id)
                .when().get(getURL(SB_PersonVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("name", is("Batinovic")).extract().asString());

        person.putString("name", "Blabla");
        given().header(TOKEN, user.getAccount().getToken())
                .body(person.encode())
                .when().put(getURL(SB_PersonVerticle.UPDATE))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("name", is("Blabla"));
    }

    /**
     * Update person with non logged user.
     */
    @Test
    public void updatePersonWithNonLoggedUser() {
        given().when().put(getURL(SB_EffectiveVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update person with wrong http method.
     */
    @Test
    public void updatePersonWithWrongHttpMethod() {
        given().when().get(getURL(SB_EffectiveVerticle.UPDATE))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update person with missing parameters.
     */
    @Test
    public void updatePersonWithMissingParameters() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_EFFECTIVE_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        given().header(TOKEN, user.getAccount().getToken())
                .when().put(getURL(SB_EffectiveVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    private JsonObject generatePerson() {
        return new JsonObject().putString("sandboxId", "558b0efebd2e39cdab651e1f")
                .putString("name", "Ranu")
                .putString("firstname", "Jean Pierre")
                .putString("gender", "Homme")
                .putNumber("birthdate", 556236000000L)
                .putString("birthcity", "Zagreb, Croatie")
                .putString("nationality", "Croatie")
                .putObject("address", new JsonObject()
                        .putString("formatedAddress", "3 Allée de Champagne, Cesson-Sévigné, France")
                        .putNumber("lat", 48.0899268000000006)
                        .putNumber("lng", -1.6145389000000001)
                        .putString("place", "3 Rue de Champagne")
                        .putString("city", "Chantepie")
                        .putString("country", "France")
                        .putString("zipcode", "35135")
                )
                .putObject("contact", new JsonObject()
                        .putString("home", "05 04 00 00 01")
                        .putString("cellphone", "07 06 00 00 01")
                        .putString("email", "batinovic@toto.com")
                )
                .putObject("status", new JsonObject()
                        .putObject("availability", new JsonObject()
                                .putString("value", "available")
                                .putString("cause", "available")
                        )

                        .putString("stateForm", "middling")
                        .putNumber("weight", 95)
                        .putNumber("height", 193)
                        .putNumber("squadnumber", 33)
                        .putString("positionType", "center-backcourt")
                        .putString("laterality", "Droitier")
                );
    }
}
