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

package com.qaobee.hive.test.api.sandbox;

import com.qaobee.hive.api.v1.sandbox.config.SandBoxCfgVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
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
 * Created by xavier on 24/06/15.
 */
public class SandBoxCfgTest extends VertxJunitSupport {

    @Test
    public void retrieveSandBoxConfigById() {

        // SandBoxCfg._id
        String id = "558b0fc0bd2e39cdab651e21";

        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);
        final Map<String, List<String>> params = new HashMap<>();
        params.put(SandBoxCfgVerticle.PARAM_ID, Collections.singletonList(id));
        req.setParams(params);

        final String reply = sendonBus(SandBoxCfgVerticle.GET, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);
        Assert.assertEquals(id, result.getString("_id"));
        Assert.assertEquals(user.get_id(), result.getObject("sandbox").getString("owner"));

    }

    @Test
    public void retrieveSandBoxConfigByOwner() {

        // SandBoxCfg._id
        String id = "558b0fc0bd2e39cdab651e21";

        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");

        user.getAccount().getListPlan().get(0).getActivity().set_id("ACT-HAND");
        try {
            mongo.save(user);

            final RequestWrapper req = new RequestWrapper();
            req.setLocale(LOCALE);
            req.setMethod(Constantes.GET);
            req.setUser(user);
            final Map<String, List<String>> params = new HashMap<>();
            params.put(SandBoxCfgVerticle.PARAM_ACTIVITY_ID, Collections.singletonList("ACT-HAND"));
            req.setParams(params);

            final String reply = sendonBus(SandBoxCfgVerticle.GET_BY_OWNER, req, user.getAccount().getToken());
            JsonObject result = new JsonObject(reply);
            Assert.assertEquals(id, result.getString("_id"));
            Assert.assertEquals(user.get_id(), result.getObject("sandbox").getString("owner"));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }
}
