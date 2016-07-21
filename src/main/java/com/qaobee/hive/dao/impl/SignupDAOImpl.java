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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
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
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

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

    @Inject
    private ActivityDAO activityDAO;
    @Inject
    private MongoDB mongo;
    @Inject
    private CountryDAO countryDAO;
    @Inject
    private UserDAO userDAO;
    @Inject
    private StructureDAO structureDAO;
    @Inject
    @Named("runtime")
    private JsonObject runtime;

    @Override
    public JsonObject finalizeSignup(JsonObject jsonUser, String activationCode, String activityId, JsonObject structure, JsonObject categoryAge, String countryId, String locale) throws QaobeeException {
        // Converts jSon to Bean (extra parameters are ignored)
        User userUpdate = Json.decodeValue(jsonUser.encode(), User.class);
        // FIXME : to JRO : Est-ce utile?
        userUpdate.set_id(jsonUser.getString("_id"));
        if (structure.containsField("_id")) {
            structure.mergeIn(structureDAO.getStructure(structure.getString("_id")));
        } else {
            JsonObject country = countryDAO.getCountryFromAlpha2(structure.getObject(COUNTRY_FIELD).getString("_id").split("-")[2]);
            structure.putObject(COUNTRY_FIELD, country);
            structure.getObject("address").putString(COUNTRY_FIELD, country.getString("label"));
            structure.putObject("activity", activityDAO.getActivity(activityId));
        }
        Structure structureObj = Json.decodeValue(structure.encode(), Structure.class);
        CategoryAge categoryAgeObj = Json.decodeValue(categoryAge.encode(), CategoryAge.class);
        User user = getUser(userUpdate.get_id(), locale);
        testAccount(user, activationCode, locale);
        // MaJ User
        user.getAccount().setActive(true);
        user.getAccount().setFirstConnexion(false);
        user.getAccount().setListPlan(userUpdate.getAccount().getListPlan());
        // récupération des activities des plans
        user.getAccount().getListPlan().stream().filter(plan -> plan.getActivity() != null).forEachOrdered(plan -> {
            Activity activity = null;
            try {
                activity = Json.decodeValue(activityDAO.getActivity(plan.getActivity().get_id()).encode(), Activity.class);
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
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
        sbSandBox.setStructure(structureObj);
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
        tabParametersSignup = mongo.aggregate(PARAMERTER_FIELD, pipelineAggregation, DBCollections.ACTIVITY_CFG);

        // Création SB_Person
        List<String> listPersonsId = new ArrayList<>();
        if (tabParametersSignup.size() > 0) {
            JsonObject parametersSignup = tabParametersSignup.get(0);
            if (parametersSignup.containsField(PARAMERTER_FIELD) && parametersSignup.getObject(PARAMERTER_FIELD).containsField("players")) {
                JsonArray tabPlayers = parametersSignup.getObject(PARAMERTER_FIELD).getArray("players");
                for (int i = 0; i < tabPlayers.size(); i++) {
                    addPlayer(tabPlayers.get(i), structureObj, categoryAgeObj, sbSandBox, listPersonsId);
                }
            }
        }
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
        sbEffective.set_id(mongo.save(sbEffective));
        sbSandBox.setEffectiveDefault(sbEffective.get_id());
        mongo.save(sbSandBox);
        user.setSandboxDefault(sbSandBox.get_id());
        mongo.save(user);
        // Création SB_Teams
        // My team
        SB_Team team = new SB_Team();
        team.setEffectiveId(sbEffective.get_id());
        team.setSandboxId(sbSandBox.get_id());
        team.setLabel("Mon équipe");
        team.setEnable(true);
        team.setAdversary(false);
        mongo.save(team);
        return new JsonObject(Json.encode(user));
    }

    @Override
    public JsonObject firstConnectionCheck(String id, String activationCode, String locale) throws QaobeeException {
        User user = getUser(id, locale);
        testAccount(user, activationCode, locale);
        user.getAccount().setToken(UUID.randomUUID().toString());
        user.getAccount().setTokenRenewDate(System.currentTimeMillis());
        mongo.save(user);
        return new JsonObject(Json.encode(user));
    }

    @Override
    public boolean accountCheck(String id, String activationCode) throws QaobeeException {
        final User user = Json.decodeValue(mongo.getById(id, DBCollections.USER).encode(), User.class);
        if (user.getAccount().getActivationCode().equals(activationCode)) {
            user.getAccount().setActive(true);
            mongo.save(user);
            return true;
        }
        return false;
    }

    @Override
    public JsonObject register(JsonObject reCaptchaJson, JsonObject userJson, String locale) throws QaobeeException {
        final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey(runtime.getString("recaptcha.pkey"));
        if (runtime.getBoolean("recaptcha")) {
            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(runtime.getString("recaptcha.site"),
                    reCaptchaJson.getString("challenge"),
                    reCaptchaJson.getString("response"));
            if(!reCaptchaResponse.isValid()) {
                throw new QaobeeException(ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha");
            }
        }
        final User user = Json.decodeValue(userJson.encode(), User.class);
        // Check user informations
        userDAO.checkUserInformations(user, locale);
        // check if email is correct
        userDAO.testEmail(user.getContact().getEmail(), locale);
        if(userDAO.existingLogin(user.getAccount().getLogin())) {
            throw new QaobeeException(ExceptionCodes.NON_UNIQUE_LOGIN, Messages.getString("login.nonunique", locale));
        }
        user.getAccount().setActive(false);
        user.getAccount().setLogin(user.getAccount().getLogin().toLowerCase());
        final Plan plan = Json.decodeValue(userJson.getObject(PARAM_PLAN).encode(), Plan.class);
        if (user.getAccount().getListPlan() == null) {
            user.getAccount().setListPlan(new ArrayList<>());
        }
        plan.setStatus("open");
        plan.setStartPeriodDate(System.currentTimeMillis());
        // Si on vient du mobile, on connait le plan, mais pas par le web
        if (plan.getActivity() != null) {
            JsonObject activity = mongo.getById(plan.getActivity().get_id(), DBCollections.ACTIVITY);
            plan.setActivity(Json.decodeValue(activity.encode(), Activity.class));
        }
        user.getAccount().getListPlan().add(plan);
            user.set_id(mongo.save(userDAO.prepareUpsert(user)));
        return new JsonObject()
                .putObject("person", mongo.getById(user.get_id(), DBCollections.USER))
                .putString("planId", plan.getPaymentId());
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

    private void addPlayer(JsonObject player, Structure structureObj, CategoryAge categoryAgeObj, SB_SandBox sbSandBox, List<String> listPersonsId) throws QaobeeException {
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

    private User getUser(String id, String locale) throws QaobeeException {
        try {
            return Json.decodeValue(userDAO.getUser(id).encode(), User.class);
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.BAD_LOGIN, Messages.getString("login.wronglogin", locale));
        }
    }

    private static long randomDate(int yearOldMin, int yearOldMax) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        if (yearOldMin >= yearOldMax) {
            calendar.add(GregorianCalendar.YEAR, -1 * yearOldMin);
        } else {
            calendar.add(GregorianCalendar.YEAR, -1 * ((int) Math.round(Math.random() * (yearOldMax - yearOldMin)) + yearOldMin));
        }
        calendar.set(GregorianCalendar.DAY_OF_YEAR, (int) Math.round(Math.random() * 365));
        return calendar.getTimeInMillis();
    }
}
