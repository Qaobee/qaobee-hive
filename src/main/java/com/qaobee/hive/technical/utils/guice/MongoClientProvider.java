package com.qaobee.hive.technical.utils.guice;

import com.qaobee.hive.technical.utils.MongoClientCustom;
import com.qaobee.hive.technical.utils.impl.MongoClientCustomImpl;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import static io.vertx.ext.mongo.MongoClient.DEFAULT_POOL_NAME;

/**
 * The type Mongo client provider.
 */
public class MongoClientProvider implements Provider<MongoClientCustom> {
    @Inject
    @Named("mongo.db")
    private JsonObject config;
    @Inject
    private Vertx vertx;

    @Override
    public MongoClientCustom get() {
        return createShared(vertx, config);
    }

    private static MongoClientCustom createShared(Vertx vertx, JsonObject config) {
         return new MongoClientCustomImpl(vertx, config, DEFAULT_POOL_NAME);
    }
}
