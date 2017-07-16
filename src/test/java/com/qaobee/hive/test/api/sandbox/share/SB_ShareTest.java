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

import com.qaobee.hive.api.MainAPI;
import com.qaobee.hive.api.v1.sandbox.config.SB_SandBoxVerticle;
import com.qaobee.hive.api.v1.sandbox.share.SB_ShareVerticle;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Sb share testBodyParams.
 */
public class SB_ShareTest extends VertxJunitSupport {

    /**
     * invite a member to a sandbox.
     */
    @Test
    public void inviteMemberToSandbox(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            generateLoggedUser().setHandler(user2 -> {
                final JsonObject params = new JsonObject()
                        .put(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                        .put(SB_ShareVerticle.PARAM_USER_EMAIL, user2.result().getContact().getEmail())
                        .put(SB_ShareVerticle.PARAM_ROLE_CODE, "member");

                given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params.encode())
                        .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .extract().path("_id");

                given().header(TOKEN, user.result().getAccount().getToken())
                        .queryParam(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                        .queryParam(SB_ShareVerticle.PARAM_INVITATION_STATUS, "ALL")
                        .when().get(getURL(SB_ShareVerticle.GET_LIST_INVITATION_TO_SANDBOX))
                        .then().assertThat().statusCode(200)
                        .body("", hasSize(1))
                        .body("_id", notNullValue())
                        .body("findAll { it.status == 'waiting' }.userId", hasItem(user2.result().get_id()));
                async.complete();
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Invite member to sandbox with wrong email.
     */
    @Test
    public void inviteMemberToSandboxWithWrongEmail(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, DATA_SANDBOXES_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            final JsonObject params = new JsonObject()
                    .put(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                    .put(SB_ShareVerticle.PARAM_USER_EMAIL, "bla")
                    .put(SB_ShareVerticle.PARAM_ROLE_CODE, "member");

            given().header(TOKEN, user.result().getAccount().getToken())
                    .body(params.encode())
                    .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                    .then().assertThat().statusCode(200)
                    .body("_id", notNullValue())
                    .body("userId", nullValue())
                    .extract();
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * acceptation invitation sandbox.
     */
    @Test
    public void acceptationInvitationToSandbox(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS, DATA_USER_QAOBEE);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            generateLoggedUser().setHandler(user2 -> {
                final JsonObject params = new JsonObject()
                        .put(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                        .put(SB_ShareVerticle.PARAM_USER_EMAIL, user2.result().getContact().getEmail())
                        .put(SB_ShareVerticle.PARAM_ROLE_CODE, "member");

                String invitationId = given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params.encode())
                        .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .extract().path("_id");

                final JsonObject params2 = new JsonObject()
                        .put(SB_ShareVerticle.PARAM_INVITATION_ID, invitationId)
                        .put(SB_ShareVerticle.PARAM_USERID, "a0ef9c2d-6864-4a20-84ba-b66a666d2bf4")
                        .put(SB_ShareVerticle.PARAM_ANSWER_INVITATION, "accepted");

                given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("status", is("accepted"));

                given().header(TOKEN, user.result().getAccount().getToken())
                        .queryParam(SB_SandBoxVerticle.PARAM_ID, "558b0efebd2e39cdab651e1f")
                        .when().get(getURL(SB_SandBoxVerticle.GET_BY_ID))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("members", hasSize(3))
                        .body("members.findAll{ it.status = 'activated' }.personId", hasItem("a0ef9c2d-6864-4a20-84ba-b66a666d2bf4"));
                async.complete();
            });
        });
        async.await(TIMEOUT);
    }

    @Test
    public void refuseInvitationToSandbox(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, SETTINGS_ACTIVITY, DATA_SANDBOXES_HAND, SETTINGS_SEASONS);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            generateLoggedUser().setHandler(user2 -> {
                final JsonObject params = new JsonObject()
                        .put(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                        .put(SB_ShareVerticle.PARAM_USER_EMAIL, user2.result().getContact().getEmail())
                        .put(SB_ShareVerticle.PARAM_ROLE_CODE, "member");

                String invitationId = given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params.encode())
                        .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .extract().path("_id");

                final JsonObject params2 = new JsonObject()
                        .put(SB_ShareVerticle.PARAM_INVITATION_ID, invitationId)
                        .put(SB_ShareVerticle.PARAM_USERID, user2.result().get_id())
                        .put(SB_ShareVerticle.PARAM_ANSWER_INVITATION, "refused");

                given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("status", is("refused"));

                given().header(TOKEN, user.result().getAccount().getToken())
                        .queryParam(SB_SandBoxVerticle.PARAM_ID, "558b0efebd2e39cdab651e1f")
                        .when().get(getURL(SB_SandBoxVerticle.GET_BY_ID))
                        .then().assertThat().statusCode(200)
                        .body("_id", notNullValue())
                        .body("members", hasSize(2))
                        .body("members.personId", not(hasItem(user2.result().get_id())));
                async.complete();
            });
        });
        async.await(TIMEOUT);
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
    public void acceptationInvitationToSandboxWithMissingParams(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, DATA_SANDBOXES_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {

            final JsonObject params = new JsonObject()
                    .put(SB_ShareVerticle.PARAM_INVITATION_ID, "12345")
                    .put(SB_ShareVerticle.PARAM_USERID, "12345")
                    .put(SB_ShareVerticle.PARAM_ANSWER_INVITATION, "accepted");

            List<String> mandatoryParams = Arrays.asList(MainAPI.getRules().get(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX).mandatoryParams());
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, user.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
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
    public void inviteMemberToSandboxWithMissingParams(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            final JsonObject params = new JsonObject()
                    .put(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                    .put(SB_ShareVerticle.PARAM_USER_EMAIL, "bla@bla.com")
                    .put(SB_ShareVerticle.PARAM_ROLE_CODE, "acoach");
            List<String> mandatoryParams = Arrays.asList(MainAPI.getRules().get(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX).mandatoryParams());
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, u.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Remove a member to a sandbox.
     */
    @Test
    public void desactivateMemberToSandbox(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY_CFG, DATA_USER_QAOBEE, DATA_SANDBOXES_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").setHandler(user -> {
            generateUser().setHandler(user2 -> {
                user2.result().getContact().setEmail("bla.bla@bla.bla");
                mongo.upsert(new JsonObject(Json.encode(user2)), DBCollections.USER, id -> {
                    if (id.succeeded()) {
                        user2.result().set_id(id.result());
                        String sandboxId = "558b0efebd2e39cdab651e1f";

                        final JsonObject params = new JsonObject()
                                .put(SB_ShareVerticle.PARAM_SANBOXID, sandboxId)
                                .put(SB_ShareVerticle.PARAM_USER_EMAIL, user2.result().getContact().getEmail())
                                .put(SB_ShareVerticle.PARAM_ROLE_CODE, "acoach");

                        String invitationId = given().header(TOKEN, user.result().getAccount().getToken())
                                .body(params.encode())
                                .when().post(getURL(SB_ShareVerticle.INVITE_MEMBER_TO_SANDBOX))
                                .then().assertThat().statusCode(200)
                                .body("_id", notNullValue())
                                .body("sandboxId", is(sandboxId))
                                .extract().path("_id");

                        final JsonObject params2 = new JsonObject()
                                .put(SB_ShareVerticle.PARAM_INVITATION_ID, invitationId)
                                .put(SB_ShareVerticle.PARAM_USERID, user2.result().get_id())
                                .put(SB_ShareVerticle.PARAM_ANSWER_INVITATION, "accepted");

                        given().header(TOKEN, user.result().getAccount().getToken())
                                .body(params2.encode())
                                .when().post(getURL(SB_ShareVerticle.CONFIRM_INVITATION_TO_SANDBOX))
                                .then().assertThat().statusCode(200)
                                .body("_id", notNullValue())
                                .body("status", is("accepted"));

                        given().header(TOKEN, user.result().getAccount().getToken())
                                .queryParam(SB_SandBoxVerticle.PARAM_ID, sandboxId)
                                .when().get(getURL(SB_SandBoxVerticle.GET_BY_ID))
                                .then().assertThat().statusCode(200)
                                .body("_id", notNullValue())
                                .body("members", hasSize(3));

                        final JsonObject params3 = new JsonObject()
                                .put(SB_ShareVerticle.PARAM_USERID, user2.result().get_id())
                                .put(SB_ShareVerticle.PARAM_SANBOXID, sandboxId);

                        given().header(TOKEN, user.result().getAccount().getToken())
                                .body(params3.encode())
                                .when().post(getURL(SB_ShareVerticle.DESACTIVATE_MEMBER_TO_SANDBOX))
                                .then().assertThat().statusCode(200)
                                .body("_id", notNullValue())
                                .body("members", hasSize(3))
                                .body("members.findAll{ it.status == 'desactivated' }.personId", hasItem(user2.result().get_id()));
                        async.complete();
                    } else {
                        Assert.fail(id.cause().getMessage());
                    }
                });
            });
        });
        async.await(TIMEOUT);
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
    public void desactivateMemberToSandboxWithMissingParams(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            final JsonObject params = new JsonObject()
                    .put(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f")
                    .put(SB_ShareVerticle.PARAM_USERID, "12345");
            List<String> mandatoryParams = Arrays.asList(MainAPI.getRules().get(SB_ShareVerticle.DESACTIVATE_MEMBER_TO_SANDBOX).mandatoryParams());
            params.fieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
                JsonObject params2 = new JsonObject(params.encode());
                params2.remove(k);
                given().header(TOKEN, u.result().getAccount().getToken())
                        .body(params2.encode())
                        .when().post(getURL(SB_ShareVerticle.DESACTIVATE_MEMBER_TO_SANDBOX))
                        .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                        .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            });
            async.complete();
        });
        async.await(TIMEOUT);
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
