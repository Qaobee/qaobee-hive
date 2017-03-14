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
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Activity dao.
 */
public class ActivityDAOImpl implements ActivityDAO{

    private final MongoDB mongo;

    /**
     * Instantiates a new Activity dao.
     *
     * @param mongo the mongo
     */
    @Inject
    public ActivityDAOImpl(MongoDB mongo) {
        this.mongo = mongo;
    }

    @Override
    public JsonArray getEnabled() {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put("enable", true);
        return mongo.findByCriterias(criterias, null, null, -1, -1, DBCollections.ACTIVITY);
    }
    @Override
    public JsonArray getActivityList() {
        return mongo.findByCriterias(null, null, null, -1, -1, DBCollections.ACTIVITY);
    }

    @Override
    public JsonObject getActivity(String id) throws QaobeeException {
        return mongo.getById(id, DBCollections.ACTIVITY);
    }
}
