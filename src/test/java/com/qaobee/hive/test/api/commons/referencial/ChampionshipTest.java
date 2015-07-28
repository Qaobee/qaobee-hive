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
		// Populate default value
		populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
		// User connected
		User user = generateLoggedAdminUser();
		
		// Request
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.POST);

		// Params
		final JsonObject params = new JsonObject();
		params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
		params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
		params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
		
		req.setBody(params.encode());
		
		// Send request
		final String reply = sendonBus(ChampionshipVerticle.GET_LIST, req, user.getAccount().getToken());
		Assert.assertEquals("getListChampionshipsTest", 1, new JsonArray(reply).size());
	}
	
	@Test
	public void getListChampionshipsWithInfraTest() {
		// Populate default value
		populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
		// User connected
		User user = generateLoggedAdminUser();
		
		// Request
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.POST);

		// Params
		final JsonObject params = new JsonObject();
		params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
		params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
		params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
		
		JsonObject paramParticipant = new JsonObject();
		paramParticipant.putString("id", "ID-PHARE-CHAMBERY");
		paramParticipant.putString("type", "infrastructure");
		params.putObject(ChampionshipVerticle.PARAM_PARTICIPANT, paramParticipant);
		
		req.setBody(params.encode());
		
		// Send request
		final String reply = sendonBus(ChampionshipVerticle.GET_LIST, req, user.getAccount().getToken());
		Assert.assertEquals("getListChampionshipsTest", 1, new JsonArray(reply).size());
	}
	
	@Test
	public void getListChampionshipsWithInfraUnknownTest() {
		// Populate default value
		populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
		// User connected
		User user = generateLoggedAdminUser();
		
		// Request
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.POST);

		// Params
		final JsonObject params = new JsonObject();
		params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
		params.putString(ChampionshipVerticle.PARAM_CATEGORY_AGE, "sen");
		params.putString(ChampionshipVerticle.PARAM_STRUCTURE, "541168295971d35c1f2d1b5e"); // Structure : CESSON
		
		JsonObject paramParticipant = new JsonObject();
		paramParticipant.putString("id", "ID-Anywhere-But-Not-There");
		paramParticipant.putString("type", "infrastructure");
		params.putObject(ChampionshipVerticle.PARAM_PARTICIPANT, paramParticipant);
		
		req.setBody(params.encode());
		
		// Send request
		final String reply = sendonBus(ChampionshipVerticle.GET_LIST, req, user.getAccount().getToken());
		Assert.assertEquals("getListChampionshipsTest", 0, new JsonArray(reply).size());
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
		Assert.assertEquals("getChampionshipByIdTest", "Championnat du bout du monde", reply.getString("label"));
	}
	
	@Test
	public void addChampionshipTest() {
		User user = generateLoggedAdminUser();
		RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.POST);
		
		final JsonObject params = new JsonObject();
		params.putString(ChampionshipVerticle.PARAM_LABEL, "Mon championnat");
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.putString("code", "R");
		jsonObject.putString("label", "regional");
		params.putObject(ChampionshipVerticle.PARAM_LEVEL_GAME, jsonObject);
		
		params.putString(ChampionshipVerticle.PARAM_SUB_LEVEL_GAME, "Honneur regional");
		params.putString(ChampionshipVerticle.PARAM_POOL, "Cocotte");
		params.putString(ChampionshipVerticle.PARAM_INSTANCE, "Ligue du Boukhistan");
		params.putString(ChampionshipVerticle.PARAM_ACTIVITY, "ACT-HAND");
		
		jsonObject = new JsonObject();
		jsonObject.putString("code", "sen");
		jsonObject.putString("label", "senior");
		jsonObject.putNumber("ageMax", 34);
		jsonObject.putNumber("ageMin", 20);
		jsonObject.putString("genre", "gender.male");
		params.putObject(ChampionshipVerticle.PARAM_CATEGORY_AGE, jsonObject);
		
		params.putString(ChampionshipVerticle.PARAM_SEASON_CODE, "SAI-2014");
		
		jsonObject = new JsonObject();
		jsonObject.putString("id", "mon-id");
		jsonObject.putString("name", "participantName");
		jsonObject.putString("structureId", "participantStructureId");
		jsonObject.putString("type", "team");
		params.putArray(ChampionshipVerticle.PARAM_LIST_PARTICIPANTS, new JsonArray().addObject(jsonObject));
		
		req.setBody(params.encode());
		
		final JsonObject result = new JsonObject(sendonBus(ChampionshipVerticle.ADD, req, user.getAccount().getToken()));
		Assert.assertNotNull("id is null", result.getString("_id"));
		
		// Test si donnée présente et accessible
		req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);
		final HashMap<String, List<String>> params2 = new HashMap<>();
		params2.put(ChampionshipVerticle.PARAM_ID, Collections.singletonList(result.getString("_id")));
		req.setParams(params2);
		
		final JsonObject reply = new JsonObject(sendonBus(ChampionshipVerticle.GET, req, user.getAccount().getToken()));
		System.out.println(reply);
		Assert.assertEquals("addChampionshipTest", "Mon championnat", reply.getString("label"));
	}
	
	@Test
	public void updateChampionshipTest() {
		populate(POPULATE_ONLY, DATA_CHAMPIONSHIP_HAND);
		User user = generateLoggedAdminUser();
		RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);
		HashMap<String, List<String>> params = new HashMap<>();
		params.put(ChampionshipVerticle.PARAM_ID, Collections.singletonList("559ebfb499f07aa6f04dec76"));
		req.setParams(params);
		
		JsonObject reply = new JsonObject(sendonBus(ChampionshipVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertEquals("updateChampionshipTest", "Championnat du bout du monde", reply.getString("label"));
		
		// Return the _id to "id" param
		reply.putString(ChampionshipVerticle.PARAM_ID, reply.getString("_id"));
		
		// Update a field
		final String newLabel = "Autre";
		reply.putString("label", newLabel);
		
		// Prepare update request
		req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.POST);
		req.setBody(reply.encode());
		
		reply = new JsonObject(sendonBus(ChampionshipVerticle.UPDATE, req, user.getAccount().getToken()));
		Assert.assertEquals("updateChampionshipTest - result update", newLabel, reply.getString("label"));
		
		
		// Just to see if it's really updated
		req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setUser(user);
		req.setMethod(Constantes.GET);
		params = new HashMap<>();
		params.put(ChampionshipVerticle.PARAM_ID, Collections.singletonList("559ebfb499f07aa6f04dec76"));
		req.setParams(params);
		
		reply = new JsonObject(sendonBus(ChampionshipVerticle.GET, req, user.getAccount().getToken()));
		Assert.assertEquals("updateChampionshipTest - get check", newLabel, reply.getString("label"));
	}

}
