/* ************************************************************************
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
 **/
package com.qaobee.hive.test.api.sandbox.stats;

import com.qaobee.hive.business.model.commons.users.User;
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

import static com.qaobee.hive.api.v1.sandbox.stats.SB_StatisticsRoute.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sb stats testBodyParams.
 */
public class SB_StatsTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/sandbox/stats/statistics");

    /**
     * Gets list detail values.
     *
     * @param context the context
     */
    @Test
    public void getListDetailValues(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STAT_HAND, DATA_USER_QAOBEE);
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put(PARAM_START_DATE, 1443650400000L)
                    .put(PARAM_END_DATE, 1451516400000L)
                    .put(PARAM_INDICATOR_CODE, new JsonArray().add("originShootAtt"))
                    .put(PARAM_LIST_OWNERS, new JsonArray().add("5f82c510-2c89-46b0-b87d-d3b59e748615"));

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getListDetailValue")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(10))
                    .body("code", hasItem("originShootAtt"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets list detail values with some params.
     *
     * @param context the context
     */
    @Test
    public void getListDetailValuesWithSomeParams(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STAT_HAND, DATA_USER_QAOBEE);
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put(PARAM_LIMIT_RESULT, 1)
                    .put(PARAM_VALUES, new JsonArray().add("CENTER9"))
                    .put(PARAM_START_DATE, 1443650400000L)
                    .put(PARAM_END_DATE, 1451516400000L)
                    .put(PARAM_INDICATOR_CODE, new JsonArray().add("originShootAtt"))
                    .put(PARAM_LIST_OWNERS, new JsonArray().add("5f82c510-2c89-46b0-b87d-d3b59e748615"));

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getListDetailValue")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("code", hasItem("originShootAtt"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets list detail values with non logged user.
     */
    @Test
    public void getListDetailValuesWithNonLoggedUser() {
        given().when().post(BASE_URL + "/getListDetailValue")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list detail values with wrong http method.
     */
    @Test
    public void getListDetailValuesWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/getListDetailValue")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list detail values with missing parameters.
     *
     * @param context the context
     */
    @Test
    public void getListDetailValuesWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put(PARAM_START_DATE, 1443650400000L)
                    .put(PARAM_END_DATE, 1451516400000L)
                    .put(PARAM_INDICATOR_CODE, new JsonArray().add("originShootAtt"))
                    .put(PARAM_LIST_OWNERS, new JsonArray().add("5f82c510-2c89-46b0-b87d-d3b59e748615"));
            List<String> mandatoryParams = Arrays.asList(PARAM_INDICATOR_CODE, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE);
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(BASE_URL + "/getListDetailValue")
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets stat group by.
     *
     * @param context the context
     */
    @Test
    public void getStatGroupBy(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STAT_HAND, DATA_USER_QAOBEE);
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put("aggregat", "COUNT")
                    .put(PARAM_START_DATE, 1443650400000L)
                    .put(PARAM_END_DATE, 1451516400000L)
                    .put(PARAM_INDICATOR_CODE, new JsonArray().add("originShootAtt"))
                    .put(PARAM_LIST_OWNERS, new JsonArray().add("5f82c510-2c89-46b0-b87d-d3b59e748615"));

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getStatGroupBy")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("value", hasItem(10));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets stat group by with some params.
     *
     * @param context the context
     */
    @Test
    @SuppressWarnings("squid:S3415")
    public void getStatGroupByWithSomeParams(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_STAT_HAND, DATA_USER_QAOBEE);
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(generateStat(user.result(), "originShootAtt", 1, "CENTER9").encode())
                    .when().put(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("owner", hasItems(user.result().get_id()));
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(generateStat(user.result(), "originShootAtt", 1, "CENTER9").encode())
                    .when().put(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("owner", hasItems(user.result().get_id()));
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(generateStat(user.result(), "originShootAtt", 1, "CENTER9").encode())
                    .when().put(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("owner", hasItems(user.result().get_id()));


            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(generateStat(user.result(), "fake", 1, 5).encode())
                    .when().put(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("owner", hasItems(user.result().get_id()));
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(generateStat(user.result(), "fake", 1, 3).encode())
                    .when().put(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("owner", hasItems(user.result().get_id()));
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(generateStat(user.result(), "fake", 1, 10).encode())
                    .when().put(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("owner", hasItems(user.result().get_id()));


            final JsonObject params = new JsonObject()
                    .put(PARAM_AGGREGAT, "COUNT")
                    .put(PARAM_LIST_SHOOTSEQID, new JsonArray().add("12345"))
                    .put(PARAM_START_DATE, System.currentTimeMillis() - 10000)
                    .put(PARAM_END_DATE, System.currentTimeMillis() + 10000)
                    .put(PARAM_VALUES, new JsonArray().add("CENTER9"))
                    .put(PARAM_LIST_GROUPBY, new JsonArray().add("code"))
                    .put(PARAM_LIMIT_RESULT, 1)
                    .put(PARAM_INDICATOR_CODE, new JsonArray().add("originShootAtt").add("fake"))
                    .put(PARAM_LIST_SORTBY, new JsonArray().add(new JsonObject().put("fieldName", "code").put("sortOrder", -1)))
                    .put(PARAM_LIST_OWNERS, new JsonArray().add(user.result().get_id()));

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getStatGroupBy")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("value", hasItem(3));

            params.remove(PARAM_VALUES);

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getStatGroupBy")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("value", hasItem(3));

            params.remove(PARAM_LIMIT_RESULT);

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getStatGroupBy")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(2))
                    .body("value", hasItem(3));

            params.remove(PARAM_LIST_GROUPBY);

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getStatGroupBy")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("value", hasItem(6));

            params.put(PARAM_AGGREGAT, "SUM");
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getStatGroupBy")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("value", hasItem(18));

            params.put(PARAM_AGGREGAT, "AVG");
            JsonArray json = new JsonArray(given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getStatGroupBy")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1)).extract().asString());
            Assert.assertEquals(6d, json.getJsonObject(0).getDouble("value"), 0d);
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets stat group by with non logged user.
     */
    @Test
    public void getStatGroupByWithNonLoggedUser() {
        given().when().post(BASE_URL + "/getStatGroupBy")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets stat group by with wrong http method.
     */
    @Test
    public void getStatGroupByWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/getStatGroupBy")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets stat group by with missing parameters.
     *
     * @param context the context
     */
    @Test
    public void getStatGroupByWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put("aggregat", "COUNT")
                    .put(PARAM_START_DATE, 1443650400000L)
                    .put(PARAM_END_DATE, 1451516400000L)
                    .put(PARAM_INDICATOR_CODE, new JsonArray().add("originShootAtt"))
                    .put(PARAM_LIST_OWNERS, new JsonArray().add("5f82c510-2c89-46b0-b87d-d3b59e748615"));
            List<String> mandatoryParams = Arrays.asList(PARAM_AGGREGAT, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE);
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(BASE_URL + "/getStatGroupBy")
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Add stat.
     *
     * @param context the context
     */
    @Test
    public void addStat(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            JsonObject s = generateStat(user.result(), "fake", 1);
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(s.encode())
                    .when().put(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("owner", hasItems(user.result().get_id()));

            final JsonObject params = new JsonObject()
                    .put(PARAM_START_DATE, 1443650400000L)
                    .put(PARAM_END_DATE, System.currentTimeMillis() + 100)
                    .put(PARAM_INDICATOR_CODE, new JsonArray().add("fake"))
                    .put(PARAM_LIST_OWNERS, new JsonArray().add(user.result().get_id()));

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getListDetailValue")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("code", hasItem("fake"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Add stat without timer.
     *
     * @param context the context
     */
    @Test
    public void addStatWithoutTimer(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            JsonObject s = generateStat(user.result(), "fake", 1);
            s.remove(TIMER_FIELD);
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(s.encode())
                    .when().put(BASE_URL + "/add")
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("owner", hasItems(user.result().get_id()));

            final JsonObject params = new JsonObject()
                    .put(PARAM_START_DATE, 1443650400000L)
                    .put(PARAM_END_DATE, System.currentTimeMillis() + 100)
                    .put(PARAM_INDICATOR_CODE, new JsonArray().add("fake"))
                    .put(PARAM_LIST_OWNERS, new JsonArray().add(user.result().get_id()));

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getListDetailValue")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("code", hasItem("fake"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Add stat with non logged user.
     */
    @Test
    public void addStatWithNonLoggedUser() {
        given().when().put(BASE_URL + "/add")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add stat with wrong http method.
     */
    @Test
    public void addStatWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/add")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add stat with missing parameters.
     *
     * @param context the context
     */
    @Test
    public void addStatWithMissingParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            List<String> mandatoryParams = Arrays.asList(CODE_FIELD, OWNER_FIELD);
            JsonObject s = generateStat(user.result(), "fake", 1);
            s.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(s.encode());
                params2.remove(k);
                given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().put(BASE_URL + "/add")
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Add stat bulk with duplicates.
     *
     * @param context the context
     */
    @Test
    public void addStatBulkWithDuplicates(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        generateLoggedUser().setHandler(user -> {
            JsonArray stats = new JsonArray();
            for (int i = 0; i < 5; i++) {
                stats.add(generateStat(user.result(), "fake", i));
            }

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(stats.encode())
                    .when().put(BASE_URL + "/addBulk")
                    .then().assertThat().statusCode(200)
                    .body("count", notNullValue())
                    .body("count", is(5));

            for (int i = 0; i < 5; i++) {
                stats.add(generateStat(user.result(), "fake", i));
            }
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(stats.encode())
                    .when().put(BASE_URL + "/addBulk")
                    .then().assertThat().statusCode(200)
                    .body("count", notNullValue())
                    .body("count", is(5));

            final JsonObject params = new JsonObject()
                    .put(PARAM_START_DATE, 1443650400000L)
                    .put(PARAM_END_DATE, System.currentTimeMillis() + 100)
                    .put(PARAM_INDICATOR_CODE, new JsonArray().add("fake"))
                    .put(PARAM_LIST_OWNERS, new JsonArray().add(user.result().get_id()));

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(BASE_URL + "/getListDetailValue")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(10))
                    .body("code", hasItem("fake"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Add stat bulk with non logged user.
     */
    @Test
    public void addStatBulkWithNonLoggedUser() {
        given().when().put(BASE_URL + "/addBulk")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add stat bulk with wrong http method.
     */
    @Test
    public void addStatBulkWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/addBulk")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }


    /**
     * Delete stats.
     *
     * @param context the context
     */
    @Test
    public void deleteStats(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            JsonArray stats = new JsonArray();
            for (int i = 0; i < 5; i++) {
                stats.add(generateStat(user.result(), "fake", i));
            }

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(stats.encode())
                    .when().put(BASE_URL + "/addBulk")
                    .then().assertThat().statusCode(200)
                    .body("count", notNullValue())
                    .body("count", is(5));

            for (int i = 0; i < 5; i++) {
                stats.add(generateStat(user.result(), "fake", i));
            }
            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(stats.encode())
                    .when().put(BASE_URL + "/addBulk")
                    .then().assertThat().statusCode(200)
                    .body("count", notNullValue())
                    .body("count", is(5));

            given().header(TOKEN, user.result().getAccount().getToken())
                    .queryParam("eventId", "12345")
                    .when().delete(BASE_URL + "/")
                    .then().assertThat().statusCode(200)
                    .body("deleteCount", is(10));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Delete stats with non logged user.
     */
    @Test
    public void deleteStatsWithNonLoggedUser() {
        given().when().delete(BASE_URL + "/")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Delete stats with wrong http method.
     */
    @Test
    public void deleteStatsWithWrongHttpMethod() {
        given().when().post(BASE_URL + "/")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Delete stats without event id.
     *
     * @param context the context
     */
    @Test
    public void deleteStatsWithoutEventId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().delete(BASE_URL + "/")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    private JsonObject generateStat(User u, String indicator, int chrono, String value) {
        try {
            Thread.sleep(10); // NOSONAR
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new JsonObject()
                .put("activityId", "ACT-HAND")
                .put("attack", false)
                .put("chrono", chrono)
                .put("timer", System.currentTimeMillis())
                .put("value", value)
                .put("eventId", "12345")
                .put("shootSeqId", "12345")
                .put("code", indicator)
                .put("owner", new JsonArray().add(u.get_id()))
                .put("producter", new JsonArray().add(u.get_id()));
    }

    private JsonObject generateStat(User u, String indicator, int chrono) {
        return generateStat(u, indicator, chrono, 1);
    }

    private JsonObject generateStat(User u, String indicator, int chrono, int value) {
        try {
            Thread.sleep(10); // NOSONAR
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new JsonObject()
                .put("activityId", "ACT-HAND")
                .put("attack", false)
                .put("chrono", chrono)
                .put("timer", System.currentTimeMillis())
                .put("value", value)
                .put("eventId", "12345")
                .put("shootSeqId", "12345")
                .put("code", indicator)
                .put("owner", new JsonArray().add(u.get_id()))
                .put("producter", new JsonArray().add(u.get_id()));
    }

}