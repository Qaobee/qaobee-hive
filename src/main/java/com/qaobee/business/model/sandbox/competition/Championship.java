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

/**
 * The Class Championship.
 *
 * @author Nada Vujanic-Maquin
 * @version V1.0
 */

public class Championship {

	// Declaration des variables

	/** Internal identifier. */
	private String _id;

	/** The label. */
	private String label;

	// Getters and Setters

	/**
	 * Returns the internal identifier of the championship.
	 * 
	 * @return String : internal identifier
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Defines the internal identifier of the championship.
	 * 
	 * @param _id
	 *            (String) : identifier
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * Returns the label of the championship.
	 *
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the label of the championship.
	 *
	 * @param label
	 *            (String) : label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
