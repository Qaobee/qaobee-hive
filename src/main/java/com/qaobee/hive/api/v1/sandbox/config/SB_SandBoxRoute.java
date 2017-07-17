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

package com.qaobee.hive.api.v1.sandbox.config;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.SandBoxService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Sand box cfg verticle.
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/sandbox/config/sandbox")
public class SB_SandBoxRoute extends AbstractRoute {// NOSONAR
    /**
     * The constant PARAM_ID.
     */
    public static final String PARAM_ID = "_id";
    /**
     * The constant PARAM_OWNER_ID.
     */
    public static final String PARAM_OWNER_ID = "owner";
    /**
     * The constant PARAM_ACTIVITY_ID.
     */
    public static final String PARAM_ACTIVITY_ID = "activity";

    @Inject
    private SandBoxService sandBoxService;
    
    @Override
    public Router init() {
        Router router = Router.router(vertx);

        router.get("/getByOwner").handler(authHandler);
        router.get("/getByOwner").handler(c->mandatoryHandler.testRequestParams(c, PARAM_ACTIVITY_ID));
        router.get("/getByOwner").handler(this::getByOwner);

        router.get("/getListByOwner").handler(authHandler);
        router.get("/getListByOwner").handler(this::getListByOwner);

        router.get("/").handler(authHandler);
        router.get("/").handler(c->mandatoryHandler.testRequestParams(c, PARAM_ID));
        router.get("/").handler(this::getSandboxById);

        router.post("/update").handler(authHandler);
        router.post("/update").handler(c->mandatoryHandler.testBodyParams(c, PARAM_ID));
        router.post("/update").handler(this::update);

        return router;
    }

    /**
     * @apiDescription Get an enriched SB_SandBox
     * @api {post} /api/v1/sandbox/config/sandbox Get an enriched SB_SandBox
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName getSandboxById
     * @apiHeader {String} token
     * @apiGroup SandBox API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    private void getSandboxById(RoutingContext context) {
        sandBoxService.getSandboxById(context.request().getParam(PARAM_ID), handleResponse(context));
    }

    /**
     * @api {post} /api/v1/sandbox/config/sandbox/getListByOwner
     * @apiVersion 0.1.0
     * @apiName getListByOwner
     * @apiGroup SandBox API
     * @apiDescription Retrieve the user's sandbox
     * @apiParam {String} activityId Mandatory The sandBox activity.
     * @apiSuccess {Object}   sandBox    The sandBox updated.
     */
    private void getListByOwner(RoutingContext context) {
        sandBoxService.getListByOwner(context.request().params().getAll(PARAM_OWNER_ID), context.user().principal().getString("_id"), handleResponseArray(context));
    }

    /**
     * @api {post} /api/v1/sandbox/config/sandbox/getByOwner
     * @apiVersion 0.1.0
     * @apiName getByOwner
     * @apiGroup SandBox API
     * @apiDescription Retrieve the user's sandbox
     * @apiParam {String} activityId Mandatory The sandBox activity.
     * @apiSuccess {sandBox}   sandBox    The sandBox updated.
     */
    private void getByOwner(RoutingContext context) {
        sandBoxService.getByOwner(context.request().getParam(PARAM_ACTIVITY_ID), context.user().principal().getString("_id"), handleResponse(context));
    }

    /**
     * @apiDescription Update sandbox
     * @api {post} /api/1/sandbox/config/sandbox/update Update sandbox
     * @apiVersion 0.1.0
     * @apiName update
     * @apiGroup SandBox API
     * @apiHeader {String} token
     * @apiParam {String} _id sandbox id
     * @apiParam {String} sandboxCfgId sandboxCfg id
     * @apiSuccess {Object} sandbox
     */
    private void update(RoutingContext context) {
        sandBoxService.updateSandbox(context.getBodyAsJson(), handleResponse(context));
    }
}
