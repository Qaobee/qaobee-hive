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
import com.qaobee.hive.services.UserService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import com.qaobee.hive.verticles.PDFVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.http.protocol.HTTP;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Shipping route.
 */
@VertxRoute(rootPath = "/api/" + Module.V1 + "/commons/users/shipping")
public class ShippingRoute extends AbstractRoute {

    @Inject
    private ShippingService shippingService;
    @Inject
    private UserService userService;

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

        addRoute(router, "/invoices/:planid", HttpMethod.GET,
                authHandler,
                this::getInvoices);
        addRoute(router, "/invoice/:planid/:id", HttpMethod.GET,
                authHandler,
                this::getInvoice);
        return router;
    }

    private void getInvoice(RoutingContext context) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        shippingService.getInvoice(context.request().getParam("id"), ar -> {
            JsonObject invoice = ar.result();
            JsonObject plan = context.user().principal().getJsonObject("account").getJsonArray("listPlan").getJsonObject(Integer.parseInt(context.request().getParam("planid")));
            JsonObject data = new JsonObject()
                    .put("data", new JsonObject()
                            .put("receipt_number", invoice.getString("receiptNumber"))
                            .put("amountPaid", plan.getInteger("amountPaid") + ",00")
                            .put("card", plan.getJsonObject("cardInfo").getString("last4"))
                            .put("brand", plan.getJsonObject("cardInfo").getString("brand").toLowerCase())
                            .put("paidDate", dateFormat.format(new Date(invoice.getLong("date") * 1000)))
                            .put("plan", plan.getString("levelPlan"))
                    )
                    .put("template", "bill.ftl")
                    .put("filename", context.request().getParam("id"));
            vertx.eventBus().send(PDFVerticle.GENERATE_HTML, data, new DeliveryOptions().setSendTimeout(Constants.TIMEOUT), (AsyncResult<Message<JsonObject>> pdfResp) -> {
                try {
                    if (pdfResp.failed()) {
                        throw pdfResp.cause();
                    } else {
                        vertx.fileSystem().readFile(pdfResp.result().body().getString(PDFVerticle.HTML), file -> context.response()
                                .putHeader(HTTP.CONTENT_TYPE, PDFVerticle.CONTENT_TYPE_HTML)
                                .setStatusCode(200).end(file.result()));
                    }
                } catch (Throwable e) { // NOSONAR
                    e.printStackTrace();
                    utils.handleError(context, new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e));
                }
            });
        });
    }

    private void unsubscribe(RoutingContext context) {
        shippingService.unsubscribe(context.user().principal(), Integer.parseInt(context.request().getParam("id")), getLocale(context), handleResponse(context));
    }

    private void getInvoices(RoutingContext context) {
        shippingService.getInvoices(context.user().principal(), Integer.parseInt(context.request().getParam("planid")), handleResponse(context));
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
