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
import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
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
import java.util.HashMap;
import java.util.Map;

/**
 * The type Activity verticle.
 *
 * @author cke
 */
@DeployableVerticle
public class ActivityVerticle extends AbstractGuiceVerticle {
    /**
     * The Constant GET.
     */
    public static final String GET = Module.VERSION + ".commons.settings.activity.get";
    // Declaration des variables finals
    /**
     * The Constant GET_LIST.
     */
    public static final String GET_LIST = Module.VERSION + ".commons.settings.activity.list";
    /**
     * The Constant GET_LIST_ENABLE.
     */
    public static final String GET_LIST_ENABLE = Module.VERSION + ".commons.settings.activity.listEnable";
    /**
     * Id of the structure
     */
    public static final String PARAM_ID = "_id";

    /* List of parameters */
    private static final Logger LOG = LoggerFactory.getLogger(ActivityVerticle.class);
    /* Injections */
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;

    @Override
    @VerticleHandler({
            @Rule(address = GET, method = Constantes.GET, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST),
            @Rule(address = GET_LIST, method = Constantes.GET),
            @Rule(address = GET_LIST_ENABLE, method = Constantes.GET)
    })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @api {get} /api/v1/commons/settings/activity/get Read data of an Activity
         * @apiVersion 0.1.0
         * @apiName get
         * @apiGroup Activity API
         * @apiPermission all
         *
         * @apiDescription get a activity to the collection activity in settings module
         *
         * @apiParam {String} id The Activity-ID.
         *
         * @apiSuccess {Activity} activity The Activity found.
         *
         * @apiError DATA_ERROR Error on DB request
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    final JsonObject json = mongo.getById(req.getParams().get(PARAM_ID).get(0), Activity.class);
                    message.reply(json.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @api {get} /api/v1/commons/settings/activity/getList List all activities
         * @apiVersion 0.1.0
         * @apiName getList
         * @apiGroup Activity API
         * @apiPermission all
         *
         * @apiDescription get all activity
         *
         * @apiSuccess {List}   activities            List all activity
         *
         */
        vertx.eventBus().registerHandler(GET_LIST, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                JsonArray resultJson = mongo.findByCriterias(null, null, null, -1, -1, Activity.class);
                message.reply(resultJson.encode());
            }
        });

        /**
         * @api {get} /api/v1/commons/settings/activity/getListEnable List of enabled activities
         * @apiVersion 0.1.0
         * @apiName getListEnable
         * @apiGroup Activity API
         * @apiPermission all
         *
         * @apiDescription List of enabled activities
         *
         * @apiSuccess {List}   activities  List of enabled activities
         *
         * @apiError DATA_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_LIST_ENABLE, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                Map<String, Object> criterias = new HashMap<>();
                criterias.put("enable", true);
                JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, Activity.class);
                message.reply(resultJson.encode());
            }
        });
    }
}
