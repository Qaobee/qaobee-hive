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
package com.qaobee.hive.test.api.commons.settings;

import com.qaobee.hive.api.v1.commons.settings.ActivityRoute;
import com.qaobee.hive.api.v1.commons.settings.CountryRoute;
import com.qaobee.hive.api.v1.commons.settings.SeasonVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.GregorianCalendar;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Season test.
 *
 * @author cke
 */
public class SeasonTest extends VertxJunitSupport {
    /**
     * Gets season test.
     */
    @Test
    public void getSeasonTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_SEASONS);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(SeasonVerticle.PARAM_ID, "559a9294889089a442f3d499")
                    .when().get(getURL(SeasonVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is("SAISON 2014-2015"));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets season with non logged user test.
     */
    @Test
    public void getSeasonWithNonLoggedUserTest() {
        given().when().get(getURL(SeasonVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets season with wrong http method test.
     */
    @Test
    public void getSeasonWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(getURL(SeasonVerticle.GET))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets season with wrong parameter test.
     */
    @Test
    public void getSeasonWithWrongParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_SEASONS);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(SeasonVerticle.PARAM_ID, "blabla")
                    .when().get(getURL(SeasonVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets season with missing parameter test.
     */
    @Test
    public void getSeasonWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_SEASONS);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(SeasonVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets season list test.
     */
    @Test
    public void getSeasonListTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getCountry("CNTR-250-FR-FRA").then(country -> {
                getActivity("ACT-HAND", u).then(activity -> {
                    given().header(TOKEN, u.getAccount().getToken())
                            .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, (String) country.getString(CountryRoute.PARAM_ID))
                            .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, (String) activity.getString(ActivityRoute.PARAM_ID))
                            .when().get(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(5));
                    async.complete();
                }).fail(e -> Assert.fail(e.getMessage()));
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets season list with non logged user test.
     */
    @Test
    public void getSeasonListWithNonLoggedUserTest() {
        given().when().get(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets season list with wrong http method test.
     */
    @Test
    public void getSeasonListWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets season list with wrong parameter test.
     */
    @Test
    public void getSeasonListWithWrongParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getCountry("CNTR-250-FR-FRA").then(country -> {
                getActivity("ACT-HAND", u).then(activity -> {
                    given().header(TOKEN, u.getAccount().getToken())
                            .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, "1322")
                            .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID))
                            .when().get(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                            .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                            .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

                    given().header(TOKEN, u.getAccount().getToken())
                            .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID))
                            .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, "ACT-BIDON")
                            .when().get(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                            .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                            .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
                    async.complete();
                }).fail(e -> Assert.fail(e.getMessage()));
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets season list with missing parameter test.
     */
    @Test
    public void getSeasonListWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }


    /**
     * Tests getCurrentHandler for SeasonVerticle
     */
    @Test
    public void getCurrentSeasonTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getCountry("CNTR-250-FR-FRA").then(country -> {
                getActivity("ACT-HAND", u).then(activity -> {
                    GregorianCalendar today = new GregorianCalendar();
                    int year = today.get(GregorianCalendar.MONTH) <= 5 ? today.get(GregorianCalendar.YEAR) - 1 : today.get(GregorianCalendar.YEAR);
                    given().header(TOKEN, u.getAccount().getToken())
                            .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID))
                            .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID))
                            .when().get(getURL(SeasonVerticle.GET_CURRENT))
                            .then().assertThat().statusCode(200)
                            .body("label", notNullValue())
                            .body("label", is("SAISON 2017-2018"))
                            .body("code", is("SAI-" + year));
                    async.complete();
                }).fail(e -> Assert.fail(e.getMessage()));
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets current season with non logged user test.
     */
    @Test
    public void getCurrentSeasonWithNonLoggedUserTest() {
        given().when().get(getURL(SeasonVerticle.GET_CURRENT))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets current season with wrong http method test.
     */
    @Test
    public void getCurrentSeasonWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(getURL(SeasonVerticle.GET_CURRENT))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets current season with wrong parameter test.
     */
    @Test
    public void getCurrentSeasonWithWrongParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getCountry("CNTR-250-FR-FRA").then(country -> {
                getActivity("ACT-HAND", u).then(activity -> {
                    given().header(TOKEN, u.getAccount().getToken())
                            .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, "1322")
                            .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID))
                            .when().get(getURL(SeasonVerticle.GET_CURRENT))
                            .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                            .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

                    given().header(TOKEN, u.getAccount().getToken())
                            .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, country.getString(CountryRoute.PARAM_ID))
                            .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, "ACT-BIDON")
                            .when().get(getURL(SeasonVerticle.GET_CURRENT))
                            .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                            .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
                    async.complete();
                }).fail(e -> Assert.fail(e.getMessage()));
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets current season with missing parameter test.
     */
    @Test
    public void getCurrentSeasonWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(SeasonVerticle.GET_CURRENT))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }
}
