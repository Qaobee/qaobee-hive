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

package com.qaobee.hive.dao.impl;

import com.qaobee.hive.dao.EffectiveDAO;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Effective dao.
 */
public class EffectiveDAOImpl implements EffectiveDAO {
    private static final String PARAM_SANDBOX_ID = "sandboxId";
    private static final String PARAM_CATEGORY_AGE_CODE = "categoryAge.code";
    private final MongoDB mongo;

    /**
     * Instantiates a new Effective dao.
     *
     * @param mongo the mongo
     */
    @Inject
    public EffectiveDAOImpl(MongoDB mongo) {
        this.mongo = mongo;
    }

    @Override
    public void add(JsonObject effective, Handler<AsyncResult<JsonObject>> resultHandler) {
        update(effective, resultHandler);
    }

    @Override
    public void update(JsonObject effective, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(effective, DBCollections.EFFECTIVE, upsertRes -> {
            if (upsertRes.succeeded()) {
                effective.put("_id", upsertRes.result());
                resultHandler.handle(Future.succeededFuture(effective));
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });
    }

    @Override
    public void getEffectiveList(String sandboxId, String categoryAgeCode, Handler<AsyncResult<JsonArray>> resultHandler) {
        JsonObject criterias = new JsonObject().put(PARAM_SANDBOX_ID, sandboxId);
        if (categoryAgeCode != null) {
            criterias.put(PARAM_CATEGORY_AGE_CODE, categoryAgeCode);
        }
        mongo.findByCriterias(criterias, new CriteriaOption(), DBCollections.EFFECTIVE, resultJson -> {
            if (resultJson.succeeded()) {
                if (resultJson.result().size() == 0) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR,
                            "No Effective found " + "for ( sandBoxId : " + sandboxId + " "
                                    + (categoryAgeCode != null ? "and for category : " + categoryAgeCode + ")" : ")"))));
                } else {
                    resultHandler.handle(Future.succeededFuture(resultJson.result()));
                }
            } else {
                resultHandler.handle(Future.failedFuture(resultJson.cause()));
            }
        });
    }

    @Override
    public void getEffective(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.EFFECTIVE, resultHandler);
    }
}
