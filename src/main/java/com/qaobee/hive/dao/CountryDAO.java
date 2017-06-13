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

package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * The interface Country dao.
 */
public interface CountryDAO {
    /**
     * Gets country from alpha 2.
     *
     * @param alpha2 the alpha 2
     * @return the country from alpha 2
     */
    JsonObject getCountryFromAlpha2(String alpha2);

    /**
     * Gets country list.
     *
     * @param locale the locale
     * @param label  the label
     * @return the country list
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getCountryList(String locale, String label) throws QaobeeException;

    /**
     * Gets country.
     *
     * @param id the id
     * @return the country
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getCountry(String id) throws QaobeeException;
}
