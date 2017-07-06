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

import com.qaobee.hive.dao.CountryDAO;
import com.qaobee.hive.dao.StructureDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;

/**
 * The type Structure dao.
 */
public class StructureDAOImpl implements StructureDAO {
    private static final String COUNTRY_ALPHA_2_FIELD = "countryAlpha2";
    private final MongoDB mongo;
    private final CountryDAO countryDAO;

    /**
     * Instantiates a new Structure dao.
     *
     * @param mongo the mongo
     */
    @Inject
    public StructureDAOImpl(MongoDB mongo, CountryDAO countryDAO) {
        this.mongo = mongo;
        this.countryDAO = countryDAO;
    }

    @Override
    public Promise<String, QaobeeException, Integer> update(JsonObject structure) {
        return mongo.upsert(structure, DBCollections.STRUCTURE);
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getListOfStructures(String activity, JsonObject address) {
        Deferred<JsonArray, QaobeeException, Integer> deferred = new DeferredObject<>();
        countryDAO.getCountryFromAlpha2(address.getString(COUNTRY_ALPHA_2_FIELD, "FR")).done(country -> {
            if (country == null) {
                deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "No Country defined for (" + address.getString(COUNTRY_ALPHA_2_FIELD) + ")"));
            } else {
                // $MACTH section
                JsonObject dbObjectParent = new JsonObject().put("activity._id", activity).put("country._id", country.getString("_id"));
                // City OR Zipcode
                JsonArray dbList = new JsonArray()
                        .add(new JsonObject().put("address.city", address.getString("city").toUpperCase()))
                        .add(new JsonObject().put("address.zipcode", address.getString("zipcode")));
                dbObjectParent.put("$or", dbList);
                JsonObject match = new JsonObject().put("$match", dbObjectParent);
                // Pipeline
                JsonArray pipelineAggregation = new JsonArray().add(match);
                mongo.aggregate(pipelineAggregation, DBCollections.STRUCTURE).done(deferred::resolve).fail(deferred::reject);
            }
        }).fail(e -> deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "No Country defined for (" + address.getString(COUNTRY_ALPHA_2_FIELD) + ")")));
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getStructure(String id) {
        return mongo.getById(id, DBCollections.STRUCTURE);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> addStructure(JsonObject structure) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.upsert(structure, DBCollections.STRUCTURE).done(id -> {
            structure.put("_id", id);
            deferred.resolve(structure);
        }).fail(deferred::reject);
        return deferred.promise();
    }
}
