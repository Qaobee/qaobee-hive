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

/**
 * The class RoleStr.
 *
 * @author Nada Vujanic-Maquin
 */

public class Role {

    /**
     * The code.
     */
    private String code;

    /**
     * The label.
     */
    private String label;

    /**
     * Gets the label.
     *
     * @return the label
     */
    public final String getLabel() {
        return label;
    }

    /**
     * Sets the label.
     *
     * @param label the label to set
     */
    public final void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public final String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the code to set
     */
    public final void setCode(final String code) {
        this.code = code;
    }

}