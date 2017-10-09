package com.qaobee.hive.api.v1.commons.users;

import com.qaobee.hive.services.NotificationsService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.AsyncResult;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The type Abstract signup.
 */
abstract class AbstractSignup extends AbstractRoute {
    @Inject
    @Named("runtime")
    private JsonObject runtime;
    @Inject
    private NotificationsService notificationsService;

    /**
     * Send register mail.
     *
     * @param signupObject the signup object
     * @param user         the user
     * @param r            the r
     * @param context      the context
     */
    void sendRegisterMail(JsonObject signupObject, JsonObject user, AsyncResult<Void> r, RoutingContext context) {
        if (r.succeeded()) {
            JsonObject notification = new JsonObject()
                    .put("content", Messages.getString("notification.first.connection.content", getLocale(context), String.valueOf(runtime.getInteger("trial.duration"))))
                    .put("title", Messages.getString("notification.first.connection.title", getLocale(context)))
                    .put("senderId", runtime.getString("admin.id")
                    );
            notificationsService.sendNotification(user.getString("_id"), DBCollections.USER, notification, new JsonArray(), ar -> {
                //empty
            });
            handleResponse(context, signupObject);
        } else {
            utils.handleError(context, r.cause());
        }
    }
}
