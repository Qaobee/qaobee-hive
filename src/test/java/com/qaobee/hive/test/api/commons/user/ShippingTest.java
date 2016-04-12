package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.v1.commons.users.ShippingVerticle;
import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Plan;
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
 * The type Shipping test.
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
        mockServer.stop();
    }

    /**
     * Create payment test.
     */
    @Test
    public void createPaymentTest() {
        User u = generateLoggedUser();
        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        String payment_url = given().header("token", u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", is(true))
                .body("status", notNullValue())
                .body("payment_url", notNullValue()).extract().path("payment_url");

        given().header("token", u.getAccount().getToken())
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
     * Create payment with wrong plan id test.
     */
    @Test
    public void createPaymentWithWrongPlanIdTest() {
        User u = generateLoggedUser();
        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "1");
        given().header("token", u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                .body("code", is(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Create payment with missing plan id test.
     */
    @Test
    public void createPaymentWithMissingPlanIdTest() {
        User u = generateLoggedUser();
        given().header("token", u.getAccount().getToken())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Create payment with wrong http method test.
     */
    @Test
    public void createPaymentWithWrongHttpMethodTest() {
        User u = generateLoggedUser();
        given().header("token", u.getAccount().getToken())
                .when().get(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Recieve payplug notification test.
     */
    @Test
    public void recievePayplugNotificationTest() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                .respond(HttpResponse.response().withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));

        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        String payment_url = given().header("token", u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true))
                .body("payment_url", notNullValue()).extract().path("payment_url");

        String paymentId = given().header("token", u.getAccount().getToken())
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
        given().header("token", u.getAccount().getToken())
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
     * Recieve payplug notification with no previous shipping test.
     */
    @Test
    public void recievePayplugNotificationWithNoPreviousShippingTest() {
        User u = generateLoggedUser();
        u.getAccount().getListPlan().get(0).setShippingList(null);
        u.getAccount().getListPlan().get(0).setCardInfo(null);
        u.getAccount().getListPlan().get(0).setCardId(null);
        try {
            mongo.save(u);
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                .respond(HttpResponse.response().withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));

        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        String payment_url = given().header("token", u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true))
                .body("payment_url", notNullValue()).extract().path("payment_url");

        String paymentId = given().header("token", u.getAccount().getToken())
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
        given().header("token", u.getAccount().getToken())
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
    }

    /**
     * Recieve payplug notification with zero amount test.
     */
    @Test
    public void recievePayplugNotificationWithZeroAmountTest() {
        User u = generateLoggedUser();
        u.getAccount().getListPlan().get(0).setAmountPaid(0L);
        try {
            mongo.save(u);
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                .respond(HttpResponse.response().withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));

        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        String payment_url = given().header("token", u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true))
                .body("payment_url", notNullValue()).extract().path("payment_url");

        String paymentId = given().header("token", u.getAccount().getToken())
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
    }

    /**
     * Recieve payplug notification with wrong http method test.
     */
    @Test
    public void recievePayplugNotificationWithWrongHttpMethodTest() {
        given().when().get(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Recieve payplug notification with missing mandatory data test.
     */
    @Test
    public void recievePayplugNotificationWithMissingMandatoryDataTest() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.removeField("created_at");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification with missing metadata test.
     */
    @Test
    public void recievePayplugNotificationWithMissingMetadataTest() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").removeField("plan_id");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }


    /**
     * Recieve payplug notification with bad plan id test.
     */
    @Test
    public void recievePayplugNotificationWithBadPlanIdTest() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("plan_id", "bwahaha");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification with non existing plan id test.
     */
    @Test
    public void recievePayplugNotificationWithNonExistingPlanIdTest() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("plan_id", "8");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification with  null plan id test.
     */
    @Test
    public void recievePayplugNotificationWithNullPlanIdTest() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").removeField("plan_id");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification with empty plan id test.
     */
    @Test
    public void recievePayplugNotificationWithEmptyPlanIdTest() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("plan_id", "");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification wrong object test.
     */
    @Test
    public void recievePayplugNotificationWrongObjectTest() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                .respond(HttpResponse.response().withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));

        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        String payment_url = given().header("token", u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", is(true))
                .body("status", notNullValue())
                .body("payment_url", notNullValue()).extract().path("payment_url");

        String paymentId = given().header("token", u.getAccount().getToken())
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
     * Recieve payplug notification not paid test.
     */
    @Test
    public void recievePayplugNotificationNotPaidTest() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                .respond(HttpResponse.response().withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));

        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        String payment_url = given().header("token", u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", is(true))
                .body("status", notNullValue())
                .body("payment_url", notNullValue()).extract().path("payment_url");

        String paymentId = given().header("token", u.getAccount().getToken())
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
        given().header("token", u.getAccount().getToken())
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

    /**
     * Recieve payplug notification with failure test.
     */
    @Test
    public void recievePayplugNotificationWithFailureTest() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080).when(HttpRequest.request().withMethod("POST").withPath("/v1/payments"))
                .respond(HttpResponse.response().withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));

        JsonObject request = new JsonObject().putString(ShippingVerticle.PARAM_PLAN_ID, "0");
        String payment_url = given().header("token", u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", is(true))
                .body("status", notNullValue())
                .body("payment_url", notNullValue()).extract().path("payment_url");

        String paymentId = given().header("token", u.getAccount().getToken())
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
            failure.putString("code", f);
            notification.putObject("failure", failure);
            given().body(notification.encodePrettily())
                    .when().post(getURL(ShippingVerticle.IPN))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));
            // Let's verify if our user have'nt be marked as paid
            given().header("token", u.getAccount().getToken())
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
    }

    /**
     * Recieve payplug notification withnon existing user id test.
     */
    @Test
    public void recievePayplugNotificationWithnonExistingUserIdTest() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("customer_id", "bwahaha");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body("code", is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Recieve payplug notification with null user id test.
     */
    @Test
    public void recievePayplugNotificationWithNullUserIdTest() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").removeField("customer_id");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve payplug notification with empty user id test.
     */
    @Test
    public void recievePayplugNotificationWithEmptyUserIdTest() {
        JsonObject notification = buildNotificationRequest("12345", generateUser());
        notification.getObject("metadata").putString("customer_id", "");
        given().body(notification.encodePrettily())
                .when().post(getURL(ShippingVerticle.IPN))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recurring payment test.
     */
    @Test
    public void recurringPaymentTest() {
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
     * Recurring payment with zero amount test.
     */
    @Test
    public void recurringPaymentWithZeroAmountTest() {
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
     * Recurring payment with no card info test.
     */
    @Test
    public void recurringPaymentWithNoCardInfoTest() {
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
     * Recurring payment with future date test.
     */
    @Test
    public void recurringPaymentWithFutureDateTest() {
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
     * Recurring payment with not supported periodicity test.
     */
    @Test
    public void recurringPaymentWithNotSupportedPeriodicityTest() {
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
     * Recurring payment with no plan test.
     */
    @Test
    public void recurringPaymentWithNoPlanTest() {
        User u = generateLoggedUser();
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/payments"))
                .respond(HttpResponse.response()
                        .withStatusCode(201)
                        .withBody(generateMockBody(u, 0)));
        u.getAccount().setListPlan(new ArrayList<Plan>());

        JsonObject res = sendOnBus(ShippingVerticle.TRIGGERED_RECURING_PAYMENT, new JsonObject(Json.encode(u)));
        Assert.assertFalse(res.encodePrettily(), res.getBoolean("status"));
    }

    /**
     * @param paymentId paymentId
     * @param u         user
     * @return a notification object
     */
    private JsonObject buildNotificationRequest(String paymentId, User u) {
        JsonObject notification = new JsonObject();
        notification.putString("id", "123456");
        notification.putNumber("amount", 900L);
        notification.putString("object", "payment");
        notification.putBoolean("is_paid", true);
        JsonObject metadata = new JsonObject();
        metadata.putString("customer_id", u.get_id());
        metadata.putString("plan_id", "0");
        metadata.putString("locale", "fr_FR");
        notification.putObject("metadata", metadata);
        JsonObject card = new JsonObject();
        card.putString("last4", "1800");
        card.putString("country", "FR");
        card.putString("brand", "Mastercard");
        card.putNumber("exp_month", 9);
        card.putNumber("exp_year", 2017);
        notification.putObject("card", card);
        notification.putNumber("created_at", new Date().getTime());
        notification.putString("payment_id", paymentId);
        return notification;
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
