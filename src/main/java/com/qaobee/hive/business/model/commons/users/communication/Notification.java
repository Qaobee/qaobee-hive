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
package com.qaobee.hive.business.model.commons.users.communication;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes a notification push.
 *
 * @author xavier
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5693811404344145826L;
	/** Internal identifier */
	private String _id; // NOSONAR
	/** Timestamp. */
	private long timestamp;
	/** Content. */
	private String content;
	/** From user _id. */
	private String from_user_id;
	/** Title. */
	private String title;
	/** Is read ? */
	private boolean read;

	/**
	 * Gets the internal identifier.
	 * @return String : _id
	 */
	public String get_id() { // NOSONAR
		return _id;
	}

	/**
	 * Sets the internal identifier.
	 * @param _id (String) : _id
	 */
	public void set_id(final String _id) { // NOSONAR
		this._id = _id;
	}

	/**
	 * Gets the timestamp.
	 * @return long : timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 * @param timestamp (long) : timestamp
	 */
	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the content.
	 * @return String : content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 * @param content (String) : content
	 */
	public void setContent(final String content) {
		this.content = content;
	}

	/**
	 * Gets the "from" user _id.
	 * @return String : from user _id
	 */
	public String getFrom_user_id() {
		return from_user_id;
	}

	/**
	 * Sets the "from" user _id.
	 * @param from_user_id (String) : from user _id
	 */
	public void setFrom_user_id(final String from_user_id) {
		this.from_user_id = from_user_id;
	}

	/**
	 * Checks if notification is read.
	 * @return boolean : true if read
	 */
	public boolean isRead() {
		return read;
	}

	/**
	 * Sets if notification is read.
	 * @param read (boolean) : true if read
	 */
	public void setRead(final boolean read) {
		this.read = read;
	}

	/**
	 * Gets the title.
	 * @return String : title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * @param title (String) : title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
}
