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

package com.qaobee.hive.technical.tools;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * The interface Password encryption service.
 */
public interface PasswordEncryptionService {
    /**
     * Authenticate.
     *
     * @param attemptedPassword the attempted password
     * @param encryptedPassword the encrypted password
     * @param salt              the salt
     * @return true, if successful
     * @throws InvalidKeySpecException  the invalid key spec exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException;

    /**
     * Gets the encrypted password.
     *
     * @param password the password
     * @param salt     the salt
     * @return the encrypted password
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     */
    byte[] getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException;

    /**
     * Generate salt.
     *
     * @return the byte[]
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    byte[] generateSalt() throws NoSuchAlgorithmException;
}
