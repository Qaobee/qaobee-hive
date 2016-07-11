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

package com.qaobee.hive.business.model.transversal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes an event link.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventLink {

    /**
     * Link identifier
     */
    private String linkId;
    /**
     * Link type
     */
    private String type;

    /**
     * Returns the link identifier.
     *
     * @return String : link ID
     */
    public String getLinkId() {
        return linkId;
    }

    /**
     * Defines the link identifier.
     *
     * @param linkId (String) : link ID
     */
    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    /**
     * Returns the link type.
     *
     * @return String : type
     */
    public String getType() {
        return type;
    }

    /**
     * Defines the link type
     *
     * @param type (String) : type
     */
    public void setType(String type) {
        this.type = type;
    }

}
