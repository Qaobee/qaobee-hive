/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.test.api.commons.user;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.commons.users.SignupVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

/**
 * @author jerome
 *
 */
public class SignupTest extends VertxJunitSupport {
	
	@Test
    public void registerOk() {
		populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
		
		final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.PUT);
        final JsonObject params = new JsonObject();
        
        // Account
        JsonObject jsonObject = new JsonObject();
        jsonObject.putString("login", "loginTest");
        jsonObject.putString("passwd", "passwdTest");
        params.putObject("account", jsonObject);
        
        // Contact
        jsonObject = new JsonObject();
        jsonObject.putString("email", "prenom.nom@fai.pays");
        params.putObject("contact", jsonObject);
        
        // Plan
        jsonObject = new JsonObject();
        jsonObject.putString("levelPlan", "FREEMIUM");
        JsonObject objChild = new JsonObject();
        objChild.putString("_id", "ACT-HAND");
        jsonObject.putObject("activity", objChild);
        params.putObject("plan", jsonObject);
        
        // Nom & Prénom
        params.putString("firstname", "Prenom");
        params.putString("name", "NOM");
        
        //jUnit
        params.putBoolean("junit", true);
        
        req.setBody(params.encode());
        final String reply = sendonBus(SignupVerticle.REGISTER, req);
        JsonObject result = new JsonObject(reply);
        
        Assert.assertNotNull(result.getObject("person"));
        Assert.assertNotNull(result.getObject("person").getString("_id"));
	}

	
}
