package com.qaobee.hive.dao.impl;

import com.asana.Client;
import com.asana.models.Task;
import com.asana.models.User;
import com.qaobee.hive.dao.FeedbackDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * The type Feedback dao.
 */
public class FeedbackDAOImpl implements FeedbackDAO {
    private static final Logger LOG = LoggerFactory.getLogger(FeedbackDAOImpl.class);
    @Inject
    @Named("asana")
    private JsonObject config;

    @Override
    public void sendFeedback(JsonObject data) throws QaobeeException {
        try {
            Client client = Client.basicAuth(config.getString("apikey"));
            User m = client.users.me().execute();
            String title = "";
            if (data.containsField("meta") && data.getObject("meta").containsField("user")) {
                title += "[" + data.getObject("meta").getObject("user").getString("firstname") + " " + data.getObject("meta").getObject("user").getString("name") + "] ";
            }
            title += data.getString("note");
            JsonObject asanaReq = new JsonObject()
                    .putString("name", title)
                    .putString("notes", data.getString("url") + "\n" + data.getObject("browser").encodePrettily())
                    .putArray("projects", new JsonArray().add(config.getString("project")))
                    .putString("assignee", m.id);
            Task t = client.tasks.create().data(asanaReq.toMap()).execute();
            byte[] img = Base64.decodeBase64(data.getString("img").replace("data:image/png;base64,", ""));
            File temp = File.createTempFile("temp-file-name", ".tmp");
            FileUtils.writeByteArrayToFile(temp, img);
            FileInputStream in = new FileInputStream(temp);
            client.attachments.createOnTask(t.id, in, UUID.randomUUID().toString() + ".png", "image/png").execute();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e.getMessage());
        }
    }
}
