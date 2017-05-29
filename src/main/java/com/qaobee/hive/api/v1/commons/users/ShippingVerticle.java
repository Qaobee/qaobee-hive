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
 * The type Shipping verticle.
 */
// Ugly hack because of a bug in Vert.X 2.x, must be in the main thread WTF!!
// https://groups.google.com/forum/#!topic/vertx/KvtxhkA0wiM
@DeployableVerticle(isWorker = false)
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
    /**
     * The constant PARAM_PLAN_ID.
     */
    public static final String PARAM_PLAN_ID = "planId";

    @Inject
    private ShippingDAO shippingDAO;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(WEB_HOOK, this::webHook)
                .registerHandler(PAY, this::pay);
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
        try {
            JsonObject body = new JsonObject(req.getBody());
            JsonObject value = shippingDAO.pay(req.getUser(), body.getObject("data"), req.getLocale());
            message.reply(value.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
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
        try {
            JsonObject body = new JsonObject(req.getBody());
            utils.sendStatus(shippingDAO.webHook(body), message);
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
