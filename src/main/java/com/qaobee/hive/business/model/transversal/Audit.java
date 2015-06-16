/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
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
package com.qaobee.hive.business.model.transversal;

import com.qaobee.hive.business.model.commons.users.User;

/**
 * @author cke
 *
 */
public class Audit {
	
	/** Author. */
	private User author;

	/** Create date. */
	private long datCreate;

	/** Update date. */
	private long datUpdate;

	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * @return the datCreate
	 */
	public long getDatCreate() {
		return datCreate;
	}

	/**
	 * @param datCreate the datCreate to set
	 */
	public void setDatCreate(long datCreate) {
		this.datCreate = datCreate;
	}

	/**
	 * @return the datUpdate
	 */
	public long getDatUpdate() {
		return datUpdate;
	}

	/**
	 * @param datUpdate the datUpdate to set
	 */
	public void setDatUpdate(long datUpdate) {
		this.datUpdate = datUpdate;
	}

}
