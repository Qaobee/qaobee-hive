package com.qaobee.hive.api.v1.sandbox.stats;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.services.Warp10Service;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

@VertxRoute(rootPath = "/api/" + Module.V1 + "/sandbox/stats/")
public class SB_StatsRoute extends AbstractRoute {// NOSONAR
    @Inject
    Warp10Service warp10Service;
    @Inject
    MongoDB mongoDB;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/", HttpMethod.POST,
                //  authHandler,
                // c -> mandatoryHandler.testBodyParams(c, PARAM_START_DATE, PARAM_END_DATE, PARAM_SANDBOX_ID),
                this::addStat);
        addRoute(router, "/exec", HttpMethod.POST,
                //  authHandler,
                // c -> mandatoryHandler.testBodyParams(c, PARAM_START_DATE, PARAM_END_DATE, PARAM_SANDBOX_ID),
                this::execWS);
        addRoute(router, "/", HttpMethod.GET,
                //  authHandler,
                // c -> mandatoryHandler.testBodyParams(c, PARAM_START_DATE, PARAM_END_DATE, PARAM_SANDBOX_ID),
                this::bulk);
        addRoute(router, "/users", HttpMethod.GET,
                //  authHandler,
                // c -> mandatoryHandler.testBodyParams(c, PARAM_START_DATE, PARAM_END_DATE, PARAM_SANDBOX_ID),
                this::bulkUser);
        addRoute(router, "/persons", HttpMethod.GET,
                //  authHandler,
                // c -> mandatoryHandler.testBodyParams(c, PARAM_START_DATE, PARAM_END_DATE, PARAM_SANDBOX_ID),
                this::bulkPersons);
        return router;
    }

    private void bulkPersons(RoutingContext context) {
        mongoDB.findAll(new CriteriaOption(), DBCollections.PERSON, event -> {
            if (event.succeeded()) {
                event.result().forEach(r -> {
                    JsonObject person = (JsonObject) r;
                    if(person != null) {
                        warp10Service.pushGTSStr("qaobee.person",
                                person.getLong("birthdate", System.currentTimeMillis()),
                                person.encode(),
                                new JsonObject()
                                        .put("_id", person.getString("_id"))
                                        .put("sandboxId", person.getString("sandboxId")), res -> {
                                    //
                                });
                    }
                });

                handleStatus(true, context);
            } else {
                handleStatus(false, context);
            }
        });
    }

    private void bulkUser(RoutingContext context) {
        mongoDB.findAll(new CriteriaOption(), DBCollections.USER, event -> {
            if (event.succeeded()) {
                event.result().forEach(r -> {
                    JsonObject user = (JsonObject) r;
                    if(user != null) {
                        warp10Service.pushGTSStr("qaobee.users",
                                user.getLong("timestamp", System.currentTimeMillis()),
                                user.encode(),
                                new JsonObject()
                                        .put("_id", user.getString("_id"))
                                        .put("login", user.getJsonObject("account").getString("login"))
                                        .put("sandboxDefault", user.getString("sandboxDefault")), res -> {
                                    //
                                });
                    }
                });
                handleStatus(true, context);
            } else {
                handleStatus(false, context);
            }
        });
    }

    private void bulk(RoutingContext context) {
        mongoDB.findAll(new CriteriaOption(), DBCollections.STATS, event -> {
            if (event.succeeded()) {
                event.result().forEach(r -> addStat((JsonObject) r, res -> System.out.println(res.succeeded())));
            } else {
                handleStatus(false, context);
            }
        });
        context.response().end("done");
    }

    private void execWS(RoutingContext context) {
        String script = context.getBodyAsString();
        warp10Service.executeWarpScript(script, handleResponseArray(context));
    }

    private void addStat(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        addStat(body, res -> handleStatus(res.succeeded(), context));
    }

    private void addStat(JsonObject body, Handler<AsyncResult<Boolean>> resultHandler) {
        body.getJsonArray("owner").forEach(o -> body.getJsonArray("producter").forEach(p -> {
            JsonObject value = new JsonObject()
                    .put("chrono", body.getInteger("chrono"))
                    .put("value", body.getValue("value"));
            warp10Service.pushGTS("qaobee." + body.getString("activityId") + "." + body.getString("code"),
                    body.getLong("timer"),
                    value,
                    new JsonObject()
                            .put("attack", body.getBoolean("attack"))
                            .put("phaseSeqId", body.getString("phaseSeqId"))
                            .put("eventId", body.getString("eventId"))
                            .put("owner", o)
                            .put("producter", p), resultHandler);
        }));
    }
}
