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
package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 * The Class LoginTest.
 *
 * @author cke
 */
public class UserServiceTest extends VertxJunitSupport {

    /**
     * Test Login OK.
     */
    @Test
    public void loginOk(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd());

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Test Login OK with an uppercase login.
     */
    @Test
    public void loginOkWithUppercaseLogin(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin().toUpperCase())
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd());

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Badlogin hTTP method.
     */
    @Test
    public void badloginHTTPMethod() {
        given().when().get(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Login ok with mobile token.
     */
    @Test
    public void loginOkWithMobileToken(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {

            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserVerticle.MOBILE_TOKEN, UUID.randomUUID().toString());

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    @Test
    public void loginOkWithMobileTokenAndPushId(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserVerticle.MOBILE_TOKEN, UUID.randomUUID().toString())
                    .put(UserVerticle.PARAM_PUSH_ID, UUID.randomUUID().toString())
                    .put(UserVerticle.PARAM_OS, "android");

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Test Login with badlogin.
     */
    @Test
    public void loginKo(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {

            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, "badlogin")
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd());

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Bad login
     */
    @Test
    public void badLogin() {
        JsonObject params = new JsonObject();
        params.put(UserVerticle.PARAM_LOGIN, "badlogin");

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));

        JsonObject params2 = new JsonObject();
        params.put(UserVerticle.PARAM_PWD, "toto");
        given().body(params2.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Test Login with bad password.
     */
    @Test
    public void loginPasswordKo(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.PARAM_PWD, "tutu");

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Test login user inactive.
     */
    @Test
    public void loginUserInactive(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            u.result().getAccount().setActive(false);
            mongo.upsert(new JsonObject(Json.encode(u)), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    JsonObject params = new JsonObject()
                            .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd());

                    given().body(params.encodePrettily())
                            .when().post(getURL(UserVerticle.LOGIN))
                            .then().assertThat().statusCode(ExceptionCodes.NON_ACTIVE.getCode())
                            .body("code", is(ExceptionCodes.NON_ACTIVE.toString()));
                    async.complete();
                } else {
                    Assert.fail(id.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token.
     */
    @Test
    public void loginByMobileToken(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserVerticle.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            JsonObject params2 = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.MOBILE_TOKEN, token);

            given().body(params2.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token trial period.
     */
    @Test
    public void loginByMobileTokenTrialPeriod(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            u.result().getAccount().getListPlan().get(0).setStatus("open");
            mongo.upsert(new JsonObject(Json.encode(u)), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    String token = UUID.randomUUID().toString();

                    JsonObject params = new JsonObject()
                            .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                            .put(UserVerticle.MOBILE_TOKEN, token);

                    given().body(params.encodePrettily())
                            .when().post(getURL(UserVerticle.LOGIN))
                            .then().assertThat().statusCode(200)
                            .body("name", is(u.result().getName()));

                    JsonObject params2 = new JsonObject()
                            .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserVerticle.MOBILE_TOKEN, token);

                    given().body(params2.encodePrettily())
                            .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                            .then().assertThat().statusCode(200)
                            .body("name", is(u.result().getName()));
                    async.complete();
                } else {
                    Assert.fail(id.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token wrong hTTP method.
     */
    @Test
    public void loginByMobileTokenWrongHTTPMethod(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserVerticle.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            given().when().get(getURL(UserVerticle.LOGIN_BY_TOKEN))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token wrong login.
     */
    @Test
    public void loginByMobileTokenWrongLogin(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserVerticle.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            JsonObject params2 = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, "badLogin")
                    .put(UserVerticle.MOBILE_TOKEN, token);

            given().body(params2.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token not paid.
     */
    @Test
    public void loginByMobileTokenNotPaid(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            u.result().getAccount().getListPlan().get(0).setStatus("notpaid");
            mongo.upsert(new JsonObject(Json.encode(u)), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    String token = UUID.randomUUID().toString();
                    JsonObject params = new JsonObject()
                            .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                            .put(UserVerticle.MOBILE_TOKEN, token);

                    given().body(params.encodePrettily())
                            .when().post(getURL(UserVerticle.LOGIN))
                            .then().assertThat().statusCode(200)
                            .body("name", is(u.result().getName()));

                    JsonObject params2 = new JsonObject()
                            .put(UserVerticle.PARAM_LOGIN, "badLogin")
                            .put(UserVerticle.MOBILE_TOKEN, token);

                    given().body(params2.encodePrettily())
                            .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                            .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                            .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
                    async.complete();
                } else {
                    Assert.fail(id.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token trial period ended.
     */
    @Test
    public void loginByMobileTokenTrialPeriodEnded(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            u.result().getAccount().getListPlan().get(0).setStatus("open");
            u.result().getAccount().getListPlan().get(0).setEndPeriodDate(0);
            mongo.upsert(new JsonObject(Json.encode(u)), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    String token = UUID.randomUUID().toString();
                    JsonObject params = new JsonObject()
                            .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                            .put(UserVerticle.MOBILE_TOKEN, token);

                    given().body(params.encodePrettily())
                            .when().post(getURL(UserVerticle.LOGIN))
                            .then().assertThat().statusCode(200)
                            .body("name", is(u.result().getName()));

                    JsonObject params2 = new JsonObject()
                            .put(UserVerticle.PARAM_LOGIN, "badLogin")
                            .put(UserVerticle.MOBILE_TOKEN, token);

                    given().body(params2.encodePrettily())
                            .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                            .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                            .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
                    async.complete();
                } else {
                    Assert.fail(id.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token wrong token.
     */
    @Test
    public void loginByMobileTokenWrongToken(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserVerticle.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            JsonObject params2 = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.MOBILE_TOKEN, "123456");

            given().body(params2.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token no data.
     */
    @Test
    public void loginByMobileTokenNoData(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserVerticle.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserVerticle.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            given().when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Fetch meta information such as current season, activity and structure
     */
    @Test
    public void getMetas(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            user.result().getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
            mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .when().get(getURL(UserVerticle.META))
                            .then().assertThat().statusCode(200)
                            .body("activityId", notNullValue())
                            .body("structure", notNullValue());
                    async.complete();
                } else {
                    Assert.fail(id.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    @Test
    public void getMetasWithSandboxId(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            user.result().getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
            mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .param("sandboxId", "558b0efebd2e39cdab651e1f")
                            .when().get(getURL(UserVerticle.META))
                            .then().assertThat().statusCode(200)
                            .body("activityId", notNullValue())
                            .body("structure", notNullValue());
                    async.complete();
                } else {
                    Assert.fail(id.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets metas with wrong hTTP method.
     */
    @Test
    public void getMetasWithWrongHTTPMethod(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            user.result().getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
            mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .when().post(getURL(UserVerticle.META))
                            .then().assertThat().statusCode(404)
                            .body(STATUS, is(false));
                } else {
                    Assert.fail(id.cause().getMessage());
                }
            });
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets metas with wrong user.
     */
    @Test
    public void getMetasWithWrongUser(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        generateLoggedUser().setHandler(user -> {
            user.result().getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
            mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    given().header(TOKEN, user.result().getAccount().getToken())
                            .when().get(getURL(UserVerticle.META))
                            .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                            .body("code", is(ExceptionCodes.DATA_ERROR.toString()));
                    async.complete();
                } else {
                    Assert.fail(id.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets metas not logged.
     */
    @Test
    public void getMetasNotLogged() {
        given().when().get(getURL(UserVerticle.META))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets metas wrong hTTP method.
     */
    @Test
    public void getMetasWrongHTTPMethod() {
        given().when().post(getURL(UserVerticle.META))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Fetch a user by id
     */
    @Test
    public void getUserById(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .param("id", user.result().get_id())
                    .when().get(getURL(UserVerticle.USER_INFO))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(user.result().get_id()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets user by id not logged.
     */
    @Test
    public void getUserByIdNotLogged() {
        given().when().get(getURL(UserVerticle.META))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets user by id wrong hTTP method.
     */
    @Test
    public void getUserByIdWrongHTTPMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().post(getURL(UserVerticle.META))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Fetch the current logged user
     */
    @Test
    public void getCurrentUser(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().get(getURL(UserVerticle.CURRENT))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(user.result().get_id()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets current user not logged.
     */
    @Test
    public void getCurrentUserNotLogged() {
        given().when().get(getURL(UserVerticle.CURRENT))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets current user wrong hTTP method.
     */
    @Test
    public void getCurrentUserWrongHTTPMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().post(getURL(UserVerticle.CURRENT))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Logout void.
     */
    @Test
    public void logout(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().get(getURL(UserVerticle.LOGOUT))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Logout bad hTTP method.
     */
    @Test
    public void logoutBadHTTPMethod() {
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().post(getURL(UserVerticle.LOGOUT));
        });
    }

    /**
     * Logout failed.
     */
    @Test
    public void logoutFailed() {
        given().when().get(getURL(UserVerticle.LOGOUT))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Password renew.
     */
    @Test
    public void passwordRenew(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserVerticle.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RENEW))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Password renew bad request.
     */
    @Test
    public void passwordRenewBadRequest() {
        given().when().post(getURL(UserVerticle.PASSWD_RENEW))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Password renew bad login.
     */
    @Test
    public void passwordRenewBadLogin() {
        JsonObject query = new JsonObject().put(UserVerticle.PARAM_LOGIN, "toto");
        given().body(query.encodePrettily())
                .when().post(getURL(UserVerticle.PASSWD_RENEW))
                .then().assertThat().statusCode(ExceptionCodes.UNKNOWN_LOGIN.getCode())
                .body("code", is(ExceptionCodes.UNKNOWN_LOGIN.toString()));
    }

    /**
     * Password renew bad hTTP method.
     */
    @Test
    public void passwordRenewBadHTTPMethod() {
        given().when().get(getURL(UserVerticle.PASSWD_RENEW))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Password renew activation code check.
     */
    @Test
    public void passwordRenewActivationCodeCheck(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserVerticle.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RENEW))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            mongo.getById(user.result().get_id(), DBCollections.USER).done(jsonuser -> {
                String code = jsonuser.getJsonObject("account").getString("activationPasswd");
                given().param("id", user.result().get_id()).param("code", code)
                        .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                        .then().assertThat().statusCode(200)
                        .body("status", notNullValue())
                        .body("status", is(true));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        });
        async.await(TIMEOUT);
    }

    /**
     * Password renew wrong activation code check.
     */
    @Test
    public void passwordRenewWrongActivationCodeCheck(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserVerticle.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RENEW))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            given().param("id", user.result().get_id()).param("code", "12345")
                    .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Password renew activation code check bad hTTP method.
     */
    @Test
    public void passwordRenewActivationCodeCheckBadHTTPMethod(TestContext context) {
        Async async = context.async();
        // First step ask for a new code
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserVerticle.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RENEW))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));

            given().when().post(getURL(UserVerticle.PASSWD_RENEW_CHK))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Password reset.
     */
    @Test
    public void passwordReset(TestContext context) {
        Async async = context.async();
        // First step ask for a new code
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserVerticle.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RENEW))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            mongo.getById(user.result().get_id(), DBCollections.USER).done(jsonuser -> {
                String code = jsonuser.getJsonObject("account").getString("activationPasswd");
                given().param("id", user.result().get_id()).param("code", code)
                        .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                        .then().assertThat().statusCode(200)
                        .body("status", notNullValue())
                        .body("status", is(true));

                JsonObject query2 = new JsonObject()
                        .put("id", user.result().get_id())
                        .put("code", code)
                        .put("passwd", "newPassword");

                given().body(query2.encodePrettily())
                        .when().post(getURL(UserVerticle.PASSWD_RESET))
                        .then().assertThat().statusCode(200)
                        .body("status", notNullValue())
                        .body("status", is(true));
                // Finaly testBodyParams login
                JsonObject params = new JsonObject()
                        .put(UserVerticle.PARAM_LOGIN, user.result().getAccount().getLogin())
                        .put(UserVerticle.PARAM_PWD, "newPassword");
                given().body(params.encodePrettily())
                        .when().post(getURL(UserVerticle.LOGIN))
                        .then().assertThat().statusCode(200)
                        .body("name", notNullValue())
                        .body("name", is(user.result().getName()));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        });
        async.await(TIMEOUT);
    }

    /**
     * Password reset wrong code.
     */
    @Test
    public void passwordResetWrongCode(TestContext context) {
        Async async = context.async();
        // First step ask for a new code
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserVerticle.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RENEW))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            mongo.getById(user.result().get_id(), DBCollections.USER).done(jsonuser -> {
                String code = jsonuser.getJsonObject("account").getString("activationPasswd");
                given().param("id", user.result().get_id()).param("code", code)
                        .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                        .then().assertThat().statusCode(200)
                        .body("status", notNullValue())
                        .body("status", is(true));

                JsonObject query2 = new JsonObject()
                        .put("id", user.result().get_id())
                        .put("code", "123456")
                        .put("passwd", "newPassword");

                given().body(query2.encodePrettily())
                        .when().post(getURL(UserVerticle.PASSWD_RESET))
                        .then().assertThat().statusCode(200)
                        .body("status", notNullValue())
                        .body("status", is(false));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        });
        async.await(TIMEOUT);
    }

    /**
     * Password reset wrong hTTP method.
     */
    @Test
    public void passwordResetWrongHTTPMethod() {
        given().when().get(getURL(UserVerticle.PASSWD_RESET))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Upload avatar
     */
    @Test
    public void uploadAvatar(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            String avatarId = given()
                    .header(TOKEN, user.result().getAccount().getToken())
                    .multiPart(new File("src/test/resources/avatar.jpg")).
                            pathParam("uid", user.result().get_id()).
                            when().
                            post(BASE_URL + "/file/" + DBCollections.USER + "/avatar/{uid}")
                    .then().assertThat().statusCode(200)
                    .body("avatar", notNullValue())
                    .extract().path("avatar");
            LOG.info(avatarId);

            byte[] byteArray = given()
                    .pathParam("avatar", avatarId)
                    .get(BASE_URL + "/file/" + DBCollections.USER + "/{avatar}")
                    .then().assertThat().statusCode(200)
                    .extract().asByteArray();

            Assert.assertEquals("Files must have same size",
                    new File("src/test/resources/avatar.jpg").length(), byteArray.length);
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Upload avatar with wrong user id
     */
    @Test
    public void uploadAvatarWithWrongUserId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .multiPart(new File("src/test/resources/avatar.jpg")).
                    pathParam("uid", "blabla").
                    when().
                    post(BASE_URL + "/file/" + DBCollections.USER + "/avatar/{uid}")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode());
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Upload avatar with not logged user
     */
    @Test
    public void uploadAvatarWithNotLoggedUser(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(user -> {
            given().multiPart(new File("src/test/resources/avatar.jpg"))
                    .pathParam("uid", user.result().get_id())
                    .when()
                    .post(BASE_URL + "/file/" + DBCollections.USER + "/avatar/{uid}")
                    .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode());
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Upload avatar with wrong token.
     */
    @Test
    public void uploadAvatarWithWrongToken(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().multiPart(new File("src/test/resources/avatar.jpg")).
                    pathParam("uid", user.result().get_id())
                    .header(TOKEN, "11111")
                    .when()
                    .post(BASE_URL + "/file/" + DBCollections.USER + "/avatar/{uid}")
                    .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode());
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Get avatar with wrong avatar id
     */
    @Test
    public void getAvatarWithWrongAvatarId() {
        given()
                .pathParam("avatar", "blabla")
                .get(BASE_URL + "/file/" + DBCollections.USER + "/{avatar}")
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode());
    }

    /**
     * Gets avatar with wrong collection.
     */
    @Test
    public void getAvatarWithWrongCollection() {
        given()
                .pathParam("avatar", "bla")
                .get(BASE_URL + "/file/toto/{avatar}")
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode());
    }

    /**
     * Gets user by login.
     */
    @Test
    public void getUserByLogin(TestContext context) {
        Async async = context.async();
        generateLoggedAdminUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .when().get(getURL(UserVerticle.USER_BY_LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", notNullValue())
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets user by login bad http method.
     */
    @Test
    public void getUserByLoginBadHTTPMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().post(getURL(UserVerticle.USER_BY_LOGIN));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets user by login winth not logged user.
     */
    @Test
    public void getUserByLoginWinthNotLoggedUser() {
        given().when().get(getURL(UserVerticle.USER_BY_LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets user by login winth not admin user.
     */
    @Test
    public void getUserByLoginWinthNotAdminUser(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().get(getURL(UserVerticle.USER_BY_LOGIN))
                    .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                    .body("code", is(ExceptionCodes.NOT_ADMIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets user by login with wrong data.
     */
    @Test
    public void getUserByLoginWithWrongData(TestContext context) {
        Async async = context.async();
        generateLoggedAdminUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(UserVerticle.PARAM_LOGIN, "blabla")
                    .when().get(getURL(UserVerticle.USER_BY_LOGIN))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body("code", is(ExceptionCodes.DATA_ERROR.toString()));

            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().get(getURL(UserVerticle.USER_BY_LOGIN))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }
}
