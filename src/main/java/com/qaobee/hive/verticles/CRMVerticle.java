package com.qaobee.hive.verticles;

import com.qaobee.hive.services.CRMService;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Crm verticle.
 */
@DeployableVerticle
public class CRMVerticle extends AbstractGuiceVerticle {
    /**
     * The constant CRMVERTICLE_REGISTER.
     */
    public static final String CRMVERTICLE_REGISTER = "CRMVerticle.register";
    /**
     * The constant UPDATE.
     */
    public static final String UPDATE = "CRMVerticle.update";
    @Inject
    private CRMService crmService;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this)
                .add(CRMVERTICLE_REGISTER, this::registerMail)
                .add(UPDATE, this::update)
                .register(startFuture);
    }

    private void update(Message<JsonObject> message) {
        crmService.registerUser(message.body(), false);
    }


    private void registerMail(Message<JsonObject> message) {
        crmService.registerUser(message.body(), true);
    }
}
