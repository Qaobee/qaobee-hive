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
import com.qaobee.hive.business.model.commons.users.account.Plan;
import io.vertx.core.json.JsonObject;

/**
 * The interface Mail utils.
 */
public interface MailUtils {
    /**
     * Generate activation body.
     *
     * @param user   the person
     * @param locale the locale language
     * @return the json object
     */
    JsonObject generateActivationBody(User user, String locale);

    /**
     * Generate newpasswd body.
     *
     * @param user   the u
     * @param locale the locale
     * @return the json object
     */
    JsonObject generateNewpasswdBody(User user, String locale);

    /**
     * Generate email body for payment confirmation.
     *
     * @param user     the person
     * @param locale   the locale
     * @param planItem plan
     * @return body json object
     */
    JsonObject generatePaymentBody(User user, String locale, Plan planItem);

    /**
     * Generate mail body for refunding.
     *
     * @param user     person
     * @param locale   locale
     * @param planItem plan
     * @return body json object
     */
    JsonObject generateRefoundBody(User user, String locale, Plan planItem);

    /**
     * Generate refused card body json object.
     *
     * @param user   the user
     * @param locale the locale
     * @param plan   the plan
     * @param reason the reason  @return the json object
     * @return the json object
     */
    JsonObject generateRefusedCardBody(User user, String locale, Plan plan, String reason);

    /**
     * Generate activation body.
     *
     * @param user         the person
     * @param locale       the locale language
     * @param emailTarget  the guest's e-mail
     * @param invitationId the invitation id
     * @param target       indicate internal or external link
     * @return the json object
     */
    JsonObject generateInvitationToSandboxBody(User user, String locale, String emailTarget, String invitationId, String target);

    /**
     * Generate payment canceled body json object.
     *
     * @param user   the user
     * @param locale the locale
     * @param plan   the plan
     * @return the json object
     */
    JsonObject generatePaymentCanceledBody(User user, String locale, Plan plan);
}
