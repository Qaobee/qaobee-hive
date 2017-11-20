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

package com.qaobee.hive.services.impl;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.AccountStatus;
import com.qaobee.hive.business.model.commons.users.account.Card;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.dao.MailUtils;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.dao.impl.TemplatesDAOImpl;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.services.ShippingService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.verticles.MailVerticle;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.stripe.model.Token;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

@ProxyService(address = "vertx.Shipping.service", iface = ShippingService.class)
public class ShippingServiceImpl implements ShippingService {
    private static final Logger LOG = LoggerFactory.getLogger(ShippingService.class);
    private static final String MONTHLY = "monthly";
    private static final String METADATA_FIELD = "metadata";
    private static final String LOCALE_FIELD = "locale";
    private static final String ACCOUNT_FIELD = "account";
    private static final String LIST_PLAN_FIELD = "listPlan";
    private static final String OBJECT_FIELD = "object";
    private static final String PLANID_FIELD = "planId";

    @Inject
    private MongoDB mongo;
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    private MailUtils mailUtils;
    @Inject
    private TemplatesDAO templatesDAO;
    @Inject
    private NotificationsService notificationsService;
    @Inject
    @Named("stripe")
    private JsonObject stripe;

    private final Vertx vertx;

    public ShippingServiceImpl(Vertx vertx) {
        super();
        this.vertx = vertx;
    }

    private void doPay(User user, JsonObject paymentData, String locale, int planId, Plan plan, Boolean yearly, Handler<AsyncResult<JsonObject>> resultHandler) {
        // Vérifier si on n'a pas déjà un customer id sur ce user
        String customerId = user.getAccount().getListPlan().get(planId).getCardId();
        getCustomerInfo(customerId, user, paymentData, locale, customer -> {
            if (customer.succeeded()) {
                if (user.getAccount().getListPlan().get(planId).getPaymentId() != null) {
                    getSubscriptionInfo(user.getAccount().getListPlan().get(planId).getPaymentId(), subscription -> {
                        if (subscription.succeeded()) {
                            if (subscription.result() != null) {
                                proceedExistingSubscription(user, planId, subscription.result(), paymentData, customer.result(), plan, yearly, locale, resultHandler);
                            } else {
                                registerSubscription(user, paymentData, locale, customer.result(), plan, planId, yearly, resultHandler);
                            }
                        } else {
                            resultHandler.handle(Future.failedFuture(subscription.cause()));
                        }
                    });
                } else {
                    registerSubscription(user, paymentData, locale, customer.result(), plan, planId, yearly, resultHandler);
                }
            } else {
                resultHandler.handle(Future.failedFuture(customer.cause()));
            }
        });
    }

