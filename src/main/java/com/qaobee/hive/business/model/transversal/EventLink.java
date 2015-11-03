package com.qaobee.hive.business.model.transversal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes an event link.
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventLink {

	/** Link identifier */
	private String linkId;
	/** Link type */
	private String type;

	/**
	 * Returns the link identifier.
	 * @return String : link ID
	 */
	public String getLinkId() {
		return linkId;
	}

	/**
	 * Defines the link identifier.
	 * @param linkId (String) : link ID
	 */
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	/**
	 * Returns the link type.
	 * @return String : type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Defines the link type
	 * @param type (String) : type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
