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
import com.qaobee.hive.services.ShippingService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Shipping route.
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/commons/users/shipping")
public class ShippingRoute extends AbstractRoute {

    @Inject
    private ShippingService shippingService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/pay", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, "planId", "levelPlan", "user_id", "yearly", "token"),
                this::pay);
        addRoute(router, "/unsubscribe/:id", HttpMethod.GET,
                authHandler,
                this::unsubscribe);

        addRoute(router, "/webHook", HttpMethod.POST,
                this::webHook);

        return router;
    }

    private void unsubscribe(RoutingContext context) {
        shippingService.unsubscribe(context.user().principal(), Integer.parseInt(context.request().getParam("id")), getLocale(context), handleResponse(context));
    }

    /**
     * @apiDescription Do a payment
     * @api {post} /api/1/commons/users/shipping/pay Do a payment
     * @apiName Payment
     * @apiGroup Shipping API
     * @apiParam {Object} data data
     * @apiHeader {String} token
     * @apiSuccess {Object} status Status with a redirect link if any
     * @apiHeader {String} token
     */
    private void pay(RoutingContext context) {
        shippingService.pay(context.user().principal(), context.getBodyAsJson(), getLocale(context), handleResponse(context));
    }

    /**
     * @apiDescription Get notified by PayPlug when a payment is done
     * @api {post} /api/1/commons/users/shipping/webHook Get notified by PayPlug when a payment is done
     * @apiName Payment Notification
     * @apiGroup Shipping API
     * @apiParam {object} payment Payment object : see https://gitlab.com/qaobee/com.qaobee.payplug/wikis/notification_url
     * @apiSuccess {object} status Status
     */
    private void webHook(RoutingContext context) {
        shippingService.webHook(context.getBodyAsJson(), r -> {
            if (r.succeeded()) {
                handleStatus(r.result(), context);
            } else {
                utils.handleError(context, r.cause());
            }
        });
    }
}
