package com.qaobee.hive;

import com.qaobee.hive.verticles.CoordinatorVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;
import io.vertx.ext.dropwizard.Match;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final String MONGO_CONF_KEY = "mongo.db";

    private Main() {
        // empty
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String... args) {
        Vertx vertx = Vertx.vertx(
                new VertxOptions()
                        .setWorkerPoolSize(40)
                        .setMetricsOptions(
                                new DropwizardMetricsOptions()
                                        .setEnabled(true)
                                        .setJmxEnabled(true).
                                        addMonitoredHttpServerUri(new Match().setValue("/"))
                        )
        );
        FileSystem fs = vertx.fileSystem();
        String env = System.getenv("ENV");
        if (StringUtils.isBlank(env)) {
            env = "DEV";
        }
        LOG.info("Running with env : {}", env);
        JsonObject config = new JsonObject(new String(fs.readFileBlocking("config.json").getBytes())).getJsonObject(env);
        if (org.apache.commons.lang.StringUtils.isNotBlank(System.getenv("OPENSHIFT_MONGODB_DB_HOST"))) {
            config.getJsonObject(MONGO_CONF_KEY).put("host", System.getenv("OPENSHIFT_MONGODB_DB_HOST"));
            config.getJsonObject(MONGO_CONF_KEY).put("port", Integer.parseInt(Optional.ofNullable(System.getenv("OPENSHIFT_MONGODB_DB_PORT")).orElse("27017")));
            config.getJsonObject(MONGO_CONF_KEY).put("password", Optional.ofNullable(System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD")).orElse(""));
            config.getJsonObject(MONGO_CONF_KEY).put("username", Optional.ofNullable(System.getenv("OPENSHIFT_MONGODB_DB_USERNAME")).orElse(""));
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("Config : {}", config.encodePrettily());
        }
        vertx.deployVerticle(CoordinatorVerticle.class.getName(), new DeploymentOptions().setConfig(config));
    }
}