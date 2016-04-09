package com.qaobee.hive.test.api;

import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * The type Common test.
 */
public class CommonTest extends VertxJunitSupport {
    /**
     * Test request on non existing resource.
     */
    @Test
    public void testRequestOnNonExistingResource() {
        given().when().get(BASE_URL + "/nothing/real")
                .then().assertThat().statusCode(404);
        given().when().post(BASE_URL + "/nothing/real")
                .then().assertThat().statusCode(404);
        given().when().put(BASE_URL + "/nothing/real")
                .then().assertThat().statusCode(404);
        given().when().delete(BASE_URL + "/nothing/real")
                .then().assertThat().statusCode(404);
        given().when().head(BASE_URL + "/nothing/real")
                .then().assertThat().statusCode(404);
    }

    /**
     * Test cors request
     */
    @Test
    public void testCORSRequest() {
        given().when().options(BASE_URL + "/nothing/real")
                .then().assertThat().statusCode(200);
    }
}
