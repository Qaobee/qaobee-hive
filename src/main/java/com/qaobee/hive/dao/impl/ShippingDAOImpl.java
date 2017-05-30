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

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Card;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.dao.NotificationsDAO;
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
import com.stripe.exception.*;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.stripe.model.Token;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Shipping dao.
 */
public class ShippingDAOImpl implements ShippingDAO {
    private static final Logger LOG = LoggerFactory.getLogger(ShippingDAO.class);
    private static final String MONTHLY = "monthly";
    private static final String METADATA_FIELD = "metadata";
    private static final String LOCALE_FIELD = "locale";
    private static final String ACCOUNT_FIELD = "account";
    private static final String LIST_PLAN_FIELD = "listPlan";
    private static final String OBJECT_FIELD = "object";
    private static final String PLANID_FIELD = "planId";
    private final MongoDB mongo;
    private final Vertx vertx;
    private final JsonObject runtime;
    private final MailUtils mailUtils;
    private final TemplatesDAO templatesDAO;
    private final NotificationsDAO notificationsDAO;
    private final Utils utils;

    /**
     * Instantiates a new Shipping dao.
     *
     * @param mongo            the mongo
     * @param vertx            the vertx
     * @param runtime          the runtime
     * @param stripe           the stripe
     * @param mailUtils        the mail utils
     * @param templatesDAO     the templates dao
     * @param notificationsDAO the notifications dao
     * @param utils            the utils
     */
    @Inject
    public ShippingDAOImpl(MongoDB mongo, Vertx vertx, @Named("runtime") JsonObject runtime,
                           @Named("stripe") JsonObject stripe, MailUtils mailUtils, TemplatesDAO templatesDAO,
                           NotificationsDAO notificationsDAO, Utils utils) {
        this.mongo = mongo;
        this.vertx = vertx;
        this.runtime = runtime;
        this.mailUtils = mailUtils;
        this.templatesDAO = templatesDAO;
        this.notificationsDAO = notificationsDAO;
        this.utils = utils;
        Stripe.apiKey = stripe.getString("api_secret");
    }

