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
package com.qaobee.hive.business.model.transversal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes a gender.
 * @author xavier
 * @see CommonType
 */
@Deprecated
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gender {

	/** Gender code. */
	private String code;

	/** Gender label. */
	private String label;

	/** Gender order view. */
	private int order;

	/**
	 * Returns the gender code.
	 * @return String : code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Defines the gender code.
	 * @param code (String) : code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Returns the label.
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the label.
	 * @param label (String) : label
	 */
	public final void setLabel(final String label) {
		this.label = label;
	}

	/**
	 * Returns the position order to show genders.
	 * @return int : order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Defines the position order to show genders.
	 * @param order (int) : order
	 */
	public void setOrder(final int order) {
		this.order = order;
	}

}
