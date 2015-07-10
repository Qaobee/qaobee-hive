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

package com.qaobee.hive.test.api.sandbox.effective;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.sandbox.effective.SB_PersonVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

/**
 * The type Person test.
 */
public class PersonTest extends VertxJunitSupport {

    @Test
    public void getListPersonTest() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_PERSON_FOOT);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

		/* test based on script mongo */
        req.setMethod(Constantes.POST);

		/* list of parameters */
        final JsonObject params = new JsonObject();

		/* ListId person */
        params.putArray(SB_PersonVerticle.PARAM_LIST_ID, new JsonArray(new String[] { "541d3136f61fbf69868c1223", "541d3136f61fbf69868c121f", "541d3136f61fbf69868c1220", "541d3136f61fbf69868c1221",
                "541d3136f61fbf69868c1226", "541d3136f61fbf69868c1228", "541d3136f61fbf69868c122a", "541d3136f61fbf69868c122c", "541d3136f61fbf69868c1230", "541d3136f61fbf69868c1222",
                "541d3136f61fbf69868c122f" }));

		/* ListId person */
        params.putArray(SB_PersonVerticle.PARAM_LIST_FIELD, new JsonArray(new String[] { "_id", "name", "firstname", "avatar", "status" }));
        req.setBody(params.encode());

		/* Call to verticle */
        final String reply = sendonBus(SB_PersonVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertEquals(11, new JsonArray(reply).size());

    }
}
