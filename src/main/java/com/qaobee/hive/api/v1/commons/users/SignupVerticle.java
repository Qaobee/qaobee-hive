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
package com.qaobee.hive.api.v1.commons.users;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.api.v1.commons.communication.NotificationsVerticle;
import com.qaobee.hive.api.v1.commons.utils.TemplatesVerticle;
import com.qaobee.hive.business.commons.settings.ActivityBusiness;
import com.qaobee.hive.business.commons.settings.CountryBusiness;
import com.qaobee.hive.business.commons.users.UsersBusiness;
import com.qaobee.hive.business.model.commons.referencial.Structure;
import com.qaobee.hive.business.model.commons.settings.*;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBoxCfg;
import com.qaobee.hive.business.model.sandbox.effective.Availability;
import com.qaobee.hive.business.model.sandbox.effective.SB_Effective;
import com.qaobee.hive.business.model.sandbox.effective.SB_Person;
import com.qaobee.hive.business.model.sandbox.effective.SB_Team;
import com.qaobee.hive.business.model.transversal.Contact;
import com.qaobee.hive.business.model.transversal.Member;
import com.qaobee.hive.business.model.transversal.Role;
import com.qaobee.hive.business.model.transversal.Status;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.AuthCheck;
import com.qaobee.hive.technical.utils.MailUtils;
import com.qaobee.hive.technical.utils.PersonUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.*;

/**
 * The Class SignupVerticle.
 *
 * @author Xavier MARIN         <ul>         <li>resthandler.register : Register a new accunt</li>         <li>resthandler.logintest : Login unicity test for rest request</li>         <li>loginExists : Login unicity test for internal use</li>         <li>resthandler.accountcheck : email validation number check</li>         </ul>
 */
