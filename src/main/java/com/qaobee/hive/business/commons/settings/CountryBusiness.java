/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
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
package com.qaobee.hive.business.commons.settings;

import com.qaobee.hive.business.model.commons.settings.Country;

/**
 * The interface Country business.
 *
 * @author jerome
 */
public interface CountryBusiness {

    /**
     * Gets country from alpha 2.
     *
     * @param alpha2 the alpha 2
     * @return the country from alpha 2
     */
    Country getCountryFromAlpha2(String alpha2);
}
