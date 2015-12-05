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
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.LevelPlan;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.PersonUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Base64;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;

/**
 * The Class ProfileVerticle.
 *
 * @author Xavier MARIN
 */
@DeployableVerticle
public class ProfileVerticle extends AbstractGuiceVerticle {

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
    /**
     * The Constant UPDATE_AVATAR.
     */
    public static final String UPDATE_AVATAR = "user.update.avatar";

    /* Injections */
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;
    @Inject
    private PersonUtils personUtils;

    /**
     * Get a message handler
     *
     * @param message Vertx message
     * @return handler
     */
    private Handler<Message<JsonObject>> getPdfHandler(final Message<String> message) {
        return new Handler<Message<JsonObject>>() {
            @Override
            public void handle(final Message<JsonObject> pdfResp) {
                final JsonObject json = new JsonObject();
                json.putString(Main.CONTENT_TYPE, PDFVerticle.CONTENT_TYPE);
                json.putString(Main.FILE_SERVE, pdfResp.body().getString(PDFVerticle.PDF));
                message.reply(json.encode());
            }
        };
    }

    /*
     * (non-Javadoc)
     *
     * @see org.vertx.java.platform.Verticle#start()
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");


        /**
         * @apiDescription User update
         * @api {post} /api/1/commons/users/profile update user
         * @apiName updateUserHandler
         * @apiGroup ProfileVerticle
         * @apiParam {Object} User com.qaobee.hive.business.model.commons.users.User
         * @apiSuccess {Object} User com.qaobee.hive.business.model.commons.users.User
         * @apiError PASSWD_EXCEPTION Password exception
         * @apiError HTTP_ERROR wrong request's method
         */
        final Handler<Message<String>> updateUserHandler = new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    final User user = Json.decodeValue(req.getBody(), User.class);
                    final JsonObject p = new JsonObject(Json.encode(personUtils.prepareUpsert(user)));
                    mongo.save(p, User.class);
                    message.reply(p.encode());
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.PASSWD_EXCEPTION, e.getMessage());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        };

        /**
         * @apiDescription Generate a PDF from the current profile
         * @api {get} /api/1/commons/users/profile/pdf Generate a PDF from the current profile
         * @apiName generatePDFHandler
         * @apiGroup ProfileVerticle
         * @apiSuccess {Object} PDF { "contenttype" : "application/pdf", 'fileserve" : "path to local pdf file" }
         * @apiError HTTP_ERROR wrong request's method
         */
        final Handler<Message<String>> generatePDFHandler = new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    final User user = req.getUser();

                    final JsonObject juser = new JsonObject(Json.encode(user));
                    if(StringUtils.isNoneBlank(user.getAvatar())) {
                        juser.putString("avatar", new String(Base64.decode(user.getAvatar())));
                    }
                    juser.putString("birthdate", utils.formatDate(user.getBirthdate(), DateFormat.MEDIUM, DateFormat.MEDIUM, req.getLocale()));
                    final JsonObject pdfReq = new JsonObject();
                    pdfReq.putString(PDFVerticle.FILE_NAME, user.getAccount().getLogin());
                    pdfReq.putString(PDFVerticle.TEMPLATE, "profile/profile.html");
                    pdfReq.putObject(PDFVerticle.DATA, juser);

                    vertx.eventBus().send(PDFVerticle.GENERATE_PDF, pdfReq, getPdfHandler(message));
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                }
            }
        };

        /**
         * @apiDescription Generate a PDF from the bill of the current profile
         * @api {get} /api/1/commons/users/profile/billpdf Generate a PDF from the bill of the current profile
         * @apiName generateBillPDFHandler
         * @apiGroup ProfileVerticle
         * @apiParam {String} plan plan type
         * @apiSuccess {Object} PDF { "contenttype" : "application/pdf", 'fileserve" : "path to local pdf file" }
         * @apiError HTTP_ERROR wrong request's method
         */
        final Handler<Message<String>> generateBillPDFHandler = new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    final User p = req.getUser();
                    final LevelPlan plan = LevelPlan.valueOf(req.getParams().get("plan").get(0));
                    boolean found = false;
                    for (final Plan planItem : p.getAccount().getListPlan()) {
                        if (planItem.getLevelPlan().equals(plan)) {
                            found = true;
                            final JsonObject juser = new JsonObject(Json.encode(p));
                            juser.putString("avatar", new String(Base64.decode(p.getAvatar())));
                            juser.putString("birthdate", utils.formatDate(p.getBirthdate(), DateFormat.MEDIUM, DateFormat.MEDIUM, req.getLocale()));
                            juser.putString("paidDate", utils.formatDate(planItem.getPaidDate(), DateFormat.MEDIUM, DateFormat.MEDIUM, req.getLocale()));
                            final JsonObject pdfReq = new JsonObject();
                            pdfReq.putString(PDFVerticle.FILE_NAME, planItem.getPaymentId() + "-Qaobee");
                            pdfReq.putString(PDFVerticle.TEMPLATE, "billing/bill.html");
                            pdfReq.putObject(PDFVerticle.DATA, juser);
                            vertx.eventBus().send(PDFVerticle.GENERATE_PDF, pdfReq, getPdfHandler(message));
                        }
                        if (!found) {
                            message.reply();
                        }
                    }
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                }
            }
        };

        // Update a person's avatar
        final Handler<Message<String>> updateAvatar = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final JsonObject req = new JsonObject(message.body());
                JsonObject jsonperson;
                try {
                    jsonperson = mongo.getById(req.getString("uid"), User.class);
                    jsonperson.putString("avatar", req.getString("filename"));
                    mongo.save(jsonperson, User.class);
                    message.reply(jsonperson);
                } catch (final EncodeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (QaobeeException e) {
                    utils.sendError(message, e);
                }
            }
        };

		/*
         * handlers registration
		 */
        vertx.eventBus().registerHandler(UPDATE, updateUserHandler);
        vertx.eventBus().registerHandler(GENERATE_PDF, generatePDFHandler);
        vertx.eventBus().registerHandler(GENERATE_BILL_PDF, generateBillPDFHandler);
        vertx.eventBus().registerHandler(UPDATE_AVATAR, updateAvatar);
    }
}
