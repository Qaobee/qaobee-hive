/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */

package com.qaobee.hive.api.v1.commons.settings;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.ActivityCfgService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * The type Activity cfg verticle.
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/commons/settings/activitycfg")
public class ActivityCfgRoute extends AbstractRoute {
    private static final Logger LOG = LoggerFactory.getLogger(ActivityCfgRoute.class);
    public static final String PARAM_FIELD_LIST = "paramFieldList";
    public static final String PARAM_DATE = "date";
    public static final String PARAM_ACTIVITY_ID = "activityId";
    public static final String PARAM_COUNTRY_ID = "countryId";

    @Inject
    private ActivityCfgService activityCfgService;

    /**
     * Init router.
     *
     * @return the router
     */
    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/get", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE),
                this::getActivityCfgHandler);

        addRoute(router, "/params", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE, PARAM_FIELD_LIST),
                this::getActivityCfgParamsHandler);

        return router;
    }

    /**
     * @apiDescription retrieve a list of value for one parameter ActivityCfg
     * @api {post} /api/1/commons/settings/activitycfg/params params ActivityCfg
     * @apiVersion 0.1.0
     * @apiName params
     * @apiGroup ActivityCfg API
     * @apiHeader {String} token
     * @apiParam {String} activityId Activity Id
     * @apiParam {String} countryId Country Id
     * @apiParam {long} date the current date
     * @apiParam {String} paramFieldList the list of value
     */
    private void getActivityCfgParamsHandler(RoutingContext context) {
        activityCfgService.getActivityCfgParams(
                context.request().getParam(PARAM_ACTIVITY_ID),
                context.request().getParam(PARAM_COUNTRY_ID),
                Long.parseLong(context.request().getParam(PARAM_DATE)),
                context.request().getParam(PARAM_FIELD_LIST),
                ar -> {
                    if (ar.succeeded()) {
                        handleResponse(context, ar.result().getJsonObject(0).getJsonArray(context.request().getParam(PARAM_FIELD_LIST)));
                    } else {
                        LOG.error(ar.cause().getMessage(), ar.cause());
                        utils.handleError(context, ar.cause());
                    }
                });
    }

    /**
     * @apiDescription Fetch ActivityCfg
     * @api {post} /api/1/commons/settings/activitycfg/get Get ActivityCfg
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup ActivityCfg API
     * @apiHeader {String} token
     * @apiParam {String} activityId Activity Id
     */
    private void getActivityCfgHandler(RoutingContext context) {
        activityCfgService.getActivityCfg(
                context.request().getParam(PARAM_ACTIVITY_ID),
                context.request().getParam(PARAM_COUNTRY_ID),
                Long.parseLong(context.request().getParam(PARAM_DATE)), handleResponse(context));
    }
}
