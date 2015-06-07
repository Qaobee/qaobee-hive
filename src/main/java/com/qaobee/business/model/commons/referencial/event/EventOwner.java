package com.qaobee.business.model.commons.referencial.event;

/**
 * 
 * @author cke
 *
 */
public class EventOwner {

	/**
	 * the owner Id
	 */
	private String ownerId;

	/**
	 * the owner Type.
	 */
	private String ownerType;

	/**
	 * @return the ownerId
	 */
	public final String getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            the ownerId to set
	 */
	public final void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the ownerType
	 */
	public final String getOwnerType() {
		return ownerType;
	}

	/**
	 * @param ownerType
	 *            the ownerType to set
	 */
	public final void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

}
