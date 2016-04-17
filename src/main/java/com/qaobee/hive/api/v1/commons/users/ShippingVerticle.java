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

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.api.v1.commons.utils.TemplatesVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Card;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.business.model.shipping.Customer;
import com.qaobee.hive.business.model.shipping.HostedPayment;
import com.qaobee.hive.business.model.shipping.Payment;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.annotations.VerticleHandler;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.tools.Params;
import com.qaobee.hive.technical.utils.MailUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * The type Shipping verticle.
 */
// Ugly hack because of a bug in Vert.X 2.x, must be in the main thread WTF!!
// https://groups.google.com/forum/#!topic/vertx/KvtxhkA0wiM
@DeployableVerticle(isWorker = false)
public class ShippingVerticle extends AbstractGuiceVerticle {
    public static final String PAY = Module.VERSION + ".commons.users.shipping.pay";
    public static final String IPN = Module.VERSION + ".commons.users.shipping.ipn";
    public static final String TRIGGERED_RECURING_PAYMENT = "inner.recuring_payment";
    public static final String PARAM_PLAN_ID = "plan_id";
    private static final Logger LOG = LoggerFactory.getLogger(ShippingVerticle.class);
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;
    @Inject
    private MailUtils mailUtils;
    @Inject
    @Named("payplug")
    private JsonObject config;
    private HttpClient client;

