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
 * Class that defines a country.
 *
 * @author Jerome
 */
public class Country {

	/** Internal identifier. */
	private String _id;

	/** OSCE Code of the country. */
	private int codeOSCE;

	/** Alpha country code in 2 characters. */
	private String alpha2;

	/** Alpha country code in 3 characters. */
	private String alpha3;

	/** Label code. */
	private String label;

	/**
	 * Constructor.
	 */
	public Country() {
	}

	/**
	 * Returns the internal identifier.
	 *
	 * @return String : id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Defines the internal identifier.
	 *
	 * @param _id
	 *            (String) : id
	 */
	public void set_id(final String _id) {
		this._id = _id;
	}

	/**
	 * Gets the oSCE Code of the country.
	 *
	 * @return the oSCE Code of the country
	 */
	public int getCodeOSCE() {
		return codeOSCE;
	}

	/**
	 * Sets the oSCE Code of the country.
	 *
	 * @param codeOSCE
	 *            the new oSCE Code of the country
	 */
	public void setCodeOSCE(final int codeOSCE) {
		this.codeOSCE = codeOSCE;
	}

	/**
	 * Gets the alpha country code in 2 characters.
	 *
	 * @return the alpha country code in 2 characters
	 */
	public String getAlpha2() {
		return alpha2;
	}

	/**
	 * Sets the alpha country code in 2 characters.
	 *
	 * @param alpha2
	 *            the new alpha country code in 2 characters
	 */
	public void setAlpha2(final String alpha2) {
		this.alpha2 = alpha2;
	}

	/**
	 * Gets the alpha country code in 3 characters.
	 *
	 * @return the alpha country code in 3 characters
	 */
	public String getAlpha3() {
		return alpha3;
	}

	/**
	 * Sets the alpha country code in 3 characters.
	 *
	 * @param alpha3
	 *            the new alpha country code in 3 characters
	 */
	public void setAlpha3(final String alpha3) {
		this.alpha3 = alpha3;
	}

	/**
	 * Gets the label code.
	 *
	 * @return the label code
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label code.
	 *
	 * @param label
	 *            the new label code
	 */
	public void setLabel(final String label) {
		this.label = label;
	}

}
