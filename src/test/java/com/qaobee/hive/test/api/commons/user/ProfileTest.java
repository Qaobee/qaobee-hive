package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.commons.users.ProfileVerticle;
import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

/**
 * Created by b3605 on 04/12/15.
 *
 * @author Xavier MARIN (b3605)
 */
public class ProfileTest extends VertxJunitSupport {

    /**
     * Update profile with common data test.
     */
    @Test
    public void updateProfileWithCommonDataTest() {
        User u = generateLoggedUser();
        u.setGender("androgyn");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setBody(Json.encode(u));
        final String reply = sendonBus(ProfileVerticle.UPDATE, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertEquals(u.getName(), result.getString("name"));
        Assert.assertEquals(u.getGender(), result.getString("gender"));
    }

    /**
     * Update profile with password change test.
     */
    @Test
    public void updateProfileWithPasswordChangeTest() {
        User u = generateLoggedUser();
        u.getAccount().setPasswd("toto");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setBody(Json.encode(u));
        final String reply = sendonBus(ProfileVerticle.UPDATE, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertEquals(u.getName(), result.getString("name"));
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, u.getAccount().getPasswd());
        req.setBody(params.encode());
        final String reply2 = sendonBus(UserVerticle.LOGIN, req);
        JsonObject result2 = new JsonObject(reply2);
        Assert.assertEquals(u.getName(), result2.getString("name"));
    }

    /**
     * Generate profile pdf test.
     */
    @Test
    public void generateProfilePDFTest() {
        User u = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(u);
        final String reply = sendonBus(ProfileVerticle.GENERATE_PDF, req);
        JsonObject result = new JsonObject(reply);
        Assert.assertTrue(result.getString(Main.FILE_SERVE), result.containsField(Main.FILE_SERVE));
    }
}
