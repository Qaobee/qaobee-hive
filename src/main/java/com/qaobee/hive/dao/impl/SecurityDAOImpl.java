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

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.dao.SecurityDAO;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.dao.UserDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.dao.PasswordEncryptionService;
import com.qaobee.hive.technical.utils.MailUtils;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Base64;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.UUID;

/**
 * The type Security dao.
 */
public class SecurityDAOImpl implements SecurityDAO {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityDAOImpl.class);
    private static final String COLLECTION = "User";
    private static final String ACCOUNT_LOGIN_FIELD = "account.login";
    private static final java.lang.String BAD_LOGIN_MESS = "bad.login";

    @Inject
    private MongoDB mongo;
    @Inject
    private MailUtils mailUtils;
    @Inject
    private UserDAO userDAO;
    @Inject
    private TemplatesDAO templatesDAO;
    @Inject
    private PasswordEncryptionService passwordEncryptionService;
    @Inject
    private Vertx vertx;
    @Inject
    @Named("runtime")
    private JsonObject runtime;

    @Override
    public JsonObject loginByToken(String login, String mobileToken, String locale) throws QaobeeException {
        CriteriaBuilder cb = new CriteriaBuilder();
        cb.add("account.mobileToken", mobileToken);
        cb.add(ACCOUNT_LOGIN_FIELD, login.toLowerCase());
        final JsonArray res = mongo.findByCriterias(cb.get(), null, null, 0, 0, COLLECTION);
        if (res.size() != 1) {
            throw new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, login));
        } else {
            // we take the first one (should be only one)
            final JsonObject jsonPerson = res.get(0);
            final User user = Json.decodeValue(jsonPerson.encode(), User.class);
            if (!"paid".equals(user.getAccount().getListPlan().get(0).getStatus()) && !testTrial(user)) {
                user.getAccount().getListPlan().get(0).setStatus("notpaid");
                throw new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale));
            } else {
                user.getAccount().setToken(UUID.randomUUID().toString());
                user.getAccount().setTokenRenewDate(System.currentTimeMillis());
            }
            mongo.save(user);
            return new JsonObject(Json.encode(user));
        }
    }

    @Override
    public boolean passwordReset(String id, String code, String passwd, boolean byPassActivationCode) throws QaobeeException {
        if (runtime.getBoolean("recaptcha", false) && runtime.getObject("captcha") != null) {
            final JsonObject catcha = runtime.getObject("captcha");
            final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
            reCaptcha.setPrivateKey(runtime.getString("recaptcha.pkey"));
            final String challenge = catcha.getString("challenge");
            final String uresponse = catcha.getString("response");
            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(runtime.getString("recaptcha.site"), challenge, uresponse);
            if (!reCaptchaResponse.isValid()) {
                throw new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha");
            }
        }
        final User user = Json.decodeValue(userDAO.getUser(id).encode(), User.class);
        if (byPassActivationCode) {
            user.getAccount().setPasswd(passwd);
            mongo.save(userDAO.prepareUpsert(user));
            return true;
        } else {
            if (code.equals(user.getAccount().getActivationPasswd())) {
                user.getAccount().setPasswd(passwd);
                mongo.save(userDAO.prepareUpsert(user));
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public JsonObject passwordRenewCheck(String id, String code) throws QaobeeException {
        JsonObject jsonUser = userDAO.getUser(id);
        if (jsonUser != null) {
            final User user = Json.decodeValue(jsonUser.encode(), User.class);
            if (code.equals(user.getAccount().getActivationPasswd())) {
                return new JsonObject()
                        .putBoolean("status", true)
                        .putObject("user", jsonUser);
            }
        }
        return null;
    }

    @Override
    public boolean passwordRenew(String login, String locale) throws QaobeeException {
        try {
            final JsonObject jsonperson = userDAO.getUserByLogin(login);
            final User user = Json.decodeValue(jsonperson.encode(), User.class);
            user.getAccount().setActivationPasswd(UUID.randomUUID().toString().replaceAll("-", ""));
            mongo.save(user);
            final JsonObject emailReq = new JsonObject()
                    .putString("from", runtime.getString("mail.from"))
                    .putString("to", user.getContact().getEmail())
                    .putString("subject", Messages.getString("mail.newpasswd.subject", locale))
                    .putString("content_type", "text/html")
                    .putString("body", templatesDAO.generateMail(new JsonObject()
                            .putString(TemplatesDAOImpl.TEMPLATE, "newPasswd.html")
                            .putObject(TemplatesDAOImpl.DATA, mailUtils.generateNewpasswdBody(user, locale))
                    ).getString("result"));
            vertx.eventBus().publish("mailer.mod", emailReq);
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale));
        }
        return true;
    }

    @Override
    public boolean logout(String token) throws QaobeeException {
        final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.token", token).get(), null, null, 0, 0, COLLECTION);
        if (res.size() != 1) {
            return false;
        }
        // we take the first one (should be only one)
        final JsonObject jsonperson = res.get(0);
        final User user = Json.decodeValue(jsonperson.encode(), User.class);
        user.getAccount().setToken(null);
        user.getAccount().setTokenRenewDate(0L);
        user.getAccount().setMobileToken(null);
        mongo.save(user);
        return true;
    }

    @Override
    public JsonObject login(String login, String password, String mobileToken, String locale) throws QaobeeException {
        JsonObject jsonPerson;
        if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            throw new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale));
        }
        try {
            jsonPerson = userDAO.getUserByLogin(login);
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale));
        }
        final User user = Json.decodeValue(jsonPerson.encode(), User.class);
        final byte[] encryptedAttemptedPassword = passwordEncryptionService.getEncryptedPassword(password, user.getAccount().getSalt());
        if (!Base64.encodeBytes(encryptedAttemptedPassword).equals(Base64.encodeBytes(user.getAccount().getPassword()))) {
            throw new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString(BAD_LOGIN_MESS, locale));
        }
        if (!user.getAccount().isActive()) {
            throw new QaobeeException(ExceptionCodes.NON_ACTIVE, Messages.getString("popup.warning.unregistreduser", locale));
        }
        // trial period test
        if (!"paid".equals(user.getAccount().getListPlan().get(0).getStatus()) && !testTrial(user)) {
            user.getAccount().getListPlan().get(0).setStatus("notpaid");
        }
        user.getAccount().setToken(UUID.randomUUID().toString());
        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
        user.getAccount().setMobileToken(mobileToken);
        mongo.save(user);
        return userDAO.getUserInfo(user.get_id());
    }

    /**
     * @param user User
     * @return in trial period
     */
    private boolean testTrial(User user) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(user.getAccount().getListPlan().get(0).getStartPeriodDate());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(user.getAccount().getListPlan().get(0).getStartPeriodDate());
        cal2.add(Calendar.MONTH, runtime.getInteger("trial.duration", 1));
        return "open".equals(user.getAccount().getListPlan().get(0).getStatus()) && cal.before(cal2);
    }
}
