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
package com.qaobee.hive.dao.impl;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.dao.MailUtils;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class MailDao.
 */
public final class MailUtilsImpl implements MailUtils {

    private static final String TITLE_FIELD = "title";
    private static final String HEADER_FIELD = "header";
    private static final String SUB_HEADER_FIELD = "subheader";
    private static final String MESSAGE_FIELD = "message";
    private static final String SITE_URL_KEY = "site.url";
    private static final String SIG_KEY = "mail.account.validation.sig";
    private static final String ASSISTANT_FIELD = "assistant";
    private static final String ASSISTANT_KEY = "mail.footer.assistant";
    private static final String SOCIAL_LINKS_FIELD = "stayConnected";
    private static final String SOCIAL_LINKS_KEY = "mail.footer.stayConnected";
    private static final String SALUTATION_FIELD = "salutation";
    private static final String ACTIVATION_LINK_FIELD = "activationlink";
    private static final String ACTIVATION_LINK_TEXT_FIELD = "activationlinkText";
    private final JsonObject runtime;

    /**
     * Instantiates a new Mail utils.
     *
     * @param runtime the runtime
     */
    @Inject
    public MailUtilsImpl(@Named("runtime") JsonObject runtime) {
        this.runtime = runtime;
    }


    @Override
    public JsonObject generateActivationBody(final User user, final String locale) {
        return new JsonObject()
                .put(TITLE_FIELD, Messages.getString("mail.account.validation.title", locale))
                .put(SALUTATION_FIELD, Messages.getString("mail.account.validation.line.1", locale, user.getFirstname() != null ? user.getFirstname() : ""))
                .put("desc", Messages.getString("mail.account.validation.line.2", locale))
                .put(HEADER_FIELD, Messages.getString("mail.account.validation.title", locale))
                .put(SUB_HEADER_FIELD, "")
                .put(MESSAGE_FIELD, Messages.getString("mail.account.validation.line.3", locale))
                .put(ACTIVATION_LINK_FIELD, runtime.getString(SITE_URL_KEY) + runtime.getString("mail.site.url.api") + "/" + user.get_id() + "/" + user.getAccount().getActivationCode())
                .put(ACTIVATION_LINK_TEXT_FIELD, Messages.getString("mail.account.validation.activationtext", locale))
                .put("sig", Messages.getString(SIG_KEY, locale, runtime.getString(SITE_URL_KEY)))
                .put(ASSISTANT_FIELD, Messages.getString(ASSISTANT_KEY, locale))
                .put(SOCIAL_LINKS_FIELD, Messages.getString(SOCIAL_LINKS_KEY, locale));
    }

    @Override
    public JsonObject generateNewpasswdBody(final User user, final String locale) {
        return new JsonObject()
                .put(TITLE_FIELD, Messages.getString("mail.account.newpasswd.title", locale))
                .put(SALUTATION_FIELD, Messages.getString("mail.account.newpasswd.line.1", locale, user.getFirstname() != null ? user.getFirstname() : ""))
                .put(HEADER_FIELD, Messages.getString("mail.account.newpasswd.title", locale))
                .put(SUB_HEADER_FIELD, "")
                .put(MESSAGE_FIELD, Messages.getString("mail.account.newpasswd.line.2", locale))
                .put(ACTIVATION_LINK_FIELD, runtime.getString(SITE_URL_KEY) + runtime.getString("newpasswd.site.url.api") + "/" + user.get_id() + "/" + user.getAccount().getActivationPasswd())
                .put(ACTIVATION_LINK_TEXT_FIELD, Messages.getString("mail.account.newpasswd.activationtext", locale))
                .put("sig", Messages.getString(SIG_KEY, locale, runtime.getString(SITE_URL_KEY)))
                .put(ASSISTANT_FIELD, Messages.getString(ASSISTANT_KEY, locale))
                .put(SOCIAL_LINKS_FIELD, Messages.getString(SOCIAL_LINKS_KEY, locale));
    }

    @Override
    public JsonObject generatePaymentBody(final User user, final String locale, final Plan planItem) {
        return new JsonObject()
                .put(TITLE_FIELD, Messages.getString("mail.payment.title", locale))
                .put("desc", Messages.getString("mail.payment.line.1", locale, user.getFirstname() + " " + user.getName(), planItem.getLevelPlan().name()))
                .put(HEADER_FIELD, Messages.getString("mail.payment.title", locale))
                .put(SUB_HEADER_FIELD, "")
                .put(MESSAGE_FIELD, Messages.getString("mail.payment.line.2", locale, planItem.getAmountPaid()))
                .put("sig", Messages.getString(SIG_KEY, locale, runtime.getString(SITE_URL_KEY)))
                .put(ASSISTANT_FIELD, Messages.getString(ASSISTANT_KEY, locale))
                .put(SOCIAL_LINKS_FIELD, Messages.getString(SOCIAL_LINKS_KEY, locale));
    }

