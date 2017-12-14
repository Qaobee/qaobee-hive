package com.qaobee.hive.technical.vertx;

import com.qaobee.hive.services.Warp10Service;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class Warp10Handler {
    private static final Logger LOG = LoggerFactory.getLogger(Warp10Handler.class);
    /**
     * The Warp 10 service.
     */
    @Inject
    private Warp10Service warp10Service;

    public void hit(RoutingContext context) {
        JsonObject labels = new JsonObject().put("path", context.request().path());
        warp10Service.sendNumber("com.qaobee.route.hit", labels, 1d, r -> {
            //empty
        });
        context.next();
    }
}
