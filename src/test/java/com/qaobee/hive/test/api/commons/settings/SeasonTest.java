/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.test.api.commons.settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.api.v1.commons.settings.CountryVerticle;
import com.qaobee.hive.api.v1.commons.settings.SeasonVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

/**
 * @author cke
 *
 */
public class SeasonTest extends VertxJunitSupport {
	
	/**
	 * Tests getHandler for SeasonVerticle
	 */
	@Test
	public void getObjectByIdOk() {

		populate(POPULATE_ONLY, SETTINGS_SEASONS);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();
		
		// id
		params.put(SeasonVerticle.PARAM_ID, Collections.singletonList("559a9294889089a442f3d499"));
		req.setParams(params);

		final String reply = sendonBus(SeasonVerticle.GET, req, user.getAccount().getToken());
		JsonObject result = new JsonObject(reply);
		
		String label = result.getString("label");
		
		Assert.assertEquals("SAISON 2014-2015", label);
	}
	
	/**
	 * Tests getHandler for SeasonVerticle
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

		JsonObject resultUpdate = new JsonObject(sendonBus(SeasonVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));
		
		// id
		params.put(SeasonVerticle.PARAM_ID, Collections.singletonList(""));
		req.setParams(params);
		
		resultUpdate = new JsonObject(sendonBus(SeasonVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertTrue("Wrong format mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));

	}
	
	/**
	 * Tests getListHandler for SeasonVerticle
	 */
	@Test
	public void getListOk() {

		populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();
		
		// id
		params.put(SeasonVerticle.PARAM_COUNTRY_ID, Collections.singletonList((String)getCountry("CNTR-250-FR-FRA").getField(CountryVerticle.PARAM_ID)));
		params.put(SeasonVerticle.PARAM_ACTIVITY_ID, Collections.singletonList((String)getActivity("ACT-HAND",user).getField(ActivityVerticle.PARAM_ID)));
		req.setParams(params);

		final String reply = sendonBus(SeasonVerticle.GET_LIST_BY_ACTIVITY, req, user.getAccount().getToken());
		Assert.assertEquals(3, new JsonArray(reply).size());
	}
	
	/**
	 * Tests getCurrentHandler for SeasonVerticle
	 */
	@Test
	public void getCurrentSeason() {

		populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_SEASONS, SETTINGS_COUNTRY);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();
		
		// id
		params.put(SeasonVerticle.PARAM_COUNTRY_ID, Collections.singletonList((String)getCountry("CNTR-250-FR-FRA").getField(CountryVerticle.PARAM_ID)));
		params.put(SeasonVerticle.PARAM_ACTIVITY_ID, Collections.singletonList((String)getActivity("ACT-HAND",user).getField(ActivityVerticle.PARAM_ID)));
		req.setParams(params);

		final String reply = sendonBus(SeasonVerticle.GET_CURRENT, req, user.getAccount().getToken());
		JsonObject result = new JsonObject(reply);
		
		String label = result.getString("label");
		
		Assert.assertEquals("SAISON 2015-2016", label);
	}

}
