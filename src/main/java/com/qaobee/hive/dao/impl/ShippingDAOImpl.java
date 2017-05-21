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
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * The type Shipping dao.
 */
public class ShippingDAOImpl implements ShippingDAO {
    private static final Logger LOG = LoggerFactory.getLogger(ShippingDAO.class);

    private static final String PAID_DATE_FIELD = "paidDate";
    private static final String LEVEL_PLAN_FIELD = "levelPlan";
    private static final String MONTHLY = "monthly";
    private static final String METADATA_FIELD = "metadata";
    private static final String LOCALE_FIELD = "locale";
    private static final String ACCOUNT_FIELD = "account";
    private static final String LIST_PLAN_FIELD = "listPlan";
    private static final String CARD_INFO_FIELD = "cardInfo";
    private static final String PAYMENT_ID_FIELD = "paymentId";
    private static final String SHIPPING_LIST_FIELD = "shippingList";
    private final MongoDB mongo;
    private final Vertx vertx;
    private final Utils utils;
    private final JsonObject runtime;
    private final JsonObject stripe;
    private final MailUtils mailUtils;
    private final TemplatesDAO templatesDAO;
    private final RequestOptions requestOptions;

    /**
     * Instantiates a new Shipping dao.
     *
     * @param mongo        the mongo
     * @param vertx        the vertx
     * @param utils        the utils
     * @param runtime      the runtime
     * @param stripe       the stripe
     * @param mailUtils    the mail utils
     * @param templatesDAO the templates dao
     */
    @Inject
    public ShippingDAOImpl(MongoDB mongo, Vertx vertx, Utils utils, @Named("runtime") JsonObject runtime, @Named("stripe") JsonObject stripe, MailUtils mailUtils, TemplatesDAO templatesDAO) {
        this.mongo = mongo;
        this.vertx = vertx;
        this.utils = utils;
        this.runtime = runtime;
        this.stripe = stripe;
        this.mailUtils = mailUtils;
        this.templatesDAO = templatesDAO;
        Stripe.apiKey = stripe.getString("api_secret");
        requestOptions = (new RequestOptions.RequestOptionsBuilder()).setApiKey(stripe.getString("api_secret")).build();

    }

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
    public void triggeredPayment(JsonObject user) {
        JsonArray listPlan = user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD);
        for (int i = 0; i < listPlan.size(); i++) {
            JsonObject plan = listPlan.get(i);
            if (plan != null && plan.containsField(LEVEL_PLAN_FIELD)
                    && plan.containsField(CARD_INFO_FIELD)
                    && plan.getObject(CARD_INFO_FIELD) != null
                    && !"null".equals(plan.getObject(CARD_INFO_FIELD).getString("id"))) {
                String s = plan.getString("periodicity", "");
                if (MONTHLY.equals(s)) {
                    triggerMonthly(plan, user, i);
                }
            }
        }
    }

    @Override
    public JsonObject pay(User user, JsonObject paymentData, String locale) throws QaobeeException {
        if (!paymentData.containsField("planId")) {
            throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "planId is not present");
        }
        int planId = paymentData.getInteger("planId");
        Plan plan = user.getAccount().getListPlan().get(planId);
        int amount = 0;
        if (runtime.getObject("plan").containsField(plan.getLevelPlan().name())) {
            amount = runtime.getObject("plan").getObject(plan.getLevelPlan().name()).getInteger("price") * 100;
        }
        if (amount != 0) {
            JsonObject resp = new JsonObject();
            try {
                Map<String, Object> metadata = new HashMap<>();
                metadata.put("_id", user.get_id());
                metadata.put("planId", planId);
                metadata.put("locale", locale);
                Map<String, Object> customerParams = new HashMap<>();
                customerParams.put("email", user.getContact().getEmail());
                customerParams.put("source", paymentData.getString("token"));
                customerParams.put("metadata", metadata);
                Customer customer = Customer.create(customerParams);

                Map<String, Object> chargeMap = new HashMap<>();
                chargeMap.put("amount", amount);
                chargeMap.put("currency", "eur");
                chargeMap.put("customer", customer.getId());
                chargeMap.put("receipt_email", user.getContact().getEmail());
                chargeMap.put("description", "Charge for " + user.getFirstname() + " " + user.getName());

                Charge charge = Charge.create(chargeMap, requestOptions);
                user.getAccount().getListPlan().get(planId).setAmountPaid(amount);
                user.getAccount().getListPlan().get(planId).setPaymentId(charge.getId());
                user.getAccount().getListPlan().get(planId).setPeriodicity(MONTHLY);
                user.getAccount().getListPlan().get(planId).setCardId(customer.getId());
                JsonObject cardJson = new JsonObject(charge.toJson()).getObject("source");
                Card card = new Card();
                if (cardJson.getString("cvc_check").equals("pass")) {
                    card.setBrand(cardJson.getString("brand"));
                    card.setCountry(cardJson.getString("country"));
                    card.setExp_month(cardJson.getInteger("exp_month"));
                    card.setExp_year(cardJson.getInteger("exp_year"));
                    card.setLast4(cardJson.getString("last4"));
                    card.setId(cardJson.getString("id"));
                    user.getAccount().getListPlan().get(planId).setCardInfo(card);
                    mongo.save(user);
                    resp.putBoolean(Constants.STATUS, true);
                } else {
                    throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "CVS Check failed");
                }
                System.out.println(charge.toJson());
            } catch (StripeException e) {
                resp.putBoolean(Constants.STATUS, false).putString("message", e.getMessage());
                LOG.error(e.getMessage(), e);
            }
            return resp;
        } else {
            return new JsonObject().putBoolean(Constants.STATUS, true);
        }
    }

    @Override
    public boolean ipn(JsonObject body) throws QaobeeException {
        utils.testMandatoryParams(body.getObject(METADATA_FIELD).toMap(), "type", "data");
        try {
            int planId = body.getObject("data").getObject("object").getObject("customer").getObject("metadata").getInteger("planId");
            final JsonObject user = mongo.getById(body.getObject("data").getObject("object").getObject("customer").getObject("metadata").getString("_id"), DBCollections.USER);
            final User u = Json.decodeValue(user.encode(), User.class);
            if ("charge.failed".equals(body.getString("type"))) {
                // -> Send a mail with the payment url link
                sendPaymentMail(body.getObject("data").getObject("object").getObject("customer").getObject("metadata"), body.getObject("data").getObject("object").getString("failure_message"), planId, u);
            } else {
                return "charge.succeeded".equals(body.getString("type")) && makePayment(body, user, u, planId);
            }
            return false;
        } catch (NumberFormatException e) {
            throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, e);
        }
    }

    private void triggerMonthly(JsonObject plan, JsonObject user, int planId) {
        long paidDate = plan.getLong(PAID_DATE_FIELD);
        Calendar initialPaidDate = Calendar.getInstance();
        initialPaidDate.setTime(new Date(paidDate));
        initialPaidDate.add(Calendar.MONTH, 1);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        // test if we are the last day of the next month following the last fee
        if (now.after(initialPaidDate)) {
            try {
                // 1- Trigger payment
                int amount = 0;
                if (runtime.getObject("plan").containsField(plan.getString(LEVEL_PLAN_FIELD))) {
                    amount = runtime.getObject("plan").getObject(plan.getString(LEVEL_PLAN_FIELD)).getInteger("price") * 100;
                }
                if (amount == 0) {
                    return;
                }
                Map<String, Object> chargeMap = new HashMap<>();
                chargeMap.put("amount", amount);
                chargeMap.put("currency", "eur");
                chargeMap.put("customer", plan.getString("cardId"));
                chargeMap.put("receipt_email", user.getObject("contact").getString("email"));
                chargeMap.put("description", "Charge for " + user.getString("firstname") + " " + user.getString("name"));

                Charge charge = Charge.create(chargeMap, requestOptions);

                ((JsonObject) user.getObject(ACCOUNT_FIELD)
                        .getArray(LIST_PLAN_FIELD)
                        .get(planId))
                        .putNumber("amountPaid", amount);
                ((JsonObject) user.getObject(ACCOUNT_FIELD)
                        .getArray(LIST_PLAN_FIELD)
                        .get(planId))
                        .putString(PAYMENT_ID_FIELD, charge.getId());
                ((JsonObject) user.getObject(ACCOUNT_FIELD)
                        .getArray(LIST_PLAN_FIELD)
                        .get(planId))
                        .putString("periodicity", MONTHLY);

                mongo.save(user, DBCollections.USER);
            } catch (QaobeeException | StripeException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private void sendPaymentMail(final JsonObject metadata, String failureMessage, int planId, final User u) throws QaobeeException {
        final JsonObject tplReq = new JsonObject()
                .putString(TemplatesDAOImpl.TEMPLATE, "payment.html")
                .putObject(TemplatesDAOImpl.DATA, mailUtils.generateRefusedCardBody(u,
                        metadata.getString("locale"),
                        u.getAccount().getListPlan().get(planId),
                        failureMessage));
        final JsonObject emailReq = new JsonObject()
                .putString("from", runtime.getString("mail.from"))
                .putString("to", u.getContact().getEmail())
                .putString("subject", Messages.getString("mail.payment.subject", metadata.getString("locale")))
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
