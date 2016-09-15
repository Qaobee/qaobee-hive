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
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import java.io.File;
import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 * The Class LoginTest.
 *
 * @author cke
 */
public class UserTest extends VertxJunitSupport {

    /**
     * Test Login OK.
     */
    @Test
    public void loginOk() {
        User u = generateUser();
        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));
    }

    /**
     * Test Login OK with an uppercase login.
     */
    @Test
    public void loginOkWithUppercaseLogin() {
        User u = generateUser();
        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin().toUpperCase())
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));
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
    public void loginOkWithMobileToken() {
        User u = generateUser();

        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                .putString(UserVerticle.MOBILE_TOKEN, UUID.randomUUID().toString());

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));
    }

    @Test
    public void loginOkWithMobileTokenAndPushId() {
        User u = generateUser();

        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                .putString(UserVerticle.MOBILE_TOKEN, UUID.randomUUID().toString())
                .putString(UserVerticle.PARAM_PUSH_ID, UUID.randomUUID().toString())
                .putString(UserVerticle.PARAM_OS, "android");

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));
    }

    /**
     * Test Login with badlogin.
     */
    @Test
    public void loginKo() {
        User u = generateUser();

        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, "badlogin")
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Bad login
     */
    @Test
    public void badLogin() {
        JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, "badlogin");

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));

        JsonObject params2 = new JsonObject();
        params.putString(UserVerticle.PARAM_PWD, "toto");
        given().body(params2.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Test Login with bad password.
     */
    @Test
    public void loginPasswordKo() {
        User u = generateUser();

        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.PARAM_PWD, "tutu");

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Test login user inactive.
     */
    @Test
    public void loginUserInactive() {
        try {
            User u = generateUser();
            u.getAccount().setActive(false);
            mongo.save(u);
            JsonObject params = new JsonObject()
                    .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                    .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(ExceptionCodes.NON_ACTIVE.getCode())
                    .body("code", is(ExceptionCodes.NON_ACTIVE.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Login by mobile token.
     */
    @Test
    public void loginByMobileToken() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();

        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                .putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));

        JsonObject params2 = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params2.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));
    }

    /**
     * Login by mobile token trial period.
     */
    @Test
    public void loginByMobileTokenTrialPeriod() {
        try {
            User u = generateUser();
            u.getAccount().getListPlan().get(0).setStatus("open");
            mongo.save(u);
            String token = UUID.randomUUID().toString();

            JsonObject params = new JsonObject()
                    .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                    .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                    .putString(UserVerticle.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.getName()));

            JsonObject params2 = new JsonObject()
                    .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                    .putString(UserVerticle.MOBILE_TOKEN, token);

            given().body(params2.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.getName()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Login by mobile token wrong hTTP method.
     */
    @Test
    public void loginByMobileTokenWrongHTTPMethod() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();

        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                .putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));

        given().when().get(getURL(UserVerticle.LOGIN_BY_TOKEN))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Login by mobile token wrong login.
     */
    @Test
    public void loginByMobileTokenWrongLogin() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();

        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                .putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));

        JsonObject params2 = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, "badLogin")
                .putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params2.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Login by mobile token not paid.
     */
    @Test
    public void loginByMobileTokenNotPaid() {
        try {
            User u = generateUser();
            u.getAccount().getListPlan().get(0).setStatus("notpaid");
            mongo.save(u);
            String token = UUID.randomUUID().toString();
            JsonObject params = new JsonObject()
                    .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                    .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                    .putString(UserVerticle.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.getName()));

            JsonObject params2 = new JsonObject()
                    .putString(UserVerticle.PARAM_LOGIN, "badLogin")
                    .putString(UserVerticle.MOBILE_TOKEN, token);

            given().body(params2.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Login by mobile token trial period ended.
     */
    @Test
    public void loginByMobileTokenTrialPeriodEnded() {
        try {
            User u = generateUser();
            u.getAccount().getListPlan().get(0).setStatus("open");
            u.getAccount().getListPlan().get(0).setEndPeriodDate(0);
            mongo.save(u);
            String token = UUID.randomUUID().toString();
            JsonObject params = new JsonObject()
                    .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                    .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                    .putString(UserVerticle.MOBILE_TOKEN, token);

            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.getName()));

            JsonObject params2 = new JsonObject()
                    .putString(UserVerticle.PARAM_LOGIN, "badLogin")
                    .putString(UserVerticle.MOBILE_TOKEN, token);

            given().body(params2.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Login by mobile token wrong token.
     */
    @Test
    public void loginByMobileTokenWrongToken() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();

        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                .putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));

        JsonObject params2 = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.MOBILE_TOKEN, "123456");

        given().body(params2.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Login by mobile token no data.
     */
    @Test
    public void loginByMobileTokenNoData() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();

        JsonObject params = new JsonObject()
                .putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd())
                .putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));

        given().when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Fetch meta information such as current season, activity and structure
     */
    @Test
    public void getMetas() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        user.getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
        try {
            mongo.save(user);
            given().header(TOKEN, user.getAccount().getToken())
                    .when().get(getURL(UserVerticle.META))
                    .then().assertThat().statusCode(200)
                    .body("activityId", notNullValue())
                    .body("structure", notNullValue());
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }
    @Test
    public void getMetasWithSandboxId() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        user.getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
        try {
            mongo.save(user);
            given().header(TOKEN, user.getAccount().getToken())
                    .param("sandboxId", "558b0efebd2e39cdab651e1f")
                    .when().get(getURL(UserVerticle.META))
                    .then().assertThat().statusCode(200)
                    .body("activityId", notNullValue())
                    .body("structure", notNullValue());
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Gets metas with wrong hTTP method.
     */
    @Test
    public void getMetasWithWrongHTTPMethod() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        user.getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
        try {
            mongo.save(user);
            given().header(TOKEN, user.getAccount().getToken())
                    .when().post(getURL(UserVerticle.META))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Gets metas with wrong user.
     */
    @Test
    public void getMetasWithWrongUser() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        User user = generateLoggedUser();
        user.getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
        try {
            mongo.save(user);
            given().header(TOKEN, user.getAccount().getToken())
                    .when().get(getURL(UserVerticle.META))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body("code", is(ExceptionCodes.DATA_ERROR.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
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
    public void getUserById() {
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .param("id", user.get_id())
                .when().get(getURL(UserVerticle.USER_INFO))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(user.get_id()));
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
    public void getUserByIdWrongHTTPMethod() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().post(getURL(UserVerticle.META))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Fetch the current logged user
     */
    @Test
    public void getCurrentUser() {
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .when().get(getURL(UserVerticle.CURRENT))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(user.get_id()));
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
    public void getCurrentUserWrongHTTPMethod() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().post(getURL(UserVerticle.CURRENT))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Logout void.
     */
    @Test
    public void logout() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().get(getURL(UserVerticle.LOGOUT))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
    }

    /**
     * Logout bad hTTP method.
     */
    @Test
    public void logoutBadHTTPMethod() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().post(getURL(UserVerticle.LOGOUT));
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
    public void passwordRenew() {
        JsonObject query = new JsonObject().putString(UserVerticle.PARAM_LOGIN, generateUser().getAccount().getLogin());
        given().body(query.encodePrettily())
                .when().post(getURL(UserVerticle.PASSWD_RENEW))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
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
        JsonObject query = new JsonObject().putString(UserVerticle.PARAM_LOGIN, "toto");
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
    public void passwordRenewActivationCodeCheck() {
        try {
            // First step ask for a new code
            User user = generateUser();
            JsonObject query = new JsonObject().putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RENEW))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            JsonObject jsonuser = mongo.getById(user.get_id(), DBCollections.USER);
            String code = jsonuser.getObject("account").getString("activationPasswd");

            given().param("id", user.get_id()).param("code", code)
                    .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Password renew wrong activation code check.
     */
    @Test
    public void passwordRenewWrongActivationCodeCheck() {
        // First step ask for a new code
        User user = generateUser();
        JsonObject query = new JsonObject().putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
        given().body(query.encodePrettily())
                .when().post(getURL(UserVerticle.PASSWD_RENEW))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
        // fetch the code
        given().param("id", user.get_id()).param("code", "12345")
                .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(false));
    }

    /**
     * Password renew activation code check bad hTTP method.
     */
    @Test
    public void passwordRenewActivationCodeCheckBadHTTPMethod() {
        // First step ask for a new code
        User user = generateUser();
        JsonObject query = new JsonObject().putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
        given().body(query.encodePrettily())
                .when().post(getURL(UserVerticle.PASSWD_RENEW))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));

        given().when().post(getURL(UserVerticle.PASSWD_RENEW_CHK))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Password reset.
     */
    @Test
    public void passwordReset() {
        try {
            // First step ask for a new code
            User user = generateUser();
            JsonObject query = new JsonObject().putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RENEW))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            JsonObject jsonuser = mongo.getById(user.get_id(), DBCollections.USER);
            String code = jsonuser.getObject("account").getString("activationPasswd");
            given().param("id", user.get_id()).param("code", code)
                    .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));

            JsonObject query2 = new JsonObject()
                    .putString("id", user.get_id())
                    .putString("code", code)
                    .putString("passwd", "newPassword");

            given().body(query2.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RESET))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // Finaly test login
            JsonObject params = new JsonObject()
                    .putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin())
                    .putString(UserVerticle.PARAM_PWD, "newPassword");
            given().body(params.encodePrettily())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", notNullValue())
                    .body("name", is(user.getName()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Password reset wrong code.
     */
    @Test
    public void passwordResetWrongCode() {
        try {
            // First step ask for a new code
            User user = generateUser();
            JsonObject query = new JsonObject().putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
            given().body(query.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RENEW))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // fetch the code
            JsonObject jsonuser = mongo.getById(user.get_id(), DBCollections.USER);
            String code = jsonuser.getObject("account").getString("activationPasswd");
            given().param("id", user.get_id()).param("code", code)
                    .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));

            JsonObject query2 = new JsonObject()
                    .putString("id", user.get_id())
                    .putString("code", "123456")
                    .putString("passwd", "newPassword");

            given().body(query2.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RESET))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
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
    public void uploadAvatar() {
        User user = generateLoggedUser();
        String avatarId = given().header(TOKEN, user.getAccount().getToken())
                .multiPart(new File("src/test/resources/avatar.jpg")).
                        pathParam("uid", user.get_id()).
                        when().
                        post(BASE_URL + "/file/User/avatar/{uid}")
                .then().assertThat().statusCode(200)
                .body("avatar", notNullValue())
                .extract().path("avatar");

        byte[] byteArray = given().pathParam("avatar", avatarId)
                .get(BASE_URL + "/file/User/{avatar}")
                .then().assertThat().statusCode(200)
                .extract().asByteArray();

        Assert.assertEquals("Files must have same size",
                byteArray.length,
                getVertx().fileSystem().propsSync("src/test/resources/avatar.jpg").size());
    }

    /**
     * Upload avatar with wrong user id
     */
    @Test
    public void uploadAvatarWithWrongUserId() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .multiPart(new File("src/test/resources/avatar.jpg")).
                pathParam("uid", "blabla").
                when().
                post(BASE_URL + "/file/User/avatar/{uid}")
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode());
    }

    /**
     * Upload avatar with not logged user
     */
    @Test
    public void uploadAvatarWithNotLoggedUser() {
        given().multiPart(new File("src/test/resources/avatar.jpg"))
                .pathParam("uid", generateUser().get_id())
                .when()
                .post(BASE_URL + "/file/User/avatar/{uid}")
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode());
    }

    /**
     * Upload avatar with wrong token.
     */
    @Test
    public void uploadAvatarWithWrongToken() {
        given().multiPart(new File("src/test/resources/avatar.jpg")).
                pathParam("uid", generateUser().get_id())
                .header(TOKEN, "11111")
                .when()
                .post(BASE_URL + "/file/User/avatar/{uid}")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode());
    }

    /**
     * Get avatar with wrong avatar id
     */
    @Test
    public void getAvatarWithWrongAvatarId() {
        given().pathParam("avatar", "blabla")
                .get(BASE_URL + "/file/User/{avatar}")
                .then().assertThat().statusCode(404);
    }

    /**
     * Gets avatar with wrong collection.
     */
    @Test
    public void getAvatarWithWrongCollection() {
       given().pathParam("avatar", "bla")
               .get(BASE_URL + "/file/toto/{avatar}")
                .then().assertThat().statusCode(404);
    }

    /**
     * Gets user by login.
     */
    @Test
    public void getUserByLogin() {
        User u = generateLoggedAdminUser();
        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin())
                .when().get(getURL(UserVerticle.USER_BY_LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", notNullValue())
                .body("name", is(u.getName()));
    }

    /**
     * Gets user by login bad http method.
     */
    @Test
    public void getUserByLoginBadHTTPMethod() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().post(getURL(UserVerticle.USER_BY_LOGIN));
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
    public void getUserByLoginWinthNotAdminUser() {
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
                .when().get(getURL(UserVerticle.USER_BY_LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.NOT_ADMIN.getCode())
                .body("code", is(ExceptionCodes.NOT_ADMIN.toString()));
    }

    /**
     * Gets user by login with wrong data.
     */
    @Test
    public void getUserByLoginWithWrongData() {
        User u = generateLoggedAdminUser();
        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(UserVerticle.PARAM_LOGIN, "blabla")
                .when().get(getURL(UserVerticle.USER_BY_LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body("code", is(ExceptionCodes.DATA_ERROR.toString()));

        given().header(TOKEN, u.getAccount().getToken())
                .when().get(getURL(UserVerticle.USER_BY_LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }
}
