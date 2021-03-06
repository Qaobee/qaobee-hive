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

/**
 * The type Payment.
 */
public class Payment {
    private String id;
    private Card cardInfo;
    private String paymentId;
    private long paidDate;
    private long amountPaid;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets card info.
     *
     * @return the card info
     */
    public Card getCardInfo() {
        return cardInfo;
    }

    /**
     * Sets card info.
     *
     * @param cardInfo the card info
     */
    public void setCardInfo(Card cardInfo) {
        this.cardInfo = cardInfo;
    }

    /**
     * Gets payment id.
     *
     * @return the payment id
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Sets payment id.
     *
     * @param paymentId the payment id
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Gets paid date.
     *
     * @return the paid date
     */
    public long getPaidDate() {
        return paidDate;
    }

    /**
     * Sets paid date.
     *
     * @param paidDate the paid date
     */
    public void setPaidDate(long paidDate) {
        this.paidDate = paidDate;
    }

    /**
     * Gets amount paid.
     *
     * @return the amount paid
     */
    public long getAmountPaid() {
        return amountPaid;
    }

    /**
     * Sets amount paid.
     *
     * @param amountPaid the amount paid
     */
    public void setAmountPaid(long amountPaid) {
        this.amountPaid = amountPaid;
    }
}
