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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.referencial.ChampionShip;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The type Championship verticle.
 *
 * @author jro
 */
@DeployableVerticle()
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

    /* List of parameters */
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
    /**
     * Journeys
     */
    public static final String PARAM_JOURNEYS = "journeys";
    private static final Logger LOG = LoggerFactory.getLogger(ChampionshipVerticle.class);

    /* Injections */
    /**
     * The Utils.
     */
    @Inject
    protected Utils utils;
    /**
     * The Mongo DB.
     */
    @Inject
    private MongoDB mongo;

    /**
     * Start void.
     */
    @Override
    @VerticleHandler({
            @Rule(address = GET_LIST, method = Constantes.POST, logged = true, mandatoryParams = {PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_STRUCTURE}, scope = Rule.Param.BODY),
            @Rule(address = ADD, method = Constantes.POST, logged = true, admin = true, mandatoryParams = {PARAM_LABEL, PARAM_LEVEL_GAME, PARAM_SUB_LEVEL_GAME, PARAM_POOL, PARAM_ACTIVITY, PARAM_CATEGORY_AGE,
                    PARAM_SEASON_CODE, PARAM_LIST_PARTICIPANTS}, scope = Rule.Param.BODY),
            @Rule(address = GET, method = Constantes.GET, logged = true, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST),
            @Rule(address = UPDATE, method = Constantes.POST, logged = true, admin = true, mandatoryParams = {"_id", PARAM_LABEL, PARAM_LEVEL_GAME, PARAM_SUB_LEVEL_GAME, PARAM_POOL, PARAM_ACTIVITY, PARAM_CATEGORY_AGE,
                    PARAM_SEASON_CODE, PARAM_LIST_PARTICIPANTS}, scope = Rule.Param.BODY),
    })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        /**
         * @apiDescription retrieve all championships.
         * @api {post} /api/1/commons/referencial/championship/list Get all championships
         * @apiName getListChampionshipsHandler
         * @apiGroup Championship API
         * @apiPermission TBD
         *
         * @apiParam {String} activityId : activity code
         * @apiParam {String} categoryAge : category age code
         * @apiParam {String} structureId : structure ID
         * @apiParam {Participant} participant : participant (optionnal)
         *
         * @apiSuccess {Array} list of championships
         */
        final Handler<Message<String>> getListChampionshipsHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                JsonObject params = new JsonObject(req.getBody());
                Map<String, Object> mapParams = params.toMap();
                // Aggregat section
                DBObject match;
                BasicDBObject dbObjectParent;
                BasicDBObject dbObjectChild;
                //$MACTH section
                dbObjectParent = new BasicDBObject();
                // Activity ID
                dbObjectParent.put("activityId", mapParams.get(PARAM_ACTIVITY));
                // Category Age Code
                dbObjectParent.put("categoryAge.code", mapParams.get(PARAM_CATEGORY_AGE));
                // Structure ID
                dbObjectParent.put("participants.structureId", mapParams.get(PARAM_STRUCTURE));
                // Participant
                if (mapParams.containsKey(PARAM_PARTICIPANT)) {
                    @SuppressWarnings("unchecked") Map<String, Object> mapParticipant = (Map<String, Object>) mapParams.get(PARAM_PARTICIPANT);
                    dbObjectChild = new BasicDBObject();
                    if (mapParticipant.containsKey("id")) {
                        dbObjectChild.put("participants.id", mapParticipant.get("id"));
                    }
                    if (mapParticipant.containsKey("structureId")) {
                        dbObjectChild.put("participants.structureId", mapParticipant.get("structureId"));
                    }
                    if (mapParticipant.containsKey("name")) {
                        dbObjectChild.put("participants.name", mapParticipant.get("name"));
                    }
                    if (mapParticipant.containsKey("type")) {
                        dbObjectChild.put("participants.type", mapParticipant.get("type"));
                    }
                    dbObjectParent.put("$and", Collections.singletonList(dbObjectChild));
                }
                match = new BasicDBObject("$match", dbObjectParent);
                // Pipeline
                List<DBObject> pipelineAggregation = Collections.singletonList(match);
                final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, ChampionShip.class);
                message.reply(resultJSon.encode());
            }
        };

        /**
         * @apiDescription Retrieve a championship by its id.
         * @api {post} /api/1/commons/referencial/championship/get Get a championship
         * @apiName getChampionshipHandler
         * @apiGroup Championship API
         * @apiPermission TBD
         *
         * @apiParam {String} id
         *
         * @apiSuccess {Object} championship com.qaobee.hive.business.model.commons.referencial.Championship
         * @apiError DATA_ERROR Error on DB request
         */
        final Handler<Message<String>> getChampionshipHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    message.reply(mongo.getById(req.getParams().get(PARAM_ID).get(0), ChampionShip.class).encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        };

        /**
         * @apiDescription Add a championship.
         * @api {post} /api/1/commons/referencial/championship/add Add a championship
         * @apiName addChampionshipHandler
         * @apiGroup Championship API
         * @apiPermission TBD
         *
         * @apiParam {String} label : championship label
         * @apiParam {LevelGame} levelGame : level game
         * @apiParam {String} subLevelGame : sub-level game label
         * @apiParam {String} pool : pool label
         * @apiParam {String} instance (optional) : instance
         * @apiParam {String} activityId : ID activity
         * @apiParam {CategoryAge} categoryAge : age category
         * @apiParam {String} seasonCode : season
         * @apiParam {Array(Participant)} participants : list of participants (at least one)
         * @apiParam {Array(Journey)} journeys (optional) : list of journeys
         *
         * @apiSuccess {Object} championship com.qaobee.hive.business.model.commons.referencial.Championship
         * @apiError DATA_ERROR Error on DB request
         */
        final Handler<Message<String>> addChampionshipHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    JsonObject championship = new JsonObject(req.getBody());
                    championship.putString("_id", mongo.save(championship, ChampionShip.class));
                    message.reply(championship.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        };

        /**
         * @apiDescription Update a championship.
         * @api {post} /api/1/commons/referencial/championship/update Update a championship
         * @apiName updateChampionshipHandler
         * @apiGroup Championship API
         * @apiPermission TBD
         *
         * @apiParam {String} id : identifier of the championship
         * @apiParam {String} label : championship label
         * @apiParam {LevelGame} levelGame : level game
         * @apiParam {String} subLevelGame : sub-level game label
         * @apiParam {String} pool : pool label
         * @apiParam {String} instance (optional) : instance
         * @apiParam {String} activityId : ID activity
         * @apiParam {CategoryAge} categoryAge : age category
         * @apiParam {String} seasonCode : season
         * @apiParam {Array(Participant)} participants : list of participants (at least one)
         * @apiParam {Array(Journey)} journeys (optional) : list of journeys
         *
         * @apiSuccess {Object} championship com.qaobee.hive.business.model.commons.referencial.Championship
         * @apiError DATA_ERROR Error on DB request
         */
        final Handler<Message<String>> updateChampionshipHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    JsonObject championship = new JsonObject(req.getBody());
                    mongo.save(championship, ChampionShip.class);
                    message.reply(championship.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        };
        
        
	   	/*
         * Handlers declaration.
		 */
        vertx.eventBus().registerHandler(GET_LIST, getListChampionshipsHandler);
        vertx.eventBus().registerHandler(ADD, addChampionshipHandler);
        vertx.eventBus().registerHandler(GET, getChampionshipHandler);
        vertx.eventBus().registerHandler(UPDATE, updateChampionshipHandler);
    }

}
