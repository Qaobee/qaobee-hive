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

package com.qaobee.hive.business.model.shipping;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Payment.
 * https://www.payplug.com/docs/api/?powershell#the-payment-object
 */
@Deprecated
public class Payment {
    private int amount;
    private boolean save_card; // NOSONAR
    private Customer customer;
    private HostedPayment hosted_payment; // NOSONAR
    private String notification_url; // NOSONAR
    private boolean force_3ds; // NOSONAR
    private String currency;
    private String payment_method; // NOSONAR
    private Map<String, String> metadata = new HashMap<>();

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Is save card boolean.
     *
     * @return the boolean
     */
    public boolean isSave_card() { // NOSONAR
        return save_card;
    }

    /**
     * Sets save card.
     *
     * @param save_card the save card
     */
    public void setSave_card(boolean save_card) { // NOSONAR
        this.save_card = save_card;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets hosted payment.
     *
     * @return the hosted payment
     */
    public HostedPayment getHosted_payment() { // NOSONAR
        return hosted_payment;
    }

    /**
     * Sets hosted payment.
     *
     * @param hosted_payment the hosted payment
     */
    public void setHosted_payment(HostedPayment hosted_payment) { // NOSONAR
        this.hosted_payment = hosted_payment;
    }

    /**
     * Gets notification url.
     *
     * @return the notification url
     */
    public String getNotification_url() { // NOSONAR
        return notification_url;
    }

    /**
     * Sets notification url.
     *
     * @param notification_url the notification url
     */
    public void setNotification_url(String notification_url) { // NOSONAR
        this.notification_url = notification_url;
    }

    /**
     * Is force 3 ds boolean.
     *
     * @return the boolean
     */
    public boolean isForce_3ds() { // NOSONAR
        return force_3ds;
    }

    /**
     * Sets force 3 ds.
     *
     * @param force_3ds the force 3 ds
     */
    public void setForce_3ds(boolean force_3ds) { // NOSONAR
        this.force_3ds = force_3ds;
    }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets payment method.
     *
     * @return the payment method
     */
    public String getPayment_method() { // NOSONAR
        return payment_method;
    }

    /**
     * Sets payment method.
     *
     * @param payment_method the payment method
     */
    public void setPayment_method(String payment_method) { // NOSONAR
        this.payment_method = payment_method;
    }

    /**
     * Gets metadata.
     *
     * @return the metadata
     */
    public Map<String, String> getMetadata() {
        return metadata;
    }

    /**
     * Sets metadata.
     *
     * @param metadata the metadata
     */
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
