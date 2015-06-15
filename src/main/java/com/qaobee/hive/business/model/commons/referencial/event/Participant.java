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
	private String id;

	/**
	 * the participant Type.
	 */
	private String name;
	
	/**
	 * the StructureId
	 */
	private String StructureId;
	
	/**
	 * the participant Type.
	 */
	private String type;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the structureId
	 */
	public String getStructureId() {
		return StructureId;
	}

	/**
	 * @param structureId the structureId to set
	 */
	public void setStructureId(String structureId) {
		StructureId = structureId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	

}
