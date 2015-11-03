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
package com.qaobee.hive.business.model.sandbox.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes a SandBox.
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SB_SandBox {

    /** Owner */
    private String owner;
    /** Activity id */
    private String activityId;
    /** Structure id */
    private String structureId;

    /**
     * Returns the owner.
     * @return String : owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Defines the owner.
     * @param owner (String) : owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Returns the activity ID.
     * @return String : activity ID
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * Defines the activity ID.
     * @param activityId (String) : activity ID
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * Returns the structure ID.
     * @return String : structure ID
     */
    public String getStructureId() {
        return structureId;
    }

    /**
     * Defines the structure ID.
     * @param structureId (String) : structure ID
     */
    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }
}
