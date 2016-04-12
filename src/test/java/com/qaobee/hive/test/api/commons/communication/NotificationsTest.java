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
import org.vertx.java.core.json.impl.Json;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * The type Notifications test.
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
     * Gets notifications test.
     */
    @Test
    public void getNotificationsTest() {
        final User u = generateLoggedUser();

        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setFrom_user_id(u.get_id());
        n.setUser_id(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.set_id(addNotification(n));

        given().header("token", u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(greaterThan(0)))
               .body("[0]._id", is(n.get_id()))
               .body("[0].content", is("Hello"));
    }

    /**
     * Gets empty notifications test.
     */
    @Test
    public void getEmptyNotificationsTest() {
        final User u = generateLoggedUser();
        given().header("token", u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(0));
    }


    /**
     * Gets notifications with limit and start test.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void getNotificationsWithLimitAndStartTest() throws InterruptedException {
        final User u = generateLoggedUser();
        for (int i = 0; i < 15; i++) {
            final Notification n = new Notification();
            n.setContent("Hello");
            n.setTitle("Message-" + i);
            n.setFrom_user_id(u.get_id());
            n.setUser_id(u.get_id());
            n.setTimestamp(i);
            n.set_id(addNotification(n));
        }

        given().header("token", u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(15))
               .body("[0].content", is("Hello"));

        given().header("token", u.getAccount().getToken())
               .param(NotificationsVerticle.PARAM_START, 5)
               .param(NotificationsVerticle.PARAM_LIMIT, 2)
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(2))
               .body("[0].title", is("Message-9"));
    }

    /**
     * Gets notifications with non logged user test.
     */
    @Test
    public void getNotificationsWithNonLoggedUserTest() {
        given().when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
               .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Gets notifications with wrong http mthod test.
     */
    @Test
    public void getNotificationsWithWrongHttpMthodTest() {
        given().when().post(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
               .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Mark as read test.
     */
    @Test
    public void markAsReadTest() {
        final User u = generateLoggedUser();
        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setFrom_user_id(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setUser_id(u.get_id());
        n.set_id(addNotification(n));

        given().header("token", u.getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, n.get_id())
               .when().post(getURL(NotificationsVerticle.READ))
               .then().assertThat().statusCode(200)
               .body("status", notNullValue())
               .body("status", is(true));

        given().header("token", u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(1))
               .body("[0].content", is("Hello"))
               .body("[0]._id", is(n.get_id()))
               .body("[0].read", is(true));
    }

    /**
     * Mark as read with wrong id test.
     */
    @Test
    public void markAsReadWithWrongIdTest() {
        given().header("token", generateLoggedUser().getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().post(getURL(NotificationsVerticle.READ))
               .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
               .body("code", is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Mark as read with missing id test.
     */
    @Test
    public void markAsReadWithMissingIdTest() {
        given().header("token", generateLoggedUser().getAccount().getToken())
               .when().post(getURL(NotificationsVerticle.READ))
               .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
               .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Mark as read with wrong http method test.
     */
    @Test
    public void markAsReadWithWrongHttpMethodTest() {
        given().header("token", generateLoggedUser().getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().get(getURL(NotificationsVerticle.READ))
               .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
               .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Delete notification test.
     */
    @Test
    public void deleteNotificationTest() {
        final User u = generateLoggedUser();

        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setFrom_user_id(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setUser_id(u.get_id());
        n.set_id(addNotification(n));
        given().header("token", u.getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, n.get_id())
               .when().delete(getURL(NotificationsVerticle.DEL))
               .then().assertThat().statusCode(200)
               .body("status", notNullValue())
               .body("status", is(true));

        given().header("token", u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(0));
    }

    /**
     * Delete notification with wrong id test.
     */
    @Test
    public void deleteNotificationWithWrongIdTest() {
        given().header("token", generateLoggedUser().getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().delete(getURL(NotificationsVerticle.DEL))
               .then().assertThat().statusCode(ExceptionCodes.DATA_ERROR.getCode())
               .body("code", is(ExceptionCodes.DATA_ERROR.toString()));
    }

    /**
     * Delete notification with missing id test.
     */
    @Test
    public void deleteNotificationWithMissingIdTest() {
        given().header("token", generateLoggedUser().getAccount().getToken())
               .when().delete(getURL(NotificationsVerticle.DEL))
               .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
               .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Delete notification with wrong http method test.
     */
    @Test
    public void deleteNotificationWithWrongHttpMethodTest() {
        given().header("token", generateLoggedUser().getAccount().getToken())
               .queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().get(getURL(NotificationsVerticle.DEL))
               .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
               .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Delete notification with not logged test.
     */
    @Test
    public void deleteNotificationWithNotLoggedTest() {
        given().queryParam(NotificationsVerticle.PARAM_NOTIF_ID, "blabla")
               .when().delete(getURL(NotificationsVerticle.DEL))
               .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
               .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
    }

    /**
     * Add notification to user test.
     */
    @Test
    public void addNotificationToUserTest() {
        User u = generateLoggedUser();
        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setFrom_user_id(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setUser_id(u.get_id());

        given().header("token", u.getAccount().getToken())
               .queryParam("id", u.get_id())
               .body(Json.encode(n))
               .when().post(getURL(NotificationsVerticle.ADD))
               .then().assertThat().statusCode(200)
               .body("status", notNullValue())
               .body("status", is(true));

        given().header("token", u.getAccount().getToken())
               .when().get(getURL(NotificationsVerticle.LIST))
               .then().assertThat().statusCode(200)
               .body("", hasSize(1))
               .body("[0].content", is("Hello"))
               .body("[0]._id", notNullValue());
    }

    /**
     * Add notification to user with wrong user id test.
     */
    @Test
    public void addNotificationToUserWithWrongUserIdTest() {
        User u = generateLoggedUser();
        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setFrom_user_id(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setUser_id(u.get_id());
        given().header("token", u.getAccount().getToken())
               .queryParam("id", "blabla")
               .body(Json.encode(n))
               .when().post(getURL(NotificationsVerticle.ADD))
               .then().assertThat().statusCode(200)
               .body("status", notNullValue())
               .body("status", is(false));
    }

    /**
     * Add notification to user with missing id test.
     */
    @Test
    public void addNotificationToUserWithMissingIdTest() {
        given().header("token", generateLoggedUser().getAccount().getToken())
               .when().post(getURL(NotificationsVerticle.ADD))
               .then().assertThat().statusCode(ExceptionCodes.MANDATORY_FIELD.getCode())
               .body("code", is(ExceptionCodes.MANDATORY_FIELD.toString()));
    }

    /**
     * Add notification to user with wrong http method test.
     */
    @Test
    public void addNotificationToUserWithWrongHttpMethodTest() {
        given().header("token", generateLoggedUser().getAccount().getToken())
               .queryParam("id", "blabla")
               .when().get(getURL(NotificationsVerticle.ADD))
               .then().assertThat().statusCode(ExceptionCodes.HTTP_ERROR.getCode())
               .body("code", is(ExceptionCodes.HTTP_ERROR.toString()));
    }

    /**
     * Add notification to user with not logged test.
     */
    @Test
    public void addNotificationToUserWithNotLoggedTest() {
        given().queryParam("id", "blabla")
               .when().post(getURL(NotificationsVerticle.ADD))
               .then().assertThat().statusCode(ExceptionCodes.NOT_LOGGED.getCode())
               .body("code", is(ExceptionCodes.NOT_LOGGED.toString()));
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
