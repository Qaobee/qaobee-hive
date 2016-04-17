/*
 * __________________
 *   Qaobee
 * __________________
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

import com.qaobee.hive.api.v1.commons.settings.ActivityCfgVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Activity cfg test.
 */
public class ActivityCfgTest extends VertxJunitSupport {
    @Test
    public void getActivityCfg() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                .when().get(getURL(ActivityCfgVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("activityId", notNullValue())
                .body("activityId", is("ACT-HAND"));
    }

    @Test
    public void getActivityCfgWithNonLoggedUserTest() {
        given().when().get(getURL(ActivityCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }
    @Test
    public void getActivityCfgWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .post(getURL(ActivityCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }
    @Test
    public void getActivityCfgWithMissingParameterTest() {
        User u =  generateLoggedUser();
        given().header(TOKEN,u.getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                .get(getURL(ActivityCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                .get(getURL(ActivityCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                .get(getURL(ActivityCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }
    @Test
    public void getActivityCfgWithWrongActivityIdTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-BIDON")
                .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                .get(getURL(ActivityCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.DB_NO_ROW_RETURNED.getCode())
                .body(CODE, is(ExceptionCodes.DB_NO_ROW_RETURNED.toString()));
    }

    /**
     * Get list of value for one parameter config.
     */
    @Test
    public void getParamsFields() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                .queryParam(ActivityCfgVerticle.PARAM_FIELD_LIST, "listPositionType")
                .when().get(getURL(ActivityCfgVerticle.PARAMS))
                .then().assertThat().statusCode(200)
                .body("", hasSize(7));
    }
    @Test
    public void getParamsFieldsWithNonLoggedUserTest() {
        given().when().get(getURL(ActivityCfgVerticle.PARAMS))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }
    @Test
    public void getParamsFieldsWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .post(getURL(ActivityCfgVerticle.PARAMS))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }
    @Test
    public void getParamsFieldsWithMissingParameterTest() {
        User u =  generateLoggedUser();
        given().header(TOKEN,u.getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                .queryParam(ActivityCfgVerticle.PARAM_FIELD_LIST, "listPositionType")
                .get(getURL(ActivityCfgVerticle.PARAMS))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                .queryParam(ActivityCfgVerticle.PARAM_FIELD_LIST, "listPositionType")
                .get(getURL(ActivityCfgVerticle.PARAMS))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                .queryParam(ActivityCfgVerticle.PARAM_FIELD_LIST, "listPositionType")
                .get(getURL(ActivityCfgVerticle.PARAMS))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                .get(getURL(ActivityCfgVerticle.PARAMS))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }
    @Test
    public void getParamsFieldsWithWrongActivityIdTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-BIDON")
                .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                .queryParam(ActivityCfgVerticle.PARAM_FIELD_LIST, "listPositionType")
                .get(getURL(ActivityCfgVerticle.PARAMS))
                .then().assertThat().statusCode(ExceptionCodes.DB_NO_ROW_RETURNED.getCode())
                .body(CODE, is(ExceptionCodes.DB_NO_ROW_RETURNED.toString()));
    }
}
