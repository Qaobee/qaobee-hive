package com.qaobee.hive;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private Main() {
        // empty
    }
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String... args) {
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        FileSystem fs = vertx.fileSystem();
        String env = System.getenv("ENV");
        if(StringUtils.isBlank(env)) {
            env = "DEV";
        }
        LOG.info("Running with env : " + env);
        JsonObject config = new JsonObject(new String(fs.readFileBlocking("config.json").getBytes())).getJsonObject(env);
        vertx.deployVerticle(com.qaobee.hive.api.Main.class.getName(), new DeploymentOptions().setConfig(config));
    }
}