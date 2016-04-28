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
import com.qaobee.hive.api.v1.commons.utils.TemplatesVerticle;
import com.qaobee.hive.api.v1.sandbox.config.SB_SandBoxCfgVerticle;
import com.qaobee.hive.api.v1.sandbox.config.SB_SandBoxVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.tools.PasswordEncryptionService;
import com.qaobee.hive.technical.utils.MailUtils;
import com.qaobee.hive.technical.utils.PersonUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.eventbus.ReplyException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Base64;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Collections;
import java.util.UUID;

/**
 * The type User verticle.
 */
@DeployableVerticle
public class UserVerticle extends AbstractGuiceVerticle {
    public static final String LOGIN = Module.VERSION + ".commons.users.user.login";
    public static final String LOGIN_BY_TOKEN = Module.VERSION + ".commons.users.user.sso";
    public static final String LOGOUT = Module.VERSION + ".commons.users.user.logout";
    public static final String PASSWD_RENEW = Module.VERSION + ".commons.users.user.newpasswd";
    public static final String PASSWD_RENEW_CHK = Module.VERSION + ".commons.users.user.passwdcheck";
    public static final String PASSWD_RESET = Module.VERSION + ".commons.users.user.resetPasswd";
    public static final String CURRENT = Module.VERSION + ".commons.users.user.current";
    public static final String META = Module.VERSION + ".commons.users.user.meta";
    public static final String USER_INFO = Module.VERSION + ".commons.users.user.user";
    public static final String USER_BY_LOGIN = Module.VERSION + ".commons.users.user.userByLogin";
    /* List of parameters */
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_COUNTRY_ID = "country";
    public static final String PARAM_PWD = "password"; // NOSONAR
    /**
     * The constant MOBILE_TOKEN.
     */
    public static final String MOBILE_TOKEN = "mobileToken";
    private static final Logger LOG = LoggerFactory.getLogger(UserVerticle.class);
    private static final String ACCOUNT_FIELD = "account";
    private static final String PASSWD_FIELD = "passwd"; // NOSONAR
    private static final String ACCOUNT_LOGIN_FIELD = "account.login";
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
    @VerticleHandler({
            @Rule(address = LOGIN, method = Constantes.POST),
            @Rule(address = LOGOUT, method = Constantes.GET, logged = true, mandatoryParams = {TOKEN}, scope = Rule.Param.HEADER),
            @Rule(address = PASSWD_RENEW, method = Constantes.POST, mandatoryParams = {PARAM_LOGIN}, scope = Rule.Param.BODY),
            @Rule(address = PASSWD_RENEW_CHK, method = Constantes.GET, mandatoryParams = {"id", "code"}, scope = Rule.Param.REQUEST),
            @Rule(address = PASSWD_RESET, method = Constantes.POST, mandatoryParams = {"id", "code", PASSWD_FIELD}, scope = Rule.Param.BODY),
            @Rule(address = CURRENT, method = Constantes.GET, logged = true),
            @Rule(address = META, method = Constantes.GET, logged = true),
            @Rule(address = USER_INFO, method = Constantes.GET, logged = true, mandatoryParams = {"id"}, scope = Rule.Param.REQUEST),
            @Rule(address = USER_BY_LOGIN, method = Constantes.GET, logged = true, admin = true, mandatoryParams = {PARAM_LOGIN}, scope = Rule.Param.REQUEST),
            @Rule(address = LOGIN_BY_TOKEN, method = Constantes.POST, mandatoryParams = {MOBILE_TOKEN, PARAM_LOGIN}, scope = Rule.Param.BODY),
    })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");