    @Override
    public JsonObject pay(User user, JsonObject paymentData, String locale) throws QaobeeException {
        utils.testMandatoryParams(paymentData.toMap(), PLANID_FIELD);
        int planId = paymentData.getInteger(PLANID_FIELD);
        if (user.getAccount().getListPlan().size() <= planId) {
            throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "planId is invalid");
        }
        Plan plan = user.getAccount().getListPlan().get(planId);
        int amount = getAmountToPay(plan);
        if (amount > 0) {
            JsonObject resp = new JsonObject();
            try {
                // Vérifier si on n'a pas déjà un customer id sur ce user
                String customerId = user.getAccount().getListPlan().get(planId).getCardId();
                Customer customer = getCustomerInfo(customerId, user, paymentData, locale);
                // Check if subscribtion exists
                Subscription subscription = getSubscriptionInfo(user.getAccount().getListPlan().get(planId).getPaymentId());
                if (subscription != null) {
                    user.getAccount().getListPlan().get(planId).setStatus(subscription.getStatus());
                    mongo.save(user);
                    if ("canceled".equals(subscription.getStatus()) || "unpaid".equals(subscription.getStatus())) {
                        registerSubscription(user, paymentData, locale, customer, plan, planId, amount, resp);
                    } else {
                        throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, Messages.getString("subscription.exists", locale));
                    }
                } else {
                    registerSubscription(user, paymentData, locale, customer, plan, planId, amount, resp);
                }
            } catch (APIException | NullPointerException | InvalidRequestException | AuthenticationException e) {
                resp.putBoolean(Constants.STATUS, false).putString("message", e.getMessage());
                LOG.error(e.getMessage(), e);
            } catch (APIConnectionException e) {
                LOG.error(e.getMessage(), e);
                throw new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage());
            } catch (CardException e) {
                resp
                        .putBoolean(Constants.STATUS, false)
                        .putString("message", e.getMessage())
                        .putString("decline_code", e.getDeclineCode())
                        .putString("param", e.getParam())
                        .putString("code", e.getCode())
                ;
                LOG.error(e.getMessage(), e);
            }
            return resp;
        } else {
            return new JsonObject().putBoolean(Constants.STATUS, true);
        }
    }

    private void registerSubscription(User user, JsonObject paymentData, String locale, Customer customer, Plan plan, int planId, int amount, JsonObject resp) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException, QaobeeException {
        Token token;
        try {
            token = Token.retrieve(paymentData.getString("token"));
        } catch (InvalidRequestException e) {
            throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Invalid token");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("customer", customer.getId());
        params.put("plan", plan.getLevelPlan().name());
        Map<String, Object> subscriptionMetadata = new HashMap<>();
        subscriptionMetadata.put("_id", user.get_id());
        subscriptionMetadata.put(LOCALE_FIELD, locale);
        subscriptionMetadata.put(PLANID_FIELD, planId);
        params.put(METADATA_FIELD, subscriptionMetadata);
        Subscription subscription = Subscription.create(params);
        user.getAccount().getListPlan().get(planId).setAmountPaid(amount / 100);
        user.getAccount().getListPlan().get(planId).setPaymentId(subscription.getId());
        user.getAccount().getListPlan().get(planId).setPaidDate(System.currentTimeMillis());
        user.getAccount().getListPlan().get(planId).setPeriodicity(MONTHLY);
        user.getAccount().getListPlan().get(planId).setCardId(customer.getId());
        user.getAccount().getListPlan().get(planId).setStatus(subscription.getStatus());
        JsonObject cardJson = new JsonObject(token.toJson()).getObject("card");
        Card card = new Card();
        card.setBrand(cardJson.getString("brand"));
        card.setCountry(cardJson.getString("country"));
        card.setExp_month(cardJson.getInteger("exp_month"));
        card.setExp_year(cardJson.getInteger("exp_year"));
        card.setLast4(cardJson.getString("last4"));
        card.setId(cardJson.getString("id"));
        if (subscription.getTrialEnd() != null) {
            user.getAccount().getListPlan().get(planId).setEndPeriodDate(subscription.getTrialEnd());
        }
        user.getAccount().getListPlan().get(planId).setStartPeriodDate(subscription.getStart());
        user.getAccount().getListPlan().get(planId).setCardInfo(card);
        mongo.save(user);
        resp.putBoolean(Constants.STATUS, registerPayment(user, planId, locale));
    }

    private int getAmountToPay(Plan plan) {
        if (runtime.getObject("plan").containsField(plan.getLevelPlan().name())) {
            return runtime.getObject("plan").getObject(plan.getLevelPlan().name()).getInteger("price") * 100;
        }
        return 0;
    }

    private Subscription getSubscriptionInfo(String paymentId) throws QaobeeException, APIException, CardException, AuthenticationException {
        if (StringUtils.isNotBlank(paymentId)) {
            try {
                return Subscription.retrieve(paymentId);
            } catch (InvalidRequestException e) {
                LOG.debug(e.getMessage(), e);
            } catch (APIConnectionException e) {
                LOG.error(e.getMessage(), e);
                throw new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage());
            }
        }
        return null;
    }

    private Customer getCustomerInfo(String customerId, User user, JsonObject paymentData, String locale) throws QaobeeException, APIException, CardException, AuthenticationException {
        try {
            if (StringUtils.isNotBlank(customerId)) {
                return Customer.retrieve(customerId);
            }
        } catch (InvalidRequestException e) {
            LOG.debug(e.getMessage(), e);
        } catch (APIConnectionException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage());
        }
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("_id", user.get_id());
        metadata.put(LOCALE_FIELD, locale);
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("email", user.getContact().getEmail());
        customerParams.put("source", paymentData.getString("token"));
        customerParams.put(METADATA_FIELD, metadata);
        try {
            return Customer.create(customerParams);
        } catch (InvalidRequestException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, e.getMessage());
        } catch (APIConnectionException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage());
        }
    }

    private boolean registerPayment(User user, int planId, String locale) throws QaobeeException {
        final JsonObject tplReq = new JsonObject()
                .putString(TemplatesDAOImpl.TEMPLATE, "payment.html")
                .putObject(TemplatesDAOImpl.DATA, mailUtils.generatePaymentBody(user,
                        locale,
                        user.getAccount().getListPlan().get(planId)));

        final String tplRes = templatesDAO.generateMail(tplReq).getString("result");
        final JsonObject emailReq = new JsonObject()
                .putString("from", runtime.getString("mail.from"))
                .putString("to", user.getContact().getEmail())
                .putString("subject", Messages.getString("mail.payment.subject", locale))
                .putString("content_type", "text/html")
                .putString("body", tplRes);
        vertx.eventBus().publish("mailer.mod", emailReq);
        return true;
    }

    @Override
    public boolean webHook(JsonObject body) throws QaobeeException {
        try {
            LOG.info(body.getString("type") + " : " + body.getObject("data").getObject(OBJECT_FIELD).getString("status"));
            if (body.getObject("data").getObject(OBJECT_FIELD).getObject(METADATA_FIELD).containsField(PLANID_FIELD)) {
                int planId = Integer.parseInt(body.getObject("data").getObject(OBJECT_FIELD).getObject(METADATA_FIELD).getString(PLANID_FIELD));
                Customer customer = Customer.retrieve(body.getObject("data").getObject(OBJECT_FIELD).getString("customer"));
                final JsonObject user = mongo.getById(customer.getMetadata().get("_id"), DBCollections.USER);
                final User u = Json.decodeValue(user.encode(), User.class);
                notificationsDAO.addNotificationToUser(user.getString("_id"), new JsonObject()
                        .putString("content", Messages.getString("notification." + body.getString("type") + ".content", customer.getMetadata().get(LOCALE_FIELD)))
                        .putString("title", Messages.getString("notification." + body.getString("type") + ".title", customer.getMetadata().get(LOCALE_FIELD)))
                        .putString("senderId", runtime.getString("admin.id")));
                return registerPayment(body.getObject("data").getObject(OBJECT_FIELD), user, u, planId);
            } else {
                throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "planId is mandatory");
            }
        } catch (NumberFormatException | StripeException e) {
            throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, e);
        }
    }

    private boolean registerPayment(final JsonObject subscription, JsonObject user, final User u, int planId) throws QaobeeException {
        if (user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).size() <= planId) {
            throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "planId is invalid");
        }
        ((JsonObject) user.getObject(ACCOUNT_FIELD).getArray(LIST_PLAN_FIELD).get(planId))
                .putString(Constants.STATUS, subscription.getString("status"));
        mongo.save(user, DBCollections.USER);
        final JsonObject tplReq = new JsonObject()
                .putString(TemplatesDAOImpl.TEMPLATE, "payment.html")
                .putObject(TemplatesDAOImpl.DATA, mailUtils.generatePaymentBody(u,
                        subscription.getObject(METADATA_FIELD).getString(LOCALE_FIELD),
                        u.getAccount().getListPlan().get(planId)));

        final String tplRes = templatesDAO.generateMail(tplReq).getString("result");
        final JsonObject emailReq = new JsonObject()
                .putString("from", runtime.getString("mail.from"))
                .putString("to", u.getContact().getEmail())
                .putString("subject", Messages.getString("mail.payment.subject",
                        subscription.getObject(METADATA_FIELD).getString(LOCALE_FIELD)))
                .putString("content_type", "text/html")
                .putString("body", tplRes);
        vertx.eventBus().publish("mailer.mod", emailReq);
        return true;
    }
}
