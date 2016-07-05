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
package com.qaobee.hive.api.v1.commons.referencial;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.dao.ChampionshipDAO;
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
 * The type Championship verticle.
 *
 * @author jro
 */
@DeployableVerticle
public class ChampionshipVerticle extends AbstractGuiceVerticle {

    /**
     * Handler to get a set of events
     */
    public static final String GET_LIST = Module.VERSION + ".commons.referencial.championship.list";
    /**
     * Handler to add a event.
     */
    public static final String ADD = Module.VERSION + ".commons.referencial.championship.add";
    /**
     * Handler to get a particular championship from its ID.
     */
    public static final String GET = Module.VERSION + ".commons.referencial.championship.get";
    /**
     * Handler to update a event.
     */
    public static final String UPDATE = Module.VERSION + ".commons.referencial.championship.update";
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
    private static final Logger LOG = LoggerFactory.getLogger(ChampionshipVerticle.class);
    @Inject
    private Utils utils;
    @Inject
    private ChampionshipDAO championshipDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET_LIST, this::getListChampionships)
                .registerHandler(GET, this::getChampionship)
                .registerHandler(ADD, this::addChampionship)
                .registerHandler(UPDATE, this::updateChampionship);
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
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = UPDATE, method = Constants.POST, logged = true, admin = true,
            mandatoryParams = {"_id", PARAM_LABEL, PARAM_LEVEL_GAME, PARAM_SUB_LEVEL_GAME, PARAM_POOL, PARAM_ACTIVITY, PARAM_CATEGORY_AGE,
                    PARAM_SEASON_CODE, PARAM_LIST_PARTICIPANTS}, scope = Rule.Param.BODY)
    private void updateChampionship(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(championshipDAO.updateChampionship(new JsonObject(req.getBody())).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
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
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = ADD, method = Constants.POST, logged = true, admin = true,
            mandatoryParams = {PARAM_LABEL, PARAM_LEVEL_GAME, PARAM_SUB_LEVEL_GAME, PARAM_POOL, PARAM_ACTIVITY, PARAM_CATEGORY_AGE,
                    PARAM_SEASON_CODE, PARAM_LIST_PARTICIPANTS}, scope = Rule.Param.BODY)
    private void addChampionship(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(championshipDAO.addChampionship(new JsonObject(req.getBody())).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Retrieve a championship by its id.
     * @api {post} /api/1/commons/referencial/championship/get Get a championship
     * @apiName getChampionship
     * @apiGroup Championship API
     * @apiPermission TBD
     * @apiParam {String} id
     * @apiSuccess {Object} championship com.qaobee.hive.business.model.commons.referencial.Championship
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST)
    private void getChampionship(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(championshipDAO.getChampionship(req.getParams().get(PARAM_ID).get(0)).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
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
    @Rule(address = GET_LIST, method = Constants.POST, logged = true, mandatoryParams = {PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_STRUCTURE}, scope = Rule.Param.BODY)
    private void getListChampionships(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(championshipDAO.getListChampionships(new JsonObject(req.getBody())).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

}
