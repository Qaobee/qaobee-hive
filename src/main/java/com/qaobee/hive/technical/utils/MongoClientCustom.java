package com.qaobee.hive.technical.utils;

import io.vertx.ext.mongo.MongoClient;

/**
 * The interface Mongo client custom.
 */
public interface MongoClientCustom extends MongoClient {
    /**
     * Gets db.
     *
     * @return the db
     */
    com.mongodb.async.client.MongoClient getDB();
}
