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

package com.qaobee.hive.dao;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Promise;

/**
 * The interface Shipping dao.
 */
public interface ShippingDAO {

    /**
     * Pay.
     *
     * @param user        the user
     * @param paymentData the plan id
     * @param locale      the locale
     * @return the completable future
     */
    Promise<JsonObject, QaobeeException, Integer> pay(User user, JsonObject paymentData, String locale);

    /**
     * webHook boolean.
     *
     * @param body the body
     * @return the boolean
     */
    Promise<Boolean, QaobeeException, Integer> webHook(JsonObject body);
}
