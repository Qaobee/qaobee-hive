/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */

package com.qaobee.hive.technical.utils.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.qaobee.hive.dao.*;
import com.qaobee.hive.dao.impl.*;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.utils.MongoClientCustom;
import com.qaobee.hive.technical.utils.QaobeeAuthHandler;
import com.qaobee.hive.technical.vertx.MandatoryHandler;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mail.LoginOption;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.StartTLSOptions;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.handler.AuthHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * The type Guice module.
 */
public class GuiceModule extends AbstractModule {
    private static final Logger LOG = LoggerFactory.getLogger(GuiceModule.class);
    private static final String MAIL_CONF_KEY = "mailer.mod";
    private final JsonObject config;
    private final Vertx vertx;

    /**
     * Instantiates a new Guice module.
     *
     * @param config the config
     * @param vertx  the vertx
     */
    public GuiceModule(JsonObject config, Vertx vertx) {
        this.config = config;
        this.vertx = vertx;
    }

    /**
     * Configure void.
     */
    @Override
    protected void configure() {
        config.getMap().keySet().forEach(k -> bind(JsonObject.class).annotatedWith(Names.named(k)).toInstance(config.getJsonObject(k)));
        bind(Vertx.class).toInstance(vertx);
        // TECHNICAL MODULES
        bind(WebClient.class).toInstance(WebClient.create(vertx));
        bind(AuthHandler.class).toInstance(new QaobeeAuthHandler());
        MailConfig mailConfig = new MailConfig();
        mailConfig.setHostname(config.getJsonObject(MAIL_CONF_KEY).getString("host"));
        mailConfig.setPort(config.getJsonObject(MAIL_CONF_KEY).getInteger("port"));
        mailConfig.setSsl(config.getJsonObject(MAIL_CONF_KEY).getBoolean("ssl"));
        if (config.getJsonObject(MAIL_CONF_KEY).getBoolean("auth")) {
            mailConfig.setUsername(config.getJsonObject(MAIL_CONF_KEY).getString("username"));
            mailConfig.setPassword(config.getJsonObject(MAIL_CONF_KEY).getString("password"));
            mailConfig.setLogin(LoginOption.REQUIRED);
            mailConfig.setStarttls(StartTLSOptions.REQUIRED);
        }


        bind(PasswordEncryptionService.class).to(PasswordEncryptionServiceImpl.class).in(Singleton.class);
        bind(PdfDAO.class).to(PdfDAOImpl.class).in(Singleton.class);
        bind(ReCaptcha.class).to(RecaptchaImpl.class).in(Singleton.class);
        bind(MailClient.class).toInstance(MailClient.createShared(vertx, mailConfig, "qaobeeMail"));
        bind(MongoClientCustom.class).toProvider(MongoClientProvider.class).asEagerSingleton();
        bind(MailUtils.class).to(MailUtilsImpl.class).in(Singleton.class);
        bind(HabilitUtils.class).to(HabilitUtilsImpl.class).in(Singleton.class);
        bind(Utils.class).to(UtilsImpl.class).in(Singleton.class);
        bind(MandatoryHandler.class).toInstance(new MandatoryHandler());

        // Templates
        Configuration cfgMails = new Configuration(new Version("2.3.23"));
        cfgMails.setClassForTemplateLoading(this.getClass(), "/mailTemplates");
        cfgMails.setIncompatibleImprovements(new Version(2, 3, 20));
        cfgMails.setDefaultEncoding("UTF-8");
        cfgMails.setLocale(Locale.US);
        cfgMails.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Configuration cfgPDF = new Configuration(new Version("2.3.23"));
        cfgPDF.setClassForTemplateLoading(this.getClass(), "/pdfTemplates");
        bind(TemplatesDAO.class).toInstance(new TemplatesDAOImpl(cfgMails, cfgPDF));

        // Services
        ProxyService.Loader.scan("com.qaobee.hive.services").forEach(c -> {
            LOG.debug(String.format("Binding %s with address %s", c.getCanonicalName(), c.getAnnotation(ProxyService.class).address()));
            Class iface = c.getAnnotation(ProxyService.class).iface();
            try {
                Method method = iface.getMethod("createProxy", new Class<?>[]{Vertx.class, String.class});
                bind(iface).toInstance(method.invoke(null, new Object[]{vertx, c.getAnnotation(ProxyService.class).address()}));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                LOG.error(e.getMessage(), e);
            }
        });
    }
}
