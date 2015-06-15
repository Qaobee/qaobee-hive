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
 * The Class Member.
 *
 * @author Nada Vujanic-Maquin
 */

public class Member {

    // Declaration des variables
    /**
     * The person.
     */
    private String personId;

    /**
     * The Function.
     */
    private Role role;

    // Getters and Setters

    /**
     * Gets the person id.
     *
     * @return the personId
     */
    public final String getPersonId() {
        return personId;
    }

    /**
     * Sets the person id.
     *
     * @param personId the personId to set
     */
    public final void setPersonId(final String personId) {
        this.personId = personId;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

}
