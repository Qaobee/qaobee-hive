/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
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

import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.api.v1.commons.settings.CountryVerticle;
import com.qaobee.hive.api.v1.commons.settings.IndicatorVerticle;
import com.qaobee.hive.api.v1.commons.settings.SeasonVerticle;
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
public class IndicatorTest extends VertxJunitSupport {

	/**
	 * Tests getHandler for IndicatorVerticle
	 */
	@Test public void getObjectByIdOk() {

		populate(POPULATE_ONLY, SETTINGS_INDICATOR);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();

		// id
		params.put(IndicatorVerticle.PARAM_ID, Collections.singletonList("559a9294889089a442f3d464"));
		req.setParams(params);

		final String reply = sendonBus(IndicatorVerticle.GET, req, user.getAccount().getToken());
		JsonObject result = new JsonObject(reply);

		String label = result.getString("code");

		Assert.assertEquals("hightPerson", label);
	}

	/**
	 * Tests getHandler for IndicatorVerticle
	 * with missing mandatory fields
	 */
	@Test public void getObjectByIdKo() {

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);

		final Map<String, List<String>> params = new HashMap<>();

		JsonObject resultUpdate = new JsonObject(sendonBus(IndicatorVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));

		// id
		params.put(SeasonVerticle.PARAM_ID, Collections.singletonList(""));
		req.setParams(params);

		resultUpdate = new JsonObject(sendonBus(IndicatorVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertTrue("Wrong format mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));

	}

	/**
	 * Tests getList for IndicatorVerticle
	 */
	@Test public void getListOk() {
		populate(POPULATE_ONLY, SETTINGS_INDICATOR, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);

		/* User simulation connection */
		User user = generateLoggedUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.POST);

		final JsonObject params = new JsonObject();

		// Aggregat

		params.putString(IndicatorVerticle.PARAM_COUNTRY_ID, (String) getCountry("CNTR-250-FR-FRA").getField(CountryVerticle.PARAM_ID));
		params.putString(IndicatorVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-HAND", user).getField(ActivityVerticle.PARAM_ID));
		params.putArray(IndicatorVerticle.PARAM_SCREEN, new JsonArray(new String[] { "COLLECTE" }));
		req.setBody(params.encode());

		final String reply = sendonBus(IndicatorVerticle.GET_LIST, req, user.getAccount().getToken());
		Assert.assertEquals(45, new JsonArray(reply).size());

	}

}
