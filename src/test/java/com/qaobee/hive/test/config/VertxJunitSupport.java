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
package com.qaobee.hive.test.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.transversal.Habilitation;
import com.qaobee.hive.services.ActivityService;
import com.qaobee.hive.services.CountryService;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.utils.MongoClientCustom;
import com.qaobee.hive.technical.utils.guice.GuiceModule;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.vertx.core.*;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.Timeout;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.apache.http.HttpHeaders;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.*;


/**
 * The Class VertxJunitSupport.
 *
 * @author xavier
 */
@RunWith(VertxUnitRunner.class)
public class VertxJunitSupport implements JSDataMongoTest {
    /**
     * The constant LOG.
     */
    protected static final Logger LOG = LoggerFactory.getLogger(VertxJunitSupport.class);
    /**
     * The constant LOCALE.
     */
    private static final String LOCALE = "fr_FR";
    /**
     * The constant CODE.
     */
    protected static final String CODE = "code";
    /**
     * The constant TOKEN.
     */
    protected static final String TOKEN = "token";
    /**
     * The constant STATUS.
     */
    protected static final String STATUS = "status";
    /**
     * The constant POPULATE_ONLY.
     */
    protected static final String POPULATE_ONLY = "only";
    /**
     * The constant TIMEOUT.
     */
    protected static final long TIMEOUT = 5000L;
    private static JsonObject config;
    private static final String POPULATE_WITHOUT = "without";
    private static final String POPULATE_ALL = "all";
    /**
     * The constant vertx.
     */
    protected static Vertx vertx;
    private static int port;
    /**
     * The name.
     */
    @Rule
    public TestName name = new TestName();
    /**
     * The Global timeout.
     */
    @Rule
    public Timeout globalTimeout = Timeout.millis(TIMEOUT);
    /**
     * The Mongo.
     */
    @Inject
    protected MongoDB mongo;
    @Inject
    private MongoClientCustom mongoClientCustom;
    @Inject
    @Named("mongo.db")
    private JsonObject mongoConf;
    @Inject
    private ActivityService activityService;
    @Inject
    private CountryService countryService;

    /**
     * Gets root url.
     *
     * @return the root url
     */
    protected static String getRootURL() {
        return "http://localhost:" + port;
    }

    /**
     * Gets base url.
     *
     * @param s the s
     * @return the base url
     */
    protected static String getBaseURL(String s) {
        return getRootURL() + "/api/" + Module.VERSION + s;
    }

