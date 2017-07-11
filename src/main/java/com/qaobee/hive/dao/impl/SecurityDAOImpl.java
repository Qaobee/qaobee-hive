/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.dao.impl;

import com.lowagie.text.pdf.codec.Base64;
import com.qaobee.hive.api.v1.commons.utils.MailVerticle;
import com.qaobee.hive.business.model.commons.users.account.Device;
import com.qaobee.hive.dao.*;
import com.qaobee.hive.services.UserService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.exceptions.QaobeeSvcException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.MailUtils;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

/**
 * The type Security dao.
 */
public class SecurityDAOImpl implements SecurityDAO {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityDAOImpl.class);
    private static final String ACCOUNT_LOGIN_FIELD = "account.login";
    private static final String BAD_LOGIN_MESS = "bad.login";

    @Inject
    private MongoDB mongo;
    @Inject
    private MailUtils mailUtils;
    @Inject
    private UserService userService;
    @Inject
    private TemplatesDAO templatesDAO;
    @Inject
    private PasswordEncryptionService passwordEncryptionService;
    @Inject
    private Vertx vertx;
    @Inject
    private ReCaptcha reCaptcha;
    @Inject
    @Named("runtime")
    private JsonObject runtime;

    @Override
    public Promise<JsonObject, QaobeeException, Integer> loginByToken(String login, String mobileToken, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        CriteriaBuilder cb = new CriteriaBuilder()
                .add("account.mobileToken", mobileToken)
                .add(ACCOUNT_LOGIN_FIELD, login.toLowerCase());
        mongo.findByCriterias(cb.get(), null, null, 0, 0, DBCollections.USER)
                .done(res -> {
                    if (res.size() != 1) {
                        deferred.reject(new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale)));
                    } else {
                        // we take the first one (should be only one)
                        JsonObject jsonPerson = res.getJsonObject(0);
                        com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(jsonPerson.encode(), com.qaobee.hive.business.model.commons.users.User.class);
                        user.getAccount().setToken(UUID.randomUUID().toString());
                        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                        mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER)
                                .done(u -> deferred.resolve(new JsonObject(Json.encode(user))))
                                .fail(deferred::reject);
                    }
                })
                .fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> passwordReset(String reCaptchaChallenge, String id, String code, String passwd, boolean byPassActivationCode) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (runtime.getBoolean("recaptcha", false)) {
            reCaptcha.verify(reCaptchaChallenge)
                    .done(captcha -> proceedPasswordReset(id, code, passwd, byPassActivationCode).done(deferred::resolve).fail(deferred::reject))
                    .fail(deferred::reject);
        } else {
            proceedPasswordReset(id, code, passwd, byPassActivationCode).done(deferred::resolve).fail(deferred::reject);
        }
        return deferred.promise();
    }

    private Promise<Boolean, QaobeeException, Integer> proceedPasswordReset(String id, String code, String passwd, boolean byPassActivationCode) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        userService.getUser(id, ar -> {
            com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(ar.result().encode(), com.qaobee.hive.business.model.commons.users.User.class);
            if (byPassActivationCode) {
                user.getAccount().setPasswd(passwd);
                this.userService.prepareUpsert(new JsonObject(Json.encode(user)), up -> {
                    if (up.succeeded()) {
                        mongo.upsert(up.result(), DBCollections.USER).done(r -> deferred.resolve(true)).fail(deferred::reject);
                    } else {
                        deferred.reject(new QaobeeException(((QaobeeSvcException) ar.cause()).getCode(), ar.cause().getMessage()));
                    }
                });
            } else {
                if (code.equals(user.getAccount().getActivationPasswd())) {
                    user.getAccount().setPasswd(passwd);
                    this.userService.prepareUpsert(new JsonObject(Json.encode(user)), up -> {
                        if (up.succeeded()) {
                            mongo.upsert(up.result(), DBCollections.USER).done(r -> deferred.resolve(true)).fail(deferred::reject);
                        } else {
                            deferred.reject(new QaobeeException(((QaobeeSvcException) ar.cause()).getCode(), ar.cause().getMessage()));
                        }
                    });
                } else {
                    deferred.resolve(false);
                }
            }
        });
        return deferred.promise();

    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> passwordRenewCheck(String id, String code) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        userService.getUser(id, ar -> {
            if (ar.succeeded()) {
                com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(ar.result().encode(), com.qaobee.hive.business.model.commons.users.User.class);
                if (code.equals(user.getAccount().getActivationPasswd())) {
                    deferred.resolve(new JsonObject()
                            .put("status", true)
                            .put("user", ar.result()));
                } else {
                    deferred.resolve(new JsonObject().put("status", false));
                }
            } else {
                deferred.reject(new QaobeeException(((QaobeeSvcException) ar.cause()).getCode(), ar.cause().getMessage()));
            }
        });
        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> passwordRenew(String login, String locale) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        userService.getUserByLogin(login, locale, ar -> {
            if (ar.succeeded()) {
                com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(ar.result().encode(), com.qaobee.hive.business.model.commons.users.User.class);
                user.getAccount().setActivationPasswd(UUID.randomUUID().toString().replaceAll("-", ""));
                mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER).done(res -> {
                    try {
                        JsonObject emailReq = new JsonObject()
                                .put("from", runtime.getString("mail.from"))
                                .put("to", user.getContact().getEmail())
                                .put("subject", Messages.getString("mail.newpasswd.subject", locale))
                                .put("content_type", "text/html")
                                .put("body", templatesDAO.generateMail(new JsonObject()
                                        .put(TemplatesDAOImpl.TEMPLATE, "newPasswd.html")
                                        .put(TemplatesDAOImpl.DATA, mailUtils.generateNewpasswdBody(user, locale))
                                ).getString("result"));
                        vertx.eventBus().publish(MailVerticle.INTERNAL_MAIL, emailReq);
                        deferred.resolve(true);
                    } catch (QaobeeException e) {
                        LOG.error(e.getMessage(), e);
                        deferred.reject(e);
                    }
                }).fail(deferred::reject);
            } else {
                deferred.reject(new QaobeeException(((QaobeeSvcException) ar.cause()).getCode(), ar.cause().getMessage()));
            }
        });
        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> logout(String token) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.findByCriterias(new CriteriaBuilder().add("account.token", token).get(), null, null, 0, 0, DBCollections.USER)
                .done(res -> {
                    if (res.size() != 1) {
                        deferred.resolve(false);
                    } else {
                        JsonObject jsonperson = res.getJsonObject(0);
                        com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(jsonperson.encode(), com.qaobee.hive.business.model.commons.users.User.class);
                        user.getAccount().setToken(null);
                        user.getAccount().setTokenRenewDate(0L);
                        user.getAccount().setMobileToken(null);
                        mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER).done(r -> deferred.resolve(true)).fail(deferred::reject);
                    }
                }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> login(String login, String password, String mobileToken, String pushId, String deviceOS, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            deferred.reject(new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale)));
        } else {
            doLogin(login, password, deferred, locale, mobileToken, pushId, deviceOS);
        }
        return deferred.promise();
    }

    private void doLogin(String login, String password, Deferred<JsonObject, QaobeeException, Integer> deferred, String locale, String mobileToken, String pushId, String deviceOS) {
        userService.getUserByLogin(login, locale, ar -> {
            if (ar.succeeded()) {
                try {
                    com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(ar.result().encode(), com.qaobee.hive.business.model.commons.users.User.class);
                    byte[] encryptedAttemptedPassword = passwordEncryptionService.getEncryptedPassword(password, user.getAccount().getSalt());
                    if (!Base64.encodeBytes(encryptedAttemptedPassword).equals(Base64.encodeBytes(user.getAccount().getPassword()))) {
                        throw new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale));
                    }
                    if (!user.getAccount().isActive()) {
                        throw new QaobeeException(ExceptionCodes.NON_ACTIVE, Messages.getString("popup.warning.unregistreduser", locale));
                    }
                    user.getAccount().setToken(UUID.randomUUID().toString());
                    user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                    user.getAccount().setMobileToken(mobileToken);
                    if (StringUtils.isNotBlank(pushId) && StringUtils.isNotBlank(deviceOS)) {
                        Device d = new Device();
                        d.setId(pushId);
                        d.setOs(deviceOS);
                        if (!user.getAccount().getDevices().contains(d)) {
                            user.getAccount().getDevices().add(d);
                        }
                    }
                    mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER).done(r -> this.userService.getUserInfo(user.get_id(), res -> {
                        if (res.succeeded()) {
                            deferred.resolve(res.result());
                        } else {
                            deferred.reject(new QaobeeException(((QaobeeSvcException) ar.cause()).getCode(), ar.cause().getMessage()));
                        }
                    })).fail(deferred::reject);
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    deferred.reject(e);
                }
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale)));
            }
        });
    }
}
