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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.transversal.Gender;
import com.qaobee.hive.business.model.transversal.Role;

/**
 * Bean that describes an activity configuration.
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityCfg {

	/** Internal identifier. */
	private String _id;

	/** activity. */
	private Activity activity;

	/** Country Id. */
	private Country country;

	/** Season. */
	private Season season;

	/** List of Level Game. */
	private List<LevelGame> listLevelGame;

	/** List of Category Age. */
	private List<CategoryAge> listCategoryAge;

	/** List of gender. */
	private List<Gender> listGender;

	/** List of license type. */
	private List<LicenseType> listLicenseType;

	/** List of roles structure. */
	private List<Role> listRolePlayer;

	/**
	 * Returns the identifier.
	 * @return String : identifier
	 */
	public final String get_id() {
		return _id;
	}

	/**
	 * Defines the identifier.
	 * @param _id (String) : identifier
	 */
	public final void set_id(final String _id) {
		this._id = _id;
	}

	/**
	 * Returns the list of level game.
	 * @return List(LevelGame) : list of level game
	 */
	public final List<LevelGame> getListLevelGame() {
		return listLevelGame;
	}

	/**
	 * Defines the list of level game.
	 * @param listLevelGame (List(LevelGame)) :  list of level game
	 */
	public final void setListLevelGame(final List<LevelGame> listLevelGame) {
		this.listLevelGame = listLevelGame;
	}

	/**
	 * Returns the list of age categories.
	 * @return List(CategoryAge) : list
	 */
	public final List<CategoryAge> getListCategoryAge() {
		return listCategoryAge;
	}

	/**
	 * Returns the list of age categories.
	 * @param listCategoryAge (List(CategoryAge)) : list
	 */
	public final void setListCategoryAge(final List<CategoryAge> listCategoryAge) {
		this.listCategoryAge = listCategoryAge;
	}

	/**
	 * Returns the list of genders.
	 * @return List(Gender) : list
	 */
	public final List<Gender> getListGender() {
		return listGender;
	}

	/**
	 * Defines the list of genders.
	 * @param listGender (List(Gender)) : list
	 */
	public final void setListGender(final List<Gender> listGender) {
		this.listGender = listGender;
	}
	

	/**
	 * Returns the list of types of license.
	 * @return List(LicenseType) : list
	 */
	public final List<LicenseType> getListLicenseType() {
		return listLicenseType;
	}

	/**
	 * Defines the list of types of license.
	 * @param listLicenseType (List(LicenseType)) : list
	 */
	public final void setListLicenseType(final List<LicenseType> listLicenseType) {
		this.listLicenseType = listLicenseType;
	}

	/**
	 * Returns the list of player roles.
	 * @return List(Role) : list
	 */
	public final List<Role> getListRolePlayer() {
		return listRolePlayer;
	}

	/**
	 * Defines the list of player roles.
	 * @param listRolePlayer (List(Role)) : list
	 */
	public final void setListRolePlayer(List<Role> listRolePlayer) {
		this.listRolePlayer = listRolePlayer;
	}

	/**
	 * Returns the season.
	 * @return Season : season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * Defines the season.
	 * @param season (Season) : season
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * Returns the activity.
	 * @return Activity : activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * Defines the activity.
	 * @param activity (Activity) : activity
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * Returns the country.
	 * @return Country : country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Defines the country.
	 * @param country (Country) : country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

}
