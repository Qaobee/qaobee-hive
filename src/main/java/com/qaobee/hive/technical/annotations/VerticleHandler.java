package com.qaobee.hive.technical.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The interface Verticle handler.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface VerticleHandler {

    /**
     * Value rule [ ].
     *
     * @return the value [ ]
     */
    Rule[] value();
}
