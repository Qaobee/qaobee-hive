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

import com.qaobee.hive.api.v1.commons.utils.PDFVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Payment;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.dao.UserDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.PasswordEncryptionService;
import com.qaobee.hive.technical.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Base64;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.text.DateFormat;

/**
 * The type User dao.
 */
public class UserDAOImpl implements UserDAO {
    private static final String ACCOUNT_FIELD = "account";
    private static final String AVATAR_FIELD = "avatar";
    private static final String ADDRESS_FIELD = "address";
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;
    @Inject
    private PasswordEncryptionService passwordEncryptionService;

    @Override
    public JsonObject updateAvatar(String uid, String filename) throws QaobeeException {
        JsonObject jsonperson = mongo.getById(uid, User.class)
                .putString(AVATAR_FIELD, filename);
        mongo.save(jsonperson, User.class);
        return jsonperson;
    }

    @Override
    public JsonObject generateBillPDF(User user, String payId, String planId, String locale) throws QaobeeException {
        Plan planItem = user.getAccount().getListPlan().get(Integer.parseInt(planId));
        Payment payment = null;
        for (Payment p : planItem.getShippingList()) {
            if (p.getId().equals(payId)) {
                payment = p;
            }
        }
        if (payment != null) {
            final JsonObject juser = new JsonObject().putString("firstname", user.getFirstname())
                    .putString("name", user.getName())
                    .putString("username", user.getAccount().getLogin())
                    .putString("phoneNumber", user.getContact().getHome())
                    .putString("email", user.getContact().getEmail())
                    .putString("paidDate", utils.formatDate(payment.getPaidDate() / 1000L, DateFormat.MEDIUM, DateFormat.MEDIUM, locale))
                    .putString("paymentId", payment.getPaymentId())
                    .putString("plan", planItem.getLevelPlan().name())
                    .putString("amountPaid", String.valueOf(payment.getAmountPaid()))
                    .putString("birthdate", utils.formatDate(user.getBirthdate(), DateFormat.MEDIUM, DateFormat.MEDIUM, locale));
            if (StringUtils.isNoneBlank(user.getAvatar())) {
                juser.putString(AVATAR_FIELD, new String(Base64.decode(user.getAvatar())));
            }
            if (user.getAddress() != null) {
                if (StringUtils.isNotBlank(user.getAddress().getFormatedAddress())) {
                    juser.putString(ADDRESS_FIELD, user.getAddress().getFormatedAddress());
                } else {
                    juser.putString(ADDRESS_FIELD, user.getAddress().getPlace()
                            + " " + user.getAddress().getZipcode()
                            + " "
                            + user.getAddress().getCity()
                            + " "
                            + user.getAddress().getCountry());
                }
            }
            return new JsonObject()
                    .putString(PDFVerticle.FILE_NAME, payment.getPaymentId() + "-Qaobee")
                    .putString(PDFVerticle.TEMPLATE, "billing/bill.ftl")
                    .putObject(PDFVerticle.DATA, juser);
        } else {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "unknown bill");
        }
    }

    @Override
    public JsonObject generateProfilePDF(User user, String locale) {
        final JsonObject juser = new JsonObject().putString("firstname", user.getFirstname())
                .putString("name", user.getName())
                .putString("username", user.getAccount().getLogin())
                .putString("phoneNumber", user.getContact().getHome())
                .putString("email", user.getContact().getEmail())
                .putString("birthdate", utils.formatDate(user.getBirthdate(), DateFormat.MEDIUM, DateFormat.MEDIUM, locale));
        if (StringUtils.isNoneBlank(user.getAvatar())) {
            juser.putString(AVATAR_FIELD, new String(Base64.decode(user.getAvatar())));
        }
        if (user.getAddress() != null) {
            if (StringUtils.isNotBlank(user.getAddress().getFormatedAddress())) {
                juser.putString(ADDRESS_FIELD, user.getAddress().getFormatedAddress());
            } else {
                juser.putString(ADDRESS_FIELD, user.getAddress().getPlace() + " " + user.getAddress().getZipcode() + " " + user.getAddress().getCity() + " " + user.getAddress().getCountry());
            }
        }
        return new JsonObject()
                .putString(PDFVerticle.FILE_NAME, user.getAccount().getLogin())
                .putString(PDFVerticle.TEMPLATE, "profile/profile.ftl")
                .putObject(PDFVerticle.DATA, juser);
    }

    @Override
    public JsonObject updateUser(JsonObject u) throws QaobeeException {
        final User user = Json.decodeValue(u.encode(), User.class);
        if (StringUtils.isNotBlank(user.getAccount().getPasswd())) {
            final byte[] salt = passwordEncryptionService.generateSalt();
            user.getAccount().setSalt(salt);
            user.getAccount().setPassword(passwordEncryptionService.getEncryptedPassword(user.getAccount().getPasswd(), salt));
            user.getAccount().setPasswd(null);
            u.putObject(ACCOUNT_FIELD, new JsonObject(Json.encode(user.getAccount())));
        } else {
            JsonObject p = mongo.getById(user.get_id(), User.class.getSimpleName());
            u.putObject(ACCOUNT_FIELD, p.getObject(ACCOUNT_FIELD));
        }
        mongo.save(u, User.class.getSimpleName());
        return u;
    }
}
