package com.qaobee.technical.utils;

import com.qaobee.swarn.business.model.tranversal.person.Person;

/**
 * Created by xavier on 09/11/14.
 */
public interface HabilitUtils {
	/**
	 * Checks for habilitation.
	 *
	 * @param u
	 *            The person
	 * @param key
	 *            Habilitation key
	 * @return true si le user la possède
	 */
	boolean hasHabilitation(Person u, String key);
}
