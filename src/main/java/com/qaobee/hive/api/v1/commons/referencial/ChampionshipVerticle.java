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
package com.qaobee.hive.api.v1.commons.referencial;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.referencial.Championship;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;

/**
 * @author jro
 *
 */
@DeployableVerticle(isWorker=true)
public class ChampionshipVerticle  extends AbstractGuiceVerticle {
	
	
	/** Handler to get a set of events */
    public static final String GET_LIST 	= Module.VERSION + ".commons.referencial.championship.list";
    /** Handler to add a event. */
    public static final String ADD 			= Module.VERSION + ".commons.referencial.championship.add";
    /** Handler to get a particular event from its ID. */
    public static final String GET 			= Module.VERSION + ".commons.referencial.championship.get";
    /** Handler to update a event. */
    public static final String UPDATE 		= Module.VERSION + ".commons.referencial.championship.update";

    /* List of parameters */
    /** Championship ID */
    public static final String PARAM_ID 			= "id";
    /** Activity ID */
    public static final String PARAM_ACTIVITY 		= "activityId";
    /** Season */
    public static final String PARAM_SEASON_CODE	= "seasonCode";
    /** Category Age */
    public static final String PARAM_CATEGORY_AGE 	= "categoryAge";
    /** Structure */
    public static final String PARAM_STRUCTURE		= "structure";
    /** Participant */
    public static final String PARAM_PARTICIPANT	= "participant";
    /** Label */
    public static final String PARAM_LABEL			= "label";
    /** Level Game */
    public static final String PARAM_LEVEL_GAME		= "levelGame";
    /** Sub Level Game */
    public static final String PARAM_SUB_LEVEL_GAME	= "subLevelGame";
    /** Pool */
    public static final String PARAM_POOL			= "pool";
    /** Instance */
    public static final String PARAM_INSTANCE		= "instance";
    /** Participants */
    public static final String PARAM_LIST_PARTICIPANTS	= "participants";
    /** Journeys */
    public static final String PARAM_JOURNEYS		= "journeys";
    
    /* Injections */
    /** The Mongo DB. */
    @Inject
    private MongoDB mongo;

    /** The Utils. */
    @Inject
    protected Utils utils;

    
    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");
        
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
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        final Handler<Message<String>> getListChampionshipsHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
            	container.logger().info("getList() - Championship");
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                	/*
                     * *** Params section ***
					 */
                    // Check param mandatory
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    utils.isUserLogged(req);
                    JsonObject params = new JsonObject(req.getBody());
                    utils.testMandatoryParams(params.toMap(), PARAM_ACTIVITY, PARAM_CATEGORY_AGE, PARAM_STRUCTURE);
                    Map<String, Object> mapParams = params.toMap();
                    
                    /*
                     * *** Aggregat section ***
					 */
                    DBObject match;
                    BasicDBObject dbObjectParent, dbObjectChild;

					/* *** $MACTH section *** */
                    dbObjectParent = new BasicDBObject();

                    // Activity ID
                    dbObjectParent.put("activityId", mapParams.get(PARAM_ACTIVITY));
                    // Category Age Code
                    dbObjectParent.put("categoryAge.code", mapParams.get(PARAM_CATEGORY_AGE));
                    // Structure ID
                    dbObjectParent.put("participants.structureId", mapParams.get(PARAM_STRUCTURE));
                    
                    // Participant
                    if(mapParams.containsKey(PARAM_PARTICIPANT)) {
                    	@SuppressWarnings("unchecked")
						Map<String, Object> mapParticipant = (Map<String, Object>)mapParams.get(PARAM_PARTICIPANT);
                    	dbObjectChild = new BasicDBObject();
                    	if(mapParticipant.containsKey("id")) {
                    		dbObjectChild.put("participants.id", mapParticipant.get("id"));
                    	}
                    	if(mapParticipant.containsKey("structureId")) {
                    		dbObjectChild.put("participants.structureId", mapParticipant.get("structureId"));
                    	}
                    	if(mapParticipant.containsKey("name")) {
                    		dbObjectChild.put("participants.name", mapParticipant.get("name"));
                    	}
                    	if(mapParticipant.containsKey("type")) {
                    		dbObjectChild.put("participants.type", mapParticipant.get("type"));
                    	}
                    	dbObjectParent.put("$and", Arrays.asList(dbObjectChild));
                    }
                    

                    match = new BasicDBObject("$match", dbObjectParent);

                    /* Pipeline */
                    List<DBObject> pipelineAggregation = Arrays.asList(match);
                    
                    container.logger().info("getListChampionshipHandler : " + pipelineAggregation.toString());

                    final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, Championship.class);

                    container.logger().info(resultJSon.encodePrettily());
                    message.reply(resultJSon.encode());
                    
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
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        final Handler<Message<String>> getChampionshipHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
            	container.logger().info("get() - Championship");
                try {
                	/*
                     * *** Params section ***
					 */
                    // Check param mandatory
                	final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    utils.isUserLogged(req);
                    utils.testMandatoryParams(req.getParams(), PARAM_ID);
                    
                    /* Call to MongoDB */
                    message.reply(mongo.getById(req.getParams().get(PARAM_ID).get(0), Championship.class).encode());
                    
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
        };
        
        /**
         * @apiDescription Add a championship.
         * @api {post} /api/1/commons/referencial/championship/add Add a championship
         * @apiName addChampionshipHandler
         * @apiGroup Championship API
         * @apiPermission TBD
         * 
         * @apiParam {String} id
         * 
         * @apiSuccess {Object} championship com.qaobee.hive.business.model.commons.referencial.Championship
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        final Handler<Message<String>> addChampionshipHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
            	container.logger().info("add() - Championship");
                try {
                	final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                	
                	// Check param mandatory
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    utils.isLoggedAndAdmin(req);
                    JsonObject championship = new JsonObject(req.getBody());
                    utils.testMandatoryParams(championship.toMap(), PARAM_LABEL, PARAM_LEVEL_GAME, PARAM_SUB_LEVEL_GAME, PARAM_POOL, PARAM_ACTIVITY, PARAM_CATEGORY_AGE,
                    		PARAM_SEASON_CODE); //, PARAM_LIST_PARTICIPANTS => pb sur TestMandatory quand liste d'objets autre que String
                    
                    // Call to MongoDB
                    final String id = mongo.save(championship, Championship.class);
                    championship.putString("_id", id);
                    
                    // Reply
                    message.reply(championship.encode());
                    
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
        };
        
	   	/*
		 * Handlers declaration.
		 */
	    vertx.eventBus().registerHandler(GET_LIST, getListChampionshipsHandler);
	    vertx.eventBus().registerHandler(ADD, addChampionshipHandler);
	    vertx.eventBus().registerHandler(GET, getChampionshipHandler);
	    vertx.eventBus().registerHandler(UPDATE, null);
    }
    
    
}
