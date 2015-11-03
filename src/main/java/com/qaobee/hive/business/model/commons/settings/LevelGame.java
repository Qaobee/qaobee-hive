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
 * Bean that describes the level game.
 * @author jeremy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LevelGame {

	/** Internal identifier */
	private String _id;
	/** Code */
	private String code;
	/** Label */
	private String label;
	/** Order list */
	private int order;

	/**
	 * Returns the level game code.
	 * @return String : code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Defines the level game code.
	 * @param code (String) : code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Returns the level game label.
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the level game label.
	 * @param label (String) : label
	 */
	public void setLabel(final String label) {
		this.label = label;
	}

	/**
	 * Returns the identifier.
	 * @return String : id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Defines the identifier.
	 * @param _id (String) : id
	 */
	public void set_id(final String _id) {
		this._id = _id;
	}

	/**
	 * Returns the order to show levels game.
	 * @return int : order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Defines the order to show levels game.
	 * @param order (int) : order
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
