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
import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.Tag;

/**
 * The Class Team.
 *
 * @author Nada Vujanic-Maquin
 * @version V1.2
 */

public class SB_Team {

	// Declaration of variables

	/** Internal identifier. */
	private String _id;
	/** The label. */
	private String label;
	/** The age category. */
	private CategoryAge categoryAge;
	/** the sandbox's id */
	private String sandboxId;
	/** Effective ID */
	private String effectiveId;
	/** Enable ? */
	private boolean enable;
	/** Adversary ? */
	private boolean adversary;
	
	/** 
	 * List labels
	 */
	private List<Tag> labels;
	
	/** 
	 * audit CRUD object 
	 */
	private Audit audit;

	// Getters and Setters

	/**
	 * Returns the internal identifier of the team.
	 * 
	 * @return String : internal identifier
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Defines the internal identifier of the team.
	 * 
	 * @param _id
	 *            (String) : identifier
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * Returns the label of the team.
	 *
	 * @return String : label
	 */
	public final String getLabel() {
		return label;
	}

	/**
	 * Defines the label of the team.
	 *
	 * @param label
	 *            (String) : label
	 */
	public final void setLabel(final String label) {
		this.label = label;
	}


	/**
	 * Returns the age category associated to the team.
	 * 
	 * @return CategoryAge : categoryAge
	 */
	public final CategoryAge getCategoryAge() {
		return categoryAge;
	}

	/**
	 * Defines the age category associated to the team.
	 * 
	 * @param categoryAge
	 *            (CategoryAge) : categoryAge
	 */
	public final void setCategoryAge(CategoryAge categoryAge) {
		this.categoryAge = categoryAge;
	}

	/**
	 * @return the sandboxId
	 */
	public String getSandboxId() {
		return sandboxId;
	}

	/**
	 * @param sandboxId the sandboxId to set
	 */
	public void setSandboxId(String sandboxId) {
		this.sandboxId = sandboxId;
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

	public String getEffectiveId() {
		return effectiveId;
	}

	public void setEffectiveId(String effectiveId) {
		this.effectiveId = effectiveId;
	}

	/**
	 * Is the team enable ?
	 * @return boolean : true if team enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * Defines if the team is enable.
	 * @param enable (boolean) : true if team enable
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * Is the team an adversary ?
	 * @return boolean : true if adversary
	 */
	public boolean isAdversary() {
		return adversary;
	}

	/**
	 * Defines if the team is an adversary.
	 * @param adversary (boolean) : true if adversary team
	 */
	public void setAdversary(boolean adversary) {
		this.adversary = adversary;
	}

}
