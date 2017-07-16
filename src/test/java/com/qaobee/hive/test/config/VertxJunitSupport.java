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
import com.qaobee.hive.api.MainAPI;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.transversal.Habilitation;
import com.qaobee.hive.services.ActivityService;
import com.qaobee.hive.services.CountryService;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.parsing.Parser;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.Timeout;
import io.vertx.ext.unit.junit.VertxUnitRunner;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


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
     * The constant BASE_URL.
     */
    protected static final String BASE_URL = "http://localhost:8888";
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
    /**
     * The name.
     */
    @Rule
    public TestName name = new TestName();
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
     * Gets url.
     *
     * @param busAddress the bus address
     * @return the url
     */
    protected String getURL(String busAddress) {
        return BASE_URL + "/api/v" + busAddress.replaceAll("\\.", "/");
    }

    /**
     * Gets base url.
     *
     * @param s the s
     * @return the base url
     */
    protected static String getBaseURL(String s) {
        return BASE_URL + "/api/" + Module.VERSION + s;
    }

    /**
     * Start mongo server.
     */
    @BeforeClass
    public static void init(TestContext context) {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader(HttpHeaders.ACCEPT_LANGUAGE, LOCALE)
                .log(LogDetail.ALL)
                .addHeader("X-qaobee-stack", "true")
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * Unconfigure rest assured.
     */
    @AfterClass
    public static void unconfigureRestAssured() {
        RestAssured.reset();
    }

    /**
     * Prints the info.
     *
     * @param context the context
     */
    @Before
    public void printInfo(TestContext context) {
        Async async = context.async();
        vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        vertx.exceptionHandler(context.exceptionHandler());
        FileSystem fs = vertx.fileSystem();
        config = new JsonObject(new String(fs.readFileBlocking("config.json").getBytes())).getJsonObject("TEST");
        try {
            JunitMongoSingleton.getInstance().startServer(config);
            LOG.info("Embeded MongoDB started");

            vertx.deployVerticle(MainAPI.class.getName(), new DeploymentOptions().setConfig(config).setWorker(false), ar -> {
                if (ar.failed()) {
                    ar.cause().printStackTrace();
                }
                vertx.exceptionHandler(context.exceptionHandler());
                Injector injector = Guice.createInjector(new GuiceTestModule(config, vertx));
                injector.injectMembers(this);
                if (mongoClientCustom != null && mongoClientCustom.getDB() != null && mongoClientCustom.getDB().getDatabase("hive") != null) {
                    mongoClientCustom.getDB().getDatabase("hive").drop((res, t) -> {
                        if (t != null) {
                            LOG.error(t.getMessage(), t);
                        }
                        LOG.info("About to execute : " + name.getMethodName());
                        async.complete();
                    });
                }
            });
        } catch (IOException e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
        async.await(TIMEOUT);
    }

    /**
     * Clean datas.
     *
     * @param context the context
     */
    @After
    public void cleanDatas(TestContext context) {
        LOG.info("Closing VertX");
        vertx.close();
        JunitMongoSingleton.getInstance().getProcess().stop();
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
            mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, u -> {
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
     * Send on bus json object.
     *
     * @param address the address
     * @param query   the query
     * @return the json object
     */
    protected Future<JsonObject> sendOnBus(String address, JsonObject query) {
        Future<JsonObject> deferred = Future.future();
        vertx.eventBus().send(address, query, new DeliveryOptions().setSendTimeout(TIMEOUT), ar -> {
            if (ar.succeeded()) {
                if (ar.result().body() instanceof ReplyException) {
                    if (((ReplyException) ar.result().body()).getMessage().startsWith("{")) {
                        deferred.complete(new JsonObject(((ReplyException) ar.result().body()).getMessage()));
                    }
                    Assert.fail(((ReplyException) ar.result().body()).getMessage());
                } else if (ar.result().body() instanceof JsonObject) {
                    deferred.complete(((JsonObject) ar.result().body()));
                } else {
                    Assert.fail("unparsable data : " + ar.result().body().toString());
                }
            } else {
                deferred.fail(ar.cause());
            }
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
