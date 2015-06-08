package com.qaobee.hive.business.model.commons.referencial.event;

/**
 * 
 * @author cke
 *
 */
public class Participant {

	/**
	 * the participant Id
	 */
	private String participantId;

	/**
	 * the participant Type.
	 */
	private String participantType;

	/**
	 * @return the participantId
	 */
	public final String getParticipantId() {
		return participantId;
	}

	/**
	 * @param participantId
	 *            the participantId to set
	 */
	public final void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	/**
	 * @return the participantType
	 */
	public final String getParticipantType() {
		return participantType;
	}

	/**
	 * @param participantType
	 *            the participantType to set
	 */
	public final void setParticipantType(String participantType) {
		this.participantType = participantType;
	}

}
