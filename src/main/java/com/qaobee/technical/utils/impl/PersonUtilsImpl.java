/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
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
package com.qaobee.technical.utils.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.qaobee.technical.tools.PasswordEncryptionService;
import com.qaobee.technical.utils.PersonUtils;

/**
 * The Class PersonUtils.
 *
 * @author xavier
 */
public final class PersonUtilsImpl implements PersonUtils {
	@Inject
	private PasswordEncryptionService passwordEncryptionService;

	/**
	 * Prepare upsert.
	 *
	 * @param p
	 *            a person
	 * @return a prepared person for upsert
	 * @throws NoSuchAlgorithmException
	 *             password encoding problem
	 * @throws InvalidKeySpecException
	 *             password encoding problem
	 */
	@Override
	public Person prepareUpsert(final Person p) throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (StringUtils.isNotBlank(p.getAccount().getPasswd())) {
			final byte[] salt = passwordEncryptionService.generateSalt();
			p.getAccount().setSalt(salt);
			p.getAccount().setPassword(passwordEncryptionService.getEncryptedPassword(p.getAccount().getPasswd(), salt));
			p.getAccount().setPasswd(null);
		}
		if (StringUtils.isBlank(p.getAccount().getActivationCode())) {
			final String activationcode = UUID.randomUUID().toString().replaceAll("-", "");
			p.getAccount().setActivationCode(activationcode);
			p.getAccount().setActive(false);
			p.getAccount().setFirstConnexion(true);
			p.setTimestamp(System.currentTimeMillis());
			p.set_id(null);
		}
		return p;
	}
}
