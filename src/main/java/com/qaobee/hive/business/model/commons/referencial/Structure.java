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
package com.qaobee.hive.business.model.commons.referencial;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.business.model.transversal.Address;
import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.Contact;

/**
 * Bean that describes structure.
 * @author Nada Vujanic-Maquin
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Structure {

    /** Internal identifier */
    private String _id;
    /** Label */
    private String label;
    /** Structure's Acronym */
    private String acronym;
    /** activity code of the structure */
    private Activity activity;
    /** Address */
    private Address address;
    /** Contact */
    private Contact contact;
    /** Avatar */
    private String avatar;
    /** audit CRUD object */
    private Audit audit;
    /** country of the structure */
    private Country country;

    /**
     * Returns the internal identifier.
     * @return String : ID
     */
    public String get_id() {
        return _id;
    }

    /**
     * Defines the internal identifier.
     * @param _id (String) : ID
     */
    public void set_id(final String _id) {
        this._id = _id;
    }

    /**
     * Returns the label.
     * @return String : label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Defines the label.
     * @param label (String) : label
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Returns the acronym.
     * @return String : acronym
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Defines the acronym.
     * @param acronym (String) : acronym
     */
    public void setAcronym(final String acronym) {
        this.acronym = acronym;
    }

    /**
     * Returns the avatar.
     * @return String : avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Defines the avatar.
     * @param avatar (String) : avatar
     */
    public void setAvatar(final String avatar) {
        this.avatar = avatar;
    }

    /**
     * Returns the country.
     * @return Country : country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Defines the country.
     * @param country (Country) : country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Returns the activity.
     * @return Activity : activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Defines the activity.
     * @param activity (Activity) : activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Returns the address.
     * @return Address : address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Defines the address.
     * @param address (Address) : address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns the contact.
     * @return Contact : contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Defines the contact.
     * @param contact (Contact) : contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * Returns the audit.
     * @return Audit : audit
     */
    public Audit getAudit() {
        return audit;
    }

    /**
     * Defines the audit.
     * @param audit (Audit) : audit
     */
    public void setAudit(Audit audit) {
        this.audit = audit;
    }

}
