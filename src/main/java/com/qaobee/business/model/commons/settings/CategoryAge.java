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
package com.qaobee.business.model.commons.settings;

/**
 * The Class CategoryAge.
 *
 * @author cke
 */
public class CategoryAge {

	/** The code. */
	private String code;

	/** The label. */
	private String label;

	/** age max. */
	private int ageMax;

	/** age min. */
	private int ageMin;

	/** genre category. */
	private String genre;

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
	 * Gets the age max.
	 *
	 * @return the ageMax
	 */
	public int getAgeMax() {
		return ageMax;
	}

	/**
	 * Sets the age max.
	 *
	 * @param ageMax
	 *            the new age max
	 */
	public void setAgeMax(final int ageMax) {
		this.ageMax = ageMax;
	}

	/**
	 * Gets the age min.
	 *
	 * @return the age min
	 */
	public int getAgeMin() {
		return ageMin;
	}

	/**
	 * Sets the age min.
	 *
	 * @param ageMin
	 *            the new age min
	 */
	public void setAgeMin(final int ageMin) {
		this.ageMin = ageMin;
	}

	/**
	 * Gets the genre category.
	 *
	 * @return the genre category
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Sets the genre category.
	 *
	 * @param genre
	 *            the new genre category
	 */
	public void setGenre(final String genre) {
		this.genre = genre;
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
