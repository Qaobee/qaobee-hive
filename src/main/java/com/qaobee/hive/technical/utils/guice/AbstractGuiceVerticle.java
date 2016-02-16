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

package com.qaobee.hive.technical.utils.guice;

import com.englishtown.promises.When;
import com.englishtown.vertx.promises.WhenEventBus;
import com.englishtown.vertx.promises.impl.DefaultWhenContainer;
import com.englishtown.vertx.promises.impl.DefaultWhenEventBus;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

/**
 * The type Abstract guice verticle.
 */
public class AbstractGuiceVerticle extends Verticle {

	protected When<String, Void> when;
	protected WhenEventBus whenEventBus;
	protected DefaultWhenContainer whenContainer;

	/**
	 * Start void.
	 */
	@Override public void start() {
		Injector injector = Guice.createInjector(new GuiceModule(container.config()));
		injector.injectMembers(this);
		when = new When<>();
		whenEventBus = new DefaultWhenEventBus(vertx, container);
		whenContainer = new DefaultWhenContainer(container);
	}

	/**
	 * Start timer for response time (<a href="http://metrics.codahale.com/getting-started/#timers">Reference</a>).
	 *
	 * @param name timer's name
	 */
	protected void startTimer(final String name) {
		final JsonObject metric = new JsonObject();
		metric.putString("name", "timer." + name);
		metric.putString("action", "start");
		vertx.eventBus().send("metrix", metric);
	}

	/**
	 * stop time (<a href="http://metrics.codahale.com/getting-started/#timers">Reference</a>).
	 *
	 * @param name timer's name
	 */
	protected void stopTimer(final String name) {
		final JsonObject metric = new JsonObject();
		metric.putString("name", "timer." + name);
		metric.putString("action", "stop");
		vertx.eventBus().send("metrix", metric);
	}
}
