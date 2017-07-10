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

import com.qaobee.hive.api.v1.commons.settings.ActivityRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * The type Activity test.
 *
 * @author cke
 */
public class ActivityTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/settings/activity");
    /**
     * Gets activity.
     */
    @Test
    public void getActivity() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);
        given().queryParam(ActivityRoute.PARAM_ID, "ACT-HAND")
                .when().get(BASE_URL+ "/get")
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is("commons.settings.activity.handball"));
    }

    /**
     * Gets activity with wrong http method test.
     */
    @Test
    public void getActivityWithWrongHttpMethodTest() {
        given().post(BASE_URL+ "/get")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets activity with missing parameter test.
     */
    @Test
    public void getActivityWithMissingParameterTest() {
        given().get(BASE_URL+ "/get")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().queryParam(ActivityRoute.PARAM_ID, "")
                .get(BASE_URL+ "/get")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets activity with wrong activity id test.
     */
    @Test
    public void getActivityWithWrongActivityIdTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        given().queryParam(ActivityRoute.PARAM_ID, "ACT-BIDON")
                .get(BASE_URL+ "/get")
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets list.
     */
    @Test
    public void getList() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);
        given().queryParam(ActivityRoute.PARAM_ID, "ACT-HAND")
                .when().get(BASE_URL+ "/list")
                .then().assertThat().statusCode(200)
                .body("", hasSize(26));
    }

    /**
     * Gets list with wrong http method test.
     */
    @Test
    public void getListWithWrongHttpMethodTest() {
        given().post(getURL(BASE_URL+ "/list"))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list enable.
     */
    @Test
    public void getListEnable() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);
        given().queryParam(ActivityRoute.PARAM_ID, "ACT-HAND")
                .when().get(BASE_URL+ "/listEnable")
                .then().assertThat().statusCode(200)
                .body("", hasSize(2));
    }

    /**
     * Gets list enable with wrong http method test.
     */
    @Test
    public void getListEnableWithWrongHttpMethodTest() {
        given().post(BASE_URL+ "/listEnable")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }
}
