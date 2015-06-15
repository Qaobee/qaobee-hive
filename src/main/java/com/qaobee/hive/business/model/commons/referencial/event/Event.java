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

import java.util.List;

import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Address;

/**
 * The Class Exercise.
 *
 * @author cke
 */
public class Event {

	/**
	 * The Event id.
	 */
	private String _id;
	
	/**
	 * categoryAge
	 */
	private CategoryAge categoryAge;

	/**
	 * the season code
	 */
	private Season season;
	
	/**
	 * the activity code
	 */
	private Activity activity;

	/**
	 * Label Event
	 */
	private String label;

	/**
	 * The event type
	 */
	private String eventType;

	/**
	 * List of owner event.
	 */
	private List<String> owner;

	/**
	 * Start date.
	 */
	private Long startDate;

	/**
	 * End date.
	 */
	private Long endDate;

	/**
	 * The participant list
	 */
	private List<Participant> participants;

	/**
	 * The event address
	 */
	private Address address;
	
	/**
	 * Event Link
	 */
	private EventLink eventLink;

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
	 * @return the categoryAge
	 */
	public CategoryAge getCategoryAge() {
		return categoryAge;
	}

	/**
	 * @param categoryAge the categoryAge to set
	 */
	public void setCategoryAge(CategoryAge categoryAge) {
		this.categoryAge = categoryAge;
	}

	/**
	 * @return the season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(Season season) {
		this.season = season;
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
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the owner
	 */
	public List<String> getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(List<String> owner) {
		this.owner = owner;
	}

	/**
	 * @return the startDate
	 */
	public Long getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the participants
	 */
	public List<Participant> getParticipants() {
		return participants;
	}

	/**
	 * @param participants the participants to set
	 */
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
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
	 * @return the eventLink
	 */
	public EventLink getEventLink() {
		return eventLink;
	}

	/**
	 * @param eventLink the eventLink to set
	 */
	public void setEventLink(EventLink eventLink) {
		this.eventLink = eventLink;
	}

	
}
