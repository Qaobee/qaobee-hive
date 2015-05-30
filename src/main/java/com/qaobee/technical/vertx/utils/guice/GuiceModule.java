package com.qaobee.technical.vertx.utils.guice;


import org.vertx.java.core.json.JsonObject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.qaobee.swarn.business.control.administration.settings.SeasonCheck;
import com.qaobee.swarn.business.control.administration.settings.SeasonCheckImpl;
import com.qaobee.swarn.business.control.tranversal.AuthCheck;
import com.qaobee.swarn.business.control.tranversal.AuthCheckImpl;
import com.qaobee.swarn.business.control.tranversal.communication.MailUtils;
import com.qaobee.swarn.business.control.tranversal.communication.MailUtilsImpl;
import com.qaobee.swarn.technical.mongo.MongoDB;
import com.qaobee.swarn.technical.tools.PasswordEncryptionService;
import com.qaobee.swarn.technical.tools.PasswordEncryptionServiceImpl;
import com.qaobee.swarn.technical.utils.HabilitUtils;
import com.qaobee.swarn.technical.utils.PersonUtils;
import com.qaobee.swarn.technical.utils.Utils;
import com.qaobee.swarn.technical.utils.impl.HabilitUtilsImpl;
import com.qaobee.swarn.technical.utils.impl.PersonUtilsImpl;
import com.qaobee.swarn.technical.utils.impl.UtilsImpl;
import com.qaobee.swarn.verticles.utils.guice.provides.MongoProvider;
import com.qaobee.swarn.verticles.utils.guice.services.Files;
import com.qaobee.swarn.verticles.utils.guice.services.impl.FIlesImpl;

/**
 * Created by xavier on 09/11/14.
 */
public class GuiceModule extends AbstractModule {


    private JsonObject config;

    public GuiceModule(JsonObject config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        //get the vertx configuration
        JsonObject mongoConfig = config.getObject("mongo.persistor");

        bind(JsonObject.class)
                .annotatedWith(Names.named("mongo.persistor"))
                .toInstance(mongoConfig);

        bind(MongoDB.class).toProvider(MongoProvider.class).in(Singleton.class);
        bind(MailUtils.class).to(MailUtilsImpl.class).in(Singleton.class);
        bind(AuthCheck.class).to(AuthCheckImpl.class).in(Singleton.class);
        bind(SeasonCheck.class).to(SeasonCheckImpl.class).in(Singleton.class);
        bind(PasswordEncryptionService.class).to(PasswordEncryptionServiceImpl.class).in(Singleton.class);
        bind(PersonUtils.class).to(PersonUtilsImpl.class).in(Singleton.class);
        bind(HabilitUtils.class).to(HabilitUtilsImpl.class).in(Singleton.class);
        bind(Utils.class).to(UtilsImpl.class).in(Singleton.class);
        bind(Files.class).to(FIlesImpl.class).in(Singleton.class);
    }
}
