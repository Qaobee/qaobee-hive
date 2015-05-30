/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.technical.exceptions;

/*
 * Copyright <b>QaoBee</b>
 */

/**
 * The Enum ExceptionCodes.
 * 
 * @author Xavier Marin
 */
public enum ExceptionCodes {

	/** The http error. */
	HTTP_ERROR(403),
	/** The non active. */
	NON_ACTIVE(405),
	/** The es unavailable. */
	ES_UNAVAILABLE(501),
	/** The passwd exception. */
	PASSWD_EXCEPTION(502),
	/** The bad format. */
	BAD_FORMAT(503),
	/** The mandatory field. */
	MANDATORY_FIELD(504),
	/** The bad login. */
	BAD_LOGIN(505),
	/** The mail exception. */
	MAIL_EXCEPTION(505),
	/** The not logged. */
	NOT_LOGGED(506),
	/** The not admin. */
	NOT_ADMIN(507),
	/** internal error. */
	INTERNAL_ERROR(500),
	/** Invalid parameter. */
	INVALID_PARAMETER(508),
	/** The mongo error. */
	MONGO_ERROR(508),
	/** The non unique login. */
	NON_UNIQUE_LOGIN(509),
	/** The captcha exception. */
	CAPTCHA_EXCEPTION(510),
	/** The json exception. */
	JSON_EXCEPTION(511),
	/** No document returned by request */
	DB_NO_ROW_RETURNED(512),
	/** More than one row returned in a read one line request */
	DB_MORE_ONE_LINE_RETURNED(513),
	/** Inconsistency of retrieved values / Inconsistent return values */
	DB_INCONSISTENT_RETURN_VALUES(514);

	/** The code. */
	private int code;

	/**
	 * Instantiates a new exception codes.
	 * 
	 * @param code
	 *            the code
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
	 * @param code
	 *            the new code
	 */
	public void setCode(final int code) {
		this.code = code;
	}

}
