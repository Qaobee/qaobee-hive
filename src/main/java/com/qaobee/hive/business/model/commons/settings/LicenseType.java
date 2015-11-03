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
package com.qaobee.hive.business.model.commons.settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that descripbes the type of license.
 * @author jeremy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseType {

	/** Code */
	private String code;
	/** Label */
	private String label;

	/**
	 * Returns the license type code.
	 * @return String : code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Defines the license type code.
	 * @param code (String) : code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Returns the license type label.
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the license type label.
	 * @param label (String) : label
	 */
	public void setLabel(final String label) {
		this.label = label;
	}
}
