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

package com.qaobee.hive.test.api.commons.referencial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.commons.referencial.EventVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

public class EventTest extends VertxJunitSupport {

	@Test
	public void getListEventTest() {
		populate(POPULATE_ONLY, DATA_EVENT_HAND);
		
		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);

		/* test based on script mongo */
		req.setMethod(Constantes.POST);

		/* list of parameters */
		final JsonObject params = new JsonObject();
		

		params.putString(EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
		params.putString(EventVerticle.PARAM_LINK_TYPE, "championship");

		/* start and end Date */
		params.putNumber(EventVerticle.PARAM_START_DATE, 1428518700000l);
		params.putNumber(EventVerticle.PARAM_END_DATE, 1435615200000l);

		/* ListId owner of event */
		params.putArray(EventVerticle.PARAM_OWNER, new JsonArray(new String[] { "ID-TEAM-DUNKERQUE" }));

		req.setBody(params.encode());

		/* Call to verticle */
		final String reply = sendonBus(EventVerticle.GET_LIST, req, user.getAccount().getToken());
		Assert.assertEquals(1, new JsonArray(reply).size());

	}
	
	@Test
	public void getObjectByIdOk() {
		populate(POPULATE_ONLY, DATA_EVENT_HAND);
		
		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);

		/* test based on script mongo */
		req.setMethod(Constantes.GET);

		/* list of parameters */
		final HashMap<String, List<String>> params = new HashMap<String, List<String>>();
		
		/* Retreive object */
		params.put(EventVerticle.PARAM_ID, Arrays.asList("55847ed0d040353767a48e71"));
		req.setParams(params);

		/* Call to verticle */
		final String reply = sendonBus(EventVerticle.GET, req, user.getAccount().getToken());
		JsonObject result = new JsonObject(reply);
		
		String label = result.getString("label");
		Assert.assertEquals("Championnat : Journée 21", label);

	}
	
	/**
	 * Tests addHandler for EventVerticle
	 */
	@Test
	public void getAddKO() {
		
		populate(POPULATE_ONLY, SETTINGS_ACTIVITY);
		populate(POPULATE_ONLY, SETTINGS_COUNTRY);
		
		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.POST);
		

		/* Generate a simple Structure Object */
		final JsonObject params = new JsonObject();
		params.putString(EventVerticle.PARAM_LABEL, "labelValue");
		params.putString(EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
		params.putString(EventVerticle.PARAM_SEASON_CODE, "SAI-2014");
		
		req.setBody(params.encode());

		final JsonObject result = new JsonObject(sendonBus(EventVerticle.ADD, req, user.getAccount().getToken()));
		Assert.assertTrue("Missing mandatory parameters", result.getString("message").contains("Missing mandatory parameters : [categoryAge, owner, startDate]"));
	}

}
