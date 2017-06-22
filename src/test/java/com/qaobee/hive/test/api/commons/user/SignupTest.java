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
package com.qaobee.hive.test.api.commons.user;

import com.qaobee.hive.api.v1.commons.users.SignupVerticle;
import com.qaobee.hive.api.v1.commons.users.UserVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * The type Signup.
 *
 * @author jerome
 */
public class SignupTest extends VertxJunitSupport {

    /**
     * Existing login.
     */
    @Test
    public void existingLogin() {
        generateUser().then(u -> {
            JsonObject param = new JsonObject().put(SignupVerticle.PARAM_LOGIN, u.getAccount().getLogin());
            sendOnBus(SignupVerticle.LOGIN_EXISTS, param).then(res -> {
                Assert.assertTrue(res.encodePrettily(), res.getBoolean("status"));
                given().body(param.encode())
                        .when().post(getURL(SignupVerticle.LOGIN_TEST))
                        .then().assertThat().statusCode(200)
                        .body("status", notNullValue())
                        .body("status", is(true));
            });
        });
    }

    /**
     * Existing login case insensitive.
     */
    @Test
    public void existingLoginCaseInsensitive() {
        generateUser().then(u -> {
            JsonObject param = new JsonObject().put(SignupVerticle.PARAM_LOGIN, u.getAccount().getLogin().toUpperCase());
            sendOnBus(SignupVerticle.LOGIN_EXISTS, param).then(res -> {
                Assert.assertTrue(res.encodePrettily(), res.getBoolean("status"));

                given().body(param.encode())
                        .when().post(getURL(SignupVerticle.LOGIN_TEST))
                        .then().assertThat().statusCode(200)
                        .body("status", notNullValue())
                        .body("status", is(true));
            });
        });
    }

    /**
     * Not existing login.
     */
    @Test
    public void notExistingLogin() {
        generateUser().then(u -> {
            JsonObject param = new JsonObject().put(SignupVerticle.PARAM_LOGIN, "blabla");
            sendOnBus(SignupVerticle.LOGIN_EXISTS, param).then(res -> {
                Assert.assertFalse(res.encodePrettily(), res.getBoolean("status"));

                given().body(param.encode())
                        .when().post(getURL(SignupVerticle.LOGIN_TEST))
                        .then().assertThat().statusCode(200)
                        .body("status", notNullValue())
                        .body("status", is(false));
            });
        });
    }

    /**
     * Existing login with wrong http method.
     */
    @Test
    public void existingLoginWithWrongHttpMethod() {
        JsonObject param = new JsonObject().put(SignupVerticle.PARAM_LOGIN, "blabla");
        given().body(param.encode())
                .when().get(getURL(SignupVerticle.LOGIN_TEST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Existing login with missing param.
     */
    @Test
    public void existingLoginWithMissingParam() {
        given().when().post(getURL(SignupVerticle.LOGIN_TEST))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register ok.
     */
    @Test
    public void registerOk() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.account.listPlan", notNullValue())
                .body("person.account.listPlan.size()", is(1))
                .body("person.account.listPlan[0].status", is("open"))
                .body("person.account.listPlan[0].activity", notNullValue())
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue());
    }

    /**
     * Register with existing login.
     */
    @Test
    public void registerWithExistingLogin() {
        generateUser().then(u -> {
            JsonObject params = generateNewUser();
            params.getJsonObject("account").put("login", u.getAccount().getLogin());
            given().body(params.encode())
                    .when().put(getURL(SignupVerticle.REGISTER))
                    .then().assertThat().statusCode(ExceptionCodes.NON_UNIQUE_LOGIN.getCode())
                    .body(CODE, is(ExceptionCodes.NON_UNIQUE_LOGIN.toString()));
        });
    }

    /**
     * Register with wrong http method.
     */
    @Test
    public void registerWithWrongHttpMethod() {
        given().when().get(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Register with bad login format.
     */
    @Test
    public void registerWithBadLoginFormat() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.getJsonObject("account").put("login", "");
        String l = "";
        for (int i = 0; i < 3; i++) {
            l += "a";
            params.getJsonObject("account").put("login", l);

            given().body(params.encode())
                    .when().put(getURL(SignupVerticle.REGISTER))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                    .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));
        }
        params.getJsonObject("account").put("login", "aaaa");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue());
    }

