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

import com.qaobee.hive.api.v1.commons.settings.CountryVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.*;

/**
 * @author cke
 *
 */
public class CountryTest extends VertxJunitSupport {
	
	/**
	 * Tests get for CountryVerticle
	 */
	@Test
	public void getObjectByIdOk() {

		populate(POPULATE_ONLY, SETTINGS_COUNTRY);

		/* test based on script mongo */
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();
		
		// id
		params.put(CountryVerticle.PARAM_ID, Collections.singletonList("CNTR-250-FR-FRA"));
		req.setParams(params);

		final String reply = sendonBus(CountryVerticle.GET, req);
		JsonObject result = new JsonObject(reply);
		
		String label = result.getString("label");
		
		Assert.assertEquals("France", label);
	}
	
	/**
	 * Tests get for CountryVerticle
	 * with missing mandatory fields
	 */
	@Test
	public void getObjectByIdKo() {

		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();
		
		JsonObject resultUpdate = new JsonObject(sendonBus(CountryVerticle.GET, req));
		Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));
		
		// id
		params.put(CountryVerticle.PARAM_ID, Collections.singletonList(""));
		req.setParams(params);
		
		resultUpdate = new JsonObject(sendonBus(CountryVerticle.GET, req));
		Assert.assertTrue("Wrong format mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));

	}
	
	/**
	 * Tests getList for CountryVerticle
	 */
	@Test
	public void getListAll() {

		populate(POPULATE_ONLY, SETTINGS_COUNTRY);
		
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.GET);
		
		final HashMap<String, List<String>> params = new HashMap<>();
		params.put(CountryVerticle.PARAM_LOCAL, Collections.singletonList("fr"));
		req.setParams(params);
		
		JsonArray result = new JsonArray(sendonBus(CountryVerticle.GET_LIST, req));
		Assert.assertEquals(202,result.size());

	}
	
	/**
	 * Tests getList for CountryVerticle
	 */
	@Test
	public void getListFilter() {

		populate(POPULATE_ONLY, SETTINGS_COUNTRY);
		
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();
		
		// label
		params.put(CountryVerticle.PARAM_LOCAL, Collections.singletonList("fr"));
		params.put(CountryVerticle.PARAM_LABEL, Collections.singletonList("France"));
		req.setParams(params);
		
		JsonArray result = new JsonArray(sendonBus(CountryVerticle.GET_LIST, req));
		Assert.assertEquals(1,result.size());

	}

}
