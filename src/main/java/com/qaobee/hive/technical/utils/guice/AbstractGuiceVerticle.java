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
import com.qaobee.hive.dao.Utils;
import io.vertx.core.*;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract guice verticle.
 */
public class AbstractGuiceVerticle extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractGuiceVerticle.class);
    private static final String MONGO_CONF_KEY = "mongo.db";
    /**
     * The Utils.
     */
    @Inject
    protected Utils utils;
    private List<Future> promises = new ArrayList<>();
    /**
     * The Injector.
     */
    protected Injector injector;

    // Optional - called when verticle is undeployed
    public void stop(Future<Void> startFuture) {
        LOG.info(this.getClass().getName() + " stoped");
        startFuture.complete();
    }


    /**
     * Inject abstract guice verticle.
     *
     * @param clazz the clazz
     *
     * @return the abstract guice verticle
     */
    public AbstractGuiceVerticle inject(AbstractGuiceVerticle clazz) {
        if (StringUtils.isNotBlank(System.getenv("OPENSHIFT_MONGODB_DB_HOST"))) {
            config().getJsonObject(MONGO_CONF_KEY).put("host", System.getenv("OPENSHIFT_MONGODB_DB_HOST"));
            config().getJsonObject(MONGO_CONF_KEY).put("port", Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT")));
            config().getJsonObject(MONGO_CONF_KEY).put("password", System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD"));
            config().getJsonObject(MONGO_CONF_KEY).put("username", System.getenv("OPENSHIFT_MONGODB_DB_USERNAME"));
        }
        injector = Guice.createInjector(new GuiceModule(this.config(), vertx));
        injector.injectMembers(this);
        LOG.debug(clazz.getClass().getName() + " started");
        return this;
    }

    /**
     * Handle json handler.
     *
     * @param message the message
     *
     * @return the handler
     */
    protected Handler<AsyncResult<JsonObject>> handleJson(Message<String> message) {
        return res -> {
            if (res.succeeded()) {
                message.reply(res.result().encode());
            } else {
                utils.sendError(message, (QaobeeException) res.cause());
            }
        };
    }

    /**
     * Handle json j handler.
     *
     * @param message the message
     *
     * @return the handler
     */
    protected Handler<AsyncResult<JsonObject>> handleJsonJ(Message<JsonObject> message) {
        return res -> {
            if (res.succeeded()) {
                message.reply(res.result());
            } else {
                utils.sendErrorJ(message, (QaobeeException) res.cause());
            }
        };
    }

    /**
     * Handle json array handler.
     *
     * @param message the message
     *
     * @return the handler
     */
    protected Handler<AsyncResult<JsonArray>> handleJsonArray(Message<String> message) {
        return res -> {
            if (res.succeeded()) {
                message.reply(res.result().encode());
            } else {
                utils.sendError(message, (QaobeeException) res.cause());
            }
        };
    }

    /**
     * Handle boolean handler.
     *
     * @param message the message
     *
     * @return the handler
     */
    protected Handler<AsyncResult<Boolean>> handleBoolean(Message<String> message) {
        return res -> {
            if (res.succeeded()) {
                utils.sendStatus(res.result(), message);
            } else {
                utils.sendStatus(false, message);
            }
        };
    }

    /**
     * Add abstract guice verticle.
     *
     * @param <T>     the type parameter
     * @param address the address
     * @param handler the handler
     *
     * @return the abstract guice verticle
     */
    public <T> AbstractGuiceVerticle add(String address, Handler<Message<T>> handler) {
        Future<Boolean> deferred = Future.future();
        vertx.eventBus().consumer(address, handler).completionHandler(ar -> {
            if (ar.succeeded()) {
                deferred.complete(true);
            } else {
                deferred.fail(ar.cause());
            }
        });
        promises.add(deferred);
        return this;
    }

    /**
     * Register.
     *
     * @param startFuture the start future
     */
    public void register(Future<Void> startFuture) {
        CompositeFuture.all(promises).setHandler(rs -> {
            if (rs.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(rs.cause());
            }
        });
    }


    /**
     * Reply json object.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyJsonObject(Message<String> message, Future<JsonObject> promise) {
        promise.setHandler(res -> {
            if (res.succeeded()) {
                message.reply(res.result());
            } else {
                utils.sendError(message, (QaobeeException) res.cause());
            }
        });
    }

    /**
     * Reply json object j.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyJsonObjectJ(Message<JsonObject> message, Future<JsonObject> promise) {
        promise.setHandler(res -> {
            if (res.succeeded()) {
                message.reply(res.result().encode());
            } else {
                utils.sendErrorJ(message, (QaobeeException) res.cause());
            }
        });
    }

    /**
     * Reply json array.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyJsonArray(Message<String> message, Future<JsonArray> promise) {
        promise.setHandler(res -> {
            if (res.succeeded()) {
                message.reply(res.result().encode());
            } else {
                utils.sendError(message, (QaobeeException) res.cause());
            }
        });
    }

    /**
     * Reply boolean.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyBoolean(Message<String> message, Future<Boolean> promise) {
        promise.setHandler(res -> {
            if (res.succeeded()) {
                utils.sendStatus(res.result(), message);
            } else {
                utils.sendStatus(false, message);
            }
        });
    }
}
