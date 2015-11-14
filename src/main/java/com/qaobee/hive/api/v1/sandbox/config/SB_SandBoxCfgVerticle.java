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
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
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
 * The type Sand box cfg verticle.
 */
@DeployableVerticle(isWorker = true)
public class SB_SandBoxCfgVerticle extends AbstractGuiceVerticle {
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
     * The constant PARAM_SEASON_CODE.
     */
    public static final String PARAM_SEASON_ID = "season._id";
    /**
     * The constant PARAM_OWNER.
     */
    public static final String PARAM_SANDBOX_ID = "sandbox._id";

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
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");

        /**
         * @apiDescription Get SandBoxCfg by its id
         * @api {get} /api/1/sandbox/config/sandboxCfg/get Get SandBoxCfg by id
         * @apiName getSandBoxCfg
         * @apiGroup SandBoxCfg API
         * @apiParam {String} _id SandBoxCfg id
         * @apiError HTTP_ERROR wrong request method
         * @apiError NOT_LOGGED invalid token
         * @apiError INVALID_PARAMETER wrong parameters
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    utils.isUserLogged(req);
                    Map<String, List<String>> params = req.getParams();
                    utils.testMandatoryParams(params, PARAM_ID);
                    final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), SB_SandBoxCfg.class);
                    container.logger().debug("SandBoxCfg found : " + json.toString());
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
                } catch (Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Get list SandBoxCfg by its owner
         * @api {get} /api/1/sandbox/config/sandboxCfg/getList Get list SandBoxCfg by its owner
         * @apiName getListSandBoxCfg
         * @apiGroup SandBoxCfg API
         * @apiParam {String} seaonId SandBoxCfg season
         * @apiParam {String} sandBoxId sandbox Id
         * @apiError HTTP_ERROR wrong request method
         * @apiError NOT_LOGGED invalid token
         * @apiError INVALID_PARAMETER wrong parameters
         */
        vertx.eventBus().registerHandler(GETLIST, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    utils.isUserLogged(req);
                    Map<String, List<String>> params = req.getParams();
                    //TODO : ID season ? pas SeasonCode ?
//                    utils.testMandatoryParams(params, PARAM_SANDBOX_ID, PARAM_SEASON_ID);
                    utils.testMandatoryParams(params, PARAM_SANDBOX_ID);

                    Map<String, Object> criterias = new HashMap<String, Object>();
                    criterias.put("sandbox._id", params.get(PARAM_SANDBOX_ID).get(0));

                    // label
//                    if (StringUtils.isNotBlank(params.get(PARAM_SEASON_ID).get(0))) {
//                        criterias.put("season._id", params.get(PARAM_SEASON_ID).get(0));
//                    }

                    JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, SB_SandBoxCfg.class);

                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No SandBoxCfg defined for sandBox if (" + params.get(PARAM_SANDBOX_ID).get(0) + ")");
                    }
                    container.logger().debug("SandBoxCfg found : " + resultJson.toString());

                    message.reply(resultJson.encode());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });
    }
}
