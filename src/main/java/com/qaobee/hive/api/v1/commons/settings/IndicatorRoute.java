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
package com.qaobee.hive.api.v1.commons.settings;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.IndicatorService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Indicator route.
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/commons/settings/indicator")
public class IndicatorRoute extends AbstractRoute {
    /**
     * Indicator id
     */
    public static final String PARAM_ID = "_id";
    /**
     * Indicator activity id
     */
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * Indicator Country Id
     */
    public static final String PARAM_COUNTRY_ID = "countryId";
    /**
     * Indicator Screen
     */
    public static final String PARAM_SCREEN = "screen";
    /**
     * The constant PARAM_INDICATOR_CODE.
     */
    public static final String PARAM_INDICATOR_CODE = "listIndicators";

    @Inject
    private IndicatorService indicatorService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/get", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ID),
                this::getIndicator);

        addRoute(router, "/getList", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_SCREEN),
                this::getIndicatorsList);

        addRoute(router, "/getByCode", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_INDICATOR_CODE),
                this::getIndicatorByCode);

        return router;
    }

    /**
     * @api {get} /api/1/commons/settings/indicator/getByCode Get indicators by code
     * @apiVersion 0.1.0
     * @apiName getIndicatorByCode
     * @apiGroup Indicator API
     * @apiPermission all
     * @apiDescription get a list of indicators by code
     * @apiParam {String} activityId Mandatory The activity Id.
     * @apiParam {String} countryId Mandatory The country Id.
     * @apiParam {Array} listIndicators Mandatory list of indicator's codes
     * @apiHeader {String} token
     * @apiSuccess {Array} indicators The list of indicators found.
     */
    private void getIndicatorByCode(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        indicatorService.getIndicatorByCode(body.getString(PARAM_ACTIVITY_ID), body.getString(PARAM_COUNTRY_ID), body.getJsonArray(PARAM_INDICATOR_CODE), handleResponseArray(context));
    }

    /**
     * @api {get} /api/1/commons/settings/indicator/getList Get a list of indicators
     * @apiVersion 0.1.0
     * @apiName getIndicatorsList
     * @apiGroup Indicator API
     * @apiPermission all
     * @apiDescription get a list of indicators to the collection indicator in settings module
     * @apiParam {String} activityId Mandatory The activity Id.
     * @apiParam {String} countryId Mandatory The country Id.
     * @apiParam {Array} screen Mandatory The list of screen name.
     * @apiHeader {String} token
     * @apiSuccess {Array} indicators The list of indicators found.
     */
    private void getIndicatorsList(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        indicatorService.getIndicatorsList(body.getString(PARAM_ACTIVITY_ID), body.getString(PARAM_COUNTRY_ID), body.getJsonArray(PARAM_SCREEN), handleResponseArray(context));
    }

    /**
     * @api {get} /api/1/commons/settings/indicator/get Read data of an indicator
     * @apiVersion 0.1.0
     * @apiName getIndicator
     * @apiGroup Indicator API
     * @apiPermission all
     * @apiDescription get a indicator to the collection indicator in settings module
     * @apiParam {String} id Mandatory The Indicator-ID.
     * @apiHeader {String} token
     * @apiSuccess {Indicator} indicator The Indicator found.
     */
    private void getIndicator(RoutingContext context) {
        indicatorService.getIndicator(context.request().getParam(PARAM_ID), handleResponse(context));
    }
}
