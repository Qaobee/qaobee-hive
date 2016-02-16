/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.api.v1.sandbox.stats;

import com.mongodb.*;
import com.mongodb.util.JSON;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.sandbox.stats.SB_Stats;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author cke
 */
@DeployableVerticle(isWorker = true)
public class SB_StatisticsVerticle extends AbstractGuiceVerticle {
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
     * List of Indicator code
     */
    public static final String PARAM_INDICATOR_CODE = "listIndicators";

	/* List of parameters */
    /**
     * Value
     */
    public static final String PARAM_VALUES = "values";
    /**
     * List of parameters for the indicator
     */
    public static final String PARAM_LIST_SHOOTSEQID = "listShootSeqId";
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
    /**
     * COUNT, SUM, AVG
     */
    public static final String PARAM_AGGREGAT = "aggregat";
    /**
     * list clause group by
     */
    public static final String PARAM_LIST_GROUPBY = "listFieldsGroupBy";
    /**
     * list clause SORT by
     */
    public static final String PARAM_LIST_SORTBY = "listFieldsSortBy";
    /**
     * limit number of result
     */
    public static final String PARAM_LIMIT_RESULT = "limitResult";
    private static final Logger LOG = LoggerFactory.getLogger(SB_StatisticsVerticle.class);
    @Inject
    protected Utils utils;
    /* Injections */
    @Inject
    private MongoDB mongo;

    /*
     * (non-Javadoc)
     *
     * @see org.vertx.java.platform.Verticle#start()
     */
    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @api {post} /api/1/sandbox/stats/statistics/getStatGroupBy
         * @apiVersion 0.1.0
         * @apiName getStatGroupBy
         * @apiGroup Statistics API
         * @apiPermission all
         *
         * @apiDescription Retrieve the statistics for mandatory parameters
         *
         * @apiParam {Array} listIndicators Mandatory The list of code indicator.
         * @apiParam {String} aggregat Mandatory the aggregat type (SUM, AVG, COUNT).
         * @apiParam {String} listOwners Mandatory The list of owner's stats.
         * @apiParam {long} startDate Mandatory The start periode interval.
         * @apiParam {long} endDate Mandatory the end periode interval.
         * @apiParam {Array} listParams Optional the criterias request.
         * @apiParam {Array} listFieldsGroupBy Optional the clause group by request
         * @apiParam {Array} listFieldsSortBy Optional the clause sort request
         * @apiParam {Array} limitResult Optional the max number element to return
         *
         * @apiSuccess {Array}   Stats    The statistics found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_STAT_GROUPBY, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                LOG.debug(GET_STAT_GROUPBY + " - Statistics");

                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    JsonObject params = new JsonObject(req.getBody());
                    utils.testMandatoryParams(params.toMap(), PARAM_INDICATOR_CODE, PARAM_AGGREGAT, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE);

                    // List of indicators
                    JsonArray listIndicators = params.getArray(PARAM_INDICATOR_CODE);
                    // List of owner
                    JsonArray listOwners = params.getArray(PARAM_LIST_OWNERS);

                    // Dates
                    Long startDate = params.getLong(PARAM_START_DATE);
                    Long endDate = params.getLong(PARAM_END_DATE);

					/*
                     * *** Aggregat section ***
					 */
                    DBObject match, project, group, sort, limit;
                    BasicDBObject dbObjectParent, dbObjectChild;

					/* *** $MACTH section *** */
                    dbObjectParent = new BasicDBObject();

                    // - code
                    dbObjectChild = new BasicDBObject("$in", listIndicators.toArray());
                    dbObjectParent.put("code", dbObjectChild);

                    // - owner
                    dbObjectChild = new BasicDBObject("$in", listOwners.toArray());
                    dbObjectParent.put("owner", dbObjectChild);

                    // - values
                    if (params.containsField(PARAM_VALUES)) {
                        dbObjectChild = new BasicDBObject("$in", params.getArray(PARAM_VALUES));
                        dbObjectParent.put("value", dbObjectChild);
                    }

                    // - shootSeqId
                    if (params.containsField(PARAM_LIST_SHOOTSEQID)) {
                        dbObjectChild = new BasicDBObject("$in", params.getArray(PARAM_LIST_SHOOTSEQID));
                        dbObjectParent.put("shootSeqId", dbObjectChild);
                    }

