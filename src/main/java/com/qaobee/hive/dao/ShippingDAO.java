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
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.concurrent.CompletableFuture;

/**
 * The interface Shipping dao.
 */
public interface ShippingDAO {
    /**
     * Periodic payment json array.
     *
     * @return the json array
     */
    JsonArray periodicPayment();

    /**
     * Triggered payment.
     *
     * @param user    the user
     * @param message the message
     */
    void triggeredPayment(JsonObject user, Message<JsonObject> message);

    /**
     * Pay.
     *
     * @param user   the user
     * @param planId the plan id
     * @param locale the locale
     * @return the completable future
     * @throws QaobeeException the qaobee exception
     */
    CompletableFuture<JsonObject> pay(User user, int planId, String locale) throws QaobeeException;

    /**
     * Ipn boolean.
     *
     * @param body the body
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    boolean ipn(JsonObject body) throws QaobeeException;
}
