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

package com.qaobee.hive.business.model.commons.referencial.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Address;
import com.qaobee.hive.business.model.transversal.EventLink;
import com.qaobee.hive.business.model.transversal.Participant;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean that describes an event.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Event {

	/**
	 * Internal identifier
	 */
	private String _id; // NOSONAR
	/**
	 * Age category
	 */
	private CategoryAge categoryAge;
	/**
	 * Season
	 */
	private Season season;
	/**
	 * Activity
	 */
	private Activity activity;
	/**
	 * Event label
	 */
	private String label;
	/**
	 * Event type
	 */
	private String eventType;
	/**
	 * List of owners
	 */
	private List<String> owner;
	/**
	 * Start date
	 */
	private Long startDate;
	/**
	 * End date
	 */
	private Long endDate;
	/**
	 * List of participants
	 */
	private List<Participant> participants;
	/**
	 * Event address
	 */
	private Address address;
	/**
	 * Event Link
	 */
	private EventLink eventLink;

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
	 * @param _id (String) : id
	 */
	public void set_id(String _id) { // NOSONAR
		this._id = _id;
	}

	/**
	 * Returns the age category.
	 *
	 * @return CategoryAge : age category
	 */
	public CategoryAge getCategoryAge() {
		return categoryAge;
	}

	/**
	 * Defines the age category.
	 *
	 * @param categoryAge (CategoryAge) : age category
	 */
	public void setCategoryAge(CategoryAge categoryAge) {
		this.categoryAge = categoryAge;
	}

	/**
	 * Returns the season that event refers to.
	 *
	 * @return Season : season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * Defines the season that event refers to.
	 *
	 * @param season (Season) : season
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * Returns the activity that event refers to.
	 *
	 * @return Activity : activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * Defines the activity that event refers to.
	 *
	 * @param activity (Activity) : activity
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * Returns the event label.
	 *
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the event label.
	 *
	 * @param label (String) : label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the event type.
	 *
	 * @return String : type
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * Defines the event type.
	 *
	 * @param eventType (String) : type
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * Returns the list of owners.
	 *
	 * @return List(String) : list
	 */
	public List<String> getOwner() {
		return owner;
	}

	/**
	 * Defines the list of owners.
	 *
	 * @param owner (List(String)) : list
	 */
	public void setOwner(List<String> owner) {
		this.owner = owner;
	}

	/**
	 * Adds an owner to the list.
	 *
	 * @param owner (String) : owner ID
	 */
	public void addOwner(String owner) {
		if (this.owner == null) {
			this.owner = new ArrayList<>();
		}
		this.owner.add(owner);
	}

	/**
	 * Returns the start date.
	 *
	 * @return Long : start date
	 */
	public Long getStartDate() {
		return startDate;
	}

	/**
	 * Defines the start date.
	 *
	 * @param startDate (Long) : start date
	 */
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 *
	 * @return Long : end date
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * Defines the end date.
	 *
	 * @param endDate (Long) : end date
	 */
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the list of participants.
	 *
	 * @return List(Participant) : list of participants
	 */
	public List<Participant> getParticipants() {
		return participants;
	}

	/**
	 * Defines the list of participants.
	 *
	 * @param participants (List(Participant)) : list
	 */
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	/**
	 * Adds a participant to the list.
	 *
	 * @param participant (Participant) : participant to add
	 */
	public void addParticipant(Participant participant) {
		if (participants == null) {
			participants = new ArrayList<>();
		}
		participants.add(participant);
	}

	/**
	 * Returns the event address.
	 *
	 * @return Address : address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Defines the event address.
	 *
	 * @param address (Address) : address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Returns the event link.
	 *
	 * @return EventLink : link
	 */
	public EventLink getEventLink() {
		return eventLink;
	}

	/**
	 * Defines the event link.
	 *
	 * @param eventLink (EventLink) : link
	 */
	public void setEventLink(EventLink eventLink) {
		this.eventLink = eventLink;
	}

}
