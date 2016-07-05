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
import com.qaobee.hive.api.v1.commons.settings.SeasonVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
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
    public void getSeasonTest() {
        populate(POPULATE_ONLY, SETTINGS_SEASONS);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(SeasonVerticle.PARAM_ID, "559a9294889089a442f3d499")
                .when().get(getURL(SeasonVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is("SAISON 2014-2015"));
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
    public void getSeasonWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().post(getURL(SeasonVerticle.GET))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets season with wrong parameter test.
     */
    @Test
    public void getSeasonWithWrongParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_SEASONS);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(SeasonVerticle.PARAM_ID, "blabla")
                .when().get(getURL(SeasonVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets season with missing parameter test.
     */
    @Test
    public void getSeasonWithMissingParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_SEASONS);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().get(getURL(SeasonVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets season list test.
     */
    @Test
    public void getSeasonListTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, (String) getCountry("CNTR-250-FR-FRA").getField(CountryVerticle.PARAM_ID))
                .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-HAND", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                .then().assertThat().statusCode(200)
                .body("", hasSize(4));
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
    public void getSeasonListWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().post(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets season list with wrong parameter test.
     */
    @Test
    public void getSeasonListWithWrongParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, "1322")
                .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-HAND", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SeasonVerticle.PARAM_COUNTRY_ID,(String) getCountry("CNTR-250-FR-FRA").getField(CountryVerticle.PARAM_ID))
                .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, "ACT-BIDON")
                .when().get(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets season list with missing parameter test.
     */
    @Test
    public void getSeasonListWithMissingParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().get(getURL(SeasonVerticle.GET_LIST_BY_ACTIVITY))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }


    /**
     * Tests getCurrentHandler for SeasonVerticle
     */
    @Test
    public void getCurrentSeasonTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, (String) getCountry("CNTR-250-FR-FRA").getField(CountryVerticle.PARAM_ID))
                .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-HAND", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SeasonVerticle.GET_CURRENT))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is("SAISON 2016-2017"));
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
    public void getCurrentSeasonWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().post(getURL(SeasonVerticle.GET_CURRENT))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets current season with wrong parameter test.
     */
    @Test
    public void getCurrentSeasonWithWrongParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SeasonVerticle.PARAM_COUNTRY_ID, "1322")
                .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-HAND", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SeasonVerticle.GET_CURRENT))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SeasonVerticle.PARAM_COUNTRY_ID,(String) getCountry("CNTR-250-FR-FRA").getField(CountryVerticle.PARAM_ID))
                .queryParam(SeasonVerticle.PARAM_ACTIVITY_ID, "ACT-BIDON")
                .when().get(getURL(SeasonVerticle.GET_CURRENT))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets current season with missing parameter test.
     */
    @Test
    public void getCurrentSeasonWithMissingParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().get(getURL(SeasonVerticle.GET_CURRENT))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }
}
