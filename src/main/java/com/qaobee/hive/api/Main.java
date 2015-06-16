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
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.tools.Params;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.technical.vertx.utils.AssetVerticle;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
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
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.io.File;
import java.util.*;

/**
 * The Class Main.
 *
 * @author Xavier.Marin
 */
public class Main extends AbstractGuiceVerticle {

    /**
     * The Constant FILE_SERVE.
     */
    public static final String FILE_SERVE = "fileserve";
    /**
     * The Constant CONTENT_TYPE.
     */
    public static final String CONTENT_TYPE = "contenttype";

    /**
     * The Utils.
     */
    @Inject
    private Utils utils;

    /**
     * Start void.
     *
     * @param startedResult the started result
     */
    @Override
    public void start(final Future<Void> startedResult) {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");
        final Map<String, String> envs = container.env();
        final JsonObject config = container.config();
        if (envs.containsKey("OPENSHIFT_MONGODB_DB_HOST")) {
            config.getObject("mongo.persistor").putString("host", envs.get("OPENSHIFT_MONGODB_DB_HOST"));
            config.getObject("mongo.persistor").putNumber("port", Integer.parseInt(envs.get("OPENSHIFT_MONGODB_DB_PORT")));
            config.getObject("mongo.persistor").putString("password", envs.get("OPENSHIFT_MONGODB_DB_PASSWORD"));
            config.getObject("mongo.persistor").putString("username", envs.get("OPENSHIFT_MONGODB_DB_USERNAME"));
        }
        final HttpServer server = vertx.createHttpServer();
        final RouteMatcher rm = new RouteMatcher();
        final EventBus eb = vertx.eventBus();


        rm.get("/file/:collection/:id", new Handler<HttpServerRequest>() {
            @Override
            public void handle(final HttpServerRequest req) {
                enableCors(req);
                final JsonObject request = new JsonObject();
                request.putString("collection", req.params().get("collection"));
                request.putString("id", req.params().get("id"));
                eb.send(AssetVerticle.GET, request, new Handler<Message<JsonObject>>() {
                    @Override
                    public void handle(Message<JsonObject> message) {
                        if (message.body().containsField("Content-Length")) {
                            req.response().putHeader("Content-Length", message.body().getString("Content-Length"));
                            req.response().end(new Buffer(message.body().getBinary("asset")));
                        } else {
                            req.response().setStatusCode(message.body().getInteger("statusCode"));
                            req.response().end(message.body().getString("message"));
                        }
                    }
                });
            }
        });
        /**
         * @apiDescription Update d'un user avec son avatar
         * @api {post} /file/avatar file.avatar
         * @apiName file/:collection/:field/:uid
         * @apiGroup Main
         * @apiParam {String} uid user id
         * @apiparam {File} file avatar
         * @apiHeader {String} token
         * @apiSuccess {Object} obj
         * @apiError NOT_LOGGED user not logged
         */
        rm.post("/file/:collection/:field/:uid", new Handler<HttpServerRequest>() {
            @Override
            public void handle(final HttpServerRequest req) {
                startTimer("main.avatar");
                enableCors(req);
                final JsonObject request = new JsonObject();
                request.putString("collection", req.params().get("collection"));
                request.putString("field", req.params().get("field"));
                request.putString("uid", req.params().get("uid"));
                request.putString("token", req.headers().get("token"));
                request.putString("locale", req.headers().get("Accept-Language"));

                // We first pause the request so we don't receive any data between now and when the file is opened
                String datadir = System.getProperty("user.home");
                if (envs.containsKey("OPENSHIFT_DATA_DIR")) {
                    datadir = envs.get("OPENSHIFT_DATA_DIR");
                }
                final File dir = new File(datadir + "/upload");
                if (!dir.exists()) {
                    boolean res = dir.mkdirs();
                    container.logger().debug("Creating " + dir.getAbsolutePath() + " result : " + res);
                }
                request.putString("datadir", datadir);
                req.expectMultiPart(true);
                req.uploadHandler(new Handler<HttpServerFileUpload>() {
                    @Override
                    public void handle(final HttpServerFileUpload upload) {
                        final String filename = dir.getAbsolutePath() + "/" + req.params().get("uid") + "." + FilenameUtils.getExtension(upload.filename());
                        System.out.println("---->" + filename);
                        request.putString("filename", filename);
                        request.putString("contentType", upload.contentType());
                        container.logger().info("filename : " + filename);
                        FileUtils.deleteQuietly(new File(filename));

                        upload.streamToFileSystem(filename);
                        upload.endHandler(new Handler<Void>() {
                            @Override
                            public void handle(Void event) {
                                eb.send(AssetVerticle.ADD, request, new Handler<Message<JsonObject>>() {
                                    @Override
                                    public void handle(Message<JsonObject> message) {
                                        req.response().setStatusCode(message.body().getInteger("statusCode"));
                                        req.response().end(message.body().getString("message"));
                                        stopTimer("main.avatar");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        // Cors
        rm.optionsWithRegEx(".*", new Handler<HttpServerRequest>() {
            @Override
            public void handle(final HttpServerRequest req) {
                if (config.getObject("cors").getBoolean("enabled", false)) {
                    enableCors(req);
                }
                req.response().end();
            }
        });
        // API Rest
        rm.allWithRegEx("^/rest/.*", new Handler<HttpServerRequest>() {
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
                        wrapper.setLocale(req.headers().get("Accept-Language"));
                        List<String> path = Arrays.asList(req.path().split("/"));

                        path = path.subList(2, path.size());
                        wrapper.setPath(path);
                        startTimer(StringUtils.join(wrapper.getPath(), '.'));
                        container.logger().debug(path);
                        // Remontée de métriques : nombre de requêtes
                        final JsonObject json = new JsonObject();
                        json.putString("name", "meter." + StringUtils.join(wrapper.getPath(), '.'));
                        json.putString("action", "mark");
                        eb.send("metrix", json);

                        String busAddress = StringUtils.join(path, '.');

                        if (Arrays.asList("prive", "admin").contains(path.get(0))) {
                            busAddress = path.get(0);
                        }
                        eb.sendWithTimeout("resthandler." + busAddress, Json.encode(wrapper), Constantes.TIMEOUT, new Handler<AsyncResult<Message<String>>>() {

                            @Override
                            public void handle(final AsyncResult<Message<String>> message) {
                                stopTimer(StringUtils.join(wrapper.getPath(), '.'));
                                if (message.succeeded()) {
                                    final String response = message.result().body();
                                    if (response.startsWith("[") || !response.startsWith("{")) {
                                        enableCors(req);
                                        req.response().end(response);
                                    } else {
                                        final JsonObject json = new JsonObject(response);
                                        if (json.containsField(FILE_SERVE)) {
                                            // TODO : externaliser dans le service File
                                            final File f = new File(json.getString(FILE_SERVE));
                                            req.response().putHeader("Content-Description", "File Transfer");
                                            req.response().putHeader("Content-Type", json.getString(CONTENT_TYPE));
                                            req.response().putHeader("Content-Disposition", "attachment; filename=" + f.getName());
                                            req.response().putHeader("Expires", "0");
                                            req.response().putHeader("Cache-Control", "must-revalidate");
                                            req.response().putHeader("Pragma", "public");
                                            req.response().putHeader("Content-Length", String.valueOf(f.length()));
                                            enableCors(req);
                                            req.response().sendFile(f.getAbsolutePath());
                                        } else {
                                            enableCors(req);
                                            req.response().end(response);
                                        }
                                    }
                                } else {
                                    final ReplyException ex = (ReplyException) message.cause();
                                    enableCors(req);
                                    container.logger().error(ex.getMessage(), ex);
                                    if (ex.failureCode() > 0) {
                                        req.response().setStatusCode(ex.failureCode());
                                    }
                                    if (ex.getMessage() != null) {
                                        req.response().end(ex.getMessage());
                                    } else {
                                        final JsonObject jsonResp = new JsonObject();
                                        jsonResp.putBoolean("status", false);
                                        jsonResp.putString("message", ex.failureType().name());
                                        req.response().setStatusCode(404);
                                        req.response().end(jsonResp.encode());
                                    }
                                }
                            }

                        });
                    }
                });
            }
        });

        final List<Promise<String, Void>> promises = new ArrayList<>();
        // Loading modules
        promises.add(whenContainer.deployWorkerVerticle(com.bloidonia.vertx.metrics.MetricsModule.class.getCanonicalName(), config.getObject("metrix.mod"), 1, true));
        // Loading Verticles
        final Set<Class<?>> restModules = DeployableVerticle.VerticleLoader.scanPackage(getClass().getPackage().getName());
        for (final Class<?> restMod : restModules) {
            promises.add(whenContainer.deployWorkerVerticle(restMod.getName(), config, 1, true));
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
                server.listen(port, ip);

                container.logger().info("The http server is started");
                startedResult.setResult(null);
                return null;
            }
        }, new Runnable<Promise<List<String>, Void>, Value<List<String>>>() {
            @Override
            public Promise<List<String>, Void> run(final Value<List<String>> value) {
                System.out.println(value.error.getMessage());
                return null;
            }
        });

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