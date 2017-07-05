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
package com.qaobee.hive.api;

import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.jdeferred.Deferred;
import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.util.*;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

/**
 * The Class Main.
 *
 * @author Xavier.Marin
 */
public class Main extends AbstractGuiceVerticle {
    /**
     * The constant FILE_SERVE.
     */
    public static final String FILE_SERVE = "fileserve";
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final String MESSAGE = "message";
    private static final Map<String, Rule> rules = new HashMap<>();
    private static final String HTTP_CODE = "httpCode";

    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    @Named("cors")
    private JsonObject cors;

    /**
     * @param restMod Class to scan
     */
    private static void manageRules(Class<?> restMod) {
        Reflections reflections = new Reflections(restMod, new MethodAnnotationsScanner());
        reflections.getMethodsAnnotatedWith(Rule.class).forEach(m -> {
            Rule r = m.getAnnotation(Rule.class);
            if (!rules.containsKey(r.address())) {
                rules.put(r.address(), r);
                LOG.info("Registring : " + r.address());
            }
        });
    }

    private void handleJsonObject(RoutingContext routingContext, String response) {
        final JsonObject json = new JsonObject(response);
        if (json.containsKey(FILE_SERVE)) {
            final File f = new File(json.getString(FILE_SERVE));
            routingContext.response().putHeader("Content-Description", "File Transfer")
                    .putHeader(HTTP.CONTENT_TYPE, json.getString(HTTP.CONTENT_TYPE))
                    .putHeader("Content-Disposition", "attachment; filename=" + f.getName())
                    .putHeader("Expires", "0")
                    .putHeader("Cache-Control", "must-revalidate")
                    .putHeader("Pragma", "public")
                    .putHeader(HTTP.CONTENT_LEN, String.valueOf(f.length()));
            enableCors(routingContext);
            routingContext.response().sendFile(f.getAbsolutePath());
        } else {
            enableCors(routingContext);
            routingContext.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            routingContext.response().end(response);
        }
    }