@DeployableVerticle
public class SignupVerticle extends AbstractGuiceVerticle {
    /**
     * The Constant REGISTER.
     */
    public static final String REGISTER = Module.VERSION + ".commons.users.signup.register";
    /**
     * The Constant LOGIN_TEST.
     */
    public static final String LOGIN_TEST = Module.VERSION + ".commons.users.signup.logintest";
    /**
     * The Constant LOGIN_EXISTS.
     */
    public static final String LOGIN_EXISTS = Module.VERSION + ".commons.users.signup.loginExists";
    /**
     * The Constant ACCOUNT_CHECK.
     */
    public static final String ACCOUNT_CHECK = Module.VERSION + ".commons.users.signup.accountcheck";
    /**
     * The Constant FIRST_CONNECTION_CHECK
     */
    public static final String FIRST_CONNECTION_CHECK = Module.VERSION + ".commons.users.signup.firstconnectioncheck";
    /**
     * The Constant FINALIZE_SIGNUP
     */
    public static final String FINALIZE_SIGNUP = Module.VERSION + ".commons.users.signup.finalize";
    /**
     * Parameter ID
     */
    public static final String PARAM_ID = "id";
    /**
     * Parameter CODE
     */
    public static final String PARAM_CODE = "code";
    /**
     * Parameter USER
     */
    public static final String PARAM_USER = "user";
    /**
     * Parameter STRUCTURE
     */
    public static final String PARAM_STRUCTURE = "structure";
    /**
     * Parameter ACTIVITY
     */
    public static final String PARAM_ACTIVITY = "activity";
    /**
     * Parameter Category Age
     */
    public static final String PARAM_CATEGORY_AGE = "categoryAge";
    /**
     * Parameter Mobile
     */
    public static final String PARAM_MOBILE = "mobile";
    /**
     * Parameter Captcha
     */
    public static final String PARAM_CAPTCHA = "captcha";
    /**
     * Parameter jUnit
     */
    public static final String PARAM_JUNIT = "junit";
    /**
     * Parameter Login
     */
    public static final String PARAM_LOGIN = "login";
    /**
     * The constant COUNTRY_FIELD.
     */
    public static final String COUNTRY_FIELD = "country";
    /**
     * The constant PARAMERTER_FIELD.
     */
    public static final String PARAMERTER_FIELD = "parametersSignup";
    /**
     * Parameter Plan
     */
    public static final String PARAM_PLAN = "plan";
    private static final Logger LOG = LoggerFactory.getLogger(SignupVerticle.class);
    @Inject
    private MongoDB mongo;
    @Inject
    private MailUtils mailUtils;
    @Inject
    private AuthCheck authCheck;
    @Inject
    private Utils utils;
    @Inject
    private PersonUtils personUtils;
    @Inject
    private UsersBusiness usersBusiness;
    @Inject
    private CountryBusiness countryBusiness;
    @Inject
    private ActivityBusiness activityBusiness;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(LOGIN_EXISTS, this::existingLoginHandler)
                .registerHandler(LOGIN_TEST, this::loginTestHandler)
                .registerHandler(REGISTER, this::registerHandler)
                .registerHandler(ACCOUNT_CHECK, this::accountCheckHandler)
                .registerHandler(FIRST_CONNECTION_CHECK, this::firstConnectionCheckHandler)
                .registerHandler(FINALIZE_SIGNUP, this::finalizeSignupHandler);
    }

    /**
     * @apiDescription Finalizes signup
     * @api {get} /api/1/commons/users/signup/finalizesignup Account finalizes signup
     * @apiParam {Object} user the user
     * @apiParam {String} activationCode The activation code
     * @apiParam {Object} structure The structure
     * @apiParam {Object} activity the activity
     * @apiVersion 0.1.0
     * @apiName finalizeSignupHandler
     * @apiGroup Signup API
     * @apiSuccess {Object} user {"status", true|false}
     * @apiError HTTP_ERROR wrong request's method
     */
    @Rule(address = FINALIZE_SIGNUP, method = Constants.POST, logged = true,
            mandatoryParams = {PARAM_USER, PARAM_CODE, PARAM_ACTIVITY, PARAM_STRUCTURE, PARAM_CATEGORY_AGE},
            scope = Rule.Param.BODY)
    private void finalizeSignupHandler(Message<String> message) { 
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            final JsonObject jsonReq = new JsonObject(req.getBody());
            // JSon User
            final JsonObject jsonUser = jsonReq.getObject(PARAM_USER);
            // Converts jSon to Bean (extra parameters are ignored)
            User userUpdate = Json.decodeValue(jsonUser.encode(), User.class);
            // FIXME : to JRO : Est-ce utile?
            userUpdate.set_id(jsonUser.getString("_id"));
            // Code activation
            final String activationCode = jsonReq.getString(PARAM_CODE);
            // JSon Activity
            final String activityId = jsonReq.getString(PARAM_ACTIVITY);
            // JSon Structure
            JsonObject structure = jsonReq.getObject(PARAM_STRUCTURE);
            if (jsonReq.getObject(PARAM_STRUCTURE).containsField("_id")) {
                structure = mongo.getById(jsonReq.getObject(PARAM_STRUCTURE).getString("_id"), Structure.class);
            } else {
                // FIXME : to JRO : Mais alpha2 n'existe pas !!!
                Country country = countryBusiness.getCountryFromAlpha2(structure.getObject("address").getObject(COUNTRY_FIELD).getString("alpha2"));
                structure.putObject(COUNTRY_FIELD, new JsonObject(Json.encode(country)));
                structure.getObject("address").putString(COUNTRY_FIELD, country.getLabel());
                structure.putObject("activity", new JsonObject(Json.encode(activityBusiness.getActivityFromId(activityId))));
            }
            Structure structureObj = Json.decodeValue(structure.encode(), Structure.class);
            // JSon Category Age
            JsonObject categoryAge = jsonReq.getObject(PARAM_CATEGORY_AGE);
            CategoryAge categoryAgeObj = Json.decodeValue(categoryAge.encode(), CategoryAge.class);
            // Country
            final String countryId = jsonReq.getString(COUNTRY_FIELD, "CNTR-250-FR-FRA");
            User user = getUser(userUpdate.get_id(), message, req.getLocale());
            if (user != null) {
                if (user.getAccount().isActive()) {
                    utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.already.active", req.getLocale()));
                    return;
                } else if (!user.getAccount().isFirstConnexion()) {
                    utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.first.done", req.getLocale()));
                    return;
                } else if (!user.getAccount().getActivationCode().equals(activationCode)) {
                    utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.activationcode.wrong", req.getLocale()));
                    return;
                }
                // MaJ User
                user.getAccount().setActive(true);
                user.getAccount().setFirstConnexion(false);
                user.getAccount().setListPlan(userUpdate.getAccount().getListPlan());
                // récupération des activities des plans
                user.getAccount().getListPlan().stream().filter(plan -> plan.getActivity() != null).forEachOrdered(plan -> {
                    Activity activity = activityBusiness.getActivityFromId(plan.getActivity().get_id());
                    if (activity != null) {
                        plan.setActivity(activity);
                    }
                });
                user.setBirthdate(userUpdate.getBirthdate());
                user.setContact(userUpdate.getContact());
                user.setCountry(structureObj.getCountry());
                user.setNationality(structureObj.getCountry());
                user.setFirstname(userUpdate.getFirstname());
                user.setGender(userUpdate.getGender());
                user.setName(userUpdate.getName());
                user.setAddress(userUpdate.getAddress());

                // Création Sandbox
                SB_SandBox sbSandBox = new SB_SandBox();
                sbSandBox.setActivityId(activityId);
                sbSandBox.setOwner(user.get_id());
                sbSandBox.set_id(mongo.save(sbSandBox));

                JsonArray tabParametersSignup;
                DBObject match;
                DBObject project;
                // $MATCH section
                BasicDBObject dbObjectParent = new BasicDBObject();
                dbObjectParent.put("activityId", activityId);
                dbObjectParent.put("countryId", countryId);
                match = new BasicDBObject("$match", dbObjectParent);
                // $PROJECT section
                dbObjectParent = new BasicDBObject();
                dbObjectParent.put("_id", 0);
                dbObjectParent.put(PARAMERTER_FIELD, 1);
                project = new BasicDBObject("$project", dbObjectParent);
                List<DBObject> pipelineAggregation = Arrays.asList(match, project);
                tabParametersSignup = mongo.aggregate(PARAMERTER_FIELD, pipelineAggregation, ActivityCfg.class);

                // Création SB_Person
                List<String> listPersonsId = new ArrayList<>();
                if (tabParametersSignup != null && tabParametersSignup.size() > 0) {
                    JsonObject parametersSignup = tabParametersSignup.get(0);
                    if (parametersSignup.containsField(PARAMERTER_FIELD) && parametersSignup.getObject(PARAMERTER_FIELD).containsField("players")) {
                        JsonArray tabPlayers = parametersSignup.getObject(PARAMERTER_FIELD).getArray("players");
                        for (int i = 0; i < tabPlayers.size(); i++) {
                            addPlayer(tabPlayers.get(i), structureObj, categoryAgeObj, sbSandBox, listPersonsId);
                        }
                    }
                }

                // Création SandBoxCfg
                SB_SandBoxCfg sbSandBoxCfg = new SB_SandBoxCfg();
                sbSandBoxCfg.setActivity(activityBusiness.getActivityFromId(activityId));
                sbSandBoxCfg.setSandbox(sbSandBox);
                sbSandBoxCfg.setStructure(structureObj);
                // Search Season
                Map<String, Object> criterias = new HashMap<>();
                criterias.put("activityId", activityId);
                criterias.put("countryId", countryId);
                JsonArray resultJson = mongo.findByCriterias(criterias, null, "endDate", -1, -1, Season.class);
                long currentDate = System.currentTimeMillis();
                if (resultJson == null || resultJson.size() == 0) {
                    throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No season defined for (" + activityId + " / " + countryId + ")");
                }
                for (int i = 0; i < resultJson.size(); i++) {
                    JsonObject s = resultJson.get(i);
                    if (s.getLong("endDate", 0) > currentDate && s.getLong("startDate") < currentDate) {
                        sbSandBoxCfg.setSeason(Json.decodeValue(s.encode(), Season.class));
                        break;
                    }
                }
                // Sauvegarde SB_Cfg
                sbSandBoxCfg.set_id(mongo.save(sbSandBoxCfg));
                // Sauvegarde Sandbox avec ID sandbox Cfg
                sbSandBox.setSandboxCfgId(sbSandBoxCfg.get_id());
                mongo.save(sbSandBox);
                // Création Sandbox Effective
                SB_Effective sbEffective = new SB_Effective();
                sbEffective.setSandBoxCfgId(sbSandBoxCfg.get_id());
                sbEffective.setLabel("Défaut");
                sbEffective.setCategoryAge(categoryAgeObj);
                // SB_Effective -> members
                for (String playerId : listPersonsId) {
                    Member member = new Member();
                    member.setRole(new Role("player", "Joueur"));
                    member.setPersonId(playerId);
                    sbEffective.addMember(member);
                }
                sbEffective.set_id(mongo.save(sbEffective));
                user.setEffectiveDefault(sbEffective.get_id());
                // Création SB_Teams
                // My team
                SB_Team team = new SB_Team();
                team.setEffectiveId(sbEffective.get_id());
                team.setSandboxId(sbSandBox.get_id());
                team.setLabel("Mon équipe");
                team.setEnable(true);
                team.setAdversary(false);
                mongo.save(team);
                mongo.save(user);
                JsonObject notification = new JsonObject();
                notification.putString("id", user.get_id());
                notification.putString("target", User.class.getSimpleName());
                notification.putObject("notification", new JsonObject()
                        .putString("content", Messages.getString("first.connection.notification.content"))
                        .putString("title", Messages.getString("first.connection.notification.title"))
                        .putString("senderId", getContainer().config().getObject(RUNTIME).getString("admin.id"))
                );
                vertx.eventBus().send(NotificationsVerticle.NOTIFY, notification);
                message.reply(Json.encode(user));
            }
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    private void addPlayer(JsonObject player, Structure structureObj, CategoryAge categoryAgeObj, SB_SandBox sbSandBox, List<String> listPersonsId) throws QaobeeException {
        for (int qte = 0; qte < player.getInteger("quantity", 0); qte++) {
            SB_Person sbPerson = new SB_Person();
            sbPerson.setFirstname("Numero " + (listPersonsId.size() + 1));
            sbPerson.setName("Joueur");
            sbPerson.setBirthcity(structureObj.getAddress().getCity());
            sbPerson.setBirthcountry(structureObj.getCountry());
            sbPerson.setBirthdate(utils.randomDate(categoryAgeObj.getAgeMin(), categoryAgeObj.getAgeMax() > 65 ? categoryAgeObj.getAgeMin() : categoryAgeObj.getAgeMax()));
            sbPerson.setNationality(structureObj.getCountry());
            sbPerson.setGender(categoryAgeObj.getGenre());
            sbPerson.setSandboxId(sbSandBox.get_id());
            sbPerson.setContact(new Contact());

            Status status = new Status();
            status.setAvailability(new Availability("available", "available"));
            status.setHeight((int) Math.round(Math.random() * 30) + 150);
            status.setLaterality(Math.random() > 0.5 ? "right-handed" : "left-handed");
            status.setStateForm("good");
            status.setWeight((int) Math.round(Math.random() * 20) + 70);
            sbPerson.setStatus(status);

            sbPerson.getStatus().setSquadnumber(listPersonsId.size() + 1);
            sbPerson.getStatus().setPositionType(player.getString("positionType"));
            listPersonsId.add(mongo.save(sbPerson));
        }
    }

    /**
     * @apiDescription First connection account check
     * @api {get} /api/1/commons/users/signup/firstconnectioncheck Account validation check
     * @apiParam {String} code Activation code
     * @apiParam {String} id Person id
     * @apiVersion 0.1.0
     * @apiName accountCheckHandler
     * @apiGroup Signup API
     * @apiSuccess {Object} status {"status", true|false}
     * @apiError HTTP_ERROR wrong request's method
     */
    @Rule(address = FIRST_CONNECTION_CHECK, method = Constants.GET, mandatoryParams = {PARAM_ID, PARAM_CODE}, scope = Rule.Param.REQUEST)
    private void firstConnectionCheckHandler(Message<String> message) { 
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            Map<String, List<String>> params = req.getParams();
            final String id = params.get(PARAM_ID).get(0);
            final String activationCode = params.get(PARAM_CODE).get(0);
            User user = getUser(id, message, req.getLocale());
            if (user != null) {
                if (user.getAccount().isActive()) {
                    utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.already.active", req.getLocale()));
                } else if (!user.getAccount().isFirstConnexion()) {
                    utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.first.done", req.getLocale()));
                } else if (!user.getAccount().getActivationCode().equals(activationCode)) {
                    utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.activationcode.wrong", req.getLocale()));
                } else {
                    user.getAccount().setToken(UUID.randomUUID().toString());
                    user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                    mongo.save(user);
                    message.reply(Json.encode(user));
                }
            }
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.DATA_ERROR, e.getMessage());
        }
    }

    /**
     * @apiDescription Account validation check
     * @api {get} /api/1/commons/users/signup/accountcheck Account validation check
     * @apiParam {String} code Activation code
     * @apiParam {String} id Person id
     * @apiVersion 0.1.0
     * @apiName accountCheckHandler
     * @apiGroup Signup API
     * @apiSuccess {Object} status {"status", true|false}
     * @apiError HTTP_ERROR wrong request's method
     */
    @Rule(address = ACCOUNT_CHECK, method = Constants.GET, mandatoryParams = {"id", "code"}, scope = Rule.Param.REQUEST)
    private void accountCheckHandler(Message<String> message) { 
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            final String id = req.getParams().get("id").get(0);
            final String activationCode = req.getParams().get("code").get(0);
            final User user = Json.decodeValue(mongo.getById(id, User.class).encode(), User.class);
            if (user.getAccount().getActivationCode().equals(activationCode)) {
                user.getAccount().setActive(true);
                mongo.save(user);
                utils.sendStatus(true, message);
            } else {
                utils.sendStatus(false, message);
            }
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendStatus(false, message);
        }
    }

    /**
     * @apiDescription Register a new account
     * @api {put} /api/1/commons/users/signup/register Register a new account
     * @apiVersion 0.1.0
     * @apiName registerHandler
     * @apiGroup Signup API
     * @apiParam {Object} person com.qaobee.swarn.business.model.tranversal.person.Person
     * @apiSuccess {Object} person com.qaobee.swarn.business.model.tranversal.person.Person
     * @apiError HTTP_ERROR wrong request's method
     * @apiError PASSWD_EXCEPTION Password encoding exception
     * @apiError NON_UNIQUE_LOGIN Non unique login
     * @apiError MAIL_EXCEPTION problème d'envoi d'email
     */
    @Rule(address = REGISTER, method = Constants.PUT)
    private void registerHandler(Message<String> message) { 
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            // Gets JSon request
            final JsonObject json = new JsonObject(req.getBody());
            // Captcha management
            final boolean bypassCaptcha = json.getBoolean(PARAM_JUNIT, json.getBoolean(PARAM_MOBILE, false));
            final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
            reCaptcha.setPrivateKey(getContainer().config().getObject(RUNTIME).getString("recaptcha.pkey"));
            ReCaptchaResponse reCaptchaResponse = null;
            if (!bypassCaptcha) {
                reCaptchaResponse = reCaptcha
                        .checkAnswer(getContainer().config().getObject(RUNTIME).getString("recaptcha.site"),
                                json.getObject(PARAM_CAPTCHA).getString("challenge"), json.getObject(PARAM_CAPTCHA).getString("response"));
            }
            // If captcha needed and wrong captcha : Error and end transaction
            if (!bypassCaptcha && !reCaptchaResponse.isValid()) {
                utils.sendError(message, ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha");
                return;
            }
            final User user = Json.decodeValue(json.encode(), User.class);
            // Check user informations
            usersBusiness.checkUserInformations(user, req.getLocale());
            // check if email is correct
            if (authCheck.testEmail(user.getContact().getEmail(), req.getLocale())) {
                // unique login test
                vertx.eventBus().send(LOGIN_EXISTS, new JsonObject().putString(PARAM_LOGIN, user.getAccount().getLogin()), (Handler<Message<JsonObject>>) usernameExistResp -> {
                    if (usernameExistResp.body().getBoolean("status", false)) {
                        utils.sendError(message, new QaobeeException(ExceptionCodes.NON_UNIQUE_LOGIN, Messages.getString("login.nonunique", req.getLocale())));
                    } else {
                        try { 
                            user.getAccount().setActive(false);
                            user.getAccount().setLogin(user.getAccount().getLogin().toLowerCase());
                            final Plan plan = Json.decodeValue(json.getObject(PARAM_PLAN).encode(), Plan.class);
                            if (user.getAccount().getListPlan() == null) {
                                user.getAccount().setListPlan(new ArrayList<>());
                            }
                            plan.setStatus("open");
                            plan.setStartPeriodDate(System.currentTimeMillis());
                            // Si on vient du mobile, on connait le plan, mais pas par le web
                            if (plan.getActivity() != null) {
                                JsonObject activity = mongo.getById(plan.getActivity().get_id(), Activity.class);
                                plan.setActivity(Json.decodeValue(activity.encode(), Activity.class));
                            }
                            user.getAccount().getListPlan().add(plan);
                            final String id = mongo.save(personUtils.prepareUpsert(user));
                            if (id != null) {
                                user.set_id(id);

                                final JsonObject tplReq = new JsonObject();
                                tplReq.putString(TemplatesVerticle.TEMPLATE, "newAccount.html");
                                tplReq.putObject(TemplatesVerticle.DATA, mailUtils.generateActivationBody(user, req.getLocale(), getContainer().config()));

                                vertx.eventBus().send(TemplatesVerticle.TEMPLATE_GENERATE, tplReq, (Handler<Message<JsonObject>>) tplResp -> {
                                    final String tplRes = tplResp.body().getString("result");
                                    final JsonObject emailReq = new JsonObject();
                                    emailReq.putString("from", getContainer().config().getObject(RUNTIME).getString("mail.from"));
                                    emailReq.putString("to", user.getContact().getEmail());
                                    emailReq.putString("subject", Messages.getString("mail.account.validation.subject"));
                                    emailReq.putString("content_type", "text/html");
                                    emailReq.putString("body", tplRes);
                                    vertx.eventBus().publish("mailer.mod", emailReq);
                                    final JsonObject res = new JsonObject();
                                    try { 
                                        res.putObject("person", mongo.getById(id, User.class));
                                        res.putString("planId", plan.getPaymentId());
                                        message.reply(res.encode());
                                    } catch (final EncodeException e) {
                                        LOG.error(e.getMessage(), e);
                                        utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                                    } catch (final QaobeeException e) {
                                        LOG.error(e.getMessage(), e);
                                        utils.sendError(message, e);
                                    }
                                });
                            }
                        } catch (final QaobeeException e) {
                            LOG.error(e.getMessage(), e);
                            utils.sendError(message, e);
                        }
                    }
                });
            }
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        } catch (final IllegalArgumentException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.MAIL_EXCEPTION, e.getMessage());
        }
    }

    /**
     * @apiDescription Login unicity test for rest request
     * @api {get} /api/1/commons/users/signup/loginExists Login unicity test
     * @apiVersion 0.1.0
     * @apiName userNameTestHandler
     * @apiGroup Signup API
     * @apiParam {String} [login] person.account.login
     * @apiSuccess {Object} status {"status", true|false}
     * @apiError HTTP_ERROR wrong request's method
     */
    @Rule(address = LOGIN_TEST, method = Constants.POST, mandatoryParams = {PARAM_LOGIN}, scope = Rule.Param.BODY)
    private void loginTestHandler(Message<String> message) { 
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        final String login = new JsonObject(req.getBody()).getString(PARAM_LOGIN).toLowerCase();
        final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", login).get(), null, null, 0, 0, User.class);
        utils.sendStatus(res.size() > 0, message);
    }

    /**
     * @apiDescription Test the existence of a username in the db
     * @api {get} /api/1/commons/users/signup/logintest
     * @apiVersion 0.1.0
     * @apiName userNameExistHandler
     * @apiGroup Signup API
     * @apiParam {String} [login] person.account.login
     * @apiSuccess {Object} status {"status", true|false}
     * @apiError HTTP_ERROR wrong request's method
     */
    private void existingLoginHandler(Message<JsonObject> message) { 
        final String login = message.body().getString(PARAM_LOGIN).toLowerCase();
        final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", login).get(), null, null, 0, 0, User.class);
        if (res.size() > 0) {
            utils.sendStatusJson(true, message);
        } else {
            utils.sendStatusJson(false, message);
        }
    }

    /**
     * @param id      User id
     * @param message VertX message
     * @return user or null
     */
    private User getUser(String id, Message<String> message, String locale) {
        try {
            return Json.decodeValue(mongo.getById(id, User.class).encode(), User.class);
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.BAD_LOGIN, Messages.getString("user.not.exist", locale));
            return null;
        }
    }
}
