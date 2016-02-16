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

package com.qaobee.hive.business.model.transversal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes a participant.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Participant {

	/**
	 * Internal identifier
	 */
	private String id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Structure ID
	 */
	private String StructureId;
	/**
	 * Participant Type
	 */
	private String type;

	/**
	 * Returns the participant identifier.
	 *
	 * @return String : ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Defines the participant identifier.
	 *
	 * @param id (String) : ID
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Returns the Structure identifier.
	 *
	 * @return String : structure ID
	 */
	public String getStructureId() {
		return StructureId;
	}

	/**
	 * Defines the structure identifier.
	 *
	 * @param structureId (String) : structure ID
	 */
	public void setStructureId(String structureId) {
		StructureId = structureId;
	}

	/**
	 * Returns the type.
	 *
	 * @return String : type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Defines the type.
	 *
	 * @param type (String) : type
	 */
	public void setType(String type) {
		this.type = type;
	}

}
