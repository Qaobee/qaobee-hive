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

import com.qaobee.hive.api.v1.commons.utils.MailVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Card;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.services.NotificationsService;
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
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final NotificationsService notificationsService;
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
     * @param notificationsService the notifications dao
     * @param utils            the utils
     */
    @Inject
    public ShippingDAOImpl(MongoDB mongo, Vertx vertx, @Named("runtime") JsonObject runtime, // NOSONAR
                           @Named("stripe") JsonObject stripe, MailUtils mailUtils, TemplatesDAO templatesDAO,
                           NotificationsService notificationsService, Utils utils) {
        this.mongo = mongo;
        this.vertx = vertx;
        this.runtime = runtime;
        this.mailUtils = mailUtils;
        this.templatesDAO = templatesDAO;
        this.notificationsService = notificationsService;
        this.utils = utils;
        Stripe.apiKey = stripe.getString("api_secret");
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> pay(User user, JsonObject paymentData, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        try {
            utils.testMandatoryParams(paymentData, PLANID_FIELD);
            int planId = paymentData.getInteger(PLANID_FIELD);
            if (user.getAccount().getListPlan().size() <= planId) {
                deferred.reject(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "planId is invalid"));
            } else {
                Plan plan = user.getAccount().getListPlan().get(planId);
                int amount = getAmountToPay(plan);
                if (amount > 0) {
                    doPay(user, paymentData, locale, planId, plan, amount, deferred);
                } else {
                    deferred.resolve(new JsonObject().put(Constants.STATUS, true));
                }
            }
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            deferred.reject(e);
        }
        return deferred.promise();
    }

    private void doPay(User user, JsonObject paymentData, String locale, int planId, Plan plan, int amount, Deferred<JsonObject, QaobeeException, Integer> deferred) {
        // Vérifier si on n'a pas déjà un customer id sur ce user
        String customerId = user.getAccount().getListPlan().get(planId).getCardId();
        getCustomerInfo(customerId, user, paymentData, locale).done(customer -> {
            if (user.getAccount().getListPlan().get(planId).getPaymentId() != null) {
                getSubscriptionInfo(user.getAccount().getListPlan().get(planId).getPaymentId()).done(subscription -> {
                    if (subscription != null) {
                        user.getAccount().getListPlan().get(planId).setStatus(subscription.getStatus());
                        mongo.upsert(user).done(id -> {
                            if ("canceled".equals(subscription.getStatus()) || "unpaid".equals(subscription.getStatus())) {
                                registerSubscription(user, paymentData, locale, customer, plan, planId, amount).done(deferred::resolve).fail(deferred::reject);
                            } else {
                                deferred.reject(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, Messages.getString("subscription.exists", locale)));
                            }
                        }).fail(deferred::reject);
                    } else {
                        registerSubscription(user, paymentData, locale, customer, plan, planId, amount).done(deferred::resolve).fail(deferred::reject);
                    }
                }).fail(deferred::reject);
            } else {
                registerSubscription(user, paymentData, locale, customer, plan, planId, amount).done(deferred::resolve).fail(deferred::reject);
            }
        }).fail(deferred::reject);
    }

    private Promise<JsonObject, QaobeeException, Integer> registerSubscription(User user, JsonObject paymentData, String locale, Customer customer, Plan plan,
                                                                               int planId, int amount) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        try {
            Token token = Token.retrieve(paymentData.getString("token"));
            if (token != null) {
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
                JsonObject cardJson = new JsonObject(token.toJson()).getJsonObject("card");
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
                mongo.upsert(user).done(id -> registerPayment(user, planId, locale).done(deferred::resolve).fail(deferred::reject)).fail(deferred::reject);
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Invalid token"));
            }
        } catch (CardException e) {
            LOG.error(e.getMessage(), e);
            deferred.resolve(new JsonObject().put(Constants.STATUS, false)
                    .put("message", e.getMessage())
                    .put("decline_code", e.getDeclineCode())
                    .put("param", e.getParam())
                    .put("code", e.getCode()));
        } catch (InvalidRequestException e) {
            LOG.debug(e.getMessage(), e);
            deferred.reject(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Invalid token"));
        } catch (APIException | AuthenticationException | APIConnectionException e) {
            LOG.error(e.getMessage(), e);
            deferred.reject(new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage()));
        }
        return deferred.promise();
    }

    private int getAmountToPay(Plan plan) {
        if (runtime.getJsonObject("plan").containsKey(plan.getLevelPlan().name())) {
            return runtime.getJsonObject("plan").getJsonObject(plan.getLevelPlan().name()).getInteger("price") * 100;
        }
        return 0;
    }

    private static Promise<Subscription, QaobeeException, Integer> getSubscriptionInfo(String paymentId) {
        Deferred<Subscription, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (StringUtils.isNotBlank(paymentId)) {
            try {
                deferred.resolve(Subscription.retrieve(paymentId));
            } catch (InvalidRequestException e) {
                LOG.error(e.getMessage(), e);
                deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, e.getMessage()));
            } catch (APIConnectionException | APIException | CardException | AuthenticationException e) {
                LOG.error(e.getMessage(), e);
                deferred.reject(new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage()));
            }
        } else {
            deferred.resolve(null);
        }
        return deferred.promise();
    }

    private static Promise<Customer, QaobeeException, Integer> getCustomerInfo(String customerId, User user, JsonObject paymentData, String locale) {
        Deferred<Customer, QaobeeException, Integer> deferred = new DeferredObject<>();
        try {
            if (StringUtils.isNotBlank(customerId)) {
                deferred.resolve(Customer.retrieve(customerId));
            } else {
                Map<String, Object> metadata = new HashMap<>();
                metadata.put("_id", user.get_id());
                metadata.put(LOCALE_FIELD, locale);
                Map<String, Object> customerParams = new HashMap<>();
                customerParams.put("email", user.getContact().getEmail());
                customerParams.put("source", paymentData.getString("token"));
                customerParams.put(METADATA_FIELD, metadata);
                deferred.resolve(Customer.create(customerParams));
            }
        } catch (InvalidRequestException e) {
            LOG.error(e.getMessage(), e);
            deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, e.getMessage()));
        } catch (APIConnectionException | APIException | CardException | AuthenticationException e) {
            LOG.error(e.getMessage(), e);
            deferred.reject(new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage()));
        }
        return deferred.promise();
    }

    private Promise<JsonObject, QaobeeException, Integer> registerPayment(User user, int planId, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        try {
            final JsonObject tplReq = new JsonObject()
                    .put(TemplatesDAOImpl.TEMPLATE, "payment.html")
                    .put(TemplatesDAOImpl.DATA, mailUtils.generatePaymentBody(user,
                            locale,
                            user.getAccount().getListPlan().get(planId)));
            final String tplRes = templatesDAO.generateMail(tplReq).getString("result");
            final JsonObject emailReq = new JsonObject()
                    .put("from", runtime.getString("mail.from"))
                    .put("to", user.getContact().getEmail())
                    .put("subject", Messages.getString("mail.payment.subject", locale))
                    .put("content_type", "text/html")
                    .put("body", tplRes);
            vertx.eventBus().publish(MailVerticle.INTERNAL_MAIL, emailReq);
            deferred.resolve(new JsonObject().put(Constants.STATUS, true));
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            deferred.reject(e);
        }
        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> webHook(JsonObject body) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        try {
            LOG.info(body.getString("type") + " : " + body.getJsonObject("data").getJsonObject(OBJECT_FIELD).getString("status"));
            if (body.getJsonObject("data").getJsonObject(OBJECT_FIELD).getJsonObject(METADATA_FIELD).containsKey(PLANID_FIELD)) {
                int planId = Integer.parseInt(body.getJsonObject("data").getJsonObject(OBJECT_FIELD).getJsonObject(METADATA_FIELD).getString(PLANID_FIELD));
                Customer customer = Customer.retrieve(body.getJsonObject("data").getJsonObject(OBJECT_FIELD).getString("customer"));
                mongo.getById(customer.getMetadata().get("_id"), DBCollections.USER).done(user -> {
                    final User u = Json.decodeValue(user.encode(), User.class);
                    notificationsService.addNotificationToUser(user.getString("_id"), new JsonObject()
                            .put("content", Messages.getString("notification." + body.getString("type") + ".content", customer.getMetadata().get(LOCALE_FIELD)))
                            .put("title", Messages.getString("notification." + body.getString("type") + ".title", customer.getMetadata().get(LOCALE_FIELD)))
                            .put("senderId", runtime.getString("admin.id")), null);
                    registerPayment(body.getJsonObject("data").getJsonObject(OBJECT_FIELD), user, u, planId).done(deferred::resolve).fail(deferred::reject);
                }).fail(deferred::reject);
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "planId is mandatory"));
            }
        } catch (NumberFormatException | StripeException e) {
            deferred.reject(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, e));
        }
        return deferred.promise();
    }

    private Promise<Boolean, QaobeeException, Integer> registerPayment(final JsonObject subscription, JsonObject user, final User u, int planId) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (user.getJsonObject(ACCOUNT_FIELD).getJsonArray(LIST_PLAN_FIELD).size() <= planId) {
            deferred.reject(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "planId is invalid"));
        } else {
            user.getJsonObject(ACCOUNT_FIELD).getJsonArray(LIST_PLAN_FIELD).getJsonObject(planId)
                    .put(Constants.STATUS, subscription.getString("status"));
            mongo.upsert(user, DBCollections.USER).done(id -> {
                try {
                    final JsonObject tplReq = new JsonObject()
                            .put(TemplatesDAOImpl.TEMPLATE, "payment.html")
                            .put(TemplatesDAOImpl.DATA, mailUtils.generatePaymentBody(u,
                                    subscription.getJsonObject(METADATA_FIELD).getString(LOCALE_FIELD),
                                    u.getAccount().getListPlan().get(planId)));
                    final String tplRes = templatesDAO.generateMail(tplReq).getString("result");
                    final JsonObject emailReq = new JsonObject()
                            .put("from", runtime.getString("mail.from"))
                            .put("to", u.getContact().getEmail())
                            .put("subject", Messages.getString("mail.payment.subject",
                                    subscription.getJsonObject(METADATA_FIELD).getString(LOCALE_FIELD)))
                            .put("content_type", "text/html")
                            .put("body", tplRes);
                    vertx.eventBus().publish(MailVerticle.INTERNAL_MAIL, emailReq);
                    deferred.resolve(true);
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    deferred.reject(e);
                }
            });
        }

        return deferred.promise();
    }
}
