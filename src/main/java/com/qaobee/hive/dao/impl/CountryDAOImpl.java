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
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.apache.commons.lang3.StringUtils;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Country dao.
 */
public class CountryDAOImpl implements CountryDAO {
    private static final String COLLECTION = "Country";
    private Map<String, JsonObject> mapCountry = null;

    @Inject
    private MongoDB mongo;

    @Override
    public JsonObject getCountryFromAlpha2(String alpha2) {
        if (mapCountry == null) {
            getCountries();
        }
        if (mapCountry == null) {
            return null;
        }
        return mapCountry.get(alpha2.toUpperCase());
    }

    @Override
    public JsonArray getCountryList(String locale, String label) throws QaobeeException {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put(CountryVerticle.PARAM_LOCAL, locale);
        // label
        if (StringUtils.isNotBlank(label)) {
            criterias.put(CountryVerticle.PARAM_LABEL, label);
        }
        JsonArray resultJson = mongo.findByCriterias(criterias, null, null, -1, -1, COLLECTION);
        if (resultJson.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR,
                    "No Country defined for (" + label + ")");
        }
        return resultJson;
    }

    @Override
    public JsonObject getCountry(String id) throws QaobeeException {
        return mongo.getById(id, COLLECTION);
    }

    private void getCountries() {
        JsonArray resultJson = mongo.findAll(null, null, -1, 0, COLLECTION);
        if (resultJson != null) {
            mapCountry = new HashMap<>();
            resultJson.forEach(c -> mapCountry.put(((JsonObject) c).getString("_id").split("-")[2], (JsonObject) c));
        }
    }
}
