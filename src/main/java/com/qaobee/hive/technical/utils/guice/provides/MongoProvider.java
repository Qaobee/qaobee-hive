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

package com.qaobee.hive.technical.utils.guice.provides;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.mongo.impl.MongoDBImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Named;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The type Mongo provider.
 */
public class MongoProvider implements Provider<MongoDB> {

	private static final Logger LOG = LoggerFactory.getLogger(MongoProvider.class);
	private final AtomicBoolean mongoInitialized = new AtomicBoolean(false);
	@Inject @Named("mongo.persistor") private JsonObject config;
	private MongoDB mongo;

	@Override public MongoDB get() {
		if (!mongoInitialized.getAndSet(true)) {
			try {
				mongo = new MongoDBImpl(config);
			} catch (UnknownHostException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return mongo;
	}
}
