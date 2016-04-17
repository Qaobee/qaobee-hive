package com.qaobee.hive.technical.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The interface Rule.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Rule {
    /**
     * Address string.
     *
     * @return the string
     */
    String address();

    /**
     * Method string.
     *
     * @return the string
     */
    String method();

    /**
     * Logged boolean.
     *
     * @return the boolean
     */
    boolean logged() default false;

    /**
     * Admin boolean.
     *
     * @return the boolean
     */
    boolean admin() default false;

    /**
     * Mandatory params string [ ].
     *
     * @return the string [ ]
     */
    String[] mandatoryParams() default {};

    /**
     * Scope param.
     *
     * @return the param
     */
    Param scope() default Param.NONE;

    /**
     * The enum Param.
     */
    enum Param {
        /**
         * Request param.
         */
        REQUEST, /**
         * Body param.
         */
        BODY,
        /**
         * Header param.
         */
        HEADER,
        /**
         * None param.
         */
        NONE
    }
}
