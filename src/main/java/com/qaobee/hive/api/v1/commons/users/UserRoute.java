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
import com.qaobee.hive.services.SecurityService;
import com.qaobee.hive.services.UserService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import static com.qaobee.hive.technical.constantes.Constants.ADMIN_HABILIT;
import static com.qaobee.hive.technical.constantes.Constants.TOKEN;

/**
 * The type User verticle.
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/commons/users/user")
public class UserRoute extends AbstractRoute {
    /**
     * The constant PARAM_LOGIN.
     */
    public static final String PARAM_LOGIN = "login";
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
    private static final String SANDBOX_ID_FIELD = "sandboxId";
    @Inject
    private UserService userService;
    @Inject
    private SecurityService securityService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/sso", HttpMethod.POST,
                c -> mandatoryHandler.testBodyParams(c, MOBILE_TOKEN, PARAM_LOGIN),
                this::loginByToken);

        addRoute(router, "/resetPasswd", HttpMethod.POST,
                c -> mandatoryHandler.testBodyParams(c, "id", "code", PASSWD_FIELD),
                this::passwordReset);

        addRoute(router, "/userByLogin", HttpMethod.GET,
                authHandler,
                c -> roleHandler.hasRole(c, ADMIN_HABILIT),
                c -> mandatoryHandler.testRequestParams(c, PARAM_LOGIN),
                this::userByLogin);

        addRoute(router, "/logout", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestHeaders(c, TOKEN),
                this::logout);

        addRoute(router, "/", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, "id"),
                this::userInfo);

        addRoute(router, "/passwdcheck", HttpMethod.GET,
                c -> mandatoryHandler.testRequestParams(c, "id", "code"),
                this::passwordRenewCheck);

        addRoute(router, "/newpasswd", HttpMethod.POST,
                c -> mandatoryHandler.testBodyParams(c, PARAM_LOGIN),
                this::passwordRenew);

        addRoute(router, "/meta", HttpMethod.GET,
                authHandler,
                this::getMeta);

        addRoute(router, "/current", HttpMethod.GET,
                authHandler,
                this::currentUser);

        router.post("/login").handler(this::login);

        return router;
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
    private void loginByToken(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        securityService.loginByToken(body.getString(PARAM_LOGIN), body.getString(MOBILE_TOKEN), getLocale(context), handleResponse(context));
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
    private void userByLogin(RoutingContext context) {
        userService.getUserByLogin(context.request().getParam("login"), getLocale(context), handleResponse(context));
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
    private void userInfo(RoutingContext context) {
        userService.getUserInfo(context.request().getParam("id"), handleResponse(context));
    }

    /**
     * @apiDescription Fetch meta information
     * @api {get} /api/1/commons/users/user/meta Fetch meta information
     * @apiVersion 0.1.0
     * @apiName getMeta
     * @apiParam sandboxId (optionnel) sandboxId
     * @apiGroup User API
     * @apiHeader {String} token
     */
    private void getMeta(RoutingContext context) {
        String sandBoxId = context.user().principal().getString("sandboxDefault");
        if (context.request().params().contains(SANDBOX_ID_FIELD) && StringUtils.isNotBlank(context.request().getParam(SANDBOX_ID_FIELD))) {
            sandBoxId = context.request().getParam(SANDBOX_ID_FIELD);
        }
        userService.getMeta(sandBoxId, handleResponse(context));
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
    private void currentUser(RoutingContext context) {
        userService.getUserInfo(context.user().principal().getString("_id"), handleResponse(context));
    }

    /**
     * @apiDescription Update password after renew ask
     * @api {post} /api/v1/commons/users/user/resetPasswd Update password
     * @apiParam {Object} data {id, code, passwd}
     * @apiVersion 0.1.0
     * @apiName resetPasswdHandler
     * @apiGroup User API
     * @apiSuccess {Object} status {"status", true|false}
     */
    private void passwordReset(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        securityService.passwordReset(
                body.getString("captcha", ""),
                body.getString("id", ""),
                body.getString("code", ""),
                body.getString(PASSWD_FIELD, ""),
                body.getBoolean("byPassActivationCode", false), r -> handleStatus(r.succeeded() && r.result(), context));
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
    private void passwordRenewCheck(RoutingContext context) {
        securityService.passwordRenewCheck(context.request().getParam("id"), context.request().getParam("code"), jsonUser -> {
            if (jsonUser.succeeded()) {
                if (jsonUser.result() == null) {
                    handleStatus(false, context);
                } else {
                    handleResponse(context, jsonUser.result());
                }
            } else {
                handleStatus(false, context);
            }
        });
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
    private void passwordRenew(RoutingContext context) {
        securityService.passwordRenew(context.getBodyAsJson().getString(PARAM_LOGIN), getLocale(context), r -> {
            if (r.succeeded()) {
                handleStatus(r.result(), context);
            } else {
                utils.handleError(context, new QaobeeException(ExceptionCodes.UNKNOWN_LOGIN, Messages.getString("login.wronglogin", getLocale(context))));
            }
        });
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
    private void logout(RoutingContext context) {
        securityService.logout(context.request().getHeader("token"), r -> handleStatus(r.succeeded() && r.result(), context));
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
    private void login(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        securityService.login(body.getString(PARAM_LOGIN),
                body.getString(PARAM_PWD),
                body.getString(MOBILE_TOKEN),
                body.getString(PARAM_PUSH_ID),
                body.getString(PARAM_OS),
                getLocale(context), handleResponse(context));
    }
}
