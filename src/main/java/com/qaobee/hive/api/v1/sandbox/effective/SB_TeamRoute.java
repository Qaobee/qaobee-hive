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

package com.qaobee.hive.api.v1.sandbox.effective;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.TeamService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

/**
 * The type Sb team route.
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/sandbox/effective/team")
public class SB_TeamRoute extends AbstractRoute {// NOSONAR
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
    private TeamService teamService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/add", HttpMethod.POST,
                authHandler,
                this::addTeam);

        addRoute(router, "/update", HttpMethod.PUT,
                authHandler,
                this::updateTeam);


        addRoute(router, "/list", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_SANDBOX_ID, PARAM_EFFECTIVE_ID, PARAM_ENABLE, PARAM_ADVERSARY),
                this::getTeamList);

        addRoute(router, "/get", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ID),
                this::getTeam);

        return router;
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
    private void getTeamList(RoutingContext context) {
        String link = "";
        if (context.request().params().contains(PARAM_LINK_TEAM_ID) && StringUtils.isNotBlank(context.request().getParam(PARAM_LINK_TEAM_ID).trim())) {
            link = context.request().getParam(PARAM_LINK_TEAM_ID).trim();
        }
        teamService.getTeamList(
                context.request().getParam(PARAM_SANDBOX_ID),
                context.request().getParam(PARAM_EFFECTIVE_ID),
                context.request().getParam(PARAM_ADVERSARY),
                context.request().getParam(PARAM_ENABLE),
                link,
                handleResponseArray(context)
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
    private void getTeam(RoutingContext context) {
        teamService.getTeam(context.request().getParam(PARAM_ID), handleResponse(context));
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
    private void updateTeam(RoutingContext context) {
        teamService.updateTeam(context.getBodyAsJson(), context.user().principal().getString("_id"), getLocale(context), handleResponse(context));
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
    private void addTeam(RoutingContext context) {
        teamService.addTeam(context.getBodyAsJson(), context.user().principal().getString("_id"), getLocale(context), handleResponse(context));
    }
}
