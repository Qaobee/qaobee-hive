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

package com.qaobee.hive.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Card;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.business.model.shipping.Customer;
import com.qaobee.hive.business.model.shipping.HostedPayment;
import com.qaobee.hive.business.model.shipping.Payment;
import com.qaobee.hive.dao.ShippingDAO;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.MailUtils;
import com.qaobee.hive.technical.utils.Utils;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * The type Shipping dao.
 */
public class ShippingDAOImpl implements ShippingDAO {
    private static final Logger LOG = LoggerFactory.getLogger(ShippingDAO.class);

    private static final String PARAM_PLAN_ID = "plan_id";
    private static final String PAID_DATE_FIELD = "paidDate";
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
    private static final String BASE_URL_FIELD = "baseUrl";
    @Inject
    private MongoDB mongo;
    @Inject
    private Vertx vertx;
    @Inject
    private Utils utils;
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    @Named("payplug")
    private JsonObject payplug;
    @Inject
    private MailUtils mailUtils;
    @Inject
    private TemplatesDAO templatesDAO;

    @Override
    public JsonArray periodicPayment() {
        // oh we are ticking each 24h after the startup time
        // First, let's collect all the guys
        DBObject statusQuery = new BasicDBObject(Constants.STATUS, "paid");
        DBObject fields = new BasicDBObject("$elemMatch", statusQuery);
        DBObject query = new BasicDBObject("account.listPlan", fields);
        DBCursor result = mongo.getDb().getCollection(DBCollections.USER).find(query);
        JsonArray res = new JsonArray();
        result.forEach(r -> res.add(new JsonObject(result.next().toString())));
        return res;
    }

