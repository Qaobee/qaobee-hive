/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
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
package com.qaobee.hive.business.model.commons.settings;

/**
 * The Class NiveauJeu.
 *
 * @author jeremy
 */
public class LevelGame {

	/** The id. */
	private String _id;

	/** The code. */
	private String code;

	/** The label. */
	private String label;

	/** order list. */
	private int order;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *            the new code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the new label
	 */
	public void setLabel(final String label) {
		this.label = label;
	}

	/**
	 * Gets the _id.
	 *
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Sets the _id.
	 *
	 * @param _id
	 *            the new _id
	 */
	public void set_id(final String _id) {
		this._id = _id;
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
	public final void setOrder(int order) {
		this.order = order;
	}

}
