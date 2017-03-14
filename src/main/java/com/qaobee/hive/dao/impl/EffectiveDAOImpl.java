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
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

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
    public JsonObject add(JsonObject effective) throws QaobeeException {
        return effective.putString("_id", mongo.save(effective, DBCollections.EFFECTIVE));
    }

    @Override
    public JsonObject update(JsonObject effective) {
        return effective.putString("_id", mongo.update(effective, DBCollections.EFFECTIVE));
    }

    @Override
    public JsonArray getEffectiveList(String sandboxId, String categoryAgeCode) throws QaobeeException {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(PARAM_SANDBOX_ID, sandboxId);
        if (categoryAgeCode != null) {
            criterias.put(PARAM_CATEGORY_AGE_CODE, categoryAgeCode);
        }
        JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, DBCollections.EFFECTIVE);
        if (resultJson.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR,
                    "No Effective found " + "for ( sandBoxId : " + sandboxId + " " + (categoryAgeCode != null ? "and for category : " + categoryAgeCode + ")" : ")"));
        }
        return resultJson;
    }

    @Override
    public JsonObject getEffective(String id) throws QaobeeException {
        return mongo.getById(id, DBCollections.EFFECTIVE);
    }
}
