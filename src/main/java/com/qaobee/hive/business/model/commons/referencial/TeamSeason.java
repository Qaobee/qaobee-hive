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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Member;

/**
 * Bean that describes a team season.
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamSeason {
	
	/** Championship */
	private ChampionShip championShip;
	/** label */
	private Season season;
	/** List of staff members */
	private List<Member> staffMembers;

	/**
	 * Returns the description of the championship.
	 * @return ChampionShip : championship 
	 */
	public ChampionShip getChampionShip() {
		return championShip;
	}

	/**
	 * Defines the description of the championship.
	 * @param championShip (ChampionShip) : championship
	 */
	public void setChampionShip(ChampionShip championShip) {
		this.championShip = championShip;
	}

	/**
	 * Returns the season.
	 * @return Season : season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * Defines the season.
	 * @param season (Season) : season
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * Returns the list of staff members.
	 * @return List(Member) : list
	 */
	public List<Member> getStaffMembers() {
		return staffMembers;
	}

	/**
	 * Defines the list of staff members.
	 * @param staffMembers (List(Member)) : list
	 */
	public void setStaffMembers(List<Member> staffMembers) {
		this.staffMembers = staffMembers;
	}
	
	/**
	 * Adds a member to the staff.
	 * @param member (Member) : member
	 */
	public void addStaffMember(Member member) {
		if(staffMembers==null) {
			staffMembers = new ArrayList<>();
		}
		staffMembers.add(member);
	}

}
