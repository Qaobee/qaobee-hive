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
import com.qaobee.hive.services.Structure;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * Module commons - referencial - Structure.
 *
 * @author Nada Vujanic-Maquin<br>         <br>         <strong>Description de la classe:</strong>         <ul>         <li>resthandler.api.1.commons.referencial.structure.add : Add a structure</li>         <li>resthandler.api.1.commons.referencial.structure.get : fetch a structure</li>         <li>resthandler.api.1.commons.referencial.structure.update : update structure</li>         </ul>
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/commons/referencial/structure")
public class StructureRoute extends AbstractRoute {
/*    public static final String ADD_STRUCTURE = Module.VERSION + ".commons.referencial.structure.add";
    public static final String GET = Module.VERSION + ".commons.referencial.structure.get";
    public static final String GET_LIST = Module.VERSION + ".commons.referencial.structure.getList";
    public static final String UPDATE = Module.VERSION + ".commons.referencial.structure.update";*/
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
    private Structure structure;

    @Override
    public Router init() {
        Router router = Router.router(vertx);
        router.post("/add").handler(authHandler);
        router.post("/add").handler(c -> mandatoryHandler.testBodyParams(c, PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY));
        router.post("/add").handler(this::addStructure);

        router.get("/get").handler(authHandler);
        router.get("/get").handler(c -> mandatoryHandler.testRequesParams(c, PARAM_ID));
        router.get("/get").handler(this::getStructure);

        router.post("/getList").handler(authHandler);
        router.post("/getList").handler(c -> mandatoryHandler.testBodyParams(c, PARAM_ACTIVITY, PARAM_ADDRESS));
        router.post("/getList").handler(this::getListOfStructures);

        router.post("/update").handler(authHandler);
        router.post("/update").handler(c -> mandatoryHandler.testBodyParams(c, "_id", PARAM_ID, PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY));
        router.post("/update").handler(this::updateStructure);
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
        structure.update(context.getBodyAsJson(), handleResponse(context));
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
        structure.getListOfStructures(body.getString(PARAM_ACTIVITY), body.getJsonObject(PARAM_ADDRESS), handleResponseArray(context));
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
        structure.getStructure(context.request().getParam(PARAM_ID), handleResponse(context));
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
        structure.addStructure(context.getBodyAsJson(), handleResponse(context));
    }
}
