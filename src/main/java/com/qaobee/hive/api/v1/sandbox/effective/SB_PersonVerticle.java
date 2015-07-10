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
import com.qaobee.hive.business.model.commons.settings.ActivityCfg;
import com.qaobee.hive.business.model.sandbox.effective.SB_Person;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The type Person verticle.
 */
@DeployableVerticle(isWorker = true)
public class SB_PersonVerticle extends AbstractGuiceVerticle {
    /**
     * Handler to get a set of persons
     */
    public static final String GET_LIST = Module.VERSION + ".sandbox.effective.person.list";
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
     * Handler to update the avatar of a person.
     */
    public static final String AVATAR = Module.VERSION + ".sandbox.effective.person.avatar";
    
	/* List of parameters */
    /**
     * Group ID
     */
    public static final String PARAM_LIST_ID = "listId";
    /**
     * list Field
     */
    public static final String PARAM_LIST_FIELD = "listField";
    /**
     * Season code
     */
    public static final String PARAM_SEASON_CODE = "seasonCode";
    /**
     * Person ID
     */
    public static final String PARAM_PERSON_ID = "id";
    
    /**
     * The Mongo.
     */
    @Inject
    private MongoDB mongo;
    /**
     * The Utils.
     */
    @Inject
    private Utils utils;

    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");

        /**
         * @apiDescription Add Person
         * @api {put} /api/1/person/add Add Person
         * @apiName addPerson
         * @apiGroup Person API
         * @apiSuccess {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
         * @apiError INTERNAL_ERROR Error during encode value
         * @apiError QAOBEE EXCEPTION Error during validate data of Person Object
         * @apiError HTTP_ERROR Bad Request
         */
        vertx.eventBus().registerHandler(ADD, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.PUT, req.getMethod());
                    utils.isUserLogged(req);
                    final JsonObject dataContainer = new JsonObject(req.getBody());
                    final JsonObject personJson = new JsonObject(dataContainer.getElement("person").toString());
                    Json.decodeValue(personJson.toString(), SB_Person.class);

					/* Control metier */
                    // TODO :      PersonCheck.getInstance().validate(person, req.getLocale());

					/* Add default values */
                    // Creation of request
                    CriteriaBuilder criterias = new CriteriaBuilder();
                    criterias.add("activityId", dataContainer.getString("activityId"));
                    criterias.add("countryId", dataContainer.getString("countryId"));
                    criterias.between("startDate", "endDate", new Date().getTime());
                    // TODO : A voir si c'est toujours comme ça
                    // retrieve characteristics for one activity
                    JsonArray resultJSon = mongo.findByCriterias(criterias.get(), null, null, -1, -1, ActivityCfg.class);
                    JsonObject json = resultJSon.get(0);
                    JsonArray caracs = json.getArray("caracteristicsPerson");

                    JsonObject status = personJson.getField("status");
                    String positionPerson = status.getField("positionType");
                    /* set default value for characteristics, depends positionType */
                    for (Object object : caracs) {
                        JsonObject obj = new JsonObject(object.toString());
                        String positiontype = obj.getField("positionType").toString();
                        if (positiontype.contains(positionPerson)) {
                            personJson.putElement("physicalFolder", obj.getArray("physicalFolder"));
                            personJson.putElement("technicalFolder", obj.getArray("technicalFolder"));
                            personJson.putElement("mentalFolder", obj.getArray("mentalFolder"));
                            break;
                        }
                    }
					/* force medicalFolder to null (bug) */
                    personJson.putElement("medicalFolder", null);
                    final String id = mongo.save(personJson, SB_Person.class);
                    personJson.putString("_id", id);
					/* return */
                    message.reply(personJson.encode());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @apiDescription Retrieve Person by this Id
         * @api {get} /api/1/person/get Get Person by Id
         * @apiName getPersonById
         * @apiGroup Person API
         * @apiParam {String} id
         * @apiSuccess {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
         * @apiError MONGO_ERROR Error during request to Mongo
         * @apiError INVALID_PARAMETER Invalid Parameters
         * @apiError HTTP_ERROR Bad Request
         */
        vertx.eventBus().registerHandler(GET, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.GET, req.getMethod());
                    utils.isUserLogged(req);
                    utils.testMandatoryParams(req.getParams(), PARAM_PERSON_ID);
                    message.reply(mongo.getById(req.getParams().get(PARAM_PERSON_ID).get(0), SB_Person.class).encode());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @apiDescription Update person
         * @api {get} /api/1/person/get Update person
         * @apiName updatePerson
         * @apiGroup Person API
         * @apiParam {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
         * @apiSuccess {Object} Person com.qaobee.hive.business.model.sandbox.effective.Person
         * @apiError HTTP_ERROR Bad request
         */
        vertx.eventBus().registerHandler(UPDATE, new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.PUT, req.getMethod());
                    utils.isUserLogged(req);
                    final JsonObject json = new JsonObject(req.getBody());
                    final String id = mongo.update(json, SB_Person.class);
                    json.putString("_id", id);
                    message.reply(json.encode());
                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final EncodeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.JSON_EXCEPTION, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e);
                }
            }
        });

        /**
         * @apiDescription Return list of person as member of group
         * @api {post} /api/1/person/list Get list of persons
         * @apiName getListPerson
         * @apiGroup Person API
         * @apiSuccess {Array} list of persons
         * @apiError HTTP_ERROR Bad request
         * @apiError MONGO_ERROR Error on DB request
         * @apiError INVALID_PARAMETER Parameters not found
         */
        vertx.eventBus().registerHandler(GET_LIST, new Handler<Message<String>>() {
            /*
             * (non-Javadoc)
             *
             * @see org.vertx.java.core.Handler#handle(java.lang.Object)
             */
            @Override
            public void handle(final Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    utils.isUserLogged(req);
                    JsonObject params = new JsonObject(req.getBody());
                    utils.testMandatoryParams(params.toMap(), PARAM_LIST_ID, PARAM_LIST_FIELD);

                    // List of id
                    JsonArray listId = params.getArray(PARAM_LIST_ID);

                    // List of field
                    JsonArray listfield = params.getArray(PARAM_LIST_FIELD);

                    DBObject match, project;
                    BasicDBObject dbObjectParent, dbObjectChild;

					/* *** $MACTH section *** */
                    dbObjectParent = new BasicDBObject();

                    dbObjectChild = new BasicDBObject("$in", listId.toArray());
                    dbObjectParent.put("_id", dbObjectChild);
                    match = new BasicDBObject("$match", dbObjectParent);

					/* *** $PROJECT section *** */
                    dbObjectParent = new BasicDBObject();
                    for (Object field : listfield) {
                        dbObjectParent.put((String) field, "$" + field);
                    }

                    project = new BasicDBObject("$project", dbObjectParent);

                    List<DBObject> pipelineAggregation = Arrays.asList(match, project);
                    container.logger().info("getListPerson : " + pipelineAggregation.toString());

                    final JsonArray resultJSon = mongo.aggregate(null, pipelineAggregation, SB_Person.class);

                    container.logger().info(resultJSon.encodePrettily());
                    message.reply(resultJSon.encode());

                } catch (final NoSuchMethodException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.INVALID_PARAMETER, e.getMessage());
                } catch (QaobeeException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendError(message, e.getCode(), e.getMessage());
                }
            }

        });
    }
}