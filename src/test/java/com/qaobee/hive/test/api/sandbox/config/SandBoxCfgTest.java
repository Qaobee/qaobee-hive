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

package com.qaobee.hive.test.api.sandbox.config;

import com.qaobee.hive.api.v1.sandbox.config.SB_SandBoxCfgVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sand box cfg test.
 */
public class SandBoxCfgTest extends VertxJunitSupport {

    /**
     * Gets sand box config by id.
     */
    @Test
    public void getSandBoxConfigById() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        String id = "558b0fc0bd2e39cdab651e21";
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxCfgVerticle.PARAM_ID, id)
                .when().get(getURL(SB_SandBoxCfgVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(id))
                .body("sandbox.owner", is(user.get_id()));
    }

    /**
     * Gets sand box config by id with non logged user.
     */
    @Test
    public void getSandBoxConfigByIdWithNonLoggedUser() {
        given().when().get(getURL(SB_SandBoxCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets sand box config by id with wrong http method.
     */
    @Test
    public void getSandBoxConfigByIdWithWrongHttpMethod() {
        given().when().post(getURL(SB_SandBoxCfgVerticle.GET))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets sand box config by id with missing parameters.
     */
    @Test
    public void getSandBoxConfigByIdWithMissingParameters() {
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .when().get(getURL(SB_SandBoxCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets sand box config by id with wrong parameters.
     */
    @Test
    public void getSandBoxConfigByIdWithWrongParameters() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxCfgVerticle.PARAM_ID, "bla")
                .when().get(getURL(SB_SandBoxCfgVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets sand box config by sand box id.
     */
    @Test
    public void getSandBoxConfigBySandBoxId() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        user.getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
        try {
            mongo.save(user);
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxCfgVerticle.PARAM_SANDBOX_ID, "558b0efebd2e39cdab651e1f")
                .queryParam(SB_SandBoxCfgVerticle.PARAM_SEASON_ID, "558b0ceaf9285df5b7553fc6")
                .when().get(getURL(SB_SandBoxCfgVerticle.GETLIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1));
    }

    /**
     * Gets sand box config by sand box id with non logged user.
     */
    @Test
    public void getSandBoxConfigBySandBoxIdWithNonLoggedUser() {
        given().when().get(getURL(SB_SandBoxCfgVerticle.GETLIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets sand box config by sand box id with wrong http method.
     */
    @Test
    public void getSandBoxConfigBySandBoxIdWithWrongHttpMethod() {
        given().when().post(getURL(SB_SandBoxCfgVerticle.GETLIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets sand box config by sand box id with missing parameters.
     */
    @Test
    public void getSandBoxConfigBySandBoxIdWithMissingParameters() {
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .when().get(getURL(SB_SandBoxCfgVerticle.GETLIST))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets sand box config by sand box id with wrong parameters.
     */
    @Test
    public void getSandBoxConfigBySandBoxIdWithWrongParameters() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxCfgVerticle.PARAM_SANDBOX_ID, "bla")
                .queryParam(SB_SandBoxCfgVerticle.PARAM_SEASON_ID, "558b0ceaf9285df5b7553fc6")
                .when().get(getURL(SB_SandBoxCfgVerticle.GETLIST))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }
}
