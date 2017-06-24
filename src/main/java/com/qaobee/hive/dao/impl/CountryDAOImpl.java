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

import com.qaobee.hive.api.v1.commons.settings.CountryVerticle;
import com.qaobee.hive.dao.CountryDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Country dao.
 */
public class CountryDAOImpl implements CountryDAO {
    private JsonObject mapCountry = new JsonObject();

    private final MongoDB mongo;

    /**
     * Instantiates a new Country dao.
     *
     * @param mongo the mongo
     */
    @Inject
    public CountryDAOImpl(MongoDB mongo) {
        this.mongo = mongo;
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getCountryFromAlpha2(String alpha2) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        getCountries()
                .done(map -> deferred.resolve(map.getJsonObject(alpha2.toUpperCase())))
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonArray, QaobeeException, Integer> getCountryList(String locale, String label) {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(CountryVerticle.PARAM_LOCAL, locale);
        // label
        if (StringUtils.isNotBlank(label)) {
            criterias.put(CountryVerticle.PARAM_LABEL, label);
        }
        return mongo.findByCriterias(criterias, null, null, -1, -1, DBCollections.COUNTRY);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getCountry(String id) {
        return mongo.getById(id, DBCollections.COUNTRY);
    }

    private Promise<JsonObject, QaobeeException, Integer> getCountries() {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (mapCountry == null || mapCountry.fieldNames().size() == 0) {
            mongo.findAll(null, null, -1, 0, DBCollections.COUNTRY)
                    .done(resultJson -> {
                        resultJson.forEach(c -> mapCountry.put(((JsonObject) c).getString("_id").split("-")[2], (JsonObject) c));
                        deferred.resolve(mapCountry);
                    })
                    .fail(deferred::reject);
        } else {
            deferred.resolve(mapCountry);
        }
        return deferred.promise();
    }
}