        /**
         * @apiDescription Login user
         * @api {post} /api/1/commons/users/user/login Login user
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
                    final JsonObject infos = new JsonObject(req.getBody());

                    if (StringUtils.isBlank(infos.getString(PARAM_LOGIN)) || StringUtils.isBlank(infos.getString(PARAM_PWD))) {
                        final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                        LOG.error(e.getMessage(), e);
                        utils.sendError(message, e);
                    } else {
                        final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add(ACCOUNT_LOGIN_FIELD,
                                infos.getString(PARAM_LOGIN).toLowerCase()).get(), null, null, 0, 0, User.class);
                        if (res.size() != 1) {
                            final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                            utils.sendError(message, e);
                        } else {
                            // we take the first one (should be only one)
                            final JsonObject jsonPerson = res.get(0);
                            final User user = Json.decodeValue(jsonPerson.encode(), User.class);
                            final byte[] encryptedAttemptedPassword = passwordEncryptionService.getEncryptedPassword(infos.getString(PARAM_PWD), user.getAccount().getSalt());
                            if (!Base64.encodeBytes(encryptedAttemptedPassword).equals(Base64.encodeBytes(user.getAccount().getPassword()))) {
                                final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                                LOG.error(e.getMessage(), e);
                                utils.sendError(message, e);
                            } else {
                                if (user.getAccount().isActive()) {
                                    // trial period test
                                    if (!"paid".equals(user.getAccount().getListPlan().get(0).getStatus()) && !testTrial(user)) {
                                        user.getAccount().getListPlan().get(0).setStatus("notpaid");
                                    }
                                    user.getAccount().setToken(UUID.randomUUID().toString());
                                    user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                                    if (infos.containsField(MOBILE_TOKEN)) {
                                        user.getAccount().setMobileToken(infos.getString(MOBILE_TOKEN));
                                    }
                                    mongo.save(user);
                                    JsonObject jUser = new JsonObject(Json.encode(user));
                                    jUser.getObject(ACCOUNT_FIELD).removeField(PASSWD_FIELD);
                                    jUser.getObject(ACCOUNT_FIELD).removeField("password");
                                    jUser.getObject(ACCOUNT_FIELD).removeField("salt");
                                    message.reply(jUser.toString());
                                } else {
                                    utils.sendError(message, ExceptionCodes.NON_ACTIVE, Messages.getString("popup.warning.unregistreduser", req.getLocale()));
                                }
                            }
                        }

                    }
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription User logout
         * @api {get} /api/1/commons/users/user/logout User logout
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
                    final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.token", req.getHeaders().get("token").get(0)).get(), null, null, 0, 0, User.class);
                    if (res.size() != 1) {
                        utils.sendStatus(false, message);
                    } else {
                        // we take the first one (should be only one)
                        final JsonObject jsonperson = res.get(0);
                        final User user = Json.decodeValue(jsonperson.encode(), User.class);
                        user.getAccount().setToken(null);
                        user.getAccount().setTokenRenewDate(0L);
                        user.getAccount().setMobileToken(null);
                        mongo.save(user);
                        utils.sendStatus(true, message);
                    }
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Mail generation for password renew
         * @api {post} /api/1/commons/users/user/newpasswd Password renew
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
                    final JsonObject infos = new JsonObject(req.getBody());
                    final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add(ACCOUNT_LOGIN_FIELD, infos.getString(PARAM_LOGIN).toLowerCase()).get(), null, null, 0, 0, User.class);
                    if (res.size() != 1) {
                        final QaobeeException e = new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("login.wronglogin", req.getLocale()));
                        LOG.error(e.getMessage(), e);
                        utils.sendError(message, e);
                    } else {
                        final JsonObject jsonperson = res.get(0);
                        final User user = Json.decodeValue(jsonperson.encode(), User.class);
                        user.getAccount().setActivationPasswd(UUID.randomUUID().toString().replaceAll("-", ""));
                        mongo.save(user);

                        final JsonObject tplReq = new JsonObject();
                        tplReq.putString(TemplatesVerticle.TEMPLATE, "newPasswd.html");
                        tplReq.putObject(TemplatesVerticle.DATA, mailUtils.generateNewpasswdBody(user, req.getLocale(), getContainer().config()));

                        vertx.eventBus().send(TemplatesVerticle.TEMPLATE_GENERATE, tplReq, new Handler<Message<JsonObject>>() {
                            @Override
                            public void handle(final Message<JsonObject> tplResp) {
                                final String tplRes = tplResp.body().getString("result");
                                final JsonObject emailReq = new JsonObject();
                                emailReq.putString("from", getContainer().config().getObject(RUNTIME).getString("mail.from"));
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
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Check activation code supplied in the renew password email
         * @api {get} /api/v1/commons/users/user/passwdcheck Check activation code
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
                    final String id = req.getParams().get("id").get(0);
                    final String code = req.getParams().get("code").get(0);
                    JsonObject jsonUser = mongo.getById(id, User.class);
                    // User not found
                    if (jsonUser == null) {
                        utils.sendStatus(false, message);
                    }

                    final User user;
                    if (jsonUser != null) {
                        user = Json.decodeValue(jsonUser.encode(), User.class);
                        if (code.equals(user.getAccount().getActivationPasswd())) {
                            final JsonObject jsonResp = new JsonObject();
                            jsonResp.putBoolean("status", true);
                            jsonResp.putObject("user", mongo.getById(id, User.class));
                            message.reply(jsonResp.encode());
                        } else {
                            // Code doesn't match
                            utils.sendStatus(false, message);
                        }
                    }
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Update password after renew ask
         * @api {post} /api/v1/commons/users/user/resetPasswd Update password
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
                    final JsonObject json = new JsonObject(req.getBody());
                    final String id = json.getString("id");
                    final String code = json.getString("code");
                    final String passwd = json.getString(PASSWD_FIELD);
                    final boolean injunit = json.getBoolean("junit", false);
                    final boolean byPassActivationCode = json.getBoolean("byPassActivationCode", false);
                    ReCaptchaResponse reCaptchaResponse = null;
                    if (!injunit) {
                        final JsonObject catcha = json.getObject("captcha");
                        final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
                        reCaptcha.setPrivateKey(getContainer().config().getObject(RUNTIME).getString("recaptcha.pkey"));
                        final String challenge = catcha.getString("challenge");
                        final String uresponse = catcha.getString("response");
                        reCaptchaResponse = reCaptcha.checkAnswer(getContainer().config().getObject(RUNTIME).getString("recaptcha.site"), challenge, uresponse);
                    }
                    if (!injunit && !reCaptchaResponse.isValid()) {
                        utils.sendError(message, ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha");
                    } else {
                        final User user = Json.decodeValue(mongo.getById(id, User.class).encode(), User.class);
                        /* update password by profil menu */
                        if (byPassActivationCode) {
                            user.getAccount().setPasswd(passwd);
                            mongo.save(personUtils.prepareUpsert(user));
                            utils.sendStatus(true, message);
                        } else {
                            /* update password by home public */
                            if (code.equals(user.getAccount().getActivationPasswd())) {
                                user.getAccount().setPasswd(passwd);
                                mongo.save(personUtils.prepareUpsert(user));
                                utils.sendStatus(true, message);
                            } else {
                                utils.sendStatus(false, message);
                            }
                        }
                    }
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Fetch the current logged user
         * @api {get} /api/1/commons/users/user/current Fetch the current logged user
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
                User user = req.getUser();
                JsonObject jUser = new JsonObject(Json.encode(user));
                jUser.getObject(ACCOUNT_FIELD).removeField(PASSWD_FIELD);
                jUser.getObject(ACCOUNT_FIELD).removeField("password");
                jUser.getObject(ACCOUNT_FIELD).removeField("salt");
                message.reply(jUser.toString());
            }
        });
        /**
         * @apiDescription Fetch meta information
         * @api {get} /api/1/commons/users/user/meta Fetch meta information
         * @apiVersion 0.1.0
         * @apiName getMetasHandler
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
                    JsonObject user = mongo.getById(req.getUser().get_id(), User.class);
                    final JsonObject activity = ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray("listPlan").get(0)).getObject("activity");
                    req.getParams().put(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, Collections.singletonList(activity.getString("_id")));
                    whenEventBus.send(SB_SandBoxVerticle.GET_BY_OWNER, Json.encode(req)).then(new Runnable<Promise<Message<Object>, Void>, Message<Object>>() {
                        @Override
                        public Promise<Message<Object>, Void> run(Message<Object> objectMessage) {
                            if (objectMessage.body() instanceof ReplyException) {
                                utils.sendError(message, (ReplyException) objectMessage.body());
                            } else {
                                JsonObject sandbox = new JsonObject((String) objectMessage.body());
                                req.getParams().put(SB_SandBoxCfgVerticle.PARAM_ID, Collections.singletonList(sandbox.getString("sandboxCfgId")));
                                whenEventBus.send(SB_SandBoxCfgVerticle.GET, Json.encode(req)).then(new Runnable<Promise<Message<Object>, Void>, Message<Object>>() {
                                    @Override
                                    public Promise<Message<Object>, Void> run(Message objectMessage) {
                                        if (objectMessage.body() instanceof ReplyException) {
                                            utils.sendError(message, (ReplyException) objectMessage.body());
                                        } else {
                                            message.reply((String) objectMessage.body());
                                        }
                                        return null;
                                    }
                                });
                            }
                            return null;
                        }
                    });
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Fetch user information by its id
         * @api {get} /api/1/commons/users/user/user Fetch user by id
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
                    JsonObject u = mongo.getById(req.getParams().get("id").get(0), User.class);
                    u.removeField("salt");
                    u.removeField(PASSWD_FIELD);
                    u.removeField("password");
                    message.reply(u.encode());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Fetch user information by its Login
         * @api {get} /api/1/commons/users/user/user Fetch user by login
         * @apiVersion 0.1.0
         * @apiName getUserByLoginhandler
         * @apiGroup User API
         * @apiParam {String} login
         * @apiError HTTP_ERROR wrong request method
         * @apiError NOT_LOGGED invalid token
         */
        vertx.eventBus().registerHandler(USER_BY_LOGIN, new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                // Creation of request
                CriteriaBuilder criterias = new CriteriaBuilder();
                criterias.add(ACCOUNT_LOGIN_FIELD, req.getParams().get("login").get(0).toLowerCase());
                JsonArray jsonArray = mongo.findByCriterias(criterias.get(), null, null, -1, -1, User.class);
                if (jsonArray == null || jsonArray.size() == 0) {
                    utils.sendError(message, ExceptionCodes.DATA_ERROR, "Login inconnu");
                    return;
                }
                if (jsonArray.size() > 1) {
                    utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, "Plus d'un résultat retourné");
                    return;
                }
                message.reply(jsonArray.get(0).toString());
            }
        });

        /**
         * @apiDescription SSO login by mobile token (token provided at the login phase corresponding to the device id)
         * @api {post} /api/1/commons/users/user/sso SSO login by mobile token
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
                    JsonObject request = new JsonObject(req.getBody());
                    CriteriaBuilder cb = new CriteriaBuilder();
                    cb.add("account.mobileToken", request.getString(MOBILE_TOKEN));
                    cb.add(ACCOUNT_LOGIN_FIELD, request.getString(PARAM_LOGIN).toLowerCase());
                    final JsonArray res = mongo.findByCriterias(cb.get(), null, null, 0, 0, User.class);
                    if (res.size() != 1) {
                        throw new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("bad.login", req.getLocale()));
                    } else {
                        // we take the first one (should be only one)
                        final JsonObject jsonPerson = res.get(0);
                        final User user = Json.decodeValue(jsonPerson.encode(), User.class);
                        user.getAccount().setToken(UUID.randomUUID().toString());
                        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                        mongo.save(user);
                        message.reply(Json.encode(user));
                    }
                } catch (final QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });
    }

    /**
     * @param user User
     * @return in triel period
     */
    private static boolean testTrial(User user) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(user.getAccount().getListPlan().get(0).getStartPeriodDate());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(user.getAccount().getListPlan().get(0).getStartPeriodDate());
        cal2.add(Calendar.MONTH, 1);
        return "open".equals(user.getAccount().getListPlan().get(0).getStatus()) && cal.before(cal2);
    }
}
