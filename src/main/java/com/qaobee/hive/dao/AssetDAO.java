package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonObject;

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
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject addAsset(String userId, String token, String filename, String collection, String field, String contentType, String locale) throws QaobeeException;

    /**
     * Gets asset.
     *
     * @param collection the collection
     * @param id         the id
     * @return the asset
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getAsset(String collection, String id) throws QaobeeException;
}
