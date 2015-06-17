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

import com.qaobee.hive.api.v1.commons.users.SignupVerticle;
import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.LevelPlan;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.vertx.java.core.json.DecodeException;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

/**
 * The Class LoginTest.
 *
 * @author cke
 */
public class LoginTest extends VertxJunitSupport {

    // TODO : (mx) : implémenter le login fail et le user non actif

    /**
     * Test Login OK.
     */
    @Test
    public void testLoginOk() {
        final User user = Json.decodeValue(moduleConfig.getObject("junit").getObject("user").copy().encode(), User.class);
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.PUT);
        final JsonObject jsono = new JsonObject(Json.encode(user));
        final Plan plan = new Plan();
        plan.setAmountPaid(500);
        plan.setLevelPlan(LevelPlan.FREEMIUM);
        plan.setPaymentId("1");
        jsono.putObject("plan", new JsonObject(Json.encode(plan)));
        jsono.putBoolean("junit", true);
        req.setBody(jsono.encode());
        try {
            final String reply = sendonBus(SignupVerticle.REGISTER, req);
            final JsonObject jsonReply = new JsonObject(reply);


            User user1 = Json.decodeValue(mongo.getById(jsonReply.getObject("person").getString("_id"), User.class).encode(), User.class);
            Assert.assertEquals(user.getFirstname(), user1.getFirstname());

            // oh wait, we have to activate the user
            user1.getAccount().setActive(true);
            mongo.save(user1);
            req.setLocale(LOCALE);
            req.setMethod(Constantes.POST);

            final JsonObject json = new JsonObject();

            json.putString("login", user.getAccount().getLogin());
            json.putString("passwd", user.getAccount().getPasswd());
            req.setBody(json.encode());
            final JsonObject jres = new JsonObject(sendonBus(UserVerticle.LOGIN, req));
            Assert.assertEquals(user.getFirstname(), jres.getString("firstname", null));
        } catch (EncodeException | DecodeException | QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * test a wrong login
     */
    @Test
    public void testLoginKo() {

        final User u = Json.decodeValue(moduleConfig.getObject("junit").getObject("user").copy().encode(), User.class);
        u.getAccount().setLogin("foo");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.PUT);
        final JsonObject jsono = new JsonObject(Json.encode(u));
        final Plan plan = new Plan();
        plan.setAmountPaid(500);
        plan.setLevelPlan(LevelPlan.FREEMIUM);
        plan.setPaymentId("1");
        jsono.putObject("plan", new JsonObject(Json.encode(plan)));
        jsono.putBoolean("junit", true);
        req.setBody(jsono.encode());
        try {
            final String reply = sendonBus(SignupVerticle.REGISTER, req);
            final JsonObject jsonReply = new JsonObject(reply);


            User user = Json.decodeValue(mongo.getById(jsonReply.getObject("person").getString("_id"), User.class).encode(), User.class);
            Assert.assertEquals(u.getFirstname(), user.getFirstname());

            // oh wait, we have to activate the user
            user.getAccount().setActive(true);
            mongo.save(user);
            req.setLocale(LOCALE);
            req.setMethod(Constantes.POST);

            final JsonObject json = new JsonObject();

            json.putString("login", u.getAccount().getLogin());
            json.putString("passwd", "bla bla bla");
            req.setBody(json.encode());
            sendonBus(UserVerticle.LOGIN, req);
        } catch (EncodeException | DecodeException | QaobeeException e) {
            if (e instanceof QaobeeException) {
                Assert.assertEquals(ExceptionCodes.BAD_LOGIN, ((QaobeeException) e).getCode());
            } else {
                Assert.fail(e.getMessage());
            }
        }
    }

    /**
     * Test if a user is logged
     */
    @Test
    public void notLoggedTest() {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        final JsonObject error = new JsonObject(sendonBus(UserVerticle.CURRENT, req));
        Assert.assertTrue(error.getBoolean("error", false));
        Assert.assertEquals(ExceptionCodes.NOT_LOGGED.name(), error.getString("code"));
    }

    /**
     * Test if a user is not an Admin
     */
    @Ignore
    @Test
    public void notAdminTest() {
        // TODO
        /*User user = generateUser();
        user.getAccount().setToken("12345");
        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
        try {
            mongo.save(user);
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        User u2 = hab

        final JsonObject error = new JsonObject(sendonBus("resthandler.admin", req));
        Assert.assertTrue(error.getBoolean("error", false));
        Assert.assertEquals(ExceptionCodes.NOT_LOGGED.name(), error.getString("code"));*/
    }
}
