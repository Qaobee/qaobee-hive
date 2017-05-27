package com.qaobee.hive.api.v1.commons.settings;


import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The type Params.
 */
@DeployableVerticle
public class Params extends AbstractGuiceVerticle {
    public static final String GET = Module.VERSION + ".commons.settings.get";
    private static final Logger LOG = LoggerFactory.getLogger(IndicatorVerticle.class);

    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    @Named("stripe")
    private JsonObject stripe;
    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET, this::getParams);
    }
    @Rule(address = GET, method = Constants.GET)
    private void getParams(Message<String> message) {
        JsonObject params = new JsonObject()
                .putString("pay_api_key", stripe.getString("api_key"))
                .putString("trial.duration", runtime.getString("trial.duration"))
                .putObject("plan", runtime.getObject("plan"));
        message.reply(params.encode());
    }
}
