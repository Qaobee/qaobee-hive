/*
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
package com.qaobee.hive.test.api.sandbox.stats;

import com.qaobee.hive.api.v1.sandbox.event.SB_EventRoute;
import com.qaobee.hive.api.v1.sandbox.stats.SB_CollectRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.qaobee.hive.api.v1.sandbox.stats.SB_CollectRoute.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sb Collect testBodyParams.
 *
 * @author cke
 */
public class SB_CollectTest extends VertxJunitSupport {
    private static final String EVENT_BASE_URL = getBaseURL("/sandbox/event/event");
    private static final String BASE_URL = getBaseURL("/sandbox/stats/collect");

    /**
     * Add collect.
     *
     * @param context the context
     */
    @Test
    public void addCollect(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_USER_QAOBEE, DATA_SANDBOXES_HAND, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            JsonObject event = new JsonObject(
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .queryParam(SB_EventRoute.PARAM_ID, "55847ed0d040353767a48e68")
                            .when().get(EVENT_BASE_URL + "/get")
                            .then().assertThat().statusCode(200)
                            .body(SB_EventRoute.PARAM_LABEL, notNullValue())
                            .body("activityId", is("ACT-HAND"))
                            .extract().asString());

            final JsonObject Collect = generateCollect(event);

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(Collect.encode())
                    .when().post(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("eventRef.address.city", is("Brest"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Add Collect with non logged user.
     */
    @Test
    public void addCollectWithNonLoggedUser() {
        given().when().post(BASE_URL + "/add")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add Collect with wrong http method.
     */
    @Test
    public void addCollectWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/add")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add Collect with missing parameters.
     *
     * @param context the context
     */
    @Test
    public void addCollectWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_USER_QAOBEE);
        generateLoggedUser().setHandler(user -> {

            JsonObject event = new JsonObject(
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .queryParam(SB_EventRoute.PARAM_ID, "5660c53ac630d9b391c0c4ec")
                            .when().get(EVENT_BASE_URL + "/get")
                            .then().assertThat().statusCode(200)
                            .body(SB_EventRoute.PARAM_LABEL, notNullValue())
                            .body("activityId", is("ACT-HAND"))
                            .extract().asString());

            final JsonObject Collect = generateCollect(event);

            List<String> mandatoryParams = Arrays.asList(PARAM_EVENT, PARAM_PLAYERS);
            Collect.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(Collect.encode());
                params2.remove(k);
                given().header(TOKEN, user.result().getAccount().getToken())
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
     * Update collect.
     *
     * @param context the context
     */
    @Test
    public void updateCollect(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_USER_QAOBEE, DATA_SANDBOXES_HAND, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            JsonObject event = new JsonObject(
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .queryParam(SB_EventRoute.PARAM_ID, "55847ed0d040353767a48e68")
                            .when().get(EVENT_BASE_URL + "/get")
                            .then().assertThat().statusCode(200)
                            .body(SB_EventRoute.PARAM_LABEL, notNullValue())
                            .body("activityId", is("ACT-HAND"))
                            .extract().asString());

            JsonObject collect = new JsonObject(
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .body(generateCollect(event).encode())
                            .when().post(BASE_URL + "/add")
                            .then().assertThat().statusCode(200)
                            .body("_id", notNullValue())
                            .body("eventRef.address.city", is("Brest"))
                            .extract().asString());

            long endDate = System.currentTimeMillis();
            collect.put("endDate", endDate);
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(collect.encode())
                    .when().post(BASE_URL + "/update")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("endDate", is(endDate));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Update collect with non logged user.
     */
    @Test
    public void updateCollectWithNonLoggedUser() {
        given().when().post(BASE_URL + "/update")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update collect with wrong http method.
     */
    @Test
    public void updateCollectWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/update")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update collect with missing parameters.
     *
     * @param context the context
     */
    @Test
    public void updateCollectWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_USER_QAOBEE, DATA_SANDBOXES_HAND, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            JsonObject event = new JsonObject(
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .queryParam(SB_EventRoute.PARAM_ID, "55847ed0d040353767a48e68")
                            .when().get(EVENT_BASE_URL + "/get")
                            .then().assertThat().statusCode(200)
                            .body(SB_EventRoute.PARAM_LABEL, notNullValue())
                            .body("activityId", is("ACT-HAND"))
                            .extract().asString());

            JsonObject collect = new JsonObject(
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .body(generateCollect(event).encode())
                            .when().post(BASE_URL + "/add")
                            .then().assertThat().statusCode(200)
                            .body("_id", notNullValue())
                            .body("eventRef.address.city", is("Brest"))
                            .extract().asString());

            long endDate = System.currentTimeMillis();
            collect.put("endDate", endDate);

            List<String> mandatoryParams = Arrays.asList(PARAM_EVENT, PARAM_PLAYERS);
            collect.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(collect.encode());
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
     * Gets list Collect.
     *
     * @param context the context
     */
    @Test
    public void getListCollect(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_USER_QAOBEE);
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put(PARAM_START_DATE, 1448491800000L)
                    .put(PARAM_END_DATE, 1448492500000L)
                    .put(SB_CollectRoute.PARAM_SANDBOX_ID, "561ec20b409937a6b439d4e9")
                    .put(SB_CollectRoute.PARAM_TEAM_ID, "937918db-848e-4a6d-8feb-a7ba6bd60f5a")
                    .put(SB_CollectRoute.PARAM_EVENT_ID, "35d65151-2fe5-48e1-a219-8534412b6bca")
                    .put(SB_CollectRoute.PARAM_EFFECTIVE_ID, "561ec4d0409937a6b439d4ea");

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/list")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("eventRef.label", hasItem("Journée 10"));

            params.put(SB_CollectRoute.PARAM_SANDBOX_ID, "TOTO");

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
     * Gets list Collect with non logged user.
     */
    @Test
    public void getListCollectWithNonLoggedUser() {
        given().when().post(BASE_URL + "/list")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list Collect with wrong http method.
     */
    @Test
    public void getListCollectWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/list")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list Collect with missing parameters.
     *
     * @param context the context
     */
    @Test
    public void getListCollectWithMissingParameters(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put(PARAM_START_DATE, 1448491800000L)
                    .put(PARAM_END_DATE, 1448492500000L)
                    .put(SB_CollectRoute.PARAM_SANDBOX_ID, "561ec20b409937a6b439d4e9")
                    .put(SB_CollectRoute.PARAM_EFFECTIVE_ID, "561ec4d0409937a6b439d4ea");

            List<String> mandatoryParams = Arrays.asList(PARAM_START_DATE, PARAM_END_DATE, PARAM_SANDBOX_ID);
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
     * Gets Collect by id.
     *
     * @param context the context
     */
    @Test
    public void getCollectById(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_USER_QAOBEE);
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam(SB_CollectRoute.PARAM_ID, "565e0f0dbcda594d193e24db")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("status", is("done"));
            async.complete();
        });
        async.await(TIMEOUT);
    }


    /**
     * Gets Collect by id with non logged user.
     */
    @Test
    public void getCollectByIdWithNonLoggedUser() {
        given().when().get(BASE_URL + "/get")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets Collect by id with wrong http method.
     */
    @Test
    public void getCollectByIdWithWrongHttpMethod() {
        given().when().post(BASE_URL + "/get")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets Collect by id with missing parameters.
     *
     * @param context the context
     */
    @Test
    public void getCollectByIdWithMissingParameters(TestContext context) {
        Async async = context.async();
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
     * Delete collect by collect id.
     *
     * @param context the context
     */
    @Test
    public void deleteCollectByCollectId(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_USER_QAOBEE, DATA_SANDBOXES_HAND, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            JsonObject event = new JsonObject(
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .queryParam(SB_EventRoute.PARAM_ID, "55847ed0d040353767a48e68")
                            .when().get(EVENT_BASE_URL + "/get")
                            .then().assertThat().statusCode(200)
                            .body(SB_EventRoute.PARAM_LABEL, notNullValue())
                            .body("activityId", is("ACT-HAND"))
                            .extract().asString());

            final JsonObject Collect = generateCollect(event);

            JsonObject collect = new JsonObject( given().header(TOKEN, user.result().getAccount().getToken())
                    .body(Collect.encode())
                    .when().post(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("eventRef.address.city", is("Brest"))
                    .extract().asString());

            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam("_id", collect.getString("_id"))
                    .when().delete(BASE_URL)
                    .then().assertThat().statusCode(200)
                    .body("collect", is(1));

            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Delete event by collect id.
     *
     * @param context the context
     */
    @Test
    public void deleteEventByCollectId(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_USER_QAOBEE, DATA_SANDBOXES_HAND, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            JsonObject event = new JsonObject(
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .queryParam(SB_EventRoute.PARAM_ID, "55847ed0d040353767a48e68")
                            .when().get(EVENT_BASE_URL + "/get")
                            .then().assertThat().statusCode(200)
                            .body(SB_EventRoute.PARAM_LABEL, notNullValue())
                            .body("activityId", is("ACT-HAND"))
                            .extract().asString());

            final JsonObject Collect = generateCollect(event);

             given().header(TOKEN, user.result().getAccount().getToken())
                    .body(Collect.encode())
                    .when().post(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("eventRef.address.city", is("Brest"));

            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam("eventId", event.getString("_id"))
                    .when().delete(BASE_URL)
                    .then().assertThat().statusCode(200)
                    .body("collect", is(1));

            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Delete collect with non logged user.
     */
    @Test
    public void deleteCollectWithNonLoggedUser() {
        given().when().delete(BASE_URL)
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Delete collect with wrong http method.
     */
    @Test
    public void deleteCollectWithWrongHttpMethod() {
        given().when().post(BASE_URL)
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Delete collect without event id and collect id.
     *
     * @param context the context
     */
    @Test
    public void deleteCollectWithoutEventIdAndCollectId(TestContext context) {
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

    private JsonObject generateCollect(JsonObject event) {
        return new JsonObject()
                .put(PARAM_EVENT, event)
                .put(PARAM_PLAYERS, new JsonArray()
                        .add("1ce4591d-74a8-46e9-af80-d633f9344d27")
                        .add("26baf31a-f153-41b0-9e1d-c32cb9e859dd")
                        .add("43e62ae5-2a92-4e1a-9b9a-d1a399c096bd")
                        .add("46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"))
                .put(PARAM_START_DATE, 1435701600000L)
                .put(PARAM_END_DATE, 1435701600100L)
                .put("observers", new JsonObject()
                        .put("observer", new JsonObject()
                                .put("userId", "b50b3325-fdbd-41bf-bda4-81c827982001")
                                .put("indicators", new JsonArray().add("all"))
                        )
                );
    }
}
