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
import com.qaobee.hive.api.v1.commons.settings.SeasonRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

import java.util.GregorianCalendar;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Season testBodyParams.
 *
 * @author cke
 */
public class SeasonServiceTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/settings/season");

    /**
     * Gets season testBodyParams.
     */
    @Test
    public void getSeasonTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_SEASONS);
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(SeasonRoute.PARAM_ID, "559a9294889089a442f3d499")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is("SAISON 2014-2015"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets season with non logged user testBodyParams.
     */
    @Test
    public void getSeasonWithNonLoggedUserTest() {
        given().when().get(BASE_URL + "/get")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets season with wrong http method testBodyParams.
     */
    @Test
    public void getSeasonWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().post(BASE_URL + "/get")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets season with wrong parameter testBodyParams.
     */
    @Test
    public void getSeasonWithWrongParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_SEASONS);
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(SeasonRoute.PARAM_ID, "blabla")
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets season with missing parameter testBodyParams.
     */
    @Test
    public void getSeasonWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_SEASONS);
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().get(BASE_URL + "/get")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets season list testBodyParams.
     */
    @Test
    public void getSeasonListTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> getCountry("CNTR-250-FR-FRA").setHandler(country -> getActivity("ACT-HAND").setHandler(activity -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(SeasonRoute.PARAM_COUNTRY_ID, (String) country.result().getString(CountryRoute.PARAM_ID))
                    .queryParam(SeasonRoute.PARAM_ACTIVITY_ID, (String) activity.result().getString(ActivityRoute.PARAM_ID))
                    .when().get(BASE_URL + "/getListByActivity")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(5));
            async.complete();
        })));
        async.await(TIMEOUT);
    }

    /**
     * Gets season list with non logged user testBodyParams.
     */
    @Test
    public void getSeasonListWithNonLoggedUserTest() {
        given().when().get(BASE_URL + "/getListByActivity")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets season list with wrong http method testBodyParams.
     */
    @Test
    public void getSeasonListWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().post(BASE_URL + "/getListByActivity")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets season list with wrong parameter testBodyParams.
     */
    @Test
    public void getSeasonListWithWrongParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> getCountry("CNTR-250-FR-FRA").setHandler(country -> getActivity("ACT-HAND").setHandler(activity -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(SeasonRoute.PARAM_COUNTRY_ID, "1322")
                    .queryParam(SeasonRoute.PARAM_ACTIVITY_ID, activity.result().getString(ActivityRoute.PARAM_ID))
                    .when().get(BASE_URL + "/getListByActivity")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(SeasonRoute.PARAM_COUNTRY_ID, country.result().getString(CountryRoute.PARAM_ID))
                    .queryParam(SeasonRoute.PARAM_ACTIVITY_ID, "ACT-BIDON")
                    .when().get(BASE_URL + "/getListByActivity")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        })));
        async.await(TIMEOUT);
    }

    /**
     * Gets season list with missing parameter testBodyParams.
     */
    @Test
    public void getSeasonListWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().get(BASE_URL + "/getListByActivity")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }


    /**
     * Tests getCurrentHandler for SeasonVerticle
     */
    @Test
    public void getCurrentSeasonTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> getCountry("CNTR-250-FR-FRA").setHandler(country -> getActivity("ACT-HAND").setHandler(activity -> {
            GregorianCalendar today = new GregorianCalendar();
            int year = today.get(GregorianCalendar.MONTH) <= 5 ? today.get(GregorianCalendar.YEAR) - 1 : today.get(GregorianCalendar.YEAR);
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(SeasonRoute.PARAM_COUNTRY_ID, country.result().getString(CountryRoute.PARAM_ID))
                    .queryParam(SeasonRoute.PARAM_ACTIVITY_ID, activity.result().getString(ActivityRoute.PARAM_ID))
                    .when().get(BASE_URL + "/current")
                    .then().assertThat().statusCode(200)
                    .body("label", notNullValue())
                    .body("label", is("SAISON 2017-2018"))
                    .body("code", is("SAI-" + year));
            async.complete();
        })));
        async.await(TIMEOUT);
    }

    /**
     * Gets current season with non logged user testBodyParams.
     */
    @Test
    public void getCurrentSeasonWithNonLoggedUserTest() {
        given().when().get(BASE_URL + "/current")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets current season with wrong http method testBodyParams.
     */
    @Test
    public void getCurrentSeasonWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().post(BASE_URL + "/current")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets current season with wrong parameter testBodyParams.
     */
    @Test
    public void getCurrentSeasonWithWrongParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> getCountry("CNTR-250-FR-FRA").setHandler(country -> getActivity("ACT-HAND").setHandler(activity -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(SeasonRoute.PARAM_COUNTRY_ID, "1322")
                    .queryParam(SeasonRoute.PARAM_ACTIVITY_ID, activity.result().getString(ActivityRoute.PARAM_ID))
                    .when().get(BASE_URL + "/current")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(SeasonRoute.PARAM_COUNTRY_ID, country.result().getString(CountryRoute.PARAM_ID))
                    .queryParam(SeasonRoute.PARAM_ACTIVITY_ID, "ACT-BIDON")
                    .when().get(BASE_URL + "/current")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        })));
        async.await(TIMEOUT);
    }

    /**
     * Gets current season with missing parameter testBodyParams.
     */
    @Test
    public void getCurrentSeasonWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().get(BASE_URL + "/current")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }
}
