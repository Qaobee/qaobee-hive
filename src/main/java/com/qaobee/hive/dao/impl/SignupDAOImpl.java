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

import com.qaobee.hive.api.v1.commons.utils.CRMVerticle;
import com.qaobee.hive.api.v1.commons.utils.MailVerticle;
import com.qaobee.hive.business.model.commons.referencial.Structure;
import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.business.model.sandbox.effective.Availability;
import com.qaobee.hive.business.model.sandbox.effective.SB_Effective;
import com.qaobee.hive.business.model.sandbox.effective.SB_Person;
import com.qaobee.hive.business.model.sandbox.effective.SB_Team;
import com.qaobee.hive.business.model.transversal.Contact;
import com.qaobee.hive.business.model.transversal.Member;
import com.qaobee.hive.business.model.transversal.Role;
import com.qaobee.hive.business.model.transversal.Status;
import com.qaobee.hive.dao.*;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.MailUtils;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jdeferred.Deferred;
import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * The type Signup dao.
 */
public class SignupDAOImpl implements SignupDAO {
    private static final Logger LOG = LoggerFactory.getLogger(SignupDAOImpl.class);
    private static final String COUNTRY_FIELD = "country";
    private static final String PARAMERTER_FIELD = "parametersSignup";
    private static final String PARAM_PLAN = "plan";

    private final ActivityDAO activityDAO;
    private final MongoDB mongo;
    private final CountryDAO countryDAO;
    private final UserDAO userDAO;
    private final StructureDAO structureDAO;
    private final ReCaptcha reCaptcha;
    private final JsonObject runtime;
    private final Vertx vertx;
    private static Random rand = new Random();
    private final MailUtils mailUtils;
    private final TemplatesDAO templatesDAO;


