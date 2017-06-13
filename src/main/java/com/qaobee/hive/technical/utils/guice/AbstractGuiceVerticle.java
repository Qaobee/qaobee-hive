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

package com.qaobee.hive.technical.utils.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.AbstractVerticle;
import org.apache.commons.lang.StringUtils;

/**
 * The type Abstract guice verticle.
 */
public class AbstractGuiceVerticle extends AbstractVerticle {
    private static final String MONGO_CONF_KEY = "mongo.persistor";

    /**
     * Start void.
     */
    @Override
    public void start() {
        if (StringUtils.isNotBlank(System.getenv("OPENSHIFT_MONGODB_DB_HOST"))) {
            config().getJsonObject(MONGO_CONF_KEY).put("host", System.getenv("OPENSHIFT_MONGODB_DB_HOST"));
            config().getJsonObject(MONGO_CONF_KEY).put("port", Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT")));
            config().getJsonObject(MONGO_CONF_KEY).put("password", System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD"));
            config().getJsonObject(MONGO_CONF_KEY).put("username", System.getenv("OPENSHIFT_MONGODB_DB_USERNAME"));
        }
        Injector injector = Guice.createInjector(new GuiceModule(this.config(), vertx));
        injector.injectMembers(this);
    }
}
