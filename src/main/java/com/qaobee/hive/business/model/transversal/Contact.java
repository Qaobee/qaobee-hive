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

/**
 * Bean that descripbes how to contact.
 *
 * @author cke
 */
public class Contact {

    /**
     * Home phone number
     */
    private String home;
    /**
     * Office phone number
     */
    private String office;
    /**
     * Cellphone number
     */
    private String cellphone;
    /**
     * e-Mail
     */
    private String email;
    /**
     * Fax number
     */
    private String fax;
    /**
     * Web site
     */
    private String webSite;
    /**
     * Facebook account
     */
    private String facebook;
    /**
     * Google plus account
     */
    private String googlePlus;
    /**
     * Twitter account
     */
    private String twitter;

    /**
     * Instantiates a new Contact.
     */
    public Contact() {
        home = "";
        cellphone = "";
        office = "";
        email = "";
    }

    /**
     * Returns the web site URL.
     *
     * @return String : URL
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * Defines the web site URL.
     *
     * @param webSite (String) : URL
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    /**
     * Returns the facebook account.
     *
     * @return String : facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * Defines the facebook account.
     *
     * @param facebook (Stirng) : facebook
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * Returns the Google+ account.
     *
     * @return String : google+
     */
    public String getGooglePlus() {
        return googlePlus;
    }

    /**
     * Defines the Google+ account.
     *
     * @param googlePlus (String) : Google+
     */
    public void setGooglePlus(String googlePlus) {
        this.googlePlus = googlePlus;
    }

    /**
     * Returns the Twitter account.
     *
     * @return String : twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * Defines the Twitter account.
     *
     * @param twitter (String) : twitter
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * Returns the home phone number.
     *
     * @return String : phone number
     */
    public String getHome() {
        return home;
    }

    /**
     * Defines the home phone number.
     *
     * @param home (String) : phone number
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * Returns the office phone number.
     *
     * @return String : phone number
     */
    public String getOffice() {
        return office;
    }

    /**
     * Defines the office phone number.
     *
     * @param office (String) : phone number
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * Returns the cellphone number.
     *
     * @return String : phone number
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * Defines the cellphone number.
     *
     * @param cellphone (String) : phone number
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * Returns the e-Mail.
     *
     * @return String : e-Mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Defines the e-Mail.
     *
     * @param email (String) : e-Mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the fax number.
     *
     * @return String : fax number
     */
    public String getFax() {
        return fax;
    }

    /**
     * Defines the fax number.
     *
     * @param fax (String) : fax number
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

}
