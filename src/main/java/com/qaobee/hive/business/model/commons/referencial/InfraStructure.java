/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */
package com.qaobee.hive.business.model.commons.referencial;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.transversal.Address;
import com.qaobee.hive.business.model.transversal.Audit;

/**
 * Bean that describes infrastructure.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfraStructure {

    /**
     * Internal identifier
     */
    private String _id; // NOSONAR
    /**
     * Federation identifier
     */
    private String idFederation;
    /**
     * Area size
     */
    private String areaSize;
    /**
     * Area nature
     */
    private String areaNature;
    /**
     * Label
     */
    private String label;
    /**
     * Address
     */
    private Address address;
    /**
     * Structure ID
     */
    private String structureId;
    /**
     * audit CRUD object
     */
    private Audit audit;

    /**
     * Returns the internal identifier.
     *
     * @return String : ID
     */
    public String get_id() { // NOSONAR
        return _id;
    }

    /**
     * Defines the internal identifier.
     *
     * @param _id (String) ; ID
     */
    public void set_id(String _id) { // NOSONAR
        this._id = _id;
    }

    /**
     * Returns the label.
     *
     * @return String : label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Defines the label.
     *
     * @param label (String) : label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns the address.
     *
     * @return Address : address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Defines the address.
     *
     * @param address (Address) : address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns the auit.
     *
     * @return Audit : audit
     */
    public Audit getAudit() {
        return audit;
    }

    /**
     * Defines the audit.
     *
     * @param audit (Audit) : audit
     */
    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    /**
     * Returns federation identifier.
     *
     * @return String : Federation ID
     */
    public String getIdFederation() {
        return idFederation;
    }

    /**
     * Defines federation identifier.
     *
     * @param idFederation (String) : federation ID
     */
    public void setIdFederation(String idFederation) {
        this.idFederation = idFederation;
    }

    /**
     * Returns area size (00x00).
     *
     * @return String : size
     */
    public String getAreaSize() {
        return areaSize;
    }

    /**
     * Defines area size (00x00).
     *
     * @param areaSize (String) : size
     */
    public void setAreaSize(String areaSize) {
        this.areaSize = areaSize;
    }

    /**
     * Returns area nature.
     *
     * @return String : nature
     */
    public String getAreaNature() {
        return areaNature;
    }

    /**
     * Defines area nature.
     *
     * @param areaNature (String) : nature
     */
    public void setAreaNature(String areaNature) {
        this.areaNature = areaNature;
    }

    /**
     * Returns Qaobee structure identifier.
     *
     * @return String : structure ID
     */
    public String getStructureId() {
        return structureId;
    }

    /**
     * Defines Qaobee structure identifier.
     *
     * @param structureId (String) : structure ID
     */
    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

}
