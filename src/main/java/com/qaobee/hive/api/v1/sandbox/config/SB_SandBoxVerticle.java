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

package com.qaobee.hive.api.v1.sandbox.config;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
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
import java.util.List;
import java.util.Map;

/**
 * The type Sand box cfg verticle.
 */
@DeployableVerticle(isWorker = true)
public class SB_SandBoxVerticle extends AbstractGuiceVerticle {

    /**
     * The constant GET.
     */
    public static final String GET = Module.VERSION + ".sandbox.config.sandbox.get";
    /**
     * The constant GET_BY_OWNER.
     */
    public static final String GET_BY_OWNER = Module.VERSION + ".sandbox.config.sandbox.getByOwner";
    
    /**
     * The constant GET_BY_OWNER.
     */
    public static final String GET_LIST_BY_OWNER = Module.VERSION + ".sandbox.config.sandbox.getListByOwner";
    
    /**
     * The constant PARAM_ID.
     */
    public static final String PARAM_ID = "_id";
    
    /**
     * The constant PARAM_ID.
     */
    public static final String PARAM_OWNER_ID = "owner";
    
    /**
     * The constant PARAM_ACTIVITY_ID.
     */
    public static final String PARAM_ACTIVITY_ID = "activityId";
    /**
     * The constant PARAM_SEASON_ID.
     */
    public static final String PARAM_SEASON_ID = "seasonId";

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
		 * @api {post} /api/v1/sandbox/config/sandbox/getByOwner
		 * @apiVersion 0.1.0
		 * @apiName getByOwner
		 * @apiGroup SandBox API
		 * @apiPermission all
		 *
		 * @apiDescription Retrieve the user's sandbox
		 *
		 * @apiParam {String} activityId Mandatory The sandBox activity.
		 * 
		 * @apiSuccess {sandBox}   sandBox    The sandBox updated.
		 *
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
        vertx.eventBus().registerHandler(GET_BY_OWNER, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
            	container.logger().info(GET_BY_OWNER+" - SandBox");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    utils.isUserLogged(req);
                    Map<String, List<String>> params = req.getParams();
                    utils.testMandatoryParams(params, PARAM_ACTIVITY_ID);

                    CriteriaBuilder cb = new CriteriaBuilder();

                    cb.add(PARAM_OWNER_ID, req.getUser().get_id());
                    cb.add(PARAM_ACTIVITY_ID, params.get(PARAM_ACTIVITY_ID).get(0));
                    
                    JsonArray resultJson = mongo.findByCriterias(cb.get(), null, null, -1, -1, SB_SandBox.class);
                    
                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No SandBox found for user id :" +req.getUser().get_id() +" ,and activityId : "+ params.get(PARAM_ACTIVITY_ID));
                    }
                    JsonObject json = resultJson.get(0);
                    container.logger().info("SandBox found : " + json.toString());
                    message.reply(json.encode());
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
        
        /**
		 * @api {post} /api/v1/sandbox/config/sandbox/getListByOwner
		 * @apiVersion 0.1.0
		 * @apiName getListByOwner
		 * @apiGroup SandBox API
		 * @apiPermission all
		 *
		 * @apiDescription Retrieve the user's sandbox
		 *
		 * @apiParam {String} activityId Mandatory The sandBox activity.
		 * 
		 * @apiSuccess {sandBox}   sandBox    The sandBox updated.
		 *
		 * @apiError HTTP_ERROR Bad request
		 * @apiError MONGO_ERROR Error on DB request
		 * @apiError INVALID_PARAMETER Parameters not found
		 */
        vertx.eventBus().registerHandler(GET_LIST_BY_OWNER, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
            	container.logger().info(GET_LIST_BY_OWNER+" - SandBox");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    utils.isUserLogged(req);
                    
                    CriteriaBuilder cb = new CriteriaBuilder();

                    cb.add(PARAM_OWNER_ID, req.getUser().get_id());
                    
                    JsonArray resultJson = mongo.findByCriterias(cb.get(), null, null, -1, -1, SB_SandBox.class);
                    
                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No SandBox found for user id :" +req.getUser().get_id());
                    }
                    
                    for (int i = 0; i < resultJson.size(); i++) {
                    	JsonObject json = resultJson.get(i);
                        container.logger().info("SandBox found : " + json.toString());
					}
                    
                    message.reply(resultJson.encode());
                    
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