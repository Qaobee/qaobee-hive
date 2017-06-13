package com.qaobee.hive.technical.utils.guice;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

/**
 * The type Mongo client provider.
 */
public class MongoClientProvider implements Provider<MongoClient> {
    @Inject
    @Named("mongo.db")
    private JsonObject config;
    @Inject
    private Vertx vertx;

    @Override
    public MongoClient get() {
        return MongoClient.createShared(vertx, config);
    }
}
