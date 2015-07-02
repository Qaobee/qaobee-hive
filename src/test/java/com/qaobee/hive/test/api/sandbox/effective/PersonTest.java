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

import com.qaobee.hive.api.v1.sandbox.effective.PersonVerticle;
import com.qaobee.hive.business.model.sandbox.effective.Person;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import java.util.*;

/**
 * The type Person test.
 */
public class PersonTest extends VertxJunitSupport {
@Ignore
    @Test
    public void getListPersonTest() {
        populate(POPULATE_ONLY, SETTINGS_PERSON_FOOT);

		/* test based on script mongo */
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);

		/* list of parameters */
        final JsonObject params = new JsonObject();

		/* ListId person */
        params.putArray(PersonVerticle.PARAM_LIST_ID, new JsonArray(new String[] { "541d3136f61fbf69868c1223", "541d3136f61fbf69868c121f", "541d3136f61fbf69868c1220", "541d3136f61fbf69868c1221",
                "541d3136f61fbf69868c1226", "541d3136f61fbf69868c1228", "541d3136f61fbf69868c122a", "541d3136f61fbf69868c122c", "541d3136f61fbf69868c1230", "541d3136f61fbf69868c1222",
                "541d3136f61fbf69868c122f" }));

		/* ListId person */
        params.putArray(PersonVerticle.PARAM_LIST_FIELD, new JsonArray(new String[] { "_id", "name", "firstname", "avatar", "status" }));
        req.setBody(params.encode());

		/* Call to verticle */
        final String reply = sendonBus(PersonVerticle.GET_LIST, req);
        Assert.assertEquals(11, new JsonArray(reply).size());

    }

    @Test
    @Ignore
    public void updateFeatureEffectiveTest() {
        populate(POPULATE_ONLY, SETTINGS_PERSON_FOOT);

        // BEFORE
        RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        Map<String, List<String>> mapParams = new HashMap<>();
        mapParams.put(PersonVerticle.PARAM_PERSON_ID, Collections.singletonList("a0ef9c2d-6864-4a20-84ba-b66a666d2bf4"));
        req.setParams(mapParams);
        String reply = sendonBus(PersonVerticle.GET, req);

        Person person = Json.decodeValue(reply, Person.class);
       // System.out.println("BEFORE : " + person.getPhysicalFolder());

        // UPDATE
        req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.PUT);

		/* list of parameters */
        final JsonObject paramsBody = new JsonObject();
        paramsBody.putString(PersonVerticle.PARAM_PERSON_ID, "a0ef9c2d-6864-4a20-84ba-b66a666d2bf4");
        paramsBody.putString(PersonVerticle.PARAM_FEATURE_FOLDER_NAME, "physical");
        paramsBody.putString(PersonVerticle.PARAM_FEATURE_KEY, "endurance");
        paramsBody.putNumber(PersonVerticle.PARAM_FEATURE_VALUE, 1);
        req.setBody(paramsBody.encode());

		/* Call to verticle */
        reply = sendonBus(PersonVerticle.UPDATE_FEATURE, req);

        // AFTER
        // Verify if value is correctly set
        req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);

        req.setParams(mapParams);
        reply = sendonBus(PersonVerticle.GET, req);

        person = Json.decodeValue(reply, Person.class);
 //       System.out.println("AFTER : " + person.getPhysicalFolder());

        boolean found = false;
  /*      for (AttributePerson attributePerson : person.getPhysicalFolder()) {
            if ("endurance".equals(attributePerson.getKey())) {
                found = true;
                Assert.assertEquals(1, Integer.parseInt(attributePerson.getValue()));
                break;
            }
        }*/
        Assert.assertTrue("Feature not found", found);

    }
}
