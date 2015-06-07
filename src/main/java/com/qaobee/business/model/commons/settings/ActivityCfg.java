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

import java.util.List;

import com.qaobee.business.model.transversal.Gender;
import com.qaobee.business.model.transversal.Role;

/**
 * Activity Configuration.
 *
 * @author cke
 */
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
	 * Gets the _id.
	 *
	 * @return the _id
	 */
	public final String get_id() {
		return _id;
	}

	/**
	 * Sets the _id.
	 *
	 * @param _id
	 *            the _id to set
	 */
	public final void set_id(final String _id) {
		this._id = _id;
	}

	/**
	 * Gets the list level game.
	 *
	 * @return the listLevelGame
	 */
	public final List<LevelGame> getListLevelGame() {
		return listLevelGame;
	}

	/**
	 * Sets the list level game.
	 *
	 * @param listLevelGame
	 *            the listLevelGame to set
	 */
	public final void setListLevelGame(final List<LevelGame> listLevelGame) {
		this.listLevelGame = listLevelGame;
	}

	/**
	 * Gets the list category age.
	 *
	 * @return the listCategoryAge
	 */
	public final List<CategoryAge> getListCategoryAge() {
		return listCategoryAge;
	}

	/**
	 * Sets the list category age.
	 *
	 * @param listCategoryAge
	 *            the listCategoryAge to set
	 */
	public final void setListCategoryAge(final List<CategoryAge> listCategoryAge) {
		this.listCategoryAge = listCategoryAge;
	}

	/**
	 * Gets the list gender.
	 *
	 * @return the listGender
	 */
	public final List<Gender> getListGender() {
		return listGender;
	}

	/**
	 * Sets the list gender.
	 *
	 * @param listGender
	 *            the listGender to set
	 */
	public final void setListGender(final List<Gender> listGender) {
		this.listGender = listGender;
	}
	

	/**
	 * Gets the list license type.
	 *
	 * @return the listLicenseType
	 */
	public final List<LicenseType> getListLicenseType() {
		return listLicenseType;
	}

	/**
	 * Sets the list license type.
	 *
	 * @param listLicenseType
	 *            the listLicenseType to set
	 */
	public final void setListLicenseType(final List<LicenseType> listLicenseType) {
		this.listLicenseType = listLicenseType;
	}

	/**
	 * @return the listRolePlayer
	 */
	public final List<Role> getListRolePlayer() {
		return listRolePlayer;
	}

	/**
	 * @param listRolePlayer
	 *            the listRolePlayer to set
	 */
	public final void setListRolePlayer(List<Role> listRolePlayer) {
		this.listRolePlayer = listRolePlayer;
	}

	/**
	 * @return the season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

}
