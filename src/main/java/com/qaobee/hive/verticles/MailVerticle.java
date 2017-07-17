package com.qaobee.hive.verticles;

import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailMessage;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The type Mail verticle.
 */
@DeployableVerticle
public class MailVerticle extends AbstractGuiceVerticle {
    /**
     * The constant INTERNAL_MAIL.
     */
    public static final String INTERNAL_MAIL = "internal.mail.send";
    private static final Logger LOG = LoggerFactory.getLogger(MailVerticle.class);
    @Inject
    private MailClient mailClient;
    @Inject
    @Named("mailer.mod")
    private JsonObject conf;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this).add(INTERNAL_MAIL, this::sendMail).register(startFuture);
    }

    private void sendMail(Message<JsonObject> message) {
        if (!conf.getBoolean("test", false)) {
            MailMessage mailMessage = new MailMessage();
            mailMessage.setFrom(message.body().getString("from"));
            mailMessage.setTo(message.body().getString("to"));
            mailMessage.setSubject(message.body().getString("subject"));
            mailMessage.setHtml(message.body().getString("body"));
            mailMessage.addHeader(HTTP.CONTENT_TYPE, message.body().getString("content_type"));
            mailClient.sendMail(mailMessage, result -> {
                if (result.succeeded()) {
                    message.reply(result.result().toJson());
                } else {
                    LOG.error(result.cause().getMessage(), result.cause());
                    message.fail(ExceptionCodes.MAIL_EXCEPTION.getCode(), result.cause().getMessage());
                }
            });
        } else {
            message.reply(new JsonObject());
        }
    }
}
