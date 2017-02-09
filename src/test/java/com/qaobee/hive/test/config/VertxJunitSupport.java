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
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.parsing.Parser;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.api.v1.commons.settings.CountryVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.transversal.Habilitation;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.ReplyException;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;
import org.vertx.java.test.TestModule;
import org.vertx.java.test.VertxConfiguration;
import org.vertx.java.test.VertxTestBase;
import org.vertx.java.test.junit.VertxJUnit4ClassRunner;
import org.vertx.java.test.utils.DeploymentUtils;
import org.vertx.java.test.utils.QueueReplyHandler;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpHeaders.Names.ACCEPT_LANGUAGE;

/**
 * The Class VertxJunitSupport.
 *
 * @author xavier
 */
@RunWith(VertxJUnit4ClassRunner.class)
@VertxConfiguration(modsDir = "build/mods")
@TestModule(name = "com.qaobee~hive~0.1", jsonConfig = "file:config.json")
public class VertxJunitSupport extends VertxTestBase implements JSDataMongoTest {
    /**
     * The constant LOCALE.
     */
    protected static final String LOCALE = "fr_FR";
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
     * The constant moduleConfig.
     */
    protected static JsonObject moduleConfig;
    private static final Logger LOG = LoggerFactory.getLogger(VertxJunitSupport.class);
    private static final String POPULATE_WITHOUT = "without";
    private static final String POPULATE_ALL = "all";
    private final LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
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
     * Start mongo server.
     */
    @BeforeClass
    public static void startMongoServer() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder().addHeader(ACCEPT_LANGUAGE, LOCALE).build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        moduleConfig = DeploymentUtils.getJsonConfig(VertxJunitSupport.class.getAnnotation(TestModule.class).jsonConfig());
        try {
            JunitMongoSingleton.getInstance().startServer(moduleConfig);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Stop all.
     */
    @AfterClass
    public static void stopAll() {
        JunitMongoSingleton.getInstance().getProcess().stop();
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
    public void printInfo() {
        Injector injector = Guice.createInjector(new GuiceTestModule(moduleConfig));
        injector.injectMembers(this);
        System.out.println("About to execute : " + name.getMethodName());
        mongo.getDb().dropDatabase();
    }


    /**
     * Generate user.
     *
     * @return a user
     */
    protected User generateUser() {
        final User user = Json.decodeValue(moduleConfig.getObject("junit").getObject("user").copy().encode(), User.class);
        user.getAccount().setActive(true);
        user.set_id(UUID.randomUUID().toString());
        try {
            final String id = mongo.save(user);
            if (id == null) {
                Assert.fail("user id is null");
            }
            user.set_id(id);
        } catch (EncodeException | QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        return user;
    }


    /**
     * Logged user user.
     *
     * @param id the id
     * @return the user
     */
    protected User loggedUser(String id) {
        User user = null;
        try {
            user = Json.decodeValue(mongo.getById(id, User.class.getSimpleName()).encode(), User.class);
            user.getAccount().setToken(UUID.randomUUID().toString());
            user.getAccount().setTokenRenewDate(System.currentTimeMillis());
            user.getAccount().setActive(true);
            mongo.save(user);
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
        return user;
    }

    /**
     * Generate logged user.
     *
     * @return the user
     */
    protected User generateLoggedUser() {
        try {
            User user = Json.decodeValue(moduleConfig.getObject("junit").getObject("user").copy().encode(), User.class);
            user.set_id(UUID.randomUUID().toString());
            final String id = mongo.save(user);
            if (id == null) {
                Assert.fail("user id is null");
            } else {
                user.set_id(id);
            }
            return generateLoggedUser(user.get_id());
        } catch (EncodeException | QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        return null;
    }

    /**
     * Generate logged user.
     *
     * @param userId the user id
     * @return the user
     */
    protected User generateLoggedUser(String userId) {
        try {
            User user = Json.decodeValue(mongo.getById(userId, DBCollections.USER).encode(), User.class);
            user.set_id(userId);
            user.getAccount().setToken(UUID.randomUUID().toString());
            user.getAccount().setTokenRenewDate(System.currentTimeMillis());
            user.getAccount().setActive(true);
            final String id = mongo.save(user);
            if (id == null) {
                Assert.fail("user id is null");
            } else {
                user.set_id(id);
            }
            return user;
        } catch (EncodeException | QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        return null;
    }

    /**
     * Generate logged admin user.
     *
     * @return the user
     */
    protected User generateLoggedAdminUser() {
        return generateLoggedAdminUser(generateLoggedUser().get_id());
    }

    /**
     * Generate logged admin user.
     *
     * @param userId the user id
     * @return the user
     */
    protected User generateLoggedAdminUser(String userId) {
        User user = generateLoggedUser(userId);
        Habilitation habilitation = new Habilitation();
        habilitation.set_id("123456");
        habilitation.setDescription("admin Qaobee");
        habilitation.setKey(Constants.ADMIN_HABILIT);
        user.getAccount().setHabilitations(new ArrayList<>());
        user.getAccount().getHabilitations().add(habilitation);
        try {
            mongo.save(user);
        } catch (EncodeException | QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        return user;
    }

    /**
     * Sendon bus.
     *
     * @param address bus address
     * @param req     request
     * @return result string
     */
    protected String sendOnBus(final String address, final RequestWrapper req) {
        long timeout = 150L;
        getEventBus().send(address, Json.encode(req), new QueueReplyHandler<Object>(queue, timeout));
        try {
            final Object result = queue.poll(timeout, TimeUnit.SECONDS);
            if (result instanceof ReplyException) {
                try {
                    final JsonObject error = new JsonObject(((ReplyException) result).getMessage());
                    LOG.error(error.getString("code") + " : " + error.getString("message"), error);
                } catch (final EncodeException e) {
                    LOG.error(((ReplyException) result).getMessage(), e);
                    Assert.fail(e.getMessage());
                }
                return ((ReplyException) result).getMessage();
            } else if (result instanceof String) {
                return (String) result;
            } else if (result instanceof JsonObject) {
                return ((JsonObject) result).encode();
            } else {
                Assert.fail("unparsable data : " + result.toString());
            }
        } catch (final InterruptedException e) {
            Assert.fail(e.getMessage());
        }
        return null;
    }

    /**
     * Sendon bus.
     *
     * @param address the address
     * @param req     the req
     * @param token   the token
     * @return the string
     */
    protected String sendOnBus(String address, RequestWrapper req, String token) {
        if (req.getHeaders() == null) {
            req.setHeaders(new HashMap<>());
        }
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
    protected JsonObject sendOnBus(String address, JsonObject query) {
        long timeout = 5L;
        getEventBus().send(address, query, new QueueReplyHandler<Object>(queue, timeout));
        try {
            final Object result = queue.poll(timeout, TimeUnit.SECONDS);
            if (result instanceof ReplyException) {
                if (((ReplyException) result).getMessage().startsWith("{")) {
                    return new JsonObject(((ReplyException) result).getMessage());
                }
                Assert.fail(((ReplyException) result).getMessage());
            } else if (result instanceof JsonObject) {
                return (JsonObject) result;
            } else {
                Assert.fail("unparsable data : " + result.toString());
            }
        } catch (final InterruptedException e) {
            Assert.fail(e.getMessage());
        }
        return null;
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
        for (String s : mongoFiles) {
            LOG.info("Populating " + s);
        }
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
                    getContainer().logger().debug("OK");
                } else if (POPULATE_ONLY.equals(populateType) && !mongoFilesList.contains(scriptMongo.getName())) {
                    continue;
                } else if (POPULATE_WITHOUT.equals(populateType) && mongoFilesList.contains(scriptMongo.getName())) {
                    continue;
                }
                BasicDBObject obj = new BasicDBObject();
                try {
                    obj.append("eval", FileUtils.readFileToString(scriptMongo, Charset.forName("UTF-8")) + "\n");
                    CommandResult res = mongo.getDb().command(obj);
                    if (!res.ok()) {
                        Assert.fail("[ " + scriptMongo.getAbsolutePath() + " ]" + res.getErrorMessage());
                    }
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    Assert.fail(e.getMessage());
                }
            }
        }

    }

    /**
     * Commons function for return a country JsonObject
     *
     * @param id   the id
     * @param user the user
     * @return activity activity
     */
    protected JsonObject getActivity(String id, User user) {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constants.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        /* Retreive object */
        params.put(ActivityVerticle.PARAM_ID, Collections.singletonList(id));
        req.setParams(params);
        final String reply = sendOnBus(ActivityVerticle.GET, req, user.getAccount().getToken());
        return new JsonObject(reply);
    }

    /**
     * Commons function for return a country JsonObject
     *
     * @param id the id
     * @return country country
     */
    protected JsonObject getCountry(String id) {
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constants.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        /* Retreive object */
        params.put(CountryVerticle.PARAM_ID, Collections.singletonList(id));
        req.setParams(params);
        final String reply = sendOnBus(CountryVerticle.GET, req);
        return new JsonObject(reply);
    }
}
