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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.settings.IndicatorCfg;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The type Indicator verticle.
 *
 * @author cke
 */
@DeployableVerticle(isWorker = true)
public class IndicatorVerticle extends AbstractGuiceVerticle {

    // Declaration des variables finals
    /**
     * The Constant GET.
     */
    public static final String GET = Module.VERSION + ".commons.settings.indicator.get";

    /**
     * Handler for retrieve list of indicators
     */
    public static final String GET_LIST = Module.VERSION + ".commons.settings.indicator.getList";

    /**
     * Handler for retrieve one indicator by his code
     */
    public static final String GET_BY_CODE = Module.VERSION + ".commons.settings.indicator.getByCode";

	/* List of parameters */
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


    /**
     * The Mongo.
     */
/* Injections */
    @Inject
    private MongoDB mongo;
    /**
     * The Utils.
     */
    @Inject
    private Utils utils;


    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");

        /**
         * @api {get} /api/1/commons/settings/indicator/get Read data of an indicator
         * @apiVersion 0.1.0
         * @apiName get
         * @apiGroup Indicator API
         * @apiPermission all
         *
         * @apiDescription get a indicator to the collection indicator in settings module
         *
         * @apiParam {String} id Mandatory The Indicator-ID.
         *
         * @apiSuccess {Indicator}   indicator            The Indicator found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                container.logger().info("get() - Indicator");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    Map<String, List<String>> params = req.getParams();

                    utils.testMandatoryParams(params, PARAM_ID);
                    utils.isUserLogged(req);

                    // Tests mandatory parameters
                    utils.testMandatoryParams(params, PARAM_ID);
                    if (StringUtils.isBlank(params.get(PARAM_ID).get(0))) {
                        throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, PARAM_ID + " is mandatory");
                    }

                    final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), IndicatorCfg.class);

                    container.logger().info("Indicator found : " + json.toString());

                    message.reply(json.encode());

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @api {get} /api/1/commons/settings/indicator/getList Get a list of indicators
         * @apiVersion 0.1.0
         * @apiName getList
         * @apiGroup Indicator API
         * @apiPermission all
         *
         * @apiDescription get a list of indicators to the collection indicator in settings module
         *
         * @apiParam {String} activityId Mandatory The activity Id.
         * @apiParam {String} countryId Mandatory The country Id.
         * @apiParam {List} screen Mandatory The list of screen name.
         *
         * @apiSuccess {List}   indicators            The list of indicators found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_LIST, new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                container.logger().info("getList() - Indicator");
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    JsonObject params = new JsonObject(req.getBody());
                    utils.testMandatoryParams(params.toMap(), PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_SCREEN);
                    utils.isUserLogged(req);

                    // Activity ID
                    String activityId = params.getString(PARAM_ACTIVITY_ID);
                    // Country ID
                    String countryId = params.getString(PARAM_COUNTRY_ID);
                    // SCREEN
                    JsonArray screen = params.getArray(PARAM_SCREEN);


                    DBObject match, project;
                    BasicDBObject dbObjectParent, dbObjectChild;

                    // $MATCH section
                    dbObjectParent = new BasicDBObject();

                    // - activity code
                    dbObjectParent.put("activityId", activityId);

                    // - country
                    dbObjectParent.put("countryId", countryId);

                    // - SCREEN
                    dbObjectChild = new BasicDBObject("$in", screen.toArray());
                    dbObjectParent.put("listScreen", dbObjectChild);

                    match = new BasicDBObject("$match", dbObjectParent);

                    // $PROJECT section
                    // { $project : { "_id" : 0, listIndicator : {code : 1, label : 1}}}
                    dbObjectParent = new BasicDBObject();
                    dbObjectParent.put("_id", 1);
                    dbObjectParent.put("code", 1);
                    dbObjectParent.put("activityId", 1);
                    dbObjectParent.put("indicatorType", 1);
                    dbObjectParent.put("listScreen", 1);
                    dbObjectParent.put("listField", 1);
                    dbObjectParent.put("listValues", 1);

                    project = new BasicDBObject("$project", dbObjectParent);

                    List<DBObject> pipelineAggregation = Arrays.asList(match, project);

                    final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, IndicatorCfg.class);

                    container.logger().info(resultJSon.encodePrettily());
                    message.reply(resultJSon.encode());

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @api {get} /api/1/commons/settings/indicator/getByCode Get indicators by code
         * @apiVersion 0.1.0
         * @apiName getByCode
         * @apiGroup Indicator API
         * @apiPermission all
         *
         * @apiDescription get a list of indicators by code
         *
         * @apiParam {String} activityId Mandatory The activity Id.
         * @apiParam {String} countryId Mandatory The country Id.
         * @apiParam {Array} listIndicators Mandatory list of indicator's codes
         *
         * @apiSuccess {List}   indicators            The list of indicators found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_BY_CODE, new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    JsonObject params = new JsonObject(req.getBody());
                    utils.testMandatoryParams(params.toMap(), PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID,
                            PARAM_INDICATOR_CODE);

                    // Indicator code
                    String activityId = params.getString(PARAM_ACTIVITY_ID);
                    // Country ID
                    String countryId = params.getString(PARAM_COUNTRY_ID);
                    // List of indicators
                    JsonArray listIndicators = params.getArray(PARAM_INDICATOR_CODE);

                    DBObject match;
                    BasicDBObject dbObjectParent, dbObjectChild;

                    // $MATCH section
                    dbObjectParent = new BasicDBObject();

                    // - activity code
                    dbObjectParent.put("activityId", activityId);

                    // - country
                    dbObjectParent.put("countryId", countryId);

                    // - code
                    dbObjectChild = new BasicDBObject("$in", listIndicators.toArray());
                    dbObjectParent.put("code", dbObjectChild);

                    match = new BasicDBObject("$match", dbObjectParent);

                    List<DBObject> pipelineAggregation = Collections.singletonList(match);

                    final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, IndicatorCfg.class);
                    message.reply(resultJSon.encode());

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });
    }
}
