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

package com.qaobee.hive.test.api.commons.communication;

import com.qaobee.hive.api.v1.commons.communication.NotificationsRoute;
import com.qaobee.hive.business.model.commons.users.communication.Notification;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.test.config.VertxJunitSupport;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.jdeferred.Deferred;
import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Notifications.
 */
public class NotificationsTest extends VertxJunitSupport {
    private static final String BASE_URL = getBaseURL("/commons/communication/notifications");

    /**
     * Gets notifications.
     */
    @Test
    public void getNotifications(TestContext context) {
        Async async = context.async();
        generateLoggedUser().done(u -> {
            final Notification n = new Notification();
            n.setContent("Hello");
            n.setTitle("Message");
            n.setSenderId(u.get_id());
            n.setTargetId(u.get_id());
            n.setTimestamp(System.currentTimeMillis());
            addNotification(n).then(id -> {
                n.set_id(id);
                given().header(TOKEN, u.getAccount().getToken())
                        .when().get(BASE_URL + "/")
                        .then().assertThat().statusCode(200)
                        .body("", hasSize(greaterThan(0)))
                        .body("_id", hasItem(n.get_id()))
                        .body("content", hasItem("Hello"));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets empty notifications.
     */
    @Test
    public void getEmptyNotifications(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(BASE_URL + "/")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }


    /**
     * Gets notifications with limit and start.
     */
    @Test
    public void getNotificationsWithLimitAndStart(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            List<Promise> promises = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                final Notification n = new Notification();
                n.setContent("Hello");
                n.setTitle("Message-" + i);
                n.setSenderId(u.get_id());
                n.setTargetId(u.get_id());
                n.setTimestamp(i);
                promises.add(addNotification(n));
            }
            DeferredManager dm = new DefaultDeferredManager();
            dm.when(promises.toArray(new Promise[promises.size()]))
                    .done(rs -> {
                        given().header(TOKEN, u.getAccount().getToken())
                                .when().get(BASE_URL + "/")
                                .then().assertThat().statusCode(200)
                                .body("", hasSize(15))
                                .body("content", hasItem("Hello"));

                        given().header(TOKEN, u.getAccount().getToken())
                                .param(NotificationsRoute.PARAM_START, 5)
                                .param(NotificationsRoute.PARAM_LIMIT, 2)
                                .when().get(BASE_URL + "/")
                                .then().assertThat().statusCode(200)
                                .body("", hasSize(2))
                                .body("title", hasItem("Message-9"));
                        async.complete();
                    }).fail(e -> Assert.fail(((Throwable)e.getReject()).getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Gets notifications with non logged user.
     */
    @Test
    public void getNotificationsWithNonLoggedUser() {
        given().when().get(BASE_URL + "/")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets notifications with wrong http method.
     */
    @Test
    public void getNotificationsWithWrongHttpMethod() {
        given().when().post(BASE_URL + "/")
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Mark as read.
     */
    @Test
    public void markAsRead(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            final Notification n = new Notification();
            n.setContent("Hello");
            n.setTitle("Message");
            n.setSenderId(u.get_id());
            n.setTimestamp(System.currentTimeMillis());
            n.setTargetId(u.get_id());
            addNotification(n).then(id -> {
                n.set_id(id);

                given().header(TOKEN, u.getAccount().getToken())
                        .queryParam(NotificationsRoute.PARAM_NOTIF_ID, n.get_id())
                        .when().post(BASE_URL + "/read")
                        .then().assertThat().statusCode(200)
                        .body(STATUS, notNullValue())
                        .body(STATUS, is(true));

                given().header(TOKEN, u.getAccount().getToken())
                        .when().get(BASE_URL + "/")
                        .then().assertThat().statusCode(200)
                        .body("", hasSize(1))
                        .body("content", hasItem("Hello"))
                        .body("_id", hasItem(n.get_id()))
                        .body("read", hasItem(true));

                given().header(TOKEN, u.getAccount().getToken())
                        .queryParam(NotificationsRoute.PARAM_NOTIF_ID, n.get_id())
                        .when().post(BASE_URL + "/read")
                        .then().assertThat().statusCode(200)
                        .body(STATUS, notNullValue())
                        .body(STATUS, is(true));

                given().header(TOKEN, u.getAccount().getToken())
                        .when().get(BASE_URL + "/")
                        .then().assertThat().statusCode(200)
                        .body("", hasSize(1))
                        .body("content", hasItem("Hello"))
                        .body("_id", hasItem(n.get_id()))
                        .body("read", hasItem(false));
                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Mark as read with wrong id.
     */
    @Test
    public void markAsReadWithWrongId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(NotificationsRoute.PARAM_NOTIF_ID, "blabla")
                    .when().post(BASE_URL + "/read")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Mark as read with missing id.
     */
    @Test
    public void markAsReadWithMissingId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(BASE_URL + "/read")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Mark as read with wrong http method.
     */
    @Test
    public void markAsReadWithWrongHttpMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(NotificationsRoute.PARAM_NOTIF_ID, "blabla")
                    .when().get(BASE_URL + "/read")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Delete notification.
     */
    @Test
    public void deleteNotification(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            final Notification n = new Notification();
            n.setContent("Hello");
            n.setTitle("Message");
            n.setSenderId(u.get_id());
            n.setTimestamp(System.currentTimeMillis());
            n.setTargetId(u.get_id());

            addNotification(n).done(id -> {
                n.set_id(id);
                given().header(TOKEN, u.getAccount().getToken())
                        .queryParam(NotificationsRoute.PARAM_NOTIF_ID, n.get_id())
                        .when().delete(BASE_URL + "/del")
                        .then().assertThat().statusCode(200)
                        .body(STATUS, notNullValue())
                        .body(STATUS, is(true));

                given().header(TOKEN, u.getAccount().getToken())
                        .when().get(BASE_URL + "/")
                        .then().assertThat().statusCode(200)
                        .body("", hasSize(0));

                async.complete();
            }).fail(e -> Assert.fail(e.getMessage()));
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Delete notification with wrong id.
     */
    @Test
    public void deleteNotificationWithWrongId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(NotificationsRoute.PARAM_NOTIF_ID, "blabla")
                    .when().delete(BASE_URL + "/del")
                    .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
                    .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Delete notification with missing id.
     */
    @Test
    public void deleteNotificationWithMissingId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().delete(BASE_URL + "/del")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Delete notification with wrong http method.
     */
    @Test
    public void deleteNotificationWithWrongHttpMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .queryParam(NotificationsRoute.PARAM_NOTIF_ID, "blabla")
                    .when().get(BASE_URL + "/del")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Delete notification with not logged.
     */
    @Test
    public void deleteNotificationWithNotLogged() {
        given().queryParam(NotificationsRoute.PARAM_NOTIF_ID, "blabla")
                .when().delete(BASE_URL + "/del")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add notification to user.
     */
    @Test
    public void addNotificationToUser(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            JsonObject n = new JsonObject()
                    .put("content", "Hello")
                    .put("title", "Message")
                    .put("senderId", u.get_id())
                    .put("timestamp", System.currentTimeMillis())
                    .put("targetId", u.get_id());

            given().header(TOKEN, u.getAccount().getToken())
                    .body(n.encode())
                    .when().post(BASE_URL + "/user/add")
                    .then().assertThat().statusCode(200)
                    .body(STATUS, notNullValue())
                    .body(STATUS, is(true));

            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(BASE_URL + "/")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(1))
                    .body("content", hasItem("Hello"))
                    .body("_id", hasItem(notNullValue()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add notification to sandbox and exclude himself.
     */
    @Test
    public void addNotificationToSandboxAndExcludeHimself(TestContext context) {
        Async async = context.async();
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(u -> {
            JsonObject n = new JsonObject()
                    .put("content", "Hello")
                    .put("title", "Message")
                    .put("senderId", u.get_id())
                    .put("timestamp", System.currentTimeMillis())
                    .put("exclude", new JsonArray().add(u.get_id()))
                    .put("targetId", "558b0efebd2e39cdab651e1f");

            given().header(TOKEN, u.getAccount().getToken())
                    .body(n.encode())
                    .when().post(BASE_URL + "/sandbox/add")
                    .then().assertThat().statusCode(200)
                    .body(STATUS, notNullValue())
                    .body(STATUS, is(true));

            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(BASE_URL + "/")
                    .then().assertThat().statusCode(200)
                    .body("", hasSize(0));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add notification to sandbox.
     */
    @Test
    public void addNotificationToSandbox(TestContext context) {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        Async async = context.async();
        generateLoggedUser("5509ef1fdb8f8b6e2f51f4ce").then(u -> {
            JsonObject n = new JsonObject()
                    .put("content", "Hello")
                    .put("title", "Message")
                    .put("senderId", u.get_id())
                    .put("timestamp", System.currentTimeMillis())
                    .put("targetId", "558b0efebd2e39cdab651e1f");

            given().header(TOKEN, u.getAccount().getToken())
                    .body(n.encode())
                    .when().post(BASE_URL + "/sandbox/add")
                    .then().assertThat().statusCode(200)
                    .body(STATUS, notNullValue())
                    .body(STATUS, is(true));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add notification to user with wrong user id.
     */
    @Test
    public void addNotificationToUserWithWrongUserId(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            JsonObject n = new JsonObject()
                    .put("content", "Hello")
                    .put("title", "Message")
                    .put("senderId", u.get_id())
                    .put("timestamp", System.currentTimeMillis());

            given().header(TOKEN, u.getAccount().getToken())
                    .body(n.encode())
                    .when().post(BASE_URL + "/user/add")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

            n.put("targetId", "bla");
            given().header(TOKEN, u.getAccount().getToken())
                    .body(n.encode())
                    .when().post(BASE_URL + "/user/add")
                    .then().assertThat().statusCode(200)
                    .body(STATUS, notNullValue())
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add notification to user with no data.
     */
    @Test
    public void addNotificationToUserWithNoData(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
           given().header(TOKEN, u.getAccount().getToken())
                    .body(new JsonObject().encode())
                    .when().post(BASE_URL + "/user/add")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

            given().header(TOKEN, u.getAccount().getToken())
                    .when().post(BASE_URL + "/user/add")
                    .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                    .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add notification to user with wrong http method.
     */
    @Test
    public void addNotificationToUserWithWrongHttpMethod(TestContext context) {
        Async async = context.async();
        generateLoggedUser().then(u -> {
            given().header(TOKEN, u.getAccount().getToken())
                    .when().get(BASE_URL + "/user/add")
                    .then().assertThat().statusCode(404)
                    .body(STATUS, is(false));
            async.complete();
        }).fail(e -> Assert.fail(e.getMessage()));
        async.await(TIMEOUT);
    }

    /**
     * Add notification to user with not logged.
     */
    @Test
    public void addNotificationToUserWithNotLogged() {
        given().when().post(BASE_URL + "/user/add")
                .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
                .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add notification.
     *
     * @param n Notification
     * @return notification id
     */
    private Promise<String, QaobeeException, Integer> addNotification(final Notification n) {
        Deferred<String, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.upsert(n).done(deferred::resolve).fail(deferred::reject);
        return deferred.promise();
    }
}
