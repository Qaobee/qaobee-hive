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
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.tools.Params;
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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

/**
 * The Class SignupVerticle.
 *
 * @author Xavier MARIN
 *         <ul>
 *         <li>resthandler.register : Register a new accunt</li>
 *         <li>resthandler.logintest : Login unicity test for rest request</li>
 *         <li>loginExists : Login unicity test for internal use</li>
 *         <li>resthandler.accountcheck : email validation number check</li>
 *         </ul>
 */
@DeployableVerticle public class SignupVerticle extends AbstractGuiceVerticle {
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
	 * Parameter Plan
	 */
	public static final String PARAM_PLAN = "plan";
	private static final Logger LOG = LoggerFactory.getLogger(SignupVerticle.class);
	// MongoDB driver
	@Inject private MongoDB mongo;
	@Inject private MailUtils mailUtils;
	@Inject private AuthCheck authCheck;
	@Inject private Utils utils;
	@Inject private PersonUtils personUtils;
	@Inject private UsersBusiness usersBusiness;
	@Inject private CountryBusiness countryBusiness;
	@Inject private ActivityBusiness activityBusiness;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.vertx.java.platform.Verticle#start()
	 */
	@Override public void start() {
		super.start();
		LOG.debug(this.getClass().getName() + " started");

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
		vertx.eventBus().registerHandler(LOGIN_EXISTS, new Handler<Message<JsonObject>>() {
			/*
			 * (non-Javadoc)
			 *
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override public void handle(final Message<JsonObject> message) {
				final JsonObject jsonReq = new JsonObject(message.body().encode());
				final String login = jsonReq.getString(PARAM_LOGIN);
				final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", login).get(), null, null, 0, 0, User.class);
				if (res.size() > 0) {
					utils.sendStatusJson(true, message);
				} else {
					utils.sendStatusJson(false, message);
				}
			}
		});

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
		vertx.eventBus().registerHandler(LOGIN_TEST, new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 *
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override public void handle(final Message<String> message) {
				final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
				try {
					utils.testHTTPMetod(Constantes.POST, req.getMethod());
					final JsonObject jsonReq = new JsonObject(req.getBody());
					if (!jsonReq.containsField("login")) {
						utils.sendStatus(false, message);
					} else {
						final String login = jsonReq.getString("login");
						final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", login).get(), null, null, 0, 0, User.class);
						if (res.size() > 0) {
							utils.sendStatus(true, message);
						} else {
							utils.sendStatus(false, message);
						}
					}
				} catch (final NoSuchMethodException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				}
			}
		});

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
		vertx.eventBus().registerHandler(REGISTER, new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 *
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override public void handle(final Message<String> message) {
				final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
				try {
					utils.testHTTPMetod(Constantes.PUT, req.getMethod());

					// Gets JSon request
					final JsonObject json = new JsonObject(req.getBody());

					// Captcha management
					final boolean bypassCaptcha = json.getBoolean(PARAM_JUNIT, json.getBoolean(PARAM_MOBILE, false));
					final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
					reCaptcha.setPrivateKey(Params.getString("recaptcha.pkey"));

					ReCaptchaResponse reCaptchaResponse = null;
					if (!bypassCaptcha) {
						reCaptchaResponse = reCaptcha
								.checkAnswer(Params.getString("recaptcha.site"), json.getObject(PARAM_CAPTCHA).getString("challenge"), json.getObject(PARAM_CAPTCHA).getString("response"));
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
						vertx.eventBus().send(LOGIN_EXISTS, new JsonObject().putString(PARAM_LOGIN, user.getAccount().getLogin()), new Handler<Message<JsonObject>>() {

							@Override public void handle(final Message<JsonObject> usernameExistResp) {
								if (usernameExistResp.body().getBoolean("status", false)) {
									utils.sendError(message, new QaobeeException(ExceptionCodes.NON_UNIQUE_LOGIN, Messages.getString("login.nonunique", req.getLocale())));
								} else {
									try { // NOSONAR
										user.getAccount().setActive(false);
										final Plan plan = Json.decodeValue(json.getObject(PARAM_PLAN).encode(), Plan.class);
										if (user.getAccount().getListPlan() == null) {
											user.getAccount().setListPlan(new ArrayList<Plan>());
										}
										plan.setPaymentId(UUID.randomUUID().toString());
										plan.setStatus("open");
										plan.setAmountPaid(Long.parseLong(Params.getString("plan." + plan.getLevelPlan().name() + ".price")) / 100L);
										plan.setStartPeriodDate(System.currentTimeMillis());
										// Si on vient du mobile, on connait le plan, mais pas par le web
										if (plan.getActivity() != null) {
											JsonObject activity = mongo.getById(plan.getActivity().get_id(), Activity.class);
											plan.setActivity(Json.<Activity>decodeValue(activity.encode(), Activity.class));
										}
										user.getAccount().getListPlan().add(plan);

										final String id = mongo.save(personUtils.prepareUpsert(user));
										if (id != null) {
											user.set_id(id);

											final JsonObject tplReq = new JsonObject();
											tplReq.putString(TemplatesVerticle.TEMPLATE, "newAccount.html");
											tplReq.putObject(TemplatesVerticle.DATA, mailUtils.generateActivationBody(user, req.getLocale()));

											vertx.eventBus().send(TemplatesVerticle.TEMPLATE_GENERATE, tplReq, new Handler<Message<JsonObject>>() {
												@Override public void handle(final Message<JsonObject> tplResp) {
													final String tplRes = tplResp.body().getString("result");
													final JsonObject emailReq = new JsonObject();
													emailReq.putString("from", Params.getString("mail.from"));
													emailReq.putString("to", user.getContact().getEmail());
													emailReq.putString("subject", Messages.getString("mail.account.validation.subject"));
													emailReq.putString("content_type", "text/html");
													emailReq.putString("body", tplRes);

													// Envoi du mail si pas en test jUnit
													if (json.getBoolean("junit", false)) {
														LOG.debug(emailReq.encode());
													} else {
														vertx.eventBus().publish("mailer.mod", emailReq);
													}

													final JsonObject res = new JsonObject();
													try { // NOSONAR
														res.putObject("person", mongo.getById(id, User.class));
														res.putString("planId", plan.getPaymentId());
														LOG.debug(res.encode());
														message.reply(res.encode());
													} catch (final EncodeException e) {
														LOG.error(e.getMessage(), e);
														utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
													} catch (final QaobeeException e) {
														LOG.error(e.getMessage(), e);
														utils.sendError(message, e);
													}
												}
											});
										}
									} catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
										LOG.error(e.getMessage(), e);
										utils.sendError(message, ExceptionCodes.PASSWD_EXCEPTION, e.getMessage());
									} catch (final EncodeException e) {
										LOG.error(e.getMessage(), e);
										utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
									} catch (final QaobeeException e) {
										LOG.error(e.getMessage(), e);
										utils.sendError(message, e);
									}
								}
							}
						});
					}
				} catch (final QaobeeException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, e);
				} catch (final NoSuchMethodException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final IllegalArgumentException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MAIL_EXCEPTION, e.getMessage());
				}
			}
		});

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
		vertx.eventBus().registerHandler(ACCOUNT_CHECK, new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 *
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override public void handle(final Message<String> message) {
				final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
				try {
					utils.testHTTPMetod(Constantes.GET, req.getMethod());
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

				} catch (final NoSuchMethodException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final EncodeException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
				} catch (final QaobeeException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
				}
			}
		});

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
		vertx.eventBus().registerHandler(FIRST_CONNECTION_CHECK, new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 *
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override public void handle(final Message<String> message) {
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.GET, req.getMethod());
					Map<String, List<String>> params = req.getParams();
					utils.testMandatoryParams(params, PARAM_ID, PARAM_CODE);

					final String id = params.get(PARAM_ID).get(0);
					final String activationCode = params.get(PARAM_CODE).get(0);

					final User user = Json.decodeValue(mongo.getById(id, User.class).encode(), User.class);
					if (user == null) {
						utils.sendError(message, ExceptionCodes.BAD_LOGIN, Messages.getString("user.not.exist", req.getLocale()));
					} else if (user.getAccount().isActive()) {
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
						utils.sendStatus(true, message);
					}

				} catch (final NoSuchMethodException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final EncodeException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
				} catch (final QaobeeException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
				}
			}
		});

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
		vertx.eventBus().registerHandler(FINALIZE_SIGNUP, new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 *
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override public void handle(final Message<String> message) {
				final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
				try {
					utils.testHTTPMetod(Constantes.POST, req.getMethod());
					utils.isUserLogged(req);
					final JsonObject jsonReq = new JsonObject(req.getBody());

					utils.testMandatoryParams(jsonReq.toMap(), PARAM_USER, PARAM_CODE, PARAM_ACTIVITY, PARAM_STRUCTURE, PARAM_CATEGORY_AGE);

					// JSon User
					final JsonObject jsonUser = jsonReq.getObject(PARAM_USER);
					// Converts jSon to Bean (extra parameters are ignored)
					User userUpdate = Json.decodeValue(jsonUser.encode(), User.class);
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
						Country country = countryBusiness.getCountryFromAlpha2(structure.getObject("address").getObject("country").getString("alpha2"));
						structure.putObject("country", new JsonObject(Json.encode(country)));
						structure.getObject("address").putString("country", country.getLabel());
						structure.putObject("activity", new JsonObject(Json.encode(activityBusiness.getActivityFromId(activityId))));
					}
					Structure structureObj = Json.decodeValue(structure.encode(), Structure.class);

					// JSon Category Age
					JsonObject categoryAge = jsonReq.getObject(PARAM_CATEGORY_AGE);
					CategoryAge categoryAgeObj = Json.decodeValue(categoryAge.encode(), CategoryAge.class);

					// Country
					final String countryId = jsonReq.getString("country", "CNTR-250-FR-FRA");

					final User user = Json.decodeValue(mongo.getById(userUpdate.get_id(), User.class).encode(), User.class);

					LOG.debug("ID: " + userUpdate.get_id() + ", code:" + activationCode + ", structure: " + structure + ", activity:" + activityId);

					if (user == null) {
						utils.sendError(message, ExceptionCodes.BAD_LOGIN, Messages.getString("user.not.exist", req.getLocale()));
					} else if (user.getAccount().isActive()) {
						utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.already.active", req.getLocale()));
					} else if (!user.getAccount().isFirstConnexion()) {
						utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.first.done", req.getLocale()));
					} else if (!user.getAccount().getActivationCode().equals(activationCode)) {
						utils.sendError(message, ExceptionCodes.BUSINESS_ERROR, Messages.getString("user.activationcode.wrong", req.getLocale()));
					} else {
						// MaJ User
						user.getAccount().setActive(true);
						user.getAccount().setFirstConnexion(false);
						user.getAccount().setListPlan(userUpdate.getAccount().getListPlan());
						// récupération des activities des plans
						for (Plan plan : user.getAccount().getListPlan()) {
							if (plan.getActivity() != null) {
								Activity activity = activityBusiness.getActivityFromId(plan.getActivity().get_id());
								if (activity != null) {
									plan.setActivity(activity);
								}
							}
						}
						user.setBirthdate(userUpdate.getBirthdate());
						user.setContact(userUpdate.getContact());
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
						dbObjectParent.put("parametersSignup", 1);
						project = new BasicDBObject("$project", dbObjectParent);
						List<DBObject> pipelineAggregation = Arrays.asList(match, project);
						tabParametersSignup = mongo.aggregate("parametersSignup", pipelineAggregation, ActivityCfg.class);

						// Création SB_Person
						List<String> listPersonsId = new ArrayList<>();
						if (tabParametersSignup != null && tabParametersSignup.size() > 0) {
							JsonObject parametersSignup = tabParametersSignup.get(0);
							if (parametersSignup.containsField("parametersSignup") && parametersSignup.getObject("parametersSignup").containsField("players")) {
								JsonArray tabPlayers = parametersSignup.getObject("parametersSignup").getArray("players");

								for (int i = 0; i < tabPlayers.size(); i++) {
									JsonObject player = tabPlayers.get(i);
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
							throw new QaobeeException(ExceptionCodes.DB_NO_ROW_RETURNED, "No season defined for (" + activityId + " / " + countryId + ")");
						}
						for (int i = 0; i < resultJson.size(); i++) {
							JsonObject s = resultJson.get(i);
							if (s.getLong("endDate", 0) > currentDate && s.getLong("startDate") < currentDate) {
								sbSandBoxCfg.setSeason((Season) Json.decodeValue(Json.encode(s), Season.class));
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
						sbEffective.setLabel("Defaut");
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
						String homeTeamId = mongo.save(team);

						// Equipe adversaire
						team.setLabel("Equipe adverse");
						team.setAdversary(true);
						team.setLinkTeamId(homeTeamId);
						mongo.save(team);

						// MàJ du plan FREEMIUM
						user.getAccount().getListPlan().get(0).setPaidDate(System.currentTimeMillis());
						user.getAccount().getListPlan().get(0).setStatus("paid");

						mongo.save(user);
						message.reply(Json.encode(user));
						utils.sendStatus(true, message);
					}

				} catch (final NoSuchMethodException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final EncodeException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
				} catch (final QaobeeException e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
				} catch (final Exception e) {
					LOG.error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
				}
			}
		});
	}
}
