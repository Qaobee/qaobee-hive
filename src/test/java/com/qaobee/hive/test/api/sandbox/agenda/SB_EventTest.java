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

package com.qaobee.hive.test.api.sandbox.agenda;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.sandbox.event.SB_EventVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Event.
 */
public class SB_EventTest extends VertxJunitSupport {

    /**
     * Add event.
     */
    @Test
    public void addEvent(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        generateLoggedUser().done(user -> {
            final JsonObject params = new JsonObject()
                    .put(SB_EventVerticle.PARAM_LABEL, "labelValue")
                    .put(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                    .put("link", new JsonObject()
                            .put(SB_EventVerticle.PARAM_LINK_TYPE, "championship")
                    )
                    .put(SB_EventVerticle.PARAM_START_DATE, 1435701600000L)
                    .put(SB_EventVerticle.PARAM_END_DATE, 1435701600100L)
                    .put(SB_EventVerticle.PARAM_OWNER, new JsonObject()
                            .put("sandboxId", "558b0efebd2e39cdab651e1f")
                            .put("effectiveId", "550b31f925da07681592db23")
                    );

            String id = given().header(TOKEN, user.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(SB_EventVerticle.ADD))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body(SB_EventVerticle.PARAM_LABEL, is(params.getString(SB_EventVerticle.PARAM_LABEL)))
                    .extract().path("_id");

            given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_EventVerticle.PARAM_ID, id)
                    .when().get(getURL(SB_EventVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                    .body(SB_EventVerticle.PARAM_LABEL, is(params.getString(SB_EventVerticle.PARAM_LABEL)));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add event with non logged user.
     */
    @Test
    public void addEventWithNonLoggedUser() {
        given().when().post(getURL(SB_EventVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add event with wrong http method.
     */
    @Test
    public void addEventWithWrongHttpMethod() {
        given().when().get(getURL(SB_EventVerticle.ADD))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add event with missing params.
     */
    @Test
    public void addEventWithMissingParams(TestContext context) {
        Async async = context.async();
        generateLoggedUser().done(u -> {
            final JsonObject params = new JsonObject()
                    .put(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                    .put(SB_EventVerticle.PARAM_LABEL, "labelValue")
                    .put("link", new JsonObject()
                            .put(SB_EventVerticle.PARAM_LINK_TYPE, "championship")
                    )
                    .put(SB_EventVerticle.PARAM_START_DATE, 1435701600000L)
                    .put(SB_EventVerticle.PARAM_END_DATE, 1435701600100L)
                    .put(SB_EventVerticle.PARAM_OWNER, new JsonObject()
                            .put(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f")
                    );

            List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_EventVerticle.ADD).mandatoryParams());
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, u.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_EventVerticle.ADD))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets list event.
     */
    @Test
    public void getListEvent(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().done(user -> {
            final JsonObject params = new JsonObject();
            params.put(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                    .put(SB_EventVerticle.PARAM_LINK_TYPE, new JsonArray().add("championship"))
                    .put(SB_EventVerticle.PARAM_START_DATE, 1435701600000L)
                    .put(SB_EventVerticle.PARAM_END_DATE, 1467237600000L)
                    .put(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f")
                    .put(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "550b31f925da07681592db23")
                    .put(SB_EventVerticle.PARAM_OWNER_TEAMID, "552d5e08644a77b3a20afdfe");

            given().header(TOKEN, user.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(SB_EventVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(4));

            params.put(SB_EventVerticle.PARAM_LIMIT_RESULT, 2)
                    .put(SB_EventVerticle.PARAM_LIST_SORTBY, new JsonArray().add(new JsonObject()
                            .put("fieldName", SB_EventVerticle.PARAM_LABEL)
                            .put("sortOrder", -1)
                    ));
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(SB_EventVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(2));

            params.put(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "");
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(SB_EventVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(2));

            params.put(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "TOTO");
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(SB_EventVerticle.GET_LIST))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets list event with non logged user.
     */
    @Test
    public void getListEventWithNonLoggedUser() {
        given().when().post(getURL(SB_EventVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list event with wrong http method.
     */
    @Test
    public void getListEventWithWrongHttpMethod() {
        given().when().get(getURL(SB_EventVerticle.GET_LIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list event with missing parameters.
     */
    @Test
    public void getListEventWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().done(user -> {
            final JsonObject params = new JsonObject()
                    .put(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                    .put(SB_EventVerticle.PARAM_LINK_TYPE, new JsonArray().add("championship"))
                    .put(SB_EventVerticle.PARAM_START_DATE, 1435701600000L)
                    .put(SB_EventVerticle.PARAM_END_DATE, 1467237600000L)
                    .put(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f")
                    .put(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "550b31f925da07681592db23");

            List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_EventVerticle.GET_LIST).mandatoryParams());
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, user.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_EventVerticle.GET_LIST))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets event by id.
     */
    @Test
    public void getEventById(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().done(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_EventVerticle.PARAM_ID, "55847ed0d040353767a48e68")
                    .when().get(getURL(SB_EventVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                    .body(SB_EventVerticle.PARAM_LABEL, is("Amical"));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets event by id with non logged user.
     */
    @Test
    public void getEventByIdWithNonLoggedUser() {
        given().when().get(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets event by id with wrong http method.
     */
    @Test
    public void getEventByIdWithWrongHttpMethod() {
        given().when().post(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets event by id with missing parameters.
     */
    @Test
    public void getEventByIdWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().done(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .when().get(getURL(SB_EventVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets event by id with wrong parameters.
     */
    @Test
    public void getEventByIdWithWrongParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().done(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_EventVerticle.PARAM_ID, "bla")
                    .when().get(getURL(SB_EventVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Update event.
     */
    @Test
    public void updateEvent(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND, DATA_SANDBOXES_HAND);
        generateLoggedUser().done(user -> {
            JsonObject event = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_EventVerticle.PARAM_ID, "55847ed0d040353767a48e68")
                    .when().get(getURL(SB_EventVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                    .body(SB_EventVerticle.PARAM_LABEL, is("Amical")).extract().asString());
            event.put(SB_EventVerticle.PARAM_LABEL, "toto");
            String id = given().header(TOKEN, user.getAccount().getToken())
                    .body(event.encode())
                    .when().post(getURL(SB_EventVerticle.UPDATE))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body(SB_EventVerticle.PARAM_LABEL, is(event.getString(SB_EventVerticle.PARAM_LABEL)))
                    .extract().path("_id");

            given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_EventVerticle.PARAM_ID, id)
                    .when().get(getURL(SB_EventVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                    .body(SB_EventVerticle.PARAM_LABEL, is(event.getString(SB_EventVerticle.PARAM_LABEL)));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Update event with non logged user.
     */
    @Test
    public void updateEventWithNonLoggedUser() {
        given().when().post(getURL(SB_EventVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update event with wrong http method.
     */
    @Test
    public void updateEventWithWrongHttpMethod(TestContext context) {
        given().when().get(getURL(SB_EventVerticle.UPDATE))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update event with missing params.
     */
    @Test
    public void updateEventWithMissingParams(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().done(user -> {
            JsonObject event = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_EventVerticle.PARAM_ID, "55847ed0d040353767a48e68")
                    .when().get(getURL(SB_EventVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                    .body(SB_EventVerticle.PARAM_LABEL, is("Amical")).extract().asString());
            event.put(SB_EventVerticle.PARAM_LABEL, "toto");
            List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_EventVerticle.UPDATE).mandatoryParams());
            event.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(event.encode());
                params2.remove(k);
                given().header(TOKEN, user.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_EventVerticle.UPDATE))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }
}
