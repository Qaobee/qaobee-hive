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
import com.qaobee.hive.dao.CountryDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

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
    @Inject
    private CountryDAO countryDAO;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this)
                .add(GET, this::get)
                .add(GET_ALPHA2, this::getAlpha2)
                .add(GET_LIST, this::getList)
                .register(startFuture);
    }

    /**
     * @api {get} /api/1/commons/settings/country/getList Read data of an Country
     * @apiVersion 0.1.0
     * @apiName getList
     * @apiGroup Country API
     * @apiPermission all
     * @apiDescription get a list of countries to the collection Country in settings module
     * @apiParam {String} label Optional The Country label.
     * @apiSuccess {Array} countries The list of countries found.
     */
    @Rule(address = GET_LIST, method = Constants.GET, mandatoryParams = PARAM_LOCAL,
            scope = Rule.Param.REQUEST)
    private void getList(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        String label = null;
        if (req.getParams().containsKey(CountryVerticle.PARAM_LABEL) && StringUtils.isNotBlank(req.getParams().get(PARAM_LABEL).get(0))) {
            label = req.getParams().get(PARAM_LABEL).get(0);
        }
        replyJsonArray(message, countryDAO.getCountryList(req.getParams().get(PARAM_LOCAL).get(0), label));
    }

    /**
     * @api {get} /api/1/commons/settings/country/getAlpha2 Read data of an Country
     * @apiVersion 0.1.0
     * @apiName getAlpha2
     * @apiGroup Country API
     * @apiPermission all
     * @apiDescription get a country to the collection country in settings module
     * @apiParam {String} alpha2 Mandatory The Alpha2.
     * @apiSuccess {Object} country The Country found.
     */
    @Rule(address = GET_ALPHA2, method = Constants.GET, mandatoryParams = PARAM_ALPHA2,
            scope = Rule.Param.REQUEST)
    private void getAlpha2(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        countryDAO.getCountryFromAlpha2(req.getParams().get(PARAM_ALPHA2).get(0)).done(country -> {
            if (country == null) {
                utils.sendError(message, new QaobeeException(ExceptionCodes.DATA_ERROR,
                        "No Country defined for (" + req.getParams().get(PARAM_ALPHA2) + ")"));
            } else {
                message.reply(country.encode());
            }
        }).fail(e -> utils.sendError(message, e));
    }

    /**
     * @api {get} /api/1/commons/settings/country/get Read data of an Country
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup Country API
     * @apiPermission all
     * @apiDescription get a country to the collection country in settings module
     * @apiParam {String} id Mandatory The Country-ID.
     * @apiSuccess {Country} country The Country found.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET, method = Constants.GET, mandatoryParams = PARAM_ID,
            scope = Rule.Param.REQUEST)
    private void get(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, countryDAO.getCountry(req.getParams().get(PARAM_ID).get(0)));
    }
}
