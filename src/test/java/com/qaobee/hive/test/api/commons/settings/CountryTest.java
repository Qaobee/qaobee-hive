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
package com.qaobee.hive.test.api.commons.settings;

import com.qaobee.hive.api.v1.commons.settings.CountryRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * The type Country test.
 *
 * @author cke
 */
public class CountryTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/settings/country");
    
    /**
     * Gets country.
     */
    @Test
    public void getCountry() {
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);
        given().queryParam(CountryRoute.PARAM_ID, "CNTR-250-FR-FRA")
                .when().get(BASE_URL + "/get")
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is("France"));
    }

    /**
     * Gets country with wrong http method test.
     */
    @Test
    public void getCountryWithWrongHttpMethodTest() {
        given().post(BASE_URL + "/get")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets country with missing parameter test.
     */
    @Test
    public void getCountryWithMissingParameterTest() {
        given().get(BASE_URL + "/get")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().queryParam(CountryRoute.PARAM_ID, "")
                .get(BASE_URL + "/get")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets country with wrong activity id test.
     */
    @Test
    public void getCountryWithWrongActivityIdTest() {
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);
        given().queryParam(CountryRoute.PARAM_ID, "Pastafarie")
                .get(BASE_URL + "/get")
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }


    /**
     * Gets list of countries.
     */
    @Test
    public void getListOfCountries() {
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);

        given().queryParam(CountryRoute.PARAM_LOCAL, "fr")
                .when().get(BASE_URL + "/getList")
                .then().assertThat().statusCode(200)
                .body("", hasSize(202));

        given().queryParam(CountryRoute.PARAM_LABEL, "//Fra")
                .queryParam(CountryRoute.PARAM_LOCAL, "fr")
                .when().get(BASE_URL + "/getList")
                .then().assertThat().statusCode(200)
                .body("", hasSize(4));
    }

    /**
     * Gets list of countries with wrong http method test.
     */
    @Test
    public void getListOfCountriesWithWrongHttpMethodTest() {
        given().post(BASE_URL + "/getList")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list of countries with missing parameter test.
     */
    @Test
    public void getListOfCountriesWithMissingParameterTest() {
        given().get(BASE_URL + "/getList")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().queryParam(CountryRoute.PARAM_LOCAL, "")
                .get(BASE_URL + "/getList")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets list of countries with wrong param local test.
     */
    @Test
    public void getListOfCountriesWithWrongParamLocalTest() {
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);
        given().queryParam(CountryRoute.PARAM_LOCAL, "Kl")
                .get(BASE_URL + "/getList")
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));
    }

    /**
     * Gets country alpha 2.
     */
    @Test
    public void getCountryAlpha2() {
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);
        given().queryParam(CountryRoute.PARAM_ALPHA2, "fr")
                .when().get(BASE_URL + "/getAlpha2")
                .then().assertThat().statusCode(200)
                .body("", notNullValue())
                .body("label", is("France"));
        given().queryParam(CountryRoute.PARAM_ALPHA2, "FR")
                .when().get(BASE_URL + "/getAlpha2")
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is("France"));
    }

    /**
     * Gets country alpha 2 with wrong http method test.
     */
    @Test
    public void getCountryAlpha2WithWrongHttpMethodTest() {
        given().post(BASE_URL + "/getAlpha2")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets country alpha 2 with missing parameter test.
     */
    @Test
    public void getCountryAlpha2WithMissingParameterTest() {
        given().get(BASE_URL + "/getAlpha2")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().queryParam(CountryRoute.PARAM_ALPHA2, "")
                .get(BASE_URL + "/getAlpha2")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets country alpha 2 with wrong activity id test.
     */
    @Test
    public void getCountryAlpha2WithWrongActivityIdTest() {
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);
        given().queryParam(CountryRoute.PARAM_ALPHA2, "kl")
                .get(BASE_URL + "/getAlpha2")
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }
}
