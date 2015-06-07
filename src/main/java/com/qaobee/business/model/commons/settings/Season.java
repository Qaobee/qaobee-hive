/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.business.model.commons.settings;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class that defines a season.
 *
 * @author Jerome
 */
public class Season {

	/** Internal identifier. */
	private String _id;

	/** label. */
	private String label;

	/** Code. */
	private String code;

	/** Start date. */
	private Long startDate;

	/** End date. */
	private Long endDate;

	/** Internal id of an activity. */
	private String activityId;

	/** Internal id of an country. */
	private String countryId;

	/**
	 * Constructor.
	 */
	public Season() {
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
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Long getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(final Long startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(final Long endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the internal id of an activity.
	 *
	 * @return the internal id of an activity
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * Sets the internal id of an activity.
	 *
	 * @param activityId
	 *            the new internal id of an activity
	 */
	public void setActivityId(final String activityId) {
		this.activityId = activityId;
	}

	/**
	 * Gets the internal id of an country.
	 *
	 * @return the internal id of an country
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * Sets the internal id of an country.
	 *
	 * @param countryId
	 *            the new internal id of an country
	 */
	public void setCountryId(final String countryId) {
		this.countryId = countryId;
	}

	/**
	 * Gets the years season.
	 *
	 * @return the years season
	 */
	@JsonIgnore
	public String getYearsSeason() {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(startDate);
		final int startYear = cal.get(Calendar.YEAR);
		cal.setTimeInMillis(endDate);
		final int endYear = cal.get(Calendar.YEAR);
		if (startYear == endYear) {
			return String.valueOf(startYear);
		}
		return startYear + "-" + endYear;
	}

	/**
	 * @return the label
	 */
	public final String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public final void setLabel(String label) {
		this.label = label;
	}

}
