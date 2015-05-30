package com.qaobee.technical.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.qaobee.swarn.business.model.tranversal.person.Person;

/**
 * Created by xavier on 09/11/14.
 */
public interface PersonUtils {
	/**
	 * Prepare upsert.
	 *
	 * @param p
	 *            a person
	 * @return a prepared person for upsert
	 * @throws java.security.NoSuchAlgorithmException
	 *             password encoding problem
	 * @throws java.security.spec.InvalidKeySpecException
	 *             password encoding problem
	 */
	Person prepareUpsert(Person p) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
