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
import com.qaobee.hive.business.commons.users.UsersBusiness;
import com.qaobee.hive.business.commons.users.impl.UsersBusinessImpl;
import com.qaobee.hive.dao.*;
import com.qaobee.hive.dao.impl.*;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.PasswordEncryptionService;
import com.qaobee.hive.technical.tools.PasswordEncryptionServiceImpl;
import com.qaobee.hive.technical.utils.*;
import com.qaobee.hive.technical.utils.guice.provides.MongoProvider;
import com.qaobee.hive.technical.utils.guice.services.Files;
import com.qaobee.hive.technical.utils.guice.services.impl.FilesImpl;
import com.qaobee.hive.technical.utils.impl.*;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonObject;

/**
 * The type Guice module.
 */
public class GuiceModule extends AbstractModule {

    /**
     * The Config.
     */
    private JsonObject config;
    private Vertx vertx;

    /**
     * Instantiates a new Guice module.
     *
     * @param config the config
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
        //get the vertx configuration
        bind(JsonObject.class).annotatedWith(Names.named("mongo.persistor")).toInstance(config.getObject("mongo.persistor"));
        bind(JsonObject.class).annotatedWith(Names.named("payplug")).toInstance(config.getObject("payplug"));
        bind(JsonObject.class).annotatedWith(Names.named("asana")).toInstance(config.getObject("asana"));

        bind(Vertx.class).toInstance(vertx);
        // TECHNICAL MODULES
        bind(MongoDB.class).toProvider(MongoProvider.class).in(Singleton.class);
        bind(MailUtils.class).to(MailUtilsImpl.class).in(Singleton.class);
        bind(AuthCheck.class).to(AuthCheckImpl.class).in(Singleton.class);
        bind(PasswordEncryptionService.class).to(PasswordEncryptionServiceImpl.class).in(Singleton.class);
        bind(PersonUtils.class).to(PersonUtilsImpl.class).in(Singleton.class);
        bind(HabilitUtils.class).to(HabilitUtilsImpl.class).in(Singleton.class);
        bind(Utils.class).to(UtilsImpl.class).in(Singleton.class);
        bind(Files.class).to(FilesImpl.class).in(Singleton.class);
        // BUSINESS MODULES
        bind(UsersBusiness.class).to(UsersBusinessImpl.class).in(Singleton.class);

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
    }
}
