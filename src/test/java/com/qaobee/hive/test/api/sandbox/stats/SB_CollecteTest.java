/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.test.api.sandbox.stats;

import com.qaobee.hive.api.v1.sandbox.agenda.SB_EventVerticle;
import com.qaobee.hive.api.v1.sandbox.stats.SB_CollecteVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author cke
 */
public class SB_CollecteTest extends VertxJunitSupport {

    /**
     * Add event test.
     */
    @Test
    public void addCollecteTest() {

        populate(POPULATE_ONLY, DATA_USER_QAOBEE);

        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);

        req.setMethod(Constantes.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        params.put(SB_EventVerticle.PARAM_ID, Collections.singletonList("e254897f-cf3a-48b8-bed5-a4d4664ab4a4"));
        req.setParams(params);
        final JsonObject event = new JsonObject(sendOnBus(SB_EventVerticle.GET, req, user.getAccount().getToken()));

        Assert.assertEquals("addCollecteEvent", "ACT-HAND", event.getString("activityId"));

        req.setMethod(Constantes.POST);
        final JsonObject collecte = new JsonObject();
        collecte.putObject("eventRef", event);

        final JsonArray players = new JsonArray();
        players.addString("1ce4591d-74a8-46e9-af80-d633f9344d27");
        players.addString("26baf31a-f153-41b0-9e1d-c32cb9e859dd");
        players.addString("43e62ae5-2a92-4e1a-9b9a-d1a399c096bd");
        players.addString("46bea3c9-a3c0-4f4e-91fc-0bd2797b48df");

        collecte.putArray("players", players);
        collecte.putNumber(SB_CollecteVerticle.PARAM_START_DATE, 1435701600000l);
        collecte.putNumber(SB_CollecteVerticle.PARAM_END_DATE, 1435701600100l);

        final JsonObject observers = new JsonObject();
        final JsonObject observer = new JsonObject();

        observer.putString("userId", "b50b3325-fdbd-41bf-bda4-81c827982001");
        final JsonArray indicators = new JsonArray();
        indicators.addString("all");
        observer.putArray("indicators", indicators);

        observers.putObject("observer", observer);
        collecte.putObject("observers", observers);

        req.setBody(collecte.encode());

        final JsonObject result = new JsonObject(sendOnBus(SB_CollecteVerticle.ADD, req, user.getAccount().getToken()));
        System.out.println(result);
        Assert.assertNotNull("id is null", result.getString("_id"));
    }

    /**
     * Add event with missing mandatory parameters test.
     */
    @Test
    public void addCollecteWithMissingMandatoryParametersTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putNumber(SB_CollecteVerticle.PARAM_START_DATE, 1446305390000l);
        params.putNumber(SB_CollecteVerticle.PARAM_END_DATE, 1446310801000l);
        params.putString(SB_CollecteVerticle.PARAM_SANDBOX_ID, "561ec20b409937a6b439d4e9");
        params.putString(SB_CollecteVerticle.PARAM_EFFECTIVE_ID, "561ec4d0409937a6b439d4ea");

