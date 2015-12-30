package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.v1.commons.users.ShippingVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

/**
 * The type Shipping test.
 */
public class ShippingTest extends VertxJunitSupport {
    /**
     * Create payment.
     */
    @Test
    public void createPayment() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setUser(u);
        JsonObject request = new JsonObject().putNumber(ShippingVerticle.PARAM_PLAN_ID, 0);
        req.setBody(request.encode());
        final String reply = sendonBus(ShippingVerticle.PAY, req);
        JsonObject result = new JsonObject(reply);
        System.out.println(result.encodePrettily());
        Assert.assertTrue("Status", result.getBoolean("status"));
        Assert.assertNotNull("Payment object", result.getObject("payment"));
        Assert.assertNotNull("Payment url", result.getObject("payment").getObject("hosted_payment").getString("payment_url"));
        System.out.println(result.getObject("payment").getObject("hosted_payment").getString("payment_url"));
    }
}
