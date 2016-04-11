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

import com.qaobee.hive.api.v1.sandbox.effective.SB_PersonVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Person test.
 */
public class SB_PersonTest extends VertxJunitSupport {

    @Test
    public void getListPersonByIdTest() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_PERSON_HAND);
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
        params.putArray(SB_PersonVerticle.PARAM_LIST_ID, new JsonArray(
                new String[]{"550a05dadb8f8b6e2f51f4db", "550a05e3db8f8b6e2f51f4dc", "550a05e9db8f8b6e2f51f4dd", "550a05f7db8f8b6e2f51f4de", "550a0600db8f8b6e2f51f4df", "550a0606db8f8b6e2f51f4e0",
                        "550a060ddb8f8b6e2f51f4e1", "550a0614db8f8b6e2f51f4e2", "550a061bdb8f8b6e2f51f4e3", "550a0620db8f8b6e2f51f4e4", "550a0620db8f8b6e2f51f4e5"}));

		/* ListId person */
        params.putArray(SB_PersonVerticle.PARAM_LIST_FIELD, new JsonArray(new String[]{"_id", "name", "firstname", "avatar", "status"}));
        req.setBody(params.encode());

		/* Call to verticle */
        final String reply = sendOnBus(SB_PersonVerticle.GET_LIST, req, user.getAccount().getToken());
        Assert.assertEquals(11, new JsonArray(reply).size());

    }

    @Test
    public void getListPersonSandboxTest() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_PERSON_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        final Map<String, List<String>> params = new HashMap<>();

        // id
        params.put(SB_PersonVerticle.PARAM_SANDBOX_ID, Collections.singletonList("558b0efebd2e39cdab651e1f"));
        req.setParams(params);

		/* Call to verticle */
        final String reply = sendOnBus(SB_PersonVerticle.GET_LIST_SANDBOX, req, user.getAccount().getToken());
        Assert.assertEquals(17, new JsonArray(reply).size());

    }
}
