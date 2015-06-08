/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.test.config;

import java.io.IOException;
import java.net.UnknownHostException;

import org.vertx.java.core.json.JsonObject;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * The Class JunitMongoSingleton.
 *
 * @author xavier
 */
public class JunitMongoSingleton {
	
	/** The mongod executable. */
	private static MongodExecutable mongodExecutable = null;
	
	/** The starter. */
	private static MongodStarter starter = MongodStarter.getDefaultInstance();
	
	/** The process. */
	private MongodProcess process;

	/**
	 * Instantiates a new junit mongo singleton.
	 */
	private JunitMongoSingleton() {
		//
	}

	/**
	 * Start server.
	 *
	 * @param config
	 *            the config
	 * @throws UnknownHostException
	 *             the unknown host exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void startServer(JsonObject config) throws UnknownHostException, IOException {
		System.out.println("testing prrocess : " + process);
		if (process == null || !process.isProcessRunning()) {
			System.out.println("Running mongod");
			int port = config.getObject("mongo.persistor").getInteger("port");
			IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.V2_4).net(new Net(port, Network.localhostIsIPv6())).build();
			mongodExecutable = starter.prepare(mongodConfig);
			setProcess(mongodExecutable.start());
		}
	}

	/**
	 * Holder.
	 */
	private static class JunitMongoSingletonHolder {

		/** unique instance *. */
		private static final JunitMongoSingleton INSTANCE = new JunitMongoSingleton();
	}

	/**
	 * Gets the single instance of JunitMongoSingleton.
	 *
	 * @return the instance
	 */
	public static JunitMongoSingleton getInstance() {
		return JunitMongoSingletonHolder.INSTANCE;
	}

	/**
	 * Gets the process.
	 *
	 * @return the process
	 */
	public MongodProcess getProcess() {
		return process;
	}

	/**
	 * Sets the process.
	 *
	 * @param process
	 *            the process to set
	 */
	public void setProcess(MongodProcess process) {
		this.process = process;
	}

}
