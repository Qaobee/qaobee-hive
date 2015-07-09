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

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.referencial.Team;
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
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author cke
 *
 */
@DeployableVerticle(isWorker = true)
public class TeamVerticle extends AbstractGuiceVerticle {
	
	// Declaration des variables finals
	/** The Constant ADD. */
	public static final String ADD = Module.VERSION + ".commons.referencial.commons.team.add";
	/** The Constant GET. */
	public static final String GET = Module.VERSION + ".commons.referencial.commons.team.get";
	/** The Constant UPDATE. */
	public static final String UPDATE = Module.VERSION + ".commons.referencial.commons.team.update";
	
	/* List of parameters */
	/** Id of the team */
	public static final String PARAM_ID = "_id";
	
	/** Label of the team */
	public static final String PARAM_LABEL = "label";
	
	/** activity of the team */
	public static final String PARAM_ACTIVITY = "activity";
	
	/** country of the team */
	public static final String PARAM_COUNTRY = "country";

	/* Injections */
	@Inject
	private MongoDB mongo;
	@Inject
	private Utils utils;


	@Override
	public void start() {
		super.start();
		container.logger().debug(this.getClass().getName() + " started");

		/**
		 * @api {post} /api/v1/commons/referencial/team/add Add team
		 * @apiVersion 0.1.0
		 * @apiName add
		 * @apiGroup Team API
		 * @apiPermission all
		 *
		 * @apiDescription Add team to the collection team in referencial module
		 * 
		 * @apiParam {String} label Mandatory The Team label.
		 * @apiParam {Activity} activity Mandatory The Team activity.
		 * @apiParam {Country} country Mandatory The Team country
		 *
		 * @apiSuccess {Team}   team            The Team added with the id.
		 *
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> add = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("add() - Team");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.POST, req.getMethod());
					final JsonObject params = new JsonObject(req.getBody());
					utils.testMandatoryParams(params.toMap(), PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY);
					
					// Insert a team
					final String id = mongo.save(params, Team.class);
					
					params.putString("_id", id);
					container.logger().info("Team added : " + params.toString());
					
					message.reply(params.encode());
					
				} catch (final NoSuchMethodException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final IllegalArgumentException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
				} catch (final EncodeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
				} catch (final QaobeeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
				}
			}
		};

		
		/**
		 * @api {get} /api/v1/commons/referencial/team/get Read data of a Team
		 * @apiVersion 0.1.0
		 * @apiName get
		 * @apiGroup Team API
		 * @apiPermission all
		 *
		 * @apiDescription get a team to the collection team in referencial module
		 *
		 * @apiParam {String} id The Team-ID.
		 * 
		 * @apiSuccess {Team}   team            The Team found.
		 *
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> get = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("get() - Team");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.GET, req.getMethod());
					Map<String, List<String>> params = req.getParams();
					
					utils.testMandatoryParams(params, PARAM_ID);
					
					// Tests mandatory parameters
					utils.testMandatoryParams(params, PARAM_ID);
					if (StringUtils.isBlank(params.get(PARAM_ID).get(0))) {
						throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, PARAM_ID+" is mandatory");
					}
					
					final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), Team.class);
					
					container.logger().info("Team found : " + json.toString());
					
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
		};

		/**
		 * @api {post} /api/v1/commons/referencial/team/update
		 * @apiVersion 0.1.0
		 * @apiName update
		 * @apiGroup Team API
		 * @apiPermission all
		 *
		 * @apiDescription Update a Team to the collection Team in referencial module
		 *
		 * @apiParam {String} _id Mandatory The Team ID.
		 * @apiParam {String} label Mandatory The Team label.
		 * @apiParam {Activity} activity Mandatory The Team activity.
		 * @apiParam {Country} country Mandatory The Team country
		 * 
		 * @apiSuccess {Team}   team            The Team updated.
		 *
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> update = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("update() - Team");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.POST, req.getMethod());
					final JsonObject params = new JsonObject(req.getBody());
					utils.testMandatoryParams(params.toMap(), PARAM_ID, PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY);
					
					// Update a team
					mongo.save(params, Team.class);
					
					container.logger().info("Team updated : " + params.toString());
					
					message.reply(params.encode());
					
				} catch (final NoSuchMethodException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final IllegalArgumentException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
				} catch (final EncodeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
				} catch (final QaobeeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
				}
			}
		};

		/*
		 * Handlers registration
		 */
		vertx.eventBus().registerHandler(ADD, add);
		vertx.eventBus().registerHandler(GET, get);
		vertx.eventBus().registerHandler(UPDATE, update);
	}

}
