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
import com.qaobee.hive.services.FeedbackService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * The type Feedback route.
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/commons/feedback")
public class FeedbackRoute extends AbstractRoute {

    private static final Logger LOG = LoggerFactory.getLogger(FeedbackRoute.class);
    @Inject
    private FeedbackService feedbackService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        router.post("/send").handler(this::postFeedback);
        router.post("/send/mob").handler(this::postFeedbackMob);

        return router;
    }

    private void internalFeeback(JsonObject data) {
        feedbackService.sendFeedback(data, ar -> {
            if (ar.failed()) {
                LOG.error(ar.cause().getMessage(), ar.cause());
            }
        });
    }

    /**
     * @apiDescription Send feedback
     * @api {post} /api/1/commons/feedback/send update user
     * @apiName POST_FEEDBACK
     * @apiGroup FeedbackRoute
     * @apiParam {String} param URL encoded string from feedback.js
     * @apiSuccess {Object} status boolean status
     */
    private void postFeedback(RoutingContext context) {
        try {
            final JsonObject data = new JsonObject(URLDecoder.decode(context.getBodyAsString().split("=")[1], StandardCharsets.UTF_8.toString()));
            internalFeeback(data);
            handleStatus(true, context);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
            utils.handleError(context, new QaobeeException( ExceptionCodes.INTERNAL_ERROR, e.getMessage()));
        }
    }

    private void postFeedbackMob(RoutingContext context) {
        internalFeeback(context.getBodyAsJson());
        handleStatus(true, context);
    }
}
