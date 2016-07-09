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
import com.qaobee.hive.dao.ActivityDAO;
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
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

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
    @Inject
    private ActivityDAO activityDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET, this::get)
                .registerHandler(GET_LIST, this::getList)
                .registerHandler(GET_LIST_ENABLE, this::getEnabled);
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
    @Rule(address = GET_LIST_ENABLE, method = Constants.GET)
    private void getEnabled(Message message) {
        message.reply(activityDAO.getEnabled().encode());
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
    @Rule(address = GET_LIST, method = Constants.GET)
    private void getList(Message message) {
        message.reply(activityDAO.getList().encode());
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
    @Rule(address = GET, method = Constants.GET, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST)
    private void get(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(activityDAO.get(req.getParams().get(PARAM_ID).get(0)).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
