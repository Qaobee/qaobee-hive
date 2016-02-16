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

/**
 * Enumeration for activity types.
 *
 * @author Jerome
 */
public enum ActivityTypeEnum {

	/**
	 * The team sport.
	 */
	TEAM_SPORT("settings.activityType.team"),
	/**
	 * The individual sport.
	 */
	INDIVIDUAL_SPORT("settings.activityType.individual"),
	/**
	 * The education.
	 */
	EDUCATION("settings.activityType.education");

	/**
	 * Label.
	 */
	private String label;

	/**
	 * Constructor.
	 *
	 * @param label (String) : label code
	 */
	ActivityTypeEnum(final String label) {
		this.label = label;
	}

	/**
	 * Gets the label.
	 *
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

}
