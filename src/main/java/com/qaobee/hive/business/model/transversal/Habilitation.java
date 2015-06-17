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

import java.io.Serializable;

/**
 * The Class Habilitation.
 *
 * @author xavier
 */
public class Habilitation implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -961204023798043214L;

    /**
     * The _id.
     */
    private String _id;

    /**
     * The key.
     */
    private String key;

    /**
     * The description.
     */
    private String description;

    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the new key
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the _id.
     *
     * @return the _id
     */
    public String get_id() {
        return _id;
    }

    /**
     * Sets the _id.
     *
     * @param _id the _id to set
     */
    public void set_id(final String _id) {
        this._id = _id;
    }
}
