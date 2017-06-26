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
import com.qaobee.hive.dao.ActivityCfgDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

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
    private ActivityCfgDAO activityCfgDAO;

    @Override
    public void start() {
        super.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug(this.getClass().getName() + " started");
        }
        vertx.eventBus().consumer(GET, this::getActivityCfgHandler);
        vertx.eventBus().consumer(PARAMS, this::getActivityCfgParamsHandler);
    }

    /**
     * @apiDescription retrieve a list of value for one parameter ActivityCfg
     * @api {post} /api/1/commons/settings/activitycfg/params params ActivityCfg
     * @apiVersion 0.1.0
     * @apiName params
     * @apiGroup ActivityCfg API
     * @apiHeader {String} token
     * @apiParam {String} activityId Activity Id
     * @apiParam {String} countryId Country Id
     * @apiParam {long} date the current date
     * @apiParam {String} paramFieldList the list of value
     */
    @Rule(address = PARAMS, method = Constants.GET, logged = true,
          mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE, PARAM_FIELD_LIST},
          scope = Rule.Param.REQUEST)
    private void getActivityCfgParamsHandler(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        activityCfgDAO.getActivityCfgParams(
                req.getParams().get(PARAM_ACTIVITY_ID).get(0),
                req.getParams().get(PARAM_COUNTRY_ID).get(0),
                Long.parseLong(req.getParams().get(PARAM_DATE).get(0)),
                req.getParams().get(PARAM_FIELD_LIST).get(0)
        ).done(params -> message.reply(params.getJsonObject(0).getJsonArray(req.getParams().get(PARAM_FIELD_LIST).get(0)).encode()))
                .fail(e -> utils.sendError(message, e));
    }

    /**
     * @apiDescription Fetch ActivityCfg
     * @api {post} /api/1/commons/settings/activitycfg/get Get ActivityCfg
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup ActivityCfg API
     * @apiHeader {String} token
     * @apiParam {String} activityId Activity Id
     */
    @Rule(address = GET, method = Constants.GET, logged = true,
          mandatoryParams = {PARAM_ACTIVITY_ID, PARAM_COUNTRY_ID, PARAM_DATE}, scope = Rule.Param.REQUEST)
    private void getActivityCfgHandler(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, activityCfgDAO.getActivityCfg(
                req.getParams().get(PARAM_ACTIVITY_ID).get(0),
                req.getParams().get(PARAM_COUNTRY_ID).get(0),
                Long.parseLong(req.getParams().get(PARAM_DATE).get(0))
        ));
    }
}
