/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
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
package com.qaobee.hive.test.api.sandbox.effective;

import com.qaobee.hive.api.v1.commons.settings.CountryVerticle;
import com.qaobee.hive.api.v1.sandbox.effective.SB_EffectiveVerticle;
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.sandbox.effective.SB_Effective;
import com.qaobee.hive.business.model.transversal.Member;
import com.qaobee.hive.business.model.transversal.Role;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import java.util.*;

/**
 * The type Effective test.
 *
 * @author cke
 */
public class SB_EffectiveTest extends VertxJunitSupport {

    /**
     * Tests of getting a list of members of effective for one category
     */
    @Test
    public void getListMembersByCategory() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_EFFECTIVE_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

		/* list of parameters */
        final Map<String, List<String>> params = new HashMap<>();

        params.put(SB_EffectiveVerticle.PARAM_SANDBOXCFG_ID, Collections.singletonList("558b0fc0bd2e39cdab651e21"));
        params.put(SB_EffectiveVerticle.PARAM_CATEGORY_AGE_CODE, Collections.singletonList("sen"));
        req.setParams(params);

        final String reply = sendonBus(SB_EffectiveVerticle.GET_LIST, req, user.getAccount().getToken());
        JsonArray result = new JsonArray(reply);

        Assert.assertEquals(1, result.size());

        JsonObject itemOne = result.get(0);
        JsonArray members = itemOne.getArray("members");

