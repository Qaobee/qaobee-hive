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

package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Activity cfg dao.
 */
public interface ActivityCfgDAO {
    /**
     * Gets activity cfg params.
     *
     * @param activityId the activity id
     * @param countryId  the country id
     * @param dateRef    the date ref
     * @param paramField the param field
     * @return the activity cfg params
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getActivityCfgParams(String activityId, String countryId, Long dateRef, String paramField) throws QaobeeException;

    /**
     * Gets activity cfg.
     *
     * @param activityId the activity id
     * @param countryId  the country id
     * @param dateRef    the date ref
     * @return the activity cfg
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getActivityCfg(String activityId, String countryId, Long dateRef) throws QaobeeException;
}
