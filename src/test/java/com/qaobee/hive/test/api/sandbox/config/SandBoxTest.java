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
package com.qaobee.hive.test.api.sandbox.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.api.v1.sandbox.config.SB_SandBoxVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;

/**
 * @author cke
 *
 */
public class SandBoxTest extends VertxJunitSupport {
	
	/**
     * Retrieve sand box by his owner.
     */
    @Test
    public void getSandBoxByOwnerOk() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);
        
        final Map<String, List<String>> params = new HashMap<>();
        params.put(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, Collections.singletonList((String)getActivity("ACT-HAND",user).getField(ActivityVerticle.PARAM_ID)));
        req.setParams(params);

        final String reply = sendonBus(SB_SandBoxVerticle.GET_BY_OWNER, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);
       
        Assert.assertEquals(user.get_id(), result.getString("owner"));

    }
    
    /**
     * Retrieve sand box by his owner.
     * with missing mandatory fields
     */
    @Test
    public void getSandBoxByOwnerKo() {

        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);
        
        final Map<String, List<String>> params = new HashMap<>();
        
        final String reply = sendonBus(SB_SandBoxVerticle.GET_BY_OWNER, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);
       
        Assert.assertTrue("Missing mandatory parameters", result.getString("message").contains("Missing mandatory parameters : [activityId]"));
        
        // id
 		params.put(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, Collections.singletonList(""));
 		req.setParams(params);
 		
 		final String reply2 = sendonBus(SB_SandBoxVerticle.GET_BY_OWNER, req, user.getAccount().getToken());
        JsonObject result2 = new JsonObject(reply2);
       
        Assert.assertTrue("Wrong format mandatory parameters", result2.getString("message").contains("Missing mandatory parameters : [activityId]"));

    }
    
    /**
     * Retrieve sand box by bad owner.
     */
    @Test
    public void getSandBoxByOwnerBadUser() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser("a0ef9c2d-6864-4a20-84ba-b66a666d2bf4");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);
        
        final Map<String, List<String>> params = new HashMap<>();
        
        params.put(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, Collections.singletonList((String)getActivity("ACT-HAND",user).getField(ActivityVerticle.PARAM_ID)));
        req.setParams(params);

        final String reply = sendonBus(SB_SandBoxVerticle.GET_BY_OWNER, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);
        
        Assert.assertTrue("SandBox not found", result.getString("message").contains("No SandBox found for user id"));

    }
    
    /**
     * Retrieve sand box by bad owner.
     */
    @Test
    public void getSandBoxByOwnerBadActivity() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);
        
        final Map<String, List<String>> params = new HashMap<>();
        
        params.put(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, Collections.singletonList((String)getActivity("ACT-FOOT",user).getField(ActivityVerticle.PARAM_ID)));
        req.setParams(params);

        final String reply = sendonBus(SB_SandBoxVerticle.GET_BY_OWNER, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);
        
        Assert.assertTrue("SandBox not found", result.getString("message").contains("No SandBox found for user id"));

    }
}
