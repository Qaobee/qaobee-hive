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
import com.qaobee.hive.dao.*;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.tools.PasswordEncryptionService;
import com.qaobee.hive.technical.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Base64;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.text.DateFormat;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * The type User dao.
 */
public class UserDAOImpl implements UserDAO {
    private static final String ACCOUNT_FIELD = "account";
    private static final String AVATAR_FIELD = "avatar";
    private static final String ADDRESS_FIELD = "address";
    private static final String PASSWD_FIELD = "passwd"; // NOSONAR
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("^([a-z'àâéèêôùûç \\-]+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_LOGIN_REGEX = Pattern.compile("^([a-z0-9\\.\\-]+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String COLLECTION = "User";
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;
    @Inject
    private PasswordEncryptionService passwordEncryptionService;
    /**
     * The Sand box dao.
     */
    @Inject
    SandBoxDAO sandBoxDAO;
    /**
     * The Season dao.
     */
    @Inject
    SeasonDAO seasonDAO;
    /**
     * The Team dao.
     */
    @Inject
    TeamDAO teamDAO;

    @Override
    public JsonObject updateAvatar(String uid, String filename) throws QaobeeException {
        JsonObject jsonperson = mongo.getById(uid, COLLECTION)
                .putString(AVATAR_FIELD, filename);
        mongo.save(jsonperson, COLLECTION);
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
        if (payment == null) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "unknown bill");
        }
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
        JsonObject p = mongo.getById(user.get_id(), COLLECTION);
        if (StringUtils.isNotBlank(user.getAccount().getPasswd())) {
            final byte[] salt = passwordEncryptionService.generateSalt();
            user.getAccount().setSalt(salt);
            user.getAccount().setPassword(passwordEncryptionService.getEncryptedPassword(user.getAccount().getPasswd(), salt));
            user.getAccount().setPasswd(null);
            u.putObject(ACCOUNT_FIELD, new JsonObject(Json.encode(user.getAccount())));
        } else {
            u.putObject(ACCOUNT_FIELD, p.getObject(ACCOUNT_FIELD));
        }
        mongo.save(u, COLLECTION);
        return u;
    }

    @Override
    public boolean checkUserInformations(User user, String locale) throws QaobeeException {
        if (user == null || user.getAccount() == null || user.getContact() == null) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.required", locale));
        }
        checkUserName(user.getName(), locale);
        checkUserFirstname(user.getFirstname(), locale);
        checkUserLogin(user.getAccount().getLogin(), locale);
        testEmail(user.getContact().getEmail(), locale);
        // Password
        if (StringUtils.isBlank(user.getAccount().getPasswd())) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.password.required", locale));
        } else if (user.getAccount().getPasswd().length() < 4) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.password.short", locale));
        }
        return true;
    }

    private static void checkUserLogin(String login, String locale) throws QaobeeException {
        if (StringUtils.isBlank(login)) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.login.required", locale));
        } else if (login.length() < 4) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.login.short", locale));
        } else if (!VALID_LOGIN_REGEX.matcher(login.trim()).find()) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.login.format", locale));
        }
    }

    private static void checkUserFirstname(String firstname, String locale) throws QaobeeException {
        if (StringUtils.isBlank(firstname)) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.firstname.required", locale));
        } else if (firstname.trim().length() < 2) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.firstname.short", locale));
        } else if (!VALID_NAME_REGEX.matcher(firstname.trim()).find()) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.firstname.format", locale));
        }
    }

    private static void checkUserName(String name, String locale) throws QaobeeException {
        if (StringUtils.isBlank(name)) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.name.required", locale));
        } else if (name.trim().length() < 2) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.name.short", locale));
        } else if (!VALID_NAME_REGEX.matcher(name.trim()).find()) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.name.format", locale));
        }
    }

    @Override
    public boolean existingLogin(String login) {
        final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", login).get(), null, null, 0, 0, COLLECTION);
        return res.size() > 0;
    }

    @Override
    public void testEmail(String email, String locale) throws QaobeeException {
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email.replaceAll("\\[at\\]", "@")).find()) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("email.bad.format", locale));
        }
        if (StringUtils.isBlank(email)) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("email.required", locale));
        }
    }

    @Override
    public User prepareUpsert(final User user) throws QaobeeException {
        user.getAccount().setLogin(user.getAccount().getLogin().toLowerCase());
        if (StringUtils.isNotBlank(user.getAccount().getPasswd())) {
            final byte[] salt = passwordEncryptionService.generateSalt();
            user.getAccount().setSalt(salt);
            user.getAccount().setPassword(passwordEncryptionService.getEncryptedPassword(user.getAccount().getPasswd(), salt));
            user.getAccount().setPasswd(null);
        }
        if (StringUtils.isBlank(user.getAccount().getActivationCode())) {
            final String activationcode = UUID.randomUUID().toString().replaceAll("-", "");
            user.getAccount().setActivationCode(activationcode);
            user.getAccount().setActive(false);
            user.getAccount().setFirstConnexion(true);
            user.setTimestamp(System.currentTimeMillis());
            user.set_id(null);
        }
        return user;
    }

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    @Override
    public JsonObject getUser(String id) throws QaobeeException {
        return mongo.getById(id, COLLECTION);
    }

    @Override
    public JsonObject getUserByLogin(String login) throws QaobeeException {
        // Creation of request
        CriteriaBuilder criterias = new CriteriaBuilder();
        criterias.add("account.login", login.toLowerCase());
        JsonArray jsonArray = mongo.findByCriterias(criterias.get(), null, null, -1, -1, COLLECTION);
        if (jsonArray == null || jsonArray.size() == 0) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "Login inconnu");
        }
        if (jsonArray.size() > 1) {
            throw new QaobeeException(ExceptionCodes.BUSINESS_ERROR, "Plus d'un résultat retourné");
        }
        return jsonArray.get(0);
    }

    @Override
    public JsonObject getUserInfo(String id) throws QaobeeException {
        JsonObject u = getUser(id);
        u.removeField("salt");
        u.removeField(PASSWD_FIELD);
        u.removeField("password");
        return u;
    }

    @Override
    public JsonObject getMeta(String userId) throws QaobeeException {
        final JsonObject activity = ((JsonObject) getUser(userId)
                .getObject(ACCOUNT_FIELD)
                .getArray("listPlan").get(0))
                .getObject("activity");

        JsonObject meta = sandBoxDAO.getByOwner(activity.getString("_id"), userId);
        meta.putObject("season", seasonDAO.getCurrentSeason(activity.getString("_id"), meta.getObject("structure").getObject("country").getString("_id")));
        meta.putArray("teams", teamDAO.getTeamList(meta.getString("_id"), meta.getString("effectiveDefault"), "false", "true", null));
        meta.putObject("activity", activity);
        return meta;
    }
}
