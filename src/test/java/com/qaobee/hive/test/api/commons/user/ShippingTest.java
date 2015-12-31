package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.v1.commons.users.ShippingVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.vertx.java.core.json.JsonObject;

import java.util.Date;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

/**
 * The type Shipping test.
 */
public class ShippingTest extends VertxJunitSupport {
    private ClientAndServer mockServer;

    @Before
    public void initMockServer() {
        mockServer = startClientAndServer(1080);
    }

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
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );

        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Create payment with wrong plan id test.
     */
    @Test
    public void createPaymentWithWrongPlanIdTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 1);
        req.setBody(request.encode());
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("createPaymentWithWrongPlanIdTest", result.getString("code").contains(ExceptionCodes.INVALID_PARAMETER.toString()));
    }


    /**
     * Create payment with missing plan id test.
     */
    @Test
    public void createPaymentWithMissingPlanIdTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject();
        req.setBody(request.encode());
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("createPaymentWithMissingPlanIdTest", result.getString("code").contains(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Create payment with wrong http method test.
     */
    @Test
    public void createPaymentWithWrongHttpMethodTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(u);
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("createPaymentWithWrongHttpMethodTest", result.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Recieve payplug notification test.
     */
    @Test
    public void recievePayplugNotificationTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
            final RequestWrapper notificationRequest = new RequestWrapper();
            notificationRequest.setLocale(LOCALE);
            notificationRequest.setMethod(Constantes.POST);
            JsonObject notification = buildNotificationRequest(plan, u);
            notificationRequest.setBody(notification.encode());
            final String notificationReply = sendonBus(ShippingVerticle.IPN, notificationRequest);
            JsonObject notificationResult = new JsonObject(notificationReply);
            Assert.assertTrue("Status is false", notificationResult.getBoolean("status"));
            // Let's verify user's info
            JsonObject notificationUser = mongo.getById(u.get_id(), User.class);
            JsonObject notificationPlan = notificationUser.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("Payment date does'nt exists", notificationPlan.containsField("paidDate"));
            Assert.assertTrue("Card is null", notificationPlan.containsField("cardInfo"));
            Assert.assertTrue("Card info are wrong", notificationPlan.getObject("cardInfo").getString("last4").equals(notification.getObject("card").getString("last4")));
            Assert.assertTrue("Payment is not in pending state", notificationPlan.getString("status").equals("paid"));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification with wrong http method test.
     */
    @Test
    public void recievePayplugNotificationWithWrongHttpMethodTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
            final RequestWrapper notificationRequest = new RequestWrapper();
            notificationRequest.setLocale(LOCALE);
            notificationRequest.setMethod(Constantes.GET);
            notificationRequest.setBody(buildNotificationRequest(plan, u).encode());
            final String notificationReply = sendonBus(ShippingVerticle.IPN, notificationRequest);
            JsonObject notificationResult = new JsonObject(notificationReply);
            Assert.assertTrue("Wrong HTTP method tested", notificationResult.getString("code").contains(ExceptionCodes.HTTP_ERROR.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification with missing mandatory data test.
     */
    @Test
    public void recievePayplugNotificationWithMissingMandatoryDataTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
            final RequestWrapper notificationRequest = new RequestWrapper();
            notificationRequest.setLocale(LOCALE);
            notificationRequest.setMethod(Constantes.POST);
            JsonObject notification = buildNotificationRequest(plan, u);
            notification.removeField("payment_id");
            notificationRequest.setBody(notification.encode());
            final String notificationReply = sendonBus(ShippingVerticle.IPN, notificationRequest);
            JsonObject notificationResult = new JsonObject(notificationReply);
            Assert.assertTrue("Wrong data tested", notificationResult.getString("code").contains(ExceptionCodes.MANDATORY_FIELD.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification with missing metadata test.
     */
    @Test
    public void recievePayplugNotificationWithMissingMetadataTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
            final RequestWrapper notificationRequest = new RequestWrapper();
            notificationRequest.setLocale(LOCALE);
            notificationRequest.setMethod(Constantes.POST);
            JsonObject notification = buildNotificationRequest(plan, u);
            notification.getObject("metadata").removeField("plan_id");
            notificationRequest.setBody(notification.encode());
            final String notificationReply = sendonBus(ShippingVerticle.IPN, notificationRequest);
            JsonObject notificationResult = new JsonObject(notificationReply);
            Assert.assertTrue("Wrong data tested", notificationResult.getString("code").contains(ExceptionCodes.MANDATORY_FIELD.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification wrong metadata test.
     */
    @Test
    public void recievePayplugNotificationWrongMetadataTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
            final RequestWrapper notificationRequest = new RequestWrapper();
            notificationRequest.setLocale(LOCALE);
            notificationRequest.setMethod(Constantes.POST);
            JsonObject notification = buildNotificationRequest(plan, u);
            notification.getObject("metadata").putString("plan_id", "bwahahaha");
            notificationRequest.setBody(notification.encode());
            final String notificationReply = sendonBus(ShippingVerticle.IPN, notificationRequest);
            JsonObject notificationResult = new JsonObject(notificationReply);
            Assert.assertTrue("Wrong data tested", notificationResult.getString("code").contains(ExceptionCodes.MANDATORY_FIELD.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification wrong object test.
     */
    @Test
    public void recievePayplugNotificationWrongObjectTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
            final RequestWrapper notificationRequest = new RequestWrapper();
            notificationRequest.setLocale(LOCALE);
            notificationRequest.setMethod(Constantes.POST);
            JsonObject notification = buildNotificationRequest(plan, u);
            notification.putString("object", "bwahahaha");
            notificationRequest.setBody(notification.encode());
            final String notificationReply = sendonBus(ShippingVerticle.IPN, notificationRequest);
            JsonObject notificationResult = new JsonObject(notificationReply);
            Assert.assertFalse("Wrong status", notificationResult.getBoolean("status"));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification wrong plan id test.
     */
    @Test
    public void recievePayplugNotificationWrongPlanIdTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
            final RequestWrapper notificationRequest = new RequestWrapper();
            notificationRequest.setLocale(LOCALE);
            notificationRequest.setMethod(Constantes.POST);
            JsonObject notification = buildNotificationRequest(plan, u);
            notification.getObject("metadata").putString("plan_id", "8");
            notificationRequest.setBody(notification.encode());
            final String notificationReply = sendonBus(ShippingVerticle.IPN, notificationRequest);
            JsonObject notificationResult = new JsonObject(notificationReply);
            Assert.assertTrue("Wrong data tested", notificationResult.getString("code").contains(ExceptionCodes.MANDATORY_FIELD.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification not paid test.
     */
    @Test
    public void recievePayplugNotificationNotPaidTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
            final RequestWrapper notificationRequest = new RequestWrapper();
            notificationRequest.setLocale(LOCALE);
            notificationRequest.setMethod(Constantes.POST);
            JsonObject notification = buildNotificationRequest(plan, u);
            notification.putBoolean("is_paid", false);
            notificationRequest.setBody(notification.encode());
            final String notificationReply = sendonBus(ShippingVerticle.IPN, notificationRequest);
            JsonObject notificationResult = new JsonObject(notificationReply);
            Assert.assertFalse("Wrong status", notificationResult.getBoolean("status"));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Recieve payplug notification wrong user id test.
     */
    @Test
    public void recievePayplugNotificationWrongUserIdTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/v1/payments")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withBody(generateMockBody(u, 0))
                );
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            JsonObject plan = user.getObject("account").getArray("listPlan").get(0);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", plan.getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", plan.containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(plan.getString("paymentId")));
            Assert.assertTrue("Payment is not in pending state", plan.getString("status").equals("pending"));
            final RequestWrapper notificationRequest = new RequestWrapper();
            notificationRequest.setLocale(LOCALE);
            notificationRequest.setMethod(Constantes.POST);
            JsonObject notification = buildNotificationRequest(plan, u);
            notification.getObject("metadata").putString("customer_id", "bwahahaha");
            notificationRequest.setBody(notification.encode());
            final String notificationReply = sendonBus(ShippingVerticle.IPN, notificationRequest);
            JsonObject notificationResult = new JsonObject(notificationReply);
            Assert.assertTrue("Wrong data tested", notificationResult.getString("code").contains(ExceptionCodes.MONGO_ERROR.toString()));
        } catch (QaobeeException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * @param plan plan
     * @param u    user
     * @return a notification object
     */
    private JsonObject buildNotificationRequest(JsonObject plan, User u) {
        JsonObject notification = new JsonObject();
        notification.putString("id", "123456");
        notification.putString("object", "payment");
        notification.putBoolean("is_paid", true);
        JsonObject metadata = new JsonObject();
        metadata.putString("customer_id", u.get_id());
        metadata.putString("plan_id", "0");
        notification.putObject("metadata", metadata);
        JsonObject card = new JsonObject();
        card.putString("last4", "1800");
        card.putString("country", "FR");
        card.putString("brand", "Mastercard");
        card.putNumber("exp_month", 9);
        card.putNumber("exp_year", 2017);
        notification.putObject("card", card);
        notification.putNumber("created_at", new Date().getTime());
        notification.putString("payment_id", plan.getString("paymentId"));
        return notification;
    }

    /**
     *
     * @param u User
     * @param i planId
     * @return body
     */
    private String generateMockBody(User u, int i) {
        return "{\n" +
                "  \"amount\": 900,\n" +
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
                "      \"plan_id\": \"" + i + "\"\n" +
                "  },\n" +
                "  \"notification\": {\n" +
                "      \"response_code\": null,\n" +
                "      \"url\": \"https://example.net/notifications?id=42710\"\n" +
                "  },\n" +
                "  \"object\": \"payment\",\n" +
                "  \"save_card\": true\n" +
                "}";
    }
}
