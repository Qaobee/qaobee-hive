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

import com.qaobee.hive.api.v1.commons.utils.AssetVerticle;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.apache.commons.io.FilenameUtils;
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
    private static final String COLLECTION = "collection";
    private static final String APPLICATION_JSON = "application/json";
    private static final String MESSAGE = "message";
    private static final Map<String, Rule> rules = new HashMap<>();

    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    @Named("cors")
    private JsonObject cors;

    /**
     * @param routingContext request
     * @param e              exception
     */
    private void handleError(RoutingContext routingContext, QaobeeException e) {
        enableCors(routingContext);
        routingContext.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        routingContext.response().setStatusCode(e.getCode().getCode());
        JsonObject jsonEx = new JsonObject(Json.encode(e));
        jsonEx.remove("stackTrace");
        jsonEx.remove("suppressed");
        routingContext.response().end(jsonEx.encode());
    }

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
        routingContext.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        routingContext.response().end(response);
    }

    private static void manage404Error(RoutingContext routingContext) {
        final JsonObject jsonResp = new JsonObject()
                .put("status", false)
                .put(MESSAGE, "Nothing here")
                .put("httpCode", 404);
        routingContext.response().setStatusCode(404).end(jsonResp.encode());
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
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        final Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/file/:collection/:id").handler(this::getAssetHandler);
        router.post("/file/:collection/:field/:uid").handler(this::assetUploadHandler);
        router.optionsWithRegex(".*").handler(req -> {
            if (config().getJsonObject("cors").getBoolean("enabled", false)) {
                enableCors(req);
            }
            req.response().end();
        });
        router.get("/").handler(event -> event.response().end("Welcome to Qaobee Hive"));
        // API Rest
        router.routeWithRegex("^/api/.*").handler(this::handleAPIRequest);
        // Load Verticles
        runWebServer(loadVerticles(), router, startFuture);
    }

    /**
     * @param promises    Promises
     * @param router      Route matcher
     * @param startFuture future
     */
    private void runWebServer(List<Promise<String, Throwable, Integer>> promises, Router router, Future<Void> startFuture) {
        DeferredManager dm = new DefaultDeferredManager();
        dm.when(promises.toArray(new Promise[promises.size()])).done(rs -> {
            handleServerStart(router);
            startFuture.complete();
        }).fail(e -> {
            LOG.error(((Throwable) e.getReject()).getMessage());
            startFuture.fail((Throwable) e.getReject());
        });
    }

    private void handleServerStart(Router router) {
        final HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept);
        String ip = runtime.getString("defaultHost");
        int port = runtime.getInteger("defaultPort");
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        sockJSHandler.bridge(new BridgeOptions());
        router.route("/eventbus/*").handler(sockJSHandler);
        server.listen(port, ip);
        LOG.info("The http server is started on : " + ip + ":" + port);
        System.out.println("Server started");
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
            //   if (restMod.getAnnotation(DeployableVerticle.class).isWorker()) {
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
        final RequestWrapper wrapper = wrapRequest(routingContext);
        List<String> path = Arrays.asList(routingContext.request().path().split("/"));
        path = path.subList(3, path.size());
        wrapper.setPath(path);
        // Collect metrics : number of requests
        final JsonObject json = new JsonObject()
                .put("name", "meter." + StringUtils.join(wrapper.getPath(), '.'))
                .put("action", "mark");
        vertx.eventBus().send("metrix", json);
        String busAddress = runtime.getInteger("version") + "." + StringUtils.join(path, '.');
        if (rules.containsKey(busAddress)) {
            testRequest(busAddress, wrapper).done(res -> {
                if (res.getBoolean("status")) {
                    Rule rule = rules.get(busAddress);
                    try {
                        testParameters(rule, wrapper);

                        vertx.eventBus().send(busAddress, Json.encode(wrapper), new DeliveryOptions().setSendTimeout(Constants.TIMEOUT), message -> {
                            try {
                                if (message.succeeded()) {
                                    final String response = (String) message.result().body();
                                    if (response.startsWith("[") || !response.startsWith("{")) {
                                        handleJsonArray(routingContext, response);
                                    } else {
                                        handleJsonObject(routingContext, response);
                                    }
                                } else {
                                    throw (ReplyException) message.cause();
                                }
                            } catch (ReplyException ex) {
                                routingContext.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
                                routingContext.response().setStatusCode(404);
                                enableCors(routingContext);
                                if (ex.failureCode() > 0) {
                                    routingContext.response().setStatusCode(ex.failureCode());
                                }
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
                        });
                    } catch (QaobeeException e) {
                        LOG.error(e.getMessage(), e);
                        final JsonObject jsonResp = new JsonObject()
                                .put("status", false)
                                .put(MESSAGE, e.getMessage())
                                .put("code", e.getCode().name());
                        routingContext.response()
                                .putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON)
                                .setStatusCode(e.getCode().getCode())
                                .end(jsonResp.encode());
                    }
                } else {
                    int code = res.getInteger("httpCode");
                    res.remove("httpCode");
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

    private RequestWrapper wrapRequest(RoutingContext routingContext) {
        RequestWrapper wrapper = new RequestWrapper();
        wrapper.setBody(routingContext.getBodyAsString());
        wrapper.setMethod(routingContext.request().rawMethod());
        wrapper.setHeaders(toMap(routingContext.request().headers()));
        wrapper.setParams(toMap(routingContext.request().params()));
        wrapper.setLocale(routingContext.request().getHeader("Accept-Language"));
        return wrapper;
    }

    private HashMap<String, List<String>> toMap(MultiMap multiMap) {
        HashMap<String, List<String>> map = new HashMap<>();
        multiMap.entries().forEach(e -> {
            if (!map.containsKey(e.getKey())) {
                map.put(e.getKey(), new ArrayList<>());
            }
            map.get(e.getKey()).add(e.getValue());
        });
        return map;
    }

    /**
     * @apiDescription Put an asset in a collection
     * @api {post} /file/:collection/:field/:uid
     * @apiName Post asset
     * @apiGroup Main
     * @apiParam {String} collection collection
     * @apiparam {String} field field
     * @apiparam {String} token token
     * @apiparam {String} locale locale
     * @apiparam {String} uid document id
     */
    private void assetUploadHandler(RoutingContext routingContext) {
        utils.isUserLogged(wrapRequest(routingContext)).done(ok -> {
            enableCors(routingContext);
            final JsonObject request = new JsonObject()
                    .put(COLLECTION, routingContext.request().getParam(COLLECTION))
                    .put("field", routingContext.request().getParam("field"))
                    .put("uid", routingContext.request().getParam("uid"))
                    .put("token", routingContext.request().getHeader("token"))
                    .put("locale", routingContext.request().getHeader("Accept-Language"));
            // We first pause the request so we don't receive any data between now and when the file is opened
            String datadir = System.getProperty("user.home");
            if (StringUtils.isNotBlank(System.getenv("OPENSHIFT_DATA_DIR"))) {
                datadir = System.getenv("OPENSHIFT_DATA_DIR");
            }
            final File dir = new File(datadir + "/upload");
            if (!dir.exists()) {
                boolean res = dir.mkdirs();
                LOG.debug("Creating " + dir.getAbsolutePath() + " result : " + res);
            }
            request.put("datadir", datadir);
            routingContext.fileUploads().forEach(upload -> handleUpload(upload, request, dir, routingContext));
        }).fail(e -> handleError(routingContext, e));
    }


    /**
     * @apiDescription Get an asset from a collection
     * @api {get} /file/:collection/:id  Get an asset from a collection
     * @apiVersion 0.1.0
     * @apiName Get asset
     * @apiGroup Main
     * @apiPermission all
     * @apiParam {String} collection Mandatory The collection.
     * @apiParam {String} id Mandatory The Asset-ID.
     */
    private void getAssetHandler(RoutingContext routingContext) {
        enableCors(routingContext);
        final JsonObject request = new JsonObject()
                .put(COLLECTION, routingContext.request().getParam(COLLECTION))
                .put("id", routingContext.request().getParam("id"));
        vertx.eventBus().send(AssetVerticle.GET, request, message -> {
            if (message.succeeded() && ((JsonObject) message.result().body()).containsKey(HTTP.CONTENT_LEN)) {
                routingContext.response().putHeader(HTTP.CONTENT_LEN, ((JsonObject) message.result().body()).getString(HTTP.CONTENT_LEN))
                        .end(Buffer.buffer(((JsonObject) message.result().body()).getBinary("asset")));
            } else {
                routingContext.response().setStatusCode(404).end(message.cause().getMessage());
            }
        });
    }

    private void handleUpload(FileUpload upload, JsonObject request, File dir, RoutingContext routingContext) {
        final String destFileName = dir.getAbsolutePath() + "/" + routingContext.request().getParam("uid") + "." + FilenameUtils.getExtension(upload.fileName());
        request.put("filename", destFileName).put(HTTP.CONTENT_TYPE, upload.contentType());
        if (vertx.fileSystem().existsBlocking(destFileName)) {
            vertx.fileSystem().deleteBlocking(destFileName);
        }
        vertx.fileSystem().copy(upload.uploadedFileName(), destFileName, res -> vertx.eventBus().send(AssetVerticle.ADD, request, message -> {
            routingContext.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            if (message.succeeded()) {
                if (message.result().body() instanceof ReplyException) {
                    final JsonObject resp = new JsonObject(((ReplyException) message.result().body()).getMessage());
                    routingContext.response().setStatusCode(ExceptionCodes.valueOf(resp.getString("code")).getCode())
                            .end(resp.getString(MESSAGE, ""));
                } else if (message.result().body() instanceof JsonObject) {
                    routingContext.response().setStatusCode(200).end(((JsonObject) message.result().body()).encode());
                } else {
                    routingContext.response().setStatusCode(200).end((String) message.result().body());
                }
            } else {
                handleError(routingContext, new QaobeeException(ExceptionCodes.DATA_ERROR, message.cause().getMessage()));
            }
        }));
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
                deferred.resolve(new JsonObject().put("status", true));
            } else {
                DeferredManager dm = new DefaultDeferredManager();
                dm.when(promises.toArray(new Promise[promises.size()])).done(rs -> deferred.resolve(new JsonObject().put("status", true))).fail(e -> {
                    QaobeeException ex = (QaobeeException) e.getReject();
                    LOG.error(ex.getMessage(), ex);
                    final JsonObject jsonResp = new JsonObject()
                            .put("status", false)
                            .put(MESSAGE, ex.getMessage())
                            .put("httpCode", ex.getCode().getCode())
                            .put("code", ex.getCode().name());
                    deferred.resolve(jsonResp);
                });
            }
        } catch (final NoSuchMethodException e) {
            LOG.error(e.getMessage(), e);
            final JsonObject jsonResp = new JsonObject()
                    .put("status", false)
                    .put(MESSAGE, "Nothing here")
                    .put("httpCode", 404);
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