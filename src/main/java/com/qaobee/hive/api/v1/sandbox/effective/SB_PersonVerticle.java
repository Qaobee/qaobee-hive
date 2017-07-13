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

package com.qaobee.hive.api.v1.sandbox.effective;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.dao.PersonDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The type Person verticle.
 */
@DeployableVerticle
public class SB_PersonVerticle extends AbstractGuiceVerticle {// NOSONAR
    /**
     * Handler to get a set of persons
     */
    public static final String GET_LIST = Module.VERSION + ".sandbox.effective.person.list";
    /**
     * Handler to get all persons to sandbox's user
     */
    public static final String GET_LIST_SANDBOX = Module.VERSION + ".sandbox.effective.person.listSandbox";
    /**
     * Handler to add a person.
     */
    public static final String ADD_PERSON = Module.VERSION + ".sandbox.effective.person.add";
    /**
     * Handler to get a particular person from its ID.
     */
    public static final String GET = Module.VERSION + ".sandbox.effective.person.get";
    /**
     * Handler to update a person.
     */
    public static final String UPDATE = Module.VERSION + ".sandbox.effective.person.update";
    /**
     * Group ID
     */
    public static final String PARAM_LIST_ID = "listId";
    /**
     * list Field
     */
    public static final String PARAM_LIST_FIELD = "listField";
    /**
     * Sandbox Id
     */
    public static final String PARAM_SANDBOX_ID = "sandboxId";
    /**
     * Person ID
     */
    public static final String PARAM_PERSON_ID = "_id";
    @Inject
    private PersonDAO personDAO;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this)
                .add(ADD_PERSON, this::addPerson)
                .add(GET, this::getPerson)
                .add(UPDATE, this::updatePerson)
                .add(GET_LIST, this::getPersonList)
                .add(GET_LIST_SANDBOX, this::getPersonListBySandbox)
                .register(startFuture);
    }

    /**
     * @apiDescription Return list of person as member of group
     * @api {post} /api/1/sandbox/effective/person/list Get list of persons
     * @apiVersion 0.1.0
     * @apiName getPersonListBySandbox
     * @apiGroup Person API
     * @apiHeader {String} token
     * @apiSuccess {Array} list of persons
     */
    @Rule(address = GET_LIST_SANDBOX, method = Constants.GET, logged = true, mandatoryParams = PARAM_SANDBOX_ID, scope = Rule.Param.REQUEST)
    private void getPersonListBySandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        personDAO.getPersonListBySandbox(req.getParams().get(PARAM_SANDBOX_ID).get(0), handleJsonArray(message));
    }

    /**
     * @apiDescription Return list of person as member of group
     * @api {post} /api/1/sandbox/effective/person/list Get list of persons
     * @apiVersion 0.1.0
     * @apiName getPersonList
     * @apiGroup Person API
     * @apiHeader {String} token
     * @apiSuccess {Array} list of persons
     */
    @Rule(address = GET_LIST, method = Constants.POST, logged = true, mandatoryParams = {PARAM_LIST_ID, PARAM_LIST_FIELD}, scope = Rule.Param.BODY)
    private void getPersonList(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject params = new JsonObject(req.getBody());
        personDAO.getPersonList(params.getJsonArray(PARAM_LIST_ID), params.getJsonArray(PARAM_LIST_FIELD), handleJsonArray(message));
    }

    /**
     * @apiDescription Update person
     * @api {get} /api/1/sandbox/effective/person/update Update person
     * @apiVersion 0.1.0
     * @apiName updatePerson
     * @apiGroup Person API
     * @apiHeader {String} token
     * @apiParam {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
     * @apiSuccess {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
     */
    @Rule(address = UPDATE, method = Constants.PUT, logged = true, mandatoryParams = PARAM_PERSON_ID, scope = Rule.Param.BODY)
    private void updatePerson(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        personDAO.updatePerson(new JsonObject(req.getBody()), req.getUser().get_id(), req.getLocale(), handleJson(message));
    }

    /**
     * @apiDescription Retrieve Person by this Id
     * @api {get} /api/1/sandbox/effective/person/get Get Person by Id
     * @apiVersion 0.1.0
     * @apiName getPerson
     * @apiGroup Person API
     * @apiParam {String} id
     * @apiHeader {String} token
     * @apiSuccess {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = PARAM_PERSON_ID, scope = Rule.Param.REQUEST)
    private void getPerson(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        personDAO.getPerson(req.getParams().get(PARAM_PERSON_ID).get(0), handleJson(message));
    }

    /**
     * @apiDescription Add Person
     * @api {put} /api/1/sandbox/effective/person/add Add Person
     * @apiVersion 0.1.0
     * @apiName addPerson
     * @apiHeader {String} token
     * @apiGroup Person API
     * @apiSuccess {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
     */
    @Rule(address = ADD_PERSON, method = Constants.PUT, logged = true, mandatoryParams = "person", scope = Rule.Param.BODY)
    private void addPerson(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        final JsonObject body = new JsonObject(req.getBody());
        personDAO.addPerson(body.getJsonObject("person"), req.getUser().get_id(), req.getLocale(), handleJson(message));
    }
}