        req.setBody(params.encode());
        final JsonObject result = new JsonObject(sendOnBus(SB_CollecteVerticle.ADD, req, user.getAccount().getToken()));
        Assert.assertTrue("addCollecteWithMissingMandatoryParametersTest", result.getString("code").contains(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Add event with wrong http method test.
     */
    @Test
    public void addCollecteWithWrongHttpMethodTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final JsonObject result = new JsonObject(sendOnBus(SB_CollecteVerticle.ADD, req, user.getAccount().getToken()));
        Assert.assertTrue("addCollecteWithWrongHttpMethodTest", result.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Add event with not logged user test.
     */
    @Test
    public void addCollecteWithNotLoggedUserTest() {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject result = new JsonObject(sendOnBus(SB_CollecteVerticle.ADD, req));
        System.out.println(result.encodePrettily());
        Assert.assertTrue("addCollecteWithNotLoggedUserTest", result.getString("code").contains(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list event test.
     */
    @Test
    public void getListCollecteTest() {

        populate(POPULATE_ONLY, DATA_USER_QAOBEE);

        //First Add an event
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);

        /* list of parameters */
        final JsonObject params = new JsonObject();
        params.putNumber(SB_CollecteVerticle.PARAM_START_DATE, 1448491800000l);
        params.putNumber(SB_CollecteVerticle.PARAM_END_DATE, 1448492500000l);
        params.putString(SB_CollecteVerticle.PARAM_SANDBOX_ID, "561ec20b409937a6b439d4e9");
        params.putString(SB_CollecteVerticle.PARAM_EFFECTIVE_ID, "561ec4d0409937a6b439d4ea");
        req.setBody(params.encode());
        final String reply = sendOnBus(SB_CollecteVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertEquals("getListCollecteTest1", 1, new JsonArray(reply).size());

        params.putString(SB_CollecteVerticle.PARAM_SANDBOX_ID, "TOTO");

        req.setBody(params.encode());
        final String reply3 = sendOnBus(SB_CollecteVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertEquals("getListCollecteTest3", 0, new JsonArray(reply3).size());
    }

    /**
     * Gets list event with wrong http method test.
     */
    @Test
    public void getListCollecteWithWrongHttpMethodTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final JsonObject params = new JsonObject();
        params.putNumber(SB_CollecteVerticle.PARAM_START_DATE, 1448491800000l);
        params.putNumber(SB_CollecteVerticle.PARAM_END_DATE, 1448492500000l);
        params.putString(SB_CollecteVerticle.PARAM_SANDBOX_ID, "561ec20b409937a6b439d4e9");
        params.putString(SB_CollecteVerticle.PARAM_EFFECTIVE_ID, "561ec4d0409937a6b439d4ea");
        req.setBody(params.encode());
        final String reply = sendOnBus(SB_CollecteVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertTrue("getListCollecteWithWrongHttpMethodTest", new JsonObject(reply).getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets list event with missing parameters test.
     */
    @Test
    public void getListCollecteWithMissingParametersTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putNumber(SB_CollecteVerticle.PARAM_START_DATE, 1448491800000l);
        params.putNumber(SB_CollecteVerticle.PARAM_END_DATE, 1448492500000l);
        req.setBody(params.encode());
        final String reply = sendOnBus(SB_CollecteVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertTrue("getListCollecteWithMissingParametersTest", new JsonObject(reply).getString("code").contains(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Gets list event with not logged user test.
     */
    @Test
    public void getListCollecteWithNotLoggedUserTest() {
        //First Add an event
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putNumber(SB_CollecteVerticle.PARAM_START_DATE, 1448491800000l);
        params.putNumber(SB_CollecteVerticle.PARAM_END_DATE, 1448492500000l);
        params.putString(SB_CollecteVerticle.PARAM_SANDBOX_ID, "561ec20b409937a6b439d4e9");
        params.putString(SB_CollecteVerticle.PARAM_EFFECTIVE_ID, "561ec4d0409937a6b439d4ea");
        req.setBody(params.encode());
        final String reply = sendOnBus(SB_CollecteVerticle.GET_LIST, req);
        Assert.assertTrue("getListCollecteWithNotLoggedUserTest", new JsonObject(reply).getString("code").contains(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets event by id test.
     */
    @Test
    public void getCollecteByIdTest() {

        populate(POPULATE_ONLY, DATA_USER_QAOBEE);

        //First Add an event
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        params.put(SB_CollecteVerticle.PARAM_ID, Collections.singletonList("565e0f0dbcda594d193e24db"));
        req.setParams(params);
        final JsonObject reply = new JsonObject(sendOnBus(SB_CollecteVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertEquals("getCollecteByIdTest", "done", reply.getString("status"));
    }

    /**
     * Gets event by id with wrong http method test.
     */
    @Test
    public void getCollecteByIdWithWrongHttpMethodTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        final HashMap<String, List<String>> params = new HashMap<>();
        params.put(SB_CollecteVerticle.PARAM_ID, Collections.singletonList("12345"));
        req.setParams(params);
        final JsonObject reply = new JsonObject(sendOnBus(SB_CollecteVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertTrue("getCollecteByIdWithWrongHttpMethodTest", reply.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets event by id with not logged user test.
     */
    @Test
    public void getCollecteByIdWithNotLoggedUserTest() {
        //First Add an event
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        params.put(SB_CollecteVerticle.PARAM_ID, Collections.singletonList("12345"));
        req.setParams(params);
        final JsonObject reply = new JsonObject(sendOnBus(SB_CollecteVerticle.GET, req));
        Assert.assertTrue("getCollecteByIdWithNotLoggedUserTest", reply.getString("code").contains(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets event by id with missing parameters test.
     */
    @Test
    public void getCollecteByIdWithMissingParametersTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        req.setParams(params);
        final JsonObject reply = new JsonObject(sendOnBus(SB_CollecteVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertTrue("getCollecteByIdWithMissingParametersTest", reply.getString("code").contains(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Gets event by id with empty parameters test.
     */
    @Test
    public void getCollecteByIdWithEmptyParametersTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final JsonObject reply = new JsonObject(sendOnBus(SB_CollecteVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertTrue("getCollecteByIdWithEmptyParametersTest", reply.getString("code").contains(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

}