    /**
     * Register with empty or no login format.
     */
    @Test
    public void registerWithEmptyOrNoLoginFormat() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.getJsonObject("account").put("login", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

        params.getJsonObject("account").remove("login");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register with empty or no password format.
     */
    @Test
    public void registerWithEmptyOrNoPasswordFormat() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.getJsonObject("account").put("passwd", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

        params.getJsonObject("account").remove("passwd");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register with empty or no name format.
     */
    @Test
    public void registerWithEmptyOrNoNameFormat() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.put("name", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        params.remove("name");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register with bad name format.
     */
    @Test
    public void registerWithBadNameFormat() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.put("name", "");
        String l = "";
        for (int i = 0; i < 1; i++) {
            l += "a";
            params.put("name", l);

            given().body(params.encode())
                    .when().put(getURL(SignupVerticle.REGISTER))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                    .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));
        }
        params.put("name", "aaa");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue());
    }

    /**
     * Register with empty or no firstname format.
     */
    @Test
    public void registerWithEmptyOrNoFirstnameFormat() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.put("firstname", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        params.remove("firstname");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register with bad firstname format.
     */
    @Test
    public void registerWithBadFirstnameFormat() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.put("firstname", "");
        String l = "";
        for (int i = 0; i < 1; i++) {
            l += "a";
            params.put("firstname", l);

            given().body(params.encode())
                    .when().put(getURL(SignupVerticle.REGISTER))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                    .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));
        }
        params.put("firstname", "aaa");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.firstname", notNullValue())
                .body("person.firstname", is(params.getString("firstname")))
                .body("person._id", notNullValue());
    }

    /**
     * Register with bad email format.
     */
    @Test
    public void registerWithBadEmailFormat() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();

        params.getJsonObject("contact").put("email", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").remove("email");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "@a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "a@");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "a@a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "a@.a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "@a.a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));
    }

    /**
     * Account check.
     */
    @Test
    public void accountCheck() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        JsonObject p = new JsonObject(given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue()).extract().asString());

        given().param("id", p.getJsonObject("person").getString("_id"))
                .param(CODE, p.getJsonObject("person").getJsonObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
    }

    /**
     * Account check wrong or missing id.
     */
    @Test
    public void accountCheckWrongOrMissingId() {
        generateUser().then(u -> {
            given().param("id", "haha")
                    .param(CODE, u.getAccount().getActivationCode())
                    .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));

            given().param(CODE, u.getAccount().getActivationCode())
                    .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }


    /**
     * Account check wrong or missing activation code.
     */
    @Test
    public void accountCheckWrongOrMissingActivationCode() {
        generateUser().then(u -> {
            given().param("id", u.get_id())
                    .param(CODE, "haha")
                    .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));

            given().param("id", u.get_id())
                    .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Account check wrong http method.
     */
    @Test
    public void accountCheckWrongHttpMethod() {
        given().when().post(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }


    /**
     * First connection check.
     */
    @Test
    public void firstConnectionCheck() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        JsonObject p = new JsonObject(given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue()).extract().asString());
        String token = given().param(SignupVerticle.PARAM_ID, p.getJsonObject("person").getString("_id"))
                .param(SignupVerticle.PARAM_CODE, p.getJsonObject("person").getJsonObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(200)
                .body("account", notNullValue())
                .body("account.token", notNullValue()).extract().path("account.token");
        given().header(TOKEN, token)
                .when().get(getURL(UserVerticle.CURRENT))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(p.getJsonObject("person").getString("_id")));

    }

    /**
     * First connection check already active user.
     */
    @Test
    public void firstConnectionCheckAlreadyActiveUser() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        JsonObject p = new JsonObject(given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");
        given().param("id", p.getString("_id"))
                .param(CODE, p.getJsonObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
        given().param(SignupVerticle.PARAM_ID, p.getString("_id"))
                .param(SignupVerticle.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(ExceptionCodes.BUSINESS_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.BUSINESS_ERROR.toString()));
    }

    /**
     * First connection check missing values.
     */
    @Test
    public void firstConnectionCheckMissingValues() {
        given().when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * First connection check wrong user id.
     */
    @Test
    public void firstConnectionCheckWrongUserId() {
        generateUser().then(u -> {
            given().param(SignupVerticle.PARAM_ID, "blabla")
                    .param(SignupVerticle.PARAM_CODE, u.getAccount().getActivationCode())
                    .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body(CODE, is(ExceptionCodes.BAD_LOGIN.toString()));
        });
    }

    /**
     * First connection check wrong activation code.
     */
    @Test
    public void firstConnectionCheckWrongActivationCode() {
        generateUser().then(u -> {
            given().param(SignupVerticle.PARAM_ID, u.get_id())
                    .param(SignupVerticle.PARAM_CODE, "blabla")
                    .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                    .then().assertThat().statusCode(ExceptionCodes.BUSINESS_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.BUSINESS_ERROR.toString()));
        });
    }


    /**
     * First connection check with wrong http method.
     */
    @Test
    public void firstConnectionCheckWithWrongHttpMethod() {
        given().when().post(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Finalize signup.
     */
    @Test
    public void finalizeSignup() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY, DATA_STRUCTURE, SETTINGS_SEASONS);
        JsonObject u = generateNewUser();
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");

        JsonObject param = new JsonObject()
                .put(SignupVerticle.PARAM_USER, p)
                .put(SignupVerticle.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .put(SignupVerticle.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"))
                .put(SignupVerticle.PARAM_STRUCTURE, getStructure())
                .put(SignupVerticle.PARAM_CATEGORY_AGE, getCategoryAge());
        given().body(param.encode())
                .when().post(getURL(SignupVerticle.FINALIZE_SIGNUP))
                .then().assertThat().statusCode(200)
                .body("name", notNullValue())
                .body("name", is(p.getString("name")))
                .body("sandboxDefault", notNullValue())
                .body("account.active", is(false));
    }

    /**
     * Finalize signup with unknown structure.
     */
    @Test
    public void finalizeSignupWithUnknownStructure() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_ACTIVITY_CFG, SETTINGS_COUNTRY, DATA_STRUCTURE, SETTINGS_SEASONS);
        JsonObject u = generateNewUser();
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");


        JsonObject structure = getStructure();
        structure.remove("_id");
        JsonObject param = new JsonObject()
                .put(SignupVerticle.PARAM_USER, p)
                .put(SignupVerticle.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .put(SignupVerticle.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"))
                .put(SignupVerticle.PARAM_STRUCTURE, structure)
                .put(SignupVerticle.PARAM_CATEGORY_AGE, getCategoryAge());
        given().body(param.encode())
                .when().post(getURL(SignupVerticle.FINALIZE_SIGNUP))
                .then().assertThat().statusCode(200)
                .body("name", notNullValue())
                .body("name", is(p.getString("name")))
                .body("sandboxDefault", notNullValue())
                .body("account.active", is(false));
    }

    /**
     * Finalize signup with wrong http method.
     */
    @Test
    public void finalizeSignupWithWrongHttpMethod() {
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(getURL(SignupVerticle.FINALIZE_SIGNUP))
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
        });
    }

    /**
     * Finalize signup with blank or null params.
     */
    @Test
    public void finalizeSignupWithBlankOrNullParams() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY, DATA_STRUCTURE, SETTINGS_SEASONS);
        JsonObject u = generateNewUser();
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");
        String token = given().param(SignupVerticle.PARAM_ID, p.getString("_id"))
                .param(SignupVerticle.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(200)
                .body("account", notNullValue())
                .body("account.token", notNullValue()).extract().path("account.token");

        JsonObject param = new JsonObject();
        param.put(SignupVerticle.PARAM_CODE, p.getJsonObject("account").getString("activationCode"));
        param.put(SignupVerticle.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"));
        param.put(SignupVerticle.PARAM_STRUCTURE, getStructure());
        param.put(SignupVerticle.PARAM_CATEGORY_AGE, getCategoryAge());
        given().header(TOKEN, token)
                .body(param.encode())
                .when().post(getURL(SignupVerticle.FINALIZE_SIGNUP))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Finalize signup with wrong params.
     */
    @Test
    public void finalizeSignupWithWrongParams() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY, DATA_STRUCTURE, SETTINGS_SEASONS);
        JsonObject u = generateNewUser();
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");
        String token = given().param(SignupVerticle.PARAM_ID, p.getString("_id"))
                .param(SignupVerticle.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(200)
                .body("account", notNullValue())
                .body("account.token", notNullValue()).extract().path("account.token");

        String id = p.getString("_id");
        p.put("_id", "blabla");
        JsonObject param = new JsonObject()
                .put(SignupVerticle.PARAM_USER, p)
                .put(SignupVerticle.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .put(SignupVerticle.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"))
                .put(SignupVerticle.PARAM_STRUCTURE, getStructure())
                .put(SignupVerticle.PARAM_CATEGORY_AGE, getCategoryAge());
        given().header(TOKEN, token)
                .body(param.encode())
                .when().post(getURL(SignupVerticle.FINALIZE_SIGNUP))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body(CODE, is(ExceptionCodes.BAD_LOGIN.toString()));

        p.put("_id", id);
        param.put(SignupVerticle.PARAM_USER, p)
                .put(SignupVerticle.PARAM_CODE, "blabla");
        given().header(TOKEN, token)
                .body(param.encode())
                .when().post(getURL(SignupVerticle.FINALIZE_SIGNUP))
                .then().assertThat().statusCode(ExceptionCodes.BUSINESS_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.BUSINESS_ERROR.toString()));
    }

    /**
     * Resend register mail.
     */
    @Test
    public void resendRegisterMail() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY, DATA_STRUCTURE, SETTINGS_SEASONS);
        JsonObject u = generateNewUser();
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");

        JsonObject param = new JsonObject()
                .put(SignupVerticle.PARAM_USER, p)
                .put(SignupVerticle.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .put(SignupVerticle.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"))
                .put(SignupVerticle.PARAM_STRUCTURE, getStructure())
                .put(SignupVerticle.PARAM_CATEGORY_AGE, getCategoryAge());
        given().body(param.encode())
                .when().post(getURL(SignupVerticle.FINALIZE_SIGNUP))
                .then().assertThat().statusCode(200)
                .body("name", notNullValue())
                .body("name", is(p.getString("name")))
                .body("sandboxDefault", notNullValue())
                .body("account.active", is(false));


        given()
                .body(new JsonObject().put(SignupVerticle.PARAM_LOGIN, "loginTest").encode())
                .when().post(getURL(SignupVerticle.RESEND_MAIL))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
    }

    /**
     * Resend register mail with unknown login.
     */
    @Test
    public void resendRegisterMailWithUnknownLogin() {
        given()
                .body(new JsonObject().put(SignupVerticle.PARAM_LOGIN, "bla").encode())
                .when().post(getURL(SignupVerticle.RESEND_MAIL))
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Resend register mail with no login.
     */
    @Test
    public void resendRegisterMailWithNoLogin() {
        given()
                .body(new JsonObject().put(SignupVerticle.PARAM_LOGIN, "").encode())
                .when().post(getURL(SignupVerticle.RESEND_MAIL))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given()
                .body(new JsonObject().encode())
                .when().post(getURL(SignupVerticle.RESEND_MAIL))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Resend register mail with wrong http method.
     */
    @Test
    public void resendRegisterMailWithWrongHttpMethod() {
        given()
                .body(new JsonObject().put(SignupVerticle.PARAM_LOGIN, "loginTest").encode())
                .when().put(getURL(SignupVerticle.RESEND_MAIL))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * @return params
     */

    private JsonObject generateNewUser() {
        final JsonObject params = new JsonObject();
        // Account
        params.put("account", new JsonObject()
                .put("origin", "junit")
                .put("login", "loginTest")
                .put("passwd", "passwdTest"))
                .put("contact", new JsonObject()
                        .put("email", "prenom.nom@fai.pays"))
                .put("plan", new JsonObject()
                        .put("levelPlan", "FREEMIUM")
                        .put("activity", new JsonObject()
                                .put("_id", "ACT-HAND"))
                )
                .put("firstname", "Prenom")
                .put("name", "NOM");
        return params;
    }

    private JsonObject getStructure() {
        return new JsonObject("{\n" +
                "    \"_id\" : \"541168295971d35c1f2d1b5e\",\n" +
                "    \"label\" : \"Dunkerque Handball\",\n" +
                "    \"acronym\" : \"USDK\",\n" +
                "    \"activity\" : {\n" +
                "        \"_id\" : \"ACT-HAND\",\n" +
                "        \"code\" : \"ACT-HAND\",\n" +
                "        \"label\" : \"admin.settings.activity.handball.label\",\n" +
                "        \"enable\" : true,\n" +
                "        \"activityType\" : \"TEAM_SPORT\"\n" +
                "    },\n" +
                "    \"address\" : {\n" +
                "        \"place\" : \" Stades de Flandres, Avenue de Rosendaël\",\n" +
                "        \"zipcode\" : \"59240\",\n" +
                "        \"city\" : \" DUNKERQUE\",\n" +
                "        \"country\" : \"France\"\n" +
                "    },\n" +
                "    \"contact\" : {\n" +
                "        \"home\" : null,\n" +
                "        \"office\" : \"03 28 66 91 52\",\n" +
                "        \"cellphone\" : \"06 30 35 38 19\",\n" +
                "        \"fax\" : \"\",\n" +
                "        \"email\" : \"melanie.lefebvre@usdk.fr\"\n" +
                "    },\n" +
                "    \"country\" : {\"_id\" : \"CNTR-250-FR-FRA\" , \"codeOSCE\" : 250 , \"label\" : \"France\" , \"local\" : \"fr\"},\n" +
                "    \"avatar\" : null\n" +
                "}");
    }

    private JsonObject getCategoryAge() {
        return new JsonObject("{ \"code\" : \"sen\", \"label\" : \"Senior Gars\" }");
    }

}
