/*
 * __________________
 *   Qaobee
 * __________________
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
import com.qaobee.hive.business.model.commons.settings.ActivityCfg;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

/**
 * The type Activity cfg verticle.
 */
@DeployableVerticle(isWorker = true)
public class ActivityCfgVerticle extends AbstractGuiceVerticle {
    /**
     * The constant GET.
     */
    public static final String GET = Module.VERSION + ".commons.settings.activitycfg.get";
    /**
     * The constant PARAM_ACTIVITY_ID.
     */
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * The Mongo.
     */
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
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");

        /**
         * @apiDescription Fetch ActivityCfg
         * @api {post} /api/1/commons/settings/activitycfg/get Get ActivityCfg
         * @apiName getById
         * @apiGroup ActivityCfg API
         * @apiParam {String} activityId Activity Id
         * @apiError HTTP_ERROR wrong request method
         * @apiError NOT_LOGGED invalid token
         * @apiError INVALID_PARAMETER wrong parameters
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    utils.isUserLogged(req);

                    utils.testMandatoryParams(req.getBody(), PARAM_ACTIVITY_ID);
                    JsonObject params = new JsonObject(req.getBody());
                    JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("activityId", params.getString(PARAM_ACTIVITY_ID)).get(), null, null, -1, -1, ActivityCfg.class);
                    if(res.size() ==0) {
                        QaobeeException e = new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No result for " + params.getString(PARAM_ACTIVITY_ID));
                        container.logger().error(e.getMessage(), e);
                        utils.sendError(message, e);
                    } else {
                        message.reply(res.get(0).toString());
                    }
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });
    }
}
