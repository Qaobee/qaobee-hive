package com.qaobee.hive.technical.utils.impl;

import com.qaobee.hive.technical.utils.MongoClientCustom;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.impl.MongoClientImpl;

/**
 * The type Mongo client custom.
 */
public class MongoClientCustomImpl extends MongoClientImpl implements MongoClientCustom {

    /**
     * Instantiates a new Mongo client custom.
     *
     * @param vertx          the vertx
     * @param config         the config
     * @param dataSourceName the data source name
     */
    public MongoClientCustomImpl(Vertx vertx, JsonObject config, String dataSourceName) {
        super(vertx, config, dataSourceName);
    }

    @Override
    public com.mongodb.async.client.MongoClient getDB() {
        return super.mongo;
    }
}