    @Override
    public JsonObject generateRefoundBody(User user, String locale, Plan planItem) {
        return new JsonObject()
                .put(TITLE_FIELD, Messages.getString("mail.refund.title", locale))
                .put("desc", Messages.getString("mail.refund.line.1", locale, user.getFirstname() + " " + user.getName(), planItem.getLevelPlan().name()))
                .put(HEADER_FIELD, Messages.getString("mail.refund.title", locale))
                .put(SUB_HEADER_FIELD, "")
                .put(MESSAGE_FIELD, Messages.getString("mail.refund.line.2", locale, planItem.getAmountPaid()))
                .put("sig", Messages.getString(SIG_KEY, locale, runtime.getString(SITE_URL_KEY)))
                .put(ASSISTANT_FIELD, Messages.getString(ASSISTANT_KEY, locale))
                .put(SOCIAL_LINKS_FIELD, Messages.getString(SOCIAL_LINKS_KEY, locale));
    }


    @Override
    public JsonObject generateRefusedCardBody(User user, String locale, Plan planItem, String reason) {
        return new JsonObject()
                .put(TITLE_FIELD, Messages.getString("mail.refusedCard.title", locale))
                .put("desc", Messages.getString("mail.refusedCard.line.1", locale, user.getFirstname() + " " + user.getName(), planItem.getLevelPlan().name()))
                .put(HEADER_FIELD, Messages.getString("mail.refusedCard.title", locale))
                .put(SUB_HEADER_FIELD, "mail.refusedCard.line.subheader." + reason)
                .put(MESSAGE_FIELD, Messages.getString("mail.refusedCard.line2", locale, runtime.getString(SITE_URL_KEY) + "/" + runtime.getString("pay.url.api")))
                .put("sig", Messages.getString(SIG_KEY, locale, runtime.getString(SITE_URL_KEY)))
                .put(ASSISTANT_FIELD, Messages.getString(ASSISTANT_KEY, locale))
                .put(SOCIAL_LINKS_FIELD, Messages.getString(SOCIAL_LINKS_KEY, locale));
    }

    @Override
    public JsonObject generateInvitationToSandboxBody(User user, String locale, String emailTarget, String invitationId, String target) {
        final JsonObject json = new JsonObject()
                .put(TITLE_FIELD, Messages.getString("mail.account.sharingSB.title", locale))
                .put(SALUTATION_FIELD, Messages.getString("mail.account.sharingSB.line.1", locale))
                .put("desc", Messages.getString("mail.account.sharingSB.line.2", locale, (user.getFirstname() != null ? user.getFirstname() : "") + " " + (user.getName() != null ? user.getName() : "")))
                .put(MESSAGE_FIELD, Messages.getString("mail.account.sharingSB.line.3", locale));
        if ("internal".equals(target)) {
            json.put(ACTIVATION_LINK_FIELD, runtime.getString(SITE_URL_KEY)
                    + runtime.getString("internal.invitation.site.url.api") + "/" + invitationId);
        } else {
            json.put(ACTIVATION_LINK_FIELD, runtime.getString(SITE_URL_KEY)
                    + runtime.getString("external.invitation.site.url.api") + "/" + invitationId);
        }

        json.put(ACTIVATION_LINK_TEXT_FIELD, Messages.getString("mail.account.sharingSB.activationtext", locale))
                .put("sig", Messages.getString(SIG_KEY, locale, runtime.getString(SITE_URL_KEY)))
                .put(ASSISTANT_FIELD, Messages.getString(ASSISTANT_KEY, locale));
        return json;
    }

    @Override
    public JsonObject generatePaymentCanceledBody(User user, String locale, Plan planItem) {
        return new JsonObject()
                .put(TITLE_FIELD, Messages.getString("mail.payment.canceled.title", locale))
                .put("desc", Messages.getString("mail.payment.canceled.line.1", locale, user.getFirstname() + " " + user.getName(), planItem.getLevelPlan().name()))
                .put(HEADER_FIELD, Messages.getString("mail.payment.canceled.title", locale))
                .put(SUB_HEADER_FIELD, "")
                .put(MESSAGE_FIELD, "")
                .put("sig", Messages.getString(SIG_KEY, locale, runtime.getString(SITE_URL_KEY)))
                .put(ASSISTANT_FIELD, Messages.getString(ASSISTANT_KEY, locale))
                .put(SOCIAL_LINKS_FIELD, Messages.getString(SOCIAL_LINKS_KEY, locale));
    }
}
