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


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.api.v1.commons.utils.TemplatesVerticle;
import com.qaobee.hive.business.commons.users.UsersBusiness;
import com.qaobee.hive.business.model.commons.referencial.Structure;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.account.Plan;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBoxCfg;
import com.qaobee.hive.business.model.sandbox.effective.SB_Effective;
import com.qaobee.hive.business.model.sandbox.effective.SB_Person;
import com.qaobee.hive.business.model.sandbox.effective.SB_Team;
import com.qaobee.hive.business.model.transversal.Member;
import com.qaobee.hive.business.model.transversal.Role;
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
@DeployableVerticle
public class SignupVerticle extends AbstractGuiceVerticle {

	/** The Constant REGISTER. */
	public static final String REGISTER = Module.VERSION + ".commons.users.signup.register";
	/** The Constant LOGIN_TEST. */
	public static final String LOGIN_TEST = Module.VERSION + ".commons.users.signup.logintest";
	/** The Constant LOGIN_EXISTS. */
	public static final String LOGIN_EXISTS = Module.VERSION + ".commons.users.signup.loginExists";
	/** The Constant ACCOUNT_CHECK. */
	public static final String ACCOUNT_CHECK = Module.VERSION + ".commons.users.signup.accountcheck";
	/** The Constant FIRST_CONNECTION_CHECK */
	public static final String FIRST_CONNECTION_CHECK = Module.VERSION + ".commons.users.signup.firstconnectioncheck";
	/** The Constant FINALIZE_SIGNUP */
	public static final String FINALIZE_SIGNUP = Module.VERSION + ".commons.users.signup.finalize";
	
	/** Parameter ID*/
	public static final String PARAM_ID = "id";
	/** Parameter CODE */
	public static final String PARAM_CODE = "code";

