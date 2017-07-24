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
import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.services.SignupService;
import com.qaobee.hive.services.UserService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class SignupVerticle.
 *
 * @author Xavier MARIN <ul>     <li>resthandler.register : Register a new accunt</li>     <li>resthandler.logintest : Login unicity testBodyParams for rest request</li>     <li>loginExists : Login unicity testBodyParams for internal use</li>     <li>resthandler.accountcheck : email validation number check</li> </ul>
 */
@VertxRoute(rootPath = "/api/" + Module.V2 + "/commons/users/signup")
public class UserSignupRoute extends AbstractRoute {
    /**
     * Parameter ID
     */
    public static final String PARAM_ID = "id";
    /**
     * Parameter CODE
     */
    public static final String PARAM_CODE = "code";
    /**
     * Parameter Login
     */
    public static final String PARAM_LOGIN = "login";
    private static final String PARAM_CAPTCHA = "captcha";
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    private UserService userService;
    @Inject
    private SignupService signupService;
    @Inject
    private NotificationsService notificationsService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);
        addRoute(router, "/test/:login", HttpMethod.GET, this::loginTest);
        addRoute(router, "/register", HttpMethod.PUT,
                c -> mandatoryHandler.testBodyParams(c, PARAM_CAPTCHA),
                this::registerUser);

        addRoute(router, "/accountcheck/:id/:code", HttpMethod.GET, this::accountCheck);
        addRoute(router, "/firstconnectioncheck/:id/:code", HttpMethod.GET, this::firstConnectionCheck);

        addRoute(router, "/mailResend", HttpMethod.POST,
                c -> mandatoryHandler.testBodyParams(c, PARAM_LOGIN),
                this::resendMail);

        return router;
    }

    /**
     * @apiDescription Resend a register mail
     * @api {post} /api/1/commons/users/user/mailResend Resend a register mail
     * @apiVersion 0.1.0
     * @apiName resendMail
     * @apiParam {String} login Login
     * @apiGroup Object Status
     */
    private void resendMail(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        signupService.resendMail(body.getString(PARAM_LOGIN), getLocale(context), ar -> {
            if (ar.succeeded()) {
                handleStatus(ar.succeeded(), context);
            } else {
                utils.handleError(context, (QaobeeException) ar.cause());
            }
        });
    }
    /**
     * @apiDescription First connection account check
     * @api {get} /api/1/commons/users/signup/firstconnectioncheck/:id/:code Account validation check
     * @apiParam {String} code Activation code
     * @apiParam {String} id Person id
     * @apiVersion 0.1.0
     * @apiName firstConnectionCheck
     * @apiGroup Signup API
     * @apiSuccess {Object} status {"status", true|false}
     */
    private void firstConnectionCheck(RoutingContext context) {
        signupService.firstConnectionCheck(context.request().getParam(PARAM_ID),
                context.request().getParam(PARAM_CODE), getLocale(context), handleResponse(context));
    }

    /**
     * @apiDescription Account validation check
     * @api {get} /api/1/commons/users/signup/accountcheck/:id/:code Account validation check
     * @apiParam {String} code Activation code
     * @apiParam {String} id Person id
     * @apiVersion 0.1.0
     * @apiName accountCheck
     * @apiGroup Signup API
     * @apiSuccess {Object} status {"status", true|false}
     */
    private void accountCheck(RoutingContext context) {
        signupService.accountCheck(context.request().getParam(PARAM_ID),
                context.request().getParam(PARAM_CODE),
                ar -> handleStatus(ar.succeeded() && ar.result(), context));
    }

    /**
     * @apiDescription Register a new account
     * @api {put} /api/1/commons/users/signup/register Register a new account
     * @apiVersion 0.1.0
     * @apiName registerUser
     * @apiGroup Signup API
     * @apiParam {Object} person com.qaobee.swarn.business.model.tranversal.person.Person
     * @apiSuccess {Object} person com.qaobee.swarn.business.model.tranversal.person.Person
     * @apiError PASSWD_EXCEPTION Password encoding exception
     * @apiError NON_UNIQUE_LOGIN Non unique login
     * @apiError MAIL_EXCEPTION problème d'envoi d'email
     */
    private void registerUser(RoutingContext context) {
        signupService.register(context.getBodyAsJson().getString(PARAM_CAPTCHA), context.getBodyAsJson(), getLocale(context), u -> {
            if (u.succeeded()) {
                JsonObject user = u.result().getJsonObject("person");
                signupService.sendRegisterMail(user, getLocale(context), r -> {
                    if (r.succeeded()) {
                        JsonObject notification = new JsonObject()
                                .put("content", Messages.getString("notification.first.connection.content", getLocale(context), String.valueOf(runtime.getInteger("trial.duration"))))
                                .put("title", Messages.getString("notification.first.connection.title", getLocale(context)))
                                .put("senderId", runtime.getString("admin.id")
                                );
                        notificationsService.sendNotification(user.getString("_id"), DBCollections.USER, notification, new JsonArray(), ar -> {
                            //empty
                        });
                        handleResponse(context, u.result());
                    } else {
                        utils.handleError(context, (QaobeeException) r.cause());
                    }
                });
            } else {
                utils.handleError(context, (QaobeeException) u.cause());
            }
        });
    }

    /**
     * @apiDescription Login unicity testBodyParams for rest request
     * @api {get} /api/1/commons/users/signup/:login Login unicity test
     * @apiVersion 0.1.0
     * @apiName loginTest
     * @apiGroup Signup API
     * @apiParam {String} [login] person.account.login
     * @apiSuccess {Object} status {"status", true|false}
     */
    private void loginTest(RoutingContext context) {
        userService.existingLogin(context.request().getParam(PARAM_LOGIN).toLowerCase(),
                ar -> handleStatus(ar.succeeded() && ar.result(), context));
    }
}
