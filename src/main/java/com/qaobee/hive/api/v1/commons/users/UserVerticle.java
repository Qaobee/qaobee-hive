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
package com.qaobee.hive.api.v1.commons.users;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.tools.Params;
import com.qaobee.hive.technical.tools.PasswordEncryptionService;
import com.qaobee.hive.technical.utils.MailUtils;
import com.qaobee.hive.technical.utils.PersonUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.api.v1.commons.utils.TemplatesVerticle;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.apache.commons.lang.StringUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Base64;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

/**
 * The type User verticle.
 */
@DeployableVerticle(isWorker = true)
public class UserVerticle extends AbstractGuiceVerticle {

    /**
     * The Constant LOGIN.
     */
    public static final String LOGIN = "resthandler.api.v1.commons.user.login";
    /**
     * The Constant LOGOUT.
     */
    public static final String LOGOUT = "resthandler.api.v1.commons.user.logout";
    /**
     * The Constant PASSWD_RENEW.
     */
    public static final String PASSWD_RENEW = "resthandler.api.v1.commons.user.newpasswd";
    /**
     * The Constant PASSWD_RENEW_CHK.
     */
    public static final String PASSWD_RENEW_CHK = "resthandler.api.v1.commons.user.passwdcheck";
    /**
     * The Constant PASSWD_RESET.
     */
    public static final String PASSWD_RESET = "resthandler.api.v1.commons.user.resetPasswd";

    /**
     * The Mongo.
     */
    @Inject
    private MongoDB mongo;
    /**
     * The Mail utils.
     */
    @Inject
    private MailUtils mailUtils;
    /**
     * The Utils.
     */
    @Inject
    private Utils utils;
    /**
     * The Password encryption service.
     */
    @Inject
    private PasswordEncryptionService passwordEncryptionService;
    /**
     * The Person utils.
     */
    @Inject
    private PersonUtils personUtils;

    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");
        final EventBus eb = vertx.eventBus();
        /**
         * @apiDescription Login user
         * @api {post} /rest/api/v1/commons/user/login resthandler.api.v1.commons.user.login
         * @apiName loginHandler
         * @apiGroup LoginVerticle
         * @apiParam {String} login login (user.username)
         * @apiParam {String} passwd password
         * @apiSuccess {Object} user com.qaobee.hive.business.model.commons.users.User
         * @apiError PASSWD_EXCEPTION wrong password encoding
         * @apiError BAD_LOGIN wrong login or password
         * @apiError NON_ACTIVE the user is not active
         * @apiError HTTP_ERROR wrong request method
         */
        final Handler<Message<String>> loginHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    final JsonObject infos = new JsonObject(req.getBody());

