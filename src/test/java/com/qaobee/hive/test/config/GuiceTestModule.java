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
import com.qaobee.hive.services.ActivityService;
import com.qaobee.hive.services.CountryService;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.mongo.impl.MongoDBImpl;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import com.qaobee.hive.technical.utils.guice.MongoClientProvider;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * The type Guice test module.
 */
class GuiceTestModule extends AbstractModule {

    private final Vertx vertx;
    private JsonObject config;

    /**
     * Instantiates a new Guice test module.
     *
     * @param config the config
     */
    GuiceTestModule(JsonObject config, Vertx vertx) {
        this.config = config;
        this.vertx = vertx;
    }

    @Override
    protected void configure() {
        bind(Vertx.class).toInstance(vertx);
        bind(JsonObject.class).annotatedWith(Names.named("mongo.db")).toInstance(config.getJsonObject("mongo.db"));
        bind(MongoDB.class).to(MongoDBImpl.class).in(Singleton.class);
        bind(MongoClientCustom.class).toProvider(MongoClientProvider.class).asEagerSingleton();

        bind(ActivityService.class).toInstance(ActivityService.createProxy(vertx, ActivityService.ADDRESS));
        bind(CountryService.class).toInstance(CountryService.createProxy(vertx, CountryService.ADDRESS));
    }
}
