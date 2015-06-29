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
import com.mongodb.MongoException;
import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.api.v1.commons.settings.CountryVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.guice.GuiceModule;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
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
import java.io.*;
import java.net.ServerSocket;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class VertxJunitSupport.
 *
 * @author xavier
 */

@RunWith(VertxJUnit4ClassRunner.class)
@VertxConfiguration(modsDir = "build/mods", injectResources = true)
@TestModule(name = "com.qaobee~hive~0.1", jsonConfig = "file:config.json")
@Ignore
public class VertxJunitSupport extends VertxTestBase implements JSDataMongoTest {
    /**
     * The Constant LOCALE.
     */
    public static final String LOCALE = "fr_FR";

    /**
     * The name.
     */
    @Rule
    public TestName name = new TestName();

    /**
     * The Constant LOG.
     */
    protected static final Logger LOG = Logger.getLogger(VertxJunitSupport.class.getName());

    /**
     * The module config.
     */
    protected static JsonObject moduleConfig;

    /**
     * The timeout.
     */
    protected long timeout = 150L;

    /**
     * The queue.
     */
    protected final LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    /**
     * The Mongo.
     */
    @Inject
    protected MongoDB mongo;

    /**
     * Prints the info.
     */
    @Before
    public void printInfo() {
        Injector injector = Guice.createInjector(new GuiceModule(moduleConfig));
        injector.injectMembers(this);
        System.out.println("About to execute : " + name.getMethodName());
        mongo.getDb().dropDatabase();
    }

