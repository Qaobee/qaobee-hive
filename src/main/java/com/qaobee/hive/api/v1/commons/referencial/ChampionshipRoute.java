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
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

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
        router.post("/list").handler(authHandler);
        router.post("/list").handler(c -> mandatoryHandler.testBodyParams(c, PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_STRUCTURE));
        router.post("/list").handler(this::getListChampionships);

        router.get("/get").handler(authHandler);
        router.get("/get").handler(c -> mandatoryHandler.testRequesParams(c, PARAM_ID));
        router.get("/get").handler(this::getChampionship);

        router.post("/add").handler(authHandler);
        router.post("/add").handler(this::addChampionship);

        router.post("/update").handler(authHandler);
        router.post("/update").handler(this::updateChampionship);
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
        context.user().isAuthorised("admin_qaobee", res -> {
            if (res.succeeded() && res.result()) {
                try {
                    utils.testMandatoryParams(context, "_id", PARAM_LABEL, PARAM_LEVEL_GAME, PARAM_SUB_LEVEL_GAME,
                            PARAM_POOL, PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_SEASON_CODE, PARAM_LIST_PARTICIPANTS);

                    championshipService.updateChampionship(context.getBodyAsJson(), ar -> {
                        if (ar.succeeded()) {
                            handleResponse(context, new JsonObject().put("_id", ar.result()));
                        } else {
                            utils.handleError(context, (QaobeeException) ar.cause());
                        }
                    });
                } catch (QaobeeException e) {
                    LOG.warn(e.getMessage(), e);
                    utils.handleError(context, e);
                }
            } else {
                utils.handleError(context, new QaobeeException(ExceptionCodes.NOT_ADMIN, Messages.getString("not.admin", getLocale(context))));
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
        context.user().isAuthorised("admin_qaobee", ar -> {
            if (ar.succeeded() && ar.result()) {
                try {
                    utils.testMandatoryParams(context, PARAM_LABEL, PARAM_LEVEL_GAME, PARAM_SUB_LEVEL_GAME, PARAM_POOL,
                            PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_SEASON_CODE, PARAM_LIST_PARTICIPANTS);
                    championshipService.addChampionship(context.getBodyAsJson(), handleResponse(context));
                } catch (QaobeeException e) {
                    LOG.warn(e.getMessage(), e);
                    utils.handleError(context, e);
                }
            } else {
                utils.handleError(context, new QaobeeException(ExceptionCodes.NOT_ADMIN, Messages.getString("not.admin", getLocale(context))));
            }
        });
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
