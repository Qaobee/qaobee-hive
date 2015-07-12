/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.api.v1.sandbox.competition;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.sandbox.competition.SB_Team;
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
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Team verticle.
 */
@DeployableVerticle(isWorker = true)
public class SB_TeamVerticle extends AbstractGuiceVerticle {
    /**
     * Handler to get a set of teams
     */
    public static final String GET_LIST = Module.VERSION + ".sandbox.competition.team.list";
    /**
     * Handler to add a team.
     */
    public static final String ADD = Module.VERSION + ".sandbox.competition.team.add";
    /**
     * Handler to get a particular team from its ID.
     */
    public static final String GET = Module.VERSION + ".sandbox.competition.team.get";
    /**
     * Handler to update a team.
     */
    public static final String UPDATE = Module.VERSION + ".sandbox.competition.team.update";

    
	/* List of parameters */
    /**
     * team ID param
     */
    public static final String PARAM_ID = "_id";
    /**
     * Sandbox Config Id param
     */
    public static final String PARAM_SANDBOXCFG_ID = "sandBoxCfgId";
    
    
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
         * @apiDescription Add Team
         * @api {put} /api/1/sandbox/competition/team/add Add Team
         * @apiVersion 0.1.0
         * @apiName add
         * @apiGroup Team API
         * @apiSuccess {Object} Team com.qaobee.hive.business.model.sandbox.competition.Team
         * @apiError INTERNAL_ERROR Error during encode value
         * @apiError QAOBEE EXCEPTION Error during validate data of Team Object
         * @apiError HTTP_ERROR Bad Request
         */
        vertx.eventBus().registerHandler(ADD, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
            	container.logger().info(ADD+" - SB_Team");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.PUT, req.getMethod());
                    utils.isUserLogged(req);
                    final JsonObject json = new JsonObject(req.getBody());
                    final String id = mongo.update(json, SB_Team.class);
                    json.putString("_id", id);
					/* return */
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
        });
        
        /**
         * @apiDescription Update Team
         * @api {get} /api/1/sandbox/competition/team/get Update team
         * @apiVersion 0.1.0
         * @apiName updateTeam
         * @apiGroup Team API
         * @apiParam {Object} Team com.qaobee.hive.business.model.sandbox.competition.Team
         * @apiSuccess {Object} Team com.qaobee.hive.business.model.sandbox.competition.Team
         * @apiError HTTP_ERROR Bad request
         */
        vertx.eventBus().registerHandler(UPDATE, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
            	container.logger().info(UPDATE+" - SB_Team");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.PUT, req.getMethod());
                    utils.isUserLogged(req);
                    final JsonObject json = new JsonObject(req.getBody());
                    final String id = mongo.update(json, SB_Team.class);
                    json.putString("_id", id);
                    message.reply(json.encode());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final EncodeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
        
        
        /**
         * @api {get} /api/v1/sandbox/competition/team/get Read data of an SB_Team
         * @apiVersion 0.1.0
         * @apiName get
         * @apiGroup SB_Team API
         * @apiPermission all
         *
         * @apiDescription get a team to the collection SB_team
         *
         * @apiParam {String} id Mandatory The SB_team Id
         *
         * @apiSuccess {SB_team}   team            The SB_Team found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
            	container.logger().info(GET+" - SB_Team");
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

                    final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), SB_Team.class);

                    container.logger().info("SB_Team found : " + json.toString());

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
        });

        /**
         * @api {get} /api/1/sandbox/competition/team/list Read data of a set of SB_Team
         * @apiVersion 0.1.0
         * @apiName get
         * @apiGroup SB_Team API
         * @apiPermission all
         *
         * @apiDescription get a team to the collection SB_team
         *
         * @apiParam {String} sandBoxCfgId Mandatory The sandBox Config Id
         *
         * @apiSuccess {Array}   teams           The set of SB_Team found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_LIST, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
            	container.logger().info(GET_LIST+" - SB_Team");
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    // Tests on method and parameters
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    Map<String, List<String>> params = req.getParams();
                    utils.testMandatoryParams(params, PARAM_SANDBOXCFG_ID);
                    utils.isUserLogged(req);
                    Map<String, Object> criterias = new HashMap<String, Object>();
                    criterias.put(PARAM_SANDBOXCFG_ID, params.get(PARAM_SANDBOXCFG_ID).get(0));
                    
                    JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, SB_Team.class);

                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No SB_Team found for sandBoxCfgId (" + PARAM_SANDBOXCFG_ID + ")");
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
        });
    }
}
