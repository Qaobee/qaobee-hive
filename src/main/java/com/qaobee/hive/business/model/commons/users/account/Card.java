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

import java.util.Map;

/**
 * The type Card.
 */
public class Card {
    private String last4;
    private String country;
    private int exp_month; // NOSONAR
    private int exp_year; // NOSONAR
    private String brand;
    private String id;
    private Map<String, String> metaDatas;

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
    public int getExp_month() { // NOSONAR
        return exp_month;
    }

    /**
     * Sets exp month.
     *
     * @param exp_month the exp month
     */
    public void setExp_month(int exp_month) { // NOSONAR
        this.exp_month = exp_month;
    }

    /**
     * Gets exp year.
     *
     * @return the exp year
     */
    public int getExp_year() { // NOSONAR
        return exp_year;
    }

    /**
     * Sets exp year.
     *
     * @param exp_year the exp year
     */
    public void setExp_year(int exp_year) { // NOSONAR
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

    /**
     * Gets meta datas.
     *
     * @return the meta datas
     */
    public Map<String, String> getMetaDatas() {
        return metaDatas;
    }

    /**
     * Sets meta datas.
     *
     * @param metaDatas the meta datas
     */
    public void setMetaDatas(Map<String, String> metaDatas) {
        this.metaDatas = metaDatas;
    }
}