    private void proceedExistingSubscription(User user, int planId, Subscription subscription, JsonObject paymentData,// NOSONAR
                                             Customer customer, Plan plan, Boolean yearly, String locale,
                                             Handler<AsyncResult<JsonObject>> resultHandler) {
        user.getAccount().getListPlan().get(planId).setStatus(subscription.getStatus());
        mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, upsertRes -> {
            if (upsertRes.succeeded()) {
                if ("canceled".equals(subscription.getStatus()) || "unpaid".equals(subscription.getStatus())) {
                    registerSubscription(user, paymentData, locale, customer, plan, planId, yearly, resultHandler);
                } else {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, Messages.getString("subscription.exists", locale))));
                }
            } else {
                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
            }
        });

    }

    private void registerSubscription(User user, JsonObject paymentData, String locale, Customer customer, Plan plan,// NOSONAR
                                      int planId, Boolean yearly, Handler<AsyncResult<JsonObject>> resultHandler) {
        try {
            Token token = Token.retrieve(paymentData.getString("token", ""));
            if (token != null) {
                Map<String, Object> params = new HashMap<>();
                params.put("customer", customer.getId());
                if(yearly) {
                    params.put("plan", plan.getLevelPlan().name() + "_Y");
                } else {
                    params.put("plan", plan.getLevelPlan().name());
                }
                Map<String, Object> subscriptionMetadata = new HashMap<>();
                subscriptionMetadata.put("_id", user.get_id());
                subscriptionMetadata.put(LOCALE_FIELD, locale);
                subscriptionMetadata.put(PLANID_FIELD, planId);
                params.put(METADATA_FIELD, subscriptionMetadata);
                Subscription subscription = Subscription.create(params);
                user.getAccount().getListPlan().get(planId).setAmountPaid(plan.getAmountPaid());
                user.getAccount().getListPlan().get(planId).setPaymentId(subscription.getId());
                user.getAccount().getListPlan().get(planId).setPaidDate(System.currentTimeMillis());
                user.getAccount().getListPlan().get(planId).setPeriodicity(MONTHLY);
                user.getAccount().getListPlan().get(planId).setCardId(customer.getId());
                user.getAccount().getListPlan().get(planId).setStatus(subscription.getStatus());
                user.getAccount().getListPlan().get(planId).setEndPeriodDate(subscription.getCurrentPeriodEnd());
                user.getAccount().setStatus(AccountStatus.ACTIVE);
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
                mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, upsertRes -> {
                    if (upsertRes.succeeded()) {
                        registerPayment(user, planId, locale, resultHandler);
                    } else {
                        resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Invalid token")));
            }
        } catch (CardException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.succeededFuture(new JsonObject().put(Constants.STATUS, false)
                    .put("message", e.getMessage())
                    .put("decline_code", e.getDeclineCode())
                    .put("param", e.getParam())
                    .put("code", e.getCode())));
        } catch (InvalidRequestException e) {
            LOG.debug(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "Invalid token")));
        } catch (APIException | AuthenticationException | APIConnectionException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage())));
        }
    }

    private int getAmountToPay(Plan plan, Boolean yearly) {
        if (runtime.getJsonObject("plan").containsKey(plan.getLevelPlan().name())) {
            if (yearly) {
                return runtime.getJsonObject("plan").getJsonObject(plan.getLevelPlan().name()).getInteger("price_y");
            } else {
                return runtime.getJsonObject("plan").getJsonObject(plan.getLevelPlan().name()).getInteger("price");
            }
        } else {
            return 0;
        }
    }

    private static void getSubscriptionInfo(String paymentId, Handler<AsyncResult<Subscription>> resultHandler) {
        if (StringUtils.isNotBlank(paymentId)) {
            try {
                resultHandler.handle(Future.succeededFuture(Subscription.retrieve(paymentId)));
            } catch (InvalidRequestException e) {
                LOG.error(e.getMessage(), e);
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, e.getMessage())));
            } catch (APIConnectionException | APIException | CardException | AuthenticationException e) {
                LOG.error(e.getMessage(), e);
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage())));
            }
        } else {
            resultHandler.handle(Future.succeededFuture(null));
        }
    }

    private static void getCustomerInfo(String customerId, User user, JsonObject paymentData, String locale, Handler<AsyncResult<Customer>> resultHandler) {
        try {
            if (StringUtils.isNotBlank(customerId)) {
                Customer cus = Customer.retrieve(customerId);
                resultHandler.handle(Future.succeededFuture(cus));
            } else {
                Map<String, Object> metadata = new HashMap<>();
                metadata.put("_id", user.get_id());
                metadata.put(LOCALE_FIELD, locale);
                Map<String, Object> customerParams = new HashMap<>();
                customerParams.put("email", user.getContact().getEmail());
                customerParams.put("source", paymentData.getString("token"));
                customerParams.put(METADATA_FIELD, metadata);
                resultHandler.handle(Future.succeededFuture(Customer.create(customerParams)));
            }
        } catch (InvalidRequestException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, e.getMessage())));
        } catch (APIConnectionException | APIException | CardException | AuthenticationException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage())));
        }
    }

    private void registerPayment(User user, int planId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
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
            resultHandler.handle(Future.succeededFuture(new JsonObject().put(Constants.STATUS, true)));
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(e));
        }
    }

    private void registerPayment(final JsonObject subscription, JsonObject user, final User u, int planId, Handler<AsyncResult<Boolean>> resultHandler) {
        if (user.getJsonObject(ACCOUNT_FIELD).getJsonArray(LIST_PLAN_FIELD).size() <= planId) {
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "planId is invalid")));
        } else {
            user.getJsonObject(ACCOUNT_FIELD).getJsonArray(LIST_PLAN_FIELD).getJsonObject(planId)
                    .put(Constants.STATUS, subscription.getString("status"));

            Calendar gc = GregorianCalendar.getInstance();
            if("month".equals(subscription.getJsonObject("plan").getString("interval"))) {
                gc.add(Calendar.MONTH, 1);
            } else if("year".equals(subscription.getJsonObject("plan").getString("interval"))) {
                gc.add(Calendar.YEAR, 1);
            }
            user.getJsonObject(ACCOUNT_FIELD).getJsonArray(LIST_PLAN_FIELD).getJsonObject(planId)
                    .put("endPeriodDate", gc.getTimeInMillis());
            mongo.upsert(user, DBCollections.USER, upsertRes -> {
                if (upsertRes.succeeded()) {
                    try {

                        final JsonObject tplReq = new JsonObject()
                                .put(TemplatesDAOImpl.TEMPLATE, "payment.html");
                        if("active".equals(subscription.getString("status"))) {

                            tplReq .put(TemplatesDAOImpl.DATA, mailUtils.generatePaymentBody(u,
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
                        }
                        if("canceled".equals(subscription.getString("status"))) {
                            tplReq.put(TemplatesDAOImpl.DATA, mailUtils.generatePaymentCanceledBody(u,
                                        subscription.getJsonObject(METADATA_FIELD).getString(LOCALE_FIELD),
                                        u.getAccount().getListPlan().get(planId)));
                            final String tplRes = templatesDAO.generateMail(tplReq).getString("result");
                            final JsonObject emailReq = new JsonObject()
                                    .put("from", runtime.getString("mail.from"))
                                    .put("to", u.getContact().getEmail())
                                    .put("subject", Messages.getString("mail.payment.canceled.subject",
                                            subscription.getJsonObject(METADATA_FIELD).getString(LOCALE_FIELD)))
                                    .put("content_type", "text/html")
                                    .put("body", tplRes);
                            vertx.eventBus().publish(MailVerticle.INTERNAL_MAIL, emailReq);
                        }
                        resultHandler.handle(Future.succeededFuture(true));
                    } catch (QaobeeException e) {
                        LOG.error(e.getMessage(), e);
                        resultHandler.handle(Future.failedFuture(e));
                    }
                } else {
                    resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                }
            });
        }
    }

    @Override
    public void pay(JsonObject u, JsonObject paymentData, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        Stripe.apiKey = stripe.getString("api_secret"); // NOSONAR
        try {
            User user = Json.decodeValue(u.encode(), User.class);
            int planId = paymentData.getInteger(PLANID_FIELD);
            if (user.getAccount().getListPlan().size() <= planId) {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "planId is invalid")));
            } else {
                Plan plan = user.getAccount().getListPlan().get(planId);
                plan.setAmountPaid(getAmountToPay(plan, paymentData.getBoolean("yearly", false)));
                if (plan.getAmountPaid() > 0) {
                    doPay(user, paymentData, locale, planId, plan, paymentData.getBoolean("yearly", false), resultHandler);
                } else {
                    resultHandler.handle(Future.succeededFuture(new JsonObject().put(Constants.STATUS, true)));
                }
            }
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(e));
        }
    }

    @Override
    public void unsubscribe(JsonObject u, int planId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        Stripe.apiKey = stripe.getString("api_secret"); // NOSONAR
        try {
            User user = Json.decodeValue(u.encode(), User.class);
            if (user.getAccount().getListPlan().size() <= planId) {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "planId is invalid")));
            } else {
                Plan plan = user.getAccount().getListPlan().get(planId);
                String customerId = user.getAccount().getListPlan().get(planId).getCardId();
                getCustomerInfo(customerId, user, new JsonObject(), locale, customer -> {
                    if (customer.succeeded()) {
                        if (plan.getPaymentId() != null) {
                            getSubscriptionInfo(user.getAccount().getListPlan().get(planId).getPaymentId(), subscription -> {
                                if (subscription.succeeded()) {
                                    if (subscription.result() != null) {
                                        Map<String, Object> params = new HashMap<>();
                                        params.put("at_period_end", false);
                                        try {
                                            subscription.result().cancel(params);
                                            user.getAccount().getListPlan().get(planId).setStatus("canceled");
                                            user.getAccount().setStatus(AccountStatus.NOT_PAID);
                                            JsonObject jUser = new JsonObject(Json.encode(user));
                                            mongo.upsert(jUser, DBCollections.USER, res -> {
                                                if (res.succeeded()) {
                                                    resultHandler.handle(Future.succeededFuture(jUser));
                                                } else {
                                                    resultHandler.handle(Future.failedFuture(res.cause()));
                                                }
                                            });
                                        } catch (InvalidRequestException e) {
                                            LOG.error(e.getMessage(), e);
                                            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, e.getMessage())));
                                        } catch (APIConnectionException | APIException | CardException | AuthenticationException e) {
                                            LOG.error(e.getMessage(), e);
                                            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.HTTP_ERROR, e.getMessage())));
                                        }
                                    }
                                } else {
                                    resultHandler.handle(Future.failedFuture(subscription.cause()));
                                }
                            });
                        } else {
                            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, "PaymentId is invalid")));
                        }
                    } else {
                        resultHandler.handle(Future.failedFuture(customer.cause()));
                    }
                });
            }
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(e));
        }
    }

    @Override
    public void webHook(JsonObject body, Handler<AsyncResult<Boolean>> resultHandler) {
        try {
            LOG.info(body.getString("type") + " : " + body.getJsonObject("data").getJsonObject(OBJECT_FIELD).getString("status"));
            if (body.getJsonObject("data").getJsonObject(OBJECT_FIELD).getJsonObject(METADATA_FIELD).containsKey(PLANID_FIELD)) {
                int planId = Integer.parseInt(body.getJsonObject("data").getJsonObject(OBJECT_FIELD).getJsonObject(METADATA_FIELD).getString(PLANID_FIELD));
                Customer customer = Customer.retrieve(body.getJsonObject("data").getJsonObject(OBJECT_FIELD).getString("customer"));
                mongo.getById(customer.getMetadata().get("_id"), DBCollections.USER, res -> {
                    if (res.succeeded()) {
                        final User u = Json.decodeValue(res.result().encode(), User.class);
                        notificationsService.addNotificationToUser(res.result().getString("_id"), new JsonObject()
                                .put("content", Messages.getString("notification." + body.getString("type") + ".content", customer.getMetadata().get(LOCALE_FIELD)))
                                .put("title", Messages.getString("notification." + body.getString("type") + ".title", customer.getMetadata().get(LOCALE_FIELD)))
                                .put("senderId", runtime.getString("admin.id")), ar -> {
                            // empty
                        });
                        registerPayment(body.getJsonObject("data").getJsonObject(OBJECT_FIELD), res.result(), u, planId, resultHandler);
                    } else {
                        resultHandler.handle(Future.failedFuture(res.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "planId is mandatory")));
            }
        } catch (NumberFormatException | StripeException e) {
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, e)));
        }
    }
}
