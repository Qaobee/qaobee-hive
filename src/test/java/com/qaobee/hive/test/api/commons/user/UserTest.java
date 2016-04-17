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
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import java.io.File;
import java.util.GregorianCalendar;
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
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());

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
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin().toUpperCase());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());

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
                .then()
                .assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Login ok with mobile token.
     */
    @Test
    public void loginOkWithMobileToken() {
        User u = generateUser();

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, UUID.randomUUID().toString());

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

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, "badlogin");
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Bad login test.
     */
    @Test
    public void badLoginTest() {
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, "badlogin");

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));

        final JsonObject params2 = new JsonObject();
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

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, "tutu");

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

            final JsonObject params = new JsonObject();
            params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
            params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());

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

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));

        final JsonObject params2 = new JsonObject();
        params2.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params2.putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params2.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));
    }

    /**
     * Login by mobile token wrong hTTP method.
     */
    @Test
    public void loginByMobileTokenWrongHTTPMethod() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));

        given().when().get(getURL(UserVerticle.LOGIN_BY_TOKEN))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Login by mobile token wrong login.
     */
    @Test
    public void loginByMobileTokenWrongLogin() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));

        final JsonObject params2 = new JsonObject();
        params2.putString(UserVerticle.PARAM_LOGIN, "badLogin");
        params2.putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params2.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN_BY_TOKEN))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Login by mobile token wrong token.
     */
    @Test
    public void loginByMobileTokenWrongToken() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);

        given().body(params.encodePrettily())
                .when().post(getURL(UserVerticle.LOGIN))
                .then().assertThat().statusCode(200)
                .body("name", is(u.getName()));

        final JsonObject params2 = new JsonObject();
        params2.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params2.putString(UserVerticle.MOBILE_TOKEN, "123456");

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

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);

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
            GregorianCalendar today = new GregorianCalendar();
            int year = today.get(GregorianCalendar.MONTH) < 5 ? today.get(GregorianCalendar.YEAR) - 1 : today.get(GregorianCalendar.YEAR);
            given().header("token", user.getAccount().getToken())
                    .param(UserVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                    .when().get(getURL(UserVerticle.META))
                    .then().assertThat().statusCode(200)
                    .body("activity", notNullValue())
                    .body("structure", notNullValue())
                    .body("season", notNullValue())
                    .body("season.activityId", is("ACT-HAND"))
                    .body("season.countryId", is("CNTR-250-FR-FRA"))
                    .body("season.code", is("SAI-" + year));
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
            given().header("token", user.getAccount().getToken())
                    .param(UserVerticle.PARAM_COUNTRY_ID, "Vulacain")
                    .when().post(getURL(UserVerticle.META))
                    .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                    .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
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
            given().header("token", user.getAccount().getToken())
                    .param(UserVerticle.PARAM_COUNTRY_ID, "CNTR-250-FR-FRA")
                    .when().get(getURL(UserVerticle.META))
                    .then().assertThat().statusCode(ExceptionCodes.DB_NO_ROW_RETURNED.getCode())
                    .body("code", is(ExceptionCodes.DB_NO_ROW_RETURNED.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Gets metas not logged.
     */
    @Test
    public void getMetasNotLogged() {
        given().param(UserVerticle.PARAM_COUNTRY_ID, "Vulacain")
                .when().get(getURL(UserVerticle.META))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets metas wrong hTTP method.
     */
    @Test
    public void getMetasWrongHTTPMethod() {
        given().when().post(getURL(UserVerticle.META))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Fetch a user by id
     */
    @Test
    public void getUserById() {
        User user = generateLoggedUser();
        given().header("token", user.getAccount().getToken())
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
        given().header("token", generateLoggedUser().getAccount().getToken())
                .when().post(getURL(UserVerticle.META))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Fetch the current logged user
     */
    @Test
    public void getCurrentUser() {
        User user = generateLoggedUser();
        given().header("token", user.getAccount().getToken())
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
        given().header("token", generateLoggedUser().getAccount().getToken())
                .when().post(getURL(UserVerticle.CURRENT))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Logout void.
     */
    @Test
    public void logout() {
        given().header("token", generateLoggedUser().getAccount().getToken())
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
        given().header("token", generateLoggedUser().getAccount().getToken())
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
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Password renew bad hTTP method.
     */
    @Test
    public void passwordRenewBadHTTPMethod() {
        given().when().get(getURL(UserVerticle.PASSWD_RENEW))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
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
            JsonObject jsonuser = mongo.getById(user.get_id(), User.class);
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
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
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
            JsonObject jsonuser = mongo.getById(user.get_id(), User.class);
            String code = jsonuser.getObject("account").getString("activationPasswd");
            given().param("id", user.get_id()).param("code", code)
                    .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));

            JsonObject query2 = new JsonObject();
            query2.putBoolean("junit", true);
            query2.putString("id", user.get_id());
            query2.putString("code", code);
            query2.putString("passwd", "newPassword");

            given().body(query2.encodePrettily())
                    .when().post(getURL(UserVerticle.PASSWD_RESET))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // Finaly test login
            final JsonObject params = new JsonObject();
            params.putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
            params.putString(UserVerticle.PARAM_PWD, "newPassword");
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
            JsonObject jsonuser = mongo.getById(user.get_id(), User.class);
            String code = jsonuser.getObject("account").getString("activationPasswd");
            given().param("id", user.get_id()).param("code", code)
                    .when().get(getURL(UserVerticle.PASSWD_RENEW_CHK))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));

            JsonObject query2 = new JsonObject();
            query2.putBoolean("junit", true);
            query2.putString("id", user.get_id());
            query2.putString("code", "123456");
            query2.putString("passwd", "newPassword");

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
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Upload avatar test.
     */
    @Test
    public void uploadAvatarTest() {
        User user = generateLoggedUser();
        String avatarId = given().header("token", user.getAccount().getToken())
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
     * Upload avatar with wrong user id test.
     */
    @Test
    public void uploadAvatarWithWrongUserIdTest() {
        given().header("token", generateLoggedUser().getAccount().getToken())
                .multiPart(new File("src/test/resources/avatar.jpg")).
                pathParam("uid", "blabla").
                when().
                post(BASE_URL + "/file/User/avatar/{uid}")
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode());
    }

    /**
     * Upload avatar with not logged user test.
     */
    @Test
    public void uploadAvatarWithNotLoggedUserTest() {
        given().multiPart(new File("src/test/resources/avatar.jpg")).
                pathParam("uid", generateUser().get_id()).
                when().
                post(BASE_URL + "/file/User/avatar/{uid}")
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode());
    }

    /**
     * Get avatar with wrong avatar id test.
     */
    @Test
    public void getAvatarWithWrongAvatarIdTest() {
        given().pathParam("avatar", "blabla")
                .get(BASE_URL + "/file/User/{avatar}")
                .then().assertThat().statusCode(404);
    }
}
