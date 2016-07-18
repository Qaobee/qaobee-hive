/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

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
import com.qaobee.hive.dao.TeamDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

/**
 * The type Team verticle.
 */
@DeployableVerticle
public class SB_TeamVerticle extends AbstractGuiceVerticle {// NOSONAR
    private static final Logger LOG = LoggerFactory.getLogger(SB_TeamVerticle.class);
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
    @Inject
    private Utils utils;
    @Inject
    private TeamDAO teamDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(ADD, this::addTeam)
                .registerHandler(UPDATE, this::updateTeam)
                .registerHandler(GET, this::getTeam)
                .registerHandler(GET_LIST, this::getTeamList);
    }

    /**
     * @api {get} /api/1/sandbox/effective/team/list Read data of a set of SB_Team
     * @apiVersion 0.1.0
     * @apiName getTeamList
     * @apiGroup SB_Team API
     * @apiHeader {String} token
     * @apiDescription get a list of my teams to the collection SB_team
     * @apiParam {String} sandBoxId Mandatory The sandBox Id
     * @apiParam {String} effectiveId Mandatory The effective Id
     * @apiSuccess {Array}   teams           The set of SB_Team found.
     */
    @Rule(address = GET_LIST, method = Constants.GET, logged = true,
            mandatoryParams = {PARAM_SANDBOX_ID, PARAM_EFFECTIVE_ID, PARAM_ENABLE, PARAM_ADVERSARY}, scope = Rule.Param.REQUEST)
    private void getTeamList(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        String link = null;
        if (req.getParams().get(PARAM_LINK_TEAM_ID) != null && !"".equals(req.getParams().get(PARAM_LINK_TEAM_ID).get(0).trim())) {
            link = req.getParams().get(PARAM_LINK_TEAM_ID).get(0);
        }
        message.reply(teamDAO.getTeamList(
                req.getParams().get(PARAM_SANDBOX_ID).get(0),
                req.getParams().get(PARAM_EFFECTIVE_ID).get(0),
                req.getParams().get(PARAM_ADVERSARY).get(0),
                req.getParams().get(PARAM_ENABLE).get(0),
                link).encode()
        );
    }

    /**
     * @api {get} /api/v1/sandbox/effective/team/get Read data of an SB_Team
     * @apiVersion 0.1.0
     * @apiName getTeam
     * @apiGroup SB_Team API
     * @apiHeader {String} token
     * @apiDescription get a team to the collection SB_team
     * @apiParam {String} id Mandatory The SB_team Id
     * @apiSuccess {Object}   team            The SB_Team found.
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST)
    private void getTeam(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(teamDAO.getTeam(req.getParams().get(PARAM_ID).get(0)).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Update Team
     * @api {put} /api/1/sandbox/effective/team/get Update team
     * @apiVersion 0.1.0
     * @apiName updateTeam
     * @apiHeader {String} token
     * @apiGroup Team API
     * @apiParam {Object} Team com.qaobee.hive.business.model.sandbox.effective.Team
     * @apiSuccess {Object} Team com.qaobee.hive.business.model.sandbox.effective.Team
     */
    @Rule(address = UPDATE, method = Constants.PUT, logged = true)
    private void updateTeam(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        message.reply(teamDAO.updateTeam(new JsonObject(req.getBody()), req.getUser().get_id(), req.getLocale()).encode());
    }

    /**
     * @apiDescription Add Team
     * @api {post} /api/1/sandbox/effective/team/add Add Team
     * @apiVersion 0.1.0
     * @apiName addTeam
     * @apiHeader {String} token
     * @apiGroup Team API
     * @apiSuccess {Object} Team com.qaobee.hive.business.model.sandbox.effective.Team
     */
    @Rule(address = ADD, method = Constants.POST, logged = true)
    private void addTeam(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(teamDAO.addTeam(new JsonObject(req.getBody()), req.getUser().get_id(), req.getLocale()).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
