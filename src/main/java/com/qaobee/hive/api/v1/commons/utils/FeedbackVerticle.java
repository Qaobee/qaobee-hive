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

package com.qaobee.hive.api.v1.commons.utils;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.dao.FeedbackDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * The type Feedback verticle.
 */
@DeployableVerticle
public class FeedbackVerticle extends AbstractGuiceVerticle {

    private static final String POST_FEEDBACK = Module.VERSION + ".commons.feedback.send";
    private static final String POST_FEEDBACK_MOB = Module.VERSION + ".commons.feedback.send.mob";
    private static final String INTERNAL_FEEDBACK = "internal.feedback.send";

    private static final Logger LOG = LoggerFactory.getLogger(FeedbackVerticle.class);
    @Inject
    private FeedbackDAO feedbackDAO;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this)
                .add(POST_FEEDBACK, this::postFeedback)
                .add(POST_FEEDBACK_MOB, this::postFeedbackMob)
                .add(INTERNAL_FEEDBACK, this::internalFeeback)
                .register(startFuture);
    }

    private void internalFeeback(Message<JsonObject> message) {
        feedbackDAO.sendFeedback(message.body(), ar -> {
            if (ar.failed()) {
                LOG.error(ar.cause().getMessage(), ar.cause());
            }
        });
    }

    /**
     * @apiDescription Send feedback
     * @api {post} /api/1/commons/feedback/send update user
     * @apiName POST_FEEDBACK
     * @apiGroup FeedbackVerticle
     * @apiParam {String} param URL encoded string from feedback.js
     * @apiSuccess {Object} status boolean status
     */
    @Rule(address = POST_FEEDBACK, method = Constants.POST)
    private void postFeedback(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            final JsonObject data = new JsonObject(URLDecoder.decode(req.getBody().split("=")[1], StandardCharsets.UTF_8.toString()));
            vertx.eventBus().send(INTERNAL_FEEDBACK, data);
            utils.sendStatus(true, message);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
        }
    }

    @Rule(address = POST_FEEDBACK_MOB, method = Constants.POST)
    private void postFeedbackMob(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        final JsonObject data = new JsonObject(req.getBody());
        vertx.eventBus().send(INTERNAL_FEEDBACK, data);
        utils.sendStatus(true, message);
    }
}
