package com.qaobee.hive.business.model.shipping;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Payment.
 * <p/>
 * https://www.payplug.com/docs/api/?powershell#the-payment-object
 */
public class Payment {
    private int amount;
    private boolean save_card;
    private Customer customer;
    private HostedPayment hosted_payment;
    private String notification_url;
    private boolean force_3ds;
    private String currency;
    private String payment_method;
    private Map<String, String> metadata = new HashMap<String, String>();

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
    public boolean isSave_card() {
        return save_card;
    }

    /**
     * Sets save card.
     *
     * @param save_card the save card
     */
    public void setSave_card(boolean save_card) {
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
    public HostedPayment getHosted_payment() {
        return hosted_payment;
    }

    /**
     * Sets hosted payment.
     *
     * @param hosted_payment the hosted payment
     */
    public void setHosted_payment(HostedPayment hosted_payment) {
        this.hosted_payment = hosted_payment;
    }

    /**
     * Gets notification url.
     *
     * @return the notification url
     */
    public String getNotification_url() {
        return notification_url;
    }

    /**
     * Sets notification url.
     *
     * @param notification_url the notification url
     */
    public void setNotification_url(String notification_url) {
        this.notification_url = notification_url;
    }

    /**
     * Is force 3 ds boolean.
     *
     * @return the boolean
     */
    public boolean isForce_3ds() {
        return force_3ds;
    }

    /**
     * Sets force 3 ds.
     *
     * @param force_3ds the force 3 ds
     */
    public void setForce_3ds(boolean force_3ds) {
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
    public String getPayment_method() {
        return payment_method;
    }

    /**
     * Sets payment method.
     *
     * @param payment_method the payment method
     */
    public void setPayment_method(String payment_method) {
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
