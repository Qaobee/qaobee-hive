/*
 * __________________
 *   Qaobee
 * __________________
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
import com.qaobee.hive.business.model.sandbox.config.SB_SandBoxCfg;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
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
 * The type Sand box cfg verticle.
 */
@DeployableVerticle
public class SB_SandBoxCfgVerticle extends AbstractGuiceVerticle { // NOSONAR
    /**
     * The constant GET.
     */
    public static final String GET = Module.VERSION + ".sandbox.config.sandboxCfg.get";
    /**
     * The constant GETLIST.
     */
    public static final String GETLIST = Module.VERSION + ".sandbox.config.sandboxCfg.getList";
    /**
     * The constant PARAM_ID.
     */
    public static final String PARAM_ID = "_id";
    /**
     * The constant PARAM_SEASON_ID.
     */
    public static final String PARAM_SEASON_ID = "season._id";
    /**
     * The constant PARAM_SANDBOX_ID.
     */
    public static final String PARAM_SANDBOX_ID = "sandbox._id";
    private static final Logger LOG = LoggerFactory.getLogger(SB_SandBoxCfgVerticle.class);
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET, this::getSandboxCfgHandler)
                .registerHandler(GETLIST, this::getSandoxCfgListHandler);
    }

    /**
     * @apiDescription Get list SandBoxCfg by its owner
     * @api {get} /api/1/sandbox/config/sandboxCfg/getList Get list SandBoxCfg by its owner
     * @apiName getListSandBoxCfg
     * @apiGroup SandBoxCfg API
     * @apiParam {String} seaonId SandBoxCfg season
     * @apiParam {String} sandBoxId sandbox Id
     */
    @Rule(address = GETLIST, method = Constantes.GET, logged = true, mandatoryParams = {PARAM_SANDBOX_ID},
            scope = Rule.Param.REQUEST)
    private void getSandoxCfgListHandler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            Map<String, Object> criterias = new HashMap<>();
            criterias.put("sandbox._id", req.getParams().get(PARAM_SANDBOX_ID).get(0));
            JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, SB_SandBoxCfg.class);
            if (resultJson == null || resultJson.size() == 0) {
                throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No SandBoxCfg defined for sandBox if (" + req.getParams().get(PARAM_SANDBOX_ID).get(0) + ")");
            }
            message.reply(resultJson.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Get SandBoxCfg by its id
     * @api {get} /api/1/sandbox/config/sandboxCfg/get Get SandBoxCfg by id
     * @apiName getSandBoxCfg
     * @apiGroup SandBoxCfg API
     * @apiParam {String} _id SandBoxCfg id
     */
    @Rule(address = GET, method = Constantes.GET, logged = true, mandatoryParams = {PARAM_ID},
            scope = Rule.Param.REQUEST)
    private void getSandboxCfgHandler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            final JsonObject json = mongo.getById(req.getParams().get(PARAM_ID).get(0), SB_SandBoxCfg.class);
            message.reply(json.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
