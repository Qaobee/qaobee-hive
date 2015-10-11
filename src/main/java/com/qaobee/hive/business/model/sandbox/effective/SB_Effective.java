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

import java.util.ArrayList;
import java.util.List;

import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.Member;
import com.qaobee.hive.business.model.transversal.Tag;

public class SB_Effective {

	/** Internal identifier. */
	private String _id;

	/** the sandbox's id */
	private String sandBoxCfgId;

	/** The age category. */
	private CategoryAge categoryAge;

	/** List of persons that composed the current group */
	private List<Member> members;
	
	/** 
	 * List labels
	 */
	private List<Tag> labels;
	
	/** 
	 * audit CRUD object 
	 */
	private Audit audit;
	

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
	
	/**
	 * Adds a member to the effective list.
	 * @param member
	 */
	public void addMember(Member member) {
		if(members==null) {
			members = new ArrayList<Member>();
		}
		members.add(member);
	}

	/**
	 * @return the sandBoxCfgId
	 */
	public String getSandBoxCfgId() {
		return sandBoxCfgId;
	}

	/**
	 * @param sandBoxCfgId the sandBoxCfgId to set
	 */
	public void setSandBoxCfgId(String sandBoxCfgId) {
		this.sandBoxCfgId = sandBoxCfgId;
	}

	/**
	 * @return the labels
	 */
	public List<Tag> getLabels() {
		return labels;
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<Tag> labels) {
		this.labels = labels;
	}

	/**
	 * @return the audit
	 */
	public Audit getAudit() {
		return audit;
	}

	/**
	 * @param audit the audit to set
	 */
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	
	
}
