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
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.jdeferred.Deferred;
import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;
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
    protected
    Utils utils;
    private List<Promise<Boolean, Throwable, Integer>> promises = new ArrayList<>();

    /**
     * Inject abstract guice verticle.
     *
     * @param clazz the clazz
     * @return the abstract guice verticle
     */
    public AbstractGuiceVerticle inject(AbstractGuiceVerticle clazz) {
        if (StringUtils.isNotBlank(System.getenv("OPENSHIFT_MONGODB_DB_HOST"))) {
            config().getJsonObject(MONGO_CONF_KEY).put("host", System.getenv("OPENSHIFT_MONGODB_DB_HOST"));
            config().getJsonObject(MONGO_CONF_KEY).put("port", Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT")));
            config().getJsonObject(MONGO_CONF_KEY).put("password", System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD"));
            config().getJsonObject(MONGO_CONF_KEY).put("username", System.getenv("OPENSHIFT_MONGODB_DB_USERNAME"));
        }
        Injector injector = Guice.createInjector(new GuiceModule(this.config(), vertx));
        injector.injectMembers(this);
        LOG.debug(clazz.getClass().getName() + " started");
        return this;
    }

    /**
     * Add abstract guice verticle.
     *
     * @param address the address
     * @param handler the handler
     * @return the abstract guice verticle
     */
    public <T> AbstractGuiceVerticle add(String address, Handler<Message<T>> handler) {
        Deferred<Boolean, Throwable, Integer> deferred = new DeferredObject<>();
        vertx.eventBus().consumer(address, handler).completionHandler(ar -> {
            if (ar.succeeded()) {
                deferred.resolve(true);
            } else {
                deferred.reject(ar.cause());
            }
        });
        promises.add(deferred);
        return this;
    }

    public void register(Future<Void> startFuture) {
        DeferredManager dm = new DefaultDeferredManager();
        dm.when(promises.toArray(new Promise[promises.size()]))
                .done(rs -> startFuture.complete())
                .fail(ex -> startFuture.fail(((Throwable) ex.getReject())));
    }


    /**
     * Reply json object.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyJsonObject(Message<String> message, Promise<JsonObject, QaobeeException, Integer> promise) {
        promise.done(json -> message.reply(json.encode())).fail(e -> utils.sendError(message, e));
    }

    /**
     * Reply json object j.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyJsonObjectJ(Message<JsonObject> message, Promise<JsonObject, QaobeeException, Integer> promise) {
        promise.done(message::reply).fail(e -> utils.sendErrorJ(message, e));
    }

    /**
     * Reply string.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyString(Message<String> message, Promise<String, QaobeeException, Integer> promise) {
        promise.done(message::reply).fail(e -> utils.sendError(message, e));
    }

    /**
     * Reply json array.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyJsonArray(Message<String> message, Promise<JsonArray, QaobeeException, Integer> promise) {
        promise.done(json -> message.reply(json.encode())).fail(e -> utils.sendError(message, e));
    }

    /**
     * Reply boolean.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyBoolean(Message<String> message, Promise<Boolean, QaobeeException, Integer> promise) {
        promise.done(r -> utils.sendStatus(r, message)).fail(e -> utils.sendStatus(false, message));
    }

    /**
     * Reply boolean j.
     *
     * @param message the message
     * @param promise the promise
     */
    protected void replyBooleanJ(Message<JsonObject> message, Promise<Boolean, QaobeeException, Integer> promise) {
        promise.done(r -> utils.sendStatusJson(r, message)).fail(e -> utils.sendStatusJson(false, message));
    }

}
