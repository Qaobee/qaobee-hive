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

package com.qaobee.hive.test.api;

import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Test;

import static io.restassured.RestAssured.*;

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
}
