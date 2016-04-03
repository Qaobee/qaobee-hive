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
import com.qaobee.hive.business.model.commons.users.account.Payment;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.PasswordEncryptionService;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.AsyncResult;
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
    private static final Logger LOG = LoggerFactory.getLogger(ProfileVerticle.class);
    /* Injections */
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;
    @Inject
    private PasswordEncryptionService passwordEncryptionService;

    /**
     * Get a message handler
     *
     * @param message Vertx message
     * @return handler
     */
    private Handler<AsyncResult<Message<JsonObject>>> getPdfHandler(final Message<String> message) {
        return new Handler<AsyncResult<Message<JsonObject>>>() {
            @Override
            public void handle(final AsyncResult<Message<JsonObject>> pdfResp) {
                if (pdfResp.failed()) {
                    LOG.error(pdfResp.cause().getMessage(), pdfResp.cause());
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, pdfResp.cause().getMessage());
                } else {
                    final JsonObject json = new JsonObject();
                    json.putString(Main.CONTENT_TYPE, PDFVerticle.CONTENT_TYPE);
                    json.putString(Main.FILE_SERVE, pdfResp.result().body().getString(PDFVerticle.PDF));
                    message.reply(json.encode());
                }
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
        LOG.debug(this.getClass().getName() + " started");

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
                    JsonObject u = new JsonObject(req.getBody());
                    final User user = Json.decodeValue(req.getBody(), User.class);
                    if (StringUtils.isNotBlank(user.getAccount().getPasswd())) {
                        final byte[] salt = passwordEncryptionService.generateSalt();
                        user.getAccount().setSalt(salt);
                        user.getAccount().setPassword(passwordEncryptionService.getEncryptedPassword(user.getAccount().getPasswd(), salt));
                        user.getAccount().setPasswd(null);
                        u.putObject("account", new JsonObject(Json.encode(user.getAccount())));
                    } else {
                        JsonObject p = mongo.getById(user.get_id(), User.class.getSimpleName());
                        u.putObject("account", p.getObject("account"));
                    }

                    mongo.save(u, User.class.getSimpleName());
                    message.reply(u.encode());
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.PASSWD_EXCEPTION, e.getMessage());
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
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
                    utils.isUserLogged(req);
                    final User user = req.getUser();

                    final JsonObject juser = new JsonObject();
                    if (StringUtils.isNoneBlank(user.getAvatar())) {
                        juser.putString("avatar", new String(Base64.decode(user.getAvatar())));
                    }
                    juser.putString("birthdate", utils.formatDate(user.getBirthdate(), DateFormat.MEDIUM, DateFormat.MEDIUM, req.getLocale()));
                    if (user.getAddress() != null) {
                        if (StringUtils.isNotBlank(user.getAddress().getFormatedAddress())) {
                            juser.putString("address", user.getAddress().getFormatedAddress());
                        } else {
                            juser.putString("address", user.getAddress().getPlace() + " " + user.getAddress().getZipcode() + " " + user.getAddress().getCity() + " " + user.getAddress().getCountry());
                        }
                    }
                    juser.putString("firstname", user.getFirstname());
                    juser.putString("name", user.getName());
                    juser.putString("username", user.getAccount().getLogin());
                    juser.putString("phoneNumber", user.getContact().getHome());
                    juser.putString("email", user.getContact().getEmail());

                    final JsonObject pdfReq = new JsonObject();
                    pdfReq.putString(PDFVerticle.FILE_NAME, user.getAccount().getLogin());
                    pdfReq.putString(PDFVerticle.TEMPLATE, "profile/profile.ftl");
                    pdfReq.putObject(PDFVerticle.DATA, juser);

                    vertx.eventBus().sendWithTimeout(PDFVerticle.GENERATE_PDF, pdfReq, 10000L, getPdfHandler(message));
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        };

        /**
         * @apiDescription Generate a PDF from the bill of the current profile
         * @api {get} /api/1/commons/users/profile/billpdf?id= Generate a PDF from the bill of the current profile
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
                    utils.testMandatoryParams(req.getParams(), "plan_id", "pay_id");
                    utils.isUserLogged(req);
                    final User user = req.getUser();
                    Plan planItem = user.getAccount().getListPlan().get(Integer.parseInt(req.getParams().get("plan_id").get(0)));
                    Payment payment = null;
                    for(Payment p : planItem.getShippingList()) {
                        if(req.getParams().get("pay_id").get(0).equals(p.getId())) {
                            payment = p;
                        }
                    }
                    if(payment !=null) {
                        final JsonObject juser = new JsonObject();
                        if (StringUtils.isNoneBlank(user.getAvatar())) {
                            juser.putString("avatar", new String(Base64.decode(user.getAvatar())));
                        }
                        juser.putString("birthdate", utils.formatDate(user.getBirthdate(), DateFormat.MEDIUM, DateFormat.MEDIUM, req.getLocale()));
                        if (user.getAddress() != null) {
                            if (StringUtils.isNotBlank(user.getAddress().getFormatedAddress())) {
                                juser.putString("address", user.getAddress().getFormatedAddress());
                            } else {
                                juser.putString("address", user.getAddress().getPlace() + " " + user.getAddress().getZipcode() + " " + user.getAddress().getCity() + " " + user.getAddress().getCountry());
                            }
                        }
                        juser.putString("firstname", user.getFirstname());
                        juser.putString("name", user.getName());
                        juser.putString("username", user.getAccount().getLogin());
                        juser.putString("phoneNumber", user.getContact().getHome());
                        juser.putString("email", user.getContact().getEmail());
                        juser.putString("paidDate", utils.formatDate(payment.getPaidDate() / 1000L, DateFormat.MEDIUM, DateFormat.MEDIUM, req.getLocale()));
                        juser.putString("paymentId", payment.getPaymentId());
                        juser.putString("plan", planItem.getLevelPlan().name());
                        juser.putString("amountPaid", String.valueOf(payment.getAmountPaid()));
                        final JsonObject pdfReq = new JsonObject();
                        pdfReq.putString(PDFVerticle.FILE_NAME, payment.getPaymentId() + "-Qaobee");
                        pdfReq.putString(PDFVerticle.TEMPLATE, "billing/bill.ftl");
                        pdfReq.putObject(PDFVerticle.DATA, juser);
                        vertx.eventBus().sendWithTimeout(PDFVerticle.GENERATE_PDF, pdfReq, 10000L, getPdfHandler(message));
                    } else {
                        throw new IllegalArgumentException("unknown bill");
                    }
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.MANDATORY_FIELD, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
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
                    LOG.error(e.getMessage(), e);
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
