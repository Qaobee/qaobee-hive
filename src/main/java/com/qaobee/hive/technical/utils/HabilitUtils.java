package com.qaobee.hive.technical.utils;

import com.qaobee.hive.business.model.commons.users.User;

/**
 * The interface Habilit utils.
 */
public interface HabilitUtils {
	/**
	 * Checks for habilitation.
	 *
	 * @param u   The person
	 * @param key Habilitation key
	 * @return true si le user la possède
	 */
	boolean hasHabilitation(User u, String key);
}
