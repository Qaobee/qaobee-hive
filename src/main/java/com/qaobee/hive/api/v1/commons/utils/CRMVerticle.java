package com.qaobee.hive.api.v1.commons.utils;

import com.qaobee.hive.dao.CRMDao;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Crm verticle.
 */
@DeployableVerticle(isWorker = false)
public class CRMVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(CRMVerticle.class);
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
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus().registerHandler(REGISTER, this::register);
        vertx.eventBus().registerHandler(UPDATE, this::update);
    }

    private void update(Message<JsonObject> message) {
        crmDao.registerUser(message.body(), false);
    }


    private void register(Message<JsonObject> message) {
        crmDao.registerUser(message.body(), true);
    }
}
