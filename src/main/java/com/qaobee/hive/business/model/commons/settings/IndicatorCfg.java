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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes the indicator config.
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndicatorCfg {
	
	/** Internal identifier */
	private String _id;
	/** Code */
	private String code;
	/** Activity identifier */
	private String activityId;
	/** Country identifier */
	private String countryId;
	/** Type of indicator */
	private String indicatorType;
	/** List of screens */
	private List<String> listScreen;
	/** Description */
	private String description;
	/** List of fields */
	private List<IndicatorField> listField;
	/** List of values */
	private List<String> listValues;

	/**
	 * Returns the internal identifier.
	 * @return String : id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Defines the internal identifier.
	 * @param _id (String) : id
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * Returns the code.
	 * @return String : code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Defines the code.
	 * @param code (String) : code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Returns the activity ID.
	 * @return String : activity ID
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * Defines the activity ID.
	 * @param activityId (String) : activity ID
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * Returns the country ID.
	 * @return String : country ID
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * Defines the country ID.
	 * @param countryId (String) : country ID
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/**
	 * Returns the type of indicator.
	 * @return String : type
	 */
	public String getIndicatorType() {
		return indicatorType;
	}

	/**
	 * Defines the type of indicator.
	 * @param indicatorType (String) : type
	 */
	public void setIndicatorType(String indicatorType) {
		this.indicatorType = indicatorType;
	}

	/**
	 * Returns the list of screens.
	 * @return List(String) : list
	 */
	public List<String> getListScreen() {
		return listScreen;
	}

	/**
	 * Defines the list of screens.
	 * @param listScreen (List(Screen)) : list
	 */
	public void setListScreen(List<String> listScreen) {
		this.listScreen = listScreen;
	}
	
	/**
	 * Adds a screen to the list.
	 * @param screen (String) : screen name
	 */
	public void addScreen(String screen) {
		if(listScreen==null) {
			listScreen = new ArrayList<>();
		}
		listScreen.add(screen);
	}

	/**
	 * Returns the indicator description.
	 * @return String : description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Defines the indicator description.
	 * @param description (String) : description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the list of fields.
	 * @return List(IndicatorField) : list
	 */
	public List<IndicatorField> getListField() {
		return listField;
	}

	/**
	 * Defines the list of fields.
	 * @param listField (List(IndicatorField)) : list
	 */
	public void setListField(List<IndicatorField> listField) {
		this.listField = listField;
	}
	
	/**
	 * Adds a field to the list of fields.
	 * @param field (IndicatorField) : field 
	 */
	public void addField(IndicatorField field) {
		if(listField==null) {
			listField = new ArrayList<>();
		}
		listField.add(field);
	}

	/**
	 * Returns the list of values.
	 * @return List(String) : list
	 */
	public List<String> getListValues() {
		return listValues;
	}

	/**
	 * Defines the list of values.
	 * @param listValues (List(String)) : list
	 */
	public void setListValues(List<String> listValues) {
		this.listValues = listValues;
	}
	
	/**
	 * Adds a value to the list.
	 * @param value (String) : value
	 */
	public void addValue(String value) {
		if(listValues==null) {
			listValues = new ArrayList<>();
		}
		listValues.add(value);
	}
}
