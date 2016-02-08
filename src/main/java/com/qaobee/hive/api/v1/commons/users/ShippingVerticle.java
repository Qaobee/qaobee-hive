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
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.business.model.shipping.Customer;
import com.qaobee.hive.business.model.shipping.HostedPayment;
import com.qaobee.hive.business.model.shipping.Payment;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Params;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Shipping verticle.
 */
// TODO : Ugly hack because of a bug in Vert.X 2.x, must be in the main thread WTF!!
// https://groups.google.com/forum/#!topic/vertx/KvtxhkA0wiM
@DeployableVerticle(isWorker = false)
public class ShippingVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(ShippingVerticle.class);
    public static final String PAY = Module.VERSION + ".commons.users.shipping.pay";
    public static final String IPN = Module.VERSION + ".commons.users.shipping.ipn";
    public static final String TRIGGERED_RECURING_PAYMENT = "inner.recuring_payment";

    public static final String PARAM_PLAN_ID = "plan_id";
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;
    @Inject
    @Named("payplug")
    private JsonObject config;
    private HttpClient client;

    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        client = vertx.createHttpClient()
                .setKeepAlive(true)
                .setHost(config.getString("baseUrl"))
                .setPort(config.getInteger("port"));
        if (config.getInteger("port") == 443) {
            client.setSSL(true).setTrustAll(true);
        }

        /**
         * @apiDescription Get notified by PayPlug when a payment is done
         * @api {post} /api/1/commons/users/shipping/ipn Get notified by PayPlug when a payment is done
         * @apiName Payment Notification
         * @apiGroup Shipping API
         * @apiParam {object} payment Payment object : see https://gitlab.com/qaobee/com.qaobee.payplug/wikis/notification_url
         * @apiSuccess {object} status Status
         */
        vertx.eventBus().registerHandler(IPN, new Handler<Message<String>>() {
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
                    utils.testMandatoryParams(req.getBody(), "id", "payment_id", "metadata", "created_at");
                    JsonObject body = new JsonObject(req.getBody());
                    if (!body.getObject("metadata").containsField("plan_id") || !body.getObject("metadata").containsField("customer_id")) {
                        throw new IllegalArgumentException("some metadatas are missing");
                    }
                    int planId = Integer.parseInt(body.getObject("metadata").getString("plan_id"));
                    JsonObject user = mongo.getById(body.getObject("metadata").getString("customer_id"), User.class);
                    switch (body.getString("object")) {
                        // We only have payments, no refunds ;)
                        case "payment":
                            if (body.containsField("is_paid") && body.getBoolean("is_paid")) {
                                if (user.getObject("account").getArray("listPlan").size() < planId) {
                                    throw new IllegalArgumentException("some metadatas are wrong");
                                }
                                ((JsonObject) user.getObject("account").getArray("listPlan").get(planId))
                                        .putString("status", "paid");
                                // TODO : Verify if created_at evolve with recurring payments
                                ((JsonObject) user.getObject("account").getArray("listPlan").get(planId))
                                        .putNumber("paidDate", body.getLong("created_at"));
                                ((JsonObject) user.getObject("account").getArray("listPlan").get(planId))
                                        .putObject("cardInfo", body.getObject("card"));
                                mongo.save(user, User.class);
                                utils.sendStatus(true, message);
                            } else {
                                // WTF, it's not paid !!! bloody hell !
                                utils.sendStatus(false, message);
                            }
                            break;
                        default:
                            utils.sendStatus(false, message);

                    }
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.MANDATORY_FIELD, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * @apiDescription Do a payment
         * @api {post} /api/1/commons/users/shipping/pay Do a payment
         * @apiName Payment
         * @apiGroup Shipping API
         * @apiParam {int} plan_id index of the plan in the user's plans list
         * @apiHeader {String} token
         * @apiSuccess {object} status Status with a redirect link if any
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
                    utils.testMandatoryParams(req.getBody(), PARAM_PLAN_ID);
                    final JsonObject body = new JsonObject(req.getBody());
                    final int planId = body.getInteger(PARAM_PLAN_ID);
                    Payment payment = new Payment();
                    if (req.getUser().getAccount().getListPlan().size() <= planId) {
                        utils.sendError(message, new QaobeeException(ExceptionCodes.INVALID_PARAMETER, planId + " is not present"));
                        return;
                    }
                    Plan plan = req.getUser().getAccount().getListPlan().get(planId);
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
                    customer.setFirst_name(req.getUser().getFirstname());
                    customer.setLast_name(req.getUser().getName());
                    customer.setEmail(req.getUser().getContact().getEmail());
                    payment.setCustomer(customer);

                    HostedPayment hostedPayment = new HostedPayment();
                    hostedPayment.setCancel_url(config.getString("cancel_url"));
                    hostedPayment.setReturn_url(config.getString("return_url"));
                    payment.setHosted_payment(hostedPayment);

                    payment.setNotification_url(config.getString("ipn_url"));

                    Map<String, String> metaDatas = new HashMap<>();
                    metaDatas.put("customer_id", req.getUser().get_id());
                    metaDatas.put("plan_id", String.valueOf(planId));
                    payment.setMetadata(metaDatas);

                    payment.setSave_card(true);
                    payment.setForce_3ds(true);

                    final int finalAmount = amount;
                    HttpClientRequest request = client.post(config.getString("basePath") + "/payments", new Handler<HttpClientResponse>() {
                        public void handle(final HttpClientResponse resp) {
                            if (resp.statusCode() >= 200 && resp.statusCode() < 400) {
                                resp.bodyHandler(new Handler<Buffer>() {
                                    public void handle(Buffer buffer) {
                                        // The entire response body has been received
                                        JsonObject res = new JsonObject(buffer.toString());
                                        req.getUser().getAccount().getListPlan().get(planId).setAmountPaid(finalAmount);
                                        req.getUser().getAccount().getListPlan().get(planId).setPaiementURL(res.getObject("hosted_payment").getString("payment_url"));
                                        req.getUser().getAccount().getListPlan().get(planId).setStatus("pending");
                                        req.getUser().getAccount().getListPlan().get(planId).setPaymentId(res.getString("id"));
                                        try {
                                            mongo.save(req.getUser());
                                            JsonObject messageResponse = new JsonObject();
                                            messageResponse.putBoolean("status", true);
                                            messageResponse.putString("payment_url", res.getObject("hosted_payment").getString("payment_url"));
                                            req.getUser().getAccount().getListPlan().get(planId).setCardId(res.getString("id"));

                                            message.reply(messageResponse.toString());
                                        } catch (QaobeeException e) {
                                            LOG.error(e.getMessage(), e);
                                            utils.sendError(message, e);
                                        }

                                    }
                                });
                            } else {
                                utils.sendError(message, new QaobeeException(ExceptionCodes.HTTP_ERROR, resp.statusCode() + " : " + resp.statusMessage()));
                            }
                        }
                    });
                    request.putHeader("Authorization", "Bearer " + config.getString("api_key")).putHeader("Content-Type", "application/json");
                    JsonObject requestBody = new JsonObject(Json.encode(payment));
                    requestBody.removeField("payment_method");
                    LOG.info(requestBody.encode());
                    request.putHeader("Content-Length", String.valueOf(requestBody.toString().length()));
                    request.write(requestBody.toString());
                    request.end();
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.MANDATORY_FIELD, e.getMessage());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * Periodic timer, each day it runs

         long timerID = vertx.setPeriodic(1000  * 60 * 60 * 24 , new Handler<Long>() {
         public void handle(Long timerID) {
         // oh we are ticking each 24h after the startup time
         // First, let's collect all the guys
         DBObject statusQuery = new BasicDBObject("status", "paid");
         // TODO : change paid level plan here
         statusQuery.put("levelPlan", "PREMIUM");
         DBObject fields = new BasicDBObject("$elemMatch", statusQuery);
         DBObject query = new BasicDBObject("account.listPlan",fields);
         DBCursor result = mongo.getDb().getCollection(User.class.getSimpleName()).find(query);
         LOG.info(result.size());
         while(result.hasNext()) {
         vertx.eventBus().send(TRIGGERED_RECURING_PAYMENT, new JsonObject(result.next().toString()));
         }
         }
         });

         vertx.eventBus().registerHandler(TRIGGERED_RECURING_PAYMENT, new Handler<Message<JsonObject>>() {
        @Override public void handle(Message<JsonObject> message) {
        JsonObject user = message.body();
        JsonArray listPlan = user.getObject("account").getArray("listPlan");
        for(int i =0; i < listPlan.size(); i++) {
        JsonObject plan = listPlan.get(i);
        // TODO : change paid level plan here
        if(plan.containsField("levelPlan") && plan.getString("levelPlan").equals("PREMIUM")) {
        switch (plan.getString("periodicity")) {
        case "monthly" :
        // test if we have to make pay him (or her) !
        long paidDate = plan.getLong("paidDate");
        Calendar initialPaidDate = Calendar.getInstance();
        initialPaidDate.setTime(new Date(paidDate));
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        // test if we are the last day of the next month following the last fee
        if(now.get(Calendar.MONTH) > initialPaidDate.get(Calendar.MONTH)
        && now.get(Calendar.DAY_OF_MONTH) == now.getActualMaximum(Calendar.DAY_OF_MONTH)) {
        // 1- Trigger payment
        // 1.1- Verify card expiry
        // 1.1.1- if expired -> send a mail with a new payment url (first payment process)
        // 1.1.2- else :
        // 1.2- process a payment with the card id

        // 2- send an email

        // 3- generate a fee
        }
        }
        }

        }
        }
        });
         */
    }
}
