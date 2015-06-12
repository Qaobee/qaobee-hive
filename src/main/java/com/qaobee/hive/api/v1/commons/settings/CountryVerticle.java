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
package com.qaobee.hive.api.v1.commons.settings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.technical.vertx.utils.guice.AbstractGuiceVerticle;

/**
 * @author cke
 *
 */
@DeployableVerticle
public class CountryVerticle extends AbstractGuiceVerticle {

	// Declaration des variables finals
	/** The Constant GET. */
	public static final String GET = "resthandler.api.v1.commons.settings.country.get";
	/** The Constant GET. */
	public static final String GET_LIST = "resthandler.api.v1.commons.settings.country.getList";
	/** The Constant ADD. */
	public static final String ADD = "resthandler.api.v1.commons.settings.country.add";
	/** The Constant UPDATE. */
	public static final String UPDATE = "resthandler.api.v1.commons.settings.country.update";
	
	/* List of parameters */
	/** Id of the structure */
	public static final String PARAM_ID = "_id";
	
	/** Label of the structure */
	public static final String PARAM_LABEL = "label";
	
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
		 * @apiDescription Add country to the collection country in settings module 
		 * @api {post} /rest/api/v1/commons/settings/country/add resthandler.api.v1.commons.settings.country.add
		 * @apiName addHandler
		 * @apiGroup countryVerticle
		 * @apiSuccess {country} the object added
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> addHandler = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("addHandler() - country");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.POST, req.getMethod());
					final JsonObject params = new JsonObject(req.getBody());
					utils.testMandatoryParams(params.toMap(), PARAM_LABEL);
					
					// Insert a country
					final String id = mongo.save(params, Country.class);
					
					container.logger().info("country added : " + params.toString());
					
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
		 * @apiDescription Update a country to the collection country in settings module 
		 * @api {post} /rest/api/v1/commons/settings/country/update resthandler.api.v1.commons.settings.country.update
		 * @apiName updateHandler
		 * @apiGroup countryVerticle
		 * @apiSuccess {country} the object updated
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> updateHandler = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("in updateHandler() - Country");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.POST, req.getMethod());
					final JsonObject params = new JsonObject(req.getBody());
					utils.testMandatoryParams(params.toMap(), PARAM_LABEL, PARAM_ID);
					
					// Update a country
					mongo.save(params, Country.class);
					
					container.logger().info("country updated : " + params.toString());
					
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
		 * @apiDescription get a country to the collection country in settings module 
		 * @api {post} /rest/api/v1/commons/settings/country/get resthandler.api.v1.commons.settings.country.get
		 * @apiName getHandler
		 * @apiGroup CountryVerticle
		 * @apiSuccess {Country} the object found
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> getHandler = new Handler<Message<String>>() {
			
			@Override
			public void handle(final Message<String> message) {
				container.logger().info("getHandler() - Country");
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
					
					final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), Country.class);
					
					container.logger().info("Country found : " + json.toString());
					
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
		 * @apiDescription get a country to the collection country in settings module 
		 * @api {post} /rest/api/v1/commons/settings/country/get resthandler.api.v1.commons.settings.country.get
		 * @apiName getHandler
		 * @apiGroup CountryVerticle
		 * @apiSuccess {Country} the object found
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
		final Handler<Message<String>> getListHandler = new Handler<Message<String>>() {

			@Override
			public void handle(final Message<String> message) {
				container.logger().info("getListHandler() - Country");
				final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
				try {
					// Tests on method and parameters
					utils.testHTTPMetod(Constantes.GET, req.getMethod());
					Map<String, Object> criterias = new HashMap<String, Object>();

					// label
					String label = req.getParams().get(PARAM_LABEL).get(0);

					// Creation of the request
					if (!StringUtils.isBlank(label)) {
						criterias.put("label", label);
					}

					JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, Country.class);

					if (resultJson == null || resultJson.size() == 0) {
						throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No Country defined for (" + label + ")");
					}

					message.reply(resultJson.encode());
				} catch (final NoSuchMethodException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final QaobeeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, e);
				}
			}
		};

		/*
		 * Handlers registration
		 */
		vertx.eventBus().registerHandler(ADD, addHandler);
		vertx.eventBus().registerHandler(UPDATE, updateHandler);
		vertx.eventBus().registerHandler(GET, getHandler);
		vertx.eventBus().registerHandler(GET_LIST, getListHandler);
	}

}
