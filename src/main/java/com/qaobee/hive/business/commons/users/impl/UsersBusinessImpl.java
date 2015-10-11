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
package com.qaobee.hive.business.commons.users.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.qaobee.hive.business.commons.users.UsersBusiness;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.AuthCheck;

/**
 * @author jerome
 *
 */
public class UsersBusinessImpl implements UsersBusiness {
	
	@Inject
	private AuthCheck authCheck;

	/* (non-Javadoc)
	 * @see com.qaobee.hive.business.commons.users.UsersBusiness#checkUserInformations(com.qaobee.hive.business.model.commons.users.User)
	 */
	@Override
	public boolean checkUserInformations(User user, String locale) throws QaobeeException {
		if(user==null || user.getAccount()==null || user.getContact()==null) {
			throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.required", locale));
		}
		
		// Name
		if(StringUtils.isBlank(user.getName())) {
			throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.name.required", locale));
        } else if(user.getName().trim().length() < 2) {
			throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.name.short", locale));
		} else if(!VALID_NAME_REGEX.matcher(user.getName().trim()).find()) {
			throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.name.format", locale));
		}		
		
		// Firstname
		if(StringUtils.isBlank(user.getFirstname())) {
			throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.firstname.required", locale));
        } else if(user.getFirstname().trim().length() < 2) {
			throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.firstname.short", locale));
		} else if(!VALID_NAME_REGEX.matcher(user.getFirstname().trim()).find()) {
			throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.firstname.format", locale));
		}
		
		// Login
		if(StringUtils.isBlank(user.getAccount().getLogin())) {
			throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.login.required", locale));
		} else if(user.getAccount().getLogin().length() < 4) {
			throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.login.short", locale));
		}  else if(!VALID_LOGIN_REGEX.matcher(user.getAccount().getLogin().trim()).find()) {
			throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.login.format", locale));
		}
		
		// e-Mail
		authCheck.testEmail(user.getContact().getEmail(), locale);
		
		// Password
		if(StringUtils.isBlank(user.getAccount().getPasswd())) {
			throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, Messages.getString("user.password.required", locale));
		} else if(user.getAccount().getPasswd().length() < 4) {
			throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.password.short", locale));
		}  else if(!VALID_LOGIN_REGEX.matcher(user.getAccount().getPasswd().trim()).find()) {
			throw new QaobeeException(ExceptionCodes.BAD_FORMAT, Messages.getString("user.login.format", locale));
		}
        
		return true;
	}

}
