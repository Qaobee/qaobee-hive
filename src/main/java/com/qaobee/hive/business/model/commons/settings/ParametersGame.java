/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
 * All Rights Reserved.
 * 
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
package com.qaobee.hive.business.model.commons.settings;

/**
 * Interface for game parameters.
 * @author jerome
 */
public interface ParametersGame {

	String NB_MAX_PLAYERS 		= "nbMaxPlayers";
	String NB_MIN_PLAYERS 		= "nbMinPlayers";
	String NB_PERIODS			= "nbPeriods";
	String PERIODE_DURATION		= "periodDuration";
	String NB_TIMEOUT			= "nbTimeout";
	String TIMEOUT_DURATION		= "timeoutDuration";
	String YELLOW_CARDS_MAX		= "yellowCardsMax";
	String EXCLUSION_TEMPO		= "exclusionTempo";
	String HALF_TIME_DURATION	= "halfTimeDuration";
}
