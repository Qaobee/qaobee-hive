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

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.referencial.Team;
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
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

/**
 * The type Team verticle.
 *
 * @author cke
 */
@DeployableVerticle(isWorker = true)
public class TeamVerticle extends AbstractGuiceVerticle {
    /**
     * The Constant ADD_TO_USER.
     */
    public static final String ADD = Module.VERSION + ".commons.referencial.team.add";
    // Declaration des variables finals
    /**
     * The Constant GET.
     */
    public static final String GET = Module.VERSION + ".commons.referencial.team.get";
    /**
     * The Constant UPDATE.
     */
    public static final String UPDATE = Module.VERSION + ".commons.referencial.team.update";
    /**
     * Id of the team
     */
    public static final String PARAM_ID = "_id";

	/* List of parameters */
    /**
     * Label of the team
     */
    public static final String PARAM_LABEL = "label";
    /**
     * activity of the team
     */
    public static final String PARAM_ACTIVITY = "activity";
    /**
     * Team sandboxId
     */
    public static final String PARAM_SANBOXID = "sandboxId";
    /**
     * Team effectiveId
     */
    public static final String PARAM_EFFECTIVEID = "effectiveId";
    private static final Logger LOG = LoggerFactory.getLogger(TeamVerticle.class);
    /* Injections */
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;

    @Override
    @VerticleHandler({
            @Rule(address = ADD, method = Constantes.POST, logged = true, mandatoryParams = {PARAM_LABEL, PARAM_ACTIVITY, PARAM_SANBOXID, PARAM_EFFECTIVEID}, scope = Rule.Param.BODY),
            @Rule(address = GET, method = Constantes.GET, logged = true, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST),
            @Rule(address = UPDATE, method = Constantes.POST, logged = true, mandatoryParams = {PARAM_ID, PARAM_LABEL, PARAM_ACTIVITY, PARAM_SANBOXID, PARAM_EFFECTIVEID}, scope = Rule.Param.BODY),
    })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @api {post} /api/1/commons/referencial/team/add Add team
         * @apiVersion 0.1.0
         * @apiName add
         * @apiGroup Team API
         * @apiPermission all
         *
         * @apiDescription Add team to the collection team in referencial module
         *
         * @apiParam {String} label Mandatory The Team label.
         * @apiParam {Activity} activity Mandatory The Team activity.
         * @apiParam {Country} country Mandatory The Team country
         *
         * @apiSuccess {Team}   team            The Team added with the id.
         *
         * @apiError DATA_ERROR Error on DB request
         */
        final Handler<Message<String>> add = new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    final JsonObject params = new JsonObject(req.getBody());
                    // Insert a team
                    final String id = mongo.save(params, Team.class);
                    params.putString("_id", id);
                    message.reply(params.encode());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.DATA_ERROR, e.getMessage());
                }
            }
        };

        /**
         * @api {get} /api/1/commons/referencial/team/get Read data of a Team
         * @apiVersion 0.1.0
         * @apiName get
         * @apiGroup Team API
         * @apiPermission all
         *
         * @apiDescription get a team to the collection team in referencial module
         *
         * @apiParam {String} id The Team-ID.
         *
         * @apiSuccess {Team}   team            The Team found.
         *
         * @apiError DATA_ERROR Error on DB request
         */
        final Handler<Message<String>> get = new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    final JsonObject json = mongo.getById(req.getParams().get(PARAM_ID).get(0), Team.class);
                    message.reply(json.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        };

        /**
         * @api {post} /api/1/commons/referencial/team/update
         * @apiVersion 0.1.0
         * @apiName update
         * @apiGroup Team API
         * @apiPermission all
         *
         * @apiDescription Update a Team to the collection Team in referencial module
         *
         * @apiParam {String} _id Mandatory The Team ID.
         * @apiParam {String} label Mandatory The Team label.
         * @apiParam {Activity} activity Mandatory The Team activity.
         * @apiParam {Country} country Mandatory The Team country
         *
         * @apiSuccess {Team}   team            The Team updated.
         *
         * @apiError DATA_ERROR Error on DB request
         */
        final Handler<Message<String>> update = new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    final JsonObject params = new JsonObject(req.getBody());
                    // Update a team
                    mongo.save(params, Team.class);
                    message.reply(params.encode());
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.DATA_ERROR, e.getMessage());
                }
            }
        };

		/*
         * Handlers registration
		 */
        vertx.eventBus().registerHandler(ADD, add);
        vertx.eventBus().registerHandler(GET, get);
        vertx.eventBus().registerHandler(UPDATE, update);
    }

}
