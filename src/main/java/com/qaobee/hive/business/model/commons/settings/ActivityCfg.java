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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.transversal.CommonType;

/**
 * Bean that describes an activity configuration.
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityCfg {

	/** Internal identifier. */
	private String _id;
	/** Activity identifier */
	private String activityId;
	/** Country Id. */
	private String countryId;
	/** Start date */
	private long startDate;
	/** End date */
	private long endDate;
	/** List of position types */
	private List<CommonType> listPositionType;
	/** List of Level Game. */
	private List<LevelGame> listLevelGame;
	/** List of roles in the structure */
	private List<CommonType> listRoleStr;
	/** List of age category */
	private List<CategoryAge> listCategoryAge;
	/** List of availability status */
	private List<CommonType> listAvailabilityStatus;
	/** List of shape condition status */
	private List<CommonType> listShapeConditionStatus;
	/** List of genders */
	private List<CommonType> listGender;
	/** List of licenses types*/
	private List<CommonType> listLicenseType;
	/** List of event types */
	private List<CommonType> listEventType;
	/** Map of parameters for game 
	 * @see ParametersGame */
	private Map<String, Object> parametersGame;
	
	/**
	 * Returns the identifier.
	 * @return String : identifier
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Defines the identifier.
	 * @param _id (String) : identifier
	 */
	public void set_id(final String _id) {
		this._id = _id;
	}

	/**
	 * Returns the list of level game.
	 * @return List(LevelGame) : list of level game
	 */
	public List<LevelGame> getListLevelGame() {
		return listLevelGame;
	}

	/**
	 * Defines the list of level game.
	 * @param listLevelGame (List(LevelGame)) :  list of level game
	 */
	public void setListLevelGame(final List<LevelGame> listLevelGame) {
		this.listLevelGame = listLevelGame;
	}

	/**
	 * Returns the list of age categories.
	 * @return List(CategoryAge) : list
	 */
	public List<CategoryAge> getListCategoryAge() {
		return listCategoryAge;
	}

	/**
	 * Returns the list of age categories.
	 * @param listCategoryAge (List(CategoryAge)) : list
	 */
	public void setListCategoryAge(final List<CategoryAge> listCategoryAge) {
		this.listCategoryAge = listCategoryAge;
	}

	/**
	 * Returns the list of genders.
	 * @return List(CommonType) : list
	 */
	public List<CommonType> getListGender() {
		return listGender;
	}

	/**
	 * Defines the list of genders.
	 * @param listGender (List(CommonType)) : list
	 */
	public void setListGender(final List<CommonType> listGender) {
		this.listGender = listGender;
	}
	

	/**
	 * Returns the list of types of license.
	 * @return List(CommonType) : list
	 */
	public List<CommonType> getListLicenseType() {
		return listLicenseType;
	}

	/**
	 * Defines the list of types of license.
	 * @param listLicenseType (List(CommonType)) : list
	 */
	public void setListLicenseType(final List<CommonType> listLicenseType) {
		this.listLicenseType = listLicenseType;
	}

	/**
	 * Returns the identifier of activity.
	 * @return String : activity ID
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * Defines the identifier of activity.
	 * @param activityId (String) : activity
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * Returns the identifier of the country.
	 * @return String : country ID
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * Defines the identifier of the country.
	 * @param countryId (String) : country ID
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/**
	 * Returns the start date.
	 * @return long : start date
	 */
	public long getStartDate() {
		return startDate;
	}

	/**
	 * Defines the start date.
	 * @param startDate (long) : start date
	 */
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * @return long : end date
	 */
	public long getEndDate() {
		return endDate;
	}

	/**
	 * Defines the end date.
	 * @param endDate (long) : end date
	 */
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the list of position types
	 * @return List(CommonType) : list
	 */
	public List<CommonType> getListPositionType() {
		return listPositionType;
	}

	/**
	 * Defines the list of position types.
	 * @param listPositionType (List(CommonType)) : list
	 */
	public void setListPositionType(List<CommonType> listPositionType) {
		this.listPositionType = listPositionType;
	}

	/**
	 * Adds a position type to the list.
	 * @param type (CommonType) : type
	 */
	public void addPositionType(CommonType type) {
		if(listPositionType==null) {
			listPositionType = new ArrayList<>();
		}
		listPositionType.add(type);
	}
	
	/**
	 * Returns a list of roles for the structure.
	 * @return List(CommonType) : list
	 */
	public List<CommonType> getListRoleStr() {
		return listRoleStr;
	}

	/**
	 * Defines the list of roles in the structure.
	 * @param listRoleStr (List(CommonType)) : list
	 */
	public void setListRoleStr(List<CommonType> listRoleStr) {
		this.listRoleStr = listRoleStr;
	}

	/**
	 * Adds a role to the list.
	 * @param role (CommonType) : role
	 */
	public void addRoleStr(CommonType role) {
		if(listRoleStr==null) {
			listRoleStr = new ArrayList<>();
		}
		listRoleStr.add(role);
	}
	
	/**
	 * Returns the list of availability status.
	 * @return List(CommonType) : list
	 */
	public List<CommonType> getListAvailabilityStatus() {
		return listAvailabilityStatus;
	}

	/**
	 * Defines the list of availability status.
	 * @param listAvailabilityStatus (List(CommonType)) : list
	 */
	public void setListAvailabilityStatus(List<CommonType> listAvailabilityStatus) {
		this.listAvailabilityStatus = listAvailabilityStatus;
	}
	
	/**
	 * Adds a status to the list.
	 * @param status (CommonType) : role
	 */
	public void addAvailabilityStatus(CommonType status) {
		if(listAvailabilityStatus==null) {
			listAvailabilityStatus = new ArrayList<>();
		}
		listAvailabilityStatus.add(status);
	}

	/**
	 * Returns the list of shape condition status.
	 * @return List(CommonType) : list
	 */
	public List<CommonType> getListShapeConditionStatus() {
		return listShapeConditionStatus;
	}

	/**
	 * Defines the list of shape condition status.
	 * @param listShapeConditionStatus (List(CommonType)) : list
	 */
	public void setListShapeConditionStatus(List<CommonType> listShapeConditionStatus) {
		this.listShapeConditionStatus = listShapeConditionStatus;
	}

	/**
	 * Adds a shape condition status to the list.
	 * @param status (CommonType) : status
	 */
	public void addShapeConditionStatus(CommonType status) {
		if(listShapeConditionStatus==null) {
			listShapeConditionStatus = new ArrayList<>();
		}
		listShapeConditionStatus.add(status);
	}
	
	/**
	 * Returns the list of event types.
	 * @return List(CommonType) : list
	 */
	public List<CommonType> getListEventType() {
		return listEventType;
	}

	/**
	 * Defines the list of event types.
	 * @param listEventType (List(CommonType)) : list
	 */
	public void setListEventType(List<CommonType> listEventType) {
		this.listEventType = listEventType;
	}

	/**
	 * Adds an event type to the list.
	 * @param type (CommonType) : event type
	 */
	public void addEventType(CommonType type) {
		if(listEventType==null) {
			listEventType = new ArrayList<>();
		}
		listEventType.add(type);
	}
	
	/**
	 * Returns the map of game parameters.
	 * @return Map(String, Object) : map
	 */
	public Map<String, Object> getParametersGame() {
		return parametersGame;
	}

	/**
	 * Defines the map of game parameters.
	 * @param parametersGame (Map(String, Object)) : map
	 */
	public void setParametersGame(Map<String, Object> parametersGame) {
		this.parametersGame = parametersGame;
	}
	
	/**
	 * Adds a parameter in parameters game collection.
	 * @param key (String) : key
	 * @param value (Object) : value
	 */
	public void addParameterGame(String key, Object value) {
		if(parametersGame==null) {
			parametersGame = new HashMap<>();
		}
		parametersGame.put(key, value);
	}
	
	/**
	 * Returns the object associated to the key in game parameters.
	 * @param key (String) : key to find the parameter
	 * @return Object : value
	 * @see ParametersGame
	 */
	public Object getParameterGame(String key) {
		if(parametersGame==null) {
			return null;
		}
		return parametersGame.get(key);
	}

}
