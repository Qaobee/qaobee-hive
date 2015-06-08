/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.business.model.sandbox.effective;

import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.business.model.transversal.Address;
import com.qaobee.hive.business.model.transversal.Contact;

/**
 * @author cke
 *
 */
public class Person {
	
	/**
	 * The _id.
	 */
	private String _id;

	/**
	 * The name.
	 */
	private String name;

	/**
	 * The firstname.
	 */
	private String firstname;

	/**
	 * The gender.
	 */
	private String gender;

	/**
	 * The birthdate.
	 */
	private long birthdate;

	/**
	 * The birthcity.
	 */
	private String birthcity;

	/**
	 * The birthcountry.
	 */
	private Country birthcountry;

	/**
	 * The nationality.
	 */
	private Country nationality;

	/**
	 * The address.
	 */
	private Address address;

	/**
	 * The contact.
	 */
	private Contact contact;

	/**
	 * The number.
	 */
	private String squadnumber;

	/**
	 * The avatar.
	 */
	private String avatar;

	/**
	 * the job of the person
	 */
	private String job;

	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthdate
	 */
	public long getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(long birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the birthcity
	 */
	public String getBirthcity() {
		return birthcity;
	}

	/**
	 * @param birthcity the birthcity to set
	 */
	public void setBirthcity(String birthcity) {
		this.birthcity = birthcity;
	}

	/**
	 * @return the birthcountry
	 */
	public Country getBirthcountry() {
		return birthcountry;
	}

	/**
	 * @param birthcountry the birthcountry to set
	 */
	public void setBirthcountry(Country birthcountry) {
		this.birthcountry = birthcountry;
	}

	/**
	 * @return the nationality
	 */
	public Country getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(Country nationality) {
		this.nationality = nationality;
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

	/**
	 * @return the squadnumber
	 */
	public String getSquadnumber() {
		return squadnumber;
	}

	/**
	 * @param squadnumber the squadnumber to set
	 */
	public void setSquadnumber(String squadnumber) {
		this.squadnumber = squadnumber;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

}
