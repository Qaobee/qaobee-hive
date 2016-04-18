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

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.commons.settings.CountryBusiness;
import com.qaobee.hive.business.model.commons.referencial.Structure;
import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Module commons - referencial - Structure.
 *
 * @author Nada Vujanic-Maquin<br>
 *         <br>
 *         <strong>Description de la classe:</strong>
 *         <ul>
 *         <li>resthandler.api.1.commons.referencial.structure.add : Add a structure</li>
 *         <li>resthandler.api.1.commons.referencial.structure.get : fetch a structure</li>
 *         <li>resthandler.api.1.commons.referencial.structure.update : update structure</li>
 *         </ul>
 */

@DeployableVerticle(isWorker = true)
public class StructureVerticle extends AbstractGuiceVerticle {
    /**
     * The Constant ADD.
     */
    public static final String ADD = Module.VERSION + ".commons.referencial.structure.add";
    /* List of handlers */
    /**
     * The Constant GET.
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

	/* List of parameters */
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

    /* Injections */
    /**
     * The Mongo.
     */
    @Inject
    private MongoDB mongo;
    /**
     * The Utils.
     */
    @Inject
    private Utils utils;
    /**
     * Country Business
     */
    @Inject
    private CountryBusiness countryBusiness;

    /**
     * Start void.
     */
    @Override
    @VerticleHandler({
                             @Rule(address = ADD, method = Constantes.POST, logged = true,
                                   mandatoryParams = {PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY},
                                   scope = Rule.Param.BODY),
                             @Rule(address = GET, method = Constantes.GET, logged = true, mandatoryParams = {PARAM_ID},
                                   scope = Rule.Param.REQUEST),
                             @Rule(address = GET_LIST, method = Constantes.POST, logged = true,
                                   mandatoryParams = {PARAM_ACTIVITY, PARAM_ADDRESS}, scope = Rule.Param.BODY),
                             @Rule(address = UPDATE, method = Constantes.POST, logged = true,
                                   mandatoryParams = {PARAM_ID, PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY},
                                   scope = Rule.Param.BODY),
                     })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @api {post} /api/1/commons/referencial/structure/add Add structure
         * @apiVersion 0.1.0
         * @apiName add
         * @apiGroup Structure API
         * @apiPermission all
         *
         * @apiDescription Add structure to the collection structure in referencial module
         *
         * @apiParam {String} label Mandatory The Structure label.
         * @apiParam {Activity} activity Mandatory The Structure activity.
         * @apiParam {Country} country Mandatory The Structure country
         * @apiParam {acronym} acronym Optional The Structure acronym.
         * @apiParam {Address} address Optional The Structure-ID.
         * @apiParam {Contact} contact Optional The Structure contact (phone number, email...).
         * @apiParam {String} avatar Optional The Structure logo.
         *
         * @apiSuccess {Structure}   structure            The Structure added with the id.
         *
         * @apiError DATA_ERROR Error on DB request
         */
        vertx.eventBus().registerHandler(ADD, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    final JsonObject params = new JsonObject(req.getBody());
                    // Insert a structure
                    final String id = mongo.save(params, Structure.class);
                    params.putString("_id", id);
                    message.reply(params.encode());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.DATA_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @api {get} /api/1/commons/referencial/structure/get Read data of a Structure
         * @apiVersion 0.1.0
         * @apiName get
         * @apiGroup Structure API
         * @apiPermission all
         *
         * @apiDescription get a structure to the collection structure in referencial module
         *
         * @apiParam {String} id The Structure-ID.
         *
         * @apiSuccess {Structure}   structure            The Structure found.
         *
         * @apiError DATA_ERROR Error on DB request
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    final JsonObject json = mongo.getById(req.getParams().get(PARAM_ID).get(0), Structure.class);
                    message.reply(json.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @api {post} /api/1/commons/referencial/structure/getList Returns list of structures from criterias
         * @apiVersion 0.1.0
         * @apiName getList
         * @apiGroup Structure API
         * @apiPermission all
         *
         * @apiDescription Gets list of structures from criterias from the collection structure in referencial module
         *
         * @apiParam {String} activity The Activity-ID.
         * @apiParam {Object} address The address
         *
         * @apiSuccess {Structure}   structure            The Structure found.
         *
         */
        vertx.eventBus().registerHandler(GET_LIST, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    JsonObject params = new JsonObject(req.getBody());
                    String activity = params.getString(PARAM_ACTIVITY);
                    JsonObject address = params.getObject(PARAM_ADDRESS);
                    Country country = countryBusiness.getCountryFromAlpha2(address.getString("countryAlpha2", "FR"));
                    if (country == null) {
                        throw new QaobeeException(ExceptionCodes.DATA_ERROR,
                                "No Country defined for (" + address.getString("countryAlpha2") + ")");
                    }
                    // Aggregat section
                    DBObject match;
                    BasicDBObject dbObjectParent;
                    // $MACTH section
                    dbObjectParent = new BasicDBObject();
                    // Activity ID
                    dbObjectParent.put("activity._id", activity);
                    // Country ID
                    dbObjectParent.put("country._id", country.get_id());
                    // City OR Zipcode
                    BasicDBList dbList = new BasicDBList();
                    dbList.add(new BasicDBObject("address.city", address.getString("city").toUpperCase()));
                    dbList.add(new BasicDBObject("address.zipcode", address.getString("zipcode")));
                    dbObjectParent.put("$or", dbList.toArray());
                    match = new BasicDBObject("$match", dbObjectParent);
                    // Pipeline
                    List<DBObject> pipelineAggregation = Collections.singletonList(match);
                    final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, Structure.class);
                    message.reply(resultJSon.encode());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @api {post} /api/1/commons/referencial/structure/update Update a structure
         * @apiVersion 0.1.0
         * @apiName update
         * @apiGroup Structure API
         * @apiPermission all
         *
         * @apiDescription Update a structure to the collection structure in referencial module
         *
         * @apiParam {String} _id Mandatory The Structure ID.
         * @apiParam {String} label Mandatory The Structure label.
         * @apiParam {Activity} activity Mandatory The Structure activity.
         * @apiParam {Country} country Mandatory The Structure country
         * @apiParam {acronym} acronym Optional The Structure acronym.
         * @apiParam {Address} address Optional The Structure-ID.
         * @apiParam {Contact} contact Optional The Structure contact (phone number, email...).
         * @apiParam {String} avatar Optional The Structure logo.
         *
         * @apiSuccess {Structure}   structure            The Structure updated.
         *
         * @apiError DATA_ERROR Error on DB request
         */
        vertx.eventBus().registerHandler(UPDATE, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                LOG.debug("update() - Structure");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    final JsonObject params = new JsonObject(req.getBody());
                    // Update a structure
                    mongo.save(params, Structure.class);
                    message.reply(params.encode());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.DATA_ERROR, e.getMessage());
                }
            }
        });
    }
}
