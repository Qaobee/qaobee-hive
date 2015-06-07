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
package com.qaobee.business.model.sandbox.effective;

import java.util.List;

import com.qaobee.business.model.commons.settings.LevelGame;
import com.qaobee.business.model.transversal.Member;

/**
 * This class describes a Group.
 * 
 * @author Jerome
 */
public class Group {

	/** Internal identifier */
	private String _id;

	/** Label */
	private String label;
	
	/** Level game */
	private LevelGame levelGame;
	
	/** List of persons that composed the current group */
	private List<Member> members;

	/**
	 * Returns the internal identifier.
	 * 
	 * @return String : internal identifier
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Defines the internal identifier.
	 * 
	 * @param _id
	 *            (String) : identifier
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * Returns the label of the group.
	 * 
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the label of the group.
	 * 
	 * @param label
	 *            (String) : label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	

	/**
	 * Returns the level game associated to the group.
	 * 
	 * @return LevelGame : level game
	 */
	public LevelGame getLevelGame() {
		return levelGame;
	}

	/**
	 * Defines the level game associated to the group.
	 * 
	 * @param levelGame
	 *            (LevelGame) : level game
	 */
	public void setLevelGame(LevelGame levelGame) {
		this.levelGame = levelGame;
	}

	/**
	 * @return the members
	 */
	public List<Member> getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	

}
