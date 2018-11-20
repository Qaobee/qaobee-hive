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
import com.qaobee.hive.services.SignupService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The Class SignupVerticle.
 *
 * @author Xavier MARIN <ul>     <li>resthandler.register : Register a new accunt</li>     <li>resthandler.logintest : Login unicity testBodyParams for rest request</li>     <li>loginExists : Login unicity testBodyParams for internal use</li>     <li>resthandler.accountcheck : email validation number check</li> </ul>
 */
@VertxRoute(rootPath = "/api/" + Module.V2 + "/commons/multi/signup")
public class MultiSignupRoute extends AbstractSignup {
    /**
     * Parameter ID
     */
    public static final String PARAM_ID = "id";
    private static final String PARAM_CAPTCHA = "captcha";
    @Inject
    private SignupService signupService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);
        addRoute(router, "/register", HttpMethod.PUT,
                c -> mandatoryHandler.testBodyParams(c, "user", "structure"),
                this::registerMulti);
        return router;
    }

    /**
     * @apiDescription Register a new account
     * @api {put} /api/1/commons/users/signup/register Register a new account
     * @apiVersion 0.1.0
     * @apiName registerMulti
     * @apiGroup Signup API
     * @apiParam {Object} person com.qaobee.swarn.business.model.tranversal.person.Person
     * @apiSuccess {Object} person com.qaobee.swarn.business.model.tranversal.person.Person
     * @apiError PASSWD_EXCEPTION Password encoding exception
     * @apiError NON_UNIQUE_LOGIN Non unique login
     * @apiError MAIL_EXCEPTION problème d'envoi d'email
     */
    private void registerMulti(RoutingContext context) {
        JsonObject userJson = context.getBodyAsJson().getJsonObject("user");
        signupService.register(userJson.getString(PARAM_CAPTCHA, ""), userJson, getLocale(context), u -> {
            if (u.succeeded()) {
                JsonObject user = u.result().getJsonObject("person");
                JsonObject structure = context.getBodyAsJson().getJsonObject("structure");
                signupService.addStructureToSandbox(user.getString("sandboxDefault"), structure, res -> signupService.sendRegisterMail(user, getLocale(context), r -> sendRegisterMail(u.result(), user, res, context)));
            } else {
                utils.handleError(context, u.cause());
            }
        });
    }
}
