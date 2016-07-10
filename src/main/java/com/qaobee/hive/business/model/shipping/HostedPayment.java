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

package com.qaobee.hive.business.model.shipping;

/**
 * The type Hosted payment.
 */
@Deprecated
public class HostedPayment {
    private String return_url; // NOSONAR
    private String cancel_url; // NOSONAR

    /**
     * Gets return url.
     *
     * @return the return url
     */
    public String getReturn_url() { // NOSONAR
        return return_url;
    }

    /**
     * Sets return url.
     *
     * @param return_url the return url
     */
    public void setReturn_url(String return_url) { // NOSONAR
        this.return_url = return_url;
    }

    /**
     * Gets cancel url.
     *
     * @return the cancel url
     */
    public String getCancel_url() { // NOSONAR
        return cancel_url;
    }

    /**
     * Sets cancel url.
     *
     * @param cancel_url the cancel url
     */
    public void setCancel_url(String cancel_url) { // NOSONAR
        this.cancel_url = cancel_url;
    }
}
