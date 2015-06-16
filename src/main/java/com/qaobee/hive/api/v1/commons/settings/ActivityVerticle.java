/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */
package com.qaobee.hive.api.v1.commons.settings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;

/**
 * @author cke
 *
 */
@DeployableVerticle
public class ActivityVerticle extends AbstractGuiceVerticle {

	// Declaration des variables finals
	/** The Constant GET. */
	public static final String GET = "resthandler.api.v1.commons.settings.activity.get";
	/** The Constant GET_LIST. */
	public static final String GET_LIST = "resthandler.api.v1.commons.settings.activity.getList";
	/** The Constant GET_LIST_ENABLE. */
	public static final String GET_LIST_ENABLE = "resthandler.api.v1.commons.settings.activity.getListEnable";
	
	/* List of parameters */
	/** Id of the structure */
	public static final String PARAM_ID = "_id";
	
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
		 * @api {get} /rest/api/v1/commons/settings/activity/get Read data of an Activity
		 * @apiVersion 0.1.0
		 * @apiName get
		 * @apiGroup Activity API
		 * @apiPermission all
		 *
		 * @apiDescription get a activity to the collection activity in settings module  
		 *
		 * @apiParam {String} id The Activity-ID.
		 * 
		 * @apiSuccess {Activity}   activity            The Activity found.
		 *
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> get = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("get() - Activity");
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
					
					final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), Activity.class);
					
					container.logger().info("Activity found : " + json.toString());
					
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
		 * @api {getList} /rest/api/v1/commons/settings/activity/getList
		 * @apiVersion 0.1.0
		 * @apiName getList
		 * @apiGroup Activity API
		 * @apiPermission all
		 *
		 * @apiDescription get all activity
		 * 
		 * @apiSuccess {List}   activities            List all activity
		 *
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> getList = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("getList() - Activity");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.GET, req.getMethod());
					
					Map<String, Object> criterias = new HashMap<String, Object>();
										
					JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, Activity.class);
					
					container.logger().info("Activities found : " + resultJson.toString());
					
					message.reply(resultJson.encode());
					
				} catch (final NoSuchMethodException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} 
			}
		};


		/**
		 * @api {getListEnable} /rest/api/v1/commons/settings/activity/getListEnable
		 * @apiVersion 0.1.0
		 * @apiName getListEnable
		 * @apiGroup Activity API
		 * @apiPermission all
		 *
		 * @apiDescription get all activity enable  
		 * 
		 * @apiSuccess {List}   activities            List of activity enable
		 *
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> getListEnable = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("getListEnable() - Activity");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.GET, req.getMethod());
					
					Map<String, Object> criterias = new HashMap<String, Object>();
					criterias.put("enable", true);
					
					JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, Activity.class);
					
					container.logger().info("Activities found : " + resultJson.toString());
					
					message.reply(resultJson.encode());
					
				} catch (final NoSuchMethodException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} 
			}
		};

		/*
		 * Handlers registration
		 */
		vertx.eventBus().registerHandler(GET, get);
		vertx.eventBus().registerHandler(GET_LIST, getList);
		vertx.eventBus().registerHandler(GET_LIST_ENABLE, getListEnable);
	}

}