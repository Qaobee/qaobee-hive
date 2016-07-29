/*
 *  __________________
 *  Qaobee
 *  __________________
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

package com.qaobee.hive.test.api.sandbox.share;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.sandbox.config.SB_SandBoxVerticle;
import com.qaobee.hive.api.v1.sandbox.share.SB_ShareVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sb share test.
 */
public class SB_ShareTest extends VertxJunitSupport {

    /**
     * invite a member to a sandbox.
     */
    @Test
    public void inviteMemberToSandbox() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, DATA_SANDBOXES_HAND);
        User user = loggedUser("5509ef1fdb8f8b6e2f51f4ce");
        User user2 = generateLoggedUser("a0ef9c2d-6864-4a20-84ba-b66a666d2bf4");
        final JsonObject params = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                .putString(SB_ShareVerticle.PARAM_USER_EMAIL, user2.getContact().getEmail())
                .putString(SB_ShareVerticle.PARAM_ROLE_CODE, "member");

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .extract().path("_id");

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                .queryParam(SB_ShareVerticle.PARAM_INVITATION_STATUS, "ALL")
                .when().get(getURL(SB_ShareVerticle.GET_LIST_INVITATION_TO_SANDBOX))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("_id", notNullValue())
                .body("findAll { it.status == 'waiting' }.userId", hasItem(user2.get_id()));
    }

    /**
     * Invite member to sandbox with wrong email.
     */
    @Test
    public void inviteMemberToSandboxWithWrongEmail() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, DATA_SANDBOXES_HAND);
        User user = loggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final JsonObject params = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                .putString(SB_ShareVerticle.PARAM_USER_EMAIL, "bla")
                .putString(SB_ShareVerticle.PARAM_ROLE_CODE, "member");

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                .then().assertThat().statusCode(ExceptionCodes.INVALID_PARAMETER.getCode())
                .body(CODE, is(ExceptionCodes.INVALID_PARAMETER.toString()));
    }

    /**
     * acceptation invitation sandbox.
     */
    @Test
    public void acceptationInvitationToSandbox() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, DATA_SANDBOXES_HAND);
        User user = loggedUser("5509ef1fdb8f8b6e2f51f4ce");
        User user2 = generateLoggedUser("a0ef9c2d-6864-4a20-84ba-b66a666d2bf4");
        final JsonObject params = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                .putString(SB_ShareVerticle.PARAM_USER_EMAIL, user2.getContact().getEmail())
                .putString(SB_ShareVerticle.PARAM_ROLE_CODE, "member");

        String invitationId = given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .extract().path("_id");
        
        final JsonObject params2 = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_INVITATION_ID, invitationId)
                .putString(SB_ShareVerticle.PARAM_USERID, "a0ef9c2d-6864-4a20-84ba-b66a666d2bf4")
                .putString(SB_ShareVerticle.PARAM_ANSWER_INVITATION, "accepted");
        
        given().header(TOKEN, user.getAccount().getToken())
                .body(params2.encode())
                .when().post(getURL(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("status",is("accepted"));
        
        given().header(TOKEN, user.getAccount().getToken())
		        .queryParam(SB_SandBoxVerticle.PARAM_ID, "558b0efebd2e39cdab651e1f")
		        .when().get(getURL(SB_SandBoxVerticle.GET_BY_ID))
		        .then().assertThat().statusCode(200)
		        .body("_id", notNullValue())
		        .body("members", hasSize(3))
                .body("members.findAll{ it.status = 'activated' }.personId", hasItem("a0ef9c2d-6864-4a20-84ba-b66a666d2bf4"));
    }

    /**
     * Acceptation invitation to sandbox with not logged user.
     */
    @Test
    public void acceptationInvitationToSandboxWithNotLoggedUser() {
        given().body(new JsonObject())
                .when().post(getURL(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Acceptation invitation to sandbox with wrong http method.
     */
    @Test
    public void acceptationInvitationToSandboxWithWrongHTTPMethod() {
        given().when().get(getURL(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Acceptation invitation to sandbox with missing params.
     */
    @Test
    public void acceptationInvitationToSandboxWithMissingParams() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, DATA_SANDBOXES_HAND);
        User user = loggedUser("5509ef1fdb8f8b6e2f51f4ce");

        final JsonObject params = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_INVITATION_ID, "12345")
                .putString(SB_ShareVerticle.PARAM_USERID, "12345")
                .putString(SB_ShareVerticle.PARAM_ANSWER_INVITATION, "accepted");

        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, user.getAccount().getToken())
                    .body(params2.encode())
                    .when().post(getURL(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Add a member to a sandbox with non logged user.
     */
    @Test
    public void inviteMemberToSandboxWithNonLoggedUser() {
        given().when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add a member to a sandbox with wrong http method.
     */
    @Test
    public void inviteMemberToSandboxWithWrongHttpMethod() {
        given().when().get(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add a member to a sandbox with missing params.
     */
    @Test
    public void inviteMemberToSandboxWithMissingParams() {
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                .putString(SB_ShareVerticle.PARAM_USER_EMAIL, "bla@bla.com")
                .putString(SB_ShareVerticle.PARAM_ROLE_CODE, "acoach");
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params2.encode())
                    .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Remove a member to a sandbox.
     */
    @Test
    public void desactivateMemberToSandbox() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, DATA_USER_QAOBEE, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        User user2 = generateUser();
        user2.getContact().setEmail("bla.bla@bla.bla");
        user2.set_id(mongo.update(new JsonObject(Json.encode(user2)), DBCollections.USER));
        String sandboxId = "558b0efebd2e39cdab651e1f";

        final JsonObject params = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_SANBOXID, sandboxId)
                .putString(SB_ShareVerticle.PARAM_USER_EMAIL, user2.getContact().getEmail())
                .putString(SB_ShareVerticle.PARAM_ROLE_CODE, "acoach");

        String invitationId = given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("sandboxId", is(sandboxId))
                .extract().path("_id");

        final JsonObject params2 = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_INVITATION_ID, invitationId)
                .putString(SB_ShareVerticle.PARAM_USERID, user2.get_id())
                .putString(SB_ShareVerticle.PARAM_ANSWER_INVITATION, "accepted");

        given().header(TOKEN, user.getAccount().getToken())
                .body(params2.encode())
                .when().post(getURL(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("status", is("accepted"));

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_SandBoxVerticle.PARAM_ID, sandboxId)
                .when().get(getURL(SB_SandBoxVerticle.GET_BY_ID))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("members", hasSize(3));

        final JsonObject params3 = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_USERID, user2.get_id())
                .putString(SB_ShareVerticle.PARAM_SANBOXID, sandboxId);

        given().header(TOKEN, user.getAccount().getToken())
                .body(params3.encode())
                .when().post(getURL(SB_ShareVerticle.DESACTIVATE_MEMBER_TO_SANDBOX))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("members", hasSize(3))
                .body("members.findAll{ it.status == 'desactivated' }.personId", hasItem(user2.get_id()));
    }

    /**
     * Remove a member to a sandbox with non logged user.
     */
    @Test
    public void desactivateMemberToSandboxWithNonLoggedUser() {
        given().when().post(getURL(SB_ShareVerticle.DESACTIVATE_MEMBER_TO_SANDBOX))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Remove a member to a sandbox with wrong http method.
     */
    @Test
    public void desactivateMemberToSandboxWithWrongHttpMethod() {
        given().when().get(getURL(SB_ShareVerticle.DESACTIVATE_MEMBER_TO_SANDBOX))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Remove a member to a sandbox with missing params.
     */
    @Test
    public void desactivateMemberToSandboxWithMissingParams() {
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject()
                .putString(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                .putString(SB_ShareVerticle.PARAM_USERID, "12345");
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_ShareVerticle.DESACTIVATE_MEMBER_TO_SANDBOX).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params2.encode())
                    .when().post(getURL(SB_ShareVerticle.DESACTIVATE_MEMBER_TO_SANDBOX))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Gets shared sandboxes with non logged user.
     */
    @Test
    public void getSharedSandboxesWithNonLoggedUser() {
        given().when().get(getURL(SB_ShareVerticle.GET_SANDBOX_SHARING_LIST))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets shared sandboxes with wrong http method.
     */
    @Test
    public void getSharedSandboxesWithWrongHttpMethod() {
        given().when().post(getURL(SB_ShareVerticle.GET_SANDBOX_SHARING_LIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }
}
