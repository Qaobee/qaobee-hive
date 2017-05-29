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
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.vertx.java.core.json.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
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
        try {
            mockData = new JsonObject(FileUtils.readFileToString(new File("src/test/resources/mocks.json")));
        } catch (IOException e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
        Stripe.overrideApiBase("http://localhost:1080");
        mockServer = startClientAndServer(1080);
    }

    /**
     * Stop mock server.
     */
    @After
    public void stopMockServer() {
        if (mockServer.isRunning()) {
            mockServer.stop();
        }
    }

    private void initMockStripe(User u) {
        JsonObject customer = mockData.getObject("customer");
        customer.putString("id", u.getAccount().getListPlan().get(0).getCardId());
        customer.putString("email", u.getContact().getEmail());
        customer.putNumber("created",new Date().getTime());
        customer.getObject("metadata").putString("_id", u.get_id());


        JsonObject subscription = mockData.getObject("subscription");
        subscription.putString("customer",  u.getAccount().getListPlan().get(0).getCardId());

        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/v1/customers/" + u.getAccount().getListPlan().get(0).getCardId()))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(customer.encode()));
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/v1/tokens/tok_1AMilbArxO1IWesL3vBI9RXu"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(mockData.getObject("token").encode()));
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/v1/tokens/bla"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
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
                        .withPath("/v1/customers/"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(customer.encode()));
        new MockServerClient("localhost", 1080)
                .when(HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/v1/subscriptions"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(subscription.encode()));
    }

    /**
     * Create payment.
     */
    @Test
    public void createPayment() {
        User u = generateLoggedUser();
        initMockStripe(u);

        JsonObject request = new JsonObject()
                .putObject("data", new JsonObject()
                        .putNumber("planId", 0)
                        .putString("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                );

        given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
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
    }

    /**
     * Create payment with wrong plan id.
     */
    @Test
    public void createPaymentWithWrongPlanId() {
        User u = generateLoggedUser();
        initMockStripe(u);
        JsonObject request = new JsonObject()
                .putObject("data", new JsonObject()
                        .putNumber("planId", 1)
                        .putString("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                );

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
        initMockStripe(u);
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
        initMockStripe(u);
        given().header(TOKEN, u.getAccount().getToken())
                .when().get(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Create payment with wrong token.
     */
    @Test
    public void createPaymentWithWrongToken() {
        User u = generateLoggedUser();
        initMockStripe(u);
        JsonObject request = new JsonObject()
                .putObject("data", new JsonObject()
                        .putNumber("planId", 0)
                        .putString("token", "bla")
                );
        given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Create payment with no server.
     */
    @Test
    public void createPaymentWithNoServer() {
        User u = generateLoggedUser();
        initMockStripe(u);
        JsonObject request = new JsonObject()
                .putObject("data", new JsonObject()
                        .putNumber("planId", 0)
                        .putString("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                );
        initMockStripe(u);
        if (mockServer.isRunning()) {
            mockServer.stop();
        }
        given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Recieve webhook notification
     */
    @Test
    public void recieveWebHookNotification() {
        User u = generateLoggedUser();
        initMockStripe(u);
        JsonObject request = new JsonObject()
                .putObject("data", new JsonObject()
                        .putNumber("planId", 0)
                        .putString("token", "tok_1AMilbArxO1IWesL3vBI9RXu")
                );

        given().header(TOKEN, u.getAccount().getToken())
                .body(request.encodePrettily())
                .when().post(getURL(ShippingVerticle.PAY))
                .then().assertThat().statusCode(200)
                .body("status", is(true))
                .body("status", notNullValue());;

        String customer = given().header(TOKEN, u.getAccount().getToken())
                .param("id", u.get_id())
                .when().get(getURL(UserVerticle.USER_INFO))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(u.get_id()))
                .body("account.listPlan[0].cardId", notNullValue())
                .body("account.listPlan[0].cardId", not(""))
                .extract().path("account.listPlan[0].cardId");

        JsonObject webHookData = mockData.getObject("customer.subscription.created");
        webHookData.getObject("data").getObject("object").getObject("metadata").putString("planId", "0");
        webHookData.getObject("data").getObject("object").putString("customer", customer);

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
        JsonObject webHookData = mockData.getObject("customer.subscription.created");
        webHookData.removeField("created");
        given().body(webHookData.encodePrettily())
                .when().post(getURL(ShippingVerticle.WEB_HOOK))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve WebHook notification with missing metadata
     */
    @Test
    public void recieveWebHookNotificationWithMissingMetadata() {
        JsonObject webHookData = mockData.getObject("customer.subscription.created");
        webHookData.getObject("data").getObject("object").getObject("metadata").removeField("planId");
        given().body(webHookData.encodePrettily())
                .when().post(getURL(ShippingVerticle.WEB_HOOK))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }


    /**
     * Recieve WebHook notification with bad plan id
     */
    @Test
    public void recieveWebHookNotificationWithBadPlanId() {
        User u = generateLoggedUser();
        initMockStripe(u);
        JsonObject webHookData = mockData.getObject("customer.subscription.created");
        webHookData.getObject("data")
                .getObject("object")
                .getObject("metadata")
                .putString("planId", "sdqsdqd");

        given().body(webHookData.encodePrettily())
                .when().post(getURL(ShippingVerticle.WEB_HOOK))
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Recieve WebHook notification with non existing plan id
     */
    @Test
    public void recieveWebHookNotificationWithNonExistingPlanId() {
        User u = generateLoggedUser();
        initMockStripe(u);
        JsonObject webHookData = mockData.getObject("customer.subscription.created");
        webHookData.getObject("data")
                .getObject("object")
                .getObject("metadata")
                .putString("planId", "1");
        given().body(webHookData.encodePrettily())
                .when().post(getURL(ShippingVerticle.WEB_HOOK))
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * Recieve WebHook notification with  null plan id
     */
    @Test
    public void recieveWebHookNotificationWithNullPlanId() {
        User u = generateLoggedUser();
        initMockStripe(u);
        JsonObject webHookData = mockData.getObject("customer.subscription.created");
        webHookData.getObject("data")
                .getObject("object")
                .getObject("metadata")
                .removeField("planId");
        given().body(webHookData.encodePrettily())
                .when().post(getURL(ShippingVerticle.WEB_HOOK))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Recieve WebHook notification with empty plan id
     */
    @Test
    public void recieveWebHookNotificationWithEmptyPlanId() {
        User u = generateLoggedUser();
        initMockStripe(u);
        JsonObject webHookData = mockData.getObject("customer.subscription.created");
        webHookData.getObject("data")
                .getObject("object")
                .getObject("metadata")
                .putString("planId", "");
        given().body(webHookData.encodePrettily())
                .when().post(getURL(ShippingVerticle.WEB_HOOK))
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
    }
}
