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
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
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
import java.util.List;
import java.util.UUID;

/**
 * @author cke
 */
@DeployableVerticle()
public class SB_StatisticsVerticle extends AbstractGuiceVerticle { // NOSONAR
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
    private static final String OWNER_FIELD = "owner";
    private static final String CODE_FIELD = "code";
    private static final String VALUE_FIELD = "value";
    private static final String TIMER_FIELD = "timer";
    @Inject
    protected Utils utils;
    /* Injections */
    @Inject
    private MongoDB mongo;

    @Override
    @VerticleHandler({
            @Rule(address = GET_STAT_GROUPBY, method = Constantes.POST, logged = true,
                    mandatoryParams = {PARAM_INDICATOR_CODE, PARAM_AGGREGAT, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE},
                    scope = Rule.Param.BODY),
            @Rule(address = GET_LISTDETAIL_VALUES, method = Constantes.POST, logged = true,
                    mandatoryParams = {PARAM_INDICATOR_CODE, PARAM_LIST_OWNERS, PARAM_START_DATE, PARAM_END_DATE},
                    scope = Rule.Param.BODY),
            @Rule(address = ADD_STAT, method = Constantes.PUT, logged = true, mandatoryParams = {CODE_FIELD, TIMER_FIELD, OWNER_FIELD},
                    scope = Rule.Param.BODY),
            @Rule(address = ADD_STAT_BULK, method = Constantes.PUT, logged = true)
    })
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
         * @apiParam {String} aggregat Mandatory the aggregate type (SUM, AVG, COUNT).
         * @apiParam {String} listOwners Mandatory The list of owner's stats.
         * @apiParam {long} startDate Mandatory The start period interval.
         * @apiParam {long} endDate Mandatory the end period interval.
         * @apiParam {Array} listParams Optional the criteria request.
         * @apiParam {Array} listFieldsGroupBy Optional the clause group by request
         * @apiParam {Array} listFieldsSortBy Optional the clause sort request
         * @apiParam {Array} limitResult Optional the max number element to return
         *
         * @apiSuccess {Array}   Stats    The statistics found.
         *
         */
        vertx.eventBus().registerHandler(GET_STAT_GROUPBY, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                JsonObject params = new JsonObject(req.getBody());
                // List of indicators
                JsonArray listIndicators = params.getArray(PARAM_INDICATOR_CODE);
                // List of owner
                JsonArray listOwners = params.getArray(PARAM_LIST_OWNERS);
                // Dates
                Long startDate = params.getLong(PARAM_START_DATE);
                Long endDate = params.getLong(PARAM_END_DATE);
                // Aggregate section
                DBObject match;
                DBObject group;
                DBObject sort;
                DBObject limit;
                BasicDBObject dbObjectParent;
                BasicDBObject dbObjectChild;
                // $MACTH section
                dbObjectParent = new BasicDBObject();
                // - code
                dbObjectChild = new BasicDBObject("$in", listIndicators.toArray());
                dbObjectParent.put(CODE_FIELD, dbObjectChild);
                // - owner
                dbObjectChild = new BasicDBObject("$in", listOwners.toArray());
                dbObjectParent.put(OWNER_FIELD, dbObjectChild);
                // - values
                if (params.containsField(PARAM_VALUES)) {
                    dbObjectChild = new BasicDBObject("$in", params.getArray(PARAM_VALUES));
                    dbObjectParent.put(VALUE_FIELD, dbObjectChild);
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
                dbObjectParent.put(TIMER_FIELD, o);
                match = new BasicDBObject("$match", dbObjectParent);
                // $GROUP section
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
                String aggregate = params.getString(PARAM_AGGREGAT);
                switch (aggregate) {
                    case "COUNT":
                        dbObjectChild = new BasicDBObject("$sum", 1);
                        dbObjectParent.put(VALUE_FIELD, dbObjectChild);
                        break;
                    case "SUM":
                        dbObjectChild = new BasicDBObject("$sum", "$value");
                        dbObjectParent.put(VALUE_FIELD, dbObjectChild);
                        break;
                    case "AVG":
                        dbObjectChild = new BasicDBObject("$avg", "$value");
                        dbObjectParent.put(VALUE_FIELD, dbObjectChild);
                        break;
                    default:
                        dbObjectChild = new BasicDBObject("$sum", 1);
                        dbObjectParent.put(VALUE_FIELD, dbObjectChild);
                        break;
                }
                group = new BasicDBObject("$group", dbObjectParent);
                // $SORT section
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
                final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, SB_Stats.class);
                message.reply(resultJSon.encode());
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
         * @apiParam {long} startDate Mandatory The start period interval.
         * @apiParam {long} endDate Mandatory the end period interval.
         * @apiParam {Array} listParams Optional the criteria request.
         * @apiParam {Array} limitResult Optional the max number element to return
         *
         * @apiSuccess {Array}   Stats    The detail value statistics found.
         *
         */
        vertx.eventBus().registerHandler(GET_LISTDETAIL_VALUES, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                JsonObject params = new JsonObject(req.getBody());
                JsonArray listIndicators = params.getArray(PARAM_INDICATOR_CODE);
                JsonArray listOwners = params.getArray(PARAM_LIST_OWNERS);
                Long startDate = params.getLong(PARAM_START_DATE);
                Long endDate = params.getLong(PARAM_END_DATE);
                DBObject match, sort, limit;
                BasicDBObject dbObjectParent, dbObjectChild;
                // $MATCH section
                dbObjectParent = new BasicDBObject();
                // - code
                dbObjectChild = new BasicDBObject("$in", listIndicators.toArray());
                dbObjectParent.put(CODE_FIELD, dbObjectChild);
                // - owner
                dbObjectChild = new BasicDBObject("$in", listOwners.toArray());
                dbObjectParent.put(OWNER_FIELD, dbObjectChild);
                // - values
                if (params.containsField(PARAM_VALUES)) {
                    dbObjectChild = new BasicDBObject("$in", params.getArray(PARAM_VALUES));
                    dbObjectParent.put(VALUE_FIELD, dbObjectChild);
                }
                // - timer
                DBObject o = new BasicDBObject();
                o.put("$gte", startDate);
                o.put("$lt", endDate);
                dbObjectParent.put(TIMER_FIELD, o);
                match = new BasicDBObject("$match", dbObjectParent);
                dbObjectParent = new BasicDBObject();
                dbObjectParent.put(OWNER_FIELD, 1);
                dbObjectParent.put(TIMER_FIELD, 1);
                sort = new BasicDBObject("$sort", dbObjectParent);
                List<DBObject> pipelineAggregation;
                if (params.containsField(PARAM_LIMIT_RESULT)) {
                    int limitNumber = params.getInteger(PARAM_LIMIT_RESULT);
                    limit = new BasicDBObject("$limit", limitNumber);
                    pipelineAggregation = Arrays.asList(match, sort, limit);
                } else {
                    pipelineAggregation = Arrays.asList(match, sort);
                }
                final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, SB_Stats.class);
                message.reply(resultJSon.encode());
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
         * @apiError DATA_ERROR Error on DB request
         */
        vertx.eventBus().registerHandler(ADD_STAT, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    JsonObject stat = new JsonObject(req.getBody());
                    if (!stat.containsField("timer") || Integer.valueOf(0).equals(stat.getInteger("timer"))) {
                        stat.putNumber("timer", System.currentTimeMillis());
                    }
                    stat.putString("_id", mongo.save(stat, SB_Stats.class));
                    message.reply(stat.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
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
         */
        vertx.eventBus().registerHandler(ADD_STAT_BULK, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    JsonArray documents = new JsonArray(req.getBody());
                    DBCollection coll = mongo.getDb().getCollection(SB_Stats.class.getSimpleName());
                    BulkWriteOperation bulk = coll.initializeUnorderedBulkOperation();
                    for (Object object : documents) {
                        JsonObject jsonO = (JsonObject) object;
                        DBObject item = (DBObject) JSON.parse(jsonO.encode());
                        item.put("_id", UUID.randomUUID().toString());
                        bulk.insert(item);
                    }
                    BulkWriteResult resultBulk = bulk.execute();
                    message.reply(new JsonObject().putNumber("count", resultBulk.getInsertedCount()).toString());
                } catch (EncodeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                }
            }
        });
    }
}
