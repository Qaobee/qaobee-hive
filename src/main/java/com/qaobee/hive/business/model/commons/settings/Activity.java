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
package com.qaobee.hive.business.model.commons.settings;

/**
 * Class that represents an Activity.
 *
 * @author jerome
 */
public class Activity {

	/** Internal identifier. */
	private String _id;

	/** Code. */
	private String code;

	/** Label. */
	private String label;

	/** Label. */
	private boolean enable;

	/** Type of activity. */
	private ActivityTypeEnum activityType;

	/**
	 * Constructor.
	 */
	public Activity() {
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
	 * Gets the type of activity.
	 *
	 * @return the type of activity
	 */
	public ActivityTypeEnum getActivityType() {
		return activityType;
	}

	/**
	 * Sets the type of activity.
	 *
	 * @param activityType
	 *            the new type of activity
	 */
	public void setActivityType(final ActivityTypeEnum activityType) {
		this.activityType = activityType;
	}

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	
}
