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
package com.qaobee.hive.business.model.commons.settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class that defines a country.
 * @author Jerome
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    /** Internal identifier */
    private String _id;

    /** OSCE Code of the country */
    private int codeOSCE;

    /** Label code */
    private String label;
    
    /** Local */
    private String local;


    /**
     * Constructor.
     */
    public Country() {
    }

    /**
     * Returns the internal identifier.
     * @return String : id
     */
    public String get_id() {
        return _id;
    }

    /**
     * Defines the internal identifier.
     * @param _id (String) : id
     */
    public void set_id(final String _id) {
        this._id = _id;
    }

    /**
     * Returns the OSCE Code of the country.
     * @return int : OSCE Code
     */
    public int getCodeOSCE() {
        return codeOSCE;
    }

    /**
     * Defines the OSCE Code of the country.
     * @param codeOSCE (int) : OSCE Code
     */
    public void setCodeOSCE(final int codeOSCE) {
        this.codeOSCE = codeOSCE;
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
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Returns the local.
     * @return String : local
     */
    public String getLocal() {
        return local;
    }

    /**
     * Defines the local.
     * @param local (String) : local
     */
    public void setLocal(String local) {
        this.local = local;
    }
}
