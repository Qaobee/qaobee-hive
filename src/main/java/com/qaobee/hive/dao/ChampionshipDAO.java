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
 * The interface Championship dao.
 */
public interface ChampionshipDAO {
    /**
     * Update championship json object.
     *
     * @param championship the championship
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    Promise<String, QaobeeException, Integer> updateChampionship(JsonObject championship) throws QaobeeException;

    /**
     * Add championship json object.
     *
     * @param championship the championship
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonObject, QaobeeException, Integer> addChampionship(JsonObject championship) throws QaobeeException;

    /**
     * Gets championship.
     *
     * @param id the id
     * @return the championship
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonObject, QaobeeException, Integer> getChampionship(String id) throws QaobeeException;

    /**
     * Gets list championships.
     *
     * @param params the params
     * @return the list championships
     * @throws QaobeeException the qaobee exception
     */
    Promise<JsonArray, QaobeeException, Integer> getListChampionships(JsonObject params) throws QaobeeException;
}
