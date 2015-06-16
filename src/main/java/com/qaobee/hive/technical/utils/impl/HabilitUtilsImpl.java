/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2014] Qaobee
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
package com.qaobee.hive.technical.utils.impl;


import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.transversal.Habilitation;
import com.qaobee.hive.technical.utils.HabilitUtils;

/**
 * The Class HabilitUtils.
 *
 * @author Xavier MARIN
 */
public final class HabilitUtilsImpl implements HabilitUtils {


    /**
     * Checks for habilitation.
     *
     * @param u   The person
     * @param key Habilitation key
     * @return true si le user la possède
     */
    @Override
    public boolean hasHabilitation(final User u, final String key) {
        if (u.getAccount().getHabilitations() != null) {
            for (final Habilitation h : u.getAccount().getHabilitations()) {
                if (key.equals(h.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }
}
