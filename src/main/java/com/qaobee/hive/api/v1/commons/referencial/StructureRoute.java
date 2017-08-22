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
package com.qaobee.hive.api.v1.commons.referencial;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.StructureService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * Module commons - referencial - Structure.
 *
 * @author Nada Vujanic-Maquin<br>         <br>         <strong>Description de la classe:</strong>         <ul>         <li>resthandler.api.1.commons.referencial.structure.add : Add a structure</li>         <li>resthandler.api.1.commons.referencial.structure.get : fetch a structure</li>         <li>resthandler.api.1.commons.referencial.structure.update : update structure</li>         </ul>
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/commons/referencial/structure")
public class StructureRoute extends AbstractRoute {
    /**
     * Id of the structure
     */
    public static final String PARAM_ID = "_id";
    /**
     * Label of the structure
     */
    public static final String PARAM_LABEL = "label";
    /**
     * activity of the structure
     */
    public static final String PARAM_ACTIVITY = "activity";
    /**
     * country of the structure
     */
    public static final String PARAM_COUNTRY = "country";
    /**
     * Address
     */
    public static final String PARAM_ADDRESS = "address";
    @Inject
    private StructureService structureService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/add", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY),
                this::addStructure);

        addRoute(router, "/get", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ID),
                this::getStructure);

        addRoute(router, "/getList", HttpMethod.POST,
              //  authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_ACTIVITY, PARAM_ADDRESS),
                this::getListOfStructures);

        addRoute(router, "/update", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, "_id", PARAM_ID, PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY),
                this::updateStructure);

        return router;
    }

    /**
     * @api {post} /api/1/commons/referencial/structure/update Update a structure
     * @apiVersion 0.1.0
     * @apiName update
     * @apiGroup Structure API
     * @apiPermission all
     * @apiDescription Update a structure to the collection structure in referencial module
     * @apiParam {String} _id Mandatory The Structure ID.
     * @apiParam {String} label Mandatory The Structure label.
     * @apiParam {Activity} activity Mandatory The Structure activity.
     * @apiParam {Country} country Mandatory The Structure country
     * @apiParam {acronym} acronym Optional The Structure acronym.
     * @apiParam {Address} address Optional The Structure-ID.
     * @apiParam {Contact} contact Optional The Structure contact (phone number, email...).
     * @apiParam {String} avatar Optional The Structure logo.
     * @apiSuccess {Structure}   structure  The Structure updated.
     */
    private void updateStructure(RoutingContext context) {
        structureService.update(context.getBodyAsJson(), handleResponse(context));
    }

    /**
     * @api {post} /api/1/commons/referencial/structure/getList Returns list of structures from criterias
     * @apiVersion 0.1.0
     * @apiName getList
     * @apiGroup Structure API
     * @apiPermission all
     * @apiDescription Gets list of structures from criterias from the collection structure in referencial module
     * @apiParam {String} activity The Activity-ID.
     * @apiParam {Object} address The address
     * @apiSuccess {Structure}   structure            The Structure found.
     */
    private void getListOfStructures(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        structureService.getListOfStructures(body.getString(PARAM_ACTIVITY), body.getJsonObject(PARAM_ADDRESS), handleResponseArray(context));
    }

    /**
     * @api {get} /api/1/commons/referencial/structure/get Read data of a Structure
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup Structure API
     * @apiPermission all
     * @apiDescription get a structure to the collection structure in referencial module
     * @apiParam {String} id The Structure-ID.
     * @apiSuccess {Structure}   structure            The Structure found.
     */
    private void getStructure(RoutingContext context) {
        structureService.getStructure(context.request().getParam(PARAM_ID), handleResponse(context));
    }

    /**
     * @api {post} /api/1/commons/referencial/structure/add Add structure
     * @apiVersion 0.1.0
     * @apiName add
     * @apiGroup Structure API
     * @apiPermission all
     * @apiDescription Add structure to the collection structure in referencial module
     * @apiParam {String} label Mandatory The Structure label.
     * @apiParam {Activity} activity Mandatory The Structure activity.
     * @apiParam {Country} country Mandatory The Structure country
     * @apiParam {acronym} acronym Optional The Structure acronym.
     * @apiParam {Address} address Optional The Structure-ID.
     * @apiParam {Contact} contact Optional The Structure contact (phone number, email...).
     * @apiParam {String} avatar Optional The Structure logo.
     * @apiSuccess {Structure}   structure            The Structure added with the id.
     */
    private void addStructure(RoutingContext context) {
        structureService.addStructure(context.getBodyAsJson(), handleResponse(context));
    }
}
