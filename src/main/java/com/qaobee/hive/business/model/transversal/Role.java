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
 * Bean that describes a role.
 * @author Nada Vujanic-Maquin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {

    /** Code */
    private String code;
    /** Label */
    private String label;
    /** Order in list */
    private int order;

    /**
     * Constructor without parameter.
     */
    public Role() {
        // Declare a constructor without any initialization
    }
    
    /**
     * Constructor.
     * @param code (String) : code
     * @param label (String) : label
     */
    public Role(String code, String label) {
    	this.code = code;
    	this.label = label;
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
     * Returns the code.
     * @return String : code
     */
    public String getCode() {
        return code;
    }

    /**
     * Defines the code.
     * @param code (String) : code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Returns order in role list.
     * @return int : order
     */
	public int getOrder() {
		return order;
	}

	/**
	 * Defines order in role list.
	 * @param order (int) : order
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}