/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.business.model.commons.referencial;

import com.qaobee.hive.business.model.transversal.Address;
import com.qaobee.hive.business.model.transversal.Audit;

/**
 * Bean that describes infrastructure.
 * @author cke
 */
public class InfraStructure {

	/** Internal identifier */
    private String _id;    
    /** Label */
    private String label;
    /** Address */
    private Address address;
    /** Structure of the Infrastructure */
    private Structure structure;
    /** audit CRUD object */
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
	 * @param _id (String) ; ID
	 */
	public void set_id(String _id) {
		this._id = _id;
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
	 * Returns the address.
	 * @return Address : address
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
	 * Returns the structure.
	 * @return Structure : structure
	 */
	public Structure getStructure() {
		return structure;
	}

	/**
	 * Defines the structure.
	 * @param structure (Structure) : structure
	 */
	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	/**
	 * Returns the auit.
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
