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
package com.qaobee.hive.business.model.commons.users.communication.network;

/**
 * The Enum SharingScopeEnum.
 */
public enum SharingScopeEnum {

	/**
	 * Public scope.
	 */
	PUBLIC("transverse.networks.sharingscope.public"),

	/**
	 * Private scope.
	 */
	PRIVATE("transverse.networks.sharingscope.private"),

	/**
	 * Circle scope.
	 */
	CIRCLE("transverse.networks.sharingscope.circle"),

	/**
	 * Staff scope.
	 */
	STAFF("transverse.networks.sharingscope.staff"),

	/**
	 * Club scope.
	 */
	CLUB("transverse.networks.sharingscope.club");

	/**
	 * scope.
	 */
	private String scope;

	/**
	 * Constructor.
	 *
	 * @param scope (String) : scope in i18n
	 */
	SharingScopeEnum(final String scope) {
		this.scope = scope;
	}

	/**
	 * Gets the scope.
	 *
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

}
