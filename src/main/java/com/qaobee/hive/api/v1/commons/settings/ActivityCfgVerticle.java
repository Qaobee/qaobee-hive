/*
 * __________________
 *   Qaobee
 * __________________
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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.settings.ActivityCfg;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * The type Activity cfg verticle.
 */
@DeployableVerticle(isWorker = true) public class ActivityCfgVerticle extends AbstractGuiceVerticle {
	/**
	 * The constant GET.
	 */
	public static final String GET = Module.VERSION + ".commons.settings.activitycfg.get";
	/**
	 * Handler for getting parameters list.
	 */
	public static final String PARAMS = Module.VERSION + ".commons.settings.activitycfg.params";
	/**
	 * List of parameters
	 */
	public static final String PARAM_FIELD_LIST = "paramFieldList";

    /* List of parameters */
	/**
	 * Reference date
	 */
	public static final String PARAM_DATE = "date";
	/**
	 * Activity code
	 */
	public static final String PARAM_ACTIVITY_ID = "activityId";
	/**
	 * Country Id
	 */
	public static final String PARAM_COUNTRY_ID = "countryId";
	private static final Logger LOG = LoggerFactory.getLogger(AbstractGuiceVerticle.class);
	/**
	 * The Mongo.
	 */
	@Inject private MongoDB mongo;
	/**
	 * The Utils.
	 */
	@Inject private Utils utils;

	/**
	 * Start void.
	 */
	@Override public void start() {
		super.start();
		LOG.debug(this.getClass().getName() + " started");

		/**
		 * @apiDescription Fetch ActivityCfg
		 * @api {post} /api/1/commons/settings/activitycfg/get Get ActivityCfg
		 * @apiVersion 0.1.0
		 * @apiName get
		 * @apiGroup ActivityCfg API
		 * @apiParam {String} activityId Activity Id
		 * @apiError HTTP_ERROR wrong request method
		 * @apiError NOT_LOGGED invalid token
		 * @apiError INVALID_PARAMETER wrong parameters
		 */
		vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {
			@Override public void handle(Message<String> message) {
				LOG.debug(GET + " - ActivityCfg");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.GET, req.getMethod());
					utils.testMandatoryParams(req.getParams(), PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE);
					// Activity ID
					String activityId = req.getParams().get(PARAM_ACTIVITY_ID).get(0);
					if (StringUtils.isBlank(activityId)) {
						throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Activity id is blank or null");
					}
					// Country ID
					String countryId = req.getParams().get(PARAM_COUNTRY_ID).get(0);
					if (StringUtils.isBlank(countryId)) {
						throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Country id is blank or null");
					}
					// Reference Date
					Long dateRef;
					if (StringUtils.isBlank(req.getParams().get(PARAM_DATE).get(0))) {
						throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Date is blank or null");
					}
					dateRef = Long.parseLong(req.getParams().get(PARAM_DATE).get(0));

					// Creation of request
					CriteriaBuilder criterias = new CriteriaBuilder();
					criterias.add("activityId", activityId);
					criterias.add("countryId", countryId);
					criterias.between("startDate", "endDate", dateRef);

					// Call to mongo
					JsonArray resultJSon = mongo.findByCriterias(criterias.get(), null, null, -1, -1, ActivityCfg.class);
					if (resultJSon == null || resultJSon.size() == 0) {
						if (resultJSon != null) {
							LOG.debug(resultJSon.encodePrettily());
						}
						throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No activity configuration was found for (" + activityId + " / " + countryId + " / " + dateRef + ")");
					}

					JsonObject jsonObject = resultJSon.get(0);
					LOG.debug(jsonObject.encodePrettily());
					message.reply(jsonObject.encode());

				} catch (NumberFormatException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, "Date is not numeric");
				} catch (final NoSuchMethodException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final IllegalArgumentException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
				} catch (QaobeeException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, e);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
				}
			}
		});

		/**
		 * @apiDescription retrieve a list of value for one parameter ActivityCfg
		 * @api {post} /api/1/commons/settings/activitycfg/params params ActivityCfg
		 * @apiVersion 0.1.0
		 * @apiName params
		 * @apiGroup ActivityCfg API
		 * @apiParam {String} activityId Activity Id
		 * @apiParam {String} countryId Country Id
		 * @apiParam {long} date the current date
		 * @apiParam {String} paramFieldList the list of value
		 * @apiError HTTP_ERROR wrong request method
		 * @apiError NOT_LOGGED invalid token
		 * @apiError INVALID_PARAMETER wrong parameters
		 */
		vertx.eventBus().registerHandler(PARAMS, new Handler<Message<String>>() {
			@Override public void handle(Message<String> message) {
				LOG.debug(PARAMS + " - ActivityCfg");
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.GET, req.getMethod());
					utils.testMandatoryParams(req.getParams(), PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE, PARAM_FIELD_LIST);

					// Activity ID
					String activityId = req.getParams().get(PARAM_ACTIVITY_ID).get(0);
					if (StringUtils.isBlank(activityId)) {
						throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Activity id is blank or null");
					}
					// Country ID
					String countryId = req.getParams().get(PARAM_COUNTRY_ID).get(0);
					if (StringUtils.isBlank(countryId)) {
						throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Country id is blank or null");
					}
					// Reference Date
					Long dateRef;
					if (StringUtils.isBlank(req.getParams().get(PARAM_DATE).get(0))) {
						throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Date is blank or null");
					}
					dateRef = Long.parseLong(req.getParams().get(PARAM_DATE).get(0));
					// Parameter Field List Name
					String paramField = req.getParams().get(PARAM_FIELD_LIST).get(0);
					if (StringUtils.isBlank(paramField)) {
						throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Param field list is blank or null");
					}

					DBObject match;
					DBObject project;
					BasicDBObject dbObjectParent;

					// $MATCH section
					dbObjectParent = new BasicDBObject();

					// - activityId
					dbObjectParent.put("activityId", activityId);
					// - countryId
					dbObjectParent.put("countryId", countryId);
					// - date between start and end dates
					dbObjectParent.put("startDate", new BasicDBObject("$lte", dateRef));
					dbObjectParent.put("endDate", new BasicDBObject("$gte", dateRef));

					match = new BasicDBObject("$match", dbObjectParent);

					// $PROJECT section
					dbObjectParent = new BasicDBObject();
					dbObjectParent.put("_id", 0);
					dbObjectParent.put(paramField, 1);
					project = new BasicDBObject("$project", dbObjectParent);

					List<DBObject> pipelineAggregation = Arrays.asList(match, project);
					LOG.debug("getParamFieldHandler : " + pipelineAggregation.toString());

					final JsonArray resultJSon = mongo.aggregate(paramField, pipelineAggregation, ActivityCfg.class);
					if (resultJSon == null) {
						throw new QaobeeException(ExceptionCodes.MONGO_ERROR, "Resultset for field '" + paramField + "' is null (" + activityId + "/" + countryId + "/" + dateRef + ")");
					}

					LOG.debug(resultJSon.encodePrettily());
					if (resultJSon.size() != 1 || !((JsonObject) resultJSon.get(0)).containsField(paramField)) {
						throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Field to retrieve is unknown : '" + paramField + "' (" + activityId + "/" + countryId + "/" + dateRef + ")");
					}

					message.reply(((JsonObject) resultJSon.get(0)).getArray(paramField).encode());

				} catch (NumberFormatException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, "Date is not numeric");
				} catch (final NoSuchMethodException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final IllegalArgumentException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
				} catch (final QaobeeException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, e);
				}
			}
		});
	}
}
