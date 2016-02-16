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
package com.qaobee.hive.business.model.transversal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes an address.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    /**
     * The place.
     */
    private String place;

    /**
     * The zipcode.
     */
    private String zipcode;

    /**
     * The city.
     */
    private String city;

    /**
     * The country.
     */
    private String country;

    /**
     * latitude *.
     */
    private double lat;

    /**
     * longitude *.
     */
    private double lng;

    /**
     * The formated address.
     */
    private String formatedAddress;

    /**
     * Returns the address place.
     *
     * @return String : place
     */
    public String getPlace() {
        return place;
    }

    /**
     * Defines the address place.
     *
     * @param place String : place
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Returns the city name.
     *
     * @return String : city
     */
    public String getCity() {
        return city;
    }

    /**
     * Defines the city name.
     *
     * @param city (String) : city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the country name/label.
     *
     * @return String : country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Defines the country name/label.
     *
     * @param country (String) : country name
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the latitude.
     *
     * @return double : latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * Defines the latitude.
     *
     * @param lat (double) : lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Returns the longitude.
     *
     * @return double : longitude
     */
    public double getLng() {
        return lng;
    }

    /**
     * Defines the longitude.
     *
     * @param lng (double) : longitude
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * Returns the formated Google address.
     *
     * @return String : formated address
     */
    public String getFormatedAddress() {
        return formatedAddress;
    }

    /**
     * Defines the formated Google address.
     *
     * @param formatedAddress (String) : formated address
     */
    public void setFormatedAddress(String formatedAddress) {
        this.formatedAddress = formatedAddress;
    }

    /**
     * Returns the city zip code.
     *
     * @return String : zip code
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Defines the city zip code.
     *
     * @param zipcode (String) : zip code
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
