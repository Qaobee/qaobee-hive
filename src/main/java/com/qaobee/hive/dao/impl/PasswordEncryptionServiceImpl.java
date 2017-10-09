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
package com.qaobee.hive.dao.impl;

import com.qaobee.hive.dao.PasswordEncryptionService;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 * The Class PasswordEncryptionService.
 *
 * @author xavier
 */
public final class PasswordEncryptionServiceImpl implements PasswordEncryptionService {

    @Override
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    public boolean authenticate(final String attemptedPassword, final byte[] encryptedPassword, final byte[] salt) throws QaobeeException {
        // Encrypt the clear-text password using the same salt that was used to
        // encrypt the original password
        final byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
        // Authentication succeeds if encrypted password that the user entered
        // is equal to the stored hash
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

    @Override
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    public byte[] getEncryptedPassword(final String password, final byte[] salt) throws QaobeeException {
        try {
            // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
            // specifically names SHA-1 as an acceptable hashing algorithm for
            // PBKDF2
            final String algorithm = "PBKDF2WithHmacSHA1";
            // SHA-1 generates 160 bit hashes, so that's what makes sense here
            final int derivedKeyLength = 160;
            // Pick an iteration count that works for you. The NIST recommends at
            // least 1,000 iterations:
            // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
            // iOS 4.x reportedly uses 10,000:
            // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
            final int iterations = 20000;
            final KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
            final SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
            return f.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new QaobeeException(ExceptionCodes.PASSWD_EXCEPTION, e);
        }
    }

    @Override
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    public byte[] generateSalt() throws QaobeeException {
        try {
            // VERY important to use SecureRandom instead of just Random
            final SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
            final byte[] salt = new byte[8];
            random.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException e) {
            throw new QaobeeException(ExceptionCodes.PASSWD_EXCEPTION, e);
        }
    }
}
