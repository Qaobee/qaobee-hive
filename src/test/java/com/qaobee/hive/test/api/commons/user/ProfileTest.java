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

package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * Created by b3605 on 04/12/15.
 *
 * @author Xavier MARIN (b3605)
 */
public class ProfileTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/users/profile");

    /**
     * Update profile with common data.
     */
    @Test
    public void updateProfileWithCommonData(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            u.result().setGender("androgyn");
            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(Json.encode(u.result()))
                    .when().post(BASE_URL + "/")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()))
                    .body("gender", is("androgyn"));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Update profile with password change.
     */
    @Test
    public void updateProfileWithPasswordChange(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            u.result().getAccount().setPasswd("toto");
            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(Json.encode(u.result()))
                    .when().post(BASE_URL + "/")
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            final JsonObject params = new JsonObject();
            params.put(UserVerticle.PARAM_LOGIN, u.result().getAccount().getLogin());
            params.put(UserVerticle.PARAM_PWD, "toto");
            given().body(params.encode())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.result().getName()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Update profile non logged user.
     */
    @Test
    public void updateProfileNonLoggedUser() {
        given().when().post(BASE_URL + "/")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update profile with wrong http method.
     */
    @Test
    public void updateProfileWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update profile with missing id.
     */
    @Test
    public void updateProfileWithMissingId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            u.result().setGender("androgyn");
            JsonObject user = new JsonObject(Json.encode(u.result()));
            user.remove("_id");

            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(user.encode())
                    .when().post(BASE_URL + "/")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Update profile with wrong id.
     */
    @Test
    public void updateProfileWithWrongId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            u.result().setGender("androgyn");
            JsonObject user = new JsonObject(Json.encode(u.result()));
            user.put("_id", "bla");

            given().header(TOKEN, u.result().getAccount().getToken())
                    .body(user.encode())
                    .when().post(BASE_URL + "/")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Generate profile pdf.
     */
    @Test
    public void generateProfilePDF(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            byte[] byteArray = given().header(TOKEN, u.result().getAccount().getToken())
                    .get(BASE_URL + "/pdf")
                    .then().assertThat().statusCode(200)
                    .extract().asByteArray();
            Assert.assertTrue(byteArray.length > 0);
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Generate profile pdf with another temp dir.
     */
    @Test
    public void generateProfilePDFWithAnotherTempDir(TestContext context) {
        Async async = context.async();
        System.setProperty("OPENSHIFT_DATA_DIR", System.getProperty("java.io.tmpdir") + "/bla");
        generateLoggedUser().setHandler(u -> {
            byte[] byteArray = given().header(TOKEN, u.result().getAccount().getToken())
                    .get(BASE_URL + "/pdf")
                    .then().assertThat().statusCode(200)
                    .extract().asByteArray();
            Assert.assertTrue(byteArray.length > 0);
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Generate profile pdf with wrong datas.
     */
    @Test
    public void generateProfilePDFWithWrongDatas(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            u.result().setName("<&#\"\\-+}]à@");
            mongo.upsert(new JsonObject(Json.encode(u.result())), DBCollections.USER, id -> {
                if (id.succeeded()) {
                    given().header(TOKEN, u.result().getAccount().getToken())
                            .get(BASE_URL + "/pdf")
                            .then().assertThat().statusCode(ExceptionCodes.INTERNAL_ERROR.getCode())
                            .body(CODE, is(ExceptionCodes.INTERNAL_ERROR.toString()));
                    async.complete();
                } else {
                    Assert.fail(id.cause().getMessage());
                }
            });
        });
        async.await(TIMEOUT);
    }

    /**
     * Generate profile with wrong http method.
     */
    @Test
    public void generateProfileWithWrongHttpMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .post(BASE_URL + "/pdf")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Generate profile pdf with non logged user.
     */
    @Test
    public void generateProfilePDFWithNonLoggedUser() {
        given().get(BASE_URL + "/pdf")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }
}
