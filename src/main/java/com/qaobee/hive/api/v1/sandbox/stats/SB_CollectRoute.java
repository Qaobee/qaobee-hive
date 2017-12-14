/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */
package com.qaobee.hive.api.v1.sandbox.stats;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.CollectService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Sb collect Route.
 *
 * @author cke
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/sandbox/stats/collect")
public class SB_CollectRoute extends AbstractRoute {// NOSONAR
    /**
     * Collecte ID
     */
    public static final String PARAM_ID = "_id";
    /**
     * Collecte event
     */
    public static final String PARAM_EVENT = "eventRef";
    /**
     * Collecte players
     */
    public static final String PARAM_PLAYERS = "players";
    /**
     * Collecte event id
     */
    public static final String PARAM_EVENT_ID = "eventId";
    /**
     * Collecte sandboxId
     */
    public static final String PARAM_SANDBOX_ID = "sandboxId";
    /**
     * Collecte effectiveId
     */
    public static final String PARAM_EFFECTIVE_ID = "effectiveId";
    /**
     * Collecte teamId
     */
    public static final String PARAM_TEAM_ID = "teamId";
    /**
     * Event Start date
     */
    public static final String PARAM_START_DATE = "startDate";
    /**
     * Event End date
     */
    public static final String PARAM_END_DATE = "endDate";

    @Inject
    private CollectService collectService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/list", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_START_DATE, PARAM_END_DATE, PARAM_SANDBOX_ID),
                this::getList);

        addRoute(router, "/add", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_EVENT, PARAM_PLAYERS),
                this::add);

        addRoute(router, "/update", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_EVENT, PARAM_PLAYERS),
                this::update);


        addRoute(router, "/get", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ID),
                this::get);

        return router;
    }

    /**
     * @apiDescription Retrieve Collect by this Id
     * @api {get} /api/1/sandbox/stats/collect/get Get collect by Id
     * @apiName get
     * @apiGroup Collect API
     * @apiParam {String} id
     * @apiHeader {String} token
     * @apiSuccess {Object} collect
     */
    private void get(RoutingContext context) {
        collectService.get(context.request().getParam(PARAM_ID), handleResponse(context));
    }

    /**
     * @apiDescription Update an collect.
     * @api {post} /api/1/sandbox/stats/collect/update Update a collect
     * @apiName update
     * @apiGroup Collect API
     * @apiHeader {String} token
     * @apiSuccess {Object} collect updated collect
     */
    private void update(RoutingContext context) {
        collectService.update(context.getBodyAsJson(), context.user().principal().getString("_id"), getLocale(context), handleResponse(context));
    }

    /**
     * @apiDescription Add a collect.
     * @api {post} /api/1/sandbox/stats/collect/add Add a collect
     * @apiName add
     * @apiGroup Collect API
     * @apiHeader {String} token
     * @apiSuccess {Object} collect Created collect
     */
    private void add(RoutingContext context) {
        JsonObject labels = new JsonObject().put("user_id", context.user().principal().getString("_id"));
        warp10Service.sendNumber("com.qaobee.mesures.collect", labels, 1, r -> {
            //empty
        });
        collectService.add(context.getBodyAsJson(), context.user().principal().getString("_id"), getLocale(context), handleResponse(context));
    }

    /**
     * @apiDescription retrieve all collects
     * @api {post} /api/1/sandbox/stats/collect/list Get all collects
     * @apiName getList
     * @apiGroup SB_Collecet API
     * @apiParam {String} startDate start date
     * @apiParam {String} endDate end date
     * @apiParam {String} sandBoxId
     * @apiHeader {String} token
     * @apiSuccess {Array} list of collects
     */
    private void getList(RoutingContext context) {
        collectService.getList(context.getBodyAsJson(), handleResponseArray(context));
    }
}
