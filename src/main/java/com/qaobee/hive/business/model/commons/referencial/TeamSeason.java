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
package com.qaobee.hive.business.model.commons.referencial;

import java.util.List;

import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Member;

/**
 * @author cke
 *
 */
public class TeamSeason {
	
	/** Internal identifier. */
	private Championship championShip;

	/** label. */
	private Season season;
	
	/** Staff Members. */
	private List<Member> staffMembers;

	/**
	 * @return the championShip
	 */
	public Championship getChampionShip() {
		return championShip;
	}

	/**
	 * @param championShip the championShip to set
	 */
	public void setChampionShip(Championship championShip) {
		this.championShip = championShip;
	}

	/**
	 * @return the season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * @return the staffMembers
	 */
	public List<Member> getStaffMembers() {
		return staffMembers;
	}

	/**
	 * @param staffMembers the staffMembers to set
	 */
	public void setStaffMembers(List<Member> staffMembers) {
		this.staffMembers = staffMembers;
	}

}
