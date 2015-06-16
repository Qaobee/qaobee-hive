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

package com.qaobee.hive.business.model.sandbox.effective;

import java.util.List;

import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.transversal.Member;

public class Effective {

	/** Internal identifier. */
	private String _id;

	/** the sandbox's id */
	private String sandBoxIdCfg;

	/** The age category. */
	private CategoryAge categoryAge;

	/** List of persons that composed the current group */
	private List<Member> members;
	

	/**
	 * @return the _id
	 */
	public final String get_id() {
		return _id;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public final void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return the sandBoxIdCfg
	 */
	public String getSandBoxIdCfg() {
		return sandBoxIdCfg;
	}

	/**
	 * @param sandBoxIdCfg the sandBoxIdCfg to set
	 */
	public void setSandBoxIdCfg(String sandBoxIdCfg) {
		this.sandBoxIdCfg = sandBoxIdCfg;
	}

	/**
	 * @return the categoryAge
	 */
	public CategoryAge getCategoryAge() {
		return categoryAge;
	}

	/**
	 * @param categoryAge the categoryAge to set
	 */
	public void setCategoryAge(CategoryAge categoryAge) {
		this.categoryAge = categoryAge;
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