    @Override
    public CompletableFuture<Boolean> triggeredPayment(JsonObject user) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        JsonArray listPlan = user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD);
        for (int i = 0; i < listPlan.size(); i++) {
            JsonObject plan = listPlan.get(i);
            if (plan != null && plan.containsField(LEVEL_PLAN_FIELD)
                    && plan.containsField(CARD_INFO_FIELD)
                    && plan.getObject(CARD_INFO_FIELD) != null
                    && !"null".equals(plan.getObject(CARD_INFO_FIELD).getString("id"))) {
                String s = plan.getString("periodicity", "");
                if (MONTHLY.equals(s)) {
                   return triggerMonthly(plan, user, i);
                } else {
                    future.complete(false);
                }
            } else {
                future.complete(false);
            }
        }
        if (listPlan.size() == 0) {
            future.complete(false);
        }
        return future;
    }

    @Override
    public CompletableFuture<JsonObject> pay(User user, int planId, String locale) throws QaobeeException {
        if (user.getAccount().getListPlan().size() <= planId) {
            throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, planId + " is not present");
        }
        Plan plan = user.getAccount().getListPlan().get(planId);
        int amount = 0;
        if (runtime.getObject("plan").containsField(plan.getLevelPlan().name())) {
            amount = runtime.getObject("plan").getObject(plan.getLevelPlan().name()).getInteger("price");
        }
        if (amount != 0) {
            return sendPayplugPayment(user, planId, amount, generatePayment(amount, user, planId, locale));
        }
        CompletableFuture<JsonObject> future = new CompletableFuture<>();
        future.complete(new JsonObject().putBoolean(Constants.STATUS, true));
        return future;
    }

    @Override
    public boolean ipn(JsonObject body) throws QaobeeException {
        utils.testMandatoryParams(body.getObject(METADATA_FIELD).toMap(), PARAM_PLAN_ID, CUSTOMER_ID_FIELD, PARAM_PLAN_ID, CUSTOMER_ID_FIELD);
        try {
            int planId = Integer.parseInt(body.getObject(METADATA_FIELD).getString(PARAM_PLAN_ID));
            final JsonObject user = mongo.getById(body.getObject(METADATA_FIELD).getString(CUSTOMER_ID_FIELD), DBCollections.USER);
            final User u = Json.decodeValue(user.encode(), User.class);
            if (body.getObject("failure") != null) {
                // -> Send a mail with the payment url link
                sendPaymentMail(body, planId, u);
            } else {
                if ("payment".equals(body.getString("object"))) {
                    return makePayment(body, user, u, planId);
                }
            }
            return false;
        } catch (NumberFormatException e) {
            throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, e);
        }
    }

    private CompletableFuture<Boolean> triggerMonthly(JsonObject plan, JsonObject user, int planId) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
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
            if (runtime.getObject("plan").containsField(plan.getString(LEVEL_PLAN_FIELD))) {
                amount = runtime.getObject("plan").getObject(plan.getString(LEVEL_PLAN_FIELD)).getInteger("price");
            }
            if (amount == 0) {
               future.complete(true);
            }
            payment.setAmount(amount * 100);
            payment.setCurrency("EUR");
            Customer customer = new Customer();
            customer.setFirst_name(user.getString("firstname"));
            customer.setLast_name(user.getString("name"));
            customer.setEmail(user.getObject("contact").getString("email"));
            payment.setCustomer(customer);
            payment.setNotification_url(payplug.getString("ipn_url"));
            payment.setPayment_method(plan.getObject(CARD_INFO_FIELD).getString("id"));
            Map<String, String> metaDatas = new HashMap<>();
            metaDatas.put(CUSTOMER_ID_FIELD, user.getString("_id"));
            metaDatas.put(PARAM_PLAN_ID, String.valueOf(planId));
            metaDatas.put(LOCALE_FIELD, user.getObject("country").getString("local"));
            payment.setMetadata(metaDatas);
            payment.setSave_card(false);
            payment.setForce_3ds(true);
            return sendPayplugRecurringPayment(user, planId, amount, new JsonObject(Json.encode(payment)).encode());
        } else {
            future.complete(false);
        }
        return future;
    }

    private CompletableFuture<JsonObject> sendPayplugPayment(final User user, final int planId, final long amount, JsonObject requestBody) {
        CompletableFuture<JsonObject> future = new CompletableFuture<>();
        HttpClient client = vertx.createHttpClient().setKeepAlive(true);
        client.setHost(payplug.getString(BASE_URL_FIELD));
        client.setPort(payplug.getInteger("port"));
        client.setHost(payplug.getString(BASE_URL_FIELD)).setPort(payplug.getInteger("port"));
        if (payplug.getInteger("port") == 443) {
            client.setSSL(true).setTrustAll(true);
        }
        client.exceptionHandler(ex -> future.completeExceptionally(new QaobeeException(ExceptionCodes.HTTP_ERROR, ex.getMessage())));
        client.post(payplug.getString("basePath") + "/payments", resp -> {
            if (resp.statusCode() >= 200 && resp.statusCode() < 400) {
                resp.bodyHandler(buffer -> {
                    try {
                        future.complete(handlePayplugPaymentResponse(buffer, user, planId, amount));
                    } catch (QaobeeException e) {
                        future.completeExceptionally(e);
                    }
                });
            } else {
                future.completeExceptionally(new QaobeeException(ExceptionCodes.HTTP_ERROR, resp.statusCode() + " : " + resp.statusMessage()));
            }
        })
                .putHeader("Authorization", "Bearer " + payplug.getString("api_key"))
                .putHeader(HTTP.CONTENT_TYPE, "application/json")
                .putHeader(HTTP.CONTENT_LEN, String.valueOf(requestBody.encode().length()))
                .end(requestBody.encode());
        return future;
    }

    private CompletableFuture<Boolean> sendPayplugRecurringPayment(final JsonObject user, final int planId, final long amount, String requestBody) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        HttpClient client = vertx.createHttpClient().setKeepAlive(true);
        client.setHost(payplug.getString(BASE_URL_FIELD));
        client.setPort(payplug.getInteger("port"));
        if (payplug.getInteger("port") == 443) {
            client.setSSL(true).setTrustAll(true);
        }
        client.exceptionHandler(ex -> future.completeExceptionally(new QaobeeException(ExceptionCodes.HTTP_ERROR, ex.getMessage())));
        client.post(payplug.getString("basePath") + "/payments", resp -> {
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
                                mongo.save(user, DBCollections.USER);
                                future.complete(true);
                            } catch (QaobeeException e) {
                                LOG.error(e.getMessage(), e);
                                future.completeExceptionally(e);
                            }
                        });
                    } else {
                        future.completeExceptionally(new QaobeeException(ExceptionCodes.HTTP_ERROR,
                                resp.statusCode() + " : " + resp.statusMessage()));
                    }
                }
        )
                .putHeader("Authorization", "Bearer " + payplug.getString("api_key"))
                .putHeader(HTTP.CONTENT_TYPE, "application/json")
                .putHeader(HTTP.CONTENT_LEN, String.valueOf(requestBody.length()))
                .end(requestBody);
        return future;
    }

    private JsonObject handlePayplugPaymentResponse(Buffer buffer, User user, int planId, long amount) throws QaobeeException {
        // The entire response body has been received
        JsonObject res = new JsonObject(buffer.toString());
        user.getAccount().getListPlan().get(planId).setAmountPaid(amount);
        user.getAccount().getListPlan().get(planId).setPaiementURL(res.getObject(HOSTED_PAYMENT_FIELD).getString(PAYMENT_URL_FIELD));
        user.getAccount().getListPlan().get(planId).setPaymentId(res.getString("id"));
        user.getAccount().getListPlan().get(planId).setPeriodicity(MONTHLY);
        if (res.getObject("card").getString("id", null) != null) {
            user.getAccount().getListPlan().get(planId).setCardInfo(Json.decodeValue(res.getObject("card").encode(), Card.class));
        }
        mongo.save(user);
        JsonObject messageResponse = new JsonObject()
                .putBoolean(Constants.STATUS, true)
                .putString(PAYMENT_URL_FIELD, res.getObject(HOSTED_PAYMENT_FIELD).getString(PAYMENT_URL_FIELD));
        user.getAccount().getListPlan().get(planId).setCardId(res.getString("id"));
        return messageResponse;
    }

    private JsonObject generatePayment(int amount, User user, int planId, String locale) {
        Payment payment = new Payment();
        payment.setAmount(amount * 100);
        payment.setCurrency("EUR");
        Customer customer = new Customer();
        customer.setFirst_name(user.getFirstname());
        customer.setLast_name(user.getName());
        customer.setEmail(user.getContact().getEmail());
        payment.setCustomer(customer);
        HostedPayment hostedPayment = new HostedPayment();
        hostedPayment.setCancel_url(payplug.getString("cancel_url"));
        hostedPayment.setReturn_url(payplug.getString("return_url"));
        payment.setHosted_payment(hostedPayment);
        payment.setNotification_url(payplug.getString("ipn_url"));
        Map<String, String> metaDatas = new HashMap<>();
        metaDatas.put(CUSTOMER_ID_FIELD, user.get_id());
        metaDatas.put(PARAM_PLAN_ID, String.valueOf(planId));
        metaDatas.put(LOCALE_FIELD, locale);
        payment.setMetadata(metaDatas);
        payment.setSave_card(true);
        payment.setForce_3ds(true);
        JsonObject requestBody = new JsonObject(Json.encode(payment));
        requestBody.removeField("payment_method");
        return requestBody;
    }

    private void sendPaymentMail(final JsonObject body, int planId, final User u) throws QaobeeException {
        final JsonObject tplReq = new JsonObject()
                .putString(TemplatesDAOImpl.TEMPLATE, "payment.html")
                .putObject(TemplatesDAOImpl.DATA, mailUtils.generateRefusedCardBody(u,
                        body.getObject(METADATA_FIELD).getString(LOCALE_FIELD),
                        u.getAccount().getListPlan().get(planId),
                        body.getObject("failure").getString("code")));
        final JsonObject emailReq = new JsonObject()
                .putString("from", runtime.getString("mail.from"))
                .putString("to", u.getContact().getEmail())
                .putString("subject", Messages.getString("mail.payment.subject", body.getObject(METADATA_FIELD).getString(LOCALE_FIELD)))
                .putString("content_type", "text/html")
                .putString("body", templatesDAO.generateMail(tplReq).getString("result"));
        vertx.eventBus().publish("mailer.mod", emailReq);
    }

    private boolean makePayment(final JsonObject body, JsonObject user, final User u, int planId) throws QaobeeException {
        if (body.containsField("is_paid") && body.getBoolean("is_paid")) {
            if (user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).size() < planId) {
                throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "some metadatas are wrong : this " + LIST_PLAN_FIELD + " does not exists");
            }
            ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                    .putString(Constants.STATUS, "paid");
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

            // TODO : MX : Verify if created_at evolve with recurring payments
            payment.putNumber(PAID_DATE_FIELD, body.getLong("created_at") * 1000L);
            payment.putNumber("amountPaid", body.getLong("amount") / 100L);
            payment.putObject(CARD_INFO_FIELD, body.getObject("card"));
            payment.putString(PAYMENT_ID_FIELD,
                    ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                            .getString(PAYMENT_ID_FIELD));
            payment.putString("id", UUID.randomUUID().toString());

            ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                    .getArray(SHIPPING_LIST_FIELD).add(payment);
            mongo.save(user, DBCollections.USER);
            final JsonObject tplReq = new JsonObject()
                    .putString(TemplatesDAOImpl.TEMPLATE, "payment.html")
                    .putObject(TemplatesDAOImpl.DATA, mailUtils.generatePaymentBody(u,
                            body.getObject(METADATA_FIELD).getString(LOCALE_FIELD),
                            u.getAccount().getListPlan().get(planId)));

            final String tplRes = templatesDAO.generateMail(tplReq).getString("result");
            final JsonObject emailReq = new JsonObject()
                    .putString("from", runtime.getString("mail.from"))
                    .putString("to", u.getContact().getEmail())
                    .putString("subject", Messages.getString("mail.payment.subject",
                            body.getObject(METADATA_FIELD).getString(LOCALE_FIELD)))
                    .putString("content_type", "text/html")
                    .putString("body", tplRes);
            vertx.eventBus().publish("mailer.mod", emailReq);
            return true;
        } else {
            LOG.info(body.encode());
        }
        return false;
    }
}
