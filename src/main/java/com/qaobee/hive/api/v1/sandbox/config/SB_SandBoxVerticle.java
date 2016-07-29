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
import com.qaobee.hive.dao.SandBoxDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.DecodeException;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

/**
 * The type Sand box cfg verticle.
 */
@DeployableVerticle
public class SB_SandBoxVerticle extends AbstractGuiceVerticle {// NOSONAR
    /**
     * The constant GET.
     */
    public static final String GET_BY_ID = Module.VERSION + ".sandbox.config.sandbox.getById";
    /**
     * The constant GET_BY_OWNER.
     */
    public static final String GET_BY_OWNER = Module.VERSION + ".sandbox.config.sandbox.getByOwner";
    /**
     * The constant GET_LIST_BY_OWNER.
     */
    public static final String GET_LIST_BY_OWNER = Module.VERSION + ".sandbox.config.sandbox.getListByOwner";
    /**
     * The constant UPDATE.
     */
    public static final String UPDATE = Module.VERSION + ".sandbox.config.sandbox.update";

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

    /**
     * The constant PARAM_USER.
     */
    public static final String PARAM_USER_ID = "uid";


    private static final Logger LOG = LoggerFactory.getLogger(SB_SandBoxVerticle.class);

    @Inject
    private Utils utils;
    @Inject
    private SandBoxDAO sandBoxDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET_BY_OWNER, this::getByOwner)
                .registerHandler(GET_LIST_BY_OWNER, this::getListByOwner)
                .registerHandler(GET_BY_ID, this::getSandboxById)
                .registerHandler(UPDATE, this::update);
    }

    /**
     * @apiDescription Get an enriched SB_SandBox
     * @api {post} /api/1/share/sandbox/getById Get an enriched SB_SandBox
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName getSandboxById
     * @apiHeader {String} token
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = GET_BY_ID, method = Constants.GET, logged = true, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST)
    private void getSandboxById(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(sandBoxDAO.getSandboxById(req.getParams().get(PARAM_ID).get(0)).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        } catch (DecodeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, new QaobeeException(ExceptionCodes.JSON_EXCEPTION, e));
        }
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
    @Rule(address = GET_LIST_BY_OWNER, method = Constants.GET, logged = true)
    private void getListByOwner(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(sandBoxDAO.getListByOwner(req.getParams().get(PARAM_OWNER_ID), req.getUser().get_id()).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
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
    @Rule(address = GET_BY_OWNER, method = Constants.GET, logged = true, mandatoryParams = {PARAM_ACTIVITY_ID},
          scope = Rule.Param.REQUEST)
    private void getByOwner(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(sandBoxDAO.getByOwner(req.getParams().get(PARAM_ACTIVITY_ID).get(0), req.getUser().get_id()).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
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
    @Rule(address = UPDATE, method = Constants.POST, logged = true, mandatoryParams = {PARAM_ID},
          scope = Rule.Param.BODY)
    private void update(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(sandBoxDAO.updateSandbox(new JsonObject(req.getBody())).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
