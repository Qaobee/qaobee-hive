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
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/commons/users/signup")
public class SignupRoute extends AbstractRoute {
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
    private UserService userService;
    @Inject
    private SignupService signupService;
    @Inject
    private NotificationsService notificationsService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/logintest", HttpMethod.POST,
                c -> mandatoryHandler.testBodyParams(c, PARAM_LOGIN),
                this::loginTest);

        addRoute(router, "/register", HttpMethod.PUT,
                c -> mandatoryHandler.testBodyParams(c, PARAM_CAPTCHA),
                this::registerUser);

        addRoute(router, "/accountcheck", HttpMethod.GET,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ID, PARAM_CODE),
                this::accountCheck);

        addRoute(router, "/firstconnectioncheck", HttpMethod.GET,
                c -> mandatoryHandler.testRequestParams(c, PARAM_ID, PARAM_CODE),
                this::firstConnectionCheck);

        addRoute(router, "/finalize", HttpMethod.POST,
                c -> mandatoryHandler.testBodyParams(c, PARAM_USER, PARAM_CODE, PARAM_ACTIVITY, PARAM_STRUCTURE, PARAM_CATEGORY_AGE),
                this::finalizeSignup);

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
    private void finalizeSignup(RoutingContext context) {
        final JsonObject body = context.getBodyAsJson();
        signupService.finalizeSignup(body.getJsonObject(PARAM_USER),
                body.getString(PARAM_CODE),
                body.getString(PARAM_ACTIVITY),
                body.getJsonObject(PARAM_STRUCTURE),
                body.getJsonObject(PARAM_CATEGORY_AGE),
                body.getString(COUNTRY_FIELD, "CNTR-250-FR-FRA"),
                getLocale(context), u -> {
                    if (u.succeeded()) {
                        signupService.sendRegisterMail(u.result(), getLocale(context), r -> {
                            if (r.succeeded()) {
                                JsonObject notification = new JsonObject()
                                        .put("content", Messages.getString("notification.first.connection.content", String.valueOf(runtime.getInteger("trial.duration")), getLocale(context)))
                                        .put("title", Messages.getString("notification.first.connection.title", getLocale(context)))
                                        .put("senderId", runtime.getString("admin.id")
                                        );
                                notificationsService.sendNotification(u.result().getString("_id"), DBCollections.USER, notification, new JsonArray(), ar -> {
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
     * @apiDescription First connection account check
     * @api {get} /api/1/commons/users/signup/firstconnectioncheck Account validation check
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
     * @api {get} /api/1/commons/users/signup/accountcheck Account validation check
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
        signupService.register(context.getBodyAsJson().getString(PARAM_CAPTCHA), context.getBodyAsJson(), getLocale(context), handleResponse(context));
    }

    /**
     * @apiDescription Login unicity testBodyParams for rest request
     * @api {get} /api/1/commons/users/signup/loginExists Login unicity testBodyParams
     * @apiVersion 0.1.0
     * @apiName loginTest
     * @apiGroup Signup API
     * @apiParam {String} [login] person.account.login
     * @apiSuccess {Object} status {"status", true|false}
     */
    private void loginTest(RoutingContext context) {
        userService.existingLogin(context.getBodyAsJson().getString(PARAM_LOGIN).toLowerCase(),
                ar -> handleStatus(ar.succeeded() && ar.result(), context));
    }
}
