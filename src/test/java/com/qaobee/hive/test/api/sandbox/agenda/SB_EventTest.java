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

package com.qaobee.hive.test.api.sandbox.agenda;

import com.qaobee.hive.api.v1.sandbox.agenda.SB_EventVerticle;
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
 * The type Event test.
 */
public class SB_EventTest extends VertxJunitSupport {


    /**
     * Add event test.
     */
    @Test
    public void addEventTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        
        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_LABEL, "labelValue");
        params.putString(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
        
        final JsonObject link = new JsonObject();
        link.putString(SB_EventVerticle.PARAM_LINK_TYPE, "championship");
        params.putObject("link", link);
        params.putNumber(SB_EventVerticle.PARAM_START_DATE, 1435701600000l);
        params.putNumber(SB_EventVerticle.PARAM_END_DATE, 1435701600100l);
        
        final JsonObject owner = new JsonObject();
        owner.putString("sandboxId", "558b0efebd2e39cdab651e1f");
        owner.putString("effectiveId", "550b31f925da07681592db23");
        params.putObject("owner", owner);
        
        req.setBody(params.encode());
        final JsonObject result = new JsonObject(sendonBus(SB_EventVerticle.ADD, req, user.getAccount().getToken()));
        System.out.println(result);
        Assert.assertNotNull("id is null", result.getString("_id"));
    }

    /**
     * Add event with missing mandatory parameters test.
     */
    @Test
    public void addEventWithMissingMandatoryParametersTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_LABEL, "labelValue");
        params.putString(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
        
        req.setBody(params.encode());
        final JsonObject result = new JsonObject(sendonBus(SB_EventVerticle.ADD, req, user.getAccount().getToken()));
        Assert.assertTrue("addEventWithMissingMandatoryParametersTest", result.getString("code").contains(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Add event with wrong http method test.
     */
    @Test
    public void addEventWithWrongHttpMethodTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final JsonObject result = new JsonObject(sendonBus(SB_EventVerticle.ADD, req, user.getAccount().getToken()));
        Assert.assertTrue("addEventWithWrongHttpMethodTest", result.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Add event with not logged user test.
     */
    @Test
    public void addEventWithNotLoggedUserTest() {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject result = new JsonObject(sendonBus(SB_EventVerticle.ADD, req));
        System.out.println(result.encodePrettily());
        Assert.assertTrue("addEventWithNotLoggedUserTest", result.getString("code").contains(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list event test.
     */
    @Test
    public void getListEventTest() {
        
    	populate(POPULATE_ONLY, DATA_EVENT_HAND);
    	
    	//First Add an event
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        
        /* list of parameters */
        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
        params.putArray(SB_EventVerticle.PARAM_LINK_TYPE, new JsonArray(new String[]{"championship"}));
        params.putNumber(SB_EventVerticle.PARAM_START_DATE, 1435701600000l);
        params.putNumber(SB_EventVerticle.PARAM_END_DATE, 1467237600000l);
        params.putString(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "550b31f925da07681592db23");
        req.setBody(params.encode());
        final String reply = sendonBus(SB_EventVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertEquals("getListEventTest1", 4, new JsonArray(reply).size());
        
        params.putString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "");
        
        req.setBody(params.encode());
        final String reply2 = sendonBus(SB_EventVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertEquals("getListEventTest2", 4, new JsonArray(reply2).size());
        
        params.putString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "TOTO");
        
        req.setBody(params.encode());
        final String reply3 = sendonBus(SB_EventVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertEquals("getListEventTest3", 0, new JsonArray(reply3).size());
    }

    /**
     * Gets list event with wrong http method test.
     */
    @Test
    public void getListEventWithWrongHttpMethodTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
        params.putArray(SB_EventVerticle.PARAM_LINK_TYPE, new JsonArray(new String[]{"championship"}));
        params.putNumber(SB_EventVerticle.PARAM_START_DATE, 1435701600000l);
        params.putNumber(SB_EventVerticle.PARAM_END_DATE, 1467237600000l);
        params.putString(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "550b31f925da07681592db23");
        req.setBody(params.encode());
        final String reply = sendonBus(SB_EventVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertTrue("getListEventWithWrongHttpMethodTest", new JsonObject(reply).getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets list event with missing parameters test.
     */
    @Test
    public void getListEventWithMissingParametersTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putArray(SB_EventVerticle.PARAM_LINK_TYPE, new JsonArray(new String[]{"championship"}));
        params.putNumber(SB_EventVerticle.PARAM_START_DATE, 1435701600000l);
        params.putNumber(SB_EventVerticle.PARAM_END_DATE, 1467237600000l);
        req.setBody(params.encode());
        final String reply = sendonBus(SB_EventVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertTrue("getListEventWithMissingParametersTest", new JsonObject(reply).getString("code").contains(ExceptionCodes.INVALID_PARAMETER.toString()));
    }
    
    /**
     * Gets list event with not logged user test.
     */
    @Test
    public void getListEventWithNotLoggedUserTest() {
        //First Add an event
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
        params.putArray(SB_EventVerticle.PARAM_LINK_TYPE, new JsonArray(new String[]{"championship"}));
        params.putNumber(SB_EventVerticle.PARAM_START_DATE, 1435701600000l);
        params.putNumber(SB_EventVerticle.PARAM_END_DATE, 1467237600000l);
        params.putString(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "550b31f925da07681592db23");
        req.setBody(params.encode());
        final String reply = sendonBus(SB_EventVerticle.GET_LIST, req);
        Assert.assertTrue("getListEventWithNotLoggedUserTest", new JsonObject(reply).getString("code").contains(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets event by id test.
     */
    @Test
    public void getEventByIdTest() {
    	
    	populate(POPULATE_ONLY, DATA_EVENT_HAND);
    	
        //First Add an event
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        params.put(SB_EventVerticle.PARAM_ID, Collections.singletonList("55847ed0d040353767a48e68"));
        req.setParams(params);
        final JsonObject reply = new JsonObject(sendonBus(SB_EventVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertEquals("getEventByIdTest", "Amical", reply.getString("label"));
    }

    /**
     * Gets event by id with wrong http method test.
     */
    @Test
    public void getEventByIdWithWrongHttpMethodTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        final HashMap<String, List<String>> params = new HashMap<>();
        params.put(SB_EventVerticle.PARAM_ID, Collections.singletonList("12345"));
        req.setParams(params);
        final JsonObject reply = new JsonObject(sendonBus(SB_EventVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertTrue("getEventByIdWithWrongHttpMethodTest", reply.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets event by id with not logged user test.
     */
    @Test
    public void getEventByIdWithNotLoggedUserTest() {
        //First Add an event
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        params.put(SB_EventVerticle.PARAM_ID, Collections.singletonList("12345"));
        req.setParams(params);
        final JsonObject reply = new JsonObject(sendonBus(SB_EventVerticle.GET, req));
        Assert.assertTrue("getEventByIdWithNotLoggedUserTest", reply.getString("code").contains(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets event by id with missing parameters test.
     */
    @Test
    public void getEventByIdWithMissingParametersTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        req.setParams(params);
        final JsonObject reply = new JsonObject(sendonBus(SB_EventVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertTrue("getEventByIdWithMissingParametersTest", reply.getString("code").contains(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Gets event by id with empty parameters test.
     */
    @Test
    public void getEventByIdWithEmptyParametersTest() {
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final JsonObject reply = new JsonObject(sendonBus(SB_EventVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertTrue("getEventByIdWithEmptyParametersTest", reply.getString("code").contains(ExceptionCodes.INVALID_PARAMETER.toString()));
    }
}
