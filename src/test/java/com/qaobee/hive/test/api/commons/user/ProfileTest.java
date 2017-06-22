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

import com.qaobee.hive.api.v1.commons.users.ProfileVerticle;
import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * Created by b3605 on 04/12/15.
 *
 * @author Xavier MARIN (b3605)
 */
public class ProfileTest extends VertxJunitSupport {

    /**
     * Update profile with common data.
     */
    @Test
    public void updateProfileWithCommonData() {
        generateLoggedUser().then(u -> {
            u.setGender("androgyn");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(Json.encode(u))
                    .when().post(getURL(ProfileVerticle.UPDATE))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.getName()))
                    .body("gender", is("androgyn"));
        });
    }

    /**
     * Update profile with password change.
     */
    @Test
    public void updateProfileWithPasswordChange() {
        generateLoggedUser().then(u -> {
            u.getAccount().setPasswd("toto");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(Json.encode(u))
                    .when().post(getURL(ProfileVerticle.UPDATE))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.getName()));
            final JsonObject params = new JsonObject();
            params.put(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
            params.put(UserVerticle.PARAM_PWD, "toto");
            given().body(params.encode())
                    .when().post(getURL(UserVerticle.LOGIN))
                    .then().assertThat().statusCode(200)
                    .body("name", is(u.getName()));
        });
    }

    /**
     * Update profile non logged user.
     */
    @Test
    public void updateProfileNonLoggedUser() {
        given().when().post(getURL(ProfileVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Update profile with wrong http method.
     */
    @Test
    public void updateProfileWithWrongHttpMethod() {
        given().when().get(getURL(ProfileVerticle.UPDATE))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Update profile with missing id.
     */
    @Test
    public void updateProfileWithMissingId() {
        generateLoggedUser().then(u -> {
            u.setGender("androgyn");
            JsonObject user = new JsonObject(Json.encode(u));
            user.remove("_id");

            given().header(TOKEN, u.getAccount().getToken())
                    .body(user.encode())
                    .when().post(getURL(ProfileVerticle.UPDATE))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Update profile with wrong id.
     */
    @Test
    public void updateProfileWithWrongId() {
        generateLoggedUser().then(u -> {
            u.setGender("androgyn");
            JsonObject user = new JsonObject(Json.encode(u));
            user.put("_id", "bla");

            given().header(TOKEN, u.getAccount().getToken())
                    .body(user.encode())
                    .when().post(getURL(ProfileVerticle.UPDATE))
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
        });
    }

    /**
     * Generate profile pdf.
     */
    @Test
    public void generateProfilePDF() {
        generateLoggedUser().then(u -> {
            byte[] byteArray = given().header(TOKEN, u.getAccount().getToken())
                    .get(getURL(ProfileVerticle.GENERATE_PDF))
                    .then().assertThat().statusCode(200)
                    .extract().asByteArray();
            Assert.assertTrue(byteArray.length > 0);
        });
    }

    /**
     * Generate profile pdf with another temp dir.
     */
    @Test
    public void generateProfilePDFWithAnotherTempDir() {
        System.setProperty("OPENSHIFT_DATA_DIR", System.getProperty("java.io.tmpdir") + "/bla");
        generateLoggedUser().then(u -> {
            byte[] byteArray = given().header(TOKEN, u.getAccount().getToken())
                    .get(getURL(ProfileVerticle.GENERATE_PDF))
                    .then().assertThat().statusCode(200)
                    .extract().asByteArray();
            Assert.assertTrue(byteArray.length > 0);
        });
    }

    /**
     * Generate profile pdf with wrong datas.
     */
    @Test
    public void generateProfilePDFWithWrongDatas() {
        generateLoggedUser().then(u -> {
            u.setName("<&#\"\\-+}]à@");
            mongo.upsert(u).then(id -> {
                given().header(TOKEN, u.getAccount().getToken())
                        .get(getURL(ProfileVerticle.GENERATE_PDF))
                        .then().assertThat().statusCode(ExceptionCodes.INTERNAL_ERROR.getCode())
                        .body(CODE, is(ExceptionCodes.INTERNAL_ERROR.toString()));
            }).fail(e -> Assert.fail(e.getMessage()));
        });
    }

    /**
     * Generate profile with wrong http method.
     */
    @Test
    public void generateProfileWithWrongHttpMethod() {
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .post(getURL(ProfileVerticle.GENERATE_PDF))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
        });
    }

    /**
     * Generate profile pdf with non logged user.
     */
    @Test
    public void generateProfilePDFWithNonLoggedUser() {
        given().get(getURL(ProfileVerticle.GENERATE_PDF))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }
}
