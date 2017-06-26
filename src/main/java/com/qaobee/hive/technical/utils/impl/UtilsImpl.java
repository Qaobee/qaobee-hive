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
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.HabilitUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.imgscalr.Scalr;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOG = LoggerFactory.getLogger(UtilsImpl.class);
    private static final String NOT_LOGGED_KEY = "not.logged";
    private static final String SESSION_EXPIRED = "session.expired";
    private static final String STATUS_FIELD = "status";
    private final MongoDB mongo;
    private final HabilitUtils habilitUtils;
    private final MongoClientCustom mongoClient;

    /**
     * Instantiates a new Utils.
     *
     * @param mongo        the mongo
     * @param habilitUtils the habilit utils
     */
    @Inject
    public UtilsImpl(MongoDB mongo, HabilitUtils habilitUtils, MongoClientCustom mongoClient) {
        this.mongo = mongo;
        this.habilitUtils = habilitUtils;
        this.mongoClient = mongoClient;
    }

    @Override
    public void sendError(final Message<String> message, final ExceptionCodes ex, final String info) {
        message.fail(ex.getCode(), Json.encode(new QaobeeException(message.body(), ex, info)));
    }

    @Override
    public void testHTTPMetod(final String allowed, final String tested) throws NoSuchMethodException {
        if (!allowed.equals(tested)) {
            throw new NoSuchMethodException(tested + " is not allowed");
        }
    }


    @Override
    public void sendError(final Message<String> message, final QaobeeException e) {
        message.fail(e.getCode().getCode(), Json.encode(e));
    }

    @Override
    public void sendError(Message<String> message, ReplyException e) {
        JsonObject ex = new JsonObject(e.getMessage());
        sendError(message, ExceptionCodes.valueOf(ex.getString("code", ExceptionCodes.INTERNAL_ERROR.name())), ex.getString("message", "Internal Error"));
    }

    @Override
    public void sendErrorJ(final Message<JsonObject> message, final QaobeeException e) {
        JsonObject err = new JsonObject(Json.encode(e));
        if (!err.getBoolean("report")) {
            err.remove("stackTrace");
        }
        message.fail(e.getCode().getCode(), err.encode());
    }

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
            targetImage = Scalr.resize(tmpTargetImage, Scalr.Mode.FIT_TO_WIDTH, width, height);
        } else {
            targetImage = Scalr.resize(originalImage, Scalr.Mode.FIT_TO_WIDTH, width, height);
        }
        final File fDest = new File(dest);
        if (!fDest.getParentFile().exists()) {
            boolean mkdirs = fDest.getParentFile().mkdirs();
            if (LOG.isInfoEnabled()) {
                LOG.info(String.format("res : %s", mkdirs));
            }
        }
        ImageIO.write(targetImage, "PNG", fDest);
    }

    @Override
    public String formatDate(final long timestamp, final int dateStyle, final int timeStyle, final String locale) {
        return DateFormat.getDateTimeInstance(dateStyle, dateStyle, new Locale(locale.split(",")[0].split("-")[0])).format(new Date(timestamp * 1000));
    }

    @Override
    public JsonObject find(final String key, final String value, final JsonArray res) {
        for (int i = 0; i < res.size(); i++) {
            final JsonObject jsonObj = res.getJsonObject(i);
            if (jsonObj.getString(key).equals(value)) {
                return jsonObj;
            }
        }
        return null;
    }

    @Override
    public void sendStatus(final boolean b, final Message<String> message) {
        message.reply(new JsonObject().put(STATUS_FIELD, b).encode());
    }

    @Override
    public void sendStatusJson(final boolean b, final Message<JsonObject> message) {
        message.reply(new JsonObject().put(STATUS_FIELD, b));
    }

    @Override
    public void sendStatusJson(boolean b, String cause, Message<JsonObject> message) {
        final JsonObject jsonResp = new JsonObject()
                .put(STATUS_FIELD, b)
                .put("cause", cause);
        message.reply(jsonResp);
    }

    @Override
    public void testMandatoryParams(HashMap<String, List<String>> map, String... fields) throws QaobeeException {
        final List<String> missingFields = new ArrayList<>();
        for (final String field : fields) {
            if (!map.containsKey(field) || map.get(field) == null || StringUtils.isBlank(map.get(field).get(0))) {
                missingFields.add(field);
            }
        }
        if (!missingFields.isEmpty()) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "Missing mandatory parameters : " + missingFields);
        }
    }

    private void testMandatoryParams(Map<String, ?> mapParams, String... fields) throws QaobeeException {
        final List<String> missingFields = new ArrayList<>();
        Map<String, ?> map = new HashMap<>();
        if (mapParams != null) {
            map = mapParams;
        }
        for (final String field : fields) {
            if (!map.containsKey(field) || map.get(field) == null || (map.get(field) instanceof String && StringUtils.isBlank((String) map.get(field))) || map.get(field) instanceof List && (((List<?>) map.get(field)).isEmpty() || ((List<?>) map.get(field)).get(0) instanceof String && StringUtils.isBlank((String) ((List<?>) map.get(field)).get(0)) || ((List<?>) map.get(field)).get(0) == null)) { // NOSONAR
                missingFields.add(field);
            }
        }
        if (!missingFields.isEmpty()) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "Missing mandatory parameters : " + missingFields);
        }
    }

    @Override
    public void testMandatoryParams(JsonObject body, final String... fields) throws QaobeeException {
        if (body == null) {
            body = new JsonObject();
        }
        final List<String> missingFields = new ArrayList<>();
        for (String field : fields) {
            if (!body.containsKey(field) || body.getValue(field) == null || (body.getValue(field) instanceof String && StringUtils.isEmpty(body.getString(field)))) {
                missingFields.add(field);
            }
        }
        if (!missingFields.isEmpty()) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "Missing mandatory parameters : " + missingFields);
        }
    }

    @Override
    public Promise<User, QaobeeException, Integer> isUserLogged(RequestWrapper request) {
        Deferred<User, QaobeeException, Integer> deferred = new DeferredObject<>();
        String token = "";
        if (request.getUser() != null) {
            deferred.resolve(request.getUser());
        } else {
            if (request.getHeaders() != null && request.getHeaders().containsKey(Constants.TOKEN)) {
                token = request.getHeaders().get(Constants.TOKEN).get(0);
            }
            if (request.getParams() != null && request.getParams().containsKey(Constants.TOKEN)) {
                token = request.getParams().get(Constants.TOKEN).get(0);
            }
            if (StringUtils.isBlank(token)) {
                deferred.reject(new QaobeeException(ExceptionCodes.NOT_LOGGED, Messages.getString(NOT_LOGGED_KEY, request.getLocale())));
            } else {
                mongoClient.findOne(DBCollections.USER, new JsonObject().put("account.token", token), new JsonObject(), result -> {
                    if (result.succeeded() && result.result() != null) {
                        // we take the first one (should be only one)
                        final JsonObject jsonUser = result.result();
                        final User user = Json.decodeValue(jsonUser.encode(), User.class);
                        if (Constants.DEFAULT_SESSION_TIMEOUT < System.currentTimeMillis() - user.getAccount().getTokenRenewDate()) {
                            jsonUser.getJsonObject("account").put("token", "");
                            user.getAccount().setToken(null);
                            jsonUser.getJsonObject("account").put("tokenRenewDate", 0L);
                            user.getAccount().setTokenRenewDate(0L);
                        } else {
                            long connectionTime = System.currentTimeMillis();
                            jsonUser.getJsonObject("account").put("tokenRenewDate", connectionTime);
                            user.getAccount().setTokenRenewDate(connectionTime);
                        }
                        mongo.upsert(jsonUser, DBCollections.USER).done(id -> {
                            if (user.getAccount().getTokenRenewDate() == 0) {
                                deferred.reject(new QaobeeException(ExceptionCodes.NOT_LOGGED, Messages.getString(SESSION_EXPIRED, request.getLocale())));
                            }
                            request.setUser(user);
                            deferred.resolve(user);
                        }).fail(deferred::reject);
                    } else {
                        deferred.reject(new QaobeeException(ExceptionCodes.NOT_LOGGED, Messages.getString(NOT_LOGGED_KEY, request.getLocale())));
                    }
                });
            }
        }
        return deferred.promise();
    }

    @Override
    public Promise<User, QaobeeException, Integer> isLoggedAndAdmin(RequestWrapper request) {
        Deferred<User, QaobeeException, Integer> deferred = new DeferredObject<>();
        isUserLogged(request).done(user -> {
            if (!habilitUtils.hasHabilitation(user, Constants.ADMIN_HABILIT)) { // are we admin ?
                deferred.reject(new QaobeeException(ExceptionCodes.NOT_ADMIN, Messages.getString("not.admin", request.getLocale())));
            }
            deferred.resolve(user);
        }).fail(deferred::reject);
        return deferred.promise();
    }
}
