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

package com.qaobee.hive.services.impl;

import com.lowagie.text.pdf.codec.Base64;
import com.qaobee.hive.verticles.PDFVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.dao.EncryptionService;
import com.qaobee.hive.services.SandBoxService;
import com.qaobee.hive.services.TeamService;
import com.qaobee.hive.services.ActivityService;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.services.SeasonService;
import com.qaobee.hive.services.UserService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.dao.Utils;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.text.DateFormat;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * The type User service.
 */
@ProxyService(address = "vertx.User.service", iface = UserService.class)
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
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
    private EncryptionService encryptionService;
    @Inject
    private SandBoxService sandBoxService;
    @Inject
    private SeasonService seasonService;
    @Inject
    private TeamService teamService;
    @Inject
    private ActivityService activityService;

    /**
     * Instantiates a new User service.
     *
     * @param vertx the vertx
     */
    public UserServiceImpl(Vertx vertx) { // NOSONAR
        super();
    }

    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    private static void checkUserLogin(String login, String locale) throws QaobeeException {
        if (StringUtils.isBlank(login)) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.login.required", locale));
        } else if (login.length() < 4) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.login.short", locale));
        } else if (!VALID_LOGIN_REGEX.matcher(login.trim()).find()) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.login.format", locale));
        }
    }

    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    private static void checkUserFirstname(String firstname, String locale) throws QaobeeException {
        if (StringUtils.isBlank(firstname)) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.firstname.required", locale));
        } else if (firstname.trim().length() < 2) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.firstname.short", locale));
        } else if (!VALID_NAME_REGEX.matcher(firstname.trim()).find()) {
            throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.firstname.format", locale));
        }
    }

    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
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
    public void generateProfilePDF(JsonObject u, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        final JsonObject juser = new JsonObject()
                .put("firstname", u.getString("firstname", ""))
                .put("name", u.getString("name", ""))
                .put("username", u.getJsonObject(ACCOUNT_FIELD).getString("login"))
                .put("phoneNumber", u.getJsonObject("contact").getString("home"))
                .put("email", u.getJsonObject("contact").getString("email"))
                .put("birthdate", utils.formatDate(u.getLong("birthdate"), DateFormat.MEDIUM, DateFormat.MEDIUM, locale));
        if (StringUtils.isNotBlank(u.getString(AVATAR_FIELD, ""))) {
            juser.put(AVATAR_FIELD, new String(Base64.decode(u.getString(AVATAR_FIELD))));
        }
        if (u.getJsonObject(ADDRESS_FIELD) != null) {
            if (StringUtils.isNotBlank(u.getJsonObject(ADDRESS_FIELD).getString("formatedAddress", ""))) {
                juser.put(ADDRESS_FIELD, u.getJsonObject(ADDRESS_FIELD).getString("formatedAddress"));
            } else {
                juser.put(ADDRESS_FIELD, u.getJsonObject(ADDRESS_FIELD).getString("place")
                        + " " + u.getJsonObject(ADDRESS_FIELD).getString("zipcode")
                        + " " + u.getJsonObject(ADDRESS_FIELD).getString("city")
                        + " " + u.getJsonObject(ADDRESS_FIELD).getString("country"));
            }
        }
        resultHandler.handle(Future.succeededFuture(new JsonObject()
                .put(PDFVerticle.FILE_NAME, u.getJsonObject(ACCOUNT_FIELD).getString("login"))
                .put(PDFVerticle.TEMPLATE, "profile.ftl")
                .put(PDFVerticle.DATA, juser)));
    }

    @Override
    public void updateUser(JsonObject u, Handler<AsyncResult<JsonObject>> resultHandler) {
        final User user = Json.decodeValue(u.encode(), User.class);
        mongo.getById(user.get_id(), DBCollections.USER, p -> {
            if (p.succeeded()) {
                try {
                    if (StringUtils.isNotBlank(user.getAccount().getPasswd())) {
                        final byte[] salt = encryptionService.generateSalt();
                        user.getAccount().setSalt(salt);
                        user.getAccount().setPassword(encryptionService.getEncrypted(user.getAccount().getPasswd(), salt));
                        user.getAccount().setPasswd(null);
                        u.put(ACCOUNT_FIELD, new JsonObject(Json.encode(user.getAccount())));
                    }
                    mongo.upsert(u, DBCollections.USER, res -> {
                        if (res.succeeded()) {
                            resultHandler.handle(Future.succeededFuture(u));
                        } else {
                            resultHandler.handle(Future.failedFuture(res.cause()));
                        }
                    });
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    resultHandler.handle(Future.failedFuture(e));
                }
            } else {
                resultHandler.handle(Future.failedFuture(p.cause()));
            }
        });
    }

    @Override
    public void checkUserInformations(JsonObject u, String locale, Handler<AsyncResult<Boolean>> resultHandler) {
        final User user = Json.decodeValue(u.encode(), User.class);
        try {
            if (user == null || user.getAccount() == null || user.getContact() == null) {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.required", locale))));
            } else {
                checkUserLogin(user.getAccount().getLogin(), locale);
                testEmail(user.getContact().getEmail(), locale, ar -> {
                    if (ar.succeeded()) {
                        // Password
                        if (StringUtils.isBlank(user.getAccount().getPasswd())) {
                            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.password.required", locale))));
                        } else if (user.getAccount().getPasswd().length() < 6) {
                            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.password.short", locale))));
                        } else {
                            resultHandler.handle(Future.succeededFuture(true));
                        }
                    } else {
                        resultHandler.handle(Future.failedFuture(ar.cause()));
                    }
                });
            }
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(e));
        }
    }

    @Override
    public void existingLogin(String login, Handler<AsyncResult<Boolean>> resultHandler) {
        mongo.findByCriterias(new JsonObject().put("account.login", login), new CriteriaOption(), DBCollections.USER, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(res.result().size() > 0));
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void testEmail(String email, String locale, Handler<AsyncResult<Boolean>> resultHandler) {
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email.replaceAll("\\[at\\]", "@")).find()) {
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("email.bad.format", locale))));
        } else if (StringUtils.isBlank(email)) {
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("email.required", locale))));
        } else {
            resultHandler.handle(Future.succeededFuture(true));
        }
    }

    @Override
    public void prepareUpsert(JsonObject u, Handler<AsyncResult<JsonObject>> resultHandler) {
        final User user = Json.decodeValue(u.encode(), User.class);
        try {
            user.getAccount().setLogin(user.getAccount().getLogin().toLowerCase());
            if (StringUtils.isNotBlank(user.getAccount().getPasswd())) {
                final byte[] salt;
                salt = encryptionService.generateSalt();
                user.getAccount().setSalt(salt);
                user.getAccount().setPassword(encryptionService.getEncrypted(user.getAccount().getPasswd(), salt));
                user.getAccount().setPasswd(null);
            }
            if (StringUtils.isBlank(user.getAccount().getActivationCode())) {
                final String activationcode = UUID.randomUUID().toString();
                user.getAccount().setActivationCode(activationcode);
                user.getAccount().setActive(false);
                user.getAccount().setFirstConnexion(true);
                user.setTimestamp(System.currentTimeMillis());
                user.set_id(null);
            }
            resultHandler.handle(Future.succeededFuture(new JsonObject(Json.encode(user))));
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(e));
        }
    }

    @Override
    public void getUser(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.getById(id, DBCollections.USER, resultHandler);
    }

    @Override
    public void getUserByLogin(String login, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        // Creation of request
        JsonObject criterias = new JsonObject().put("account.login", login.toLowerCase());
        mongo.findByCriterias(criterias, new CriteriaOption().withLimit(1), DBCollections.USER, jsonArray -> {
            if (jsonArray.succeeded()) {
                if (jsonArray.result().size() == 0) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, Messages.getString("login.wronglogin", locale))));
                } else if (jsonArray.result().size() > 1) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BUSINESS_ERROR, "Plus d'un résultat retourné")));
                } else {
                    resultHandler.handle(Future.succeededFuture(jsonArray.result().getJsonObject(0)));
                }
            } else {
                resultHandler.handle(Future.failedFuture(jsonArray.cause()));
            }
        });
    }

    @Override
    public void getUserInfo(String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        getUser(id, res -> {
            if (res.succeeded()) {
                JsonObject u = res.result();
                u.remove("salt");
                u.remove(PASSWD_FIELD);
                u.remove("password");
                resultHandler.handle(Future.succeededFuture(u));
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void getMeta(String sandboxId, Handler<AsyncResult<JsonObject>> resultHandler) {
         sandBoxService.getSandboxById(sandboxId, sbRes -> {
            if (sbRes.succeeded()) {
                JsonObject meta = sbRes.result();
                seasonService.getCurrentSeason(meta.getString("activityId"), meta.getJsonObject("structure").getJsonObject("country").getString("_id"), season -> {
                    if (season.succeeded()) {
                        meta.put("season", season.result());
                        teamService.getTeamList(meta.getString("_id"), meta.getString("effectiveDefault"), "false", "true", null, teams -> {
                            if (teams.succeeded()) {
                                meta.put("teams", teams.result());
                                activityService.getActivity(meta.getString("activityId"), activity -> {
                                    if (activity.succeeded()) {
                                        meta.put("activity", activity.result());
                                        resultHandler.handle(Future.succeededFuture(meta));
                                    } else {
                                        resultHandler.handle(Future.failedFuture(activity.cause()));
                                    }
                                });
                            } else {
                                resultHandler.handle(Future.failedFuture(teams.cause()));
                            }
                        });
                    } else {
                        resultHandler.handle(Future.failedFuture(season.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(sbRes.cause()));
            }
        });
    }
}
