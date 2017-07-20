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

import com.qaobee.hive.services.*;
import com.qaobee.hive.verticles.CRMVerticle;
import com.qaobee.hive.verticles.MailVerticle;
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
import com.qaobee.hive.dao.ReCaptcha;
import com.qaobee.hive.dao.impl.TemplatesDAOImpl;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.dao.MailUtils;
import io.vertx.core.*;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.VertxContextPRNG;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * The type Signup dao.
 */
@ProxyService(address = "vertx.Signup.service", iface = SignupService.class)
public class SignupServiceImpl implements SignupService {
    private static final Logger LOG = LoggerFactory.getLogger(SignupServiceImpl.class);
    private static final String COUNTRY_FIELD = "country";
    private static final String PARAMERTER_FIELD = "parametersSignup";
    private static final String PARAM_PLAN = "plan";
    private static final Random RANDOM = new Random();
    private Vertx vertx;

    @Inject
    private ActivityService activityService;
    @Inject
    private MongoDB mongo;
    @Inject
    private CountryService countryService;
    @Inject
    private UserService userService;
    @Inject
    private StructureService structureService;
    @Inject
    private ReCaptcha reCaptcha;
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    private MailUtils mailUtils;
    @Inject
    private TemplatesDAO templatesDAO;

    /**
     * Instantiates a new Signup service.
     *
     * @param vertx the vertx
     */
    public SignupServiceImpl(Vertx vertx) {
        super();
        this.vertx = vertx;
    }

