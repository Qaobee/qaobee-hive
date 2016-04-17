package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.v1.commons.users.ProfileVerticle;
import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * Created by b3605 on 04/12/15.
 *
 * @author Xavier MARIN (b3605)
 */
public class ProfileTest extends VertxJunitSupport {

    /**
     * Update profile with common data test.
     */
    @Test
    public void updateProfileWithCommonDataTest() {
        User u = generateLoggedUser();
        u.setGender("androgyn");
        given().header(TOKEN, u.getAccount().getToken())
               .body(Json.encode(u))
               .when().post(getURL(ProfileVerticle.UPDATE))
               .then().assertThat().statusCode(200)
               .body("name", is(u.getName()))
               .body("gender", is("androgyn"));
    }

    /**
     * Update profile with password change test.
     */
    @Test
    public void updateProfileWithPasswordChangeTest() {
        User u = generateLoggedUser();
        u.getAccount().setPasswd("toto");
        given().header(TOKEN, u.getAccount().getToken())
               .body(Json.encode(u))
               .when().post(getURL(ProfileVerticle.UPDATE))
               .then().assertThat().statusCode(200)
               .body("name", is(u.getName()));
        final JsonObject params = new JsonObject();
        params.putString(UserVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        params.putString(UserVerticle.PARAM_PWD, "toto");
        given().body(params.encode())
               .when().post(getURL(UserVerticle.LOGIN))
               .then().assertThat().statusCode(200)
               .body("name", is(u.getName()));
    }

    /**
     * Generate profile pdf test.
     */
    @Test
    public void generateProfilePDFTest() {
        User u = generateLoggedUser();
        byte[] byteArray = given().header(TOKEN, u.getAccount().getToken())
                                  .get(getURL(ProfileVerticle.GENERATE_PDF))
                                  .then().assertThat().statusCode(200)
                                  .extract().asByteArray();
        Assert.assertTrue(byteArray.length > 0);
    }

    /**
     * Generate profile with wrong http method test.
     */
    @Test
    public void generateProfileWithWrongHttpMethodTest() {
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
               .post(getURL(ProfileVerticle.GENERATE_PDF))
               .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
               .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Generate profile pdf with non logged user test.
     */
    @Test
    public void generateProfilePDFWithNonLoggedUserTest() {
        given().get(getURL(ProfileVerticle.GENERATE_PDF))
               .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
               .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Generate billing pdf test.
     */
    @Test
    public void generateBillingPDFTest() {
        User u = generateLoggedUser();
        byte[] byteArray = given().header(TOKEN, u.getAccount().getToken())
                                  .param("plan_id", 0)
                                  .param("pay_id", u.getAccount().getListPlan().get(0).getShippingList().get(0).getId())
                                  .get(getURL(ProfileVerticle.GENERATE_BILL_PDF))
                                  .then().assertThat().statusCode(200)
                                  .extract().asByteArray();
        Assert.assertTrue(byteArray.length > 0);
    }

    /**
     * Generate billing pdf with non logged user test.
     */
    @Test
    public void generateBillingPDFWithNonLoggedUserTest() {
        given().param("plan_id", 0)
               .param("pay_id", "blabla")
               .get(getURL(ProfileVerticle.GENERATE_BILL_PDF))
               .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
               .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Generate billing pdf with missing data test.
     */
    @Test
    public void generateBillingPDFWithMissingDataTest() {
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
               .param("pay_id", u.getAccount().getListPlan().get(0).getShippingList().get(0).getId())
               .get(getURL(ProfileVerticle.GENERATE_BILL_PDF))
               .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
               .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Generate billing pdf with wrong data test.
     */
    @Test
    public void generateBillingPDFWithWrongDataTest() {
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
               .param("plan_id", 0)
               .param("pay_id", "blabla")
               .get(getURL(ProfileVerticle.GENERATE_BILL_PDF))
               .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
               .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }
}
