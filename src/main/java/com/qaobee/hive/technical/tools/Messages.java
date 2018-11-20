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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The Class Messages.
 */
public final class Messages {

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
    private static final Logger LOG = LoggerFactory.getLogger(Messages.class);
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Instantiates a new messages.
     */
    private Messages() {
        // empty
    }

    /**
     * Gets the string.
     *
     * @param key the key
     * @return the string
     */
    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (final MissingResourceException e) {
            LOG.warn(e.getMessage(), e);
            return '!' + key + '!';
        }
    }

    /**
     * Gets the string.
     *
     * @param key    the key
     * @param locale the locale
     * @param params the params
     * @return the string
     */
    public static String getString(final String key, final String locale, final Object... params) {
        Locale.setDefault(Locale.ENGLISH);
        ResourceBundle.clearCache();
        try {
            if (params.length > 0) {
                return MessageFormat.format(ResourceBundle.getBundle(BUNDLE_NAME, Locale.forLanguageTag(locale.split(",")[0].replaceAll("-", "_"))).getString(key), params);
            } else {
                return ResourceBundle.getBundle(BUNDLE_NAME, Locale.forLanguageTag(locale.split(",")[0].replaceAll("-", "_"))).getString(key);
            }
        } catch (final MissingResourceException e) {
            LOG.warn(e.getMessage());
            return '!' + key + '!';
        }
    }
}
