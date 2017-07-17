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

package com.qaobee.hive.api.v1.sandbox.effective;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.EffectiveService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

/**
 * The type Effective verticle.
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/sandbox/effective/effective")
public class SB_EffectiveRoute extends AbstractRoute {// NOSONAR
    /**
     * The constant PARAM_ID.
     */
    public static final String PARAM_ID = "_id";
    /**
     * Sandbox config id
     */
    public static final String PARAM_SANDBOX_ID = "sandboxId";
    /**
     * Category Age Code
     */
    public static final String PARAM_CATEGORY_AGE_CODE = "categoryAge.code";
    @Inject
    private EffectiveService effectiveService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        router.get("/get").handler(authHandler);
        router.get("/get").handler(c->mandatoryHandler.testRequesParams(c, PARAM_ID));
        router.get("/get").handler(this::getEffective);

        router.get("/getList").handler(authHandler);
        router.get("/getList").handler(c->mandatoryHandler.testRequesParams(c, PARAM_SANDBOX_ID));
        router.get("/getList").handler(this::getEffectiveList);

        router.put("/update").handler(authHandler);
        router.put("/update").handler(c->mandatoryHandler.testBodyParams(c, PARAM_ID));
        router.put("/update").handler(this::updateEffective);

        router.post("/add").handler(authHandler);
        router.post("/add").handler(this::addEffective);

        return router;
    }

    /**
     * @api {post} /api/1/sandbox/effective/effective/add
     * @apiVersion 0.1.0
     * @apiName addEffective
     * @apiGroup Effective API
     * @apiDescription add one effective
     * @apiParam {Object} effective Mandatory The effective to add.
     * @apiSuccess {Object}   effective    The effective added.
     * @apiHeader {String} token
     */
    private void addEffective(RoutingContext context) {
        effectiveService.add(context.getBodyAsJson(), handleResponse(context));
    }

    /**
     * @api {post} /api/1/sandbox/effective/effective/update
     * @apiVersion 0.1.0
     * @apiName updateEffective
     * @apiGroup Effective API
     * @apiHeader {String} token
     * @apiDescription Update one effective
     * @apiParam {Object} effective Mandatory The effective to update.
     * @apiSuccess {Object}   effective    The effective updated.
     */
    private void updateEffective(RoutingContext context) {
        effectiveService.update(context.getBodyAsJson(), handleResponse(context));
    }

    /**
     * @api {get} /api/1/sandbox/effective/effective/getList
     * @apiVersion 0.1.0
     * @apiName getEffectiveList
     * @apiGroup SB_Effective API
     * @apiHeader {String} token
     * @apiDescription get a list of effectives for one sandbox Config id
     * @apiParam {String} sandBoxCfgId Mandatory The sandBox config Id.
     * @apiParam {String} categoryCode Optional The category code of the effective.
     * @apiSuccess {Array}   effectives            The list of effectives found.
     */
    private void getEffectiveList(RoutingContext context) {
        String categoryAgeCode = "";
        if (context.request().params().contains(PARAM_CATEGORY_AGE_CODE)&& StringUtils.isNotBlank(context.request().getParam(PARAM_CATEGORY_AGE_CODE))) {
            categoryAgeCode = context.request().getParam(PARAM_CATEGORY_AGE_CODE);
        }
        effectiveService.getEffectiveList(context.request().getParam(PARAM_SANDBOX_ID), categoryAgeCode, handleResponseArray(context));
    }

    /**
     * @api {get} /api/1/sandbox/effective/effective/get
     * @apiVersion 0.1.0
     * @apiName getEffective
     * @apiGroup SB_Effective API
     * @apiDescription Retrieve the effective by id
     * @apiHeader {String} token
     * @apiParam {String} _id Mandatory The effective Id.
     * @apiSuccess {Object}   effective    The effective found.
     */
    private void getEffective(RoutingContext context) {
        effectiveService.getEffective(context.request().getParam(PARAM_ID), handleResponse(context));
    }
}