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
package com.qaobee.hive.business.model.sandbox.stats;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes a stat.
 * @author cke | Jerome
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SB_Stats { // NOSONAR

    /** Internal identifier */
    private String _id; // NOSONAR
    /** Code */
    private String code;
    /** Chrono */
    private String chrono;
    /** Timer */
    private long timer;
    /** Owner */
    private String owner;
    /** List of producers */
    private List<String> producter;
    /** Sandbox ID */
    private String sandBoxId;
    /** Activity ID */
    private String activityId;
    /** Event identifier */
    private String eventId;
    /** Value */
    private String value;

    /**
     * Returns the internal identifier.
     * @return String : ID
     */
    public String get_id() { // NOSONAR
        return _id;
    }

    /**
     * Defines the internal identifier.
     * @param _id (String) : ID
     */
    public void set_id(String _id) { // NOSONAR
        this._id = _id;
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
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the chrono.
     * @return String : chrono
     */
    public String getChrono() {
        return chrono;
    }

    /**
     * Defines the chrono.
     * @param chrono (String) : chrono
     */
    public void setChrono(String chrono) {
        this.chrono = chrono;
    }

    /**
     * Returns the timer.
     * @return long : timer
     */
    public long getTimer() {
        return timer;
    }

    /**
     * Defines the timer.
     * @param timer (long) : timer
     */
    public void setTimer(long timer) {
        this.timer = timer;
    }

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
     * Returns the sandbox id.
     * @return String : ID
     */
    public String getSandBoxId() {
        return sandBoxId;
    }

    /**
     * Defines the SandBox ID.
     * @param sandBoxId (String) : ID
     */
    public void setSandBoxId(String sandBoxId) {
        this.sandBoxId = sandBoxId;
    }

    /**
     * Returns the activity id.
     * @return String : ID
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * Defines the activity id.
     * @param activityId (String) : ID
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * Returns the value.
     * @return String : value
     */
    public String getValue() {
        return value;
    }

    /**
     * Defines the value.
     * @param value (String) : value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the event id.
     * @return String : ID
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Defines the event id.
     * @param eventId (String) : ID
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * Returns list of producers.
     * @return List(String) : list
     */
    public List<String> getProducter() {
        return producter;
    }

    /**
     * Defines the list of producers.
     * @param producter (List(String)) : list
     */
    public void setProducter(List<String> producter) {
        this.producter = producter;
    }
    
    /**
     * Adds a producer to the list.
     * @param producter (String) : producer
     */
    public void addProducter(String producter) {
    	if(this.producter==null) {
    		this.producter = new ArrayList<>();
    	}
    	this.producter.add(producter);
    }
}