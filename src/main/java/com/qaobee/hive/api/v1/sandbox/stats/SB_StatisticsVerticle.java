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
import com.qaobee.hive.dao.StatisticsDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

/**
 * The type Sb statistics verticle.
 *
 * @author cke
 */
@DeployableVerticle
public class SB_StatisticsVerticle extends AbstractGuiceVerticle {// NOSONAR
    /**
     * Handler for average rate for one or many indicator and for one or many person, group by PARAM_LIST_GROUPBY
     */
    public static final String GET_STAT_GROUPBY = Module.VERSION + ".sandbox.stats.statistics.getStatGroupBy";
    /**
     * Handler for individual list of values for a stat
     */
    public static final String GET_LISTDETAIL_VALUES = Module.VERSION + ".sandbox.stats.statistics.getListDetailValue";
    /**
     * Handler for adding a new stat
     */
    public static final String ADD_STAT = Module.VERSION + ".sandbox.stats.statistics.add";
    /**
     * Handler for adding a set of stat
     */
    public static final String ADD_STAT_BULK = Module.VERSION + ".sandbox.stats.statistics.addBulk";
    /**
     * The constant GET_STATS.
     */
    public static final String GET_STATS  = Module.VERSION + ".sandbox.stats.statistics";
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
    private static final String PARAM_VALUES = "values";
    private static final String PARAM_LIST_SHOOTSEQID = "listShootSeqId";
    private static final String PARAM_AGGREGAT = "aggregat";
    private static final String PARAM_LIST_GROUPBY = "listFieldsGroupBy";
    private static final String PARAM_LIST_SORTBY = "listFieldsSortBy";
    private static final String PARAM_LIMIT_RESULT = "limitResult";
    private static final Logger LOG = LoggerFactory.getLogger(SB_StatisticsVerticle.class);
    private static final String OWNER_FIELD = "owner";
    private static final String CODE_FIELD = "code";
    private static final String TIMER_FIELD = "timer";
    @Inject
    private Utils utils;
    @Inject
    private StatisticsDAO statisticsDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET_STAT_GROUPBY, this::getStatsGroupedBy)
                .registerHandler(GET_LISTDETAIL_VALUES, this::getListDetailValue)
                .registerHandler(ADD_STAT, this::addStat)
                .registerHandler(GET_STATS, this::getListForEvent)
                .registerHandler(ADD_STAT_BULK, this::addBulk);
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
    @Rule(address = ADD_STAT_BULK, method = Constants.PUT, logged = true)
    private void addBulk(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        message.reply(statisticsDAO.addBulk(new JsonArray(req.getBody())).encode());
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
    @Rule(address = GET_STATS, method = Constants.GET, logged = true)
    private void getListForEvent(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        message.reply(statisticsDAO.getListForEvent(req.getParams().get("eventId").get(0)).encode());
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
    @Rule(address = ADD_STAT, method = Constants.PUT, logged = true, mandatoryParams = {CODE_FIELD, TIMER_FIELD, OWNER_FIELD},
            scope = Rule.Param.BODY)
    private void addStat(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(statisticsDAO.addStat(new JsonObject(req.getBody())).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
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
    @Rule(address = GET_LISTDETAIL_VALUES, method = Constants.POST, logged = true,
            mandatoryParams = {PARAM_INDICATOR_CODE, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE},
            scope = Rule.Param.BODY)
    private void getListDetailValue(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            JsonObject params = new JsonObject(req.getBody());
            message.reply(statisticsDAO.getListDetailValue(params.getArray(PARAM_INDICATOR_CODE),
                    params.getArray(PARAM_LIST_OWNERS),
                    params.getLong(PARAM_START_DATE),
                    params.getLong(PARAM_END_DATE),
                    params.getArray(PARAM_VALUES),
                    params.containsField(PARAM_LIMIT_RESULT) ? params.getInteger(PARAM_LIMIT_RESULT) : 0
            ).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
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
    @Rule(address = GET_STAT_GROUPBY, method = Constants.POST, logged = true,
            mandatoryParams = {PARAM_INDICATOR_CODE, PARAM_AGGREGAT, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE},
            scope = Rule.Param.BODY)
    private void getStatsGroupedBy(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            JsonObject params = new JsonObject(req.getBody());
            message.reply(statisticsDAO.getStatsGroupedBy(params.getArray(PARAM_INDICATOR_CODE),
                    params.getArray(PARAM_LIST_OWNERS),
                    params.getLong(PARAM_START_DATE),
                    params.getLong(PARAM_END_DATE),
                    params.getString(PARAM_AGGREGAT),
                    params.getArray(PARAM_VALUES),
                    params.getArray(PARAM_LIST_SHOOTSEQID),
                    params.getArray(PARAM_LIST_GROUPBY),
                    params.getArray(PARAM_LIST_SORTBY),
                    params.containsField(PARAM_LIMIT_RESULT) ? params.getInteger(PARAM_LIMIT_RESULT) : 0
            ).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
