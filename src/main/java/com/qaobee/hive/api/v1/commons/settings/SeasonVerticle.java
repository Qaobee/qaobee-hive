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

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
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
import java.util.HashMap;
import java.util.Map;

/**
 * The type Season verticle.
 *
 * @author cke
 */
@DeployableVerticle
public class SeasonVerticle extends AbstractGuiceVerticle {
    /**
     * The Constant GET.
     */
    public static final String GET = Module.VERSION + ".commons.settings.season.get";
    // Declaration des variables finals
    /**
     * The Constant GET_LIST_BY_ACTIVITY.
     */
    public static final String GET_LIST_BY_ACTIVITY = Module.VERSION + ".commons.settings.season.getListByActivity";
    /**
     * The constant GET_CURRENT.
     */
    public static final String GET_CURRENT = Module.VERSION + ".commons.settings.season.current";
    /**
     * Id of the season
     */
    public static final String PARAM_ID = "_id";

 /* List of parameters */
    /**
     * Activity ID
     */
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * Country ID
     */
    public static final String PARAM_COUNTRY_ID = "countryId";
    /**
     * Reference date
     */
    public static final String PARAM_DATE = "date";
    private static final Logger LOG = LoggerFactory.getLogger(SeasonVerticle.class);
    /**
     * The Mongo.
     */
/* Injections */
    @Inject
    private MongoDB mongo;
    /**
     * The Utils.
     */
    @Inject
    private Utils utils;

    /**
     * Start void.
     */
    @Override
    @VerticleHandler({
            @Rule(address = GET, method = Constantes.GET, logged = true, mandatoryParams = {PARAM_ID},
                    scope = Rule.Param.REQUEST),
            @Rule(address = GET_LIST_BY_ACTIVITY, method = Constantes.GET, logged = true,
                    mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID},
                    scope = Rule.Param.REQUEST),
            @Rule(address = GET_CURRENT, method = Constantes.GET, logged = true,
                    mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID},
                    scope = Rule.Param.REQUEST)
    })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @apiDescription get a season to the collection season in settings module
         * @api {get} /api/1/commons/settings/season/get Get season by id
         * @apiVersion 0.1.0
         * @apiName getHandler
         * @apiGroup Season API
         * @apiParam {String} _id Mandatory The season Id.
         * @apiSuccess {Season} the object found
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    final JsonObject json = mongo.getById(req.getParams().get(PARAM_ID).get(0), Season.class);
                    message.reply(json.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @apiDescription Retrieve all seasons for one activity and one country
         * @api {get} /api/1/commons/settings/season/getListByActivity Retrieve all seasons
         * @apiVersion 0.1.0
         * @apiName getListByActivityHandler
         * @apiParam activityId Activity Id
         * @apiParam countryId Country Id (ie "CNTR-250-FR-FRA")
         * @apiGroup Season API
         * @apiSuccess {Array} seasons com.qaobee.hive.business.model.commons.settings.Season
         */
        vertx.eventBus().registerHandler(GET_LIST_BY_ACTIVITY, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    // Activity ID
                    String activityId = req.getParams().get(PARAM_ACTIVITY_ID).get(0);
                    // Country ID
                    String countryId = req.getParams().get(PARAM_COUNTRY_ID).get(0);
                    // Creation of the request
                    Map<String, Object> criterias = new HashMap<>();
                    criterias.put("activityId", activityId);
                    criterias.put("countryId", countryId);
                    JsonArray resultJson = mongo.findByCriterias(criterias, null, "endDate", -1, -1, Season.class);
                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No season defined for (" + activityId + " / " + countryId + ")");
                    }
                    message.reply(resultJson.encode());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @apiDescription Retrieve current season for one activity and one country
         * @api {get} /api/1/commons/settings/season/current Retrieve current seasons
         * @apiVersion 0.1.0
         * @apiName getCurrentHandler
         * @apiGroup Season API
         * @apiParam activityId Activity Id
         * @apiParam countryId Country Id (ie "CNTR-250-FR-FRA")
         * @apiSuccess {Object} seasons com.qaobee.hive.business.model.commons.settings.Season
         */
        vertx.eventBus().registerHandler(GET_CURRENT, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                LOG.debug("getCurrentHandler() - Season");
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    // Activity ID
                    String activityId = req.getParams().get(PARAM_ACTIVITY_ID).get(0);
                    // Country ID
                    String countryId = req.getParams().get(PARAM_COUNTRY_ID).get(0);
                    // Creation of the request
                    Map<String, Object> criterias = new HashMap<>();
                    criterias.put("activityId", activityId);
                    criterias.put("countryId", countryId);
                    JsonArray resultJson = mongo.findByCriterias(criterias, null, "endDate", -1, -1, Season.class);
                    long currentDate = System.currentTimeMillis();
                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No season defined for (" + activityId + " / " + countryId + ")");
                    }
                    for (int i = 0; i < resultJson.size(); i++) {
                        JsonObject s = resultJson.get(i);
                        if (s.getLong("endDate", 0) > currentDate && s.getLong("startDate") < currentDate) {
                            message.reply(s.encode());
                            return;
                        }
                    }
                    message.reply(new JsonObject().encode());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
    }

}
