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

import com.qaobee.hive.api.v1.sandbox.effective.SB_EffectiveVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Effective test.
 *
 * @author cke
 */
public class SB_EffectiveTest extends VertxJunitSupport {
    /**
     * Gets list members by category.
     */
    @Test
    public void getListMembersByCategory() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        String id = "558b0fc0bd2e39cdab651e21";
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EffectiveVerticle.PARAM_SANDBOXCFG_ID, id)
                .queryParam(SB_EffectiveVerticle.PARAM_CATEGORY_AGE_CODE, "sen")
                .when().get(getURL(SB_EffectiveVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("[0].members", hasSize(16));
    }

    /**
     * Gets list members by unknow category.
     */
    @Test
    public void getListMembersByUnknowCategory() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        String id = "558b0fc0bd2e39cdab651e21";
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EffectiveVerticle.PARAM_SANDBOXCFG_ID, id)
                .queryParam(SB_EffectiveVerticle.PARAM_CATEGORY_AGE_CODE, "zzz")
                .when().get(getURL(SB_EffectiveVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets list members by category with non logged user.
     */
    @Test
    public void getListMembersByCategoryWithNonLoggedUser() {
        given().when().get(getURL(SB_EffectiveVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list members by category with wrong http method.
     */
    @Test
    public void getListMembersByCategoryWithWrongHttpMethod() {
        given().when().post(getURL(SB_EffectiveVerticle.GET_LIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list members by category with missing parameters.
     */
    @Test
    public void getListMembersByCategoryWithMissingParameters() {
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .when().get(getURL(SB_EffectiveVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets list members by category with wrong parameters.
     */
    @Test
    public void getListMembersByCategoryWithWrongParameters() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EffectiveVerticle.PARAM_SANDBOXCFG_ID, "bla")
                .when().get(getURL(SB_EffectiveVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets effective.
     */
    @Test
    public void getEffective() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        String id = "550b31f925da07681592db23";
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EffectiveVerticle.PARAM_ID, id)
                .when().get(getURL(SB_EffectiveVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", is(id))
                .body("members", hasSize(16));
    }

    /**
     * Gets effective with non logged user.
     */
    @Test
    public void getEffectiveWithNonLoggedUser() {
        given().when().get(getURL(SB_EffectiveVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets effective with wrong http method.
     */
    @Test
    public void getEffectiveWithWrongHttpMethod() {
        given().when().post(getURL(SB_EffectiveVerticle.GET))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets effective with missing parameters.
     */
    @Test
    public void getEffectiveWithMissingParameters() {
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .when().get(getURL(SB_EffectiveVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets effective with wrong parameters.
     */
    @Test
    public void getEffectiveWithWrongParameters() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EffectiveVerticle.PARAM_ID, "bla")
                .when().get(getURL(SB_EffectiveVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Add effective.
     */
    @Test
    public void addEffective() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        JsonObject effective = generateEffective();
        given().header(TOKEN, user.getAccount().getToken())
                .body(effective.encode())
                .when().post(getURL(SB_EffectiveVerticle.ADD))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("members", hasSize(effective.getArray("members").size()));
    }

    /**
     * Add effective with non logged user.
     */
    @Test
    public void addEffectiveWithNonLoggedUser() {
        given().when().post(getURL(SB_EffectiveVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add effective with wrong http method.
     */
    @Test
    public void addEffectiveWithWrongHttpMethod() {
        given().when().get(getURL(SB_EffectiveVerticle.ADD))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update effective remove one member.
     */
    @Test
    public void updateEffectiveRemoveOneMember() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        String id = "550b31f925da07681592db23";
        JsonObject effective = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EffectiveVerticle.PARAM_ID, id)
                .when().get(getURL(SB_EffectiveVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", is(id))
                .body("members", hasSize(16)).extract().asString());
        JsonArray newMembers = new JsonArray();
        for (Object object : effective.getArray("members")) {
            JsonObject item = (JsonObject) object;
            if (!"550a05dadb8f8b6e2f51f4db".equals(item.getString("personId"))) {
                newMembers.add(item);
            }
        }
        effective.putArray("members", newMembers);
        given().header(TOKEN, user.getAccount().getToken())
                .body(effective.encode())
                .when().put(getURL(SB_EffectiveVerticle.UPDATE))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("members", hasSize(15));
    }

    /**
     * Update effective add one member.
     */
    @Test
    public void updateEffectiveAddOneMember() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        String id = "550b31f925da07681592db23";
        JsonObject effective = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EffectiveVerticle.PARAM_ID, id)
                .when().get(getURL(SB_EffectiveVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", is(id))
                .body("members", hasSize(16)).extract().asString());

        effective.getArray("members").add(new JsonObject()
                .putString("_id", "test")
                .putObject("role", new JsonObject()
                        .putString("code", "player")
                        .putString("label", "Joueur"))
        );
        given().header(TOKEN, user.getAccount().getToken())
                .body(effective.encode())
                .when().put(getURL(SB_EffectiveVerticle.UPDATE))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("members", hasSize(17));
    }

    /**
     * Update effective with non logged user.
     */
    @Test
    public void updateEffectiveWithNonLoggedUser() {
        given().when().put(getURL(SB_EffectiveVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update effective with wrong http method.
     */
    @Test
    public void updateEffectiveWithWrongHttpMethod() {
        given().when().get(getURL(SB_EffectiveVerticle.UPDATE))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update effective with missing parameters.
     */
    @Test
    public void updateEffectiveWithMissingParameters() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        JsonObject effective = generateEffective();
        given().header(TOKEN, user.getAccount().getToken())
                .body(effective.encode())
                .when().put(getURL(SB_EffectiveVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    private JsonObject generateEffective() {
        JsonObject effective = new JsonObject()
                .putObject("categoryAge", new JsonObject()
                        .putString("code", "u19")
                        .putString("label", "U19")
                        .putString("genre", "Masculin")
                        .putNumber("ageMax", 18)
                        .putNumber("ageMin", 17)
                        .putNumber("order", 4)
                )
                .putString("sandBoxCfgId", "blabla");
        JsonArray members = new JsonArray();
        for (int i = 0; i < 10; i++) {
            members.add(new JsonObject()
                    .putString("_id", i + "-test")
                    .putObject("role", new JsonObject()
                            .putString("code", "player")
                            .putString("label", "Joueur"))
            );
        }
        effective.putArray("members", members);
        return effective;
    }

}
