package com.qaobee.hive.test.api.sandbox.share;

import com.qaobee.hive.api.Main;
import com.qaobee.hive.api.v1.sandbox.share.SB_ShareVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * The type Sb share test.
 */
public class SB_ShareTest extends VertxJunitSupport {

    /**
     * Add a member to a sandbox.
     */
    @Test
    public void addAMemberToASandbox() {
        populate(POPULATE_ONLY, DATA_USER_QAOBEE, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final JsonObject params = new JsonObject();
        params.putString(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_ShareVerticle.PARAM_USERID, "5509ef1fdb8f8b6e2f51f4ce");

        String id = given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_ShareVerticle.ADD_FRIEND))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .extract().path("_id");

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_ShareVerticle.PARAM_SANBOXID, id)
                .when().get(getURL(SB_ShareVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("sandboxCfg.members", hasSize(1));
    }

    /**
     * Add a member to a sandbox with non logged user.
     */
    @Test
    public void addAMemberToASandboxWithNonLoggedUser() {
        given().when().post(getURL(SB_ShareVerticle.ADD_FRIEND))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add a member to a sandbox with wrong http method.
     */
    @Test
    public void addAMemberToASandboxWithWrongHttpMethod() {
        given().when().get(getURL(SB_ShareVerticle.ADD_FRIEND))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add a member to a sandbox with missing params.
     */
    @Test
    public void addAMemberToASandboxWithMissingParams() {
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_ShareVerticle.PARAM_USERID, "b50b3325-fdbd-41bf-bda4-81c827982001");
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_ShareVerticle.ADD_FRIEND).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params2.encode())
                    .when().post(getURL(SB_ShareVerticle.ADD_FRIEND))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }

    /**
     * Remove a member to a sandbox.
     */
    @Test
    public void removeAMemberToASandbox() {
        populate(POPULATE_ONLY, DATA_USER_QAOBEE, DATA_SANDBOXES_HAND);
        User user = generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce");
        final JsonObject params = new JsonObject();
        params.putString(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_ShareVerticle.PARAM_USERID, "5509ef1fdb8f8b6e2f51f4ce");

        String id = given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_ShareVerticle.ADD_FRIEND))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .extract().path("_id");

        given().header(TOKEN, user.getAccount().getToken())
                .queryParam(SB_ShareVerticle.PARAM_SANBOXID, id)
                .when().get(getURL(SB_ShareVerticle.GET))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("sandboxCfg.members", hasSize(1));

        given().header(TOKEN, user.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(SB_ShareVerticle.DEL_FRIEND))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue())
                .body("sandboxCfg.members", hasSize(0));
    }

    /**
     * Remove a member to a sandbox with non logged user.
     */
    @Test
    public void removeAMemberToASandboxWithNonLoggedUser() {
        given().when().post(getURL(SB_ShareVerticle.DEL_FRIEND))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Remove a member to a sandbox with wrong http method.
     */
    @Test
    public void removeAMemberToASandboxWithWrongHttpMethod() {
        given().when().get(getURL(SB_ShareVerticle.DEL_FRIEND))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Remove a member to a sandbox with missing params.
     */
    @Test
    public void removeAMemberToASandboxWithMissingParams() {
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(SB_ShareVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(SB_ShareVerticle.PARAM_USERID, "b50b3325-fdbd-41bf-bda4-81c827982001");
        List<String> mandatoryParams = Arrays.asList(Main.getRules().get(SB_ShareVerticle.DEL_FRIEND).mandatoryParams());
        params.getFieldNames().stream().filter(mandatoryParams::contains).forEach(k -> {
            JsonObject params2 = new JsonObject(params.encode());
            params2.removeField(k);
            given().header(TOKEN, u.getAccount().getToken())
                    .body(params2.encode())
                    .when().post(getURL(SB_ShareVerticle.DEL_FRIEND))
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
        });
    }
}
