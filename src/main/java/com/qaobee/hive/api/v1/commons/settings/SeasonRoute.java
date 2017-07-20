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
import com.qaobee.hive.services.SeasonService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

@VertxRoute(rootPath = "/api/" + Module.VERSION + "/commons/settings/season")
public class SeasonRoute extends AbstractRoute {
    /**
     * Id of the season
     */
    public static final String PARAM_ID = "_id";
    /**
     * Activity ID
     */
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * Country ID
     */
    public static final String PARAM_COUNTRY_ID = "countryId";
    @Inject
    private SeasonService seasonService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/get", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ID),
                this::getSeason);

        addRoute(router, "/getListByActivity", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID),
                this::getListByActivity);

        addRoute(router, "/current", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID),
                this::getCurrentSeason);

        return router;
    }

    /**
     * @apiDescription Retrieve current season for one activity and one country
     * @api {get} /api/1/commons/settings/season/current Retrieve current seasons
     * @apiVersion 0.1.0
     * @apiName getCurrentSeason
     * @apiGroup Season API
     * @apiParam activityId Activity Id
     * @apiParam countryId Country Id (ie "CNTR-250-FR-FRA")
     * @apiHeader {String} token
     * @apiSuccess {Object} seasons com.qaobee.hive.business.model.commons.settings.Season
     */
    private void getCurrentSeason(RoutingContext context) {
        seasonService.getCurrentSeason(context.request().getParam(PARAM_ACTIVITY_ID), context.request().getParam(PARAM_COUNTRY_ID), handleResponse(context));
    }

    /**
     * @apiDescription Retrieve all seasons for one activity and one country
     * @api {get} /api/1/commons/settings/season/getListByActivity Retrieve all seasons
     * @apiVersion 0.1.0
     * @apiName getListByActivity
     * @apiParam activityId Activity Id
     * @apiParam countryId Country Id (ie "CNTR-250-FR-FRA")
     * @apiGroup Season API
     * @apiHeader {String} token
     * @apiSuccess {Array} seasons com.qaobee.hive.business.model.commons.settings.Season
     */
    private void getListByActivity(RoutingContext context) {
        seasonService.getListByActivity(context.request().getParam(PARAM_ACTIVITY_ID), context.request().getParam(PARAM_COUNTRY_ID), handleResponseArray(context));
    }

    /**
     * @apiDescription get a season to the collection season in settings module
     * @api {get} /api/1/commons/settings/season/get Get season by id
     * @apiVersion 0.1.0
     * @apiName getSeason
     * @apiGroup Season API
     * @apiHeader {String} token
     * @apiParam {String} _id Mandatory The season Id.
     * @apiSuccess {Object} the object found
     */
    private void getSeason(RoutingContext context) {
        seasonService.getSeason(context.request().getParam(PARAM_ID), handleResponse(context));
    }
}
