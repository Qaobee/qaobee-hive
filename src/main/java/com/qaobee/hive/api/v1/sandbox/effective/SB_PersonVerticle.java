/*
 * __________________
 *   Qaobee
 * __________________
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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.sandbox.effective.SB_Person;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * The type Person verticle.
 */
@DeployableVerticle
public class SB_PersonVerticle extends AbstractGuiceVerticle { // NOSONAR
    private static final Logger LOG = LoggerFactory.getLogger(SB_PersonVerticle.class);
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
    public static final String ADD = Module.VERSION + ".sandbox.effective.person.add";
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
    private MongoDB mongo;
    @Inject
    private Utils utils;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(ADD, this::addPersonHandler)
                .registerHandler(GET, this::getPersonHandler)
                .registerHandler(UPDATE, this::updatePersonHandler)
                .registerHandler(GET_LIST, this::getPersonListHandler)
                .registerHandler(GET_LIST_SANDBOX, this::getPersonListBySandboxHandler);
    }

    /**
     * @apiDescription Return list of person as member of group
     * @api {post} /api/1/sandbox/effective/person/list Get list of persons
     * @apiVersion 0.1.0
     * @apiName getListPerson
     * @apiGroup Person API
     * @apiSuccess {Array} list of persons
     */
    @Rule(address = GET_LIST_SANDBOX, method = Constants.GET, logged = true, mandatoryParams = {PARAM_SANDBOX_ID}, scope = Rule.Param.REQUEST)
    private void getPersonListBySandboxHandler(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            CriteriaBuilder criteria = new CriteriaBuilder()
                    .add(PARAM_SANDBOX_ID, req.getParams().get(PARAM_SANDBOX_ID).get(0));
            JsonArray resultJson = mongo.findByCriterias(criteria.get(), null, null, -1, -1, SB_Person.class);
            if (resultJson == null || resultJson.size() == 0) {
                throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No person found for sandboxId (" + req.getParams().get(PARAM_SANDBOX_ID).get(0) + ")");
            }
            message.reply(resultJson.encode());
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }

    }

    /**
     * @apiDescription Return list of person as member of group
     * @api {post} /api/1/sandbox/effective/person/list Get list of persons
     * @apiVersion 0.1.0
     * @apiName getListPerson
     * @apiGroup Person API
     * @apiSuccess {Array} list of persons
     */
    @Rule(address = GET_LIST, method = Constants.POST, logged = true, mandatoryParams = {PARAM_LIST_ID, PARAM_LIST_FIELD}, scope = Rule.Param.BODY)
    private void getPersonListHandler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            JsonObject params = new JsonObject(req.getBody());
            JsonArray listId = params.getArray(PARAM_LIST_ID);
            JsonArray listfield = params.getArray(PARAM_LIST_FIELD);
            BasicDBObject dbObjectParent = new BasicDBObject();
            BasicDBObject dbObjectChild = new BasicDBObject("$in", listId.toArray());
            dbObjectParent.put("_id", dbObjectChild);
            DBObject match = new BasicDBObject("$match", dbObjectParent);
            listfield.forEach(field -> dbObjectParent.put((String) field, "$" + field));
            DBObject project = new BasicDBObject("$project", dbObjectParent);
            List<DBObject> pipelineAggregation = Arrays.asList(match, project);
            final JsonArray resultJSon = mongo.aggregate(null, pipelineAggregation, SB_Person.class);
            message.reply(resultJSon.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e.getCode(), e.getMessage());
        }
    }

    /**
     * @apiDescription Update person
     * @api {get} /api/1/sandbox/effective/person/update Update person
     * @apiVersion 0.1.0
     * @apiName updatePerson
     * @apiGroup Person API
     * @apiParam {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
     * @apiSuccess {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
     */
    @Rule(address = UPDATE, method = Constants.PUT, logged = true, mandatoryParams = {PARAM_PERSON_ID}, scope = Rule.Param.BODY)
    private void updatePersonHandler(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        final JsonObject json = new JsonObject(req.getBody());
        final String id = mongo.update(json, SB_Person.class);
        json.putString("_id", id);
        message.reply(json.encode());
    }

    /**
     * @apiDescription Retrieve Person by this Id
     * @api {get} /api/1/sandbox/effective/person/get Get Person by Id
     * @apiVersion 0.1.0
     * @apiName getPersonById
     * @apiGroup Person API
     * @apiParam {String} id
     * @apiSuccess {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = {PARAM_PERSON_ID}, scope = Rule.Param.REQUEST)
    private void getPersonHandler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            utils.testMandatoryParams(req.getParams(), PARAM_PERSON_ID);
            message.reply(mongo.getById(req.getParams().get(PARAM_PERSON_ID).get(0), SB_Person.class).encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Add Person
     * @api {put} /api/1/sandbox/effective/person/add Add Person
     * @apiVersion 0.1.0
     * @apiName addPerson
     * @apiGroup Person API
     * @apiSuccess {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
     */
    @Rule(address = ADD, method = Constants.PUT, logged = true, mandatoryParams = {"person"}, scope = Rule.Param.BODY)
    private void addPersonHandler(Message<String> message) {
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            final JsonObject dataContainer = new JsonObject(req.getBody());
            final JsonObject personJson = new JsonObject(dataContainer.getElement("person").toString());
            final String id = mongo.save(personJson, SB_Person.class);
            personJson.putString("_id", id);
            message.reply(personJson.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }
}
