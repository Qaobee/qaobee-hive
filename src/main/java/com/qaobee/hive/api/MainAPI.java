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
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.util.*;


/**
 * The Class Main.
 *
 * @author Xavier.Marin
 */
public class MainAPI extends AbstractGuiceVerticle {
    /**
     * The constant FILE_SERVE.
     */
    public static final String FILE_SERVE = "fileserve";
    private static final Logger LOG = LoggerFactory.getLogger(MainAPI.class);
    private static final String MESSAGE = "message";
    private static final Map<String, Rule> rules = new HashMap<>();
    private static final String HTTP_CODE = "httpCode";

    @Inject
    @Named("runtime")
    private JsonObject runtime;

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

    private static void handleJsonObject(RoutingContext routingContext, String response) {
        final JsonObject json = new JsonObject(response);
        if (json.containsKey(FILE_SERVE)) {
            final File f = new File(json.getString(FILE_SERVE));
            routingContext.response().putHeader("Content-Description", "File Transfer")
                    .putHeader(HttpHeaders.CONTENT_TYPE, json.getString(HttpHeaders.CONTENT_TYPE.toString()))
                    .putHeader("Content-Disposition", "attachment; filename=" + f.getName())
                    .putHeader("Expires", "0")
                    .putHeader("Cache-Control", "must-revalidate")
                    .putHeader("Pragma", "public")
                    .putHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(f.length()));
            routingContext.response().sendFile(f.getAbsolutePath());
        } else {
            routingContext.response().putHeader(HttpHeaders.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON);
            routingContext.response().end(response);
        }
    }

    private static void handleJsonArray(RoutingContext routingContext, String response) {
        routingContext.response().putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON).end(response);
    }

    private static void manage404Error(RoutingContext routingContext) {
        final JsonObject jsonResp = new JsonObject()
                .put(Constants.STATUS, false)
                .put(MESSAGE, "Nothing here")
                .put(HTTP_CODE, 404);
        routingContext.response().putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON).setStatusCode(404).end(jsonResp.encode());
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
        router.route().path("/*").produces("application/json").handler(MainAPI::jsonHandler);
        router.get("/").handler(event -> event.response().end("Welcome to Qaobee Hive"));
        VertxRoute.Loader.getRoutesInPackage(getClass().getPackage().getName())
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
        router.route().last().handler(MainAPI::manage404Error);
        // Load Verticles
        runWebServer(loadVerticles(), router).setHandler(r -> {
            if (r.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(r.cause());
            }
        });
    }

    private static void jsonHandler(RoutingContext context) {
        context.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
                .putHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0, must-revalidate")
                .putHeader("Pragma", "no-cache")
                .putHeader(HttpHeaders.EXPIRES, "0");
        context.next();
    }

    /**
     * @param promises Promises
     * @param router   Route matcher
     */
    private Future<Boolean> runWebServer(List<Future> promises, Router router) {
        Future<Boolean> deferred = Future.future();
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
                LOG.info("Server started");
                deferred.complete(true);
            } else {
                LOG.error(rs.cause().getMessage(), rs.cause());
                deferred.fail(rs.cause());
            }
        });
        return deferred;
    }

    /**
     * @return start promises
     */
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
            manageRules(restMod);
        });
        return promises;
    }

    private void handleAPIRequest(RoutingContext context) {
        final RequestWrapper wrapper = utils.wrapRequest(context);
        List<String> path = Arrays.asList(context.request().path().split("/"));
        path = path.subList(3, path.size());
        wrapper.setPath(path);
        String busAddress = runtime.getInteger("version") + "." + StringUtils.join(path, '.');
        if (rules.containsKey(busAddress)) {
            testRequest(busAddress, wrapper).setHandler(res -> {
                if (res.succeeded()) {
                    if (res.result().getBoolean(Constants.STATUS)) {
                        Rule rule = rules.get(busAddress);
                        try {
                            testParameters(rule, wrapper);
                            vertx.eventBus().send(busAddress, Json.encode(wrapper),
                                    new DeliveryOptions().setSendTimeout(Constants.TIMEOUT),
                                    message -> handleResponse(message, context));
                        } catch (QaobeeException e) {
                            LOG.error(e.getMessage(), e);
                            final JsonObject jsonResp = new JsonObject()
                                    .put(Constants.STATUS, false)
                                    .put(MESSAGE, e.getMessage())
                                    .put("code", e.getCode().name());
                            context.response()
                                    .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                                    .setStatusCode(e.getCode().getCode())
                                    .end(jsonResp.encode());
                        }
                    } else {
                        JsonObject r = res.result();
                        int code = r.getInteger(HTTP_CODE);
                        r.remove(HTTP_CODE);
                        context.response()
                                .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                                .setStatusCode(code)
                                .end(r.encode());
                    }
                }
            });
        } else {
            manage404Error(context);
        }
    }

    private void handleResponse(AsyncResult<Message<Object>> message, RoutingContext context) {
        if (message.succeeded()) {
            final String response = (String) message.result().body();
            System.out.println(response);
            if (response.startsWith("[") || !response.startsWith("{")) {
                handleJsonArray(context, response);
            } else {
                handleJsonObject(context, response);
            }
        } else {
            handleException((ReplyException) message.cause(), context);
        }
    }

    private static void handleException(ReplyException ex, RoutingContext context) {
        context.response().putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        context.response().setStatusCode(500);
        if (ex.getMessage() != null) {
            String exStr = ex.getMessage();
            if (ex.getMessage().startsWith("{")) {
                JsonObject jsonEx = new JsonObject(ex.getMessage());
                context.response().setStatusCode(ExceptionCodes.valueOf(jsonEx.getString("code", "INTERNAL_ERROR")).getCode());
                if (!"true".equals(context.request().getHeader("X-qaobee-stack"))) {
                    jsonEx.remove("stackTrace");
                    jsonEx.remove("suppressed");
                }
                exStr = jsonEx.encode();
            }
            LOG.error(exStr);
            context.response().end(exStr);
        } else {
            manage404Error(context);
        }
    }

    /**
     * @param busAddress address
     * @param wrapper    request wrapper
     *
     * @return success
     */
    private Future<JsonObject> testRequest(String busAddress, RequestWrapper wrapper) {
        Future<JsonObject> deferred = Future.future();
        try {
            Rule rule = rules.get(busAddress);
            if (StringUtils.isNotBlank(rule.method())) {
                utils.testHTTPMetod(rule.method(), wrapper.getMethod());
            }
            List<Future> promises = new ArrayList<>();
            if (rule.logged()) {
                promises.add(utils.isUserLogged(wrapper));
            }
            if (rule.admin()) {
                promises.add(utils.isLoggedAndAdmin(wrapper));
            }
            if (promises.isEmpty()) {
                deferred.complete(new JsonObject().put(Constants.STATUS, true));
            } else {
                CompositeFuture.all(promises).setHandler(rs -> {
                    if (rs.succeeded()) {
                        deferred.complete(new JsonObject().put(Constants.STATUS, true));
                    } else {
                        QaobeeException ex = (QaobeeException) rs.cause();
                        LOG.error(ex.getMessage(), ex);
                        final JsonObject jsonResp = new JsonObject()
                                .put(Constants.STATUS, false)
                                .put(MESSAGE, ex.getMessage())
                                .put(HTTP_CODE, ex.getCode().getCode())
                                .put("code", ex.getCode().name());
                        deferred.complete(jsonResp);
                    }
                });
            }
        } catch (final NoSuchMethodException e) {
            LOG.error(e.getMessage(), e);
            final JsonObject jsonResp = new JsonObject()
                    .put(Constants.STATUS, false)
                    .put(MESSAGE, "Nothing here")
                    .put(HTTP_CODE, 404);
            deferred.complete(jsonResp);
        }
        return deferred;
    }

    private void testParameters(Rule rule, RequestWrapper wrapper) throws QaobeeException {
        switch (rule.scope()) {
            case BODY:
                if (StringUtils.isNotBlank(wrapper.getBody())) {
                    utils.testMandatoryParams(new JsonObject(wrapper.getBody()), rule.mandatoryParams());
                } else {
                    utils.testMandatoryParams(new JsonObject(), rule.mandatoryParams());
                }
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