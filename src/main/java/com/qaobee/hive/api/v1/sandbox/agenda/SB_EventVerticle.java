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
package com.qaobee.hive.api.v1.sandbox.agenda;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.sandbox.agenda.SB_Event;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
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
public class SB_EventVerticle extends AbstractGuiceVerticle {
    public static final String GET_LIST = Module.VERSION + ".sandbox.agenda.event.list";
    public static final String ADD = Module.VERSION + ".sandbox.agenda.event.add";
    public static final String GET = Module.VERSION + ".sandbox.agenda.event.get";
    public static final String UPDATE = Module.VERSION + ".sandbox.agenda.event.update";
    /**
     * Event Group ID
     */
    public static final String PARAM_LIST_ID = "listId";

	/* List of parameters */
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
     * link id
     */
    public static final String PARAM_LINK_ID = "id";
    /**
     * link Type
     */
    public static final String PARAM_LINK_TYPE = "type";
    /**
     * The constant PARAM_LINK.
     */
    public static final String PARAM_LINK = "link";
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
    private static final Logger LOG = LoggerFactory.getLogger(SB_EventVerticle.class);
    /**
     * The Utils.
     */
    @Inject
    protected Utils utils;
    /**
     * The Mongo.
     */
    @Inject
    private MongoDB mongo;

    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @apiDescription retrieve all events for one or  many owner
         * @api {post} /api/1/sandbox/agenda/event/list Get all SB_Event
         * @apiName getListOwner
         * @apiGroup SB_Event API
         * @apiParam {String} startDate start date
         * @apiParam {String} endDate end date
         * @apiParam {Array} linkType Link type
         * @apiParam {String} activityId Activity Id
         * @apiParam {Array} owner Owner
         * @apiHeader {String} token
         * @apiSuccess {Array} list of SB_Event
         * @apiError HTTP_ERROR Bad request
         * @apiError DATA_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_LIST, new Handler<Message<String>>() {
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
                    utils.testMandatoryParams(params.toMap(), PARAM_START_DATE, PARAM_END_DATE, PARAM_ACTIVITY_ID, PARAM_OWNER_SANBOXID);

					/*
					 * *** Aggregat section ***
					 */
                    DBObject match, sort, limit;
                    BasicDBObject dbObjectParent, dbObjectChild;

					/* *** $MACTH section *** */
                    dbObjectParent = new BasicDBObject();

                    // Event Activity
                    dbObjectParent.put(PARAM_ACTIVITY_ID, params.getString(PARAM_ACTIVITY_ID));

                    // Event sandboxId
                    dbObjectParent.put("owner.sandboxId", params.getString(PARAM_OWNER_SANBOXID));

                    if (params.getString(PARAM_OWNER_EFFECTIVEID) != null && !"".equals(params.getString(PARAM_OWNER_EFFECTIVEID).trim())) {
                        dbObjectParent.put("owner.effectiveId", params.getString(PARAM_OWNER_EFFECTIVEID));
                    }

                    if (params.getString(PARAM_OWNER_TEAMID) != null && !"".equals(params.getString(PARAM_OWNER_TEAMID).trim())) {
                        dbObjectParent.put("owner.teamId", params.getString(PARAM_OWNER_TEAMID));
                    }

                    DBObject o = new BasicDBObject();
                    o.put("$gte", params.getLong(PARAM_START_DATE));
                    o.put("$lt", params.getLong(PARAM_END_DATE));
                    dbObjectParent.put("startDate", o);

                    // Link.type
                    if (params.containsField(PARAM_LINK_TYPE)) {
                        dbObjectChild = new BasicDBObject("$in", params.getArray(PARAM_LINK_TYPE).toArray());
                        dbObjectParent.put("link.type", dbObjectChild);
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

                    LOG.debug("getList : " + pipelineAggregation.toString());

                    final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, SB_Event.class);

                    LOG.debug(resultJSon.encodePrettily());
                    message.reply(resultJSon.encode());

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
         * @apiDescription Add an SB_Event.
         * @api {post} /api/1/sandbox/agenda/event/add Add an SB_Event
         * @apiName add
         * @apiGroup SB_Event API
         * @apiSuccess {SB_Event} SB_Event create
         * @apiError HTTP_ERROR Bad request
         * @apiError DATA_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(ADD, new Handler<Message<String>>() {
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
                    JsonObject event = new JsonObject(req.getBody());
                    utils.testMandatoryParams(event.toMap(), PARAM_LABEL, PARAM_ACTIVITY_ID, PARAM_OWNER, PARAM_START_DATE);

                    final String id = mongo.save(event, SB_Event.class);
                    event.putString("_id", id);

					/* return */
                    message.reply(event.encode());

                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (EncodeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Update an event.
         * @api {post} /api/1/sandbox/agenda/event/update Update an SB_Event
         * @apiName update
         * @apiGroup SB_Event API
         * @apiSuccess {SB_Event} SB_Event updated
         * @apiError HTTP_ERROR Bad request
         * @apiError DATA_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(UPDATE, new Handler<Message<String>>() {
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
                    JsonObject event = new JsonObject(req.getBody());
                    utils.testMandatoryParams(event.toMap(), PARAM_LABEL, PARAM_ACTIVITY_ID, PARAM_OWNER, PARAM_START_DATE);

                    mongo.save(event, SB_Event.class);

                    LOG.debug("SB_Event updated : " + event.toString());

					/* return */
                    message.reply(event.encode());

                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (EncodeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Retrieve Event by this Id
         * @api {get} /api/1/sandbox/agenda/event/get Get event by Id
         * @apiName getEventHandler
         * @apiGroup Event API
         * @apiParam {String} id
         * @apiSuccess {Object} event com.qaobee.swarn.business.model.tranversal.event.event;
         * @apiError DATA_ERROR Error during request to Mongo
         * @apiError INVALID_PARAMETER Invalid Parameters
         * @apiError HTTP_ERROR Bad Request
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    utils.isUserLogged(req);
                    utils.testMandatoryParams(req.getParams(), PARAM_ID);

                    message.reply(mongo.getById(req.getParams().get(PARAM_ID).get(0), SB_Event.class).encode());
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });
    }
}
