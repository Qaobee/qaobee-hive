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
import com.qaobee.hive.services.ActivityCfgService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.exceptions.QaobeeSvcException;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Activity cfg verticle.
 */
@VertxRoute(rootPath = "/api/v" + Module.VERSION + "/commons/settings/activitycfg")
public class ActivityCfgRoute extends AbstractRoute {
    public static final String PARAM_FIELD_LIST = "paramFieldList";
    public static final String PARAM_DATE = "date";
    public static final String PARAM_ACTIVITY_ID = "activityId";
    public static final String PARAM_COUNTRY_ID = "countryId";

    @Inject
    private ActivityCfgService activityCfgService;

    /**
     * Init router.
     *
     * @return the router
     */
    @Override
    public Router init() {
        Router router = Router.router(vertx);
        router.get("/*").handler(authHandler);
        router.get("/get").handler(this::getActivityCfgHandler);
        router.get("/params").handler(this::getActivityCfgParamsHandler);
        return router;
    }

    /**
     * @apiDescription retrieve a list of value for one parameter ActivityCfg
     * @api {post} /api/1/commons/settings/activitycfg/params params ActivityCfg
     * @apiVersion 0.1.0
     * @apiName params
     * @apiGroup ActivityCfg API
     * @apiHeader {String} token
     * @apiParam {String} activityId Activity Id
     * @apiParam {String} countryId Country Id
     * @apiParam {long} date the current date
     * @apiParam {String} paramFieldList the list of value
     */
    private void getActivityCfgParamsHandler(RoutingContext context) {
        try {
            utils.testMandatoryParams(context.request().params(), PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE, PARAM_FIELD_LIST);
            activityCfgService.getActivityCfgParams(
                    context.request().getParam(PARAM_ACTIVITY_ID),
                    context.request().getParam(PARAM_COUNTRY_ID),
                    Long.parseLong(context.request().getParam(PARAM_DATE)),
                    context.request().getParam(PARAM_FIELD_LIST),
                    ar -> {
                        if (ar.succeeded()) {
                            handleResponse(context, ar.result().getJsonObject(0).getJsonArray(context.request().getParam(PARAM_FIELD_LIST)));
                        } else {
                            ar.cause().printStackTrace();
                            handleError(context, (QaobeeSvcException) ar.cause());
                        }
                    });
        } catch (QaobeeException e) {
            handleError(context, e);
        }
    }

    /**
     * @apiDescription Fetch ActivityCfg
     * @api {post} /api/1/commons/settings/activitycfg/get Get ActivityCfg
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup ActivityCfg API
     * @apiHeader {String} token
     * @apiParam {String} activityId Activity Id
     */
    private void getActivityCfgHandler(RoutingContext context) {
        try {
            utils.testMandatoryParams(context.request().params(), PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE);
            activityCfgService.getActivityCfg(
                    context.request().getParam(PARAM_ACTIVITY_ID),
                    context.request().getParam(PARAM_COUNTRY_ID),
                    Long.parseLong(context.request().getParam(PARAM_DATE)), handleResponse(context));
        } catch (QaobeeException e) {
            handleError(context, e);
        }
    }
}
