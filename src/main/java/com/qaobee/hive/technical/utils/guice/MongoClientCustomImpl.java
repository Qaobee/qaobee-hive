package com.qaobee.hive.technical.utils.guice;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.impl.MongoClientImpl;

/**
 * Created by b3605 on 14/06/17.
 *
 * @author Xavier MARIN (b3605)
 */
public class MongoClientCustomImpl extends MongoClientImpl implements MongoClientCustom {

    MongoClientCustomImpl(Vertx vertx, JsonObject config, String dataSourceName) {
        super(vertx, config, dataSourceName);
    }

    @Override
    public com.mongodb.async.client.MongoClient getDB() {
        return super.mongo;
    }
}
