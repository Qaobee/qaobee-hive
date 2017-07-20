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
import com.qaobee.hive.services.PersonService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Sb person route.
 */
@VertxRoute(rootPath = "/api/" + Module.VERSION + "/sandbox/effective/person")
public class SB_PersonRoute extends AbstractRoute {// NOSONAR
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
    private PersonService personService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);

        addRoute(router, "/add", HttpMethod.PUT,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, "person"),
                this::addPerson);

        addRoute(router, "/update", HttpMethod.PUT,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_PERSON_ID),
                this::updatePerson);

        addRoute(router, "/list", HttpMethod.POST,
                authHandler,
                c -> mandatoryHandler.testBodyParams(c, PARAM_LIST_ID, PARAM_LIST_FIELD),
                this::getPersonList);

        addRoute(router, "/get", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_PERSON_ID),
                this::getPerson);

        addRoute(router, "/listSandbox", HttpMethod.GET,
                authHandler,
                c -> mandatoryHandler.testRequestParams(c, PARAM_SANDBOX_ID),
                this::getPersonListBySandbox);

        return router;
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
    private void getPersonListBySandbox(RoutingContext context) {
        personService.getPersonListBySandbox(context.request().getParam(PARAM_SANDBOX_ID), handleResponseArray(context));
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
    private void getPersonList(RoutingContext context) {
        JsonObject params = context.getBodyAsJson();
        personService.getPersonList(params.getJsonArray(PARAM_LIST_ID), params.getJsonArray(PARAM_LIST_FIELD), handleResponseArray(context));
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
    private void updatePerson(RoutingContext context) {
        personService.updatePerson(context.getBodyAsJson(), context.user().principal().getString("_id"), getLocale(context), handleResponse(context));
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
    private void getPerson(RoutingContext context) {
        personService.getPerson(context.request().getParam(PARAM_PERSON_ID), handleResponse(context));
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
    private void addPerson(RoutingContext context) {
        personService.addPerson(context.getBodyAsJson().getJsonObject("person"), context.user().principal().getString("_id"), getLocale(context), handleResponse(context));
    }
}
