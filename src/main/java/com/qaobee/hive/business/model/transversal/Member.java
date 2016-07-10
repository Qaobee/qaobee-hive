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
 * Bean that describes a member.
 *
 * @author Nada Vujanic-Maquin
 */
@Deprecated
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    /**
     * Person ID
     */
    private String personId;
    /**
     * Member role
     */
    private Role role;

    /**
     * Returns the person id.
     *
     * @return String : person ID
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * Defines the person id.
     *
     * @param personId (String) : person ID
     */
    public void setPersonId(final String personId) {
        this.personId = personId;
    }

    /**
     * Returns the role of the member.
     *
     * @return Role : role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Defines the role of the member.
     *
     * @param role (Role) : role
     */
    public void setRole(Role role) {
        this.role = role;
    }

}
