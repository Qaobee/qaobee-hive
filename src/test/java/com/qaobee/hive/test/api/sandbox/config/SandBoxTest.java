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
package com.qaobee.hive.test.api.sandbox.config;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.api.v1.sandbox.config.SB_SandBoxVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sand box test.
 *
 * @author cke
 */
public class SandBoxTest extends VertxJunitSupport {

    /**
     * Retrieve sand box by his owner.
     */
    @Test
    public void getSandBoxByOwner() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-HAND", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(200)
                .body("owner", notNullValue())
                .body("owner", is(user.get_id()));
    }

    /**
     * Gets sand box by owner with non logged user.
     */
    @Test
    public void getSandBoxByOwnerWithNonLoggedUser() {
        given().when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets sand box by owner with wrong http method.
     */
    @Test
    public void getSandBoxByOwnerWithWrongHttpMethod() {
        given().when().post(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets sand box by owner with missing parameters.
     */
    @Test
    public void getSandBoxByOwnerWithMissingParameters() {
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets sand box by owner with wrong parameters.
     */
    @Test
    public void getSandBoxByOwnerWithWrongParameters() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, "bla")
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets sand box by owner with wrong user.
     */
    @Test
    public void getSandBoxByOwnerWithWrongUser() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-HAND", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets sand box by owner with wrong activity.
     */
    @Test
    public void getSandBoxByOwnerWithWrongActivity() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-FOOT", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Gets sand box list by owner.
     */
    @Test
    public void getSandBoxListByOwner() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_OWNER_ID, user.get_id())
                .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("owner", hasItem(user.get_id()));

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_OWNER_ID, "")
                .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("owner", hasItem(user.get_id()));

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_OWNER_ID, "bla")
                .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));

        given().header(TOKEN, user.getAccount().getToken())
                .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("owner", hasItem(user.get_id()));
    }

    /**
     * Gets sand box list by owner with non logged user.
     */
    @Test
    public void getSandBoxListByOwnerWithNonLoggedUser() {
        given().when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets sand box list by owner with wrong http method.
     */
    @Test
    public void getSandBoxListByOwnerWithWrongHttpMethod() {
        given().when().post(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets sand box list by owner with wrong parameters.
     */
    @Test
    public void getSandBoxListByOwnerWithWrongParameters() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_OWNER_ID, user.get_id())
                .when().get(getURL(SB_SandBoxVerticle.GET_LIST_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Add sand box.
     */
    @Test
    public void addSandBox() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser();
        JsonObject params = new JsonObject()
                .putString(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, "ACT-WATER-PONEY")
                .putString(SB_SandBoxVerticle.PARAM_USER_ID, user.get_id());
        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().put(getURL(SB_SandBoxVerticle.ADD))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("owner", is(user.get_id()));
    }

    /**
     * Add sand box with non logged user.
     */
    @Test
    public void addSandBoxWithNonLoggedUser() {
        given().when().put(getURL(SB_SandBoxVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add sand box with wrong http method.
     */
    @Test
    public void addSandBoxWithWrongHttpMethod() {
        given().when().post(getURL(SB_SandBoxVerticle.ADD))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add sand box with missing params.
     */
    @Test
    public void addSandBoxWithMissingParams() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser();
        JsonObject params = new JsonObject()
                .putString(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, "ACT-WATER-PONEY")
                .putString(SB_SandBoxVerticle.PARAM_USER_ID, user.get_id());

        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_SandBoxVerticle.ADD).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params2.encode())
                    .when().put(getURL(SB_SandBoxVerticle.ADD))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    //- --------------

    /**
     * Update sand box.
     */
    @Test
    public void updateSandBox() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        JsonObject sb = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-HAND", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("owner", notNullValue())
                .body("owner", is(user.get_id()))
                .extract().asString());

        JsonObject params = new JsonObject()
                .putString(SB_SandBoxVerticle.PARAM_ID, sb.getString("_id"))
                .putString(SB_SandBoxVerticle.PARAM_SB_CFG_ID, "123456");
        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_SandBoxVerticle.UPDATE))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body(SB_SandBoxVerticle.PARAM_SB_CFG_ID, is("123456"));
    }

    /**
     * Update sand box with wrong id.
     */
    @Test
    public void updateSandBoxWithWrongId() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        JsonObject params = new JsonObject()
                .putString(SB_SandBoxVerticle.PARAM_ID, "bla")
                .putString(SB_SandBoxVerticle.PARAM_SB_CFG_ID, "123456");
        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_SandBoxVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Update sand box with non logged user.
     */
    @Test
    public void updateSandBoxWithNonLoggedUser() {
        given().when().post(getURL(SB_SandBoxVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update sand box with wrong http method.
     */
    @Test
    public void updateSandBoxWithWrongHttpMethod() {
        given().when().get(getURL(SB_SandBoxVerticle.UPDATE))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update sand box with missing params.
     */
    @Test
    public void updateSandBoxWithMissingParams() {
        User user = generateLoggedUser();
        JsonObject params = new JsonObject()
                .putString(SB_SandBoxVerticle.PARAM_ID, "123456")
                .putString(SB_SandBoxVerticle.PARAM_SB_CFG_ID, "123456");

        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_SandBoxVerticle.UPDATE).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params2.encode())
                    .when().post(getURL(SB_SandBoxVerticle.UPDATE))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Update sand box with wrong params.
     */
    @Test
    public void updateSandBoxWithWrongParams() {
        User user = generateLoggedUser();
        JsonObject params = new JsonObject()
                .putString(SB_SandBoxVerticle.PARAM_ID, "123456")
                .putString(SB_SandBoxVerticle.PARAM_SB_CFG_ID, "123456");
        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_SandBoxVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }
}
