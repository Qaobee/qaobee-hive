/*
 * __________________
 *   Qaobee
 * __________________
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

import com.qaobee.hive.api.v1.commons.referencial.EventVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class EventTest extends VertxJunitSupport {

	@Test
	public void getListEventTest() {
		populate(POPULATE_ONLY, SETTINGS_EVENT_HAND);

		/* test based on script mongo */
		final RequestWrapper req = new RequestWrapper();
		req.setLocale(LOCALE);
		req.setMethod(Constantes.POST);

		/* list of parameters */
		final JsonObject params = new JsonObject();

		params.putString(EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
		params.putString(EventVerticle.PARAM_EVENT_TYPE, "championship");

		/* start and end Date */
		params.putNumber(EventVerticle.PARAM_START_DATE, 1429117200000l);
		params.putNumber(EventVerticle.PARAM_END_DATE, 1435615200000l);

		/* ListId owner of event */
		params.putArray(EventVerticle.PARAM_EVENT_OWNER, new JsonArray(new String[] { "TEAM-DUNKERQUE" }));

		req.setBody(params.encode());

		/* Call to verticle */
		final String reply = sendonBus(EventVerticle.GET_LIST, req);
		Assert.assertEquals(1, new JsonArray(reply).size());

	}

}
