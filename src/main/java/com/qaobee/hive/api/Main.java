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

import com.englishtown.promises.Promise;
import com.englishtown.promises.When;
import com.qaobee.hive.api.v1.commons.utils.AssetVerticle;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.technical.vertx.ServerHook;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Future;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.eventbus.ReplyException;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerFileUpload;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.mods.Mailer;

import javax.inject.Inject;
import java.io.File;
import java.util.*;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;

/**
 * The Class Main.
 *
 * @author Xavier.Marin
 */
public class Main extends AbstractGuiceVerticle {
    public static final String FILE_SERVE = "fileserve";
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final String COLLECTION = "collection";
    private static final String APPLICATION_JSON = "application/json";
    private static final String MESSAGE = "message";
    @Inject
    private Utils utils;
    private SockJSServer sockJSServer;
    private static Map<String, Rule> rules = new HashMap<>();

    /**
     * Start void.
     *
     * @param startedResult the started result
     */
    @Override
    public void start(final Future<Void> startedResult) {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        final RouteMatcher rm = new RouteMatcher();

        /**
         * @api {get} /file/:collection/:id  Get an asset from a collection
         * @apiVersion 0.1.0
         * @apiName Get asset
         * @apiGroup Main API
         * @apiPermission all
         *
         * @apiDescription Get an asset from a collection
         *
         * @apiParam {String} collection Mandatory The collection.
         * @apiParam {String} id Mandatory The Asset-ID.
         */
        rm.get("/file/:collection/:id", this::getAssetHandler);
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
         *
         * @apiError NOT_LOGGED user not logged
         */
        rm.post("/file/:collection/:field/:uid", this::assetUploadHandler);
        rm.optionsWithRegEx(".*", req -> {
            if (container.config().getObject("cors").getBoolean("enabled", false)) {
                enableCors(req);
            }
            req.response().end();
        });
        rm.get("/", event -> event.response().end("Welcome to Qaobee Hive"));
        // API Rest
        rm.allWithRegEx("^/api/.*", this::apiHandler);
        // Load Verticles
        List<Promise<String, Void>> promises = loadVerticles();
        runWebServer(promises, rm, startedResult);
    }

    /**
     * @param promises    Promises
     * @param rm          Route matcher
     * @param startResult Start result
     */
    private void runWebServer(List<Promise<String, Void>> promises, Handler<HttpServerRequest> rm, Future<Void> startResult) {
        final HttpServer server = vertx.createHttpServer();
        new When<String, Void>().all(promises, value -> {
            server.requestHandler(rm);
            String ip = container.config().getObject(RUNTIME).getString("defaultHost");
            int port = container.config().getObject(RUNTIME).getInteger("defaultPort");
            if (container.env().containsKey("OPENSHIFT_VERTX_IP")) {
                ip = container.env().get("OPENSHIFT_VERTX_IP");
            }
            if (container.env().containsKey("OPENSHIFT_VERTX_PORT")) {
                port = Integer.parseInt(container.env().get("OPENSHIFT_VERTX_PORT"));
            }
            sockJSServer = vertx.createSockJSServer(server);
            JsonObject wsConfig = new JsonObject().putString("prefix", "/eventbus");
            JsonArray outboundPermitted = new JsonArray();
            JsonArray inboundPermitted = new JsonArray();
            outboundPermitted.add(new JsonObject());
            inboundPermitted.add(new JsonObject().putObject("match", new JsonObject().putString("secret", UUID.randomUUID().toString())));
            sockJSServer.setHook(new ServerHook(container.config().getObject(RUNTIME).getString("site.url")));
            sockJSServer.bridge(wsConfig, inboundPermitted, outboundPermitted);
            server.listen(port, ip);
            LOG.info("The http server is started on : " + ip + ":" + port);
            startResult.setResult(null);
            return null;
        }, value -> {
            LOG.error(value.error.getMessage());
            startResult.setFailure(value.error);
            return null;
        });
    }

    /**
     * @return start promises
     */
    private List<Promise<String, Void>> loadVerticles() {
        final List<Promise<String, Void>> promises = new ArrayList<>();
        // Loading modules
        promises.add(whenContainer.deployWorkerVerticle(com.bloidonia.vertx.metrics.MetricsModule.class.getCanonicalName(), container.config().getObject("metrix.mod"), 1, true));
        promises.add(whenContainer.deployWorkerVerticle(Mailer.class.getCanonicalName(), container.config().getObject("mailer.mod"), 1, true));
        // Loading Verticles
        final Set<Class<?>> restModules = DeployableVerticle.VerticleLoader.scanPackage(getClass().getPackage().getName());
        restModules.forEach(restMod -> {
            if (restMod.getAnnotation(DeployableVerticle.class).isWorker()) {
                promises.add(whenContainer.deployWorkerVerticle(restMod.getName(), container.config(), 2, true));
            } else {
                promises.add(whenContainer.deployVerticle(restMod.getName(), container.config()));
            }
            manageRules(restMod);
        });
        return promises;
    }

