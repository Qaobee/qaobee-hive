/*
 * __________________
 *   Qaobee
 * __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */

package com.qaobee.hive.test.api.sandbox.agenda;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.sandbox.agenda.SB_EventVerticle;
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
 * The type Event test.
 */
public class SB_EventTest extends VertxJunitSupport {

    /**
     * Add event test.
     */
    @Test
    public void addEventTest() {
        User user = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_LABEL, "labelValue");
        params.putString(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
        final JsonObject link = new JsonObject();
        link.putString(SB_EventVerticle.PARAM_LINK_TYPE, "championship");
        params.putObject("link", link);
        params.putNumber(SB_EventVerticle.PARAM_START_DATE, 1435701600000L);
        params.putNumber(SB_EventVerticle.PARAM_END_DATE, 1435701600100L);
        final JsonObject owner = new JsonObject();
        owner.putString(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f");
        owner.putString("effectiveId", "550b31f925da07681592db23");
        params.putObject(SB_EventVerticle.PARAM_OWNER, owner);

        String id = given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_EventVerticle.ADD))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body(SB_EventVerticle.PARAM_LABEL, is(params.getString(SB_EventVerticle.PARAM_LABEL)))
                .extract().path("_id");

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EventVerticle.PARAM_ID, id)
                .when().get(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                .body(SB_EventVerticle.PARAM_LABEL, is(params.getString(SB_EventVerticle.PARAM_LABEL)));
    }

    /**
     * Add event with non logged user test.
     */
    @Test
    public void addEventWithNonLoggedUserTest() {
        given().when().post(getURL(SB_EventVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add event with wrong http method test.
     */
    @Test
    public void addEventWithWrongHttpMethodTest() {
        given().when().get(getURL(SB_EventVerticle.ADD))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Add event with missing params test.
     */
    @Test
    public void addEventWithMissingParamsTest() {
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
        params.putString(SB_EventVerticle.PARAM_LABEL, "labelValue");
        final JsonObject link = new JsonObject();
        link.putString(SB_EventVerticle.PARAM_LINK_TYPE, "championship");
        params.putObject("link", link);
        params.putNumber(SB_EventVerticle.PARAM_START_DATE, 1435701600000L);
        params.putNumber(SB_EventVerticle.PARAM_END_DATE, 1435701600100L);
        final JsonObject owner = new JsonObject();
        owner.putString(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f");
        owner.putString("effectiveId", "550b31f925da07681592db23");
        params.putObject(SB_EventVerticle.PARAM_OWNER, owner);
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_EventVerticle.ADD).mandatoryParams());
        for (String k : params.getFieldNames()) {
            if (mandatoryParams.contains(k)) {
                JsonObject params2 = new JsonObject(params.encode());
                params2.removeField(k);
                given().header(TOKEN, u.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_EventVerticle.ADD))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            }
        }
    }

    /**
     * Gets list event test.
     */
    @Test
    public void getListEventTest() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
        params.putArray(SB_EventVerticle.PARAM_LINK_TYPE, new JsonArray(new String[]{"championship"}));
        params.putNumber(SB_EventVerticle.PARAM_START_DATE, 1435701600000L);
        params.putNumber(SB_EventVerticle.PARAM_END_DATE, 1467237600000L);
        params.putString(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "550b31f925da07681592db23");
        params.putNumber(SB_EventVerticle.PARAM_LIMIT_RESULT, 5);
        params.putArray(SB_EventVerticle.PARAM_LIST_SORTBY, new JsonArray().add(new JsonObject()
                        .putString("fieldName", SB_EventVerticle.PARAM_LABEL)
                        .putNumber("sortOrder", -1)
                )
        );

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_EventVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(4));

        params.putString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "");
        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_EventVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(4));

        params.putString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "TOTO");
        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_EventVerticle.GET_LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(0));
    }

    /**
     * Gets list event with non logged user test.
     */
    @Test
    public void getListEventWithNonLoggedUserTest() {
        given().when().post(getURL(SB_EventVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets list event with wrong http method test.
     */
    @Test
    public void getListEventWithWrongHttpMethodTest() {
        given().when().get(getURL(SB_EventVerticle.GET_LIST))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets list event with missing parameters test.
     */
    @Test
    public void getListEventWithMissingParametersTest() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(SB_EventVerticle.PARAM_ACTIVITY_ID, "ACT-HAND");
        params.putArray(SB_EventVerticle.PARAM_LINK_TYPE, new JsonArray(new String[]{"championship"}));
        params.putNumber(SB_EventVerticle.PARAM_START_DATE, 1435701600000L);
        params.putNumber(SB_EventVerticle.PARAM_END_DATE, 1467237600000L);
        params.putString(SB_EventVerticle.PARAM_OWNER_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_EventVerticle.PARAM_OWNER_EFFECTIVEID, "550b31f925da07681592db23");
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_EventVerticle.GET_LIST).mandatoryParams());
        for (String k : params.getFieldNames()) {
            if (mandatoryParams.contains(k)) {
                JsonObject params2 = new JsonObject(params.encode());
                params2.removeField(k);
                given().header(TOKEN, user.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_EventVerticle.GET_LIST))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            }
        }
    }

    /**
     * Gets event by id test.
     */
    @Test
    public void getEventByIdTest() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EventVerticle.PARAM_ID, "55847ed0d040353767a48e68")
                .when().get(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                .body(SB_EventVerticle.PARAM_LABEL, is("Amical"));
    }

    /**
     * Gets event by id with non logged user test.
     */
    @Test
    public void getEventByIdWithNonLoggedUserTest() {
        given().when().get(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets event by id with wrong http method test.
     */
    @Test
    public void getEventByIdWithWrongHttpMethodTest() {
        given().when().post(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Gets event by id with missing parameters test.
     */
    @Test
    public void getEventByIdWithMissingParametersTest() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .when().get(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Gets event by id with wrong parameters test.
     */
    @Test
    public void getEventByIdWithWrongParametersTest() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EventVerticle.PARAM_ID, "bla")
                .when().get(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Update event test.
     */
    @Test
    public void updateEventTest() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        JsonObject event = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EventVerticle.PARAM_ID, "55847ed0d040353767a48e68")
                .when().get(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                .body(SB_EventVerticle.PARAM_LABEL, is("Amical")).extract().asString());
        event.putString(SB_EventVerticle.PARAM_LABEL, "toto");
        String id = given().header(TOKEN, user.getAccount().getToken())
                .body(event.encode())
                .when().post(getURL(SB_EventVerticle.UPDATE))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body(SB_EventVerticle.PARAM_LABEL, is(event.getString(SB_EventVerticle.PARAM_LABEL)))
                .extract().path("_id");

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EventVerticle.PARAM_ID, id)
                .when().get(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                .body(SB_EventVerticle.PARAM_LABEL, is(event.getString(SB_EventVerticle.PARAM_LABEL)));
    }

    /**
     * Update event with non logged user test.
     */
    @Test
    public void updateEventWithNonLoggedUserTest() {
        given().when().post(getURL(SB_EventVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update event with wrong http method test.
     */
    @Test
    public void updateEventWithWrongHttpMethodTest() {
        given().when().get(getURL(SB_EventVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Update event with missing params test.
     */
    @Test
    public void updateEventWithMissingParamsTest() {
        populate(POPULATE_ONLY, DATA_EVENT_HAND);
        User user = generateLoggedUser();
        JsonObject event = new JsonObject(given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_EventVerticle.PARAM_ID, "55847ed0d040353767a48e68")
                .when().get(getURL(SB_EventVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(SB_EventVerticle.PARAM_LABEL, notNullValue())
                .body(SB_EventVerticle.PARAM_LABEL, is("Amical")).extract().asString());
        event.putString(SB_EventVerticle.PARAM_LABEL, "toto");
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_EventVerticle.UPDATE).mandatoryParams());
        for (String k : event.getFieldNames()) {
            if (mandatoryParams.contains(k)) {
                JsonObject params2 = new JsonObject(event.encode());
                params2.removeField(k);
                given().header(TOKEN, user.getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_EventVerticle.UPDATE))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            }
        }
    }
}
