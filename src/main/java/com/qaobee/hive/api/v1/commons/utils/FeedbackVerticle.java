package com.qaobee.hive.api.v1.commons.utils;

import com.asana.Client;
import com.asana.models.Task;
import com.asana.models.User;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * The type Feedback verticle.
 */
@DeployableVerticle
public class FeedbackVerticle extends AbstractGuiceVerticle {

    private static final String POST_FEEDBACK = Module.VERSION + ".commons.feedback.send";
    private static final Logger LOG = LoggerFactory.getLogger(FeedbackVerticle.class);
    @Inject
    private Utils utils;
    @Inject
    @Named("asana")
    private JsonObject config;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(POST_FEEDBACK,this::postFeedbackHandler)
                .registerHandler("internal.feedback.send", this::internarFeebackHandler);
    }

    private void internarFeebackHandler(Message<JsonObject> message) {// NOSONAR
        try {
            JsonObject data = message.body();
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
        } catch (final IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * @apiDescription Send feedback
     * @api {post} /api/1/commons/feedback/send update user
     * @apiName POST_FEEDBACK
     * @apiGroup FeedbackVerticle
     * @apiParam {String} param URL encoded string from feedback.js
     * @apiSuccess {Object} status boolean status
     */
    @Rule(address = POST_FEEDBACK, method = Constants.POST)
    private void postFeedbackHandler(Message<String> message) {// NOSONAR
        try {
            final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
            String[] postRequest = req.getBody().split("=");
            String decoded = URLDecoder.decode(postRequest[1], StandardCharsets.UTF_8.toString());
            final JsonObject data = new JsonObject(decoded);
            vertx.eventBus().send("internal.feedback.send", data);
            utils.sendStatus(true, message);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
        }
    }
}
