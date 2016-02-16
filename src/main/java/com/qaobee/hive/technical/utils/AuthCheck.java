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

package com.qaobee.hive.technical.utils;

import com.qaobee.hive.technical.exceptions.QaobeeException;

import java.util.regex.Pattern;

/**
 * The interface Auth check.
 */
public interface AuthCheck {
    /**
     * The Constant VALID_EMAIL_ADDRESS_REGEX.
     */
    Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * Permet de vérifier une adresse e-mail.
     *
     * @param email  the email
     * @param locale the locale
     * @return valid or not
     * @throws QaobeeException the qaobee exception
     */
    boolean testEmail(String email, String locale) throws QaobeeException;
}
