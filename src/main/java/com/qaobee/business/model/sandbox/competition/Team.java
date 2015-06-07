/*************************************************************************
 *
 * Qaobee
 * __________________
 *
 * [2014] Qaobee
 * All Rights Reserved.
 *
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.business.model.sandbox.competition;

import com.qaobee.business.model.commons.settings.CategoryAge;

/**
 * The Class Team.
 *
 * @author Nada Vujanic-Maquin
 * @version V1.2
 */

public class Team {

	// Declaration of variables

	/** Internal identifier. */
	private String _id;

	/** The label. */
	private String label;

	/** The age category. */
	private CategoryAge categoryAge;
	
	/** the sandbox's id */
	private String sandBoxIdCfg;

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

}
