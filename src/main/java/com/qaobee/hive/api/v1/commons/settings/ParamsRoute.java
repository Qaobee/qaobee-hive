package com.qaobee.hive.api.v1.commons.settings;


import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The type Params route.
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/commons/settings")
public class ParamsRoute extends AbstractRoute {

    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    @Named("stripe")
    private JsonObject stripe;

    @Override
    public Router init() {
        Router router = Router.router(vertx);
        router.get("/get").handler(this::getParams);
        return router;
    }

    private void getParams(RoutingContext context) {
        handleResponse(context, new JsonObject()
                .put("pay_api_key", stripe.getString("api_key"))
                .put("trial.duration", runtime.getString("trial.duration"))
                .put("plan", runtime.getJsonObject("plan")));
    }
}