    /**
     * Instantiates a new Signup dao.
     *
     * @param activityDAO  the activity dao
     * @param mongo        the mongo
     * @param countryDAO   the country dao
     * @param userDAO      the user dao
     * @param structureDAO the structure dao
     * @param reCaptcha    the re captcha
     * @param runtime      the runtime
     * @param mailUtils    the mail utils
     * @param templatesDAO the templates dao
     * @param vertx        the vertx
     */
    @Inject
    public SignupDAOImpl(ActivityDAO activityDAO, MongoDB mongo, CountryDAO countryDAO, UserDAO userDAO, // NOSONAR
                         StructureDAO structureDAO, ReCaptcha reCaptcha, @Named("runtime") JsonObject runtime,
                         MailUtils mailUtils, TemplatesDAO templatesDAO, Vertx vertx) {
        this.activityDAO = activityDAO;
        this.mongo = mongo;
        this.countryDAO = countryDAO;
        this.userDAO = userDAO;
        this.structureDAO = structureDAO;
        this.reCaptcha = reCaptcha;
        this.mailUtils = mailUtils;
        this.templatesDAO = templatesDAO;
        this.runtime = runtime;
        this.vertx = vertx;
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> finalizeSignup(JsonObject jsonUser, String activationCode, String activityId, JsonObject struct, JsonObject categoryAge, String countryId, String locale) {

        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        // Converts jSon to Bean (extra parameters are ignored)
        User userUpdate = Json.decodeValue(jsonUser.encode(), User.class);
        userUpdate.set_id(jsonUser.getString("_id"));
        updateStructure(struct, activityId).done(structure -> {
            Structure structureObj = Json.decodeValue(structure.encode(), Structure.class);
            CategoryAge categoryAgeObj = Json.decodeValue(categoryAge.encode(), CategoryAge.class);
            getUser(userUpdate.get_id(), locale).done(user -> {
                try {
                    testAccount(user, activationCode, locale);
                    // MaJ User
                    user.getAccount().setActive(false);
                    user.getAccount().setFirstConnexion(true);
                    user.getAccount().setListPlan(userUpdate.getAccount().getListPlan());
                    // récupération des activities des plans
                    List<Promise> promises = new ArrayList<>();
                    user.getAccount().getListPlan().stream().filter(plan -> plan.getActivity() != null).forEachOrdered(plan -> {
                        Deferred<Boolean, QaobeeException, Integer> d = new DeferredObject<>();
                        activityDAO.getActivity(plan.getActivity().get_id()).done(a -> {
                            Activity activity = Json.decodeValue(a.encode(), Activity.class);
                            plan.setActivity(activity);
                            d.resolve(true);
                        });
                        promises.add(d.promise());
                    });
                    DeferredManager dm = new DefaultDeferredManager();
                    dm.when(promises.toArray(new Promise[promises.size()])).done(rs -> {
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
                        sbSandBox.setStructure(structureObj);
                        mongo.upsert(sbSandBox).done(sbSandBoxId -> {
                            sbSandBox.set_id(sbSandBoxId);
                            // $MATCH section
                            JsonObject dbObjectParent = new JsonObject()
                                    .put("activityId", activityId)
                                    .put("countryId", countryId);
                            JsonObject match = new JsonObject().put("$match", dbObjectParent);
                            // $PROJECT section
                            dbObjectParent = new JsonObject()
                                    .put("_id", 0)
                                    .put(PARAMERTER_FIELD, 1);
                            JsonObject project = new JsonObject().put("$project", dbObjectParent);
                            JsonArray pipelineAggregation = new JsonArray().add(match).add(project);
                            mongo.aggregate(pipelineAggregation, DBCollections.ACTIVITY_CFG).done(tabParametersSignup -> {
                                // Création SB_Person
                                List<String> listPersonsId = new ArrayList<>();
                                List<Promise> promisesPlayers = new ArrayList<>();
                                if (tabParametersSignup.size() > 0) {
                                    JsonObject parametersSignup = tabParametersSignup.getJsonObject(0);
                                    if (parametersSignup.containsKey(PARAMERTER_FIELD) && parametersSignup.getJsonObject(PARAMERTER_FIELD).containsKey("players")) {
                                        JsonArray tabPlayers = parametersSignup.getJsonObject(PARAMERTER_FIELD).getJsonArray("players");
                                        for (int i = 0; i < tabPlayers.size(); i++) {
                                            promisesPlayers.add(addPlayer(tabPlayers.getJsonObject(i), structureObj, categoryAgeObj, sbSandBox, listPersonsId));
                                        }
                                    }

                                    dm.when(promisesPlayers.toArray(new Promise[promisesPlayers.size()])).done(rs2 -> {
                                        // Création Effective
                                        SB_Effective sbEffective = new SB_Effective();
                                        sbEffective.setSandboxId(sbSandBox.get_id());
                                        sbEffective.setLabel("Défaut");
                                        sbEffective.setCategoryAge(categoryAgeObj);
                                        // SB_Effective -> members
                                        for (String playerId : listPersonsId) {
                                            Member member = new Member();
                                            member.setRole(new Role("player", "Joueur"));
                                            member.setPersonId(playerId);
                                            sbEffective.addMember(member);
                                        }
                                        mongo.upsert(sbEffective).done(sbEffectiveId -> {
                                            sbEffective.set_id(sbEffectiveId);
                                            sbSandBox.setEffectiveDefault(sbEffective.get_id());
                                            //Add owner in member's list of sandbox
                                            Member member = new Member();
                                            member.setRole(new Role("admin", "Admin"));
                                            member.setPersonId(user.get_id());
                                            member.setStatus("activated");
                                            List<Member> members = new ArrayList<>();
                                            members.add(member);
                                            sbSandBox.setMembers(members);
                                            mongo.upsert(sbSandBox).done(sbId -> {
                                                user.setSandboxDefault(sbId);
                                                mongo.upsert(user).done(userId -> {
                                                    // Création SB_Teams
                                                    // My team
                                                    SB_Team team = new SB_Team();
                                                    team.setEffectiveId(sbEffective.get_id());
                                                    team.setSandboxId(sbSandBox.get_id());
                                                    team.setLabel("Mon équipe");
                                                    team.setEnable(true);
                                                    team.setAdversary(false);
                                                    mongo.upsert(team).done(teamId -> deferred.resolve(new JsonObject(Json.encode(user)))).fail(deferred::reject);
                                                }).fail(deferred::reject);
                                            }).fail(deferred::reject);
                                        }).fail(deferred::reject);
                                    }).fail(e -> {
                                        LOG.error(((Throwable) e.getReject()).getMessage());
                                        deferred.reject(((QaobeeException) e.getReject()));
                                    });
                                } else {
                                    deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "tabParametersSignup is empty"));
                                }
                            }).fail(deferred::reject);
                        }).fail(deferred::reject);
                    }).fail(e -> {
                        LOG.error(((Throwable) e.getReject()).getMessage());
                        deferred.reject(((QaobeeException) e.getReject()));
                    });
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    deferred.reject(e);
                }
            }).fail(deferred::reject);
        }).fail(deferred::reject);
        return deferred.promise();
    }

    private Promise<JsonObject, QaobeeException, Integer> updateStructure(JsonObject structure, String activityId) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (structure.containsKey("_id")) {
            structureDAO.getStructure(structure.getString("_id")).done(s -> {
                structure.mergeIn(s);
                deferred.resolve(structure);
            }).fail(deferred::reject);
        } else {
            countryDAO.getCountryFromAlpha2(structure.getJsonObject(COUNTRY_FIELD).getString("_id").split("-")[2]).done(country -> {
                structure.put(COUNTRY_FIELD, country);
                structure.getJsonObject("address").put(COUNTRY_FIELD, country.getString("label"));
                activityDAO.getActivity(activityId).done(activity -> {
                    structure.put("activity", activity);
                    deferred.resolve(structure);
                }).fail(deferred::reject);
            }).fail(deferred::reject);
        }
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> firstConnectionCheck(String id, String activationCode, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        getUser(id, locale).done(user -> {
            try {
                testAccount(user, activationCode, locale);
                user.getAccount().setToken(UUID.randomUUID().toString());
                user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                // MaJ User
                user.getAccount().setActive(true);
                user.getAccount().setFirstConnexion(false);
                mongo.upsert(user).done(userId -> {
                    vertx.eventBus().send(CRMVerticle.UPDATE, new JsonObject(Json.encode(user)));
                    deferred.resolve(new JsonObject(Json.encode(user)));
                }).fail(deferred::reject);
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
                deferred.reject(e);
            }
        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> accountCheck(String id, String activationCode) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.getById(id, DBCollections.USER).done(u -> {
            final User user = Json.decodeValue(u.encode(), User.class);
            if (user.getAccount().getActivationCode().equals(activationCode)) {
                user.getAccount().setActive(true);
                mongo.upsert(user).done(userId -> deferred.resolve(true)).fail(deferred::reject);
            } else {
                deferred.resolve(false);
            }
        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> register(String reCaptchaChallenge, JsonObject userJson, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        if (runtime.getBoolean("recaptcha") && !"mobile".equals(userJson.getJsonObject("account").getString("origin", "web"))) {
            reCaptcha.verify(reCaptchaChallenge).done(res -> {
                if (!res) {
                    deferred.reject(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha"));
                } else {
                    proceedRegister(userJson, locale).done(deferred::resolve).fail(deferred::reject);
                }
            }).fail(e -> deferred.reject(new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha")));
        } else {
            proceedRegister(userJson, locale).done(deferred::resolve).fail(deferred::reject);
        }
        return deferred.promise();
    }

    private Promise<JsonObject, QaobeeException, Integer> proceedRegister(JsonObject userJson, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        final User user = Json.decodeValue(userJson.encode(), User.class);
        try {
            // Check user informations
            userDAO.checkUserInformations(user, locale);
            // check if email is correct
            userDAO.testEmail(user.getContact().getEmail(), locale);
            userDAO.existingLogin(user.getAccount().getLogin()).done(r -> {
                if (!r) {
                    user.getAccount().setToken(UUID.randomUUID().toString());
                    user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                    user.getAccount().setActive(false);
                    user.getAccount().setLogin(user.getAccount().getLogin().toLowerCase());
                    final Plan plan = Json.decodeValue(userJson.getJsonObject(PARAM_PLAN).encode(), Plan.class);
                    if (user.getAccount().getListPlan() == null) {
                        user.getAccount().setListPlan(new ArrayList<>());
                    }
                    plan.setStatus("open");
                    plan.setStartPeriodDate(System.currentTimeMillis());
                    plan.setAmountPaid(runtime.getJsonObject("plan").getJsonObject(plan.getLevelPlan().name()).getInteger("price"));
                    Calendar gc = GregorianCalendar.getInstance();
                    gc.add(Calendar.MONTH, runtime.getInteger("trial.duration"));
                    plan.setEndPeriodDate(gc.getTimeInMillis());
                    // Si on vient du mobile, on connait le plan, mais pas par le web
                    Deferred<Boolean, QaobeeException, Integer> d = new DeferredObject<>();
                    if (plan.getActivity() != null) {
                        mongo.getById(plan.getActivity().get_id(), DBCollections.ACTIVITY).done(activity -> {
                            plan.setActivity(Json.decodeValue(activity.encode(), Activity.class));
                            d.resolve(true);
                        }).fail(deferred::reject);
                    } else {
                        d.resolve(true);
                    }
                    d.promise().done(res -> {
                        user.getAccount().getListPlan().add(plan);
                        try {
                            mongo.upsert(userDAO.prepareUpsert(user)).done(userId -> {
                                user.set_id(userId);
                                vertx.eventBus().send(CRMVerticle.REGISTER, new JsonObject(Json.encode(user)));
                                mongo.getById(user.get_id(), DBCollections.USER).done(u ->
                                        deferred.resolve(new JsonObject()
                                                .put("person", u)
                                                .put("planId", plan.getPaymentId()))
                                ).fail(deferred::reject);
                            }).fail(deferred::reject);
                        } catch (QaobeeException e) {
                            LOG.error(e.getMessage(), e);
                            deferred.reject(e);
                        }
                    }).fail(deferred::reject);
                } else {
                    deferred.reject(new QaobeeException(ExceptionCodes.NON_UNIQUE_LOGIN, Messages.getString("login.nonunique", locale)));
                }
            }).fail(e -> deferred.reject(new QaobeeException(ExceptionCodes.NON_UNIQUE_LOGIN, Messages.getString("login.nonunique", locale))));
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            deferred.reject(e);
        }
        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> resendMail(String login, String locale) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        userDAO.getUserByLogin(login, locale).done(user -> {
            try {
                sendRegisterMail(user, locale);
                deferred.resolve(true);
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
                deferred.reject(e);
            }
        }).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> sendRegisterMail(JsonObject user, String locale) throws QaobeeException {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        final JsonObject tplReq = new JsonObject()
                .put(TemplatesDAOImpl.TEMPLATE, "newAccount.html")
                .put(TemplatesDAOImpl.DATA, mailUtils.generateActivationBody(Json.decodeValue(user.encode(), User.class), locale));
        final JsonObject emailReq = new JsonObject()
                .put("from", runtime.getString("mail.from"))
                .put("to", user.getJsonObject("contact").getString("email"))
                .put("subject", Messages.getString("mail.account.validation.subject", locale))
                .put("content_type", "text/html")
                .put("body", templatesDAO.generateMail(tplReq).getString("result"));
        vertx.eventBus().send(MailVerticle.INTERNAL_MAIL, emailReq, new DeliveryOptions().setSendTimeout(Constants.TIMEOUT), ar -> {
            if(ar.succeeded()) {
                deferred.resolve(true);
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.MAIL_EXCEPTION, Messages.getString("email.invalid", locale)));
            }
        });
        return deferred.promise();
    }

    private static void testAccount(User user, String activationCode, String locale) throws QaobeeException {
        if (user.getAccount().isActive()) {
            throw new QaobeeException(ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.already.active", locale));
        } else if (!user.getAccount().isFirstConnexion()) {
            throw new QaobeeException(ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.first.done", locale));
        } else if (!user.getAccount().getActivationCode().equals(activationCode)) {
            throw new QaobeeException(ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.activationcode.wrong", locale));
        }
    }

    private Promise<Boolean, QaobeeException, Integer> addPlayer(JsonObject player, Structure structureObj, CategoryAge categoryAgeObj, SB_SandBox sbSandBox, List<String> listPersonsId) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        List<Promise> promises = new ArrayList<>();
        for (int qte = 0; qte < player.getInteger("quantity", 0); qte++) {
            SB_Person sbPerson = new SB_Person();
            sbPerson.setFirstname("Numero " + (listPersonsId.size() + 1));
            sbPerson.setName("Joueur");
            sbPerson.setBirthcity(structureObj.getAddress().getCity());
            sbPerson.setBirthcountry(structureObj.getCountry());
            sbPerson.setBirthdate(randomDate(categoryAgeObj.getAgeMin(), categoryAgeObj.getAgeMax() > 65 ? categoryAgeObj.getAgeMin() : categoryAgeObj.getAgeMax()));
            sbPerson.setNationality(structureObj.getCountry());
            sbPerson.setGender(categoryAgeObj.getGenre());
            sbPerson.setSandboxId(sbSandBox.get_id());
            sbPerson.setContact(new Contact());
            Status status = new Status();
            status.setAvailability(new Availability("available", "available"));
            status.setHeight(rand.nextInt(30) + 150);
            status.setLaterality(Math.random() > 0.5 ? "right-handed" : "left-handed");
            status.setStateForm("good");
            status.setWeight(rand.nextInt(20) + 70);
            sbPerson.setStatus(status);
            sbPerson.getStatus().setSquadnumber(listPersonsId.size() + 1);
            sbPerson.getStatus().setPositionType(player.getString("positionType"));
            promises.add(mongo.upsert(sbPerson));
        }
        DeferredManager dm = new DefaultDeferredManager();
        dm.when(promises.toArray(new Promise[promises.size()]))
                .done(rs -> {
                    rs.forEach(r -> listPersonsId.add((String) r.getResult()));
                    deferred.resolve(true);
                })
                .fail(e -> {
                    LOG.error(((Throwable) e.getReject()).getMessage());
                    deferred.reject(((QaobeeException) e.getReject()));
                });
        return deferred.promise();
    }

    private Promise<User, QaobeeException, Integer> getUser(String id, String locale) {
        Deferred<User, QaobeeException, Integer> deferred = new DeferredObject<>();
        userDAO.getUser(id).done(u -> deferred.resolve(Json.decodeValue(u.encode(), User.class))).fail(e -> deferred.reject(new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("login.wronglogin", locale))));
        return deferred.promise();
    }

    private static long randomDate(int yearOldMin, int yearOldMax) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        if (yearOldMin >= yearOldMax) {
            calendar.add(GregorianCalendar.YEAR, -1 * yearOldMin);
        } else {
            calendar.add(GregorianCalendar.YEAR, -1 * rand.nextInt(yearOldMax - yearOldMin) + yearOldMin);
        }
        calendar.set(GregorianCalendar.DAY_OF_YEAR, rand.nextInt(365));
        return calendar.getTimeInMillis();
    }
}
