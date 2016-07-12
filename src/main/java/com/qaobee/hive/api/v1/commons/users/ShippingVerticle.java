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
     * The constant IPN.
     */
    public static final String IPN = Module.VERSION + ".commons.users.shipping.ipn";
    /**
     * The constant TRIGGERED_RECURING_PAYMENT.
     */
    public static final String TRIGGERED_RECURING_PAYMENT = "inner.recuring_payment";
    /**
     * The constant PARAM_PLAN_ID.
     */
    public static final String PARAM_PLAN_ID = "plan_id";
    private static final String METADATA_FIELD = "metadata";

    @Inject
    private ShippingDAO shippingDAO;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(IPN, this::ipn)
                .registerHandler(PAY, this::pay)
                .registerHandler(TRIGGERED_RECURING_PAYMENT, this::triggeredPayment);
        vertx.setPeriodic(1000 * 60 * 60 * 24L, this::periodicHandler);
    }

    /**
     * Periodic timer, each day it runs
     */
    private void periodicHandler(long l) {
        LOG.info("Running each " + l);
        shippingDAO.periodicPayment().forEach(user -> vertx.eventBus().send(TRIGGERED_RECURING_PAYMENT, (JsonObject) user));
    }

    private void triggeredPayment(Message<JsonObject> message) {
       shippingDAO.triggeredPayment(message.body()).whenComplete((value, error) -> {
           if (value != null) {
              utils.sendStatusJson(value, message);
           } else {
               QaobeeException e = (QaobeeException) error;
               utils.sendErrorJ(message, e.getCode(), e.getMessage());
           }
       });
    }

    /**
     * @apiDescription Do a payment
     * @api {post} /api/1/commons/users/shipping/pay Do a payment
     * @apiName Payment
     * @apiGroup Shipping API
     * @apiParam {int} plan_id index of the plan in the user's plans list
     * @apiHeader {String} token
     * @apiSuccess {Object} status Status with a redirect link if any
     * @apiHeader {String} token
     */
    @Rule(address = PAY, method = Constants.POST, logged = true, mandatoryParams = {PARAM_PLAN_ID}, scope = Rule.Param.BODY)
    private void pay(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            JsonObject body = new JsonObject(req.getBody());
            shippingDAO.pay(req.getUser(), Integer.parseInt(body.getString(PARAM_PLAN_ID)), req.getLocale()).whenComplete((value, error) -> {
                if (value != null) {
                    message.reply(value.encode());
                } else {
                    utils.sendError(message, (QaobeeException) error);
                }
            });
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Get notified by PayPlug when a payment is done
     * @api {post} /api/1/commons/users/shipping/ipn Get notified by PayPlug when a payment is done
     * @apiName Payment Notification
     * @apiGroup Shipping API
     * @apiParam {object} payment Payment object : see https://gitlab.com/qaobee/com.qaobee.payplug/wikis/notification_url
     * @apiSuccess {object} status Status
     */
    @Rule(address = IPN, method = Constants.POST, mandatoryParams = {"id", "id", METADATA_FIELD, "created_at"}, scope = Rule.Param.BODY)
    private void ipn(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            JsonObject body = new JsonObject(req.getBody());
            utils.sendStatus(shippingDAO.ipn(body), message);
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
