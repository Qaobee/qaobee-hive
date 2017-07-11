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
import com.qaobee.hive.services.Activity;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Activity verticle.
 *
 * @author cke
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/commons/settings/activity")
public class ActivityRoute extends AbstractRoute {
    /**
     * Id of the structure
     */
    public static final String PARAM_ID = "_id";
    @Inject
    private Activity activity;


    /**
     * Init router.
     *
     * @return the router
     */
    @Override
    public Router init() {
        Router router = Router.router(vertx);
        router.get("/get").handler(this::get);
        router.get("/list").handler(this::getList);
        router.get("/listEnable").handler(this::getEnabled);
        return router;
    }

    /**
     * @api {get} /api/v1/commons/settings/activity/getListEnable List of enabled activities
     * @apiVersion 0.1.0
     * @apiName getEnabled
     * @apiGroup Activity API
     * @apiPermission all
     * @apiDescription List of enabled activities
     * @apiSuccess {Array}   activities  List of enabled activities
     */
    private void getEnabled(RoutingContext context) {
        activity.getEnabled(handleResponseArray(context));
    }

    /**
     * @api {get} /api/v1/commons/settings/activity/getList List all activities
     * @apiVersion 0.1.0
     * @apiName getList
     * @apiGroup Activity API
     * @apiPermission all
     * @apiDescription get all activity
     * @apiSuccess {Array}   activities List all activity
     */
    private void getList(RoutingContext context) {
        activity.getActivityList(handleResponseArray(context));
    }

    /**
     * @api {get} /api/v1/commons/settings/activity/get Read data of an Activity
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup Activity API
     * @apiPermission all
     * @apiDescription get a activity to the collection activity in settings module
     * @apiParam {String} id The Activity-ID.
     * @apiSuccess {Object} activity The Activity found.
     */
    private void get(RoutingContext context) {
        try {
            utils.testMandatoryParams(context.request().params(), PARAM_ID);
            activity.getActivity(context.request().getParam(PARAM_ID), handleResponse(context));
        } catch (QaobeeException e) {
            handleError(context, e);
        }
    }
}
