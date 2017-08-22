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
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The interface Utils.
 */
public interface Utils {

    /**
     * Send error j.
     *
     * @param message the message
     * @param e       the e
     */
    void sendError(Message<JsonObject> message, QaobeeException e);

    /**
     * Save and resize image.
     *
     * @param source   fichier source
     * @param dest     fichier de destination
     * @param width    largeur
     * @param height   hauteur
     * @param isSquare carrée
     *
     * @throws IOException une erreur d'IO
     */
    void saveAndResizeImage(File source, String dest, int width, int height, boolean isSquare) throws IOException;

    /**
     * Format date.
     *
     * @param timestamp unix timestamp
     * @param dateStyle the date style
     * @param timeStyle the time style
     * @param locale    the locale
     *
     * @return formated date
     */
    String formatDate(long timestamp, int dateStyle, int timeStyle, String locale);

    /**
     * Recherche dans un JsonArray d'un JsonObject en fonction de la clef.
     *
     * @param key   clef de recherche
     * @param value valeur recherchée
     * @param res   JsonArray à parcourir
     *
     * @return l 'objet trouvé, null sinon
     */
    JsonObject find(String key, String value, JsonArray res);

    /**
     * Send status json.
     *
     * @param b       the b
     * @param message the message
     */
    void sendStatus(boolean b, Message<JsonObject> message);

    /**
     * Send status json.
     *
     * @param b       the b
     * @param cause   the cause
     * @param message the message
     */
    void sendStatus(boolean b, String cause, Message<JsonObject> message);


    /**
     * Test mandatory params.
     *
     * @param map    request's parameters
     * @param fields array of fields to testBodyParams
     *
     * @throws QaobeeException explain missing fields
     */
    void testMandatoryParams(Map<String, List<String>> map, String... fields) throws QaobeeException;

    /**
     * Test mandatory params.
     *
     * @param json   request's json body
     * @param fields array of fields to testBodyParams
     *
     * @throws QaobeeException explain missing fields
     */
    void testMandatoryParams(JsonObject json, String... fields) throws QaobeeException;

    /**
     * Test mandatory params.
     *
     * @param context the context
     * @param fields  the fields
     *
     * @throws QaobeeException the qaobee exception
     */
    void testMandatoryParams(RoutingContext context, String... fields) throws QaobeeException;

    /**
     * Test mandatory params.
     *
     * @param params  the params
     * @param context the context
     * @param fields  the fields
     *
     * @throws QaobeeException the qaobee exception
     */
    void testMandatoryParams(MultiMap params, RoutingContext context, String... fields) throws QaobeeException;

    /**
     * Handle error.
     *
     * @param context the context
     * @param e       the e
     */
    void handleError(RoutingContext context, Throwable e);

    /**
     * Gets minimal.
     *
     * @param minimal the minimal
     *
     * @return the minimal
     */
    JsonObject getMinimal(List<String> minimal);
}
