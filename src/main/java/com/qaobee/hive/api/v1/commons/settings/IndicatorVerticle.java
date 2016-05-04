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
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The type Indicator verticle.
 *
 * @author cke
 */
@DeployableVerticle
public class IndicatorVerticle extends AbstractGuiceVerticle {
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
    private static final Logger LOG = LoggerFactory.getLogger(IndicatorVerticle.class);
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET, this::getIndicatorHandler)
                .registerHandler(GET_LIST, this::getIndicatorsListHandler)
                .registerHandler(GET_BY_CODE, this::getIndicatorByCodeHandler);
    }

    /**
     * @api {get} /api/1/commons/settings/indicator/getByCode Get indicators by code
     * @apiVersion 0.1.0
     * @apiName getByCode
     * @apiGroup Indicator API
     * @apiPermission all
     * @apiDescription get a list of indicators by code
     * @apiParam {String} activityId Mandatory The activity Id.
     * @apiParam {String} countryId Mandatory The country Id.
     * @apiParam {Array} listIndicators Mandatory list of indicator's codes
     * @apiSuccess {List}   indicators            The list of indicators found.
     */
    @Rule(address = GET_BY_CODE, method = Constantes.POST, logged = true,
            mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_INDICATOR_CODE},
            scope = Rule.Param.BODY)
    private void getIndicatorByCodeHandler(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            JsonObject params = new JsonObject(req.getBody());
            // Indicator code
            String activityId = params.getString(PARAM_ACTIVITY_ID);
            // Country ID
            String countryId = params.getString(PARAM_COUNTRY_ID);
            // List of indicators
            JsonArray listIndicators = params.getArray(PARAM_INDICATOR_CODE);
            DBObject match;
            BasicDBObject dbObjectParent;
            BasicDBObject dbObjectChild;
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
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
        }
    }

    /**
     * @api {get} /api/1/commons/settings/indicator/getList Get a list of indicators
     * @apiVersion 0.1.0
     * @apiName getList
     * @apiGroup Indicator API
     * @apiPermission all
     * @apiDescription get a list of indicators to the collection indicator in settings module
     * @apiParam {String} activityId Mandatory The activity Id.
     * @apiParam {String} countryId Mandatory The country Id.
     * @apiParam {List} screen Mandatory The list of screen name.
     * @apiSuccess {List}   indicators            The list of indicators found.
     */
    @Rule(address = GET_LIST, method = Constantes.POST, logged = true,
            mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_SCREEN},
            scope = Rule.Param.BODY)
    private void getIndicatorsListHandler(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            JsonObject params = new JsonObject(req.getBody());
            // Activity ID
            String activityId = params.getString(PARAM_ACTIVITY_ID);
            // Country ID
            String countryId = params.getString(PARAM_COUNTRY_ID);
            // SCREEN
            JsonArray screen = params.getArray(PARAM_SCREEN);
            DBObject match;
            DBObject project;
            BasicDBObject dbObjectParent;
            BasicDBObject dbObjectChild;
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
            message.reply(resultJSon.encode());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
        }
    }

    /**
     * @api {get} /api/1/commons/settings/indicator/get Read data of an indicator
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup Indicator API
     * @apiPermission all
     * @apiDescription get a indicator to the collection indicator in settings module
     * @apiParam {String} id Mandatory The Indicator-ID.
     * @apiSuccess {Indicator}   indicator            The Indicator found.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET, method = Constantes.GET, logged = true, mandatoryParams = {PARAM_ID},
            scope = Rule.Param.REQUEST)
    private void getIndicatorHandler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            final JsonObject json = mongo.getById(req.getParams().get(PARAM_ID).get(0), IndicatorCfg.class);
            message.reply(json.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
