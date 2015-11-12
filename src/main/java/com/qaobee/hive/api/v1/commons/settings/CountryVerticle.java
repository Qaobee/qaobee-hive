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
import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;

import org.apache.commons.lang3.StringUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cke
 */
@DeployableVerticle(isWorker = true)
public class CountryVerticle extends AbstractGuiceVerticle {

    // Declaration des variables finals
    /**
     * The Constant GET.
     */
    public static final String GET = Module.VERSION + ".commons.settings.country.get";
    /**
     * The Constant GET.
     */
    public static final String GET_LIST = Module.VERSION + ".commons.settings.country.getList";

	/* List of parameters */
    /**
     * Id of the structure
     */
    public static final String PARAM_ID = "_id";

    /**
     * Label of the structure
     */
    public static final String PARAM_LABEL = "label";
    public static final String PARAM_LOCAL = "local";
  

    /* Injections */
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;


    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");

        /**
         * @api {get} /api/1/commons/settings/country/get Read data of an Country
         * @apiVersion 0.1.0
         * @apiName get
         * @apiGroup Country API
         * @apiPermission all
         *
         * @apiDescription get a country to the collection country in settings module
         *
         * @apiParam {String} id Mandatory The Country-ID.
         *
         * @apiSuccess {Country}   country            The Country found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        final Handler<Message<String>> get = new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                container.logger().debug("get - Country");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    Map<String, List<String>> params = req.getParams();

                    utils.testMandatoryParams(params, PARAM_ID);

                    // Tests mandatory parameters
                    utils.testMandatoryParams(params, PARAM_ID);
                    if (StringUtils.isBlank(params.get(PARAM_ID).get(0))) {
                        throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, PARAM_ID + " is mandatory");
                    }

                    final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), Country.class);

                    container.logger().debug("Country found : " + json.toString());

                    message.reply(json.encode());

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        };

        /**
         * @api {get} /api/1/commons/settings/country/getList Read data of an Country
         * @apiVersion 0.1.0
         * @apiName getList
         * @apiGroup Country API
         * @apiPermission all
         *
         * @apiDescription get a list of countries to the collection Country in settings module
         *
         * @apiParam {String} label Optional The Country label.
         *
         * @apiSuccess {List}   countries            The list of countries found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        final Handler<Message<String>> getList = new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                container.logger().debug("getList() - Country");
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    // Tests on method and parameters
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    Map<String, List<String>> params = req.getParams();
                    utils.testMandatoryParams(params, PARAM_LOCAL);
                    
                    Map<String, Object> criterias = new HashMap<String, Object>();
                    criterias.put(PARAM_LOCAL, params.get(PARAM_LOCAL).get(0));
                    
                    String label = "undefined";

                    // label
                    if (params.get(PARAM_LABEL) != null && !StringUtils.isBlank(params.get(PARAM_LABEL).get(0))) {
                    	label = params.get(PARAM_LABEL).get(0);
                    	criterias.put(PARAM_LABEL, label);
                    } 
                    
                    JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, Country.class);

                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No Country defined for (" + label + ")");
                    }

                    message.reply(resultJson.encode());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        };

		/*
         * Handlers registration
		 */
        vertx.eventBus().registerHandler(GET, get);
        vertx.eventBus().registerHandler(GET_LIST, getList);
    }

}
