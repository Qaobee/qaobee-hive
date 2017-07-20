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

package com.qaobee.hive.api.v1.sandbox.event;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.services.EventService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Event Route.
 *
 * @author cke
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/sandbox/event/event")
public class SB_EventRoute extends AbstractRoute { // NOSONAR
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
    @Inject
    private EventService eventService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/add", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_LABEL, PARAM_ACTIVITY_ID, PARAM_OWNER, PARAM_START_DATE),
                this::addEvent);

        addRoute(router, "/list", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_START_DATE, PARAM_END_DATE, PARAM_ACTIVITY_ID, PARAM_OWNER_SANBOXID),
                this::getEventList);

        addRoute(router, "/update", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_LABEL, PARAM_ACTIVITY_ID, PARAM_OWNER, PARAM_START_DATE),
                this::updateEvent);

        addRoute(router, "/get", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ID),
                this::getEvent);

        return router;
    }

    /**
     * @apiDescription Retrieve Event by this Id
     * @api {get} /api/1/sandbox/event/event/get Get event by Id
     * @apiName getEvent
     * @apiGroup Event API
     * @apiHeader {String} token
     * @apiParam {String} id
     * @apiSuccess {Object} event event;
     */
    private void getEvent(RoutingContext context) {
        eventService.getEvent(context.request().getParam(PARAM_ID), handleResponse(context));
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
     * @apiSuccess {Object} event updated event
     */
    private void updateEvent(RoutingContext context) {
        eventService.updateEvent(context.getBodyAsJson(), context.user().principal().getString("_id"), getLocale(context), handleResponse(context));
    }

    /**
     * @apiDescription Add an event.
     * @api {post} /api/1/sandbox/event/event/add Add an SB_Event
     * @apiName addEvent
     * @apiHeader {String} token
     * @apiParam {String} label Event label
     * @apiParam {String} activityId Event activity id
     * @apiParam {String} owner Event owner
     * @apiParam {Number} startDate Event start date
     * @apiGroup SB_Event API
     * @apiSuccess {Object} event created event
     */
    private void addEvent(RoutingContext context) {
        eventService.addEvent(context.getBodyAsJson(), context.user().principal().getString("_id"), getLocale(context), handleResponse(context));
    }

    /**
     * @apiDescription retrieve all events for one or  many owner
     * @api {post} /api/1/sandbox/event/event/list Get all events
     * @apiName getEventList
     * @apiGroup SB_Event API
     * @apiParam {Number} startDate start date
     * @apiParam {Number} endDate end date
     * @apiParam {Array} linkType Link type
     * @apiParam {String} activityId Activity Id
     * @apiParam {Array} owner Owner
     * @apiHeader {String} token
     * @apiSuccess {Array} list of events
     */
    private void getEventList(RoutingContext context) {
        eventService.getEventList(context.getBodyAsJson(), handleResponseArray(context));
    }
}
