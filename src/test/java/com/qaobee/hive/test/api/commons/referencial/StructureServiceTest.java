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

import com.qaobee.hive.api.v1.commons.referencial.StructureRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Structure testBodyParams.
 *
 * @author cke
 */
public class StructureServiceTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/referencial/structure");

    /**
     * Gets structure by id.
     */
    @Test
    public void getStructureById(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(StructureRoute.PARAM_ID, "541168295971d35c1f2d1b5e")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body(StructureRoute.PARAM_LABEL, notNullValue())
                    .body(StructureRoute.PARAM_LABEL, is("Dunkerque Handball"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets structure by id with non logged user testBodyParams.
     */
    @Test
    public void getStructureByIdWithNonLoggedUserTest() {
        given().when().get(BASE_URL + "/get")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets structure by id with wrong http method testBodyParams.
     */
    @Test
    public void getStructureByIdWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .post(BASE_URL + "/get")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets structure by id with missing parameter testBodyParams.
     */
    @Test
    public void getStructureByIdWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .get(BASE_URL + "/get")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets structures list.
     */
    @Test
    public void getStructuresList(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> {
            JsonObject param = new JsonObject()
                    .put(StructureRoute.PARAM_ACTIVITY, "ACT-HAND")
                    .put(StructureRoute.PARAM_ADDRESS, new JsonObject()
                            .put("city", "DUNKERQUE")
                            .put("zipcode", "59240")
                    );

            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(param.encode())
                    .when().post(BASE_URL + "/getList")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("acronym", hasItem("USDK"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets structures list with non logged user testBodyParams.
     */
    @Test
    public void getStructuresListWithNonLoggedUserTest() {
        given().when().post(BASE_URL + "/getList")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets structures list with wrong http method testBodyParams.
     */
    @Test
    public void getStructuresListWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .get(BASE_URL + "/getList")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets structures list with missing parameter testBodyParams.
     */
    @Test
    public void getStructuresListWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .post(BASE_URL + "/getList")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets structures list with wrong parameters.
     */
    @Test
    public void getStructuresListWithWrongParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> {
            JsonObject param = new JsonObject()
                    .put(StructureRoute.PARAM_ACTIVITY, "ACT-HAND")
                    .put(StructureRoute.PARAM_ADDRESS, new JsonObject()
                            .put("city", "DUNKERQUE")
                            .put("zipcode", "59240")
                            .put("countryAlpha2", "KL")
                    );

            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(param.encode())
                    .when().post(BASE_URL + "/getList")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

            param = new JsonObject()
                    .put(StructureRoute.PARAM_ACTIVITY, "ACT-HAND")
                    .put(StructureRoute.PARAM_ADDRESS, new JsonObject()
                            .put("city", "blabla")
                            .put("zipcode", "bla")
                    );

            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(param.encode())
                    .when().post(BASE_URL + "/getList")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));

            param = new JsonObject()
                    .put(StructureRoute.PARAM_ACTIVITY, "ACT-BIDON")
                    .put(StructureRoute.PARAM_ADDRESS, new JsonObject()
                            .put("city", "DUNKERQUE")
                            .put("zipcode", "59240")
                    );

            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(param.encode())
                    .when().post(BASE_URL + "/getList")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
            async.complete();
        });
        async.await(TIMEOUT);
    }


    /**
     * Update structure.
     */
    @Test
    public void updateStructure(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        generateLoggedUser().setHandler(u -> {
            JsonObject structure = new JsonObject(given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(StructureRoute.PARAM_ID, "541168295971d35c1f2d1b5e")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body(StructureRoute.PARAM_LABEL, notNullValue())
                    .body(StructureRoute.PARAM_LABEL, is("Dunkerque Handball"))
                    .extract().asString());
            structure.put(StructureRoute.PARAM_LABEL, "newValue");
            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(structure.encode())
                    .when().post(BASE_URL + "/update")
                    .then().assertThat().statusCode(200)
                    .body(StructureRoute.PARAM_LABEL, notNullValue())
                    .body(StructureRoute.PARAM_LABEL, is("newValue"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Update structure with non logged user testBodyParams.
     */
    @Test
    public void updateStructureWithNonLoggedUserTest() {
        given().when().post(BASE_URL + "/update")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update structure with wrong http method testBodyParams.
     */
    @Test
    public void updateStructureWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .get(BASE_URL + "/update")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Update structure with missing parameter testBodyParams.
     */
    @Test
    public void updateStructureWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        generateLoggedUser().setHandler(u -> {
            JsonObject structure = new JsonObject(given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(StructureRoute.PARAM_ID, "541168295971d35c1f2d1b5e")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body(StructureRoute.PARAM_LABEL, notNullValue())
                    .body(StructureRoute.PARAM_LABEL, is("Dunkerque Handball"))
                    .extract().asString());
            structure.remove(StructureRoute.PARAM_COUNTRY);
            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(structure.encode())
                    .post(BASE_URL + "/update")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Tests addHandler for StructureVerticle
     */
    @Test
    public void addStructure(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> getCountry("CNTR-250-FR-FRA")
                .setHandler(country -> getActivity("ACT-HAND")
                        .setHandler(activity -> {
                            final JsonObject params = new JsonObject()
                                    .put("label", "labelValue")
                                    .put("acronym", "acronymValue")
                                    .put(StructureRoute.PARAM_COUNTRY, country.result())
                                    .put(StructureRoute.PARAM_ACTIVITY, activity.result());
                            given().header(TOKEN, u.result().getAccount().getToken())
                                    .body(params.encode())
                                    .when().post(BASE_URL + "/add")
                                    .then().assertThat().statusCode(200)
                                    .body("_id", notNullValue());
                            async.complete();
                        })));
        async.await(TIMEOUT);
    }

    /**
     * Add structure with non logged user testBodyParams.
     */
    @Test
    public void addStructureWithNonLoggedUserTest() {
        given().when().post(BASE_URL + "/add")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add structure with wrong http method testBodyParams.
     */
    @Test
    public void addStructureWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .get(BASE_URL + "/add")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Add structure with missing parameter testBodyParams.
     */
    @Test
    public void addStructureWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> getCountry("CNTR-250-FR-FRA").setHandler(country -> {
            final JsonObject params = new JsonObject();
            params.put("label", "labelValue");
            params.put("acronym", "acronymValue");
            params.put(StructureRoute.PARAM_COUNTRY, country.result());
            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/add")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }));
        async.await(TIMEOUT);
    }
}
