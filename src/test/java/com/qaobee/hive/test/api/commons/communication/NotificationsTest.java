package com.qaobee.hive.test.api.commons.communication;

import com.qaobee.hive.api.v1.commons.communication.NotificationsVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.communication.Notification;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import com.qaobee.hive.test.config.VertxJunitSupport;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.ArrayList;

/**
 * The type Notifications test.
 */
public class NotificationsTest extends VertxJunitSupport {
    /**
     * A notification.
     */
    @Test
    public void getNotifications() {
        final User u = generateUser();

        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setFrom_user_id(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setRead(false);

        n.set_id(addnotification(n, u));

        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(u);
        final JsonArray jar = new JsonArray(sendonBus(NotificationsVerticle.LIST, req));
        Assert.assertTrue(jar.size() > 0);
        boolean found = false;
        for (int index = 0; index < jar.size(); index++) {
            final JsonObject item = jar.get(index);
            if (item.getString("_id").equals(n.get_id())) {
                Assert.assertEquals("Hello", item.getString("content"));
                found = true;
            }
        }
        Assert.assertTrue(found);
    }

    /**
     * Mark As Read.
     */
    @Test
    public void markAsRead() {
        final User u = generateUser();

        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setFrom_user_id(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setRead(false);

        n.set_id(addnotification(n, u));

        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.POST);
        req.setParams(getParams(new String[]{"id", n.get_id()}));
        req.setUser(u);
        final JsonObject status = new JsonObject(sendonBus(NotificationsVerticle.READ, req));
        Assert.assertTrue(status.getBoolean("status", false));

        req.setMethod(Constantes.GET);
        final JsonArray jar = new JsonArray(sendonBus(NotificationsVerticle.LIST, req));
        Assert.assertTrue(jar.size() > 0);
        boolean found = false;
        for (int index = 0; index < jar.size(); index++) {
            final JsonObject item = jar.get(index);
            if (item.getString("_id").equals(n.get_id())) {
                Assert.assertEquals("Hello", item.getString("content"));
                Assert.assertTrue(item.getBoolean("read"));
                found = true;
            }
        }
        Assert.assertTrue(found);
    }

    /**
     * Deletenotification.
     */
    @Test
    public void delete() {
        final User u = generateUser();

        final Notification n = new Notification();
        n.setContent("Hello");
        n.setTitle("Message");
        n.setFrom_user_id(u.get_id());
        n.setTimestamp(System.currentTimeMillis());
        n.setRead(false);

        n.set_id(addnotification(n, u));

        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.DELETE);
        req.setParams(getParams(new String[]{"id", n.get_id()}));
        req.setUser(u);
        final JsonObject status = new JsonObject(sendonBus(NotificationsVerticle.DEL, req));
        Assert.assertTrue(status.getBoolean("status", false));

        req.setParams(null);
        req.setMethod(Constantes.GET);

        final JsonArray jar = new JsonArray(sendonBus(NotificationsVerticle.LIST, req));
        boolean found = false;
        for (int index = 0; index < jar.size(); index++) {
            final JsonObject item = jar.get(index);
            if (item.getString("_id").equals(n.get_id())) {
                found = true;
            }
        }
        Assert.assertFalse(found);
    }

    /**
     * No notifications.
     */
    @Test
    public void getEmptyNotifications() {
        final User u = generateUser();
        // del all notifications
        final RequestWrapper req = new RequestWrapper();
        req.setLocale(LOCALE);
        req.setMethod(Constantes.GET);
        req.setUser(u);
        final JsonArray jar = new JsonArray(sendonBus(NotificationsVerticle.LIST, req));
        for (int index = 0; index < jar.size(); index++) {
            final JsonObject item = jar.get(index);
            req.setMethod(Constantes.DELETE);
            req.setParams(getParams(new String[]{"id", item.getString("_id")}));
            final JsonObject status = new JsonObject(sendonBus(NotificationsVerticle.DEL, req));
            Assert.assertTrue(status.getBoolean("status", false));
        }

        req.setMethod(Constantes.GET);

        final JsonArray jar2 = new JsonArray(sendonBus(NotificationsVerticle.LIST, req));
        Assert.assertEquals(0, jar2.size());
    }

    /**
     * Addnotification.
     *
     * @param n Notification
     * @param u User
     * @return notification id
     */
    private String addnotification(final Notification n, final User u) {
        String nid = null;
        try {
            nid = mongo.save(n);
        } catch (EncodeException | QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        if (nid == null) {
            Assert.fail("notification id is null");
        }
        n.set_id(nid);

        u.setNotifications(new ArrayList<Notification>());
        u.getNotifications().add(n);

        try {
            mongo.save(u);
        } catch (EncodeException | QaobeeException e) {
            Assert.fail(e.getMessage());
        }
        return nid;
    }
}
