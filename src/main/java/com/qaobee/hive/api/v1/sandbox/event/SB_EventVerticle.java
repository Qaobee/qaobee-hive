/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
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
package com.qaobee.hive.api.v1.sandbox.event;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.dao.EventDAO;
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
 * The type Event verticle.
 *
 * @author cke
 */
@DeployableVerticle
public class SB_EventVerticle extends AbstractGuiceVerticle { // NOSONAR
    /**
     * The constant GET_LIST.
     */
    public static final String GET_LIST = Module.VERSION + ".sandbox.event.event.list";
    /**
     * The constant ADD.
     */
    public static final String ADD = Module.VERSION + ".sandbox.event.event.add";
    /**
     * The constant GET.
     */
    public static final String GET = Module.VERSION + ".sandbox.event.event.get";
    /**
     * The constant UPDATE.
     */
    public static final String UPDATE = Module.VERSION + ".sandbox.event.event.update";
    /**
     * Event ID
     */
    public static final String PARAM_ID = "_id";
    /**
     * Event activity
     */
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * Event Season code
     */
    public static final String PARAM_LABEL = "label";
    /**
     * Event Start date
     */
    public static final String PARAM_START_DATE = "startDate";
    /**
     * Event End date
     */
    public static final String PARAM_END_DATE = "endDate";
    /**
     * link Type
     */
    public static final String PARAM_LINK_TYPE = "type";
    /**
     * Event Owner
     */
    public static final String PARAM_OWNER = "owner";
    /**
     * Event Owner sandboxId
     */
    public static final String PARAM_OWNER_SANBOXID = "ownersandboxId";
    /**
     * Event Owner effectiveId
     */
    public static final String PARAM_OWNER_EFFECTIVEID = "ownereffectiveId";
    /**
     * Event Owner teamId
     */
    public static final String PARAM_OWNER_TEAMID = "ownerteamId";
    /**
     * list clause SORT by
     */
    public static final String PARAM_LIST_SORTBY = "listFieldsSortBy";
    /**
     * limit number of result
     */
    public static final String PARAM_LIMIT_RESULT = "limitResult";
    private static final Logger LOG = LoggerFactory.getLogger(SB_EventVerticle.class);
    @Inject
    private Utils utils;
    @Inject
    private EventDAO eventDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET_LIST, this::getEventList)
                .registerHandler(ADD, this::addEvent)
                .registerHandler(UPDATE, this::updateEvent)
                .registerHandler(GET, this::getEvent);
    }

    /**
     * @apiDescription Retrieve Event by this Id
     * @api {get} /api/1/sandbox/event/event/get Get event by Id
     * @apiName getEvent
     * @apiGroup Event API
     * @apiHeader {String} token
     * @apiParam {String} id
     * @apiSuccess {Object} event com.qaobee.swarn.business.model.tranversal.event.event;
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = {PARAM_ID},
            scope = Rule.Param.REQUEST)
    private void getEvent(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(eventDAO.getEvent(req.getParams().get(PARAM_ID).get(0)).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Update an event.
     * @api {post} /api/1/sandbox/event/event/update Update an SB_Event
     * @apiName updateEvent
     * @apiHeader {String} token
     * @apiParam {String} label Event label
     * @apiParam {String} activityId Event activity id
     * @apiParam {String} owner Event owner
     * @apiParam {Number} startDate Event start date
     * @apiGroup SB_Event API
     * @apiSuccess {SB_Event} SB_Event updated
     */
    @Rule(address = UPDATE, method = Constants.POST, logged = true,
            mandatoryParams = {PARAM_LABEL, PARAM_ACTIVITY_ID, PARAM_OWNER, PARAM_START_DATE},
            scope = Rule.Param.BODY)
    private void updateEvent(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(eventDAO.updateEvent(new JsonObject(req.getBody()), req.getUser().get_id(), req.getLocale()).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Add an SB_Event.
     * @api {post} /api/1/sandbox/event/event/add Add an SB_Event
     * @apiName addEvent
     * @apiHeader {String} token
     * @apiParam {String} label Event label
     * @apiParam {String} activityId Event activity id
     * @apiParam {String} owner Event owner
     * @apiParam {Number} startDate Event start date
     * @apiGroup SB_Event API
     * @apiSuccess {SB_Event} SB_Event create
     */
    @Rule(address = ADD, method = Constants.POST, logged = true,
            mandatoryParams = {PARAM_LABEL, PARAM_ACTIVITY_ID, PARAM_OWNER, PARAM_START_DATE},
            scope = Rule.Param.BODY)
    private void addEvent(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(eventDAO.addEvent(new JsonObject(req.getBody()), req.getUser().get_id(), req.getLocale()).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription retrieve all events for one or  many owner
     * @api {post} /api/1/sandbox/event/event/list Get all SB_Event
     * @apiName getEventList
     * @apiGroup SB_Event API
     * @apiParam {Number} startDate start date
     * @apiParam {Number} endDate end date
     * @apiParam {Array} linkType Link type
     * @apiParam {String} activityId Activity Id
     * @apiParam {Array} owner Owner
     * @apiHeader {String} token
     * @apiSuccess {Array} list of SB_Event
     */
    @Rule(address = GET_LIST, method = Constants.POST, logged = true,
            mandatoryParams = {PARAM_START_DATE, PARAM_END_DATE, PARAM_ACTIVITY_ID, PARAM_OWNER_SANBOXID},
            scope = Rule.Param.BODY)
    private void getEventList(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            message.reply(eventDAO.getEventList(new JsonObject(req.getBody())).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
