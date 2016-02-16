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
package com.qaobee.hive.business.model.sandbox.effective;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.business.model.transversal.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean that describes a SandBox Person.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class SB_Person { // NOSONAR

	/**
	 * Internal identifier
	 */
	private String _id; // NOSONAR
	/**
	 * Name
	 */
	private String name;
	/**
	 * Firstname
	 */
	private String firstname;
	/**
	 * Gender
	 */
	private String gender;
	/**
	 * Birthdate
	 */
	private long birthdate;
	/**
	 * Birthcity
	 */
	private String birthcity;
	/**
	 * Birth country
	 */
	// TODO Country or String ?
	private Country birthcountry;
	/**
	 * Nationality
	 */
	// TODO Country or String ?
	private Country nationality;
	/**
	 * Address
	 */
	private Address address;
	/**
	 * Contact
	 */
	private Contact contact;
	/**
	 * Status
	 */
	private Status status;
	/**
	 * Avatar
	 */
	private String avatar;
	/**
	 * Job of the person
	 */
	private String job;
	/**
	 * List of labels
	 */
	private List<Tag> labels;
	/**
	 * audit CRUD object
	 */
	private Audit audit;
	/**
	 * Sandbox ID
	 */
	private String sandboxId;

	/**
	 * Returns the internal identifier.
	 *
	 * @return String : ID
	 */
	public String get_id() { // NOSONAR
		return _id;
	}

	/**
	 * Defines the internal identifier.
	 *
	 * @param _id (String) : ID
	 */
	public void set_id(String _id) { // NOSONAR
		this._id = _id;
	}

	/**
	 * Returns the name.
	 *
	 * @return String : name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Defines the name.
	 *
	 * @param name (String) : name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the firstname.
	 *
	 * @return String : firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Defines the firstname.
	 *
	 * @param firstname (String) : firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Returns the gender.
	 *
	 * @return String : gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Defines the gender.
	 *
	 * @param gender (String) : gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the birthdate.
	 *
	 * @return long : date
	 */
	public long getBirthdate() {
		return birthdate;
	}

	/**
	 * Defines the birthdate.
	 *
	 * @param birthdate (long) : date
	 */
	public void setBirthdate(long birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Returns the birthcity.
	 *
	 * @return String : city
	 */
	public String getBirthcity() {
		return birthcity;
	}

	/**
	 * Defines the birthcity.
	 *
	 * @param birthcity (String) : city
	 */
	public void setBirthcity(String birthcity) {
		this.birthcity = birthcity;
	}

	/**
	 * Returns the birth country.
	 *
	 * @return Country : country
	 */
	public Country getBirthcountry() {
		return birthcountry;
	}

	/**
	 * Defines the birth country.
	 *
	 * @param birthcountry (Country) : country
	 */
	public void setBirthcountry(Country birthcountry) {
		this.birthcountry = birthcountry;
	}

	/**
	 * Returns the nationality.
	 *
	 * @return Country : nationality
	 */
	public Country getNationality() {
		return nationality;
	}

	/**
	 * Defines the nationality.
	 *
	 * @param nationality (Country) : nationality
	 */
	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}

	/**
	 * Returns the address.
	 *
	 * @return Address : address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Defines the address.
	 *
	 * @param address (Address) : address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Returns how to contact the person.
	 *
	 * @return Contact : contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * Defines how to contact the person.
	 *
	 * @param contact (Contact) : contact
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
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
	 * Returns the job of the person.
	 *
	 * @return String : job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * Defines the job of the person.
	 *
	 * @param job (String) : job
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * Returns the list of labels.
	 *
	 * @return List(Tag) : list
	 */
	public List<Tag> getLabels() {
		return labels;
	}

	/**
	 * Defines the list of labels.
	 *
	 * @param labels (List(Tag)) : list
	 */
	public void setLabels(List<Tag> labels) {
		this.labels = labels;
	}

	/**
	 * Adds a label to the list.
	 *
	 * @param label (Tag) : label
	 */
	public void addLabel(Tag label) {
		if (labels == null) {
			labels = new ArrayList<>();
		}
		labels.add(label);
	}

	/**
	 * Returns the audit.
	 *
	 * @return Audit : audit
	 */
	public Audit getAudit() {
		return audit;
	}

	/**
	 * Defines the audit.
	 *
	 * @param audit (Audit) : audit
	 */
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	/**
	 * Returns the sandbox ID.
	 *
	 * @return String : _id
	 */
	public String getSandboxId() {
		return sandboxId;
	}

	/**
	 * Defines the sandbox ID.
	 *
	 * @param sandboxId (String) : _id
	 */
	public void setSandboxId(String sandboxId) {
		this.sandboxId = sandboxId;
	}

	/**
	 * Returns the status.
	 *
	 * @return Status : bean that describes the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Defines the status.
	 *
	 * @param status (Status) : bean that describes the status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

}
