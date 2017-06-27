package com.qaobee.hive.api.v1.commons.utils;

import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailMessage;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

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

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus().consumer(INTERNAL_MAIL, this::sendMail);
    }

    private void sendMail(Message<JsonObject> message) {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setFrom(message.body().getString("from"));
        mailMessage.setTo(message.body().getString("to"));
        mailMessage.setSubject(message.body().getString("subject"));
        mailMessage.setHtml(message.body().getString("body"));
        mailMessage.addHeader(HTTP.CONTENT_TYPE, message.body().getString("content_type"));
        mailClient.sendMail(mailMessage, result -> {
            if (result.succeeded()) {
                message.reply(result.result());
            } else {
                LOG.error(result.cause().getMessage(), result.cause());
                message.fail(ExceptionCodes.MAIL_EXCEPTION.getCode(), result.cause().getMessage());
            }
        });
    }
}
