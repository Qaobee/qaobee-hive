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
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * The type Signup test.
 *
 * @author jerome
 */
public class SignupTest extends VertxJunitSupport {

    /**
     * Existing login test.
     */
    @Test
    public void existingLoginTest() {
        User u = generateUser();
        JsonObject param = new JsonObject().putString(SignupVerticle.PARAM_LOGIN, u.getAccount().getLogin());
        JsonObject res = sendOnBus(SignupVerticle.LOGIN_EXISTS, param);
        Assert.assertTrue(res.encodePrettily(), res.getBoolean("status"));

        given().body(param.encode())
                .when().post(getURL(SignupVerticle.LOGIN_TEST))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
    }

    /**
     * Existing login caseinsensitive test.
     */
    @Test
    public void existingLoginCaseinsensitiveTest() {
        User u = generateUser();
        JsonObject param = new JsonObject().putString(SignupVerticle.PARAM_LOGIN, u.getAccount().getLogin().toUpperCase());
        JsonObject res = sendOnBus(SignupVerticle.LOGIN_EXISTS, param);
        Assert.assertTrue(res.encodePrettily(), res.getBoolean("status"));

        given().body(param.encode())
                .when().post(getURL(SignupVerticle.LOGIN_TEST))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
    }

    /**
     * Not existing login test.
     */
    @Test
    public void notExistingLoginTest() {
        generateUser();
        JsonObject param = new JsonObject().putString(SignupVerticle.PARAM_LOGIN, "blabla");
        JsonObject res = sendOnBus(SignupVerticle.LOGIN_EXISTS, param);
        Assert.assertFalse(res.encodePrettily(), res.getBoolean("status"));

        given().body(param.encode())
                .when().post(getURL(SignupVerticle.LOGIN_TEST))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(false));
    }

    /**
     * Existing login with wrong http method test.
     */
    @Test
    public void existingLoginWithWrongHttpMethodTest() {
        JsonObject param = new JsonObject().putString(SignupVerticle.PARAM_LOGIN, "blabla");
        given().body(param.encode())
                .when().get(getURL(SignupVerticle.LOGIN_TEST))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Existing login with missing param test.
     */
    @Test
    public void existingLoginWithMissingParamTest() {
        given().when().post(getURL(SignupVerticle.LOGIN_TEST))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register ok test.
     */
    @Test
    public void registerOkTest() {
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
     * Register with existing login test.
     */
    @Test
    public void registerWithExistingLoginTest() {
        User u = generateUser();
        JsonObject params = generateNewUser();
        params.getObject("account").putString("login", u.getAccount().getLogin());
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.NON_UNIQUE_LOGIN.getCode())
                .body("code", is(ExceptionCodes.NON_UNIQUE_LOGIN.toString()));
    }

    /**
     * Register with wrong http method test.
     */
    @Test
    public void registerWithWrongHttpMethodTest() {
        given().when().get(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Register with bad login format test.
     */
    @Test
    public void registerWithBadLoginFormatTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.getObject("account").putString("login", "");
        String l = "";
        for (int i = 0; i < 3; i++) {
            l += "a";
            params.getObject("account").putString("login", l);

            given().body(params.encode())
                    .when().put(getURL(SignupVerticle.REGISTER))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                    .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));
        }
        params.getObject("account").putString("login", "aaaa");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue());
    }

    /**
     * Register with empty or no login format test.
     */
    @Test
    public void registerWithEmptyOrNoLoginFormatTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.getObject("account").putString("login", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));

        params.getObject("account").removeField("login");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register with empty or no password format test.
     */
    @Test
    public void registerWithEmptyOrNoPasswordFormatTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.getObject("account").putString("passwd", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));

        params.getObject("account").removeField("passwd");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register with empty or no name format test.
     */
    @Test
    public void registerWithEmptyOrNoNameFormatTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.putString("name", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
        params.removeField("name");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register with bad name format test.
     */
    @Test
    public void registerWithBadNameFormatTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.putString("name", "");
        String l = "";
        for (int i = 0; i < 1; i++) {
            l += "a";
            params.putString("name", l);

            given().body(params.encode())
                    .when().put(getURL(SignupVerticle.REGISTER))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                    .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));
        }
        params.putString("name", "aaa");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue());
    }

    /**
     * Register with empty or no firstname format test.
     */
    @Test
    public void registerWithEmptyOrNoFirstnameFormatTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.putString("firstname", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
        params.removeField("firstname");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register with bad firstname format test.
     */
    @Test
    public void registerWithBadFirstnameFormatTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();
        params.putString("firstname", "");
        String l = "";
        for (int i = 0; i < 1; i++) {
            l += "a";
            params.putString("firstname", l);

            given().body(params.encode())
                    .when().put(getURL(SignupVerticle.REGISTER))
                    .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                    .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));
        }
        params.putString("firstname", "aaa");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.firstname", notNullValue())
                .body("person.firstname", is(params.getString("firstname")))
                .body("person._id", notNullValue());
    }

    /**
     * Register with bad email format test.
     */
    @Test
    public void registerWithBadEmailFormatTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser();

        params.getObject("contact").putString("email", "");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getObject("contact").removeField("email");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getObject("contact").putString("email", "a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getObject("contact").putString("email", "@a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getObject("contact").putString("email", "a@");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getObject("contact").putString("email", "a@a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getObject("contact").putString("email", "a@.a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getObject("contact").putString("email", "@a.a");
        given().body(params.encode())
                .when().put(getURL(SignupVerticle.REGISTER))
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body("code", is(ExceptionCodes.BAD_FORMAT.toString()));
    }

    /**
     * Account check test.
     */
    @Test
    public void accountCheckTest() {
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

        given().param("id", p.getObject("person").getString("_id"))
                .param("code", p.getObject("person").getObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
    }

    /**
     * Account check wrong or missing id test.
     */
    @Test
    public void accountCheckWrongOrMissingIdTest() {
        User u = generateUser();
        given().param("id", "haha")
                .param("code", u.getAccount().getActivationCode())
                .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(false));

        given().param("code", u.getAccount().getActivationCode())
                .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Account check wrong or missing activation code test.
     */
    @Test
    public void accountCheckWrongOrMissingActivationCodeTest() {
        User u = generateUser();
        given().param("id", u.get_id())
                .param("code", "haha")
                .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(false));

        given().param("id", u.get_id())
                .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Account check wrong http method test.
     */
    @Test
    public void accountCheckWrongHttpMethodTest() {
        given().when().post(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }


    @Test
    public void firstConnectionCheckTest() {
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
        String token = given().param(SignupVerticle.PARAM_ID, p.getObject("person").getString("_id"))
                .param(SignupVerticle.PARAM_CODE, p.getObject("person").getObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(200)
                .body("account", notNullValue())
                .body("account.token", notNullValue()).extract().path("account.token");
        given().header("token", token)
                .when().get(getURL(UserVerticle.CURRENT))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("_id", is(p.getObject("person").getString("_id")));

    }

    @Test
    public void firstConnectionCheckAlreadyActiveUserTest() {
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
        given().param("id", p.getObject("person").getString("_id"))
                .param("code", p.getObject("person").getObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.ACCOUNT_CHECK))
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
        given().param(SignupVerticle.PARAM_ID, p.getObject("person").getString("_id"))
                .param(SignupVerticle.PARAM_CODE, p.getObject("person").getObject("account").getString("activationCode"))
                .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(ExceptionCodes.BUSINESS_ERROR.getCode())
                .body("code", is(ExceptionCodes.BUSINESS_ERROR.toString()));
    }

    @Test
    public void firstConnectionCheckMissingValuesTest() {
        given().when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    @Test
    public void firstConnectionCheckWrongUserIdTest() {
        User u = generateUser();
        given().param(SignupVerticle.PARAM_ID, "blabla")
                .param(SignupVerticle.PARAM_CODE, u.getAccount().getActivationCode())
                .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body("code", is(ExceptionCodes.BAD_LOGIN.toString()));
    }

    @Test
    public void firstConnectionCheckWrongActivationCodeTest() {
        User u = generateUser();
        given().param(SignupVerticle.PARAM_ID, u.get_id())
                .param(SignupVerticle.PARAM_CODE, "blabla")
                .when().get(getURL(SignupVerticle.FIRST_CONNECTION_CHECK))
                .then().assertThat().statusCode(ExceptionCodes.BUSINESS_ERROR.getCode())
                .body("code", is(ExceptionCodes.BUSINESS_ERROR.toString()));
    }

    /**
     * @return params
     */
    private JsonObject generateNewUser() {
        final JsonObject params = new JsonObject();
        // Account
        JsonObject jsonObject = new JsonObject();
        jsonObject.putString("login", "loginTest");
        jsonObject.putString("passwd", "passwdTest");
        params.putObject("account", jsonObject);
        // Contact
        jsonObject = new JsonObject();
        jsonObject.putString("email", "prenom.nom@fai.pays");
        params.putObject("contact", jsonObject);
        // Plan
        jsonObject = new JsonObject();
        jsonObject.putString("levelPlan", "FREEMIUM");
        JsonObject objChild = new JsonObject();
        objChild.putString("_id", "ACT-HAND");
        jsonObject.putObject("activity", objChild);
        params.putObject("plan", jsonObject);
        // Nom & Prénom
        params.putString("firstname", "Prenom");
        params.putString("name", "NOM");
        //jUnit
        params.putBoolean("junit", true);
        return params;
    }

}
