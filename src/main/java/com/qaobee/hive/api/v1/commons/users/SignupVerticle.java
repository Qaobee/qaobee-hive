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

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.api.v1.commons.communication.NotificationsVerticle;
import com.qaobee.hive.dao.SignupDAO;
import com.qaobee.hive.dao.UserDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class SignupVerticle.
 *
 * @author Xavier MARIN <ul>     <li>resthandler.register : Register a new accunt</li>     <li>resthandler.logintest : Login unicity test for rest request</li>     <li>loginExists : Login unicity test for internal use</li>     <li>resthandler.accountcheck : email validation number check</li> </ul>
 */
@DeployableVerticle
public class SignupVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(SignupVerticle.class);
    /**
     * The Constant REGISTER.
     */
    public static final String REGISTER = Module.VERSION + ".commons.users.signup.register";
    /**
     * The Constant LOGIN_TEST.
     */
    public static final String LOGIN_TEST = Module.VERSION + ".commons.users.signup.logintest";
    /**
     * The Constant LOGIN_EXISTS.
     */
    public static final String LOGIN_EXISTS = Module.VERSION + ".commons.users.signup.loginExists";
    /**
     * The Constant ACCOUNT_CHECK.
     */
    public static final String ACCOUNT_CHECK = Module.VERSION + ".commons.users.signup.accountcheck";
    /**
     * The Constant FIRST_CONNECTION_CHECK
     */
    public static final String FIRST_CONNECTION_CHECK = Module.VERSION + ".commons.users.signup.firstconnectioncheck";
    /**
     * The Constant FINALIZE_SIGNUP
     */
    public static final String FINALIZE_SIGNUP = Module.VERSION + ".commons.users.signup.finalize";
    /**
     * The constant RESEND_MAIL.
     */
    public static final String RESEND_MAIL = Module.VERSION + ".commons.users.signup.mailResend";
    /**
     * Parameter ID
     */
    public static final String PARAM_ID = "id";
    /**
     * Parameter CODE
     */
    public static final String PARAM_CODE = "code";
    /**
     * Parameter USER
     */
    public static final String PARAM_USER = "user";
    /**
     * Parameter STRUCTURE
     */
    public static final String PARAM_STRUCTURE = "structure";
    /**
     * Parameter ACTIVITY
     */
    public static final String PARAM_ACTIVITY = "activity";
    /**
     * Parameter Category Age
     */
    public static final String PARAM_CATEGORY_AGE = "categoryAge";
    /**
     * Parameter Login
     */
    public static final String PARAM_LOGIN = "login";
    private static final String COUNTRY_FIELD = "country";
    private static final String PARAM_CAPTCHA = "captcha";
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    private Utils utils;
    @Inject
    private UserDAO userDAO;
    @Inject
    private SignupDAO signupDAO;

    @Override
    public void start() {
        super.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug(this.getClass().getName() + " started");
        }
        vertx.eventBus().consumer(LOGIN_EXISTS, this::existingLogin);
        vertx.eventBus().consumer(LOGIN_TEST, this::loginTest);
        vertx.eventBus().consumer(REGISTER, this::register);
        vertx.eventBus().consumer(ACCOUNT_CHECK, this::accountCheck);
        vertx.eventBus().consumer(FIRST_CONNECTION_CHECK, this::firstConnectionCheck);
        vertx.eventBus().consumer(RESEND_MAIL, this::resendMail);
        vertx.eventBus().consumer(FINALIZE_SIGNUP, this::finalizeSignup);
    }

    /**
     * @apiDescription Resend a register mail
     * @api {post} /api/1/commons/users/user/mailResend Resend a register mail
     * @apiVersion 0.1.0
     * @apiName resendMail
     * @apiParam {String} login Login
     * @apiGroup Object Status
     */
    @Rule(address = RESEND_MAIL, method = Constants.POST, mandatoryParams = {PARAM_LOGIN},
          scope = Rule.Param.BODY)
    private void resendMail(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        signupDAO.resendMail(new JsonObject(req.getBody()).getString(PARAM_LOGIN), req.getLocale())
                .done(r -> utils.sendStatus(r, message))
                .fail(e -> utils.sendError(message, e));
    }

    /**
     * @apiDescription Finalizes signup
     * @api {get} /api/1/commons/users/signup/finalizesignup Account finalizes signup
     * @apiParam {Object} user the user
     * @apiParam {String} activationCode The activation code
     * @apiParam {Object} structure The structure
     * @apiParam {Object} activity the activity
     * @apiVersion 0.1.0
     * @apiName finalizeSignup
     * @apiGroup Signup API
     * @apiSuccess {Object} user {"status", true|false}
     * @apiHeader {String} token
     */
    @Rule(address = FINALIZE_SIGNUP, method = Constants.POST,
          mandatoryParams = {PARAM_USER, PARAM_CODE, PARAM_ACTIVITY, PARAM_STRUCTURE, PARAM_CATEGORY_AGE},
          scope = Rule.Param.BODY)
    private void finalizeSignup(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        final JsonObject body = new JsonObject(req.getBody());
        signupDAO.finalizeSignup(body.getJsonObject(PARAM_USER),
                body.getString(PARAM_CODE),
                body.getString(PARAM_ACTIVITY),
                body.getJsonObject(PARAM_STRUCTURE),
                body.getJsonObject(PARAM_CATEGORY_AGE),
                body.getString(COUNTRY_FIELD, "CNTR-250-FR-FRA"),
                req.getLocale())
                .done(u -> {
                    try {
                        signupDAO.sendRegisterMail(u, req.getLocale());
                        JsonObject notification = new JsonObject()
                                .put("id", u.getString("_id"))
                                .put("target", "User")
                                .put("notification", new JsonObject()
                                        .put("content", Messages.getString("notification.first.connection.content", String.valueOf(runtime.getInteger("trial.duration")), req.getLocale()))
                                        .put("title", Messages.getString("notification.first.connection.title", req.getLocale()))
                                        .put("senderId", runtime.getString("admin.id")
                                        )
                                );
                        vertx.eventBus().send(NotificationsVerticle.NOTIFY, notification);
                        message.reply(u.encode());
                    } catch (final QaobeeException e) {
                        LOG.error(e.getMessage(), e);
                        utils.sendError(message, e);
                    }
                }).fail(e -> utils.sendError(message, e));
    }

    /**
     * @apiDescription First connection account check
     * @api {get} /api/1/commons/users/signup/firstconnectioncheck Account validation check
     * @apiParam {String} code Activation code
     * @apiParam {String} id Person id
     * @apiVersion 0.1.0
     * @apiName firstConnectionCheck
     * @apiGroup Signup API
     * @apiSuccess {Object} status {"status", true|false}
     */
    @Rule(address = FIRST_CONNECTION_CHECK, method = Constants.GET, mandatoryParams = {PARAM_ID, PARAM_CODE},
          scope = Rule.Param.REQUEST)
    private void firstConnectionCheck(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, signupDAO.firstConnectionCheck(req.getParams().get(PARAM_ID), req.getParams().get(PARAM_CODE), req.getLocale()));
    }

    /**
     * @apiDescription Account validation check
     * @api {get} /api/1/commons/users/signup/accountcheck Account validation check
     * @apiParam {String} code Activation code
     * @apiParam {String} id Person id
     * @apiVersion 0.1.0
     * @apiName accountCheck
     * @apiGroup Signup API
     * @apiSuccess {Object} status {"status", true|false}
     */
    @Rule(address = ACCOUNT_CHECK, method = Constants.GET, mandatoryParams = {"id", "code"}, scope = Rule.Param.REQUEST)
    private void accountCheck(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        signupDAO.accountCheck(req.getParams().get("id"), req.getParams().get("code")).done(r -> utils.sendStatus(r, message));
    }

    /**
     * @apiDescription Register a new account
     * @api {put} /api/1/commons/users/signup/register Register a new account
     * @apiVersion 0.1.0
     * @apiName register
     * @apiGroup Signup API
     * @apiParam {Object} person com.qaobee.swarn.business.model.tranversal.person.Person
     * @apiSuccess {Object} person com.qaobee.swarn.business.model.tranversal.person.Person
     * @apiError PASSWD_EXCEPTION Password encoding exception
     * @apiError NON_UNIQUE_LOGIN Non unique login
     * @apiError MAIL_EXCEPTION problème d'envoi d'email
     */
    @Rule(address = REGISTER, method = Constants.PUT)
    private void register(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject body = new JsonObject(req.getBody());
        replyJsonObject(message, signupDAO.register(body.getString(PARAM_CAPTCHA), body, req.getLocale()));
    }

    /**
     * @apiDescription Login unicity test for rest request
     * @api {get} /api/1/commons/users/signup/loginExists Login unicity test
     * @apiVersion 0.1.0
     * @apiName loginTest
     * @apiGroup Signup API
     * @apiParam {String} [login] person.account.login
     * @apiSuccess {Object} status {"status", true|false}
     */
    @Rule(address = LOGIN_TEST, method = Constants.POST, mandatoryParams = PARAM_LOGIN, scope = Rule.Param.BODY)
    private void loginTest(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyBoolean(message, userDAO.existingLogin(new JsonObject(req.getBody()).getString(PARAM_LOGIN).toLowerCase()));
    }


    /**
     * @apiDescription Test the existence of a username in the db
     * @api {get} /api/1/commons/users/signup/logintest
     * @apiVersion 0.1.0
     * @apiName existingLogin
     * @apiGroup Signup API
     * @apiParam {String} [login] person.account.login
     * @apiSuccess {Object} status {"status", true|false}
     */
    private void existingLogin(Message<JsonObject> message) {
        replyBooleanJ(message, userDAO.existingLogin(message.body().getString(PARAM_LOGIN).toLowerCase()));
    }
}
