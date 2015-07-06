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


import com.englishtown.promises.Promise;
import com.englishtown.promises.Runnable;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.api.v1.commons.settings.SeasonVerticle;
import com.qaobee.hive.api.v1.commons.utils.TemplatesVerticle;
import com.qaobee.hive.api.v1.sandbox.config.SandBoxVerticle;
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
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.apache.commons.lang.StringUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.eventbus.ReplyException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Base64;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Collections;
import java.util.UUID;

/**
 * The type User verticle.
 */
@DeployableVerticle(isWorker = true)
public class UserVerticle extends AbstractGuiceVerticle {

    /**
     * The Constant LOGIN.
     */
    public static final String LOGIN = Module.VERSION + ".commons.user.login";
    /**
     * The constant LOGIN_BY_TOKEN.
     */
    public static final String LOGIN_BY_TOKEN = Module.VERSION + ".commons.user.sso";
    /**
     * The Constant LOGOUT.
     */
    public static final String LOGOUT = Module.VERSION + ".commons.user.logout";
    /**
     * The Constant PASSWD_RENEW.
     */
    public static final String PASSWD_RENEW = Module.VERSION + ".commons.user.newpasswd";
    /**
     * The Constant PASSWD_RENEW_CHK.
     */
    public static final String PASSWD_RENEW_CHK = Module.VERSION + ".commons.user.passwdcheck";
    /**
     * The Constant PASSWD_RESET.
     */
    public static final String PASSWD_RESET = Module.VERSION + ".commons.user.resetPasswd";
    /**
     * The Constant CURRENT.
     */
    public static final String CURRENT = Module.VERSION + ".commons.user.current";
    /**
     * The Constant META.
     */
    public static final String META = Module.VERSION + ".commons.user.meta";
    /**
     * The constant SEASONS_INFO.
     */
    public static final String SEASONS_INFO = Module.VERSION + ".commons.user.season";
    /**
     * The Constant USER_INFO
     */
    public static final String USER_INFO = Module.VERSION + ".commons.user.user";
    
