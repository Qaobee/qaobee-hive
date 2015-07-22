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
package com.qaobee.hive.test.api.commons.referencial;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.commons.referencial.ChampionshipVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

/**
 * @author jerome
 *
 */
public class ChampionshipTest extends VertxJunitSupport {
	
	@Test
	public void getListChampionshipsTest() {
		populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
		User user = generateLoggedAdminUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.POST);
		final JsonObject params = new JsonObject();
		params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
		
		req.setBody(params.encode());
		final String reply = sendonBus(ChampionshipVerticle.GET_LIST, req, user.getAccount().getToken());
		Assert.assertEquals("getListChampionshipsTest", 2, new JsonArray(reply).size());
	}
	
	@Test
	public void getChampionshipTest() {
		populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
		User user = generateLoggedAdminUser();
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);
		final HashMap<String, List<String>> params = new HashMap<>();
		params.put(ChampionshipVerticle.PARAM_ID, Collections.singletonList("559ebfb499f07aa6f04dec76"));
		req.setParams(params);
		
		final JsonObject reply = new JsonObject(sendonBus(ChampionshipVerticle.GET, req, user.getAccount().getToken()));
		System.out.println(reply);
		Assert.assertEquals("getChampionshipByIdTest", "Championnat du bout du monde", reply.getString("label"));
	}

}
