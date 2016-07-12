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

package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.v1.commons.users.ShippingVerticle;
import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import java.util.ArrayList;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

/**
 * The type Shipping
 */
public class ShippingTest extends VertxJunitSupport {
    private ClientAndServer mockServer;

    /**
     * Init mock server.
     */
    @Before
    public void initMockServer() {
        mockServer = startClientAndServer(1080);
    }

    /**
     * Stop mock server.
     */
    @After
    public void stopMockServer() {
        if(mockServer.isRunning()) {
            mockServer.stop();
        }
    }

    /**
     * Create payment.
     */
    @Test
    public void createPayment() {
        User u = generateLoggedUser();
        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        String payment_url = given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", is(true))
                .body("status", notNullValue())
                .body("payment_url", notNullValue()).extract().path("payment_url");

        given().header(TOKEN, u.getAccount().getToken())
                .param("id", u.get_id())
                .when().get(getURL(UserVerticle.USER_INFO))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(u.get_id()))
                .body("account.listPlan[0].paiementURL", notNullValue())
                .body("account.listPlan[0].paiementURL", is(payment_url))
                .body("account.listPlan[0].paymentId", notNullValue())
                .body("account.listPlan[0].paymentId", not(""));

    }

    /**
     * Create payment with wrong plan id.
     */
    @Test
    public void createPaymentWithWrongPlanId() {
        User u = generateLoggedUser();
        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "1");
        given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Create payment with missing plan id.
     */
    @Test
    public void createPaymentWithMissingPlanId() {
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Create payment with wrong http method.
     */
    @Test
    public void createPaymentWithWrongHttpMethod() {
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
                .when().get(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Create payment with wrong payplug response.
     */
    @Test
    public void createPaymentWithWrongPayplugResponse() {
        User u = generateLoggedUser();
        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(500)
                        .withBody(generateMockBody(u, 0)));
        given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Create payment with no payplug server.
     */
    @Test
    public void createPaymentWithNoPayplugServer() {
        User u = generateLoggedUser();
        if(mockServer.isRunning()) {
            mockServer.stop();
        }
        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Recieve payplug notification
     */
    @Test
    public void recievePayplugNotification() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                .respond(HttpResponse.response().withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));

        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        String payment_url = given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true))
                .body("payment_url", notNullValue()).extract().path("payment_url");

        String paymentId = given().header(TOKEN, u.getAccount().getToken())
                .param("id", u.get_id())
                .when().get(getURL(UserVerticle.USER_INFO))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(u.get_id()))
                .body("account.listPlan[0].paiementURL", notNullValue())
                .body("account.listPlan[0].paiementURL", is(payment_url))
                .body("account.listPlan[0].paymentId", notNullValue())
                .body("account.listPlan[0].paymentId", not(""))
                .extract().path("paymentId");

        JsonObject notification = buildNotificationRequest(paymentId, u);
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
        // Let's verify user's info
        given().header(TOKEN, u.getAccount().getToken())
                .param("id", u.get_id())
                .when().get(getURL(UserVerticle.USER_INFO))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(u.get_id()))
                .body("account.listPlan[0].paidDate", notNullValue())
                .body("account.listPlan[0].cardInfo", notNullValue())
                .body("account.listPlan[0].cardInfo.last4", notNullValue())
                .body("account.listPlan[0].cardInfo.last4", is(notification.getObject("card").getString("last4")))
                .body("account.listPlan[0].shippingList.size()", is(2))
                .body("account.listPlan[0].shippingList[1].cardInfo.last4", notNullValue())
                .body("account.listPlan[0].shippingList[1].cardInfo.last4", is(notification.getObject("card").getString("last4")))
                .body("account.listPlan[0].paymentId", not(""))
                .body("account.listPlan[0].status", is("paid"))
                .extract().path("paymentId");
    }

    /**
     * Recieve payplug notification with no previous shipping
     */
    @Test
    public void recievePayplugNotificationWithNoPreviousShipping() {
        try {
            User u = generateLoggedUser();
            u.getAccount().getListPlan().get(0).setShippingList(null);
            u.getAccount().getListPlan().get(0).setCardInfo(null);
            u.getAccount().getListPlan().get(0).setCardId(null);
            mongo.save(u);
            new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                    .respond(HttpResponse.response().withStatusCode(201)
                            .withBody(generateMockBody(u, 0)));

            JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
            String payment_url = given().header(TOKEN, u.getAccount().getToken())
                    .body(request.encodePrettily())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true))
                    .body("payment_url", notNullValue()).extract().path("payment_url");

            String paymentId = given().header(TOKEN, u.getAccount().getToken())
                    .param("id", u.get_id())
                    .when().get(getURL(UserVerticle.USER_INFO))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(u.get_id()))
                    .body("account.listPlan[0].paiementURL", notNullValue())
                    .body("account.listPlan[0].paiementURL", is(payment_url))
                    .body("account.listPlan[0].paymentId", notNullValue())
                    .body("account.listPlan[0].paymentId", not(""))
                    .extract().path("paymentId");

            JsonObject notification = buildNotificationRequest(paymentId, u);
            given().body(notification.encodePrettily())
                    .when().post(getURL(ShippingVerticle.IPN))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            // Let's verify user's info
            given().header(TOKEN, u.getAccount().getToken())
                    .param("id", u.get_id())
                    .when().get(getURL(UserVerticle.USER_INFO))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(u.get_id()))
                    .body("account.listPlan[0].paidDate", notNullValue())
                    .body("account.listPlan[0].cardInfo", notNullValue())
                    .body("account.listPlan[0].cardInfo.last4", notNullValue())
                    .body("account.listPlan[0].cardInfo.last4", is(notification.getObject("card").getString("last4")))
                    .body("account.listPlan[0].paymentId", not(""))
                    .body("account.listPlan[0].shippingList.size()", is(1))
                    .body("account.listPlan[0].shippingList[0].cardInfo.last4", notNullValue())
                    .body("account.listPlan[0].shippingList[0].cardInfo.last4", is(notification.getObject("card").getString("last4")))
                    .body("account.listPlan[0].status", is("paid"))
                    .extract().path("paymentId");

        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification with zero amount
     */
    @Test
    public void recievePayplugNotificationWithZeroAmount() {
        try {
            User u = generateLoggedUser();
            u.getAccount().getListPlan().get(0).setAmountPaid(0L);
            mongo.save(u);
            new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                    .respond(HttpResponse.response().withStatusCode(201)
                            .withBody(generateMockBody(u, 0)));

            JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
            String payment_url = given().header(TOKEN, u.getAccount().getToken())
                    .body(request.encodePrettily())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true))
                    .body("payment_url", notNullValue()).extract().path("payment_url");

            String paymentId = given().header(TOKEN, u.getAccount().getToken())
                    .param("id", u.get_id())
                    .when().get(getURL(UserVerticle.USER_INFO))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(u.get_id()))
                    .body("account.listPlan[0].paiementURL", notNullValue())
                    .body("account.listPlan[0].paiementURL", is(payment_url))
                    .body("account.listPlan[0].paymentId", notNullValue())
                    .body("account.listPlan[0].paymentId", not(""))
                    .extract().path("paymentId");

            JsonObject notification = buildNotificationRequest(paymentId, u);
            given().body(notification.encodePrettily())
                    .when().post(getURL(ShippingVerticle.IPN))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));

        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification with wrong http method
     */
    @Test
    public void recievePayplugNotificationWithWrongHttpMethod() {
        given().when().get(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Recieve payplug notification with missing mandatory data
     */
    @Test
    public void recievePayplugNotificationWithMissingMandatoryData() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.removeField("created_at");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification with missing metadata
     */
    @Test
    public void recievePayplugNotificationWithMissingMetadata() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").removeField("plan_id");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }


    /**
     * Recieve payplug notification with bad plan id
     */
    @Test
    public void recievePayplugNotificationWithBadPlanId() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("plan_id", "bwahaha");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Recieve payplug notification with non existing plan id
     */
    @Test
    public void recievePayplugNotificationWithNonExistingPlanId() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("plan_id", "8");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Recieve payplug notification with  null plan id
     */
    @Test
    public void recievePayplugNotificationWithNullPlanId() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").removeField("plan_id");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification with empty plan id
     */
    @Test
    public void recievePayplugNotificationWithEmptyPlanId() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("plan_id", "");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification wrong object
     */
    @Test
    public void recievePayplugNotificationWrongObject() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                .respond(HttpResponse.response().withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));

        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        String payment_url = given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", is(true))
                .body("status", notNullValue())
                .body("payment_url", notNullValue()).extract().path("payment_url");

        String paymentId = given().header(TOKEN, u.getAccount().getToken())
                .param("id", u.get_id())
                .when().get(getURL(UserVerticle.USER_INFO))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(u.get_id()))
                .body("account.listPlan[0].paiementURL", notNullValue())
                .body("account.listPlan[0].paiementURL", is(payment_url))
                .body("account.listPlan[0].paymentId", notNullValue())
                .body("account.listPlan[0].paymentId", not(""))
                .extract().path("paymentId");

        JsonObject notification = buildNotificationRequest(paymentId, u);
        notification.putString("object", "bwahahaha");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(false));
    }

    /**
     * Recieve payplug notification not paid
     */
    @Test
    public void recievePayplugNotificationNotPaid() {
        try {
            User u = generateLoggedUser();
            u.getAccount().getListPlan().get(0).setStatus("notpaid");
            mongo.save(u);
            new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                    .respond(HttpResponse.response().withStatusCode(201)
                            .withBody(generateMockBody(u, 0)));

            JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
            String payment_url = given().header(TOKEN, u.getAccount().getToken())
                    .body(request.encodePrettily())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(200)
                    .body("status", is(true))
                    .body("status", notNullValue())
                    .body("payment_url", notNullValue()).extract().path("payment_url");

            String paymentId = given().header(TOKEN, u.getAccount().getToken())
                    .param("id", u.get_id())
                    .when().get(getURL(UserVerticle.USER_INFO))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(u.get_id()))
                    .body("account.listPlan[0].paiementURL", notNullValue())
                    .body("account.listPlan[0].paiementURL", is(payment_url))
                    .body("account.listPlan[0].paymentId", notNullValue())
                    .body("account.listPlan[0].paymentId", not(""))
                    .extract().path("paymentId");

            JsonObject notification = buildNotificationRequest(paymentId, u);
            notification.putBoolean("is_paid", false);
            given().body(notification.encodePrettily())
                    .when().post(getURL(ShippingVerticle.IPN))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));
            given().header(TOKEN, u.getAccount().getToken())
                    .param("id", u.get_id())
                    .when().get(getURL(UserVerticle.USER_INFO))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(u.get_id()))
                    .body("account.listPlan[0].paiementURL", notNullValue())
                    .body("account.listPlan[0].paiementURL", is(payment_url))
                    .body("account.listPlan[0].paymentId", notNullValue())
                    .body("account.listPlan[0].paymentId", not(""))
                    .body("account.listPlan[0].status", is("notpaid"));

        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification with failure
     */
    @Test
    public void recievePayplugNotificationWithFailure() {
        try {
            User u = generateLoggedUser();
            u.getAccount().getListPlan().get(0).setStatus("notpaid");
            mongo.save(u);
            new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                    .respond(HttpResponse.response().withStatusCode(201)
                            .withBody(generateMockBody(u, 0)));

            JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
            String payment_url = given().header(TOKEN, u.getAccount().getToken())
                    .body(request.encodePrettily())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(200)
                    .body("status", is(true))
                    .body("status", notNullValue())
                    .body("payment_url", notNullValue()).extract().path("payment_url");

            String paymentId = given().header(TOKEN, u.getAccount().getToken())
                    .param("id", u.get_id())
                    .when().get(getURL(UserVerticle.USER_INFO))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(u.get_id()))
                    .body("account.listPlan[0].paiementURL", notNullValue())
                    .body("account.listPlan[0].paiementURL", is(payment_url))
                    .body("account.listPlan[0].paymentId", notNullValue())
                    .body("account.listPlan[0].paymentId", not(""))
                    .body("account.listPlan[0].status", is("notpaid"))
                    .extract().path("paymentId");

            JsonObject failure = new JsonObject();
            JsonObject notification = buildNotificationRequest(paymentId, u);
            String[] failures =
                    {"processing_error", "card_declined", "insufficient_funds", "fraud_suspected", "3ds_declined",
                            "incorrect_number", "aborted"};
            for (String f : failures) {
                failure.putString(CODE, f);
                notification.putObject("failure", failure);
                given().body(notification.encodePrettily())
                        .when().post(getURL(ShippingVerticle.IPN))
                        .then().assertThat().statusCode(200)
                        .body("status", notNullValue())
                        .body("status", is(false));
                // Let's verify if our user have'nt be marked as paid
                given().header(TOKEN, u.getAccount().getToken())
                        .param("id", u.get_id())
                        .when().get(getURL(UserVerticle.USER_INFO))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("_id", is(u.get_id()))
                        .body("account.listPlan[0].paiementURL", notNullValue())
                        .body("account.listPlan[0].paiementURL", is(payment_url))
                        .body("account.listPlan[0].paymentId", notNullValue())
                        .body("account.listPlan[0].paymentId", not(""))
                        .body("account.listPlan[0].status", is("notpaid"));
            }
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification withnon existing user id
     */
    @Test
    public void recievePayplugNotificationWithnonExistingUserId() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("customer_id", "bwahaha");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Recieve payplug notification with null user id
     */
    @Test
    public void recievePayplugNotificationWithNullUserId() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").removeField("customer_id");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification with empty user id
     */
    @Test
    public void recievePayplugNotificationWithEmptyUserId() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("customer_id", "");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recurring payment
     */
    @Test
    public void recurringPayment() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        u.getAccount().getListPlan().get(0).setPaidDate(0);
        u.getAccount().getListPlan().get(0).setCardId("123456");
        u.getAccount().getListPlan().get(0).setPeriodicity("monthly");

        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u)));
        Assert.assertTrue(res.encodePrettily(), res.getBoolean("status"));
    }

    /**
     * Recurring payment multi user.
     */
    @Test
    public void recurringPaymentMultiUser() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        u.getAccount().getListPlan().get(0).setPaidDate(0);
        u.getAccount().getListPlan().get(0).setCardId("123456");
        u.getAccount().getListPlan().get(0).setPeriodicity("monthly");

        JsonObject res = sendOnBus(ShippingVerticle.PERIODIC_RECURING_PAYMENT, new JsonObject());
        Assert.assertTrue(res.encodePrettily(), res.getBoolean("status"));
    }

    /**
     * Recurring payment with zero amount
     */
    @Test
    public void recurringPaymentWithZeroAmount() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0, 0)));
        u.getAccount().getListPlan().get(0).setPaidDate(0);
        u.getAccount().getListPlan().get(0).setCardId("123456");
        u.getAccount().getListPlan().get(0).setPeriodicity("monthly");

        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u)));
        Assert.assertTrue(res.encodePrettily(), res.getBoolean("status"));
    }

    /**
     * Recurring payment with no card info
     */
    @Test
    public void recurringPaymentWithNoCardInfo() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        u.getAccount().getListPlan().get(0).setPaidDate(0);
        u.getAccount().getListPlan().get(0).setCardId(null);
        u.getAccount().getListPlan().get(0).setCardInfo(null);
        u.getAccount().getListPlan().get(0).setPeriodicity("monthly");

        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u)));
        Assert.assertFalse(res.encodePrettily(), res.getBoolean("status"));
    }

    /**
     * Recurring payment with future date.
     */
    @Test
    public void recurringPaymentWithFutureDate() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        u.getAccount().getListPlan().get(0).setPaidDate(System.currentTimeMillis());
        u.getAccount().getListPlan().get(0).setCardId("123456");
        u.getAccount().getListPlan().get(0).setPeriodicity("monthly");

        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u)));
        Assert.assertFalse(res.encodePrettily(), res.getBoolean("status"));
    }

    /**
     * Recurring payment with not supported periodicity.
     */
    @Test
    public void recurringPaymentWithNotSupportedPeriodicity() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        u.getAccount().getListPlan().get(0).setPaidDate(0);
        u.getAccount().getListPlan().get(0).setCardId("123456");
        u.getAccount().getListPlan().get(0).setPeriodicity("daily");

        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u)));
        Assert.assertFalse(res.encodePrettily(), res.getBoolean("status"));
    }

    /**
     * Recurring payment with no plan.
     */
    @Test
    public void recurringPaymentWithNoPlan() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        u.getAccount().setListPlan(new ArrayList<>());

        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u)));
        Assert.assertFalse(res.encodePrettily(), res.getBoolean("status"));
    }

    /**
     * Recurring payment with no payplug servers.
     */
    @Test
    public void recurringPaymentWithNoPayplugServers() {
        User u = generateLoggedUser();
        u.getAccount().getListPlan().get(0).setPaidDate(0);
        u.getAccount().getListPlan().get(0).setCardId("123456");
        u.getAccount().getListPlan().get(0).setPeriodicity("monthly");
        if (mockServer.isRunning()) {
            mockServer.stop();
        }
        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u)));
        Assert.assertEquals(res.encodePrettily(), res.getString("code"), ExceptionCodes.HTTP_ERROR.name());
    }

    /**
     * Recurring payment with wrong data.
     */
    @Test
    public void recurringPaymentWithWrongData() {
        User u = generateUser();
        User u2 = generateLoggedUser("123785");
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        u.getAccount().setListPlan(new ArrayList<>());

        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u2)));
        Assert.assertFalse(res.encodePrettily(), res.getBoolean("status"));
    }

    /**
     * Recurring payment with bad payplug response.
     */
    @Test
    public void recurringPaymentWithBadPayplugResponse() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(500)
                        .withBody(generateMockBody(u, 0)));
        u.getAccount().getListPlan().get(0).setPaidDate(0);
        u.getAccount().getListPlan().get(0).setCardId("123456");
        u.getAccount().getListPlan().get(0).setPeriodicity("monthly");

        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u)));
        Assert.assertEquals(res.encodePrettily(), res.getString("code"), ExceptionCodes.HTTP_ERROR.name());
    }

    /**
     * @param paymentId paymentId
     * @param u         user
     * @return a notification object
     */

    private JsonObject buildNotificationRequest(String paymentId, User u) {
        return new JsonObject()
                .putString("id", "123456")
                .putNumber("amount", 900L)
                .putString("object", "payment")
                .putBoolean("is_paid", true)
                .putObject("metadata", new JsonObject()
                        .putString("customer_id", u.get_id())
                        .putString("plan_id", "0")
                        .putString("locale", "fr_FR")
                )
                .putObject("card", new JsonObject()
                        .putString("last4", "1800")
                        .putString("country", "FR")
                        .putString("brand", "Mastercard")
                        .putNumber("exp_month", 9)
                        .putNumber("exp_year", 2017)
                )
                .putNumber("created_at", new Date().getTime())
                .putString("payment_id", paymentId);
    }

    /**
     * @param u      user
     * @param planId plan id
     * @param amount amount
     * @return body
     */
    private String generateMockBody(User u, int planId, int amount) {
        return "{\n" +
                "  \"amount\": " + amount + ",\n" +
                "  \"amount_refunded\": 0,\n" +
                "  \"card\": {\n" +
                "      \"brand\": null,\n" +
                "      \"country\": null,\n" +
                "      \"exp_month\": null,\n" +
                "      \"exp_year\": null,\n" +
                "      \"id\": null,\n" +
                "      \"last4\": null\n" +
                "  },\n" +
                "  \"created_at\": " + new Date().getTime() + ",\n" +
                "  \"currency\": \"EUR\",\n" +
                "  \"customer\": {\n" +
                "      \"address1\": null,\n" +
                "      \"address2\": null,\n" +
                "      \"city\": null,\n" +
                "      \"country\": null,\n" +
                "      \"email\": \"" + u.getContact().getEmail() + "\",\n" +
                "      \"first_name\": \"" + u.getFirstname() + "\",\n" +
                "      \"last_name\": \"" + u.getName() + "\",\n" +
                "      \"postcode\": null\n" +
                "  },\n" +
                "  \"failure\": null,\n" +
                "  \"hosted_payment\": {\n" +
                "      \"cancel_url\": \"" + moduleConfig.getObject("payplug").getString("cancel_url") + "\",\n" +
                "      \"paid_at\": null,\n" +
                "      \"payment_url\": \"https://www.payplug.com/pay/test/2DNkjF024bcLFhTn7OBfcc\",\n" +
                "      \"return_url\": \"" + moduleConfig.getObject("payplug").getString("return_url") + "\"\n" +
                "  },\n" +
                "  \"id\": \"pay_2DNkjF024bcLFhTn7OBfcc\",\n" +
                "  \"is_3ds\": null,\n" +
                "  \"is_live\": false,\n" +
                "  \"is_paid\": false,\n" +
                "  \"is_refunded\": false,\n" +
                "  \"metadata\": {\n" +
                "      \"customer_id\": \"" + u.get_id() + "\",\n" +
                "      \"plan_id\": \"" + planId + "\",\n" +
                "      \"locale\": \"" + LOCALE + "\"\n" +
                "  },\n" +
                "  \"notification\": {\n" +
                "      \"response_code\": null,\n" +
                "      \"url\": \"https://example.net/notifications?id=42710\"\n" +
                "  },\n" +
                "  \"object\": \"payment\",\n" +
                "  \"save_card\": true\n" +
                "}";
    }

    /**
     * @param u User
     * @param i planId
     * @return body
     */
    private String generateMockBody(User u, int i) {
        return generateMockBody(u, i, 900);
    }
}
