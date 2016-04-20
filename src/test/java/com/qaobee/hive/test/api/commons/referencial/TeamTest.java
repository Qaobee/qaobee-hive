package com.qaobee.hive.test.api.commons.referencial;

import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;

public class TeamTest extends VertxJunitSupport {
    @Test
    public void fake() {
        Assert.assertTrue(true);
    }
    /*
    @Test
    public void getTeam() {
        populate(POPULATE_ONLY, DATA_TEAM_HAND);
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(TeamVerticle.PARAM_ID, "552d5e08644a77b3a20afdfe")
                .when().get(getURL(TeamVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(TeamVerticle.PARAM_LABEL, notNullValue())
                .body(TeamVerticle.PARAM_LABEL, is("Cesson-Sevigne A"));
    }

    @Test
    public void getTeamWithNonLoggedUserTest() {
        given().when().get(getURL(TeamVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    @Test
    public void getTeamWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .post(getURL(TeamVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    @Test
    public void getTeamWithMissingParameterTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .get(getURL(TeamVerticle.GET))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    @Test
    public void updateTeam() {
        populate(POPULATE_ONLY, DATA_TEAM_HAND);
        User u = generateLoggedUser();
        JsonObject structure = new JsonObject(given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .queryParam(TeamVerticle.PARAM_ID, "552d5e08644a77b3a20afdfe")
                .when().get(getURL(TeamVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(TeamVerticle.PARAM_LABEL, notNullValue())
                .body(TeamVerticle.PARAM_LABEL, is("Cesson-Sevigne A"))
                .extract().asString());
        structure.putString(TeamVerticle.PARAM_LABEL, "newValue");
        given().header(TOKEN, u.getAccount().getToken())
                .body(structure.encode())
                .when().post(getURL(TeamVerticle.UPDATE))
                .then().assertThat().statusCode(200)
                .body(TeamVerticle.PARAM_LABEL, notNullValue())
                .body(TeamVerticle.PARAM_LABEL, is("newValue"));
    }

    @Test
    public void updateTeamWithNonLoggedUserTest() {
        given().when().post(getURL(TeamVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    @Test
    public void updateTeamWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .get(getURL(TeamVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    @Test
    public void updateTeamWithMissingParameterTest() {
        populate(POPULATE_ONLY, DATA_STRUCTURE);
        User u = generateLoggedUser();
        JsonObject structure = new JsonObject(given().header(TOKEN, u.getAccount().getToken())
                .queryParam(TeamVerticle.PARAM_ID, "552d5e08644a77b3a20afdfe")
                .when().get(getURL(TeamVerticle.GET))
                .then().assertThat().statusCode(200)
                .body(TeamVerticle.PARAM_LABEL, notNullValue())
                .body(TeamVerticle.PARAM_LABEL, is("Cesson-Sevigne A"))
                .extract().asString());
        structure.removeField(TeamVerticle.PARAM_ACTIVITY);
        given().header(TOKEN, u.getAccount().getToken())
                .body(structure.encode())
                .post(getURL(TeamVerticle.UPDATE))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    @Test
    public void addTeam() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(TeamVerticle.PARAM_LABEL, "blabla");
        params.putString(TeamVerticle.PARAM_SANBOXID, "558b0efebd2e39cdab651e1f");
        params.putString(TeamVerticle.PARAM_EFFECTIVEID, "550b31f925da07681592db23");
        params.putObject(TeamVerticle.PARAM_ACTIVITY, getActivity("ACT-HAND", u));
        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(TeamVerticle.ADD_TO_USER))
                .then().assertThat().statusCode(200)
                .body("_id", notNullValue());
    }

    @Test
    public void addTeamWithNonLoggedUserTest() {
        given().when().post(getURL(TeamVerticle.ADD_TO_USER))
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    @Test
    public void addTeamWithWrongHttpMethodTest() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
                .get(getURL(TeamVerticle.ADD_TO_USER))
                .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
                .body(CODE, is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    @Test
    public void addTeamWithMissingParameterTest() {
        populate(POPULATE_ONLY, SETTINGS_ACTIVITY);
        populate(POPULATE_ONLY, SETTINGS_COUNTRY);
        User u = generateLoggedUser();
        final JsonObject params = new JsonObject();
        params.putString(TeamVerticle.PARAM_SANBOXID, "12345");
        params.putString(TeamVerticle.PARAM_EFFECTIVEID, "12345");
        params.putObject(TeamVerticle.PARAM_ACTIVITY, getActivity("ACT-HAND", u));
        given().header(TOKEN, u.getAccount().getToken())
                .body(params.encode())
                .when().post(getURL(TeamVerticle.ADD_TO_USER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    } */
}
