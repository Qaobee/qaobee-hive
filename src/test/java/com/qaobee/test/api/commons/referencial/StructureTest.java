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
package com.qaobee.test.api.commons.referencial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;

import com.qaobee.api.v1.commons.referencial.StructureVerticle;
import com.qaobee.technical.constantes.Constantes;
import com.qaobee.technical.vertx.RequestWrapper;
import com.qaobee.test.config.VertxJunitSupport;

/**
 * @author cke
 *
 */
public class StructureTest extends VertxJunitSupport {
	
	/**
	 * Tests getHandler for StructureVerticle
	 */
	@Test
	public void getStructure() {

		populate(POPULATE_ONLY, SETTINGS_STRUCTURE);

		/* test based on script mongo */
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.GET);

		final HashMap<String, List<String>> params = new HashMap<String, List<String>>();
		
		// id
		params.put(StructureVerticle.PARAM_ID, Arrays.asList("541168295971d35c1f2d1b5e"));
		req.setParams(params);

		final String reply = sendonBus(StructureVerticle.GET, req);
		Assert.assertEquals(1, new JsonArray(reply).size());
	}
}
