package com.qaobee.hive.api.v1.commons.users;

import com.asana.Client;
import com.asana.models.User;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * The type Feedback verticle.
 */
@DeployableVerticle(isWorker = true)
public class FeedbackVerticle extends AbstractGuiceVerticle {
    /**
     * The constant POST_FEEDBACK.
     */
    public static final String POST_FEEDBACK = Module.VERSION + ".commons.feedback.send";
    private static final Logger LOG = LoggerFactory.getLogger(FeedbackVerticle.class);
    /**
     * The Utils.
     */
    @Inject
    private Utils utils;

    @Inject
    @Named("asana")
    private JsonObject config;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus().registerHandler(POST_FEEDBACK, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                try {
                    final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
                    utils.testHTTPMetod(Constantes.POST, req.getMethod());
                    String[] postRequest = req.getBody().split("=");

                    String decoded = URLDecoder.decode(postRequest[1]);

                    final JsonObject data = new JsonObject(decoded);
                    String me = "28216887974449";

                    Client client = Client.basicAuth(config.getString("apikey"));
                    User m = client.users.me().execute();
                    System.out.println(m.id + " " + m.name);
                    JsonObject asanaReq = new JsonObject();
                    asanaReq.putString("name", data.getString("note"));
                    asanaReq.putString("notes", data.getObject("browser").encodePrettily() + "\n" +
                            data.getString("url")); // + "\n" +
                    //data.getString("img"));
                    asanaReq.putArray("projects", new JsonArray().add(config.getString("project")));
                    asanaReq.putString("assignee", m.id );
                    client.tasks.create().data(asanaReq.toMap()).execute();
                    utils.sendStatus(true, message);
                } catch (final NoSuchMethodException e) {
                    LOG.error(e.getMessage(), e);
                    utils.sendError(message, ExceptionCodes.HTTP_ERROR, e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
