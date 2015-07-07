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

import java.util.List;

/**
 * @author cke
 *
 */
public class IndicatorCfg {
	
	private String _id;
	
	private String code;
	
	private String activityId;
	
	private String countryId;
	
	private String indicatorType;
	
	private List<String> listScreen;
	
	private String description;
	
	private List<IndicatorField> listField;
	
	private List<String> listValues;

	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the indicatorType
	 */
	public String getIndicatorType() {
		return indicatorType;
	}

	/**
	 * @param indicatorType the indicatorType to set
	 */
	public void setIndicatorType(String indicatorType) {
		this.indicatorType = indicatorType;
	}

	/**
	 * @return the listScreen
	 */
	public List<String> getListScreen() {
		return listScreen;
	}

	/**
	 * @param listScreen the listScreen to set
	 */
	public void setListScreen(List<String> listScreen) {
		this.listScreen = listScreen;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the listField
	 */
	public List<IndicatorField> getListField() {
		return listField;
	}

	/**
	 * @param listField the listField to set
	 */
	public void setListField(List<IndicatorField> listField) {
		this.listField = listField;
	}

	/**
	 * @return the listValues
	 */
	public List<String> getListValues() {
		return listValues;
	}

	/**
	 * @param listValues the listValues to set
	 */
	public void setListValues(List<String> listValues) {
		this.listValues = listValues;
	}
	
	
}
