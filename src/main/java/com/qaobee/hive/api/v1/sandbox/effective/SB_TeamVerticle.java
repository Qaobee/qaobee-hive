/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
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
package com.qaobee.hive.api.v1.sandbox.effective;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.sandbox.effective.SB_Team;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * Handler to get a set of team
     */
    public static final String GET_LIST = Module.VERSION + ".sandbox.effective.team.list";
    /**
     * Handler to add a team.
     */
    public static final String ADD = Module.VERSION + ".sandbox.effective.team.add";
    /**
     * Handler to get a particular team from its ID.
     */
    public static final String GET = Module.VERSION + ".sandbox.effective.team.get";
    /**
     * Handler to update a team.
     */
    public static final String UPDATE = Module.VERSION + ".sandbox.effective.team.update";
    /**
     * team ID param
     */
    public static final String PARAM_ID = "_id";

    
	/* List of parameters */
    /**
     * SandboxId param
     */
    public static final String PARAM_SANDBOX_ID = "sandboxId";
    /**
     * EffectiveId param
     */
    public static final String PARAM_EFFECTIVE_ID = "effectiveId";
    /**
     * adversary param (true or false)
     */
    public static final String PARAM_ENABLE = "enable";
    /**
     * adversary param (true or false)
     */
    public static final String PARAM_ADVERSARY = "adversary";
    /**
     * team home link
     */
    public static final String PARAM_LINK_TEAM_ID = "linkTeamId";
    private static final Logger LOG = LoggerFactory.getLogger(SB_TeamVerticle.class);
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
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @apiDescription Add Team
         * @api {put} /api/1/sandbox/effective/team/add Add Team
         * @apiVersion 0.1.0
         * @apiName add
         * @apiGroup Team API
         * @apiSuccess {Object} Team com.qaobee.hive.business.model.sandbox.effective.Team
         * @apiError INTERNAL_ERROR Error during encode value
         * @apiError QAOBEE EXCEPTION Error during validate data of Team Object
         * @apiError HTTP_ERROR Bad Request
         */
        vertx.eventBus().registerHandler(ADD, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                LOG.debug(ADD + " - SB_Team");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    utils.isUserLogged(req);
                    final JsonObject json = new JsonObject(req.getBody());
                    final String id = mongo.save(json, SB_Team.class);
                    json.putString("_id", id);
                    /* return */
                    message.reply(json.encode());
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @apiDescription Update Team
         * @api {get} /api/1/sandbox/effective/team/get Update team
         * @apiVersion 0.1.0
         * @apiName updateTeam
         * @apiGroup Team API
         * @apiParam {Object} Team com.qaobee.hive.business.model.sandbox.effective.Team
         * @apiSuccess {Object} Team com.qaobee.hive.business.model.sandbox.effective.Team
         * @apiError HTTP_ERROR Bad request
         */
        vertx.eventBus().registerHandler(UPDATE, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                LOG.debug(UPDATE + " - SB_Team");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.PUT, req.getMethod());
                    utils.isUserLogged(req);
                    final JsonObject json = new JsonObject(req.getBody());
                    final String id = mongo.update(json, SB_Team.class);
                    json.putString("_id", id);
                    message.reply(json.encode());
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final EncodeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @api {get} /api/v1/sandbox/effective/team/get Read data of an SB_Team
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
         * @apiError DATA_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                LOG.debug(GET + " - SB_Team");
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

                    LOG.debug("SB_Team found : " + json.toString());

                    message.reply(json.encode());

                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @api {get} /api/1/sandbox/effective/team/list Read data of a set of SB_Team
         * @apiVersion 0.1.0
         * @apiName get
         * @apiGroup SB_Team API
         * @apiPermission all
         *
         * @apiDescription get a list of my teams to the collection SB_team
         *
         * @apiParam {String} sandBoxId Mandatory The sandBox Id
         * @apiParam {String} effectiveId Mandatory The effective Id
         *
         * @apiSuccess {Array}   teams           The set of SB_Team found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError DATA_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_LIST, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                LOG.debug(GET_LIST + " - SB_Team");
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    // Tests on method and parameters
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    Map<String, List<String>> params = req.getParams();
                    utils.testMandatoryParams(params, PARAM_SANDBOX_ID, PARAM_EFFECTIVE_ID, PARAM_ENABLE, PARAM_ADVERSARY);
                    utils.isUserLogged(req);
                    Map<String, Object> criterias = new HashMap<>();
                    criterias.put(PARAM_SANDBOX_ID, params.get(PARAM_SANDBOX_ID).get(0));
                    criterias.put(PARAM_EFFECTIVE_ID, params.get(PARAM_EFFECTIVE_ID).get(0));

                    if (!"all".equals(params.get(PARAM_ENABLE).get(0))) {
                        criterias.put(PARAM_ENABLE, "true".equals(params.get(PARAM_ENABLE).get(0)));
                    }
                    criterias.put(PARAM_ADVERSARY, "true".equals(params.get(PARAM_ADVERSARY).get(0)));

                    if (params.get(PARAM_LINK_TEAM_ID) != null && !"".equals(params.get(PARAM_LINK_TEAM_ID).get(0).trim())) {
                        criterias.put(PARAM_LINK_TEAM_ID, params.get(PARAM_LINK_TEAM_ID).get(0));
                    }

                    JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, SB_Team.class);
                    LOG.debug("RETURN : " + resultJson);

                    message.reply(resultJson.encode());
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
    }
}
