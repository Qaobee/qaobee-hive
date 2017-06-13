package com.qaobee.hive.technical.annotations;

import com.google.inject.Injector;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.serviceproxy.ProxyHelper;
import org.reflections.Reflections;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * The interface Injectable service.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyService {
    /**
     * Address string.
     *
     * @return the string
     */
    String address();

    /**
     * Iface class.
     *
     * @return the class
     */
    Class iface();

    /**
     * The type Loader.
     */
    class Loader {
        private static final Logger LOG = LoggerFactory.getLogger(ProxyService.class.getName());

        private Loader() {
            // empty
        }

        /**
         * Load.
         *
         * @param packageName the package name
         * @param injector    the injector
         * @param vertx       the vertx
         */
        public static void load(String packageName, Injector injector, Vertx vertx) {
            Reflections reflections = new Reflections(packageName);
            Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ProxyService.class);
            for (Class<?> rit : annotated) {
                try {
                    Object r = rit.getConstructor(Vertx.class).newInstance(vertx);
                    LOG.info("Getting service : " + rit.getCanonicalName());
                    injector.injectMembers(r);
                    ProxyHelper.registerService(r.getClass().getAnnotation(ProxyService.class).iface(), vertx, r, r.getClass().getAnnotation(ProxyService.class).address());
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                        | InvocationTargetException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

    }
}