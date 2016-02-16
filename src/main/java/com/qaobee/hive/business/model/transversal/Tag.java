/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
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
package com.qaobee.hive.business.model.transversal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes a tag.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Tag {
	/**
	 * Internal identifier
	 */
	private String _id;
	/**
	 * Label
	 */
	private String label;
	/**
	 * Type
	 */
	private String type;

	/**
	 * Returns the internal identifier.
	 *
	 * @return String : ID
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Defines the internal identifier.
	 *
	 * @param _id (String) : ID
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * Returns the label.
	 *
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the label.
	 *
	 * @param label (String) : label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the type.
	 *
	 * @return String : type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Defines the type.
	 *
	 * @param type (String) : type
	 */
	public void setType(String type) {
		this.type = type;
	}

}
