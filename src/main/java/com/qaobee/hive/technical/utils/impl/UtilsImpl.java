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
package com.qaobee.hive.technical.utils.impl;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.HabilitUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang.StringUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.vertx.java.core.MultiMap;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.eventbus.ReplyException;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

/**
 * Classe utilitaire foure-tout.
 *
 * @author Xavier MARIN
 */
public class UtilsImpl implements Utils {
    /**
     * The Mongo.
     */
    @Inject
    private MongoDB mongo;
    /**
     * The Habilit utils.
     */
    @Inject
    private HabilitUtils habilitUtils;

    /**
     * Send error.
     *
     * @param message the message
     * @param ex      the ex
     * @param info    the info
     */
    @Override
    public void sendError(final Message<String> message, final ExceptionCodes ex, final String info) {
        message.fail(ex.getCode(), Json.encode(new QaobeeException(message.body(), ex, info)));
    }

    /**
     * Test http metod.
     *
     * @param allowed méthode accèptée
     * @param tested  méthode testée
     * @throws NoSuchMethodException si les deux ne correspondent pas
     */
    @Override
    public void testHTTPMetod(final String allowed, final String tested) throws NoSuchMethodException {
        if (!allowed.equals(tested)) {
            throw new NoSuchMethodException(tested + " is not allowed");
        }
    }

    /**
     * To map.
     *
     * @param multiMap the multi map
     * @return the map
     */
    @Override
    public Map<String, List<String>> toMap(final MultiMap multiMap) {
        final Map<String, List<String>> map = new HashMap<>();
        for (final String key : multiMap.names()) {
            map.put(key, multiMap.getAll(key));
        }
        return map;
    }

    /**
     * Send error.
     *
     * @param message un message
     * @param e       une exception
     */
    @Override
    public void sendError(final Message<String> message, final QaobeeException e) {
        message.fail(e.getCode().getCode(), Json.encode(e));
    }

    /**
     * Send error.
     *
     * @param message the message
     * @param e       the e
     */
    @Override
    public void sendError(Message<String> message, ReplyException e) {
        JsonObject ex = new JsonObject(e.getMessage());
        sendError(message, ExceptionCodes.valueOf(ex.getString("code", ExceptionCodes.INTERNAL_ERROR.name())), ex.getString("message", "Internal Error"));
    }

    /**
     * Send error j.
     *
     * @param message un message
     * @param code    une erreur
     * @param error   un libellé d'erreur
     */
    @Override
    public void sendErrorJ(final Message<JsonObject> message, final ExceptionCodes code, final String error) {
        message.fail(code.getCode(), error);

    }

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
    @Override
    public void saveAndResizeImage(final File source, final String dest, final int width, final int height, final boolean isSquare) throws IOException {
        final BufferedImage originalImage = ImageIO.read(source);

        final BufferedImage targetImage;
        if (isSquare) {
            final BufferedImage tmpTargetImage;
            if (originalImage.getWidth() > originalImage.getHeight()) {
                tmpTargetImage = Scalr.crop(originalImage, originalImage.getHeight(), originalImage.getHeight());
            } else {
                tmpTargetImage = Scalr.crop(originalImage, originalImage.getWidth(), originalImage.getWidth());
            }
            targetImage = Scalr.resize(tmpTargetImage, Mode.FIT_TO_WIDTH, width, height);
        } else {
            targetImage = Scalr.resize(originalImage, Mode.FIT_TO_WIDTH, width, height);
        }
        final File fDest = new File(dest);
        if (!fDest.getParentFile().exists()) {
            boolean mkdirs = fDest.getParentFile().mkdirs();
        }
        ImageIO.write(targetImage, "PNG", fDest);
    }

    /**
     * Format date.
     *
     * @param timestamp unix timestamp
     * @param dateStyle the date style
     * @param timeStyle the time style
     * @param locale    the locale
     * @return formated date
     */
    @Override
    public String formatDate(final long timestamp, final int dateStyle, final int timeStyle, final String locale) {
        return DateFormat.getDateTimeInstance(dateStyle, dateStyle, new Locale(locale.split(",")[0].split("-")[0])).format(new Date(timestamp * 1000));
    }

    /**
     * Recherche dans un JsonArray d'un JsonObject en fonction de la clef.
     *
     * @param key   clef de recherche
     * @param value valeur recherchée
     * @param res   JsonArray à parcourir
     * @return l 'objet trouvé, null sinon
     */
    @Override
    public JsonObject find(final String key, final String value, final JsonArray res) {
        for (int i = 0; i < res.size(); i++) {
            final JsonObject jsonObj = res.get(i);
            if (jsonObj.getString(key).equals(value)) {
                return jsonObj;
            }
        }
        return null;
    }

