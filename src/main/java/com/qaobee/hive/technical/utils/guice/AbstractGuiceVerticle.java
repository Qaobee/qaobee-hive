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
import com.qaobee.hive.dao.Utils;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.*;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
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

    /**
     * Inject abstract guice verticle.
     *
     * @param clazz the clazz
     *
     * @return the abstract guice verticle
     */
    public AbstractGuiceVerticle inject(AbstractGuiceVerticle clazz) {
        injector = Guice.createInjector(new GuiceModule(this.config(), vertx));
        injector.injectMembers(this);
        LOG.debug(clazz.getClass().getName() + " started");
        return this;
    }

    /**
     * Handle json j handler.
     *
     * @param message the message
     *
     * @return the handler
     */
    protected Handler<AsyncResult<JsonObject>> handleJson(Message<JsonObject> message) {
        return res -> {
            if (res.succeeded()) {
                message.reply(res.result());
            } else {
                if(res.cause() instanceof QaobeeException) {
                    utils.sendError(message, (QaobeeException) res.cause());
                } else {
                    utils.sendError(message, new QaobeeException(ExceptionCodes.INTERNAL_ERROR, res.cause().getMessage()));
                }
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
}
