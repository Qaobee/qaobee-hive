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
package com.qaobee.hive.technical.utils.impl;

import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.AuthCheck;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;

/**
 * The Class AuthDAO.
 *
 * @author xavier
 */
public final class AuthCheckImpl implements AuthCheck {

	/**
	 * Validate.
	 *
	 * @param emailStr the email str
	 * @return true, if successful
	 */
	private static boolean validate(final String emailStr) {
		final Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	@Override public boolean testEmail(final String email, final String locale) throws QaobeeException {
		if (!validate(email.replaceAll("\\[at\\]", "@"))) {
			throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("email.bad.format", locale));
		}
		if (StringUtils.isBlank(email)) {
			throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("email.required", locale));
		}
		return true;
	}

}
