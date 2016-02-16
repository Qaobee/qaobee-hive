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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes a blog post.
 * @author Xavier MARIN
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {

	/** The _id. */
	private String _id; // NOSONAR
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
	 * @return long : timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Defines the timestamp.
	 * @param timestamp (long) : timestamp
	 */
	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
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

	/**
	 * Gets the exerp.
	 * @return String : exerp
	 */
	public String getExerp() {
		return exerp;
	}

	/**
	 * Sets the exerp.
	 * @param exerp (String) :  exerp
	 */
	public void setExerp(final String exerp) {
		this.exerp = exerp;
	}

	/**
	 * Gets the content of the post.
	 * @return String : content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content of the post.
	 * @param content (String) : content
	 */
	public void setContent(final String content) {
		this.content = content;
	}

	/**
	 * Gets the _id.
	 * @return String : _id
	 */
	public String get_id() { // NOSONAR
		return _id;
	}

	/**
	 * Sets the _id.
	 * @param _id (String) : _id
	 */
	public void set_id(final String _id) { // NOSONAR
		this._id = _id;
	}

	/**
	 * Gets the user _id.
	 * @return String : user identifier
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * Sets the user _id.
	 * @param user_id (String) : user identifier
	 */
	public void setUser_id(final String user_id) {
		this.user_id = user_id;
	}

}