    /**
     * renvoie une réponse Json de type status.
     *
     * @param b       le résultat d'une opération
     * @param message le message sur le bus
     */
    @Override
    public void sendStatus(final boolean b, final Message<String> message) {
        final JsonObject jsonResp = new JsonObject();
        jsonResp.putBoolean("status", b);
        message.reply(jsonResp.encode());

    }

    /**
     * Send status json.
     *
     * @param b       the b
     * @param message the message
     */
    @Override
    public void sendStatusJson(final boolean b, final Message<JsonObject> message) {
        final JsonObject jsonResp = new JsonObject();
        jsonResp.putBoolean("status", b);
        message.reply(jsonResp);

    }

    /**
     * Test mandatory params.
     *
     * @param map    the map
     * @param fields the fields
     * @throws IllegalArgumentException the illegal argument exception
     */
    @Override
    public void testMandatoryParams(Map<String, ?> map, String... fields) throws IllegalArgumentException {
        final List<String> missingFields = new ArrayList<>();
        if (map == null) {
            map = new HashMap<>();
        }

        for (final String field : fields) {
            if (!map.containsKey(field) || map.get(field) == null) {
                missingFields.add(field);
            } else if ((map.get(field) instanceof List && StringUtils.isBlank((String) ((List<?>) map.get(field)).get(0)))
                    || (map.get(field) instanceof String && StringUtils.isBlank(((String) map.get(field))))) {
                missingFields.add(field);
            }
        }

        if (!missingFields.isEmpty()) {
            throw new IllegalArgumentException("Missing mandatory parameters : " + missingFields);
        }
    }

    /**
     * Test mandatory params.
     *
     * @param body   the body
     * @param fields the fields
     * @throws IllegalArgumentException the illegal argument exception
     */
    @Override
    public void testMandatoryParams(String body, final String... fields) throws IllegalArgumentException {
        testMandatoryParams(new JsonObject(body).toMap(), fields);
    }

    /**
     * Is user logged.
     *
     * @param request the request
     * @return the User
     * @throws QaobeeException the qaobee exception
     */
    @Override
    public User isUserLogged(RequestWrapper request) throws QaobeeException {
        String token = "";
        if (request.getHeaders() != null && request.getHeaders().containsKey("token")) {
            token = request.getHeaders().get("token").get(0);
        }
        if (request.getParams() != null && request.getParams().containsKey("token")) {
            token = request.getParams().get("token").get(0);
        }
        if (StringUtils.isBlank(token)) {
            throw new QaobeeException(ExceptionCodes.NOT_LOGGED, Messages.getString("not.logged", request.getLocale()));
        }
        final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.token", token).get(), null, null, 0, 0, User.class);
        if (res.size() != 1) {
            throw new QaobeeException(ExceptionCodes.NOT_LOGGED, Messages.getString("not.logged", request.getLocale()));
        } else {
            // we take the first one (should be only one)
            final JsonObject jsonUser = res.get(0);
            JsonObject userToSave = new JsonObject();
            final User user = Json.decodeValue(jsonUser.encode(), User.class);
            userToSave.putString("_id", user.get_id());

            if (Constantes.DEFAULT_SESSION_TIMEOUT < System.currentTimeMillis() - user.getAccount().getTokenRenewDate()) {
                userToSave.putString("account.token", null);
                user.getAccount().setToken(null);
                userToSave.putNumber("account.tokenRenewDate", 0l);
                user.getAccount().setTokenRenewDate(0l);
            } else {
                long connectionTime = System.currentTimeMillis();
                userToSave.putNumber("account.tokenRenewDate", connectionTime);
                user.getAccount().setTokenRenewDate(connectionTime);
            }

            try {
                mongo.update(userToSave, User.class);
                if (user.getAccount().getTokenRenewDate() == 0) {
                    throw new QaobeeException(ExceptionCodes.NOT_LOGGED, Messages.getString("not.logged", request.getLocale()));
                }
                request.setUser(user);
                return user;
            } catch (final EncodeException e) {
                throw new QaobeeException(ExceptionCodes.JSON_EXCEPTION, e.getMessage());
            }
        }
    }

    /**
     * Is logged and admin.
     *
     * @param request the request
     * @return the user
     * @throws QaobeeException the qaobee exception
     */
    @Override
    public User isLoggedAndAdmin(RequestWrapper request) throws QaobeeException {
        User user = isUserLogged(request);
        if (!habilitUtils.hasHabilitation(user, Constantes.ADMIN_HABILIT)) { // are we admin ?
            throw new QaobeeException(ExceptionCodes.NOT_ADMIN, Messages.getString("not.admin", request.getLocale()));
        }
        return user;
    }
}
