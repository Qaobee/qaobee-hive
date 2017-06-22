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

package com.qaobee.hive.technical.utils;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * The interface Utils.
 */
public interface Utils {
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
     * @return formated date
     */
    String formatDate(long timestamp, int dateStyle, int timeStyle, String locale);

    /**
     * Recherche dans un JsonArray d'un JsonObject en fonction de la clef.
     *
     * @param key   clef de recherche
     * @param value valeur recherchée
     * @param res   JsonArray à parcourir
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
     * @param fields array of fields to test
     * @throws QaobeeException explain missing fields
     */
    void testMandatoryParams(HashMap<String, List<String>> map, String... fields) throws QaobeeException;

    /**
     * Test mandatory params.
     *
     * @param json   request's json body
     * @param fields array of fields to test
     * @throws QaobeeException explain missing fields
     */
    void testMandatoryParams(JsonObject json, String... fields) throws QaobeeException;

    /**
     * Is user logged.
     *
     * @param request the request
     * @return the User
     * @throws QaobeeException the qaobee exception
     */
    Promise<User, QaobeeException, Integer> isUserLogged(RequestWrapper request) throws QaobeeException;

    /**
     * Is admin.
     *
     * @param request the request
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    Promise<User, QaobeeException, Integer> isLoggedAndAdmin(RequestWrapper request) throws QaobeeException;
}
