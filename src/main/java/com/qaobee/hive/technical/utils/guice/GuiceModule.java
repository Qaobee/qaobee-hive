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
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.mongo.impl.MongoDBImpl;
import com.qaobee.hive.technical.utils.HabilitUtils;
import com.qaobee.hive.technical.utils.MailUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.impl.HabilitUtilsImpl;
import com.qaobee.hive.technical.utils.impl.MailUtilsImpl;
import com.qaobee.hive.technical.utils.impl.UtilsImpl;
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

import java.util.Locale;

/**
 * The type Guice module.
 */
public class GuiceModule extends AbstractModule {
    private static final String MAIL_CONF_KEY = "mailer.mod";
    private final JsonObject config;
    private final Vertx vertx;

    /**
     * Instantiates a new Guice module.
     *
     * @param config the config
     * @param vertx  the vertx
     */
    GuiceModule(JsonObject config, Vertx vertx) {
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
        bind(MongoDB.class).to(MongoDBImpl.class).in(Singleton.class);
        bind(WebClient.class).toInstance(WebClient.create(vertx));
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


        bind(MailClient.class).toInstance(MailClient.createShared(vertx, mailConfig, "qaobeeMail"));
        bind(MongoClientCustom.class).toProvider(MongoClientProvider.class).asEagerSingleton();
        bind(MailUtils.class).to(MailUtilsImpl.class).in(Singleton.class);
        bind(PasswordEncryptionService.class).to(PasswordEncryptionServiceImpl.class).in(Singleton.class);
        bind(HabilitUtils.class).to(HabilitUtilsImpl.class).in(Singleton.class);
        bind(Utils.class).to(UtilsImpl.class).in(Singleton.class);

        //
        Configuration cfgMails = new Configuration(new Version("2.3.23"));
        cfgMails.setClassForTemplateLoading(this.getClass(), "/mailTemplates");
        cfgMails.setIncompatibleImprovements(new Version(2, 3, 20));
        cfgMails.setDefaultEncoding("UTF-8");
        cfgMails.setLocale(Locale.US);
        cfgMails.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Configuration cfgPDF = new Configuration(new Version("2.3.23"));
        cfgPDF.setClassForTemplateLoading(this.getClass(), "/pdfTemplates");
        bind(TemplatesDAO.class).toInstance(new TemplatesDAOImpl(cfgMails, cfgPDF));

        // DAO
        bind(ActivityCfgDAO.class).to(ActivityCfgDAOImpl.class).in(Singleton.class);
        bind(ActivityDAO.class).to(ActivityDAOImpl.class).in(Singleton.class);
        bind(ShareDAO.class).to(ShareDAOImpl.class).in(Singleton.class);
        bind(NotificationsDAO.class).to(NotificationsDAOImpl.class).in(Singleton.class);
        bind(ChampionshipDAO.class).to(ChampionshipDAOImpl.class).in(Singleton.class);
        bind(StructureDAO.class).to(StructureDAOImpl.class).in(Singleton.class);
        bind(EventDAO.class).to(EventDAOImpl.class).in(Singleton.class);
        bind(CollectDAO.class).to(CollectDAOImpl.class).in(Singleton.class);
        bind(CountryDAO.class).to(CountryDAOImpl.class).in(Singleton.class);
        bind(IndicatorDAO.class).to(IndicatorDAOImpl.class).in(Singleton.class);
        bind(SeasonDAO.class).to(SeasonDAOImpl.class).in(Singleton.class);
        bind(UserDAO.class).to(UserDAOImpl.class).in(Singleton.class);
        bind(ShippingDAO.class).to(ShippingDAOImpl.class).in(Singleton.class);
        bind(SignupDAO.class).to(SignupDAOImpl.class).in(Singleton.class);
        bind(SandBoxDAO.class).to(SandBoxDAOImpl.class).in(Singleton.class);
        bind(SecurityDAO.class).to(SecurityDAOImpl.class).in(Singleton.class);
        bind(AssetDAO.class).to(AssetDAOImpl.class).in(Singleton.class);
        bind(FeedbackDAO.class).to(FeedbackDAOImpl.class).in(Singleton.class);
        bind(FeedbackDAO.class).to(FeedbackDAOImpl.class).in(Singleton.class);
        bind(EffectiveDAO.class).to(EffectiveDAOImpl.class).in(Singleton.class);
        bind(PersonDAO.class).to(PersonDAOImpl.class).in(Singleton.class);
        bind(PdfDAO.class).to(PdfDAOImpl.class).in(Singleton.class);
        bind(TeamDAO.class).to(TeamDAOImpl.class).in(Singleton.class);
        bind(StatisticsDAO.class).to(StatisticsDAOImpl.class).in(Singleton.class);
        bind(ReCaptcha.class).to(RecaptchaImpl.class).in(Singleton.class);
        bind(CRMDao.class).to(CRMDaoImpl.class).in(Singleton.class);
    }
}
