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
    Rule.Param scope() default Rule.Param.NONE;

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
