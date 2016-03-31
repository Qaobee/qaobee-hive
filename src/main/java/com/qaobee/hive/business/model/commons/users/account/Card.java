package com.qaobee.hive.business.model.commons.users.account;

/**
 * The type Card.
 */
public class Card {
    private String last4;
    private String country;
    private int exp_month;
    private int exp_year;
    private String brand;
    private String id;

    /**
     * Gets last 4.
     *
     * @return the last 4
     */
    public String getLast4() {
        return last4;
    }

    /**
     * Sets last 4.
     *
     * @param last4 the last 4
     */
    public void setLast4(String last4) {
        this.last4 = last4;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets exp month.
     *
     * @return the exp month
     */
    public int getExp_month() {
        return exp_month;
    }

    /**
     * Sets exp month.
     *
     * @param exp_month the exp month
     */
    public void setExp_month(int exp_month) {
        this.exp_month = exp_month;
    }

    /**
     * Gets exp year.
     *
     * @return the exp year
     */
    public int getExp_year() {
        return exp_year;
    }

    /**
     * Sets exp year.
     *
     * @param exp_year the exp year
     */
    public void setExp_year(int exp_year) {
        this.exp_year = exp_year;
    }

    /**
     * Gets brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets brand.
     *
     * @param brand the brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

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
}
