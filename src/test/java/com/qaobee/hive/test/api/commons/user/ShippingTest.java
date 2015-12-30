package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.v1.commons.users.ShippingVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

/**
 * The type Shipping test.
 */
public class ShippingTest extends VertxJunitSupport {
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
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue("Status is false", result.getBoolean("status"));
        Assert.assertNotNull("Payment url does'nt exists", result.getString("payment_url"));
        try {
            JsonObject user = mongo.getById(u.get_id(), User.class);
            Assert.assertTrue("user id is not equals", user.getString("_id").equals(u.get_id()));
            Assert.assertTrue("Payment url is not equals", ((JsonObject) user.getObject("account").getArray("listPlan").get(0)).getString("paiementURL").equals(result.getString("payment_url")));
            Assert.assertTrue("Payment id does'nt exists", ((JsonObject) user.getObject("account").getArray("listPlan").get(0)).containsField("paymentId"));
            Assert.assertTrue("Payment id is blank", StringUtils.isNotBlank(((JsonObject) user.getObject("account").getArray("listPlan").get(0)).getString("paymentId")));
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

}
