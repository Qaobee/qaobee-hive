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
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.MailUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpClient;
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
    private static final java.lang.String PAID_DATE_FIELD = "paidDate";
    private static final String LEVEL_PLAN_FIELD = "levelPlan";
    private static final String HOSTED_PAYMENT_FIELD = "hosted_payment";
    private static final String PAYMENT_URL_FIELD = "payment_url";
    private static final String MONTHLY = "monthly";
    private static final String METADATA_FIELD = "metadata";
    private static final String CUSTOMER_ID_FIELD = "customer_id";
    private static final String LOCALE_FIELD = "locale";
    private static final String ACCOUNT_FIELD = "account";
    private static final String LIST_PLAN_FIELD = "listPlan";
    private static final String CARD_INFO_FIELD = "cardInfo";
    private static final String PAYMENT_ID_FIELD = "paymentId";
    private static final String SHIPPING_LIST_FIELD = "shippingList";

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

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        client = vertx.createHttpClient().setKeepAlive(true).setHost(config.getString("baseUrl"))
                .setPort(config.getInteger("port"));
        if (config.getInteger("port") == 443) {
            client.setSSL(true).setTrustAll(true);
        }
        vertx.eventBus()
                .registerHandler(IPN, this::ipnHandler)
                .registerHandler(PAY, this::payHandler)
                .registerHandler(TRIGGERED_RECURING_PAYMENT, this::triggeredPaymentHandler);
        vertx.setPeriodic(1000 * 60 * 60 * 24L, this::periodicHandler);
    }

    /**
     * Periodic timer, each day it runs
     */
    private void periodicHandler(Long aLong) {
        // oh we are ticking each 24h after the startup time
        // First, let's collect all the guys
        DBObject statusQuery = new BasicDBObject(STATUS, "paid");
        // TODO : change paid level plan here
        DBObject fields = new BasicDBObject("$elemMatch", statusQuery);
        DBObject query = new BasicDBObject("account.listPlan", fields);
        DBCursor result = mongo.getDb().getCollection(User.class.getSimpleName()).find(query);
        while (result.hasNext()) {
            DBObject p = result.next();
            vertx.eventBus().send(TRIGGERED_RECURING_PAYMENT, new JsonObject(p.toString()));
        }
    }

    private void triggeredPaymentHandler(Message<JsonObject> message) {
        final JsonObject user = message.body();
        JsonArray listPlan = user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD);
        for (int i = 0; i < listPlan.size(); i++) {
            JsonObject plan = listPlan.get(i);
            // TODO : change paid level plan here
            if (plan != null && plan.containsField(LEVEL_PLAN_FIELD)
                    && plan.containsField(CARD_INFO_FIELD)
                    && plan.getObject(CARD_INFO_FIELD) != null
                    && !"null".equals(plan.getObject(CARD_INFO_FIELD).getString("id"))) {
                String s = plan.getString("periodicity", "");
                if (MONTHLY.equals(s)) {
                    triggerMonthly(plan, message, user, i);
                } else {
                    utils.sendStatusJson(false, message);
                }
            } else {
                utils.sendStatusJson(false, message);
            }
        }
        if (listPlan.size() == 0) {
            utils.sendStatusJson(false, message);
        }
    }

    /**
     * @apiDescription Do a payment
     * @api {post} /api/1/commons/users/shipping/pay Do a payment
     * @apiName Payment
     * @apiGroup Shipping API
     * @apiParam {int} plan_id index of the plan in the user's plans list
     * @apiHeader {String} token
     * @apiSuccess {object} status Status with a redirect link if any
     */
    @Rule(address = PAY, method = Constantes.POST, logged = true, mandatoryParams = {PARAM_PLAN_ID}, scope = Rule.Param.BODY)
    private void payHandler(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            final JsonObject body = new JsonObject(req.getBody());
            final int planId = Integer.parseInt(body.getString(PARAM_PLAN_ID));
            if (req.getUser().getAccount().getListPlan().size() <= planId) {
                utils.sendError(message, new QaobeeException(ExceptionCodes.INVALID_PARAMETER, planId + " is not present"));
                return;
            }
            Plan plan = req.getUser().getAccount().getListPlan().get(planId);
            int amount = 0;
            if (getContainer().config().getObject(RUNTIME).getObject("plan").containsField(plan.getLevelPlan().name())) {
                amount = getContainer().config().getObject(RUNTIME).getObject("plan").getObject(plan.getLevelPlan().name()).getInteger("price");
            }
            if (amount == 0) {
                // FREEMIUM, nothing to do
                utils.sendStatus(true, message);
                return;
            }
            sendPayplugPayment(req, planId, amount, message, generatePayment(amount, req, planId).encode());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
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
    @Rule(address = IPN, method = Constantes.POST, mandatoryParams = {"id", "id", METADATA_FIELD, "created_at"}, scope = Rule.Param.BODY)
    private void ipnHandler(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            final JsonObject body = new JsonObject(req.getBody());
            if (!body.getObject(METADATA_FIELD).containsField(PARAM_PLAN_ID)
                    || !body.getObject(METADATA_FIELD).containsField(CUSTOMER_ID_FIELD)
                    || StringUtils.isBlank(body.getObject(METADATA_FIELD).getString(PARAM_PLAN_ID))
                    || StringUtils.isBlank(body.getObject(METADATA_FIELD).getString(CUSTOMER_ID_FIELD))
                    ) {
                throw new IllegalArgumentException("some metadatas are missing");
            }
            int planId = Integer.parseInt(body.getObject(METADATA_FIELD).getString(PARAM_PLAN_ID));
            final JsonObject user = mongo.getById(body.getObject(METADATA_FIELD).getString(CUSTOMER_ID_FIELD), User.class);
            final User u = Json.decodeValue(user.encode(), User.class);
            if (body.getObject("failure") != null) {
                // -> Send a mail with the payment url link
                sendPaymentMail(body, planId, u, message);
            } else {
                if ("payment".equals(body.getString("object"))) {
                    makePayment(body, user, u, planId, message);
                } else {
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

    private void sendPayplugPayment(final RequestWrapper req, final int planId, final long amount, final Message<String> message, String requestBody) {
        client.post(config.getString("basePath") + "/payments", resp -> {
            if (resp.statusCode() >= 200 && resp.statusCode() < 400) {
                resp.bodyHandler(buffer -> handlePayplugPaymentResponse(buffer, req, planId, amount, message));
            } else {
                utils.sendError(message, new QaobeeException(ExceptionCodes.HTTP_ERROR, resp.statusCode() + " : " + resp.statusMessage()));
            }
        })
                .putHeader("Authorization", "Bearer " + config.getString("api_key"))
                .putHeader(HTTP.CONTENT_TYPE, "application/json")
                .putHeader(HTTP.CONTENT_LEN, String.valueOf(requestBody.length()))
                .write(requestBody).end();
    }

    private void triggerMonthly(JsonObject plan, Message<JsonObject> message, JsonObject user, int planId) {
        long paidDate = plan.getLong(PAID_DATE_FIELD);
        Calendar initialPaidDate = Calendar.getInstance();
        initialPaidDate.setTime(new Date(paidDate));
        initialPaidDate.add(Calendar.MONTH, 1);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        // test if we are the last day of the next month following the last fee
        if (now.after(initialPaidDate)) {
            // 1- Trigger payment
            Payment payment = new Payment();
            int amount = 0;
            if (getContainer().config().getObject(RUNTIME).getObject("plan").containsField(plan.getString(LEVEL_PLAN_FIELD))) {
                amount = getContainer().config().getObject(RUNTIME).getObject("plan").getObject(plan.getString(LEVEL_PLAN_FIELD)).getInteger("price");
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
            payment.setPayment_method(plan.getObject(CARD_INFO_FIELD).getString("id"));
            Map<String, String> metaDatas = new HashMap<>();
            metaDatas.put(CUSTOMER_ID_FIELD, user.getString("_id"));
            metaDatas.put(PARAM_PLAN_ID, String.valueOf(planId));
            metaDatas.put(LOCALE_FIELD, user.getObject("country").getString("local"));
            payment.setMetadata(metaDatas);
            payment.setSave_card(false);
            payment.setForce_3ds(true);
            sendPayplugRecurringPayment(user, planId, amount, message, new JsonObject(Json.encode(payment)).encode());
        } else {
            utils.sendStatusJson(false, message);
        }
    }

    private void sendPayplugRecurringPayment(final JsonObject user, final int planId, final long amount, final Message<JsonObject> message, String requestBody) {
        client.post(config.getString("basePath") + "/payments", resp -> {
                    if (resp.statusCode() >= 200 && resp.statusCode() < 400) {
                        resp.bodyHandler(buffer -> {
                            // The entire response body has been received
                            JsonObject res = new JsonObject(buffer.toString());
                            ((JsonObject) user.getObject(ACCOUNT_FIELD)
                                    .getArray(LIST_PLAN_FIELD)
                                    .get(planId))
                                    .putNumber("amountPaid", amount);
                            ((JsonObject) user.getObject(ACCOUNT_FIELD)
                                    .getArray(LIST_PLAN_FIELD)
                                    .get(planId))
                                    .putString(PAYMENT_ID_FIELD, res.getString("id"));
                            ((JsonObject) user.getObject(ACCOUNT_FIELD)
                                    .getArray(LIST_PLAN_FIELD)
                                    .get(planId))
                                    .putString("periodicity", MONTHLY);
                            if (res.getObject("card").getString("id", null) != null) {
                                ((JsonObject) user.getObject(ACCOUNT_FIELD)
                                        .getArray(LIST_PLAN_FIELD)
                                        .get(planId))
                                        .putObject(CARD_INFO_FIELD, res.getObject("card"));
                            }
                            try {
                                ((JsonObject) user.getObject(ACCOUNT_FIELD)
                                        .getArray(LIST_PLAN_FIELD)
                                        .get(planId))
                                        .putString("cardId", res.getString("id"));
                                mongo.save(user, User.class);
                                utils.sendStatusJson(true, message);
                            } catch (QaobeeException e) {
                                LOG.error(e.getMessage(), e);
                                utils.sendErrorJ(message, e.getCode(), e.getMessage());
                            }
                        });
                    } else {
                        LOG.error(resp.statusCode() + " : " + resp.statusMessage());
                        utils.sendErrorJ(message, ExceptionCodes.HTTP_ERROR,
                                resp.statusCode() + " : " +
                                        resp.statusMessage());
                    }
                }
        )
                .putHeader("Authorization", "Bearer " + config.getString("api_key"))
                .putHeader(HTTP.CONTENT_TYPE, "application/json")
                .putHeader(HTTP.CONTENT_LEN, String.valueOf(requestBody.length()))
                .write(requestBody)
                .end();
    }

    private void handlePayplugPaymentResponse(Buffer buffer, RequestWrapper req, int planId, long amount, Message<String> message) {
        try {
            // The entire response body has been received
            JsonObject res = new JsonObject(buffer.toString());
            req.getUser().getAccount().getListPlan().get(planId).setAmountPaid(amount);
            req.getUser().getAccount().getListPlan().get(planId).setPaiementURL(res.getObject(HOSTED_PAYMENT_FIELD).getString(PAYMENT_URL_FIELD));
            req.getUser().getAccount().getListPlan().get(planId).setPaymentId(res.getString("id"));
            req.getUser().getAccount().getListPlan().get(planId).setPeriodicity(MONTHLY);
            if (res.getObject("card").getString("id", null) != null) {
                req.getUser().getAccount().getListPlan().get(planId).setCardInfo(Json.decodeValue(res.getObject("card").encode(), Card.class));
            }
            mongo.save(req.getUser());
            JsonObject messageResponse = new JsonObject()
                    .putBoolean(STATUS, true)
                    .putString(PAYMENT_URL_FIELD, res.getObject(HOSTED_PAYMENT_FIELD).getString(PAYMENT_URL_FIELD));
            req.getUser().getAccount().getListPlan().get(planId).setCardId(res.getString("id"));
            message.reply(messageResponse.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }

    }

    private JsonObject generatePayment(int amount, RequestWrapper req, int planId) {
        Payment payment = new Payment();
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
        metaDatas.put(CUSTOMER_ID_FIELD, req.getUser().get_id());
        metaDatas.put(PARAM_PLAN_ID, String.valueOf(planId));
        metaDatas.put(LOCALE_FIELD, req.getLocale());
        payment.setMetadata(metaDatas);
        payment.setSave_card(true);
        payment.setForce_3ds(true);
        JsonObject requestBody = new JsonObject(Json.encode(payment));
        requestBody.removeField("payment_method");
        return requestBody;
    }

    private void sendPaymentMail(final JsonObject body, int planId, final User u, final Message<String> message) {
        final JsonObject tplReq = new JsonObject();
        tplReq.putString(TemplatesVerticle.TEMPLATE, "payment.html");
        tplReq.putObject(TemplatesVerticle.DATA, mailUtils.generateRefusedCardBody(u,
                body.getObject(METADATA_FIELD).getString(LOCALE_FIELD),
                u.getAccount().getListPlan().get(planId),
                body.getObject("failure").getString("code"), getContainer().config()));

        vertx.eventBus().send(TemplatesVerticle.TEMPLATE_GENERATE, tplReq, (Handler<Message<JsonObject>>) tplResp -> {
            final String tplRes = tplResp.body().getString("result");
            final JsonObject emailReq = new JsonObject();
            emailReq.putString("from", getContainer().config().getObject(RUNTIME).getString("mail.from"));
            emailReq.putString("to", u.getContact().getEmail());
            emailReq.putString("subject", Messages.getString("mail.payment.subject", body.getObject(METADATA_FIELD).getString(LOCALE_FIELD)));
            emailReq.putString("content_type", "text/html");
            emailReq.putString("body", tplRes);
            vertx.eventBus().publish("mailer.mod", emailReq);
            utils.sendStatus(false, message);
        });
    }

    private void makePayment(final JsonObject body, JsonObject user, final User u, int planId, final Message<String> message) throws QaobeeException {
        if (body.containsField("is_paid") && body.getBoolean("is_paid")) {
            if (user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).size() < planId) {
                throw new IllegalArgumentException("some metadatas are wrong");
            }
            ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                    .putString(STATUS, "paid");
            ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                    .putObject(CARD_INFO_FIELD, body.getObject("card"));

            ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                    .putNumber(PAID_DATE_FIELD, body.getLong("created_at") * 1000);
            if (!((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId)).containsField(SHIPPING_LIST_FIELD)
                    || ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId)).getArray(SHIPPING_LIST_FIELD, null) == null) {
                ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                        .putArray(SHIPPING_LIST_FIELD, new JsonArray());
            }
            JsonObject payment = new JsonObject();

            // TODO : Verify if created_at evolve with recurring payments
            payment.putNumber(PAID_DATE_FIELD, body.getLong("created_at") * 1000L);
            payment.putNumber("amountPaid", body.getLong("amount") / 100L);
            payment.putObject(CARD_INFO_FIELD, body.getObject("card"));
            payment.putString(PAYMENT_ID_FIELD,
                    ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                            .getString(PAYMENT_ID_FIELD));
            payment.putString("id", UUID.randomUUID().toString());

            ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                    .getArray(SHIPPING_LIST_FIELD).add(payment);
            mongo.save(user, User.class);
            final JsonObject tplReq = new JsonObject();
            tplReq.putString(TemplatesVerticle.TEMPLATE, "payment.html");
            tplReq.putObject(TemplatesVerticle.DATA, mailUtils.generatePaymentBody(u,
                    body.getObject(METADATA_FIELD).getString(LOCALE_FIELD),
                    u.getAccount().getListPlan().get(planId), getContainer().config()));

            vertx.eventBus().send(TemplatesVerticle.TEMPLATE_GENERATE, tplReq, (Handler<Message<JsonObject>>) tplResp -> {
                final String tplRes = tplResp.body().getString("result");
                final JsonObject emailReq = new JsonObject();
                emailReq.putString("from", getContainer().config().getObject(RUNTIME).getString("mail.from"));
                emailReq.putString("to", u.getContact().getEmail());
                emailReq.putString("subject",
                        Messages.getString("mail.payment.subject",
                                body.getObject(METADATA_FIELD).getString(LOCALE_FIELD)));
                emailReq.putString("content_type", "text/html");
                emailReq.putString("body", tplRes);
                vertx.eventBus().publish("mailer.mod", emailReq);
                final JsonObject resp = new JsonObject();
                resp.putBoolean(STATUS, true);
                utils.sendStatus(true, message);
            });
        } else {
            // WTF, it's not paid !!! bloody hell !
            LOG.info(body.encode());
            utils.sendStatus(false, message);
        }
    }
}
