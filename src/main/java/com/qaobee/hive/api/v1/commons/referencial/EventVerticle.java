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
package com.qaobee.hive.api.v1.commons.referencial;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.referencial.event.Event;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * The type Event verticle.
 *
 * @author cke
 */
@DeployableVerticle
public class EventVerticle extends AbstractGuiceVerticle {

    /**
     * Handler to get a set of events
     */
    public static final String GET_LIST = Module.VERSION + ".commons.referencial.event.list";
    /**
     * Handler to add a event.
     */
    public static final String ADD = Module.VERSION + ".commons.referencial.event.add";
    /**
     * Handler to get a particular event from its ID.
     */
    public static final String GET = Module.VERSION + ".commons.referencial.event.get";
    /**
     * Handler to update a event.
     */
    public static final String UPDATE = Module.VERSION + ".commons.referencial.event.update";

	/* List of parameters */
    /**
     * Event Group ID
     */
    public static final String PARAM_LIST_ID = "listId";
    /**
     * Event Group ID
     */
    public static final String PARAM_CATEGORY = "categoryAge";
    /**
     * Event ID
     */
    public static final String PARAM_ID = "id";
    /**
     * Event activity
     */
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * Event Season code
     */
    public static final String PARAM_SEASON_CODE = "seasonCode";
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
     * link Id
     */
    public static final String PARAM_LINK_ID = "link.linkId";
    /**
     * link Type
     */
    public static final String PARAM_LINK_TYPE = "link.type";
    /**
     * Event Owner
     */
    public static final String PARAM_OWNER = "owner";
    /**
     * participants
     */
    public static final String PARAM_PARTICIPANTS_CLAUSE = "participantClause";

    /**
     * list clause group by
     */
    public static final String PARAM_LIST_GROUPBY = "listFieldsGroupBy";
    /**
     * list clause SORT by
     */
    public static final String PARAM_LIST_SORTBY = "listFieldsSortBy";
    /**
     * limit number of result
     */
    public static final String PARAM_LIMIT_RESULT = "limitResult";

    /**
     * The Mongo.
     */
    @Inject
    private MongoDB mongo;
    /**
     * The Utils.
     */
    @Inject
    protected Utils utils;

    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");

        /**
         * @apiDescription retrieve all events match to parameters filter
         * @api {post} /rest/prive/transversal/event/list resthandler.prive.transversal.event.list
         * @apiName getListEventHandler
         * @apiGroup EventVerticle
         * @apiSuccess {Array} list of events
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        final Handler<Message<String>> getListEventHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {

					/*
                     * *** Params section ***
					 */
                    // Check param mandatory
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    utils.isUserLogged(req);
                    JsonObject params = new JsonObject(req.getBody());
                    utils.testMandatoryParams(params.toMap(), PARAM_START_DATE, PARAM_END_DATE, PARAM_LINK_TYPE, PARAM_ACTIVITY_ID, PARAM_OWNER);

					/*
                     * *** Aggregat section ***
					 */
                    DBObject match, sort, limit;
                    BasicDBObject dbObjectParent, dbObjectChild;

					/* *** $MACTH section *** */
                    dbObjectParent = new BasicDBObject();

                    // Event Activity
                    dbObjectParent.put(PARAM_ACTIVITY_ID, params.getString(PARAM_ACTIVITY_ID));

                    // Event Type
                    dbObjectParent.put(PARAM_LINK_TYPE, params.getString(PARAM_LINK_TYPE));

                    // Owner
                    dbObjectChild = new BasicDBObject("$in", params.getArray(PARAM_OWNER).toArray());
                    dbObjectParent.put("owner", dbObjectChild);

                    // start date
                    DBObject o = new BasicDBObject();
                    o.put("$gte", params.getLong(PARAM_START_DATE));
                    dbObjectParent.put("startDate", o);

                    // end date
                    o = new BasicDBObject();
                    o.put("$lt", params.getLong(PARAM_END_DATE));
                    dbObjectParent.put("endDate", o);

                    // seasonCode
                    if (params.containsField(PARAM_SEASON_CODE)) {
                        dbObjectParent.put(PARAM_SEASON_CODE, params.getString(PARAM_SEASON_CODE));
                    }

                    // participants
                    if (params.containsField(PARAM_PARTICIPANTS_CLAUSE)) {
                        dbObjectParent.put("participants", params.getString(PARAM_PARTICIPANTS_CLAUSE));
                    }

                    match = new BasicDBObject("$match", dbObjectParent);

					/* *** $SORT section *** */
                    dbObjectParent = new BasicDBObject();
                    if (params.containsField(PARAM_LIST_SORTBY)) {
                        for (Object item : params.getArray(PARAM_LIST_SORTBY)) {
                            JsonObject field = (JsonObject) item;
                            dbObjectParent.put(field.getString("fieldName"), field.getInteger("sortOrder"));
                        }
                    } else {
                        dbObjectParent.put("_id", 1);
                    }
                    sort = new BasicDBObject("$sort", dbObjectParent);

					/* *** $limit section *** */
                    List<DBObject> pipelineAggregation;
                    if (params.containsField(PARAM_LIMIT_RESULT)) {
                        int limitNumber = params.getInteger(PARAM_LIMIT_RESULT);
                        limit = new BasicDBObject("$limit", limitNumber);
                        pipelineAggregation = Arrays.asList(match, sort, limit);
                    } else {
                        pipelineAggregation = Arrays.asList(match, sort);
                    }

                    container.logger().info("getListEventHandler : " + pipelineAggregation.toString());

                    final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, Event.class);

                    container.logger().info(resultJSon.encodePrettily());
                    message.reply(resultJSon.encode());

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
         * @apiDescription Add a event.
         * @api {post} /rest/prive/transversal/event/add resthandler.prive.transversal.event.add
         * @apiName addEvent
         * @apiGroup EventVerticle
         * @apiSuccess {Event} Event create
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        final Handler<Message<String>> addEvent = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    utils.isUserLogged(req);
                    JsonObject params = new JsonObject(req.getBody());
                    utils.testMandatoryParams(params.toMap(), PARAM_LABEL, PARAM_ACTIVITY_ID, PARAM_CATEGORY, PARAM_SEASON_CODE, PARAM_OWNER, PARAM_START_DATE);
                    JsonObject event = params.getObject("event");

                    final String id = mongo.save(event, Event.class);
                    event.putString("_id", id);

					/* return */
                    message.reply(event.encode());

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (EncodeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
                }
            }
        };

        /**
         * @apiDescription Retrieve Event by this Id
         * @api {get} /rest/prive/transversal/event/get resthandler.prive.transversal.event.get
         * @apiName getEventHandler
         * @apiGroup EventVerticle
         * @apiParam {String} id
         * @apiSuccess {Object} event com.qaobee.swarn.business.model.tranversal.event.event;
         * @apiError MONGO_ERROR Error during request to Mongo
         * @apiError INVALID_PARAMETER Invalid Parameters
         * @apiError HTTP_ERROR Bad Request
         */
        final Handler<Message<String>> getEventHandler = new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    utils.isUserLogged(req);
                    utils.testMandatoryParams(req.getParams(), PARAM_ID);

                    message.reply(mongo.getById(req.getParams().get(PARAM_ID).get(0), Event.class).encode());
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

		/*
		 * Handlers declaration.
		 */
        vertx.eventBus().registerHandler(GET_LIST, getListEventHandler);
        vertx.eventBus().registerHandler(GET, getEventHandler);
        vertx.eventBus().registerHandler(ADD, addEvent);

    }
}
