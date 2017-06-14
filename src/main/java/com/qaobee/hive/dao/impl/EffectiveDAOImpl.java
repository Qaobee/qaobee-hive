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
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

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
    public Promise<JsonObject, QaobeeException, Integer> add(JsonObject effective) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.upsert(effective, DBCollections.EFFECTIVE)
                .done(res -> {
                    effective.put("_id", res);
                    deferred.resolve(effective);
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> update(JsonObject effective) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.upsert(effective, DBCollections.EFFECTIVE)
                .done(res -> {
                    effective.put("_id", res);
                    deferred.resolve(effective);
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getEffectiveList(String sandboxId, String categoryAgeCode) {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(PARAM_SANDBOX_ID, sandboxId);
        if (categoryAgeCode != null) {
            criterias.put(PARAM_CATEGORY_AGE_CODE, categoryAgeCode);
        }
        return mongo.findByCriterias(criterias, null, null, -1, -1, DBCollections.EFFECTIVE);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getEffective(String id) {
        return mongo.getById(id, DBCollections.EFFECTIVE);
    }
}
