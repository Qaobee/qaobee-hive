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
import com.qaobee.hive.business.commons.settings.CountryBusiness;
import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
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
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Country verticle.
 *
 * @author cke
 */
@DeployableVerticle
public class CountryVerticle extends AbstractGuiceVerticle {
    /**
     * The Constant GET.
     */
    public static final String GET = Module.VERSION + ".commons.settings.country.get";
    /**
     * The Constant GET_ALPHA2.
     */
    public static final String GET_ALPHA2 = Module.VERSION + ".commons.settings.country.getAlpha2";
    /**
     * The Constant GET.
     */
    public static final String GET_LIST = Module.VERSION + ".commons.settings.country.getList";
    /**
     * Id of the structure
     */
    public static final String PARAM_ID = "_id";
    /**
     * Alpha 2 code
     */
    public static final String PARAM_ALPHA2 = "alpha2";
    /**
     * Label of the structure
     */
    public static final String PARAM_LABEL = "label";
    /**
     * The constant PARAM_LOCAL.
     */
    public static final String PARAM_LOCAL = "local";
    private static final Logger LOG = LoggerFactory.getLogger(CountryVerticle.class);
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;
    @Inject
    private CountryBusiness countryBusiness;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET, this::getCountryHandler)
                .registerHandler(GET_ALPHA2, this::getAlpha2Handler)
                .registerHandler(GET_LIST, this::getListHandler);
    }

    /**
     * @api {get} /api/1/commons/settings/country/getList Read data of an Country
     * @apiVersion 0.1.0
     * @apiName getList
     * @apiGroup Country API
     * @apiPermission all
     * @apiDescription get a list of countries to the collection Country in settings module
     * @apiParam {String} label Optional The Country label.
     * @apiSuccess {List}   countries            The list of countries found.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET_LIST, method = Constantes.GET, mandatoryParams = {PARAM_LOCAL},
            scope = Rule.Param.REQUEST)
    private void getListHandler(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            Map<String, Object> criterias = new HashMap<>();
            criterias.put(PARAM_LOCAL, req.getParams().get(PARAM_LOCAL).get(0));
            String label = "undefined";
            // label
            if (req.getParams().containsKey(PARAM_LABEL) && StringUtils.isNotBlank(req.getParams().get(PARAM_LABEL).get(0))) {
                label = req.getParams().get(PARAM_LABEL).get(0);
                criterias.put(PARAM_LABEL, label);
            }
            JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, Country.class);
            if (resultJson == null || resultJson.size() == 0) {
                throw new QaobeeException(ExceptionCodes.DATA_ERROR,
                        "No Country defined for (" + label + ")");
            }
            message.reply(resultJson.encode());
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }


    /**
     * @api {get} /api/1/commons/settings/country/getAlpha2 Read data of an Country
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup Country API
     * @apiPermission all
     * @apiDescription get a country to the collection country in settings module
     * @apiParam {String} alpha2 Mandatory The Alpha2.
     * @apiSuccess {Country}   country            The Country found.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET_ALPHA2, method = Constantes.GET, mandatoryParams = {PARAM_ALPHA2},
            scope = Rule.Param.REQUEST)
    private void getAlpha2Handler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            Country country = countryBusiness.getCountryFromAlpha2(req.getParams().get(PARAM_ALPHA2).get(0));
            if (country == null) {
                throw new QaobeeException(ExceptionCodes.DATA_ERROR,
                        "No Country defined for (" + req.getParams().get(PARAM_ALPHA2).get(0) + ")");
            }
            message.reply(Json.encode(country));
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @api {get} /api/1/commons/settings/country/get Read data of an Country
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup Country API
     * @apiPermission all
     * @apiDescription get a country to the collection country in settings module
     * @apiParam {String} id Mandatory The Country-ID.
     * @apiSuccess {Country}   country            The Country found.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET, method = Constantes.GET, mandatoryParams = {PARAM_ID},
            scope = Rule.Param.REQUEST)
    private void getCountryHandler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            final JsonObject json = mongo.getById(req.getParams().get(PARAM_ID).get(0), Country.class);
            message.reply(json.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

}
