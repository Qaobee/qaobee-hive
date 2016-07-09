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

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.dao.CountryDAO;
import com.qaobee.hive.dao.StructureDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * The type Structure dao.
 */
public class StructureDAOImpl implements StructureDAO {

    private static final String COLLECTION = "Structure";
    @Inject
    private MongoDB mongo;
    @Inject
    private CountryDAO countryDAO;

    @Override
    public JsonObject update(JsonObject structure) throws QaobeeException {
        mongo.save(structure, COLLECTION);
        return structure;
    }

    @Override
    public JsonArray getListOfStructures(String activity, JsonObject address) throws QaobeeException {
        JsonObject country = countryDAO.getCountryFromAlpha2(address.getString("countryAlpha2", "FR"));
        if (country == null) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No Country defined for (" + address.getString("countryAlpha2") + ")");
        }
        // Aggregat section
        DBObject match;
        BasicDBObject dbObjectParent;
        // $MACTH section
        dbObjectParent = new BasicDBObject();
        // Activity ID
        dbObjectParent.put("activity._id", activity);
        // Country ID
        dbObjectParent.put("country._id", country.getString("_id"));
        // City OR Zipcode
        BasicDBList dbList = new BasicDBList();
        dbList.add(new BasicDBObject("address.city", address.getString("city").toUpperCase()));
        dbList.add(new BasicDBObject("address.zipcode", address.getString("zipcode")));
        dbObjectParent.put("$or", dbList.toArray());
        match = new BasicDBObject("$match", dbObjectParent);
        // Pipeline
        List<DBObject> pipelineAggregation = Collections.singletonList(match);
       return mongo.aggregate("_id", pipelineAggregation, COLLECTION);
    }

    @Override
    public JsonObject getStructure(String id) throws QaobeeException {
        return mongo.getById(id, COLLECTION);
    }

    @Override
    public JsonObject addStructure(JsonObject structure) throws QaobeeException {
        final String id = mongo.save(structure, COLLECTION);
        structure.putString("_id", id);
        return structure;
    }
}
