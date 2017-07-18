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
package com.qaobee.hive.dao.impl;

import com.qaobee.hive.dao.Utils;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
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
    private static final String MISSING_PARAMS = "Missing mandatory parameters : ";


    private static HashMap<String, List<String>> toMap(MultiMap multiMap) {
        HashMap<String, List<String>> map = new HashMap<>();
        multiMap.entries().forEach(e -> {
            if (!map.containsKey(e.getKey())) {
                map.put(e.getKey(), new ArrayList<>());
            }
            map.get(e.getKey()).add(e.getValue());
        });
        return map;
    }

    @Override
    public void sendError(final Message<JsonObject> message, final QaobeeException e) {
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
    public void sendStatus(final boolean b, final Message<JsonObject> message) {
        message.reply(new JsonObject().put(Constants.STATUS, b));
    }

    @Override
    public void sendStatus(boolean b, String cause, Message<JsonObject> message) {
        final JsonObject jsonResp = new JsonObject()
                .put(Constants.STATUS, b)
                .put("cause", cause);
        message.reply(jsonResp);
    }

    @Override
    public void testMandatoryParams(Map<String, List<String>> map, String... fields) throws QaobeeException {
        final List<String> missingFields = new ArrayList<>();
        for (final String field : fields) {
            if (!map.containsKey(field) || map.get(field) == null || StringUtils.isBlank(map.get(field).get(0))) {
                missingFields.add(field);
            }
        }
        if (!missingFields.isEmpty()) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, MISSING_PARAMS + missingFields);
        }
    }

    @Override
    public void testMandatoryParams(JsonObject payload, final String... fields) throws QaobeeException {
        JsonObject body = Optional.ofNullable(payload).orElse(new JsonObject());
        final List<String> missingFields = new ArrayList<>();
        for (String field : fields) {
            if (!body.containsKey(field) || body.getValue(field) == null || (body.getValue(field) instanceof String && StringUtils.isEmpty(body.getString(field)))) {
                missingFields.add(field);
            }
        }
        if (!missingFields.isEmpty()) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, MISSING_PARAMS + missingFields);
        }
    }

    @Override
    public void testMandatoryParams(RoutingContext context, String... fields) {
        try {
            testMandatoryParams(context.getBodyAsJson(), fields);
        } catch (DecodeException e) {
            LOG.warn(e.getMessage(), e);
            testMandatoryParams(new JsonObject(), fields);
        }
    }

    @Override
    public void testMandatoryParams(MultiMap params, RoutingContext context, String... fields) {
        testMandatoryParams(toMap(params), fields);
    }

    @Override
    public void handleError(RoutingContext context, QaobeeException e) {
        LOG.error(String.format("[ %s : %d ] %s", e.getCode().name(), e.getCode().getCode(), e.getMessage()), e);
        context.response().putHeader(HTTP.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .setStatusCode(e.getCode().getCode())
                .end(new JsonObject()
                        .put(Constants.STATUS, false)
                        .put("message", e.getMessage())
                        .put("code", e.getCode().name())
                        .encode()
                );
    }

    @Override
    public JsonObject getMinimal(final List<String> minimal) {
        final JsonObject map = new JsonObject();
        for (final String key : minimal) {
            map.put(key, Boolean.TRUE);
        }
        return map;
    }
}