    /**
     * Start mongo server.
     */
    @BeforeClass
    public static void startMongoServer() {
        moduleConfig = DeploymentUtils.getJsonConfig(VertxJunitSupport.class.getAnnotation(TestModule.class).jsonConfig());
        try {
            JunitMongoSingleton.getInstance().startServer(moduleConfig);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
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
     * Generate user.
     *
     * @return a user
     */
    protected User generateUser() {
        final User user = Json.decodeValue(moduleConfig.getObject("junit").getObject("user").copy().encode(), User.class);
        user.getAccount().setActive(true);
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
     * Generate logged user.
     *
     * @return the user
     */
    protected User generateLoggedUser() {
        return generateLoggedUser("12345");
    }

    protected User generateLoggedUser(String userId) {
        final User user = Json.decodeValue(moduleConfig.getObject("junit").getObject("user").copy().encode(), User.class);
        try {
            user.set_id(userId);
            user.getAccount().setToken("12345");
            user.getAccount().setTokenRenewDate(System.currentTimeMillis());
            user.getAccount().setActive(true);
            final String id = mongo.save(user);
            if (id == null) {
                Assert.fail("user id is null");
            } else {
                user.set_id(id);
            }
        } catch (EncodeException | QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        return user;
    }

    /**
     * Gets the params.
     *
     * @param args tableaux de type [clef, val1, val2, ...]
     * @return map de paramètres de requête
     */
    protected Map<String, List<String>> getParams(final String[]... args) {
        final Map<String, List<String>> params = new HashMap<>();
        for (final String[] arg : args) {
            params.put(arg[0], Arrays.asList((String[]) ArrayUtils.subarray(arg, 1, arg.length)));
        }
        return params;
    }

    /**
     * Sendon bus.
     *
     * @param address bus address
     * @param req     request
     * @return result string
     */
    protected String sendonBus(final String address, final RequestWrapper req) {
        getEventBus().send(address, Json.encode(req), new QueueReplyHandler<>(queue, timeout));
        try {
            final Object result = queue.poll(timeout, TimeUnit.SECONDS);
            if (result instanceof ReplyException) {
                try {
                    final JsonObject error = new JsonObject(((ReplyException) result).getMessage());
                    LOG.log(Level.SEVERE, error.getString("code") + " : " + error.getString("message"));
                } catch (final EncodeException e) {
                    LOG.log(Level.SEVERE, ((ReplyException) result).getMessage());
                    Assert.fail(e.getMessage());
                }
                return ((ReplyException) result).getMessage();
            } else if (result instanceof String) {
                return (String) result;
            } else if (result instanceof JsonObject) {
                return ((JsonObject) result).encode();
            } else {
                Assert.fail("unparsable data");
            }
        } catch (final InterruptedException e) {
            Assert.fail(e.getMessage());
        }
        return null;
    }


    protected String sendonBus(String address, RequestWrapper req, String token) {
        if(req.getHeaders() == null){
            req.setHeaders(new HashMap<String, List<String>>());
        }
        req.getHeaders().put("token", Arrays.asList(token));
        return sendonBus(address, req);
    }


    /**
     * The Constant POPULATE_ONLY.
     */
    protected static final String POPULATE_ONLY = "only";

    /**
     * The Constant POPULATE_WITHOUT.
     */
    protected static final String POPULATE_WITHOUT = "without";

    /**
     * The Constant POPULATE_ALL.
     */
    protected static final String POPULATE_ALL = "all";

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
        boolean comments = false;

        File[] listFiles = (new File("scripts/mongo" + relativeDirectory)).listFiles();
        if (listFiles != null && listFiles.length > 0) {
            BufferedReader reader = null;
            List<String> mongoFilesList = new ArrayList<>();
            if (mongoFiles != null && mongoFiles.length > 0) {
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

                try {
                    StringBuilder sb = new StringBuilder(1000);
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(scriptMongo)));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        line = line.trim();
                        if (line.startsWith("//")) {
                            continue;
                        }
                        if (line.startsWith("/*") && line.endsWith("*/")) {
                            continue;
                        }
                        if (line.startsWith("/*")) {
                            comments = true;
                            continue;
                        }
                        if (line.startsWith("*/")) {
                            comments = false;
                            continue;
                        }
                        if (comments) {
                            continue;
                        }

                        if (line.contains("//")) {
                            line = line.substring(0, line.indexOf("//"));
                        }

                        sb.append(line);

                        if (line.trim().endsWith(";")) {
                            if (sb.toString().trim().startsWith("var ")) {
                                sb = new StringBuilder(1000);
                                continue;
                            }
                            if (sb.toString().contains(" _id")) {
                                String replace = sb.toString().replace(" _id", " \"test" + System.currentTimeMillis() + "\"");
                                sb = new StringBuilder(replace);
                            }
                            if (sb.toString().contains(" id")) {
                                String replace = sb.toString().replace(" id", " \"test" + System.currentTimeMillis() + "\"");
                                sb = new StringBuilder(replace);
                            }
                            try {
                                System.out.println("POPULATE --------------> : scriptMongo : " + scriptMongo);
                                System.out.println("POPULATE --------------> : insertData : " + sb.toString());
                                mongo.getDb().doEval(sb.toString());
                            } catch (MongoException e) {
                                System.out.println(sb.toString());
                                e.printStackTrace();
                            }
                            sb = new StringBuilder(1000);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            getContainer().logger().error(e.getMessage(), e);
                        }
                    }
                }
            }
        }

    }

    /**
     * Commons function for return a country JsonObject
     *
     * @param id the id
     * @return activity activity
     */
    protected JsonObject getActivity(String id) {

        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);

        final HashMap<String, List<String>> params = new HashMap<>();

		/* Retreive object */
        params.put(ActivityVerticle.PARAM_ID, Collections.singletonList(id));
        req.setParams(params);
        final String reply = sendonBus(ActivityVerticle.GET, req);
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
        req.setMethod(Constantes.GET);
        final HashMap<String, List<String>> params = new HashMap<>();
        /* Retreive object */
        params.put(CountryVerticle.PARAM_ID, Collections.singletonList(id));
        req.setParams(params);
        final String reply = sendonBus(CountryVerticle.GET, req);
        return new JsonObject(reply);
    }
}
