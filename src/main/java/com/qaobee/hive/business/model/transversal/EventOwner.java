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
 * Bean that describes an event owner.
 *
 * @author cke
 */
@Deprecated
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventOwner {

    /**
     * Owner identifier
     */
    private String ownerId;
    /**
     * Owner type
     */
    private String ownerType;

    /**
     * Returns the owner identifier.
     *
     * @return String : owner ID
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Defines the owner identifier.
     *
     * @param ownerId (String) : owener ID
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Returns the owner type.
     *
     * @return String : type
     */
    public String getOwnerType() {
        return ownerType;
    }

    /**
     * Defines the owner type.
     *
     * @param ownerType (String) : type
     */
    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

}
