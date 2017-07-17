/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.api.v1.commons.users;

import com.qaobee.hive.api.MainAPI;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.verticles.PDFVerticle;
import com.qaobee.hive.services.UserService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * The type Profile route.
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/commons/users/profile")
public class ProfileRoute extends AbstractRoute {
    private static final Logger LOG = LoggerFactory.getLogger(ProfileRoute.class);
    @Inject
    private UserService userService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        router.post("/").handler(authHandler);
        router.post("/").handler(c -> mandatoryHandler.testBodyParams(c, "_id"));
        router.post("/").handler(this::updateUser);

        router.get("/pdf").handler(authHandler);
        router.get("/pdf").handler(this::generateProfilePDF);
        return router;
    }

    /**
     * @apiDescription Generate a PDF from the current profile
     * @api {get} /api/1/commons/users/profile/pdf Generate a PDF from the current profile
     * @apiName generateProfilePDF
     * @apiGroup ProfileVerticle
     * @apiSuccess {Object} PDF { "Content-Type" : "application/pdf", 'fileserve" : "path to local pdf file" }
     * @apiHeader {String} token
     */
    private void generateProfilePDF(RoutingContext context) {
        userService.generateProfilePDF(context.user().principal(), getLocale(context), ar -> {
            if (ar.succeeded()) {
                vertx.eventBus().send(PDFVerticle.GENERATE_PDF, ar.result(), new DeliveryOptions().setSendTimeout(Constants.TIMEOUT), getPdfHandler(context));
            } else {
                utils.handleError(context, (QaobeeException) ar.cause());
            }
        });

    }

    /**
     * @apiDescription User update
     * @api {post} /api/1/commons/users/profile update user
     * @apiName updateUser
     * @apiGroup ProfileVerticle
     * @apiParam {Object} User com.qaobee.hive.business.model.commons.users.User
     * @apiSuccess {Object} User com.qaobee.hive.business.model.commons.users.User
     * @apiHeader {String} token
     */
    private void updateUser(RoutingContext context) {
        userService.updateUser(context.getBodyAsJson(), handleResponse(context));
    }

    private Handler<AsyncResult<Message<JsonObject>>> getPdfHandler(final RoutingContext context) {
        return pdfResp -> {
            try {
                if (pdfResp.failed()) {
                    throw pdfResp.cause();
                } else {
                    handleResponse(context, new JsonObject()
                            .put(HTTP.CONTENT_TYPE, PDFVerticle.CONTENT_TYPE)
                            .put(MainAPI.FILE_SERVE, pdfResp.result().body().getString(PDFVerticle.PDF)));
                }
            } catch (Throwable e) { // NOSONAR
                LOG.error(e.getMessage(), e);
                utils.handleError(context, new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e));
            }
        };
    }
}