        Assert.assertEquals(16, members.size());
    }

    /**
     * Tests of getting a list of members of effective for one unknown category
     */
    @Test
    public void getListMembersByUnknowCategory() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_EFFECTIVE_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

		/* list of parameters */
        final Map<String, List<String>> params = new HashMap<>();

        params.put(SB_EffectiveVerticle.PARAM_SANDBOXCFG_ID, Collections.singletonList("558b0fc0bd2e39cdab651e21"));
        params.put(SB_EffectiveVerticle.PARAM_CATEGORY_AGE_CODE, Collections.singletonList("zzz"));
        req.setParams(params);

        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.GET_LIST, req, user.getAccount().getToken()));
        Assert.assertTrue("Bad parameters", resultUpdate.getString("message").contains("No Effective found"));
    }

    /**
     * Tests of getting a list of members without sandbox config id
     */
    @Test
    public void getListMembersMissingSandBoxCfgId() {

        populate(POPULATE_ONLY, DATA_USERS);
        User user = generateLoggedUser("54160977d5bd065a1bb1e565");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.GET_LIST, req, user.getAccount().getToken()));
        Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [sandBoxCfgId]"));
    }

    /**
     * Tests of getting a list of members -> Bad method request
     */
    @Test
    public void getListMembersBadMethodRequest() {

        populate(POPULATE_ONLY, DATA_USERS);
        User user = generateLoggedUser("54160977d5bd065a1bb1e565");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.PUT);
        req.setUser(user);

        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.GET_LIST, req, user.getAccount().getToken()));
        Assert.assertTrue("Bad method request", resultUpdate.getString("message").contains("PUT is not allowed"));
    }

    /**
     * Tests of getting a list of members without sandbox config id
     */
    @Test
    public void getListMembersMissingUser() {

        User user = null;
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        final Map<String, List<String>> params = new HashMap<>();

        params.put(SB_EffectiveVerticle.PARAM_SANDBOXCFG_ID, Collections.singletonList("559d268318e3cb71c60d9649"));
        req.setParams(params);

        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.GET_LIST, req, ""));
        Assert.assertTrue("User not connected", resultUpdate.getString("message").contains("Please log in first"));
    }

    /**
     * Tests get for EffectiveVerticle
     */
    @Test
    public void getObjectByIdOk() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_EFFECTIVE_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        final Map<String, List<String>> params = new HashMap<>();

        // id
        params.put(SB_EffectiveVerticle.PARAM_ID, Collections.singletonList("550b31f925da07681592db23"));
        req.setParams(params);

        final String reply = sendonBus(SB_EffectiveVerticle.GET, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);

        JsonArray members = result.getArray("members");
        Assert.assertEquals(16, members.size());
    }

    /**
     * Tests get for EffectiveVerticle -> Bad method request
     */
    @Test
    public void getObjectByIdBadMethodRequest() {

        populate(POPULATE_ONLY, DATA_USERS);
        User user = generateLoggedUser("54160977d5bd065a1bb1e565");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.PUT);
        req.setUser(user);

        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertTrue("Bad method request", resultUpdate.getString("message").contains("PUT is not allowed"));
    }

    /**
     * Tests get for EffectiveVerticle
     * with missing mandatory fields
     */
    @Test
    public void getObjectByIdKo() {

        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        final Map<String, List<String>> params = new HashMap<>();

        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertTrue("Missing mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));

        // id
        params.put(CountryVerticle.PARAM_ID, Collections.singletonList(""));
        req.setParams(params);

        resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.GET, req, user.getAccount().getToken()));
        Assert.assertTrue("Wrong format mandatory parameters", resultUpdate.getString("message").contains("Missing mandatory parameters : [_id]"));

    }

    /**
     * Tests add effective
     */
    @Test
    public void addEffective() {

        populate(POPULATE_ONLY, DATA_USERS);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        CategoryAge categoryAge = new CategoryAge();
        categoryAge.setCode("u19");
        categoryAge.setAgeMax(18);
        categoryAge.setAgeMin(17);
        categoryAge.setLabel("U19");
        categoryAge.setGenre("Masculin");
        categoryAge.setOrder(4);

        List<Member> members = new ArrayList<Member>();
        Role role;
        Member member;
        for (int i = 0; i < 10; i++) {
            member = new Member();
            member.setPersonId(i + "test");
            role = new Role();
            role.setCode("player");
            role.setLabel("Joueur");
            member.setRole(role);

            members.add(member);
        }

        SB_Effective effective = new SB_Effective();
        effective.setSandBoxCfgId("idBidon");
        effective.setCategoryAge(categoryAge);
        effective.setMembers(members);

        req.setMethod(Constantes.PUT);

        req.setBody(Json.encode(effective));

        final String reply = sendonBus(SB_EffectiveVerticle.ADD, req, user.getAccount().getToken());
        JsonObject result = new JsonObject(reply);
        String id = result.getString("_id");

        Assert.assertNotNull(id);
        JsonArray listMember = result.getArray("members");

        Assert.assertEquals(10, listMember.size());
    }

    /**
     * Tests add effective -> Bad method request
     */
    @Test
    public void addEffectiveBadMethodRequest() {

        populate(POPULATE_ONLY, DATA_USERS);
        User user = generateLoggedUser("54160977d5bd065a1bb1e565");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.ADD, req, user.getAccount().getToken()));
        Assert.assertTrue("Bad method request", resultUpdate.getString("message").contains("GET is not allowed"));
    }

    /**
     * Tests add effective
     */
    @Test
    public void addEffectiveMissingUser() {

        populate(POPULATE_ONLY, DATA_USERS);
        User user = null;
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        CategoryAge categoryAge = new CategoryAge();
        categoryAge.setCode("u19");
        categoryAge.setAgeMax(18);
        categoryAge.setAgeMin(17);
        categoryAge.setLabel("U19");
        categoryAge.setGenre("Masculin");
        categoryAge.setOrder(4);

        List<Member> members = new ArrayList<Member>();
        Role role;
        Member member;
        for (int i = 0; i < 10; i++) {
            member = new Member();
            member.setPersonId(i + "test");
            role = new Role();
            role.setCode("player");
            role.setLabel("Joueur");
            member.setRole(role);

            members.add(member);
        }

        SB_Effective effective = new SB_Effective();
        effective.setSandBoxCfgId("idBidon");
        effective.setCategoryAge(categoryAge);
        effective.setMembers(members);

        req.setMethod(Constantes.PUT);

        req.setBody(Json.encode(effective));

        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.ADD, req, ""));
        Assert.assertTrue("User not connected", resultUpdate.getString("message").contains("Please log in first"));
    }

    /**
     * Tests update effective, remove one person
     */
    @Test
    public void updateRemovePerson() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_EFFECTIVE_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        /* list of parameters */
        final Map<String, List<String>> params = new HashMap<>();

        params.put(SB_EffectiveVerticle.PARAM_SANDBOXCFG_ID, Collections.singletonList("558b0fc0bd2e39cdab651e21"));
        params.put(SB_EffectiveVerticle.PARAM_CATEGORY_AGE_CODE, Collections.singletonList("sen"));
        req.setParams(params);

        final String reply = sendonBus(SB_EffectiveVerticle.GET_LIST, req, user.getAccount().getToken());
        JsonArray result = new JsonArray(reply);

        Assert.assertEquals(1, result.size());

        JsonObject itemOne = result.get(0);
        JsonArray members = itemOne.getArray("members");
        Assert.assertEquals(16, members.size());

        JsonArray newMembers = new JsonArray();
        for (Object object : members) {
            JsonObject item = (JsonObject) object;
            if (!"550a05dadb8f8b6e2f51f4db".equals(item.getString("personId"))) {
                newMembers.add(item);
            }
        }
        itemOne.putArray("members", newMembers);

        req.setMethod(Constantes.PUT);
        req.setBody(itemOne.encode());
        final String reply2 = sendonBus(SB_EffectiveVerticle.UPDATE, req, user.getAccount().getToken());

        JsonObject result2 = new JsonObject(reply2);
        JsonArray membersUpdate = result2.getArray("members");

        Assert.assertEquals(15, membersUpdate.size());

    }

    /**
     * test update effective, add one member
     */
    @Test
    public void updateEffectiveAddMember() {

        populate(POPULATE_ONLY, DATA_USERS, DATA_EFFECTIVE_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        /* list of parameters */
        final Map<String, List<String>> params = new HashMap<>();

        params.put(SB_EffectiveVerticle.PARAM_SANDBOXCFG_ID, Collections.singletonList("558b0fc0bd2e39cdab651e21"));
        params.put(SB_EffectiveVerticle.PARAM_CATEGORY_AGE_CODE, Collections.singletonList("sen"));
        req.setParams(params);

        final String reply = sendonBus(SB_EffectiveVerticle.GET_LIST, req, user.getAccount().getToken());
        JsonArray result = new JsonArray(reply);

        Assert.assertEquals(1, result.size());

        JsonObject effective = result.get(0);
        JsonArray members = effective.getArray("members");
        Assert.assertEquals(16, members.size());

        Member member = new Member();
        member.setPersonId("addMember");
        Role role = new Role();
        role.setCode("player");
        role.setLabel("Joueur");
        member.setRole(role);

        members.add(Json.encode(member));

        req.setMethod(Constantes.PUT);
        req.setBody(effective.encode());
        final String reply2 = sendonBus(SB_EffectiveVerticle.UPDATE, req, user.getAccount().getToken());

        JsonObject result2 = new JsonObject(reply2);
        JsonArray membersUpdate = result2.getArray("members");

        Assert.assertEquals(17, membersUpdate.size());

    }

    /**
     * test update effective, add one member
     */
    @Test
    public void updateEffectiveMissingUser() {

        User user = null;
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);

        req.setMethod(Constantes.PUT);
        req.setBody(Json.encode("toto"));
        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.UPDATE, req, ""));
        Assert.assertTrue("User not connected", resultUpdate.getString("message").contains("Please log in first"));

    }

    /**
     * Tests update effective -> Bad method request
     */
    @Test
    public void updateEffectiveBadMethodRequest() {

        populate(POPULATE_ONLY, DATA_USERS);
        User user = generateLoggedUser("54160977d5bd065a1bb1e565");
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(user);

        JsonObject resultUpdate = new JsonObject(sendonBus(SB_EffectiveVerticle.UPDATE, req, user.getAccount().getToken()));
        Assert.assertTrue("Bad method request", resultUpdate.getString("message").contains("GET is not allowed"));
    }
}
