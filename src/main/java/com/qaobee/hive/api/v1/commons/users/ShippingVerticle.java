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
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Params;
import com.qaobee.hive.technical.utils.AuthCheck;
import com.qaobee.hive.technical.utils.MailUtils;
import com.qaobee.hive.technical.utils.PersonUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.payplug.PayPlug;
import com.qaobee.payplug.model.Customer;
import com.qaobee.payplug.model.HostedPayment;
import com.qaobee.payplug.model.Payment;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Shipping verticle.
 */
@DeployableVerticle
public class ShippingVerticle extends AbstractGuiceVerticle {
    /**
     * The Constant REGISTER.
     */
    public static final String PAY = Module.VERSION + ".commons.users.shipping.pay";
    /**
     * The constant SAVE_CARD.
     */
    public static final String SAVE_CARD = Module.VERSION + ".commons.users.shipping.save_card";
    /**
     * The constant REFUND.
     */
    public static final String REFUND = Module.VERSION + ".commons.users.shipping.refund";
    /**
     * The constant TRIGGERED_RECURING_PAYMENT.
     */
    public static final String TRIGGERED_RECURING_PAYMENT = Module.VERSION + ".commons.users.shipping.recuring_payment";

    // MongoDB driver
    @Inject
    private MongoDB mongo;
    @Inject
    private MailUtils mailUtils;
    @Inject
    private AuthCheck authCheck;
    @Inject
    private Utils utils;
    @Inject
    private PersonUtils personUtils;
    @Inject
    private PayPlug payPlug;

    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");

        /**
         * @apiDescription Do a payment
         * @api {post} /api/1/commons/users/shipping/pay Do a payment
         * @apiName pay
         * @apiGroup ShippingVerticle API
         * @apiParam {int} plan_id index of the plan in the user's plans list
         * @apiHeader {String} token
         * @apiSuccess {object} status status with a redirect link if any
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(PAY, new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                try {
                    // Check param mandatory
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    utils.isUserLogged(req);
                    utils.testMandatoryParams(req.getBody(), "plan_id");
                    JsonObject body = new JsonObject(req.getBody());
                    Payment payment = new Payment();
                    // TODO : sécuriser en fonction de l'index fourni
                    Plan plan = req.getUser().getAccount().getListPlan().get(body.getInteger("plan_id"));
                    int amount = 0;
                    if (Params.containsKey("plan." + plan.getLevelPlan() + ".price")) {
                        amount = Integer.parseInt(Params.getString("plan." + plan.getLevelPlan() + ".price"));
                    }
                    if (amount == 0) {
                        // FREEMIUM, nothing to do
                        utils.sendStatus(true, message);
                        return;
                    }
                    payment.setAmount(amount);
                    payment.setCurrency("EUR");

                    Customer customer = new Customer();
                    customer.setFirstName(req.getUser().getFirstname());
                    customer.setLastName(req.getUser().getName());
                    customer.setEmail(req.getUser().getContact().getEmail());
                    payment.setCustomer(customer);

                    HostedPayment hostedPayment = new HostedPayment();
                    hostedPayment.setCancelUrl(container.config().getObject("payplug").getString("cancel_url"));
                    hostedPayment.setReturnUrl(container.config().getObject("payplug").getString("return_url"));
                    payment.setHostedPayment(hostedPayment);

                    payment.setNotificationUrl(container.config().getObject("payplug").getString("ipn_url"));

                    Map<String, String> metaDatas = new HashMap<>();
                    metaDatas.put("customer_id", req.getUser().get_id());
                    payment.setMetadata(metaDatas);

                    payment.setSaveCard(true);
                    payment.setForce3ds(true);

                    JsonObject res = new JsonObject(payPlug.createPayment(payment));
                    container.logger().debug(res.toString());
                    req.getUser().getAccount().getListPlan().get(body.getInteger("plan_id")).setAmountPaid(amount);
                    req.getUser().getAccount().getListPlan().get(body.getInteger("plan_id")).setPaiementURL(res.getObject("hosted_payment").getString("payment_url"));
                    req.getUser().getAccount().getListPlan().get(body.getInteger("plan_id")).setStatus("pending");
                    req.getUser().getAccount().getListPlan().get(body.getInteger("plan_id")).setPaymentId(res.getString("id"));

                    // TODO ajouter la notion de carte
                  //  req.getUser().getAccount().getListPlan().get(body.getInteger("plan_id")).setCard(res.getString("id"));
                    JsonObject messageResponse = new JsonObject();
                    messageResponse.putBoolean("status", true);
                    messageResponse.putObject("payment", res);
                    message.reply(messageResponse.toString());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });
    }
}
