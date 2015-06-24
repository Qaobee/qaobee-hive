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
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import java.util.GregorianCalendar;

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

        populate(POPULATE_ONLY, DATA_USERS);

		/* test based on script mongo */
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, "ccaft");
        params.putString(UserVerticle.PARAM_PWD, "toto");
        req.setBody(params.encode());

        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);

        String name = result.getString("name");

        Assert.assertEquals("Hidalgo", name);
    }

    /**
     * Test Login with badlogin.
     */
    @Test
    public void loginKo() {

        populate(POPULATE_ONLY, DATA_USERS);

		/* test based on script mongo */
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, "badlogin");
        params.putString(UserVerticle.PARAM_PWD, "toto");
        req.setBody(params.encode());

        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Wrong login", result.getString("message").contains("Bad login"));
    }

    /**
     * Test Login with bad password.
     */
    @Test
    public void loginPasswordKo() {

        populate(POPULATE_ONLY, DATA_USERS);

		/* test based on script mongo */
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, "ccaft");
        params.putString(UserVerticle.PARAM_PWD, "tutu");
        req.setBody(params.encode());

        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Wrong login", result.getString("message").contains("Bad login"));
    }

    /**
     * Test login user inactive.
     */
    @Test
    public void loginUserInactive() {

        populate(POPULATE_ONLY, DATA_USERS);

		/* test based on script mongo */
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);

        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, "badlogin");
        params.putString(UserVerticle.PARAM_PWD, "toto");
        req.setBody(params.encode());

        final String reply = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Wrong login", result.getString("message").contains("Bad login"));
    }

    /**
     * Fetch meta information such as current season, activity and structure
     */
    @Test
    public void getMetasHandler() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES);

        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        user.getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
        try {
            mongo.save(user);

            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.GET);
            req.setUser(user);
            final JsonObject result = new JsonObject(sendonBus(UserVerticle.META, req, user.getAccount().getToken()));
            Assert.assertTrue("Season not found !", result.containsField("season"));
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
     * Fetch a user by id
     */
    @Test
    public void getUserById() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);
        req.setParams(getParams(new String[]{"id", user.get_id()}));
        final JsonObject result = new JsonObject(sendonBus(UserVerticle.USER_INFO, req, user.getAccount().getToken()));
        Assert.assertEquals(result.getString("_id"), user.get_id());
    }

    /**
     * Fetch the current logged user
     */
    @Test
    public void getCurrentUser() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setParams(getParams());
        req.setMethod(Constantes.GET);
        req.setUser(user);
        User p2 = Json.decodeValue(new JsonObject(sendonBus(UserVerticle.CURRENT, req, user.getAccount().getToken())).encode(), User.class);
        Assert.assertEquals(user.get_id(), p2.get_id());
    }

}
