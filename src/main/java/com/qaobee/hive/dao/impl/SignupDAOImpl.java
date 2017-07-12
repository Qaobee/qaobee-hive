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
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
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
import com.qaobee.hive.dao.ReCaptcha;
import com.qaobee.hive.dao.SignupDAO;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.services.ActivityService;
import com.qaobee.hive.services.CountryService;
import com.qaobee.hive.services.StructureService;
import com.qaobee.hive.services.UserService;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.exceptions.QaobeeSvcException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.MailUtils;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.VertxContextPRNG;
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
    private final static Random RANDOM = new Random();

    private final ActivityService activityService;
    private final MongoDB mongo;
    private final CountryService countryService;
    private final UserService userService;
    private final StructureService structureService;
    private final ReCaptcha reCaptcha;
    private final JsonObject runtime;
    private final Vertx vertx;
    private final MailUtils mailUtils;
    private final TemplatesDAO templatesDAO;


    /**
     * Instantiates a new Signup dao.
     *
     * @param activityService  the activity dao
     * @param mongo            the mongo
     * @param countryService   the country dao
     * @param userService      the user dao
     * @param structureService the structure dao
     * @param reCaptcha        the re captcha
     * @param runtime          the runtime
     * @param mailUtils        the mail utils
     * @param templatesDAO     the templates dao
     * @param vertx            the vertx
     */
    @Inject
    public SignupDAOImpl(ActivityService activityService, MongoDB mongo, CountryService countryService, UserService userService, // NOSONAR
                         StructureService structureService, ReCaptcha reCaptcha, @Named("runtime") JsonObject runtime,
                         MailUtils mailUtils, TemplatesDAO templatesDAO, Vertx vertx) {
        this.activityService = activityService;
        this.mongo = mongo;
        this.countryService = countryService;
        this.userService = userService;
        this.structureService = structureService;
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
        com.qaobee.hive.business.model.commons.users.User userUpdate = Json.decodeValue(jsonUser.encode(), com.qaobee.hive.business.model.commons.users.User.class);
        userUpdate.set_id(jsonUser.getString("_id"));
        updateStructure(struct, activityId).done(structure -> {
            com.qaobee.hive.business.model.commons.referencial.Structure structureObj = Json.decodeValue(structure.encode(), com.qaobee.hive.business.model.commons.referencial.Structure.class);
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
                        activityService.getActivity(plan.getActivity().get_id(), a -> {
                            if (a.succeeded()) {
                                com.qaobee.hive.business.model.commons.settings.Activity activity = Json.decodeValue(a.result().encode(), com.qaobee.hive.business.model.commons.settings.Activity.class);
                                plan.setActivity(activity);
                                d.resolve(true);
                            }
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
                        mongo.upsert(new JsonObject(Json.encode(sbSandBox)), DBCollections.SANDBOX, sbSandBoxId -> {
                            if (sbSandBoxId.succeeded()) {
                                sbSandBox.set_id(sbSandBoxId.result());
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
                                            mongo.upsert(new JsonObject(Json.encode(sbEffective)), DBCollections.EFFECTIVE, sbEffectiveId -> {
                                                if (sbEffectiveId.succeeded()) {
                                                    sbEffective.set_id(sbEffectiveId.result());
                                                    sbSandBox.setEffectiveDefault(sbEffective.get_id());
                                                    //Add owner in member's list of sandbox
                                                    Member member = new Member();
                                                    member.setRole(new Role("admin", "Admin"));
                                                    member.setPersonId(user.get_id());
                                                    member.setStatus("activated");
                                                    List<Member> members = new ArrayList<>();
                                                    members.add(member);
                                                    sbSandBox.setMembers(members);
                                                    mongo.upsert(new JsonObject(Json.encode(sbSandBox)), DBCollections.SANDBOX, sbId -> {
                                                        if (sbId.succeeded()) {
                                                            user.setSandboxDefault(sbId.result());
                                                            mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, userId -> {
                                                                if (userId.succeeded()) {
                                                                    // Création SB_Teams
                                                                    // My team
                                                                    SB_Team team = new SB_Team();
                                                                    team.setEffectiveId(sbEffective.get_id());
                                                                    team.setSandboxId(sbSandBox.get_id());
                                                                    team.setLabel("Mon équipe");
                                                                    team.setEnable(true);
                                                                    team.setAdversary(false);
                                                                    mongo.upsert(new JsonObject(Json.encode(team)), DBCollections.TEAM, teamId -> {
                                                                        if (teamId.succeeded()) {
                                                                            deferred.resolve(new JsonObject(Json.encode(user)));
                                                                        } else {
                                                                            deferred.reject(new QaobeeException(((QaobeeSvcException) teamId.cause()).getCode(), teamId.cause()));
                                                                        }
                                                                    });
                                                                } else {
                                                                    deferred.reject(new QaobeeException(((QaobeeSvcException) userId.cause()).getCode(), userId.cause()));
                                                                }
                                                            });
                                                        } else {
                                                            deferred.reject(new QaobeeException(((QaobeeSvcException) sbId.cause()).getCode(), sbId.cause()));
                                                        }
                                                    });
                                                } else {
                                                    deferred.reject(new QaobeeException(((QaobeeSvcException) sbEffectiveId.cause()).getCode(), sbEffectiveId.cause()));
                                                }
                                            });
                                        }).fail(e -> {
                                            LOG.error(((Throwable) e.getReject()).getMessage());
                                            deferred.reject((QaobeeException) e.getReject());
                                        });
                                    } else {
                                        deferred.reject(new QaobeeException(ExceptionCodes.DATA_ERROR, "tabParametersSignup is empty"));
                                    }
                                }).fail(deferred::reject);
                            } else {
                                deferred.reject(new QaobeeException(((QaobeeSvcException) sbSandBoxId.cause()).getCode(), sbSandBoxId.cause()));
                            }
                        });
                    }).fail(e -> {
                        LOG.error(((Throwable) e.getReject()).getMessage());
                        deferred.reject((QaobeeException) e.getReject());
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
            this.structureService.getStructure(structure.getString("_id"), s -> {
                if (s.succeeded()) {
                    structure.mergeIn(s.result());
                    deferred.resolve(structure);
                } else {
                    deferred.reject(new QaobeeException(((QaobeeSvcException) s.cause()).getCode(), s.cause()));
                }
            });
        } else {
            countryService.getCountryFromAlpha2(structure.getJsonObject(COUNTRY_FIELD).getString("_id").split("-")[2], country -> {
                if (country.succeeded()) {
                    structure.put(COUNTRY_FIELD, country.result());
                    structure.getJsonObject("address").put(COUNTRY_FIELD, country.result().getString("label"));
                    activityService.getActivity(activityId, activity -> {
                        if (activity.succeeded()) {
                            structure.put("activity", activity.result());
                            deferred.resolve(structure);
                        } else {
                            deferred.reject(new QaobeeException(((QaobeeSvcException) activity.cause()).getCode(), activity.cause()));
                        }
                    });
                } else {
                    deferred.reject(new QaobeeException(((QaobeeSvcException) country.cause()).getCode(), country.cause()));
                }
            });
        }
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> firstConnectionCheck(String id, String activationCode, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        getUser(id, locale).done(user -> {
            try {
                testAccount(user, activationCode, locale);
                user.getAccount().setToken(VertxContextPRNG.current(vertx).nextString(32));
                user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                // MaJ User
                user.getAccount().setActive(true);
                user.getAccount().setFirstConnexion(false);
                mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, userId -> {
                    if(userId.succeeded()) {
                        vertx.eventBus().send(CRMVerticle.UPDATE, new JsonObject(Json.encode(user)));
                        deferred.resolve(new JsonObject(Json.encode(user)));
                    } else {
                        deferred.reject(new QaobeeException(((QaobeeSvcException) userId.cause()).getCode(), userId.cause()));
                    }
                });
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
            final com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(u.encode(), com.qaobee.hive.business.model.commons.users.User.class);
            if (user.getAccount().getActivationCode().equals(activationCode)) {
                user.getAccount().setActive(true);
                mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, userId -> {
                    if(userId.succeeded()) {
                        deferred.resolve(true);
                    } else {
                        deferred.reject(new QaobeeException(((QaobeeSvcException) userId.cause()).getCode(), userId.cause()));
                    }
                });
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
        final com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(userJson.encode(), com.qaobee.hive.business.model.commons.users.User.class);
        // Check user informations
        userService.checkUserInformations(userJson, locale, ar -> {
            if (ar.succeeded()) {
                // check if email is correct
                userService.testEmail(user.getContact().getEmail(), locale, ar2 -> {
                    if (ar2.succeeded()) {
                        userService.existingLogin(user.getAccount().getLogin(), r -> {
                            if (r.succeeded() && !r.result()) {
                                user.getAccount().setToken(VertxContextPRNG.current(vertx).nextString(32));
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
                                        plan.setActivity(Json.decodeValue(activity.encode(), com.qaobee.hive.business.model.commons.settings.Activity.class));
                                        d.resolve(true);
                                    }).fail(deferred::reject);
                                } else {
                                    d.resolve(true);
                                }
                                d.promise().done(res -> {
                                    user.getAccount().getListPlan().add(plan);
                                    this.userService.prepareUpsert(new JsonObject(Json.encode(user)), ar3 -> {
                                        if (ar3.succeeded()) {
                                            mongo.upsert(ar3.result(), DBCollections.USER, userId -> {
                                                if(userId.succeeded()) {
                                                    user.set_id(userId.result());
                                                    vertx.eventBus().send(CRMVerticle.CRMVERTICLE_REGISTER, new JsonObject(Json.encode(user)));
                                                    mongo.getById(userId.result(), DBCollections.USER).done(u ->
                                                            deferred.resolve(new JsonObject()
                                                                    .put("person", u)
                                                                    .put("planId", plan.getPaymentId()))
                                                    ).fail(deferred::reject);
                                                } else {
                                                    deferred.reject(new QaobeeException(((QaobeeSvcException) userId.cause()).getCode(), userId.cause()));
                                                }
                                            });
                                        }
                                    });
                                }).fail(deferred::reject);
                            } else {
                                deferred.reject(new QaobeeException(ExceptionCodes.NON_UNIQUE_LOGIN, Messages.getString("login.nonunique", locale)));
                            }
                        });
                    } else {
                        deferred.reject(new QaobeeException(((QaobeeSvcException) ar2.cause()).getCode(), ar2.cause().getMessage()));
                    }
                });
            } else {
                deferred.reject(new QaobeeException(((QaobeeSvcException) ar.cause()).getCode(), ar.cause().getMessage()));
            }
        });

        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> resendMail(String login, String locale) {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        userService.getUserByLogin(login, locale, ar -> {
            if (ar.succeeded()) {
                try {
                    sendRegisterMail(ar.result(), locale);
                    deferred.resolve(true);
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    deferred.reject(e);
                }
            } else {
                deferred.reject(new QaobeeException(((QaobeeSvcException) ar.cause()).getCode(), ar.cause().getMessage()));
            }
        });
        return deferred.promise();
    }

    @Override
    public Promise<Boolean, QaobeeException, Integer> sendRegisterMail(JsonObject user, String locale) throws QaobeeException {
        Deferred<Boolean, QaobeeException, Integer> deferred = new DeferredObject<>();
        final JsonObject tplReq = new JsonObject()
                .put(TemplatesDAOImpl.TEMPLATE, "newAccount.html")
                .put(TemplatesDAOImpl.DATA, mailUtils.generateActivationBody(Json.decodeValue(user.encode(), com.qaobee.hive.business.model.commons.users.User.class), locale));
        final JsonObject emailReq = new JsonObject()
                .put("from", runtime.getString("mail.from"))
                .put("to", user.getJsonObject("contact").getString("email"))
                .put("subject", Messages.getString("mail.account.validation.subject", locale))
                .put("content_type", "text/html")
                .put("body", templatesDAO.generateMail(tplReq).getString("result"));
        vertx.eventBus().send(MailVerticle.INTERNAL_MAIL, emailReq, new DeliveryOptions().setSendTimeout(Constants.TIMEOUT), ar -> {
            if (ar.succeeded()) {
                deferred.resolve(true);
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.MAIL_EXCEPTION, Messages.getString("email.invalid", locale)));
            }
        });
        return deferred.promise();
    }

    private static void testAccount(com.qaobee.hive.business.model.commons.users.User user, String activationCode, String locale) throws QaobeeException {
        if (user.getAccount().isActive()) {
            throw new QaobeeException(ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.already.active", locale));
        } else if (!user.getAccount().isFirstConnexion()) {
            throw new QaobeeException(ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.first.done", locale));
        } else if (!user.getAccount().getActivationCode().equals(activationCode)) {
            throw new QaobeeException(ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.activationcode.wrong", locale));
        }
    }

    private Promise<Boolean, QaobeeException, Integer> addPlayer(JsonObject player, com.qaobee.hive.business.model.commons.referencial.Structure structureObj, CategoryAge categoryAgeObj, SB_SandBox sbSandBox, List<String> listPersonsId) {
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
            status.setHeight(RANDOM.nextInt(30) + 150);
            status.setLaterality(Math.random() > 0.5 ? "right-handed" : "left-handed");
            status.setStateForm("good");
            status.setWeight(RANDOM.nextInt(20) + 70);
            sbPerson.setStatus(status);
            sbPerson.getStatus().setSquadnumber(listPersonsId.size() + 1);
            sbPerson.getStatus().setPositionType(player.getString("positionType"));
            Deferred<String, QaobeeException, Integer> d = new DeferredObject<>();
            mongo.upsert(new JsonObject(Json.encode(sbPerson)), DBCollections.PERSON, ar->{
                if(ar.succeeded()) {
                   d.resolve(ar.result());
                }
            });
            promises.add(d);
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

    private Promise<com.qaobee.hive.business.model.commons.users.User, QaobeeException, Integer> getUser(String id, String locale) {
        Deferred<com.qaobee.hive.business.model.commons.users.User, QaobeeException, Integer> deferred = new DeferredObject<>();
        userService.getUser(id, ar -> {
            if (ar.succeeded()) {
                deferred.resolve(Json.decodeValue(ar.result().encode(), com.qaobee.hive.business.model.commons.users.User.class));
            } else {
                deferred.reject(new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("login.wronglogin", locale)));
            }
        });
        return deferred.promise();
    }

    private static long randomDate(int yearOldMin, int yearOldMax) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        if (yearOldMin >= yearOldMax) {
            calendar.add(GregorianCalendar.YEAR, -1 * yearOldMin);
        } else {
            calendar.add(GregorianCalendar.YEAR, -1 * RANDOM.nextInt(yearOldMax - yearOldMin) + yearOldMin);
        }
        calendar.set(GregorianCalendar.DAY_OF_YEAR, RANDOM.nextInt(365));
        return calendar.getTimeInMillis();
    }
}
