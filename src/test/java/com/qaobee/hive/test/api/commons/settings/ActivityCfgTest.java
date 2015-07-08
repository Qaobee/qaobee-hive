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

package com.qaobee.hive.test.api.commons.settings;

import com.qaobee.hive.api.v1.commons.settings.ActivityCfgVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Activity cfg test.
 */
public class ActivityCfgTest extends VertxJunitSupport {

    /**
     * Get test.
     */
    @Test
    public void getTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);

        final Map<String, List<String>> params = new HashMap<>();

        // id
        params.put(ActivityCfgVerticle.PARAM_ACTIVITY_ID, Collections.singletonList("ACT-HAND"));
        req.setParams(params);

        final String reply = sendonBus(ActivityCfgVerticle.GET, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);

        String id = result.getString("activityId");
        Assert.assertEquals("ACT-HAND", id);
    }

    /**
     * Get with wrong http method test.
     */
    @Test
    public void getWithWrongHttpMethodTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.GET);
        final Map<String, List<String>> params = new HashMap<>();

        // id
        params.put(ActivityCfgVerticle.PARAM_ACTIVITY_ID, Collections.singletonList("ACT-HAND"));
        req.setParams(params);
        final String reply = sendonBus(ActivityCfgVerticle.GET, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("getWithWrongHttpMethodTest", result.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Get without parameter test.
     */
    @Test
    public void getWithoutParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        final String reply = sendonBus(ActivityCfgVerticle.GET, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("getWithoutParameterTest", result.getString("code").contains(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Get wit wrong activity id test.
     */
    @Test
    public void getWitWrongActivityIdTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG);
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);
        final Map<String, List<String>> params = new HashMap<>();

        // id
        params.put(ActivityCfgVerticle.PARAM_ACTIVITY_ID, Collections.singletonList("123"));
        req.setParams(params);
        final String reply = sendonBus(ActivityCfgVerticle.GET, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);
        System.out.println(reply);
        Assert.assertTrue("getWitWrongActivityIdTest", result.getString("code").contains(ExceptionCodes.DB_NO_ROW_RETURNED.toString()));
    }
}
