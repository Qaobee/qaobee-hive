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
package com.qaobee.hive.technical.utils.impl;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.tools.Params;
import com.qaobee.hive.technical.utils.MailUtils;
import org.vertx.java.core.json.JsonObject;

/**
 * The Class MailDao.
 */
public final class MailUtilsImpl implements MailUtils {

    /**
     * Generate activation body.
     *
     * @param user   the user
     * @param locale the locale language
     * @return the json object
     */
    @Override
    public JsonObject generateActivationBody(final User user, final String locale) {
        final JsonObject json = new JsonObject();
        json.putString("title", Messages.getString("mail.account.validation.title", locale));
        json.putString("salutation", Messages.getString("mail.account.validation.line.1", locale, user.getFirstname() + " " + user.getName()));
        json.putString("desc", Messages.getString("mail.account.validation.line.2", locale));
        json.putString("header", Messages.getString("mail.account.validation.title", locale));
        json.putString("subheader", "");
        json.putString("message", Messages.getString("mail.account.validation.line.3", locale));
        json.putString("activationlink", Params.getString("site.url") + Params.getString("mail.site.url.api") + "/" + user.get_id() + "/" + user.getAccount().getActivationCode());
        json.putString("sig", Messages.getString("mail.account.validation.sig", locale, Params.getString("site.url")));
        json.putString("assistant", Messages.getString("mail.footer.assistant", locale));
        json.putString("stayConnected", Messages.getString("mail.footer.stayConnected", locale));
        return json;
    }

    /**
     * Generate newpasswd body.
     *
     * @param user   the user
     * @param locale the locale
     * @return the json object
     */
    @Override
    public JsonObject generateNewpasswdBody(final User user, final String locale) {
        final JsonObject json = new JsonObject();
        json.putString("title", Messages.getString("mail.account.newpasswd.title", locale));
        json.putString("desc", Messages.getString("mail.account.newpasswd.line.1", locale, user.getFirstname() + " " + user.getName()));
        json.putString("header", Messages.getString("mail.account.newpasswd.title", locale));
        json.putString("subheader", "");
        json.putString("message", Messages.getString("mail.account.newpasswd.line.2", locale));
        json.putString("activationlink", Params.getString("site.url") + Params.getString("newpasswd.site.url.api") + "/" + user.get_id() + "/" + user.getAccount().getActivationPasswd());
        json.putString("sig", Messages.getString("mail.account.validation.sig", locale, Params.getString("site.url")));
        json.putString("assistant", Messages.getString("mail.footer.assistant", locale));
        json.putString("stayConnected", Messages.getString("mail.footer.stayConnected", locale));
        return json;
    }

    /**
     * Generate email body for payment confirmation.
     *
     * @param user     the person
     * @param locale   the locale
     * @param planItem plan
     * @return body json object
     */
    @Override
    public JsonObject generatePaymentBody(final User user, final String locale, final Plan planItem) {
        final JsonObject json = new JsonObject();
        json.putString("title", Messages.getString("mail.payment.title", locale));
        json.putString("desc", Messages.getString("mail.payment.line.1", locale, user.getFirstname() + " " + user.getName(), planItem.getLevelPlan().name()));
        json.putString("header", Messages.getString("mail.payment.title", locale));
        json.putString("subheader", "");
        json.putString("message", Messages.getString("mail.payment.line.2", locale, planItem.getAmountPaid()));
        json.putString("sig", Messages.getString("mail.account.validation.sig", locale, Params.getString("site.url")));
        json.putString("assistant", Messages.getString("mail.footer.assistant", locale));
        json.putString("stayConnected", Messages.getString("mail.footer.stayConnected", locale));
        return json;
    }

    /**
     * Generate mail body for refunding.
     *
     * @param user     person
     * @param locale   locale
     * @param planItem plan
     * @return body json object
     */
    @Override
    public JsonObject generateRefoundBody(User user, String locale, Plan planItem) {
        final JsonObject json = new JsonObject();
        json.putString("title", Messages.getString("mail.refund.title", locale));
        json.putString("desc", Messages.getString("mail.refund.line.1", locale, user.getFirstname() + " " + user.getName(), planItem.getLevelPlan().name()));
        json.putString("header", Messages.getString("mail.refund.title", locale));
        json.putString("subheader", "");
        json.putString("message", Messages.getString("mail.refund.line.2", locale, planItem.getAmountPaid()));
        json.putString("sig", Messages.getString("mail.account.validation.sig", locale, Params.getString("site.url")));
        json.putString("assistant", Messages.getString("mail.footer.assistant", locale));
        json.putString("stayConnected", Messages.getString("mail.footer.stayConnected", locale));
        return json;
    }


    @Override
    public JsonObject generateRefusedCardBody(User user, String locale, Plan planItem, String reason) {
        final JsonObject json = new JsonObject();
        json.putString("title", Messages.getString("mail.refusedCard.title", locale));
        json.putString("desc", Messages.getString("mail.refusedCard.line.1", locale, user.getFirstname() + " " + user.getName(), planItem.getLevelPlan().name()));
        json.putString("header", Messages.getString("mail.refusedCard.title", locale));
        json.putString("subheader", "mail.refusedCard.line.subheader." + reason);
        json.putString("message", Messages.getString("mail.refusedCard.line2", locale, Params.getString("site.url") + "/" + Params.getString("pay.url.api")));
        json.putString("sig", Messages.getString("mail.account.validation.sig", locale, Params.getString("site.url")));
        json.putString("assistant", Messages.getString("mail.footer.assistant", locale));
        json.putString("stayConnected", Messages.getString("mail.footer.stayConnected", locale));
        return json;
    }
}
