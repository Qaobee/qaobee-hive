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
import com.qaobee.hive.dao.StructureDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Module commons - referencial - Structure.
 *
 * @author Nada Vujanic-Maquin<br>         <br>
 *         <strong>Description de la classe:</strong>
 *         <ul>
 *         <li>resthandler.api.1.commons.referencial.structure.add : Add a structure</li>
 *         <li>resthandler.api.1.commons.referencial.structure.get : fetch a structure</li>
 *         <li>resthandler.api.1.commons.referencial.structure.update : update structure</li>
 *         </ul>
 */
@DeployableVerticle
public class StructureVerticle extends AbstractGuiceVerticle {
    /**
     * The Constant ADD_TO_USER.
     */
    public static final String ADD = Module.VERSION + ".commons.referencial.structure.add";
    /* List of handlers */
    /**
     * The Constant GET
     */
    public static final String GET = Module.VERSION + ".commons.referencial.structure.get";
    /**
     * The Constant GET_LIST.
     */
    public static final String GET_LIST = Module.VERSION + ".commons.referencial.structure.getList";
    /**
     * The Constant UPDATE.
     */
    public static final String UPDATE = Module.VERSION + ".commons.referencial.structure.update";
    /**
     * Id of the structure
     */
    public static final String PARAM_ID = "_id";

    // List of parameters
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
    private static final Logger LOG = LoggerFactory.getLogger(StructureVerticle.class);
    @Inject
    private StructureDAO structureDAO;

    @Override
    public void start() {
        super.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug(this.getClass().getName() + " started");
        }
        vertx.eventBus().consumer(ADD, this::addStructure);
        vertx.eventBus().consumer(GET, this::getStructure);
        vertx.eventBus().consumer(GET_LIST, this::getListOfStructures);
        vertx.eventBus().consumer(UPDATE, this::updateStructure);
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
     * @apiSuccess {Structure}   structure            The Structure updated.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = UPDATE, method = Constants.POST, logged = true,
          mandatoryParams = {PARAM_ID, PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY},
          scope = Rule.Param.BODY)
    private void updateStructure(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyString(message, structureDAO.update(new JsonObject(req.getBody())));
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
    @Rule(address = GET_LIST, method = Constants.POST, logged = true,
          mandatoryParams = {PARAM_ACTIVITY, PARAM_ADDRESS}, scope = Rule.Param.BODY)
    private void getListOfStructures(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject body = new JsonObject(req.getBody());
        replyJsonArray(message, structureDAO.getListOfStructures(body.getString(PARAM_ACTIVITY), body.getJsonObject(PARAM_ADDRESS)));
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
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = PARAM_ID,
          scope = Rule.Param.REQUEST)
    private void getStructure(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, structureDAO.getStructure(req.getParams().get(PARAM_ID).get(0)));
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
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = ADD, method = Constants.POST, logged = true,
          mandatoryParams = {PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY},
          scope = Rule.Param.BODY)
    private void addStructure(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, structureDAO.addStructure(new JsonObject(req.getBody())));
    }
}
