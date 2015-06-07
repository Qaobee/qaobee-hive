/*************************************************************************
 * 
 * Qaobee
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
package com.qaobee.business.model.commons.users.account;

import com.qaobee.business.model.commons.settings.Activity;


/**
 * The Class Plan.
 */
public class Plan {

	/** Payment Id. */
	private String paymentId;

	/** Level Plan. */
	private LevelPlan levelPlan;

	/** amount paid. */
	private long amountPaid;

	/** date Paid. */
	private long paidDate;

	/** start period date. */
	private long startPeriodDate;

	/** end Period date. */
	private long endPeriodDate;

	/** Status Payment. */
	private String status;

	/** periodicity. */
	private String periodicity;

	/** Activity. */
	private Activity activity;

	private String paiementURL;

	/**
	 * Gets the payment id.
	 *
	 * @return the paymentId
	 */
	public final String getPaymentId() {
		return paymentId;
	}

	/**
	 * Sets the payment id.
	 *
	 * @param paymentId
	 *            the paymentId to set
	 */
	public final void setPaymentId(final String paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * Gets the level plan.
	 *
	 * @return the levelPlan
	 */
	public final LevelPlan getLevelPlan() {
		return levelPlan;
	}

	/**
	 * Sets the level plan.
	 *
	 * @param levelPlan
	 *            the levelPlan to set
	 */
	public final void setLevelPlan(final LevelPlan levelPlan) {
		this.levelPlan = levelPlan;
	}

	/**
	 * Gets the amount paid.
	 *
	 * @return the amountPaid
	 */
	public final long getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Sets the amount paid.
	 *
	 * @param amountPaid
	 *            the amountPaid to set
	 */
	public final void setAmountPaid(final long amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * Gets the paid date.
	 *
	 * @return the paidDate
	 */
	public final long getPaidDate() {
		return paidDate;
	}

	/**
	 * Sets the paid date.
	 *
	 * @param paidDate
	 *            the paidDate to set
	 */
	public final void setPaidDate(final long paidDate) {
		this.paidDate = paidDate;
	}

	/**
	 * Gets the start period date.
	 *
	 * @return the startPeriodDate
	 */
	public final long getStartPeriodDate() {
		return startPeriodDate;
	}

	/**
	 * Sets the start period date.
	 *
	 * @param startPeriodDate
	 *            the startPeriodDate to set
	 */
	public final void setStartPeriodDate(final long startPeriodDate) {
		this.startPeriodDate = startPeriodDate;
	}

	/**
	 * Gets the end period date.
	 *
	 * @return the endPeriodDate
	 */
	public final long getEndPeriodDate() {
		return endPeriodDate;
	}

	/**
	 * Sets the end period date.
	 *
	 * @param endPeriodDate
	 *            the endPeriodDate to set
	 */
	public final void setEndPeriodDate(final long endPeriodDate) {
		this.endPeriodDate = endPeriodDate;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public final String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the status to set
	 */
	public final void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * Gets the periodicity.
	 *
	 * @return the periodicity
	 */
	public final String getPeriodicity() {
		return periodicity;
	}

	/**
	 * Sets the periodicity.
	 *
	 * @param periodicity
	 *            the periodicity to set
	 */
	public final void setPeriodicity(final String periodicity) {
		this.periodicity = periodicity;
	}

	/**
	 * Gets the activity.
	 *
	 * @return the activity
	 */
	public final Activity getActivity() {
		return activity;
	}

	/**
	 * Sets the activity.
	 *
	 * @param activity
	 *            the activity to set
	 */
	public final void setActivity(final Activity activity) {
		this.activity = activity;
	}

	/**
	 * @return the paiementURL
	 */
	public String getPaiementURL() {
		return paiementURL;
	}

	/**
	 * @param paiementURL
	 *            the paiementURL to set
	 */
	public void setPaiementURL(String paiementURL) {
		this.paiementURL = paiementURL;
	}
}
