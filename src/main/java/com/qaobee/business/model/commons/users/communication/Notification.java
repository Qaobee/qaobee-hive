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
package com.qaobee.business.model.commons.users.communication;

import java.io.Serializable;

/**
 * The Class Notification.
 *
 * @author xavier
 */
public class Notification implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5693811404344145826L;

	/** The _id. */
	private String _id;

	/** The timestamp. */
	private long timestamp;

	/** The content. */
	private String content;

	/** The from_user_id. */
	private String from_user_id;

	/** The title. */
	private String title;

	/** The read. */
	private boolean read;

	/**
	 * Gets the _id.
	 *
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Sets the _id.
	 *
	 * @param _id
	 *            the _id to set
	 */
	public void set_id(final String _id) {
		this._id = _id;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp
	 *            the new timestamp
	 */
	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content
	 *            the new content
	 */
	public void setContent(final String content) {
		this.content = content;
	}

	/**
	 * Gets the from_user_id.
	 *
	 * @return the from_user_id
	 */
	public String getFrom_user_id() {
		return from_user_id;
	}

	/**
	 * Sets the from_user_id.
	 *
	 * @param from_user_id
	 *            the new from_user_id
	 */
	public void setFrom_user_id(final String from_user_id) {
		this.from_user_id = from_user_id;
	}

	/**
	 * Checks if is the read.
	 *
	 * @return the read
	 */
	public boolean isRead() {
		return read;
	}

	/**
	 * Sets the read.
	 *
	 * @param read
	 *            the new read
	 */
	public void setRead(final boolean read) {
		this.read = read;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
}
