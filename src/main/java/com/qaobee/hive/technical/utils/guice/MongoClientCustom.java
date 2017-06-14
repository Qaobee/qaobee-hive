package com.qaobee.hive.technical.utils.guice;

import io.vertx.ext.mongo.MongoClient;

/**
 * Created by b3605 on 14/06/17.
 *
 * @author Xavier MARIN (b3605)
 */
public interface MongoClientCustom extends MongoClient {
    /**
     * Gets db.
     *
     * @return the db
     */
    com.mongodb.async.client.MongoClient getDB();
}
