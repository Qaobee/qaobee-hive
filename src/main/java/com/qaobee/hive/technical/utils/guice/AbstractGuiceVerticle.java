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
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.jdeferred.Promise;

import javax.inject.Inject;

/**
 * The type Abstract guice verticle.
 */
public class AbstractGuiceVerticle extends AbstractVerticle {
    private static final String MONGO_CONF_KEY = "mongo.persistor";
    @Inject
    protected
    Utils utils;

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


    protected void replyJsonObject(Message<String> message, Promise<JsonObject, QaobeeException, Integer> promise) {
        promise.done(json -> {
            message.reply(json.encode());
        }).fail(e -> utils.sendError(message, e));
    }

    protected void replyJsonObjectJ(Message<JsonObject> message, Promise<JsonObject, QaobeeException, Integer> promise) {
        promise.done(json -> message.reply(json.encode())).fail(e -> utils.sendErrorJ(message, e));
    }

    protected void replyString(Message<String> message, Promise<String, QaobeeException, Integer> promise) {
        promise.done(message::reply).fail(e -> utils.sendError(message, e));
    }

    protected void replyJsonArray(Message<String> message, Promise<JsonArray, QaobeeException, Integer> promise) {
        promise.done(json -> message.reply(json.encode())).fail(e -> utils.sendError(message, e));
    }

    protected void replyStatus(Message<String> message, Promise<?, QaobeeException, Integer> promise) {
        promise.done(json -> utils.sendStatus(true, message)).fail(e -> utils.sendStatus(false, message));
    }

    protected void replyBoolean(Message<String> message, Promise<Boolean, QaobeeException, Integer> promise) {
        promise.done(r -> utils.sendStatus(r, message)).fail(e -> utils.sendStatus(false, message));
    }

    protected void replyBooleanJ(Message<JsonObject> message, Promise<Boolean, QaobeeException, Integer> promise) {
        promise.done(r -> utils.sendStatusJson(r, message)).fail(e -> utils.sendStatusJson(false, message));
    }
}
