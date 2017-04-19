package com.qaobee.hive.dao.impl;

import com.qaobee.hive.dao.CRMDao;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The type Crm dao.
 */
public class CRMDaoImpl implements CRMDao {
    private static final Logger LOG = LoggerFactory.getLogger(CRMDaoImpl.class);
    @Inject
    @Named("mailchimp")
    private JsonObject mailchimp;
    @Inject
    private Vertx vertx;
    @Inject
    @Named("env")
    private JsonObject env;


    @Override
    public void registerUser(JsonObject user, boolean firstLogin) {
        JsonArray members = new JsonArray();
        members.add(new JsonObject()
                .putObject("merge_fields", new JsonObject()
                        .putString("NAME", user.getString("name"))
                        .putString("FIRSTNAME", user.getString("firstname"))
                        .putString("NEVERCONNE", String.valueOf(firstLogin))
                        .putString("ENV", env.getString("ENV", "DEV"))
                )
                .putString("status", "subscribed")
                .putString("email_address", user.getObject("contact").getString("email"))
        );
        JsonObject requestBody = new JsonObject()
                .putArray("members", members)
                .putBoolean("update_existing", true);
        String body = requestBody.encode();
        HttpClient client = vertx.createHttpClient().setKeepAlive(true);
        client.setHost(mailchimp.getString("host"));
        client.setPort(mailchimp.getInteger("port"));
        if (mailchimp.getInteger("port") == 443) {
            client.setSSL(true).setTrustAll(true);
        }
        LOG.info(body);
        client.post(mailchimp.getString("path"), resp -> {
            LOG.info("Status : " + resp.statusCode());
            resp.bodyHandler(buffer -> LOG.info(buffer.toString()));
        })
                .putHeader("Authorization", "Basic " + Base64.encodeBase64String((mailchimp.getString("user") + ":" + mailchimp.getString("key")).getBytes()))
                .putHeader(HTTP.CONTENT_TYPE, "application/json")
                .putHeader(HTTP.CONTENT_LEN, String.valueOf(body.length()))
                .end(body);
    }
}
