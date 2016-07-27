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
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Season dao.
 */
public interface SeasonDAO {
    /**
     * Gets current season.
     *
     * @param activityId the activity id
     * @param countryId  the country id
     * @return the current season
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getCurrentSeason(String activityId, String countryId) throws QaobeeException;

    /**
     * Gets list by activity.
     *
     * @param activityId the activity id
     * @param countryId  the country id
     * @return the list by activity
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getListByActivity(String activityId, String countryId) throws QaobeeException;

    /**
     * Gets season.
     *
     * @param id the id
     * @return the season
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getSeason(String id) throws QaobeeException;
}
