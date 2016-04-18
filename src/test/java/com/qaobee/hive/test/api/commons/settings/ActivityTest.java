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

import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * The type Activity test.
 *
 * @author cke
 */
public class ActivityTest extends VertxJunitSupport {
    /**
     * Gets activity.
     */
    @Test
    public void getActivity() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);
        given().queryParam(ActivityVerticle.PARAM_ID, "ACT-HAND")
                .when().get(getURL(ActivityVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("label", notNullValue())
                .body("label", is("commons.settings.activity.handball"));
    }

    /**
     * Gets activity with wrong http method test.
     */
    @Test
    public void getActivityWithWrongHttpMethodTest() {
        given().post(getURL(ActivityVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets activity with missing parameter test.
     */
    @Test
    public void getActivityWithMissingParameterTest() {
        given().get(getURL(ActivityVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given().queryParam(ActivityVerticle.PARAM_ID, "")
                .get(getURL(ActivityVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets activity with wrong activity id test.
     */
    @Test
    public void getActivityWithWrongActivityIdTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        given().queryParam(ActivityVerticle.PARAM_ID, "ACT-BIDON")
                .get(getURL(ActivityVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Tests getListHandler for ActivityVerticle
     */
    @Test
    public void getListOk() {

        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);

		/* User simulation connection */
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);

        final Map<String, List<String>> params = new HashMap<>();

        // id
        params.put(ActivityVerticle.PARAM_ID, Collections.singletonList("ACT-HAND"));
        req.setParams(params);

        final String reply = sendOnBus(ActivityVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertEquals(26, new JsonArray(reply).size());
    }

    /**
     * Tests getListEnableHandler for ActivityVerticle
     */
    @Test
    public void getListEnableOk() {

        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);

		/* User simulation connection */
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);

        final Map<String, List<String>> params = new HashMap<>();

        // id
        params.put(ActivityVerticle.PARAM_ID, Collections.singletonList("ACT-HAND"));
        req.setParams(params);

        final String reply = sendOnBus(ActivityVerticle.GET_LIST_ENABLE, req, user.getAccount().getToken());
        Assert.assertEquals(2, new JsonArray(reply).size());
    }
}
