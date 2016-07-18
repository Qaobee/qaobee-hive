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
 * [2014] Qaobee
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

import java.io.Serializable;

/**
 * Bean that describes habilitation.
 *
 * @author xavier
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Habilitation implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -961204023798043214L;

    /**
     * Internal identifier
     */
    private String _id; // NOSONAR
    /**
     * Key
     */
    private String key;
    /**
     * Description
     */
    private String description;

    /**
     * Returns the key.
     *
     * @return String : key
     */
    public String getKey() {
        return key;
    }

    /**
     * Defines the key.
     *
     * @param key (String) : key
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Returns the description.
     *
     * @return String : description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Defines the description.
     *
     * @param description (String) : description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns the internal identifier.
     *
     * @return String : ID
     */
    public String get_id() { // NOSONAR
        return _id;
    }

    /**
     * Defines the internal identifier.
     *
     * @param _id (String) : ID
     */
    public void set_id(final String _id) { // NOSONAR
        this._id = _id;
    }
}
