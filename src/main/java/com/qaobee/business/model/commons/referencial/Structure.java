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
package com.qaobee.business.model.commons.referencial;

import com.qaobee.business.model.commons.settings.Activity;
import com.qaobee.business.model.commons.settings.Country;
import com.qaobee.business.model.transversal.Address;
import com.qaobee.business.model.transversal.Contact;

/**
 * The Class Structure.
 *
 * @author Nada Vujanic-Maquin
 */

public class Structure {

    // Declaration des variables
    /**
     * The _id.
     */
    private String _id;

    /**
     * The label.
     */
    private String label;

    /**
     * Structure's Acronym.
     */
    private String acronym;

    /**
     * activity code of the structure.
     */
    private Activity activity;

    /**
     * The address.
     */
    private Address address;

    /**
     * The contact.
     */
    private Contact contact;

    /**
     * The avatar.
     */
    private String avatar;


    /**
     * country of the structure
     */
    private Country country;

    // Getters and Setters

    /**
     * Gets the _id.
     *
     * @return the _id
     */
    public final String get_id() {
        return _id;
    }

    /**
     * Sets the _id.
     *
     * @param _id the _id to set
     */
    public final void set_id(final String _id) {
        this._id = _id;
    }

    /**
     * Gets the label.
     *
     * @return the label
     */
    public final String getLabel() {
        return label;
    }

    /**
     * Sets the label.
     *
     * @param label the new label
     */
    public final void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Gets the acronym.
     *
     * @return the acronym
     */
    public final String getAcronym() {
        return acronym;
    }

    /**
     * Sets the acronym.
     *
     * @param acronym the acronym to set
     */
    public final void setAcronym(final String acronym) {
        this.acronym = acronym;
    }

    
    /**
     * Gets the avatar.
     *
     * @return the avatar
     */
    public final String getAvatar() {
        return avatar;
    }

    /**
     * Sets the avatar.
     *
     * @param avatar the avatar to set
     */
    public final void setAvatar(final String avatar) {
        this.avatar = avatar;
    }


    /**
     * @return the country
     */
    public final Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public final void setCountry(Country country) {
        this.country = country;
    }

	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
