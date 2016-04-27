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
package com.qaobee.hive.api.v1.sandbox.stats;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
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
import java.util.Collections;
import java.util.List;

/**
 * @author cke
 */
@DeployableVerticle
public class SB_CollecteVerticle extends AbstractGuiceVerticle { // NOSONAR
    /**
     * Collection name
     */
    public static final String COLLECTION_NAME = "SB_Collecte";
    /**
     * Handler for get a list of collecte document
     */
    public static final String GET_LIST = Module.VERSION + ".sandbox.stats.collecte.list";
    /**
     * Handler for get individual collecte document
     */
    public static final String GET = Module.VERSION + ".sandbox.stats.collecte.get";
    /**
     * Handler for adding a collecte document
     */
    public static final String ADD = Module.VERSION + ".sandbox.stats.collecte.add";
    /**
     * Handler for update a collecte document
     */
    public static final String UPDATE = Module.VERSION + ".sandbox.stats.collecte.update";
    /**
     * Collecte ID
     */
    public static final String PARAM_ID = "_id";
    /**
     * Collecte status
     */
    public static final String PARAM_STATUS = "status";
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
    private static final Logger LOG = LoggerFactory.getLogger(SB_CollecteVerticle.class);
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
    @VerticleHandler({
            @Rule(address = GET_LIST, method = Constantes.POST, logged = true,
                    mandatoryParams = {PARAM_START_DATE, PARAM_END_DATE, PARAM_SANDBOX_ID}, scope = Rule.Param.BODY),
            @Rule(address = ADD, method = Constantes.POST, logged = true, mandatoryParams = {PARAM_EVENT, PARAM_PLAYERS}, scope = Rule.Param.BODY),
            @Rule(address = UPDATE, method = Constantes.POST, logged = true, mandatoryParams = {PARAM_EVENT, PARAM_PLAYERS}, scope = Rule.Param.BODY),
            @Rule(address = GET, method = Constantes.GET, logged = true, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST)
    })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @apiDescription retrieve all documents collecte
         * @api {post} /api/1/sandbox/stats/collecte/list Get all SB_Collecte
         * @apiName getListOwner
         * @apiGroup SB_Collecet API
         * @apiParam {String} startDate start date
         * @apiParam {String} endDate end date
         * @apiParam {String} sandBoxId
         * @apiHeader {String} token
         * @apiSuccess {Array} list of SB_Collecte
         */
        vertx.eventBus().registerHandler(GET_LIST, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    JsonObject params = new JsonObject(req.getBody());
                    DBObject match;
                    BasicDBObject dbObjectParent;
                    dbObjectParent = new BasicDBObject();
                    // Collecte sandboxId
                    dbObjectParent.put("eventRef.owner.sandboxId", params.getString(PARAM_SANDBOX_ID));
                    if (params.getString(PARAM_EVENT_ID) != null && !"".equals(params.getString(PARAM_EVENT_ID).trim())) {
                        dbObjectParent.put("eventRef._id", params.getString(PARAM_EVENT_ID));
                    }
                    if (params.getString(PARAM_EFFECTIVE_ID) != null && !"".equals(params.getString(PARAM_EFFECTIVE_ID).trim())) {
                        dbObjectParent.put("eventRef.owner.effectiveId", params.getString(PARAM_EFFECTIVE_ID));
                    }
                    if (params.getString(PARAM_TEAM_ID) != null && !"".equals(params.getString(PARAM_TEAM_ID).trim())) {
                        dbObjectParent.put("eventRef.owner.teamId", params.getString(PARAM_TEAM_ID));
                    }
                    DBObject o = new BasicDBObject();
                    o.put("$gte", params.getLong(PARAM_START_DATE));
                    o.put("$lt", params.getLong(PARAM_END_DATE));
                    dbObjectParent.put("startDate", o);
                    match = new BasicDBObject("$match", dbObjectParent);
                    List<DBObject> pipelineAggregation;
                    pipelineAggregation = Collections.singletonList(match);
                    final JsonArray resultJSon = mongo.aggregate("_id", pipelineAggregation, COLLECTION_NAME);
                    message.reply(resultJSon.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @apiDescription Add an SB_Collecte.
         * @api {post} /api/1/sandbox/stats/collecte/add Add an SB_Collecte
         * @apiName add
         * @apiGroup SB_Collecet API
         * @apiSuccess {SB_Collecte} SB_Collecte create
         * @apiError HTTP_ERROR Bad request
         * @apiError DATA_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(ADD, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    JsonObject object = new JsonObject(req.getBody());
                    final String id = mongo.save(object, COLLECTION_NAME);
                    object.putString("_id", id);
                    message.reply(object.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @apiDescription Update an collecte document.
         * @api {post} /api/1/sandbox/stats/collecte/update Update an SB_Collecte
         * @apiName update
         * @apiGroup SB_Collecet API
         * @apiSuccess {SB_Collecte} SB_Collecte updated
         * @apiError HTTP_ERROR Bad request
         * @apiError DATA_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(UPDATE, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                JsonObject object = new JsonObject(req.getBody());
                mongo.update(object, COLLECTION_NAME);
                message.reply(object.encode());
            }
        });

        /**
         * @apiDescription Retrieve Collecte by this Id
         * @api {get} /api/1/sandbox/stats/collecte/get Get event by Id
         * @apiName getEventHandler
         * @apiGroup Collecte API
         * @apiParam {String} id
         * @apiSuccess {Object} collecte
         * @apiError DATA_ERROR Error during request to Mongo
         * @apiError INVALID_PARAMETER Invalid Parameters
         * @apiError HTTP_ERROR Bad Request
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    message.reply(mongo.getById(req.getParams().get(PARAM_ID).get(0), COLLECTION_NAME).encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
    }
}