                    // - timer
                    DBObject o = new BasicDBObject();
                    o.put("$gte", startDate);
                    o.put("$lt", endDate);
                    dbObjectParent.put("timer", o);

                    match = new BasicDBObject("$match", dbObjectParent);

					/* *** $PROJECT section *** 
					dbObjectParent = new BasicDBObject();
                    dbObjectParent.put("owner", "$owner");
                    dbObjectParent.put("code", "$code");
                    dbObjectParent.put("timer", "$timer");
                    dbObjectParent.put("value", "$value");

                    project = new BasicDBObject("$project", dbObjectParent);
                    */
					/* *** $GROUP section *** */
                    dbObjectParent = new BasicDBObject();
                    dbObjectChild = new BasicDBObject();

                    // - _id - List of field for id's group step
                    if (params.containsField(PARAM_LIST_GROUPBY)) {
                        for (Object field : params.getArray(PARAM_LIST_GROUPBY)) {
                            dbObjectChild.append((String) field, "$" + field);
                        }
                    }
                    dbObjectParent.put("_id", dbObjectChild);

                    // - average
                    String aggregat = params.getString(PARAM_AGGREGAT);
                    switch (aggregat) {
                        case "COUNT":
                            dbObjectChild = new BasicDBObject("$sum", 1);
                            dbObjectParent.put("value", dbObjectChild);
                            break;

                        case "SUM":
                            dbObjectChild = new BasicDBObject("$sum", "$value");
                            dbObjectParent.put("value", dbObjectChild);
                            break;

                        case "AVG":
                            dbObjectChild = new BasicDBObject("$avg", "$value");
                            dbObjectParent.put("value", dbObjectChild);
                            break;

                        default:
                            dbObjectChild = new BasicDBObject("$sum", 1);
                            dbObjectParent.put("value", dbObjectChild);
                            break;
                    }
                    group = new BasicDBObject("$group", dbObjectParent);

					/* *** $SORT section *** */
                    dbObjectParent = new BasicDBObject();
                    if (params.containsField(PARAM_LIST_SORTBY)) {
                        for (Object item : params.getArray(PARAM_LIST_SORTBY)) {
                            JsonObject field = (JsonObject) item;
                            dbObjectParent.put(field.getString("fieldName"), field.getInteger("sortOrder"));
                        }
                    } else {
                        dbObjectParent.put("_id", 1);
                    }
                    sort = new BasicDBObject("$sort", dbObjectParent);

                    List<DBObject> pipelineAggregation;
                    if (params.containsField(PARAM_LIMIT_RESULT)) {
                        int limitNumber = params.getInteger(PARAM_LIMIT_RESULT);
                        limit = new BasicDBObject("$limit", limitNumber);
                        pipelineAggregation = Arrays.asList(match, group, sort, limit);
                    } else {
                        pipelineAggregation = Arrays.asList(match, group, sort);
                    }

                    LOG.debug("getStatGroupBy : " + pipelineAggregation.toString());

