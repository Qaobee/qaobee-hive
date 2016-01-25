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
package com.qaobee.hive.business.model.sandbox.effective;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes availability.
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Availability {
	
	/**
	 * Value of status.<br>
	 * <ul>
	 * <li>available</li>
	 * <li>unavailable</li>
	 * </ul>
	 * */
	private String value;

	/**
	 * Detail value. <br>
	 * <ul>
	 * <li>0=available</li>
	 * <li>1=injured</li>
	 * <li>2=suspended</li>
	 * <li>3=uncertain</li>
	 * </ul>
	 * */
	private String cause;

	/** End date of status */
	private Long endDate;

	/**
	 * Constructor without parameter.
	 */
	public Availability() {
	}
	
	/**
	 * Constructor.
	 * @param value (String) : availability value
	 * @param cause (String) : availability cause
	 */
	public Availability(String value, String cause) {
		this.value = value;
		this.cause = cause;
	}
	
	/**
	 * Returns the value.<br>
	 * <ul>
	 * <li>available</li>
	 * <li>unavailable</li>
	 * </ul>
	 * @return String : value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Defines the value.<br>
	 * <ul>
	 * <li>available</li>
	 * <li>unavailable</li>
	 * </ul>
	 * @param value (String) : value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Returns the cause.<br>
	 * <ul>
	 * <li>0=available</li>
	 * <li>1=injured</li>
	 * <li>2=suspended</li>
	 * <li>3=uncertain</li>
	 * </ul>
	 * @return String : cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * Defines the cause.<br>
	 * <ul>
	 * <li>0=available</li>
	 * <li>1=injured</li>
	 * <li>2=suspended</li>
	 * <li>3=uncertain</li>
	 * </ul>
	 * @param cause (String) : cause
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * Returns the end date.
	 * @return Long : date
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * Defines the end date.
	 * @param endDate (Long) : date
	 */
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

}
