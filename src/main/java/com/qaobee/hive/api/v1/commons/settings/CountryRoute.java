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
import com.qaobee.hive.services.CountryService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

/**
 * The type Country verticle.
 *
 * @author cke
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/commons/settings/country")
public class CountryRoute extends AbstractRoute {
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
    private CountryService countryService;


    @Override
    public Router init() {
        Router router = Router.router(vertx);
        router.get("/get").handler(this::get);
        router.get("/getAlpha2").handler(this::getAlpha2);
        router.get("/getList").handler(this::getList);
        return router;
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
    private void getList(RoutingContext context) {
        try {
            utils.testMandatoryParams(context.request().params(), PARAM_LOCAL);
            String label = null;
            if (context.request().params().contains(CountryRoute.PARAM_LABEL) && StringUtils.isNotBlank(context.request().getParam(PARAM_LABEL))) {
                label = context.request().getParam(PARAM_LABEL);
            }
            countryService.getCountryList(context.request().getParam(PARAM_LOCAL), label, handleResponseArray(context));
        } catch (QaobeeException e) {
            handleError(context, e);
        }
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
    private void getAlpha2(RoutingContext context) {
        try {
            utils.testMandatoryParams(context.request().params(), PARAM_ALPHA2);
            countryService.getCountryFromAlpha2(context.request().getParam(PARAM_ALPHA2), handleResponse(context));
        } catch (QaobeeException e) {
            handleError(context, e);
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
     * @apiSuccess {Country} country The Country found.
     * @apiError DATA_ERROR Error on DB request
     */
    private void get(RoutingContext context) {
        try {
            utils.testMandatoryParams(context.request().params(), PARAM_ID);
            countryService.getCountry(context.request().getParam(PARAM_ID), handleResponse(context));
        } catch (QaobeeException e) {
            handleError(context, e);
        }
    }
}
