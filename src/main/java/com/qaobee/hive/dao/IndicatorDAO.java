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
import org.jdeferred.Promise;

/**
 * The interface Indicator dao.
 */
public interface IndicatorDAO {
    /**
     * Gets indicator by code.
     *
     * @param activityId     the activity id
     * @param countryId      the country id
     * @param listIndicators the list indicators
     * @return the indicator by code
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonArray, QaobeeException, Integer> getIndicatorByCode(String activityId, String countryId, JsonArray listIndicators) throws QaobeeException;

    /**
     * Gets indicators list.
     *
     * @param activityId the activity id
     * @param countryId  the country id
     * @param screen     the screen
     * @return the indicators list
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonArray, QaobeeException, Integer> getIndicatorsList(String activityId, String countryId, JsonArray screen) throws QaobeeException;

    /**
     * Gets indicator.
     *
     * @param id the id
     * @return the indicator
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonObject, QaobeeException, Integer> getIndicator(String id) throws QaobeeException;
}
