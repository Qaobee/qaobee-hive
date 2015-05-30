package com.qaobee.technical.vertx.utils.guice;

import org.vertx.java.platform.Verticle;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by xavier on 09/11/14.
 */
public class AbstractGuiceVerticle extends Verticle {
    @Override
    public void start() {
        Injector injector = Guice.createInjector(new GuiceModule(container.config()));
        injector.injectMembers(this);
    }
}
