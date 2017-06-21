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

import com.lowagie.text.pdf.codec.Base64;
import com.qaobee.hive.api.v1.commons.utils.PDFVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.dao.*;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.Utils;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.text.DateFormat;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * The type User dao.
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);
    private static final String ACCOUNT_FIELD = "account";
    private static final String AVATAR_FIELD = "avatar";
    private static final String ADDRESS_FIELD = "address";
    private static final String PASSWD_FIELD = "passwd"; // NOSONAR
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("^([a-z'àâéèêôùûç \\-]+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_LOGIN_REGEX = Pattern.compile("^([a-z0-9.\\-]+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;
    @Inject
    private PasswordEncryptionService passwordEncryptionService;
    @Inject
    private SandBoxDAO sandBoxDAO;
    @Inject
    private SeasonDAO seasonDAO;
    @Inject
    private TeamDAO teamDAO;
    @Inject
    private ActivityDAO activityDAO;

    public Promise<JsonObject, QaobeeException, Integer> updateAvatar(String uid, String filename) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(uid, DBCollections.USER)
                .done(jsonperson -> {
                    jsonperson.put(AVATAR_FIELD, filename);
                    mongo.upsert(jsonperson, DBCollections.USER)
                            .done(res -> deferred.resolve(jsonperson))
                            .fail(deferred::reject);
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public JsonObject generateProfilePDF(User user, String locale) {
        final JsonObject juser = new JsonObject().put("firstname", user.getFirstname())
                .put("name", user.getName())
                .put("username", user.getAccount().getLogin())
                .put("phoneNumber", user.getContact().getHome())
                .put("email", user.getContact().getEmail())
                .put("birthdate", utils.formatDate(user.getBirthdate(), DateFormat.MEDIUM, DateFormat.MEDIUM, locale));
        if (StringUtils.isNoneBlank(user.getAvatar())) {
            juser.put(AVATAR_FIELD, new String(Base64.decode(user.getAvatar())));
        }
        if (user.getAddress() != null) {
            if (StringUtils.isNotBlank(user.getAddress().getFormatedAddress())) {
                juser.put(ADDRESS_FIELD, user.getAddress().getFormatedAddress());
            } else {
                juser.put(ADDRESS_FIELD, user.getAddress().getPlace() + " " + user.getAddress().getZipcode() + " " + user.getAddress().getCity() + " " + user.getAddress().getCountry());
            }
        }
        return new JsonObject()
                .put(PDFVerticle.FILE_NAME, user.getAccount().getLogin())
                .put(PDFVerticle.TEMPLATE, "profile/profile.ftl")
                .put(PDFVerticle.DATA, juser);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> updateUser(JsonObject u) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        final User user = Json.decodeValue(u.encode(), User.class);
        mongo.getById(user.get_id(), DBCollections.USER)
                .done(p -> {
                    try {
                        if (StringUtils.isNotBlank(user.getAccount().getPasswd())) {
                            final byte[] salt = passwordEncryptionService.generateSalt();
                            user.getAccount().setSalt(salt);
                            user.getAccount().setPassword(passwordEncryptionService.getEncryptedPassword(user.getAccount().getPasswd(), salt));
                            user.getAccount().setPasswd(null);
                            u.put(ACCOUNT_FIELD, new JsonObject(Json.encode(user.getAccount())));
                        } else {
                            u.put(ACCOUNT_FIELD, p.getJsonObject(ACCOUNT_FIELD));
                        }
                        mongo.upsert(u, DBCollections.USER)
                                .done(res -> deferred.resolve(u))
                                .fail(deferred::reject);
                    } catch (QaobeeException e) {
                        LOG.error(e.getMessage(), e);
                        deferred.reject(e);
                    }
                })
                .fail(deferred::reject);
        return deferred.promise();
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
        } else if (user.getAccount().getPasswd().length() < 6) {
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
    public Promise<Boolean, QaobeeException, Integer> existingLogin(String login) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.findByCriterias(new CriteriaBuilder().add("account.login", login).get(), null, null, 0, 0, DBCollections.USER)
                .done(res -> deferred.resolve(res.size() > 0))
                .fail(deferred::reject);
        return deferred.promise();
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

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getUser(String id) {
        return mongo.getById(id, DBCollections.USER);
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getUserByLogin(String login) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        // Creation of request
        CriteriaBuilder criterias = new CriteriaBuilder();
        criterias.add("account.login", login.toLowerCase());
        mongo.findByCriterias(criterias.get(), null, null, -1, -1, DBCollections.USER)
                .done(jsonArray -> {
                    if (jsonArray.size() == 0) {
                        deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "Login inconnu"));
                    }
                    if (jsonArray.size() > 1) {
                        deferred.reject(new QaobeeException(ExceptionCodes.BUSINESS_ERROR, "Plus d'un résultat retourné"));
                    }
                    deferred.resolve(jsonArray.getJsonObject(0));
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getUserInfo(String id) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        getUser(id).done(u -> {
            u.remove("salt");
            u.remove(PASSWD_FIELD);
            u.remove("password");
            deferred.resolve(u);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getMeta(String sandboxId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        sandBoxDAO.getSandboxById(sandboxId)
                .done(meta -> {
                    meta.put("season", seasonDAO.getCurrentSeason(meta.getString("activityId"), meta.getJsonObject("structure").getJsonObject("country").getString("_id")));
                    meta.put("teams", teamDAO.getTeamList(meta.getString("_id"), meta.getString("effectiveDefault"), "false", "true", null));
                    meta.put("activity", activityDAO.getActivity(meta.getString("activityId")));
                    deferred.resolve(meta);
                })
                .fail(deferred::reject);
        return deferred.promise();
    }
}
