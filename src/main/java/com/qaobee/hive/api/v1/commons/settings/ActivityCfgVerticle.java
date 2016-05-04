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
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@DeployableVerticle
public class ActivityCfgVerticle extends AbstractGuiceVerticle {
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
    private static final Logger LOG = LoggerFactory.getLogger(ActivityCfgVerticle.class);
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET, this::getActivityCfgHandler)
                .registerHandler(PARAMS, this::getActivityCfgParamsHandler);
    }

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
     */
    @Rule(address = PARAMS, method = Constantes.GET, logged = true,
            mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE, PARAM_FIELD_LIST}, scope = Rule.Param.REQUEST)
    private void getActivityCfgParamsHandler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            // Activity ID
            String activityId = req.getParams().get(PARAM_ACTIVITY_ID).get(0);
            // Country ID
            String countryId = req.getParams().get(PARAM_COUNTRY_ID).get(0);
            // Reference Date
            Long dateRef = Long.parseLong(req.getParams().get(PARAM_DATE).get(0));
            // Parameter Field List Name
            String paramField = req.getParams().get(PARAM_FIELD_LIST).get(0);
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
            final JsonArray resultJSon = mongo.aggregate(paramField, pipelineAggregation, ActivityCfg.class);
            if (resultJSon == null) {
                throw new QaobeeException(ExceptionCodes.DATA_ERROR, "Resultset for field '" + paramField + "' is null (" + activityId + "/" + countryId + "/" + dateRef + ")");
            }
            if (resultJSon.size() != 1 || !((JsonObject) resultJSon.get(0)).containsField(paramField)) {
                throw new QaobeeException(ExceptionCodes.DATA_ERROR, "Field to retrieve is unknown : '" + paramField + "' (" + activityId + "/" + countryId + "/" + dateRef + ")");
            }
            message.reply(((JsonObject) resultJSon.get(0)).getArray(paramField).encode());
        } catch (NumberFormatException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, "Date is not numeric");
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Fetch ActivityCfg
     * @api {post} /api/1/commons/settings/activitycfg/get Get ActivityCfg
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup ActivityCfg API
     * @apiParam {String} activityId Activity Id
     */
    @Rule(address = GET, method = Constantes.GET, logged = true,
            mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE}, scope = Rule.Param.REQUEST)
    private void getActivityCfgHandler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            // Activity ID
            String activityId = req.getParams().get(PARAM_ACTIVITY_ID).get(0);
            // Country ID
            String countryId = req.getParams().get(PARAM_COUNTRY_ID).get(0);
            // Reference Date
            Long dateRef = Long.parseLong(req.getParams().get(PARAM_DATE).get(0));
            // Creation of request
            CriteriaBuilder criterias = new CriteriaBuilder();
            criterias.add("activityId", activityId);
            criterias.add("countryId", countryId);
            criterias.between("startDate", "endDate", dateRef);
            // Call to mongo
            JsonArray resultJSon = mongo.findByCriterias(criterias.get(), null, null, -1, -1, ActivityCfg.class);
            if (resultJSon == null || resultJSon.size() == 0) {
                throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No activity configuration was found for (" + activityId + " / " + countryId + " / " + dateRef + ")");
            }
            JsonObject jsonObject = resultJSon.get(0);
            message.reply(jsonObject.encode());
        } catch (NumberFormatException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, "Date is not numeric");
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
