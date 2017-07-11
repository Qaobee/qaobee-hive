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
import com.qaobee.hive.test.config.VertxJunitSupport;
import com.stripe.Stripe;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

/**
 * The type Shipping
 */
public class ShippingTest extends VertxJunitSupport {
    private ClientAndServer mockServer;
    private JsonObject mockData;

    /**
     * Init mock server.
     */
    @Before
    public void initMockServer() {
        Stripe.overrideApiBase("http://localhost:1080");
        try {
            mockData = new JsonObject(FileUtils.readFileToString(new File("src/testBodyParams/resources/mocks.json"), "UTF-8"));
        } catch (IOException e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
        mockServer = startClientAndServer(1080);
        LOG.info("MockServer started");
    }

    /**
     * Stop mock server.
     */
    @After
    public void stopMockServer() {
        if (mockServer != null && mockServer.isRunning()) {
            mockServer.stop();
        }
    }

    private void initMockStripe(User u) {
        JsonObject customer = mockData.getJsonObject("customer")
                .put("id", u.getAccount().getListPlan().get(0).getCardId())
                .put("email", u.getContact().getEmail())
                .put("created", new Date().getTime());
        customer.getJsonObject("metadata").put("_id", u.get_id());
        JsonObject subscription = mockData.getJsonObject("subscription");
        subscription.put("customer", u.getAccount().getListPlan().get(0).getCardId());

        if ("12345".equals(u.getAccount().getListPlan().get(0).getCardId())) {
            new MockServerClient("localhost", 1080)
                    .when(HttpRequest.request()
                            .withMethod("GET")
                            .withPath("/v1/customers/12345"))
                    .respond(HttpResponse.response()
                            .withStatusCode(404)
                            .withBody("{\n" +
                                    "  \"error\": {\n" +
                                    "    \"type\": \"invalid_request_error\",\n" +
                                    "    \"message\": \"No such customer: 12345\",\n" +
                                    "    \"param\": \"id\"\n" +
                                    "  }\n" +
                                    "}\n"));
        } else {
            new MockServerClient("localhost", 1080)
                    .when(HttpRequest.request()
                            .withMethod("GET")
                            .withPath("/v1/customers/" + u.getAccount().getListPlan().get(0).getCardId()))
                    .respond(HttpResponse.response()
                            .withStatusCode(200)
                            .withBody(customer.encode()));
        }
        if ("canceled".equals(u.getAccount().getListPlan().get(0).getPaymentId())) {
            subscription.put("status", "canceled");
            new MockServerClient("localhost", 1080)
                    .when(HttpRequest.request()
                            .withMethod("GET")
                            .withPath("/v1/subscriptions/canceled"))
                    .respond(HttpResponse.response()
                            .withStatusCode(200)
                            .withBody(subscription.encode()));
        } else if ("12345".equals(u.getAccount().getListPlan().get(0).getPaymentId())) {
            new MockServerClient("localhost", 1080)
                    .when(HttpRequest.request()
                            .withMethod("GET")
                            .withPath("/v1/subscriptions/12345"))
                    .respond(HttpResponse.response()
                            .withStatusCode(404)
                            .withBody("{\n" +
                                    "  \"error\": {\n" +
                                    "    \"type\": \"invalid_request_error\",\n" +
                                    "    \"message\": \"No such subscription: 12345\",\n" +
                                    "    \"param\": \"id\"\n" +
                                    "  }\n" +
                                    "}\n"));
        } else {
            new MockServerClient("localhost", 1080)
                    .when(HttpRequest.request()
                            .withMethod("GET")
                            .withPath("/v1/subscriptions/sub_AkHS7YtIEi1Oy9"))
                    .respond(HttpResponse.response()
                            .withStatusCode(200)
                            .withBody(subscription.encode()));
        }
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/v1/tokens/tok_1AMilbArxO1IWesL3vBI9RXu"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(mockData.getJsonObject("token").encode()));
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/v1/tokens/bla"))
                .respond(HttpResponse.response()
                        .withStatusCode(404)
                        .withBody("{\n" +
                                "  \"error\": {\n" +
                                "    \"type\": \"invalid_request_error\",\n" +
                                "    \"message\": \"No such token: bla\",\n" +
                                "    \"param\": \"token\"\n" +
                                "  }\n" +
                                "}\n"));
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/customers"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(customer.put("id", UUID.randomUUID().toString()).encode()));
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/subscriptions"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(subscription.put("id", UUID.randomUUID().toString()).encode()));
    }

    /**
     * Create payment.
     */
    @Test
    public void createPayment(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            JsonObject request = new JsonObject()
                    .put("data", new JsonObject()
                            .put("planId", 0)
                            .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                    );
            given().header(TOKEN, u.getAccount().getToken())
                    .body(request.encode())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(200)
                    .body("status", is(true))
                    .body("status", notNullValue());

            given().header(TOKEN, u.getAccount().getToken())
                    .param("id", u.get_id())
                    .when().get(getURL(UserVerticle.USER_INFO))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(u.get_id()))
                    .body("account.listPlan[0].cardId", notNullValue())
                    .body("account.listPlan[0].cardId", not(""))
                    .body("account.listPlan[0].paymentId", notNullValue())
                    .body("account.listPlan[0].paymentId", not(""));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with wrong plan id.
     */
    @Test
    public void createPaymentWithWrongPlanId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            JsonObject request = new JsonObject()
                    .put("data", new JsonObject()
                            .put("planId", 1)
                            .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                    );
            System.out.println(getURL(ShippingVerticle.PAY));
            given().header(TOKEN, u.getAccount().getToken())
                    .body(request.encode())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                    .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with new customer.
     */
    @Test
    public void createPaymentWithNewCustomer(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            u.getAccount().getListPlan().get(0).setCardId(null);
            mongo.upsert(u).done(id -> {
                initMockStripe(u);
                JsonObject request = new JsonObject()
                        .put("data", new JsonObject()
                                .put("planId", 0)
                                .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                        );

                given().header(TOKEN, u.getAccount().getToken())
                        .body(request.encode())
                        .when().post(getURL(ShippingVerticle.PAY))
                        .then().assertThat().statusCode(200)
                        .body("status", is(true))
                        .body("status", notNullValue());

                given().header(TOKEN, u.getAccount().getToken())
                        .param("id", u.get_id())
                        .when().get(getURL(UserVerticle.USER_INFO))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("_id", is(u.get_id()))
                        .body("account.listPlan[0].cardId", notNullValue())
                        .body("account.listPlan[0].cardId", not(""))
                        .body("account.listPlan[0].paymentId", notNullValue())
                        .body("account.listPlan[0].paymentId", not(""));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with new customer and new subscription.
     */
    @Test
    public void createPaymentWithNewCustomerAndNewSubscription(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            u.getAccount().getListPlan().get(0).setCardId(null);
            u.getAccount().getListPlan().get(0).setPaymentId(null);

            mongo.upsert(u).done(id -> {
                initMockStripe(u);
                JsonObject request = new JsonObject()
                        .put("data", new JsonObject()
                                .put("planId", 0)
                                .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                        );

                given().header(TOKEN, u.getAccount().getToken())
                        .body(request.encode())
                        .when().post(getURL(ShippingVerticle.PAY))
                        .then().assertThat().statusCode(200)
                        .body("status", is(true))
                        .body("status", notNullValue());

                given().header(TOKEN, u.getAccount().getToken())
                        .param("id", u.get_id())
                        .when().get(getURL(UserVerticle.USER_INFO))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("_id", is(u.get_id()))
                        .body("account.listPlan[0].cardId", notNullValue())
                        .body("account.listPlan[0].cardId", not(""))
                        .body("account.listPlan[0].paymentId", notNullValue())
                        .body("account.listPlan[0].paymentId", not(""));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with existing subscription.
     */
    @Test
    public void createPaymentWithExistingSubscription(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            u.getAccount().getListPlan().get(0).setPaymentId("sub_AkHS7YtIEi1Oy9");
            mongo.upsert(u).done(id -> {
                initMockStripe(u);
                JsonObject request = new JsonObject()
                        .put("data", new JsonObject()
                                .put("planId", 0)
                                .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                        );

                given().header(TOKEN, u.getAccount().getToken())
                        .body(request.encode())
                        .when().post(getURL(ShippingVerticle.PAY))
                        .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                        .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with canceled subscription.
     */
    @Test
    public void createPaymentWithCanceledSubscription(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            u.getAccount().getListPlan().get(0).setPaymentId("canceled");
            mongo.upsert(u).done(id -> {
                initMockStripe(u);

                JsonObject request = new JsonObject()
                        .put("data", new JsonObject()
                                .put("planId", 0)
                                .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                        );

                given().header(TOKEN, u.getAccount().getToken())
                        .body(request.encode())
                        .when().post(getURL(ShippingVerticle.PAY))
                        .then().assertThat().statusCode(200)
                        .body("status", is(true))
                        .body("status", notNullValue());

                given().header(TOKEN, u.getAccount().getToken())
                        .param("id", u.get_id())
                        .when().get(getURL(UserVerticle.USER_INFO))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("_id", is(u.get_id()))
                        .body("account.listPlan[0].cardId", notNullValue())
                        .body("account.listPlan[0].cardId", not(""))
                        .body("account.listPlan[0].paymentId", notNullValue())
                        .body("account.listPlan[0].paymentId", not(""));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with new subscription.
     */
    @Test
    public void createPaymentWithNewSubscription(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            u.getAccount().getListPlan().get(0).setPaymentId(null);
            mongo.upsert(u).done(id -> {
                initMockStripe(u);
                JsonObject request = new JsonObject()
                        .put("data", new JsonObject()
                                .put("planId", 0)
                                .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                        );

                given().header(TOKEN, u.getAccount().getToken())
                        .body(request.encode())
                        .when().post(getURL(ShippingVerticle.PAY))
                        .then().assertThat().statusCode(200)
                        .body("status", is(true))
                        .body("status", notNullValue());

                given().header(TOKEN, u.getAccount().getToken())
                        .param("id", u.get_id())
                        .when().get(getURL(UserVerticle.USER_INFO))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("_id", is(u.get_id()))
                        .body("account.listPlan[0].cardId", notNullValue())
                        .body("account.listPlan[0].cardId", not(""))
                        .body("account.listPlan[0].paymentId", notNullValue())
                        .body("account.listPlan[0].paymentId", not(""));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with wrong subscription.
     */
    @Test
    public void createPaymentWithWrongSubscription(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            u.getAccount().getListPlan().get(0).setPaymentId("12345");
            initMockStripe(u);
            mongo.upsert(u).done(id -> {
                JsonObject request = new JsonObject()
                        .put("data", new JsonObject()
                                .put("planId", 0)
                                .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                        );

                given().header(TOKEN, u.getAccount().getToken())
                        .body(request.encode())
                        .when().post(getURL(ShippingVerticle.PAY))
                        .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                        .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with missing plan id.
     */
    @Test
    public void createPaymentWithMissingPlanId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with wrong http method.
     */
    @Test
    public void createPaymentWithWrongHttpMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with wrong token.
     */
    @Test
    public void createPaymentWithWrongToken(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            JsonObject request = new JsonObject()
                    .put("data", new JsonObject()
                            .put("planId", 0)
                            .put("token", "bla")
                    );
            given().header(TOKEN, u.getAccount().getToken())
                    .body(request.encode())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                    .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Create payment with no server.
     */
    @Test
    public void createPaymentWithNoServer(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            JsonObject request = new JsonObject()
                    .put("data", new JsonObject()
                            .put("planId", 0)
                            .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                    );
            initMockStripe(u);
            if (mockServer.isRunning()) {
                mockServer.stop();
            }
            given().header(TOKEN, u.getAccount().getToken())
                    .body(request.encode())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Recieve webhook notification
     */
    @Test
    public void recieveWebHookNotification(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            JsonObject request = new JsonObject()
                    .put("data", new JsonObject()
                            .put("planId", 0)
                            .put("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                    );

            given().header(TOKEN, u.getAccount().getToken())
                    .body(request.encode())
                    .when().post(getURL(ShippingVerticle.PAY))
                    .then().assertThat().statusCode(200)
                    .body("status", is(true))
                    .body("status", notNullValue());

            String customer = given().header(TOKEN, u.getAccount().getToken())
                    .param("id", u.get_id())
                    .when().get(getURL(UserVerticle.USER_INFO))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("_id", is(u.get_id()))
                    .body("account.listPlan[0].cardId", notNullValue())
                    .body("account.listPlan[0].cardId", not(""))
                    .extract().path("account.listPlan[0].cardId");

            JsonObject webHookData = mockData.getJsonObject("customer.subscription.created");
            webHookData.getJsonObject("data").getJsonObject("object").getJsonObject("metadata").put("planId", "0");
            webHookData.getJsonObject("data").getJsonObject("object").put("customer", customer);

            given().body(webHookData.encode())
                    .when().post(getURL(ShippingVerticle.WEB_HOOK))
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
                    .body("account.listPlan[0].paymentId", not(""))
                    .body("account.listPlan[0].status", is("active"));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Recieve WebHook notification with wrong http method
     */
    @Test
    public void recieveWebHookNotificationWithWrongHttpMethod() {
        given().when().get(getURL(ShippingVerticle.WEB_HOOK))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Recieve WebHook notification with missing mandatory data
     */
    @Test
    public void recieveWebHookNotificationWithMissingMandatoryData() {
        JsonObject webHookData = mockData.getJsonObject("customer.subscription.created");
        webHookData.remove("created");
        given().body(webHookData.encode())
                .when().post(getURL(ShippingVerticle.WEB_HOOK))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve WebHook notification with missing metadata
     */
    @Test
    public void recieveWebHookNotificationWithMissingMetadata() {
        JsonObject webHookData = mockData.getJsonObject("customer.subscription.created");
        webHookData.getJsonObject("data").getJsonObject("object").getJsonObject("metadata").remove("planId");
        given().body(webHookData.encode())
                .when().post(getURL(ShippingVerticle.WEB_HOOK))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }


    /**
     * Recieve WebHook notification with bad plan id
     */
    @Test
    public void recieveWebHookNotificationWithBadPlanId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            JsonObject webHookData = mockData.getJsonObject("customer.subscription.created");
            webHookData.getJsonObject("data")
                    .getJsonObject("object")
                    .getJsonObject("metadata")
                    .put("planId", "sdqsdqd");

            given().body(webHookData.encode())
                    .when().post(getURL(ShippingVerticle.WEB_HOOK))
                    .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                    .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Recieve WebHook notification with non existing plan id
     */
    @Test
    public void recieveWebHookNotificationWithNonExistingPlanId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            JsonObject webHookData = mockData.getJsonObject("customer.subscription.created");
            webHookData.getJsonObject("data")
                    .getJsonObject("object")
                    .getJsonObject("metadata")
                    .put("planId", "1");
            given().body(webHookData.encode())
                    .when().post(getURL(ShippingVerticle.WEB_HOOK))
                    .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                    .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Recieve WebHook notification with  null plan id
     */
    @Test
    public void recieveWebHookNotificationWithNullPlanId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            JsonObject webHookData = mockData.getJsonObject("customer.subscription.created");
            webHookData.getJsonObject("data")
                    .getJsonObject("object")
                    .getJsonObject("metadata")
                    .remove("planId");
            given().body(webHookData.encode())
                    .when().post(getURL(ShippingVerticle.WEB_HOOK))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Recieve WebHook notification with empty plan id
     */
    @Test
    public void recieveWebHookNotificationWithEmptyPlanId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            initMockStripe(u);
            JsonObject webHookData = mockData.getJsonObject("customer.subscription.created");
            webHookData.getJsonObject("data")
                    .getJsonObject("object")
                    .getJsonObject("metadata")
                    .put("planId", "");
            given().body(webHookData.encode())
                    .when().post(getURL(ShippingVerticle.WEB_HOOK))
                    .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                    .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }
}
