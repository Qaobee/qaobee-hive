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
import com.qaobee.hive.dao.SecurityDAO;
import com.qaobee.hive.dao.UserDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

/**
 * The type User verticle.
 */
@DeployableVerticle
public class UserVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(UserVerticle.class);
    /**
     * The constant LOGIN.
     */
    public static final String LOGIN = Module.VERSION + ".commons.users.user.login";
    /**
     * The constant LOGIN_BY_TOKEN.
     */
    public static final String LOGIN_BY_TOKEN = Module.VERSION + ".commons.users.user.sso";
    /**
     * The constant LOGOUT.
     */
    public static final String LOGOUT = Module.VERSION + ".commons.users.user.logout";
    /**
     * The constant PASSWD_RENEW.
     */
    public static final String PASSWD_RENEW = Module.VERSION + ".commons.users.user.newpasswd";
    /**
     * The constant PASSWD_RENEW_CHK.
     */
    public static final String PASSWD_RENEW_CHK = Module.VERSION + ".commons.users.user.passwdcheck";
    /**
     * The constant PASSWD_RESET.
     */
    public static final String PASSWD_RESET = Module.VERSION + ".commons.users.user.resetPasswd";
    /**
     * The constant CURRENT.
     */
    public static final String CURRENT = Module.VERSION + ".commons.users.user.current";
    /**
     * The constant META.
     */
    public static final String META = Module.VERSION + ".commons.users.user.meta";
    /**
     * The constant USER_INFO.
     */
    public static final String USER_INFO = Module.VERSION + ".commons.users.user.user";
    /**
     * The constant USER_BY_LOGIN.
     */
    public static final String USER_BY_LOGIN = Module.VERSION + ".commons.users.user.getUserByLogin";
    /**
     * The constant PARAM_LOGIN.
     */
    public static final String PARAM_LOGIN = "login";
    /**
     * The constant PARAM_COUNTRY_ID.
     */
    public static final String PARAM_COUNTRY_ID = "country";
    /**
     * The constant PARAM_PWD.
     */
    public static final String PARAM_PWD = "password"; // NOSONAR
    /**
     * The constant MOBILE_TOKEN.
     */
    public static final String MOBILE_TOKEN = "mobileToken";
    /**
     * The constant PARAM_OS.
     */
    public static final String PARAM_OS = "os";
    /**
     * The constant PARAM_PUSH_ID.
     */
    public static final String PARAM_PUSH_ID = "pushId";
    private static final String PASSWD_FIELD = "passwd"; // NOSONAR
    @Inject
    private Utils utils;
    @Inject
    private UserDAO userDAO;
    @Inject
    private SecurityDAO securityDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(LOGIN, this::login)
                .registerHandler(LOGOUT, this::logout)
                .registerHandler(PASSWD_RENEW, this::passwordRenew)
                .registerHandler(PASSWD_RENEW_CHK, this::passwordRenewCheck)
                .registerHandler(PASSWD_RESET, this::passwordReset)
                .registerHandler(CURRENT, this::currentUser)
                .registerHandler(META, this::getMeta)
                .registerHandler(USER_INFO, this::userInfo)
                .registerHandler(USER_BY_LOGIN, this::userByLogin)
                .registerHandler(LOGIN_BY_TOKEN, this::loginByToken);
    }

    /**
     * @apiDescription SSO login by mobile token (token provided at the login phase corresponding to the device id)
     * @api {post} /api/1/commons/users/user/sso SSO login by mobile token
     * @apiVersion 0.1.0
     * @apiName loginByToken
     * @apiParam {String} mobileToken Mobile device Id
     * @apiParam {String} login Login
     * @apiGroup User API
     */
    @Rule(address = LOGIN_BY_TOKEN, method = Constants.POST, mandatoryParams = {MOBILE_TOKEN, PARAM_LOGIN},
          scope = Rule.Param.BODY)
    private void loginByToken(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            JsonObject request = new JsonObject(req.getBody());
            message.reply(securityDAO.loginByToken(request.getString(PARAM_LOGIN), request.getString(MOBILE_TOKEN), req.getLocale()).encode());
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Fetch user information by its Login
     * @api {get} /api/1/commons/users/user/user Fetch user by login
     * @apiVersion 0.1.0
     * @apiName getUserByLogin
     * @apiGroup User API
     * @apiParam {String} login
     * @apiHeader {String} token
     */
    @Rule(address = USER_BY_LOGIN, method = Constants.GET, logged = true, admin = true, mandatoryParams = {PARAM_LOGIN},
          scope = Rule.Param.REQUEST)
    private void userByLogin(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(userDAO.getUserByLogin(req.getParams().get("login").get(0)).encode());
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Fetch user information by its id
     * @api {get} /api/1/commons/users/user/user Fetch user by id
     * @apiVersion 0.1.0
     * @apiName getUserByIdhandler
     * @apiGroup User API
     * @apiParam {String} id
     * @apiHeader {String} token
     */
    @Rule(address = USER_INFO, method = Constants.GET, logged = true, mandatoryParams = {"id"},
          scope = Rule.Param.REQUEST)
    private void userInfo(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(userDAO.getUserInfo(req.getParams().get("id").get(0)).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Fetch meta information
     * @api {get} /api/1/commons/users/user/meta Fetch meta information
     * @apiVersion 0.1.0
     * @apiName getMeta
     * @apiGroup User API
     * @apiHeader {String} token
     */
    @Rule(address = META, method = Constants.GET, logged = true)
    private void getMeta(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(userDAO.getMeta(req.getUser().get_id()).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Fetch the current logged user
     * @api {get} /api/1/commons/users/user/current Fetch the current logged user
     * @apiVersion 0.1.0
     * @apiName currentUser
     * @apiGroup User API
     * @apiHeader {String} token
     * @apiParam {Object} Person com.qaobee.swarn.business.model.tranversal.person.Person
     * @apiSuccess {Object} Person com.qaobee.swarn.business.model.tranversal.person.Person
     */
    @Rule(address = CURRENT, method = Constants.GET, logged = true)
    private void currentUser(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            message.reply(userDAO.getUserInfo(req.getUser().get_id()).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

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
    @Rule(address = PASSWD_RESET, method = Constants.POST, mandatoryParams = {"id", "code", PASSWD_FIELD},
          scope = Rule.Param.BODY)
    private void passwordReset(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            final JsonObject body = new JsonObject(req.getBody());
            utils.sendStatus(securityDAO.passwordReset(body.getString("id"), body.getString("code"), body.getString(PASSWD_FIELD), body.getBoolean("byPassActivationCode", false)), message);
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Check activation code supplied in the renew password email
     * @api {get} /api/v1/commons/users/user/passwdcheck Check activation code
     * @apiParam {String} code Activation code
     * @apiParam {String} id Person id
     * @apiVersion 0.1.0
     * @apiName passwordRenewCheck
     * @apiGroup User API
     * @apiSuccess {Object} status {"status" : true|false, "user" : Object(user)}
     */
    @Rule(address = PASSWD_RENEW_CHK, method = Constants.GET, mandatoryParams = {"id", "code"},
          scope = Rule.Param.REQUEST)
    private void passwordRenewCheck(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            final String id = req.getParams().get("id").get(0);
            final String code = req.getParams().get("code").get(0);
            JsonObject jsonUser =securityDAO.passwordRenewCheck(id, code);
            if(jsonUser == null) {
                utils.sendStatus(false, message);
            } else {
                message.reply(jsonUser.encode());
            }
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Mail generation for password renew
     * @api {post} /api/1/commons/users/user/newpasswd Password renew
     * @apiVersion 0.1.0
     * @apiName passwordRenew
     * @apiGroup User API
     * @apiParam {String} login user login
     * @apiSuccess {Object} status
     */
    @Rule(address = PASSWD_RENEW, method = Constants.POST, mandatoryParams = {PARAM_LOGIN}, scope = Rule.Param.BODY)
    private void passwordRenew(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            final JsonObject infos = new JsonObject(req.getBody());
           utils.sendStatus(securityDAO.passwordRenew(infos.getString(PARAM_LOGIN), req.getLocale()), message);
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription User logout
     * @api {get} /api/1/commons/users/user/logout User logout
     * @apiVersion 0.1.0
     * @apiName logout
     * @apiGroup User API
     * @apiHeader {String} token
     * @apiSuccess {Object} status {"status", true|false}
     */
    @Rule(address = LOGOUT, method = Constants.GET, logged = true, mandatoryParams = {Constants.TOKEN},
          scope = Rule.Param.HEADER)
    private void logout(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            utils.sendStatus( securityDAO.logout(req.getHeaders().get("token").get(0)), message);
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Login user
     * @api {post} /api/1/commons/users/user/login Login user
     * @apiVersion 0.1.0
     * @apiName login
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
    @Rule(address = LOGIN, method = Constants.POST)
    private void login(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            final JsonObject infos = new JsonObject(req.getBody());
            message.reply(securityDAO.login(infos.getString(PARAM_LOGIN),
                    infos.getString(PARAM_PWD),
                    infos.getString(MOBILE_TOKEN),
                    infos.getString(PARAM_PUSH_ID),
                    infos.getString(PARAM_OS),
                    req.getLocale()).toString());
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
