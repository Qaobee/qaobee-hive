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

import com.qaobee.hive.api.v1.commons.settings.ActivityCfgVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Activity cfg test.
 */
public class ActivityCfgTest extends VertxJunitSupport {
    /**
     * Gets activity cfg.
     */
    @Test
    public void getActivityCfg(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);

        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                    .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                    .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                    .when().get(getURL(ActivityCfgVerticle.GET))
                    .then().assertThat().statusCode(200)
                    .body("activityId", notNullValue())
                    .body("activityId", is("ACT-HAND"));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets activity cfg with non logged user test.
     */
    @Test
    public void getActivityCfgWithNonLoggedUserTest() {
        given().when().get(getURL(ActivityCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets activity cfg with wrong http method test.
     */
    @Test
    public void getActivityCfgWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .post(getURL(ActivityCfgVerticle.GET))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets activity cfg with missing parameter test.
     */
    @Test
    public void getActivityCfgWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
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
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets activity cfg with wrong activity id test.
     */
    @Test
    public void getActivityCfgWithWrongActivityIdTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-BIDON")
                    .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                    .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                    .get(getURL(ActivityCfgVerticle.GET))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Get list of value for one parameter config.
     */
    @Test
    public void getParamsFields(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-HAND")
                    .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                    .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                    .queryParam(ActivityCfgVerticle.PARAM_FIELD_LIST, "listPositionType")
                    .when().get(getURL(ActivityCfgVerticle.PARAMS))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(7));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets params fields with non logged user test.
     */
    @Test
    public void getParamsFieldsWithNonLoggedUserTest() {
        given().when().get(getURL(ActivityCfgVerticle.PARAMS))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets params fields with wrong http method test.
     */
    @Test
    public void getParamsFieldsWithWrongHttpMethodTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .post(getURL(ActivityCfgVerticle.PARAMS))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets params fields with missing parameter test.
     */
    @Test
    public void getParamsFieldsWithMissingParameterTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
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
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets params fields with wrong activity id test.
     */
    @Test
    public void getParamsFieldsWithWrongActivityIdTest(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(ActivityCfgVerticle.PARAM_ACTIVITY_ID, "ACT-BIDON")
                    .queryParam(ActivityCfgVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                    .queryParam(ActivityCfgVerticle.PARAM_DATE, "1391209200000")
                    .queryParam(ActivityCfgVerticle.PARAM_FIELD_LIST, "listPositionType")
                    .get(getURL(ActivityCfgVerticle.PARAMS))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }
}
