/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */
package com.qaobee.hive.test.api.sandbox.config;

import com.qaobee.hive.api.v1.commons.settings.ActivityRoute;
import com.qaobee.hive.api.v1.sandbox.config.SB_SandBoxVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sand box test.
 *
 * @author cke
 */
public class SandBoxTest extends VertxJunitSupport {

    /**
     * Retrieve sand box by his owner.
     */
    @Test
    public void getSandBoxByOwner(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(u -> {
            getActivity("ACT-HAND", u).then(activity -> {
                given().header(TOKEN, u.getAccount().getToken())
                        .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID))
                        .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                        .then().assertThat().statusCode(200)
                        .body("owner", notNullValue())
                        .body("owner", is(u.get_id()));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets sand box by owner with non logged user.
     */
    @Test
    public void getSandBoxByOwnerWithNonLoggedUser() {
        given().when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets sand box by owner with wrong http method.
     */
    @Test
    public void getSandBoxByOwnerWithWrongHttpMethod() {
        given().when().post(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets sand box by owner with missing parameters.
     */
    @Test
    public void getSandBoxByOwnerWithMissingParameters(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets sand box by owner with wrong parameters.
     */
    @Test
    public void getSandBoxByOwnerWithWrongParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        generateLoggedUser().then(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, "bla")
                    .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets sand box by owner with wrong user.
     */
    @Test
    public void getSandBoxByOwnerWithWrongUser(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        generateLoggedUser().then(user -> {
            getActivity("ACT-HAND", user).then(activity -> {
                given().header(TOKEN, user.getAccount().getToken())
                        .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID))
                        .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                        .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                        .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets sand box by owner with wrong activity.
     */
    @Test
    public void getSandBoxByOwnerWithWrongActivity(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(user -> {
            getActivity("ACT-FOOT", user).then(activity -> {
                given().header(TOKEN, user.getAccount().getToken())
                        .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID))
                        .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                        .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                        .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets sand box list by owner.
     */
    @Test
    public void getSandBoxListByOwner(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(user -> {

            given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_SandBoxVerticle.PARAM_OWNER_ID, user.get_id())
                    .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("owner", hasItem(user.get_id()));

            given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_SandBoxVerticle.PARAM_OWNER_ID, "")
                    .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("owner", hasItem(user.get_id()));

            given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_SandBoxVerticle.PARAM_OWNER_ID, "bla")
                    .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

            given().header(TOKEN, user.getAccount().getToken())
                    .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("owner", hasItem(user.get_id()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets sand box list by owner with non logged user.
     */
    @Test
    public void getSandBoxListByOwnerWithNonLoggedUser() {
        given().when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets sand box list by owner with wrong http method.
     */
    @Test
    public void getSandBoxListByOwnerWithWrongHttpMethod() {
        given().when().post(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets sand box list by owner with wrong parameters.
     */
    @Test
    public void getSandBoxListByOwnerWithWrongParameters(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        generateLoggedUser().then(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .queryParam(SB_SandBoxVerticle.PARAM_OWNER_ID, user.get_id())
                    .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Update sand box.
     */
    @Test
    public void updateSandBox(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(user -> {
            getActivity("ACT-HAND", user).then(activity -> {
                JsonObject sb = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                        .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, activity.getString(ActivityRoute.PARAM_ID))
                        .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("owner", notNullValue())
                        .body("owner", is(user.get_id()))
                        .extract().asString());

                sb.put("effectiveDefault", "123456");
                given().header(TOKEN, user.getAccount().getToken())
                        .body(sb.encode())
                        .when().post(getURL(SB_SandBoxVerticle.UPDATE))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("effectiveDefault", is("123456"));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Update sand box with non logged user.
     */
    @Test
    public void updateSandBoxWithNonLoggedUser() {
        given().when().post(getURL(SB_SandBoxVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update sand box with wrong http method.
     */
    @Test
    public void updateSandBoxWithWrongHttpMethod() {
        given().when().get(getURL(SB_SandBoxVerticle.UPDATE))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update sand box with missing params.
     */
    @Test
    public void updateSandBoxWithMissingParams(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .when().post(getURL(SB_SandBoxVerticle.UPDATE))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets sandbox with non logged user.
     */
    @Test
    public void getSandboxByIdWithNonLoggedUser() {
        given().when().get(getURL(SB_SandBoxVerticle.GET_BY_ID))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets sandbox with wrong http method.
     */
    @Test
    public void getSandboxByIWithWrongHttpMethod() {
        given().when().post(getURL(SB_SandBoxVerticle.GET_BY_ID))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets sandbox with missing params.
     */
    @Test
    public void getSandboxByIWithMissingParams(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(user -> {
            given().header(TOKEN, user.getAccount().getToken())
                    .when().get(getURL(SB_SandBoxVerticle.GET_BY_ID))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }
}
