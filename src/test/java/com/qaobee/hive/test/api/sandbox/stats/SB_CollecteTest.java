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
package com.qaobee.hive.test.api.sandbox.stats;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.sandbox.event.SB_EventVerticle;
import com.qaobee.hive.api.v1.sandbox.stats.SB_CollecteVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sb collecte test.
 *
 * @author cke
 */
public class SB_CollecteTest extends VertxJunitSupport {
    /**
     * Add collecte.
     */
    @Test
    public void addCollecte() {
        populate(POPULATE_ONLY, DATA_USER_QAOBEE);
        User user = generateLoggedUser();

        JsonObject event = new JsonObject(
                given().header(TOKEN, user.getAccount().getToken())
                        .queryParam(SB_EventVerticle.PARAM_ID, "e254897f-cf3a-48b8-bed5-a4d4664ab4a4")
                        .when().get(getURL(SB_EventVerticle.GET))
                        .then().assertThat().statusCode(200)
                        .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                        .body("activityId", is("ACT-HAND"))
                        .extract().asString());

        final JsonObject collecte = generateCollect(event);

        given().header(TOKEN, user.getAccount().getToken())
                .body(collecte.encode())
                .when().post(getURL(SB_CollecteVerticle.ADD))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("eventRef.address.city", is("Guilers"));
    }


    /**
     * Add collecte with non logged user.
     */
    @Test
    public void addCollecteWithNonLoggedUser() {
        given().when().post(getURL(SB_CollecteVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add collecte with wrong http method.
     */
    @Test
    public void addCollecteWithWrongHttpMethod() {
        given().when().get(getURL(SB_CollecteVerticle.ADD))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add collecte with missing parameters.
     */
    @Test
    public void addCollecteWithMissingParameters() {
        populate(POPULATE_ONLY, DATA_USER_QAOBEE);
        User user = generateLoggedUser();

        JsonObject event = new JsonObject(
                given().header(TOKEN, user.getAccount().getToken())
                        .queryParam(SB_EventVerticle.PARAM_ID, "e254897f-cf3a-48b8-bed5-a4d4664ab4a4")
                        .when().get(getURL(SB_EventVerticle.GET))
                        .then().assertThat().statusCode(200)
                        .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                        .body("activityId", is("ACT-HAND"))
                        .extract().asString());

        final JsonObject collecte = generateCollect(event);

        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_CollecteVerticle.ADD).mandatoryParams());
        for (String k : collecte.getFieldNames()) {
            if (mandatoryParams.contains(k)) {
                JsonObject params2 = new JsonObject(collecte.encode());
                params2.removeField(k);
                given().header(TOKEN, user.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_CollecteVerticle.ADD))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            }
        }
    }

    /**
     * Gets list collecte.
     */
    @Test
    public void getListCollecte() {
        populate(POPULATE_ONLY, DATA_USER_QAOBEE);
        User user = generateLoggedUser();
        final JsonObject params = new JsonObject()
                .putNumber(SB_CollecteVerticle.PARAM_START_DATE, 1448491800000L)
                .putNumber(SB_CollecteVerticle.PARAM_END_DATE, 1448492500000L)
                .putString(SB_CollecteVerticle.PARAM_SANDBOX_ID, "561ec20b409937a6b439d4e9")
                .putString(SB_CollecteVerticle.PARAM_EFFECTIVE_ID, "561ec4d0409937a6b439d4ea");

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_CollecteVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("eventRef.label", hasItem("Journée 10"));

        params.putString(SB_CollecteVerticle.PARAM_SANDBOX_ID, "TOTO");

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_CollecteVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));

    }

    /**
     * Gets list collecte with non logged user.
     */
    @Test
    public void getListCollecteWithNonLoggedUser() {
        given().when().post(getURL(SB_CollecteVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list collecte with wrong http method.
     */
    @Test
    public void getListCollecteWithWrongHttpMethod() {
        given().when().get(getURL(SB_CollecteVerticle.GET_LIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets list collecte with missing parameters.
     */
    @Test
    public void getListCollecteWithMissingParameters() {
        User user = generateLoggedUser();
        final JsonObject params = new JsonObject()
                .putNumber(SB_CollecteVerticle.PARAM_START_DATE, 1448491800000L)
                .putNumber(SB_CollecteVerticle.PARAM_END_DATE, 1448492500000L)
                .putString(SB_CollecteVerticle.PARAM_SANDBOX_ID, "561ec20b409937a6b439d4e9")
                .putString(SB_CollecteVerticle.PARAM_EFFECTIVE_ID, "561ec4d0409937a6b439d4ea");

        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_CollecteVerticle.GET_LIST).mandatoryParams());
        for (String k : params.getFieldNames()) {
            if (mandatoryParams.contains(k)) {
                JsonObject params2 = new JsonObject(params.encode());
                params2.removeField(k);
                given().header(TOKEN, user.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_CollecteVerticle.GET_LIST))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            }
        }
    }

    /**
     * Gets collecte by id.
     */
    @Test
    public void getCollecteById() {
        populate(POPULATE_ONLY, DATA_USER_QAOBEE);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(SB_CollecteVerticle.PARAM_ID, "565e0f0dbcda594d193e24db")
                .when().get(getURL(SB_CollecteVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("status", is("done"));
    }


    /**
     * Gets collecte by id with non logged user.
     */
    @Test
    public void getCollecteByIdWithNonLoggedUser() {
        given().when().get(getURL(SB_CollecteVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets collecte by id with wrong http method.
     */
    @Test
    public void getCollecteByIdWithWrongHttpMethod() {
        given().when().post(getURL(SB_CollecteVerticle.GET))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Gets collecte by id with missing parameters.
     */
    @Test
    public void getCollecteByIdWithMissingParameters() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .when().get(getURL(SB_CollecteVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    private JsonObject generateCollect(JsonObject event) {
        return new JsonObject()
                .putObject("eventRef", event)
                .putArray("players", new JsonArray()
                        .addString("1ce4591d-74a8-46e9-af80-d633f9344d27")
                        .addString("26baf31a-f153-41b0-9e1d-c32cb9e859dd")
                        .addString("43e62ae5-2a92-4e1a-9b9a-d1a399c096bd")
                        .addString("46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"))
                .putNumber(SB_CollecteVerticle.PARAM_START_DATE, 1435701600000L)
                .putNumber(SB_CollecteVerticle.PARAM_END_DATE, 1435701600100L)
                .putObject("observers", new JsonObject()
                        .putObject("observer", new JsonObject()
                                .putString("userId", "b50b3325-fdbd-41bf-bda4-81c827982001")
                                .putArray("indicators", new JsonArray().addString("all"))
                        )
                );
    }
}
