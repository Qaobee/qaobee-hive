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
package com.qaobee.hive.test.api.sandbox.effective;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.sandbox.effective.EffectiveVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

/**
 * @author cke
 *
 */
public class EffectiveTest extends VertxJunitSupport {
	
	/**
	 * Tests of getting a list of members of effective for one category
	 */
	@Test
	public void getListMembersByCategory() {

		populate(POPULATE_ONLY, DATA_USERS, DATA_EFFECTIVE_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

		/* list of parameters */
        final Map<String, List<String>> params = new HashMap<>();
		
		params.put(EffectiveVerticle.PARAM_SANDBOXCFG_ID, Collections.singletonList("558b0fc0bd2e39cdab651e21"));
		params.put(EffectiveVerticle.PARAM_CATEGORY_AGE_CODE, Collections.singletonList("sen"));
		req.setParams(params);

		final String reply = sendonBus(EffectiveVerticle.GET_LIST, req, user.getAccount().getToken());
		JsonArray result = new JsonArray(reply);

		Assert.assertEquals(1, result.size());
		
		JsonObject itemOne = result.get(0);
		JsonArray members = itemOne.getArray("members");

		Assert.assertEquals(16, members.size());
	}
	
	/**
	 * Tests of getting a list of all members for one sandbox config
	 */
	@Test
	public void getListMembersBySandBoxCfgId() {

		populate(POPULATE_ONLY, DATA_USERS, DATA_EFFECTIVE_FOOT);
        User user = generateLoggedUser("54160977d5bd065a1bb1e565");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        final Map<String, List<String>> params = new HashMap<>();
		
		params.put(EffectiveVerticle.PARAM_SANDBOXCFG_ID, Collections.singletonList("559d268318e3cb71c60d9649"));
		req.setParams(params);


		final String reply = sendonBus(EffectiveVerticle.GET_LIST, req, user.getAccount().getToken());
		JsonArray result = new JsonArray(reply);

		Assert.assertEquals(2, result.size());
		
		JsonObject itemOne = result.get(0);
		JsonArray members = itemOne.getArray("members");

		Assert.assertEquals(24, members.size());
		
		JsonObject itemTwo = result.get(1);
		members = itemTwo.getArray("members");

		Assert.assertEquals(23, members.size());
	}
}
