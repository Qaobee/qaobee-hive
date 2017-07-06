package com.qaobee.hive.api.v1.commons.settings;


import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The type Params.
 */
@DeployableVerticle
public class Params extends AbstractGuiceVerticle {
    /**
     * The constant GET.
     */
    public static final String GET = Module.VERSION + ".commons.settings.get";

    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    @Named("stripe")
    private JsonObject stripe;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this).add(GET, this::getParams).register(startFuture);
    }

    @Rule(address = GET, method = Constants.GET)
    private void getParams(Message<String> message) {
        JsonObject params = new JsonObject()
                .put("pay_api_key", stripe.getString("api_key"))
                .put("trial.duration", runtime.getString("trial.duration"))
                .put("plan", runtime.getJsonObject("plan"));
        message.reply(params.encode());
    }
}
