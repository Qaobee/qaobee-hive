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
 * Bean that describes the age category.
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryAge {

	/** Category code */
	private String code;

	/** Category label. */
	private String label;

	/** Age max */
	private int ageMax;

	/** Age min */
	private int ageMin;

	/** Genre */
	private String genre;

	/** Order list */
	private int order;

	/**
	 * Returns the age category code.
	 * @return String : code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Defines the age category code.
	 * @param code (String) : code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Returns the age category label.
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the age category label.
	 * @param label (String) : label
	 */
	public void setLabel(final String label) {
		this.label = label;
	}

	/**
	 * Returns the age max.
	 * @return int : age max
	 */
	public int getAgeMax() {
		return ageMax;
	}

	/**
	 * Defines the age max.
	 * @param ageMax (int) : age max
	 */
	public void setAgeMax(final int ageMax) {
		this.ageMax = ageMax;
	}

	/**
	 * Returns the age min.
	 * @return int : age min
	 */
	public int getAgeMin() {
		return ageMin;
	}

	/**
	 * Defines the age min.
	 * @param ageMin (int) : age min
	 */
	public void setAgeMin(final int ageMin) {
		this.ageMin = ageMin;
	}

	/**
	 * Returns the genre category.
	 * @return String : genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Defines the genre category.
	 * @param genre (String) : genre
	 */
	public void setGenre(final String genre) {
		this.genre = genre;
	}

	/**
	 * Returns the order in category list.
	 * @return int : order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Defines the order in category list.
	 * @param order (int) : order
	 */
	public void setOrder(final int order) {
		this.order = order;
	}

}
