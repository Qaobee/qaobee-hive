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

/**
 * The Class BlogPost.
 *
 * @author Xavier MARIN
 */
public class BlogPost {

	/** The _id. */
	private String _id;

	/** The timestamp. */
	private long timestamp;

	/** The title. */
	private String title;

	/** The exerp. */
	private String exerp;

	/** The content. */
	private String content;

	/** The user_id. */
	private String user_id;

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

	/**
	 * Gets the exerp.
	 *
	 * @return the exerp
	 */
	public String getExerp() {
		return exerp;
	}

	/**
	 * Sets the exerp.
	 *
	 * @param exerp
	 *            the new exerp
	 */
	public void setExerp(final String exerp) {
		this.exerp = exerp;
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
	 * Gets the user_id.
	 *
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * Sets the user_id.
	 *
	 * @param user_id
	 *            the new user_id
	 */
	public void setUser_id(final String user_id) {
		this.user_id = user_id;
	}

}
