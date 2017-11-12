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

package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;

/**
 * The interface Password encryption service.
 */
public interface EncryptionService {

    /**
     * Authenticate boolean.
     *
     * @param attemptedMessage the attempted password
     * @param encryptedMessage the encrypted password
     * @param salt              the salt
     * @return the boolean
     * @throws QaobeeException the qaobee exception
     */
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    boolean authenticate(String attemptedMessage, byte[] encryptedMessage, byte[] salt) throws QaobeeException;

    /**
     * Gets the encrypted.
     *
     * @param message the message
     * @param salt     the salt
     * @return the encrypted message
     * @throws QaobeeException the qaobee exception
     */
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    byte[] getEncrypted(String message, byte[] salt) throws QaobeeException;

    /**
     * Generate salt.
     *
     * @return the byte[]
     * @throws QaobeeException the qaobee exception
     */
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    byte[] generateSalt() throws QaobeeException;
}