    private void handleJsonArray(RoutingContext routingContext, String response) {
        enableCors(routingContext);
        routingContext.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON).end(response);
    }

    private static void manage404Error(RoutingContext routingContext) {
        final JsonObject jsonResp = new JsonObject()
                .put(Constants.STATUS, false)
                .put(MESSAGE, "Nothing here")
                .put(HTTP_CODE, 404);
        routingContext.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON).setStatusCode(404).end(jsonResp.encode());
    }

    /**
     * Enable cors.
     *
     * @param routingContext the req
     */
    private void enableCors(final RoutingContext routingContext) {
        if (cors.getBoolean("enabled")) {
            routingContext.response().headers().add("Access-Control-Allow-Origin", "*");
            routingContext.response().headers().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
            routingContext.response().headers().add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, token, uid");
        }
    }

    /**
     * Gets rules.
     *
     * @return the rules
     */
    public static Map<String, Rule> getRules() {
        return rules;
    }

    @Override
    public void start(Future<Void> startFuture) {
        super.inject(this);
        ProxyService.Loader.load("com.qaobee.hive.services.impl", injector, vertx);
        final Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.optionsWithRegex(".*").handler(req -> {
            if (config().getJsonObject("cors").getBoolean("enabled", false)) {
                enableCors(req);
            }
            req.response().end();
        });
        router.get("/").handler(event -> event.response().end("Welcome to Qaobee Hive"));
        VertxRoute.Loader.getRoutesInPackage("com.qaobee.hive.api")
                .entrySet().stream().sorted(Comparator.comparingInt(e -> e.getKey().order()))
                .forEachOrdered(item -> {
                            LOG.info("Injecting " + item.getKey());
                            injector.injectMembers(item.getValue());
                            try {
                                router.mountSubRouter(item.getKey().rootPath(), item.getValue().init());
                            } catch (Exception e) {
                                LOG.error(e.getMessage(), e);
                            }
                        }
                );
        // API Rest
        router.routeWithRegex("^/api/.*").handler(this::handleAPIRequest);
        // Load Verticles
        runWebServer(loadVerticles(), router).done(r -> startFuture.complete()).fail(startFuture::fail);
    }

    /**
     * @param promises Promises
     * @param router   Route matcher
     */
    private Promise<Boolean, Throwable, Integer> runWebServer(List<Promise<String, Throwable, Integer>> promises, Router router) {
        Deferred<Boolean, Throwable, Integer> deferred = new DeferredObject<>();
        DeferredManager dm = new DefaultDeferredManager();
        dm.when(promises.toArray(new Promise[promises.size()])).done(rs -> {
            final HttpServer server = vertx.createHttpServer();
            server.requestHandler(router::accept);
            String ip = runtime.getString("defaultHost");
            int port = runtime.getInteger("defaultPort");
            SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
            sockJSHandler.bridge(new BridgeOptions());
            router.route("/eventbus/*").handler(sockJSHandler);
            server.listen(port, ip);
            LOG.info("The http server is started on : {} : {}", ip, port);
            LOG.info("Server started");
            deferred.resolve(true);
        }).fail(e -> {
            LOG.error(((Throwable) e.getReject()).getMessage());
            deferred.reject((Throwable) e.getReject());
        });
        return deferred.promise();
    }

    /**
     * @return start promises
     */
    private List<Promise<String, Throwable, Integer>> loadVerticles() {
        List<Promise<String, Throwable, Integer>> promises = new ArrayList<>();
        // Loading Verticles
        final Set<Class<?>> restModules = DeployableVerticle.VerticleLoader.scanPackage(getClass().getPackage().getName());
        restModules.forEach(restMod -> {
            Deferred<String, Throwable, Integer> deferred = new DeferredObject<>();
            promises.add(deferred.promise());
            vertx.deployVerticle(restMod.getName(), new DeploymentOptions()
                    .setConfig(config())
                    .setWorker(true)
                    .setWorkerPoolSize(restMod.getAnnotation(DeployableVerticle.class).poolSize()), res -> {
                if (res.succeeded()) {
                    deferred.resolve(res.result());
                } else {
                    deferred.reject(res.cause());
                }
            });
            manageRules(restMod);
        });
        return promises;
    }

    private void handleAPIRequest(RoutingContext routingContext) {
        final RequestWrapper wrapper = utils.wrapRequest(routingContext);
        List<String> path = Arrays.asList(routingContext.request().path().split("/"));
        path = path.subList(3, path.size());
        wrapper.setPath(path);
        String busAddress = runtime.getInteger("version") + "." + StringUtils.join(path, '.');
        if (rules.containsKey(busAddress)) {
            testRequest(busAddress, wrapper).done(res -> {
                if (res.getBoolean(Constants.STATUS)) {
                    Rule rule = rules.get(busAddress);
                    try {
                        testParameters(rule, wrapper);
                        vertx.eventBus().send(busAddress, Json.encode(wrapper),
                                new DeliveryOptions().setSendTimeout(Constants.TIMEOUT),
                                message -> handleResponse(message, routingContext));
                    } catch (QaobeeException e) {
                        LOG.error(e.getMessage(), e);
                        final JsonObject jsonResp = new JsonObject()
                                .put(Constants.STATUS, false)
                                .put(MESSAGE, e.getMessage())
                                .put("code", e.getCode().name());
                        routingContext.response()
                                .putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON)
                                .setStatusCode(e.getCode().getCode())
                                .end(jsonResp.encode());
                    }
                } else {
                    int code = res.getInteger(HTTP_CODE);
                    res.remove(HTTP_CODE);
                    routingContext.response()
                            .putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON)
                            .setStatusCode(code)
                            .end(res.encode());
                }
            });
        } else {
            manage404Error(routingContext);
        }
    }

    private void handleResponse(AsyncResult<Message<Object>> message, RoutingContext routingContext) {
        try {
            if (message.succeeded()) {
                final String response = (String) message.result().body();
                if (response.startsWith("[") || !response.startsWith("{")) {
                    handleJsonArray(routingContext, response);
                } else {
                    handleJsonObject(routingContext, response);
                }
            } else {
                throw message.cause();
            }
        } catch (Throwable ex) {
            handleException(ex, routingContext);
        }
    }

    private void handleException(Throwable ex, RoutingContext routingContext) {
        routingContext.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        routingContext.response().setStatusCode(500);
        enableCors(routingContext);
        if (ex.getMessage() != null) {
            String exStr = ex.getMessage();
            if (ex.getMessage().startsWith("{")) {
                JsonObject jsonEx = new JsonObject(ex.getMessage());
                if (!"true".equals(routingContext.request().getHeader("X-qaobee-stack"))) {
                    jsonEx.remove("stackTrace");
                    jsonEx.remove("suppressed");
                }
                exStr = jsonEx.encode();
            }
            LOG.error(exStr);
            routingContext.response().end(exStr);
        } else {
            manage404Error(routingContext);
        }
    }

    /**
     * @param busAddress address
     * @param wrapper    request wrapper
     * @return success
     */
    private Promise<JsonObject, QaobeeException, Integer> testRequest(String busAddress, RequestWrapper wrapper) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        try {
            Rule rule = rules.get(busAddress);
            if (StringUtils.isNotBlank(rule.method())) {
                utils.testHTTPMetod(rule.method(), wrapper.getMethod());
            }
            List<Promise> promises = new ArrayList<>();
            if (rule.logged()) {
                promises.add(utils.isUserLogged(wrapper));
            }
            if (rule.admin()) {
                promises.add(utils.isLoggedAndAdmin(wrapper));
            }
            if (promises.isEmpty()) {
                deferred.resolve(new JsonObject().put(Constants.STATUS, true));
            } else {
                DeferredManager dm = new DefaultDeferredManager();
                dm.when(promises.toArray(new Promise[promises.size()])).done(rs -> deferred.resolve(new JsonObject().put(Constants.STATUS, true))).fail(e -> {
                    QaobeeException ex = (QaobeeException) e.getReject();
                    LOG.error(ex.getMessage(), ex);
                    final JsonObject jsonResp = new JsonObject()
                            .put(Constants.STATUS, false)
                            .put(MESSAGE, ex.getMessage())
                            .put(HTTP_CODE, ex.getCode().getCode())
                            .put("code", ex.getCode().name());
                    deferred.resolve(jsonResp);
                });
            }
        } catch (final NoSuchMethodException e) {
            LOG.error(e.getMessage(), e);
            final JsonObject jsonResp = new JsonObject()
                    .put(Constants.STATUS, false)
                    .put(MESSAGE, "Nothing here")
                    .put(HTTP_CODE, 404);
            deferred.resolve(jsonResp);
        }
        return deferred.promise();
    }

    private void testParameters(Rule rule, RequestWrapper wrapper) throws QaobeeException {
        switch (rule.scope()) {
            case BODY:
                JsonObject body = new JsonObject();
                if (StringUtils.isNotBlank(wrapper.getBody())) {
                    body = new JsonObject(wrapper.getBody());
                }
                utils.testMandatoryParams(body, rule.mandatoryParams());
                break;
            case REQUEST:
                utils.testMandatoryParams(wrapper.getParams(), rule.mandatoryParams());
                break;
            case HEADER:
                utils.testMandatoryParams(wrapper.getHeaders(), rule.mandatoryParams());
                break;
            default:
                break;
        }
    }
}