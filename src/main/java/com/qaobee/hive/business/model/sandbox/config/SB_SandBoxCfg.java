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
package com.qaobee.hive.business.model.sandbox.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.referencial.Structure;
import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean that describes a Sandbox Configuration.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class SB_SandBoxCfg { // NOSONAR

	/**
	 * Internal identifier
	 */
	private String _id; // NOSONAR
	/**
	 * Activity
	 */
	private Activity activity;
	/**
	 * Structure
	 */
	private Structure structure;
	/**
	 * The Sand box.
	 */
	private SB_SandBox sandbox;
	/**
	 * Season
	 */
	private Season season;
	/**
	 * List of Members
	 */
	private List<Member> members;

	/**
	 * Returns the internal identifier.
	 *
	 * @return String : ID
	 */
	public String get_id() { // NOSONAR
		return _id;
	}

	/**
	 * Defines the internal identifier.
	 *
	 * @param _id (String) : ID
	 */
	public void set_id(String _id) { // NOSONAR
		this._id = _id;
	}

	/**
	 * Returns the season.
	 *
	 * @return Season : season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * Defines the season.
	 *
	 * @param season (Season) : season
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * Returns the sandbox.
	 *
	 * @return SB_SandBox : sandbox
	 */
	public SB_SandBox getSandbox() {
		return sandbox;
	}

	/**
	 * Defines the sandbox.
	 *
	 * @param sandbox (SB_SandBox) : sandbox
	 */
	public void setSandbox(SB_SandBox sandbox) {
		this.sandbox = sandbox;
	}

	/**
	 * Returns the structure.
	 *
	 * @return Structure : structure
	 */
	public Structure getStructure() {
		return structure;
	}

	/**
	 * Defines the structure.
	 *
	 * @param structure (Structure) : structure
	 */
	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	/**
	 * Returns the list of members.
	 *
	 * @return List(Member) : list
	 */
	public List<Member> getMembers() {
		return members;
	}

	/**
	 * Defines the list of members.
	 *
	 * @param members (List(Member)) : list
	 */
	public void setMembers(List<Member> members) {
		this.members = members;
	}

	/**
	 * Adds a member to the list.
	 *
	 * @param member (Member) : member
	 */
	public void addMember(Member member) {
		if (members == null) {
			members = new ArrayList<>();
		}
		members.add(member);
	}

	/**
	 * Returns the activity.
	 *
	 * @return Activity : bean that describes activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * Defines the activity.
	 *
	 * @param activity (Activity) : bean that describes the activity
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
