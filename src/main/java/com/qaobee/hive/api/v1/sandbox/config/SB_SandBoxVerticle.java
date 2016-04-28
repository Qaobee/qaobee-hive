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
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * The type Sand box cfg verticle.
 */
@DeployableVerticle
public class SB_SandBoxVerticle extends AbstractGuiceVerticle {// NOSONAR
    /**
     * The constant GET.
     */
    public static final String GET = Module.VERSION + ".sandbox.config.sandbox.get";
    /**
     * The constant GET_BY_OWNER.
     */
    public static final String GET_BY_OWNER = Module.VERSION + ".sandbox.config.sandbox.getByOwner";
    /**
     * The constant GET_LIST_BY_OWNER.
     */
    public static final String GET_LIST_BY_OWNER = Module.VERSION + ".sandbox.config.sandbox.getListByOwner";
    /**
     * The constant ADD_TO_USER.
     */
    public static final String ADD = Module.VERSION + ".sandbox.config.sandbox.add";
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
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * The constant PARAM_SEASON_ID.
     */
    public static final String PARAM_SEASON_ID = "seasonId";
    /**
     * The constant PARAM_USER.
     */
    public static final String PARAM_USER_ID = "uid";
    /**
     * The constant PARAM_SB_CFG_ID.
     */
    public static final String PARAM_SB_CFG_ID = "sandboxCfgId";
    private static final Logger LOG = LoggerFactory.getLogger(SB_SandBoxVerticle.class);
    /* Injections */
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;

    /**
     * Start void.
     */
    @Override
    @VerticleHandler({
            @Rule(address = GET_BY_OWNER, method = Constantes.GET, logged = true, mandatoryParams = {PARAM_ACTIVITY_ID},
                    scope = Rule.Param.REQUEST),
            @Rule(address = GET_LIST_BY_OWNER, method = Constantes.GET, logged = true),
            @Rule(address = ADD, method = Constantes.PUT, logged = true, mandatoryParams = {PARAM_USER_ID, PARAM_ACTIVITY_ID},
                    scope = Rule.Param.BODY),
            @Rule(address = UPDATE, method = Constantes.POST, logged = true, mandatoryParams = {PARAM_ID, PARAM_SB_CFG_ID},
                    scope = Rule.Param.BODY),
    })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @api {post} /api/v1/sandbox/config/sandbox/getByOwner
         * @apiVersion 0.1.0
         * @apiName getByOwner
         * @apiGroup SandBox API
         * @apiPermission all
         *
         * @apiDescription Retrieve the user's sandbox
         *
         * @apiParam {String} activityId Mandatory The sandBox activity.
         *
         * @apiSuccess {sandBox}   sandBox    The sandBox updated.
         */
        vertx.eventBus().registerHandler(GET_BY_OWNER, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    Map<String, List<String>> params = req.getParams();
                    CriteriaBuilder cb = new CriteriaBuilder()
                            .add(PARAM_OWNER_ID, req.getUser().get_id())
                            .add(PARAM_ACTIVITY_ID, params.get(PARAM_ACTIVITY_ID).get(0));
                    JsonArray resultJson = mongo.findByCriterias(cb.get(), null, null, -1, -1, SB_SandBox.class);
                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for user id :" + req.getUser().get_id() + " ,and activityId : " + params.get(PARAM_ACTIVITY_ID));
                    }
                    JsonObject json = resultJson.get(0);
                    message.reply(json.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @api {post} /api/v1/sandbox/config/sandbox/getListByOwner
         * @apiVersion 0.1.0
         * @apiName getListByOwner
         * @apiGroup SandBox API
         * @apiPermission all
         *
         * @apiDescription Retrieve the user's sandbox
         *
         * @apiParam {String} activityId Mandatory The sandBox activity.
         *
         * @apiSuccess {sandBox}   sandBox    The sandBox updated.
         *
         */
        vertx.eventBus().registerHandler(GET_LIST_BY_OWNER, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    CriteriaBuilder cb = new CriteriaBuilder();
                    if (req.getParams().get("id") != null && !req.getParams().get("id").isEmpty() && StringUtils.isNoneBlank(req.getParams().get("id").get(0))) {
                        cb.add(PARAM_OWNER_ID, req.getParams().get("id").get(0));
                    } else {
                        cb.add(PARAM_OWNER_ID, req.getUser().get_id());
                    }
                    JsonArray resultJson = mongo.findByCriterias(cb.get(), null, null, -1, -1, SB_SandBox.class);
                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for user id :" + req.getUser().get_id());
                    }
                    message.reply(resultJson.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         *
         */
        vertx.eventBus().registerHandler(ADD, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    final JsonObject jsonReq = new JsonObject(req.getBody());
                    JsonObject sandbox = new JsonObject()
                            .putString("activityId", jsonReq.getString(PARAM_ACTIVITY_ID))
                            .putString("owner", jsonReq.getString(PARAM_USER_ID));
                    sandbox.putString("_id", mongo.save(sandbox, SB_SandBox.class));
                    message.reply(sandbox.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         *
         */
        vertx.eventBus().registerHandler(UPDATE, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    JsonObject body = new JsonObject(req.getBody());
                    final JsonObject sandbox = mongo.getById(body.getString(PARAM_ID), SB_SandBox.class);
                    if (sandbox == null) {
                        throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBox found for id :" + body.getString(PARAM_ID));
                    }
                    sandbox.putString("sandboxCfgId", body.getString(PARAM_SB_CFG_ID));
                    mongo.save(sandbox, SB_SandBox.class);
                    message.reply(sandbox.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });
    }
}