	// MongoDB driver
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vertx.java.platform.Verticle#start()
	 */
	@Override
	public void start() {
		super.start();
		container.logger().debug(this.getClass().getName() + " started");

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
		final Handler<Message<JsonObject>> userNameExistHandler = new Handler<Message<JsonObject>>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override
			public void handle(final Message<JsonObject> message) {
				final JsonObject jsonReq = new JsonObject(message.body().encode());
				final String login = jsonReq.getString("login");
				final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.login", login).get(), null, null, 0, 0, User.class);
				if (res.size() > 0) {
					utils.sendStatusJson(true, message);
				} else {
					utils.sendStatusJson(false, message);
				}
			}
		};

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
		final Handler<Message<String>> userNameTestHandler = new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override
			public void handle(final Message<String> message) {
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
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				}
			}
		};
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
		final Handler<Message<String>> registerHandler = new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override
			public void handle(final Message<String> message) {
				final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
				try {
					utils.testHTTPMetod(Constantes.PUT, req.getMethod());

					final JsonObject json = new JsonObject(req.getBody());
					final Plan plan = Json.decodeValue(json.getObject("plan").encode(), Plan.class);

					final boolean injunit = json.getBoolean("junit", false);
					final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
					reCaptcha.setPrivateKey(Params.getString("recaptcha.pkey"));

					ReCaptchaResponse reCaptchaResponse = null;
					if (!injunit) {
						reCaptchaResponse = reCaptcha
								.checkAnswer(Params.getString("recaptcha.site"), json.getObject("captcha").getString("challenge"), json.getObject("captcha").getString("response"));
					}
					if (!injunit && !reCaptchaResponse.isValid()) {
						utils.sendError(message, ExceptionCodes.CAPTCHA_EXCEPTION, "wrong captcha");
					} else {
						json.removeField("junit");
						json.removeField("captcha");
						json.removeField("plan");
						container.logger().info(json.encode());
						final User user = Json.decodeValue(json.encode(), User.class);
						
						// Check user informations
						usersBusiness.checkUserInformations(user, req.getLocale());
						
						// check if email is correct
						if (authCheck.testEmail(user.getContact().getEmail(), req.getLocale())) {
							// unique login test
							vertx.eventBus().send(LOGIN_EXISTS, new JsonObject().putString("login", user.getAccount().getLogin()), new Handler<Message<JsonObject>>() {

								/*
								 * (non-Javadoc)
								 * 
								 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
								 */
								@Override
								public void handle(final Message<JsonObject> usernameExistResp) {
									if (usernameExistResp.body().getBoolean("status", false)) {
										utils.sendError(message, new QaobeeException(ExceptionCodes.NON_UNIQUE_LOGIN, Messages.getString("login.nonunique", req.getLocale())));
									} else {
										try {
											user.getAccount().setActive(false);
											if (user.getAccount().getListPlan() == null) {
												user.getAccount().setListPlan(new ArrayList<Plan>());
											}
											plan.setPaymentId(UUID.randomUUID().toString());
											plan.setStatus("open");
											plan.setAmountPaid(Long.parseLong(Params.getString("plan." + plan.getLevelPlan().name() + ".price")) / 100l);
											plan.setStartPeriodDate(System.currentTimeMillis());
											user.getAccount().getListPlan().add(plan);

											final String id = mongo.save(personUtils.prepareUpsert(user));
											if (id != null) {
												user.set_id(id);

												final JsonObject tplReq = new JsonObject();
												tplReq.putString(TemplatesVerticle.TEMPLATE, "newAccount.html");
												tplReq.putObject(TemplatesVerticle.DATA, mailUtils.generateActivationBody(user, req.getLocale()));

												vertx.eventBus().send(TemplatesVerticle.TEMPLATE_GENERATE, tplReq, new Handler<Message<JsonObject>>() {
													@Override
													public void handle(final Message<JsonObject> tplResp) {
														final String tplRes = tplResp.body().getString("result");
														final JsonObject emailReq = new JsonObject();
														emailReq.putString("from", Params.getString("mail.from"));
														emailReq.putString("to", user.getContact().getEmail());
														emailReq.putString("subject", Messages.getString("mail.account.validation.subject"));
														emailReq.putString("content_type", "text/html");
														emailReq.putString("body", tplRes);
														vertx.eventBus().publish("mailer.mod", emailReq);
														final JsonObject res = new JsonObject();
														try {
															res.putObject("person", mongo.getById(id, User.class));
															res.putString("planId", plan.getPaymentId());
															container.logger().info(res.encode());
															message.reply(res.encode());
														} catch (final EncodeException e) {
															container.logger().error(e.getMessage(), e);
															utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
														} catch (final QaobeeException e) {
															container.logger().error(e.getMessage(), e);
															utils.sendError(message, e);
														}
													}
												});
											}
										} catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
											container.logger().error(e.getMessage(), e);
											utils.sendError(message, ExceptionCodes.PASSWD_EXCEPTION, e.getMessage());
										} catch (final EncodeException e) {
											container.logger().error(e.getMessage(), e);
											utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
										} catch (final QaobeeException e) {
											container.logger().error(e.getMessage(), e);
											utils.sendError(message, e);
										}
									}
								}
							});
						}
					}
				} catch (final QaobeeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, e);
				} catch (final NoSuchMethodException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final IllegalArgumentException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MAIL_EXCEPTION, e.getMessage());
				}
			}
		};

		/**
		 * @apiDescription Account validation check
		 * @api {get} /api/1/commons/users/signup/accountcheck Account validation check
		 * @apiParam {String} code Activation code
		 * @apiParam {String} Person id
		 * @apiVersion 0.1.0
         * @apiName accountCheckHandler
		 * @apiGroup SignupV API
		 * @apiSuccess {Object} status {"status", true|false}
		 * @apiError HTTP_ERROR wrong request's method
		 */
		final Handler<Message<String>> accountCheckHandler = new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override
			public void handle(final Message<String> message) {
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
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final EncodeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
				} catch (final QaobeeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
				}
			}
		};
		
		/**
		 * @apiDescription First connection account check
		 * @api {get} /api/1/commons/users/signup/firstconnectioncheck Account validation check
		 * @apiParam {String} code Activation code
		 * @apiParam {String} Person id
		 * @apiVersion 0.1.0
         * @apiName accountCheckHandler
		 * @apiGroup SignupV API
		 * @apiSuccess {Object} status {"status", true|false}
		 * @apiError HTTP_ERROR wrong request's method
		 */
		final Handler<Message<String>> firstConnectionCheckHandler = new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override
			public void handle(final Message<String> message) {
				try {
					final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
					utils.testHTTPMetod(Constantes.GET, req.getMethod());
					Map<String, List<String>> params = req.getParams();
                    utils.testMandatoryParams(params, PARAM_ID, PARAM_CODE);
                    
					final String id = params.get(PARAM_ID).get(0);
					final String activationCode = params.get(PARAM_CODE).get(0);
					
					final User user = Json.decodeValue(mongo.getById(id, User.class).encode(), User.class);
					if(user==null) {
						utils.sendError(message, ExceptionCodes.BAD_LOGIN, "user.not.exist");
					} else if(!user.getAccount().isActive()) {
						utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, "user.not.active");
					} else if(!user.getAccount().isFirstConnexion()) {
						utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, "user.first.done");
					} else if(!user.getAccount().getActivationCode().equals(activationCode)) {
						utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, "user.activationcode.wrong");
					} else {
						message.reply(Json.encode(user));
						utils.sendStatus(true, message);
					}

				} catch (final NoSuchMethodException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final EncodeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
				} catch (final QaobeeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
				}
			}
		};
		
		/**
		 * @apiDescription Finalizes signup 
		 * @api {get} /api/1/commons/users/signup/finalizesignup Account finalizes signup
		 * @apiParam {Object} user
		 * @apiParam {String} activation code
		 * @apiParam {Object} structure
		 * @apiParam {Object] activity
		 * @apiParam 
		 * @apiVersion 0.1.0
         * @apiName finalizeSignupHandler
		 * @apiGroup Signup API
		 * @apiSuccess {Object} user {"status", true|false}
		 * @apiError HTTP_ERROR wrong request's method
		 */
		final Handler<Message<String>> finalizeSignupHandler = new Handler<Message<String>>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
			 */
			@Override
			public void handle(final Message<String> message) {
				final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
				try {
					utils.testHTTPMetod(Constantes.POST, req.getMethod());
					final JsonObject jsonReq = new JsonObject(req.getBody());
					container.logger().error(jsonReq.encodePrettily());
					
					// JSon User
					final JsonObject jsonUser = jsonReq.getObject("user");
					//TODO : pb sur la date de naissance
					jsonUser.removeField("birthdate");
					jsonUser.removeField("activity");
					
					User userUpdate = Json.decodeValue(jsonUser.encode(), User.class);
					final String id = jsonUser.getString("_id");
					// Code activation
					final String activationCode = jsonReq.getString("code");
					
					// JSon Structure
					final JsonObject jsonStructure = jsonReq.getObject("structure");
					String structureId = jsonStructure.getString("referencialId");
					jsonStructure.removeField("referencialId");
					Structure structure = Json.decodeValue(jsonStructure.encode(), Structure.class);
					
					// JSon Activity
					final String jsonActivity = jsonReq.getString("activity");
					
					final User user = Json.decodeValue(mongo.getById(id, User.class).encode(), User.class);
					
					container.logger().error("ID: " + id + ", code:" + activationCode + ", structure: " + jsonStructure + ", activity:" + jsonActivity);
					
					if(user==null) {
						utils.sendError(message, ExceptionCodes.BAD_LOGIN, "user.not.exist");
					} else if(!user.getAccount().isActive()) {
						utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, "user.not.active");
					} else if(!user.getAccount().isFirstConnexion()) {
						utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, "user.first.done");
					} else if(!user.getAccount().getActivationCode().equals(activationCode)) {
						utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, "user.activationcode.wrong");
					} else {
						// MaJ User
						user.getAccount().setFirstConnexion(false);
						user.setContact(userUpdate.getContact());
						user.setCountry(null);
						user.setGender(user.getGender());
						user.setFirstname(userUpdate.getFirstname());
						user.setName(userUpdate.getName());
						mongo.save(user);				
						
						// Création SandBox
						SB_SandBox sandbox = new SB_SandBox();
						sandbox.setActivityId(jsonActivity);
						sandbox.setOwner(user.get_id());
						sandbox.setStructureId(structureId);
						String sandboxId = mongo.save(sandbox);
						
						// Création SandBoxCfg
						SB_SandBoxCfg sandboxCfg = new SB_SandBoxCfg();
						sandboxCfg.setStructure(structure);
						sandboxCfg.setSandBox(sandbox);
						sandboxCfg.setStructure(structure);
						String sandboxCfgId = mongo.save(sandboxCfg);
						
						// Création Effective
						SB_Effective sandboxEffective = new SB_Effective();
						sandboxEffective.setSandBoxCfgId(sandboxCfgId);
						Role role = new Role();
						role.setCode("player");
						role.setLabel("Joueur");
						for(int i=1; i<13 ;i++) {
							// Création Person
							SB_Person sandboxPerson = new SB_Person();
							sandboxPerson.setName("Player " + i);
							sandboxPerson.setFirstname("Player " + i);
							String personId = mongo.save(sandboxPerson);
							Member member = new Member();
							member.setPersonId(personId);
							member.setRole(role);
							sandboxEffective.addMember(member);
						}
						String sandboxEffectiveId = mongo.save(sandboxEffective);

						// Création Team
						SB_Team sandBoxTeam = new SB_Team();
						sandBoxTeam.setLabel("Team 1");
						sandBoxTeam.setSandBoxIdCfg(sandboxCfgId);
						sandBoxTeam.setEffectiveId(sandboxEffectiveId);
						String sandboxTeamId = mongo.save(sandBoxTeam);
						
						message.reply(Json.encode(user));
						utils.sendStatus(true, message);
					}
					
				} catch (final NoSuchMethodException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
				} catch (final EncodeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
				} catch (final QaobeeException e) {
					container.logger().error(e.getMessage(), e);
					utils.sendError(message, ExceptionCodes.MONGO_ERROR, e.getMessage());
				}
			}
		};
		
		/*
		 * Handlers declaration
		 */
		vertx.eventBus().registerHandler(REGISTER, registerHandler);
		vertx.eventBus().registerHandler(LOGIN_TEST, userNameTestHandler);
		vertx.eventBus().registerHandler(LOGIN_EXISTS, userNameExistHandler);
		vertx.eventBus().registerHandler(ACCOUNT_CHECK, accountCheckHandler);
		vertx.eventBus().registerHandler(FIRST_CONNECTION_CHECK, firstConnectionCheckHandler);
		vertx.eventBus().registerHandler(FINALIZE_SIGNUP, finalizeSignupHandler);
	}
}
