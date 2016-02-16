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
package com.qaobee.hive.test.api.sandbox.stats;

import com.qaobee.hive.api.v1.sandbox.stats.SB_StatisticsVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * This class tests services provided by SB_StatisticsVerticle.
 *
 * @author CKE
 */
public class SB_StatsTest extends VertxJunitSupport {

    /**
     * Gets list event test.
     */
    @Test
    public void getListDetailValuesTest() {

        populate(POPULATE_ONLY, DATA_STAT_HAND, DATA_USER_QAOBEE);

        //First Add an event
        User user = generateLoggedUser();
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setUser(user);
        req.setMethod(Constantes.POST);

        /* list of parameters */
        final JsonObject params = new JsonObject();
        params.putNumber(SB_StatisticsVerticle.PARAM_START_DATE, 1443650400000l);
        params.putNumber(SB_StatisticsVerticle.PARAM_END_DATE, 1451516400000l);
        params.putArray(SB_StatisticsVerticle.PARAM_INDICATOR_CODE, new JsonArray(new String[]{"originShootAtt"}));
        params.putArray(SB_StatisticsVerticle.PARAM_LIST_OWNERS, new JsonArray(new String[]{"5f82c510-2c89-46b0-b87d-d3b59e748615"}));

        req.setBody(params.encode());

        final String reply = sendonBus(SB_StatisticsVerticle.GET_LISTDETAIL_VALUES, req, user.getAccount().getToken());
        Assert.assertEquals("getListDetailValues", 10, new JsonArray(reply).size());

    }

}