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
import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.api.v1.commons.settings.CountryVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.transversal.Habilitation;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
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

import static io.netty.handler.codec.http.HttpHeaders.Names.ACCEPT_LANGUAGE;

/**
 * The Class VertxJunitSupport.
 *
 * @author xavier
 */
@RunWith(VertxUnitRunner.class)
public class VertxJunitSupport implements JSDataMongoTest {
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
     * The constant BASE_URL.
     */
    protected static final String BASE_URL = "http://localhost:8888";
    protected static final long TIMEOUT = 5000L;
    /**
     * The constant config.
     */
    private static JsonObject config;
    private static final String POPULATE_WITHOUT = "without";
    private static final String POPULATE_ALL = "all";
    /**
     * The constant vertx.
     */
    protected static Vertx vertx;
    /**
     * The name.
     */
    @Rule
    public TestName name = new TestName();

    /**
     * The Mongo.
     */
    @Inject
    protected MongoDB mongo;
    /**
     * The Mongo client custom.
     */
    @Inject
    protected MongoClientCustom mongoClientCustom;
    @Inject
    @Named("mongo.db")
    private JsonObject mongoConf;

    /**
     * Start mongo server.
     */
    @BeforeClass
    public static void startMongoServer() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader(ACCEPT_LANGUAGE, LOCALE)
                .addHeader("X-qaobee-stack", "true")
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
    /**
     * Find free port.
     *
     * @return the int
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static int findFreePort() throws IOException {
        final ServerSocket server = new ServerSocket(0);
        final int port = server.getLocalPort();
        server.close();
        return port;
    }

    /**
     * Gets url.
     *
     * @param busAddress the bus address
     * @return the url
     */
    protected String getURL(String busAddress) {
        return BASE_URL + "/api/v" + busAddress.replaceAll("\\.", "/");
    }

