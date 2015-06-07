/*************************************************************************
 * 
 * Qaobee
 * 
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
package com.qaobee.business.model.sandbox.training;

import java.util.List;

/**
 * The Class Theme.
 * 
 * @author Mohamed EL MARZGIOUI
 *
 */
public class Theme {

	/** the _id. */
	private String _id;

	/** label */
	private String label;

	/** description. */
	private String description;

	/** subthemes's list. */
	private List<SubTheme> subThemeList;

	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the subThemeList
	 */
	public List<SubTheme> getSubThemeList() {
		return subThemeList;
	}

	/**
	 * @param subThemeList
	 *            the subThemeList to set
	 */
	public void setSubThemeList(List<SubTheme> subThemeList) {
		this.subThemeList = subThemeList;
	}

}
