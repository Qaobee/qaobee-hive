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
import com.qaobee.hive.business.model.transversal.Audit;

/**
 * Class that represents an Activity.
 *
 * @author jerome
 */
@Deprecated
@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity {

    /**
     * Internal identifier.
     */
    private String _id; // NOSONAR

    /**
     * Code.
     */
    private String code;

    /**
     * Label.
     */
    private String label;

    /**
     * Label.
     */
    private boolean enable;

    /**
     * Type of activity.
     */
    private ActivityTypeEnum activityType;

    /**
     * audit CRUD object
     */
    private Audit audit;

    /**
     * Returns the internal identifier.
     *
     * @return String : id
     */
    public String get_id() {// NOSONAR
        return _id;
    }

    /**
     * Defines the internal identifier.
     *
     * @param _id (String) : id
     */
    public void set_id(final String _id) {// NOSONAR
        this._id = _id;
    }

    /**
     * Returns the code.
     *
     * @return String : activity code
     */
    public String getCode() {
        return code;
    }

    /**
     * Defines the code.
     *
     * @param code (String) : activity code
     */
    public void setCode(final String code) {
        this.code = code;
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
     * @param label (String) :  label
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Returns the type of activity.
     *
     * @return ActivityTypeEnum : type of activity
     */
    public ActivityTypeEnum getActivityType() {
        return activityType;
    }

    /**
     * Sets the type of activity.
     *
     * @param activityType (ActivityTypeEnum) : type of activity
     */
    public void setActivityType(final ActivityTypeEnum activityType) {
        this.activityType = activityType;
    }

    /**
     * Returns if the activity is enable.
     *
     * @return true if enable
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Defines if the activity is enable.
     *
     * @param enable (boolean) : true if enable
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * Returns the audit.
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
}
