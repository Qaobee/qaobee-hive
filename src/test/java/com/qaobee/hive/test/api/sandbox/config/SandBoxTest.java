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

import com.qaobee.hive.api.v1.commons.settings.ActivityVerticle;
import com.qaobee.hive.api.v1.sandbox.config.SB_SandBoxVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
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
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, "bla")
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.DB_NO_ROW_RETURNED.getCode())
                .body(CODE, is(ExceptionCodes.DB_NO_ROW_RETURNED.toString()));
    }

    /**
     * Gets sand box by owner with wrong user.
     */
    @Test
    public void getSandBoxByOwnerWithWrongUser() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-HAND", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.DB_NO_ROW_RETURNED.getCode())
                .body(CODE, is(ExceptionCodes.DB_NO_ROW_RETURNED.toString()));
    }

    /**
     * Gets sand box by owner with wrong activity.
     */
    @Test
    public void getSandBoxByOwnerWithWrongActivity() {
        populate(POPULATE_ONLY, DATA_USERS, DATA_SANDBOXES_HAND, SETTINGS_ACTIVITY);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_ACTIVITY_ID, (String) getActivity("ACT-FOOT", user).getField(ActivityVerticle.PARAM_ID))
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_OWNER))
                .then().assertThat().statusCode(ExceptionCodes.DB_NO_ROW_RETURNED.getCode())
                .body(CODE, is(ExceptionCodes.DB_NO_ROW_RETURNED.toString()));
    }
}
