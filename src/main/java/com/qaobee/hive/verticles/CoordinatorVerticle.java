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
package com.qaobee.hive.verticles;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


/**
 * The Class Main.
 *
 * @author Xavier.Marin
 */
public class CoordinatorVerticle extends AbstractGuiceVerticle {
    /**
     * The constant FILE_SERVE.
     */
    public static final String FILE_SERVE = "fileserve";

    private static final Logger LOG = LoggerFactory.getLogger(CoordinatorVerticle.class);
    private static final String MESSAGE = "message";
    private static final String HTTP_CODE = "httpCode";

    @Inject
    @Named("runtime")
    private JsonObject runtime;

    @Override
    public void start(Future<Void> startFuture) {
        super.inject(this);
        String env = System.getenv("ENV");
        if (StringUtils.isBlank(env)) {
            env = "DEV";
        }
        ProxyService.Loader.load("com.qaobee.hive.services.impl", injector, vertx);
        final Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        if ("DEV".equals(env)) {
            router.route().handler(CorsHandler.create("*")
                    .allowedMethod(HttpMethod.GET)
                    .allowedMethod(HttpMethod.POST)
                    .allowedMethod(HttpMethod.PUT)
                    .allowedMethod(HttpMethod.DELETE)
                    .allowedHeader("X-Requested-With")
                    .allowedHeader("Content-Type")
                    .allowedHeader("Origin")
                    .allowedHeader("token")
                    .allowedHeader("uid")
            );
        }
        router.route().path("/*").produces("application/json").handler(CoordinatorVerticle::jsonHandler);
        router.get("/").handler(event -> event.response().end("Welcome to Qaobee Hive"));

        // Load Routes
        VertxRoute.Loader.getRoutesInPackage(Module.class.getPackage().getName())
                .entrySet().stream().sorted(Comparator.comparingInt(e -> e.getKey().order()))
                .forEachOrdered(item -> {
                            injector.injectMembers(item.getValue());
                            try {
                                LOG.debug("Deploy " + item.getKey());
                                router.mountSubRouter(item.getKey().rootPath(), item.getValue().init());
                            } catch (Exception e) {
                                LOG.error(e.getMessage(), e);
                            }
                        }
                );
        router.route().last().handler(CoordinatorVerticle::manage404Error);

        // Load Verticles
        runWebServer(loadVerticles(), router, startFuture);
    }

    private static void jsonHandler(RoutingContext context) {
        context.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
                .putHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0, must-revalidate")
                .putHeader("Pragma", "no-cache")
                .putHeader(HttpHeaders.EXPIRES, "0");
        context.next();
    }

    private void runWebServer(List<Future> promises, Router router, Future<Void> startFuture) {
        CompositeFuture.all(promises).setHandler(rs -> {
            if (rs.succeeded()) {
                final HttpServer server = vertx.createHttpServer();
                server.requestHandler(router::accept);
                String ip = runtime.getString("defaultHost");
                int port = runtime.getInteger("defaultPort");
                SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
                sockJSHandler.bridge(new BridgeOptions());
                router.route("/eventbus/*").handler(sockJSHandler);
                server.listen(port, ip);
                LOG.info("The http server is started on : {} : {}", ip, port);
                startFuture.complete();
            } else {
                LOG.error(rs.cause().getMessage(), rs.cause());
                startFuture.fail(rs.cause());
            }
        });
    }

    private List<Future> loadVerticles() {
        List<Future> promises = new ArrayList<>();
        // Loading Verticles
        final Set<Class<?>> restModules = DeployableVerticle.VerticleLoader.scanPackage(getClass().getPackage().getName());
        restModules.addAll(DeployableVerticle.VerticleLoader.scanPackage("com.qaobee.hive.verticles"));
        restModules.forEach(restMod -> {
            Future<String> deferred = Future.future();
            promises.add(deferred);
            vertx.deployVerticle(restMod.getName(), new DeploymentOptions()
                    .setConfig(config())
                    .setWorker(true)
                    .setWorkerPoolSize(restMod.getAnnotation(DeployableVerticle.class).poolSize()), res -> {
                if (res.succeeded()) {
                    deferred.complete(res.result());
                } else {
                    deferred.fail(res.cause());
                }
            });
        });
        return promises;
    }

    private static void manage404Error(RoutingContext routingContext) {
        final JsonObject jsonResp = new JsonObject()
                .put(Constants.STATUS, false)
                .put(MESSAGE, "Nothing here")
                .put(HTTP_CODE, 404);
        routingContext.response().putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON).setStatusCode(404).end(jsonResp.encode());
    }
}