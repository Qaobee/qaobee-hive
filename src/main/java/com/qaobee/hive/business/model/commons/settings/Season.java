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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.transversal.Audit;

import java.util.Calendar;

/**
 * Class that defines a season.
 *
 * @author Jerome
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Season {

	/**
	 * Internal identifier.
	 */
	private String _id; // NOSONAR
	/**
	 * label.
	 */
	private String label;
	/**
	 * Code.
	 */
	private String code;
	/**
	 * Start date.
	 */
	private Long startDate;
	/**
	 * End date.
	 */
	private Long endDate;
	/**
	 * Internal id of an activity.
	 */
	private String activityId;
	/**
	 * Internal id of an country.
	 */
	private String countryId;
	/**
	 * audit CRUD object
	 */
	private Audit audit;

	/**
	 * Returns the internal identifier.
	 *
	 * @return String : id
	 */
	public String get_id() {// NOSONAR
		return _id;
	}

	/**
	 * Defines the internal identifier.
	 *
	 * @param _id (String) : id
	 */
	public void set_id(final String _id) {// NOSONAR
		this._id = _id;
	}

	/**
	 * Returns the season code.
	 *
	 * @return String : code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Defines the season code.
	 *
	 * @param code (String) : code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Returns the start date.
	 *
	 * @return Long : start date
	 */
	public Long getStartDate() {
		return startDate;
	}

	/**
	 * Defines the start date.
	 *
	 * @param startDate (Long) : start date
	 */
	public void setStartDate(final Long startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 *
	 * @return Long : end date
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * Defines the end date.
	 *
	 * @param endDate (Long) : end date
	 */
	public void setEndDate(final Long endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the internal id of an activity.
	 *
	 * @return String : activity ID
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * Defines the internal id of an activity.
	 *
	 * @param activityId (String) : activity ID
	 */
	public void setActivityId(final String activityId) {
		this.activityId = activityId;
	}

	/**
	 * Returns the internal id of a country.
	 *
	 * @return String : country ID
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * Defines the internal id of a country.
	 *
	 * @param countryId (String) : country ID
	 */
	public void setCountryId(final String countryId) {
		this.countryId = countryId;
	}

	/**
	 * Returns the years season.
	 *
	 * @return String : years season
	 */
	@JsonIgnore public String getYearsSeason() {
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
	 * Returns the season label.
	 *
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the season label.
	 *
	 * @param label (String) : label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the audit.
	 *
	 * @return Audit : audit
	 */
	public Audit getAudit() {
		return audit;
	}

	/**
	 * Defines the audit.
	 *
	 * @param audit (Audit) : audit
	 */
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
}
