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

import com.qaobee.hive.api.v1.sandbox.event.SB_EventRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.qaobee.hive.api.v1.sandbox.event.SB_EventRoute.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Event.
 */
public class SB_EventTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/sandbox/event/event");

    /**
     * Add event.
     *
     * @param context the context
     */
    @Test
    public void addEvent(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put(PARAM_LABEL, "labelValue")
                    .put(PARAM_ACTIVITY_ID, "ACT-HAND")
                    .put("link", new JsonObject()
                            .put(SB_EventRoute.PARAM_LINK_TYPE, "championship")
                    )
                    .put(PARAM_START_DATE, 1435701600000L)
                    .put(SB_EventRoute.PARAM_END_DATE, 1435701600100L)
                    .put(PARAM_OWNER, new JsonObject()
                            .put("sandboxId", "558b0efebd2e39cdab651e1f")
                            .put("effectiveId", "550b31f925da07681592db23")
                    );

            String id = given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body(PARAM_LABEL, is(params.getString(PARAM_LABEL)))
                    .extract().path("_id");

            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_EventRoute.PARAM_ID, id)
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body(PARAM_LABEL, notNullValue())
                    .body(PARAM_LABEL, is(params.getString(PARAM_LABEL)));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Add event with non logged user.
     */
    @Test
    public void addEventWithNonLoggedUser() {
        given().when().post(BASE_URL + "/add")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add event with wrong http method.
     */
    @Test
    public void addEventWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/add")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add event with missing params.
     *
     * @param context the context
     */
    @Test
    public void addEventWithMissingParams(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            final JsonObject params = new JsonObject()
                    .put(PARAM_ACTIVITY_ID, "ACT-HAND")
                    .put(PARAM_LABEL, "labelValue")
                    .put("link", new JsonObject()
                            .put(SB_EventRoute.PARAM_LINK_TYPE, "championship")
                    )
                    .put(PARAM_START_DATE, 1435701600000L)
                    .put(SB_EventRoute.PARAM_END_DATE, 1435701600100L)
                    .put(PARAM_OWNER, new JsonObject()
                            .put(SB_EventRoute.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f")
                    );

            List<String> mandatoryParams = Arrays.asList(PARAM_LABEL, PARAM_ACTIVITY_ID, PARAM_OWNER, PARAM_START_DATE);
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, u.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(BASE_URL + "/add")
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets list event.
     *
     * @param context the context
     */
    @Test
    public void getListEvent(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject();
            params.put(PARAM_ACTIVITY_ID, "ACT-HAND")
                    .put(SB_EventRoute.PARAM_LINK_TYPE, new JsonArray().add("championship"))
                    .put(PARAM_START_DATE, 1435701600000L)
                    .put(SB_EventRoute.PARAM_END_DATE, 1467237600000L)
                    .put(SB_EventRoute.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f")
                    .put(SB_EventRoute.PARAM_OWNER_EFFECTIVEID, "550b31f925da07681592db23")
                    .put(SB_EventRoute.PARAM_OWNER_TEAMID, "552d5e08644a77b3a20afdfe");

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(4));

            params.put(SB_EventRoute.PARAM_LIMIT_RESULT, 2)
                    .put(SB_EventRoute.PARAM_LIST_SORTBY, new JsonArray().add(new JsonObject()
                            .put("fieldName", PARAM_LABEL)
                            .put("sortOrder", -1)
                    ));
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(2));

            params.put(SB_EventRoute.PARAM_OWNER_EFFECTIVEID, "");
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(2));

            params.put(SB_EventRoute.PARAM_OWNER_EFFECTIVEID, "TOTO");
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets list event with non logged user.
     */
    @Test
    public void getListEventWithNonLoggedUser() {
        given().when().post(BASE_URL + "/list")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list event with wrong http method.
     */
    @Test
    public void getListEventWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/list")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list event with missing parameters.
     *
     * @param context the context
     */
    @Test
    public void getListEventWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put(PARAM_ACTIVITY_ID, "ACT-HAND")
                    .put(SB_EventRoute.PARAM_LINK_TYPE, new JsonArray().add("championship"))
                    .put(PARAM_START_DATE, 1435701600000L)
                    .put(SB_EventRoute.PARAM_END_DATE, 1467237600000L)
                    .put(SB_EventRoute.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f")
                    .put(SB_EventRoute.PARAM_OWNER_EFFECTIVEID, "550b31f925da07681592db23");

            List<String> mandatoryParams = Arrays.asList(PARAM_START_DATE, PARAM_END_DATE, PARAM_ACTIVITY_ID, PARAM_OWNER_SANBOXID);
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(BASE_URL + "/list")
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets event by id.
     *
     * @param context the context
     */
    @Test
    public void getEventById(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_EventRoute.PARAM_ID, "55847ed0d040353767a48e68")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body(PARAM_LABEL, notNullValue())
                    .body(PARAM_LABEL, is("Amical"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets event by id with non logged user.
     */
    @Test
    public void getEventByIdWithNonLoggedUser() {
        given().when().get(BASE_URL + "/get")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets event by id with wrong http method.
     */
    @Test
    public void getEventByIdWithWrongHttpMethod() {
        given().when().post(BASE_URL + "/get")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets event by id with missing parameters.
     *
     * @param context the context
     */
    @Test
    public void getEventByIdWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets event by id with wrong parameters.
     *
     * @param context the context
     */
    @Test
    public void getEventByIdWithWrongParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_EventRoute.PARAM_ID, "bla")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Update event.
     *
     * @param context the context
     */
    @Test
    public void updateEvent(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND, DATA_SANDBOXES_HAND);
        generateLoggedUser().setHandler(user -> {
            JsonObject event = new JsonObject(given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_EventRoute.PARAM_ID, "55847ed0d040353767a48e68")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body(PARAM_LABEL, notNullValue())
                    .body(PARAM_LABEL, is("Amical")).extract().asString());
            event.put(PARAM_LABEL, "toto");
            String id = given().header(TOKEN, user.result().getAccount().getToken())
                    .body(event.encode())
                    .when().post(BASE_URL + "/update")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body(PARAM_LABEL, is(event.getString(PARAM_LABEL)))
                    .extract().path("_id");

            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_EventRoute.PARAM_ID, id)
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body(PARAM_LABEL, notNullValue())
                    .body(PARAM_LABEL, is(event.getString(PARAM_LABEL)));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Update event with non logged user.
     */
    @Test
    public void updateEventWithNonLoggedUser() {
        given().when().post(BASE_URL + "/update")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update event with wrong http method.
     */
    @Test
    public void updateEventWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/update")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update event with missing params.
     *
     * @param context the context
     */
    @Test
    public void updateEventWithMissingParams(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            JsonObject event = new JsonObject(given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_EventRoute.PARAM_ID, "55847ed0d040353767a48e68")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body(PARAM_LABEL, notNullValue())
                    .body(PARAM_LABEL, is("Amical")).extract().asString());
            event.put(PARAM_LABEL, "toto");
            List<String> mandatoryParams = Arrays.asList(PARAM_LABEL, PARAM_ACTIVITY_ID, PARAM_OWNER, PARAM_START_DATE);
            event.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(event.encode());
                params2.remove(k);
                given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(BASE_URL + "/update")
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Delete event by id.
     *
     * @param context the context
     */
    @Test
    public void deleteEventById(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            JsonObject event = new JsonObject(given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_EventRoute.PARAM_ID, "55847ed0d040353767a48e68")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body(PARAM_LABEL, notNullValue())
                    .body(PARAM_LABEL, is("Amical"))
            .extract().asString());


            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam("_id", event.getString("_id"))
                    .when().delete(BASE_URL)
                    .then().assertThat().statusCode(200)
                    .body("event", is(1));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Delete event with non logged user.
     */
    @Test
    public void deleteEventWithNonLoggedUser() {
        given().when().delete(BASE_URL)
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Delete event with wrong http method.
     */
    @Test
    public void deleteEventWithWrongHttpMethod() {
        given().when().post(BASE_URL)
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Delete event without event id.
     *
     * @param context the context
     */
    @Test
    public void deleteEventWithoutEventId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().delete(BASE_URL)
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }
}
