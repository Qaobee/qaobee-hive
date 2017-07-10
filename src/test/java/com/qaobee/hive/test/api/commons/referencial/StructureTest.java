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
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Structure test.
 *
 * @author cke
 */
@Ignore
public class StructureTest extends VertxJunitSupport {

    /**
     * Gets structure by id.
     */
    @Test
    public void getStructureById(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(StructureVerticle.PARAM_ID, "541168295971d35c1f2d1b5e")
                    .when().get(getURL(StructureVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body(StructureVerticle.PARAM_LABEL, notNullValue())
                    .body(StructureVerticle.PARAM_LABEL, is("Dunkerque Handball"));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets structure by id with non logged user test.
     */
    @Test
    public void getStructureByIdWithNonLoggedUserTest(TestContext context) {
        given().when().get(getURL(StructureVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets structure by id with wrong http method test.
     */
    @Test
    public void getStructureByIdWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .post(getURL(StructureVerticle.GET))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets structure by id with missing parameter test.
     */
    @Test
    public void getStructureByIdWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .get(getURL(StructureVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets structures list.
     */
    @Test
    public void getStructuresList(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            JsonObject param = new JsonObject()
                    .put(StructureVerticle.PARAM_ACTIVITY, "ACT-HAND")
                    .put(StructureVerticle.PARAM_ADDRESS, new JsonObject()
                            .put("city", "DUNKERQUE")
                            .put("zipcode", "59240")
                    );

            given().header(TOKEN, u.getAccount().getToken())
                    .body(param.encode())
                    .when().post(getURL(StructureVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("acronym", hasItem("USDK"));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets structures list with non logged user test.
     */
    @Test
    public void getStructuresListWithNonLoggedUserTest(TestContext context) {
        given().when().post(getURL(StructureVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets structures list with wrong http method test.
     */
    @Test
    public void getStructuresListWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .get(getURL(StructureVerticle.GET_LIST))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets structures list with missing parameter test.
     */
    @Test
    public void getStructuresListWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .post(getURL(StructureVerticle.GET_LIST))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets structures list with wrong parameters.
     */
    @Test
    public void getStructuresListWithWrongParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            JsonObject param = new JsonObject()
                    .put(StructureVerticle.PARAM_ACTIVITY, "ACT-HAND")
                    .put(StructureVerticle.PARAM_ADDRESS, new JsonObject()
                            .put("city", "DUNKERQUE")
                            .put("zipcode", "59240")
                            .put("countryAlpha2", "KL")
                    );

            given().header(TOKEN, u.getAccount().getToken())
                    .body(param.encode())
                    .when().post(getURL(StructureVerticle.GET_LIST))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

            param = new JsonObject()
                    .put(StructureVerticle.PARAM_ACTIVITY, "ACT-HAND")
                    .put(StructureVerticle.PARAM_ADDRESS, new JsonObject()
                            .put("city", "blabla")
                            .put("zipcode", "bla")
                    );

            given().header(TOKEN, u.getAccount().getToken())
                    .body(param.encode())
                    .when().post(getURL(StructureVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));

            param = new JsonObject()
                    .put(StructureVerticle.PARAM_ACTIVITY, "ACT-BIDON")
                    .put(StructureVerticle.PARAM_ADDRESS, new JsonObject()
                            .put("city", "DUNKERQUE")
                            .put("zipcode", "59240")
                    );

            given().header(TOKEN, u.getAccount().getToken())
                    .body(param.encode())
                    .when().post(getURL(StructureVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }


    /**
     * Update structure.
     */
    @Test
    public void updateStructure(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        generateLoggedUser().then(u -> {
            JsonObject structure = new JsonObject(given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(StructureVerticle.PARAM_ID, "541168295971d35c1f2d1b5e")
                    .when().get(getURL(StructureVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body(StructureVerticle.PARAM_LABEL, notNullValue())
                    .body(StructureVerticle.PARAM_LABEL, is("Dunkerque Handball"))
                    .extract().asString());
            structure.put(StructureVerticle.PARAM_LABEL, "newValue");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(structure.encode())
                    .when().post(getURL(StructureVerticle.UPDATE))
                    .then().assertThat().statusCode(200)
                    .body(StructureVerticle.PARAM_LABEL, notNullValue())
                    .body(StructureVerticle.PARAM_LABEL, is("newValue"));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Update structure with non logged user test.
     */
    @Test
    public void updateStructureWithNonLoggedUserTest(TestContext context) {
        given().when().post(getURL(StructureVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update structure with wrong http method test.
     */
    @Test
    public void updateStructureWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .get(getURL(StructureVerticle.UPDATE))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Update structure with missing parameter test.
     */
    @Test
    public void updateStructureWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        generateLoggedUser().then(u -> {
            JsonObject structure = new JsonObject(given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(StructureVerticle.PARAM_ID, "541168295971d35c1f2d1b5e")
                    .when().get(getURL(StructureVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body(StructureVerticle.PARAM_LABEL, notNullValue())
                    .body(StructureVerticle.PARAM_LABEL, is("Dunkerque Handball"))
                    .extract().asString());
            structure.remove(StructureVerticle.PARAM_COUNTRY);
            given().header(TOKEN, u.getAccount().getToken())
                    .body(structure.encode())
                    .post(getURL(StructureVerticle.UPDATE))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Tests addHandler for StructureVerticle
     */
    @Test
    public void addStructure(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put("label", "labelValue");
            params.put("acronym", "acronymValue");
            params.put(StructureVerticle.PARAM_COUNTRY, getCountry("CNTR-250-FR-FRA"));
            params.put(StructureVerticle.PARAM_ACTIVITY, getActivity("ACT-HAND", u));
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(StructureVerticle.ADD_STRUCTURE))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue());
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add structure with non logged user test.
     */
    @Test
    public void addStructureWithNonLoggedUserTest(TestContext context) {
        given().when().post(getURL(StructureVerticle.ADD_STRUCTURE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add structure with wrong http method test.
     */
    @Test
    public void addStructureWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .get(getURL(StructureVerticle.ADD_STRUCTURE))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add structure with missing parameter test.
     */
    @Test
    public void addStructureWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            final JsonObject params = new JsonObject();
            params.put("label", "labelValue");
            params.put("acronym", "acronymValue");
            params.put(StructureVerticle.PARAM_COUNTRY, getCountry("CNTR-250-FR-FRA"));
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(StructureVerticle.ADD_STRUCTURE))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }
}
