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

/**
 * The Class Gender.
 *
 * @author xavier
 */
public class Gender {

	/** Gender code. */
	private String code;

	/** Gender label. */
	private String label;

	/** Gender order view. */
	private int order;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public final String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *            the code to set
	 */
	public final void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public final String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the label to set
	 */
	public final void setLabel(final String label) {
		this.label = label;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public final int getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order
	 *            the order to set
	 */
	public final void setOrder(final int order) {
		this.order = order;
	}

}
