package com.qaobee.hive.test.api.commons.communication;

import com.qaobee.hive.api.v1.commons.communication.NotificationsVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.communication.Notification;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Notifications.
 */
public class NotificationsTest extends VertxJunitSupport {

    /**
     * Init.
     */
    @Before
    public void init() {
        mongo.getDb().getCollection(Notification.class.getSimpleName()).drop();
    }

    /**
     * Gets notifications.
     */
    @Test
    public void getNotifications() {
        final User u = generateLoggedUser();

        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setSenderId(u.get_id());
        n.setTargetId(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.set_id(addNotification(n));

        given().header(TOKEN, u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(greaterThan(0)))
               .body("_id", hasItem(n.get_id()))
               .body("content", hasItem("Hello"));
    }

    /**
     * Gets empty notifications.
     */
    @Test
    public void getEmptyNotifications() {
        final User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(0));
    }


    /**
     * Gets notifications with limit and start.
     */
    @Test
    public void getNotificationsWithLimitAndStart() {
        final User u = generateLoggedUser();
        for (int i = 0; i < 15; i++) {
            final Notification n = new Notification();
            n.setContent("Hello");
            n.setTitle("Message-" + i);
            n.setSenderId(u.get_id());
            n.setTargetId(u.get_id());
            n.setTimestamp(i);
            n.set_id(addNotification(n));
        }

        given().header(TOKEN, u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(15))
               .body("content", hasItem("Hello"));

        given().header(TOKEN, u.getAccount().getToken())
               .param(NotificationsVerticle.PARAM_START, 5)
               .param(NotificationsVerticle.PARAM_LIMIT, 2)
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(2))
               .body("title", hasItem("Message-9"));
    }

    /**
     * Gets notifications with non logged user.
     */
    @Test
    public void getNotificationsWithNonLoggedUser() {
        given().when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
               .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets notifications with wrong http method.
     */
    @Test
    public void getNotificationsWithWrongHttpMethod() {
        given().when().post(getURL(NotificationsVerticle.LIST))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Mark as read.
     */
    @Test
    public void markAsRead() {
        final User u = generateLoggedUser();
        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setSenderId(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setTargetId(u.get_id());
        n.set_id(addNotification(n));

        given().header(TOKEN, u.getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, n.get_id())
               .when().post(getURL(NotificationsVerticle.READ))
               .then().assertThat().statusCode(200)
               .body(STATUS, notNullValue())
               .body(STATUS, is(true));

        given().header(TOKEN, u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(1))
               .body("content", hasItem("Hello"))
               .body("_id", hasItem(n.get_id()))
               .body("read", hasItem(true));

        given().header(TOKEN, u.getAccount().getToken())
                .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, n.get_id())
                .when().post(getURL(NotificationsVerticle.READ))
                .then().assertThat().statusCode(200)
                .body(STATUS, notNullValue())
                .body(STATUS, is(true));

        given().header(TOKEN, u.getAccount().getToken())
                .when().get(getURL(NotificationsVerticle.LIST))
                .then().assertThat().statusCode(200)
                .body("", hasSize(1))
                .body("content", hasItem("Hello"))
                .body("_id", hasItem(n.get_id()))
                .body("read", hasItem(false));
    }

    /**
     * Mark as read with wrong id.
     */
    @Test
    public void markAsReadWithWrongId() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().post(getURL(NotificationsVerticle.READ))
               .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
               .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Mark as read with missing id.
     */
    @Test
    public void markAsReadWithMissingId() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
               .when().post(getURL(NotificationsVerticle.READ))
               .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
               .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Mark as read with wrong http method.
     */
    @Test
    public void markAsReadWithWrongHttpMethod() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().get(getURL(NotificationsVerticle.READ))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Delete notification.
     */
    @Test
    public void deleteNotification() {
        final User u = generateLoggedUser();

        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setSenderId(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setTargetId(u.get_id());
        n.set_id(addNotification(n));
        given().header(TOKEN, u.getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, n.get_id())
               .when().delete(getURL(NotificationsVerticle.DEL))
               .then().assertThat().statusCode(200)
               .body(STATUS, notNullValue())
               .body(STATUS, is(true));

        given().header(TOKEN, u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(0));
    }

    /**
     * Delete notification with wrong id.
     */
    @Test
    public void deleteNotificationWithWrongId() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().delete(getURL(NotificationsVerticle.DEL))
               .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
               .body(CODE, is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Delete notification with missing id.
     */
    @Test
    public void deleteNotificationWithMissingId() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
               .when().delete(getURL(NotificationsVerticle.DEL))
               .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
               .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Delete notification with wrong http method.
     */
    @Test
    public void deleteNotificationWithWrongHttpMethod() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().get(getURL(NotificationsVerticle.DEL))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Delete notification with not logged.
     */
    @Test
    public void deleteNotificationWithNotLogged() {
        given().queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().delete(getURL(NotificationsVerticle.DEL))
               .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
               .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add notification to user.
     */
    @Test
    public void addNotificationToUser() {
        User u = generateLoggedUser();
        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setSenderId(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setTargetId(u.get_id());

        given().header(TOKEN, u.getAccount().getToken())
               .body(Json.encode(n))
               .when().post(getURL(NotificationsVerticle.ADD_TO_USER))
               .then().assertThat().statusCode(200)
               .body(STATUS, notNullValue())
               .body(STATUS, is(true));

        given().header(TOKEN, u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(1))
               .body("content", hasItem("Hello"))
               .body("_id", hasItem(notNullValue()));
    }

    /**
     * Add notification to sandbox.
     */
    @Test
    public void addNotificationToSandbox() {
        populate(POPULATE_ONLY, DATA_SANDBOXES_HAND);
        User u = generateLoggedUser();
        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setSenderId(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setTargetId("558b0efebd2e39cdab651e1f");

        given().header(TOKEN, u.getAccount().getToken())
               .body(Json.encode(n))
               .when().post(getURL(NotificationsVerticle.ADD_TO_SANDBOX))
               .then().assertThat().statusCode(200)
               .body(STATUS, notNullValue())
               .body(STATUS, is(true));
    }

    /**
     * Add notification to user with wrong user id.
     */
    @Test
    public void addNotificationToUserWithWrongUserId() {
        User u = generateLoggedUser();
        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setSenderId("blabla");
        n.setTimestamp(System.currentTimeMillis());
        n.setTargetId("blabla");
        given().header(TOKEN, u.getAccount().getToken())
               .body(Json.encode(n))
               .when().post(getURL(NotificationsVerticle.ADD_TO_USER))
               .then().assertThat().statusCode(200)
               .body(STATUS, notNullValue())
               .body(STATUS, is(false));
    }

    /**
     * Add notification to user with no data.
     */
    @Test
    public void addNotificationToUserWithNoData() {
        User u = generateLoggedUser();
        given().header(TOKEN, u.getAccount().getToken())
                .body(new JsonObject().encode())
                .when().post(getURL(NotificationsVerticle.ADD_TO_USER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));

        given().header(TOKEN, u.getAccount().getToken())
                .when().post(getURL(NotificationsVerticle.ADD_TO_USER))
                .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
                .body(CODE, is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Add notification to user with wrong http method.
     */
    @Test
    public void addNotificationToUserWithWrongHttpMethod() {
        given().header(TOKEN, generateLoggedUser().getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.ADD_TO_USER))
                .then().assertThat().statusCode(404)
                .body(STATUS, is(false));
    }

    /**
     * Add notification to user with not logged.
     */
    @Test
    public void addNotificationToUserWithNotLogged() {
        given().when().post(getURL(NotificationsVerticle.ADD_TO_USER))
               .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
               .body(CODE, is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add notification.
     *
     * @param n Notification
     *
     * @return notification id
     */
    private String addNotification(final Notification n) {
        String nid = null;
        try {
            nid = mongo.save(n);
        } catch (EncodeException | QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        return nid;
    }
}
