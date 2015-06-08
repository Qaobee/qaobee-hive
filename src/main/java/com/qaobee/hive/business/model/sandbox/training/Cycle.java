/*************************************************************************
 * 
 * Qaobee
 * 
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.business.model.sandbox.training;

import java.util.List;

/**
 * The Class Cycle.
 */
public class Cycle extends TrainingAbstract {

	/** Session's list. */
	private List<Session> sessionList;

	/** Session's start date. */
	private long startDate;

	/** Session's end date. */
	private long endDate;

	/**
	 * @return the sessionList
	 */
	public List<Session> getSessionList() {
		return sessionList;
	}

	/**
	 * @param sessionList
	 *            the sessionList to set
	 */
	public void setSessionList(List<Session> sessionList) {
		this.sessionList = sessionList;
	}

	/**
	 * @return the startDate
	 */
	public long getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public long getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

}
