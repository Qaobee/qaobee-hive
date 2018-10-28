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
package com.qaobee.hive.business.model.sandbox.effective;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes Laterality.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Laterality {

    /**
     * Detail code. <br>
     */
    private String code;

    /**
     * Detail label. <br>
     */
    private String label;

    /**
     * Detail order. <br>
     */
    private Double order;

    /**
     * Constructor without parameter.
     */
    public Laterality() {
        // empty
    }

    /**
     * Constructor.
     *
     * @param code (String)
     * @param label (String)
     * @param order (Double)
     */
    public Laterality(String code, String label, Double order) {
        this.code = code;
        this.label = label;
        this.order = order;
    }

    /**
     * Returns the code.<br>
     *
     * @return String : code
     */
    public String getCode() {
        return code;
    }

    /**
     * Defines the code.<br>
     
     * @param code (String) : code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the label.<br>
     
     * @return String : label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Defines the label.<br>
    
     * @param label (String) : label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns the order.<br>
     *
     * @return Double : order
     */
    public Double getOrder() {
        return order;
    }

    /**
     * Defines the order.<br>
     *
     * @param order (Double) : order
     */
    public void setOrder(Double order) {
        this.order = order;
    }
}
