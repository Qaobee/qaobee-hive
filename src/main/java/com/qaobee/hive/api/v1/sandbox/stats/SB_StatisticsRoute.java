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
import com.qaobee.hive.services.StatisticsService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Sb statistics Route.
 *
 * @author cke
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/sandbox/stats/statistics")
public class SB_StatisticsRoute extends AbstractRoute {// NOSONAR

    /**
     * List of Indicator code
     */
    public static final String PARAM_INDICATOR_CODE = "listIndicators";
    /**
     * List of owners
     */
    public static final String PARAM_LIST_OWNERS = "listOwners";
    /**
     * Start date
     */
    public static final String PARAM_START_DATE = "startDate";
    /**
     * End date
     */
    public static final String PARAM_END_DATE = "endDate";
    public static final String PARAM_AGGREGAT = "aggregat";
    public static final String PARAM_VALUES = "values";
    public static final String PARAM_LIST_SHOOTSEQID = "listShootSeqId";
    public static final String PARAM_LIST_GROUPBY = "listFieldsGroupBy";
    public static final String PARAM_LIST_SORTBY = "listFieldsSortBy";
    public static final String PARAM_LIMIT_RESULT = "limitResult";
    public static final String OWNER_FIELD = "owner";
    public static final String CODE_FIELD = "code";
    public static final String TIMER_FIELD = "timer";

    @Inject
    private StatisticsService statisticsService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/getStatGroupBy", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_AGGREGAT, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE),
                this::getStatsGroupedBy);

        addRoute(router, "/getListDetailValue", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_INDICATOR_CODE, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE),
                this::getListDetailValue);

        addRoute(router, "/add", HttpMethod.PUT,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, CODE_FIELD, OWNER_FIELD),
                this::addStat);

        addRoute(router, "/", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, "eventId"),
                this::getListForEvent);

        addRoute(router, "/addBulk", HttpMethod.PUT,
                authHandler,
                this::addBulk);


        addRoute(router, "/", HttpMethod.DELETE,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, "eventId"),
                this::deleteStatsForEvent);

        return router;
    }

    /**
     * @api {delete} /api/1/sandbox/stats/statistics
     * @apiVersion 0.1.0
     * @apiName deleteStatsForEvent
     * @apiGroup Statistics API
     * @apiHeader {String} token
     * @apiDescription delete statistics linked to an event
     * @apiParam {String} eventId Mandatory The event id
     * @apiSuccess {number}   deleteCount    The deleted count
     */
    private void deleteStatsForEvent(RoutingContext context) {
        statisticsService.deletStatsForEventId(context.request().getParam("eventId"), handleResponse(context));
    }

    /**
     * @api {post} /api/1/sandbox/stats/statistics/addBulk
     * @apiVersion 0.1.0
     * @apiName addBulk
     * @apiGroup Statistics API
     * @apiHeader {String} token
     * @apiDescription add many statistics in once time
     * @apiParam {Array} stats Mandatory The stats object to add.
     * @apiSuccess {Object}   stats    The stats added.
     */
    private void addBulk(RoutingContext context) {
        statisticsService.addBulk(context.getBodyAsJsonArray(), handleResponse(context));
    }

    /**
     * @api {get} /api/1/sandbox/stats/statistics/?eventId
     * @apiVersion 0.1.0
     * @apiName addBulk
     * @apiGroup Statistics API
     * @apiHeader {String} token
     * @apiDescription add many statistics in once time
     * @apiParam {Array} stats Mandatory The stats object to add.
     * @apiSuccess {Object}   stats    The stats added.
     */
    private void getListForEvent(RoutingContext context) {
        statisticsService.getListForEvent(context.request().getParam("eventId"), handleResponse(context));
    }

    /**
     * @api {post} /api/1/sandbox/stats/statistics/add
     * @apiVersion 0.1.0
     * @apiName addStat
     * @apiGroup Statistics API
     * @apiHeader {String} token
     * @apiDescription add statistic
     * @apiParam {Object} stats Mandatory The stats object to add.
     * @apiSuccess {Object}   stats    The stats added.
     */
    private void addStat(RoutingContext context) {
        statisticsService.addStat(context.getBodyAsJson(), handleResponse(context));
    }

    /**
     * @api {post} /api/1/sandbox/stats/statistics/getListDetailValue
     * @apiVersion 0.1.0
     * @apiName getListDetailValue
     * @apiGroup Statistics API
     * @apiHeader {String} token
     * @apiDescription Retrieve detail value for statistics for mandatory parameters
     * @apiParam {Array} listIndicators Mandatory The list of code indicator.
     * @apiParam {String} listOwners Mandatory The list of owner's stats.
     * @apiParam {long} startDate Mandatory The start period interval.
     * @apiParam {long} endDate Mandatory the end period interval.
     * @apiParam {Array} listParams Optional the criteria request.
     * @apiParam {Array} limitResult Optional the max number element to return
     * @apiSuccess {Array}   Stats    The detail value statistics found.
     */
    private void getListDetailValue(RoutingContext context) {
        JsonObject params = context.getBodyAsJson();
        statisticsService.getListDetailValue(params.getJsonArray(PARAM_INDICATOR_CODE),
                params.getJsonArray(PARAM_LIST_OWNERS, new JsonArray()),
                params.getLong(PARAM_START_DATE, 0L),
                params.getLong(PARAM_END_DATE, 0L),
                params.getJsonArray(PARAM_VALUES, new JsonArray()),
                params.getInteger(PARAM_LIMIT_RESULT, 0),
                handleResponseArray(context));
    }

    /**
     * @api {post} /api/1/sandbox/stats/statistics/getStatGroupBy
     * @apiVersion 0.1.0
     * @apiName getStatGroupBy
     * @apiGroup Statistics API
     * @apiHeader {String} token
     * @apiDescription Retrieve the statistics for mandatory parameters
     * @apiParam {Array} listIndicators Mandatory The list of code indicator.
     * @apiParam {String} aggregat Mandatory the aggregate type (SUM, AVG, COUNT).
     * @apiParam {String} listOwners Mandatory The list of owner's stats.
     * @apiParam {long} startDate Mandatory The start period interval.
     * @apiParam {long} endDate Mandatory the end period interval.
     * @apiParam {Array} listParams Optional the criteria request.
     * @apiParam {Array} listFieldsGroupBy Optional the clause group by request
     * @apiParam {Array} listFieldsSortBy Optional the clause sort request
     * @apiParam {Array} limitResult Optional the max number element to return
     * @apiSuccess {Array}   Stats    The statistics found.
     */
    private void getStatsGroupedBy(RoutingContext context) {
        JsonObject params = context.getBodyAsJson();
        statisticsService.getStatsGroupedBy(params.getJsonArray(PARAM_INDICATOR_CODE),
                params.getJsonArray(PARAM_LIST_OWNERS, new JsonArray()),
                params.getLong(PARAM_START_DATE, 0L),
                params.getLong(PARAM_END_DATE, 0L),
                params.getString(PARAM_AGGREGAT, ""),
                params.getJsonArray(PARAM_VALUES, new JsonArray()),
                params.getJsonArray(PARAM_LIST_SHOOTSEQID, new JsonArray()),
                params.getJsonArray(PARAM_LIST_GROUPBY, new JsonArray()),
                params.getJsonArray(PARAM_LIST_SORTBY, new JsonArray()),
                params.getInteger(PARAM_LIMIT_RESULT, 0),
                handleResponseArray(context)
        );
    }
}
