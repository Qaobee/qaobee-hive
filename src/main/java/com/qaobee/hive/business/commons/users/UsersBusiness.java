/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.business.commons.users;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.QaobeeException;

import java.util.regex.Pattern;

/**
 * The interface Users business.
 *
 * @author jerome
 */
public interface UsersBusiness {

	/**
	 * The constant VALID_NAME_REGEX.
	 */
	Pattern VALID_NAME_REGEX = Pattern.compile("^([a-z'àâéèêôùûç\\ \\-]+)$", Pattern.CASE_INSENSITIVE);
	/**
	 * The constant VALID_LOGIN_REGEX.
	 */
	Pattern VALID_LOGIN_REGEX = Pattern.compile("^([a-z0-9\\.\\-]+)$", Pattern.CASE_INSENSITIVE);

	/**
	 * Check user informations boolean.
	 *
	 * @param user   the user
	 * @param locale the locale
	 * @return the boolean
	 * @throws QaobeeException the qaobee exception
	 */
	boolean checkUserInformations(User user, String locale) throws QaobeeException;

	
}