    /**
     * Start void.
     */
    @Override
    @VerticleHandler({
            @Rule(address = IPN, method = Constantes.POST, mandatoryParams = {"id", "id", "metadata", "created_at"}, scope = Rule.Param.BODY),
            @Rule(address = PAY, method = Constantes.POST, logged = true, mandatoryParams = {PARAM_PLAN_ID}, scope = Rule.Param.BODY),
    })
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        client = vertx.createHttpClient().setKeepAlive(true).setHost(config.getString("baseUrl"))
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
                    final JsonObject body = new JsonObject(req.getBody());
                    if (!body.getObject("metadata").containsField("plan_id")
                            || !body.getObject("metadata").containsField("customer_id")
                            || StringUtils.isBlank(body.getObject("metadata").getString("plan_id"))
                            || StringUtils.isBlank(body.getObject("metadata").getString("customer_id"))
                            ) {
                        throw new IllegalArgumentException("some metadatas are missing");
                    }
                    int planId = Integer.parseInt(body.getObject("metadata").getString("plan_id"));
                    final JsonObject user =
                            mongo.getById(body.getObject("metadata").getString("customer_id"), User.class);
                    final User u = Json.decodeValue(user.encode(), User.class);
                    if (body.getObject("failure") != null) {
                        // -> Send a mail with the payment url link
                        final JsonObject tplReq = new JsonObject();
                        tplReq.putString(TemplatesVerticle.TEMPLATE, "payment.html");
                        tplReq.putObject(TemplatesVerticle.DATA, mailUtils.generateRefusedCardBody(u,
                                body.getObject("metadata").getString("locale"),
                                u.getAccount().getListPlan().get(planId),
                                body.getObject("failure").getString("code")));

                        vertx.eventBus()
                                .send(TemplatesVerticle.TEMPLATE_GENERATE, tplReq, new Handler<Message<JsonObject>>() {
                                    @Override
                                    public void handle(final Message<JsonObject> tplResp) {
                                        final String tplRes = tplResp.body().getString("result");
                                        final JsonObject emailReq = new JsonObject();
                                        emailReq.putString("from", Params.getString("mail.from"));
                                        emailReq.putString("to", u.getContact().getEmail());
                                        emailReq.putString("subject", Messages.getString("mail.payment.subject",
                                                body.getObject("metadata").getString("locale")));
                                        emailReq.putString("content_type", "text/html");
                                        emailReq.putString("body", tplRes);
                                        vertx.eventBus().publish("mailer.mod", emailReq);
                                        utils.sendStatus(false, message);
                                    }
                                });
                    } else {
                        switch (body.getString("object")) {
                            // We only have payments, no refunds ;)
                            case "payment":
                                if (body.containsField("is_paid") && body.getBoolean("is_paid")) {
                                    if (user.getObject("account").getArray("listPlan").size() < planId) {
                                        throw new IllegalArgumentException("some metadatas are wrong");
                                    }
                                    ((JsonObject) user.getObject("account").getArray("listPlan").get(planId))
                                            .putString("status", "paid");
                                    ((JsonObject) user.getObject("account").getArray("listPlan").get(planId))
                                            .putObject("cardInfo", body.getObject("card"));

                                    ((JsonObject) user.getObject("account").getArray("listPlan").get(planId))
                                            .putNumber("paidDate", body.getLong("created_at") * 1000);
                                    if (!((JsonObject) user.getObject("account").getArray("listPlan").get(planId)).containsField("shippingList")
                                            || ((JsonObject) user.getObject("account").getArray("listPlan").get(planId)).getArray("shippingList", null) == null) {
                                        ((JsonObject) user.getObject("account").getArray("listPlan").get(planId))
                                                .putArray("shippingList", new JsonArray());
                                    }
                                    JsonObject payment = new JsonObject();

                                    // TODO : Verify if created_at evolve with recurring payments
                                    payment.putNumber("paidDate", body.getLong("created_at") * 1000L);
                                    payment.putNumber("amountPaid", body.getLong("amount") / 100L);
                                    payment.putObject("cardInfo", body.getObject("card"));
                                    payment.putString("paymentId",
                                            ((JsonObject) user.getObject("account").getArray("listPlan").get(planId))
                                                    .getString("paymentId"));
                                    payment.putString("id", UUID.randomUUID().toString());

                                    ((JsonObject) user.getObject("account").getArray("listPlan").get(planId))
                                            .getArray("shippingList").add(payment);
                                    mongo.save(user, User.class);
                                    final JsonObject tplReq = new JsonObject();
                                    tplReq.putString(TemplatesVerticle.TEMPLATE, "payment.html");
                                    tplReq.putObject(TemplatesVerticle.DATA, mailUtils.generatePaymentBody(u,
                                            body.getObject("metadata").getString("locale"),
                                            u.getAccount().getListPlan().get(planId)));

                                    vertx.eventBus().send(TemplatesVerticle.TEMPLATE_GENERATE, tplReq,
                                            new Handler<Message<JsonObject>>() {
                                                @Override
                                                public void handle(final Message<JsonObject> tplResp) {
                                                    final String tplRes = tplResp.body().getString("result");
                                                    final JsonObject emailReq = new JsonObject();
                                                    emailReq.putString("from", Params.getString("mail.from"));
                                                    emailReq.putString("to", u.getContact().getEmail());
                                                    emailReq.putString("subject",
                                                            Messages.getString("mail.payment.subject",
                                                                    body.getObject("metadata").getString("locale")));
                                                    emailReq.putString("content_type", "text/html");
                                                    emailReq.putString("body", tplRes);
                                                    vertx.eventBus().publish("mailer.mod", emailReq);
                                                    final JsonObject resp = new JsonObject();
                                                    resp.putBoolean("status", true);
                                                    utils.sendStatus(true, message);
                                                }
                                            });
                                } else {
                                    // WTF, it's not paid !!! bloody hell !
                                    LOG.info(body.encode());
                                    utils.sendStatus(false, message);
                                }
                                break;
                            default:
                                utils.sendStatus(false, message);
                        }
                    }
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, e);
                } catch (final IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.MANDATORY_FIELD, e.getMessage());
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
                    final JsonObject body = new JsonObject(req.getBody());
                    final int planId = Integer.parseInt(body.getString(PARAM_PLAN_ID));
                    Payment payment = new Payment();
                    if (req.getUser().getAccount().getListPlan().size() <= planId) {
                        utils.sendError(message,
                                new QaobeeException(ExceptionCodes.INVALID_PARAMETER, planId + " is not present"));
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
                    payment.setAmount(amount * 100);
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
                    metaDatas.put("locale", req.getLocale());
                    payment.setMetadata(metaDatas);

                    payment.setSave_card(true);
                    payment.setForce_3ds(true);

                    final int finalAmount = amount;
                    HttpClientRequest request =
                            client.post(config.getString("basePath") + "/payments", new Handler<HttpClientResponse>() {
                                @Override
                                public void handle(final HttpClientResponse resp) {
                                    if (resp.statusCode() >= 200 && resp.statusCode() < 400) {
                                        resp.bodyHandler(new Handler<Buffer>() {
                                            @Override
                                            public void handle(Buffer buffer) {
                                                // The entire response body has been received
                                                JsonObject res = new JsonObject(buffer.toString());
                                                req.getUser().getAccount().getListPlan().get(planId)
                                                        .setAmountPaid(finalAmount);
                                                req.getUser().getAccount().getListPlan().get(planId).setPaiementURL(
                                                        res.getObject("hosted_payment").getString("payment_url"));
                                                req.getUser().getAccount().getListPlan().get(planId)
                                                        .setPaymentId(res.getString("id"));
                                                req.getUser().getAccount().getListPlan().get(planId)
                                                        .setPeriodicity("monthly");
                                                if (res.getObject("card").getString("id", null) != null) {
                                                    req.getUser().getAccount().getListPlan().get(planId).setCardInfo(
                                                            Json.<Card>decodeValue(res.getObject("card").encode(),
                                                                    Card.class));
                                                }
                                                try { // NOSONAR
                                                    mongo.save(req.getUser());
                                                    JsonObject messageResponse = new JsonObject();
                                                    messageResponse.putBoolean("status", true);
                                                    messageResponse.putString("payment_url",
                                                            res.getObject("hosted_payment").getString("payment_url"));
                                                    req.getUser().getAccount().getListPlan().get(planId)
                                                            .setCardId(res.getString("id"));

                                                    message.reply(messageResponse.toString());
                                                } catch (QaobeeException e) {
                                                    LOG.error(e.getMessage(), e);
                                                    utils.sendError(message, e);
                                                }

                                            }
                                        });
                                    } else {
                                        utils.sendError(message, new QaobeeException(ExceptionCodes.HTTP_ERROR,
                                                resp.statusCode() + " : " + resp.statusMessage()));
                                    }
                                }
                            });
                    request.putHeader("Authorization", "Bearer " + config.getString("api_key"))
                            .putHeader("Content-Type", "application/json");
                    JsonObject requestBody = new JsonObject(Json.encode(payment));
                    requestBody.removeField("payment_method");
                    LOG.info(requestBody.encode());
                    request.putHeader("Content-Length", String.valueOf(requestBody.toString().length()));
                    request.write(requestBody.toString());
                    request.end();
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        });

        /**
         * Periodic timer, each day it runs
         */

        long timerID = vertx.setPeriodic(1000 * 60 * 60 * 24, new Handler<Long>() {
            public void handle(Long timerID) {
                // oh we are ticking each 24h after the startup time
                // First, let's collect all the guys
                DBObject statusQuery = new BasicDBObject("status", "paid");
                // TODO : change paid level plan here
                DBObject fields = new BasicDBObject("$elemMatch", statusQuery);
                DBObject query = new BasicDBObject("account.listPlan", fields);
                DBCursor result = mongo.getDb().getCollection(User.class.getSimpleName()).find(query);
                while (result.hasNext()) {
                    DBObject p = result.next();
                    vertx.eventBus().send(TRIGGERED_RECURING_PAYMENT, new JsonObject(p.toString()));
                }
            }
        });

        vertx.eventBus().registerHandler(TRIGGERED_RECURING_PAYMENT, new Handler<Message<JsonObject>>() {
            @Override
            public void handle(final Message<JsonObject> message) {
                final JsonObject user = message.body();
                JsonArray listPlan = user.getObject("account").getArray("listPlan");
                for (int i = 0; i < listPlan.size(); i++) {
                    JsonObject plan = listPlan.get(i);
                    // TODO : change paid level plan here
                    if (plan != null && plan.containsField("levelPlan")
                            && plan.containsField("cardInfo")
                            && plan.getObject("cardInfo") != null
                            && !"null".equals(plan.getObject("cardInfo").getString("id"))) {
                        switch (plan.getString("periodicity", "")) {
                            case "monthly":
                                // test if we have to make pay him (or her) !
                                long paidDate = plan.getLong("paidDate");
                                Calendar initialPaidDate = Calendar.getInstance();
                                initialPaidDate.setTime(new Date(paidDate));
                                initialPaidDate.add(Calendar.MONTH, 1);
                                Calendar now = Calendar.getInstance();
                                now.setTime(new Date());
                                JsonObject account = user.getObject("account");
                                // test if we are the last day of the next month following the last fee
                                if (now.after(initialPaidDate)) {
                                    // 1- Trigger payment
                                    Payment payment = new Payment();
                                    int amount = 0;
                                    if (Params.containsKey("plan." + plan.getString("levelPlan") + ".price")) {
                                        amount = Integer.parseInt(
                                                Params.getString("plan." + plan.getString("levelPlan") + ".price"));
                                    }
                                    if (amount == 0) {
                                        // FREEMIUM, nothing to do
                                        utils.sendStatusJson(true, message);
                                        return;
                                    }
                                    payment.setAmount(amount * 100);
                                    payment.setCurrency("EUR");
                                    Customer customer = new Customer();
                                    customer.setFirst_name(user.getString("firstname"));
                                    customer.setLast_name(user.getString("name"));
                                    customer.setEmail(user.getObject("contact").getString("email"));
                                    payment.setCustomer(customer);
                                    payment.setNotification_url(config.getString("ipn_url"));
                                    payment.setPayment_method(plan.getObject("cardInfo").getString("id"));
                                    Map<String, String> metaDatas = new HashMap<>();
                                    metaDatas.put("customer_id", user.getString("_id"));
                                    metaDatas.put("plan_id", String.valueOf(i));
                                    metaDatas.put("locale", user.getObject("country").getString("local"));
                                    payment.setMetadata(metaDatas);
                                    payment.setSave_card(false);
                                    payment.setForce_3ds(true);

                                    final int finalAmount = amount;
                                    final int finalI = i;
                                    HttpClientRequest request =
                                            client.post(config.getString("basePath") + "/payments",
                                                    new Handler<HttpClientResponse>() {
                                                        @Override
                                                        public void handle(final HttpClientResponse resp) {
                                                            if (resp.statusCode() >= 200 &&
                                                                    resp.statusCode() < 400) {
                                                                resp.bodyHandler(new Handler<Buffer>() {
                                                                    @Override
                                                                    public void handle(Buffer buffer) {
                                                                        // The entire response body has been received
                                                                        JsonObject res =
                                                                                new JsonObject(buffer.toString());
                                                                        ((JsonObject) user.getObject("account")
                                                                                .getArray("listPlan")
                                                                                .get(finalI))
                                                                                .putNumber("amountPaid", finalAmount);
                                                                        ((JsonObject) user.getObject("account")
                                                                                .getArray("listPlan")
                                                                                .get(finalI))
                                                                                .putString("paymentId",
                                                                                        res.getString("id"));
                                                                        ((JsonObject) user.getObject("account")
                                                                                .getArray("listPlan")
                                                                                .get(finalI))
                                                                                .putString("periodicity", "monthly");
                                                                        if (res.getObject("card")
                                                                                .getString("id", null) != null) {
                                                                            ((JsonObject) user.getObject("account")
                                                                                    .getArray("listPlan")
                                                                                    .get(finalI))
                                                                                    .putObject("cardInfo",
                                                                                            res.getObject("card"));
                                                                        }
                                                                        try { // NOSONAR
                                                                            ((JsonObject) user.getObject("account")
                                                                                    .getArray("listPlan")
                                                                                    .get(finalI))
                                                                                    .putString("cardId",
                                                                                            res.getString("id"));
                                                                            mongo.save(user, User.class);
                                                                            utils.sendStatusJson(true, message);
                                                                        } catch (QaobeeException e) {
                                                                            LOG.error(e.getMessage(), e);
                                                                            utils.sendErrorJ(message, e.getCode(),
                                                                                    e.getMessage());
                                                                        }
                                                                    }
                                                                });
                                                            } else {
                                                                LOG.error(resp.statusCode() + " : " +
                                                                        resp.statusMessage());
                                                                utils.sendErrorJ(message, ExceptionCodes.HTTP_ERROR,
                                                                        resp.statusCode() + " : " +
                                                                                resp.statusMessage());
                                                            }
                                                        }
                                                    });
                                    request.putHeader("Authorization", "Bearer " + config.getString("api_key"))
                                            .putHeader("Content-Type", "application/json");
                                    JsonObject requestBody = new JsonObject(Json.encode(payment));
                                    requestBody.removeField("hosted_payment");
                                    request.putHeader("Content-Length",
                                            String.valueOf(requestBody.toString().length()));
                                    request.write(requestBody.toString());
                                    request.end();
                                    break;
                                } else {
                                    utils.sendStatusJson(false, message);
                                }
                            default:
                                utils.sendStatusJson(false, message);
                                break;
                        }
                    } else {
                        utils.sendStatusJson(false, message);
                    }
                }
                if (listPlan.size() == 0) {
                    utils.sendStatusJson(false, message);
                }
            }
        });
    }
}
