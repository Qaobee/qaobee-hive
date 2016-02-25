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

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.technical.tools.PasswordEncryptionService;
import com.qaobee.hive.technical.utils.PersonUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

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
     * @param user a user
     * @return a prepared person for upsert
     * @throws NoSuchAlgorithmException password encoding problem
     * @throws InvalidKeySpecException  password encoding problem
     */
    @Override
    public User prepareUpsert(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        user.getAccount().setLogin(user.getAccount().getLogin().toLowerCase());
        if (StringUtils.isNotBlank(user.getAccount().getPasswd())) {
            final byte[] salt = passwordEncryptionService.generateSalt();
            user.getAccount().setSalt(salt);
            user.getAccount().setPassword(passwordEncryptionService.getEncryptedPassword(user.getAccount().getPasswd(), salt));
            user.getAccount().setPasswd(null);
        }
        if (StringUtils.isBlank(user.getAccount().getActivationCode())) {
            final String activationcode = UUID.randomUUID().toString().replaceAll("-", "");
            user.getAccount().setActivationCode(activationcode);
            user.getAccount().setActive(false);
            user.getAccount().setFirstConnexion(true);
            user.setTimestamp(System.currentTimeMillis());
            user.set_id(null);
        }
        return user;
    }
}
