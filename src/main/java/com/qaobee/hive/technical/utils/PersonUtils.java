package com.qaobee.hive.technical.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.qaobee.hive.business.model.commons.users.User;

/**
 * Created by xavier on 09/11/14.
 */
public interface PersonUtils {
	/**
	 * Prepare upsert.
	 *
	 * @param u
	 *            a user
	 * @return a prepared person for upsert
	 * @throws java.security.NoSuchAlgorithmException
	 *             password encoding problem
	 * @throws java.security.spec.InvalidKeySpecException
	 *             password encoding problem
	 */
	User prepareUpsert(User u) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
