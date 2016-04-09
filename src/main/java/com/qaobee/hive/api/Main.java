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
import com.englishtown.promises.Runnable;
import com.englishtown.promises.Value;
import com.qaobee.hive.api.v1.commons.utils.AssetVerticle;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.tools.Params;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.technical.vertx.ServerHook;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Future;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.EventBus;
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

import static io.netty.handler.codec.http.HttpHeaders.Names.ACCEPT_LANGUAGE;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

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

    /**
     * Start void.
     *
     * @param startedResult the started result
     */
    @Override
    public void start(final Future<Void> startedResult) {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        final Map<String, String> envs = container.env();
        final JsonObject config = container.config();
        if (envs.containsKey("OPENSHIFT_MONGODB_DB_HOST")) {
            JsonObject mongopersistor = config.getObject("mongo.persistor");
            mongopersistor.putString("host", envs.get("OPENSHIFT_MONGODB_DB_HOST"));
            mongopersistor.putNumber("port", Integer.parseInt(envs.get("OPENSHIFT_MONGODB_DB_PORT")));
            mongopersistor.putString("password", envs.get("OPENSHIFT_MONGODB_DB_PASSWORD"));
            mongopersistor.putString("username", envs.get("OPENSHIFT_MONGODB_DB_USERNAME"));
        }
        final HttpServer server = vertx.createHttpServer();

        final RouteMatcher rm = new RouteMatcher();
        final EventBus eb = vertx.eventBus();

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
        rm.get("/file/:collection/:id", new Handler<HttpServerRequest>() {
            @Override
            public void handle(final HttpServerRequest req) {
                enableCors(req);
                final JsonObject request = new JsonObject();
                request.putString(COLLECTION, req.params().get(COLLECTION));
                request.putString("id", req.params().get("id"));
                eb.send(AssetVerticle.GET, request, new Handler<Message<JsonObject>>() {
                    @Override
                    public void handle(Message<JsonObject> message) {
                        if (message.body().containsField(CONTENT_LENGTH)) {
                            req.response().putHeader(CONTENT_LENGTH, message.body().getString(CONTENT_LENGTH));
                            req.response().end(new Buffer(message.body().getBinary("asset")));
                        } else {
                            req.response().setStatusCode(404);
                            req.response().end(message.body().getString(MESSAGE));
                        }
                    }
                });
            }
        });
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
        rm.post("/file/:collection/:field/:uid", new Handler<HttpServerRequest>() {
            @Override
            public void handle(final HttpServerRequest req) {
                startTimer("main.avatar");
                enableCors(req);
                final JsonObject request = new JsonObject();
                request.putString(COLLECTION, req.params().get(COLLECTION));
                request.putString("field", req.params().get("field"));
                request.putString("uid", req.params().get("uid"));
                request.putString("token", req.headers().get("token"));
                request.putString("locale", req.headers().get(ACCEPT_LANGUAGE));

                // We first pause the request so we don't receive any data between now and when the file is opened
                String datadir = System.getProperty("user.home");
                if (envs.containsKey("OPENSHIFT_DATA_DIR")) {
                    datadir = envs.get("OPENSHIFT_DATA_DIR");
                }
                final File dir = new File(datadir + "/upload");
                if (!dir.exists()) {
                    boolean res = dir.mkdirs();
                    LOG.debug("Creating " + dir.getAbsolutePath() + " result : " + res);
                }
                request.putString("datadir", datadir);
                req.expectMultiPart(true);
                req.uploadHandler(new Handler<HttpServerFileUpload>() {
                    @Override
                    public void handle(final HttpServerFileUpload upload) {
                        final String filename = new StringBuilder().append(dir.getAbsolutePath()).append("/").append(req.params().get("uid")).append(".")
                                .append(FilenameUtils.getExtension(upload.filename())).toString();
                        LOG.debug("---->" + filename);
                        request.putString("filename", filename);
                        request.putString("contentType", upload.contentType());
                        LOG.debug("filename : " + filename);
                        if(vertx.fileSystem().existsSync(filename)) {
                            vertx.fileSystem().deleteSync(filename);
                        }
                        upload.streamToFileSystem(filename);
                        upload.endHandler(new Handler<Void>() {
                            @Override
                            public void handle(Void event) {
                                upload.pause();
                                eb.send(AssetVerticle.ADD, request, new Handler<Message<JsonObject>>() {
                                    @Override
                                    public void handle(Message<JsonObject> message) {
                                        req.response().putHeader(CONTENT_TYPE, APPLICATION_JSON);
                                        req.response().setStatusCode(message.body().getInteger("statusCode"));
                                        req.response().end(message.body().getString(MESSAGE));
                                        stopTimer("main.avatar");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }).optionsWithRegEx(".*", new Handler<HttpServerRequest>() {
            @Override
            public void handle(final HttpServerRequest req) {
                if (config.getObject("cors").getBoolean("enabled", false)) {
                    enableCors(req);
                }
                req.response().end();
            }
        });

        // Cors

        rm.get("/", new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest event) {
                event.response().end("Welcome to Qaobee Hive");
            }
        });
        // API Rest
        rm.allWithRegEx("^/api/.*", new Handler<HttpServerRequest>() {
            @Override
            public void handle(final HttpServerRequest req) {
                req.bodyHandler(new Handler<Buffer>() {
                    @Override
                    public void handle(final Buffer event) {

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
                        LOG.debug(path.toString());
                        // Remontée de métriques : nombre de requêtes
                        final JsonObject json = new JsonObject();
                        json.putString("name", "meter." + StringUtils.join(wrapper.getPath(), '.'));
                        json.putString("action", "mark");
                        eb.send("metrix", json);
                        String busAddress = StringUtils.join(path, '.');
                        eb.sendWithTimeout(config.getObject("runtime").getInteger("version") + "." + busAddress, Json.encode(wrapper), Constantes.TIMEOUT, new Handler<AsyncResult<Message<String>>>() {

                            @Override
                            public void handle(final AsyncResult<Message<String>> message) {
                                stopTimer(StringUtils.join(wrapper.getPath(), '.'));
                                handleResult(message, req);
                            }

                        });
                    }
                });
            }
        });

        final List<Promise<String, Void>> promises = new ArrayList<>();
        // Loading modules
        promises.add(whenContainer.deployWorkerVerticle(com.bloidonia.vertx.metrics.MetricsModule.class.getCanonicalName(), config.getObject("metrix.mod"), 1, true));
        promises.add(whenContainer.deployWorkerVerticle(Mailer.class.getCanonicalName(), config.getObject("mailer.mod"), 1, true));
        // Loading Verticles
        final Set<Class<?>> restModules = DeployableVerticle.VerticleLoader.scanPackage(getClass().getPackage().getName());
        for (final Class<?> restMod : restModules) {
            if (restMod.getAnnotation(DeployableVerticle.class).isWorker())
                promises.add(whenContainer.deployWorkerVerticle(restMod.getName(), config, 2, true));
            else
                promises.add(whenContainer.deployVerticle(restMod.getName(), config));
        }
        when.all(promises, new com.englishtown.promises.Runnable<Promise<List<String>, Void>, List<String>>() {
            @Override
            public Promise<List<String>, Void> run(final List<String> value) {
                server.requestHandler(rm);
                String ip = Params.getString("defaultHost");
                int port = Integer.parseInt(Params.getString("defaultPort"));
                if (envs.containsKey("OPENSHIFT_VERTX_IP")) {
                    ip = envs.get("OPENSHIFT_VERTX_IP");
                }
                if (envs.containsKey("OPENSHIFT_VERTX_PORT")) {
                    port = Integer.parseInt(envs.get("OPENSHIFT_VERTX_PORT"));
                }

                sockJSServer = vertx.createSockJSServer(server);
                JsonObject config = new JsonObject().putString("prefix", "/eventbus");

                JsonArray outboundPermitted = new JsonArray();
                JsonArray inboundPermitted = new JsonArray();
                outboundPermitted.add(new JsonObject());
                inboundPermitted.add(new JsonObject().putObject("match", new JsonObject().putString("secret", UUID.randomUUID().toString())));
                sockJSServer.setHook(new ServerHook());
                sockJSServer.bridge(config, inboundPermitted, outboundPermitted);
                server.listen(port, ip);
                LOG.info("The http server is started");
                startedResult.setResult(null);
                return null;
            }
        }, new Runnable<Promise<List<String>, Void>, Value<List<String>>>() {
            @Override
            public Promise<List<String>, Void> run(final Value<List<String>> value) {
                LOG.error(value.error.getMessage());
                return null;
            }
        });
    }

    private void handleResult(AsyncResult<Message<String>> message, HttpServerRequest req) {
        if (message.succeeded()) {
            final String response = message.result().body();
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
            enableCors(req);
            if (ex.failureCode() > 0) {
                req.response().setStatusCode(ex.failureCode());
            }
            if (ex.getMessage() != null) {
                String exStr = ex.getMessage();
                if(ex.getMessage().startsWith("{")) {
                    JsonObject jsonEx = new JsonObject(ex.getMessage());
                    if (jsonEx.containsField("stackTrace")) {
                        jsonEx.removeField("stackTrace");
                    }
                    exStr = jsonEx.encode();
                }
                req.response().end(exStr);
            } else {
                final JsonObject jsonResp = new JsonObject();
                jsonResp.putBoolean("status", false);
                jsonResp.putString(MESSAGE, "Nothing here");
                jsonResp.putNumber("httpCode", 404);
                req.response().setStatusCode(404);
                req.response().end(jsonResp.encode());
            }
        }
    }


    /**
     * Enable cors.
     *
     * @param req the req
     */
    private void enableCors(final HttpServerRequest req) {
        req.response().headers().add("Access-Control-Allow-Origin", "*");
        req.response().headers().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        req.response().headers().add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, token, uid");
    }
}