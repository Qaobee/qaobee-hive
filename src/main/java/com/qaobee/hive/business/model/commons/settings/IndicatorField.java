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
 * Bean tha describes a field to an indicator.
 *
 * @author cke
 * @see IndicatorCfg
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class IndicatorField {

	/**
	 * Name of the field
	 */
	private String name;
	/**
	 * Type of field
	 */
	private String type;

	/**
	 * Constructor with parameters.
	 *
	 * @param name (String) : name
	 * @param type (String) : type
	 */
	public IndicatorField(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Returns the name of the field.
	 *
	 * @return String : name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Defines the name of the field.
	 *
	 * @param name (String) : name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the type of field.
	 *
	 * @return String : type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Defines the type of field.
	 *
	 * @param type (String) : type
	 */
	public void setType(String type) {
		this.type = type;
	}

}
