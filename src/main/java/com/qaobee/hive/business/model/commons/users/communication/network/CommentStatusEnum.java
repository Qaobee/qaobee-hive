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
 * The Enum CommentStatusEnum.
 */
public enum CommentStatusEnum {

	/**
	 * Public scope.
	 */
	PUBLIC("transverse.networks.comment.status.public"),

	/**
	 * Blocked scope.
	 */
	BOCKED("transverse.networks.comment.status.blocked"),

	/**
	 * Deleted status.
	 */
	DELETED("transverse.networks.comment.status.deleted");

	/**
	 * status.
	 */
	private String status;

	/**
	 * Constructor.
	 *
	 * @param status (String) : status in i18n
	 */
	CommentStatusEnum(final String status) {
		this.status = status;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

}
