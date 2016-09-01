/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */
package com.qaobee.hive.api.v1.sandbox.stats;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.dao.CollectDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

/**
 * The type Sb collect verticle.
 *
 * @author cke
 */
@DeployableVerticle
public class SB_CollectVerticle extends AbstractGuiceVerticle {// NOSONAR
    /**
     * Handler for get a list of collecte document
     */
    public static final String GET_LIST = Module.VERSION + ".sandbox.stats.collect.list";
    /**
     * Handler for get individual collecte document
     */
    public static final String GET = Module.VERSION + ".sandbox.stats.collect.get";
    /**
     * Handler for adding a collecte document
     */
    public static final String ADD = Module.VERSION + ".sandbox.stats.collect.add";
    /**
     * Handler for update a collecte document
     */
    public static final String UPDATE = Module.VERSION + ".sandbox.stats.collect.update";
    /**
     * Collecte ID
     */
    public static final String PARAM_ID = "_id";
    /**
     * Collecte event
     */
    public static final String PARAM_EVENT = "eventRef";
    /**
     * Collecte players
     */
    public static final String PARAM_PLAYERS = "players";
    /**
     * Collecte event id
     */
    public static final String PARAM_EVENT_ID = "eventId";
    /**
     * Collecte sandboxId
     */
    public static final String PARAM_SANDBOX_ID = "sandboxId";
    /**
     * Collecte effectiveId
     */
    public static final String PARAM_EFFECTIVE_ID = "effectiveId";
    /**
     * Collecte teamId
     */
    public static final String PARAM_TEAM_ID = "teamId";
    /**
     * Event Start date
     */
    public static final String PARAM_START_DATE = "startDate";
    /**
     * Event End date
     */
    public static final String PARAM_END_DATE = "endDate";
    private static final Logger LOG = LoggerFactory.getLogger(SB_CollectVerticle.class);

    @Inject
    private Utils utils;
    @Inject
    private CollectDAO collectDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET_LIST, this::getList)
                .registerHandler(ADD, this::add)
                .registerHandler(UPDATE, this::update)
                .registerHandler(GET, this::get);
    }

    /**
     * @apiDescription Retrieve Collect by this Id
     * @api {get} /api/1/sandbox/stats/collect/get Get collect by Id
     * @apiName get
     * @apiGroup Collect API
     * @apiParam {String} id
     * @apiHeader {String} token
     * @apiSuccess {Object} collect
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = PARAM_ID, scope = Rule.Param.REQUEST)
    private void get(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(collectDAO.get(req.getParams().get(PARAM_ID).get(0)).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Update an collect.
     * @api {post} /api/1/sandbox/stats/collect/update Update a collect
     * @apiName update
     * @apiGroup Collect API
     * @apiHeader {String} token
     * @apiSuccess {Object} collect updated collect
     */
    @Rule(address = UPDATE, method = Constants.POST, logged = true, mandatoryParams = {PARAM_EVENT, PARAM_PLAYERS}, scope = Rule.Param.BODY)
    private void update(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        message.reply(collectDAO.update(new JsonObject(req.getBody()), req.getUser().get_id(), req.getLocale()).encode());
    }

    /**
     * @apiDescription Add a collect.
     * @api {post} /api/1/sandbox/stats/collect/add Add a collect
     * @apiName add
     * @apiGroup Collect API
     * @apiHeader {String} token
     * @apiSuccess {Object} collect Created collect
     */
    @Rule(address = ADD, method = Constants.POST, logged = true, mandatoryParams = {PARAM_EVENT, PARAM_PLAYERS}, scope = Rule.Param.BODY)
    private void add(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(collectDAO.add(new JsonObject(req.getBody()), req.getUser().get_id(), req.getLocale()).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription retrieve all collects
     * @api {post} /api/1/sandbox/stats/collect/list Get all collects
     * @apiName getList
     * @apiGroup SB_Collecet API
     * @apiParam {String} startDate start date
     * @apiParam {String} endDate end date
     * @apiParam {String} sandBoxId
     * @apiHeader {String} token
     * @apiSuccess {Array} list of collects
     */
    @Rule(address = GET_LIST, method = Constants.POST, logged = true,
            mandatoryParams = {PARAM_START_DATE, PARAM_END_DATE, PARAM_SANDBOX_ID}, scope = Rule.Param.BODY)
    private void getList(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(collectDAO.getList(new JsonObject(req.getBody())).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
