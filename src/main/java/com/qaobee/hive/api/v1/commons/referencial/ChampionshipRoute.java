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
package com.qaobee.hive.api.v1.commons.referencial;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.ChampionshipService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static com.qaobee.hive.technical.constantes.Constants.ADMIN_HABILIT;

/**
 * The type Championship route.
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/commons/referencial/championship")
public class ChampionshipRoute extends AbstractRoute {
    private static final Logger LOG = LoggerFactory.getLogger(ChampionshipRoute.class);
    /**
     * Championship ID
     */
    public static final String PARAM_ID = "id";
    /**
     * Activity ID
     */
    public static final String PARAM_ACTIVITY = "activityId";
    /**
     * Season
     */
    public static final String PARAM_SEASON_CODE = "seasonCode";
    /**
     * Category Age
     */
    public static final String PARAM_CATEGORY_AGE = "categoryAge";
    /**
     * Structure
     */
    public static final String PARAM_STRUCTURE = "structure";
    /**
     * Participant
     */
    public static final String PARAM_PARTICIPANT = "participant";
    /**
     * Label
     */
    public static final String PARAM_LABEL = "label";
    /**
     * Level Game
     */
    public static final String PARAM_LEVEL_GAME = "levelGame";
    /**
     * Sub Level Game
     */
    public static final String PARAM_SUB_LEVEL_GAME = "subLevelGame";
    /**
     * Pool
     */
    public static final String PARAM_POOL = "pool";
    /**
     * Instance
     */
    public static final String PARAM_INSTANCE = "instance";
    /**
     * Participants
     */
    public static final String PARAM_LIST_PARTICIPANTS = "participants";
    @Inject
    private ChampionshipService championshipService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/list", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_STRUCTURE),
                this::getListChampionships);

        addRoute(router, "/get", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ID),
                this::getChampionship);

        addRoute(router, "/add", HttpMethod.POST,
                authHandler,
                c -> roleHandler.hasRole(c, ADMIN_HABILIT),
                c -> mandatoryHandler.testBodyParams(c, PARAM_LABEL, PARAM_LEVEL_GAME, PARAM_SUB_LEVEL_GAME, PARAM_POOL, PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_SEASON_CODE, PARAM_LIST_PARTICIPANTS),
                this::addChampionship);

        addRoute(router, "/update", HttpMethod.POST,
                authHandler,
                c -> roleHandler.hasRole(c, ADMIN_HABILIT),
                c -> mandatoryHandler.testBodyParams(c, "_id", PARAM_LABEL, PARAM_LEVEL_GAME, PARAM_SUB_LEVEL_GAME, PARAM_POOL, PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_SEASON_CODE, PARAM_LIST_PARTICIPANTS),
                this::updateChampionship);

        return router;
    }

    /**
     * @apiDescription Update a championship.
     * @api {post} /api/1/commons/referencial/championship/update Update a championship
     * @apiName updateChampionship
     * @apiGroup Championship API
     * @apiPermission TBD
     * @apiParam {String} id : identifier of the championship
     * @apiParam {String} label : championship label
     * @apiParam {LevelGame} levelGame : level game
     * @apiParam {String} subLevelGame : sub-level game label
     * @apiParam {String} pool : pool label
     * @apiParam {String} instance (optional) : instance
     * @apiParam {String} activityId : ID activity
     * @apiParam {CategoryAge} categoryAge : age category
     * @apiParam {String} seasonCode : season
     * @apiHeader {String} token
     * @apiParam {Array(Participant)} participants : list of participants (at least one)
     * @apiParam {Array(Journey)} journeys (optional) : list of journeys
     * @apiSuccess {Object} championship com.qaobee.hive.business.model.commons.referencial.Championship
     */
    private void updateChampionship(RoutingContext context) {
        championshipService.updateChampionship(context.getBodyAsJson(), ar -> {
            if (ar.succeeded()) {
                handleResponse(context, context.getBodyAsJson());
            } else {
                utils.handleError(context, (QaobeeException) ar.cause());
            }
        });
    }


    /**
     * @apiDescription Add a championship.
     * @api {post} /api/1/commons/referencial/championship/add Add a championship
     * @apiName addChampionship
     * @apiGroup Championship API
     * @apiPermission TBD
     * @apiParam {String} label : championship label
     * @apiParam {LevelGame} levelGame : level game
     * @apiParam {String} subLevelGame : sub-level game label
     * @apiParam {String} pool : pool label
     * @apiParam {String} instance (optional) : instance
     * @apiParam {String} activityId : ID activity
     * @apiParam {CategoryAge} categoryAge : age category
     * @apiParam {String} seasonCode : season
     * @apiHeader {String} token
     * @apiParam {Array(Participant)} participants : list of participants (at least one)
     * @apiParam {Array(Journey)} journeys (optional) : list of journeys
     * @apiSuccess {Object} championship com.qaobee.hive.business.model.commons.referencial.Championship
     */
    private void addChampionship(RoutingContext context) {
        championshipService.addChampionship(context.getBodyAsJson(), handleResponse(context));
    }

    /**
     * @apiDescription Retrieve a championship by its id.
     * @api {post} /api/1/commons/referencial/championship/get Get a championship
     * @apiName getChampionship
     * @apiGroup Championship API
     * @apiPermission TBD
     * @apiParam {String} id
     * @apiSuccess {Object} championship com.qaobee.hive.business.model.commons.referencial.Championship
     */
    private void getChampionship(RoutingContext context) {
        championshipService.getChampionship(context.request().getParam(PARAM_ID), handleResponse(context));
    }

    /**
     * @apiDescription retrieve all championships.
     * @api {post} /api/1/commons/referencial/championship/list Get all championships
     * @apiName getListChampionships
     * @apiGroup Championship API
     * @apiPermission TBD
     * @apiParam {String} activityId : activity code
     * @apiParam {String} categoryAge : category age code
     * @apiParam {String} structureId : structure ID
     * @apiParam {Participant} participant : participant (optionnal)
     * @apiSuccess {Array} list of championships
     */
    private void getListChampionships(RoutingContext context) {
        championshipService.getListChampionships(context.getBodyAsJson(), handleResponseArray(context));
    }
}
