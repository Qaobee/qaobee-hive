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

package com.qaobee.hive.api.v1.sandbox.effective;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.dao.EffectiveDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * The type Effective verticle.
 */
@DeployableVerticle
public class SB_EffectiveVerticle extends AbstractGuiceVerticle {// NOSONAR
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
    public static final String ADD_EFFECTIVE = Module.VERSION + ".sandbox.effective.effective.add";
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
    public static final String PARAM_SANDBOX_ID = "sandboxId";
    /**
     * Category Age Code
     */
    public static final String PARAM_CATEGORY_AGE_CODE = "categoryAge.code";
    @Inject
    private EffectiveDAO effectiveDAO;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this)
                .add(GET, this::getEffective)
                .add(GET_LIST, this::getEffectiveList)
                .add(UPDATE, this::updateEffective)
                .add(ADD_EFFECTIVE, this::addEffective)
                .register(startFuture);
    }

    /**
     * @api {post} /api/1/sandbox/effective/effective/add
     * @apiVersion 0.1.0
     * @apiName addEffective
     * @apiGroup Effective API
     * @apiDescription add one effective
     * @apiParam {Object} effective Mandatory The effective to add.
     * @apiSuccess {Object}   effective    The effective added.
     * @apiHeader {String} token
     */
    @Rule(address = ADD_EFFECTIVE, method = Constants.POST, logged = true)
    private void addEffective(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, effectiveDAO.add(new JsonObject(req.getBody())));
    }

    /**
     * @api {post} /api/1/sandbox/effective/effective/update
     * @apiVersion 0.1.0
     * @apiName updateEffective
     * @apiGroup Effective API
     * @apiHeader {String} token
     * @apiDescription Update one effective
     * @apiParam {Object} effective Mandatory The effective to update.
     * @apiSuccess {Object}   effective    The effective updated.
     */
    @Rule(address = UPDATE, method = Constants.PUT, logged = true, mandatoryParams = PARAM_ID,
            scope = Rule.Param.BODY)
    private void updateEffective(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, effectiveDAO.update(new JsonObject(req.getBody())));
    }

    /**
     * @api {get} /api/1/sandbox/effective/effective/getList
     * @apiVersion 0.1.0
     * @apiName getEffectiveList
     * @apiGroup SB_Effective API
     * @apiHeader {String} token
     * @apiDescription get a list of effectives for one sandbox Config id
     * @apiParam {String} sandBoxCfgId Mandatory The sandBox config Id.
     * @apiParam {String} categoryCode Optional The category code of the effective.
     * @apiSuccess {Array}   effectives            The list of effectives found.
     */
    @Rule(address = GET_LIST, method = Constants.GET, logged = true, mandatoryParams = PARAM_SANDBOX_ID,
            scope = Rule.Param.REQUEST)
    private void getEffectiveList(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        Map<String, List<String>> params = req.getParams();
        String categoryAgeCode = null;
        if (params.get(PARAM_CATEGORY_AGE_CODE) != null && !StringUtils.isBlank(params.get(PARAM_CATEGORY_AGE_CODE).get(0))) {
            categoryAgeCode = params.get(PARAM_CATEGORY_AGE_CODE).get(0);
        }
        replyJsonArray(message, effectiveDAO.getEffectiveList(params.get(PARAM_SANDBOX_ID).get(0), categoryAgeCode));
    }

    /**
     * @api {get} /api/1/sandbox/effective/effective/get
     * @apiVersion 0.1.0
     * @apiName getEffective
     * @apiGroup SB_Effective API
     * @apiDescription Retrieve the effective by id
     * @apiHeader {String} token
     * @apiParam {String} _id Mandatory The effective Id.
     * @apiSuccess {Object}   effective    The effective found.
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = PARAM_ID,
            scope = Rule.Param.REQUEST)
    private void getEffective(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, effectiveDAO.getEffective(req.getParams().get(PARAM_ID).get(0)));
    }
}