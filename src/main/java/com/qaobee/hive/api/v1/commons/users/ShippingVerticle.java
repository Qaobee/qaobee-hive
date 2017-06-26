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
import com.qaobee.hive.dao.ShippingDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * The type Shipping verticle.
 */
@DeployableVerticle
public class ShippingVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(ShippingVerticle.class);
    /**
     * The constant PAY.
     */
    public static final String PAY = Module.VERSION + ".commons.users.shipping.pay";
    /**
     * The constant WEB_HOOK.
     */
    public static final String WEB_HOOK = Module.VERSION + ".commons.users.shipping.webHook";

    @Inject
    private ShippingDAO shippingDAO;

    @Override
    public void start() {
        super.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug(this.getClass().getName() + " started");
        }
        vertx.eventBus().consumer(WEB_HOOK, this::webHook);
        vertx.eventBus().consumer(PAY, this::pay);
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
    @Rule(address = PAY, method = Constants.POST, logged = true, mandatoryParams = "data", scope = Rule.Param.BODY)
    private void pay(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        replyJsonObject(message, shippingDAO.pay(req.getUser(), new JsonObject(req.getBody()).getJsonObject("data"), req.getLocale()));
    }

    /**
     * @apiDescription Get notified by PayPlug when a payment is done
     * @api {post} /api/1/commons/users/shipping/webHook Get notified by PayPlug when a payment is done
     * @apiName Payment Notification
     * @apiGroup Shipping API
     * @apiParam {object} payment Payment object : see https://gitlab.com/qaobee/com.qaobee.payplug/wikis/notification_url
     * @apiSuccess {object} status Status
     */
    @Rule(address = WEB_HOOK, method = Constants.POST, mandatoryParams = {"id", "created"}, scope = Rule.Param.BODY)
    private void webHook(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        shippingDAO.webHook(new JsonObject(req.getBody())).done(r->utils.sendStatus(r, message)).fail(e -> utils.sendError(message, e));
    }
}
