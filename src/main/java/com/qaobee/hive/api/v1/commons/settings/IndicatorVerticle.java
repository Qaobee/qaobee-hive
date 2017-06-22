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
import com.qaobee.hive.dao.IndicatorDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

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
    private Utils utils;
    @Inject
    private IndicatorDAO indicatorDAO;

    @Override
    public void start() {
        super.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug(this.getClass().getName() + " started");
        }
        vertx.eventBus().consumer(GET, this::getIndicator);
        vertx.eventBus().consumer(GET_LIST, this::getIndicatorsList);
        vertx.eventBus().consumer(GET_BY_CODE, this::getIndicatorByCode);
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
    @Rule(address = GET_BY_CODE, method = Constants.POST, logged = true,
          mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_INDICATOR_CODE},
          scope = Rule.Param.BODY)
    private void getIndicatorByCode(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject body = new JsonObject(req.getBody());
        replyJsonArray(message, indicatorDAO.getIndicatorByCode(body.getString(PARAM_ACTIVITY_ID),
                body.getString(PARAM_COUNTRY_ID), body.getJsonArray(PARAM_INDICATOR_CODE)));
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
    @Rule(address = GET_LIST, method = Constants.POST, logged = true,
          mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_SCREEN},
          scope = Rule.Param.BODY)
    private void getIndicatorsList(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject body = new JsonObject(req.getBody());
        replyJsonArray(message, indicatorDAO.getIndicatorsList(body.getString(PARAM_ACTIVITY_ID),
                body.getString(PARAM_COUNTRY_ID), body.getJsonArray(PARAM_SCREEN)));
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
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = PARAM_ID,
          scope = Rule.Param.REQUEST)
    private void getIndicator(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, indicatorDAO.getIndicator(req.getParams().get(PARAM_ID).get(0)));
    }
}
