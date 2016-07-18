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
package com.qaobee.hive.business.model.sandbox.training;

import java.util.List;

/**
 * The Class Cycle.
 */
@Deprecated
public class SB_Cycle extends TrainingAbstract { // NOSONAR

    /**
     * Session's list.
     */
    private List<SB_Session> sessionList;

    /**
     * Session's start date.
     */
    private long startDate;

    /**
     * Session's end date.
     */
    private long endDate;

    /**
     * Gets session list.
     *
     * @return the sessionList
     */
    public List<SB_Session> getSessionList() {
        return sessionList;
    }

    /**
     * Sets session list.
     *
     * @param sessionList the sessionList to set
     */
    public void setSessionList(List<SB_Session> sessionList) {
        this.sessionList = sessionList;
    }

    /**
     * Gets start date.
     *
     * @return the startDate
     */
    public long getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the startDate to set
     */
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the endDate
     */
    public long getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the endDate to set
     */
    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

}
