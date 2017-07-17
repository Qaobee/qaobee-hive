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

package com.qaobee.hive.services.impl;

import com.lowagie.text.pdf.codec.Base64;
import com.qaobee.hive.verticles.MailVerticle;
import com.qaobee.hive.business.model.commons.users.account.Device;
import com.qaobee.hive.dao.PasswordEncryptionService;
import com.qaobee.hive.dao.ReCaptcha;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.dao.impl.TemplatesDAOImpl;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.services.SecurityService;
import com.qaobee.hive.services.UserService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaOption;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.MailUtils;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.VertxContextPRNG;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The type Security service.
 */
@ProxyService(address = SecurityService.ADDRESS, iface = SecurityService.class)
public class SecurityServiceImpl implements SecurityService {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityServiceImpl.class);
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
    private ReCaptcha reCaptcha;
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    private final Vertx vertx;

    /**
     * Instantiates a new Security service.
     *
     * @param vertx the vertx
     */
    public SecurityServiceImpl(Vertx vertx) {
        super();
        this.vertx = vertx;
    }

    private void proceedPasswordReset(String id, String code, String passwd, boolean byPassActivationCode, Handler<AsyncResult<Boolean>> resultHandler) {
        userService.getUser(id, ar -> {
            com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(ar.result().encode(), com.qaobee.hive.business.model.commons.users.User.class);
            if (byPassActivationCode) {
                user.getAccount().setPasswd(passwd);
                this.userService.prepareUpsert(new JsonObject(Json.encode(user)), up -> {
                    if (up.succeeded()) {
                        mongo.upsert(up.result(), DBCollections.USER, upsertRes -> {
                            if (upsertRes.succeeded()) {
                                resultHandler.handle(Future.succeededFuture(true));
                            } else {
                                resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                            }
                        });
                    } else {
                        resultHandler.handle(Future.failedFuture(ar.cause()));
                    }
                });
            } else {
                if (code.equals(user.getAccount().getActivationPasswd())) {
                    user.getAccount().setPasswd(passwd);
                    this.userService.prepareUpsert(new JsonObject(Json.encode(user)), up -> {
                        if (up.succeeded()) {
                            mongo.upsert(up.result(), DBCollections.USER, upsertRes -> {
                                if (upsertRes.succeeded()) {
                                    resultHandler.handle(Future.succeededFuture(true));
                                } else {
                                    resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                                }
                            });
                        } else {
                            resultHandler.handle(Future.failedFuture(ar.cause()));
                        }
                    });
                } else {
                    resultHandler.handle(Future.succeededFuture(false));
                }
            }
        });
    }

    private void doLogin(String login, String password, String locale, String mobileToken, String pushId, String deviceOS, Handler<AsyncResult<JsonObject>> resultHandler) {
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
                    user.getAccount().setToken(VertxContextPRNG.current(vertx).nextString(32));
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
                    mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, upsertRes -> {
                        if (upsertRes.succeeded()) {
                            userService.getUserInfo(user.get_id(), res -> {
                                if (res.succeeded()) {
                                    resultHandler.handle(Future.succeededFuture(res.result()));
                                } else {
                                    resultHandler.handle(Future.failedFuture(res.cause()));
                                }
                            });
                        } else {
                            resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                        }
                    });
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    resultHandler.handle(Future.failedFuture(e));
                }
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale))));
            }
        });
    }

    @Override
    public void loginByToken(String login, String mobileToken, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject cb = new JsonObject()
                .put("account.mobileToken", mobileToken)
                .put(ACCOUNT_LOGIN_FIELD, login.toLowerCase());
        mongo.findByCriterias(cb, new CriteriaOption(), DBCollections.USER, res -> {
            if (res.succeeded()) {
                if (res.result().size() != 1) {
                    resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale))));
                } else {
                    // we take the first one (should be only one)
                    JsonObject jsonPerson = res.result().getJsonObject(0);
                    com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(jsonPerson.encode(), com.qaobee.hive.business.model.commons.users.User.class);
                    user.getAccount().setToken(VertxContextPRNG.current(vertx).nextString(32));
                    user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                    mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, upsertRes -> {
                        if (upsertRes.succeeded()) {
                            resultHandler.handle(Future.succeededFuture(new JsonObject(Json.encode(user))));
                        } else {
                            resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                        }
                    });
                }
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void passwordReset(String reCaptchaChallenge, String id, String code, String passwd, boolean byPassActivationCode, Handler<AsyncResult<Boolean>> resultHandler) {
        if (runtime.getBoolean("recaptcha", false)) {
            reCaptcha.verify(reCaptchaChallenge, captcha -> {
                if (captcha.succeeded()) {
                    proceedPasswordReset(id, code, passwd, byPassActivationCode, resultHandler);
                } else {
                    resultHandler.handle(Future.failedFuture(captcha.cause()));
                }
            });
        } else {
            proceedPasswordReset(id, code, passwd, byPassActivationCode, resultHandler);
        }
    }

    @Override
    public void passwordRenewCheck(String id, String code, Handler<AsyncResult<JsonObject>> resultHandler) {
        userService.getUser(id, ar -> {
            if (ar.succeeded()) {
                com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(ar.result().encode(), com.qaobee.hive.business.model.commons.users.User.class);
                if (code.equals(user.getAccount().getActivationPasswd())) {
                    resultHandler.handle(Future.succeededFuture(new JsonObject()
                            .put("status", true)
                            .put("user", ar.result())));
                } else {
                    resultHandler.handle(Future.succeededFuture(new JsonObject().put("status", false)));
                }
            } else {
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    @Override
    public void passwordRenew(String login, String locale, Handler<AsyncResult<Boolean>> resultHandler) {
        userService.getUserByLogin(login, locale, ar -> {
            if (ar.succeeded()) {
                com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(ar.result().encode(), com.qaobee.hive.business.model.commons.users.User.class);
                user.getAccount().setActivationPasswd(VertxContextPRNG.current(vertx).nextString(32));
                mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, upsertRes -> {
                    if (upsertRes.succeeded()) {
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
                            resultHandler.handle(Future.succeededFuture(true));
                        } catch (QaobeeException e) {
                            LOG.error(e.getMessage(), e);
                            resultHandler.handle(Future.failedFuture(e));
                        }
                    } else {
                        resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    @Override
    public void logout(String token, Handler<AsyncResult<Boolean>> resultHandler) {
        mongo.findByCriterias(new JsonObject().put("account.token", token), new CriteriaOption(), DBCollections.USER, res -> {
            if (res.succeeded()) {
                if (res.result().size() != 1) {
                    resultHandler.handle(Future.succeededFuture(false));
                } else {
                    JsonObject jsonperson = res.result().getJsonObject(0);
                    com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(jsonperson.encode(), com.qaobee.hive.business.model.commons.users.User.class);
                    user.getAccount().setToken(null);
                    user.getAccount().setTokenRenewDate(0L);
                    user.getAccount().setMobileToken(null);
                    mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, upsertRes -> {
                        if (upsertRes.succeeded()) {
                            resultHandler.handle(Future.succeededFuture(true));
                        } else {
                            resultHandler.handle(Future.failedFuture(upsertRes.cause()));
                        }
                    });
                }
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void login(String login, String password, String mobileToken, String pushId, String deviceOS, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale))));
        } else {
            doLogin(login, password, locale, mobileToken, pushId, deviceOS, resultHandler);
        }
    }
}
