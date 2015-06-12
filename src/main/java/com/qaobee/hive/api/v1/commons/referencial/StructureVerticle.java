/*************************************************************************
 *
 * Qaobee
 * __________________
 *
 * [2014] Qaobee
 * All Rights Reserved.
 *
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.api.v1.commons.referencial;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import com.qaobee.hive.business.model.commons.referencial.Structure;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.technical.vertx.utils.guice.AbstractGuiceVerticle;

/**
 * Module commons - referencial - Structure.
 *
 * @author Nada Vujanic-Maquin<br>
 * <br>
 *         <strong>Description de la classe:</strong>
 *         <ul>
 *         <li>resthandler.api.v1.commons.referencial.structure.add : Add a structure</li>
 *         <li>resthandler.api.v1.commons.referencial.structure.get : fetch a structure</li>
 *         <li>resthandler.api.v1.commons.referencial.structure.update : update structure</li>
 *         </ul>
 */

@DeployableVerticle
public class StructureVerticle extends AbstractGuiceVerticle {

	// Declaration des variables finals
	/** The Constant ADD. */
	public static final String ADD = "resthandler.api.v1.commons.referencial.commons.structure.add";
	/** The Constant GET. */
	public static final String GET = "resthandler.api.v1.commons.referencial.commons.structure.get";
	/** The Constant UPDATE. */
	public static final String UPDATE = "resthandler.api.v1.commons.referencial.commons.structure.update";
	
	/* List of parameters */
	/** Id of the structure */
	public static final String PARAM_ID = "_id";
	
	/** Label of the structure */
	public static final String PARAM_LABEL = "label";
	
	/** activity of the structure */
	public static final String PARAM_ACTIVITY = "activity";
	
	/** country of the structure */
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
		 * @apiDescription Add structure to the collection structure in referencial module 
		 * @api {post} /rest/api/v1/commons/referencial/structure/add resthandler.api.v1.commons.referencial.structure.add
		 * @apiName addHandler
		 * @apiGroup StructureVerticle
		 * @apiSuccess {Structure} the object added
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> addHandler = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("addHandler() - Structure");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.POST, req.getMethod());
					final JsonObject params = new JsonObject(req.getBody());
					utils.testMandatoryParams(params.toMap(), PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY);
					
					// Insert a structure
					final String id = mongo.save(params, Structure.class);
					
					container.logger().info("Structure added : " + params.toString());
					
					params.putString("_id", id);
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
		 * @apiDescription get a structure to the collection structure in referencial module 
		 * @api {post} /rest/api/v1/commons/referencial/structure/get resthandler.api.v1.commons.referencial.structure.get
		 * @apiName getHandler
		 * @apiGroup StructureVerticle
		 * @apiSuccess {Structure} the object found
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> getHandler = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("getHandler() - Structure");
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
					
					final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), Structure.class);
					
					container.logger().info("Structure found : " + json.toString());
					
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
		 * @apiDescription Update a structure to the collection structure in referencial module 
		 * @api {post} /rest/api/v1/commons/referencial/structure/update resthandler.api.v1.commons.referencial.structure.update
		 * @apiName updateHandler
		 * @apiGroup StructureVerticle
		 * @apiSuccess {Structure} the object updated
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> updateHandler = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("in updateStructureHandler()");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.POST, req.getMethod());
					final JsonObject params = new JsonObject(req.getBody());
					utils.testMandatoryParams(params.toMap(), PARAM_ID, PARAM_LABEL, PARAM_ACTIVITY, PARAM_COUNTRY);
					
					// Update a structure
					mongo.save(params, Structure.class);
					
					container.logger().info("Structure updated : " + params.toString());
					
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
		vertx.eventBus().registerHandler(ADD, addHandler);
		vertx.eventBus().registerHandler(GET, getHandler);
		vertx.eventBus().registerHandler(UPDATE, updateHandler);
	}
}
