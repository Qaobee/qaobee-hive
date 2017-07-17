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

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
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
     * Wrap request request wrapper.
     *
     * @param routingContext the routing context
     *
     * @return the request wrapper
     */
    RequestWrapper wrapRequest(RoutingContext routingContext);

    /**
     * Send error.
     *
     * @param message the message
     * @param ex      the ex
     * @param info    the info
     */
    void sendError(Message<String> message, ExceptionCodes ex, String info);

    /**
     * Test http metod.
     *
     * @param allowed méthode accèptée
     * @param tested  méthode testée
     *
     * @throws NoSuchMethodException si les deux ne correspondent pas
     */
    void testHTTPMetod(String allowed, String tested) throws NoSuchMethodException;

    /**
     * Send error.
     *
     * @param message un message
     * @param e       une exception
     */
    void sendError(Message<String> message, QaobeeException e);

    /**
     * Send error.
     *
     * @param message the message
     * @param e       the e
     */
    void sendError(Message<String> message, ReplyException e);

    /**
     * Send error j.
     *
     * @param message the message
     * @param e       the e
     */
    void sendErrorJ(Message<JsonObject> message, QaobeeException e);

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
     * renvoie une réponse Json de type status.
     *
     * @param b       le résultat d'une opération
     * @param message le message sur le bus
     */
    void sendStatus(boolean b, Message<String> message);

    /**
     * Send status json.
     *
     * @param b       the b
     * @param message the message
     */
    void sendStatusJson(boolean b, Message<JsonObject> message);

    /**
     * Send status json.
     *
     * @param b       the b
     * @param cause   the cause
     * @param message the message
     */
    void sendStatusJson(boolean b, String cause, Message<JsonObject> message);


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
     * Is user logged.
     *
     * @param request the request
     *
     * @return the User
     */
    Future<User> isUserLogged(RequestWrapper request);

    /**
     * Is admin.
     *
     * @param request the request
     *
     * @return the boolean
     */
    Future<User> isLoggedAndAdmin(RequestWrapper request);

    /**
     * Test mandatory params.
     *
     * @param params the params
     * @param fields the fields
     *
     * @throws QaobeeException the qaobee exception
     */
    void testMandatoryParams(MultiMap params, String... fields) throws QaobeeException;

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
    void handleError(RoutingContext context, QaobeeException e);

    /**
     * Test mandatory params.
     *
     * @param obj     the obj
     * @param context the context
     * @param fields  the fields
     */
    void testMandatoryParams(JsonObject obj, RoutingContext context, String... fields);

    /**
     * Gets minimal.
     *
     * @param minimal the minimal
     *
     * @return the minimal
     */
    JsonObject getMinimal(List<String> minimal);
}
