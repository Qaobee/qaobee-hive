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
 * The interface Structure dao.
 */
public interface StructureDAO {
    /**
     * Update json object.
     *
     * @param structure the structure
     * @return the json object
     */
    Promise<String, QaobeeException, Integer> update(JsonObject structure);

    /**
     * Gets list of structures.
     *
     * @param activity the activity
     * @param address  the address
     * @return the list of structures
     */
    Promise<JsonArray, QaobeeException, Integer> getListOfStructures(String activity, JsonObject address);

    /**
     * Gets structure.
     *
     * @param id the id
     * @return the structure
     */
    Promise<JsonObject, QaobeeException, Integer> getStructure(String id);

    /**
     * Add structure json object.
     *
     * @param structure the structure
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> addStructure(JsonObject structure);
}
