/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.test.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
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

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.MongoException;
import com.qaobee.business.model.sandbox.effective.Person;
import com.qaobee.technical.exceptions.QaobeeException;
import com.qaobee.technical.mongo.MongoDB;
import com.qaobee.technical.vertx.RequestWrapper;
import com.qaobee.technical.vertx.utils.guice.GuiceModule;

/**
 * The Class VertxJunitSupport.
 * 
 * @author xavier
 */

@RunWith(VertxJUnit4ClassRunner.class)
@VertxConfiguration(modsDir = "target/mods", injectResources = true)
@TestModule(name = "com.qaobee~swarn~0.1", jsonConfig = "file:config.json")
@Ignore
public class VertxJunitSupport extends VertxTestBase implements JSDataMongoTest {

	/** The Constant DB_NAME. */
	public static final String DB_NAME = "hive";

	/** The Constant LOCALE. */
	public static final String LOCALE = "fr_FR";

	/** The name. */
	@Rule
	public TestName name = new TestName();

	/** The Constant LOG. */
	protected static final Logger LOG = Logger.getLogger(VertxJunitSupport.class.getName());

	/** The module config. */
	protected static JsonObject moduleConfig;

	/** The timeout. */
	protected long timeout = 150L;

	/** The queue. */
	protected final LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();

	/** List for tests with a blank value */
	protected List<String> blank = Arrays.asList(" ");

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
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
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
	public Person generateUser() {
		final Person p = Json.decodeValue(moduleConfig.getObject("junit").getObject("user").copy().encode(), Person.class);
		try {
			final String id = mongo.save(p);
			if (id == null) {
				Assert.fail("user id is null");
			}
			p.set_id(id);
		} catch (EncodeException | QaobeeException e) {
			Assert.fail(e.getMessage());
		}
		return p;
	}

	/**
	 * Gets the params.
	 * 
	 * @param args
	 *            tableaux de type [clef, val1, val2, ...]
	 * @return map de paramètres de requête
	 */
	public Map<String, List<String>> getParams(final String[]... args) {
		final Map<String, List<String>> params = new HashMap<String, List<String>>();
		for (final String[] arg : args) {
			params.put(arg[0], Arrays.asList((String[]) ArrayUtils.subarray(arg, 1, arg.length)));
		}
		return params;
	}

	/**
	 * Sendon bus.
	 * 
	 * @param address
	 *            bus address
	 * @param req
	 *            request
	 * @return result
	 */
	public String sendonBus(final String address, final RequestWrapper req) {
		getEventBus().send(address, Json.encode(req), new QueueReplyHandler<Object>(queue, timeout));
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

	/** The Constant POPULATE_ONLY. */
	public static final String POPULATE_ONLY = "only";

	/** The Constant POPULATE_WITHOUT. */
	public static final String POPULATE_WITHOUT = "without";

	/** The Constant POPULATE_ALL. */
	public static final String POPULATE_ALL = "all";

	/**
	 * Populates the test base.
	 * 
	 * @param populateType
	 *            (String) : POPULATE_ONLY, POPULATE_WITHOUT, POPULATE_ALL
	 * @param mongoFiles
	 *            (String[]) : array of filenames
	 */
	public void populate(String populateType, String... mongoFiles) {
		populate(populateType, "", mongoFiles);
	}

	/**
	 * Populates the test base. It is not needed to indicate the subdirectory name, the function will search in all directories
	 * of "scripts/mongo".
	 * 
	 * @param populateType
	 *            (String) : POPULATE_ONLY, POPULATE_WITHOUT, POPULATE_ALL
	 * @param relativeDirectory
	 *            (String) : relative dir from "scripts/mongo"
	 * @param mongoFiles
	 *            (String[]) : array of filenames
	 */
	private void populate(String populateType, String relativeDirectory, String... mongoFiles) {
		boolean comments = false;

		File[] listFiles = (new File("scripts/mongo" + relativeDirectory)).listFiles();
		if (listFiles != null && listFiles.length > 0) {
			BufferedReader reader = null;
			List<String> mongoFilesList = new ArrayList<String>();
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

						}
					}
				}
			}
		}

	}
}
