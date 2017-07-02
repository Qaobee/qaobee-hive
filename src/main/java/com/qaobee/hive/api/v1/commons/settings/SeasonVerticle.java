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
import com.qaobee.hive.dao.SeasonDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;

import javax.inject.Inject;

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
    /**
     * Activity ID
     */
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * Country ID
     */
    public static final String PARAM_COUNTRY_ID = "countryId";
    @Inject
    private SeasonDAO seasonDAO;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this)
                .add(GET, this::getSeason)
                .add(GET_LIST_BY_ACTIVITY, this::getListByActivity)
                .add(GET_CURRENT, this::getCurrentSeason)
                .register(startFuture);
    }

    /**
     * @apiDescription Retrieve current season for one activity and one country
     * @api {get} /api/1/commons/settings/season/current Retrieve current seasons
     * @apiVersion 0.1.0
     * @apiName getCurrentSeason
     * @apiGroup Season API
     * @apiParam activityId Activity Id
     * @apiParam countryId Country Id (ie "CNTR-250-FR-FRA")
     * @apiHeader {String} token
     * @apiSuccess {Object} seasons com.qaobee.hive.business.model.commons.settings.Season
     */
    @Rule(address = GET_CURRENT, method = Constants.GET, logged = true,
            mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID},
            scope = Rule.Param.REQUEST)
    private void getCurrentSeason(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, seasonDAO.getCurrentSeason(req.getParams().get(PARAM_ACTIVITY_ID).get(0), req.getParams().get(PARAM_COUNTRY_ID).get(0)));
    }

    /**
     * @apiDescription Retrieve all seasons for one activity and one country
     * @api {get} /api/1/commons/settings/season/getListByActivity Retrieve all seasons
     * @apiVersion 0.1.0
     * @apiName getListByActivity
     * @apiParam activityId Activity Id
     * @apiParam countryId Country Id (ie "CNTR-250-FR-FRA")
     * @apiGroup Season API
     * @apiHeader {String} token
     * @apiSuccess {Array} seasons com.qaobee.hive.business.model.commons.settings.Season
     */
    @Rule(address = GET_LIST_BY_ACTIVITY, method = Constants.GET, logged = true,
            mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID},
            scope = Rule.Param.REQUEST)
    private void getListByActivity(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonArray(message, seasonDAO.getListByActivity(req.getParams().get(PARAM_ACTIVITY_ID).get(0), req.getParams().get(PARAM_COUNTRY_ID).get(0)));
    }

    /**
     * @apiDescription get a season to the collection season in settings module
     * @api {get} /api/1/commons/settings/season/get Get season by id
     * @apiVersion 0.1.0
     * @apiName getSeason
     * @apiGroup Season API
     * @apiHeader {String} token
     * @apiParam {String} _id Mandatory The season Id.
     * @apiSuccess {Object} the object found
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = PARAM_ID,
            scope = Rule.Param.REQUEST)
    private void getSeason(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, seasonDAO.getSeason(req.getParams().get(PARAM_ID).get(0)));
    }
}
