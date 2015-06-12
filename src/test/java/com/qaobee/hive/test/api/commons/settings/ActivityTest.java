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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.commons.referencial.StructureVerticle;
import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

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

		/* test based on script mongo */
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.GET);

		final HashMap<String, List<String>> params = new HashMap<String, List<String>>();
		
		// id
		params.put(ActivityVerticle.PARAM_ID, Arrays.asList("ACT-HAND"));
		req.setParams(params);

		final String reply = sendonBus(ActivityVerticle.GET, req);
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

		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.GET);

		final HashMap<String, List<String>> params = new HashMap<String, List<String>>();
		
		JsonObject resultUpdate = new JsonObject(sendonBus(ActivityVerticle.GET, req));
		Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));
		
		// id
		params.put(ActivityVerticle.PARAM_ID, Arrays.asList(""));
		req.setParams(params);
		
		resultUpdate = new JsonObject(sendonBus(ActivityVerticle.GET, req));
		Assert.assertTrue("Wrong format mandatory parameters", resultUpdate.getString("message").contains("_id is mandatory"));

	}
}
