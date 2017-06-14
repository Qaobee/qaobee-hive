/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

/**
 * The interface Asset dao.
 */
public interface AssetDAO {
    /**
     * Add asset json object.
     *
     * @param userId      the user id
     * @param token       the token
     * @param filename    the filename
     * @param collection  the collection
     * @param field       the field
     * @param contentType the content type
     * @param locale      the locale
     *
     * @return the json object
     */
    Promise<JsonObject, QaobeeException, Integer> addAsset(String userId, String token, String filename, String collection, String field, String contentType, String locale);

    /**
     * Gets asset.
     *
     * @param collection the collection
     * @param id         the id
     *
     * @return the asset
     */
    Promise<JsonObject, QaobeeException, Integer> getAsset(String collection, String id);
}