    private void updateStructure(JsonObject structure, String activityId, Handler<AsyncResult<JsonObject>> resultHandler) {
        if (structure.containsKey("_id")) {
            this.structureService.getStructure(structure.getString("_id"), s -> {
                if (s.succeeded()) {
                    structure.mergeIn(s.result());
                    resultHandler.handle(Future.succeededFuture(structure));
                } else {
                    resultHandler.handle(Future.failedFuture(s.cause()));
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
                            resultHandler.handle(Future.succeededFuture(structure));
                        } else {
                            resultHandler.handle(Future.failedFuture(activity.cause()));
                        }
                    });
                } else {
                    resultHandler.handle(Future.failedFuture(country.cause()));
                }
            });
        }
    }

    private void proceedRegister(JsonObject userJson, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        final User user = Json.decodeValue(userJson.encode(), User.class);
        // Check user informations
        userService.checkUserInformations(userJson, locale, ar -> {
            if (ar.succeeded()) {
                // check if email is correct
                checkMail(user, userJson, locale, resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    private void checkMail(User user, JsonObject userJson, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        userService.testEmail(user.getContact().getEmail(), locale, ar2 -> {
            if (ar2.succeeded()) {
                userService.existingLogin(user.getAccount().getLogin(), r -> {
                    if (r.succeeded() && !r.result()) {
                        createUser(user, userJson, resultHandler);
                    } else {
                        resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.NON_UNIQUE_LOGIN, Messages.getString("login.nonunique", locale))));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(ar2.cause()));
            }
        });
    }

    private void createUser(User user, JsonObject userJson, Handler<AsyncResult<JsonObject>> resultHandler) {
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
        Future<Void> d = Future.future();
        if (plan.getActivity() != null) {
            mongo.getById(plan.getActivity().get_id(), DBCollections.ACTIVITY, activity -> {
                if (activity.succeeded()) {
                    plan.setActivity(Json.decodeValue(activity.result().encode(), Activity.class));
                    d.complete();
                } else {
                    resultHandler.handle(Future.failedFuture(activity.cause()));
                }
            });
        } else {
            d.complete();
        }
        d.setHandler(res -> {
            if (res.succeeded()) {
                user.getAccount().getListPlan().add(plan);
                upsertNewUser(user, plan, resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    private void upsertNewUser(User user, Plan plan, Handler<AsyncResult<JsonObject>> resultHandler) {
        this.userService.prepareUpsert(new JsonObject(Json.encode(user)), ar3 -> {
            if (ar3.succeeded()) {
                mongo.upsert(ar3.result(), DBCollections.USER, userId -> {
                    if (userId.succeeded()) {
                        user.set_id(userId.result());
                        vertx.eventBus().send(CRMVerticle.CRMVERTICLE_REGISTER, new JsonObject(Json.encode(user)));
                        mongo.getById(userId.result(), DBCollections.USER, u -> {
                                    if (u.succeeded()) {
                                        resultHandler.handle(Future.succeededFuture(new JsonObject()
                                                .put("person", u.result())
                                                .put("planId", plan.getPaymentId())));
                                    } else {
                                        resultHandler.handle(Future.failedFuture(u.cause()));
                                    }
                                }
                        );
                    } else {
                        resultHandler.handle(Future.failedFuture(userId.cause()));
                    }
                });
            }
        });
    }

    private static void testAccount(User user, String activationCode, String locale, Handler<AsyncResult<Void>> resultHandler) {
        if (user.getAccount().isActive()) {
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.already.active", locale))));
        } else if (!user.getAccount().isFirstConnexion()) {
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.first.done", locale))));
        } else if (!user.getAccount().getActivationCode().equals(activationCode)) {
            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.activationcode.wrong", locale))));
        }
        resultHandler.handle(Future.succeededFuture());
    }

    private Future<Void> addPlayer(JsonObject player, Structure structureObj, CategoryAge categoryAgeObj, SB_SandBox sbSandBox, List<String> listPersonsId) {
        Future<Void> deferred = Future.future();
        List<Future> promises = new ArrayList<>();
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
            Future<String> d = Future.future();
            mongo.upsert(new JsonObject(Json.encode(sbPerson)), DBCollections.PERSON, ar -> {
                if (ar.succeeded()) {
                    d.complete(ar.result());
                } else {
                    d.fail(ar.cause());
                }
            });
            promises.add(d);
        }
        CompositeFuture.all(promises).setHandler(rs -> {
            if (rs.succeeded()) {
                rs.result().list().forEach(r -> listPersonsId.add((String) r));
                deferred.complete();
            } else {
                deferred.fail(rs.cause());
            }
        });
        return deferred;
    }

    private void getUser(String id, String locale, Handler<AsyncResult<User>> resultHandler) {
        userService.getUser(id, ar -> {
            if (ar.succeeded()) {
                resultHandler.handle(Future.succeededFuture(Json.decodeValue(ar.result().encode(), User.class)));
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("login.wronglogin", locale))));
            }
        });
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

    @Override
    public void finalizeSignup(JsonObject jsonUser, String activationCode, String activityId, JsonObject struct, JsonObject categoryAge, String countryId, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        User userUpdate = Json.decodeValue(jsonUser.encode(), User.class);
        userUpdate.set_id(jsonUser.getString("_id"));
        updateStructure(struct, activityId, structure -> {
            if (structure.succeeded()) {
                Structure structureObj = Json.decodeValue(structure.result().encode(), Structure.class);
                CategoryAge categoryAgeObj = Json.decodeValue(categoryAge.encode(), CategoryAge.class);
                getUser(userUpdate.get_id(), locale, userRes -> {
                    if (userRes.succeeded()) {
                        User user = userRes.result();
                        testAccount(user, activationCode, locale, ar -> {
                            if (ar.succeeded()) {
                                updateUser(userUpdate, activityId, countryId, structureObj, categoryAgeObj, user, resultHandler);
                            } else {
                                resultHandler.handle(Future.failedFuture(ar.cause()));
                            }
                        });
                    } else {
                        resultHandler.handle(Future.failedFuture(userRes.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(structure.cause()));
            }
        });
    }


    @Override
    public void firstConnectionCheck(String id, String activationCode, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        getUser(id, locale, userRes -> {
            if (userRes.succeeded()) {
                testAccount(userRes.result(), activationCode, locale, ar -> {
                    if (ar.succeeded()) {
                        User user = userRes.result();
                        user.getAccount().setToken(VertxContextPRNG.current(vertx).nextString(32));
                        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
                        // MaJ User
                        user.getAccount().setActive(true);
                        user.getAccount().setFirstConnexion(false);
                        mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, userId -> {
                            if (userId.succeeded()) {
                                vertx.eventBus().send(CRMVerticle.UPDATE, new JsonObject(Json.encode(user)));
                                resultHandler.handle(Future.succeededFuture(new JsonObject(Json.encode(user))));
                            } else {
                                resultHandler.handle(Future.failedFuture(userId.cause()));
                            }
                        });
                    } else {
                        resultHandler.handle(Future.failedFuture(ar.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(userRes.cause()));
            }
        });
    }

    @Override
    public void accountCheck(String id, String activationCode, Handler<AsyncResult<Boolean>> resultHandler) {
        mongo.getById(id, DBCollections.USER, userRes -> {
            if (userRes.succeeded()) {
                final User user = Json.decodeValue(userRes.result().encode(), User.class);
                if (user.getAccount().getActivationCode().equals(activationCode)) {
                    user.getAccount().setActive(true);
                    mongo.upsert(new JsonObject(Json.encode(user)), DBCollections.USER, userId -> {
                        if (userId.succeeded()) {
                            resultHandler.handle(Future.succeededFuture(true));
                        } else {
                            resultHandler.handle(Future.failedFuture(userId.cause()));
                        }
                    });
                } else {
                    resultHandler.handle(Future.succeededFuture(false));
                }
            } else {
                resultHandler.handle(Future.failedFuture(userRes.cause()));
            }
        });
    }

    @Override
    public void register(String reCaptchaChallenge, JsonObject userJson, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        if (runtime.getBoolean("recaptcha") && !"mobile".equals(userJson.getJsonObject("account").getString("origin", "web"))) {
            reCaptcha.verify(reCaptchaChallenge, res -> {
                if (res.succeeded()) {
                    proceedRegister(userJson, locale, resultHandler);
                } else {
                    resultHandler.handle(Future.failedFuture(res.cause()));
                }
            });
        } else {
            proceedRegister(userJson, locale, resultHandler);
        }
    }

    @Override
    public void resendMail(String login, String locale, Handler<AsyncResult<Void>> resultHandler) {
        userService.getUserByLogin(login, locale, ar -> {
            if (ar.succeeded()) {
                try {
                    sendRegisterMail(ar.result(), locale, resultHandler);
                    resultHandler.handle(Future.succeededFuture());
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                    resultHandler.handle(Future.failedFuture(e));
                }
            } else {
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    @Override
    public void sendRegisterMail(JsonObject user, String locale, Handler<AsyncResult<Void>> resultHandler) {
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
                resultHandler.handle(Future.succeededFuture());
            } else {
                resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.MAIL_EXCEPTION, Messages.getString("email.invalid", locale))));
            }
        });
    }

    private void updateUser(User userUpdate, String activityId, String countryId, Structure structureObj, CategoryAge categoryAgeObj, User user, Handler<AsyncResult<JsonObject>> resultHandler) {
        // MaJ User
        user.getAccount().setActive(false);
        user.getAccount().setFirstConnexion(true);
        user.getAccount().setListPlan(userUpdate.getAccount().getListPlan());
        // récupération des activities des plans
        List<Future> promises = new ArrayList<>();
        user.getAccount().getListPlan().stream().filter(plan -> plan.getActivity() != null).forEachOrdered(plan -> {
            Future<Void> d = Future.future();
            activityService.getActivity(plan.getActivity().get_id(), a -> {
                if (a.succeeded()) {
                    Activity activity = Json.decodeValue(a.result().encode(), Activity.class);
                    plan.setActivity(activity);
                    d.complete();
                } else {
                    d.fail(a.cause());
                }
            });
            promises.add(d);
        });
        CompositeFuture.all(promises).setHandler(rs -> {
            if (rs.succeeded()) {
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
                createSandBox(activityId, countryId, sbSandBox, structureObj, categoryAgeObj, user, resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(rs.cause()));
            }
        });
    }

    private void createPlayers(JsonArray tabParametersSignup, Structure structureObj, CategoryAge categoryAgeObj, SB_SandBox sbSandBox, User user, Handler<AsyncResult<JsonObject>> resultHandler) {
        List<String> listPersonsId = new ArrayList<>();
        List<Future> promisesPlayers = new ArrayList<>();
        JsonObject parametersSignup = tabParametersSignup.getJsonObject(0);
        if (parametersSignup.containsKey(PARAMERTER_FIELD) && parametersSignup.getJsonObject(PARAMERTER_FIELD).containsKey("players")) {
            JsonArray tabPlayers = parametersSignup.getJsonObject(PARAMERTER_FIELD).getJsonArray("players");
            for (int i = 0; i < tabPlayers.size(); i++) {
                promisesPlayers.add(addPlayer(tabPlayers.getJsonObject(i), structureObj, categoryAgeObj, sbSandBox, listPersonsId));
            }
        }

        CompositeFuture.all(promisesPlayers).setHandler(rs2 -> {
            if (rs2.succeeded()) {
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
                createEffective(sbEffective, sbSandBox, user, resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(rs2.cause()));
            }
        });
    }

    private void createEffective(SB_Effective sbEffective, SB_SandBox sbSandBox, User user, Handler<AsyncResult<JsonObject>> resultHandler) {
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
                updateSandbox(sbEffective, sbSandBox, user, resultHandler);
            } else {
                resultHandler.handle(Future.failedFuture(sbEffectiveId.cause()));
            }
        });
    }

    private void updateSandbox(SB_Effective sbEffective, SB_SandBox sbSandBox, User user, Handler<AsyncResult<JsonObject>> resultHandler) {
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
                        createNewTeam(team, user, resultHandler);
                    } else {
                        resultHandler.handle(Future.failedFuture(userId.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(sbId.cause()));
            }
        });
    }

    private void createSandBox(String activityId, String countryId, SB_SandBox sbSandBox, Structure structureObj, CategoryAge categoryAgeObj, User user, Handler<AsyncResult<JsonObject>> resultHandler) {
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
                mongo.aggregate(pipelineAggregation, DBCollections.ACTIVITY_CFG, tabParametersSignup -> {
                    if (tabParametersSignup.succeeded()) {
                        // Création SB_Person
                        if (tabParametersSignup.result().size() > 0) {
                            createPlayers(tabParametersSignup.result(), structureObj, categoryAgeObj, sbSandBox, user, resultHandler);
                        } else {
                            resultHandler.handle(Future.failedFuture(new QaobeeException(ExceptionCodes.DATA_ERROR, "tabParametersSignup is empty")));
                        }
                    } else {
                        resultHandler.handle(Future.failedFuture(tabParametersSignup.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(sbSandBoxId.cause()));
            }
        });
    }

    private void createNewTeam(SB_Team team, User user, Handler<AsyncResult<JsonObject>> resultHandler) {
        mongo.upsert(new JsonObject(Json.encode(team)), DBCollections.TEAM, teamId -> {
            if (teamId.succeeded()) {
                resultHandler.handle(Future.succeededFuture(new JsonObject(Json.encode(user))));
            } else {
                resultHandler.handle(Future.failedFuture(teamId.cause()));
            }
        });
    }
}
