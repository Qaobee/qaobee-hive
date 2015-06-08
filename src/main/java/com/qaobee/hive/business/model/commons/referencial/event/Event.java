package com.qaobee.hive.business.model.commons.referencial.event;

import java.util.List;

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
	 * the season code
	 */
	private String seasonCode;

	/**
	 * Label Event
	 */
	private String label;

	/**
	 * The event type
	 */
	private String eventType;

	/**
	 * the event owner
	 */
	private EventOwner eventOwner;

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
	 * @return the _id
	 */
	public final String get_id() {
		return _id;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public final void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return the label
	 */
	public final String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public final void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the eventType
	 */
	public final String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public final void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the eventOwner
	 */
	public final EventOwner getEventOwner() {
		return eventOwner;
	}

	/**
	 * @param eventOwner
	 *            the eventOwner to set
	 */
	public final void setEventOwner(EventOwner eventOwner) {
		this.eventOwner = eventOwner;
	}

	/**
	 * @return the startDate
	 */
	public final Long getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public final void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public final Long getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public final void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the participants
	 */
	public final List<Participant> getParticipants() {
		return participants;
	}

	/**
	 * @param participants
	 *            the participants to set
	 */
	public final void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	/**
	 * @return the address
	 */
	public final Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public final void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the seasonCode
	 */
	public final String getSeasonCode() {
		return seasonCode;
	}

	/**
	 * @param seasonCode
	 *            the seasonCode to set
	 */
	public final void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

}
