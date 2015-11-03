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

package com.qaobee.hive.business.model.sandbox.agenda;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Address;
import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.EventLink;
import com.qaobee.hive.business.model.transversal.Participant;
import com.qaobee.hive.business.model.transversal.Tag;

/**
 * Bean that describes a SandBox Event.
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SB_Event {

	/** Internal identifier */
	private String _id;
	/** Age category */
	private CategoryAge categoryAge;
	/** Season */
	private Season season;
	/** Activity */
	private Activity activity;
	/** Label */
	private String label;
	/** Event type */
	private String eventType;
	/** List of owners */
	private List<String> owner;
	/** Start date */
	private Long startDate;
	/** End date */
	private Long endDate;
	/** List of participants */
	private List<Participant> participants;
	/** Event address */
	private Address address;
	/** Event link */
	private EventLink eventLink;
	/** List of labels */
	private List<Tag> labels;
	/** Audit CRUD object */
	private Audit audit;

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
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * Returns the age category.
	 * @return Category : age category
	 */
	public CategoryAge getCategoryAge() {
		return categoryAge;
	}

	/**
	 * Defines the age category.
	 * @param categoryAge (CategoryAge) : age category
	 */
	public void setCategoryAge(CategoryAge categoryAge) {
		this.categoryAge = categoryAge;
	}

	/**
	 * Returns the season.
	 * @return Season : season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * Defines the season.
	 * @param season (Season) : season
	 */
	public void setSeason(Season season) {
		this.season = season;
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
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the event type.
	 * @return String : event type
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * Defines the event type.
	 * @param eventType (String) : event type
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * Returns the list of owners.
	 * @return List(String) : list of owners
	 */
	public List<String> getOwner() {
		return owner;
	}

	/**
	 * Defines the list of owners.
	 * @param owner (List(String)) : list of owners
	 */
	public void setOwner(List<String> owner) {
		this.owner = owner;
	}
	
	public void addOwner(String owner) {
		if(this.owner==null) {
			this.owner = new ArrayList<>();
		}
		this.owner.add(owner);
	}

	/**
	 * Returns start date.
	 * @return Long : start date
	 */
	public Long getStartDate() {
		return startDate;
	}

	/**
	 * Defines the start date.
	 * @param startDate (Long) : start date 
	 */
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * @return Long : end date
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * Defines the end date.
	 * @param endDate (Long) : end date
	 */
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the list of participants.
	 * @return List(Participant) : list
	 */
	public List<Participant> getParticipants() {
		return participants;
	}

	/**
	 * Defines the list of participants.
	 * @param participants (List(Participant)) : list
	 */
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	/**
	 * Adds a participant to the list.
	 * @param participant (Participant) : participant
	 */
	public void addParticipant(Participant participant) {
		if(participants==null) {
			participants = new ArrayList<>();
		}
		participants.add(participant);
	}
	
	/**
	 * Returns the address.
	 * @return Address  : address
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
	 * Returns the event link.
	 * @return EventLink : event link
	 */
	public EventLink getEventLink() {
		return eventLink;
	}

	/**
	 * Defines the event link.
	 * @param eventLink (EventLink) : event link 
	 */
	public void setEventLink(EventLink eventLink) {
		this.eventLink = eventLink;
	}

	/**
	 * Returns the list of tags/labels.
	 * @return List(Tag) : list
	 */
	public List<Tag> getLabels() {
		return labels;
	}

	/**
	 * Defines the list of labels/tags.
	 * @param labels (List(Tag)) : list
	 */
	public void setLabels(List<Tag> labels) {
		this.labels = labels;
	}
	
	/**
	 * Adds a label to the list.
	 * @param label (Tag) : label
	 */
	public void addLabel(Tag label) {
		if(labels==null) {
			labels = new ArrayList<>();
		}
		labels.add(label);
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
