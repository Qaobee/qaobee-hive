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
 */
package com.qaobee.hive.test.api.commons.settings;

import com.qaobee.hive.api.v1.commons.settings.ActivityRoute;
import com.qaobee.hive.api.v1.commons.settings.CountryRoute;
import com.qaobee.hive.api.v1.commons.settings.IndicatorRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Indicator testBodyParams.
 *
 * @author cke
 */
public class IndicatorServiceTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/settings/indicator");

    /**
     * Gets indicator testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getIndicatorTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_INDICATOR);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(IndicatorRoute.PARAM_ID, "559a9294889089a442f3d464")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body("code", notNullValue())
                    .body("code", is("hightPerson"));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets indicator with non logged user testBodyParams.
     */
    @Test
    public void getIndicatorWithNonLoggedUserTest() {
        given().when().get(BASE_URL + "/get")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets indicator with wrong http method testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getIndicatorWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(BASE_URL + "/get")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets indicator with wrong parameter testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getIndicatorWithWrongParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_INDICATOR);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(IndicatorRoute.PARAM_ID, "blabla")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets indicator with missing parameter testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getIndicatorWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_INDICATOR);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }


    /**
     * Gets list indicator testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getListIndicatorTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getCountry("CNTR-250-FR-FRA").then(country -> {
                getActivity("ACT-HAND", u).then(activity -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorRoute.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID));
                    params.put(IndicatorRoute.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID));
                    params.put(IndicatorRoute.PARAM_SCREEN, new JsonArray().add("COLLECTE"));

                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getList")
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(45));
                    async.complete();
                }).fail(e -> Assert.fail(e.getMessage()));
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets list indicator with non logged user testBodyParams.
     */
    @Test
    public void getListIndicatorWithNonLoggedUserTest() {
        given().when().post(BASE_URL + "/getList")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list indicator with wrong http method testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getListIndicatorWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(BASE_URL + "/getList")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets list indicator with missing parameter testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getListIndicatorWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(user -> {
            getActivity("ACT-HAND", user).then(activity -> {
                getCountry("CNTR-250-FR-FRA").then(country -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorRoute.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID));
                    params.put(IndicatorRoute.PARAM_SCREEN, new JsonArray().add("COLLECTE"));
                    given().header(TOKEN, user.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getList")
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

                    params.put(IndicatorRoute.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID));
                    params.remove(IndicatorRoute.PARAM_ACTIVITY_ID);
                    given().header(TOKEN, user.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getList")
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

                    params.put(IndicatorRoute.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID));
                    params.remove(IndicatorRoute.PARAM_SCREEN);
                    given().header(TOKEN, user.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getList")
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
                    async.complete();
                }).fail(e -> Assert.fail(e.getMessage()));
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets indicator by code testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getIndicatorByCodeTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getActivity("ACT-HAND", u).then(activity -> {
                getCountry("CNTR-250-FR-FRA").then(country -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorRoute.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID));
                    params.put(IndicatorRoute.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID));
                    params.put(IndicatorRoute.PARAM_INDICATOR_CODE, new JsonArray().add("hightPerson"));

                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getByCode")
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(1))
                            .body("code", hasItem("hightPerson"));
                    async.complete();
                }).fail(e -> Assert.fail(e.getMessage()));
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets indicator by code with non logged user testBodyParams.
     */
    @Test
    public void getIndicatorByCodeWithNonLoggedUserTest() {
        given().when().post(BASE_URL + "/getByCode")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets indicator by code with wrong http method testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getIndicatorByCodeWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(BASE_URL + "/getByCode")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets indicator by code with wrong parameter testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getIndicatorByCodeWithWrongParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getActivity("ACT-HAND", u).then(activity -> {
                getCountry("CNTR-250-FR-FRA").then(country -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorRoute.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID));
                    params.put(IndicatorRoute.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID));
                    params.put(IndicatorRoute.PARAM_INDICATOR_CODE, new JsonArray().add("blabla"));

                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getByCode")
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(0));

                    params.put(IndicatorRoute.PARAM_INDICATOR_CODE, new JsonArray().add("hightPerson"));
                    params.put(IndicatorRoute.PARAM_COUNTRY_ID, "123");
                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getByCode")
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(0));

                    params.put(IndicatorRoute.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID));
                    params.put(IndicatorRoute.PARAM_ACTIVITY_ID, "123");
                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getByCode")
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(0));
                    async.complete();
                }).fail(e -> Assert.fail(e.getMessage()));
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets indicator by code with missing parameter testBodyParams.
     *
     * @param context the context
     */
    @Test
    public void getIndicatorByCodeWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getActivity("ACT-HAND", u).then(activity -> {
                getCountry("CNTR-250-FR-FRA").then(country -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorRoute.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID));
                    params.put(IndicatorRoute.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID));

                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getByCode")
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

                    params.put(IndicatorRoute.PARAM_INDICATOR_CODE, new JsonArray().add("hightPerson"));
                    params.remove(IndicatorRoute.PARAM_COUNTRY_ID);
                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getByCode")
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

                    params.put(IndicatorRoute.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID));
                    params.remove(IndicatorRoute.PARAM_ACTIVITY_ID);
                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(BASE_URL + "/getByCode")
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
                    async.complete();
                }).fail(e -> Assert.fail(e.getMessage()));
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

}
