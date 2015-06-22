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
package com.qaobee.hive.test.api.commons.referencial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.commons.referencial.StructureVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

/**
 * @author cke
 *
 */
public class StructureTest extends VertxJunitSupport {
	
	/**
	 * Tests getHandler for StructureVerticle
	 */
	@Test
	public void getObjectByIdOk() {

		populate(POPULATE_ONLY, DATA_STRUCTURE);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final HashMap<String, List<String>> params = new HashMap<String, List<String>>();
		
		// id
		params.put(StructureVerticle.PARAM_ID, Arrays.asList("541168295971d35c1f2d1b5e"));
		req.setParams(params);

		final String reply = sendonBus(StructureVerticle.GET, req, user.getAccount().getToken());
		JsonObject result = new JsonObject(reply);
		
		String label = result.getString("label");
		
		Assert.assertEquals("Dunkerque Handball", label);
	}
	
	/**
	 * Tests getHandler for StructureVerticle
	 * with missing mandatory fields
	 */
	@Test
	public void getObjectByIdKo() {

		populate(POPULATE_ONLY, DATA_STRUCTURE);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final HashMap<String, List<String>> params = new HashMap<String, List<String>>();
		
		JsonObject resultUpdate = new JsonObject(sendonBus(StructureVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));
		
		// id
		params.put(StructureVerticle.PARAM_ID, Arrays.asList(""));
		req.setParams(params);
		
		resultUpdate = new JsonObject(sendonBus(StructureVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertTrue("Wrong format mandatory parameters", resultUpdate.getString("message").contains("_id is mandatory"));

	}
	
	/**
	 * Tests updateHandler for StructureVerticle
	 */
	@Test
	public void getUpdateOk() {

		populate(POPULATE_ONLY, DATA_STRUCTURE);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final HashMap<String, List<String>> params = new HashMap<String, List<String>>();
		
		/* Retreive object */
		params.put(StructureVerticle.PARAM_ID, Arrays.asList("541168295971d35c1f2d1b5e"));
		req.setParams(params);

		final String reply = sendonBus(StructureVerticle.GET, req, user.getAccount().getToken());
		JsonObject result = new JsonObject(reply);
		
		String label = result.getString("label");
		Assert.assertEquals("Dunkerque Handball", label);
		
		/* Update object */
		req.setMethod(Constantes.POST);
		
		result.putString("label", "newValue");
		req.setBody(result.encode());

		final String reply2 = sendonBus(StructureVerticle.UPDATE, req, user.getAccount().getToken());
		JsonObject result2 = new JsonObject(reply2);
		label = result2.getString("label");
		Assert.assertEquals("newValue", label);
		
	}
	
	/**
	 * Tests updateHandler for StructureVerticle
	 * with missing mandatory fields
	 */
	@Test
	public void getUpdateKo() {

		populate(POPULATE_ONLY, DATA_STRUCTURE);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final HashMap<String, List<String>> params = new HashMap<String, List<String>>();
		
		/* Retreive object */
		params.put(StructureVerticle.PARAM_ID, Arrays.asList("541168295971d35c1f2d1b5e"));
		req.setParams(params);

		final String reply = sendonBus(StructureVerticle.GET, req, user.getAccount().getToken());
		JsonObject result = new JsonObject(reply);
		
		String label = result.getString("label");
		Assert.assertEquals("Dunkerque Handball", label);
		
		/* Update object */
		req.setMethod(Constantes.POST);
		
		result.putString("label", "newValue");
		result.putObject("country", null);
		req.setBody(result.encode());

		final JsonObject resultUpdate = new JsonObject(sendonBus(StructureVerticle.UPDATE, req, user.getAccount().getToken()));
		Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [country]"));
	}
	
	/**
	 * Tests addHandler for StructureVerticle
	 */
	@Test
	public void getAddOk() {
		
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
		params.putString("label", "labelValue");
		params.putString("acronym", "acronymValue");
		params.putObject(StructureVerticle.PARAM_COUNTRY, getCountry("CNTR-250-FR-FRA"));
		params.putObject(StructureVerticle.PARAM_ACTIVITY, getActivity("ACT-HAND"));
		
		req.setBody(params.encode());

		final JsonObject result = new JsonObject(sendonBus(StructureVerticle.ADD, req, user.getAccount().getToken()));
		String id = result.getString("_id");
		Assert.assertNotNull(id);
	}
	
	/**
	 * Tests addHandler for StructureVerticle
	 * with missing mandatory fields
	 */
	@Test
	public void getAddKo() {
		
		populate(POPULATE_ONLY, SETTINGS_ACTIVITY);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.POST);

		/* Generate a simple Structure Object */
		final JsonObject params = new JsonObject();
		params.putString("label", "labelValue");
		params.putString("acronym", "acronymValue");
		params.putObject(StructureVerticle.PARAM_ACTIVITY, getActivity("ACT-HAND"));

		req.setBody(params.encode());

		final JsonObject resultUpdate = new JsonObject(sendonBus(StructureVerticle.ADD, req, user.getAccount().getToken()));
		Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [country]"));
	}

	
}
