package com.qaobee.technical.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.vertx.java.core.MultiMap;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.technical.exceptions.ExceptionCodes;
import com.qaobee.technical.exceptions.QaobeeException;

/**
 * Created by xavier on 09/11/14.
 */
public interface Utils {
	/**
	 * Send error.
	 *
	 * @param message
	 *            the message
	 * @param ex
	 *            the ex
	 * @param info
	 *            the info
	 */
	void sendError(Message<String> message, ExceptionCodes ex, String info);

	/**
	 * Test http metod.
	 *
	 * @param allowed
	 *            méthode accèptée
	 * @param tested
	 *            méthode testée
	 * @throws NoSuchMethodException
	 *             si les deux ne correspondent pas
	 */
	void testHTTPMetod(String allowed, String tested) throws NoSuchMethodException;

	/**
	 * To map.
	 *
	 * @param multiMap
	 *            the multi map
	 * @return the map
	 */
	Map<String, List<String>> toMap(MultiMap multiMap);

	/**
	 * Send error.
	 *
	 * @param message
	 *            un message
	 * @param e
	 *            une exception
	 */
	void sendError(Message<String> message, QaobeeException e);

	/**
	 * Send error j.
	 *
	 * @param message
	 *            un message
	 * @param code
	 *            une erreur
	 * @param error
	 *            un libellé d'erreur
	 */
	void sendErrorJ(Message<JsonObject> message, ExceptionCodes code, String error);

	/**
	 * Save and resize image.
	 *
	 * @param source
	 *            fichier source
	 * @param dest
	 *            fichier de destination
	 * @param width
	 *            largeur
	 * @param height
	 *            hauteur
	 * @param isSquare
	 *            carrée
	 * @throws java.io.IOException
	 *             une erreur d'IO
	 */
	void saveAndResizeImage(File source, String dest, int width, int height, boolean isSquare) throws IOException;

	/**
	 * Format date.
	 *
	 * @param timestamp
	 *            unix timestamp
	 * @param dateStyle
	 *            the date style
	 * @param timeStyle
	 *            the time style
	 * @param locale
	 *            the locale
	 * @return formated date
	 * @see java.text.DateFormat
	 * @see java.text.DateFormat
	 * @see com.qaobee.swarn.technical.vertx.RequestWrapper
	 */
	String formatDate(long timestamp, int dateStyle, int timeStyle, String locale);

	/**
	 * Recherche dans un JsonArray d'un JsonObject en fonction de la clef.
	 *
	 * @param key
	 *            clef de recherche
	 * @param value
	 *            valeur recherchée
	 * @param res
	 *            JsonArray à parcourir
	 * @return l'objet trouvé, null sinon
	 */
	JsonObject find(String key, String value, JsonArray res);

	/**
	 * renvoie une réponse Json de type status.
	 *
	 * @param b
	 *            le résultat d'une opération
	 * @param message
	 *            le message sur le bus
	 */
	void sendStatus(boolean b, Message<String> message);

	/**
	 * Send status json.
	 *
	 * @param b
	 *            the b
	 * @param message
	 *            the message
	 */
	void sendStatusJson(boolean b, Message<JsonObject> message);

	/**
	 * Test mandatory params.
	 *
	 * @param map
	 *            request's parameters
	 * @param fields
	 *            array of fields to test
	 * @throws IllegalArgumentException
	 *             explain missing fields
	 */
	void testMandatoryParams(Map<String, ?> map, String... fields) throws IllegalArgumentException;

	/**
	 * Test mandatory params.
	 *
	 * @param json
	 *            request's json body
	 * @param fields
	 *            array of fields to test
	 * @throws IllegalArgumentException
	 *             explain missing fields
	 */
	void testMandatoryParams(String json, String... fields) throws IllegalArgumentException;
}