    /* List of parameters */
    /**
     * User login
     */
    public static final String PARAM_LOGIN = "login";
    /**
     * The constant PARAM_COUNTRY_ID.
     */
    public static final String PARAM_COUNTRY_ID = "country";
    /**
     * User password
     */
    public static final String PARAM_PWD = "password";
    /**
     * The constant MOBILE_TOKEN.
     */
    public static final String MOBILE_TOKEN = "mobileToken";

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
        /**
         * @apiDescription Login user
         * @api {post} /api/1/commons/user/login Login user
         * @apiVersion 0.1.0
         * @apiName loginHandler
         * @apiGroup User API
         * @apiParam {String} login login (user.username)
         * @apiParam {String} password password
         * @apiParam {String} [mobileToken] optionnal mobile token for SSO
         * @apiSuccess {Object} user com.qaobee.hive.business.model.commons.users.User
         * @apiError PASSWD_EXCEPTION wrong password encoding
         * @apiError BAD_LOGIN wrong login or password
         * @apiError NON_ACTIVE the user is not active
         * @apiError HTTP_ERROR wrong request method
         */
        vertx.eventBus().registerHandler(LOGIN, new Handler<Message<String>>() {
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

                    if (StringUtils.isBlank(infos.getString(PARAM_LOGIN)) || StringUtils.isBlank(infos.getString(PARAM_PWD))) {
                        final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                        container.logger().error(e.getMessage(), e);
                        utils.sendError(message, e);
                    } else {
                        final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", infos.getString(PARAM_LOGIN)).get(), null, null, 0, 0, User.class);
                        if (res.size() != 1) {
                            final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                            container.logger().error(e.getMessage(), e);
                            utils.sendError(message, e);
                        } else {
                            // we take the first one (should be only one)
                            final JsonObject jsonPerson = res.get(0);
                            final User user = Json.decodeValue(jsonPerson.encode(), User.class);
                            final byte[] encryptedAttemptedPassword = passwordEncryptionService.getEncryptedPassword(infos.getString(PARAM_PWD), user.getAccount().getSalt());
                            if (!Base64.encodeBytes(encryptedAttemptedPassword).equals(Base64.encodeBytes(user.getAccount().getPassword()))) {
                                final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                                container.logger().error(e.getMessage(), e);
                                utils.sendError(message, e);
                            } else {
                                if (user.getAccount().isActive()) {
                                    user.getAccount().setToken(UUID.randomUUID().toString());
                                    user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                                    if (infos.containsField(MOBILE_TOKEN)) {
                                        user.getAccount().setMobileToken(infos.getString(MOBILE_TOKEN));
                                    }
                                    mongo.save(user);
                                    JsonObject jUser = new JsonObject(Json.encode(user));
                                    jUser.getObject("account").removeField("passwd");
                                    jUser.getObject("account").removeField("password");
                                    jUser.getObject("account").removeField("salt");
                                    message.reply(jUser.toString());
                                } else {
                                    utils.sendError(message, ExceptionCodes.NON_ACTIVE, Messages.getString("popup.warning.unregistreduser", req.getLocale()));
                                }
                            }
                        }

                    }
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription User logout
         * @api {get} /api/1/commons/user/logout User logout
         * @apiVersion 0.1.0
         * @apiName logoutHandler
         * @apiGroup User API
         * @apiHeader {String} token
         * @apiSuccess {Object} status {"status", true|false}
         * @apiError HTTP_ERROR wrong request method
         */
        vertx.eventBus().registerHandler(LOGOUT, new Handler<Message<String>>() {
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
                        user.getAccount().setMobileToken(null);
                        mongo.save(user);
                        utils.sendStatus(true, message);
                    }
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Mail generation for password renew
         * @api {post} /api/1/commons/user/newpasswd Password renew
         * @apiVersion 0.1.0
         * @apiName newPasswdHandler
         * @apiGroup User API
         * @apiParam {String} login user login
         * @apiSuccess {Object} status
         * @apiError HTTP_ERROR wrong request method
         * @apiError MAIL_EXCEPTION email problem
         */
        vertx.eventBus().registerHandler(PASSWD_RENEW, new Handler<Message<String>>() {
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
                    final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", infos.getString(PARAM_LOGIN)).get(), null, null, 0, 0, User.class);
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

                        vertx.eventBus().send(TemplatesVerticle.TEMPLATE_GENERATE, tplReq, new Handler<Message<JsonObject>>() {
                            @Override
                            public void handle(final Message<JsonObject> tplResp) {
                                final String tplRes = tplResp.body().getString("result");
                                final JsonObject emailReq = new JsonObject();
                                emailReq.putString("from", Params.getString("mail.from"));
                                emailReq.putString("to", user.getContact().getEmail());
                                emailReq.putString("subject", Messages.getString("mail.newpasswd.subject"));
                                emailReq.putString("content_type", "text/html");
                                emailReq.putString("body", tplRes);
                                vertx.eventBus().publish("mailer.mod", emailReq);
                                final JsonObject resp = new JsonObject();
                                resp.putBoolean("status", true);
                                message.reply(resp.encode());
                            }
                        });
                    }

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Check activation code supplied in the renew password email
         * @api {get} /api/v1/commons/user/passwdcheck Check activation code
         * @apiParam {String} code Activation code
         * @apiParam {String} id Person id
         * @apiVersion 0.1.0
         * @apiName passwdCheckHandler
         * @apiGroup User API
         * @apiSuccess {Object} status {"status" : true|false, "user" : Object(user)}
         * @apiError HTTP_ERROR wrong request method
         */
        vertx.eventBus().registerHandler(PASSWD_RENEW_CHK, new Handler<Message<String>>() {
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
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Update password after renew ask
         * @api {post} /api/v1/commons/user/resetPasswd Update password
         * @apiParam {Object} data {id, code, passwd}
         * @apiVersion 0.1.0
         * @apiName resetPasswdHandler
         * @apiGroup User API
         * @apiSuccess {Object} status {"status", true|false}
         * @apiError HTTP_ERROR wrong request method
         */
        vertx.eventBus().registerHandler(PASSWD_RESET, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    final JsonObject json = new JsonObject(req.getBody());
                    final String id = json.getString("id");
                    final String code = json.getString("code");
                    final String passwd = json.getString("passwd");
                    final boolean injunit = json.getBoolean("junit", false);
                    ReCaptchaResponse reCaptchaResponse = null;
                    if (!injunit) {
                        final JsonObject catcha = json.getObject("captcha");
                        final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
                        reCaptcha.setPrivateKey(Params.getString("recaptcha.pkey"));
                        final String challenge = catcha.getString("challenge");
                        final String uresponse = catcha.getString("response");
                        reCaptchaResponse = reCaptcha.checkAnswer(Params.getString("recaptcha.site"), challenge, uresponse);
                    }
                    if (!injunit && !reCaptchaResponse.isValid()) {
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
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Fetch the current logged user
         * @api {get} /api/1/commons/user/current Fetch the current logged user
         * @apiVersion 0.1.0
         * @apiName currentHandler
         * @apiGroup User API
         * @apiHeader {String} token
         * @apiParam {Object} Person com.qaobee.swarn.business.model.tranversal.person.Person
         * @apiSuccess {Object} Person com.qaobee.swarn.business.model.tranversal.person.Person
         * @apiError HTTP_ERROR wrong request method
         * @apiError NOT_LOGGED invalid token
         */
        vertx.eventBus().registerHandler(CURRENT, new Handler<Message<String>>() {
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
                    User user = utils.isUserLogged(req);
                    JsonObject jUser = new JsonObject(Json.encode(user));
                    jUser.getObject("account").removeField("passwd");
                    jUser.getObject("account").removeField("password");
                    jUser.getObject("account").removeField("salt");
                    message.reply(jUser.toString());
                    utils.sendStatus(true, message);
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });
        /**
         * @apiDescription Fetch meta information
         * @api {get} /api/1/commons/user/meta?country= Fetch meta information
         * @apiVersion 0.1.0
         * @apiName getMetasHandler
         * @apiParam country Country Id (ie "CNTR-250-FR-FRA")
         * @apiGroup User API
         * @apiHeader {String} token
         * @apiError HTTP_ERROR wrong request method
         * @apiError NOT_LOGGED invalid token
         */
        vertx.eventBus().registerHandler(META, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    User u = utils.isUserLogged(req);
                    utils.testMandatoryParams(req.getParams(), PARAM_COUNTRY_ID);
                    JsonObject user = mongo.getById(u.get_id(), User.class);
                    final JsonObject activity = ((JsonObject) user.getObject("account").getArray("listPlan").get(0)).getObject("activity");

                    req.getParams().put(SeasonVerticle.PARAM_ACTIVITY_ID, Collections.singletonList(activity.getString("_id")));
                    req.getParams().put(SeasonVerticle.PARAM_COUNTRY_ID, Collections.singletonList(req.getParams().get(PARAM_COUNTRY_ID).get(0)));
                    whenEventBus.send(SeasonVerticle.GET_CURRENT, Json.encode(req)).then(new Runnable<Promise<Message<Object>, Void>, Message<Object>>() {
                        @Override
                        public Promise<Message<Object>, Void> run(Message<Object> objectMessage) {
                            if (objectMessage.body() instanceof ReplyException) {
                                utils.sendError(message, (ReplyException) objectMessage.body());
                            } else {
                                final JsonObject season = new JsonObject((String) objectMessage.body());
                                req.getParams().put(SandBoxVerticle.PARAM_ACTIVITY_ID, Collections.singletonList(activity.getString("_id")));
                                req.getParams().put(SandBoxVerticle.PARAM_SEASON_ID, Collections.singletonList(season.getString("_id")));
                                whenEventBus.send(SandBoxVerticle.GET_BY_OWNER, Json.encode(req))
                                        .then(new Runnable<Promise<Message<Object>, Void>, Message<Object>>() {
                                            @Override
                                            public Promise<Message<Object>, Void> run(Message<Object> objectMessage) {
                                                if (objectMessage.body() instanceof ReplyException) {
                                                    utils.sendError(message, (ReplyException) objectMessage.body());
                                                } else {
                                                    JsonObject sandbox = new JsonObject((String) objectMessage.body());
                                                    sandbox.putObject("activity", activity);
                                                    message.reply(sandbox.encode());
                                                }
                                                return null;
                                            }
                                        });
                            }
                            return null;
                        }
                    });
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Fetch user information by its id
         * @api {get} /api/1/commons/user/user Fetch user by id
         * @apiVersion 0.1.0
         * @apiName getUserByIdhandler
         * @apiGroup User API
         * @apiParam {String} id
         * @apiError HTTP_ERROR wrong request method
         * @apiError NOT_LOGGED invalid token
         */
        vertx.eventBus().registerHandler(USER_INFO, new Handler<Message<String>>() {
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
                    utils.isUserLogged(req);
                    message.reply(mongo.getById(req.getParams().get("id").get(0), User.class).encode());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription SSO login by mobile token (token provided at the login phase corresponding to the device id)
         * @api {post} /api/1/commons/user/sso SSO login by mobile token
         * @apiVersion 0.1.0
         * @apiName loginByMobileToken
         * @apiParam {String} mobileToken Mobile device Id
         * @apiParam {String} login Login
         * @apiGroup User API
         * @apiError HTTP_ERROR wrong request method
         * @apiError NOT_LOGGED invalid token
         */
        vertx.eventBus().registerHandler(LOGIN_BY_TOKEN, new Handler<Message<String>>() {
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
                    utils.testMandatoryParams(req.getBody(), MOBILE_TOKEN, PARAM_LOGIN);
                    JsonObject request = new JsonObject(req.getBody());
                    CriteriaBuilder cb = new CriteriaBuilder();
                    cb.add("account.mobileToken", request.getString(MOBILE_TOKEN));
                    cb.add("account.login", request.getString(PARAM_LOGIN));

                    final JsonArray res = mongo.findByCriterias(cb.get(), null, null, 0, 0, User.class);
                    if (res.size() != 1) {
                        final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                        container.logger().error(e.getMessage(), e);
                        utils.sendError(message, e);
                    } else {
                        // we take the first one (should be only one)
                        final JsonObject jsonPerson = res.get(0);
                        final User user = Json.decodeValue(jsonPerson.encode(), User.class);
                        user.getAccount().setToken(UUID.randomUUID().toString());
                        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                        String result = Json.encode(user);
                        container.logger().debug(result);
                        message.reply(result);
                    }
                } catch (final IllegalArgumentException | NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });
    }
}
