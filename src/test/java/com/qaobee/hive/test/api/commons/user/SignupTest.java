/* ************************************************************************
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

import com.qaobee.hive.api.v1.commons.users.SignupRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * The type Signup.
 *
 * @author jerome
 */
public class SignupTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/users/signup");
    private static final String USER_BASE_URL = getBaseURL("/commons/users/user");

    /**
     * Existing login.
     */
    @Test
    public void existingLogin(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject param = new JsonObject().put(SignupRoute.PARAM_LOGIN, u.result().getAccount().getLogin());
            given().body(param.encode())
                    .when().post(BASE_URL + "/logintest")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Existing login case insensitive.
     */
    @Test
    public void existingLoginCaseInsensitive(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject param = new JsonObject().put(SignupRoute.PARAM_LOGIN, u.result().getAccount().getLogin().toUpperCase());
            given().body(param.encode())
                    .when().post(BASE_URL + "/logintest")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(true));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Not existing login.
     */
    @Test
    public void notExistingLogin(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject param = new JsonObject().put(SignupRoute.PARAM_LOGIN, "blabla");
            given().body(param.encode())
                    .when().post(BASE_URL + "/logintest")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Existing login with wrong http method.
     */
    @Test
    public void existingLoginWithWrongHttpMethod() {
        JsonObject param = new JsonObject().put(SignupRoute.PARAM_LOGIN, "blabla");
        given().body(param.encode())
                .when().get(BASE_URL + "/logintest")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Existing login with missing param.
     */
    @Test
    public void existingLoginWithMissingParam() {
        given().when().post(BASE_URL + "/logintest")
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
        params.put("captcha", "fake");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
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
    public void registerWithExistingLogin(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            JsonObject params = generateNewUser();
            params.put("captcha", "fake");
            params.getJsonObject("account").put("login", u.result().getAccount().getLogin());
            given().body(params.encode())
                    .when().put(BASE_URL + "/register")
                    .then().assertThat().statusCode(ExceptionCodes.NON_UNIQUE_LOGIN.getCode())
                    .body(CODE, is(ExceptionCodes.NON_UNIQUE_LOGIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Register with wrong http method.
     */
    @Test
    public void registerWithWrongHttpMethod() {
        given().when().get(BASE_URL + "/register")
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
            params.put("captcha", "fake");

            given().body(params.encode())
                    .when().put(BASE_URL + "/register")
                    .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                    .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));
        }
        params.getJsonObject("account").put("login", "aaaa");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
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
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

        params.getJsonObject("account").remove("login");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
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
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

        params.getJsonObject("account").remove("passwd");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
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
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        params.remove("name");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Register with bad name format.
     */
    @Test
    public void registerWithBadNameFormat() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY);
        JsonObject params = generateNewUser()
                .put("captcha", "fake")
                .put("name", "");
        String l = "";
        for (int i = 0; i < 1; i++) {
            l += "a";
            params.put("name", l);

            given().body(params.encode())
                    .when().put(BASE_URL + "/register")
                    .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                    .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));
        }
        params.put("name", "aaa");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
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
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        params.remove("firstname");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
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
        params.put("firstname", "").put("captcha", "fake");
        String l = "";
        for (int i = 0; i < 1; i++) {
            l += "a";
            params.put("firstname", l);

            given().body(params.encode())
                    .when().put(BASE_URL + "/register")
                    .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                    .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));
        }
        params.put("firstname", "aaa");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
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
        params.put("captcha", "fake");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").remove("email");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "a");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "@a");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "a@");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "a@a");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "a@.a");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(ExceptionCodes.BAD_FORMAT.getCode())
                .body(CODE, is(ExceptionCodes.BAD_FORMAT.toString()));

        params.getJsonObject("contact").put("email", "@a.a");
        given().body(params.encode())
                .when().put(BASE_URL + "/register")
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
        params.put("captcha", "fake");
        JsonObject p = new JsonObject(given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue()).extract().asString());

        given().param("id", p.getJsonObject("person").getString("_id"))
                .param(CODE, p.getJsonObject("person").getJsonObject("account").getString("activationCode"))
                .when().get(BASE_URL + "/accountcheck")
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
    }

    /**
     * Account check wrong or missing id.
     */
    @Test
    public void accountCheckWrongOrMissingId(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            given().param("id", "haha")
                    .param(CODE, u.result().getAccount().getActivationCode())
                    .when().get(BASE_URL + "/accountcheck")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));

            given().param(CODE, u.result().getAccount().getActivationCode())
                    .when().get(BASE_URL + "/accountcheck")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }


    /**
     * Account check wrong or missing activation code.
     */
    @Test
    public void accountCheckWrongOrMissingActivationCode(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            given().param("id", u.result().get_id())
                    .param(CODE, "haha")
                    .when().get(BASE_URL + "/accountcheck")
                    .then().assertThat().statusCode(200)
                    .body("status", notNullValue())
                    .body("status", is(false));

            given().param("id", u.result().get_id())
                    .when().get(BASE_URL + "/accountcheck")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Account check wrong http method.
     */
    @Test
    public void accountCheckWrongHttpMethod() {
        given().when().post(BASE_URL + "/accountcheck")
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
        params.put("captcha", "fake");
        JsonObject p = new JsonObject(given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue()).extract().asString());
        String token = given().param(SignupRoute.PARAM_ID, p.getJsonObject("person").getString("_id"))
                .param(SignupRoute.PARAM_CODE, p.getJsonObject("person").getJsonObject("account").getString("activationCode"))
                .when().get(BASE_URL + "/firstconnectioncheck")
                .then().assertThat().statusCode(200)
                .body("account", notNullValue())
                .body("account.token", notNullValue()).extract().path("account.token");
        given().header(TOKEN, token)
                .when().get(USER_BASE_URL + "/current")
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
        params.put("captcha", "fake");
        JsonObject p = new JsonObject(given().body(params.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(params.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");
        given().param("id", p.getString("_id"))
                .param(CODE, p.getJsonObject("account").getString("activationCode"))
                .when().get(BASE_URL + "/accountcheck")
                .then().assertThat().statusCode(200)
                .body("status", notNullValue())
                .body("status", is(true));
        given().param(SignupRoute.PARAM_ID, p.getString("_id"))
                .param(SignupRoute.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .when().get(BASE_URL + "/firstconnectioncheck")
                .then().assertThat().statusCode(ExceptionCodes.BUSINESS_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.BUSINESS_ERROR.toString()));
    }

    /**
     * First connection check missing values.
     */
    @Test
    public void firstConnectionCheckMissingValues() {
        given().when().get(BASE_URL + "/firstconnectioncheck")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * First connection check wrong user id.
     */
    @Test
    public void firstConnectionCheckWrongUserId(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            given().param(SignupRoute.PARAM_ID, "blabla")
                    .param(SignupRoute.PARAM_CODE, u.result().getAccount().getActivationCode())
                    .when().get(BASE_URL + "/firstconnectioncheck")
                    .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                    .body(CODE, is(ExceptionCodes.BAD_LOGIN.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * First connection check wrong activation code.
     */
    @Test
    public void firstConnectionCheckWrongActivationCode(TestContext context) {
        Async async = context.async();
        generateUser().setHandler(u -> {
            given().param(SignupRoute.PARAM_ID, u.result().get_id())
                    .param(SignupRoute.PARAM_CODE, "blabla")
                    .when().get(BASE_URL + "/firstconnectioncheck")
                    .then().assertThat().statusCode(ExceptionCodes.BUSINESS_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.BUSINESS_ERROR.toString()));
            async.complete();
        });
        async.await(TIMEOUT);
    }


    /**
     * First connection check with wrong http method.
     */
    @Test
    public void firstConnectionCheckWithWrongHttpMethod() {
        given().when().post(BASE_URL + "/firstconnectioncheck")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Finalize signup.
     */
    @Test
    public void finalizeSignup() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_ACTIVITY_CFG, SETTINGS_COUNTRY, DATA_STRUCTURE, SETTINGS_SEASONS);
        JsonObject u = generateNewUser();
        u.put("captcha", "fake");
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");

        JsonObject param = new JsonObject()
                .put(SignupRoute.PARAM_USER, p)
                .put(SignupRoute.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .put(SignupRoute.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"))
                .put(SignupRoute.PARAM_STRUCTURE, getStructure())
                .put(SignupRoute.PARAM_CATEGORY_AGE, getCategoryAge());
        given().body(param.encode())
                .when().post(BASE_URL + "/finalize")
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
        u.put("captcha", "fake");
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");


        JsonObject structure = getStructure();
        structure.remove("_id");
        JsonObject param = new JsonObject()
                .put(SignupRoute.PARAM_USER, p)
                .put(SignupRoute.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .put(SignupRoute.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"))
                .put(SignupRoute.PARAM_STRUCTURE, structure)
                .put(SignupRoute.PARAM_CATEGORY_AGE, getCategoryAge());
        given().body(param.encode())
                .when().post(BASE_URL + "/finalize")
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
    public void finalizeSignupWithWrongHttpMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().setHandler(u -> {
            given().header(TOKEN, u.result().getAccount().getToken())
                    .when().get(BASE_URL + "/finalize")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        });
        async.await(TIMEOUT);
    }

    /**
     * Finalize signup with blank or null params.
     */
    @Test
    public void finalizeSignupWithBlankOrNullParams() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_COUNTRY, DATA_STRUCTURE, SETTINGS_SEASONS);
        JsonObject u = generateNewUser();
        u.put("captcha", "fake");
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");
        String token = given().param(SignupRoute.PARAM_ID, p.getString("_id"))
                .param(SignupRoute.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .when().get(BASE_URL + "/firstconnectioncheck")
                .then().assertThat().statusCode(200)
                .body("account", notNullValue())
                .body("account.token", notNullValue()).extract().path("account.token");

        JsonObject param = new JsonObject();
        param.put(SignupRoute.PARAM_CODE, p.getJsonObject("account").getString("activationCode"));
        param.put(SignupRoute.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"));
        param.put(SignupRoute.PARAM_STRUCTURE, getStructure());
        param.put(SignupRoute.PARAM_CATEGORY_AGE, getCategoryAge());
        given().header(TOKEN, token)
                .body(param.encode())
                .when().post(BASE_URL + "/finalize")
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
        u.put("captcha", "dummy");
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");
        String token = given().param(SignupRoute.PARAM_ID, p.getString("_id"))
                .param(SignupRoute.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .when().get(BASE_URL + "/firstconnectioncheck")
                .then().assertThat().statusCode(200)
                .body("account", notNullValue())
                .body("account.token", notNullValue()).extract().path("account.token");

        String id = p.getString("_id");
        p.put("_id", "blabla");
        JsonObject param = new JsonObject()
                .put(SignupRoute.PARAM_USER, p)
                .put(SignupRoute.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .put(SignupRoute.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"))
                .put(SignupRoute.PARAM_STRUCTURE, getStructure())
                .put(SignupRoute.PARAM_CATEGORY_AGE, getCategoryAge());
        given().header(TOKEN, token)
                .body(param.encode())
                .when().post(BASE_URL + "/finalize")
                .then().assertThat().statusCode(ExceptionCodes.BAD_LOGIN.getCode())
                .body(CODE, is(ExceptionCodes.BAD_LOGIN.toString()));

        p.put("_id", id);
        param.put(SignupRoute.PARAM_USER, p)
                .put(SignupRoute.PARAM_CODE, "blabla");
        given().header(TOKEN, token)
                .body(param.encode())
                .when().post(BASE_URL + "/finalize")
                .then().assertThat().statusCode(ExceptionCodes.BUSINESS_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.BUSINESS_ERROR.toString()));
    }

    /**
     * Resend register mail.
     */
    @Test
    public void resendRegisterMail() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY, SETTINGS_ACTIVITY_CFG, SETTINGS_COUNTRY, DATA_STRUCTURE, SETTINGS_SEASONS);
        JsonObject u = generateNewUser();
        u.put("captcha", "fake");
        JsonObject p = new JsonObject(given().body(u.encode())
                .when().put(BASE_URL + "/register")
                .then().assertThat().statusCode(200)
                .body("person", notNullValue())
                .body("person.account.active", is(false))
                .body("person.name", notNullValue())
                .body("person.name", is(u.getString("name")))
                .body("person._id", notNullValue()).extract().asString()).getJsonObject("person");

        JsonObject param = new JsonObject()
                .put(SignupRoute.PARAM_USER, p)
                .put(SignupRoute.PARAM_CODE, p.getJsonObject("account").getString("activationCode"))
                .put(SignupRoute.PARAM_ACTIVITY, u.getJsonObject("plan").getJsonObject("activity").getString("_id"))
                .put(SignupRoute.PARAM_STRUCTURE, getStructure())
                .put(SignupRoute.PARAM_CATEGORY_AGE, getCategoryAge());
        given().body(param.encode())
                .when().post(BASE_URL + "/finalize")
                .then().assertThat().statusCode(200)
                .body("name", notNullValue())
                .body("name", is(p.getString("name")))
                .body("sandboxDefault", notNullValue())
                .body("account.active", is(false));


        given()
                .body(new JsonObject().put(SignupRoute.PARAM_LOGIN, "loginTest").encode())
                .when().post(BASE_URL + "/mailResend")
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
                .body(new JsonObject().put(SignupRoute.PARAM_LOGIN, "bla").encode())
                .when().post(BASE_URL + "/mailResend")
                .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Resend register mail with no login.
     */
    @Test
    public void resendRegisterMailWithNoLogin() {
        given()
                .body(new JsonObject().put(SignupRoute.PARAM_LOGIN, "").encode())
                .when().post(BASE_URL + "/mailResend")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        given()
                .body(new JsonObject().encode())
                .when().post(BASE_URL + "/mailResend")
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Resend register mail with wrong http method.
     */
    @Test
    public void resendRegisterMailWithWrongHttpMethod() {
        given()
                .body(new JsonObject().put(SignupRoute.PARAM_LOGIN, "loginTest").encode())
                .when().put(BASE_URL + "/mailResend")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * @return params
     */

    private JsonObject generateNewUser() {
        return new JsonObject()
                .put("account", new JsonObject()
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
