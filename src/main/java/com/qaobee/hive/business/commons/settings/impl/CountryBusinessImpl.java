/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.business.commons.settings.impl;

import com.qaobee.hive.business.commons.settings.CountryBusiness;
import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Country business.
 *
 * @author jerome
 */
public class CountryBusinessImpl implements CountryBusiness {

    private Map<String, Country> mapCountry = null;

    @Inject
    private MongoDB mongo;

    @Override
    public Country getCountryFromAlpha2(String alpha2) {
        if (mapCountry == null) {
            getCountries();
        }
        if (mapCountry == null) {
            return null;
        }
        return mapCountry.get(alpha2);
    }

    private void getCountries() {
        JsonArray resultJson = mongo.findAll(null, null, -1, 0, Country.class);
        if (resultJson != null && resultJson.size() > 0) {
            Country country;
            String[] tabId;
            mapCountry = new HashMap<>();
            for (int i = 0; i < resultJson.size(); i++) {
                country = Json.decodeValue(resultJson.get(i).toString(), Country.class);
                tabId = country.get_id().split("-");
                mapCountry.put(tabId[2], country);
            }
        }
    }
}
