package com.qaobee.hive.technical.tools;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by xavier on 09/11/14.
 */
public interface PasswordEncryptionService {
	/**
	 * Authenticate.
	 *
	 * @param attemptedPassword
	 *            the attempted password
	 * @param encryptedPassword
	 *            the encrypted password
	 * @param salt
	 *            the salt
	 * @return true, if successful
	 * @throws java.security.NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws java.security.spec.InvalidKeySpecException
	 *             the invalid key spec exception
	 */
	boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException;

	/**
	 * Gets the encrypted password.
	 *
	 * @param password
	 *            the password
	 * @param salt
	 *            the salt
	 * @return the encrypted password
	 * @throws java.security.NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws java.security.spec.InvalidKeySpecException
	 *             the invalid key spec exception
	 */
	byte[] getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException;

	/**
	 * Generate salt.
	 *
	 * @return the byte[]
	 * @throws java.security.NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	byte[] generateSalt() throws NoSuchAlgorithmException;
}
