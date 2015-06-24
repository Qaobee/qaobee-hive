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
package com.qaobee.hive.test.api.commons.settings;

import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
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
 * @author cke
 *
 */
public class ActivityTest extends VertxJunitSupport {
	
	/**
	 * Tests getHandler for ActivityVerticle
	 */
	@Test
	public void getObjectByIdOk() {

		populate(POPULATE_ONLY, SETTINGS_ACTIVITY);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();
		
		// id
		params.put(ActivityVerticle.PARAM_ID, Collections.singletonList("ACT-HAND"));
		req.setParams(params);

		final String reply = sendonBus(ActivityVerticle.GET, req, user.getAccount().getToken());
		JsonObject result = new JsonObject(reply);
		
		String label = result.getString("label");
		
		Assert.assertEquals("admin.settings.activity.handball.label", label);
	}
	
	/**
	 * Tests getHandler for ActivityVerticle
	 * with missing mandatory fields
	 */
	@Test
	public void getObjectByIdKo() {

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();

		JsonObject resultUpdate = new JsonObject(sendonBus(ActivityVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));
		
		// id
		params.put(ActivityVerticle.PARAM_ID, Collections.singletonList(""));
		req.setParams(params);
		
		resultUpdate = new JsonObject(sendonBus(ActivityVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertTrue("Wrong format mandatory parameters", resultUpdate.getString("message").contains("_id is mandatory"));

	}
	
	/**
	 * Tests getListHandler for ActivityVerticle
	 */
	@Test
	public void getListOk() {

		populate(POPULATE_ONLY, SETTINGS_ACTIVITY);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();
		
		// id
		params.put(ActivityVerticle.PARAM_ID, Collections.singletonList("ACT-HAND"));
		req.setParams(params);

		final String reply = sendonBus(ActivityVerticle.GET_LIST, req, user.getAccount().getToken());
		Assert.assertEquals(3, new JsonArray(reply).size());
	}
	
	/**
	 * Tests getListEnableHandler for ActivityVerticle
	 */
	@Test
	public void getListEnableOk() {

		populate(POPULATE_ONLY, SETTINGS_ACTIVITY);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();
		
		// id
		params.put(ActivityVerticle.PARAM_ID, Collections.singletonList("ACT-HAND"));
		req.setParams(params);

		final String reply = sendonBus(ActivityVerticle.GET_LIST_ENABLE, req, user.getAccount().getToken());
		Assert.assertEquals(2, new JsonArray(reply).size());
	}
}
