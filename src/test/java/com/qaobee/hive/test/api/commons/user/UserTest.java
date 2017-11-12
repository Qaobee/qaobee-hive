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

import com.qaobee.hive.api.v1.commons.users.UserRoute;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.apache.commons.codec.binary.Base64;
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
public class UserTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/users/user");

    /**
     * Test Login OK.
     *
     * @param context the context
     */
    @Test
    public void loginOk(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd());

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Test Login OK with an uppercase login.
     *
     * @param context the context
     */
    @Test
    public void loginOkWithUppercaseLogin(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin().toUpperCase())
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd());

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
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
        given().when().get(BASE_URL + "/login")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Login ok with mobile token.
     *
     * @param context the context
     */
    @Test
    public void loginOkWithMobileToken(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {

            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserRoute.MOBILE_TOKEN, UUID.randomUUID().toString());

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Login ok with mobile token and push id.
     *
     * @param context the context
     */
    @Test
    public void loginOkWithMobileTokenAndPushId(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserRoute.MOBILE_TOKEN, UUID.randomUUID().toString())
                    .put(UserRoute.PARAM_PUSH_ID, UUID.randomUUID().toString())
                    .put(UserRoute.PARAM_OS, "android");

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Test Login with badlogin.
     *
     * @param context the context
     */
    @Test
    public void loginKo(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {

            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, "badlogin")
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd());

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
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
        JsonObject params = new JsonObject()
                .put(UserRoute.PARAM_LOGIN, "badlogin");

        given().body(params.encodePrettily())
                .when().post(BASE_URL + "/login")
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));

        JsonObject params2 = new JsonObject();
        params.put(UserRoute.PARAM_PWD, "toto");
        given().body(params2.encodePrettily())
                .when().post(BASE_URL + "/login")
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Test Login with bad password.
     *
     * @param context the context
     */
    @Test
    public void loginPasswordKo(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.PARAM_PWD, "tutu");

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Test login user inactive.
     *
     * @param context the context
     */
    @Test
    public void loginUserInactive(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            u.result().getAccount().setActive(false);
            mongo.upsert(new JsonObject(Json.encode(u.result())), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    JsonObject params = new JsonObject()
                            .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd());

                    given().body(params.encodePrettily())
                            .when().post(BASE_URL + "/login")
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
     *
     * @param context the context
     */
    @Test
    public void loginByMobileToken(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserRoute.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            JsonObject params2 = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.MOBILE_TOKEN, token);

            given().body(params2.encodePrettily())
                    .when().post(BASE_URL + "/sso")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token trial period.
     *
     * @param context the context
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
                            .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                            .put(UserRoute.MOBILE_TOKEN, token);

                    given().body(params.encodePrettily())
                            .when().post(BASE_URL + "/login")
                            .then().assertThat().statusCode(200)
                            .body("name", is(u.result().getName()));

                    JsonObject params2 = new JsonObject()
                            .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserRoute.MOBILE_TOKEN, token);

                    given().body(params2.encodePrettily())
                            .when().post(BASE_URL + "/sso")
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
     *
     * @param context the context
     */
    @Test
    public void loginByMobileTokenWrongHTTPMethod(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserRoute.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            given().when().get(BASE_URL + "/sso")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token wrong login.
     *
     * @param context the context
     */
    @Test
    public void loginByMobileTokenWrongLogin(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserRoute.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            JsonObject params2 = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, "badLogin")
                    .put(UserRoute.MOBILE_TOKEN, token);

            given().body(params2.encodePrettily())
                    .when().post(BASE_URL + "/sso")
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token not paid.
     *
     * @param context the context
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
                            .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                            .put(UserRoute.MOBILE_TOKEN, token);

                    given().body(params.encodePrettily())
                            .when().post(BASE_URL + "/login")
                            .then().assertThat().statusCode(200)
                            .body("name", is(u.result().getName()));

                    JsonObject params2 = new JsonObject()
                            .put(UserRoute.PARAM_LOGIN, "badLogin")
                            .put(UserRoute.MOBILE_TOKEN, token);

                    given().body(params2.encodePrettily())
                            .when().post(BASE_URL + "/sso")
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
     *
     * @param context the context
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
                            .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                            .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                            .put(UserRoute.MOBILE_TOKEN, token);

                    given().body(params.encodePrettily())
                            .when().post(BASE_URL + "/login")
                            .then().assertThat().statusCode(200)
                            .body("name", is(u.result().getName()));

                    JsonObject params2 = new JsonObject()
                            .put(UserRoute.PARAM_LOGIN, "badLogin")
                            .put(UserRoute.MOBILE_TOKEN, token);

                    given().body(params2.encodePrettily())
                            .when().post(BASE_URL + "/sso")
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
     *
     * @param context the context
     */
    @Test
    public void loginByMobileTokenWrongToken(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserRoute.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            JsonObject params2 = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.MOBILE_TOKEN, "123456");

            given().body(params2.encodePrettily())
                    .when().post(BASE_URL + "/sso")
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Login by mobile token no data.
     *
     * @param context the context
     */
    @Test
    public void loginByMobileTokenNoData(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .put(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .put(UserRoute.PARAM_PWD, u.result().getAccount().getPasswd())
                    .put(UserRoute.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(BASE_URL + "/login")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));

            given().when().post(BASE_URL + "/sso")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Fetch meta information such as current season, activity and structure
     *
     * @param context the context
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
                            .when().get(BASE_URL + "/meta")
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
     * Gets metas with sandbox id.
     *
     * @param context the context
     */
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
                            .when().get(BASE_URL + "/meta")
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
     *
     * @param context the context
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
                            .when().post(BASE_URL + "/meta")
                            .then().assertThat().statusCode(404)
                            .body(STATUS, is(false));
                } else {
                    Assert.fail(id.cause().getMessage());
                }
                async.complete();
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets metas with wrong user.
     *
     * @param context the context
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
                            .when().get(BASE_URL + "/meta")
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
        given().when().get(BASE_URL + "/meta")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets metas wrong hTTP method.
     */
    @Test
    public void getMetasWrongHTTPMethod() {
        given().when().post(BASE_URL + "/meta")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Fetch a user by id
     *
     * @param context the context
     */
    @Test
    public void getUserById(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .param("id", user.result().get_id())
                    .when().get(BASE_URL + "/")
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
        given().when().get(BASE_URL + "/meta")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets user by id wrong hTTP method.
     *
     * @param context the context
     */
    @Test
    public void getUserByIdWrongHTTPMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().post(BASE_URL + "/meta")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Fetch the current logged user
     *
     * @param context the context
     */
    @Test
    public void getCurrentUser(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().get(BASE_URL + "/current")
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
        given().when().get(BASE_URL + "/current")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets current user wrong hTTP method.
     *
     * @param context the context
     */
    @Test
    public void getCurrentUserWrongHTTPMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().post(BASE_URL + "/current")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Logout void.
     *
     * @param context the context
     */
    @Test
    public void logout(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().get(BASE_URL + "/logout")
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
        generateLoggedUser().setHandler(user -> given().header(TOKEN, user.result().getAccount().getToken())
                .when().post(BASE_URL + "/logout"));
    }

    /**
     * Logout failed.
     */
    @Test
    public void logoutFailed() {
        given().when().get(BASE_URL + "/logout")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Password renew.
     *
     * @param context the context
     */
    @Test
    public void passwordRenew(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserRoute.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(BASE_URL + "/newpasswd")
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
        given().when().post(BASE_URL + "/newpasswd")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Password renew bad login.
     */
    @Test
    public void passwordRenewBadLogin() {
        JsonObject query = new JsonObject().put(UserRoute.PARAM_LOGIN, "toto");
        given().body(query.encodePrettily())
                .when().post(BASE_URL + "/newpasswd")
                .then().assertThat().statusCode(ExceptionCodes.UNKNOWN_LOGIN.getCode())
                .body("code", is(ExceptionCodes.UNKNOWN_LOGIN.toString()));
    }

    /**
     * Password renew bad hTTP method.
     */
    @Test
    public void passwordRenewBadHTTPMethod() {
        given().when().get(BASE_URL + "/newpasswd")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Password renew activation code check.
     *
     * @param context the context
     */
    @Test
    public void passwordRenewActivationCodeCheck(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserRoute.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(BASE_URL + "/newpasswd")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            mongo.getById(user.result().get_id(), DBCollections.USER, jsonuser -> {
                if (jsonuser.succeeded()) {
                    String code = jsonuser.result().getJsonObject("account").getString("activationPasswd");
                    given().param("id", user.result().get_id()).param("code", code)
                            .when().get(BASE_URL + "/passwdcheck")
                            .then().assertThat().statusCode(200)
                            .body("status", notNullValue())
                            .body("status", is(true));
                    async.complete();
                } else {
                    Assert.fail(jsonuser.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Password renew wrong activation code check.
     *
     * @param context the context
     */
    @Test
    public void passwordRenewWrongActivationCodeCheck(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserRoute.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(BASE_URL + "/newpasswd")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            given().param("id", user.result().get_id()).param("code", "12345")
                    .when().get(BASE_URL + "/passwdcheck")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Password renew activation code check bad hTTP method.
     *
     * @param context the context
     */
    @Test
    public void passwordRenewActivationCodeCheckBadHTTPMethod(TestContext context) {
        Async async = context.async();
        // First step ask for a new code
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserRoute.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(BASE_URL + "/newpasswd")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));

            given().when().post(BASE_URL + "/passwdcheck")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Password reset.
     *
     * @param context the context
     */
    @Test
    public void passwordReset(TestContext context) {
        Async async = context.async();
        // First step ask for a new code
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserRoute.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(BASE_URL + "/newpasswd")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            mongo.getById(user.result().get_id(), DBCollections.USER, jsonuser -> {
                if (jsonuser.succeeded()) {
                    String code = jsonuser.result().getJsonObject("account").getString("activationPasswd");
                    given().param("id", user.result().get_id()).param("code", code)
                            .when().get(BASE_URL + "/passwdcheck")
                            .then().assertThat().statusCode(200)
                            .body("status", notNullValue())
                            .body("status", is(true));

                    JsonObject query2 = new JsonObject()
                            .put("id", user.result().get_id())
                            .put("code", code)
                            .put("passwd", "newPassword");

                    given().body(query2.encodePrettily())
                            .when().post(BASE_URL + "/resetPasswd")
                            .then().assertThat().statusCode(200)
                            .body("status", notNullValue())
                            .body("status", is(true));
                    // Finaly testBodyParams login
                    JsonObject params = new JsonObject()
                            .put(UserRoute.PARAM_LOGIN, user.result().getAccount().getLogin())
                            .put(UserRoute.PARAM_PWD, "newPassword");
                    given().body(params.encodePrettily())
                            .when().post(BASE_URL + "/login")
                            .then().assertThat().statusCode(200)
                            .body("name", notNullValue())
                            .body("name", is(user.result().getName()));
                    async.complete();
                } else {
                    Assert.fail(jsonuser.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Password reset bypassing activation code.
     *
     * @param context the context
     */
    @Test
    public void passwordResetBypassingActivationCode(TestContext context) {
        Async async = context.async();
        // First step ask for a new code
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserRoute.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(BASE_URL + "/newpasswd")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            mongo.getById(user.result().get_id(), DBCollections.USER, jsonuser -> {
                if (jsonuser.succeeded()) {
                    String code = jsonuser.result().getJsonObject("account").getString("activationPasswd");
                    given().param("id", user.result().get_id()).param("code", code)
                            .when().get(BASE_URL + "/passwdcheck")
                            .then().assertThat().statusCode(200)
                            .body("status", notNullValue())
                            .body("status", is(true));

                    JsonObject query2 = new JsonObject()
                            .put("id", user.result().get_id())
                            .put("code", code)
                            .put("byPassActivationCode", true)
                            .put("passwd", "newPassword");

                    given().body(query2.encodePrettily())
                            .when().post(BASE_URL + "/resetPasswd")
                            .then().assertThat().statusCode(200)
                            .body("status", notNullValue())
                            .body("status", is(true));
                    // Finaly testBodyParams login
                    JsonObject params = new JsonObject()
                            .put(UserRoute.PARAM_LOGIN, user.result().getAccount().getLogin())
                            .put(UserRoute.PARAM_PWD, "newPassword");
                    given().body(params.encodePrettily())
                            .when().post(BASE_URL + "/login")
                            .then().assertThat().statusCode(200)
                            .body("name", notNullValue())
                            .body("name", is(user.result().getName()));
                    async.complete();
                } else {
                    Assert.fail(jsonuser.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Password reset wrong code.
     *
     * @param context the context
     */
    @Test
    public void passwordResetWrongCode(TestContext context) {
        Async async = context.async();
        // First step ask for a new code
        generateUser().setHandler(user -> {
            JsonObject query = new JsonObject().put(UserRoute.PARAM_LOGIN, user.result().getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(BASE_URL + "/newpasswd")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            mongo.getById(user.result().get_id(), DBCollections.USER, jsonuser -> {
                if (jsonuser.succeeded()) {
                    String code = jsonuser.result().getJsonObject("account").getString("activationPasswd");
                    given().param("id", user.result().get_id()).param("code", code)
                            .when().get(BASE_URL + "/passwdcheck")
                            .then().assertThat().statusCode(200)
                            .body("status", notNullValue())
                            .body("status", is(true));

                    JsonObject query2 = new JsonObject()
                            .put("id", user.result().get_id())
                            .put("code", "123456")
                            .put("passwd", "newPassword");

                    given().body(query2.encodePrettily())
                            .when().post(BASE_URL + "/resetPasswd")
                            .then().assertThat().statusCode(200)
                            .body("status", notNullValue())
                            .body("status", is(false));
                    async.complete();
                } else {
                    Assert.fail(jsonuser.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Password reset wrong hTTP method.
     */
    @Test
    public void passwordResetWrongHTTPMethod() {
        given().when().get(BASE_URL + "/resetPasswd")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Upload avatar
     *
     * @param context the context
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
                            post(getRootURL() + "/file/" + DBCollections.USER + "/avatar/{uid}")
                    .then().assertThat().statusCode(200)
                    .body("avatar", notNullValue())
                    .extract().path("avatar");
            LOG.info(avatarId);

            byte[] byteArray = given()
                    .pathParam("avatar", avatarId)
                    .get(getRootURL() + "/file/" + DBCollections.USER + "/{avatar}")
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
     *
     * @param context the context
     */
    @Test
    public void uploadAvatarWithWrongUserId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .multiPart(new File("src/test/resources/avatar.jpg")).
                    pathParam("uid", "blabla").
                    when().
                    post(getRootURL() + "/file/" + DBCollections.USER + "/avatar/{uid}")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode());
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Upload avatar with not logged user
     *
     * @param context the context
     */
    @Test
    public void uploadAvatarWithNotLoggedUser(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(user -> {
            given().multiPart(new File("src/test/resources/avatar.jpg"))
                    .pathParam("uid", user.result().get_id())
                    .when()
                    .post(getRootURL() + "/file/" + DBCollections.USER + "/avatar/{uid}")
                    .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode());
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Upload avatar with wrong token.
     *
     * @param context the context
     */
    @Test
    public void uploadAvatarWithWrongToken(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().multiPart(new File("src/test/resources/avatar.jpg")).
                    pathParam("uid", user.result().get_id())
                    .header(TOKEN, "11111")
                    .when()
                    .post(getRootURL() + "/file/" + DBCollections.USER + "/avatar/{uid}")
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
                .then().assertThat().statusCode(404);
    }

    /**
     * Gets avatar with wrong collection.
     */
    @Test
    public void getAvatarWithWrongCollection() {
        given()
                .pathParam("avatar", "bla")
                .get(BASE_URL + "/file/toto/{avatar}")
                .then().assertThat().statusCode(404);
    }

    /**
     * Gets user by login.
     *
     * @param context the context
     */
    @Test
    public void getUserByLogin(TestContext context) {
        Async async = context.async();
        generateLoggedAdminUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(UserRoute.PARAM_LOGIN, u.result().getAccount().getLogin())
                    .when().get(BASE_URL + "/userByLogin")
                    .then().assertThat().statusCode(200)
                    .body("name", notNullValue())
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets user by login bad http method.
     *
     * @param context the context
     */
    @Test
    public void getUserByLoginBadHTTPMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(user -> {
            given().header(TOKEN, user.result().getAccount().getToken())
                    .when().post(BASE_URL + "/userByLogin");
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets user by login winth not logged user.
     */
    @Test
    public void getUserByLoginWinthNotLoggedUser() {
        given().when().get(BASE_URL + "/userByLogin")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets user by login winth not admin user.
     *
     * @param context the context
     */
    @Test
    public void getUserByLoginWinthNotAdminUser(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(UserRoute.PARAM_LOGIN, "toto")
                    .when().get(BASE_URL + "/userByLogin")
                    .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                    .body("code", is(ExceptionCodes.NOT_ADMIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Gets user by login with wrong data.
     *
     * @param context the context
     */
    @Test
    public void getUserByLoginWithWrongData(TestContext context) {
        Async async = context.async();
        generateLoggedAdminUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .queryParam(UserRoute.PARAM_LOGIN, "blabla")
                    .when().get(BASE_URL + "/userByLogin")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body("code", is(ExceptionCodes.DATA_ERROR.toString()));

            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().get(BASE_URL + "/userByLogin")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Sso test.
     *
     * @param context the context
     */
    @Test
    public void ssoTest(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            String secret = given().header(TOKEN, u.result().getAccount().getToken())
                    .body(new JsonObject().put("path", "/private/sso/area").encode())
                    .when().post(BASE_URL + "/encrypt")
                    .then().assertThat().statusCode(200)
                    .body("secret", notNullValue())
                    .extract().path("secret");
            given().body(new JsonObject().put("_id", u.result().get_id()).put("secret",secret).encode())
                    .when().post(BASE_URL + "/decrypt")
                    .then().assertThat().statusCode(200)
                    .body("token", notNullValue())
                    .body("token", is(u.result().getAccount().getToken()))
                    .body("path", notNullValue())
                    .body("path", is("/private/sso/area"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Sso test missing datas.
     *
     * @param context the context
     */
    @Test
    public void ssoTestMissingDatas(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .body("{}")
                    .when().post(BASE_URL + "/encrypt")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            given().body(new JsonObject().put("secret", "123").encode())
                    .when().post(BASE_URL + "/decrypt")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            given().body(new JsonObject().put("_id", u.result().get_id()).encode())
                    .when().post(BASE_URL + "/decrypt")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Sso test wrong data.
     *
     * @param context the context
     */
    @Test
    public void ssoTestWrongData(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().body(new JsonObject().put("_id", u.result().get_id()).put("secret", "123456").encode())
                    .when().post(BASE_URL + "/decrypt")
                    .then().assertThat().statusCode(ExceptionCodes.INTERNAL_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.INTERNAL_ERROR.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Sso test wrong user.
     *
     * @param context the context
     */
    @Test
    public void ssoTestWrongUser(TestContext context) {
        given().body(new JsonObject().put("_id", "123456").put("secret", Base64.encodeBase64String(new JsonObject()
                .put("token", "123456")
                .put("path", "/private/sso/area")
                .encode().getBytes()
        )).encode())
                .when().post(BASE_URL + "/decrypt")
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }
}