                    final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, SB_Stats.class);

                    LOG.debug(resultJSon.encodePrettily());
                    message.reply(resultJSon.encode());

                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                }
            }
        });

        /**
         * @api {post} /api/1/sandbox/stats/statistics/getListDetailValue
         * @apiVersion 0.1.0
         * @apiName getListDetailValue
         * @apiGroup Statistics API
         * @apiPermission all
         *
         * @apiDescription Retrieve detail value for statistics for mandatory parameters
         *
         * @apiParam {Array} listIndicators Mandatory The list of code indicator.
         * @apiParam {String} listOwners Mandatory The list of owner's stats.
         * @apiParam {long} startDate Mandatory The start periode interval.
         * @apiParam {long} endDate Mandatory the end periode interval.
         * @apiParam {Array} listParams Optional the criterias request.
         * @apiParam {Array} limitResult Optional the max number element to return
         *
         * @apiSuccess {Array}   Stats    The detail value statistics found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_LISTDETAIL_VALUES, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                LOG.debug(GET_LISTDETAIL_VALUES + " - Statistics");

                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    JsonObject params = new JsonObject(req.getBody());
                    utils.testMandatoryParams(params.toMap(), PARAM_INDICATOR_CODE, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE);

                    JsonArray listIndicators = params.getArray(PARAM_INDICATOR_CODE);

                    JsonArray listOwners = params.getArray(PARAM_LIST_OWNERS);

                    Long startDate = params.getLong(PARAM_START_DATE);
                    Long endDate = params.getLong(PARAM_END_DATE);

                    DBObject match, sort, limit;
                    BasicDBObject dbObjectParent, dbObjectChild;

                    // $MATCH section
                    dbObjectParent = new BasicDBObject();

                    // - code
                    // - code
                    dbObjectChild = new BasicDBObject("$in", listIndicators.toArray());
                    dbObjectParent.put("code", dbObjectChild);

                    // - owner
                    dbObjectChild = new BasicDBObject("$in", listOwners.toArray());
                    dbObjectParent.put("owner", dbObjectChild);

                    // - values
                    if (params.containsField(PARAM_VALUES)) {
                        dbObjectChild = new BasicDBObject("$in", params.getArray(PARAM_VALUES));
                        dbObjectParent.put("value", dbObjectChild);
                    }

                    // - timer
                    DBObject o = new BasicDBObject();
                    o.put("$gte", startDate);
                    o.put("$lt", endDate);
                    dbObjectParent.put("timer", o);

                    match = new BasicDBObject("$match", dbObjectParent);

                    // $SORT section
                    // { $sort: { owner:1, timer: 1 } }
                    dbObjectParent = new BasicDBObject();
                    dbObjectParent.put("owner", 1);
                    dbObjectParent.put("timer", 1);
                    sort = new BasicDBObject("$sort", dbObjectParent);

                    List<DBObject> pipelineAggregation;
                    if (params.containsField(PARAM_LIMIT_RESULT)) {
                        int limitNumber = params.getInteger(PARAM_LIMIT_RESULT);
                        limit = new BasicDBObject("$limit", limitNumber);
                        pipelineAggregation = Arrays.asList(match, sort, limit);
                    } else {
                        pipelineAggregation = Arrays.asList(match, sort);
                    }

                    LOG.debug("getListDetailValue : " + pipelineAggregation.toString());

                    final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, SB_Stats.class);

                    LOG.debug(resultJSon.encodePrettily());
                    message.reply(resultJSon.encode());

                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                }
            }

        });

        /**
         * @api {post} /api/1/sandbox/stats/statistics/add
         * @apiVersion 0.1.0
         * @apiName add
         * @apiGroup Statistics API
         * @apiPermission all
         *
         * @apiDescription add statistic
         *
         * @apiParam {Stats} stats Mandatory The stats object to add.
         *
         * @apiSuccess {Stats}   stats    The stats added.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(ADD_STAT, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                LOG.debug(ADD_STAT + " - Statistics");

                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.PUT, req.getMethod());
                    JsonObject params = new JsonObject(req.getBody());
                    SB_Stats stats = Json.decodeValue(params.encode(), SB_Stats.class);
                    if (stats.getTimer() == 0) {
                        stats.setTimer(new Date().getTime());
                    }

                    String id = mongo.save(stats);
                    if (StringUtils.isEmpty(stats.get_id())) {
                        stats.set_id(id);
                    }
                    message.reply(Json.encode(stats));

                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (EncodeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @api {post} /api/1/sandbox/stats/statistics/addBulk
         * @apiVersion 0.1.0
         * @apiName addBulk
         * @apiGroup Statistics API
         * @apiPermission all
         *
         * @apiDescription add many statistics in once time
         *
         * @apiParam {Array} stats Mandatory The stats object to add.
         *
         * @apiSuccess {Stats}   stats    The stats added.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(ADD_STAT_BULK, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                LOG.debug(ADD_STAT_BULK + " - Statistics");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.PUT, req.getMethod());
                    utils.isUserLogged(req);
                    JsonArray documents = new JsonArray(req.getBody());
                    DBCollection coll = mongo.getDb().getCollection(SB_Stats.class.getSimpleName());
                    BulkWriteOperation bulk = coll.initializeUnorderedBulkOperation();
                    for (Object object : documents) {
                        JsonObject jsonO = (JsonObject) object;
                        DBObject item = (DBObject) JSON.parse(jsonO.encode());
                        bulk.insert(item);
                    }

                    BulkWriteResult resultBulk = bulk.execute();
                    message.reply(new JsonObject().putNumber("count", resultBulk.getInsertedCount()).toString());

                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (EncodeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
    }
}
