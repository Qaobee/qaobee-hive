package com.qaobee.hive.technical.annotations;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import org.reflections.Reflections;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The interface Vertx route.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface VertxRoute {
    /**
     * Root path string.
     *
     * @return the string
     */
    String rootPath() default "/";

    /**
     * Order int.
     *
     * @return the int
     */
    int order() default 0;

    /**
     * The interface Route.
     */
    @FunctionalInterface
    interface Route {
        /**
         * Init router.
         *
         * @return the router
         */
        Router init();
    }

    /**
     * The type Loader.
     */
    class Loader {
        private static final Logger LOG = LoggerFactory.getLogger(VertxRoute.class.getName());

        private Loader() {
            // empty
        }

        /**
         * Gets routes in package.
         *
         * @param packageName the package name
         * @return the routes in package
         */
        public static Map<VertxRoute, Route> getRoutesInPackage(String packageName) {
            Reflections reflections = new Reflections(packageName);
            Map<VertxRoute, Route> routers = new HashMap<>();
            Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(VertxRoute.class);
            for (Class<?> rit : annotated) {
                try {
                    if (Route.class.isAssignableFrom(rit)) {
                        Route r = (Route) rit.getConstructor().newInstance();
                        LOG.info("Getting route : " + rit.getCanonicalName());
                        routers.put(rit.getAnnotation(VertxRoute.class), r);
                    }
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                        | InvocationTargetException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            return routers;
        }
    }
}
