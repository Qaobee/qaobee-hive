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

import com.qaobee.hive.business.model.commons.users.User;

import java.util.Date;

/**
 * The type History.
 */
public class History {
    /**
     * The author.
     */
    private User author;
    /**
     * The date.
     */
    private Date date;
    /**
     * The date.
     */
    private String historyType;

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets history type.
     *
     * @return the historyType
     */
    public String getHistoryType() {
        return historyType;
    }

    /**
     * Sets history type.
     *
     * @param historyType the historyType to set
     */
    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author to set
     */
    public void setAuthor(User author) {
        this.author = author;
    }

}