                    if (StringUtils.isBlank(infos.getString("login")) || StringUtils.isBlank(infos.getString("passwd"))) {
                        final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                        container.logger().error(e.getMessage(), e);
                        utils.sendError(message, e);
                    } else {
                        final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", infos.getString("login")).get(), null, null, 0, 0, User.class);

                        if (res.size() != 1) {
                            final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                            container.logger().error(e.getMessage(), e);
                            utils.sendError(message, e);
                        } else {
                            // we take the first one (should be only one)
                            final JsonObject jsonPerson = res.get(0);
                            final User user = Json.decodeValue(jsonPerson.encode(), User.class);
                            try {
                                final byte[] encryptedAttemptedPassword = passwordEncryptionService.getEncryptedPassword(infos.getString("passwd"), user.getAccount().getSalt());
                                if (!Base64.encodeBytes(encryptedAttemptedPassword).equals(Base64.encodeBytes(user.getAccount().getPassword()))) {
                                    final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                                    container.logger().error(e.getMessage(), e);
                                    utils.sendError(message, e);
                                } else {
                                    if (user.getAccount().isActive()) {
                                        user.getAccount().setToken(UUID.randomUUID().toString());
                                        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                                        mongo.save(user);
                                        String result = Json.encode(user);
                                        container.logger().debug(result);
                                        message.reply(result);
                                    } else {
                                        utils.sendError(message, ExceptionCodes.NON_ACTIVE, Messages.getString("popup.warning.unregistreduser", req.getLocale()));
                                    }
                                }
                            } catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
                                container.logger().error(e.getMessage(), e);
                                utils.sendError(message, ExceptionCodes.PASSWD_EXCEPTION, e.getMessage());
                            } catch (final EncodeException e) {
                                container.logger().error(e.getMessage(), e);
                                utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                            } catch (final QaobeeException e) {
                                container.logger().error(e.getMessage(), e);
                                utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
                            }
                        }

                    }
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                }
            }
        };

        /**
         * @apiDescription User logout
         * @api {get} /rest/api/v1/user/logout resthandler.api.v1.user.logout
         * @apiName logoutHandler
         * @apiGroup LoginVerticle
         * @apiHeader {String} token
         * @apiSuccess {Object} status {"status", true|false}
         * @apiError HTTP_ERROR wrong request method
         */
        final Handler<Message<String>> logoutHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.token", req.getHeaders().get("token").get(0)).get(), null, null, 0, 0, User.class);
                    if (res.size() != 1) {
                        utils.sendStatus(false, message);
                    } else {
                        // we take the first one (should be only one)
                        final JsonObject jsonperson = res.get(0);
                        final User user = Json.decodeValue(jsonperson.encode(), User.class);
                        user.getAccount().setToken(null);
                        user.getAccount().setTokenRenewDate(0l);
                        mongo.save(user);
                        utils.sendStatus(true, message);
                    }
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final EncodeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
                }
            }
        };

        /**
         * @apiDescription Mail generation for password renew
         * @api {post} /rest/api/v1/user/newpasswd resthandler.api.v1.user.newpasswd
         * @apiName newPasswdHandler
         * @apiGroup LoginVerticle
         * @apiParam {String} login user login
         * @apiSuccess {Object} status
         * @apiError HTTP_ERROR wrong request method
         * @apiError MAIL_EXCEPTION email problem
         */
        final Handler<Message<String>> newPasswdHandler = new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    final JsonObject infos = new JsonObject(req.getBody());
                    final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", infos.getString("login")).get(), null, null, 0, 0, User.class);
                    if (res.size() != 1) {
                        final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("login.wronglogin", req.getLocale()));
                        container.logger().error(e.getMessage(), e);
                        utils.sendError(message, e);
                    } else {
                        final JsonObject jsonperson = res.get(0);
                        final User user = Json.decodeValue(jsonperson.encode(), User.class);
                        user.getAccount().setActivationPasswd(UUID.randomUUID().toString().replaceAll("-", ""));
                        mongo.save(user);

                        final JsonObject tplReq = new JsonObject();
                        tplReq.putString(TemplatesVerticle.TEMPLATE, "newPasswd.html");
                        tplReq.putObject(TemplatesVerticle.DATA, mailUtils.generateNewpasswdBody(user, req.getLocale()));

                        eb.send("templates", tplReq, new Handler<Message<JsonObject>>() {
                            @Override
                            public void handle(final Message<JsonObject> tplResp) {
                                final String tplRes = tplResp.body().getString("result");
                                final JsonObject emailReq = new JsonObject();
                                emailReq.putString("from", Params.getString("mail.from"));
                                emailReq.putString("to", user.getContact().getEmail());
                                emailReq.putString("subject", Messages.getString("mail.newpasswd.subject"));
                                emailReq.putString("content_type", "text/html");
                                emailReq.putString("body", tplRes);
                                eb.publish("mailer.mod", emailReq);
                                final JsonObject resp = new JsonObject();
                                resp.putBoolean("status", true);
                                message.reply(resp.encode());
                            }
                        });
                    }

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.MAIL_EXCEPTION, e.getMessage());
                } catch (final EncodeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
                }
            }
        };

        /**
         * @apiDescription Check activation code supplied in the renew password email
         * @api {get} /rest/api/v1/user/passwdcheck?code=:code&id=:id resthandler.api.v1.user.passwdcheck
         * @apiParam {String} code Activation code
         * @apiParam {String} id Person id
         * @apiName passwdCheckHandler
         * @apiGroup LoginVerticle
         * @apiSuccess {Object} status {"status" : true|false, "user" : Object(user)}
         * @apiError HTTP_ERROR wrong request method
         */
        final Handler<Message<String>> passwdCheckHandler = new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    final String id = req.getParams().get("id").get(0);
                    final String code = req.getParams().get("code").get(0);
                    JsonObject jsonUser = mongo.getById(id, User.class);
                    final User user = Json.decodeValue(jsonUser.encode(), User.class);
                    if (code.equals(user.getAccount().getActivationPasswd())) {
                        final JsonObject jsonResp = new JsonObject();
                        jsonResp.putBoolean("status", true);
                        jsonResp.putObject("user", mongo.getById(id, User.class));
                        message.reply(jsonResp.encode());
                    } else {
                        utils.sendStatus(false, message);
                    }
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (QaobeeException e) {
                    utils.sendError(message, e);
                }
            }
        };

        /**
         * @apiDescription Update password after renew ask
         * @api {post} /rest/api/v1/user/resetPasswd resthandler.api.v1.user.resetPasswd
         * @apiParam {Object} data {id, code, passwd}
         * @apiName resetPasswdHandler
         * @apiGroup LoginVerticle
         * @apiSuccess {Object} status {"status", true|false}
         * @apiError HTTP_ERROR wrong request method
         */
        final Handler<Message<String>> resetPasswdHandler = new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    final JsonObject json = new JsonObject(req.getBody());
                    final String id = json.getString("id");
                    final String code = json.getString("code");
                    final String passwd = json.getString("passwd");
                    final JsonObject catcha = json.getObject("captcha");
                    final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
                    reCaptcha.setPrivateKey(Params.getString("recaptcha.pkey"));
                    final String challenge = catcha.getString("challenge");
                    final String uresponse = catcha.getString("response");
                    final ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(Params.getString("recaptcha.site"), challenge, uresponse);
                    if (!reCaptchaResponse.isValid()) {
                        utils.sendError(message, ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha");
                    } else {
                        final User user = Json.decodeValue(mongo.getById(id, User.class).encode(), User.class);
                        if (code.equals(user.getAccount().getActivationPasswd())) {
                            user.getAccount().setPasswd(passwd);
                            mongo.save(personUtils.prepareUpsert(user));
                            utils.sendStatus(true, message);
                        } else {
                            utils.sendStatus(false, message);
                        }
                    }
                } catch (final EncodeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                } catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.PASSWD_EXCEPTION, e.getMessage());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        };

		/*
         * Handlers declaration
		 */
        eb.registerHandler(LOGIN, loginHandler);
        eb.registerHandler(LOGOUT, logoutHandler);
        eb.registerHandler(PASSWD_RENEW, newPasswdHandler);
        eb.registerHandler(PASSWD_RENEW_CHK, passwdCheckHandler);
        eb.registerHandler(PASSWD_RESET, resetPasswdHandler);
    }

}
