package com.qaobee.technical.vertx.utils.guice.provides;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Named;

import org.vertx.java.core.json.JsonObject;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.qaobee.swarn.technical.mongo.MongoDB;
import com.qaobee.swarn.technical.mongo.impl.MongoDBImpl;

/**
 * Created by xavier on 09/11/14.
 */
public class MongoProvider implements Provider<MongoDB> {
	@Inject
	@Named("mongo.persistor")
	private JsonObject config;
	private MongoDB mongo;
	private final AtomicBoolean mongoInitialized = new AtomicBoolean(false);

	@Override
	public MongoDB get() {
		if (!mongoInitialized.getAndSet(true)) {
			try {
				mongo = new MongoDBImpl(config);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return mongo;
	}
}
