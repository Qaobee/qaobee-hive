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
package com.qaobee.hive.business.model.commons.users.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.Activity;


/**
 * Bean that describes a plan.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {

    /**
     * Payment Id
     */
    private String paymentId;
    /**
     * Level Plan
     */
    private LevelPlan levelPlan;
    /**
     * amount paid
     */
    private long amountPaid;
    /**
     * Payment date
     */
    private long paidDate;
    /**
     * Date of start period
     */
    private long startPeriodDate;
    /**
     * Date of end period
     */
    private long endPeriodDate;
    /**
     * Payment status
     */
    private String status;
    /**
     * Payment periodicity
     */
    private String periodicity;
    /**
     * Activity
     */
    private Activity activity;
    /**
     * Payment URL
     */
    private String paymentURL;
    private String cardId;

    /**
     * Gets card id.
     *
     * @return the card id
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * Sets card id.
     *
     * @param cardId the card id
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * Returns the payment id.
     *
     * @return String : payment
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Defines the payment id.
     *
     * @param paymentId (String) : id
     */
    public void setPaymentId(final String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Returns the level plan.
     *
     * @return LevelPlan : level plan
     */
    public LevelPlan getLevelPlan() {
        return levelPlan;
    }

    /**
     * Defines the level plan.
     *
     * @param levelPlan (LevelPlan) : level plan
     */
    public void setLevelPlan(final LevelPlan levelPlan) {
        this.levelPlan = levelPlan;
    }

    /**
     * Returns the amount paid.
     *
     * @return long : amount
     */
    public long getAmountPaid() {
        return amountPaid;
    }

    /**
     * Defines the amount paid.
     *
     * @param amountPaid (long) : amount
     */
    public void setAmountPaid(final long amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Returns the date of payment.
     *
     * @return the paidDate (long) : date
     */
    public long getPaidDate() {
        return paidDate;
    }

    /**
     * Defines the date of payment.
     *
     * @param paidDate (long) : date
     */
    public void setPaidDate(final long paidDate) {
        this.paidDate = paidDate;
    }

    /**
     * Returns the start date for the period.
     *
     * @return long : start date
     */
    public long getStartPeriodDate() {
        return startPeriodDate;
    }

    /**
     * Defines the start date for the period.
     *
     * @param startPeriodDate (long) : date
     */
    public void setStartPeriodDate(final long startPeriodDate) {
        this.startPeriodDate = startPeriodDate;
    }

    /**
     * Returns the end date for the period.
     *
     * @return long : date
     */
    public long getEndPeriodDate() {
        return endPeriodDate;
    }

    /**
     * Defines the end date for the period.
     *
     * @param endPeriodDate (long) : date
     */
    public void setEndPeriodDate(final long endPeriodDate) {
        this.endPeriodDate = endPeriodDate;
    }

    /**
     * Returns the status.
     *
     * @return String : status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Defines the status.
     *
     * @param status (String) : status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Returns the payment periodicity.
     *
     * @return String : periodicity
     */
    public String getPeriodicity() {
        return periodicity;
    }

    /**
     * Defines the payment periodicity.
     *
     * @param periodicity (String) : periodicity
     */
    public void setPeriodicity(final String periodicity) {
        this.periodicity = periodicity;
    }

    /**
     * Returns the activity.
     *
     * @return Activity : activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Defines the activity.
     *
     * @param activity (Activity) : activity
     */
    public void setActivity(final Activity activity) {
        this.activity = activity;
    }

    /**
     * Returns the payment URL.
     *
     * @return String : URL
     */
    public String getPaiementURL() {
        return paymentURL;
    }

    /**
     * Defines the payment URL.
     *
     * @param paymentURL (String) : URL
     */
    public void setPaiementURL(String paymentURL) {
        this.paymentURL = paymentURL;
    }
}
