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
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * The Constant GET_SANDOX_SHARING.
     */
    public static final String GET = Module.VERSION + ".commons.settings.activity.get";
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
    private static final Logger LOG = LoggerFactory.getLogger(ActivityVerticle.class);
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET, this::getActivityHandler)
                .registerHandler(GET_LIST, this::getListHandler)
                .registerHandler(GET_LIST_ENABLE, this::getEnableActivitiesHandler);
    }

    /**
     * @api {get} /api/v1/commons/settings/activity/getListEnable List of enabled activities
     * @apiVersion 0.1.0
     * @apiName getListEnable
     * @apiGroup Activity API
     * @apiPermission all
     * @apiDescription List of enabled activities
     * @apiSuccess {List}   activities  List of enabled activities
     * @apiError DATA_ERROR Error on DB request
     * @apiError INVALID_PARAMETER Parameters not found
     */
    @Rule(address = GET_LIST_ENABLE, method = Constants.GET)
    private void getEnableActivitiesHandler(Message message) { 
        Map<String, Object> criterias = new HashMap<>();
        criterias.put("enable", true);
        JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, Activity.class);
        message.reply(resultJson.encode());
    }

    /**
     * @api {get} /api/v1/commons/settings/activity/getList List all activities
     * @apiVersion 0.1.0
     * @apiName getList
     * @apiGroup Activity API
     * @apiPermission all
     * @apiDescription get all activity
     * @apiSuccess {List}   activities            List all activity
     */
    @Rule(address = GET_LIST, method = Constants.GET)
    private void getListHandler(Message message) { 
        JsonArray resultJson = mongo.findByCriterias(null, null, null, -1, -1, Activity.class);
        message.reply(resultJson.encode());
    }

    /**
     * @api {get} /api/v1/commons/settings/activity/get Read data of an Activity
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup Activity API
     * @apiPermission all
     * @apiDescription get a activity to the collection activity in settings module
     * @apiParam {String} id The Activity-ID.
     * @apiSuccess {Activity} activity The Activity found.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET, method = Constants.GET, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST)
    private void getActivityHandler(Message<String> message) { 
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            final JsonObject json = mongo.getById(req.getParams().get(PARAM_ID).get(0), Activity.class);
            message.reply(json.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
