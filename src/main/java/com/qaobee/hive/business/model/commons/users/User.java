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
package com.qaobee.hive.business.model.commons.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.business.model.commons.users.account.Account;
import com.qaobee.hive.business.model.commons.users.communication.Notification;
import com.qaobee.hive.business.model.transversal.Address;
import com.qaobee.hive.business.model.transversal.Contact;

import java.util.List;

/**
 * Bean that describes a user.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String _id; // NOSONAR
    private String avatar;
    private String name;
    private String firstname;
    private Address address;
    private long birthdate;
    private Contact contact;
    private Country country;
    private Country nationality;
    private Account account;
    private long timestamp;
    private String gender;
    private List<Notification> notifications;
    private String sandboxDefault;

    /**
     * Returns the internal identifier.
     *
     * @return String : id
     */
    public String get_id() { // NOSONAR
        return _id;
    }

    /**
     * Defines the internal identifier.
     *
     * @param _id String : id
     */
    public void set_id(String _id) { // NOSONAR
        this._id = _id;
    }

    /**
     * Returns the avatar.
     *
     * @return String : avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Defines the avatar.
     *
     * @param avatar (String) : avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Returns the name of the user.
     *
     * @return String : name
     */
    public String getName() {
        return name;
    }

    /**
     * Defines the name of the user.
     *
     * @param name (String) : name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the firstname of the user.
     *
     * @return String : firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Defines the firstname of the user.
     *
     * @param firstname (String) : firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Returns the address of the user.
     *
     * @return Address : address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Defines the address of the user.
     *
     * @param address (Address) : address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns how to contact the user.
     *
     * @return Contact : contacts
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Defines how to contact the user.
     *
     * @param contact (Contact) : contacts
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * Returns the country.
     *
     * @return Country : country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Defines the country.
     *
     * @param country (Country) : country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Returns user account.
     *
     * @return Account : user account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Defines user account.
     *
     * @param account (Account) : account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Returns list of notifications.
     *
     * @return List(Notification) : list
     */
    public List<Notification> getNotifications() {
        return notifications;
    }

    /**
     * Defines the list of notifications.
     *
     * @param notifications (List(Notification)) : list
     */
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * Returns the timestamp.
     *
     * @return long : timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Defines the timestamp.
     *
     * @param timestamp (long) : timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns user gender.
     *
     * @return String : gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Defines user gender.
     *
     * @param gender (String) : gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns user nationality.
     *
     * @return Country : nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Defines user nationality.
     *
     * @param nationality (Country) : nationality
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     * Returns birthdate.
     *
     * @return long : birthdate
     */
    public long getBirthdate() {
        return birthdate;
    }

    /**
     * Defines birthdate.
     *
     * @param birthdate (long) : birthdate
     */
    public void setBirthdate(long birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Gets sandbox default.
     *
     * @return the sandboxDefault
     */
    public String getSandboxDefault() {
        return sandboxDefault;
    }

    /**
     * Sets sandbox default.
     *
     * @param sandboxDefault the sandboxDefault to set
     */
    public void setSandboxDefault(String sandboxDefault) {
        this.sandboxDefault = sandboxDefault;
    }
}
