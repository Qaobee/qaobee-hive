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

package com.qaobee.hive.api.v1.sandbox.effective;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.sandbox.effective.SB_Effective;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Effective verticle.
 */
@DeployableVerticle(isWorker = true)
public class SB_EffectiveVerticle extends AbstractGuiceVerticle {

    /**
     * The constant GET.
     */
    public static final String GET = Module.VERSION + ".sandbox.effective.effective.get";

    /**
     * The constant GET_LIST.
     */
    public static final String GET_LIST = Module.VERSION + ".sandbox.effective.effective.getList";

    /**
     * The constant ADD.
     */
    public static final String ADD = Module.VERSION + ".sandbox.effective.effective.add";

    /**
     * The constant update.
     */
    public static final String UPDATE = Module.VERSION + ".sandbox.effective.effective.update";


    /**
     * The constant PARAM_ID.
     */
    public static final String PARAM_ID = "_id";

    /**
     * Sandbox config id
     */
    public static final String PARAM_SANDBOXCFG_ID = "sandBoxCfgId";

    /**
     * Category Age Code
     */
    public static final String PARAM_CATEGORY_AGE_CODE = "categoryAge.code";

    /**
     * Role of member
     */
    public static final String PARAM_ROLE_MEMBER = "members.role.code";


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
         * @api {post} /api/v1/sandbox/effective/effective/get
         * @apiVersion 0.1.0
         * @apiName get
         * @apiGroup Effective API
         * @apiPermission all
         *
         * @apiDescription Retrieve the effective by id
         *
         * @apiParam {String} id Mandatory The effective Id.
         *
         * @apiSuccess {Effective}   effective    The effective found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                container.logger().info(GET + " - Effective");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    Map<String, List<String>> params = req.getParams();
                    utils.testMandatoryParams(params, PARAM_ID);
                    utils.isUserLogged(req);

                    final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), SB_Effective.class);
                    container.logger().info("Effective found : " + json.toString());

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
                }
            }
        });

        /**
         * @api {get} /api/v1/commons/effective/effective/getList 
         * @apiVersion 0.1.0
         * @apiName getList
         * @apiGroup Effective API
         * @apiPermission all
         *
         * @apiDescription get a list of effectives for one sandbox Config id
         *
         * @apiParam {String} sandBoxCfgId Mandatory The sandBox config Id.
         * @apiParam {String} categoryCode Optional The category code of the effective.
         *
         * @apiSuccess {List}   effectives            The list of effectives found.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_LIST, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                container.logger().info(GET_LIST + " - Effective");
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    // Tests on method and parameters
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    Map<String, List<String>> params = req.getParams();
                    utils.testMandatoryParams(params, PARAM_SANDBOXCFG_ID);
                    utils.isUserLogged(req);

                    Map<String, Object> criterias = new HashMap<String, Object>();
                    criterias.put(PARAM_SANDBOXCFG_ID, params.get(PARAM_SANDBOXCFG_ID).get(0));

                    // category code
                    String code = null;
                    if (params.get(PARAM_CATEGORY_AGE_CODE) != null && !StringUtils.isBlank(params.get(PARAM_CATEGORY_AGE_CODE).get(0))) {
                        code = params.get(PARAM_CATEGORY_AGE_CODE).get(0);
                        criterias.put(PARAM_CATEGORY_AGE_CODE, code);
                    }

                    JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, SB_Effective.class);

                    if (resultJson == null || resultJson.size() == 0) {
                        throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No Effective found "
                                + "for ( sandBoxCfgId : " + params.get(PARAM_SANDBOXCFG_ID).get(0) + " " + (code != null ? "and for category : " + code + ")" : ")"));
                    }

                    message.reply(resultJson.encode());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @api {post} /api/v1/sandbox/effective/effective/update
         * @apiVersion 0.1.0
         * @apiName update
         * @apiGroup Effective API
         * @apiPermission all
         *
         * @apiDescription Update one effective
         *
         * @apiParam {Effective} effective Mandatory The effective to update.
         *
         * @apiSuccess {Effective}   effective    The effective updated.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(UPDATE, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                container.logger().info(UPDATE + " - Effective");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.PUT, req.getMethod());
                    utils.isUserLogged(req);
                    final JsonObject json = new JsonObject(req.getBody());

                    final String id = mongo.update(json, SB_Effective.class);
                    json.putString("_id", id);

                    message.reply(json.encode());

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final EncodeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @api {post} /api/v1/sandbox/effective/effective/add
         * @apiVersion 0.1.0
         * @apiName add
         * @apiGroup Effective API
         * @apiPermission all
         *
         * @apiDescription Update one effective
         *
         * @apiParam {Effective} effective Mandatory The effective to add.
         *
         * @apiSuccess {Effective}   effective    The effective added.
         *
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(ADD, new Handler<Message<String>>() {

            @Override
            public void handle(final Message<String> message) {
                container.logger().info(ADD + " - Effective");
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.PUT, req.getMethod());
                    final JsonObject json = new JsonObject(req.getBody());
                    utils.isUserLogged(req);

                    final String id = mongo.save(json, SB_Effective.class);
                    json.putString("_id", id);

                    message.reply(json.encode());

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final EncodeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
    }
}
