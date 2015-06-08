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
