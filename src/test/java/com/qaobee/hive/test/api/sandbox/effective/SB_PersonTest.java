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

package com.qaobee.hive.test.api.sandbox.effective;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.sandbox.effective.SB_EffectiveVerticle;
import com.qaobee.hive.api.v1.sandbox.effective.SB_PersonVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
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
        populate(POPULATE_ONLY, DATA_PERSON_HAND);
        final JsonObject params = new JsonObject()
                .put(SB_PersonVerticle.PARAM_LIST_ID, new JsonArray(
                        Arrays.asList(new String[]{"550a05dadb8f8b6e2f51f4db", "550a05e3db8f8b6e2f51f4dc",
                                "550a05e9db8f8b6e2f51f4dd", "550a05f7db8f8b6e2f51f4de",
                                "550a0600db8f8b6e2f51f4df", "550a0606db8f8b6e2f51f4e0",
                                "550a060ddb8f8b6e2f51f4e1", "550a0614db8f8b6e2f51f4e2",
                                "550a061bdb8f8b6e2f51f4e3", "550a0620db8f8b6e2f51f4e4",
                                "550a0620db8f8b6e2f51f4e5"})))
                .put(SB_PersonVerticle.PARAM_LIST_FIELD, new JsonArray(Arrays.asList(new String[]{"_id", "name", "firstname", "avatar", "status"})));
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(SB_PersonVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(11))
                    .body("name", hasItem("Batinovic"));
        });
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
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        final JsonObject params = new JsonObject();
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").done(user -> {
            params.put(SB_PersonVerticle.PARAM_LIST_ID, new JsonArray(Arrays.asList(
                    new String[]{"550a05dadb8f8b6e2f51f4db", "550a05e3db8f8b6e2f51f4dc", "550a05e9db8f8b6e2f51f4dd", "550a05f7db8f8b6e2f51f4de", "550a0600db8f8b6e2f51f4df", "550a0606db8f8b6e2f51f4e0",
                            "550a060ddb8f8b6e2f51f4e1", "550a0614db8f8b6e2f51f4e2", "550a061bdb8f8b6e2f51f4e3", "550a0620db8f8b6e2f51f4e4", "550a0620db8f8b6e2f51f4e5"})));
            params.put(SB_PersonVerticle.PARAM_LIST_FIELD, new JsonArray(Arrays.asList(new String[]{"_id", "name", "firstname", "avatar", "status"})));
            List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_PersonVerticle.GET_LIST).mandatoryParams());
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, user.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_PersonVerticle.GET_LIST))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
        });
    }

    /**
     * Gets list person sandbox.
     */
    @Test
    public void getListPersonSandbox() {
        populate(POPULATE_ONLY, DATA_PERSON_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(SB_PersonVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f")
                    .when().get(getURL(SB_PersonVerticle.GET_LIST_SANDBOX))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(17))
                    .body("name", hasItem("Batinovic"));
        });
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
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(SB_PersonVerticle.GET_LIST_SANDBOX))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Gets list person sandbox with wring parameters.
     */
    @Test
    public void getListPersonSandboxWithWringParameters() {
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(SB_PersonVerticle.PARAM_SANDBOX_ID, "bla")
                    .when().get(getURL(SB_PersonVerticle.GET_LIST_SANDBOX))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
        });
    }


    /**
     * Upload avatar
     */
    @Test
    public void uploadAvatar() {
        populate(POPULATE_ONLY, DATA_PERSON_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(user -> {

            JsonObject person = new JsonObject().put("person", generatePerson());
            String id = given().header(TOKEN, user.getAccount().getToken())
                    .body(person.encode())
                    .when().put(getURL(SB_PersonVerticle.ADD))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("name", is("Ranu")).extract().path("_id");

            String avatarId = given().header(TOKEN, user.getAccount().getToken())
                    .multiPart(new File("src/test/resources/avatar.jpg")).
                            pathParam("uid", id).
                            when().
                            post(BASE_URL + "/file/SB_Person/avatar/{uid}")
                    .then().assertThat().statusCode(200)
                    .body("avatar", notNullValue())
                    .extract().path("avatar");

            byte[] byteArray = given().pathParam("avatar", avatarId)
                    .get(BASE_URL + "/file/SB_Person/{avatar}")
                    .then().assertThat().statusCode(200)
                    .extract().asByteArray();

            Assert.assertEquals("Files must have same size",
                    byteArray.length,
                    vertx.fileSystem().propsBlocking("src/test/resources/avatar.jpg").size());
        });
    }

    /**
     * Upload avatar with wrong user id
     */
    @Test
    public void uploadAvatarWithWrongUserId() {
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .multiPart(new File("src/test/resources/avatar.jpg")).
                    pathParam("uid", "blabla").
                    when().
                    post(BASE_URL + "/file/SB_Person/avatar/{uid}")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode());
        });
    }

    /**
     * Upload avatar with not logged user
     */
    @Test
    public void uploadAvatarWithNotLoggedUser() {
        generateUser().then(u -> {
            given().multiPart(new File("src/test/resources/avatar.jpg"))
                    .pathParam("uid", u.get_id())
                    .when()
                    .post(BASE_URL + "/file/SB_Person/avatar/{uid}")
                    .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode());
        });
    }

    /**
     * Upload avatar with wrong token.
     */
    @Test
    public void uploadAvatarWithWrongToken() {
        generateUser().then(u -> {
            given().multiPart(new File("src/test/resources/avatar.jpg")).
                    pathParam("uid", u.get_id())
                    .header(TOKEN, "11111")
                    .when()
                    .post(BASE_URL + "/file/SB_Person/avatar/{uid}")
                    .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode());
        });
    }

    /**
     * Get avatar with wrong avatar id
     */
    @Test
    public void getAvatarWithWrongAvatarId() {
        given().pathParam("avatar", "blabla")
                .get(BASE_URL + "/file/SB_Person/{avatar}")
                .then().assertThat().statusCode(404);
    }

    /**
     * Add person.
     */
    @Test
    public void addPerson() {
        populate(POPULATE_ONLY, DATA_PERSON_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(user -> {
            JsonObject person = new JsonObject().put("person", generatePerson());
            given().header(TOKEN, user.getAccount().getToken())
                    .body(person.encode())
                    .when().put(getURL(SB_PersonVerticle.ADD))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("name", is("Ranu"));
        });
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
        populate(POPULATE_ONLY, DATA_PERSON_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .when().put(getURL(SB_PersonVerticle.ADD))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Update person.
     */
    @Test
    public void updatePerson() {
        populate(POPULATE_ONLY, DATA_PERSON_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(user -> {
            String id = "550a05dadb8f8b6e2f51f4db";
            JsonObject person = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_PersonVerticle.PARAM_PERSON_ID, id)
                    .when().get(getURL(SB_PersonVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("name", is("Batinovic")).extract().asString());

            person.put("name", "Blabla");
            given().header(TOKEN, user.getAccount().getToken())
                    .body(person.encode())
                    .when().put(getURL(SB_PersonVerticle.UPDATE))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("name", is("Blabla"));
        });
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
        populate(POPULATE_ONLY, DATA_EFFECTIVE_HAND);
        generateLoggedUser().then(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .when().put(getURL(SB_EffectiveVerticle.UPDATE))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    private JsonObject generatePerson() {
        return new JsonObject().put("sandboxId", "558b0efebd2e39cdab651e1f")
                .put("name", "Ranu")
                .put("firstname", "Jean Pierre")
                .put("gender", "Homme")
                .put("birthdate", 556236000000L)
                .put("birthcity", "Zagreb, Croatie")
                .put("nationality", "Croatie")
                .put("address", new JsonObject()
                        .put("formatedAddress", "3 Allée de Champagne, Cesson-Sévigné, France")
                        .put("lat", 48.0899268000000006)
                        .put("lng", -1.6145389000000001)
                        .put("place", "3 Rue de Champagne")
                        .put("city", "Chantepie")
                        .put("country", "France")
                        .put("zipcode", "35135")
                )
                .put("contact", new JsonObject()
                        .put("home", "05 04 00 00 01")
                        .put("cellphone", "07 06 00 00 01")
                        .put("email", "batinovic@toto.com")
                )
                .put("status", new JsonObject()
                        .put("availability", new JsonObject()
                                .put("value", "available")
                                .put("cause", "available")
                        )

                        .put("stateForm", "middling")
                        .put("weight", 95)
                        .put("height", 193)
                        .put("squadnumber", 33)
                        .put("positionType", "center-backcourt")
                        .put("laterality", "Droitier")
                );
    }
}
