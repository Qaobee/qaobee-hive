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

import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.api.v1.commons.settings.CountryVerticle;
import com.qaobee.hive.api.v1.commons.settings.IndicatorVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Indicator test.
 *
 * @author cke
 */
public class IndicatorTest extends VertxJunitSupport {

    /**
     * Gets indicator test.
     */
    @Test
    public void getIndicatorTest() {
        populate(POPULATE_ONLY, SETTINGS_INDICATOR);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(IndicatorVerticle.PARAM_ID, "559a9294889089a442f3d464")
                    .when().get(getURL(IndicatorVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("code", notNullValue())
                    .body("code", is("hightPerson"));
        });
    }

    /**
     * Gets indicator with non logged user test.
     */
    @Test
    public void getIndicatorWithNonLoggedUserTest() {
        given().when().get(getURL(IndicatorVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets indicator with wrong http method test.
     */
    @Test
    public void getIndicatorWithWrongHttpMethodTest() {
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(getURL(IndicatorVerticle.GET))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
        });
    }

    /**
     * Gets indicator with wrong parameter test.
     */
    @Test
    public void getIndicatorWithWrongParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_INDICATOR);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(IndicatorVerticle.PARAM_ID, "blabla")
                    .when().get(getURL(IndicatorVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
        });
    }

    /**
     * Gets indicator with missing parameter test.
     */
    @Test
    public void getIndicatorWithMissingParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_INDICATOR);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(IndicatorVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }


    /**
     * Gets list indicator test.
     */
    @Test
    public void getListIndicatorTest() {
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getCountry("CNTR-250-FR-FRA").then(country -> {
                getActivity("ACT-HAND", u).then(activity -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorVerticle.PARAM_COUNTRY_ID, country.getString(CountryVerticle.PARAM_ID));
                    params.put(IndicatorVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityVerticle.PARAM_ID));
                    params.put(IndicatorVerticle.PARAM_SCREEN, new JsonArray().add("COLLECTE"));

                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_LIST))
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(45));
                });
            });

        });
    }

    /**
     * Gets list indicator with non logged user test.
     */
    @Test
    public void getListIndicatorWithNonLoggedUserTest() {
        given().when().post(getURL(IndicatorVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list indicator with wrong http method test.
     */
    @Test
    public void getListIndicatorWithWrongHttpMethodTest() {
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(IndicatorVerticle.GET_LIST))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
        });
    }

    /**
     * Gets list indicator with missing parameter test.
     */
    @Test
    public void getListIndicatorWithMissingParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(user -> {
            getActivity("ACT-HAND", user).then(activity -> {
                getCountry("CNTR-250-FR-FRA").then(country -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityVerticle.PARAM_ID));
                    params.put(IndicatorVerticle.PARAM_SCREEN, new JsonArray().add("COLLECTE"));
                    given().header(TOKEN, user.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_LIST))
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

                    params.put(IndicatorVerticle.PARAM_COUNTRY_ID, country.getString(CountryVerticle.PARAM_ID));
                    params.remove(IndicatorVerticle.PARAM_ACTIVITY_ID);
                    given().header(TOKEN, user.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_LIST))
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

                    params.put(IndicatorVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityVerticle.PARAM_ID));
                    params.remove(IndicatorVerticle.PARAM_SCREEN);
                    given().header(TOKEN, user.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_LIST))
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
                });
            });

        });
    }

    /**
     * Gets indicator by code test.
     */
    @Test
    public void getIndicatorByCodeTest() {
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getActivity("ACT-HAND", u).then(activity -> {
                getCountry("CNTR-250-FR-FRA").then(country -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorVerticle.PARAM_COUNTRY_ID, country.getString(CountryVerticle.PARAM_ID));
                    params.put(IndicatorVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityVerticle.PARAM_ID));
                    params.put(IndicatorVerticle.PARAM_INDICATOR_CODE, new JsonArray().add("hightPerson"));

                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_BY_CODE))
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(1))
                            .body("code", hasItem("hightPerson"));
                });
            });
        });
    }

    /**
     * Gets indicator by code with non logged user test.
     */
    @Test
    public void getIndicatorByCodeWithNonLoggedUserTest() {
        given().when().post(getURL(IndicatorVerticle.GET_BY_CODE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets indicator by code with wrong http method test.
     */
    @Test
    public void getIndicatorByCodeWithWrongHttpMethodTest() {
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(IndicatorVerticle.GET_BY_CODE))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
        });
    }

    /**
     * Gets indicator by code with wrong parameter test.
     */
    @Test
    public void getIndicatorByCodeWithWrongParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getActivity("ACT-HAND", u).then(activity -> {
                getCountry("CNTR-250-FR-FRA").then(country -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorVerticle.PARAM_COUNTRY_ID, country.getString(CountryVerticle.PARAM_ID));
                    params.put(IndicatorVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityVerticle.PARAM_ID));
                    params.put(IndicatorVerticle.PARAM_INDICATOR_CODE, new JsonArray().add("blabla"));

                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_BY_CODE))
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(0));

                    params.put(IndicatorVerticle.PARAM_INDICATOR_CODE, new JsonArray().add("hightPerson"));
                    params.put(IndicatorVerticle.PARAM_COUNTRY_ID, "123");
                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_BY_CODE))
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(0));

                    params.put(IndicatorVerticle.PARAM_COUNTRY_ID, country.getString(CountryVerticle.PARAM_ID));
                    params.put(IndicatorVerticle.PARAM_ACTIVITY_ID, "123");
                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_BY_CODE))
                            .then().assertThat().statusCode(200)
                            .body("", hasSize(0));
                });
            });
        });
    }

    /**
     * Gets indicator by code with missing parameter test.
     */
    @Test
    public void getIndicatorByCodeWithMissingParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        generateLoggedUser().then(u -> {
            getActivity("ACT-HAND", u).then(activity -> {
                getCountry("CNTR-250-FR-FRA").then(country -> {
                    final JsonObject params = new JsonObject();
                    params.put(IndicatorVerticle.PARAM_COUNTRY_ID, country.getString(CountryVerticle.PARAM_ID));
                    params.put(IndicatorVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityVerticle.PARAM_ID));

                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_BY_CODE))
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

                    params.put(IndicatorVerticle.PARAM_INDICATOR_CODE, new JsonArray().add("hightPerson"));
                    params.remove(IndicatorVerticle.PARAM_COUNTRY_ID);
                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_BY_CODE))
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

                    params.put(IndicatorVerticle.PARAM_COUNTRY_ID, country.getString(CountryVerticle.PARAM_ID));
                    params.remove(IndicatorVerticle.PARAM_ACTIVITY_ID);
                    given().header(TOKEN, u.getAccount().getToken())
                            .body(params.encode())
                            .when().post(getURL(IndicatorVerticle.GET_BY_CODE))
                            .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                            .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
                });
            });
        });
    }

}