    /**
     * @param req Request
     */
    private void apiHandler(HttpServerRequest req) { // NOSONAR
        req.bodyHandler(event -> {
            final RequestWrapper wrapper = new RequestWrapper();
            wrapper.setBody(event.toString());
            wrapper.setMethod(req.method());
            wrapper.setHeaders(utils.toMap(req.headers()));
            wrapper.setParams(utils.toMap(req.params()));
            wrapper.setLocale(req.headers().get(ACCEPT_LANGUAGE));
            List<String> path = Arrays.asList(req.path().split("/"));
            path = path.subList(3, path.size());
            wrapper.setPath(path);
            startTimer(StringUtils.join(wrapper.getPath(), '.'));
            // Collect metrics : number of requests
            final JsonObject json = new JsonObject()
                    .putString("name", "meter." + StringUtils.join(wrapper.getPath(), '.'))
                    .putString("action", "mark");
            vertx.eventBus().send("metrix", json);
            String busAddress = container.config().getObject(RUNTIME).getInteger("version") + "." + StringUtils.join(path, '.');
            if (rules.containsKey(busAddress)) {
                if(testRequest(req, busAddress, wrapper)) {
                    vertx.eventBus().sendWithTimeout(busAddress, Json.encode(wrapper), Constants.TIMEOUT, message -> {
                        stopTimer(StringUtils.join(wrapper.getPath(), '.'));
                        handleResult(message, req);
                    });
                }
            } else {
                manage404Error(req);
            }
        });
    }

    /**
     * @param req Request
     */
    private void assetUploadHandler(HttpServerRequest req) { // NOSONAR
        startTimer("main.avatar");
        enableCors(req);
        final JsonObject request = new JsonObject()
                .putString(COLLECTION, req.params().get(COLLECTION))
                .putString("field", req.params().get("field"))
                .putString("uid", req.params().get("uid"))
                .putString("token", req.headers().get("token"))
                .putString("locale", req.headers().get(ACCEPT_LANGUAGE));
        // We first pause the request so we don't receive any data between now and when the file is opened
        String datadir = System.getProperty("user.home");
        if (container.env().containsKey("OPENSHIFT_DATA_DIR")) {
            datadir = container.env().get("OPENSHIFT_DATA_DIR");
        }
        final File dir = new File(datadir + "/upload");
        if (!dir.exists()) {
            boolean res = dir.mkdirs();
            LOG.debug("Creating " + dir.getAbsolutePath() + " result : " + res);
        }
        request.putString("datadir", datadir);
        req.expectMultiPart(true).uploadHandler(getUploadHandler(request, dir, req));
    }

    /**
     * @param req Request
     */
    private void getAssetHandler(HttpServerRequest req) { // NOSONAR
        enableCors(req);
        final JsonObject request = new JsonObject()
                .putString(COLLECTION, req.params().get(COLLECTION))
                .putString("id", req.params().get("id"));
        vertx.eventBus().send(AssetVerticle.GET, request, (Handler<Message<JsonObject>>) message -> {
            if (message.body().containsField(CONTENT_LENGTH)) {
                req.response().putHeader(CONTENT_LENGTH, message.body().getString(CONTENT_LENGTH))
                        .end(new Buffer(message.body().getBinary("asset")));
            } else {
                req.response().setStatusCode(404).end(message.body().getString(MESSAGE));
            }
        });
    }

    /**
     * @param request Request
     * @param dir     Directory
     * @param req     HTTP Request
     * @return Handler
     */
    private Handler<HttpServerFileUpload> getUploadHandler(final JsonObject request, final File dir, final HttpServerRequest req) {
        return upload -> {
            final String filename = dir.getAbsolutePath() + "/" + req.params().get("uid") + "." + FilenameUtils.getExtension(upload.filename());
            request.putString("filename", filename).putString("contentType", upload.contentType());
            if (vertx.fileSystem().existsSync(filename)) {
                vertx.fileSystem().deleteSync(filename);
            }
            upload.streamToFileSystem(filename).endHandler(event -> {
                upload.pause();
                vertx.eventBus().send(AssetVerticle.ADD, request, (Handler<Message<JsonObject>>) message -> {
                    req.response().putHeader(CONTENT_TYPE, APPLICATION_JSON)
                            .setStatusCode(message.body().getInteger("statusCode"))
                            .end(message.body().getString(MESSAGE));
                    stopTimer("main.avatar");
                });
            });
        };
    }

