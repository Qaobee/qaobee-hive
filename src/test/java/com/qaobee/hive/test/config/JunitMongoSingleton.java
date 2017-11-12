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

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.config.io.ProcessOutput;
import de.flapdoodle.embed.process.runtime.Network;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The Class JunitMongoSingleton.
 *
 * @author xavier
 */
class JunitMongoSingleton {
    private static final Logger LOG = LoggerFactory.getLogger(JunitMongoSingleton.class.getName());

    /**
     * The process.
     */
    private MongodProcess process;

    /**
     * Instantiates a new junit mongo singleton.
     */
    private JunitMongoSingleton() {
        //
    }

    /**
     * Gets the single instance of JunitMongoSingleton.
     *
     * @return the instance
     */
    static JunitMongoSingleton getInstance() {
        return JunitMongoSingleton.JunitMongoSingletonHolder.INSTANCE;
    }

    /**
     * Start server.
     *
     * @param config the config
     * @throws IOException Signals that an I/O exception has occurred.
     */
    void startServer(JsonObject config) throws IOException {
        LOG.info("testing process : " + process);
        if (process == null || !process.isProcessRunning()) {
            LOG.info("Running mongod");
            int port = config.getJsonObject("mongo.db").getInteger("port");
            IMongodConfig mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.V3_5)
                    .net(new Net(port, Network.localhostIsIPv6()))
                    .build();
            IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                    .defaultsWithLogger(Command.MongoD, LOG)
                    .processOutput(ProcessOutput.getDefaultInstanceSilent())
                    .build();
            MongodStarter starter = MongodStarter.getInstance(runtimeConfig);
            MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
            setProcess(mongodExecutable.start());
        }
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
     * @param process the process to set
     */
    public void setProcess(MongodProcess process) {
        this.process = process;
    }

    /**
     * Holder.
     */
    private static class JunitMongoSingletonHolder {

        /**
         * unique instance *.
         */
        private static final JunitMongoSingleton INSTANCE = new JunitMongoSingleton();
    }

}
