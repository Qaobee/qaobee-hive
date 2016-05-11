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
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
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
@DeployableVerticle
public class SB_EffectiveVerticle extends AbstractGuiceVerticle { // NOSONAR
    /**
     * The constant GET.
     */
    public static final String GET = Module.VERSION + ".sandbox.effective.effective.get";
    /**
     * The constant GET_LIST.
     */
    public static final String GET_LIST = Module.VERSION + ".sandbox.effective.effective.getList";
    /**
     * The constant ADD_TO_USER.
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
    private static final Logger LOG = LoggerFactory.getLogger(SB_EffectiveVerticle.class);
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET, this::getEffectiveHandler)
                .registerHandler(GET_LIST, this::getEffectiveListHandler)
                .registerHandler(UPDATE, this::updateEffectiveHandler)
                .registerHandler(ADD, this::addEffectiveHandler);
    }

    /**
     * @api {post} /api/1/sandbox/effective/effective/add
     * @apiVersion 0.1.0
     * @apiName add
     * @apiGroup Effective API
     * @apiPermission all
     * @apiDescription add one effective
     * @apiParam {Effective} effective Mandatory The effective to add.
     * @apiSuccess {Effective}   effective    The effective added.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = ADD, method = Constants.POST, logged = true)
    private void addEffectiveHandler(Message<String> message) {// NOSONAR
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            final JsonObject json = new JsonObject(req.getBody());
            final String id = mongo.save(json, SB_Effective.class);
            json.putString("_id", id);
            message.reply(json.encode());
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @api {post} /api/1/sandbox/effective/effective/update
     * @apiVersion 0.1.0
     * @apiName update
     * @apiGroup Effective API
     * @apiPermission all
     * @apiDescription Update one effective
     * @apiParam {Effective} effective Mandatory The effective to update.
     * @apiSuccess {Effective}   effective    The effective updated.
     */
    @Rule(address = UPDATE, method = Constants.PUT, logged = true, mandatoryParams = {PARAM_ID}, scope = Rule.Param.BODY)
    private void updateEffectiveHandler(Message<String> message) {// NOSONAR
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        final JsonObject json = new JsonObject(req.getBody());
        final String id = mongo.update(json, SB_Effective.class);
        json.putString("_id", id);
        message.reply(json.encode());
    }

    /**
     * @api {get} /api/1/sandbox/effective/effective/getList
     * @apiVersion 0.1.0
     * @apiName getList
     * @apiGroup SB_Effective API
     * @apiPermission all
     * @apiHeader {String} token
     * @apiDescription get a list of effectives for one sandbox Config id
     * @apiParam {String} sandBoxCfgId Mandatory The sandBox config Id.
     * @apiParam {String} categoryCode Optional The category code of the effective.
     * @apiSuccess {List}   effectives            The list of effectives found.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET_LIST, method = Constants.GET, logged = true, mandatoryParams = {PARAM_SANDBOXCFG_ID}, scope = Rule.Param.REQUEST)
    private void getEffectiveListHandler(Message<String> message) {// NOSONAR
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            Map<String, List<String>> params = req.getParams();
            Map<String, Object> criterias = new HashMap<>();
            criterias.put(PARAM_SANDBOXCFG_ID, params.get(PARAM_SANDBOXCFG_ID).get(0));
            // category code
            String code = null;
            if (params.get(PARAM_CATEGORY_AGE_CODE) != null && !StringUtils.isBlank(params.get(PARAM_CATEGORY_AGE_CODE).get(0))) {
                code = params.get(PARAM_CATEGORY_AGE_CODE).get(0);
                criterias.put(PARAM_CATEGORY_AGE_CODE, code);
            }
            JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, SB_Effective.class);
            if (resultJson == null || resultJson.size() == 0) {
                throw new QaobeeException(ExceptionCodes.DATA_ERROR,
                        "No Effective found " + "for ( sandBoxCfgId : " + params.get(PARAM_SANDBOXCFG_ID).get(0) + " " + (code != null ? "and for category : " + code + ")" : ")"));
            }
            message.reply(resultJson.encode());
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @api {get} /api/1/sandbox/effective/effective/get
     * @apiVersion 0.1.0
     * @apiName get
     * @apiGroup SB_Effective API
     * @apiPermission all
     * @apiDescription Retrieve the effective by id
     * @apiHeader {String} token
     * @apiParam {String} _id Mandatory The effective Id.
     * @apiSuccess {Effective}   effective    The effective found.
     * @apiError DATA_ERROR Error on DB request
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = {PARAM_ID}, scope = Rule.Param.REQUEST)
    private void getEffectiveHandler(Message<String> message) {// NOSONAR
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            Map<String, List<String>> params = req.getParams();
            final JsonObject json = mongo.getById(params.get(PARAM_ID).get(0), SB_Effective.class);
            message.reply(json.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
