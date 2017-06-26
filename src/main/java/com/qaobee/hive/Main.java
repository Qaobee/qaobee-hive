package com.qaobee.hive;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;

public class Main {
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
        JsonObject config = new JsonObject(new String(fs.readFileBlocking("config.json").getBytes()));
        vertx.deployVerticle(com.qaobee.hive.api.Main.class.getName(), new DeploymentOptions().setConfig(config));
    }
}