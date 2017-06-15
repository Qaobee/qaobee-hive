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

package com.qaobee.hive.dao.impl;

import com.qaobee.hive.dao.ActivityDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Activity dao.
 */
public class ActivityDAOImpl implements ActivityDAO {

    @Inject
    private MongoDB mongo;

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getEnabled() {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put("enable", true);
        return mongo.findByCriterias(criterias, null, null, -1, -1, DBCollections.ACTIVITY);
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getActivityList() {
        return mongo.findByCriterias(null, null, null, -1, -1, DBCollections.ACTIVITY);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getActivity(String id) {
        return mongo.getById(id, DBCollections.ACTIVITY);
    }
}
