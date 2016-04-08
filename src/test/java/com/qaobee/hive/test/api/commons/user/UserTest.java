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
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.UUID;

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
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        req.setBody(params.encode());
        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        String name = result.getString("name");
        Assert.assertEquals(u.getName(), name);
    }

    /**
     * Test Login OK with an uppercase login.
     */
    @Test
    public void loginOkWithUppercaseLogin() {
        User u = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin().toUpperCase());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        req.setBody(params.encode());
        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        String name = result.getString("name");
        Assert.assertEquals(u.getName(), name);
    }

    /**
     * Badlogin hTTP method.
     */
    @Test
    public void badloginHTTPMethod() {
        User u = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        req.setBody(params.encode());
        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Wrong http method", result.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Login ok with mobile token.
     */
    @Test
    public void loginOkWithMobileToken() {
        User u = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, UUID.randomUUID().toString());
        req.setBody(params.encode());
        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        String name = result.getString("name");
        Assert.assertEquals(u.getName(), name);
    }

    /**
     * Test Login with badlogin.
     */
    @Test
    public void loginKo() {
        User u = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, "badlogin");
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        req.setBody(params.encode());
        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Wrong login", result.getString("code").contains(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Bad login test.
     */
    @Test
    public void badLoginTest() {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, "badlogin");
        req.setBody(params.encode());
        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Wrong login", result.getString("code").contains(ExceptionCodes.BAD_LOGIN.toString()));
        final JsonObject params2 = new JsonObject();
        params.putString(UserVerticle.PARAM_PWD, "toto");
        req.setBody(params2.encode());
        final String reply2 = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result2 = new JsonObject(reply2);
        Assert.assertTrue("Wrong login", result2.getString("code").contains(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Test Login with bad password.
     */
    @Test
    public void loginPasswordKo() {
        User u = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, "tutu");
        req.setBody(params.encode());
        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Wrong login", result.getString("code").contains(ExceptionCodes.BAD_LOGIN.toString()));
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
            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.POST);
            final JsonObject params = new JsonObject();
            params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
            params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
            req.setBody(params.encode());
            JsonObject result = new JsonObject(sendonBus(UserVerticle.LOGIN, req));
            Assert.assertTrue("User inactive", result.getString("code").contains(ExceptionCodes.NON_ACTIVE.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
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
            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.GET);
            req.getParams().put(UserVerticle.PARAM_COUNTRY_ID, Collections.singletonList("CNTR-250-FR-FRA"));
            final JsonObject result = new JsonObject(sendonBus(UserVerticle.META, req, user.getAccount().getToken()));
            Assert.assertTrue("Activity not found !", result.containsField("activity"));
            Assert.assertTrue("Structure not found !", result.containsField("structure"));
            // ACT-HAND FR
            Assert.assertTrue("Season not found !", result.containsField("season"));
            JsonObject seasonObject = result.getObject("season");
            Assert.assertEquals("Wrong activity found for season", "ACT-HAND", seasonObject.getString("activityId"));
            Assert.assertEquals("Wrong country found for season", "CNTR-250-FR-FRA", seasonObject.getString("countryId"));
            GregorianCalendar today = new GregorianCalendar();
            int year = today.get(GregorianCalendar.MONTH) < 5 ? today.get(GregorianCalendar.YEAR) - 1 : today.get(GregorianCalendar.YEAR);
            Assert.assertEquals("Wrong period found for season", "SAI-" + year, seasonObject.getString("code"));
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
            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.POST);
            req.getParams().put(UserVerticle.PARAM_COUNTRY_ID, Collections.singletonList("Vulcain"));
            final JsonObject result = new JsonObject(sendonBus(UserVerticle.META, req, user.getAccount().getToken()));
            Assert.assertTrue("getMetasWithWrongCountry", result.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
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
            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.GET);
            req.getParams().put(UserVerticle.PARAM_COUNTRY_ID, Collections.singletonList("CNTR-250-FR-FRA"));
            final JsonObject result = new JsonObject(sendonBus(UserVerticle.META, req, user.getAccount().getToken()));
            Assert.assertTrue("getMetasWithWrongCountry", result.getString("code").contains(ExceptionCodes.DB_NO_ROW_RETURNED.toString()));
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
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);
        req.setBody(params.encode());
        JsonObject result = new JsonObject(sendonBus(UserVerticle.LOGIN, req));
        Assert.assertEquals(u.getName(), result.getString("name"));

        final JsonObject params2 = new JsonObject();
        params2.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params2.putString(UserVerticle.MOBILE_TOKEN, token);
        req.setBody(params.encode());
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.LOGIN_BY_TOKEN, req));
        Assert.assertEquals(u.getName(), reply.getString("name"));
    }

    /**
     * Login by mobile token wrong hTTP method.
     */
    @Test
    public void loginByMobileTokenWrongHTTPMethod() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);
        req.setBody(params.encode());
        JsonObject result = new JsonObject(sendonBus(UserVerticle.LOGIN, req));
        Assert.assertEquals(u.getName(), result.getString("name"));

        req.setMethod(Constantes.GET);
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.LOGIN_BY_TOKEN, req));
        Assert.assertTrue("Wrong http method", reply.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Login by mobile token wrong login.
     */
    @Test
    public void loginByMobileTokenWrongLogin() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);
        req.setBody(params.encode());
        JsonObject result = new JsonObject(sendonBus(UserVerticle.LOGIN, req));
        Assert.assertEquals(u.getName(), result.getString("name"));

        final JsonObject params2 = new JsonObject();
        params2.putString(UserVerticle.PARAM_LOGIN, "badLogin");
        params2.putString(UserVerticle.MOBILE_TOKEN, token);
        req.setBody(params2.encode());
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.LOGIN_BY_TOKEN, req));
        Assert.assertTrue("loginByMobileTokenWrongLogin", reply.getString("code").contains(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Login by mobile token wrong token.
     */
    @Test
    public void loginByMobileTokenWrongToken() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);
        req.setBody(params.encode());
        JsonObject result = new JsonObject(sendonBus(UserVerticle.LOGIN, req));
        Assert.assertEquals(u.getName(), result.getString("name"));

        final JsonObject params2 = new JsonObject();
        params2.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params2.putString(UserVerticle.MOBILE_TOKEN, "123456");
        req.setBody(params2.encode());
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.LOGIN_BY_TOKEN, req));
        Assert.assertTrue("loginByMobileTokenWrongToken", reply.getString("code").contains(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Login by mobile token no data.
     */
    @Test
    public void loginByMobileTokenNoData() {
        User u = generateUser();
        String token = UUID.randomUUID().toString();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        params.putString(UserVerticle.MOBILE_TOKEN, token);
        req.setBody(params.encode());
        JsonObject result = new JsonObject(sendonBus(UserVerticle.LOGIN, req));
        Assert.assertEquals(u.getName(), result.getString("name"));
        req.setBody(null);
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.LOGIN_BY_TOKEN, req));
        Assert.assertTrue("loginByMobileTokenNoData", reply.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets metas not logged.
     */
    @Test
    public void getMetasNotLogged() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND);
        User user = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        final JsonObject result = new JsonObject(sendonBus(UserVerticle.META, req, user.getAccount().getToken()));
        Assert.assertTrue("getMetasWrongHTTPMethod", result.getString("code").contains(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets metas wrong hTTP method.
     */
    @Test
    public void getMetasWrongHTTPMethod() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND);
        User user = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject result = new JsonObject(sendonBus(UserVerticle.META, req, user.getAccount().getToken()));
        Assert.assertTrue("getMetasWrongHTTPMethod", result.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Fetch a user by id
     */
    @Test
    public void getUserById() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setParams(getParams(new String[]{"id", user.get_id()}));
        final JsonObject result = new JsonObject(sendonBus(UserVerticle.USER_INFO, req, user.getAccount().getToken()));
        Assert.assertEquals(result.getString("_id"), user.get_id());
    }

    /**
     * Gets user by id not logged.
     */
    @Test
    public void getUserByIdNotLogged() {
        User user = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setParams(getParams(new String[]{"id", user.get_id()}));
        final JsonObject result = new JsonObject(sendonBus(UserVerticle.USER_INFO, req, user.getAccount().getToken()));
        Assert.assertTrue("Wrong login", result.getString("code").contains(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets user by id wrong hTTP method.
     */
    @Test
    public void getUserByIdWrongHTTPMethod() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setParams(getParams(new String[]{"id", user.get_id()}));
        final JsonObject result = new JsonObject(sendonBus(UserVerticle.USER_INFO, req, user.getAccount().getToken()));
        Assert.assertTrue("Wrong login", result.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Fetch the current logged user
     */
    @Test
    public void getCurrentUser() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        User p2 = Json.decodeValue(new JsonObject(sendonBus(UserVerticle.CURRENT, req, user.getAccount().getToken())).encode(), User.class);
        Assert.assertEquals(user.get_id(), p2.get_id());
    }

    /**
     * Gets current user not logged.
     */
    @Test
    public void getCurrentUserNotLogged() {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        JsonObject result = new JsonObject(sendonBus(UserVerticle.CURRENT, req, "abcd"));
        Assert.assertTrue("Wrong login", result.getString("code").contains(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets current user wrong hTTP method.
     */
    @Test
    public void getCurrentUserWrongHTTPMethod() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        JsonObject result = new JsonObject(sendonBus(UserVerticle.CURRENT, req, user.getAccount().getToken()));
        Assert.assertTrue("Wrong http method", result.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Logout void.
     */
    @Test
    public void logout() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.LOGOUT, req, user.getAccount().getToken()));
        Assert.assertEquals("logout ko", reply.getBoolean("status"), true);
    }

    /**
     * Logout bad hTTP method.
     */
    @Test
    public void logoutBadHTTPMethod() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.LOGOUT, req, user.getAccount().getToken()));
        Assert.assertTrue("Wrong http method", reply.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Logout failed.
     */
    @Test
    public void logoutFailed() {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.LOGOUT, req, "abcd"));
        Assert.assertEquals("logout failed ko", reply.getBoolean("status"), false);
    }

    /**
     * Password renew.
     */
    @Test
    public void passwordRenew() {
        User user = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        JsonObject query = new JsonObject();
        query.putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
        req.setBody(query.encode());
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
        Assert.assertEquals("passwordRenew ko", reply.getBoolean("status"), true);
    }

    /**
     * Password renew bad request.
     */
    @Test
    public void passwordRenewBadRequest() {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
        Assert.assertTrue("passwordRenewBadLogin ko", reply.getString("code").contains(ExceptionCodes.INTERNAL_ERROR.toString()));
    }

    /**
     * Password renew bad login.
     */
    @Test
    public void passwordRenewBadLogin() {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        JsonObject query = new JsonObject();
        query.putString(UserVerticle.PARAM_LOGIN, "toto");
        req.setBody(query.encode());
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
        Assert.assertTrue("passwordRenewBadLogin ko", reply.getString("code").contains(ExceptionCodes.BAD_LOGIN.toString()));
    }

    /**
     * Password renew bad hTTP method.
     */
    @Test
    public void passwordRenewBadHTTPMethod() {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
        Assert.assertTrue("passwordRenewBadHTTPMethod ko", reply.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Password renew activation code check.
     */
    @Test
    public void passwordRenewActivationCodeCheck() {
        try {
            // First step ask for a new code
            User user = generateUser();
            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.POST);
            JsonObject query = new JsonObject();
            query.putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
            req.setBody(query.encode());
            JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
            Assert.assertEquals("passwordRenewActivationCodeCheck - step 1 - ko", reply.getBoolean("status"), true);
            // fetch the code
            JsonObject jsonuser = mongo.getById(user.get_id(), User.class);
            String code = jsonuser.getObject("account").getString("activationPasswd");
            req.setParams(getParams(new String[]{"id", user.get_id()}, new String[]{"code", code}));
            req.setMethod(Constantes.GET);
            JsonObject reply2 = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW_CHK, req));
            Assert.assertEquals("passwordRenewActivationCodeCheck ko", reply2.getBoolean("status"), true);
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
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        JsonObject query = new JsonObject();
        query.putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
        req.setBody(query.encode());
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
        Assert.assertEquals("passwordRenewActivationCodeCheck - step 1 - ko", reply.getBoolean("status"), true);
        req.setParams(getParams(new String[]{"id", user.get_id()}, new String[]{"code", "12345"}));
        req.setMethod(Constantes.GET);
        JsonObject reply2 = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW_CHK, req));
        Assert.assertEquals("passwordRenewActivationCodeCheck ko", reply2.getBoolean("status"), false);
    }

    /**
     * Password renew activation code check bad hTTP method.
     */
    @Test
    public void passwordRenewActivationCodeCheckBadHTTPMethod() {
        // First step ask for a new code
        User user = generateUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        JsonObject query = new JsonObject();
        query.putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
        req.setBody(query.encode());
        JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
        Assert.assertEquals("passwordRenewActivationCodeCheck - step 1 - ko", reply.getBoolean("status"), true);
        req.setParams(getParams(new String[]{"id", user.get_id()}, new String[]{"code", "12345"}));
        req.setMethod(Constantes.POST);
        JsonObject reply2 = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW_CHK, req));
        Assert.assertTrue("passwordRenewActivationCodeCheckBadHTTPMethod ko", reply2.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Password reset.
     */
    @Test
    public void passwordReset() {
        try {
            // First step ask for a new code
            User user = generateUser();
            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.POST);
            JsonObject query = new JsonObject();
            query.putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
            req.setBody(query.encode());
            JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
            Assert.assertEquals("passwordRenewActivationCodeCheck - step 1 - ko", reply.getBoolean("status"), true);
            // fetch the code
            JsonObject jsonuser = mongo.getById(user.get_id(), User.class);
            String code = jsonuser.getObject("account").getString("activationPasswd");
            req.setParams(getParams(new String[]{"id", user.get_id()}, new String[]{"code", code}));
            req.setMethod(Constantes.GET);
            JsonObject reply2 = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW_CHK, req));
            Assert.assertEquals("passwordRenewActivationCodeCheck ko", reply2.getBoolean("status"), true);
            JsonObject query2 = new JsonObject();
            query2.putBoolean("junit", true);
            query2.putString("id", user.get_id());
            query2.putString("code", code);
            query2.putString("passwd", "newPassword");
            req.setBody(query2.encode());
            req.setMethod(Constantes.POST);
            JsonObject reply3 = new JsonObject(sendonBus(UserVerticle.PASSWD_RESET, req));
            Assert.assertEquals("passwordReset ko", reply3.getBoolean("status"), true);
            // Finaly test login
            final JsonObject params = new JsonObject();
            params.putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
            params.putString(UserVerticle.PARAM_PWD, "newPassword");
            req.setBody(params.encode());
            JsonObject result = new JsonObject(sendonBus(UserVerticle.LOGIN, req));
            String name = result.getString("name");
            Assert.assertEquals(user.getName(), name);
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
            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.POST);
            JsonObject query = new JsonObject();
            query.putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
            req.setBody(query.encode());
            JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
            Assert.assertEquals("passwordRenewActivationCodeCheck - step 1 - ko", reply.getBoolean("status"), true);
            // fetch the code
            JsonObject jsonuser = mongo.getById(user.get_id(), User.class);
            String code = jsonuser.getObject("account").getString("activationPasswd");
            req.setParams(getParams(new String[]{"id", user.get_id()}, new String[]{"code", code}));
            req.setMethod(Constantes.GET);
            JsonObject reply2 = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW_CHK, req));
            Assert.assertEquals("passwordRenewActivationCodeCheck ko", reply2.getBoolean("status"), true);
            JsonObject query2 = new JsonObject();
            query2.putBoolean("junit", true);
            query2.putString("id", user.get_id());
            query2.putString("code", "123456");
            query2.putString("passwd", "newPassword");
            req.setBody(query2.encode());
            req.setMethod(Constantes.POST);
            JsonObject reply3 = new JsonObject(sendonBus(UserVerticle.PASSWD_RESET, req));
            Assert.assertEquals("passwordResetWrongCode ko", reply3.getBoolean("status"), false);
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Password reset wrong hTTP method.
     */
    @Test
    public void passwordResetWrongHTTPMethod() {
        try {
            // First step ask for a new code
            User user = generateUser();
            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.POST);
            JsonObject query = new JsonObject();
            query.putString(UserVerticle.PARAM_LOGIN, user.getAccount().getLogin());
            req.setBody(query.encode());
            JsonObject reply = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW, req));
            Assert.assertEquals("passwordRenewActivationCodeCheck - step 1 - ko", reply.getBoolean("status"), true);
            // fetch the code
            JsonObject jsonuser = mongo.getById(user.get_id(), User.class);
            String code = jsonuser.getObject("account").getString("activationPasswd");
            req.setParams(getParams(new String[]{"id", user.get_id()}, new String[]{"code", code}));
            req.setMethod(Constantes.GET);
            JsonObject reply2 = new JsonObject(sendonBus(UserVerticle.PASSWD_RENEW_CHK, req));
            Assert.assertEquals("passwordRenewActivationCodeCheck ko", reply2.getBoolean("status"), true);
            req.setMethod(Constantes.GET);
            JsonObject reply3 = new JsonObject(sendonBus(UserVerticle.PASSWD_RESET, req));
            Assert.assertTrue("passwordResetWrongHTTPMethod ko", reply3.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

}
