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
package com.qaobee.hive.test.api.sandbox.stats;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.sandbox.stats.SB_StatisticsVerticle;
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
 * The type Sb stats test.
 */
public class SB_StatsTest extends VertxJunitSupport {
    /**
     * Gets list detail values.
     */
    @Test
    public void getListDetailValues() {
        populate(POPULATE_ONLY, DATA_STAT_HAND, DATA_USER_QAOBEE);
        User user = generateLoggedUser();
        final JsonObject params = new JsonObject()
                .putNumber(SB_StatisticsVerticle.PARAM_START_DATE, 1443650400000L)
                .putNumber(SB_StatisticsVerticle.PARAM_END_DATE, 1451516400000L)
                .putArray(SB_StatisticsVerticle.PARAM_INDICATOR_CODE, new JsonArray(new String[]{"originShootAtt"}))
                .putArray(SB_StatisticsVerticle.PARAM_LIST_OWNERS, new JsonArray(new String[]{"5f82c510-2c89-46b0-b87d-d3b59e748615"}));

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_StatisticsVerticle.GET_LISTDETAIL_VALUES))
                .then().assertThat().statusCode(200)
                .body("", hasSize(10))
                .body("code", hasItem("originShootAtt"));
    }

    /**
     * Gets list detail values with non logged user.
     */
    @Test
    public void getListDetailValuesWithNonLoggedUser() {
        given().when().post(getURL(SB_StatisticsVerticle.GET_LISTDETAIL_VALUES))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list detail values with wrong http method.
     */
    @Test
    public void getListDetailValuesWithWrongHttpMethod() {
        given().when().get(getURL(SB_StatisticsVerticle.GET_LISTDETAIL_VALUES))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list detail values with missing parameters.
     */
    @Test
    public void getListDetailValuesWithMissingParameters() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        final JsonObject params = new JsonObject()
                .putNumber(SB_StatisticsVerticle.PARAM_START_DATE, 1443650400000L)
                .putNumber(SB_StatisticsVerticle.PARAM_END_DATE, 1451516400000L)
                .putArray(SB_StatisticsVerticle.PARAM_INDICATOR_CODE, new JsonArray(new String[]{"originShootAtt"}))
                .putArray(SB_StatisticsVerticle.PARAM_LIST_OWNERS, new JsonArray(new String[]{"5f82c510-2c89-46b0-b87d-d3b59e748615"}));
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_StatisticsVerticle.GET_LISTDETAIL_VALUES).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params2.encode())
                    .when().post(getURL(SB_StatisticsVerticle.GET_LISTDETAIL_VALUES))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Gets stat group by.
     */
    @Test
    public void getStatGroupBy() {
        populate(POPULATE_ONLY, DATA_STAT_HAND, DATA_USER_QAOBEE);
        User user = generateLoggedUser();
        final JsonObject params = new JsonObject()
                .putString("aggregat", "COUNT")
                .putNumber(SB_StatisticsVerticle.PARAM_START_DATE, 1443650400000L)
                .putNumber(SB_StatisticsVerticle.PARAM_END_DATE, 1451516400000L)
                .putArray(SB_StatisticsVerticle.PARAM_INDICATOR_CODE, new JsonArray(new String[]{"originShootAtt"}))
                .putArray(SB_StatisticsVerticle.PARAM_LIST_OWNERS, new JsonArray(new String[]{"5f82c510-2c89-46b0-b87d-d3b59e748615"}));

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_StatisticsVerticle.GET_STAT_GROUPBY))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("value", hasItem(10));
    }

    /**
     * Gets stat group by with non logged user.
     */
    @Test
    public void getStatGroupByWithNonLoggedUser() {
        given().when().post(getURL(SB_StatisticsVerticle.GET_STAT_GROUPBY))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets stat group by with wrong http method.
     */
    @Test
    public void getStatGroupByWithWrongHttpMethod() {
        given().when().get(getURL(SB_StatisticsVerticle.GET_STAT_GROUPBY))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets stat group by with missing parameters.
     */
    @Test
    public void getStatGroupByWithMissingParameters() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        final JsonObject params = new JsonObject()
                .putString("aggregat", "COUNT")
                .putNumber(SB_StatisticsVerticle.PARAM_START_DATE, 1443650400000L)
                .putNumber(SB_StatisticsVerticle.PARAM_END_DATE, 1451516400000L)
                .putArray(SB_StatisticsVerticle.PARAM_INDICATOR_CODE, new JsonArray(new String[]{"originShootAtt"}))
                .putArray(SB_StatisticsVerticle.PARAM_LIST_OWNERS, new JsonArray(new String[]{"5f82c510-2c89-46b0-b87d-d3b59e748615"}));
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_StatisticsVerticle.GET_STAT_GROUPBY).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params2.encode())
                    .when().post(getURL(SB_StatisticsVerticle.GET_STAT_GROUPBY))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Add stat.
     */
    @Test
    public void addStat() {
        User user = generateLoggedUser();
        JsonObject s = generateStat(user, "fake", 1);
        given().header(TOKEN, user.getAccount().getToken())
                .body(s.encode())
                .when().put(getURL(SB_StatisticsVerticle.ADD_STAT))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("owner", is(user.get_id()));

        final JsonObject params = new JsonObject()
                .putNumber(SB_StatisticsVerticle.PARAM_START_DATE, 1443650400000L)
                .putNumber(SB_StatisticsVerticle.PARAM_END_DATE, System.currentTimeMillis() + 100)
                .putArray(SB_StatisticsVerticle.PARAM_INDICATOR_CODE, new JsonArray(new String[]{"fake"}))
                .putArray(SB_StatisticsVerticle.PARAM_LIST_OWNERS, new JsonArray(new String[]{user.get_id()}));

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_StatisticsVerticle.GET_LISTDETAIL_VALUES))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("code", hasItem("fake"));

    }

    /**
     * Add stat with non logged user.
     */
    @Test
    public void addStatWithNonLoggedUser() {
        given().when().put(getURL(SB_StatisticsVerticle.ADD_STAT))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add stat with wrong http method.
     */
    @Test
    public void addStatWithWrongHttpMethod() {
        given().when().get(getURL(SB_StatisticsVerticle.ADD_STAT))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add stat with missing parameters.
     */
    @Test
    public void addStatWithMissingParameters() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_StatisticsVerticle.ADD_STAT).mandatoryParams());
        JsonObject s = generateStat(user, "fake", 1);
        s.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(s.encode());
            params2.removeField(k);
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params2.encode())
                    .when().put(getURL(SB_StatisticsVerticle.ADD_STAT))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Add stat bulk.
     */
    @Test
    public void addStatBulk() {
        User user = generateLoggedUser();
        JsonArray stats = new JsonArray();
        for (int i = 0; i < 10; i++) {
            stats.add(generateStat(user, "fake", i));
        }

        given().header(TOKEN, user.getAccount().getToken())
                .body(stats.encode())
                .when().put(getURL(SB_StatisticsVerticle.ADD_STAT_BULK))
                .then().assertThat().statusCode(200)
                .body("count", notNullValue())
                .body("count", is(10));

        final JsonObject params = new JsonObject()
                .putNumber(SB_StatisticsVerticle.PARAM_START_DATE, 1443650400000L)
                .putNumber(SB_StatisticsVerticle.PARAM_END_DATE, System.currentTimeMillis() + 100)
                .putArray(SB_StatisticsVerticle.PARAM_INDICATOR_CODE, new JsonArray(new String[]{"fake"}))
                .putArray(SB_StatisticsVerticle.PARAM_LIST_OWNERS, new JsonArray(new String[]{user.get_id()}));

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_StatisticsVerticle.GET_LISTDETAIL_VALUES))
                .then().assertThat().statusCode(200)
                .body("", hasSize(10))
                .body("code", hasItem("fake"));

    }

    /**
     * Add stat bulk with non logged user.
     */
    @Test
    public void addStatBulkWithNonLoggedUser() {
        given().when().put(getURL(SB_StatisticsVerticle.ADD_STAT_BULK))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add stat bulk with wrong http method.
     */
    @Test
    public void addStatBulkWithWrongHttpMethod() {
        given().when().get(getURL(SB_StatisticsVerticle.ADD_STAT_BULK))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    private JsonObject generateStat(User u, String indicator, int chrono) {
        return new JsonObject()
                .putString("activityId", "ACT-HAND")
                .putBoolean("attack", false)
                .putNumber("chrono", chrono)
                .putNumber("timer", System.currentTimeMillis())
                .putNumber("value", 1)
                .putString("eventId", "12345")
                .putString("code", indicator)
                .putString("owner", u.get_id())
                .putArray("producter", new JsonArray().add(u.get_id()));
    }

}