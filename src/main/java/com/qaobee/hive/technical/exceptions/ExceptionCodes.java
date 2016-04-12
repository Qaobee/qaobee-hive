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
package com.qaobee.hive.technical.exceptions;

/**
 * The Enum ExceptionCodes.
 *
 * @author Xavier Marin
 */
public enum ExceptionCodes {

    /**
     * The http error.
     */
    HTTP_ERROR(405),
    /**
     * The non active.
     */
    NON_ACTIVE(403),
    /**
     * The passwd exception.
     */
    PASSWD_EXCEPTION(500),
    /**
     * The bad format.
     */
    BAD_FORMAT(503),
    /**
     * The mandatory field.
     */
    MANDATORY_FIELD(400),
    /**
     * The bad login.
     */
    BAD_LOGIN(401),
    /**
     * The mail exception.
     */
    MAIL_EXCEPTION(505),
    /**
     * The not logged.
     */
    NOT_LOGGED(401),
    /**
     * The not admin.
     */
    NOT_ADMIN(401),
    /**
     * internal error.
     */
    INTERNAL_ERROR(500),
    /**
     * Invalid parameter.
     */
    INVALID_PARAMETER(400),
    /**
     * The mongo error.
     */
    DATA_ERROR(500),
    /**
     * The non unique login.
     */
    NON_UNIQUE_LOGIN(400),
    /**
     * The captcha exception.
     */
    CAPTCHA_EXCEPTION(400),
    /**
     * The json exception.
     */
    JSON_EXCEPTION(500),
    /**
     * No document returned by request
     */
    DB_NO_ROW_RETURNED(500),
    /**
     * Business Error
     */
    BUSINESS_ERROR(200),
    /**
     * Trial ended exception codes.
     */
    TRIAL_ENDED(403),
    /**
     * Not paid exception codes.
     */
    NOT_PAID(403);

    /**
     * The code.
     */
    private int code;

    /**
     * Instantiates a new exception codes.
     *
     * @param code the code
     */
    ExceptionCodes(final int code) {
        this.code = code;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(final int code) {
        this.code = code;
    }

}