    /**
     * Prints the info.
     */
    @Before
    public void printInfo(TestContext context) {
        Async async = context.async();
        vertx = Vertx.vertx(new VertxOptions().setBlockedThreadCheckInterval(20000).setWorkerPoolSize(50));
        vertx.exceptionHandler(context.exceptionHandler());
        FileSystem fs = vertx.fileSystem();
        config = new JsonObject(new String(fs.readFileBlocking("config.json").getBytes()));
        try {
            JunitMongoSingleton.getInstance().startServer(config);
            LOG.info("Embeded MongoDB started");

            vertx.deployVerticle(com.qaobee.hive.api.Main.class.getName(), new DeploymentOptions().setConfig(config), ar -> {
                context.assertTrue(ar.succeeded());
                vertx.exceptionHandler(context.exceptionHandler());
                Injector injector = Guice.createInjector(new GuiceTestModule(config, vertx));
                injector.injectMembers(this);
                LOG.info("About to execute : " + name.getMethodName());
                async.complete();
            });
        } catch (IOException e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
        async.await(TIMEOUT);
    }

    @After
    public void cleanDatas(TestContext context) {
        Async async = context.async();
        mongoClientCustom.getDB().getDatabase("hive").drop((res, t) -> {
            if (t != null) {
                LOG.error(t.getMessage(), t);
            }
            vertx.close();

            JunitMongoSingleton.getInstance().getProcess().stop();
        /*    vertx.close(event -> {
                if(event.failed()) {
                    LOG.error(event.cause().getMessage(), event.cause());
                }
                async.complete();
            });*/
            async.complete();

        });
        async.await(TIMEOUT * 10);
    }


    /**
     * Generate user.
     *
     * @return a user
     */
    protected Promise<User, QaobeeException, Integer> generateUser() {
        Deferred<User, QaobeeException, Integer> deferred = new DeferredObject<>();
        final User user = Json.decodeValue(config.getJsonObject("junit").getJsonObject("user").copy().encode(), User.class);
        user.getAccount().setActive(true);
        user.set_id(UUID.randomUUID().toString());
        mongo.upsert(user).done(id -> {
            if (id == null) {
                Assert.fail("user id is null");
            }
            user.set_id(id);
            deferred.resolve(user);
        }).fail(e -> Assert.fail("user id is null"));
        return deferred.promise();
    }

    /**
     * Generate logged user.
     *
     * @return the user
     */
    protected Promise<User, QaobeeException, Integer> generateLoggedUser() {
        Deferred<User, QaobeeException, Integer> deferred = new DeferredObject<>();
        User user = Json.decodeValue(config.getJsonObject("junit").getJsonObject("user").copy().encode(), User.class);
        user.set_id(UUID.randomUUID().toString());
        user.getAccount().setToken(UUID.randomUUID().toString());
        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
        user.getAccount().setActive(true);
        mongo.upsert(user).done(i -> {
            user.set_id(i);
            deferred.resolve(user);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    /**
     * Generate logged user.
     *
     * @param userId the user id
     * @return the user
     */
    protected Promise<User, QaobeeException, Integer> generateLoggedUser(String userId) {
        Deferred<User, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(userId, User.class.getSimpleName()).done(u -> {
            User user = Json.decodeValue(u.encode(), User.class);
            user.getAccount().setToken(UUID.randomUUID().toString());
            user.getAccount().setTokenRenewDate(System.currentTimeMillis());
            user.getAccount().setActive(true);
            mongo.upsert(user).done(i -> {
                user.set_id(i);
                deferred.resolve(user);
            }).fail(deferred::reject);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    /**
     * Generate logged admin user.
     *
     * @return the user
     */
    protected Promise<User, QaobeeException, Integer> generateLoggedAdminUser() {
        Deferred<User, QaobeeException, Integer> deferred = new DeferredObject<>();
        generateLoggedUser().done(user -> generateLoggedAdminUser(user.get_id()).done(deferred::resolve).fail(deferred::reject)).fail(deferred::reject);
        return deferred.promise();
    }

    /**
     * Generate logged admin user.
     *
     * @param userId the user id
     * @return the user
     */
    protected Promise<User, QaobeeException, Integer> generateLoggedAdminUser(String userId) {
        Deferred<User, QaobeeException, Integer> deferred = new DeferredObject<>();
        generateLoggedUser(userId).done(user -> {
            Habilitation habilitation = new Habilitation();
            habilitation.set_id("123456");
            habilitation.setDescription("admin Qaobee");
            habilitation.setKey(Constants.ADMIN_HABILIT);
            user.getAccount().setHabilitations(new ArrayList<>());
            user.getAccount().getHabilitations().add(habilitation);
            mongo.upsert(user).done(u -> deferred.resolve(user)).fail(deferred::reject);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    /**
     * Sendon bus.
     *
     * @param address bus address
     * @param req     request
     * @return result string
     */
    protected Promise<String, Throwable, Integer> sendOnBus(final String address, final RequestWrapper req) {
        Deferred<String, Throwable, Integer> deferred = new DeferredObject<>();
        vertx.eventBus().send(address, Json.encode(req), new DeliveryOptions().setSendTimeout(150L), ar -> {
            if (ar.succeeded()) {
                if (ar.result().body() instanceof ReplyException) {
                    try {
                        final JsonObject error = new JsonObject(((ReplyException) ar.result().body()).getMessage());
                        LOG.error(error.getString("code") + " : " + error.getString("message"), error);
                    } catch (final EncodeException e) {
                        LOG.error(((ReplyException) ar.result()).getMessage(), e);
                        Assert.fail(e.getMessage());
                    }
                    deferred.resolve(((ReplyException) ar.result().body()).getMessage());
                } else if (ar.result().body() instanceof String) {
                    deferred.resolve((String) ar.result().body());
                } else if (ar.result().body() instanceof JsonObject) {
                    deferred.resolve(((JsonObject) ar.result().body()).encode());
                } else {
                    Assert.fail("unparsable data : " + ar.result().body().toString());
                }
            } else {
                deferred.reject(ar.cause());
            }
        });
        return deferred.promise();
    }

    /**
     * Sendon bus.
     *
     * @param address the address
     * @param req     the req
     * @param token   the token
     * @return the string
     */
    protected Promise<String, Throwable, Integer> sendOnBus(String address, RequestWrapper req, String token) {
        req.getHeaders().put("token", Collections.singletonList(token));
        return sendOnBus(address, req);
    }


    /**
     * Send on bus json object.
     *
     * @param address the address
     * @param query   the query
     * @return the json object
     */
    protected Promise<JsonObject, Throwable, Integer> sendOnBus(String address, JsonObject query) {
        Deferred<JsonObject, Throwable, Integer> deferred = new DeferredObject<>();
        vertx.eventBus().send(address, query, new DeliveryOptions().setSendTimeout(5L), ar -> {
            if (ar.succeeded()) {
                if (ar.result().body() instanceof ReplyException) {
                    if (((ReplyException) ar.result().body()).getMessage().startsWith("{")) {
                        deferred.resolve(new JsonObject(((ReplyException) ar.result().body()).getMessage()));
                    }
                    Assert.fail(((ReplyException) ar.result().body()).getMessage());
                } else if (ar.result().body() instanceof JsonObject) {
                    deferred.resolve(((JsonObject) ar.result().body()));
                } else {
                    Assert.fail("unparsable data : " + ar.result().body().toString());
                }
            } else {
                deferred.reject(ar.cause());
            }
        });
        return deferred.promise();
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
     * @param id   the id
     * @param user the user
     * @return activity activity
     */
    protected Promise<JsonObject, Throwable, Integer> getActivity(String id, User user) {
        Deferred<JsonObject, Throwable, Integer> deferred = new DeferredObject<>();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constants.GET);
        req.getParams().put(ActivityVerticle.PARAM_ID, Collections.singletonList(id));
        sendOnBus(ActivityVerticle.GET, req, user.getAccount().getToken()).done(res -> deferred.resolve(new JsonObject(res))).fail(deferred::reject);
        return deferred.promise();
    }

    /**
     * Commons function for return a country JsonObject
     *
     * @param id the id
     * @return country country
     */
    protected Promise<JsonObject, Throwable, Integer> getCountry(String id) {
        Deferred<JsonObject, Throwable, Integer> deferred = new DeferredObject<>();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constants.GET);
        req.getParams().put(CountryVerticle.PARAM_ID, Collections.singletonList(id));
        sendOnBus(CountryVerticle.GET, req).done(res -> deferred.resolve(new JsonObject(res))).fail(deferred::reject);
        return deferred.promise();
    }


}
