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
package com.qaobee.hive.test.api.commons.user;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

/**
 * The Class LoginTest.
 *
 * @author cke
 */
public class UserTest extends VertxJunitSupport {

	/**
     * Test Login OK.
     */
	@Test
	public void loginOk() {

		populate(POPULATE_ONLY, DATA_USERS);

		/* test based on script mongo */
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.POST);
		
		final JsonObject params = new JsonObject();
		params.putString(UserVerticle.PARAM_LOGIN, "ccaft");
		params.putString(UserVerticle.PARAM_PWD, "toto");
		req.setBody(params.encode());

		final String reply = sendonBus(UserVerticle.LOGIN, req);
		JsonObject result = new JsonObject(reply);
		
		String name = result.getString("name");
		
		Assert.assertEquals("Hidalgo", name);
	}

	/**
     * Test Login with badlogin.
     */
	@Test
	public void loginKo() {

		populate(POPULATE_ONLY, DATA_USERS);

		/* test based on script mongo */
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.POST);
		
		final JsonObject params = new JsonObject();
		params.putString(UserVerticle.PARAM_LOGIN, "badlogin");
		params.putString(UserVerticle.PARAM_PWD, "toto");
		req.setBody(params.encode());

		final String reply = sendonBus(UserVerticle.LOGIN, req);
		JsonObject result = new JsonObject(reply);
		Assert.assertTrue("Wrong login", result.getString("message").contains("Bad login"));
	}
	
	/**
     * Test Login with bad password.
     */
	@Test
	public void loginPasswordKo() {

		populate(POPULATE_ONLY, DATA_USERS);

		/* test based on script mongo */
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.POST);
		
		final JsonObject params = new JsonObject();
		params.putString(UserVerticle.PARAM_LOGIN, "ccaft");
		params.putString(UserVerticle.PARAM_PWD, "tutu");
		req.setBody(params.encode());

		final String reply = sendonBus(UserVerticle.LOGIN, req);
		JsonObject result = new JsonObject(reply);
		Assert.assertTrue("Wrong login", result.getString("message").contains("Bad login"));
	}
	
	/**
     * Test login user inactive.
     */
	@Test
	public void loginUserInactive() {

		populate(POPULATE_ONLY, DATA_USERS);

		/* test based on script mongo */
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.POST);
		
		final JsonObject params = new JsonObject();
		params.putString(UserVerticle.PARAM_LOGIN, "badlogin");
		params.putString(UserVerticle.PARAM_PWD, "toto");
		req.setBody(params.encode());

		final String reply = sendonBus(UserVerticle.LOGIN, req);
		JsonObject result = new JsonObject(reply);
		Assert.assertTrue("Wrong login", result.getString("message").contains("Bad login"));
	}

}
