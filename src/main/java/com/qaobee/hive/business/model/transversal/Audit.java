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

/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.business.model.transversal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.users.User;

/**
 * Bean that describes audit trail.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Audit {

    /**
     * Author
     */
    private User author;
    /**
     * Create date
     */
    private long dateCreate;
    /**
     * Update date.
     */
    private long dateUpdate;

    /**
     * Returns the author.
     *
     * @return User : author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Defines the author.
     *
     * @param author (User) : author
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Returns the creation date.
     *
     * @return long : date
     */
    public long getDateCreate() {
        return dateCreate;
    }

    /**
     * Defines the creation date.
     *
     * @param dateCreate (long) : date
     */
    public void setDatCreate(long dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * Returns the updating date.
     *
     * @return long : date
     */
    public long getDatUpdate() {
        return dateUpdate;
    }

    /**
     * Defines the updating date.
     *
     * @param dateUpdate (long) : date
     */
    public void setDatUpdate(long dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

}
