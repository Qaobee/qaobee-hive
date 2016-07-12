/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2014] Qaobee
 * All Rights Reserved.
 * <p/>
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
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
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

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
    /**
     * The Constant GENERATE_BILL_PDF.
     */
    public static final String GENERATE_BILL_PDF = Module.VERSION + ".commons.users.profile.billpdf";
    @Inject
    private UserDAO userDAO;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(UPDATE, this::updateUser)
                .registerHandler(GENERATE_PDF, this::generateProfilePDF)
                .registerHandler(GENERATE_BILL_PDF, this::generateBillPDF);
    }

    /**
     * @apiDescription Generate a PDF from the bill of the current profile
     * @api {get} /api/1/commons/users/profile/billpdf?id= Generate a PDF from the bill of the current profile
     * @apiName generateBillPDF
     * @apiGroup ProfileVerticle
     * @apiParam {String} plan plan type
     * @apiSuccess {Object} PDF { "Content-Type" : "application/pdf", 'fileserve" : "path to local pdf file" }
     * @apiHeader {String} token
     */
    @Rule(address = GENERATE_BILL_PDF, method = Constants.GET, logged = true, mandatoryParams = {"plan_id", "pay_id"}, scope = Rule.Param.REQUEST)
    private void generateBillPDF(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            vertx.eventBus().sendWithTimeout(PDFVerticle.GENERATE_PDF, userDAO.generateBillPDF(req.getUser(),
                    req.getParams().get("pay_id").get(0),
                    req.getParams().get("plan_id").get(0),
                    req.getLocale()), 10000L, getPdfHandler(message));
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
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
        vertx.eventBus().sendWithTimeout(PDFVerticle.GENERATE_PDF, userDAO.generateProfilePDF(req.getUser(), req.getLocale()), 10000L, getPdfHandler(message));
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
    @Rule(address = UPDATE, method = Constants.POST, logged = true, mandatoryParams = {"_id"}, scope = Rule.Param.BODY)
    private void updateUser(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(userDAO.updateUser(new JsonObject(req.getBody())).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    private Handler<AsyncResult<Message<JsonObject>>> getPdfHandler(final Message<String> message) {
        return pdfResp -> {
            if (pdfResp.failed()) {
                LOG.error(pdfResp.cause().getMessage(), pdfResp.cause());
                utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, pdfResp.cause().getMessage());
            } else {
                message.reply(new JsonObject()
                        .putString(CONTENT_TYPE, PDFVerticle.CONTENT_TYPE)
                        .putString(Main.FILE_SERVE, pdfResp.result().body().getString(PDFVerticle.PDF))
                        .encode());
            }
        };
    }
}
