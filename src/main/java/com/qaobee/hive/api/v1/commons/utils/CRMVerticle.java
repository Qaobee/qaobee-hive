package com.qaobee.hive.api.v1.commons.utils;

import com.qaobee.hive.dao.CRMDao;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Crm verticle.
 */
@DeployableVerticle(isWorker = false)
public class CRMVerticle extends AbstractGuiceVerticle {
    /**
     * The constant REGISTER.
     */
    public static final String REGISTER = "CRMVerticle.register";
    /**
     * The constant UPDATE.
     */
    public static final String UPDATE = "CRMVerticle.update";
    @Inject
    private CRMDao crmDao;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this)
                .add(REGISTER, this::registerMail)
                .add(UPDATE, this::update)
                .register(startFuture);
    }

    private void update(Message<JsonObject> message) {
        crmDao.registerUser(message.body(), false);
    }


    private void registerMail(Message<JsonObject> message) {
        crmDao.registerUser(message.body(), true);
    }
}
