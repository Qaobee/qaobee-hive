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
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SB_SandBox { // NOSONAR

    /**
     * Identifier
     */
    private String _id; // NOSONAR
    /**
     * Owner
     */
    private String owner;
    /**
     * Activity id
     */
    private String activityId;
    /**
     * Sandbox Cfg ID
     */
    private String sandboxCfgId;

    /**
     * Returns the owner.
     *
     * @return String : owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Defines the owner.
     *
     * @param owner (String) : owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Returns the activity ID.
     *
     * @return String : activity ID
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * Defines the activity ID.
     *
     * @param activityId (String) : activity ID
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * Returns the sandbox identifier.
     *
     * @return String : identifier
     */
    public String get_id() { // NOSONAR
        return _id;
    }

    /**
     * Defines the sandbox identifier.
     *
     * @param _id (String) : identifier
     */
    public void set_id(String _id) { // NOSONAR
        this._id = _id;
    }

    /**
     * Returns the sandbox config ID.
     *
     * @return String : sandbox config ID
     */
    public String getSandboxCfgId() {
        return sandboxCfgId;
    }

    /**
     * Defines the sandbox config ID.
     *
     * @param sandboxCfgId (String) : sandbox config ID
     */
    public void setSandboxCfgId(String sandboxCfgId) {
        this.sandboxCfgId = sandboxCfgId;
    }
}
