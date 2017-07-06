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
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Sand box cfg verticle.
 */
@DeployableVerticle
public class SB_SandBoxVerticle extends AbstractGuiceVerticle {// NOSONAR
    /**
     * The constant GET.
     */
    public static final String GET_BY_ID = Module.VERSION + ".sandbox.config.sandbox";
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

    @Inject
    private SandBoxDAO sandBoxDAO;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this)
                .add(GET_BY_OWNER, this::getByOwner)
                .add(GET_LIST_BY_OWNER, this::getListByOwner)
                .add(GET_BY_ID, this::getSandboxById)
                .add(UPDATE, this::update)
                .register(startFuture);
    }

    /**
     * @apiDescription Get an enriched SB_SandBox
     * @api {post} /api/v1/sandbox/config/sandbox Get an enriched SB_SandBox
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName getSandboxById
     * @apiHeader {String} token
     * @apiGroup SandBox API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = GET_BY_ID, method = Constants.GET, logged = true, mandatoryParams = PARAM_ID, scope = Rule.Param.REQUEST)
    private void getSandboxById(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, sandBoxDAO.getSandboxById(req.getParams().get(PARAM_ID).get(0)));
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
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonArray(message, sandBoxDAO.getListByOwner(req.getParams().get(PARAM_OWNER_ID), req.getUser().get_id()));
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
    @Rule(address = GET_BY_OWNER, method = Constants.GET, logged = true, mandatoryParams = PARAM_ACTIVITY_ID,
            scope = Rule.Param.REQUEST)
    private void getByOwner(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, sandBoxDAO.getByOwner(req.getParams().get(PARAM_ACTIVITY_ID).get(0), req.getUser().get_id()));
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
    @Rule(address = UPDATE, method = Constants.POST, logged = true, mandatoryParams = PARAM_ID,
            scope = Rule.Param.BODY)
    private void update(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, sandBoxDAO.updateSandbox(new JsonObject(req.getBody())));
    }
}
