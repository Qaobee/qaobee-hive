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

package com.qaobee.hive.test.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.guice.provides.MongoProvider;
import org.vertx.java.core.json.JsonObject;

/**
 * The type Guice test module.
 */
class GuiceTestModule extends AbstractModule {

    private JsonObject config;

    /**
     * Instantiates a new Guice test module.
     *
     * @param config the config
     */
    GuiceTestModule(JsonObject config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        bind(JsonObject.class).annotatedWith(Names.named("mongo.persistor")).toInstance(config.getObject("mongo.persistor"));
        bind(MongoDB.class).toProvider(MongoProvider.class).in(Singleton.class);
    }
}
