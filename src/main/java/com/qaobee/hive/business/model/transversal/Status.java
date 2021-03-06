/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.business.model.transversal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.sandbox.effective.Availability;
import com.qaobee.hive.business.model.sandbox.effective.Laterality;
import com.qaobee.hive.business.model.sandbox.effective.PositionType;

/**
 * The type Status.
 *
 * @author jerome
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    /**
     * State form
     */
    private String stateForm;
    /**
     * Weight
     */
    private int weight;
    /**
     * Height
     */
    private int height;
    /**
     * Squad Number
     */
    private int squadnumber;
    /**
     * Position type
     */
    private PositionType positionType;
    /**
     * Laterality
     */
    private Laterality laterality;
    /**
     * Availability
     */
    private Availability availability;

    /**
     * Returns the state form.
     *
     * @return String : state form
     */
    public String getStateForm() {
        return stateForm;
    }

    /**
     * Defines the state form.
     *
     * @param stateForm (String) : state form
     */
    public void setStateForm(String stateForm) {
        this.stateForm = stateForm;
    }

    /**
     * Returns the weight.
     *
     * @return int : weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Defines the weight.
     *
     * @param weight (int) : weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Returns the height.
     *
     * @return int : height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Defines the height.
     *
     * @param height (int) : height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the squad number.
     *
     * @return int : squad number
     */
    public int getSquadnumber() {
        return squadnumber;
    }

    /**
     * Defines the squad number.
     *
     * @param squadnumber (int) : suad number
     */
    public void setSquadnumber(int squadnumber) {
        this.squadnumber = squadnumber;
    }

    /**
     * Returns the position type.
     *
     * @return PositionType : position type
     */
    public PositionType getPositionType() {
        return positionType;
    }

    /**
     * Defines the position type.
     *
     * @param positionType (PositionType) : position type
     */
    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    /**
     * Returns the laterality.
     *
     * @return String : laterality
     */
    public Laterality getLaterality() {
        return laterality;
    }

    /**
     * Defines the laterality.
     *
     * @param laterality (Laterality)  : laterality
     */
    public void setLaterality(Laterality laterality) {
        this.laterality = laterality;
    }

    /**
     * Returns the availability.
     *
     * @return Availability : bean that describes the availability
     */
    public Availability getAvailability() {
        return availability;
    }

    /**
     * Defines the availability.
     *
     * @param availability (Availability) : bean that describes availability
     */
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

}
