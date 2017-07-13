package com.qaobee.hive.dao.impl;

import com.qaobee.hive.dao.CRMDao;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private WebClient webClient;


    @Override
    public void registerUser(JsonObject user, boolean firstLogin) {

        if (!mailchimp.getBoolean("test", false)) {
            JsonArray members = new JsonArray();
            String env = "DEV";
            if (StringUtils.isNotBlank(System.getenv("ENV"))) {
                env = System.getenv("ENV");
            }
            members.add(new JsonObject()
                    .put("merge_fields", new JsonObject()
                            .put("NAME", user.getString("name"))
                            .put("FIRSTNAME", user.getString("firstname"))
                            .put("NEVERCONNE", String.valueOf(firstLogin))
                            .put("ENV", env)
                    )
                    .put("status", "subscribed")
                    .put("email_address", user.getJsonObject("contact").getString("email"))
            );
            JsonObject requestBody = new JsonObject()
                    .put("members", members)
                    .put("update_existing", true);
            HttpRequest<Buffer> req = webClient.post(mailchimp.getInteger("port"), mailchimp.getString("host"), mailchimp.getString("path"));
            if (mailchimp.getInteger("port") == 443) {
                req.ssl(true);
            }
            req.putHeader("Authorization", "Basic " + Base64.encodeBase64String((mailchimp.getString("user") + ":" + mailchimp.getString("key")).getBytes()))
                    .putHeader(HTTP.CONTENT_TYPE, "application/json")
                    //    .putHeader(HTTP.CONTENT_LEN, String.valueOf(requestBody..length()))
                    .sendJsonObject(requestBody, res -> {
                        if (res.succeeded()) {
                            LOG.info(requestBody.encode() + " : ok");
                        } else {
                            LOG.error(res.cause().getMessage(), res.cause());
                        }
                    });
        }
    }
}