    /**
     * @param req        Request
     * @param busAddress address
     * @param wrapper    request wrapper
     * @return success
     */
    private boolean testRequest(HttpServerRequest req, String busAddress, RequestWrapper wrapper) {
        try {
            Rule rule = rules.get(busAddress);
            if (StringUtils.isNotBlank(rule.method())) {
                utils.testHTTPMetod(rule.method(), wrapper.getMethod());
            }
            if (rule.logged()) {
                utils.isUserLogged(wrapper);
            }
            if (rule.admin()) {
                utils.isLoggedAndAdmin(wrapper);
            }
            switch (rule.scope()) {
                case BODY:
                    utils.testMandatoryParams(wrapper.getBody(), rule.mandatoryParams());
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
        } catch (final NoSuchMethodException e) {
            LOG.error(e.getMessage(), e);
            final JsonObject jsonResp = new JsonObject();
            jsonResp.putBoolean("status", false);
            jsonResp.putString(MESSAGE, "Nothing here");
            jsonResp.putNumber("httpCode", 404);
            req.response().putHeader(CONTENT_TYPE, APPLICATION_JSON).setStatusCode(404).end(jsonResp.encode());
            return false;
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            handleError(req, e);
            return false;
        }
        return true;
    }

    /**
     * @param req request
     * @param e   exception
     */
    private static void handleError(HttpServerRequest req, QaobeeException e) {
        req.response().putHeader(CONTENT_TYPE, APPLICATION_JSON);
        req.response().setStatusCode(e.getCode().getCode());
        JsonObject jsonEx = new JsonObject(Json.encode(e));
        jsonEx.removeField("stackTrace");
        jsonEx.removeField("suppressed");
        req.response().end(jsonEx.encode());
    }

    /**
     * @param restMod Class to scan
     */
    private static void manageRules(Class<?> restMod) {
        Reflections reflections = new Reflections(restMod, new MethodAnnotationsScanner());
        reflections.getMethodsAnnotatedWith(Rule.class).forEach(m-> {
            Rule r = m.getAnnotation(Rule.class);
            if (!rules.containsKey(r.address())) {
                rules.put(r.address(), r);
                LOG.info("Registring : " + r.address());
            }
        });
    }

    /**
     * @param message Vert.X message
     * @param req     Request
     */
    private static void handleResult(AsyncResult<Message<Object>> message, HttpServerRequest req) {
        if (message.succeeded()) {
            final String response = (String) message.result().body();
            if (response.startsWith("[") || !response.startsWith("{")) {
                enableCors(req);
                req.response().putHeader(CONTENT_TYPE, APPLICATION_JSON);
                req.response().end(response);
            } else {
                final JsonObject json = new JsonObject(response);
                if (json.containsField(FILE_SERVE)) {
                    final File f = new File(json.getString(FILE_SERVE));
                    req.response().putHeader("Content-Description", "File Transfer");
                    req.response().putHeader(CONTENT_TYPE, json.getString(CONTENT_TYPE));
                    req.response().putHeader("Content-Disposition", "attachment; filename=" + f.getName());
                    req.response().putHeader("Expires", "0");
                    req.response().putHeader("Cache-Control", "must-revalidate");
                    req.response().putHeader("Pragma", "public");
                    req.response().putHeader(CONTENT_LENGTH, String.valueOf(f.length()));
                    enableCors(req);
                    req.response().sendFile(f.getAbsolutePath());
                } else {
                    enableCors(req);
                    req.response().putHeader(CONTENT_TYPE, APPLICATION_JSON);
                    req.response().end(response);
                }
            }
        } else {
            req.response().putHeader(CONTENT_TYPE, APPLICATION_JSON);
            final ReplyException ex = (ReplyException) message.cause();
            req.response().setStatusCode(404);
            enableCors(req);
            if (ex.failureCode() > 0) {
                req.response().setStatusCode(ex.failureCode());
            }
            if (ex.getMessage() != null) {
                String exStr = ex.getMessage();
                if (ex.getMessage().startsWith("{")) {
                    JsonObject jsonEx = new JsonObject(ex.getMessage());
                    jsonEx.removeField("stackTrace");
                    jsonEx.removeField("suppressed");
                    exStr = jsonEx.encode();
                }
                req.response().end(exStr);
            } else {
                manage404Error(req);
            }
        }
    }

    private static void manage404Error(HttpServerRequest req) {
        final JsonObject jsonResp = new JsonObject();
        jsonResp.putBoolean("status", false);
        jsonResp.putString(MESSAGE, "Nothing here");
        jsonResp.putNumber("httpCode", 404);
        req.response().end(jsonResp.encode());
    }


    /**
     * Enable cors.
     *
     * @param req the req
     */
    private static void enableCors(final HttpServerRequest req) {
        req.response().headers().add("Access-Control-Allow-Origin", "*");
        req.response().headers().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        req.response().headers().add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, token, uid");
    }

    /**
     * Gets rules.
     *
     * @return the rules
     */
    public static Map<String, Rule> getRules() {
        return rules;
    }
}