    /**
     * Start mongo server.
     *
     * @param context the context
     */
    @BeforeClass
    public static void init(TestContext context) {
        Async async = context.async();
        try {
            port = findFreePort();
            RestAssured.defaultParser = Parser.JSON;
            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .addHeader(HttpHeaders.ACCEPT_LANGUAGE, LOCALE)
                    .addHeader("X-qaobee-stack", "true")
                    .build();
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

            vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(150));
            FileSystem fs = vertx.fileSystem();
            config = new JsonObject(new String(fs.readFileBlocking("config.json").getBytes())).getJsonObject("TEST");
            JunitMongoSingleton.getInstance().startServer(config);
            LOG.info("Embeded MongoDB started");

            Injector injector = Guice.createInjector(new GuiceModule(config, vertx));
            ProxyService.Loader.load("com.qaobee.hive.services.impl", injector, vertx);
            final Router router = Router.router(vertx);
            router.route().handler(BodyHandler.create());
            router.route().path("/*").produces("application/json").handler(c -> {
                c.response().putHeader(io.vertx.core.http.HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
                        .putHeader(io.vertx.core.http.HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0, must-revalidate")
                        .putHeader("Pragma", "no-cache")
                        .putHeader(io.vertx.core.http.HttpHeaders.EXPIRES, "0");
                c.next();
            });
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
            router.route().last().handler(c -> {
                final JsonObject jsonResp = new JsonObject()
                        .put(Constants.STATUS, false)
                        .put("message", "Nothing here")
                        .put("httpCode", 404);
                c.response().putHeader(io.vertx.core.http.HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON).setStatusCode(404).end(jsonResp.encode());
            });

            // Load Verticles
            runWebServer(loadVerticles(), router, async, context);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
        async.await(TIMEOUT);
    }

    private static int findFreePort() throws IOException {
        final ServerSocket server = new ServerSocket(0);
        final int port = server.getLocalPort();
        server.close();
        return port;
    }


    private static void runWebServer(List<Future> futures, Router router, Async async, TestContext context) {
        CompositeFuture.all(futures).setHandler(rs -> {
            if (rs.succeeded()) {

                final HttpServer server = vertx.createHttpServer();
                server.requestHandler(router::accept);
                String ip = "0.0.0.0";
                SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
                sockJSHandler.bridge(new BridgeOptions());
                router.route("/eventbus/*").handler(sockJSHandler);
                server.listen(port, ip);
                LOG.info("The http server is started on : {} : {}", ip, port);
                async.complete();
            } else {
                LOG.error(rs.cause().getMessage(), rs.cause());
                context.fail(rs.cause());
            }
        });
    }

    private static List<Future> loadVerticles() {
        List<Future> promises = new ArrayList<>();
        // Loading Verticles
        final Set<Class<?>> restModules = DeployableVerticle.VerticleLoader.scanPackage("com.qaobee.hive.verticles");
        restModules.addAll(DeployableVerticle.VerticleLoader.scanPackage("com.qaobee.hive.verticles"));
        restModules.forEach(restMod -> {
            Future<String> deferred = Future.future();
            promises.add(deferred);
            vertx.deployVerticle(restMod.getName(), new DeploymentOptions()
                    .setConfig(config)
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

    /**
     * Unconfigure rest assured.
     *
     * @param context the context
     */
    @AfterClass
    public static void unconfigureRestAssured(TestContext context) {
        RestAssured.reset();
        JunitMongoSingleton.getInstance().getProcess().stop();
        LOG.info("Closing VertX");
        vertx.close(context.asyncAssertSuccess());
    }

    /**
     * Prints the info.
     *
     * @param context the context
     */
    @Before
    public void printInfo(TestContext context) {
        Async async = context.async();
        Injector injector = Guice.createInjector(new GuiceTestModule(config, vertx));
        injector.injectMembers(this);
        if (mongoClientCustom != null && mongoClientCustom.getDB() != null && mongoClientCustom.getDB().getDatabase("hive") != null) {
            mongoClientCustom.getDB().getDatabase("hive").drop((res, t) -> {
                if (t != null) {
                    LOG.error(t.getMessage(), t);
                    context.fail(t);
                } else {
                    LOG.info("About to execute : " + name.getMethodName());
                    async.complete();
                }
            });
        }
        async.await(TIMEOUT);
    }

    /**
     * Generate user.
     *
     * @return a user
     */
    protected Future<User> generateUser() {
        Future<User> deferred = Future.future();
        final User user = Json.decodeValue(config.getJsonObject("junit").getJsonObject("user").copy().encode(), User.class);
        user.getAccount().setActive(true);
        user.set_id(UUID.randomUUID().toString());
        mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, id -> {
            if (id.succeeded()) {
                user.set_id(id.result());
                deferred.complete(user);
            } else {
                Assert.fail(id.cause().getMessage());
            }
        });
        return deferred;
    }

    /**
     * Generate logged user.
     *
     * @return the user
     */
    protected Future<User> generateLoggedUser() {
        Future<User> deferred = Future.future();
        User user = Json.decodeValue(config.getJsonObject("junit").getJsonObject("user").copy().encode(), User.class);
        user.set_id(UUID.randomUUID().toString());
        user.getAccount().setToken(UUID.randomUUID().toString());
        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
        user.getAccount().setActive(true);
        mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, id -> {
            if (id.succeeded()) {
                user.set_id(id.result());
                deferred.complete(user);
            } else {
                Assert.fail(id.cause().getMessage());
            }
        });
        return deferred;
    }

    /**
     * Generate logged user.
     *
     * @param userId the user id
     * @return the user
     */
    protected Future<User> generateLoggedUser(String userId) {
        Future<User> deferred = Future.future();
        mongo.getById(userId, DBCollections.USER, u -> {
            if (u.succeeded()) {
                User user = Json.decodeValue(u.result().encode(), User.class);
                user.getAccount().setToken(UUID.randomUUID().toString());
                user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                user.getAccount().setActive(true);
                mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, id -> {
                    if (id.succeeded()) {
                        user.set_id(userId);
                        deferred.complete(user);
                    } else {
                        Assert.fail(id.cause().getMessage());
                    }
                });
            } else {
                Assert.fail(u.cause().getMessage());
            }
        });
        return deferred;
    }

    /**
     * Generate logged admin user.
     *
     * @return the user
     */
    protected Future<User> generateLoggedAdminUser() {
        Future<User> deferred = Future.future();
        generateLoggedUser().setHandler(user -> generateLoggedAdminUser(user.result().get_id())
                .setHandler(r -> deferred.complete(r.result())));
        return deferred;
    }

    /**
     * Generate logged admin user.
     *
     * @param userId the user id
     * @return the user
     */
    protected Future<User> generateLoggedAdminUser(String userId) {
        Future<User> deferred = Future.future();
        generateLoggedUser(userId).setHandler(user -> {
            Habilitation habilitation = new Habilitation();
            habilitation.set_id("123456");
            habilitation.setDescription("admin Qaobee");
            habilitation.setKey(Constants.ADMIN_HABILIT);
            user.result().getAccount().setHabilitations(new ArrayList<>());
            user.result().getAccount().getHabilitations().add(habilitation);
            mongo.upsert(new JsonObject(Json.encode(user.result())), DBCollections.USER, u -> {
                if (u.succeeded()) {
                    deferred.complete(user.result());
                } else {
                    Assert.fail(u.cause().getMessage());
                }
            });
        });
        return deferred;
    }

    /**
     * Populates the test base.
     *
     * @param populateType (String) : POPULATE_ONLY, POPULATE_WITHOUT, POPULATE_ALL
     * @param mongoFiles   (String[]) : array of filenames
     */
    protected void populate(String populateType, String... mongoFiles) {
        populate(populateType, "", mongoFiles);
    }

    /**
     * Populates the test base. It is not needed to indicate the subdirectory name, the function will search in all directories
     * of "scripts/mongo".
     *
     * @param populateType      (String) : POPULATE_ONLY, POPULATE_WITHOUT, POPULATE_ALL
     * @param relativeDirectory (String) : relative dir from "scripts/mongo"
     * @param mongoFiles        (String[]) : array of filenames
     */
    private void populate(String populateType, String relativeDirectory, String... mongoFiles) {
        File[] listFiles = new File("scripts/mongo" + relativeDirectory).listFiles();
        if (listFiles != null && listFiles.length > 0) {
            List<String> mongoFilesList = new ArrayList<>();
            if (mongoFiles.length > 0) {
                mongoFilesList = Arrays.asList(mongoFiles);
            }
            for (File scriptMongo : listFiles) {
                if (scriptMongo.isDirectory()) {
                    String dirPath = scriptMongo.getAbsolutePath();
                    dirPath = dirPath.substring(dirPath.indexOf("scripts/mongo") + "scripts/mongo".length());
                    populate(populateType, dirPath, mongoFiles);
                    continue;
                }
                // all
                if (POPULATE_ALL.equals(populateType)) {
                    // OK
                    LOG.debug("OK");
                } else if (POPULATE_ONLY.equals(populateType) && !mongoFilesList.contains(scriptMongo.getName())) {
                    continue;
                } else if (POPULATE_WITHOUT.equals(populateType) && mongoFilesList.contains(scriptMongo.getName())) {
                    continue;
                }
                //  try {
                LOG.info("Populating " + scriptMongo.getName());
                String command = "mongo " +
                        mongoConf.getString("host") + ":" + mongoConf.getInteger("port") + "/" + mongoConf.getString("db_name")
                        + " " + scriptMongo.getAbsolutePath();
                LOG.info(executeCommand(command));
            }
        }
    }

    private String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return output.toString();

    }

    /**
     * Commons function for return a country JsonObject
     *
     * @param id the id
     * @return the activity
     */
    protected Future<JsonObject> getActivity(String id) {
        Future<JsonObject> d = Future.future();
        activityService.getActivity(id, ar -> {
            if (ar.succeeded()) {
                d.complete(ar.result());
            } else {
                Assert.fail(ar.cause().getMessage());
            }
        });
        return d;
    }

    /**
     * Commons function for return a country JsonObject
     *
     * @param id the id
     * @return the country
     */
    protected Future<JsonObject> getCountry(String id) {
        Future<JsonObject> d = Future.future();
        countryService.getCountry(id, ar -> {
            if (ar.succeeded()) {
                d.complete(ar.result());
            } else {
                Assert.fail(ar.cause().getMessage());
            }
        });
        return d;
    }
}
