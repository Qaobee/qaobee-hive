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

import java.util.List;

/**
 * Circle define a list of friends for an user.
 * @author cke
 */
public class CircleFriend {

	/** The _id. */
	private String _id;

	/** label. */
	private String label;

	/** Create date. */
	private long datCreate;

	/** Sharing list Member. */
	private List<String> sharingListMembers;

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
	 * Gets the dat create.
	 *
	 * @return the datCreate
	 */
	public final long getDatCreate() {
		return datCreate;
	}

	/**
	 * Sets the dat create.
	 *
	 * @param datCreate
	 *            the datCreate to set
	 */
	public final void setDatCreate(final long datCreate) {
		this.datCreate = datCreate;
	}

	/**
	 * Gets the sharing list members.
	 *
	 * @return the sharingListMembers
	 */
	public final List<String> getSharingListMembers() {
		return sharingListMembers;
	}

	/**
	 * Sets the sharing list members.
	 *
	 * @param sharingListMembers
	 *            the sharingListMembers to set
	 */
	public final void setSharingListMembers(final List<String> sharingListMembers) {
		this.sharingListMembers = sharingListMembers;
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
	 *            the label to set
	 */
	public void setLabel(final String label) {
		this.label = label;
	}

}
