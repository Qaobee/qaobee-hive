package com.qaobee.hive.technical.vertx.utils.guice;


import org.vertx.java.core.json.JsonObject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.PasswordEncryptionService;
import com.qaobee.hive.technical.tools.PasswordEncryptionServiceImpl;
import com.qaobee.hive.technical.utils.PersonUtils;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.impl.PersonUtilsImpl;
import com.qaobee.hive.technical.utils.impl.UtilsImpl;
import com.qaobee.hive.technical.vertx.utils.guice.provides.MongoProvider;
import com.qaobee.hive.technical.vertx.utils.guice.services.Files;
import com.qaobee.hive.technical.vertx.utils.guice.services.impl.FIlesImpl;

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
//        bind(MailUtils.class).to(MailUtilsImpl.class).in(Singleton.class);
//        bind(AuthCheck.class).to(AuthCheckImpl.class).in(Singleton.class);
//        bind(SeasonCheck.class).to(SeasonCheckImpl.class).in(Singleton.class);
        bind(PasswordEncryptionService.class).to(PasswordEncryptionServiceImpl.class).in(Singleton.class);
        bind(PersonUtils.class).to(PersonUtilsImpl.class).in(Singleton.class);

        bind(Utils.class).to(UtilsImpl.class).in(Singleton.class);
        bind(Files.class).to(FIlesImpl.class).in(Singleton.class);
    }
}
