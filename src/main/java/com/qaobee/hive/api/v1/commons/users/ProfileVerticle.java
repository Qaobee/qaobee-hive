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

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.api.v1.commons.utils.PDFVerticle;
import com.qaobee.hive.dao.UserDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

/**
 * The Class ProfileVerticle.
 *
 * @author Xavier MARIN
 */
@DeployableVerticle
public class ProfileVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(ProfileVerticle.class);
    /**
     * The Constant UPDATE.
     */
    public static final String UPDATE = Module.VERSION + ".commons.users.profile";
    /**
     * The Constant GENERATE_PDF.
     */
    public static final String GENERATE_PDF = Module.VERSION + ".commons.users.profile.pdf";
    @Inject
    private UserDAO userDAO;

    @Override
    public void start() {
        super.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug(this.getClass().getName() + " started");
        }
        vertx.eventBus().consumer(UPDATE, this::updateUser);
        vertx.eventBus().consumer(GENERATE_PDF, this::generateProfilePDF);
    }

    /**
     * @apiDescription Generate a PDF from the current profile
     * @api {get} /api/1/commons/users/profile/pdf Generate a PDF from the current profile
     * @apiName generateProfilePDF
     * @apiGroup ProfileVerticle
     * @apiSuccess {Object} PDF { "Content-Type" : "application/pdf", 'fileserve" : "path to local pdf file" }
     * @apiHeader {String} token
     */
    @Rule(address = GENERATE_PDF, method = Constants.GET, logged = true)
    private void generateProfilePDF(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        vertx.eventBus().send(PDFVerticle.GENERATE_PDF, userDAO.generateProfilePDF(req.getUser(), req.getLocale()),
                new DeliveryOptions().setSendTimeout(Constants.TIMEOUT), getPdfHandler(message));
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
    @Rule(address = UPDATE, method = Constants.POST, logged = true, mandatoryParams = "_id", scope = Rule.Param.BODY)
    private void updateUser(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, userDAO.updateUser(new JsonObject(req.getBody())));
    }

    private Handler<AsyncResult<Message<JsonObject>>> getPdfHandler(final Message<String> message) {
        return pdfResp -> {
            try {
                if (pdfResp.failed()) {
                    throw pdfResp.cause();
                } else {
                    message.reply(new JsonObject()
                            .put(CONTENT_TYPE, PDFVerticle.CONTENT_TYPE)
                            .put(Main.FILE_SERVE, pdfResp.result().body().getString(PDFVerticle.PDF))
                            .encode());
                }
            } catch (Throwable e) { // NOSONAR
                LOG.error(e.getMessage(), e);
                utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
            }
        };
    }
}
