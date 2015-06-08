package com.qaobee.hive.business.model.sandbox.training;

import java.util.Date;

import com.qaobee.hive.business.model.commons.users.User;

public class History {
	/**
	 * The author.
	 */
	private User author;
	/**
	 * The date.
	 */
	private Date date;
	/**
	 * The date.
	 */
	private String historyType;

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	

	/**
	 * @return the historyType
	 */
	public String getHistoryType() {
		return historyType;
	}

	/**
	 * @param historyType
	 *            the historyType to set
	 */
	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}

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

}
